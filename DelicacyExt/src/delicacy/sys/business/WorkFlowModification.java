package delicacy.sys.business;

import common.utils.UserInfoUtils;
import delicacy.common.BaseHelpUtils;
import delicacy.common.BusinessAbstract;
import delicacy.common.GenericProcessor;
import delicacy.connection.ThreadConnection;
import delicacy.servlet.AbstractProcessores;
import delicacy.sys.bean.BaseSystemProcessActivity;
import delicacy.sys.bean.BaseSystemProcessAttention;
import delicacy.sys.bean.BaseSystemProcessInstanceActivity;
import delicacy.sys.bean.BaseSystemProcessPooledTask;
import delicacy.sys.dao.SystemProcessAttention;
import delicacy.sys.dao.SystemProcessInstance;
import delicacy.sys.dao.SystemProcessInstanceActivity;
import delicacy.sys.dao.SystemProcessPooledTask;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author guangxun
 */
public class WorkFlowModification implements GenericProcessor {

	private GenericWorkflowProcessor gwp = null;
	private static final Logger __logger = Logger.getLogger(WorkFlowModification.class);

	@Override
	public String execute(String creteria, HttpServletRequest request) throws Exception {
		// 获取操作员,部门,角色等信息
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

	public String execute(String source, int operator,int organizationId) throws Exception {
		// 前台将以ProcessInformation的结构形式打包过来
		ProcessInformation processInformation = new ProcessInformation();
		processInformation.setDataFromJSON(source);

		ProcessInstance processInstance = processInformation.getProcessInstance();
		
		SystemProcessDefinition definition = processInformation.getProcessDefinition();

		String executeClassName = AbstractProcessores
				.getWorkflowProcessorClass(definition.getProcessType().getProcessExecuteName());
		if (executeClassName == null) {
			throw new SQLException("process execute class not found");
		}
		ThreadConnection.beginTransaction();
		// 实例化客户流程处理程序
		gwp = (GenericWorkflowProcessor) Class.forName(executeClassName).newInstance();
		// 获取从前端传过来的业务数据-前端的业务数据经过解析后会保存在ProcessInformation的userData中
		Map params = (Map) processInformation.getUserData();
		params.put("organizationId", organizationId);
		// 执行客户化流程处理程序的提交新的业务流程方法,该方法会输入一个Map数据,返回一个业务主键,保存在我们流程实例中
		BusinessAbstract businessId = gwp.executeActivity(processInformation, params, operator, true, false);

		processInstance.setBusinessName(businessId.getBusinessSubject());
		SystemProcessInstance spi = new SystemProcessInstance();
		spi.setConditionProcessId("=", definition.getProcessId());
		spi.setConditionBusinessId("=", businessId.getBusinessId());
		spi.setBusinessName(businessId.getBusinessSubject());
		spi.conditionalUpdate();
		// 获取流程实例ID
		int processInstanceId = BaseHelpUtils.getIntValue(spi.getProcessInstanceId());
		// find next activity
		BaseSystemProcessActivity firstActivity = ProcessUtil.firstActivity(definition);
		BaseSystemProcessActivity nextActivity = ProcessUtil.findNextActivity(definition, gwp, firstActivity);
		boolean completed = false;
		if (nextActivity.getActivitySerialNo() == SystemProcessConstants.PROCESS_LAST_ACTIVITY) {
			completed = true;
		}
		// create first activity for the process instance
		SystemProcessInstanceActivity spia = new SystemProcessInstanceActivity();
		List<ProcessInstanceActivity> instanceActivities = new ArrayList<>();
		List<BaseSystemProcessPooledTask> tasks = new ArrayList<>();
		boolean jumpOver = false;
		boolean pooled = false;
		int idx = 0;
		ProcessInstanceActivity ia = ProcessUtil.createInstanceActivity(definition.getProcessId(),
				processInstance.getProcessInstanceId(), definition.getProcessTypeId(), businessId.getBusinessId(),
				businessId.getBusinessSubject(),organizationId);
		ia.setProcessActivityId(firstActivity.getProcessActivityId());
		ia.setEmployeeId(operator);
		ia.setOperateTime(new Date());
		ia.setProcessComment("");
		ia.setActivityId(idx++);
		ia.setNextActivityId(idx);
		ia.setActivityType(firstActivity.getActivityType());
		ia.setStatus(SystemProcessConstants.ACTIVITY_STATUS_PASS);
		instanceActivities.add(ia);

		if (processInformation.getAdditionalApprovalment() != null) {
			ia = ProcessUtil.createInstanceActivity(definition.getProcessId(), processInstance.getProcessInstanceId(),
					definition.getProcessTypeId(), businessId.getBusinessId(), businessId.getBusinessSubject(),organizationId);
			ia.setProcessActivityId(0);
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

		ia = ProcessUtil.createInstanceActivity(definition.getProcessId(), processInstance.getProcessInstanceId(),
				definition.getProcessTypeId(), businessId.getBusinessId(), businessId.getBusinessSubject(),organizationId);
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
			int[] nextEmployeeId = gwp.findActivityOwner(processInstance.getEmployeeId(),
					nextActivity.getEmployeeId() == 0 || nextActivity.getEmployeeId() == null ? null
							: nextActivity.getEmployeeId(),
					nextActivity.getDepartmentId() == 0 || nextActivity.getDepartmentId() == null ? null
							: nextActivity.getDepartmentId(),
					nextActivity.getRoleId() == 0 || nextActivity.getRoleId() == null ? null
							: nextActivity.getRoleId(), nextActivity.getPoolType());
			if (nextEmployeeId == null || nextEmployeeId.length == 0) {
				BaseSystemProcessActivity processActivity = ProcessUtil.findProcessActivityById(definition,
						ia.getProcessActivityId());
				if (processActivity == null || !processActivity.getIfAllowOver())
					throw new SQLException("Could not find the next activity owner");
				else
					jumpOver = true;
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
			if (!jumpOver)
				instanceActivities.add(ia);
			else {
				idx--;
			}
		}
		BaseSystemProcessActivity bspalast = nextActivity;
		if (!completed) {
			completed = false;
			while (true) {
				jumpOver = false;
				pooled = false;
				BaseSystemProcessActivity bspa = ProcessUtil.findNextActivity(definition, gwp, bspalast);
				if (bspa.getActivitySerialNo() == SystemProcessConstants.PROCESS_LAST_ACTIVITY) {
					completed = true;
				}
				ia = ProcessUtil.createInstanceActivity(definition.getProcessId(),
						processInstance.getProcessInstanceId(), definition.getProcessTypeId(),
						businessId.getBusinessId(), businessId.getBusinessSubject(),organizationId);
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
					int[] nextEmployeeId = gwp.findActivityOwner(processInstance.getEmployeeId(),
							bspa.getEmployeeId() == 0 || bspa.getEmployeeId() == null ? null : bspa.getEmployeeId(),
							bspa.getDepartmentId() == 0 || bspa.getDepartmentId() == null ? null
									: bspa.getDepartmentId(),
							bspa.getRoleId() == 0 || bspa.getRoleId() == null ? null : bspa.getRoleId(), bspa.getPoolType());
					if (nextEmployeeId == null || nextEmployeeId.length == 0) {
						BaseSystemProcessActivity processActivity = ProcessUtil.findProcessActivityById(definition,
								ia.getProcessActivityId());
						if (processActivity == null || !processActivity.getIfAllowOver())
							throw new SQLException("Could not find the next activity owner");
						else
							jumpOver = true;
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
					if (!jumpOver)
						instanceActivities.add(ia);
					else
						idx--;
				}
				if (completed) {
					break;
				}
				bspalast = bspa;
			}
		}
		for (BaseSystemProcessInstanceActivity i : instanceActivities) {
			i.setInstanceActivityCreateTime(new Date());
			if (i.getStatus() == SystemProcessConstants.ACTIVITY_STATUS_ACTIVE) {
				i.setInstanceActivityStartTime(new Date());
			}
			i.setOperateTime(null);
		}
		boolean isChanged = false;
		for (BaseSystemProcessInstanceActivity i : instanceActivities) {
			boolean found = false;
			for (BaseSystemProcessInstanceActivity j : processInstance.getActivities()) {
				if (Objects.equals(i.getEmployeeId(), j.getEmployeeId())
						&& Objects.equals(i.getActivityType(), j.getActivityType())
						&& Objects.equals(i.getProcessActivityId(), j.getProcessActivityId())
						&& Objects.equals(i.getNodeType(), j.getNodeType())
						&& Objects.equals(i.getActivityId(), j.getActivityId())
						&& Objects.equals(i.getNextActivityId(), j.getNextActivityId())) {
					found = true;
					break;
				}
			}
			if (!found) {
				isChanged = true;
				break;
			}
		}
		// 算出每个流程实例节点下面对应的知会人节点
		List<BaseSystemProcessAttention> attentions = ProcessUtil.generateAllActivityAttentions(instanceActivities,
				gwp, definition, operator);
		// 找出每个流程实例节点下面的任务池节点
		ProcessUtil.findOutAllTasks(instanceActivities, tasks);
		// 处理如果流程中当前活动节点的处理人和申请人相同，则设置为自动跳过状态，同时设置下一个节点为活动节点
		ProcessUtil.jumpActivityProcess(instanceActivities);
		if (isChanged) {
			spia.setConditionProcessInstanceId("=", processInstance.getProcessInstanceId());
			spia.conditionalDelete();
			for (ProcessInstanceActivity i : instanceActivities) {
				spia.clear();
				spia.setDataFromBase(i);
				spia.save();
				i.setProcessInstanceActivityId(spia.getProcessInstanceActivityId());
			}
		}
		ProcessUtil.setAttentionActivityId(instanceActivities, attentions);
		ProcessUtil.setPooledTaskActivityId(instanceActivities, tasks);
		if (!attentions.isEmpty()) {
			SystemProcessAttention attentionDao = new SystemProcessAttention();
			attentionDao.setConditionProcessInstanceId("=", processInstance.getProcessInstanceId());
			attentionDao.conditionalDelete();
			attentionDao.save(attentions);
		}
		if (!tasks.isEmpty()) {
			SystemProcessPooledTask taskDao = new SystemProcessPooledTask();
			taskDao.setConditionProcessInstanceId("=", processInstance.getProcessInstanceId());
			taskDao.conditionalDelete();
			taskDao.save(tasks);
		}
		if (isChanged) {
			ProcessInstance pi = new ProcessInstance();
			processInstance.cloneCopy(pi);
			pi.setActivities(instanceActivities);
			pi.setAttentions(attentions);
			ThreadConnection.commit();
			return pi.toOneLineJSON();
		} else {
			ThreadConnection.commit();
			return processInstance.toOneLineJSON();
		}
	}

}
