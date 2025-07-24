package delicacy.system.bean;

import java.util.Map;
import java.util.Objects;
import delicacy.common.GenericBase;
import delicacy.common.BaseFactory;

public class BaseSsystemprocesspor extends GenericBase implements BaseFactory<BaseSsystemprocesspor>, Comparable<BaseSsystemprocesspor> {

    public static BaseSsystemprocesspor newInstance() {
        return new BaseSsystemprocesspor();
    }

    @Override
    public BaseSsystemprocesspor make() {
        BaseSsystemprocesspor b = new BaseSsystemprocesspor();
        return b;
    }

    public final static java.lang.String CS_PROCESS_ID = "process_id";
    public final static java.lang.String CS_PROCESS_TYPE_ID = "process_type_id";
    public final static java.lang.String CS_DEPARTMENT_ID = "department_id";
    public final static java.lang.String CS_INCLUDE_DEPARTMENT_ID = "include_department_id";
    public final static java.lang.String CS_CREATE_EMPLOYEE_ID = "create_employee_id";
    public final static java.lang.String CS_CREATE_TIME = "create_time";
    public final static java.lang.String CS_PROCESS_NAME = "process_name";
    public final static java.lang.String CS_COUNTERSIGN = "countersign";
    public final static java.lang.String CS_HOLD_DEPARTMENT_ID = "hold_department_id";
    public final static java.lang.String CS_HOLD_DUTY_ID = "hold_duty_id";
    public final static java.lang.String CS_DESCRIPTION = "description";
    public final static java.lang.String CS_ENABLE = "enable";

    public final static java.lang.String ALL_CAPTIONS = "主键编码,流程类型编码,归属部门,归属部门(包括下级部门),创建人,创建时间,流程名称,是否会签,归档部门,归档职务,描述,是否可用";

    public java.lang.Integer getProcessId() {
        return this.__process_id;
    }

    public void setProcessId(java.lang.Integer value) {
        this.__process_id = value;
    }

    public java.lang.Integer getProcessTypeId() {
        return this.__process_type_id;
    }

    public void setProcessTypeId(java.lang.Integer value) {
        this.__process_type_id = value;
    }

    public java.lang.Integer getDepartmentId() {
        return this.__department_id;
    }

    public void setDepartmentId(java.lang.Integer value) {
        this.__department_id = value;
    }

    public java.lang.Integer getIncludeDepartmentId() {
        return this.__include_department_id;
    }

    public void setIncludeDepartmentId(java.lang.Integer value) {
        this.__include_department_id = value;
    }

    public java.lang.Integer getCreateEmployeeId() {
        return this.__create_employee_id;
    }

    public void setCreateEmployeeId(java.lang.Integer value) {
        this.__create_employee_id = value;
    }

    public java.util.Date getCreateTime() {
        return this.__create_time;
    }

    public void setCreateTime(java.util.Date value) {
        this.__create_time = value;
    }

    public java.lang.String getProcessName() {
        return this.__process_name;
    }

    public void setProcessName(java.lang.String value) {
        this.__process_name = value;
    }

    public java.lang.Boolean getCountersign() {
        return this.__countersign;
    }

    public void setCountersign(java.lang.Boolean value) {
        this.__countersign = value;
    }

    public java.lang.Integer getHoldDepartmentId() {
        return this.__hold_department_id;
    }

    public void setHoldDepartmentId(java.lang.Integer value) {
        this.__hold_department_id = value;
    }

    public java.lang.Integer getHoldDutyId() {
        return this.__hold_duty_id;
    }

    public void setHoldDutyId(java.lang.Integer value) {
        this.__hold_duty_id = value;
    }

    public java.lang.String getDescription() {
        return this.__description;
    }

    public void setDescription(java.lang.String value) {
        this.__description = value;
    }

    public java.lang.Boolean getEnable() {
        return this.__enable;
    }

    public void setEnable(java.lang.Boolean value) {
        this.__enable = value;
    }

    public java.util.List<BaseSystemProcessActivity> getDetailSystemProcessActivity() {
        return this.__detailSystemProcessActivity;
    }

    public void setDetailSystemProcessActivity(java.util.List<BaseSystemProcessActivity> value) {
        this.__detailSystemProcessActivity = value;
    }

