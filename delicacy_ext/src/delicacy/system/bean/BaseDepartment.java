package delicacy.system.bean;

import java.util.Map;
import java.util.HashMap;
import java.util.Objects;
import delicacy.common.GenericBase;
import delicacy.common.BaseFactory;

public class BaseDepartment extends GenericBase implements BaseFactory<BaseDepartment>, Comparable<BaseDepartment> {

    public static BaseDepartment newInstance() {
        return new BaseDepartment();
    }

    @Override
    public BaseDepartment make() {
        BaseDepartment b = new BaseDepartment();
        return b;
    }

    public final static java.lang.String CS_DEPARTMENT_ID = "department_id";
    public final static java.lang.String CS_DEPARTMENT_NAME = "department_name";
    public final static java.lang.String CS_ABBREVIATION = "abbreviation";
    public final static java.lang.String CS_MANAGER_ID = "manager_id";
    public final static java.lang.String CS_MANAGER_NAME = "manager_name";
    public final static java.lang.String CS_PARENT_ID = "parent_id";
    public final static java.lang.String CS_ENABLED = "enabled";
    public final static java.lang.String CS_ORIGINAL_ID = "original_id";
    public final static java.lang.String CS_PLATE_ID = "plate_id";
    public final static java.lang.String CS_IS_HEADCOUNT = "is_headcount";
    public final static java.lang.String CS_DEPARTMENT_TYPE = "department_type";
    public final static java.lang.String CS_WEIXIN_DEPARTMENT_ID = "weixin_department_id";
    public final static java.lang.String CS_EMAIL_DEPARTMENT_ID = "email_department_id";
    public final static java.lang.String CS_ECMC_DEPARTMENT_ID = "ecmc_department_id";
    public final static java.lang.String CS_DELETE_FLAG = "delete_flag";
    public final static java.lang.String CS_IS_ENABLE = "is_enable";

    public final static java.lang.String ALL_CAPTIONS = "部门编码,部门名称,部门名称缩写,部门负责编码,部门负责人姓名,上级部门,是否有效,,板块,总部, 2业务部门,微信部门编码,企业邮箱部门编码,ECMC部门编码,0 未删除  1 已删除,";

    public java.lang.Integer getDepartmentId() {
        return this.__department_id;
    }

    public void setDepartmentId(java.lang.Integer value) {
        this.__department_id = value;
    }

    public java.lang.String getDepartmentName() {
        return this.__department_name;
    }

    public void setDepartmentName(java.lang.String value) {
        this.__department_name = value;
    }

    public java.lang.String getAbbreviation() {
        return this.__abbreviation;
    }

    public void setAbbreviation(java.lang.String value) {
        this.__abbreviation = value;
    }

    public java.lang.Integer getManagerId() {
        return this.__manager_id;
    }

    public void setManagerId(java.lang.Integer value) {
        this.__manager_id = value;
    }

    public java.lang.String getManagerName() {
        return this.__manager_name;
    }

    public void setManagerName(java.lang.String value) {
        this.__manager_name = value;
    }

    public java.lang.Integer getParentId() {
        return this.__parent_id;
    }

    public void setParentId(java.lang.Integer value) {
        this.__parent_id = value;
    }

    public java.lang.Boolean getEnabled() {
        return this.__enabled;
    }

    public void setEnabled(java.lang.Boolean value) {
        this.__enabled = value;
    }

    public java.lang.Integer getOriginalId() {
        return this.__original_id;
    }

    public void setOriginalId(java.lang.Integer value) {
        this.__original_id = value;
    }

    public java.lang.Integer getPlateId() {
        return this.__plate_id;
    }

    public void setPlateId(java.lang.Integer value) {
        this.__plate_id = value;
    }

    public java.lang.Boolean getIsHeadcount() {
        return this.__is_headcount;
    }

    public void setIsHeadcount(java.lang.Boolean value) {
        this.__is_headcount = value;
    }

    public java.lang.Integer getDepartmentType() {
        return this.__department_type;
    }

    public void setDepartmentType(java.lang.Integer value) {
        this.__department_type = value;
    }

    public java.lang.Long getWeixinDepartmentId() {
        return this.__weixin_department_id;
    }

    public void setWeixinDepartmentId(java.lang.Long value) {
        this.__weixin_department_id = value;
    }

