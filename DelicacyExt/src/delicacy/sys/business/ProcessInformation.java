package delicacy.sys.business;

import java.util.Map;

import delicacy.common.GenericBase;
import delicacy.common.MapUtils;

/**
 *
 * @author Peter
 */
public class ProcessInformation extends GenericBase {

	private SystemProcessDefinition processDefinition;
        private Integer additionalApprovalment = null;
	private ProcessInstance processInstance;
	private Map userData;
	private String customData;

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

	/**
	 * @return the processInstance
	 */
	public ProcessInstance getProcessInstance() {
		return processInstance;
	}

	/**
	 * @param processInstance the processInstance to set
	 */
	public void setProcessInstance(ProcessInstance processInstance) {
		this.processInstance = processInstance;
	}

	/**
	 * @return the userData
	 */
	public Map getUserData() {
		return userData;
	}

	/**
	 * @param userData the userData to set
	 */
	public void setUserData(Map userData) {
		this.userData = userData;
	}

	@Override
	public String toJSONString() {
		StringBuilder sb = new StringBuilder();
		int count = 0;
		if (getProcessDefinition() != null) {
			sb.append(__wrapObject(count, "processDefinition", getProcessDefinition()));
			count++;
		}
		if (getProcessInstance() != null) {
			sb.append(__wrapObject(count, "processInstance", getProcessInstance()));
			count++;
		}
		if (getUserData() != null) {
			if (count > 0) {
				sb.append(",");
			}
			sb.append("\"userData\":");
			sb.append(MapUtils.toJSON(userData));
			count++;
		}
		if (getCustomData() != null) {
			if (count > 0) {
				sb.append(",");
			}
			sb.append("\"userData\":");
			sb.append(getCustomData());
			count++;
		}
		return sb.toString();
	}

	@Override
	public void setDataFromMap(Map<String, Object> values) {
		Map<String, Object> val;
                Object oval;
		if ((val = (Map<String, Object>) values.get("processDefinition")) != null) {
			this.processDefinition = new SystemProcessDefinition();
			this.processDefinition.setDataFromMap(val);
		}
		if ((val = (Map<String, Object>) values.get("processInstance")) != null) {
			this.processInstance = new ProcessInstance();
			this.processInstance.setDataFromMap(val);
		}
		if ((val = (Map<String, Object>) values.get("userData")) != null) {
			this.userData = val;
		}
                if ((oval = values.get("approval")) != null) {
			this.additionalApprovalment = __getInt(oval);
		}
	}

	/**
	 * @return the customData
	 */
	public String getCustomData() {
		return customData;
	}

	/**
	 * @param customData the customData to set
	 */
	public void setCustomData(String customData) {
		this.customData = customData;
	}

    /**
     * @return the additionalApprovalment
     */
    public Integer getAdditionalApprovalment() {
        return additionalApprovalment;
    }

    /**
     * @param additionalApprovalment the additionalApprovalment to set
     */
    public void setAdditionalApprovalment(Integer additionalApprovalment) {
        this.additionalApprovalment = additionalApprovalment;
    }

}
