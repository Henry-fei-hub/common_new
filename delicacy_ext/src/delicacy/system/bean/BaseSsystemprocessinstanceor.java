package delicacy.system.bean;

import java.util.Map;
import java.util.Objects;
import delicacy.common.GenericBase;
import delicacy.common.BaseFactory;

public class BaseSsystemprocessinstanceor extends GenericBase implements BaseFactory<BaseSsystemprocessinstanceor>, Comparable<BaseSsystemprocessinstanceor> {

    public static BaseSsystemprocessinstanceor newInstance() {
        return new BaseSsystemprocessinstanceor();
    }

    @Override
    public BaseSsystemprocessinstanceor make() {
        BaseSsystemprocessinstanceor b = new BaseSsystemprocessinstanceor();
        return b;
    }

    public final static java.lang.String CS_PROCESS_INSTANCE_ID = "process_instance_id";
    public final static java.lang.String CS_PROCESS_TYPE = "process_type";
    public final static java.lang.String CS_BUSINESS_ID = "business_id";
    public final static java.lang.String CS_BUSINESS_NAME = "business_name";
    public final static java.lang.String CS_PROCESS_ID = "process_id";
    public final static java.lang.String CS_PROCESS_INSTANCE_ACTIVITY_ID = "process_instance_activity_id";
    public final static java.lang.String CS_PROCESS_STATUS = "process_status";
    public final static java.lang.String CS_EMPLOYEE_ID = "employee_id";
    public final static java.lang.String CS_CREATE_TIME = "create_time";
    public final static java.lang.String CS_COMPLETE_TIME = "complete_time";

    public final static java.lang.String ALL_CAPTIONS = "主键编码,流程类型,业务编码,业务名称,流程编码,活动实列编码,流程状态,创建人,创建时间,完成时间";

    public java.lang.Integer getProcessInstanceId() {
        return this.__process_instance_id;
    }

    public void setProcessInstanceId(java.lang.Integer value) {
        this.__process_instance_id = value;
    }

    public java.lang.Integer getProcessType() {
        return this.__process_type;
    }

    public void setProcessType(java.lang.Integer value) {
        this.__process_type = value;
    }

    public java.lang.Integer getBusinessId() {
        return this.__business_id;
    }

    public void setBusinessId(java.lang.Integer value) {
        this.__business_id = value;
    }

    public java.lang.String getBusinessName() {
        return this.__business_name;
    }

    public void setBusinessName(java.lang.String value) {
        this.__business_name = value;
    }

    public java.lang.Integer getProcessId() {
        return this.__process_id;
    }

    public void setProcessId(java.lang.Integer value) {
        this.__process_id = value;
    }

    public java.lang.Integer getProcessInstanceActivityId() {
        return this.__process_instance_activity_id;
    }

    public void setProcessInstanceActivityId(java.lang.Integer value) {
        this.__process_instance_activity_id = value;
    }

    public java.lang.Integer getProcessStatus() {
        return this.__process_status;
    }

    public void setProcessStatus(java.lang.Integer value) {
        this.__process_status = value;
    }

    public java.lang.Integer getEmployeeId() {
        return this.__employee_id;
    }

    public void setEmployeeId(java.lang.Integer value) {
        this.__employee_id = value;
    }

    public java.util.Date getCreateTime() {
        return this.__create_time;
    }

    public void setCreateTime(java.util.Date value) {
        this.__create_time = value;
    }

    public java.util.Date getCompleteTime() {
        return this.__complete_time;
    }

    public void setCompleteTime(java.util.Date value) {
        this.__complete_time = value;
    }

    public void cloneCopy(BaseSsystemprocessinstanceor __bean) {
        __bean.setProcessInstanceId(getProcessInstanceId());
        __bean.setProcessType(getProcessType());
        __bean.setBusinessId(getBusinessId());
        __bean.setBusinessName(getBusinessName());
        __bean.setProcessId(getProcessId());
        __bean.setProcessInstanceActivityId(getProcessInstanceActivityId());
        __bean.setProcessStatus(getProcessStatus());
        __bean.setEmployeeId(getEmployeeId());
        __bean.setCreateTime(getCreateTime());
        __bean.setCompleteTime(getCompleteTime());
    }

    public java.lang.String toCSVString() {

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
        StringBuilder sb = new StringBuilder();
        sb.append(getProcessInstanceId() == null ? "" : getProcessInstanceId());
        sb.append(",");
        String strProcessType = delicacy.system.executor.SelectValueCache.getSelectValue("system_process_types", String.valueOf(getProcessType()));
        sb.append(strProcessType == null ? "" : strProcessType);
        sb.append(",");
        sb.append(getBusinessId() == null ? "" : getBusinessId());
        sb.append(",");
        sb.append(getBusinessName() == null ? "" : getBusinessName());
        sb.append(",");
        sb.append(getProcessId() == null ? "" : getProcessId());
        sb.append(",");
        sb.append(getProcessInstanceActivityId() == null ? "" : getProcessInstanceActivityId());
        sb.append(",");
        sb.append(getProcessStatus() == null ? "" : getProcessStatus());
        sb.append(",");
        sb.append(getEmployeeId() == null ? "" : getEmployeeId());
        sb.append(",");
        sb.append(getCreateTime() == null ? "" : sdf.format(getCreateTime()));
        sb.append(",");
        sb.append(getCompleteTime() == null ? "" : sdf.format(getCompleteTime()));
        return sb.toString();
    }

