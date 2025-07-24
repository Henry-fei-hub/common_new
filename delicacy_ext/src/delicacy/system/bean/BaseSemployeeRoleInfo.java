package delicacy.system.bean;

import java.util.Map;
import java.util.Objects;
import delicacy.common.GenericBase;
import delicacy.common.BaseFactory;

public class BaseSemployeeRoleInfo extends GenericBase implements BaseFactory<BaseSemployeeRoleInfo>, Comparable<BaseSemployeeRoleInfo> {

    public static BaseSemployeeRoleInfo newInstance() {
        return new BaseSemployeeRoleInfo();
    }

    @Override
    public BaseSemployeeRoleInfo make() {
        BaseSemployeeRoleInfo b = new BaseSemployeeRoleInfo();
        return b;
    }

    public final static java.lang.String CS_ROLE_ID = "role_id";
    public final static java.lang.String CS_ROLE_NAME = "role_name";
    public final static java.lang.String CS_APPLICATION_ID = "application_id";
    public final static java.lang.String CS_ROLE_TYPE = "role_type";

    public final static java.lang.String ALL_CAPTIONS = "角色编码,角色名称,应用系统代码,角色类型";

    public java.lang.Integer getRoleId() {
        return this.__role_id;
    }

    public void setRoleId(java.lang.Integer value) {
        this.__role_id = value;
    }

    public java.lang.String getRoleName() {
        return this.__role_name;
    }

    public void setRoleName(java.lang.String value) {
        this.__role_name = value;
    }

    public java.lang.Integer getApplicationId() {
        return this.__application_id;
    }

    public void setApplicationId(java.lang.Integer value) {
        this.__application_id = value;
    }

    public java.lang.Integer getRoleType() {
        return this.__role_type;
    }

    public void setRoleType(java.lang.Integer value) {
        this.__role_type = value;
    }

    public void cloneCopy(BaseSemployeeRoleInfo __bean) {
        __bean.setRoleId(getRoleId());
        __bean.setRoleName(getRoleName());
        __bean.setApplicationId(getApplicationId());
        __bean.setRoleType(getRoleType());
    }

    public java.lang.String toCSVString() {

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
        StringBuilder sb = new StringBuilder();
        String strRoleId = delicacy.system.executor.SelectValueCache.getSelectValue("roles", String.valueOf(getRoleId()));
        sb.append(strRoleId == null ? "" : strRoleId);
        sb.append(",");
        sb.append(getRoleName() == null ? "" : getRoleName());
        sb.append(",");
        String strApplicationId = delicacy.system.executor.SelectValueCache.getSelectValue("functions", String.valueOf(getApplicationId()));
        sb.append(strApplicationId == null ? "" : strApplicationId);
        sb.append(",");
        sb.append(getRoleType() == null ? "" : getRoleType());
        return sb.toString();
    }

    @Override
    public int compareTo(BaseSemployeeRoleInfo o) {
        return __role_id == null ? -1 : __role_id.compareTo(o.getRoleId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.__role_id);
        hash = 97 * hash + Objects.hashCode(this.__role_name);
        hash = 97 * hash + Objects.hashCode(this.__application_id);
        hash = 97 * hash + Objects.hashCode(this.__role_type);
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
        final BaseSemployeeRoleInfo o = (BaseSemployeeRoleInfo) obj;
        if (!Objects.equals(this.__role_id, o.getRoleId())) {
            return false;
        }
        if (!Objects.equals(this.__role_name, o.getRoleName())) {
            return false;
        }
        if (!Objects.equals(this.__application_id, o.getApplicationId())) {
            return false;
        }
        if (!Objects.equals(this.__role_type, o.getRoleType())) {
            return false;
        }
        return true;
    }

    @Override
    public java.lang.String toJSONString() {

        StringBuilder sb = new StringBuilder();
        int count = 0;
        if (getRoleId() != null) {
            sb.append(__wrapNumber(count++, "roleId", getRoleId()));
        }
        if (getRoleName() != null) {
            sb.append(__wrapString(count++, "roleName", getRoleName()));
        }
        if (getApplicationId() != null) {
            sb.append(__wrapNumber(count++, "applicationId", getApplicationId()));
        }
        if (getRoleType() != null) {
            sb.append(__wrapNumber(count++, "roleType", getRoleType()));
        }
        return sb.toString();
    }

    @Override
    public void setDataFromMap(Map<String, Object> values) {
        Object val;
        if ((val = values.get("roleId")) != null) {
            setRoleId(__getInt(val));
        }
        if ((val = values.get("roleName")) != null) {
            setRoleName(__getString(val));
        }
        if ((val = values.get("applicationId")) != null) {
            setApplicationId(__getInt(val));
        }
        if ((val = values.get("roleType")) != null) {
            setRoleType(__getInt(val));
        }
    }

    protected java.lang.Integer __role_id;
    protected java.lang.String __role_name;
    protected java.lang.Integer __application_id;
    protected java.lang.Integer __role_type;
}