    public java.lang.Long getEmailDepartmentId() {
        return this.__email_department_id;
    }

    public void setEmailDepartmentId(java.lang.Long value) {
        this.__email_department_id = value;
    }

    public java.lang.Integer getEcmcDepartmentId() {
        return this.__ecmc_department_id;
    }

    public void setEcmcDepartmentId(java.lang.Integer value) {
        this.__ecmc_department_id = value;
    }

    public java.lang.Integer getDeleteFlag() {
        return this.__delete_flag;
    }

    public void setDeleteFlag(java.lang.Integer value) {
        this.__delete_flag = value;
    }

    public java.lang.Boolean getIsEnable() {
        return this.__is_enable;
    }

    public void setIsEnable(java.lang.Boolean value) {
        this.__is_enable = value;
    }

    public void cloneCopy(BaseDepartment __bean) {
        __bean.setDepartmentId(getDepartmentId());
        __bean.setDepartmentName(getDepartmentName());
        __bean.setAbbreviation(getAbbreviation());
        __bean.setManagerId(getManagerId());
        __bean.setManagerName(getManagerName());
        __bean.setParentId(getParentId());
        __bean.setEnabled(getEnabled());
        __bean.setOriginalId(getOriginalId());
        __bean.setPlateId(getPlateId());
        __bean.setIsHeadcount(getIsHeadcount());
        __bean.setDepartmentType(getDepartmentType());
        __bean.setWeixinDepartmentId(getWeixinDepartmentId());
        __bean.setEmailDepartmentId(getEmailDepartmentId());
        __bean.setEcmcDepartmentId(getEcmcDepartmentId());
        __bean.setDeleteFlag(getDeleteFlag());
        __bean.setIsEnable(getIsEnable());
    }

    public java.lang.String toCSVString() {

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
        StringBuilder sb = new StringBuilder();
        String strDepartmentId = delicacy.system.executor.SelectValueCache.getSelectValue("departments", String.valueOf(getDepartmentId()));
        sb.append(strDepartmentId == null ? "" : strDepartmentId);
        sb.append(",");
        sb.append(getDepartmentName() == null ? "" : getDepartmentName());
        sb.append(",");
        sb.append(getAbbreviation() == null ? "" : getAbbreviation());
        sb.append(",");
        sb.append(getManagerId() == null ? "" : getManagerId());
        sb.append(",");
        sb.append(getManagerName() == null ? "" : getManagerName());
        sb.append(",");
        sb.append(getParentId() == null ? "" : getParentId());
        sb.append(",");
        sb.append(getEnabled() == null ? "" : getEnabled());
        sb.append(",");
        sb.append(getOriginalId() == null ? "" : getOriginalId());
        sb.append(",");
        sb.append(getPlateId() == null ? "" : getPlateId());
        sb.append(",");
        sb.append(getIsHeadcount() == null ? "" : getIsHeadcount());
        sb.append(",");
        sb.append(getDepartmentType() == null ? "" : getDepartmentType());
        sb.append(",");
        sb.append(getWeixinDepartmentId() == null ? "" : getWeixinDepartmentId());
        sb.append(",");
        sb.append(getEmailDepartmentId() == null ? "" : getEmailDepartmentId());
        sb.append(",");
        sb.append(getEcmcDepartmentId() == null ? "" : getEcmcDepartmentId());
        sb.append(",");
        sb.append(getDeleteFlag() == null ? "" : getDeleteFlag());
        sb.append(",");
        sb.append(getIsEnable() == null ? "" : getIsEnable());
        return sb.toString();
    }

