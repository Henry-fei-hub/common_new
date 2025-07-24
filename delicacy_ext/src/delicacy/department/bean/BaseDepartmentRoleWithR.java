package delicacy.department.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import delicacy.system.bean.BaseDepartmentRole;
import delicacy.system.bean.BaseRoleFunction;


public class BaseDepartmentRoleWithR extends BaseDepartmentRole 
{

	public static BaseDepartmentRoleWithR newInstance(){
		return new BaseDepartmentRoleWithR();
	}

	@Override
	public BaseDepartmentRoleWithR make(){
		BaseDepartmentRoleWithR b = new BaseDepartmentRoleWithR();
		return b;
	}

	public List<BaseRoleFunction> getDetailRoleFunction() {
		return this.__detail_role_functions;
	}

	public void setDetailRoleFunction( List<BaseRoleFunction> value ) {
		this.__detail_role_functions = value;
	}

	public BaseDepartmentRole toBase(){
		BaseDepartmentRole base = new BaseDepartmentRole();
		cloneCopy(base);
		return base;
	}

	public void getDataFromBase(BaseDepartmentRole base){
		base.cloneCopy(this);
	}

	public static List<BaseDepartmentRole> getBaseList(List<BaseDepartmentRoleWithR> beans) {
		List<BaseDepartmentRole> result = new ArrayList<>();
		if(beans == null || beans.isEmpty()) return result;
		for(BaseDepartmentRoleWithR bean : beans) {
			result.add(bean.toBase());
		}
		return result;
	}

	public static List<BaseDepartmentRoleWithR> getBeanList(List<BaseDepartmentRole> beans) {
		List<BaseDepartmentRoleWithR> result = new ArrayList<>();
		if(beans == null || beans.isEmpty()) return result;
		for(BaseDepartmentRole bean : beans) {
			BaseDepartmentRoleWithR val = new BaseDepartmentRoleWithR();
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
		count = sb.length(); sb.append(__wrapList(count, "detailRoleFunction", getDetailRoleFunction()));
		return sb.toString() ;
	}

	@Override
	public void setDataFromMap(Map<String, Object> values){
		Object val;
		super.setDataFromMap(values);
		if((val = values.get("detailRoleFunction")) != null) setDetailRoleFunction(__getList(val, BaseRoleFunction.newInstance()));
	}

	protected List<BaseRoleFunction> __detail_role_functions ; 
}
