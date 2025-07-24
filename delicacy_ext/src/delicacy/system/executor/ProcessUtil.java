package delicacy.system.executor;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.apache.log4j.Logger;

import delicacy.common.BaseHelpUtils;
import delicacy.common.ExpressionData;
import delicacy.common.GenericWorkflowProcessor;
import delicacy.expression.Expression;
import delicacy.expression.ParseException;
import delicacy.servlet.AbstractProcessores;
import delicacy.system.bean.BaseDepartment;
import delicacy.system.bean.BaseEmployee;
import delicacy.system.bean.BaseEmployeeRole;
import delicacy.system.bean.BaseSystemProcessActivity;
import delicacy.system.bean.BaseSystemProcessAttention;
import delicacy.system.bean.BaseSystemProcessInstanceActivity;
import delicacy.system.bean.BaseSystemProcessLink;
import delicacy.system.bean.BaseSystemProcessPooledTask;
import delicacy.system.bean.ProcessInformation;
import delicacy.system.bean.ProcessInstance;
import delicacy.system.bean.ProcessInstanceActivity;
import delicacy.system.bean.SystemProcessDefinition;
import delicacy.system.dao.Department;
import delicacy.system.dao.Employee;
import delicacy.system.dao.EmployeeRole;
import delicacy.system.dao.SystemProcess;
import delicacy.system.dao.SystemProcessActivity;
import delicacy.system.dao.SystemProcessAttention;
import delicacy.system.dao.SystemProcessInstance;
import delicacy.system.dao.SystemProcessInstanceActivity;
import delicacy.system.dao.SystemProcessLink;
import delicacy.system.dao.SystemProcessPooledTask;
import delicacy.system.dao.SystemProcessType;

/**
 *
 * @author Peter
 */
public class ProcessUtil {

    private static final Logger __logger = Logger.getLogger(ProcessUtil.class);

    public static ProcessInstance loadProcessInstance(int processInstanceID) throws Exception {
        ProcessInstance pi = new ProcessInstance();
        SystemProcessInstance spi = new SystemProcessInstance();
        spi.setProcessInstanceId(processInstanceID);
        if (!spi.load()) {
            throw new SQLException(String.format("Process Instance : %1$d not found", processInstanceID));
        }
        spi.setDataToBase(pi);
        SystemProcessInstanceActivity spia = new SystemProcessInstanceActivity();
        spia.setConditionProcessInstanceId("=", processInstanceID);
        List<BaseSystemProcessInstanceActivity> bactivities = spia.conditionalLoad("order by activity_id");
        SystemProcessAttention spa = new SystemProcessAttention();
        spa.setConditionProcessInstanceId("=", processInstanceID);
        pi.setAttentions(spa.conditionalLoad());
        SystemProcessPooledTask sppt = new SystemProcessPooledTask();
        sppt.setConditionProcessInstanceId("=", processInstanceID);
        pi.setTasks(sppt.conditionalLoad());
        List<ProcessInstanceActivity> pactivities = new ArrayList<>();
        bactivities.stream().map(a -> {
            ProcessInstanceActivity pia = new ProcessInstanceActivity();
            a.cloneCopy(pia);
            if (pi.getAttentions() != null && !pi.getAttentions().isEmpty()) {
                List<BaseSystemProcessAttention> attentions = new ArrayList<>();
                pi.getAttentions().stream().filter(attention -> (Objects.equals(attention.getActivityId(), a.getActivityId()))).forEachOrdered(attention -> {
                    attentions.add(attention);
                });
                if (attentions.size() > 0) {
                    pia.setAttentions(attentions);
                }
            }
            if (pi.getTasks() != null && !pi.getTasks().isEmpty()) {
                List<BaseSystemProcessPooledTask> tasks = new ArrayList<>();
                pi.getTasks().stream().filter(task -> (Objects.equals(task.getActivityId(), a.getActivityId()))).forEachOrdered(task -> {
                    tasks.add(task);
                });
                if (tasks.size() > 0) {
                    pia.setTasks(tasks);
                }
            }
            return pia;
        }).forEachOrdered(pia -> {
            pactivities.add(pia);
        });
        pi.setActivities(pactivities);
        return pi;
    }

    public static SystemProcessDefinition loadProcess(int processID) throws Exception {
        SystemProcessDefinition spd = new SystemProcessDefinition();
        SystemProcess sp = new SystemProcess();
        sp.setProcessId(processID);
        if (!sp.load()) {
            throw new SQLException(String.format("Process : %1$d not found", processID));
        }
        sp.setDataToBase(spd);
        SystemProcessType spt = new SystemProcessType();
        spt.setProcessTypeId(spd.getProcessTypeId());
        if (!spt.load()) {
            throw new SQLException(String.format("Process Type : %1$d not found", spd.getProcessTypeId()));
        }
        spd.setProcessType(spt.generateBase());
        SystemProcessActivity spa = new SystemProcessActivity();
        spa.setConditionProcessId("=", processID);
        spd.setActivities(spa.conditionalLoad());
        SystemProcessLink spl = new SystemProcessLink();
        spl.setConditionProcessId("=", processID);
        spd.setLinks(spl.conditionalLoad());
        return spd;
    }

    public static ProcessInformation getProcessDefinition(int processID) throws Exception {
        ProcessInformation pi = new ProcessInformation();
        pi.setProcessDefinition(loadProcess(processID));
        return pi;
    }

    public static void processInstanceActivity(ProcessInstance pi, SystemProcessDefinition def) {
        for (ProcessInstanceActivity pia : pi.getActivities()) {
            for (BaseSystemProcessActivity spa : def.getActivities()) {
                if (Objects.equals(pia.getProcessActivityId(), spa.getProcessActivityId())) {
                    pia.setPoolType(spa.getPoolType());
                    pia.setBackViewName(spa.getActivityName());
                    break;
                }
            }
        }
    }

    public static void processInstanceActivity(List<ProcessInstanceActivity> instanceActivities,
            SystemProcessDefinition def) {
        for (ProcessInstanceActivity pia : instanceActivities) {
            for (BaseSystemProcessActivity spa : def.getActivities()) {
                if (Objects.equals(pia.getProcessActivityId(), spa.getProcessActivityId())) {
                    pia.setPoolType(spa.getPoolType());
                    pia.setBackViewName(spa.getActivityName());
                    break;
                }
            }
        }
    }

    public static void findOutAllTasks(List<ProcessInstanceActivity> activities,
            List<BaseSystemProcessPooledTask> tasks) {
        if (tasks == null || tasks.isEmpty()) {
            return;
        }
        if (activities == null || activities.isEmpty()) {
            return;
        }
        activities.stream().map(a -> {
            a.setTasks(new ArrayList<>());
            return a;
        }).forEachOrdered(a -> {
            tasks.stream().filter(t -> (Objects.equals(a.getActivityId(), t.getActivityId()))).forEachOrdered(t -> {
                a.getTasks().add(t);
            });
        });
    }

