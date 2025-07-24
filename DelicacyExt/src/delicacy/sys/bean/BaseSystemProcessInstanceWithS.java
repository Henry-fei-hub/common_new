package delicacy.sys.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class BaseSystemProcessInstanceWithS extends BaseSystemProcessInstance 
{

	public static BaseSystemProcessInstanceWithS newInstance(){
		return new BaseSystemProcessInstanceWithS();
	}

	@Override
	public BaseSystemProcessInstanceWithS make(){
		BaseSystemProcessInstanceWithS b = new BaseSystemProcessInstanceWithS();
		return b;
	}

	public List<BaseSystemProcessInstanceActivity> getDetailSystemProcessInstanceActivity() {
		return this.__detail_system_process_instance_activities;
	}

	public void setDetailSystemProcessInstanceActivity( List<BaseSystemProcessInstanceActivity> value ) {
		this.__detail_system_process_instance_activities = value;
	}

	public BaseSystemProcessInstance toBase(){
		BaseSystemProcessInstance base = new BaseSystemProcessInstance();
		cloneCopy(base);
		return base;
	}

	public void getDataFromBase(BaseSystemProcessInstance base){
		base.cloneCopy(this);
	}

	public static List<BaseSystemProcessInstance> getBaseList(List<BaseSystemProcessInstanceWithS> beans) {
		List<BaseSystemProcessInstance> result = new ArrayList<>();
		if(beans == null || beans.isEmpty()) return result;
		for(BaseSystemProcessInstanceWithS bean : beans) {
			result.add(bean.toBase());
		}
		return result;
	}

	public static List<BaseSystemProcessInstanceWithS> getBeanList(List<BaseSystemProcessInstance> beans) {
		List<BaseSystemProcessInstanceWithS> result = new ArrayList<>();
		if(beans == null || beans.isEmpty()) return result;
		for(BaseSystemProcessInstance bean : beans) {
			BaseSystemProcessInstanceWithS val = new BaseSystemProcessInstanceWithS();
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
		return sb.toString() ;
	}

	@Override
	public void setDataFromMap(Map<String, Object> values){
		Object val;
		super.setDataFromMap(values);
		if((val = values.get("detailSystemProcessInstanceActivity")) != null) setDetailSystemProcessInstanceActivity(__getList(val, BaseSystemProcessInstanceActivity.newInstance()));
	}

	protected List<BaseSystemProcessInstanceActivity> __detail_system_process_instance_activities ; 
}
