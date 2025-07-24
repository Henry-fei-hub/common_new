package delicacy.system.bean;

import java.util.Map;
import java.util.Objects;
import delicacy.common.GenericBase;
import delicacy.common.BaseFactory;

public class BaseEmployeeRole extends GenericBase implements BaseFactory<BaseEmployeeRole>, Comparable<BaseEmployeeRole> {

    public static BaseEmployeeRole newInstance() {
        return new BaseEmployeeRole();
    }

    @Override
    public BaseEmployeeRole make() {
        BaseEmployeeRole b = new BaseEmployeeRole();
        return b;
    }

    public final static java.lang.String CS_EMPLOYEE_ROLE_ID = "employee_role_id";
    public final static java.lang.String CS_EMPLOYEE_ID = "employee_id";
    public final static java.lang.String CS_ROLE_ID = "role_id";
    public final static java.lang.String CS_DEPARTMENT_ID = "department_id";
    public final static java.lang.String CS_IS_DEFAULT = "is_default";

    public final static java.lang.String ALL_CAPTIONS = "员工角色编码,员工编码,角色编码,部门,是默认角色";

    public java.lang.Integer getEmployeeRoleId() {
        return this.__employee_role_id;
    }

    public void setEmployeeRoleId(java.lang.Integer value) {
        this.__employee_role_id = value;
    }

    public java.lang.Integer getEmployeeId() {
        return this.__employee_id;
    }

    public void setEmployeeId(java.lang.Integer value) {
        this.__employee_id = value;
    }

    public java.lang.Integer getRoleId() {
        return this.__role_id;
    }

    public void setRoleId(java.lang.Integer value) {
        this.__role_id = value;
    }

    public java.lang.Integer getDepartmentId() {
        return this.__department_id;
    }

    public void setDepartmentId(java.lang.Integer value) {
        this.__department_id = value;
    }

    public java.lang.Boolean getIsDefault() {
        return this.__is_default;
    }

    public void setIsDefault(java.lang.Boolean value) {
        this.__is_default = value;
    }

    public void cloneCopy(BaseEmployeeRole __bean) {
        __bean.setEmployeeRoleId(getEmployeeRoleId());
        __bean.setEmployeeId(getEmployeeId());
        __bean.setRoleId(getRoleId());
        __bean.setDepartmentId(getDepartmentId());
        __bean.setIsDefault(getIsDefault());
    }

    public java.lang.String toCSVString() {

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
        StringBuilder sb = new StringBuilder();
        sb.append(getEmployeeRoleId() == null ? "" : getEmployeeRoleId());
        sb.append(",");
        sb.append(getEmployeeId() == null ? "" : getEmployeeId());
        sb.append(",");
        sb.append(getRoleId() == null ? "" : getRoleId());
        sb.append(",");
        sb.append(getDepartmentId() == null ? "" : getDepartmentId());
        sb.append(",");
        sb.append(getIsDefault() == null ? "" : getIsDefault());
        return sb.toString();
    }

    @Override
    public int compareTo(BaseEmployeeRole o) {
        return __employee_role_id == null ? -1 : __employee_role_id.compareTo(o.getEmployeeRoleId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.__employee_role_id);
        hash = 97 * hash + Objects.hashCode(this.__employee_id);
        hash = 97 * hash + Objects.hashCode(this.__role_id);
        hash = 97 * hash + Objects.hashCode(this.__department_id);
        hash = 97 * hash + Objects.hashCode(this.__is_default);
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
        final BaseEmployeeRole o = (BaseEmployeeRole) obj;
        if (!Objects.equals(this.__employee_role_id, o.getEmployeeRoleId())) {
            return false;
        }
        if (!Objects.equals(this.__employee_id, o.getEmployeeId())) {
            return false;
        }
        if (!Objects.equals(this.__role_id, o.getRoleId())) {
            return false;
        }
        if (!Objects.equals(this.__department_id, o.getDepartmentId())) {
            return false;
        }
        if (!Objects.equals(this.__is_default, o.getIsDefault())) {
            return false;
        }
        return true;
    }

    @Override
    public java.lang.String toJSONString() {

        StringBuilder sb = new StringBuilder();
        int count = 0;
        if (getEmployeeRoleId() != null) {
            sb.append(__wrapNumber(count++, "employeeRoleId", getEmployeeRoleId()));
        }
        if (getEmployeeId() != null) {
            sb.append(__wrapNumber(count++, "employeeId", getEmployeeId()));
        }
        if (getRoleId() != null) {
            sb.append(__wrapNumber(count++, "roleId", getRoleId()));
        }
        if (getDepartmentId() != null) {
            sb.append(__wrapNumber(count++, "departmentId", getDepartmentId()));
        }
        if (getIsDefault() != null) {
            sb.append(__wrapBoolean(count++, "isDefault", getIsDefault()));
        }
        return sb.toString();
    }

    @Override
    public void setDataFromMap(Map<String, Object> values) {
        Object val;
        if ((val = values.get("employeeRoleId")) != null) {
            setEmployeeRoleId(__getInt(val));
        }
        if ((val = values.get("employeeId")) != null) {
            setEmployeeId(__getInt(val));
        }
        if ((val = values.get("roleId")) != null) {
            setRoleId(__getInt(val));
        }
        if ((val = values.get("departmentId")) != null) {
            setDepartmentId(__getInt(val));
        }
        if ((val = values.get("isDefault")) != null) {
            setIsDefault(__getBoolean(val));
        }
    }

    protected java.lang.Integer __employee_role_id;
    protected java.lang.Integer __employee_id;
    protected java.lang.Integer __role_id;
    protected java.lang.Integer __department_id;
    protected java.lang.Boolean __is_default;
}
