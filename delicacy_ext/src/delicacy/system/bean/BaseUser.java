package delicacy.system.bean;

import java.util.Map;
import java.util.Objects;
import delicacy.common.GenericBase;
import delicacy.common.BaseFactory;

public class BaseUser extends GenericBase implements BaseFactory<BaseUser>, Comparable<BaseUser> 
{


	public static BaseUser newInstance(){
		return new BaseUser();
	}

	@Override
	public BaseUser make(){
		BaseUser b = new BaseUser();
		return b;
	}

	public final static java.lang.String CS_USER_ID = "user_id" ;
	public final static java.lang.String CS_USER_NO = "user_no" ;
	public final static java.lang.String CS_USER_NAME = "user_name" ;
	public final static java.lang.String CS_MOBILE_PHONE = "mobile_phone" ;
	public final static java.lang.String CS_PHONE_NUMBER = "phone_number" ;
	public final static java.lang.String CS_EMAIL = "email" ;
	public final static java.lang.String CS_COMPANY_ID = "company_id" ;
	public final static java.lang.String CS_COMPANY_NO = "company_no" ;
	public final static java.lang.String CS_DEPARTMENT = "department" ;
	public final static java.lang.String CS_JOB = "job" ;
	public final static java.lang.String CS_EMPLOYEE_ID = "employee_id" ;
	public final static java.lang.String CS_ECMC_WORKSPACE = "ecmc_workspace" ;
	public final static java.lang.String CS_USER_TYPE = "user_type" ;
	public final static java.lang.String CS_USER_STATUS = "user_status" ;
	public final static java.lang.String CS_CREATE_TIME = "create_time" ;
	public final static java.lang.String CS_DELETE_TIME = "delete_time" ;
	public final static java.lang.String CS_DELETE_FLAG = "delete_flag" ;

	public final static java.lang.String ALL_CAPTIONS = "主键编码,用户编号,用户名称,手机号码,电话号码,邮箱,公司,公司编码,部门,职务,职员编码,ECMC本地工作空间, 1外部用户, 1停用,创建时间,删除时间,删除标志";

	public java.lang.Integer getUserId() {
		return this.__user_id;
	}

	public void setUserId( java.lang.Integer value ) {
		this.__user_id = value;
	}

	public java.lang.String getUserNo() {
		return this.__user_no;
	}

	public void setUserNo( java.lang.String value ) {
		this.__user_no = value;
	}

	public java.lang.String getUserName() {
		return this.__user_name;
	}

	public void setUserName( java.lang.String value ) {
		this.__user_name = value;
	}

	public java.lang.String getMobilePhone() {
		return this.__mobile_phone;
	}

	public void setMobilePhone( java.lang.String value ) {
		this.__mobile_phone = value;
	}

	public java.lang.String getPhoneNumber() {
		return this.__phone_number;
	}

	public void setPhoneNumber( java.lang.String value ) {
		this.__phone_number = value;
	}

	public java.lang.String getEmail() {
		return this.__email;
	}

	public void setEmail( java.lang.String value ) {
		this.__email = value;
	}

	public java.lang.Integer getCompanyId() {
		return this.__company_id;
	}

	public void setCompanyId( java.lang.Integer value ) {
		this.__company_id = value;
	}
	
	public java.lang.String getCompanyNo() {
		return this.__company_no;
	}
	
	public void setCompanyNo( java.lang.String value ) {
		this.__company_no = value;
	}

	public java.lang.String getDepartment() {
		return this.__department;
	}

	public void setDepartment( java.lang.String value ) {
		this.__department = value;
	}

	public java.lang.String getJob() {
		return this.__job;
	}

	public void setJob( java.lang.String value ) {
		this.__job = value;
	}

	public java.lang.Integer getEmployeeId() {
		return this.__employee_id;
	}

	public void setEmployeeId( java.lang.Integer value ) {
		this.__employee_id = value;
	}

	public java.lang.String getEcmcWorkspace() {
		return this.__ecmc_workspace;
	}

	public void setEcmcWorkspace( java.lang.String value ) {
		this.__ecmc_workspace = value;
	}

	public java.lang.Integer getUserType() {
		return this.__user_type;
	}

	public void setUserType( java.lang.Integer value ) {
		this.__user_type = value;
	}

	public java.lang.Integer getUserStatus() {
		return this.__user_status;
	}

	public void setUserStatus( java.lang.Integer value ) {
		this.__user_status = value;
	}

	public java.util.Date getCreateTime() {
		return this.__create_time;
	}

	public void setCreateTime( java.util.Date value ) {
		this.__create_time = value;
	}

	public java.util.Date getDeleteTime() {
		return this.__delete_time;
	}

	public void setDeleteTime( java.util.Date value ) {
		this.__delete_time = value;
	}

	public java.lang.Integer getDeleteFlag() {
		return this.__delete_flag;
	}

	public void setDeleteFlag( java.lang.Integer value ) {
		this.__delete_flag = value;
	}