    @Override
    public int compareTo(BaseDepartment o) {
        return __department_id == null ? -1 : __department_id.compareTo(o.getDepartmentId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.__department_id);
        hash = 97 * hash + Objects.hashCode(this.__department_name);
        hash = 97 * hash + Objects.hashCode(this.__abbreviation);
        hash = 97 * hash + Objects.hashCode(this.__manager_id);
        hash = 97 * hash + Objects.hashCode(this.__manager_name);
        hash = 97 * hash + Objects.hashCode(this.__parent_id);
        hash = 97 * hash + Objects.hashCode(this.__enabled);
        hash = 97 * hash + Objects.hashCode(this.__original_id);
        hash = 97 * hash + Objects.hashCode(this.__plate_id);
        hash = 97 * hash + Objects.hashCode(this.__is_headcount);
        hash = 97 * hash + Objects.hashCode(this.__department_type);
        hash = 97 * hash + Objects.hashCode(this.__weixin_department_id);
        hash = 97 * hash + Objects.hashCode(this.__email_department_id);
        hash = 97 * hash + Objects.hashCode(this.__ecmc_department_id);
        hash = 97 * hash + Objects.hashCode(this.__delete_flag);
        hash = 97 * hash + Objects.hashCode(this.__is_enable);
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
        final BaseDepartment o = (BaseDepartment) obj;
        if (!Objects.equals(this.__department_id, o.getDepartmentId())) {
            return false;
        }
        if (!Objects.equals(this.__department_name, o.getDepartmentName())) {
            return false;
        }
        if (!Objects.equals(this.__abbreviation, o.getAbbreviation())) {
            return false;
        }
        if (!Objects.equals(this.__manager_id, o.getManagerId())) {
            return false;
        }
        if (!Objects.equals(this.__manager_name, o.getManagerName())) {
            return false;
        }
        if (!Objects.equals(this.__parent_id, o.getParentId())) {
            return false;
        }
        if (!Objects.equals(this.__enabled, o.getEnabled())) {
            return false;
        }
        if (!Objects.equals(this.__original_id, o.getOriginalId())) {
            return false;
        }
        if (!Objects.equals(this.__plate_id, o.getPlateId())) {
            return false;
        }
        if (!Objects.equals(this.__is_headcount, o.getIsHeadcount())) {
            return false;
        }
        if (!Objects.equals(this.__department_type, o.getDepartmentType())) {
            return false;
        }
        if (!Objects.equals(this.__weixin_department_id, o.getWeixinDepartmentId())) {
            return false;
        }
        if (!Objects.equals(this.__email_department_id, o.getEmailDepartmentId())) {
            return false;
        }
        if (!Objects.equals(this.__ecmc_department_id, o.getEcmcDepartmentId())) {
            return false;
        }
        if (!Objects.equals(this.__delete_flag, o.getDeleteFlag())) {
            return false;
        }
        if (!Objects.equals(this.__is_enable, o.getIsEnable())) {
            return false;
        }
        return true;
    }

    @Override
    public java.lang.String toJSONString() {

        StringBuilder sb = new StringBuilder();
        int count = 0;
        if (getDepartmentId() != null) {
            sb.append(__wrapNumber(count++, "departmentId", getDepartmentId()));
        }
        if (getDepartmentName() != null) {
            sb.append(__wrapString(count++, "departmentName", getDepartmentName()));
        }
        if (getAbbreviation() != null) {
            sb.append(__wrapString(count++, "abbreviation", getAbbreviation()));
        }
        if (getManagerId() != null) {
            sb.append(__wrapNumber(count++, "managerId", getManagerId()));
        }
        if (getManagerName() != null) {
            sb.append(__wrapString(count++, "managerName", getManagerName()));
        }
        if (getParentId() != null) {
            sb.append(__wrapNumber(count++, "parentId", getParentId()));
        }
        if (getEnabled() != null) {
            sb.append(__wrapBoolean(count++, "enabled", getEnabled()));
        }
        if (getOriginalId() != null) {
            sb.append(__wrapNumber(count++, "originalId", getOriginalId()));
        }
        if (getPlateId() != null) {
            sb.append(__wrapNumber(count++, "plateId", getPlateId()));
        }
        if (getIsHeadcount() != null) {
            sb.append(__wrapBoolean(count++, "isHeadcount", getIsHeadcount()));
        }
        if (getDepartmentType() != null) {
            sb.append(__wrapNumber(count++, "departmentType", getDepartmentType()));
        }
        if (getWeixinDepartmentId() != null) {
            sb.append(__wrapNumber(count++, "weixinDepartmentId", getWeixinDepartmentId()));
        }
        if (getEmailDepartmentId() != null) {
            sb.append(__wrapNumber(count++, "emailDepartmentId", getEmailDepartmentId()));
        }
        if (getEcmcDepartmentId() != null) {
            sb.append(__wrapNumber(count++, "ecmcDepartmentId", getEcmcDepartmentId()));
        }
        if (getDeleteFlag() != null) {
            sb.append(__wrapNumber(count++, "deleteFlag", getDeleteFlag()));
        }
        if (getIsEnable() != null) {
            sb.append(__wrapBoolean(count++, "isEnable", getIsEnable()));
        }
        return sb.toString();
    }

