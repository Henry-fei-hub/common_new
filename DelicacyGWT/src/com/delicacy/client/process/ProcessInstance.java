package com.delicacy.client.process;

import com.delicacy.client.data.BSystemProcessInstance;
import com.delicacy.client.data.BSystemProcessInstanceActivity;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import java.util.List;

/**
 *
 * @author Peter
 */
public class ProcessInstance extends BSystemProcessInstance {

	@Override
	public String toJSONString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toJSONString());
		if (activities != null) {
			sb.append(__wrapList(1, "activities", activities));
		}
		return sb.toString();
	}

	@Override
	public void setDataFromJSON(JSONObject values) {
		JSONValue val;
		super.setDataFromJSON(values);
		if ((val = values.get("activities")) != null) {
			setActivities(__getList(val.isArray(), BSystemProcessInstanceActivity.newInstance()));
		}
	}

	private List<BSystemProcessInstanceActivity> activities = null;

	/**
	 * @return the activities
	 */
	public List<BSystemProcessInstanceActivity> getActivities() {
		return activities;
	}

	/**
	 * @param activities the activities to set
	 */
	public void setActivities(List<BSystemProcessInstanceActivity> activities) {
		this.activities = activities;
	}

}
