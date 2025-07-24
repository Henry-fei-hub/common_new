package com.delicacy.client.data;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.view.client.ProvidesKey;

public class BEmployee extends GenericBase{

	public static final ProvidesKey<BEmployee> KEY_PROVIDER = new ProvidesKey<BEmployee>() {
		@Override
		public Object getKey(BEmployee item) {
			return item == null ? null : item.getEmployeeId();
		}
	};

	public static BEmployee newInstance() {
		return new BEmployee();
	}

	public final static java.lang.String CAPTION_EMPLOYEE_ID = "员工编码";
	public final static java.lang.String CAPTION_EMPLOYEE_NO = "员工编号";
	public final static java.lang.String CAPTION_EMPLOYEE_NAME = "员工姓名";
	public final static java.lang.String CAPTION_DEPARTMENT_ID = "部门";
	public final static java.lang.String CAPTION_ROLE_ID = "角色";
	public final static java.lang.String CAPTION_GRADE_ID = "等级";
	public final static java.lang.String CAPTION_EMPLOYEE_PASSWORD = "密码";
	public final static java.lang.String CAPTION_MOBILE = "手机";
	public final static java.lang.String CAPTION_PHONE = "电话";
	public final static java.lang.String CAPTION_QQ = "QQ";
	public final static java.lang.String CAPTION_EMAIL = "邮箱";
	public final static java.lang.String CAPTION_ONBOARD_DATE = "入职日期";
	public final static java.lang.String CAPTION_RESIGNATION_DATE = "离职日期";
	public final static java.lang.String CAPTION_STATUS = "状态";
	public final static java.lang.String CAPTION_USABLE_STATUS = "是否可用";
	public final static java.lang.String CAPTION_IS_DEPARTMENT = "是否为部门负责人";
	public final static java.lang.String CAPTION_PHOTO = "头像";
	public final static java.lang.String CAPTION_GENDER = "性别";
	public final static java.lang.String CAPTION_AUTOGRAPH = "我的签名";
	public final static java.lang.String CAPTION_AGE = "";
	public final static java.lang.String CAPTION_BIRTH = "出生日期";
	public final static java.lang.String CAPTION_CARD = "身份证号";
	public final static java.lang.String CAPTION_ADDRESS = "家庭地址";
	public final static java.lang.String CAPTION_ALTERNATE_FIELD1 = "备用字段1";
	public final static java.lang.String CAPTION_ALTERNATE_FIELD2 = "备用字段2";
	public final static java.lang.String CAPTION_ALTERNATE_FIELD3 = "备用字段3";
	public final static java.lang.String CAPTION_LOCKED = "是否锁定";
	public final static java.lang.String CAPTION_PLATE_ID = "板块";
	public final static java.lang.String CAPTION_DUTY_ID = "职位名称";
	public final static java.lang.String CAPTION_USER_ACCT = "上级领导";
	public final static java.lang.String CAPTION_EMPLOYEE_NAME_EN = "英文名";
	public final static java.lang.String CAPTION_EDUCATION = "学历";
	public final static java.lang.String CAPTION_DEGREE = "学位";
	public final static java.lang.String CAPTION_NATIONALITY = "民族";
	public final static java.lang.String CAPTION_MARRIED_STATUS = "婚姻状况";
	public final static java.lang.String CAPTION_HEALTH = "身体状况";
	public final static java.lang.String CAPTION_WORK_ADDRESS = "工作地";
	public final static java.lang.String CAPTION_REGISTERED_ADDRESS = "注册地";
	public final static java.lang.String CAPTION_OA_ID = "OA编码";
	public final static java.lang.String CAPTION_OA_DEPART = "OA部门";
	public final static java.lang.String CAPTION_IS_HEADCOUNT = "总部员工";
	public final static java.lang.String CAPTION_IS_CHECK = "参与考勤";
	public final static java.lang.String CAPTION_DIRECT_LEADER = "直接领导";

	public Integer getEmployeeId() {
		return this.__employee_id;
	}

	public void setEmployeeId(Integer value) {
		this.__employee_id = value;
	}

	public String getEmployeeNo() {
		return this.__employee_no;
	}

