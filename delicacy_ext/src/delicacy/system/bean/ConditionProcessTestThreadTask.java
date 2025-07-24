package delicacy.system.bean;

import delicacy.common.GenericCondition;
import java.util.Map;

public class ConditionProcessTestThreadTask extends GenericCondition{

	public ConditionProcessTestThreadTask(){
		setParameterCount(3);
	}

	public java.lang.Integer getThreadTaskManageId() {
		return this.__thread_task_manage_id;
	}

	public void setThreadTaskManageId( java.lang.Integer value ) {
		this.__thread_task_manage_id = value;
	}

	public java.lang.Integer getTaskType() {
		return this.__task_type;
	}

	public void setTaskType( java.lang.Integer value ) {
		this.__task_type = value;
	}

	public java.lang.Integer getStatus() {
		return this.__status;
	}

	public void setStatus( java.lang.Integer value ) {
		this.__status = value;
	}

	@Override
	public java.lang.String toJSONString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toJSONString());
		if(getThreadTaskManageId() != null) sb.append(__wrapNumber(1, "threadTaskManageId", getThreadTaskManageId()));
		if(getTaskType() != null) sb.append(__wrapNumber(1, "taskType", getTaskType()));
		if(getStatus() != null) sb.append(__wrapNumber(1, "status", getStatus()));
		return sb.toString();
	}

	@Override
	public void setDataFromMap(Map<String, Object> values){
		super.setDataFromMap(values);
		Object val;
		if((val = values.get("threadTaskManageId")) != null) setThreadTaskManageId(__getInt(val)); 
		if((val = values.get("taskType")) != null) setTaskType(__getInt(val)); 
		if((val = values.get("status")) != null) setStatus(__getInt(val)); 
	}

	private java.lang.Integer __thread_task_manage_id = null;
	private java.lang.Integer __task_type = null;
	private java.lang.Integer __status = null;
}

