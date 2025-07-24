package delicacy.sys.business;

import java.io.StringReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import common.utils.UserInfoUtils;
import delicacy.common.BaseHelpUtils;
import delicacy.common.GenericProcessor;
import delicacy.connection.ThreadConnection;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import delicacy.servlet.AbstractProcessores;
import delicacy.sys.bean.BaseSystemProcess;
import delicacy.sys.bean.BaseSystemProcessActivity;
import delicacy.sys.bean.BaseSystemProcessAttention;
import delicacy.sys.bean.BaseSystemProcessInstanceActivity;
import delicacy.sys.bean.BaseSystemProcessLink;
import delicacy.sys.bean.BaseSystemProcessPooledTask;
import delicacy.sys.dao.SystemProcessAttention;
import delicacy.sys.dao.SystemProcessInstanceActivity;
import delicacy.sys.dao.SystemProcessInstanceWithSss;
import delicacy.sys.dao.SystemProcessPooledTask;

/**
 *
 * @author guangxun
 */
public class WorkFlowActivityProcessor implements GenericProcessor {

    private GenericWorkflowProcessor gwp = null;
    private final BaseSystemProcess bsp = null;
    private final List<BaseSystemProcessActivity> bspas = null;
    private final List<BaseSystemProcessLink> bspls = null;
    private BaseSystemProcessInstanceActivity currentInstanceActivity = null;