	public void setEmployeeNo(String value) {
		this.__employee_no = value;
	}

	public String getEmployeeName() {
		return this.__employee_name;
	}

	public void setEmployeeName(String value) {
		this.__employee_name = value;
	}

	public Integer getDepartmentId() {
		return this.__department_id;
	}

	public void setDepartmentId(Integer value) {
		this.__department_id = value;
	}

	public Integer getRoleId() {
		return this.__role_id;
	}

	public void setRoleId(Integer value) {
		this.__role_id = value;
	}

	public Integer getGradeId() {
		return this.__grade_id;
	}

	public void setGradeId(Integer value) {
		this.__grade_id = value;
	}

	public String getEmployeePassword() {
		return this.__employee_password;
	}

	public void setEmployeePassword(String value) {
		this.__employee_password = value;
	}

	public String getMobile() {
		return this.__mobile;
	}

	public void setMobile(String value) {
		this.__mobile = value;
	}

	public String getPhone() {
		return this.__phone;
	}

	public void setPhone(String value) {
		this.__phone = value;
	}

	public String getQq() {
		return this.__qq;
	}

	public void setQq(String value) {
		this.__qq = value;
	}

	public String getEmail() {
		return this.__email;
	}

	public void setEmail(String value) {
		this.__email = value;
	}

	public java.util.Date getOnboardDate() {
		return this.__onboard_date;
	}

	public void setOnboardDate(java.util.Date value) {
		this.__onboard_date = value;
	}

	public java.util.Date getResignationDate() {
		return this.__resignation_date;
	}

	public void setResignationDate(java.util.Date value) {
		this.__resignation_date = value;
	}

	public Integer getStatus() {
		return this.__status;
	}

	public void setStatus(Integer value) {
		this.__status = value;
	}

	public Boolean getUsableStatus() {
		return this.__usable_status;
	}

	public void setUsableStatus(Boolean value) {
		this.__usable_status = value;
	}

	public Boolean getIsDepartment() {
		return this.__is_department;
	}

	public void setIsDepartment(Boolean value) {
		this.__is_department = value;
	}

	public String getPhoto() {
		return this.__photo;
	}

	public void setPhoto(String value) {
		this.__photo = value;
	}

	public Integer getGender() {
		return this.__gender;
	}

	public void setGender(Integer value) {
		this.__gender = value;
	}

	public String getAutograph() {
		return this.__autograph;
	}

	public void setAutograph(String value) {
		this.__autograph = value;
	}

	public Integer getAge() {
		return this.__age;
	}

	public void setAge(Integer value) {
		this.__age = value;
	}

	public java.util.Date getBirth() {
		return this.__birth;
	}

	public void setBirth(java.util.Date value) {
		this.__birth = value;
	}

	public String getCard() {
		return this.__card;
	}

	public void setCard(String value) {
		this.__card = value;
	}

	public String getAddress() {
		return this.__address;
	}

	public void setAddress(String value) {
		this.__address = value;
	}

	public String getAlternateField1() {
		return this.__alternate_field1;
	}

	public void setAlternateField1(String value) {
		this.__alternate_field1 = value;
	}

	public String getAlternateField2() {
		return this.__alternate_field2;
	}

	public void setAlternateField2(String value) {
		this.__alternate_field2 = value;
	}

	public String getAlternateField3() {
		return this.__alternate_field3;
	}

	public void setAlternateField3(String value) {
		this.__alternate_field3 = value;
	}

	public Boolean getLocked() {
		return this.__locked;
	}

	public void setLocked(Boolean value) {
		this.__locked = value;
	}

	public Integer getPlateId() {
		return this.__plate_id;
	}

	public void setPlateId(Integer value) {
		this.__plate_id = value;
	}

	public Integer getDutyId() {
		return this.__duty_id;
	}

	public void setDutyId(Integer value) {
		this.__duty_id = value;
	}

	public String getUserAcct() {
		return this.__user_acct;
	}

	public void setUserAcct(String value) {
		this.__user_acct = value;
	}

	public String getEmployeeNameEn() {
		return this.__employee_name_en;
	}

	public void setEmployeeNameEn(String value) {
		this.__employee_name_en = value;
	}

	public String getEducation() {
		return this.__education;
	}

