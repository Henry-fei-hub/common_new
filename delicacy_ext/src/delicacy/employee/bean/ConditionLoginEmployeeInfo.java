package delicacy.employee.bean;

import delicacy.common.GenericCondition;
import java.util.Map;

public class ConditionLoginEmployeeInfo extends GenericCondition {

	public ConditionLoginEmployeeInfo() {
		setParameterCount(3);
	}

	public java.lang.String getUserAccount() {
		return this.__user_account;
	}

	public void setUserAccount(java.lang.String value) {
		this.__user_account = value;
	}

	public java.lang.String getCompanyWeixin() {
		return this.__company_weixin;
	}

	public void setCompanyWeixin(java.lang.String value) {
		this.__company_weixin = value;
	}

	public java.lang.Integer getStatus() {
		return this.__status;
	}

	public void setStatus(java.lang.Integer value) {
		this.__status = value;
	}

	@Override
	public java.lang.String toJSONString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toJSONString());
		if (getUserAccount() != null)
			sb.append(__wrapString(1, "userAccount", getUserAccount()));
		if (getCompanyWeixin() != null)
			sb.append(__wrapString(1, "companyWeixin", getCompanyWeixin()));
		if (getStatus() != null)
			sb.append(__wrapNumber(1, "status", getStatus()));
		return sb.toString();
	}

	@Override
	public void setDataFromMap(Map<String, Object> values) {
		super.setDataFromMap(values);
		Object val;
		if ((val = values.get("userAccount")) != null)
			setUserAccount(__getString(val));
		if ((val = values.get("companyWeixin")) != null)
			setCompanyWeixin(__getString(val));
		if ((val = values.get("status")) != null)
			setStatus(__getInt(val));
	}

	private java.lang.String __user_account = null;
	private java.lang.String __company_weixin = null;
	private java.lang.Integer __status = null;
}
