package delicacy.system.executor;

import delicacy.common.BusinessAbstract;
import delicacy.common.GenericWorkflowProcessor;
import delicacy.system.bean.ProcessInformation;
import delicacy.system.bean.SystemProcessDefinition;

import java.util.Map;

/**
 *
 * @author guangxun
 */
public class AbstractWorkflowProcessor implements GenericWorkflowProcessor {

	protected Map __parameter;
	protected ProcessInformation __processInformation;

	@Override
	public BusinessAbstract startNewWorkflow(SystemProcessDefinition pd, Map params, int employeeId) throws Exception {
		setParameter(params);
		return null;
	}

	@Override
	public BusinessAbstract executeActivity(ProcessInformation pi, Map params, int employeeId, boolean modified, boolean completed)
			throws Exception {
		setParameter(params);
		return null;
	}

	@Override
	public boolean computeExpression(String expression) throws Exception {

		return false;
	}

	@Override
	public int[] findActivityOwner(int processCreator, Integer employeeId, Integer departmentId, Integer roleId, Integer poolType)
			throws Exception {
		return new int[0];
	}

	@Override
	public String getBusinessData(Integer businessId) throws Exception {
		return null;
	}

	/**
	 * @return the parameter
	 */
	public Map getParameter() {
		return __parameter;
	}

	/**
	 * @param parameter
	 *            the parameter to set
	 */
	public void setParameter(Map parameter) {
		this.__parameter = parameter;
	}

	/**
	 * @return the __processInformation
	 */
	public ProcessInformation getProcessInformation() {
		return __processInformation;
	}

	/**
	 * @param __processInformation
	 *            the __processInformation to set
	 */
	public void setProcessInformation(ProcessInformation __processInformation) {
		this.__processInformation = __processInformation;
	}

}