    public Map<String, Object> toMap() {
        Map<String, Object> res = new HashMap<>();
        if (getDepartmentId() != null) {
            res.put("departmentId", getDepartmentId());
        }
        if (getDepartmentName() != null) {
            res.put("departmentName", getDepartmentName());
        }
        if (getAbbreviation() != null) {
            res.put("abbreviation", getAbbreviation());
        }
        if (getManagerId() != null) {
            res.put("managerId", getManagerId());
        }
        if (getManagerName() != null) {
            res.put("managerName", getManagerName());
        }
        if (getParentId() != null) {
            res.put("parentId", getParentId());
        }
        if (getEnabled() != null) {
            res.put("enabled", getEnabled());
        }
        if (getOriginalId() != null) {
            res.put("originalId", getOriginalId());
        }
        if (getPlateId() != null) {
            res.put("plateId", getPlateId());
        }
        if (getIsHeadcount() != null) {
            res.put("isHeadcount", getIsHeadcount());
        }
        if (getDepartmentType() != null) {
            res.put("departmentType", getDepartmentType());
        }
        if (getWeixinDepartmentId() != null) {
            res.put("weixinDepartmentId", getWeixinDepartmentId());
        }
        if (getEmailDepartmentId() != null) {
            res.put("emailDepartmentId", getEmailDepartmentId());
        }
        if (getEcmcDepartmentId() != null) {
            res.put("ecmcDepartmentId", getEcmcDepartmentId());
        }
        if (getDeleteFlag() != null) {
            res.put("deleteFlag", getDeleteFlag());
        }
        if (getIsEnable() != null) {
            res.put("isEnable", getIsEnable());
        }
        return res;
    }

    @Override
    public void setDataFromMap(Map<String, Object> values) {
        Object val;
        if ((val = values.get("departmentId")) != null) {
            setDepartmentId(__getInt(val));
        }
        if ((val = values.get("departmentName")) != null) {
            setDepartmentName(__getString(val));
        }
        if ((val = values.get("abbreviation")) != null) {
            setAbbreviation(__getString(val));
        }
        if ((val = values.get("managerId")) != null) {
            setManagerId(__getInt(val));
        }
        if ((val = values.get("managerName")) != null) {
            setManagerName(__getString(val));
        }
        if ((val = values.get("parentId")) != null) {
            setParentId(__getInt(val));
        }
        if ((val = values.get("enabled")) != null) {
            setEnabled(__getBoolean(val));
        }
        if ((val = values.get("originalId")) != null) {
            setOriginalId(__getInt(val));
        }
        if ((val = values.get("plateId")) != null) {
            setPlateId(__getInt(val));
        }
        if ((val = values.get("isHeadcount")) != null) {
            setIsHeadcount(__getBoolean(val));
        }
        if ((val = values.get("departmentType")) != null) {
            setDepartmentType(__getInt(val));
        }
        if ((val = values.get("weixinDepartmentId")) != null) {
            setWeixinDepartmentId(__getLong(val));
        }
        if ((val = values.get("emailDepartmentId")) != null) {
            setEmailDepartmentId(__getLong(val));
        }
        if ((val = values.get("ecmcDepartmentId")) != null) {
            setEcmcDepartmentId(__getInt(val));
        }
        if ((val = values.get("deleteFlag")) != null) {
            setDeleteFlag(__getInt(val));
        }
        if ((val = values.get("isEnable")) != null) {
            setIsEnable(__getBoolean(val));
        }
    }

    protected java.lang.Integer __department_id;
    protected java.lang.String __department_name;
    protected java.lang.String __abbreviation;
    protected java.lang.Integer __manager_id;
    protected java.lang.String __manager_name;
    protected java.lang.Integer __parent_id;
    protected java.lang.Boolean __enabled;
    protected java.lang.Integer __original_id;
    protected java.lang.Integer __plate_id;
    protected java.lang.Boolean __is_headcount;
    protected java.lang.Integer __department_type;
    protected java.lang.Long __weixin_department_id;
    protected java.lang.Long __email_department_id;
    protected java.lang.Integer __ecmc_department_id;
    protected java.lang.Integer __delete_flag;
    protected java.lang.Boolean __is_enable;
}
