package delicacy.system.bean;

import delicacy.common.GenericCondition;
import java.util.Map;

public class ConditionMemployee extends GenericCondition {

    public ConditionMemployee() {
        setParameterCount(16);
    }

    public java.lang.Integer getPlateId() {
        return this.__plate_id;
    }

    public void setPlateId(java.lang.Integer value) {
        this.__plate_id = value;
    }

    public java.lang.String getEmployeeNo() {
        return this.__employee_no == null ? null : (this.__employee_no.contains("%") ? this.__employee_no : "%" + this.__employee_no + "%");
    }

    public void setEmployeeNo(java.lang.String value) {
        this.__employee_no = value;
    }

    public java.lang.String getEmployeeName() {
        return this.__employee_name == null ? null : (this.__employee_name.contains("%") ? this.__employee_name : "%" + this.__employee_name + "%");
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

    public java.lang.Integer getStatus() {
        return this.__status;
    }

    public void setStatus(java.lang.Integer value) {
        this.__status = value;
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

    public java.lang.Integer getDutyId() {
        return this.__duty_id;
    }

    public void setDutyId(java.lang.Integer value) {
        this.__duty_id = value;
    }

    public java.lang.Integer getOnboardYear() {
        return this.__onboard_year;
    }

    public void setOnboardYear(java.lang.Integer value) {
        this.__onboard_year = value;
    }

    public java.lang.Integer getOnboardMonth() {
        return this.__onboard_month;
    }

    public void setOnboardMonth(java.lang.Integer value) {
        this.__onboard_month = value;
    }

    public java.lang.Integer getResiYear() {
        return this.__resi_year;
    }

    public void setResiYear(java.lang.Integer value) {
        this.__resi_year = value;
    }

    public java.lang.Integer getResiMonth() {
        return this.__resi_month;
    }

    public void setResiMonth(java.lang.Integer value) {
        this.__resi_month = value;
    }

    public java.lang.Integer getBirthMonth() {
        return this.__birth_month;
    }

    public void setBirthMonth(java.lang.Integer value) {
        this.__birth_month = value;
    }

    public java.lang.Boolean getIsCheck() {
        return this.__is_check;
    }

    public void setIsCheck(java.lang.Boolean value) {
        this.__is_check = value;
    }

    public java.lang.String getMobile() {
        return this.__mobile;
    }

    public void setMobile(java.lang.String value) {
        this.__mobile = value;
    }

    @Override
    public java.lang.String toJSONString() {

        StringBuilder sb = new StringBuilder();
        sb.append(super.toJSONString());
        if (getPlateId() != null) {
            sb.append(__wrapNumber(1, "plateId", getPlateId()));
        }
        if (getEmployeeNo() != null) {
            sb.append(__wrapString(1, "employeeNo", getEmployeeNo()));
        }
        if (getEmployeeName() != null) {
            sb.append(__wrapString(1, "employeeName", getEmployeeName()));
        }
        if (getDepartmentId() != null) {
            sb.append(__wrapNumber(1, "departmentId", getDepartmentId()));
        }
        if (getRoleId() != null) {
            sb.append(__wrapNumber(1, "roleId", getRoleId()));
        }
        if (getGradeId() != null) {
            sb.append(__wrapNumber(1, "gradeId", getGradeId()));
        }
        if (getStatus() != null) {
            sb.append(__wrapNumber(1, "status", getStatus()));
        }
        if (getIsDepartment() != null) {
            sb.append(__wrapBoolean(1, "isDepartment", getIsDepartment()));
        }
        if (getGender() != null) {
            sb.append(__wrapNumber(1, "gender", getGender()));
        }
        if (getDutyId() != null) {
            sb.append(__wrapNumber(1, "dutyId", getDutyId()));
        }
        if (getOnboardYear() != null) {
            sb.append(__wrapNumber(1, "onboardYear", getOnboardYear()));
        }
        if (getOnboardMonth() != null) {
            sb.append(__wrapNumber(1, "onboardMonth", getOnboardMonth()));
        }
        if (getResiYear() != null) {
            sb.append(__wrapNumber(1, "resiYear", getResiYear()));
        }
        if (getResiMonth() != null) {
            sb.append(__wrapNumber(1, "resiMonth", getResiMonth()));
        }
        if (getBirthMonth() != null) {
            sb.append(__wrapNumber(1, "birthMonth", getBirthMonth()));
        }
        if (getIsCheck() != null) {
            sb.append(__wrapBoolean(1, "isCheck", getIsCheck()));
        }
        if (getMobile() != null) {
            sb.append(__wrapString(1, "mobile", getMobile()));
        }
        return sb.toString();
    }

    @Override
    public void setDataFromMap(Map<String, Object> values) {
        super.setDataFromMap(values);
        Object val;
        if ((val = values.get("plateId")) != null) {
            setPlateId(__getInt(val));
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
        if ((val = values.get("status")) != null) {
            setStatus(__getInt(val));
        }
        if ((val = values.get("isDepartment")) != null) {
            setIsDepartment(__getBoolean(val));
        }
        if ((val = values.get("gender")) != null) {
            setGender(__getInt(val));
        }
        if ((val = values.get("dutyId")) != null) {
            setDutyId(__getInt(val));
        }
        if ((val = values.get("onboardYear")) != null) {
            setOnboardYear(__getInt(val));
        }
        if ((val = values.get("onboardMonth")) != null) {
            setOnboardMonth(__getInt(val));
        }
        if ((val = values.get("resiYear")) != null) {
            setResiYear(__getInt(val));
        }
        if ((val = values.get("resiMonth")) != null) {
            setResiMonth(__getInt(val));
        }
        if ((val = values.get("birthMonth")) != null) {
            setBirthMonth(__getInt(val));
        }
        if ((val = values.get("isCheck")) != null) {
            setIsCheck(__getBoolean(val));
        }
        if ((val = values.get("mobile")) != null) {
            setMobile(__getString(val));
        }
    }

    private java.lang.Integer __plate_id = null;
    private java.lang.String __employee_no = null;
    private java.lang.String __employee_name = null;
    private java.lang.Integer __department_id = null;
    private java.lang.Integer __role_id = null;
    private java.lang.Integer __grade_id = null;
    private java.lang.Integer __status = null;
    private java.lang.Boolean __is_department = null;
    private java.lang.Integer __gender = null;
    private java.lang.Integer __duty_id = null;
    private java.lang.Integer __onboard_year = null;
    private java.lang.Integer __onboard_month = null;
    private java.lang.Integer __resi_year = null;
    private java.lang.Integer __resi_month = null;
    private java.lang.Integer __birth_month = null;
    private java.lang.Boolean __is_check = null;
    private java.lang.String __mobile = null;
}
