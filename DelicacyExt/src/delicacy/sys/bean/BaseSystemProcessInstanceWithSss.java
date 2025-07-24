package delicacy.sys.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaseSystemProcessInstanceWithSss extends BaseSystemProcessInstance 
{

	public static BaseSystemProcessInstanceWithSss newInstance(){
		return new BaseSystemProcessInstanceWithSss();
	}

	@Override
	public BaseSystemProcessInstanceWithSss make(){
		BaseSystemProcessInstanceWithSss b = new BaseSystemProcessInstanceWithSss();
		return b;
	}

	public List<BaseSystemProcessInstanceActivity> getDetailSystemProcessInstanceActivity() {
		return this.__detail_system_process_instance_activities;
	}

	public void setDetailSystemProcessInstanceActivity( List<BaseSystemProcessInstanceActivity> value ) {
		this.__detail_system_process_instance_activities = value;
	}

	public List<BaseSystemProcessAttention> getDetailSystemProcessAttention() {
		return this.__detail_system_process_attentions;
	}

	public void setDetailSystemProcessAttention( List<BaseSystemProcessAttention> value ) {
		this.__detail_system_process_attentions = value;
	}

	public List<BaseSystemProcessPooledTask> getDetailSystemProcessPooledTask() {
		return this.__detail_system_process_pooled_tasks;
	}

	public void setDetailSystemProcessPooledTask( List<BaseSystemProcessPooledTask> value ) {
		this.__detail_system_process_pooled_tasks = value;
	}

	public BaseSystemProcessInstance toBase(){
		BaseSystemProcessInstance base = new BaseSystemProcessInstance();
		cloneCopy(base);
		return base;
	}

	public void getDataFromBase(BaseSystemProcessInstance base){
		base.cloneCopy(this);
	}

	public static List<BaseSystemProcessInstance> getBaseList(List<BaseSystemProcessInstanceWithSss> beans) {
		List<BaseSystemProcessInstance> result = new ArrayList<>();
		if(beans == null || beans.isEmpty()) return result;
		for(BaseSystemProcessInstanceWithSss bean : beans) {
			result.add(bean.toBase());
		}
		return result;
	}

	public static List<BaseSystemProcessInstanceWithSss> getBeanList(List<BaseSystemProcessInstance> beans) {
		List<BaseSystemProcessInstanceWithSss> result = new ArrayList<>();
		if(beans == null || beans.isEmpty()) return result;
		for(BaseSystemProcessInstance bean : beans) {
			BaseSystemProcessInstanceWithSss val = new BaseSystemProcessInstanceWithSss();
			val.getDataFromBase(bean);
			result.add(val);
		}
		return result;
	}

	@Override
	public java.lang.String toJSONString() {

		int count = 0;
		StringBuilder sb = new StringBuilder();
		sb.append(super.toJSONString());
		count = sb.length(); sb.append(__wrapList(count, "detailSystemProcessInstanceActivity", getDetailSystemProcessInstanceActivity()));
		count = sb.length(); sb.append(__wrapList(count, "detailSystemProcessAttention", getDetailSystemProcessAttention()));
		count = sb.length(); sb.append(__wrapList(count, "detailSystemProcessPooledTask", getDetailSystemProcessPooledTask()));
		return sb.toString() ;
	}

	@Override
	public void setDataFromMap(Map<String, Object> values){
		Object val;
		super.setDataFromMap(values);
		if((val = values.get("detailSystemProcessInstanceActivity")) != null) setDetailSystemProcessInstanceActivity(__getList(val, BaseSystemProcessInstanceActivity.newInstance()));
		if((val = values.get("detailSystemProcessAttention")) != null) setDetailSystemProcessAttention(__getList(val, BaseSystemProcessAttention.newInstance()));
		if((val = values.get("detailSystemProcessPooledTask")) != null) setDetailSystemProcessPooledTask(__getList(val, BaseSystemProcessPooledTask.newInstance()));
	}

	protected List<BaseSystemProcessInstanceActivity> __detail_system_process_instance_activities ; 
	protected List<BaseSystemProcessAttention> __detail_system_process_attentions ; 
	protected List<BaseSystemProcessPooledTask> __detail_system_process_pooled_tasks ; 
}
