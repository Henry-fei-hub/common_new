package delicacy.system.bean;

import delicacy.common.BaseFactory;
import delicacy.common.GenericBase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BaseSubjectDepartment extends GenericBase implements BaseFactory<BaseSubjectDepartment>, Comparable<BaseSubjectDepartment>
{


	public static BaseSubjectDepartment newInstance(){
		return new BaseSubjectDepartment();
	}

	@Override
	public BaseSubjectDepartment make(){
		BaseSubjectDepartment b = new BaseSubjectDepartment();
		return b;
	}

	public final static String CS_SUBJECT_DEPARTMENT_ID = "subject_department_id" ;
	public final static String CS_SUBJECT_TYPE_ID = "subject_type_id" ;
	public final static String CS_DEPARTMENT_ID = "department_id" ;

	public final static String ALL_CAPTIONS = "主键编码,科目编码,部门编码";

	public Integer getSubjectDepartmentId() {
		return this.__subject_department_id;
	}

	public void setSubjectDepartmentId( Integer value ) {
		this.__subject_department_id = value;
	}

	public Integer getSubjectTypeId() {
		return this.__subject_type_id;
	}

	public void setSubjectTypeId( Integer value ) {
		this.__subject_type_id = value;
	}

	public Integer getDepartmentId() {
		return this.__department_id;
	}

	public void setDepartmentId( Integer value ) {
		this.__department_id = value;
	}

	public void cloneCopy(BaseSubjectDepartment __bean){
		__bean.setSubjectDepartmentId(getSubjectDepartmentId());
		__bean.setSubjectTypeId(getSubjectTypeId());
		__bean.setDepartmentId(getDepartmentId());
	}

	public String toCSVString() {

		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
		StringBuilder sb = new StringBuilder();
		sb.append(getSubjectDepartmentId() == null ? "" : getSubjectDepartmentId());
		sb.append(",");
		sb.append(getSubjectTypeId() == null ? "" : getSubjectTypeId());
		sb.append(",");
		sb.append(getDepartmentId() == null ? "" : getDepartmentId());
		return sb.toString();
	}

	@Override
	public int compareTo(BaseSubjectDepartment o) {
		return __subject_department_id == null ? -1 : __subject_department_id.compareTo(o.getSubjectDepartmentId());
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 97 * hash + Objects.hashCode(this.__subject_department_id);
		hash = 97 * hash + Objects.hashCode(this.__subject_type_id);
		hash = 97 * hash + Objects.hashCode(this.__department_id);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(getClass() != obj.getClass()) return false;
		final BaseSubjectDepartment o = (BaseSubjectDepartment)obj;
		if(!Objects.equals(this.__subject_department_id, o.getSubjectDepartmentId())) return false;
		if(!Objects.equals(this.__subject_type_id, o.getSubjectTypeId())) return false;
		if(!Objects.equals(this.__department_id, o.getDepartmentId())) return false;
		return true;
	}

	@Override
	public String toJSONString() {

		StringBuilder sb = new StringBuilder();
		int count = 0;
		if(getSubjectDepartmentId() != null) sb.append(__wrapNumber(count++, "subjectDepartmentId", getSubjectDepartmentId()));
		if(getSubjectTypeId() != null) sb.append(__wrapNumber(count++, "subjectTypeId", getSubjectTypeId()));
		if(getDepartmentId() != null) sb.append(__wrapNumber(count++, "departmentId", getDepartmentId()));
		return sb.toString();
	}

	public Map<String, Object> toMap() {
		Map<String, Object> res = new HashMap<>();
		if(getSubjectDepartmentId() != null) res.put("subjectDepartmentId", getSubjectDepartmentId());
		if(getSubjectTypeId() != null) res.put("subjectTypeId", getSubjectTypeId());
		if(getDepartmentId() != null) res.put("departmentId", getDepartmentId());
		return res;
	}

	@Override
	public void setDataFromMap(Map<String, Object> values){
		Object val;
		if((val = values.get("subjectDepartmentId")) != null) setSubjectDepartmentId(__getInt(val));
		if((val = values.get("subjectTypeId")) != null) setSubjectTypeId(__getInt(val));
		if((val = values.get("departmentId")) != null) setDepartmentId(__getInt(val));
	}

	protected Integer  __subject_department_id ;
	protected Integer  __subject_type_id ;
	protected Integer  __department_id ;
}
