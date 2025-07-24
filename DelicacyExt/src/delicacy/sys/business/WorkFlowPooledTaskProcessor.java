package delicacy.sys.business;

import java.io.StringReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import delicacy.common.BaseHelpUtils;
import delicacy.common.GenericProcessor;
import delicacy.connection.ThreadConnection;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import delicacy.servlet.AbstractProcessores;
import delicacy.servlet.HttpCookie;
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
public class WorkFlowPooledTaskProcessor implements GenericProcessor {

    private GenericWorkflowProcessor gwp = null;
    private final BaseSystemProcess bsp = null;
    private final List<BaseSystemProcessActivity> bspas = null;
    private final List<BaseSystemProcessLink> bspls = null;
    private ProcessInstanceActivity currentInstanceActivity = null;
    private BaseSystemProcessPooledTask currentPooledTask = null;

    @Override
    public String execute(String creteria, HttpServletRequest request) throws Exception {
        HttpCookie cookies = new HttpCookie(request);
        return execute(creteria, cookies.getOperatorId());
    }

    public String execute(String creteria, int employeeId) throws Exception {
        // get employee ID, department ID and role ID from Cookie
        boolean completed = false;

        // 获得从前台传过来的任务池相关的数据
        JSON parser = new JSON(new StringReader(creteria));
        Map<String, Object> result = (Map<String, Object>) parser.parse(new BasicHandler());

        // 把数据放入当前任务池实例中
        currentInstanceActivity = null;
        currentPooledTask = new BaseSystemProcessPooledTask();
        currentPooledTask.setDataFromMap(result);

        // 装入所有该流程实例的数据
        ProcessInformation pi = ProcessUtil.getAllInformation(currentPooledTask.getProcessInstanceId());
        SystemProcessDefinition definition = pi.getProcessDefinition();
        ProcessInstance instance = pi.getProcessInstance();

        // 从流程实例中找到该任务池对应的处理节点，为当前的处理节点
        for (ProcessInstanceActivity a : instance.getActivities()) {
            if (Objects.equals(a.getActivityId(), currentPooledTask.getActivityId())) {
                currentInstanceActivity = a;
                break;
            }
        }

        // 如果没有找到对应的处理节点则抛出异常
        if (currentInstanceActivity == null) {
            throw new SQLException("Could not find activity");
        }

        //	节点还未开始处理
        if(Objects.equals(currentInstanceActivity.getStatus(), SystemProcessConstants.ACTIVITY_STATUS_NEW_CREATED)) {
        	throw new Exception("该节点尚未开始审批");
        }
        
        //	节点已被处理，无需重复审批
        if(!Objects.equals(currentInstanceActivity.getStatus(), SystemProcessConstants.ACTIVITY_STATUS_ACTIVE)) {
        	throw new Exception("该节点已被处理");
        }
        
        // 把用户的审批意见存入当前处理节点
        currentInstanceActivity.setProcessComment(currentPooledTask.getProcessComment());

        // 获取用户操作的类型，0-同意，1-驳回，2-退回，3-转交， 4-回退给申请人
        int approvalment = BaseHelpUtils.getIntValue(result, "approvalment");
        // 获取用户增加的审批人信息 
        int additionalEmployee = BaseHelpUtils.getIntValue(result, "additionalApprovalment");

        // 获取跟该流程实例对应的业务处理程序的名子
        String executeClassName = AbstractProcessores
                .getWorkflowProcessorClass(definition.getProcessType().getProcessExecuteName());

        // 如果没有找到相应的处理程序的名称，则抛出异常
        if (executeClassName == null) {
            throw new SQLException("process execute class not found");
        }
        gwp = (GenericWorkflowProcessor) Class.forName(executeClassName).newInstance();
        Map params = (Map) result.get("userData");
        // 开始数据库事务
        ThreadConnection.beginTransaction();

        switch (approvalment) {
            case 0: // 同意
                // 找到当前活动节点对应的流程定义的节点定义，主要是看看该节点是平行节点还是串行节点
                BaseSystemProcessActivity processActivity = ProcessUtil.findProcessActivityById(definition,
                        currentInstanceActivity.getProcessActivityId());
                // 如果没有找到对应的流程定义节点，或者是并行节点
                if (processActivity == null || processActivity.getPoolType() == null
                        || processActivity.getPoolType() == 0) {
                    // 直接将当前节点设置为通过状态
                    currentInstanceActivity.setStatus(SystemProcessConstants.ACTIVITY_STATUS_PASS);
                    // 激活下一个流程节点
                    completed = ProcessUtil.activeNextInstanceActivity(instance,
                            currentInstanceActivity.getNextActivityId());
                    // 把与当前处理节点关联的所有任务池的任务
                    ProcessUtil.passCurrentInstanceActivity(instance, currentInstanceActivity.getActivityId(), employeeId,
                            currentInstanceActivity.getProcessComment(), SystemProcessConstants.ACTIVITY_STATUS_PASS, 0);
                    if (!completed) {
                        completed = ProcessUtil.findNextAvailableActivity(currentInstanceActivity.getNextActivityId(), instance.getActivities());
                    }
                } else {
                    // 是否跟该处理节点关联的所有任务池的任务都处理完了？
                    if (ProcessUtil.isAllPooledTaskPassed(instance, currentPooledTask)) {
                        // 激活下一个流程节点
                        completed = ProcessUtil.activeNextInstanceActivity(instance,
                                currentInstanceActivity.getNextActivityId());
                        // 通过当前的流程节点
                        ProcessUtil.passCurrentInstanceActivity(instance, currentInstanceActivity.getActivityId(),
                                employeeId, currentInstanceActivity.getProcessComment(),
                                SystemProcessConstants.ACTIVITY_STATUS_PASS, 1);
                        completed = ProcessUtil.findNextAvailableActivity(currentInstanceActivity.getNextActivityId(), instance.getActivities());
                    }
                    // 通过本次任务节点
                    ProcessUtil.passCurrentPooledTask(instance, currentPooledTask.getProcessPooledTaskId(),
                            currentInstanceActivity.getProcessComment());
                }
                if (!completed) {
                    instance.setProcessStatus(SystemProcessConstants.PROCESS_STATUS_WORKING);
                }
                break;
            case 1://驳回
                instance.setProcessStatus(SystemProcessConstants.PROCESS_STATUS_REJECT);
                instance.setCompleteTime(new Date());
                ProcessUtil.passCurrentInstanceActivity(instance, currentInstanceActivity.getActivityId(), employeeId,
                        currentInstanceActivity.getProcessComment(), SystemProcessConstants.ACTIVITY_STATUS_REJECT, 0);

                completed = true;
                break;
            case 2://回退
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
            case 3://转交
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
                completed = ProcessUtil.findNextAvailableActivity(currentInstanceActivity.getNextActivityId(), instance.getActivities());
                break;
            case 4://	回退给发起人
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
        if(approvalment == 2) {
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
            currentActivity.setProcessComment(currentPooledTask.getProcessComment());
            instance.setCurrentActivity(currentActivity);
        	gwp.executeActivity(pi, params, employeeId, false, completed);
        }
        ThreadConnection.commit();
        return spiws.generateBaseExt().toOneLineJSON();
    }

}
