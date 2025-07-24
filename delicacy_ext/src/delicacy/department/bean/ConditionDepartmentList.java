package delicacy.department.bean;

import delicacy.common.GenericCondition;
import java.util.Map;

public class ConditionDepartmentList extends GenericCondition{

	public ConditionDepartmentList(){
		setParameterCount(3);
	}

	public java.lang.String getManagerName() {
		return this.__manager_name == null ? null : (this.__manager_name.indexOf("%") >= 0 ? this.__manager_name : "%"+this.__manager_name+"%");
	}

	public void setManagerName( java.lang.String value ) {
		this.__manager_name = value;
	}

	public java.lang.Integer getManagerId() {
		return this.__manager_id;
	}

	public void setManagerId( java.lang.Integer value ) {
		this.__manager_id = value;
	}

	public java.lang.String getDepartmentName() {
		return this.__department_name == null ? null : (this.__department_name.indexOf("%") >= 0 ? this.__department_name : "%"+this.__department_name+"%");
	}

	public void setDepartmentName( java.lang.String value ) {
		this.__department_name = value;
	}

	@Override
	public java.lang.String toJSONString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toJSONString());
		if(getManagerName() != null) sb.append(__wrapString(1, "managerName", getManagerName()));
		if(getManagerId() != null) sb.append(__wrapNumber(1, "managerId", getManagerId()));
		if(getDepartmentName() != null) sb.append(__wrapString(1, "departmentName", getDepartmentName()));
		return sb.toString();
	}

	@Override
	public void setDataFromMap(Map<String, Object> values){
		super.setDataFromMap(values);
		Object val;
		if((val = values.get("managerName")) != null) setManagerName(__getString(val));
		if((val = values.get("managerId")) != null) setManagerId(__getInt(val)); 
		if((val = values.get("departmentName")) != null) setDepartmentName(__getString(val));
	}

	private java.lang.String __manager_name = null;
	private java.lang.Integer __manager_id = null;
	private java.lang.String __department_name = null;
}