    /**
     * 符合自动跳过条件的节点自动跳过，并找到下一个需要人为审批的有效节点，如果流程跑完，则返回true；否则返回false
     *
     * @param startNo
     * @param activities
     * @return
     */
    public static boolean findNextAvailableActivity(int startNo, List<ProcessInstanceActivity> activities) {
        if (null == activities || activities.isEmpty()) {
            return false;
        }
        int size = activities.size();
        if (startNo >= size - 1) {
            return true;
        }
        // 先把节点升序排好
        Collections.sort(activities, (ProcessInstanceActivity o1, ProcessInstanceActivity o2) -> o1.getActivityId().compareTo(o2.getActivityId()));
        // 如果第一个检测是否需要跳过的节点是处理节点，那么直接返回false
        if (activities.get(startNo).getActivityType().equals(SystemProcessConstants.ACTIVITY_TYPE_PROCESS)) {
            return false;
        }
        /*
                 * 如果一个审批流程中只有一个审批人，而且审批人就是发起人，那就不能做自动跳过处理
         */
        Set<Integer> allEmployeeIds = new HashSet<>();
        activities.stream().filter(a -> !(a.getActivityType() == SystemProcessConstants.ACTIVITY_TYPE_END)).forEachOrdered(a -> {
            allEmployeeIds.add(a.getEmployeeId());
        });
        int allEmployeeIdLength = allEmployeeIds.size();
        // 存放所有已经审批通过的用户ID（包括了发起人的ID）
        Set<Integer> allUserSet = new HashSet<>();
        for (int i = 0; i < startNo; i++) {
            ProcessInstanceActivity obj = activities.get(i);
            if (obj.getStatus().equals(SystemProcessConstants.ACTIVITY_STATUS_PASS)) {
                if (null != obj.getTasks() && !obj.getTasks().isEmpty()) {
                    if (obj.getPoolType() == 0) {
                        if (null != obj.getEmployeeId()) {
                            allUserSet.add(obj.getEmployeeId());
                        }
                    } else {
                        List<BaseSystemProcessPooledTask> tempList = obj.getTasks();
                        tempList.stream().filter(task -> (null != task.getEmployeeId())).forEachOrdered(task -> {
                            allUserSet.add(task.getEmployeeId());
                        });
                    }
                } else {
                    allUserSet.add(obj.getEmployeeId());
                }
            }
        }

        // 如果该审批节点的人员在他的最近一次审批节点和当前节点之间，存在“回退”，则在本次的跳过节点任务中，该人员所在的节点不能跳过
        Set<Integer> set = new HashSet<>();
        allUserSet.stream().filter(employeeId -> (canJump(startNo, activities, employeeId))).forEachOrdered(employeeId -> {
            set.add(employeeId);
        });
        // 申请人的所有审批节点都是需要自动跳过的，所以保险起见，在set中加上申请人的ID
        if (null != activities.get(0).getEmployeeId()) {
            set.add(activities.get(0).getEmployeeId());
        }
        // 经过上述操作，set中的所有员工，都是可以跳过节点的
        Date date = new Date();
        for (int i = startNo; i < size - 1; i++) {
            ProcessInstanceActivity obj = activities.get(i);
            // 遇到处理节点，则后续节点包括当前节点都不能跳过
            if (obj.getActivityType().equals(SystemProcessConstants.ACTIVITY_TYPE_PROCESS)) {
                break;
            }
            if (null != obj.getTasks() && !obj.getTasks().isEmpty()) {
                List<BaseSystemProcessPooledTask> taskList = obj.getTasks();
                int taskJumpNum = 0;
                for (BaseSystemProcessPooledTask task : taskList) {
                    Integer employeeId = task.getEmployeeId();
                    if (set.contains(employeeId)) {
                        taskJumpNum++;
                        // 并行的，只要有一个通过了就ok
                        if (obj.getPoolType() == SystemProcessConstants.POOL_TYPE_0) {
                            obj.setStatus(SystemProcessConstants.ACTIVITY_STATUS_PASS);
                            obj.setProcessComment("同意");
                            obj.setInstanceActivityStartTime(date);
                            obj.setOperateTime(date);
                            obj.setEmployeeId(employeeId);
                            if (i < size - 2) {
                                activeNextActivity(activities, i + 1);
                            }
                            break;
                        } else {
                            task.setInstanceActivityStartTime(date);
                            task.setStatus(SystemProcessConstants.POOLED_TASK_STATUS_DONE);
                            task.setOperateTime(date);
                            task.setProcessComment("之前审批过了，本次审批略过");
                        }
                    }
                }
                if (taskJumpNum > 0) {
                    // 说明该任务池跳过了至少一个节点
                    if (obj.getPoolType() == SystemProcessConstants.POOL_TYPE_0) {
                        taskList.stream().map(task -> {
                            task.setStatus(SystemProcessConstants.POOLED_TASK_STATUS_DONE);
                            return task;
                        }).map(task -> {
                            task.setInstanceActivityStartTime(date);
                            return task;
                        }).map(task -> {
                            task.setOperateTime(date);
                            return task;
                        }).forEachOrdered(task -> {
                            task.setProcessComment("同意");
                        });
                    } else {
                        // 串行，则看任务池中的每一个节点是否都跳过了，如果是，设置该任务池跳过
                        if (taskJumpNum == taskList.size()) {
                            obj.setInstanceActivityStartTime(date);
                            obj.setStatus(SystemProcessConstants.ACTIVITY_STATUS_PASS);
                            obj.setProcessComment("任务池中的所有人之前都曾经审批过了，故本次审批略过");
                            obj.setOperateTime(date);
                            if (i < size - 2) {
                                activeNextActivity(activities, i + 1);
                            }
                        } else {
                            // 串行任务池中，并没有每个人都自动通过，所以后续节点也不可能通过，故break跳出循环
                            break;
                        }
                    }
                } else {
                    // 该任务池中，一个节点都没有跳过，直接break跳出本次循环
                    break;
                }
            } else {
                // 普通审批节点，如果set中含有该节点的employeeId，则该节点可以跳过
                Integer employeeId = obj.getEmployeeId();
                if (set.contains(employeeId) && allEmployeeIdLength > 1) {
                    obj.setInstanceActivityStartTime(date);
                    obj.setStatus(SystemProcessConstants.ACTIVITY_STATUS_PASS);
                    obj.setProcessComment("同意");
                    obj.setOperateTime(date);
                    if (i < size - 2) {
                        activeNextActivity(activities, i + 1);
                    }
                } else {
                    // 如果该审批节点的人员在他的最近一次审批节点和当前节点之间，存在“回退”，则不跳过该节点
                    break;
                }
            }
        }
        ProcessInstanceActivity tempActivity = activities.get(size - 2);
        return null != tempActivity && null != tempActivity.getStatus()
                && tempActivity.getStatus().equals(SystemProcessConstants.ACTIVITY_STATUS_PASS);
    }

    public static void activeNextActivity(List<ProcessInstanceActivity> activities, int orderNo) {
        Date date = new Date();
        ProcessInstanceActivity nextObj = activities.get(orderNo);
        nextObj.setStatus(SystemProcessConstants.ACTIVITY_STATUS_ACTIVE);
        nextObj.setInstanceActivityStartTime(date);
        List<BaseSystemProcessPooledTask> taskList = nextObj.getTasks();
        // 如果有任务池，则将该任务池中的所有节点设置为active状态
        if (null != taskList && !taskList.isEmpty()) {
            taskList.stream().map(obj -> {
                obj.setStatus(SystemProcessConstants.POOLED_TASK_STATUS_ACTIVE);
                return obj;
            }).forEachOrdered(obj -> {
                obj.setInstanceActivityStartTime(date);
            });
        }
        List<BaseSystemProcessAttention> attentionList = nextObj.getAttentions();
        // 如果有知会节点，则将该节点的所有知会节点设置为active状态
        if (null != attentionList && !attentionList.isEmpty()) {
            attentionList.stream().map(obj -> {
                obj.setStatus(SystemProcessConstants.ATTENTION_STATUS_ACTIVE);
                return obj;
            }).forEachOrdered(obj -> {
                obj.setInstanceActivityStartTime(date);
            });
        }
    }

