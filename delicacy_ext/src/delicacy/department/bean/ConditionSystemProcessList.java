package delicacy.department.bean;

import delicacy.common.GenericCondition;
import java.util.Map;

public class ConditionSystemProcessList extends GenericCondition{

	public ConditionSystemProcessList(){
		setParameterCount(2);
	}

	public java.lang.String getProcessName() {
		return this.__process_name == null ? null : (this.__process_name.indexOf("%") >= 0 ? this.__process_name : "%"+this.__process_name+"%");
	}

	public void setProcessName( java.lang.String value ) {
		this.__process_name = value;
	}

	public java.lang.Integer getProcessTypeId() {
		return this.__process_type_id;
	}

	public void setProcessTypeId( java.lang.Integer value ) {
		this.__process_type_id = value;
	}

	@Override
	public java.lang.String toJSONString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toJSONString());
		if(getProcessName() != null) sb.append(__wrapString(1, "processName", getProcessName()));
		if(getProcessTypeId() != null) sb.append(__wrapNumber(1, "processTypeId", getProcessTypeId()));
		return sb.toString();
	}

	@Override
	public void setDataFromMap(Map<String, Object> values){
		super.setDataFromMap(values);
		Object val;
		if((val = values.get("processName")) != null) setProcessName(__getString(val));
		if((val = values.get("processTypeId")) != null) setProcessTypeId(__getInt(val)); 
	}

	private java.lang.String __process_name = null;
	private java.lang.Integer __process_type_id = null;
}