    public void cloneCopy(BaseSsystemprocesspor __bean) {
        __bean.setProcessId(getProcessId());
        __bean.setProcessTypeId(getProcessTypeId());
        __bean.setDepartmentId(getDepartmentId());
        __bean.setIncludeDepartmentId(getIncludeDepartmentId());
        __bean.setCreateEmployeeId(getCreateEmployeeId());
        __bean.setCreateTime(getCreateTime());
        __bean.setProcessName(getProcessName());
        __bean.setCountersign(getCountersign());
        __bean.setHoldDepartmentId(getHoldDepartmentId());
        __bean.setHoldDutyId(getHoldDutyId());
        __bean.setDescription(getDescription());
        __bean.setEnable(getEnable());
    }

    public java.lang.String toCSVString() {

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
        StringBuilder sb = new StringBuilder();
        sb.append(getProcessId() == null ? "" : getProcessId());
        sb.append(",");
        String strProcessTypeId = delicacy.system.executor.SelectValueCache.getSelectValue("system_process_types", String.valueOf(getProcessTypeId()));
        sb.append(strProcessTypeId == null ? "" : strProcessTypeId);
        sb.append(",");
        String strDepartmentId = delicacy.system.executor.SelectValueCache.getSelectValue("departments", String.valueOf(getDepartmentId()));
        sb.append(strDepartmentId == null ? "" : strDepartmentId);
        sb.append(",");
        String strIncludeDepartmentId = delicacy.system.executor.SelectValueCache.getSelectValue("departments", String.valueOf(getIncludeDepartmentId()));
        sb.append(strIncludeDepartmentId == null ? "" : strIncludeDepartmentId);
        sb.append(",");
        String strCreateEmployeeId = delicacy.system.executor.SelectValueCache.getSelectValue("employees", String.valueOf(getCreateEmployeeId()));
        sb.append(strCreateEmployeeId == null ? "" : strCreateEmployeeId);
        sb.append(",");
        sb.append(getCreateTime() == null ? "" : sdf.format(getCreateTime()));
        sb.append(",");
        sb.append(getProcessName() == null ? "" : getProcessName());
        sb.append(",");
        sb.append(getCountersign() == null ? "" : getCountersign());
        sb.append(",");
        String strHoldDepartmentId = delicacy.system.executor.SelectValueCache.getSelectValue("departments", String.valueOf(getHoldDepartmentId()));
        sb.append(strHoldDepartmentId == null ? "" : strHoldDepartmentId);
        sb.append(",");
        String strHoldDutyId = delicacy.system.executor.SelectValueCache.getSelectValue("roles", String.valueOf(getHoldDutyId()));
        sb.append(strHoldDutyId == null ? "" : strHoldDutyId);
        sb.append(",");
        sb.append(getDescription() == null ? "" : getDescription());
        sb.append(",");
        sb.append(getEnable() == null ? "" : getEnable());
        return sb.toString();
    }

    @Override
    public int compareTo(BaseSsystemprocesspor o) {
        return __process_id == null ? -1 : __process_id.compareTo(o.getProcessId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.__process_id);
        hash = 97 * hash + Objects.hashCode(this.__process_type_id);
        hash = 97 * hash + Objects.hashCode(this.__department_id);
        hash = 97 * hash + Objects.hashCode(this.__include_department_id);
        hash = 97 * hash + Objects.hashCode(this.__create_employee_id);
        hash = 97 * hash + Objects.hashCode(this.__create_time);
        hash = 97 * hash + Objects.hashCode(this.__process_name);
        hash = 97 * hash + Objects.hashCode(this.__countersign);
        hash = 97 * hash + Objects.hashCode(this.__hold_department_id);
        hash = 97 * hash + Objects.hashCode(this.__hold_duty_id);
        hash = 97 * hash + Objects.hashCode(this.__description);
        hash = 97 * hash + Objects.hashCode(this.__enable);
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
        final BaseSsystemprocesspor o = (BaseSsystemprocesspor) obj;
        if (!Objects.equals(this.__process_id, o.getProcessId())) {
            return false;
        }
        if (!Objects.equals(this.__process_type_id, o.getProcessTypeId())) {
            return false;
        }
        if (!Objects.equals(this.__department_id, o.getDepartmentId())) {
            return false;
        }
        if (!Objects.equals(this.__include_department_id, o.getIncludeDepartmentId())) {
            return false;
        }
        if (!Objects.equals(this.__create_employee_id, o.getCreateEmployeeId())) {
            return false;
        }
        if (!Objects.equals(this.__create_time, o.getCreateTime())) {
            return false;
        }
        if (!Objects.equals(this.__process_name, o.getProcessName())) {
            return false;
        }
        if (!Objects.equals(this.__countersign, o.getCountersign())) {
            return false;
        }
        if (!Objects.equals(this.__hold_department_id, o.getHoldDepartmentId())) {
            return false;
        }
        if (!Objects.equals(this.__hold_duty_id, o.getHoldDutyId())) {
            return false;
        }
        if (!Objects.equals(this.__description, o.getDescription())) {
            return false;
        }
        if (!Objects.equals(this.__enable, o.getEnable())) {
            return false;
        }
        return true;
    }

