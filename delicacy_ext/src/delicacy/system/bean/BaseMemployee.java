package delicacy.system.bean;

import java.util.Map;
import java.util.Objects;
import delicacy.common.GenericBase;
import delicacy.common.BaseFactory;

public class BaseMemployee extends GenericBase implements BaseFactory<BaseMemployee>, Comparable<BaseMemployee> {

    public static BaseMemployee newInstance() {
        return new BaseMemployee();
    }

    @Override
    public BaseMemployee make() {
        BaseMemployee b = new BaseMemployee();
        return b;
    }

    public final static java.lang.String CS_EMPLOYEE_ID = "employee_id";
    public final static java.lang.String CS_PHOTO = "photo";
    public final static java.lang.String CS_EMPLOYEE_NO = "employee_no";
    public final static java.lang.String CS_EMPLOYEE_NAME = "employee_name";
    public final static java.lang.String CS_DEPARTMENT_ID = "department_id";
    public final static java.lang.String CS_ROLE_ID = "role_id";
    public final static java.lang.String CS_GRADE_ID = "grade_id";
    public final static java.lang.String CS_EMPLOYEE_PASSWORD = "employee_password";
    public final static java.lang.String CS_MOBILE = "mobile";
    public final static java.lang.String CS_PHONE = "phone";
    public final static java.lang.String CS_QQ = "qq";
    public final static java.lang.String CS_EMAIL = "email";
    public final static java.lang.String CS_ONBOARD_DATE = "onboard_date";
    public final static java.lang.String CS_RESIGNATION_DATE = "resignation_date";
    public final static java.lang.String CS_STATUS = "status";
    public final static java.lang.String CS_USABLE_STATUS = "usable_status";
    public final static java.lang.String CS_IS_DEPARTMENT = "is_department";
    public final static java.lang.String CS_GENDER = "gender";
    public final static java.lang.String CS_AUTOGRAPH = "autograph";
    public final static java.lang.String CS_AGE = "age";
    public final static java.lang.String CS_BIRTH = "birth";
    public final static java.lang.String CS_CARD = "card";
    public final static java.lang.String CS_ADDRESS = "address";
    public final static java.lang.String CS_LOCKED = "locked";
    public final static java.lang.String CS_PLATE_ID = "plate_id";
    public final static java.lang.String CS_DUTY_ID = "duty_id";
    public final static java.lang.String CS_USER_ACCT = "user_acct";
    public final static java.lang.String CS_EMPLOYEE_NAME_EN = "employee_name_en";
    public final static java.lang.String CS_EDUCATION = "education";
    public final static java.lang.String CS_DEGREE = "degree";
    public final static java.lang.String CS_NATIONALITY = "nationality";
    public final static java.lang.String CS_MARRIED_STATUS = "married_status";
    public final static java.lang.String CS_HEALTH = "health";
    public final static java.lang.String CS_WORK_ADDRESS = "work_address";
    public final static java.lang.String CS_REGISTERED_ADDRESS = "registered_address";
    public final static java.lang.String CS_EMPLOYEE_ROLE_NAMES = "employee_role_names";

    public final static java.lang.String ALL_CAPTIONS = "员工编码,头像,员工编号,员工姓名,部门,角色编码,等级,密码,手机,电话,QQ,邮箱,入职日期,离职日期,状态,是否可用,是否为部门负责人,性别,我的签名,age,出生日期,身份证号,家庭地址,是否锁定,板块,职位名称,上级领导,英文名,学历,学位,名族,婚姻状况,身体状况,工作地,注册地,角色";
    public final static java.lang.String CUSTOM_CAPTIONS = "员工编号,员工姓名,上级领导,业务部门,所属部门,职位名称,职级,状态,性别";

    public java.lang.Integer getEmployeeId() {
        return this.__employee_id;
    }

    public void setEmployeeId(java.lang.Integer value) {
        this.__employee_id = value;
    }

    public java.lang.String getPhoto() {
        return this.__photo;
    }