    @Override
    public int compareTo(BaseSsystemprocessinstanceor o) {
        return __process_instance_id == null ? -1 : __process_instance_id.compareTo(o.getProcessInstanceId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.__process_instance_id);
        hash = 97 * hash + Objects.hashCode(this.__process_type);
        hash = 97 * hash + Objects.hashCode(this.__business_id);
        hash = 97 * hash + Objects.hashCode(this.__business_name);
        hash = 97 * hash + Objects.hashCode(this.__process_id);
        hash = 97 * hash + Objects.hashCode(this.__process_instance_activity_id);
        hash = 97 * hash + Objects.hashCode(this.__process_status);
        hash = 97 * hash + Objects.hashCode(this.__employee_id);
        hash = 97 * hash + Objects.hashCode(this.__create_time);
        hash = 97 * hash + Objects.hashCode(this.__complete_time);
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
        final BaseSsystemprocessinstanceor o = (BaseSsystemprocessinstanceor) obj;
        if (!Objects.equals(this.__process_instance_id, o.getProcessInstanceId())) {
            return false;
        }
        if (!Objects.equals(this.__process_type, o.getProcessType())) {
            return false;
        }
        if (!Objects.equals(this.__business_id, o.getBusinessId())) {
            return false;
        }
        if (!Objects.equals(this.__business_name, o.getBusinessName())) {
            return false;
        }
        if (!Objects.equals(this.__process_id, o.getProcessId())) {
            return false;
        }
        if (!Objects.equals(this.__process_instance_activity_id, o.getProcessInstanceActivityId())) {
            return false;
        }
        if (!Objects.equals(this.__process_status, o.getProcessStatus())) {
            return false;
        }
        if (!Objects.equals(this.__employee_id, o.getEmployeeId())) {
            return false;
        }
        if (!Objects.equals(this.__create_time, o.getCreateTime())) {
            return false;
        }
        if (!Objects.equals(this.__complete_time, o.getCompleteTime())) {
            return false;
        }
        return true;
    }

    @Override
    public java.lang.String toJSONString() {

        StringBuilder sb = new StringBuilder();
        int count = 0;
        if (getProcessInstanceId() != null) {
            sb.append(__wrapNumber(count++, "processInstanceId", getProcessInstanceId()));
        }
        if (getProcessType() != null) {
            sb.append(__wrapNumber(count++, "processType", getProcessType()));
        }
        if (getBusinessId() != null) {
            sb.append(__wrapNumber(count++, "businessId", getBusinessId()));
        }
        if (getBusinessName() != null) {
            sb.append(__wrapString(count++, "businessName", getBusinessName()));
        }
        if (getProcessId() != null) {
            sb.append(__wrapNumber(count++, "processId", getProcessId()));
        }
        if (getProcessInstanceActivityId() != null) {
            sb.append(__wrapNumber(count++, "processInstanceActivityId", getProcessInstanceActivityId()));
        }
        if (getProcessStatus() != null) {
            sb.append(__wrapNumber(count++, "processStatus", getProcessStatus()));
        }
        if (getEmployeeId() != null) {
            sb.append(__wrapNumber(count++, "employeeId", getEmployeeId()));
        }
        if (getCreateTime() != null) {
            sb.append(__wrapDate(count++, "createTime", getCreateTime()));
        }
        if (getCompleteTime() != null) {
            sb.append(__wrapDate(count++, "completeTime", getCompleteTime()));
        }
        return sb.toString();
    }

    @Override
    public void setDataFromMap(Map<String, Object> values) {
        Object val;
        if ((val = values.get("processInstanceId")) != null) {
            setProcessInstanceId(__getInt(val));
        }
        if ((val = values.get("processType")) != null) {
            setProcessType(__getInt(val));
        }
        if ((val = values.get("businessId")) != null) {
            setBusinessId(__getInt(val));
        }
        if ((val = values.get("businessName")) != null) {
            setBusinessName(__getString(val));
        }
        if ((val = values.get("processId")) != null) {
            setProcessId(__getInt(val));
        }
        if ((val = values.get("processInstanceActivityId")) != null) {
            setProcessInstanceActivityId(__getInt(val));
        }
        if ((val = values.get("processStatus")) != null) {
            setProcessStatus(__getInt(val));
        }
        if ((val = values.get("employeeId")) != null) {
            setEmployeeId(__getInt(val));
        }
        if ((val = values.get("createTime")) != null) {
            setCreateTime(__getDate(val));
        }
        if ((val = values.get("completeTime")) != null) {
            setCompleteTime(__getDate(val));
        }
    }

    protected java.lang.Integer __process_instance_id;
    protected java.lang.Integer __process_type;
    protected java.lang.Integer __business_id;
    protected java.lang.String __business_name;
    protected java.lang.Integer __process_id;
    protected java.lang.Integer __process_instance_activity_id;
    protected java.lang.Integer __process_status;
    protected java.lang.Integer __employee_id;
    protected java.util.Date __create_time;
    protected java.util.Date __complete_time;
}
