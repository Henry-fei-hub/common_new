package delicacy.system.bean;

import java.util.Map;
import java.util.Objects;
import delicacy.common.GenericBase;
import delicacy.common.BaseFactory;

public class BaseDepartmentRoleFunction extends GenericBase implements BaseFactory<BaseDepartmentRoleFunction>, Comparable<BaseDepartmentRoleFunction> {

    public static BaseDepartmentRoleFunction newInstance() {
        return new BaseDepartmentRoleFunction();
    }

    @Override
    public BaseDepartmentRoleFunction make() {
        BaseDepartmentRoleFunction b = new BaseDepartmentRoleFunction();
        return b;
    }

    public final static java.lang.String CS_DEPARTMENT_ROLE_FUNCTION_ID = "department_role_function_id";
    public final static java.lang.String CS_FUNCTION_ID = "function_id";
    public final static java.lang.String CS_APPLICATION_ID = "application_id";
    public final static java.lang.String CS_ROLE_ID = "role_id";
    public final static java.lang.String CS_DEPARTMENT_ID = "department_id";

    public final static java.lang.String ALL_CAPTIONS = "部门角色功能编码id,功能编码,应用系统id,角色编码,部门编码";

    public java.lang.Integer getDepartmentRoleFunctionId() {
        return this.__department_role_function_id;
    }

    public void setDepartmentRoleFunctionId(java.lang.Integer value) {
        this.__department_role_function_id = value;
    }

    public java.lang.Integer getFunctionId() {
        return this.__function_id;
    }

    public void setFunctionId(java.lang.Integer value) {
        this.__function_id = value;
    }

    public java.lang.Integer getApplicationId() {
        return this.__application_id;
    }

    public void setApplicationId(java.lang.Integer value) {
        this.__application_id = value;
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

    public void cloneCopy(BaseDepartmentRoleFunction __bean) {
        __bean.setDepartmentRoleFunctionId(getDepartmentRoleFunctionId());
        __bean.setFunctionId(getFunctionId());
        __bean.setApplicationId(getApplicationId());
        __bean.setRoleId(getRoleId());
        __bean.setDepartmentId(getDepartmentId());
    }

    public java.lang.String toCSVString() {

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
        StringBuilder sb = new StringBuilder();
        sb.append(getDepartmentRoleFunctionId() == null ? "" : getDepartmentRoleFunctionId());
        sb.append(",");
        sb.append(getFunctionId() == null ? "" : getFunctionId());
        sb.append(",");
        sb.append(getApplicationId() == null ? "" : getApplicationId());
        sb.append(",");
        sb.append(getRoleId() == null ? "" : getRoleId());
        sb.append(",");
        sb.append(getDepartmentId() == null ? "" : getDepartmentId());
        return sb.toString();
    }

    @Override
    public int compareTo(BaseDepartmentRoleFunction o) {
        return __department_role_function_id == null ? -1 : __department_role_function_id.compareTo(o.getDepartmentRoleFunctionId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.__department_role_function_id);
        hash = 97 * hash + Objects.hashCode(this.__function_id);
        hash = 97 * hash + Objects.hashCode(this.__application_id);
        hash = 97 * hash + Objects.hashCode(this.__role_id);
        hash = 97 * hash + Objects.hashCode(this.__department_id);
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
        final BaseDepartmentRoleFunction o = (BaseDepartmentRoleFunction) obj;
        if (!Objects.equals(this.__department_role_function_id, o.getDepartmentRoleFunctionId())) {
            return false;
        }
        if (!Objects.equals(this.__function_id, o.getFunctionId())) {
            return false;
        }
        if (!Objects.equals(this.__application_id, o.getApplicationId())) {
            return false;
        }
        if (!Objects.equals(this.__role_id, o.getRoleId())) {
            return false;
        }
        if (!Objects.equals(this.__department_id, o.getDepartmentId())) {
            return false;
        }
        return true;
    }

    @Override
    public java.lang.String toJSONString() {

        StringBuilder sb = new StringBuilder();
        int count = 0;
        if (getDepartmentRoleFunctionId() != null) {
            sb.append(__wrapNumber(count++, "departmentRoleFunctionId", getDepartmentRoleFunctionId()));
        }
        if (getFunctionId() != null) {
            sb.append(__wrapNumber(count++, "functionId", getFunctionId()));
        }
        if (getApplicationId() != null) {
            sb.append(__wrapNumber(count++, "applicationId", getApplicationId()));
        }
        if (getRoleId() != null) {
            sb.append(__wrapNumber(count++, "roleId", getRoleId()));
        }
        if (getDepartmentId() != null) {
            sb.append(__wrapNumber(count++, "departmentId", getDepartmentId()));
        }
        return sb.toString();
    }

    @Override
    public void setDataFromMap(Map<String, Object> values) {
        Object val;
        if ((val = values.get("departmentRoleFunctionId")) != null) {
            setDepartmentRoleFunctionId(__getInt(val));
        }
        if ((val = values.get("functionId")) != null) {
            setFunctionId(__getInt(val));
        }
        if ((val = values.get("applicationId")) != null) {
            setApplicationId(__getInt(val));
        }
        if ((val = values.get("roleId")) != null) {
            setRoleId(__getInt(val));
        }
        if ((val = values.get("departmentId")) != null) {
            setDepartmentId(__getInt(val));
        }
    }

    protected java.lang.Integer __department_role_function_id;
    protected java.lang.Integer __function_id;
    protected java.lang.Integer __application_id;
    protected java.lang.Integer __role_id;
    protected java.lang.Integer __department_id;
}
