package delicacy.sys.business;

import java.util.Map;

import delicacy.common.BusinessAbstract;


public interface GenericWorkflowProcessor {

	public BusinessAbstract startNewWorkflow(SystemProcessDefinition pd, Map params, int employeeId)
			throws Exception;

	public BusinessAbstract executeActivity(ProcessInformation pi, Map params, int employeeId, boolean modified,
			boolean completed) throws Exception;

	public boolean computeExpression(String expression) throws Exception;

	public int[] findActivityOwner(int processCreator, Integer employeeId, Integer departmentId, Integer roleId,
			Integer poolType) throws Exception;

	public String getBusinessData(Integer businessId) throws Exception;
}

