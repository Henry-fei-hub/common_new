package com.delicacy.client.process;

import com.delicacy.client.MapUtils;
import com.delicacy.client.data.DBDataSource;
import com.delicacy.client.data.GenericBase;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Peter
 */
public class ProcessInformation extends GenericBase {

	private SystemProcessDefinition processDefinition;
	private ProcessInstance processInstance;
	private Map userData;
	private ListGridRecord[] Data;

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
		return sb.toString();
	}

	@Override
	public void setDataFromJSON(JSONObject values) {
		JSONValue val;
		if ((val = values.get("processDefinition")) != null) {
			this.processDefinition = new SystemProcessDefinition();
			this.processDefinition.setDataFromJSON(val.isObject());
		}
		if ((val = values.get("processInstance")) != null) {
			this.processInstance = new ProcessInstance();
			this.processInstance.setDataFromJSON(val.isObject());
		}
		if ((val = values.get("userData")) != null) {
			if (val.isArray() != null) {
				try {
					setData(DBDataSource.getDataFromJSON(val.isArray()));
				} catch (Exception ex) {
					Logger.getLogger(ProcessInformation.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
			if (val.isObject() != null) {
				ListGridRecord[] records = new ListGridRecord[1];
				try {
					records[0] = DBDataSource.getDataFromJSONObject(val.isObject());
				} catch (Exception ex) {
					Logger.getLogger(ProcessInformation.class.getName()).log(Level.SEVERE, null, ex);
				}
				setData(records);
			}
		}
	}

	/**
	 * @return the Data
	 */
	public ListGridRecord[] getData() {
		return Data;
	}

	/**
	 * @param Data the Data to set
	 */
	public void setData(ListGridRecord[] Data) {
		this.Data = Data;
	}

}
