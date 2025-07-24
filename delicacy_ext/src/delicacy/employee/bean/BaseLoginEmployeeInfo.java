package delicacy.employee.bean;

import java.util.Map;
import java.util.Objects;
import delicacy.common.GenericBase;
import delicacy.common.BaseFactory;

public class BaseLoginEmployeeInfo extends GenericBase
		implements BaseFactory<BaseLoginEmployeeInfo>, Comparable<BaseLoginEmployeeInfo> {

	public static BaseLoginEmployeeInfo newInstance() {
		return new BaseLoginEmployeeInfo();
	}

	@Override
	public BaseLoginEmployeeInfo make() {
		BaseLoginEmployeeInfo b = new BaseLoginEmployeeInfo();
		return b;
	}

	public final static java.lang.String CS_EMPLOYEE_ID = "employee_id";
	public final static java.lang.String CS_EMPLOYEE_NO = "employee_no";
	public final static java.lang.String CS_EMPLOYEE_NAME = "employee_name";
	public final static java.lang.String CS_EMPLOYEE_PASSWORD = "employee_password";
	public final static java.lang.String CS_PHOTO = "photo";
	public final static java.lang.String CS_DEPARTMENT_ID = "department_id";
	public final static java.lang.String CS_PLATE_ID = "plate_id";
	public final static java.lang.String CS_ROLE_ID = "role_id";
	public final static java.lang.String CS_GRADE_ID = "grade_id" ;
	public final static java.lang.String CS_GRADE_NAME = "grade_name" ;
	public final static java.lang.String CS_COMPANY_ID = "company_id";
	public final static java.lang.String CS_DUTY_ID = "duty_id";
	public final static java.lang.String CS_DUTY_NAME = "duty_name";
	public final static java.lang.String CS_COMPANY_NO = "company_no";

	public final static java.lang.String ALL_CAPTIONS = "员工编码,员工编号,员工姓名,密码,头像,部门,业务部门,角色,职级,职级名称,归属公司,职位,职位名称,公司编号";

	public java.lang.Integer getEmployeeId() {
		return this.__employee_id;
	}

	public void setEmployeeId(java.lang.Integer value) {
		this.__employee_id = value;
	}

	public java.lang.String getEmployeeNo() {
		return this.__employee_no;
	}

	public void setEmployeeNo(java.lang.String value) {
		this.__employee_no = value;
	}

	public java.lang.String getEmployeeName() {
		return this.__employee_name;
	}

	public void setEmployeeName(java.lang.String value) {
		this.__employee_name = value;
	}

	public java.lang.String getEmployeePassword() {
		return this.__employee_password;
	}

	public void setEmployeePassword(java.lang.String value) {
		this.__employee_password = value;
	}

	public java.lang.String getPhoto() {
		return this.__photo;
	}

	public void setPhoto(java.lang.String value) {
		this.__photo = value;
	}

	public java.lang.Integer getDepartmentId() {
		return this.__department_id;
	}

	public void setDepartmentId(java.lang.Integer value) {
		this.__department_id = value;
	}

	public java.lang.Integer getPlateId() {
		return this.__plate_id;
	}

	public void setPlateId(java.lang.Integer value) {
		this.__plate_id = value;
	}

	public java.lang.Integer getRoleId() {
		return this.__role_id;
	}

	public void setRoleId(java.lang.Integer value) {
		this.__role_id = value;
	}

	public java.lang.Integer getGradeId() {
		return this.__grade_id;
	}

	public void setGradeId( java.lang.Integer value ) {
		this.__grade_id = value;
	}

	public java.lang.String getGradeName() {
		return this.__grade_name;
	}

	public void setGradeName( java.lang.String value ) {
		this.__grade_name = value;
	}

	public java.lang.Integer getCompanyId() {
		return this.__company_id;
	}

	public void setCompanyId(java.lang.Integer value) {
		this.__company_id = value;
	}

	public java.lang.Integer getDutyId() {
		return this.__duty_id;
	}

	public void setDutyId(java.lang.Integer value) {
		this.__duty_id = value;
	}

	public java.lang.String getDutyName() {
		return this.__duty_name;
	}

	public void setDutyName( java.lang.String value ) {
		this.__duty_name = value;
	}

	public java.lang.String getCompanyNo() {
		return this.__company_no;
	}

	public void setCompanyNo(java.lang.String value) {
		this.__company_no = value;
	}

	public void cloneCopy(BaseLoginEmployeeInfo __bean) {
		__bean.setEmployeeId(getEmployeeId());
		__bean.setEmployeeNo(getEmployeeNo());
		__bean.setEmployeeName(getEmployeeName());
		__bean.setEmployeePassword(getEmployeePassword());
		__bean.setPhoto(getPhoto());
		__bean.setDepartmentId(getDepartmentId());
		__bean.setPlateId(getPlateId());
		__bean.setRoleId(getRoleId());
		__bean.setGradeId(getGradeId());
		__bean.setGradeName(getGradeName());
		__bean.setCompanyId(getCompanyId());
		__bean.setDutyId(getDutyId());
		__bean.setDutyName(getDutyName());
		__bean.setCompanyNo(getCompanyNo());
	}

	public java.lang.String toCSVString() {

		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
		StringBuilder sb = new StringBuilder();
		String strEmployeeId = delicacy.system.executor.SelectValueCache.getSelectValue("employees",
				String.valueOf(getEmployeeId()));
		sb.append(strEmployeeId == null ? "" : strEmployeeId);
		sb.append(",");
		sb.append(getEmployeeNo() == null ? "" : getEmployeeNo());
		sb.append(",");
		String strEmployeeName = delicacy.system.executor.SelectValueCache.getSelectValue("employees",
				String.valueOf(getEmployeeName()));
		sb.append(strEmployeeName == null ? "" : strEmployeeName);
		sb.append(",");
		sb.append(getEmployeePassword() == null ? "" : getEmployeePassword());
		sb.append(",");
		sb.append(getPhoto() == null ? "" : getPhoto());
		sb.append(",");
		String strDepartmentId = delicacy.system.executor.SelectValueCache.getSelectValue("departments",
				String.valueOf(getDepartmentId()));
		sb.append(strDepartmentId == null ? "" : strDepartmentId);
		sb.append(",");
		String strPlateId = delicacy.system.executor.SelectValueCache.getSelectValue("system_dictionary_1",
				String.valueOf(getPlateId()));
		sb.append(strPlateId == null ? "" : strPlateId);
		sb.append(",");
		sb.append(getRoleId() == null ? "" : getRoleId());
		sb.append(",");
		sb.append(getGradeId() == null ? "" : getGradeId());
		sb.append(",");
		sb.append(getGradeName() == null ? "" : getGradeName());
		sb.append(",");
		String strCompanyId = delicacy.system.executor.SelectValueCache.getSelectValue("system_dictionary_26",
				String.valueOf(getCompanyId()));
		sb.append(strCompanyId == null ? "" : strCompanyId);
		sb.append(",");
		sb.append(getDutyId() == null ? "" : getDutyId());
		sb.append(",");
		sb.append(getDutyName() == null ? "" : getDutyName());
		sb.append(",");
		sb.append(getCompanyNo() == null ? "" : getCompanyNo());
		return sb.toString();
	}

	@Override
	public int compareTo(BaseLoginEmployeeInfo o) {
		return __employee_id == null ? -1 : __employee_id.compareTo(o.getEmployeeId());
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 97 * hash + Objects.hashCode(this.__employee_id);
		hash = 97 * hash + Objects.hashCode(this.__employee_no);
		hash = 97 * hash + Objects.hashCode(this.__employee_name);
		hash = 97 * hash + Objects.hashCode(this.__employee_password);
		hash = 97 * hash + Objects.hashCode(this.__photo);
		hash = 97 * hash + Objects.hashCode(this.__department_id);
		hash = 97 * hash + Objects.hashCode(this.__plate_id);
		hash = 97 * hash + Objects.hashCode(this.__role_id);
		hash = 97 * hash + Objects.hashCode(this.__grade_id);
		hash = 97 * hash + Objects.hashCode(this.__grade_name);
		hash = 97 * hash + Objects.hashCode(this.__company_id);
		hash = 97 * hash + Objects.hashCode(this.__duty_id);
		hash = 97 * hash + Objects.hashCode(this.__duty_name);
		hash = 97 * hash + Objects.hashCode(this.__company_no);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final BaseLoginEmployeeInfo o = (BaseLoginEmployeeInfo) obj;
		if (!Objects.equals(this.__employee_id, o.getEmployeeId()))
			return false;
		if (!Objects.equals(this.__employee_no, o.getEmployeeNo()))
			return false;
		if (!Objects.equals(this.__employee_name, o.getEmployeeName()))
			return false;
		if (!Objects.equals(this.__employee_password, o.getEmployeePassword()))
			return false;
		if (!Objects.equals(this.__photo, o.getPhoto()))
			return false;
		if (!Objects.equals(this.__department_id, o.getDepartmentId()))
			return false;
		if (!Objects.equals(this.__plate_id, o.getPlateId()))
			return false;
		if (!Objects.equals(this.__role_id, o.getRoleId()))
			return false;
		if(!Objects.equals(this.__grade_id, o.getGradeId()))
			return false;
		if(!Objects.equals(this.__grade_name, o.getGradeName()))
			return false;
		if (!Objects.equals(this.__company_id, o.getCompanyId()))
			return false;
		if (!Objects.equals(this.__duty_id, o.getDutyId()))
			return false;
		if (!Objects.equals(this.__duty_name, o.getDutyName()))
			return false;
		if (!Objects.equals(this.__company_no, o.getCompanyNo()))
			return false;
		return true;
	}

	@Override
	public java.lang.String toJSONString() {

		StringBuilder sb = new StringBuilder();
		int count = 0;
		if (getEmployeeId() != null)
			sb.append(__wrapNumber(count++, "employeeId", getEmployeeId()));
		if (getEmployeeNo() != null)
			sb.append(__wrapString(count++, "employeeNo", getEmployeeNo()));
		if (getEmployeeName() != null)
			sb.append(__wrapString(count++, "employeeName", getEmployeeName()));
		if (getEmployeePassword() != null)
			sb.append(__wrapString(count++, "employeePassword", getEmployeePassword()));
		if (getPhoto() != null)
			sb.append(__wrapString(count++, "photo", getPhoto()));
		if (getDepartmentId() != null)
			sb.append(__wrapNumber(count++, "departmentId", getDepartmentId()));
		if (getPlateId() != null)
			sb.append(__wrapNumber(count++, "plateId", getPlateId()));
		if (getRoleId() != null)
			sb.append(__wrapNumber(count++, "roleId", getRoleId()));
		if(getGradeId() != null)
			sb.append(__wrapNumber(count++, "gradeId", getGradeId()));
		if(getGradeName() != null)
			sb.append(__wrapString(count++, "gradeName", getGradeName()));
		if (getCompanyId() != null)
			sb.append(__wrapNumber(count++, "companyId", getCompanyId()));
		if (getDutyId() != null)
			sb.append(__wrapNumber(count++, "dutyId", getDutyId()));
		if (getDutyName() != null)
			sb.append(__wrapString(count++, "dutyName", getDutyName()));
		if (getCompanyNo() != null)
			sb.append(__wrapString(count++, "companyNo", getCompanyNo()));
		return sb.toString();
	}

	@Override
	public void setDataFromMap(Map<String, Object> values) {
		Object val;
		if ((val = values.get("employeeId")) != null)
			setEmployeeId(__getInt(val));
		if ((val = values.get("employeeNo")) != null)
			setEmployeeNo(__getString(val));
		if ((val = values.get("employeeName")) != null)
			setEmployeeName(__getString(val));
		if ((val = values.get("employeePassword")) != null)
			setEmployeePassword(__getString(val));
		if ((val = values.get("photo")) != null)
			setPhoto(__getString(val));
		if ((val = values.get("departmentId")) != null)
			setDepartmentId(__getInt(val));
		if ((val = values.get("plateId")) != null)
			setPlateId(__getInt(val));
		if ((val = values.get("roleId")) != null)
			setRoleId(__getInt(val));
		if((val = values.get("gradeId")) != null)
			setGradeId(__getInt(val));
		if((val = values.get("gradeName")) != null)
			setGradeName(__getString(val));
		if ((val = values.get("companyId")) != null)
			setCompanyId(__getInt(val));
		if ((val = values.get("dutyId")) != null)
			setDutyId(__getInt(val));
		if((val = values.get("dutyName")) != null)
			setDutyName(__getString(val));
		if ((val = values.get("companyNo")) != null)
			setCompanyNo(__getString(val));
	}

	protected java.lang.Integer __employee_id;
	protected java.lang.String __employee_no;
	protected java.lang.String __employee_name;
	protected java.lang.String __employee_password;
	protected java.lang.String __photo;
	protected java.lang.Integer __department_id;
	protected java.lang.Integer __plate_id;
	protected java.lang.Integer __role_id;
	protected java.lang.Integer  __grade_id ;
	protected java.lang.String  __grade_name ;
	protected java.lang.Integer __company_id;
	protected java.lang.Integer __duty_id;
	protected java.lang.String __duty_name;
	protected java.lang.String __company_no;
}