    public static boolean canJump(int startNo, List<ProcessInstanceActivity> activities, Integer employeeId) {
        for (int i = startNo - 1; i >= 0; i--) {
            ProcessInstanceActivity temp = activities.get(i);
            if (null != temp.getStatus()
                    && (temp.getStatus().equals(SystemProcessConstants.ACTIVITY_STATUS_RETURN_LAST) || temp.getStatus().equals(SystemProcessConstants.ACTIVITY_STATUS_RETURN_FIRST))) {
                return false;
            } else if (Objects.equals(temp.getStatus(), SystemProcessConstants.ACTIVITY_STATUS_PASS)) {
                if (null != temp.getTasks() && !temp.getTasks().isEmpty()) {
                    if (temp.getPoolType() == SystemProcessConstants.POOL_TYPE_0) {
                        if (null != temp.getEmployeeId() && temp.getEmployeeId().equals(employeeId)) {
                            return true;
                        }
                    } else {
                        List<BaseSystemProcessPooledTask> tempList = temp.getTasks();
                        if (tempList.stream().anyMatch(task -> (task.getEmployeeId().equals(employeeId)))) {
                            return true;
                        }
                    }
                } else {
                    if (null != temp.getEmployeeId() && temp.getEmployeeId().equals(employeeId)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean jumpActivityProcess(List<ProcessInstanceActivity> activities) {
        if (activities == null || activities.isEmpty()) {
            return false;
        }
        int idx = 1;
        ProcessInstanceActivity activity = activities.get(0);
        int employeeId = activity.getEmployeeId();
        while (true) {
            activity = activities.get(idx);
            if (activity.getStatus() != SystemProcessConstants.ACTIVITY_STATUS_ACTIVE) {
                if (idx == activities.size() - 1) {
                    return true;
                }
                idx++;
                continue;
            }
            // 当为处理节点的时候不准许跳过
            if (activity.getActivityType() == 3) {
                idx++;
                continue;
            }
            if (activity.getTasks() != null && !activity.getTasks().isEmpty()) {
                for (BaseSystemProcessPooledTask t : activity.getTasks()) {
                    if (Objects.equals(t.getEmployeeId(), employeeId)) {// 说明任务池中的处理人有申请人
                        if (activity.getPoolType() == 0) {
                            for (BaseSystemProcessPooledTask tt : activity.getTasks()) {
                                tt.setStatus(SystemProcessConstants.POOLED_TASK_STATUS_DONE);
                                tt.setOperateTime(new Date());
                                tt.setProcessComment("和申请人为同一人，略过");
                            }
                            activity.setStatus(SystemProcessConstants.ACTIVITY_STATUS_PASS);
                            activity.setProcessComment("和申请人为同一人，略过");
                            activity.setOperateTime(new Date());
                            activity.setEmployeeId(employeeId);
                            if (idx == activities.size() - 1) {
                                return true;
                            }
                            activity = activities.get(++idx);
                            if (activity.getNextActivityId() != 0) {// 不是结束节点，才可以设置为active状态
                                activity.setStatus(SystemProcessConstants.ACTIVITY_STATUS_ACTIVE);
                                activity.setInstanceActivityStartTime(new Date());
                                if (activity.getTasks() != null && !activity.getTasks().isEmpty()) {
                                    for (BaseSystemProcessPooledTask t1 : activity.getTasks()) {
                                        t1.setStatus(SystemProcessConstants.POOLED_TASK_STATUS_ACTIVE);
                                        t1.setInstanceActivityStartTime(new Date());
                                    }
                                }
                            }
                        } else {
                            t.setStatus(SystemProcessConstants.POOLED_TASK_STATUS_DONE);
                            t.setOperateTime(new Date());
                            t.setProcessComment("和申请人为同一人，略过");
                            return false;
                        }
                    }
                }
            } else {
                if (Objects.equals(activity.getEmployeeId(), employeeId) && activity.getActivityType() == 2) {
                    // 审核节点
                    activity.setStatus(SystemProcessConstants.ACTIVITY_STATUS_PASS);
                    activity.setProcessComment("和申请人为同一人，略过");
                    activity.setOperateTime(new Date());
                    // 如果该节点有任务池，则设置为通过
                    // if (activity.getTasks() != null &&
                    // !activity.getTasks().isEmpty()) {
                    // for (BaseSystemProcessPooledTask t : activity.getTasks())
                    // {
                    // t.setStatus(SystemProcessConstants.POOLED_TASK_STATUS_DONE);
                    // t.setProcessComment("和申请人为同一人，略过");
                    // t.setOperateTime(new Date());
                    // ;
                    // }
                    // }
                    if (idx == activities.size() - 1) {
                        return true;
                    }
                    activity = activities.get(++idx);
                    if (activity.getNextActivityId() != 0) {// 不是结束节点，才可以设置为active状态
                        activity.setStatus(SystemProcessConstants.ACTIVITY_STATUS_ACTIVE);
                        activity.setInstanceActivityStartTime(new Date());
                        if (activity.getTasks() != null && !activity.getTasks().isEmpty()) {
                            for (BaseSystemProcessPooledTask t : activity.getTasks()) {
                                t.setStatus(SystemProcessConstants.POOLED_TASK_STATUS_ACTIVE);
                                t.setInstanceActivityStartTime(new Date());
                            }
                        }
                    }
                    continue;
                }
            }
            return false;
        }
    }

    public static List<BaseSystemProcessAttention> generateAllActivityAttentions(
            List<ProcessInstanceActivity> instanceActivities, GenericWorkflowProcessor gwp,
            SystemProcessDefinition processDefinition, Integer employeeId) throws Exception {
        List<BaseSystemProcessAttention> attentions = new ArrayList<>();
        for (ProcessInstanceActivity i : instanceActivities) {
            // 如果本节点类型没有设置，或者是开始、结束，或者该节点没有对应的流程设计节点（后来人为添加的审核节点），肯定没有对应
            // 的知会人
            if (i.getActivityType() == null || i.getActivityType() == 0 || i.getActivityType() == 1
                    || i.getProcessActivityId() == null) {
                continue;
            }
            // 找到对应的流程设计节点
            BaseSystemProcessActivity pa = findActivityById(processDefinition, i.getProcessActivityId());
            // 如果没有对应的流程设计节点
            if (pa == null) {
                continue;
            }
            // 通过该流程设计节点，找到所有跟该节点链接的知会人节点
            List<BaseSystemProcessActivity> pas = findAttends(processDefinition, gwp, pa);
            // 如果没有找到这样的节点
            if (pas.isEmpty()) {
                continue;
            }
            // 将找到的知会人节点列表放入该流程实例节点
            i.setAttentions(new ArrayList<>());
            // 处理每个知会人节点
            for (BaseSystemProcessActivity kk : pas) {
                // 为每个知会人创建一个流程实例知会人节点
                BaseSystemProcessAttention att = createAttention(i.getProcessId(), i.getProcessInstanceId(),
                        i.getProcessType(), i.getBusinessId(), i.getBusinessName());
                // 设置流程设计节点的ID
                att.setProcessActivityId(i.getProcessActivityId());
                // 设置流程实例节点的ID
                att.setActivityId(i.getActivityId());
                // 设置节点创建时间为当前时间
                att.setInstanceActivityCreateTime(new Date());
                // 设置节点名称
                att.setBackViewName(kk.getActivityName());
                //	设置节点是否允许跳过
                att.setIfAllowOver(kk.getIfAllowOver());
                // 计算出该节点的处理人
                int[] nextEmployeeId = gwp.findActivityOwner(employeeId,
                        kk.getEmployeeId() == 0 || kk.getEmployeeId() == null ? null : kk.getEmployeeId(),
                        kk.getDepartmentId() == 0 || kk.getDepartmentId() == null ? null : kk.getDepartmentId(),
                        kk.getRoleId() == 0 || kk.getRoleId() == null ? null : kk.getRoleId(), kk.getPoolType());
                // 如果没有找到该知会人，只会打印出错误日志，并不会引起流程错误
                // 这样做也不一定对，有待商榷
                if (nextEmployeeId == null || nextEmployeeId.length == 0) {//只针对项目日常报销的
                	int processType = BaseHelpUtils.getIntValue(i.getProcessType());
                	if(!BaseHelpUtils.isNullOrEmpty(i.getProcessType())) {
                		
                		if(processType == 2) {
                			if(i.getBackViewName().equals("项目经理")) {
                				
                                 int projectManagerEmployeeId = BaseHelpUtils.getIntValue(i.getEmployeeId());//项目经理id
                                 __logger.error(" projectManagerEmployeeId:"+projectManagerEmployeeId+" BackViewName ： "+i.getBackViewName());
                                 if(projectManagerEmployeeId > 0) {
                                	 //针对项目经理做一个特殊的知会节点处理
                                	 __logger.error(" projectManagerEmployeeId:"+projectManagerEmployeeId+" BackViewName ： "+i.getBackViewName());
                                	 
                                	 Employee employeeDao = new Employee();
                                     employeeDao.unsetSelectFlags();
                                     employeeDao.setSelectEmployeeId(true);
                                     employeeDao.setSelectDepartmentId(true);
                                     employeeDao.setConditionStatus("=", 0);// 在职人员
                                     employeeDao.addCondition(BaseEmployee.CS_EMPLOYEE_ID, "=", projectManagerEmployeeId);
                                     BaseEmployee baseEmployee = employeeDao.executeQueryOneRow();
                                     if(null != baseEmployee) {
                                    	 int departmentId= baseEmployee.getDepartmentId();
                                    	 if(departmentId > 0) {
                                    		 Department dao = new Department();
	                            	       	 dao.setConditionDeleteFlag("=", 0);
	                            	         dao.setConditionDepartmentId("=",departmentId);
	                            	         dao.setConditionIsEnable("=", Boolean.TRUE);
	                            	         List<BaseDepartment> baseDepartmentList = dao.conditionalLoad();
	                            	         BaseDepartment baseDepartment = baseDepartmentList.get(0);
	                            	         if(!BaseHelpUtils.isNullOrEmpty(baseDepartment) ) {
	                            	        	 if(BaseHelpUtils.getIntValue(baseDepartment.getParentId()) != 1) {//找当前项目经理的上级部门
	                            	        		 dao.clear();
		                            	        	 dao.setConditionDepartmentId("=",baseDepartment.getParentId());
			                            	         dao.setConditionIsEnable("=", Boolean.TRUE);
			                            	         baseDepartmentList = dao.conditionalLoad();
		                            	        	 
	                                    	         if(!BaseHelpUtils.isNullOrEmpty(baseDepartmentList) && baseDepartmentList.size() > 0) {
	                                    	        	 nextEmployeeId = new int[baseDepartmentList.size()];
	                                    	        	 int index = 0;
	                                    	        	 for(BaseDepartment entity : baseDepartmentList) {
	                                    	        		 int managerId = BaseHelpUtils.getIntValue(entity.getManagerId());
	                                    	        		 if(managerId > 0) {
	                                    	        			 nextEmployeeId[index] = managerId;
	                                        	        		 index++;
	                                    	        		 }
	                                    	        		 __logger.info(" nextEmployeeId : "+nextEmployeeId.length );
	                                    	        		 if(nextEmployeeId.length > 0) {
	                                    	        			 __logger.info(" nextEmployeeId[0] : "+nextEmployeeId[0] );
	                                    	        		 }
	                                    	        	 }
	                                    	         }
	                            	        	 }else {//上级部门为1 就是杰恩 就取当前的部门
	                            	        		 if(!BaseHelpUtils.isNullOrEmpty(baseDepartmentList) && baseDepartmentList.size() > 0) {
	                                    	        	 nextEmployeeId = new int[baseDepartmentList.size()];
	                                    	        	 int index = 0;
	                                    	        	 for(BaseDepartment entity : baseDepartmentList) {
	                                    	        		 int managerId = BaseHelpUtils.getIntValue(entity.getManagerId());
	                                    	        		 if(managerId > 0) {
	                                    	        			 nextEmployeeId[index] = managerId;
	                                        	        		 index++;
	                                    	        		 }
	                                    	        	 }
	                                    	         }
	                            	        	 }
	                            	         }
                                    	 }
                                     }
                                	 
                                 }else {
                                	 __logger.error("Could not find the next activity owner");
                         			continue;
                                 }
                			}else {
                				__logger.error("Could not find the next activity owner");
                    			continue;
                			}
                			
                		}else {
                			__logger.error("Could not find the next activity owner");
                			continue;
                		}
                	}
                    
                }
               
                // 不管找到多少人，都去第一个
                if(nextEmployeeId.length > 0) {
                	 att.setEmployeeId(nextEmployeeId[0]);
                }
               
                // 如果当前实例节点的状态为活动，则知会人的状态也是活动
                if (i.getStatus() == SystemProcessConstants.ACTIVITY_STATUS_ACTIVE) {
                    att.setStatus(SystemProcessConstants.ATTENTION_STATUS_ACTIVE);
                    att.setInstanceActivityStartTime(new Date());
                } else { // 否则状态均为新建
                    att.setStatus(SystemProcessConstants.ATTENTION_STATUS_NEW);
                }
                // 有多个知会人才执行，创建出多个知会人节点
                if (nextEmployeeId.length > 1) {
                    List<BaseSystemProcessAttention> tempList = new ArrayList<>();
                    tempList.add(att);
                    for (int num = 1; num < nextEmployeeId.length; num++) {
                        BaseSystemProcessAttention obj = new BaseSystemProcessAttention();
                        att.cloneCopy(obj);
                        obj.setEmployeeId(nextEmployeeId[num]);
                        tempList.add(obj);
                    }
                    // 将知会人节点添加到本流程实例节点的知会人列表中
                    i.getAttentions().addAll(tempList);
                    // 将知会人节点添加到本流程的知会人节点列表中
                    attentions.addAll(tempList);
                } else {
                    // 将知会人节点添加到本流程实例节点的知会人列表中
                    i.getAttentions().add(att);
                    // 将知会人节点添加到本流程的知会人节点列表中
                    attentions.add(att);
                }
            }
        }
        return attentions;
    }

    public static ProcessInformation getAllInformation(int processInstanceID) throws Exception {
        ProcessInformation pi = new ProcessInformation();
        pi.setProcessInstance(loadProcessInstance(processInstanceID));
        pi.setProcessDefinition(loadProcess(pi.getProcessInstance().getProcessId()));
        processInstanceActivity(pi.getProcessInstance(), pi.getProcessDefinition());
        String executeClassName = AbstractProcessores
                .getWorkflowProcessorClass(pi.getProcessDefinition().getProcessType().getProcessExecuteName());
        if (executeClassName == null) {
            throw new SQLException("process execute class not found");
        }
        GenericWorkflowProcessor gwp = (GenericWorkflowProcessor) Class.forName(executeClassName).newInstance();
        pi.setCustomData(gwp.getBusinessData(pi.getProcessInstance().getBusinessId()));
        return pi;
    }

    public static BaseSystemProcessInstanceActivity findActiveInstanceActivity(ProcessInformation pi) {
        ProcessInstance spi = pi.getProcessInstance();
        for (BaseSystemProcessInstanceActivity spia : spi.getActivities()) {
            if (spia.getStatus() == SystemProcessConstants.ACTIVITY_STATUS_ACTIVE) {
                return spia;
            }
        }
        return null;
    }

    public static BaseSystemProcessActivity findProcessActivityById(SystemProcessDefinition spd, int id) {
        for (BaseSystemProcessActivity bspa : spd.getActivities()) {
            if (bspa.getProcessActivityId() == id) {
                return bspa;
            }
        }
        return null;
    }

    public static BaseSystemProcessActivity firstActivity(SystemProcessDefinition spd) throws SQLException {
        for (BaseSystemProcessActivity bspa : spd.getActivities()) {
            if (bspa.getActivitySerialNo() == SystemProcessConstants.PROCESS_FIRST_ACTIVITY) {
                return bspa;
            }
        }
        throw new SQLException("Could not find first activity");
    }

    public static BaseSystemProcessActivity lastActivity(SystemProcessDefinition spd) throws SQLException {
        for (BaseSystemProcessActivity bspa : spd.getActivities()) {
            if (bspa.getActivitySerialNo() == SystemProcessConstants.PROCESS_LAST_ACTIVITY) {
                return bspa;
            }
        }
        throw new SQLException("Could not find last activity");
    }

    public static BaseSystemProcessActivity findActivityById(SystemProcessDefinition spd, int id) {
        for (BaseSystemProcessActivity bspa : spd.getActivities()) {
            if (bspa.getProcessActivityId() == id) {
                return bspa;
            }
        }
        return null;
    }

    public static BaseSystemProcessActivity findSpecifiedActivity(SystemProcessDefinition spd, int id)
            throws SQLException {
        for (BaseSystemProcessActivity bspa : spd.getActivities()) {
            if (bspa.getActivitySerialNo() == id) {
                return bspa;
            }
        }
        throw new SQLException("Could not find specified activity");
    }

    public static List<BaseSystemProcessLink> findSpecifiedLinks(SystemProcessDefinition spd, int id)
            throws SQLException {
        List<BaseSystemProcessLink> links = new ArrayList<>();
        spd.getLinks().stream().filter(bspl -> (bspl.getProcessActivityId() == id)).forEachOrdered(bspl -> {
            links.add(bspl);
        });
        // if (links.isEmpty()) {
        // throw new SQLException("Could not find specified links");
        // }
        return links;
    }

    public static List<BaseSystemProcessActivity> findAttends(SystemProcessDefinition spd, GenericWorkflowProcessor gwp,
            BaseSystemProcessActivity currentActivity) throws Exception {
        List<BaseSystemProcessActivity> activities = new ArrayList<>();
        List<BaseSystemProcessLink> links = findSpecifiedLinks(spd, currentActivity.getActivitySerialNo());
        if (links.isEmpty()) {
            return activities;
        }
        for (BaseSystemProcessLink bspl : links) {
            if (!BaseHelpUtils.isNullOrEmpty(bspl.getCondition())) {
                if (!gwp.computeExpression(bspl.getCondition())) {
                    continue;
                }
            }
            BaseSystemProcessActivity spa = findSpecifiedActivity(spd, bspl.getToProcessActivityId());
            if (spa == null || spa.getActivityType() != 4) {
                continue;
            }
            activities.add(spa);
        }
        List<BaseSystemProcessActivity> allActivities = new ArrayList<>();
        allActivities.addAll(activities);
        for (BaseSystemProcessActivity bsp : activities) {
            List<BaseSystemProcessActivity> subactivities = findAttends(spd, gwp, bsp);
            allActivities.addAll(subactivities);
        }
        return allActivities;
    }

    public static void findCurrentInstanceActivity(ProcessInstance instance,
            BaseSystemProcessInstanceActivity currentInstanceActivity) {
        instance.getActivities().stream().filter(a -> (Objects.equals(a.getProcessInstanceActivityId(),
                currentInstanceActivity.getProcessInstanceActivityId()))).forEachOrdered(a -> {
            a.cloneCopy(currentInstanceActivity);
        });
    }

    public static ProcessInstanceActivity findBackInstanceActivity(ProcessInstance instance,
            BaseSystemProcessInstanceActivity currentInstanceActivity) {
        __logger.info("currentInstanceActivity===============================" + currentInstanceActivity);
        int idx = -1;
        for (int i = 0; i < instance.getActivities().size(); i++) {
            ProcessInstanceActivity ca = instance.getActivities().get(i);
            if (Objects.equals(ca.getActivityId(), currentInstanceActivity.getActivityId())) {
                idx = i;
                break;
            }
        }
        if (idx == -1) {
            return null;
        }
        Set<Integer> exceptPersons = new HashSet<>();
        // 从当前节点往前找
        for (int i = idx - 1; i >= 0; i--) {
            ProcessInstanceActivity ca = instance.getActivities().get(i);
            // 如果已经到达开始节点，那么就返回申请人节点
            if (i == 0) {
                return ca;
            }
            // 如果找到回退的节点，就跳过该节点，同时把该节点的处理人加入需要跳过的人集合中
            if (ca.getStatus() == SystemProcessConstants.ACTIVITY_STATUS_RETURN_LAST) {
                exceptPersons.add(ca.getEmployeeId());
                continue;
            }
            // 如果找到和当前节点相同处理人的节点，就跳过该节点，同时把该节点的处理人加入需要跳过的人集合中
            if (Objects.equals(ca.getEmployeeId(), currentInstanceActivity.getEmployeeId())) {
                exceptPersons.add(ca.getEmployeeId());
                continue;
            }
            // 如果当前节点的处理人就是发起人，则跳过该节点，同时把该节点的处理人加入到需要跳过的人集合中
//            if (Objects.equals(ca.getEmployeeId(), instance.getActivities().get(0).getEmployeeId())) {
//                exceptPersons.add(ca.getEmployeeId());
//                continue;
//            }
            // 如果在需要跳过的处理人中发现该节点，则跳过
            if (exceptPersons.contains(ca.getEmployeeId())) {
                continue;
            }
            return ca;
        }
        return null;
    }

    public static BaseSystemProcessActivity findNextActivity(SystemProcessDefinition spd, GenericWorkflowProcessor gwp,
            BaseSystemProcessActivity currentActivity) throws Exception {
        List<BaseSystemProcessLink> links = findSpecifiedLinks(spd, currentActivity.getActivitySerialNo());
        if (links.isEmpty()) {
            return null;
        }
        if (links.size() == 1) {
            return findSpecifiedActivity(spd, links.get(0).getToProcessActivityId());
        }
        BaseSystemProcessLink defaultLink = null;
        for (BaseSystemProcessLink bspl : links) {
            if (BaseHelpUtils.isNullOrEmpty(bspl.getCondition())) {
                BaseSystemProcessActivity spa = findSpecifiedActivity(spd, bspl.getToProcessActivityId());
                if (spa == null || spa.getActivityType() == 4) {
                    continue;
                }
                defaultLink = bspl;
                break;
            }
        }
        BaseSystemProcessLink rightLink = null;
        for (BaseSystemProcessLink bspl : links) {
            if (BaseHelpUtils.isNullOrEmpty(bspl.getCondition())) {
                continue;
            }
            if (gwp.computeExpression(bspl.getCondition())) {
                BaseSystemProcessActivity spa = findSpecifiedActivity(spd, bspl.getToProcessActivityId());
                if (spa == null || spa.getActivityType() == 4) {
                    continue;
                }
                rightLink = bspl;
                break;
            }
        }
        if (rightLink != null) {
            return findSpecifiedActivity(spd, rightLink.getToProcessActivityId());
        }
        if (defaultLink != null) {
            return findSpecifiedActivity(spd, defaultLink.getToProcessActivityId());
        }
        throw new SQLException("Could not find next activity");
    }

    public static boolean executeExpression(Expression e, String expression) throws Exception {
        try {
            ExpressionData ed = e.evaluateExpression();
            return ed.getBooleanValue();
        } catch (ParseException t) {
            throw new SQLException(expression + " >>>表达式错误,请仔细检查前面的表达式<<< " + t.getMessage());
        }
    }

    public static boolean executeExpression(String express, Map<String, Object> params) throws Exception {
        Expression e = new Expression(express);
        params.keySet().forEach(name -> {
            Object val = params.get(name);

            e.setValue(name, val);
        });
        return executeExpression(e, express);
    }

    public static boolean executeExpression(String express, String name, BigDecimal val) throws Exception {
        Expression e = new Expression(express);
        e.setValue(name, val.doubleValue());
        return executeExpression(e, express);
    }

    public static boolean executeExpression(String express, String name, Double val) throws Exception {
        Expression e = new Expression(express);
        e.setValue(name, val);
        return executeExpression(e, express);
    }

    public static boolean executeExpression(String express, String name, Integer val) throws Exception {
        Expression e = new Expression(express);
        e.setValue(name, val);
        return executeExpression(e, express);
    }

    public static boolean executeExpression(String express, String name, String val) throws Exception {
        Expression e = new Expression(express);
        e.setValue(name, val);
        return executeExpression(e, express);
    }

    public static int[] findNextOwner(Integer applyEmployee, Integer employeeId, Integer departmentId, Integer roleId)
            throws Exception {
        if (employeeId != null && employeeId != 0) {
            return new int[]{employeeId};
        }
        if (departmentId != null && roleId != null && departmentId != 0 && roleId != 0) {
            EmployeeRole er = new EmployeeRole();
            er.unsetSelectFlags();
            er.setSelectEmployeeId(true);
            er.setConditionDepartmentId("=", departmentId);
            er.setConditionRoleId("=", roleId);
            List<BaseEmployeeRole> bers = er.conditionalLoad();
            if (bers.size() > 0) {
                Object[] arr = new Object[bers.size()];
                int idx = 0;
                for (BaseEmployeeRole ber : bers) {
                    arr[idx++] = ber.getEmployeeId();
                }
                Employee employeeDao = new Employee();
                employeeDao.unsetSelectFlags();
                employeeDao.setSelectEmployeeId(true);
                employeeDao.setConditionStatus("=", 0);// 在职人员
                employeeDao.addCondition(BaseEmployee.CS_EMPLOYEE_ID, "in", arr);
                List<BaseEmployee> list = employeeDao.conditionalLoad();
                if (null != list && !list.isEmpty()) {
                	Set<Integer> setIds = new HashSet<>();
                    for (BaseEmployee e : list) {
                    	int empId = BaseHelpUtils.getIntValue(e.getEmployeeId());
                    	if(empId > 0) {
                    		setIds.add(empId);
                    	}
                    }
                    int size = setIds.size();
                    int[] res = new int[size];
                    int i = 0;
                    for(Integer id : setIds) {
                    	res[i] = id;
                    	i++;
                    }
                    return res;
                }
            }
        }
        if (roleId != null && roleId != 0) {
            EmployeeRole er = new EmployeeRole();
            er.unsetSelectFlags();
            er.setSelectEmployeeId(true);
            Department d = new Department();
            d.unsetSelectFlags();
            d.setSelectParentId(true);
            Employee e = new Employee();
            e.unsetSelectFlags();
            e.setSelectDepartmentId(true);
            e.setSelectEmployeeId(true);
            e.setEmployeeIdClear(applyEmployee);
            if (!e.load()) {
                throw new SQLException(String.format("Not found employee ID = [%1$d]", applyEmployee));
            }
            int dept = e.getDepartmentId();
            while (dept != 0) {
                er.setConditionDepartmentId("=", dept);
                er.setConditionRoleId("=", roleId);
                List<BaseEmployeeRole> bers = er.conditionalLoad();
                if (bers.size() > 0) {
                    Object[] arr = new Object[bers.size()];
                    int idx = 0;
                    for (BaseEmployeeRole ber : bers) {
                        arr[idx++] = ber.getEmployeeId();
                    }
                    e.setConditionStatus("=", 0);// 在职人员
                    e.addCondition(BaseEmployee.CS_EMPLOYEE_ID, "in", arr);
                    List<BaseEmployee> list = e.conditionalLoad();
                    if (null != list && !list.isEmpty()) {
                    	Set<Integer> setIds = new HashSet<>();
                        for (BaseEmployee ee : list) {
                        	int empId = BaseHelpUtils.getIntValue(ee.getEmployeeId());
                        	if(empId > 0) {
                        		setIds.add(empId);
                        	}
                        }
                        int size = setIds.size();
                        int[] res = new int[size];
                        int i = 0;
                        for(Integer id : setIds) {
                        	res[i] = id;
                        	i++;
                        }
                        return res;
                    }
                }
                d.setDepartmentIdClear(dept);
                if (!d.load()) {
                    throw new SQLException(String.format("Not found department ID = [%1$d]", dept));
                }
                dept = d.getParentId();
            }
        }
        return new int[0];
    }

    public static BaseSystemProcessAttention createAttention(int processId, int processInstanceId, int processTypeId,
            int businessId, String businessDesc) {
        BaseSystemProcessAttention res = new BaseSystemProcessAttention();
        res.setProcessId(processId);
        res.setProcessType(processTypeId);
        res.setProcessInstanceId(processInstanceId);
        res.setBusinessId(businessId);
        res.setBusinessName(businessDesc);
        return res;
    }

    public static ProcessInstanceActivity createInstanceActivity(int processId, int processInstanceId,
            int processTypeId, int businessId, String businessDesc) {
        ProcessInstanceActivity res = new ProcessInstanceActivity();
        res.setProcessId(processId);
        res.setProcessType(processTypeId);
        res.setProcessInstanceId(processInstanceId);
        res.setBusinessId(businessId);
        res.setBusinessName(businessDesc);
        return res;
    }

    public static BaseSystemProcessPooledTask createPooledTask(BaseSystemProcessInstanceActivity a) {
        BaseSystemProcessPooledTask task = new BaseSystemProcessPooledTask();
        task.setActivityId(a.getActivityId());
        task.setActivityType(a.getActivityType());
        task.setBusinessId(a.getBusinessId());
        task.setBusinessName(a.getBusinessName());
        task.setStatus(a.getStatus());
        task.setProcessId(a.getProcessId());
        task.setProcessInstanceId(a.getProcessInstanceId());
        task.setProcessType(a.getProcessType());
        task.setInstanceActivityCreateTime(new Date());
        task.setNodeType(a.getNodeType());
        task.setProcessActivityId(a.getProcessActivityId());
        task.setBackViewName(a.getBackViewName());
        return task;
    }

    /**
     * ***********************
     * 激活下一个流程节点，传进来的是下一个流程节点，如果整个流程已经结束则返回true 否则返回false
     * ************************ @param instance
     * @param activityId
     * @return
     */
    public static boolean activeNextInstanceActivity(ProcessInstance instance, Integer activityId) {
        boolean completed = false;
        for (BaseSystemProcessInstanceActivity a : instance.getActivities()) {
            if (Objects.equals(a.getActivityId(), activityId)) {

                if (a.getNextActivityId() == null || a.getNextActivityId() == 0) {
                    a.setStatus(SystemProcessConstants.ACTIVITY_STATUS_PASS);
                    completed = true;
                } else {
                    a.setStatus(SystemProcessConstants.ACTIVITY_STATUS_ACTIVE);
                    a.setInstanceActivityStartTime(new Date());
                    activePooledTaskAndAttentions(instance, a.getActivityId());
                }
            }
        }
        return completed;
    }

    public static void passCurrentInstanceActivity(ProcessInstance instance, Integer activityId, Integer employeeId,
            String comment, int status, int poolType) {
        instance.getActivities().stream().filter(a -> (Objects.equals(a.getActivityId(), activityId))).map(a -> {
            if (poolType == 0) {
                a.setEmployeeId(employeeId);
            }
            return a;
        }).map(a -> {
            a.setProcessComment(comment);
            return a;
        }).map(a -> {
            a.setStatus(status);
            return a;
        }).map(a -> {
            a.setOperateTime(new Date());
            return a;
        }).filter(a -> (poolType == 0)).forEachOrdered(a -> {
            passPooledTaskAndAttentions(instance, a.getActivityId(), comment);
        });
    }

    public static void newCreateAttentionToActivity(ProcessInstanceActivity pia) {
        if (pia.getAttentions() != null && !pia.getAttentions().isEmpty()) {
            List<BaseSystemProcessAttention> newAttentions = new ArrayList<>();
            pia.getAttentions().stream().map(t -> {
                BaseSystemProcessAttention n = new BaseSystemProcessAttention();
                t.cloneCopy(n);
                return n;
            }).map(n -> {
                n.setSystemProcessAttentionId(null);
                return n;
            }).map(n -> {
                n.setInstanceActivityId(-1);
                return n;
            }).map(n -> {
                n.setActivityId(pia.getActivityId());
                return n;
            }).map(n -> {
                if (pia.getStatus() == SystemProcessConstants.ATTENTION_STATUS_ACTIVE) {
                    n.setStatus(SystemProcessConstants.ATTENTION_STATUS_ACTIVE);
                } else {
                    n.setStatus(SystemProcessConstants.ATTENTION_STATUS_NEW);
                }
                return n;
            }).forEachOrdered(n -> {
                newAttentions.add(n);
            });
            pia.setAttentions(newAttentions);
        }
    }

    public static void newCreateTaskToActivity(ProcessInstanceActivity pia) {
        if (pia.getTasks() != null && !pia.getTasks().isEmpty()) {
            List<BaseSystemProcessPooledTask> newTasks = new ArrayList<>();
            pia.getTasks().stream().map(t -> {
                BaseSystemProcessPooledTask n = new BaseSystemProcessPooledTask();
                t.cloneCopy(n);
                return n;
            }).map(n -> {
                n.setProcessPooledTaskId(null);
                return n;
            }).map(n -> {
                n.setInstanceActivityId(-1);
                return n;
            }).map(n -> {
                n.setActivityId(pia.getActivityId());
                return n;
            }).map(n -> {
                if (pia.getStatus() == SystemProcessConstants.ACTIVITY_STATUS_ACTIVE) {
                    n.setStatus(SystemProcessConstants.POOLED_TASK_STATUS_ACTIVE);
                } else {
                    n.setStatus(SystemProcessConstants.POOLED_TASK_STATUS_NEW);
                }
                return n;
            }).forEachOrdered(n -> {
                newTasks.add(n);
            });
            pia.setTasks(newTasks);
        }
    }

    public static void activePooledTaskAndAttentions(ProcessInstance instance, Integer activityId) {
        if (instance.getTasks() != null && !instance.getTasks().isEmpty()) {
            instance.getTasks().stream().filter(t -> !(!Objects.equals(t.getActivityId(), activityId))).map(t -> {
                t.setStatus(SystemProcessConstants.POOLED_TASK_STATUS_ACTIVE);
                return t;
            }).forEachOrdered(t -> {
                t.setInstanceActivityStartTime(new Date());
            });
        }
        if (instance.getAttentions() != null && !instance.getAttentions().isEmpty()) {
            instance.getAttentions().stream().filter(t -> !(!Objects.equals(t.getActivityId(), activityId))).map(t -> {
                t.setStatus(SystemProcessConstants.POOLED_TASK_STATUS_ACTIVE);
                return t;
            }).forEachOrdered(t -> {
                t.setInstanceActivityStartTime(new Date());
            });
        }
    }

    public static void passPooledTaskAndAttentions(ProcessInstance instance, int activityId, String comment) {
        if (instance.getTasks() != null && !instance.getTasks().isEmpty()) {
            instance.getTasks().stream().filter(t -> !(!Objects.equals(t.getActivityId(), activityId))).map(t -> {
                t.setStatus(SystemProcessConstants.POOLED_TASK_STATUS_DONE);
                return t;
            }).map(t -> {
                t.setOperateTime(new Date());
                return t;
            }).forEachOrdered(t -> {
                t.setProcessComment(comment);
            });
        }
    }

    public static void passCurrentPooledTask(ProcessInstance instance, int activityId, String comment) {
        if (instance.getTasks() != null && !instance.getTasks().isEmpty()) {
            instance.getTasks().stream().filter(t -> !(!Objects.equals(t.getProcessPooledTaskId(), activityId))).map(t -> {
                t.setStatus(SystemProcessConstants.POOLED_TASK_STATUS_DONE);
                return t;
            }).map(t -> {
                t.setOperateTime(new Date());
                return t;
            }).forEachOrdered(t -> {
                t.setProcessComment(comment);
            });
        }
    }

    public static boolean isAllPooledTaskPassed(ProcessInstance instance, BaseSystemProcessPooledTask task) {
        if (instance.getTasks() != null && !instance.getTasks().isEmpty()) {
            return instance.getTasks().stream().filter(t -> !(!Objects.equals(t.getActivityId(), task.getActivityId()))).filter(t -> !(t.getProcessPooledTaskId().equals(task.getProcessPooledTaskId()))).noneMatch(t -> (t.getStatus() != SystemProcessConstants.POOLED_TASK_STATUS_DONE));
        } else {
            return true;
        }
    }

    public static void changeActivityId(ProcessInstance instance, Integer instanceActivityId, int activityId,
            int newId) {
        if (instance.getTasks() != null && !instance.getTasks().isEmpty()) {
            instance.getTasks().stream().filter(t -> !(!Objects.equals(t.getActivityId(), activityId))).filter(t -> !(!Objects.equals(t.getInstanceActivityId(), instanceActivityId))).forEachOrdered(t -> {
                t.setActivityId(newId);
            });
        }
        if (instance.getAttentions() != null && !instance.getAttentions().isEmpty()) {
            instance.getAttentions().stream().filter(a -> !(!Objects.equals(a.getActivityId(), activityId))).filter(a -> !(!Objects.equals(a.getInstanceActivityId(), instanceActivityId))).forEachOrdered(a -> {
                a.setActivityId(newId);
            });
        }
    }

    public static void resetActivityId(ProcessInstance instance, List<ProcessInstanceActivity> nl) {
        int count = 0;
        for (BaseSystemProcessInstanceActivity a : nl) {
            int oid, nid;
            oid = a.getActivityId();
            a.setActivityId(count++);
            nid = a.getActivityId();
            if (oid != nid) {
                changeActivityId(instance, a.getProcessInstanceActivityId(), oid, nid);
            }
            if (a.getActivityType() != 1) {
                a.setNextActivityId(count);
            } else {
                a.setNextActivityId(0);
            }
        }
    }

    public static void setAttentionActivityId(List<ProcessInstanceActivity> nl, List<BaseSystemProcessAttention> atts) {
        if (atts != null && !atts.isEmpty() && nl != null && !nl.isEmpty()) {
            //遍历获取每一个节点的审批人员，以activityId作为Key值存到activityEmployeeMap中
        	Set<Integer> set = new HashSet<>();
            nl.stream().map((ProcessInstanceActivity act) -> {
                if (null != act.getTasks() && !act.getTasks().isEmpty()) {
                    act.getTasks().forEach(task -> {
                        set.add(task.getEmployeeId());
                    });
                } else {
                    set.add(act.getEmployeeId());
                }
                return act;
            }).forEachOrdered(act -> {
                act.setAttentions(new ArrayList<>());
            });
            List<BaseSystemProcessAttention> natts = new ArrayList<>();
            atts.forEach(na -> {
                natts.add(na);
            });
            atts.clear();
            //已经知会的人员集合
            Set<Integer> attEmpSet = new HashSet<>();
            for (BaseSystemProcessAttention a : natts) {
                for (ProcessInstanceActivity act : nl) {
                    if (Objects.equals(a.getActivityId(), act.getActivityId())) {
                    	Boolean ifAllowOver = a.getIfAllowOver();
                    	//不允许跳过  或者  审批人员中不包含这个人
                        if ((null != ifAllowOver && !ifAllowOver) || !set.contains(a.getEmployeeId())) {
                            if(null == ifAllowOver || ifAllowOver) {
                            	//如果该知会节点允许跳过，则判断之前的知会节点中是否已知会了该人员。如果已知会，则跳过该节点。
                            	if(attEmpSet.contains(a.getEmployeeId())) {
                                	continue;
                                }
                            }
                            attEmpSet.add(a.getEmployeeId());
                        	a.setInstanceActivityId(act.getProcessInstanceActivityId());
                            switch(act.getStatus()) {
                            case SystemProcessConstants.ACTIVITY_STATUS_NEW_CREATED:
                            	a.setStatus(SystemProcessConstants.ATTENTION_STATUS_NEW);
                            	break;
                            default:
                            	a.setStatus(SystemProcessConstants.ATTENTION_STATUS_ACTIVE);
                            	break;
                            }
                            atts.add(a);
                            act.getAttentions().add(a);
                        }
                        break;
                    }
                }
            }
        }
    }

    public static void setPooledTaskActivityId(List<ProcessInstanceActivity> nl,
            List<BaseSystemProcessPooledTask> atts) {
        if (atts != null && !atts.isEmpty()) {
            atts.forEach((BaseSystemProcessPooledTask a) -> {
                nl.stream().filter(act -> (Objects.equals(a.getActivityId(), act.getActivityId()))).map(act -> {
                    a.setInstanceActivityId(act.getProcessInstanceActivityId());
                    return act;
                }).forEachOrdered(act -> {
                    a.setBackViewName(act.getBackViewName());
                });
            });
        }
    }

    public static int saveInstanceActivity(List<ProcessInstanceActivity> instanceActivities, List<BaseSystemProcessAttention> attentions,
            List<BaseSystemProcessPooledTask> tasks) throws SQLException {
        int doneNum = 0;
        if (null == instanceActivities || instanceActivities.isEmpty()) {
            return doneNum;
        }
        List<ProcessInstanceActivity> newInstanceActivities = new ArrayList<>();
        instanceActivities.forEach(act -> {
            newInstanceActivities.add(act);
        });
        instanceActivities.clear();
        int idx = 0;
        int size = newInstanceActivities.size();
        ProcessInstanceActivity tempActivity = null;
        SystemProcessInstanceActivity spia = new SystemProcessInstanceActivity();
        // 挨个保存每个实例节点
        for (int i = 0; i < size; i++) {
            ProcessInstanceActivity act = newInstanceActivities.get(i);
            //开始和结束节点不允许合并，所以这里跳过开始和结束节点
            if (i > 0 && i < size - 1) {
                if (null != act.getTasks() && !act.getTasks().isEmpty()) {
                    //任务池节点，不允许合并，tempActivity置空
                    tempActivity = null;
                } else {
                    //不是任务池节点
                    if (null != tempActivity) {
                    	if(Objects.equals(tempActivity.getEmployeeId(), act.getEmployeeId())) {
	                        //该节点可以跟上一个节点合并
	                        updateAttentionActivityIdByProcessActivityId(attentions, act.getProcessActivityId(), tempActivity.getActivityId());
	                        if (act.getActivityType() == 3) {
	                            //如果该节点是处理节点，则节点名称、节点类型等都以该节点的为准
	                            spia.clear();
	                            spia.setDataFromBase(tempActivity);
	                            spia.clearModifiedFlags();
	                            spia.setProcessActivityId(act.getProcessActivityId());
	                            spia.setActivityType(act.getActivityType());
	                            spia.setNodeType(act.getNodeType());
	                            spia.setBackViewName(act.getBackViewName());
	                            spia.setMessageStatus(act.getMessageStatus());
	                            //处理节点，在新建流程或者撤销修改的时候，只有可能是新建状态或者是活跃状态，所以如果合并节点状态为已处理状态（status = 2）的话，则将status的值修改为1
	                            if(tempActivity.getStatus() == SystemProcessConstants.ACTIVITY_STATUS_PASS) {
	                            	spia.setStatus(SystemProcessConstants.ACTIVITY_STATUS_ACTIVE);
	                            }
	                            spia.setOperateTime(null);
	                            spia.setProcessComment(null);
	                            spia.update();
	                            instanceActivities.get(instanceActivities.size() - 1).setActivityType(act.getActivityType());
	                            instanceActivities.get(instanceActivities.size() - 1).setStatus(spia.getStatus());
	                            //因为处理节点可能会有些特殊操作，所以仅允许合并一个处理节点，合并完之后，将tempActivity置空
	                            tempActivity = null;
	                        }
	                        continue;
                    	}else {
                    		tempActivity = null;
                    	}
                    }
                }
            }
            spia.clear();
            /*因为合并节点可能会导致实际的activityId跟创建时的activityId不一致。
        	 * 不一致的时候，需要更新：
        	 * ① 该节点的activityId和nextActivityId；
        	 * ② 该节点的知会节点以及任务池节点的activityId。
        	 * （因为后续更新知会节点和任务池节点的processInstanceActivityId就是以activityId作为条件去更新的）*/
            if (!Objects.equals(idx, act.getActivityId())) {
                updateAttentionActivityIdByProcessActivityId(attentions, act.getProcessActivityId(), idx);
                updateTaskActivityIdByProcessActivityId(tasks, act.getProcessActivityId(), idx);
                act.setActivityId(idx++);
                act.setNextActivityId(idx);
            } else {
                idx++;
            }
            if (i == size - 1) {
                act.setNextActivityId(0);
            }
            spia.setDataFromBase(act);
            spia.save();
            act.setProcessInstanceActivityId(spia.getProcessInstanceActivityId());
            if (i > 0 && act.getActivityType() != 3 && (null == act.getTasks() || act.getTasks().isEmpty())) {
                //不是开始节点，不是任务池，也不是处理节点，则该节点属于允许合并的节点
                tempActivity = act;
            }
            if (null != spia.getStatus() && spia.getStatus() == SystemProcessConstants.POOLED_TASK_STATUS_DONE) {
                doneNum++;
            }

            instanceActivities.add(act);
        }
        return doneNum;
    }

    /**
     * 根据processActivityId，修改符合条件的attentions的activityId
     *
     * @param attentions
     * @param processActivityId
     * @param activityId
     */
    public static void updateAttentionActivityIdByProcessActivityId(List<BaseSystemProcessAttention> attentions, Integer processActivityId, Integer activityId) {
        if (null == attentions || attentions.isEmpty() || null == processActivityId || null == activityId) {
            return;
        }
        attentions.stream().filter(att -> (Objects.equals(att.getProcessActivityId(), processActivityId))).forEachOrdered(att -> {
            att.setActivityId(activityId);
        });
    }

    /**
     * 根据processActivityId，修改符合条件的tasks的activityId
     *
     * @param tasks
     * @param processActivityId
     * @param activityId
     */
    public static void updateTaskActivityIdByProcessActivityId(List<BaseSystemProcessPooledTask> tasks, Integer processActivityId, Integer activityId) {
        if (null == tasks || tasks.isEmpty() || null == processActivityId || null == activityId) {
            return;
        }
        tasks.stream().filter(task -> (Objects.equals(task.getProcessActivityId(), processActivityId))).forEachOrdered(task -> {
            task.setActivityId(activityId);
        });
    }
}