	public void setEducation(String value) {
		this.__education = value;
	}

	public String getDegree() {
		return this.__degree;
	}

	public void setDegree(String value) {
		this.__degree = value;
	}

	public String getNationality() {
		return this.__nationality;
	}

	public void setNationality(String value) {
		this.__nationality = value;
	}

	public String getMarriedStatus() {
		return this.__married_status;
	}

	public void setMarriedStatus(String value) {
		this.__married_status = value;
	}

	public String getHealth() {
		return this.__health;
	}

	public void setHealth(String value) {
		this.__health = value;
	}

	public String getWorkAddress() {
		return this.__work_address;
	}

	public void setWorkAddress(String value) {
		this.__work_address = value;
	}

	public String getRegisteredAddress() {
		return this.__registered_address;
	}

	public void setRegisteredAddress(String value) {
		this.__registered_address = value;
	}

	public Integer getOaId() {
		return this.__oa_id;
	}

	public void setOaId(Integer value) {
		this.__oa_id = value;
	}

	public Integer getOaDepart() {
		return this.__oa_depart;
	}

	public void setOaDepart(Integer value) {
		this.__oa_depart = value;
	}

	public Boolean getIsHeadcount() {
		return this.__is_headcount;
	}

	public void setIsHeadcount(Boolean value) {
		this.__is_headcount = value;
	}

	public Boolean getIsCheck() {
		return this.__is_check;
	}

	public void setIsCheck(Boolean value) {
		this.__is_check = value;
	}

	public Integer getDirectLeader() {
		return this.__direct_leader;
	}

	public void setDirectLeader(Integer value) {
		this.__direct_leader = value;
	}