    public void setPhoto(java.lang.String value) {
        this.__photo = value;
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

    public java.lang.Integer getDepartmentId() {
        return this.__department_id;
    }

    public void setDepartmentId(java.lang.Integer value) {
        this.__department_id = value;
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

    public void setGradeId(java.lang.Integer value) {
        this.__grade_id = value;
    }

    public java.lang.String getEmployeePassword() {
        return this.__employee_password;
    }

    public void setEmployeePassword(java.lang.String value) {
        this.__employee_password = value;
    }

    public java.lang.String getMobile() {
        return this.__mobile;
    }

    public void setMobile(java.lang.String value) {
        this.__mobile = value;
    }

    public java.lang.String getPhone() {
        return this.__phone;
    }

    public void setPhone(java.lang.String value) {
        this.__phone = value;
    }

    public java.lang.String getQq() {
        return this.__qq;
    }

    public void setQq(java.lang.String value) {
        this.__qq = value;
    }

    public java.lang.String getEmail() {
        return this.__email;
    }

    public void setEmail(java.lang.String value) {
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

    public java.lang.Integer getStatus() {
        return this.__status;
    }

    public void setStatus(java.lang.Integer value) {
        this.__status = value;
    }

    public java.lang.Boolean getUsableStatus() {
        return this.__usable_status;
    }

    public void setUsableStatus(java.lang.Boolean value) {
        this.__usable_status = value;
    }

    public java.lang.Boolean getIsDepartment() {
        return this.__is_department;
    }

    public void setIsDepartment(java.lang.Boolean value) {
        this.__is_department = value;
    }

    public java.lang.Integer getGender() {
        return this.__gender;
    }

    public void setGender(java.lang.Integer value) {
        this.__gender = value;
    }

    public java.lang.String getAutograph() {
        return this.__autograph;
    }

    public void setAutograph(java.lang.String value) {
        this.__autograph = value;
    }

    public java.lang.Integer getAge() {
        return this.__age;
    }

    public void setAge(java.lang.Integer value) {
        this.__age = value;
    }

    public java.util.Date getBirth() {
        return this.__birth;
    }

    public void setBirth(java.util.Date value) {
        this.__birth = value;
    }

    public java.lang.String getCard() {
        return this.__card;
    }

    public void setCard(java.lang.String value) {
        this.__card = value;
    }

    public java.lang.String getAddress() {
        return this.__address;
    }

    public void setAddress(java.lang.String value) {
        this.__address = value;
    }

    public java.lang.Boolean getLocked() {
        return this.__locked;
    }

    public void setLocked(java.lang.Boolean value) {
        this.__locked = value;
    }

    public java.lang.Integer getPlateId() {
        return this.__plate_id;
    }

    public void setPlateId(java.lang.Integer value) {
        this.__plate_id = value;
    }

    public java.lang.Integer getDutyId() {
        return this.__duty_id;
    }

    public void setDutyId(java.lang.Integer value) {
        this.__duty_id = value;
    }

    public java.lang.String getUserAcct() {
        return this.__user_acct;
    }

    public void setUserAcct(java.lang.String value) {
        this.__user_acct = value;
    }

    public java.lang.String getEmployeeNameEn() {
        return this.__employee_name_en;
    }

    public void setEmployeeNameEn(java.lang.String value) {
        this.__employee_name_en = value;
    }

    public java.lang.String getEducation() {
        return this.__education;
    }

    public void setEducation(java.lang.String value) {
        this.__education = value;
    }

    public java.lang.String getDegree() {
        return this.__degree;
    }

    public void setDegree(java.lang.String value) {
        this.__degree = value;
    }

    public java.lang.String getNationality() {
        return this.__nationality;
    }

    public void setNationality(java.lang.String value) {
        this.__nationality = value;
    }

    public java.lang.String getMarriedStatus() {
        return this.__married_status;
    }

    public void setMarriedStatus(java.lang.String value) {
        this.__married_status = value;
    }

    public java.lang.String getHealth() {
        return this.__health;
    }

    public void setHealth(java.lang.String value) {
        this.__health = value;
    }

    public java.lang.String getWorkAddress() {
        return this.__work_address;
    }

    public void setWorkAddress(java.lang.String value) {
        this.__work_address = value;
    }

    public java.lang.String getRegisteredAddress() {
        return this.__registered_address;
    }

    public void setRegisteredAddress(java.lang.String value) {
        this.__registered_address = value;
    }

    public java.lang.String getEmployeeRoleNames() {
        return this.__employee_role_names;
    }

    public void setEmployeeRoleNames(java.lang.String value) {
        this.__employee_role_names = value;
    }

    public java.util.List<BaseEmployeeRole> getDetailEmployeeRole() {
        return this.__detailEmployeeRole;
    }

    public void setDetailEmployeeRole(java.util.List<BaseEmployeeRole> value) {
        this.__detailEmployeeRole = value;
    }

    public void cloneCopy(BaseMemployee __bean) {
        __bean.setEmployeeId(getEmployeeId());
        __bean.setPhoto(getPhoto());
        __bean.setEmployeeNo(getEmployeeNo());
        __bean.setEmployeeName(getEmployeeName());
        __bean.setDepartmentId(getDepartmentId());
        __bean.setRoleId(getRoleId());
        __bean.setGradeId(getGradeId());
        __bean.setEmployeePassword(getEmployeePassword());
        __bean.setMobile(getMobile());
        __bean.setPhone(getPhone());
        __bean.setQq(getQq());
        __bean.setEmail(getEmail());
        __bean.setOnboardDate(getOnboardDate());
        __bean.setResignationDate(getResignationDate());
        __bean.setStatus(getStatus());
        __bean.setUsableStatus(getUsableStatus());
        __bean.setIsDepartment(getIsDepartment());
        __bean.setGender(getGender());
        __bean.setAutograph(getAutograph());
        __bean.setAge(getAge());
        __bean.setBirth(getBirth());
        __bean.setCard(getCard());
        __bean.setAddress(getAddress());
        __bean.setLocked(getLocked());
        __bean.setPlateId(getPlateId());
        __bean.setDutyId(getDutyId());
        __bean.setUserAcct(getUserAcct());
        __bean.setEmployeeNameEn(getEmployeeNameEn());
        __bean.setEducation(getEducation());
        __bean.setDegree(getDegree());
        __bean.setNationality(getNationality());
        __bean.setMarriedStatus(getMarriedStatus());
        __bean.setHealth(getHealth());
        __bean.setWorkAddress(getWorkAddress());
        __bean.setRegisteredAddress(getRegisteredAddress());
        __bean.setEmployeeRoleNames(getEmployeeRoleNames());
    }

    public java.lang.String toCSVString() {

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
        StringBuilder sb = new StringBuilder();
        sb.append(getEmployeeNo() == null ? "" : getEmployeeNo());
        sb.append(",");
        sb.append(getEmployeeName() == null ? "" : getEmployeeName());
        sb.append(",");
        String strUserAcct = delicacy.system.executor.SelectValueCache.getSelectValue("employee_names", String.valueOf(getUserAcct()));
        sb.append(strUserAcct == null ? "" : strUserAcct);
        sb.append(",");
        String strPlateId = delicacy.system.executor.SelectValueCache.getSelectValue("plate_records", String.valueOf(getPlateId()));
        sb.append(strPlateId == null ? "" : strPlateId);
        sb.append(",");
        String strDepartmentId = delicacy.system.executor.SelectValueCache.getSelectValue("departments", String.valueOf(getDepartmentId()));
        sb.append(strDepartmentId == null ? "" : strDepartmentId);
        sb.append(",");
        String strDuty = delicacy.system.executor.SelectValueCache.getSelectValue("duties", String.valueOf(getDutyId()));
        sb.append(strDuty == null ? "" : strDuty);
        sb.append(",");
        String strGradeId = delicacy.system.executor.SelectValueCache.getSelectValue("system_dictionary_3", String.valueOf(getGradeId()));
        sb.append(strGradeId == null ? "" : strGradeId);
        sb.append(",");
        String strStatus = delicacy.system.executor.SelectValueCache.getSelectValue("system_dictionary_11", String.valueOf(getStatus()));
        sb.append(strStatus == null ? "" : strStatus);
        sb.append(",");
        String strGender = delicacy.system.executor.SelectValueCache.getSelectValue("system_dictionary_12", String.valueOf(getGender()));
        sb.append(strGender == null ? "" : strGender);
        return sb.toString();
    }

    @Override
    public int compareTo(BaseMemployee o) {
        return __employee_id == null ? -1 : __employee_id.compareTo(o.getEmployeeId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.__employee_id);
        hash = 97 * hash + Objects.hashCode(this.__photo);
        hash = 97 * hash + Objects.hashCode(this.__employee_no);
        hash = 97 * hash + Objects.hashCode(this.__employee_name);
        hash = 97 * hash + Objects.hashCode(this.__department_id);
        hash = 97 * hash + Objects.hashCode(this.__role_id);
        hash = 97 * hash + Objects.hashCode(this.__grade_id);
        hash = 97 * hash + Objects.hashCode(this.__employee_password);
        hash = 97 * hash + Objects.hashCode(this.__mobile);
        hash = 97 * hash + Objects.hashCode(this.__phone);
        hash = 97 * hash + Objects.hashCode(this.__qq);
        hash = 97 * hash + Objects.hashCode(this.__email);
        hash = 97 * hash + Objects.hashCode(this.__onboard_date);
        hash = 97 * hash + Objects.hashCode(this.__resignation_date);
        hash = 97 * hash + Objects.hashCode(this.__status);
        hash = 97 * hash + Objects.hashCode(this.__usable_status);
        hash = 97 * hash + Objects.hashCode(this.__is_department);
        hash = 97 * hash + Objects.hashCode(this.__gender);
        hash = 97 * hash + Objects.hashCode(this.__autograph);
        hash = 97 * hash + Objects.hashCode(this.__age);
        hash = 97 * hash + Objects.hashCode(this.__birth);
        hash = 97 * hash + Objects.hashCode(this.__card);
        hash = 97 * hash + Objects.hashCode(this.__address);
        hash = 97 * hash + Objects.hashCode(this.__locked);
        hash = 97 * hash + Objects.hashCode(this.__plate_id);
        hash = 97 * hash + Objects.hashCode(this.__duty_id);
        hash = 97 * hash + Objects.hashCode(this.__user_acct);
        hash = 97 * hash + Objects.hashCode(this.__employee_name_en);
        hash = 97 * hash + Objects.hashCode(this.__education);
        hash = 97 * hash + Objects.hashCode(this.__degree);
        hash = 97 * hash + Objects.hashCode(this.__nationality);
        hash = 97 * hash + Objects.hashCode(this.__married_status);
        hash = 97 * hash + Objects.hashCode(this.__health);
        hash = 97 * hash + Objects.hashCode(this.__work_address);
        hash = 97 * hash + Objects.hashCode(this.__registered_address);
        hash = 97 * hash + Objects.hashCode(this.__employee_role_names);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BaseMemployee o = (BaseMemployee) obj;
        if (!Objects.equals(this.__employee_id, o.getEmployeeId())) {
            return false;
        }
        if (!Objects.equals(this.__photo, o.getPhoto())) {
            return false;
        }
        if (!Objects.equals(this.__employee_no, o.getEmployeeNo())) {
            return false;
        }
        if (!Objects.equals(this.__employee_name, o.getEmployeeName())) {
            return false;
        }
        if (!Objects.equals(this.__department_id, o.getDepartmentId())) {
            return false;
        }
        if (!Objects.equals(this.__role_id, o.getRoleId())) {
            return false;
        }
        if (!Objects.equals(this.__grade_id, o.getGradeId())) {
            return false;
        }
        if (!Objects.equals(this.__employee_password, o.getEmployeePassword())) {
            return false;
        }
        if (!Objects.equals(this.__mobile, o.getMobile())) {
            return false;
        }
        if (!Objects.equals(this.__phone, o.getPhone())) {
            return false;
        }
        if (!Objects.equals(this.__qq, o.getQq())) {
            return false;
        }
        if (!Objects.equals(this.__email, o.getEmail())) {
            return false;
        }
        if (!Objects.equals(this.__onboard_date, o.getOnboardDate())) {
            return false;
        }
        if (!Objects.equals(this.__resignation_date, o.getResignationDate())) {
            return false;
        }
        if (!Objects.equals(this.__status, o.getStatus())) {
            return false;
        }
        if (!Objects.equals(this.__usable_status, o.getUsableStatus())) {
            return false;
        }
        if (!Objects.equals(this.__is_department, o.getIsDepartment())) {
            return false;
        }
        if (!Objects.equals(this.__gender, o.getGender())) {
            return false;
        }
        if (!Objects.equals(this.__autograph, o.getAutograph())) {
            return false;
        }
        if (!Objects.equals(this.__age, o.getAge())) {
            return false;
        }
        if (!Objects.equals(this.__birth, o.getBirth())) {
            return false;
        }
        if (!Objects.equals(this.__card, o.getCard())) {
            return false;
        }
        if (!Objects.equals(this.__address, o.getAddress())) {
            return false;
        }
        if (!Objects.equals(this.__locked, o.getLocked())) {
            return false;
        }
        if (!Objects.equals(this.__plate_id, o.getPlateId())) {
            return false;
        }
        if (!Objects.equals(this.__duty_id, o.getDutyId())) {
            return false;
        }
        if (!Objects.equals(this.__user_acct, o.getUserAcct())) {
            return false;
        }
        if (!Objects.equals(this.__employee_name_en, o.getEmployeeNameEn())) {
            return false;
        }
        if (!Objects.equals(this.__education, o.getEducation())) {
            return false;
        }
        if (!Objects.equals(this.__degree, o.getDegree())) {
            return false;
        }
        if (!Objects.equals(this.__nationality, o.getNationality())) {
            return false;
        }
        if (!Objects.equals(this.__married_status, o.getMarriedStatus())) {
            return false;
        }
        if (!Objects.equals(this.__health, o.getHealth())) {
            return false;
        }
        if (!Objects.equals(this.__work_address, o.getWorkAddress())) {
            return false;
        }
        if (!Objects.equals(this.__registered_address, o.getRegisteredAddress())) {
            return false;
        }
        if (!Objects.equals(this.__employee_role_names, o.getEmployeeRoleNames())) {
            return false;
        }
        return true;
    }

    @Override
    public java.lang.String toJSONString() {

        StringBuilder sb = new StringBuilder();
        int count = 0;
        if (getEmployeeId() != null) {
            sb.append(__wrapNumber(count++, "employeeId", getEmployeeId()));
        }
        if (getPhoto() != null) {
            sb.append(__wrapString(count++, "photo", getPhoto()));
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
        if (getEmployeeRoleNames() != null) {
            sb.append(__wrapString(count++, "employeeRoleNames", getEmployeeRoleNames()));
        }
        if (getDetailEmployeeRole() != null) {
            sb.append(__wrapList(count++, "detailEmployeeRole", getDetailEmployeeRole()));
        }
        return sb.toString();
    }

    @Override
    public void setDataFromMap(Map<String, Object> values) {
        Object val;
        if ((val = values.get("employeeId")) != null) {
            setEmployeeId(__getInt(val));
        }
        if ((val = values.get("photo")) != null) {
            setPhoto(__getString(val));
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
        if ((val = values.get("employeeRoleNames")) != null) {
            setEmployeeRoleNames(__getString(val));
        }
        if ((val = values.get("detailEmployeeRole")) != null) {
            setDetailEmployeeRole(__getList(val, BaseEmployeeRole.newInstance()));
        }
    }

    protected java.lang.Integer __employee_id;
    protected java.lang.String __photo;
    protected java.lang.String __employee_no;
    protected java.lang.String __employee_name;
    protected java.lang.Integer __department_id;
    protected java.lang.Integer __role_id;
    protected java.lang.Integer __grade_id;
    protected java.lang.String __employee_password;
    protected java.lang.String __mobile;
    protected java.lang.String __phone;
    protected java.lang.String __qq;
    protected java.lang.String __email;
    protected java.util.Date __onboard_date;
    protected java.util.Date __resignation_date;
    protected java.lang.Integer __status;
    protected java.lang.Boolean __usable_status;
    protected java.lang.Boolean __is_department;
    protected java.lang.Integer __gender;
    protected java.lang.String __autograph;
    protected java.lang.Integer __age;
    protected java.util.Date __birth;
    protected java.lang.String __card;
    protected java.lang.String __address;
    protected java.lang.Boolean __locked;
    protected java.lang.Integer __plate_id;
    protected java.lang.Integer __duty_id;
    protected java.lang.String __user_acct;
    protected java.lang.String __employee_name_en;
    protected java.lang.String __education;
    protected java.lang.String __degree;
    protected java.lang.String __nationality;
    protected java.lang.String __married_status;
    protected java.lang.String __health;
    protected java.lang.String __work_address;
    protected java.lang.String __registered_address;
    protected java.lang.String __employee_role_names;
    protected java.util.List<BaseEmployeeRole> __detailEmployeeRole = null;
}
