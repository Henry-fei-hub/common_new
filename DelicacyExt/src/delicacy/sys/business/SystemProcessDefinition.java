package delicacy.sys.business;

import java.util.List;
import java.util.Map;

import delicacy.sys.bean.BaseSystemProcess;
import delicacy.sys.bean.BaseSystemProcessActivity;
import delicacy.sys.bean.BaseSystemProcessLink;
import delicacy.sys.bean.BaseSystemProcessType;

/**
 *
 * @author Peter
 */
public class SystemProcessDefinition extends BaseSystemProcess {

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
	public void setDataFromMap(Map<String, Object> values) {
		super.setDataFromMap(values);
		Object val = null;
		if ((val = values.get("activities")) != null) {
			setActivities(__getList(val, BaseSystemProcessActivity.newInstance()));
		}
		if ((val = values.get("links")) != null) {
			setLinks(__getList(val, BaseSystemProcessLink.newInstance()));
		}
		if ((val = values.get("processType")) != null) {
			processType = new BaseSystemProcessType();
			processType.setDataFromMap((Map<String, Object>)val);
		}
	}

	private List<BaseSystemProcessActivity> activities = null;
	private List<BaseSystemProcessLink> links = null;
	private BaseSystemProcessType processType = null;

	/**
	 * @return the activities
	 */
	public List<BaseSystemProcessActivity> getActivities() {
		return activities;
	}

	/**
	 * @param activities the activities to set
	 */
	public void setActivities(List<BaseSystemProcessActivity> activities) {
		this.activities = activities;
	}

	/**
	 * @return the links
	 */
	public List<BaseSystemProcessLink> getLinks() {
		return links;
	}

	/**
	 * @param links the links to set
	 */
	public void setLinks(List<BaseSystemProcessLink> links) {
		this.links = links;
	}

	/**
	 * @return the processType
	 */
	public BaseSystemProcessType getProcessType() {
		return processType;
	}

	/**
	 * @param processType the processType to set
	 */
	public void setProcessType(BaseSystemProcessType processType) {
		this.processType = processType;
	}

}