	@Override
	public java.lang.String toJSONString() {

		StringBuilder sb = new StringBuilder();
		int count = 0;
		if (getEmployeeId() != null) {
			sb.append(__wrapNumber(count++, "employeeId", getEmployeeId()));
		}
		if (getEmployeeNo() != null) {
			sb.append(__wrapString(count++, "employeeNo", getEmployeeNo()));
		}
		if (getEmployeeName() != null) {
			sb.append(__wrapString(count++, "employeeName", getEmployeeName()));
		}
		if (getDepartmentId() != null) {
			sb.append(__wrapNumber(count++, "departmentId", getDepartmentId()));
		}
		if (getRoleId() != null) {
			sb.append(__wrapNumber(count++, "roleId", getRoleId()));
		}
		if (getGradeId() != null) {
			sb.append(__wrapNumber(count++, "gradeId", getGradeId()));
		}
		if (getEmployeePassword() != null) {
			sb.append(__wrapString(count++, "employeePassword", getEmployeePassword()));
		}
		if (getMobile() != null) {
			sb.append(__wrapString(count++, "mobile", getMobile()));
		}
		if (getPhone() != null) {
			sb.append(__wrapString(count++, "phone", getPhone()));
		}
		if (getQq() != null) {
			sb.append(__wrapString(count++, "qq", getQq()));
		}
		if (getEmail() != null) {
			sb.append(__wrapString(count++, "email", getEmail()));
		}
		if (getOnboardDate() != null) {
			sb.append(__wrapDate(count++, "onboardDate", getOnboardDate()));
		}
		if (getResignationDate() != null) {
			sb.append(__wrapDate(count++, "resignationDate", getResignationDate()));
		}
		if (getStatus() != null) {
			sb.append(__wrapNumber(count++, "status", getStatus()));
		}
		if (getUsableStatus() != null) {
			sb.append(__wrapBoolean(count++, "usableStatus", getUsableStatus()));
		}
		if (getIsDepartment() != null) {
			sb.append(__wrapBoolean(count++, "isDepartment", getIsDepartment()));
		}
		if (getPhoto() != null) {
			sb.append(__wrapString(count++, "photo", getPhoto()));
		}
		if (getGender() != null) {
			sb.append(__wrapNumber(count++, "gender", getGender()));
		}
		if (getAutograph() != null) {
			sb.append(__wrapString(count++, "autograph", getAutograph()));
		}
		if (getAge() != null) {
			sb.append(__wrapNumber(count++, "age", getAge()));
		}
		if (getBirth() != null) {
			sb.append(__wrapDate(count++, "birth", getBirth()));
		}
		if (getCard() != null) {
			sb.append(__wrapString(count++, "card", getCard()));
		}
		if (getAddress() != null) {
			sb.append(__wrapString(count++, "address", getAddress()));
		}
		if (getAlternateField1() != null) {
			sb.append(__wrapString(count++, "alternateField1", getAlternateField1()));
		}
		if (getAlternateField2() != null) {
			sb.append(__wrapString(count++, "alternateField2", getAlternateField2()));
		}
		if (getAlternateField3() != null) {
			sb.append(__wrapString(count++, "alternateField3", getAlternateField3()));
		}
		if (getLocked() != null) {
			sb.append(__wrapBoolean(count++, "locked", getLocked()));
		}
		if (getPlateId() != null) {
			sb.append(__wrapNumber(count++, "plateId", getPlateId()));
		}
		if (getDutyId() != null) {
			sb.append(__wrapNumber(count++, "dutyId", getDutyId()));
		}
		if (getUserAcct() != null) {
			sb.append(__wrapString(count++, "userAcct", getUserAcct()));
		}
		if (getEmployeeNameEn() != null) {
			sb.append(__wrapString(count++, "employeeNameEn", getEmployeeNameEn()));
		}
		if (getEducation() != null) {
			sb.append(__wrapString(count++, "education", getEducation()));
		}
		if (getDegree() != null) {
			sb.append(__wrapString(count++, "degree", getDegree()));
		}
		if (getNationality() != null) {
			sb.append(__wrapString(count++, "nationality", getNationality()));
		}
		if (getMarriedStatus() != null) {
			sb.append(__wrapString(count++, "marriedStatus", getMarriedStatus()));
		}
		if (getHealth() != null) {
			sb.append(__wrapString(count++, "health", getHealth()));
		}
		if (getWorkAddress() != null) {
			sb.append(__wrapString(count++, "workAddress", getWorkAddress()));
		}
		if (getRegisteredAddress() != null) {
			sb.append(__wrapString(count++, "registeredAddress", getRegisteredAddress()));
		}
		if (getOaId() != null) {
			sb.append(__wrapNumber(count++, "oaId", getOaId()));
		}
		if (getOaDepart() != null) {
			sb.append(__wrapNumber(count++, "oaDepart", getOaDepart()));
		}
		if (getIsHeadcount() != null) {
			sb.append(__wrapBoolean(count++, "isHeadcount", getIsHeadcount()));
		}
		if (getIsCheck() != null) {
			sb.append(__wrapBoolean(count++, "isCheck", getIsCheck()));
		}
		if (getDirectLeader() != null) {
			sb.append(__wrapNumber(count++, "directLeader", getDirectLeader()));
		}
		return sb.toString();
	}

