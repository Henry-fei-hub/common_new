package delicacy.system.bean;

import delicacy.common.GenericCondition;
import java.util.Map;

public class ConditionSemployeeRoleInfo extends GenericCondition{

	public ConditionSemployeeRoleInfo(){
		setParameterCount(2);
	}

	public java.lang.Integer getApplicationId() {
		return this.__application_id;
	}

	public void setApplicationId( java.lang.Integer value ) {
		this.__application_id = value;
	}

	public java.lang.String getRoleName() {
		return this.__role_name == null ? null : (this.__role_name.indexOf("%") >= 0 ? this.__role_name : "%"+this.__role_name+"%");
	}

	public void setRoleName( java.lang.String value ) {
		this.__role_name = value;
	}

	@Override
	public java.lang.String toJSONString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toJSONString());
		if(getApplicationId() != null) sb.append(__wrapNumber(1, "applicationId", getApplicationId()));
		if(getRoleName() != null) sb.append(__wrapString(1, "roleName", getRoleName()));
		return sb.toString();
	}

	@Override
	public void setDataFromMap(Map<String, Object> values){
		super.setDataFromMap(values);
		Object val;
		if((val = values.get("applicationId")) != null) setApplicationId(__getInt(val)); 
		if((val = values.get("roleName")) != null) setRoleName(__getString(val));
	}

	private java.lang.Integer __application_id = null;
	private java.lang.String __role_name = null;
}

