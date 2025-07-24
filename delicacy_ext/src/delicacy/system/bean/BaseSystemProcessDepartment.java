package delicacy.system.bean;

import java.util.Map;
import java.util.HashMap;
import java.util.Objects;
import delicacy.common.GenericBase;
import delicacy.common.BaseFactory;

public class BaseSystemProcessDepartment extends GenericBase implements BaseFactory<BaseSystemProcessDepartment>, Comparable<BaseSystemProcessDepartment> 
{


	public static BaseSystemProcessDepartment newInstance(){
		return new BaseSystemProcessDepartment();
	}

	@Override
	public BaseSystemProcessDepartment make(){
		BaseSystemProcessDepartment b = new BaseSystemProcessDepartment();
		return b;
	}

	public final static java.lang.String CS_PROCESS_DEPARTMENT_ID = "process_department_id" ;
	public final static java.lang.String CS_PROCESS_ID = "process_id" ;
	public final static java.lang.String CS_DEPARTMENT_ID = "department_id" ;
	public final static java.lang.String CS_IS_ENABLE = "is_enable" ;

	public final static java.lang.String ALL_CAPTIONS = "编码,流程编码,部门编码,是否可用";

	public java.lang.Integer getProcessDepartmentId() {
		return this.__process_department_id;
	}

	public void setProcessDepartmentId( java.lang.Integer value ) {
		this.__process_department_id = value;
	}

	public java.lang.Integer getProcessId() {
		return this.__process_id;
	}

	public void setProcessId( java.lang.Integer value ) {
		this.__process_id = value;
	}

	public java.lang.Integer getDepartmentId() {
		return this.__department_id;
	}

	public void setDepartmentId( java.lang.Integer value ) {
		this.__department_id = value;
	}

	public java.lang.Boolean getIsEnable() {
		return this.__is_enable;
	}

	public void setIsEnable( java.lang.Boolean value ) {
		this.__is_enable = value;
	}

	public void cloneCopy(BaseSystemProcessDepartment __bean){
		__bean.setProcessDepartmentId(getProcessDepartmentId());
		__bean.setProcessId(getProcessId());
		__bean.setDepartmentId(getDepartmentId());
		__bean.setIsEnable(getIsEnable());
	}

	public java.lang.String toCSVString() {

		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
		StringBuilder sb = new StringBuilder();
		sb.append(getProcessDepartmentId() == null ? "" : getProcessDepartmentId());
		sb.append(",");
		sb.append(getProcessId() == null ? "" : getProcessId());
		sb.append(",");
		String strDepartmentId = delicacy.system.executor.SelectValueCache.getSelectValue("departments", String.valueOf(getDepartmentId()));
		sb.append(strDepartmentId == null ? "" : strDepartmentId);
		sb.append(",");
		sb.append(getIsEnable() == null ? "" : getIsEnable());
		return sb.toString();
	}

	@Override
	public int compareTo(BaseSystemProcessDepartment o) {
		return __process_department_id == null ? -1 : __process_department_id.compareTo(o.getProcessDepartmentId());
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 97 * hash + Objects.hashCode(this.__process_department_id);
		hash = 97 * hash + Objects.hashCode(this.__process_id);
		hash = 97 * hash + Objects.hashCode(this.__department_id);
		hash = 97 * hash + Objects.hashCode(this.__is_enable);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(getClass() != obj.getClass()) return false;
		final BaseSystemProcessDepartment o = (BaseSystemProcessDepartment)obj;
		if(!Objects.equals(this.__process_department_id, o.getProcessDepartmentId())) return false;
		if(!Objects.equals(this.__process_id, o.getProcessId())) return false;
		if(!Objects.equals(this.__department_id, o.getDepartmentId())) return false;
		if(!Objects.equals(this.__is_enable, o.getIsEnable())) return false;
		return true;
	}

	@Override
	public java.lang.String toJSONString() {

		StringBuilder sb = new StringBuilder();
		int count = 0;
		if(getProcessDepartmentId() != null) sb.append(__wrapNumber(count++, "processDepartmentId", getProcessDepartmentId()));
		if(getProcessId() != null) sb.append(__wrapNumber(count++, "processId", getProcessId()));
		if(getDepartmentId() != null) sb.append(__wrapNumber(count++, "departmentId", getDepartmentId()));
		if(getIsEnable() != null) sb.append(__wrapBoolean(count++, "isEnable", getIsEnable()));
		return sb.toString();
	}

	public Map<String, Object> toMap() {
		Map<String, Object> res = new HashMap<>();
		if(getProcessDepartmentId() != null) res.put("processDepartmentId", getProcessDepartmentId());
		if(getProcessId() != null) res.put("processId", getProcessId());
		if(getDepartmentId() != null) res.put("departmentId", getDepartmentId());
		if(getIsEnable() != null) res.put("isEnable", getIsEnable());
		return res;
	}

	@Override
	public void setDataFromMap(Map<String, Object> values){
		Object val;
		if((val = values.get("processDepartmentId")) != null) setProcessDepartmentId(__getInt(val)); 
		if((val = values.get("processId")) != null) setProcessId(__getInt(val)); 
		if((val = values.get("departmentId")) != null) setDepartmentId(__getInt(val)); 
		if((val = values.get("isEnable")) != null) setIsEnable(__getBoolean(val));
	}

	protected java.lang.Integer  __process_department_id ;
	protected java.lang.Integer  __process_id ;
	protected java.lang.Integer  __department_id ;
	protected java.lang.Boolean  __is_enable ;
}
