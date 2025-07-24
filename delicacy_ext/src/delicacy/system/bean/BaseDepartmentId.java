package delicacy.system.bean;

import java.util.Map;
import java.util.Objects;
import delicacy.common.GenericBase;
import delicacy.common.BaseFactory;

public class BaseDepartmentId extends GenericBase implements BaseFactory<BaseDepartmentId>, Comparable<BaseDepartmentId> {

    public static BaseDepartmentId newInstance() {
        return new BaseDepartmentId();
    }

    @Override
    public BaseDepartmentId make() {
        BaseDepartmentId b = new BaseDepartmentId();
        return b;
    }

    public final static java.lang.String CS_DEPARTMENT_ID_ID = "department_id_id";
    public final static java.lang.String CS_DEPARTMENT_ID = "department_id";
    public final static java.lang.String CS_CHILD_ID = "child_id";

    public final static java.lang.String ALL_CAPTIONS = ",,";

    public java.lang.Integer getDepartmentIdId() {
        return this.__department_id_id;
    }

    public void setDepartmentIdId(java.lang.Integer value) {
        this.__department_id_id = value;
    }

    public java.lang.Integer getDepartmentId() {
        return this.__department_id;
    }

    public void setDepartmentId(java.lang.Integer value) {
        this.__department_id = value;
    }

    public java.lang.Integer getChildId() {
        return this.__child_id;
    }

    public void setChildId(java.lang.Integer value) {
        this.__child_id = value;
    }

    public void cloneCopy(BaseDepartmentId __bean) {
        __bean.setDepartmentIdId(getDepartmentIdId());
        __bean.setDepartmentId(getDepartmentId());
        __bean.setChildId(getChildId());
    }

    public java.lang.String toCSVString() {

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
        StringBuilder sb = new StringBuilder();
        sb.append(getDepartmentIdId() == null ? "" : getDepartmentIdId());
        sb.append(",");
        sb.append(getDepartmentId() == null ? "" : getDepartmentId());
        sb.append(",");
        sb.append(getChildId() == null ? "" : getChildId());
        return sb.toString();
    }

    @Override
    public int compareTo(BaseDepartmentId o) {
        return __department_id_id == null ? -1 : __department_id_id.compareTo(o.getDepartmentIdId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.__department_id_id);
        hash = 97 * hash + Objects.hashCode(this.__department_id);
        hash = 97 * hash + Objects.hashCode(this.__child_id);
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
        final BaseDepartmentId o = (BaseDepartmentId) obj;
        if (!Objects.equals(this.__department_id_id, o.getDepartmentIdId())) {
            return false;
        }
        if (!Objects.equals(this.__department_id, o.getDepartmentId())) {
            return false;
        }
        if (!Objects.equals(this.__child_id, o.getChildId())) {
            return false;
        }
        return true;
    }

    @Override
    public java.lang.String toJSONString() {

        StringBuilder sb = new StringBuilder();
        int count = 0;
        if (getDepartmentIdId() != null) {
            sb.append(__wrapNumber(count++, "departmentIdId", getDepartmentIdId()));
        }
        if (getDepartmentId() != null) {
            sb.append(__wrapNumber(count++, "departmentId", getDepartmentId()));
        }
        if (getChildId() != null) {
            sb.append(__wrapNumber(count++, "childId", getChildId()));
        }
        return sb.toString();
    }

    @Override
    public void setDataFromMap(Map<String, Object> values) {
        Object val;
        if ((val = values.get("departmentIdId")) != null) {
            setDepartmentIdId(__getInt(val));
        }
        if ((val = values.get("departmentId")) != null) {
            setDepartmentId(__getInt(val));
        }
        if ((val = values.get("childId")) != null) {
            setChildId(__getInt(val));
        }
    }

    protected java.lang.Integer __department_id_id;
    protected java.lang.Integer __department_id;
    protected java.lang.Integer __child_id;
}
