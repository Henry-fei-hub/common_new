package delicacy.sys.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaseSystemProcessWithSss extends BaseSystemProcess 
{

	public static BaseSystemProcessWithSss newInstance(){
		return new BaseSystemProcessWithSss();
	}

	@Override
	public BaseSystemProcessWithSss make(){
		BaseSystemProcessWithSss b = new BaseSystemProcessWithSss();
		return b;
	}

	public List<BaseSystemProcessDepartment> getDetailSystemProcessDepartment() {
		return this.__detail_system_process_departments;
	}

	public void setDetailSystemProcessDepartment( List<BaseSystemProcessDepartment> value ) {
		this.__detail_system_process_departments = value;
	}

	public List<BaseSystemProcessActivity> getDetailSystemProcessActivity() {
		return this.__detail_system_process_activities;
	}

	public void setDetailSystemProcessActivity( List<BaseSystemProcessActivity> value ) {
		this.__detail_system_process_activities = value;
	}

	public List<BaseSystemProcessLink> getDetailSystemProcessLink() {
		return this.__detail_system_process_links;
	}

	public void setDetailSystemProcessLink( List<BaseSystemProcessLink> value ) {
		this.__detail_system_process_links = value;
	}

	public BaseSystemProcess toBase(){
		BaseSystemProcess base = new BaseSystemProcess();
		cloneCopy(base);
		return base;
	}

	public void getDataFromBase(BaseSystemProcess base){
		base.cloneCopy(this);
	}

	public static List<BaseSystemProcess> getBaseList(List<BaseSystemProcessWithSss> beans) {
		List<BaseSystemProcess> result = new ArrayList<>();
		if(beans == null || beans.isEmpty()) return result;
		for(BaseSystemProcessWithSss bean : beans) {
			result.add(bean.toBase());
		}
		return result;
	}

	public static List<BaseSystemProcessWithSss> getBeanList(List<BaseSystemProcess> beans) {
		List<BaseSystemProcessWithSss> result = new ArrayList<>();
		if(beans == null || beans.isEmpty()) return result;
		for(BaseSystemProcess bean : beans) {
			BaseSystemProcessWithSss val = new BaseSystemProcessWithSss();
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
		count = sb.length(); sb.append(__wrapList(count, "detailSystemProcessDepartment", getDetailSystemProcessDepartment()));
		count = sb.length(); sb.append(__wrapList(count, "detailSystemProcessActivity", getDetailSystemProcessActivity()));
		count = sb.length(); sb.append(__wrapList(count, "detailSystemProcessLink", getDetailSystemProcessLink()));
		return sb.toString() ;
	}

	@Override
	public void setDataFromMap(Map<String, Object> values){
		Object val;
		super.setDataFromMap(values);
		if((val = values.get("detailSystemProcessDepartment")) != null) setDetailSystemProcessDepartment(__getList(val, BaseSystemProcessDepartment.newInstance()));
		if((val = values.get("detailSystemProcessActivity")) != null) setDetailSystemProcessActivity(__getList(val, BaseSystemProcessActivity.newInstance()));
		if((val = values.get("detailSystemProcessLink")) != null) setDetailSystemProcessLink(__getList(val, BaseSystemProcessLink.newInstance()));
	}

	protected List<BaseSystemProcessDepartment> __detail_system_process_departments ; 
	protected List<BaseSystemProcessActivity> __detail_system_process_activities ; 
	protected List<BaseSystemProcessLink> __detail_system_process_links ; 
}