    @Override
    public java.lang.String toJSONString() {

        StringBuilder sb = new StringBuilder();
        int count = 0;
        if (getProcessId() != null) {
            sb.append(__wrapNumber(count++, "processId", getProcessId()));
        }
        if (getProcessTypeId() != null) {
            sb.append(__wrapNumber(count++, "processTypeId", getProcessTypeId()));
        }
        if (getDepartmentId() != null) {
            sb.append(__wrapNumber(count++, "departmentId", getDepartmentId()));
        }
        if (getIncludeDepartmentId() != null) {
            sb.append(__wrapNumber(count++, "includeDepartmentId", getIncludeDepartmentId()));
        }
        if (getCreateEmployeeId() != null) {
            sb.append(__wrapNumber(count++, "createEmployeeId", getCreateEmployeeId()));
        }
        if (getCreateTime() != null) {
            sb.append(__wrapDate(count++, "createTime", getCreateTime()));
        }
        if (getProcessName() != null) {
            sb.append(__wrapString(count++, "processName", getProcessName()));
        }
        if (getCountersign() != null) {
            sb.append(__wrapBoolean(count++, "countersign", getCountersign()));
        }
        if (getHoldDepartmentId() != null) {
            sb.append(__wrapNumber(count++, "holdDepartmentId", getHoldDepartmentId()));
        }
        if (getHoldDutyId() != null) {
            sb.append(__wrapNumber(count++, "holdDutyId", getHoldDutyId()));
        }
        if (getDescription() != null) {
            sb.append(__wrapString(count++, "description", getDescription()));
        }
        if (getEnable() != null) {
            sb.append(__wrapBoolean(count++, "enable", getEnable()));
        }
        if (getDetailSystemProcessActivity() != null) {
            sb.append(__wrapList(count++, "detailSystemProcessActivity", getDetailSystemProcessActivity()));
        }
        return sb.toString();
    }

    @Override
    public void setDataFromMap(Map<String, Object> values) {
        Object val;
        if ((val = values.get("processId")) != null) {
            setProcessId(__getInt(val));
        }
        if ((val = values.get("processTypeId")) != null) {
            setProcessTypeId(__getInt(val));
        }
        if ((val = values.get("departmentId")) != null) {
            setDepartmentId(__getInt(val));
        }
        if ((val = values.get("includeDepartmentId")) != null) {
            setIncludeDepartmentId(__getInt(val));
        }
        if ((val = values.get("createEmployeeId")) != null) {
            setCreateEmployeeId(__getInt(val));
        }
        if ((val = values.get("createTime")) != null) {
            setCreateTime(__getDate(val));
        }
        if ((val = values.get("processName")) != null) {
            setProcessName(__getString(val));
        }
        if ((val = values.get("countersign")) != null) {
            setCountersign(__getBoolean(val));
        }
        if ((val = values.get("holdDepartmentId")) != null) {
            setHoldDepartmentId(__getInt(val));
        }
        if ((val = values.get("holdDutyId")) != null) {
            setHoldDutyId(__getInt(val));
        }
        if ((val = values.get("description")) != null) {
            setDescription(__getString(val));
        }
        if ((val = values.get("enable")) != null) {
            setEnable(__getBoolean(val));
        }
        if ((val = values.get("detailSystemProcessActivity")) != null) {
            setDetailSystemProcessActivity(__getList(val, BaseSystemProcessActivity.newInstance()));
        }
    }

    protected java.lang.Integer __process_id;
    protected java.lang.Integer __process_type_id;
    protected java.lang.Integer __department_id;
    protected java.lang.Integer __include_department_id;
    protected java.lang.Integer __create_employee_id;
    protected java.util.Date __create_time;
    protected java.lang.String __process_name;
    protected java.lang.Boolean __countersign;
    protected java.lang.Integer __hold_department_id;
    protected java.lang.Integer __hold_duty_id;
    protected java.lang.String __description;
    protected java.lang.Boolean __enable;
    protected java.util.List<BaseSystemProcessActivity> __detailSystemProcessActivity = null;
}