	public void cloneCopy(BaseUser __bean){
		__bean.setUserId(getUserId());
		__bean.setUserNo(getUserNo());
		__bean.setUserName(getUserName());
		__bean.setMobilePhone(getMobilePhone());
		__bean.setPhoneNumber(getPhoneNumber());
		__bean.setEmail(getEmail());
		__bean.setCompanyId(getCompanyId());
		__bean.setCompanyNo(getCompanyNo());
		__bean.setDepartment(getDepartment());
		__bean.setJob(getJob());
		__bean.setEmployeeId(getEmployeeId());
		__bean.setEcmcWorkspace(getEcmcWorkspace());
		__bean.setUserType(getUserType());
		__bean.setUserStatus(getUserStatus());
		__bean.setCreateTime(getCreateTime());
		__bean.setDeleteTime(getDeleteTime());
		__bean.setDeleteFlag(getDeleteFlag());
	}

	public java.lang.String toCSVString() {

		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
		StringBuilder sb = new StringBuilder();
		sb.append(getUserId() == null ? "" : getUserId());
		sb.append(",");
		sb.append(getUserNo() == null ? "" : getUserNo());
		sb.append(",");
		sb.append(getUserName() == null ? "" : getUserName());
		sb.append(",");
		sb.append(getMobilePhone() == null ? "" : getMobilePhone());
		sb.append(",");
		sb.append(getPhoneNumber() == null ? "" : getPhoneNumber());
		sb.append(",");
		sb.append(getEmail() == null ? "" : getEmail());
		sb.append(",");
		sb.append(getCompanyId() == null ? "" : getCompanyId());
		sb.append(",");
		sb.append(getCompanyNo() == null ? "" : getCompanyNo());
		sb.append(",");
		String strDepartment = delicacy.system.executor.SelectValueCache.getSelectValue("departments", String.valueOf(getDepartment()));
		sb.append(strDepartment == null ? "" : strDepartment);
		sb.append(",");
		sb.append(getJob() == null ? "" : getJob());
		sb.append(",");
		String strEmployeeId = delicacy.system.executor.SelectValueCache.getSelectValue("employees", String.valueOf(getEmployeeId()));
		sb.append(strEmployeeId == null ? "" : strEmployeeId);
		sb.append(",");
		sb.append(getEcmcWorkspace() == null ? "" : getEcmcWorkspace());
		sb.append(",");
		sb.append(getUserType() == null ? "" : getUserType());
		sb.append(",");
		sb.append(getUserStatus() == null ? "" : getUserStatus());
		sb.append(",");
		sb.append(getCreateTime() == null ? "" : sdf.format(getCreateTime()));
		sb.append(",");
		sb.append(getDeleteTime() == null ? "" : sdf.format(getDeleteTime()));
		sb.append(",");
		sb.append(getDeleteFlag() == null ? "" : getDeleteFlag());
		return sb.toString();
	}

	@Override
	public int compareTo(BaseUser o) {
		return __user_id == null ? -1 : __user_id.compareTo(o.getUserId());
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 97 * hash + Objects.hashCode(this.__user_id);
		hash = 97 * hash + Objects.hashCode(this.__user_no);
		hash = 97 * hash + Objects.hashCode(this.__user_name);
		hash = 97 * hash + Objects.hashCode(this.__mobile_phone);
		hash = 97 * hash + Objects.hashCode(this.__phone_number);
		hash = 97 * hash + Objects.hashCode(this.__email);
		hash = 97 * hash + Objects.hashCode(this.__company_id);
		hash = 97 * hash + Objects.hashCode(this.__company_no);
		hash = 97 * hash + Objects.hashCode(this.__department);
		hash = 97 * hash + Objects.hashCode(this.__job);
		hash = 97 * hash + Objects.hashCode(this.__employee_id);
		hash = 97 * hash + Objects.hashCode(this.__ecmc_workspace);
		hash = 97 * hash + Objects.hashCode(this.__user_type);
		hash = 97 * hash + Objects.hashCode(this.__user_status);
		hash = 97 * hash + Objects.hashCode(this.__create_time);
		hash = 97 * hash + Objects.hashCode(this.__delete_time);
		hash = 97 * hash + Objects.hashCode(this.__delete_flag);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(getClass() != obj.getClass()) return false;
		final BaseUser o = (BaseUser)obj;
		if(!Objects.equals(this.__user_id, o.getUserId())) return false;
		if(!Objects.equals(this.__user_no, o.getUserNo())) return false;
		if(!Objects.equals(this.__user_name, o.getUserName())) return false;
		if(!Objects.equals(this.__mobile_phone, o.getMobilePhone())) return false;
		if(!Objects.equals(this.__phone_number, o.getPhoneNumber())) return false;
		if(!Objects.equals(this.__email, o.getEmail())) return false;
		if(!Objects.equals(this.__company_id, o.getCompanyId())) return false;
		if(!Objects.equals(this.__company_no, o.getCompanyNo())) return false;
		if(!Objects.equals(this.__department, o.getDepartment())) return false;
		if(!Objects.equals(this.__job, o.getJob())) return false;
		if(!Objects.equals(this.__employee_id, o.getEmployeeId())) return false;
		if(!Objects.equals(this.__ecmc_workspace, o.getEcmcWorkspace())) return false;
		if(!Objects.equals(this.__user_type, o.getUserType())) return false;
		if(!Objects.equals(this.__user_status, o.getUserStatus())) return false;
		if(!Objects.equals(this.__create_time, o.getCreateTime())) return false;
		if(!Objects.equals(this.__delete_time, o.getDeleteTime())) return false;
		if(!Objects.equals(this.__delete_flag, o.getDeleteFlag())) return false;
		return true;
	}

