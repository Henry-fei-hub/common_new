package com.delicacy.client.process;

import com.delicacy.client.data.BSystemProcess;
import com.delicacy.client.data.BSystemProcessActivity;
import com.delicacy.client.data.BSystemProcessLink;
import com.delicacy.client.data.BSystemProcessType;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import java.util.List;

/**
 *
 * @author Peter
 */
public class SystemProcessDefinition extends BSystemProcess {

	@Override
	public String toJSONString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toJSONString());
		if (activities != null) {
			sb.append(__wrapList(1, "activities", activities));
		}
		if (links != null) {
			sb.append(__wrapList(1, "links", links));
		}
		if (processType != null) {
			sb.append(__wrapObject(1, "processType", processType));
		}
		return sb.toString();
	}

	@Override
	public void setDataFromJSON(JSONObject values) {
		JSONValue val;
		super.setDataFromJSON(values);
		if ((val = values.get("activities")) != null) {
			setActivities(__getList(val.isArray(), BSystemProcessActivity.newInstance()));
		}
		if ((val = values.get("links")) != null) {
			setLinks(__getList(val.isArray(), BSystemProcessLink.newInstance()));
		}
		if ((val = values.get("processType")) != null) {
			processType = new BSystemProcessType();
			processType.setDataFromJSON(val.isObject());
		}
	}

	private List<BSystemProcessActivity> activities = null;
	private List<BSystemProcessLink> links = null;
	private BSystemProcessType processType = null;

	/**
	 * @return the activities
	 */
	public List<BSystemProcessActivity> getActivities() {
		return activities;
	}

	/**
	 * @param activities the activities to set
	 */
	public void setActivities(List<BSystemProcessActivity> activities) {
		this.activities = activities;
	}

	/**
	 * @return the links
	 */
	public List<BSystemProcessLink> getLinks() {
		return links;
	}

	/**
	 * @param links the links to set
	 */
	public void setLinks(List<BSystemProcessLink> links) {
		this.links = links;
	}

	/**
	 * @return the processType
	 */
	public BSystemProcessType getProcessType() {
		return processType;
	}

	/**
	 * @param processType the processType to set
	 */
	public void setProcessType(BSystemProcessType processType) {
		this.processType = processType;
	}

}
