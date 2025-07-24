package delicacy.system.bean;

import delicacy.common.GenericCondition;
import java.util.Map;

public class ConditionSsystemprocesspor extends GenericCondition{

	public ConditionSsystemprocesspor(){
		setParameterCount(1);
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
		if(getProcessTypeId() != null) sb.append(__wrapNumber(1, "processTypeId", getProcessTypeId()));
		return sb.toString();
	}

	@Override
	public void setDataFromMap(Map<String, Object> values){
		super.setDataFromMap(values);
		Object val;
		if((val = values.get("processTypeId")) != null) setProcessTypeId(__getInt(val)); 
	}

	private java.lang.Integer __process_type_id = null;
}

