package delicacy.sys.business;

import common.utils.UserInfoUtils;
import delicacy.common.BusinessAbstract;
import delicacy.common.GenericProcessor;
import delicacy.connection.ThreadConnection;
import delicacy.servlet.AbstractProcessores;
import delicacy.sys.bean.BaseSystemProcessActivity;
import delicacy.sys.bean.BaseSystemProcessAttention;
import delicacy.sys.bean.BaseSystemProcessPooledTask;
import delicacy.sys.dao.SystemProcessAttention;
import delicacy.sys.dao.SystemProcessInstance;
import delicacy.sys.dao.SystemProcessInstanceActivity;
import delicacy.sys.dao.SystemProcessPooledTask;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author guangxun
 */
public class NewCreateWorkFlow implements GenericProcessor {

    private GenericWorkflowProcessor gwp = null;
    private SystemProcessDefinition processDefinition;
    private static final Logger __logger = Logger.getLogger(NewCreateWorkFlow.class);

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
        return execute(creteria, operatorId,organizationId, true);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public String execute(String source, int operator,int organizationId, boolean useTransaction) throws Exception {
        // 前台将以ProcessInformation的结构形式打包过来
        ProcessInformation processInformation = new ProcessInformation();
        processInformation.setDataFromJSON(source);
        // 从数据库中装载流程定义信息
        processDefinition = ProcessUtil.loadProcess(processInformation.getProcessDefinition().getProcessId());
        // 获取客户化业务流程处理的程序-根据每个流程执行程序的名字,从系统的任务分发表
        String executeClassName = AbstractProcessores
                .getWorkflowProcessorClass(processDefinition.getProcessType().getProcessExecuteName());
        if (executeClassName == null) {
            throw new SQLException("process execute class not found");
        }
        if (useTransaction) {
            ThreadConnection.beginTransaction();
        }
        // 实例化客户流程处理程序
        gwp = (GenericWorkflowProcessor) Class.forName(executeClassName).newInstance();
        // 获取从前端传过来的业务数据-前端的业务数据经过解析后会保存在ProcessInformation的userData中
        Map params = (Map) processInformation.getUserData();
        params.put("organizationId", organizationId);
        // 执行客户化流程处理程序的提交新的业务流程方法,该方法会输入一个Map数据,返回一个业务主键,保存在我们流程实例中
        BusinessAbstract businessId = gwp.startNewWorkflow(processDefinition, params, operator);
        // 新建一个流程的实例
        SystemProcessInstance spi = new SystemProcessInstance();
        spi.setProcessId(processDefinition.getProcessId());
        spi.setBusinessId(businessId.getBusinessId());
        spi.setBusinessName(businessId.getBusinessSubject());
        spi.setCreateTime(new Date());
        spi.setEmployeeId(operator);
        spi.setOrganizationId(organizationId);
        spi.setProcessType(processDefinition.getProcessTypeId());
        spi.setProcessStatus(SystemProcessConstants.PROCESS_STATUS_NEW_CREATED);

        // 查找下一个流程节点,先找到流程的第一个节点
        BaseSystemProcessActivity firstActivity = ProcessUtil.firstActivity(processDefinition);
        // 查找下一个流程节点
        BaseSystemProcessActivity nextActivity = ProcessUtil.findNextActivity(processDefinition, gwp, firstActivity);
        boolean completed = false;
        // 看看是否下一个流程节点是结束节点,如果是结束节点,即将流程结束
        if (nextActivity.getActivitySerialNo() == SystemProcessConstants.PROCESS_LAST_ACTIVITY) {
            spi.setProcessStatus(SystemProcessConstants.PROCESS_STATUS_COMPLETED);
            completed = true;
        }
        // 保存流程实例
        spi.save();
        // 实例化流程节点实例的DAO
        SystemProcessInstanceActivity spia = new SystemProcessInstanceActivity();
        List<ProcessInstanceActivity> instanceActivities = new ArrayList<>();
        List<BaseSystemProcessPooledTask> tasks = new ArrayList<>();

        int idx = 0;
        //开始节点
        ProcessInstanceActivity ia = ProcessUtil.createInstanceActivity(processDefinition.getProcessId(),
                spi.getProcessInstanceId(), processDefinition.getProcessTypeId(), businessId.getBusinessId(),
                businessId.getBusinessSubject(),organizationId);
        ia.setProcessActivityId(firstActivity.getProcessActivityId());
        ia.setEmployeeId(operator);
        ia.setOperateTime(new Date());
        ia.setProcessComment("");
        ia.setActivityType(firstActivity.getActivityType());
        ia.setActivityId(idx++);
        ia.setNextActivityId(idx);
        ia.setStatus(SystemProcessConstants.ACTIVITY_STATUS_PASS);
        instanceActivities.add(ia);

        //如果添加了额外的审批人，则在开始节点之后新增一个审批节点
        if (processInformation.getAdditionalApprovalment() != null) {
            ia = ProcessUtil.createInstanceActivity(processDefinition.getProcessId(), spi.getProcessInstanceId(),
                    processDefinition.getProcessTypeId(), businessId.getBusinessId(), businessId.getBusinessSubject(),organizationId);
            ia.setProcessActivityId(null);
            ia.setEmployeeId(processInformation.getAdditionalApprovalment());
            ia.setOperateTime(null);
            ia.setProcessComment("");
            ia.setActivityType(2);
            ia.setNodeType(1);
            ia.setActivityId(idx++);
            ia.setNextActivityId(idx);
            ia.setStatus(SystemProcessConstants.ACTIVITY_STATUS_ACTIVE);
            instanceActivities.add(ia);
        }
        boolean jumpOver = false;
        boolean pooled = false;
        ia = ProcessUtil.createInstanceActivity(processDefinition.getProcessId(), spi.getProcessInstanceId(),
                processDefinition.getProcessTypeId(), businessId.getBusinessId(), businessId.getBusinessSubject(),organizationId);
        ia.setProcessActivityId(nextActivity.getProcessActivityId());
        ia.setActivityType(nextActivity.getActivityType());
        ia.setBackViewName(nextActivity.getActivityName());
        if (completed) {
            ia.setStatus(SystemProcessConstants.ACTIVITY_STATUS_PASS);
            ia.setEmployeeId(operator);
            ia.setActivityId(idx++);
            ia.setNextActivityId(0);
            ia.setOperateTime(new Date());
        } else {
            ia.setActivityId(idx++);
            ia.setNextActivityId(idx);
            if (processInformation.getAdditionalApprovalment() == null) {
                ia.setStatus(SystemProcessConstants.ACTIVITY_STATUS_ACTIVE);
            } else {
                ia.setStatus(SystemProcessConstants.ACTIVITY_STATUS_NEW_CREATED);
            }
            int[] nextEmployeeId = gwp.findActivityOwner(spi.getEmployeeId(),
                    nextActivity.getEmployeeId() == 0 || nextActivity.getEmployeeId() == null ? null
                    : nextActivity.getEmployeeId(),
                    nextActivity.getDepartmentId() == 0 || nextActivity.getDepartmentId() == null ? null
                    : nextActivity.getDepartmentId(),
                    nextActivity.getRoleId() == 0 || nextActivity.getRoleId() == null ? null : nextActivity.getRoleId(),
                    nextActivity.getPoolType());
            if (nextEmployeeId == null || nextEmployeeId.length == 0) {
                BaseSystemProcessActivity processActivity = ProcessUtil.findProcessActivityById(processDefinition,
                        ia.getProcessActivityId());
                if (processActivity == null || !processActivity.getIfAllowOver()) {
                    throw new SQLException("流程节点（" + nextActivity.getActivityName() + "）找不到审批人员");
                } else {
                    jumpOver = true;
                }
            }
            if (!jumpOver) {
                if (nextEmployeeId.length == 1) {
                    ia.setEmployeeId(nextEmployeeId[0]);
                } else {
                    ia.setPoolType(nextActivity.getPoolType());
                    ia.setEmployeeId(null);
                    for (Integer ii : nextEmployeeId) {
                        BaseSystemProcessPooledTask task = ProcessUtil.createPooledTask(ia);
                        task.setEmployeeId(ii);
                        tasks.add(task);
                    }
                }
                ia.setOperateTime(null);
                ia.setProcessComment(null);
            }
        }
        if (!pooled) {
            if (!jumpOver) {
                instanceActivities.add(ia);
            } else {
                idx--;
            }
        }
        BaseSystemProcessActivity bspalast = nextActivity;
        if (!completed) {
            completed = false;
            while (true) {
                jumpOver = false;
                pooled = false;
                BaseSystemProcessActivity bspa = ProcessUtil.findNextActivity(processDefinition, gwp, bspalast);
                if (bspa.getActivitySerialNo() == SystemProcessConstants.PROCESS_LAST_ACTIVITY) {
                    completed = true;
                }
                ia = ProcessUtil.createInstanceActivity(processDefinition.getProcessId(), spi.getProcessInstanceId(),
                        processDefinition.getProcessTypeId(), businessId.getBusinessId(),
                        businessId.getBusinessSubject(),organizationId);
                ia.setProcessActivityId(bspa.getProcessActivityId());
                ia.setActivityType(bspa.getActivityType());
                ia.setBackViewName(bspa.getActivityName());
                if (completed) {
                    ia.setActivityId(idx++);
                    ia.setNextActivityId(0);
                    ia.setStatus(SystemProcessConstants.ACTIVITY_STATUS_PASS);
                    ia.setEmployeeId(operator);
                    ia.setOperateTime(new Date());
                } else {
                    ia.setActivityId(idx++);
                    ia.setNextActivityId(idx);
                    if (instanceActivities.size() < 2) {
                        ia.setStatus(SystemProcessConstants.ACTIVITY_STATUS_ACTIVE);
                    } else {
                        ia.setStatus(SystemProcessConstants.ACTIVITY_STATUS_NEW_CREATED);
                    }
                    int[] nextEmployeeId = gwp.findActivityOwner(spi.getEmployeeId(),
                            bspa.getEmployeeId() == 0 || bspa.getEmployeeId() == null ? null : bspa.getEmployeeId(),
                            bspa.getDepartmentId() == 0 || bspa.getDepartmentId() == null ? null
                            : bspa.getDepartmentId(),
                            bspa.getRoleId() == 0 || bspa.getRoleId() == null ? null : bspa.getRoleId(),
                            bspa.getPoolType());
                    if (nextEmployeeId == null || nextEmployeeId.length == 0) {
                        BaseSystemProcessActivity processActivity = ProcessUtil
                                .findProcessActivityById(processDefinition, ia.getProcessActivityId());
                        if (processActivity == null || !processActivity.getIfAllowOver()) {
                            throw new SQLException("流程节点（" + bspa.getActivityName() + "）找不到审批人员");
                        } else {
                            jumpOver = true;
                        }
                    }
                    if (!jumpOver) {
                        if (nextEmployeeId.length == 1) {
                            ia.setEmployeeId(nextEmployeeId[0]);
                        } else {
                            ia.setPoolType(bspa.getPoolType());
                            ia.setEmployeeId(null);
                            for (Integer ii : nextEmployeeId) {
                                BaseSystemProcessPooledTask task = ProcessUtil.createPooledTask(ia);
                                task.setEmployeeId(ii);
                                tasks.add(task);
                            }
                        }
                        ia.setOperateTime(null);
                        ia.setProcessComment(null);
                    }
                }
                if (!pooled) {
                    if (!jumpOver) {
                        instanceActivities.add(ia);
                    } else {
                        idx--;
                    }
                }
                if (completed) {
                    break;
                }
                bspalast = bspa;
            }
        }
        for (ProcessInstanceActivity i : instanceActivities) {

            i.setInstanceActivityCreateTime(new Date());
            if (i.getStatus() == SystemProcessConstants.ACTIVITY_STATUS_ACTIVE) {
                i.setInstanceActivityStartTime(new Date());
            }
            i.setOperateTime(null);
        }
        ProcessUtil.processInstanceActivity(instanceActivities, processDefinition);
        // 算出每个流程实例节点下面对应的知会人节点
        List<BaseSystemProcessAttention> attentions = ProcessUtil.generateAllActivityAttentions(instanceActivities,
                gwp, processDefinition, spi.getEmployeeId());

        // 找出每个流程实例节点下面的任务池节点
        ProcessUtil.findOutAllTasks(instanceActivities, tasks);
        // 处理如果流程中当前活动节点的处理人和申请人相同，则设置为自动跳过状态，同时设置下一个节点为活动节点
        ProcessUtil.findNextAvailableActivity(1, instanceActivities);
        // 挨个保存每个实例节点
        int doneNum = 0;
        for (ProcessInstanceActivity i : instanceActivities) {
            spia.clear();
            spia.setDataFromBase(i);
            spia.save();
            i.setProcessInstanceActivityId(spia.getProcessInstanceActivityId());
            if (null != spia.getStatus() && spia.getStatus().intValue() == SystemProcessConstants.POOLED_TASK_STATUS_DONE) {
                doneNum++;
            }
        }
        if (doneNum == instanceActivities.size()) {
            //所有节点都已跳过，流程已经跑完
            spi.setProcessStatus(SystemProcessConstants.PROCESS_STATUS_COMPLETED);
            spi.setCompleteTime(new Date());
            spi.update();
        }
        // 更新所有知会人节点的流程实例节点的ID
        ProcessUtil.setAttentionActivityId(instanceActivities, attentions);
        // 更新所有任务池节点的流程实例节点ID
        ProcessUtil.setPooledTaskActivityId(instanceActivities, tasks);
        // 如果知会人节点列表不为空，则保存知会人节点列表
        if (!attentions.isEmpty()) {
            SystemProcessAttention attentionDao = new SystemProcessAttention();
            attentionDao.save(attentions);
        }
        // 如果任务池不为空，则保存任务池节点列表
        if (!tasks.isEmpty()) {
            SystemProcessPooledTask taskDao = new SystemProcessPooledTask();
            taskDao.save(tasks);
        }
        // 把数据封装，准备返回
        ProcessInstance pi = new ProcessInstance();
        // 流程实例数据
        spi.generateBase().cloneCopy(pi);
        // 流程实例处理节点
        pi.setActivities(instanceActivities);
        // 知会人节点列表
        pi.setAttentions(attentions);

        // 提交事务 
        if (useTransaction) {
            ThreadConnection.commit();
        }
        // 返回流程实例的封装数据
        return pi.toOneLineJSON();
    }

    /**
     * @return the processDefinition
     */
    public SystemProcessDefinition getProcessDefinition() {
        return processDefinition;
    }

    /**
     * @param processDefinition the processDefinition to set
     */
    public void setProcessDefinition(SystemProcessDefinition processDefinition) {
        this.processDefinition = processDefinition;
    }
}
