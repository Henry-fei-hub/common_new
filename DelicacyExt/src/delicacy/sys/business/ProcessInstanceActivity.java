package delicacy.sys.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import delicacy.sys.bean.BaseSystemProcessAttention;
import delicacy.sys.bean.BaseSystemProcessInstanceActivity;
import delicacy.sys.bean.BaseSystemProcessPooledTask;

public class ProcessInstanceActivity extends BaseSystemProcessInstanceActivity {
	
	public static ProcessInstanceActivity newInstance(){
		return new ProcessInstanceActivity();
	}
	
	@Override
	public ProcessInstanceActivity make(){
		return new ProcessInstanceActivity();
	}
	
	public static List<BaseSystemProcessInstanceActivity> toBaseList(List<ProcessInstanceActivity> org){
		List<BaseSystemProcessInstanceActivity> res = new ArrayList<>();
		for(ProcessInstanceActivity i : org){
			BaseSystemProcessInstanceActivity na = new BaseSystemProcessInstanceActivity();
			i.cloneCopy(na);
			res.add(na);
		}
		return res;
	}
	
	@Override
	public java.lang.String toJSONString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toJSONString());
		sb.append(__wrapNumber(1, "poolType", getPoolType()));
		if(getAttentions() != null)
			sb.append(__wrapList(1, "attentions", getAttentions()));
		if(getTasks() != null)
			sb.append(__wrapList(1, "tasks", getTasks()));
		return sb.toString();
	}
	
	@Override
	public void setDataFromMap(Map<String, Object> values){
		Object val;
		super.setDataFromMap(values);
		if((val = values.get("poolType")) != null) setPoolType(__getInt(val)); 
		if ((val = values.get("attentions")) != null) {
			setAttentions(__getList(val, BaseSystemProcessAttention.newInstance()));
		}
		if ((val = values.get("tasks")) != null) {
			setTasks(__getList(val, BaseSystemProcessPooledTask.newInstance()));
		}
	}
	
	public int getPoolType() {
		return poolType;
	}
	public void setPoolType(int poolType) {
		this.poolType = poolType;
	}
	public List<BaseSystemProcessAttention> getAttentions() {
		return attentions;
	}
	public void setAttentions(List<BaseSystemProcessAttention> attentions) {
		this.attentions = attentions;
	}
	public List<BaseSystemProcessPooledTask> getTasks() {
		return tasks;
	}
	public void setTasks(List<BaseSystemProcessPooledTask> tasks) {
		this.tasks = tasks;
	}
	
	protected int poolType = 0;
	protected List<BaseSystemProcessAttention> attentions = new ArrayList<>();
	protected List<BaseSystemProcessPooledTask> tasks = new ArrayList<>();
}