	@Override
	public java.lang.String toJSONString() {

		StringBuilder sb = new StringBuilder();
		int count = 0;
		if(getUserId() != null) sb.append(__wrapNumber(count++, "userId", getUserId()));
		if(getUserNo() != null) sb.append(__wrapString(count++, "userNo", getUserNo()));
		if(getUserName() != null) sb.append(__wrapString(count++, "userName", getUserName()));
		if(getMobilePhone() != null) sb.append(__wrapString(count++, "mobilePhone", getMobilePhone()));
		if(getPhoneNumber() != null) sb.append(__wrapString(count++, "phoneNumber", getPhoneNumber()));
		if(getEmail() != null) sb.append(__wrapString(count++, "email", getEmail()));
		if(getCompanyId() != null) sb.append(__wrapNumber(count++, "companyId", getCompanyId()));
		if(getCompanyNo() != null) sb.append(__wrapString(count++, "companyNo", getCompanyNo()));
		if(getDepartment() != null) sb.append(__wrapString(count++, "department", getDepartment()));
		if(getJob() != null) sb.append(__wrapString(count++, "job", getJob()));
		if(getEmployeeId() != null) sb.append(__wrapNumber(count++, "employeeId", getEmployeeId()));
		if(getEcmcWorkspace() != null) sb.append(__wrapString(count++, "ecmcWorkspace", getEcmcWorkspace()));
		if(getUserType() != null) sb.append(__wrapNumber(count++, "userType", getUserType()));
		if(getUserStatus() != null) sb.append(__wrapNumber(count++, "userStatus", getUserStatus()));
		if(getCreateTime() != null) sb.append(__wrapDate(count++, "createTime", getCreateTime()));
		if(getDeleteTime() != null) sb.append(__wrapDate(count++, "deleteTime", getDeleteTime()));
		if(getDeleteFlag() != null) sb.append(__wrapNumber(count++, "deleteFlag", getDeleteFlag()));
		return sb.toString();
	}

	@Override
	public void setDataFromMap(Map<String, Object> values){
		Object val;
		if((val = values.get("userId")) != null) setUserId(__getInt(val)); 
		if((val = values.get("userNo")) != null) setUserNo(__getString(val));
		if((val = values.get("userName")) != null) setUserName(__getString(val));
		if((val = values.get("mobilePhone")) != null) setMobilePhone(__getString(val));
		if((val = values.get("phoneNumber")) != null) setPhoneNumber(__getString(val));
		if((val = values.get("email")) != null) setEmail(__getString(val));
		if((val = values.get("companyId")) != null) setCompanyId(__getInt(val));
		if((val = values.get("companyNo")) != null) setCompanyNo(__getString(val));
		if((val = values.get("department")) != null) setDepartment(__getString(val));
		if((val = values.get("job")) != null) setJob(__getString(val));
		if((val = values.get("employeeId")) != null) setEmployeeId(__getInt(val)); 
		if((val = values.get("ecmcWorkspace")) != null) setEcmcWorkspace(__getString(val));
		if((val = values.get("userType")) != null) setUserType(__getInt(val)); 
		if((val = values.get("userStatus")) != null) setUserStatus(__getInt(val)); 
		if((val = values.get("createTime")) != null) setCreateTime(__getDate(val)); 
		if((val = values.get("deleteTime")) != null) setDeleteTime(__getDate(val)); 
		if((val = values.get("deleteFlag")) != null) setDeleteFlag(__getInt(val)); 
	}

	protected java.lang.Integer  __user_id ;
	protected java.lang.String  __user_no ;
	protected java.lang.String  __user_name ;
	protected java.lang.String  __mobile_phone ;
	protected java.lang.String  __phone_number ;
	protected java.lang.String  __email ;
	protected java.lang.Integer  __company_id ;
	protected java.lang.String  __company_no ;
	protected java.lang.String  __department ;
	protected java.lang.String  __job ;
	protected java.lang.Integer  __employee_id ;
	protected java.lang.String  __ecmc_workspace ;
	protected java.lang.Integer  __user_type ;
	protected java.lang.Integer  __user_status ;
	protected java.util.Date  __create_time ;
	protected java.util.Date  __delete_time ;
	protected java.lang.Integer  __delete_flag ;
}
