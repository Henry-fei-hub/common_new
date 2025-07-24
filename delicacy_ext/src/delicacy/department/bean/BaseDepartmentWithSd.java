package delicacy.department.bean;

import delicacy.system.bean.BaseDepartment;
import delicacy.system.bean.BaseDepartmentRole;
import delicacy.system.bean.BaseSubjectDepartment;
import delicacy.system.bean.BaseSystemProcessDepartment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaseDepartmentWithSd extends BaseDepartment
{

	public static BaseDepartmentWithSd newInstance(){
		return new BaseDepartmentWithSd();
	}

	@Override
	public BaseDepartmentWithSd make(){
		BaseDepartmentWithSd b = new BaseDepartmentWithSd();
		return b;
	}

	public List<BaseSystemProcessDepartment> getDetailSystemProcessDepartment() {
		return this.__detail_system_process_departments;
	}

	public void setDetailSystemProcessDepartment( List<BaseSystemProcessDepartment> value ) {
		this.__detail_system_process_departments = value;
	}

	public List<BaseDepartmentRole> getDetailDepartmentRole() {
		return this.__detail_department_roles;
	}

	public void setDetailDepartmentRole( List<BaseDepartmentRole> value ) {
		this.__detail_department_roles = value;
	}

	public List<BaseSubjectDepartment> getDetailSubjectDepartment() {
		return this.__detail_subject_departments;
	}

	public void setDetailSubjectDepartment( List<BaseSubjectDepartment> value ) {
		this.__detail_subject_departments = value;
	}

	public BaseDepartment toBase(){
		BaseDepartment base = new BaseDepartment();
		cloneCopy(base);
		return base;
	}

	public void getDataFromBase(BaseDepartment base){
		base.cloneCopy(this);
	}

	public static List<BaseDepartment> getBaseList(List<BaseDepartmentWithSd> beans) {
		List<BaseDepartment> result = new ArrayList<>();
		if(beans == null || beans.isEmpty()) return result;
		for(BaseDepartmentWithSd bean : beans) {
			result.add(bean.toBase());
		}
		return result;
	}

	public static List<BaseDepartmentWithSd> getBeanList(List<BaseDepartment> beans) {
		List<BaseDepartmentWithSd> result = new ArrayList<>();
		if(beans == null || beans.isEmpty()) return result;
		for(BaseDepartment bean : beans) {
			BaseDepartmentWithSd val = new BaseDepartmentWithSd();
			val.getDataFromBase(bean);
			result.add(val);
		}
		return result;
	}

	@Override
	public String toJSONString() {

		int count = 0;
		StringBuilder sb = new StringBuilder();
		sb.append(super.toJSONString());
		count = sb.length(); sb.append(__wrapList(count, "detailSystemProcessDepartment", getDetailSystemProcessDepartment()));
		count = sb.length(); sb.append(__wrapList(count, "detailDepartmentRole", getDetailDepartmentRole()));
		count = sb.length(); sb.append(__wrapList(count, "detailSubjectDepartment", getDetailSubjectDepartment()));
		return sb.toString() ;
	}

	@Override
	public void setDataFromMap(Map<String, Object> values){
		Object val;
		super.setDataFromMap(values);
		if((val = values.get("detailSystemProcessDepartment")) != null) setDetailSystemProcessDepartment(__getList(val, BaseSystemProcessDepartment.newInstance()));
		if((val = values.get("detailDepartmentRole")) != null) setDetailDepartmentRole(__getList(val, BaseDepartmentRole.newInstance()));
		if((val = values.get("detailSubjectDepartment")) != null) setDetailSubjectDepartment(__getList(val, BaseSubjectDepartment.newInstance()));
	}

	protected List<BaseSystemProcessDepartment> __detail_system_process_departments ;
	protected List<BaseDepartmentRole> __detail_department_roles ;
	protected List<BaseSubjectDepartment> __detail_subject_departments ;
}
