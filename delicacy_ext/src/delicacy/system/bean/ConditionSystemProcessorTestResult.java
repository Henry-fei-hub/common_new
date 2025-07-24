package delicacy.system.bean;

import delicacy.common.GenericCondition;
import java.util.Map;

public class ConditionSystemProcessorTestResult extends GenericCondition{

	public ConditionSystemProcessorTestResult(){
		setParameterCount(3);
	}

	public java.lang.Integer getThreadTaskManageId() {
		return this.__thread_task_manage_id;
	}

	public void setThreadTaskManageId( java.lang.Integer value ) {
		this.__thread_task_manage_id = value;
	}

	public java.lang.String getProcessName() {
		return this.__process_name == null ? null : (this.__process_name.indexOf("%") >= 0 ? this.__process_name : "%"+this.__process_name+"%");
	}

	public void setProcessName( java.lang.String value ) {
		this.__process_name = value;
	}

	public java.lang.Integer getTestResult() {
		return this.__test_result;
	}

	public void setTestResult( java.lang.Integer value ) {
		this.__test_result = value;
	}

	@Override
	public java.lang.String toJSONString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toJSONString());
		if(getThreadTaskManageId() != null) sb.append(__wrapNumber(1, "threadTaskManageId", getThreadTaskManageId()));
		if(getProcessName() != null) sb.append(__wrapString(1, "processName", getProcessName()));
		if(getTestResult() != null) sb.append(__wrapNumber(1, "testResult", getTestResult()));
		return sb.toString();
	}

	@Override
	public void setDataFromMap(Map<String, Object> values){
		super.setDataFromMap(values);
		Object val;
		if((val = values.get("threadTaskManageId")) != null) setThreadTaskManageId(__getInt(val)); 
		if((val = values.get("processName")) != null) setProcessName(__getString(val));
		if((val = values.get("testResult")) != null) setTestResult(__getInt(val)); 
	}

	private java.lang.Integer __thread_task_manage_id = null;
	private java.lang.String __process_name = null;
	private java.lang.Integer __test_result = null;
}