    @Override
    public String execute(String creteria, HttpServletRequest request) throws Exception {
    	int operatorId = UserInfoUtils.getEmployeeId(request);
    	int organizationId = UserInfoUtils.getOrganizationId(request);
    	if (operatorId == 0) {
    		throw new Exception("请重新登录后再申请本流程");
    	}
    	if(organizationId == 0) {
    		throw new Exception("获取组织代码失败");
    	}
        return execute(creteria, operatorId,organizationId);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public String execute(String creteria, int employeeId,int organizationId) throws Exception {
        // get employee ID, department ID and role ID from Cookie
        boolean completed = false;

        // get data from front-end
        JSON parser = new JSON(new StringReader(creteria));
        Map<String, Object> result = (Map<String, Object>) parser.parse(new BasicHandler());

        // current process instance activity
        currentInstanceActivity = new BaseSystemProcessInstanceActivity();
        currentInstanceActivity.setDataFromMap(result);

        ProcessInformation pi = ProcessUtil.getAllInformation(currentInstanceActivity.getProcessInstanceId());
        SystemProcessDefinition definition = pi.getProcessDefinition();
        ProcessInstance instance = pi.getProcessInstance();
        ProcessUtil.findCurrentInstanceActivity(instance, currentInstanceActivity);
        currentInstanceActivity.setProcessComment(BaseHelpUtils.getStringValue(result, "processComment"));
        int approvalment = BaseHelpUtils.getIntValue(result, "approvalment");
        int additionalEmployee = BaseHelpUtils.getIntValue(result, "additionalApprovalment");

        String executeClassName = AbstractProcessores
                .getWorkflowProcessorClass(definition.getProcessType().getProcessExecuteName());
        if (executeClassName == null) {
            throw new SQLException("process execute class not found");
        }
        gwp = (GenericWorkflowProcessor) Class.forName(executeClassName).newInstance();
        Map params = (Map) result.get("userData");
        params.put("organizationId", organizationId);
        ThreadConnection.beginTransaction();
        boolean resetProcessInstanceActivityId = false;
        boolean addNewInstanceActivities = false;
        switch (approvalment) {
            case 0: // 通过
                currentInstanceActivity.setStatus(SystemProcessConstants.ACTIVITY_STATUS_PASS);
                BaseSystemProcessInstanceActivity lastActivity = null;
                int activitySize = 0;
                for (BaseSystemProcessInstanceActivity a : instance.getActivities()) {
                    //改变当前节点的下一个节点的状态
                    if (Objects.equals(a.getActivityId(), currentInstanceActivity.getNextActivityId())) {
                        if (a.getNextActivityId() == null || a.getNextActivityId() == 0) {
                            a.setStatus(SystemProcessConstants.ACTIVITY_STATUS_PASS);
                            completed = true;
                        } else {
                            a.setStatus(SystemProcessConstants.ACTIVITY_STATUS_ACTIVE);
                            a.setInstanceActivityStartTime(new Date());
                            ProcessUtil.activePooledTaskAndAttentions(instance, a.getActivityId());
                        }
                    }
                    //改变当前节点的审批意见和状态
                    if (Objects.equals(a.getActivityId(), currentInstanceActivity.getActivityId())) {
                    	if(activitySize != 0) {
                    		lastActivity = instance.getActivities().get(activitySize - 1);
                    	}
                        a.setProcessComment(currentInstanceActivity.getProcessComment());
                        a.setStatus(SystemProcessConstants.ACTIVITY_STATUS_PASS);
                        a.setOperateTime(new Date());
                        ProcessUtil.passPooledTaskAndAttentions(instance, a.getActivityId(),
                                currentInstanceActivity.getProcessComment());
                    }
                    activitySize++;
                }
                if(null != lastActivity && Objects.equals(lastActivity.getStatus(), SystemProcessConstants.ACTIVITY_STATUS_RETURN_FIRST)) {
                	//如果上一个节点是回退给发起人，那么本次则是发起人重新修改数据后，通过节点审批，需要根据新的数据，重新生成新的流程
                	resetProcessInstanceActivityId = true;
                	completed = restartNewWorkflow(instance, pi, params, employeeId, definition, true,organizationId);
                }else {
	                if (!completed) {
	                    instance.setProcessStatus(SystemProcessConstants.PROCESS_STATUS_WORKING);
	                    completed = ProcessUtil.findNextAvailableActivity(currentInstanceActivity.getNextActivityId(), instance.getActivities());
	                }
                }
                break;
            case 1: // 驳回
                instance.setProcessStatus(SystemProcessConstants.PROCESS_STATUS_REJECT);
                instance.setCompleteTime(new Date());
                for (BaseSystemProcessInstanceActivity a : instance.getActivities()) {
                    if (Objects.equals(a.getActivityId(), currentInstanceActivity.getActivityId())) {
                        a.setEmployeeId(employeeId);
                        a.setProcessComment(currentInstanceActivity.getProcessComment());
                        a.setStatus(SystemProcessConstants.ACTIVITY_STATUS_REJECT);
                        a.setOperateTime(new Date());
                        ProcessUtil.passPooledTaskAndAttentions(instance, a.getActivityId(),
                                currentInstanceActivity.getProcessComment());
                    }
                }
                completed = true;
                break;
            case 2: // 回退
            	if(lastActivityIsReturnToDrafter(instance.getActivities(), currentInstanceActivity.getActivityId())) {
            		throw new SQLException("回退给申请人之后，申请人不能回退到上一个节点");
            	}
            	resetProcessInstanceActivityId = true;
                instance.setProcessStatus(SystemProcessConstants.PROCESS_STATUS_WORKING);
                List<ProcessInstanceActivity> nl = new ArrayList<>();
                ProcessInstanceActivity la = new ProcessInstanceActivity();
                ProcessInstanceActivity la0 = ProcessUtil.findBackInstanceActivity(instance, currentInstanceActivity);
                if (la0 == null) {
                    throw new SQLException("Could not found the back Node");
                }
                la0.cloneCopy(la);
                // 如果节点类型是申请人节点，则将该节点类型改成处理节点
                if (la.getActivityType() == 0) {
                    la.setActivityType(3);
                }
                la.setProcessInstanceActivityId(null);
                la.setStatus(SystemProcessConstants.ACTIVITY_STATUS_ACTIVE);
                la.setInstanceActivityStartTime(new Date());
                la.setTasks(la0.getTasks());
                if(null != la0.getTasks() && !la0.getTasks().isEmpty()) {
                	la.setEmployeeId(null);
                }
                la.setAttentions(la0.getAttentions());
                ProcessInstanceActivity ca = new ProcessInstanceActivity();
                for (ProcessInstanceActivity a : instance.getActivities()) {
                    nl.add(a);
                    if (Objects.equals(a.getActivityId(), currentInstanceActivity.getActivityId())) {
                        a.cloneCopy(ca);
                        a.setEmployeeId(employeeId);
                        a.setProcessComment(currentInstanceActivity.getProcessComment());
                        a.setStatus(SystemProcessConstants.ACTIVITY_STATUS_RETURN_LAST);
                        ca.setProcessInstanceActivityId(null);
                        ca.setStatus(SystemProcessConstants.ACTIVITY_STATUS_NEW_CREATED);
                        ProcessUtil.passPooledTaskAndAttentions(instance, a.getActivityId(),
                                currentInstanceActivity.getProcessComment());
                        ca.setTasks(a.getTasks());
                        ca.setAttentions(a.getAttentions());
                        a.setOperateTime(new Date());
                        if(null != a.getTasks() && !a.getTasks().isEmpty()) {
                        	ca.setEmployeeId(null);
                        }
                        nl.add(la);
                        nl.add(ca);
                    }
                }
                instance.setActivities(nl);
                ProcessUtil.resetActivityId(instance, nl);
                ProcessUtil.newCreateTaskToActivity(ca);
                ProcessUtil.newCreateTaskToActivity(la);
                ProcessUtil.newCreateAttentionToActivity(ca);
                ProcessUtil.newCreateAttentionToActivity(la);
                if (ca.getTasks() != null && !ca.getTasks().isEmpty()) {
                    instance.getTasks().addAll(ca.getTasks());
                }
                if (la.getTasks() != null && !la.getTasks().isEmpty()) {
                    instance.getTasks().addAll(la.getTasks());
                }
                if (ca.getAttentions() != null && !ca.getAttentions().isEmpty()) {
                    instance.getAttentions().addAll(ca.getAttentions());
                }
                if (la.getAttentions() != null && !la.getAttentions().isEmpty()) {
                    instance.getAttentions().addAll(la.getAttentions());
                }
                ProcessUtil.activePooledTaskAndAttentions(instance, la.getActivityId());
                break;
            case 3: // 转交
                instance.setProcessStatus(SystemProcessConstants.PROCESS_STATUS_WORKING);
                List<ProcessInstanceActivity> nl1 = new ArrayList<>();
                ProcessInstanceActivity la1 = new ProcessInstanceActivity();
                for (ProcessInstanceActivity a : instance.getActivities()) {
                    nl1.add(a);
                    if (Objects.equals(a.getActivityId(), currentInstanceActivity.getActivityId())) {
                        a.setEmployeeId(employeeId);
                        a.cloneCopy(la1);
                        la1.setProcessInstanceActivityId(null);
                        la1.setStatus(SystemProcessConstants.ACTIVITY_STATUS_ACTIVE);
                        la1.setInstanceActivityStartTime(new Date());
                        la1.setEmployeeId(additionalEmployee);
                        a.setProcessComment(currentInstanceActivity.getProcessComment());
                        ProcessUtil.passPooledTaskAndAttentions(instance, a.getActivityId(),
                                currentInstanceActivity.getProcessComment());
                        a.setStatus(SystemProcessConstants.ACTIVITY_STATUS_REASSIGN);
                        a.setOperateTime(new Date());
                        nl1.add(la1);
                    }
                }
                instance.setActivities(nl1);
                ProcessUtil.resetActivityId(instance, nl1);
                if(lastActivityIsReturnToDrafter(instance.getActivities(), currentInstanceActivity.getActivityId())) {
                	//如果上一个节点是回退给发起人，那么本次则是发起人重新修改数据后，转交给其他员工（相对于变相添加审批人），需要根据新的数据，重新生成新的流程
                	resetProcessInstanceActivityId = true;
                	completed = restartNewWorkflow(instance, pi, params, employeeId, definition, false,organizationId);
                }else {
                	completed = ProcessUtil.findNextAvailableActivity(currentInstanceActivity.getNextActivityId(), instance.getActivities());
                }
                break;
            case 4://	回退给发起人
            	if(lastActivityIsReturnToDrafter(instance.getActivities(), currentInstanceActivity.getActivityId())) {
            		throw new SQLException("回退给申请人之后，申请人不能继续回退给申请人");
            	}
            	List<ProcessInstanceActivity> originalActivityList = pi.getProcessInstance().getActivities();
            	List<ProcessInstanceActivity> newActivityList = new ArrayList<>();
            	List<BaseSystemProcessAttention> newAttentionList = new ArrayList<>();
            	List<BaseSystemProcessPooledTask> newTaskList = new ArrayList<>();
            	int i = 0;
            	ProcessInstanceActivity drafterActivity = null;
            	for(ProcessInstanceActivity activity : originalActivityList) {
            		newActivityList.add(activity);
            		if(null != activity.getAttentions() && !activity.getAttentions().isEmpty()) {
            			newAttentionList.addAll(activity.getAttentions());
            		}
            		if(null != activity.getTasks() && !activity.getTasks().isEmpty()) {
            			newTaskList.addAll(activity.getTasks());
            		}
    				if(i == 0) {
    					//只复制申请人节点
            			drafterActivity = new ProcessInstanceActivity();
            			activity.cloneCopy(drafterActivity);
                        drafterActivity.setProcessInstanceActivityId(null);
                    	drafterActivity.setActivityType(SystemProcessConstants.ACTIVITY_TYPE_PROCESS);
                    	drafterActivity.setInstanceActivityStartTime(new Date());
                    	drafterActivity.setStatus(SystemProcessConstants.ACTIVITY_STATUS_ACTIVE);
                        drafterActivity.setOperateTime(null);
	            	}
            		if(Objects.equals(activity.getActivityId(), currentInstanceActivity.getActivityId())) {
            			activity.setStatus(SystemProcessConstants.ACTIVITY_STATUS_RETURN_FIRST);
            			activity.setEmployeeId(employeeId);
            			activity.setOperateTime(new Date());
            			newActivityList.add(drafterActivity);//加上申请人节点
            			newActivityList.add(originalActivityList.get(originalActivityList.size() - 1));//加上结束节点
            			ProcessUtil.passPooledTaskAndAttentions(instance, activity.getActivityId(),
                                currentInstanceActivity.getProcessComment());
            			break;
            		}
            		i++;
            	}
            	instance.setActivities(newActivityList);
            	ProcessUtil.resetActivityId(instance, newActivityList);
            	instance.setActivities(newActivityList);
            	instance.setAttentions(newAttentionList);
            	instance.setTasks(newTaskList);
                break;
        }
        if (!completed) {
            instance.setProcessStatus(SystemProcessConstants.PROCESS_STATUS_WORKING);
        } else {
            if (approvalment == 0 || approvalment == 3) {//同意和转交存在自动跳过节点的情况，所以流程都有可能跑完
                instance.setProcessStatus(SystemProcessConstants.PROCESS_STATUS_COMPLETED);
            } else if (approvalment == 1) {
                instance.setProcessStatus(SystemProcessConstants.PROCESS_STATUS_REJECT);
            }
            instance.setCompleteTime(new Date());
        }
        SystemProcessInstanceWithSss spiws = new SystemProcessInstanceWithSss();
        spiws.setPrimaryKeyFromBase(instance);
        spiws.load();
        spiws.setDataFromBase(instance);
        spiws.setDetailSystemProcessInstanceActivity(ProcessInstanceActivity.toBaseList(instance.getActivities()));
        spiws.setDetailSystemProcessAttention(instance.getAttentions());
        spiws.setDetailSystemProcessPooledTask(instance.getTasks());
        spiws.update();
        //如果需要重新设置任务池和知会节点所关联的流程实例节点ID
    	if(resetProcessInstanceActivityId) {
        	List<ProcessInstanceActivity> piaList = instance.getActivities();
        	int currentActivityId = BaseHelpUtils.getIntValue(currentInstanceActivity.getActivityId());
        	for(ProcessInstanceActivity piaBean : piaList) {
        		if(BaseHelpUtils.getIntValue(piaBean.getActivityId()) <= currentActivityId) {
        			continue;
        		}
        		List<BaseSystemProcessPooledTask> taskList = piaBean.getTasks();
        		List<BaseSystemProcessAttention> attentionList = piaBean.getAttentions();
        		boolean needToResetInstanceActivityId = false;
        		if(null != taskList && !taskList.isEmpty() && BaseHelpUtils.getIntValue(taskList.get(0).getInstanceActivityId()) != -1) {
        			needToResetInstanceActivityId = true;
        		}
        		if(!needToResetInstanceActivityId && null != attentionList && !attentionList.isEmpty() && BaseHelpUtils.getIntValue(attentionList.get(0).getInstanceActivityId()) != -1) {
        			needToResetInstanceActivityId = true;
        		}
        		if(needToResetInstanceActivityId) {
        			continue;
        		}
        		Integer newInstanceActivityId = null;
	        	SystemProcessInstanceActivity spiaDao = new SystemProcessInstanceActivity();
	        	spiaDao.setConditionActivityId("=", piaBean.getActivityId());
	        	spiaDao.setConditionProcessInstanceId("=", currentInstanceActivity.getProcessInstanceId());
	        	spiaDao.unsetSelectFlags();
	        	spiaDao.setSelectProcessInstanceActivityId(true);
	        	BaseSystemProcessInstanceActivity spiaBean = spiaDao.executeQueryOneRow();
	        	if(null != spiaBean) {
	        		newInstanceActivityId = spiaBean.getProcessInstanceActivityId();
	        	}
	        	if(null == newInstanceActivityId) {
	        		continue;
	        	}
        		if(null != taskList && !taskList.isEmpty()) {
        			SystemProcessPooledTask spptDao = new SystemProcessPooledTask();
		        	spptDao.setConditionActivityId("=", piaBean.getActivityId());
		        	spptDao.setConditionInstanceActivityId("=", -1);
		        	spptDao.setInstanceActivityId(newInstanceActivityId);
		        	spptDao.conditionalUpdate();
        		}
        		if(null != attentionList && !attentionList.isEmpty()) {
        			SystemProcessAttention spaDao = new SystemProcessAttention();
        			spaDao.setConditionActivityId("=", piaBean.getActivityId());
        			spaDao.setConditionInstanceActivityId("=", -1);
        			spaDao.setInstanceActivityId(newInstanceActivityId);
        			spaDao.conditionalUpdate();
        		}
        	}
        }
        
        if (currentInstanceActivity.getActivityType() != null) {
            ProcessInstanceActivity currentActivity = new ProcessInstanceActivity();
            currentInstanceActivity.cloneCopy(currentActivity);
            currentActivity.setProcessComment(currentInstanceActivity.getProcessComment());
            instance.setCurrentActivity(currentActivity);
            //如果流程根据业务数据，重新生成了新的流程节点，则说明前面已经执行过此方法了，此处直接跳过
            if(!addNewInstanceActivities) {
            	gwp.executeActivity(pi, params, employeeId, false, completed);
            }
        }
        ThreadConnection.commit();
        return spiws.generateBaseExt().toOneLineJSON();
    }

    /**
     * 判断上一个节点是不是回退给申请人节点，如果是，返回true; 否则返回false
     * @param activities
     * @param currentActivityId
     * @return
     */
    public boolean lastActivityIsReturnToDrafter(List<ProcessInstanceActivity> activities, Integer currentActivityId) {
    	if(null == activities || activities.isEmpty()) {
    		return false;
    	}
    	if(null == currentActivityId) {
    		return false;
    	}
    	int i = 0;
        for (BaseSystemProcessInstanceActivity bean : activities) {
        	if(Objects.equals(bean.getActivityId(), currentActivityId)) {
        		BaseSystemProcessInstanceActivity lastActivity = activities.get(i - 1);
        		if(Objects.equals(lastActivity.getStatus(), SystemProcessConstants.ACTIVITY_STATUS_RETURN_FIRST)) {
        			return true;
        		}else {
        			return false;
        		}
        	}
        	i++;
        }
    	return false;
    }
    
    public boolean restartNewWorkflow(ProcessInstance instance, ProcessInformation pi, Map params, Integer employeeId, SystemProcessDefinition definition, boolean needToActiveFirstActivity,int organizationId) throws Exception {
    	boolean completed = false;
    	List<ProcessInstanceActivity> newActivityList = instance.getActivities();
    	newActivityList.remove(newActivityList.size() - 1);//去掉结束节点
    	ProcessInstanceActivity secondActivity = newActivityList.get(1);//取流程原有节点的第二个节点
    	boolean additionalApprovalment = false;//是否有额外审批人
    	if(null != secondActivity.getProcessActivityId() && secondActivity.getProcessActivityId().intValue() == 0) {
    		//如果第二个节点是申请人添加的“额外审批人”，则新流程中也需要加入这个节点
    		additionalApprovalment = true;
    		ProcessInstanceActivity newActivity = new ProcessInstanceActivity();
			secondActivity.cloneCopy(newActivity);
            newActivity.setProcessInstanceActivityId(null);
            if(needToActiveFirstActivity) {
            	newActivity.setStatus(SystemProcessConstants.ACTIVITY_STATUS_ACTIVE);
            }else {
            	newActivity.setStatus(SystemProcessConstants.ACTIVITY_STATUS_NEW_CREATED);
            }
            newActivity.setOperateTime(null);
            newActivityList.add(newActivity);
            newActivity.setActivityId(newActivityList.size() - 1);
            newActivity.setInstanceActivityStartTime(new Date());
            newActivity.setNextActivityId(newActivityList.size());
    	}
    	List<BaseSystemProcessAttention> newAttentionList = instance.getAttentions();
    	List<BaseSystemProcessPooledTask> newTaskList = instance.getTasks();
    	gwp.executeActivity(pi, params, employeeId, false, completed);
    	int idx = newActivityList.size();
    	List<ProcessInstanceActivity> newCreateActivities = new ArrayList<>();//新建流程需要发起的节点，开始节点和添加审批人节点除外，因为这两个节点已经从旧数据中复制了
    	List<BaseSystemProcessPooledTask> tasks = new ArrayList<>();
        boolean jumpOver = false;
        boolean pooled = false;
        ProcessInstanceActivity ia = null;
        BaseSystemProcessActivity bspalast = ProcessUtil.firstActivity(definition);
        boolean workFlowCompleted = false;
        while (true) {
            jumpOver = false;
            pooled = false;
            BaseSystemProcessActivity bspa = ProcessUtil.findNextActivity(definition, gwp, bspalast);
            if (bspa.getActivitySerialNo() == SystemProcessConstants.PROCESS_LAST_ACTIVITY) {
            	workFlowCompleted = true;
            }
            ia = ProcessUtil.createInstanceActivity(definition.getProcessId(), instance.getProcessInstanceId(),
                    definition.getProcessTypeId(), instance.getBusinessId(),
                    instance.getBusinessName(),organizationId);
            ia.setProcessActivityId(bspa.getProcessActivityId());
            ia.setActivityType(bspa.getActivityType());
            ia.setBackViewName(bspa.getActivityName());
            ia.setInstanceActivityCreateTime(new Date());
            ia.setActivityId(idx);
            if (workFlowCompleted) {
                ia.setNextActivityId(0);
                ia.setStatus(SystemProcessConstants.ACTIVITY_STATUS_PASS);
                ia.setEmployeeId(currentInstanceActivity.getEmployeeId());
                ia.setOperateTime(new Date());
            } else {
                if(idx == newActivityList.size()) {
                	if(needToActiveFirstActivity) {
                		//如果是第一个节点，且看是否有额外审批人，如果有，则本节点设置为新建状态；没有，则设置为活跃状态
                		if(additionalApprovalment) {
                    		ia.setStatus(SystemProcessConstants.ACTIVITY_STATUS_NEW_CREATED);
                    	}else {
                    		ia.setStatus(SystemProcessConstants.ACTIVITY_STATUS_ACTIVE);
                    		ia.setInstanceActivityStartTime(new Date());
                    	}
                    }else {
                    	//如果不需要设置第一个节点为活跃节点，则所有节点都设置为新建状态
                    	ia.setStatus(SystemProcessConstants.ACTIVITY_STATUS_NEW_CREATED);
                    }
                	
                }else {
                	//不是第一个节点，则全都设置为新建状态
                	ia.setStatus(SystemProcessConstants.ACTIVITY_STATUS_NEW_CREATED);
                }
                ia.setNextActivityId(++idx);
                int[] nextEmployeeIds = gwp.findActivityOwner(instance.getEmployeeId(),
                        bspa.getEmployeeId() == 0 || bspa.getEmployeeId() == null ? null : bspa.getEmployeeId(),
                        bspa.getDepartmentId() == 0 || bspa.getDepartmentId() == null ? null
                        : bspa.getDepartmentId(),
                        bspa.getRoleId() == 0 || bspa.getRoleId() == null ? null : bspa.getRoleId(),
                        bspa.getPoolType());
                if (nextEmployeeIds == null || nextEmployeeIds.length == 0) {
                    BaseSystemProcessActivity processActivity = ProcessUtil
                            .findProcessActivityById(definition, ia.getProcessActivityId());
                    if (processActivity == null || !processActivity.getIfAllowOver()) {
                        throw new SQLException("流程节点（" + bspa.getActivityName() + "）找不到审批人员");
                    } else {
                        jumpOver = true;
                    }
                }
                if (!jumpOver) {
                    if (nextEmployeeIds.length == 1) {
                        ia.setEmployeeId(nextEmployeeIds[0]);
                    } else {
                        ia.setPoolType(bspa.getPoolType());
                        ia.setEmployeeId(null);
                        for (Integer ii : nextEmployeeIds) {
                            BaseSystemProcessPooledTask task = ProcessUtil.createPooledTask(ia);
                            task.setEmployeeId(ii);
                            task.setInstanceActivityId(-1);
                            tasks.add(task);
                        }
                    }
                    ia.setOperateTime(null);
                    ia.setProcessComment(null);
                }
            }
            if (!pooled) {
                if (!jumpOver) {
                	newCreateActivities.add(ia);
                } else {
                    idx--;
                }
            }
            if (workFlowCompleted) {
                break;
            }
            bspalast = bspa;
        }
        ProcessUtil.processInstanceActivity(newCreateActivities, definition);
        // 算出每个流程实例节点下面对应的知会人节点
        List<BaseSystemProcessAttention> attentions = ProcessUtil.generateAllActivityAttentions(newCreateActivities,
                gwp, definition, instance.getEmployeeId());
        for(BaseSystemProcessAttention obj : attentions) {
        	obj.setInstanceActivityId(-1);
        }
        // 找出每个流程实例节点下面的任务池节点
        ProcessUtil.findOutAllTasks(newCreateActivities, tasks);
        newActivityList.addAll(newCreateActivities);
        newAttentionList.addAll(attentions);
        newTaskList.addAll(tasks);
    	instance.setActivities(newActivityList);
    	instance.setAttentions(newAttentionList);
    	instance.setTasks(newTaskList);
    	return completed;
    }
    
}
