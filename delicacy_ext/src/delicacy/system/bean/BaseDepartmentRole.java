package delicacy.system.bean;

import java.util.Map;
import java.util.Objects;
import delicacy.common.GenericBase;
import delicacy.common.BaseFactory;

public class BaseDepartmentRole extends GenericBase implements BaseFactory<BaseDepartmentRole>, Comparable<BaseDepartmentRole> {

    public static BaseDepartmentRole newInstance() {
        return new BaseDepartmentRole();
    }

    @Override
    public BaseDepartmentRole make() {
        BaseDepartmentRole b = new BaseDepartmentRole();
        return b;
    }

    public final static java.lang.String CS_DEPARTMENT_ROLE_ID = "department_role_id";
    public final static java.lang.String CS_DEPARTMENT_ID = "department_id";
    public final static java.lang.String CS_ROLE_ID = "role_id";

    public final static java.lang.String ALL_CAPTIONS = "部门角色代码,部门,角色";

    public java.lang.Integer getDepartmentRoleId() {
        return this.__department_role_id;
    }

    public void setDepartmentRoleId(java.lang.Integer value) {
        this.__department_role_id = value;
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

    public void cloneCopy(BaseDepartmentRole __bean) {
        __bean.setDepartmentRoleId(getDepartmentRoleId());
        __bean.setDepartmentId(getDepartmentId());
        __bean.setRoleId(getRoleId());
    }

    public java.lang.String toCSVString() {

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
        StringBuilder sb = new StringBuilder();
        sb.append(getDepartmentRoleId() == null ? "" : getDepartmentRoleId());
        sb.append(",");
        sb.append(getDepartmentId() == null ? "" : getDepartmentId());
        sb.append(",");
        sb.append(getRoleId() == null ? "" : getRoleId());
        return sb.toString();
    }

    @Override
    public int compareTo(BaseDepartmentRole o) {
        return __department_role_id == null ? -1 : __department_role_id.compareTo(o.getDepartmentRoleId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.__department_role_id);
        hash = 97 * hash + Objects.hashCode(this.__department_id);
        hash = 97 * hash + Objects.hashCode(this.__role_id);
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
        final BaseDepartmentRole o = (BaseDepartmentRole) obj;
        if (!Objects.equals(this.__department_role_id, o.getDepartmentRoleId())) {
            return false;
        }
        if (!Objects.equals(this.__department_id, o.getDepartmentId())) {
            return false;
        }
        if (!Objects.equals(this.__role_id, o.getRoleId())) {
            return false;
        }
        return true;
    }

    @Override
    public java.lang.String toJSONString() {

        StringBuilder sb = new StringBuilder();
        int count = 0;
        if (getDepartmentRoleId() != null) {
            sb.append(__wrapNumber(count++, "departmentRoleId", getDepartmentRoleId()));
        }
        if (getDepartmentId() != null) {
            sb.append(__wrapNumber(count++, "departmentId", getDepartmentId()));
        }
        if (getRoleId() != null) {
            sb.append(__wrapNumber(count++, "roleId", getRoleId()));
        }
        return sb.toString();
    }

    @Override
    public void setDataFromMap(Map<String, Object> values) {
        Object val;
        if ((val = values.get("departmentRoleId")) != null) {
            setDepartmentRoleId(__getInt(val));
        }
        if ((val = values.get("departmentId")) != null) {
            setDepartmentId(__getInt(val));
        }
        if ((val = values.get("roleId")) != null) {
            setRoleId(__getInt(val));
        }
    }

    protected java.lang.Integer __department_role_id;
    protected java.lang.Integer __department_id;
    protected java.lang.Integer __role_id;
}
