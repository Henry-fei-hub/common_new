package delicacy.system.bean;

import delicacy.common.GenericCondition;
import java.util.Map;

public class ConditionMsystemprocesssor extends GenericCondition{

	public ConditionMsystemprocesssor(){
		setParameterCount(0);
	}

	@Override
	public java.lang.String toJSONString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toJSONString());
		return sb.toString();
	}

	@Override
	public void setDataFromMap(Map<String, Object> values){
		super.setDataFromMap(values);
	}

}

