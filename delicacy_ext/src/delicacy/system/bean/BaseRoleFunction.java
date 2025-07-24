package delicacy.system.bean;

import java.util.Map;
import java.util.Objects;
import delicacy.common.GenericBase;
import delicacy.common.BaseFactory;

public class BaseRoleFunction extends GenericBase implements BaseFactory<BaseRoleFunction>, Comparable<BaseRoleFunction> {

    public static BaseRoleFunction newInstance() {
        return new BaseRoleFunction();
    }

    @Override
    public BaseRoleFunction make() {
        BaseRoleFunction b = new BaseRoleFunction();
        return b;
    }

    public final static java.lang.String CS_ROLE_FUNCTION_ID = "role_function_id";
    public final static java.lang.String CS_ROLE_ID = "role_id";
    public final static java.lang.String CS_FUNCTION_ID = "function_id";
    public final static java.lang.String CS_DEPARTMENT_ID = "department_id";
    public final static java.lang.String CS_APPLICATION_ID = "application_id";

    public final static java.lang.String ALL_CAPTIONS = "角色功能编码,角色编码,功能编码,部门,应用系统";

    public java.lang.Integer getRoleFunctionId() {
        return this.__role_function_id;
    }

    public void setRoleFunctionId(java.lang.Integer value) {
        this.__role_function_id = value;
    }

    public java.lang.Integer getRoleId() {
        return this.__role_id;
    }

    public void setRoleId(java.lang.Integer value) {
        this.__role_id = value;
    }

    public java.lang.Integer getFunctionId() {
        return this.__function_id;
    }

    public void setFunctionId(java.lang.Integer value) {
        this.__function_id = value;
    }

    public java.lang.Integer getDepartmentId() {
        return this.__department_id;
    }

    public void setDepartmentId(java.lang.Integer value) {
        this.__department_id = value;
    }

    public java.lang.Integer getApplicationId() {
        return this.__application_id;
    }

    public void setApplicationId(java.lang.Integer value) {
        this.__application_id = value;
    }

    public void cloneCopy(BaseRoleFunction __bean) {
        __bean.setRoleFunctionId(getRoleFunctionId());
        __bean.setRoleId(getRoleId());
        __bean.setFunctionId(getFunctionId());
        __bean.setDepartmentId(getDepartmentId());
        __bean.setApplicationId(getApplicationId());
    }

    public java.lang.String toCSVString() {

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
        StringBuilder sb = new StringBuilder();
        sb.append(getRoleFunctionId() == null ? "" : getRoleFunctionId());
        sb.append(",");
        sb.append(getRoleId() == null ? "" : getRoleId());
        sb.append(",");
        sb.append(getFunctionId() == null ? "" : getFunctionId());
        sb.append(",");
        sb.append(getDepartmentId() == null ? "" : getDepartmentId());
        sb.append(",");
        sb.append(getApplicationId() == null ? "" : getApplicationId());
        return sb.toString();
    }

    @Override
    public int compareTo(BaseRoleFunction o) {
        return __role_function_id == null ? -1 : __role_function_id.compareTo(o.getRoleFunctionId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.__role_function_id);
        hash = 97 * hash + Objects.hashCode(this.__role_id);
        hash = 97 * hash + Objects.hashCode(this.__function_id);
        hash = 97 * hash + Objects.hashCode(this.__department_id);
        hash = 97 * hash + Objects.hashCode(this.__application_id);
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
        final BaseRoleFunction o = (BaseRoleFunction) obj;
        if (!Objects.equals(this.__role_function_id, o.getRoleFunctionId())) {
            return false;
        }
        if (!Objects.equals(this.__role_id, o.getRoleId())) {
            return false;
        }
        if (!Objects.equals(this.__function_id, o.getFunctionId())) {
            return false;
        }
        if (!Objects.equals(this.__department_id, o.getDepartmentId())) {
            return false;
        }
        if (!Objects.equals(this.__application_id, o.getApplicationId())) {
            return false;
        }
        return true;
    }

    @Override
    public java.lang.String toJSONString() {

        StringBuilder sb = new StringBuilder();
        int count = 0;
        if (getRoleFunctionId() != null) {
            sb.append(__wrapNumber(count++, "roleFunctionId", getRoleFunctionId()));
        }
        if (getRoleId() != null) {
            sb.append(__wrapNumber(count++, "roleId", getRoleId()));
        }
        if (getFunctionId() != null) {
            sb.append(__wrapNumber(count++, "functionId", getFunctionId()));
        }
        if (getDepartmentId() != null) {
            sb.append(__wrapNumber(count++, "departmentId", getDepartmentId()));
        }
        if (getApplicationId() != null) {
            sb.append(__wrapNumber(count++, "applicationId", getApplicationId()));
        }
        return sb.toString();
    }

    @Override
    public void setDataFromMap(Map<String, Object> values) {
        Object val;
        if ((val = values.get("roleFunctionId")) != null) {
            setRoleFunctionId(__getInt(val));
        }
        if ((val = values.get("roleId")) != null) {
            setRoleId(__getInt(val));
        }
        if ((val = values.get("functionId")) != null) {
            setFunctionId(__getInt(val));
        }
        if ((val = values.get("departmentId")) != null) {
            setDepartmentId(__getInt(val));
        }
        if ((val = values.get("applicationId")) != null) {
            setApplicationId(__getInt(val));
        }
    }

    protected java.lang.Integer __role_function_id;
    protected java.lang.Integer __role_id;
    protected java.lang.Integer __function_id;
    protected java.lang.Integer __department_id;
    protected java.lang.Integer __application_id;
}