	@Override
	public void setDataFromJSON(JSONObject values) {
		JSONValue val;
		if ((val = values.get("employeeId")) != null) {
			setEmployeeId(__getInt(val));
		}
		if ((val = values.get("employeeNo")) != null) {
			setEmployeeNo(__getString(val));
		}
		if ((val = values.get("employeeName")) != null) {
			setEmployeeName(__getString(val));
		}
		if ((val = values.get("departmentId")) != null) {
			setDepartmentId(__getInt(val));
		}
		if ((val = values.get("roleId")) != null) {
			setRoleId(__getInt(val));
		}
		if ((val = values.get("gradeId")) != null) {
			setGradeId(__getInt(val));
		}
		if ((val = values.get("employeePassword")) != null) {
			setEmployeePassword(__getString(val));
		}
		if ((val = values.get("mobile")) != null) {
			setMobile(__getString(val));
		}
		if ((val = values.get("phone")) != null) {
			setPhone(__getString(val));
		}
		if ((val = values.get("qq")) != null) {
			setQq(__getString(val));
		}
		if ((val = values.get("email")) != null) {
			setEmail(__getString(val));
		}
		if ((val = values.get("onboardDate")) != null) {
			setOnboardDate(__getDate(val));
		}
		if ((val = values.get("resignationDate")) != null) {
			setResignationDate(__getDate(val));
		}
		if ((val = values.get("status")) != null) {
			setStatus(__getInt(val));
		}
		if ((val = values.get("usableStatus")) != null) {
			setUsableStatus(__getBoolean(val));
		}
		if ((val = values.get("isDepartment")) != null) {
			setIsDepartment(__getBoolean(val));
		}
		if ((val = values.get("photo")) != null) {
			setPhoto(__getString(val));
		}
		if ((val = values.get("gender")) != null) {
			setGender(__getInt(val));
		}
		if ((val = values.get("autograph")) != null) {
			setAutograph(__getString(val));
		}
		if ((val = values.get("age")) != null) {
			setAge(__getInt(val));
		}
		if ((val = values.get("birth")) != null) {
			setBirth(__getDate(val));
		}
		if ((val = values.get("card")) != null) {
			setCard(__getString(val));
		}
		if ((val = values.get("address")) != null) {
			setAddress(__getString(val));
		}
		if ((val = values.get("alternateField1")) != null) {
			setAlternateField1(__getString(val));
		}
		if ((val = values.get("alternateField2")) != null) {
			setAlternateField2(__getString(val));
		}
		if ((val = values.get("alternateField3")) != null) {
			setAlternateField3(__getString(val));
		}
		if ((val = values.get("locked")) != null) {
			setLocked(__getBoolean(val));
		}
		if ((val = values.get("plateId")) != null) {
			setPlateId(__getInt(val));
		}
		if ((val = values.get("dutyId")) != null) {
			setDutyId(__getInt(val));
		}
		if ((val = values.get("userAcct")) != null) {
			setUserAcct(__getString(val));
		}
		if ((val = values.get("employeeNameEn")) != null) {
			setEmployeeNameEn(__getString(val));
		}
		if ((val = values.get("education")) != null) {
			setEducation(__getString(val));
		}
		if ((val = values.get("degree")) != null) {
			setDegree(__getString(val));
		}
		if ((val = values.get("nationality")) != null) {
			setNationality(__getString(val));
		}
		if ((val = values.get("marriedStatus")) != null) {
			setMarriedStatus(__getString(val));
		}
		if ((val = values.get("health")) != null) {
			setHealth(__getString(val));
		}
		if ((val = values.get("workAddress")) != null) {
			setWorkAddress(__getString(val));
		}
		if ((val = values.get("registeredAddress")) != null) {
			setRegisteredAddress(__getString(val));
		}
		if ((val = values.get("oaId")) != null) {
			setOaId(__getInt(val));
		}
		if ((val = values.get("oaDepart")) != null) {
			setOaDepart(__getInt(val));
		}
		if ((val = values.get("isHeadcount")) != null) {
			setIsHeadcount(__getBoolean(val));
		}
		if ((val = values.get("isCheck")) != null) {
			setIsCheck(__getBoolean(val));
		}
		if ((val = values.get("directLeader")) != null) {
			setDirectLeader(__getInt(val));
		}
	}

	protected Integer __employee_id;
	protected String __employee_no;
	protected String __employee_name;
	protected Integer __department_id;
	protected Integer __role_id;
	protected Integer __grade_id;
	protected String __employee_password;
	protected String __mobile;
	protected String __phone;
	protected String __qq;
	protected String __email;
	protected java.util.Date __onboard_date;
	protected java.util.Date __resignation_date;
	protected Integer __status;
	protected Boolean __usable_status;
	protected Boolean __is_department;
	protected String __photo;
	protected Integer __gender;
	protected String __autograph;
	protected Integer __age;
	protected java.util.Date __birth;
	protected String __card;
	protected String __address;
	protected String __alternate_field1;
	protected String __alternate_field2;
	protected String __alternate_field3;
	protected Boolean __locked;
	protected Integer __plate_id;
	protected Integer __duty_id;
	protected String __user_acct;
	protected String __employee_name_en;
	protected String __education;
	protected String __degree;
	protected String __nationality;
	protected String __married_status;
	protected String __health;
	protected String __work_address;
	protected String __registered_address;
	protected Integer __oa_id;
	protected Integer __oa_depart;
	protected Boolean __is_headcount;
	protected Boolean __is_check;
	protected Integer __direct_leader;
}
