package delicacy.system.bean;

import java.util.Map;
import java.util.Objects;
import delicacy.common.GenericBase;
import delicacy.common.BaseFactory;

public class BaseDepartmentGroup extends GenericBase implements BaseFactory<BaseDepartmentGroup>, Comparable<BaseDepartmentGroup> {

    public static BaseDepartmentGroup newInstance() {
        return new BaseDepartmentGroup();
    }

    @Override
    public BaseDepartmentGroup make() {
        BaseDepartmentGroup b = new BaseDepartmentGroup();
        return b;
    }

    public final static java.lang.String CS_DEPARTMENT_GROUP_ID = "department_group_id";
    public final static java.lang.String CS_GROUP_NAME = "group_name";
    public final static java.lang.String CS_IS_COMPANY = "is_company";

    public final static java.lang.String ALL_CAPTIONS = "部门组编码,部门组名,是公司";

    public java.lang.Integer getDepartmentGroupId() {
        return this.__department_group_id;
    }

    public void setDepartmentGroupId(java.lang.Integer value) {
        this.__department_group_id = value;
    }

    public java.lang.String getGroupName() {
        return this.__group_name;
    }

    public void setGroupName(java.lang.String value) {
        this.__group_name = value;
    }

    public java.lang.Boolean getIsCompany() {
        return this.__is_company;
    }

    public void setIsCompany(java.lang.Boolean value) {
        this.__is_company = value;
    }

    public void cloneCopy(BaseDepartmentGroup __bean) {
        __bean.setDepartmentGroupId(getDepartmentGroupId());
        __bean.setGroupName(getGroupName());
        __bean.setIsCompany(getIsCompany());
    }

    public java.lang.String toCSVString() {

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
        StringBuilder sb = new StringBuilder();
        sb.append(getDepartmentGroupId() == null ? "" : getDepartmentGroupId());
        sb.append(",");
        sb.append(getGroupName() == null ? "" : getGroupName());
        sb.append(",");
        sb.append(getIsCompany() == null ? "" : getIsCompany());
        return sb.toString();
    }

    @Override
    public int compareTo(BaseDepartmentGroup o) {
        return __department_group_id == null ? -1 : __department_group_id.compareTo(o.getDepartmentGroupId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.__department_group_id);
        hash = 97 * hash + Objects.hashCode(this.__group_name);
        hash = 97 * hash + Objects.hashCode(this.__is_company);
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
        final BaseDepartmentGroup o = (BaseDepartmentGroup) obj;
        if (!Objects.equals(this.__department_group_id, o.getDepartmentGroupId())) {
            return false;
        }
        if (!Objects.equals(this.__group_name, o.getGroupName())) {
            return false;
        }
        if (!Objects.equals(this.__is_company, o.getIsCompany())) {
            return false;
        }
        return true;
    }

    @Override
    public java.lang.String toJSONString() {

        StringBuilder sb = new StringBuilder();
        int count = 0;
        if (getDepartmentGroupId() != null) {
            sb.append(__wrapNumber(count++, "departmentGroupId", getDepartmentGroupId()));
        }
        if (getGroupName() != null) {
            sb.append(__wrapString(count++, "groupName", getGroupName()));
        }
        if (getIsCompany() != null) {
            sb.append(__wrapBoolean(count++, "isCompany", getIsCompany()));
        }
        return sb.toString();
    }

    @Override
    public void setDataFromMap(Map<String, Object> values) {
        Object val;
        if ((val = values.get("departmentGroupId")) != null) {
            setDepartmentGroupId(__getInt(val));
        }
        if ((val = values.get("groupName")) != null) {
            setGroupName(__getString(val));
        }
        if ((val = values.get("isCompany")) != null) {
            setIsCompany(__getBoolean(val));
        }
    }

    protected java.lang.Integer __department_group_id;
    protected java.lang.String __group_name;
    protected java.lang.Boolean __is_company;
}
