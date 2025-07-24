package delicacy.system.bean;

import delicacy.common.GenericCondition;
import java.util.Map;

public class ConditionDomainValue extends GenericCondition {

	public ConditionDomainValue() {
		setParameterCount(4);
	}

	public java.lang.String getSelectId() {
		return this.__select_id;
	}

	public void setSelectId(java.lang.String value) {
		this.__select_id = value;
	}

	public java.lang.Boolean getLoadOnStartup() {
		return this.__load_on_startup;
	}

	public void setLoadOnStartup(java.lang.Boolean value) {
		this.__load_on_startup = value;
	}

	public java.lang.Boolean getIsBasicData() {
		return __is_basic_data;
	}

	public void setIsBasicData(java.lang.Boolean value) {
		this.__is_basic_data = value;
	}

	public java.lang.Integer getApplicationId() {
		return __application_id;
	}

	public void setApplicationId(java.lang.Integer value) {
		this.__application_id = value;
	}

	@Override
	public java.lang.String toJSONString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toJSONString());
		if (getSelectId() != null) {
			sb.append(__wrapString(1, "selectId", getSelectId()));
		}
		if (getLoadOnStartup() != null) {
			sb.append(__wrapBoolean(1, "loadOnStartup", getLoadOnStartup()));
		}
		if (getIsBasicData() != null) {
			sb.append(__wrapBoolean(1, "isBasicData", getIsBasicData()));
		}
		if (getApplicationId() != null) {
			sb.append(__wrapNumber(1, "applicationId", getApplicationId()));
		}
		return sb.toString();
	}

	@Override
	public void setDataFromMap(Map<String, Object> values) {
		super.setDataFromMap(values);
		Object val;
		if ((val = values.get("selectId")) != null) {
			setSelectId(__getString(val));
		}
		if ((val = values.get("loadOnStartup")) != null) {
			setLoadOnStartup(__getBoolean(val));
		}
		if ((val = values.get("isBasicData")) != null) {
			setIsBasicData(__getBoolean(val));
		}
		if ((val = values.get("applicationId")) != null) {
			setApplicationId(__getInt(val));
		}
	}

	private java.lang.String __select_id = null;
	private java.lang.Boolean __load_on_startup = null;
	private java.lang.Boolean __is_basic_data = null;
	private java.lang.Integer __application_id = null;
}
