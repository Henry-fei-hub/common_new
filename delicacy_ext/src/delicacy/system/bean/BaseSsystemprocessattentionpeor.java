package delicacy.system.bean;

import java.util.Map;
import java.util.Objects;
import delicacy.common.GenericBase;
import delicacy.common.BaseFactory;

public class BaseSsystemprocessattentionpeor extends GenericBase implements BaseFactory<BaseSsystemprocessattentionpeor>, Comparable<BaseSsystemprocessattentionpeor> {

    public static BaseSsystemprocessattentionpeor newInstance() {
        return new BaseSsystemprocessattentionpeor();
    }

    @Override
    public BaseSsystemprocessattentionpeor make() {
        BaseSsystemprocessattentionpeor b = new BaseSsystemprocessattentionpeor();
        return b;
    }

    public final static java.lang.String CS_SYSTEM_PROCESS_ATTENTION_ID = "system_process_attention_id";
    public final static java.lang.String CS_PROCESS_TYPE = "process_type";
    public final static java.lang.String CS_BUSINESS_ID = "business_id";
    public final static java.lang.String CS_BUSINESS_NAME = "business_name";
    public final static java.lang.String CS_PROCESS_ACTIVITY_ID = "process_activity_id";
    public final static java.lang.String CS_PROCESS_ID = "process_id";
    public final static java.lang.String CS_PROCESS_INSTANCE_ID = "process_instance_id";
    public final static java.lang.String CS_BACK_VIEW_NAME = "back_view_name";
    public final static java.lang.String CS_INSTANCE_ACTIVITY_ID = "instance_activity_id";
    public final static java.lang.String CS_INSTANCE_ACTIVITY_CREATE_TIME = "instance_activity_create_time";
    public final static java.lang.String CS_INSTANCE_ACTIVITY_START_TIME = "instance_activity_start_time";
    public final static java.lang.String CS_EMPLOYEE_ID = "employee_id";
    public final static java.lang.String CS_OPERATE_TIME = "operate_time";
    public final static java.lang.String CS_PROCESS_COMMENT = "process_comment";
    public final static java.lang.String CS_STATUS = "status";

    public final static java.lang.String ALL_CAPTIONS = "主键编码,流程类型,业务编码,业务名称,活动编码,流程编码,流程实列编码,返回数据集,流程实例节点编码,流程实例节点创建时间,流程实例节点开始处于活动时间,处理人,操作时间,提交意见,状态";

    public java.lang.Integer getSystemProcessAttentionId() {
        return this.__system_process_attention_id;
    }

    public void setSystemProcessAttentionId(java.lang.Integer value) {
        this.__system_process_attention_id = value;
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

    public java.lang.Integer getProcessActivityId() {
        return this.__process_activity_id;
    }

    public void setProcessActivityId(java.lang.Integer value) {
        this.__process_activity_id = value;
    }

    public java.lang.Integer getProcessId() {
        return this.__process_id;
    }

    public void setProcessId(java.lang.Integer value) {
        this.__process_id = value;
    }

    public java.lang.Integer getProcessInstanceId() {
        return this.__process_instance_id;
    }

    public void setProcessInstanceId(java.lang.Integer value) {
        this.__process_instance_id = value;
    }

    public java.lang.String getBackViewName() {
        return this.__back_view_name;
    }

    public void setBackViewName(java.lang.String value) {
        this.__back_view_name = value;
    }

    public java.lang.Integer getInstanceActivityId() {
        return this.__instance_activity_id;
    }

    public void setInstanceActivityId(java.lang.Integer value) {
        this.__instance_activity_id = value;
    }

    public java.util.Date getInstanceActivityCreateTime() {
        return this.__instance_activity_create_time;
    }

    public void setInstanceActivityCreateTime(java.util.Date value) {
        this.__instance_activity_create_time = value;
    }

    public java.util.Date getInstanceActivityStartTime() {
        return this.__instance_activity_start_time;
    }

    public void setInstanceActivityStartTime(java.util.Date value) {
        this.__instance_activity_start_time = value;
    }

    public java.lang.Integer getEmployeeId() {
        return this.__employee_id;
    }

    public void setEmployeeId(java.lang.Integer value) {
        this.__employee_id = value;
    }

    public java.util.Date getOperateTime() {
        return this.__operate_time;
    }

    public void setOperateTime(java.util.Date value) {
        this.__operate_time = value;
    }

    public java.lang.String getProcessComment() {
        return this.__process_comment;
    }

    public void setProcessComment(java.lang.String value) {
        this.__process_comment = value;
    }

    public java.lang.Integer getStatus() {
        return this.__status;
    }

    public void setStatus(java.lang.Integer value) {
        this.__status = value;
    }

    public void cloneCopy(BaseSsystemprocessattentionpeor __bean) {
        __bean.setSystemProcessAttentionId(getSystemProcessAttentionId());
        __bean.setProcessType(getProcessType());
        __bean.setBusinessId(getBusinessId());
        __bean.setBusinessName(getBusinessName());
        __bean.setProcessActivityId(getProcessActivityId());
        __bean.setProcessId(getProcessId());
        __bean.setProcessInstanceId(getProcessInstanceId());
        __bean.setBackViewName(getBackViewName());
        __bean.setInstanceActivityId(getInstanceActivityId());
        __bean.setInstanceActivityCreateTime(getInstanceActivityCreateTime());
        __bean.setInstanceActivityStartTime(getInstanceActivityStartTime());
        __bean.setEmployeeId(getEmployeeId());
        __bean.setOperateTime(getOperateTime());
        __bean.setProcessComment(getProcessComment());
        __bean.setStatus(getStatus());
    }

    public java.lang.String toCSVString() {

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
        StringBuilder sb = new StringBuilder();
        sb.append(getSystemProcessAttentionId() == null ? "" : getSystemProcessAttentionId());
        sb.append(",");
        String strProcessType = delicacy.system.executor.SelectValueCache.getSelectValue("system_process_types", String.valueOf(getProcessType()));
        sb.append(strProcessType == null ? "" : strProcessType);
        sb.append(",");
        sb.append(getBusinessId() == null ? "" : getBusinessId());
        sb.append(",");
        sb.append(getBusinessName() == null ? "" : getBusinessName());
        sb.append(",");
        sb.append(getProcessActivityId() == null ? "" : getProcessActivityId());
        sb.append(",");
        sb.append(getProcessId() == null ? "" : getProcessId());
        sb.append(",");
        sb.append(getProcessInstanceId() == null ? "" : getProcessInstanceId());
        sb.append(",");
        sb.append(getBackViewName() == null ? "" : getBackViewName());
        sb.append(",");
        sb.append(getInstanceActivityId() == null ? "" : getInstanceActivityId());
        sb.append(",");
        sb.append(getInstanceActivityCreateTime() == null ? "" : sdf.format(getInstanceActivityCreateTime()));
        sb.append(",");
        sb.append(getInstanceActivityStartTime() == null ? "" : sdf.format(getInstanceActivityStartTime()));
        sb.append(",");
        sb.append(getEmployeeId() == null ? "" : getEmployeeId());
        sb.append(",");
        sb.append(getOperateTime() == null ? "" : sdf.format(getOperateTime()));
        sb.append(",");
        sb.append(getProcessComment() == null ? "" : getProcessComment());
        sb.append(",");
        sb.append(getStatus() == null ? "" : getStatus());
        return sb.toString();
    }

    @Override
    public int compareTo(BaseSsystemprocessattentionpeor o) {
        return __system_process_attention_id == null ? -1 : __system_process_attention_id.compareTo(o.getSystemProcessAttentionId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.__system_process_attention_id);
        hash = 97 * hash + Objects.hashCode(this.__process_type);
        hash = 97 * hash + Objects.hashCode(this.__business_id);
        hash = 97 * hash + Objects.hashCode(this.__business_name);
        hash = 97 * hash + Objects.hashCode(this.__process_activity_id);
        hash = 97 * hash + Objects.hashCode(this.__process_id);
        hash = 97 * hash + Objects.hashCode(this.__process_instance_id);
        hash = 97 * hash + Objects.hashCode(this.__back_view_name);
        hash = 97 * hash + Objects.hashCode(this.__instance_activity_id);
        hash = 97 * hash + Objects.hashCode(this.__instance_activity_create_time);
        hash = 97 * hash + Objects.hashCode(this.__instance_activity_start_time);
        hash = 97 * hash + Objects.hashCode(this.__employee_id);
        hash = 97 * hash + Objects.hashCode(this.__operate_time);
        hash = 97 * hash + Objects.hashCode(this.__process_comment);
        hash = 97 * hash + Objects.hashCode(this.__status);
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
        final BaseSsystemprocessattentionpeor o = (BaseSsystemprocessattentionpeor) obj;
        if (!Objects.equals(this.__system_process_attention_id, o.getSystemProcessAttentionId())) {
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
        if (!Objects.equals(this.__process_activity_id, o.getProcessActivityId())) {
            return false;
        }
        if (!Objects.equals(this.__process_id, o.getProcessId())) {
            return false;
        }
        if (!Objects.equals(this.__process_instance_id, o.getProcessInstanceId())) {
            return false;
        }
        if (!Objects.equals(this.__back_view_name, o.getBackViewName())) {
            return false;
        }
        if (!Objects.equals(this.__instance_activity_id, o.getInstanceActivityId())) {
            return false;
        }
        if (!Objects.equals(this.__instance_activity_create_time, o.getInstanceActivityCreateTime())) {
            return false;
        }
        if (!Objects.equals(this.__instance_activity_start_time, o.getInstanceActivityStartTime())) {
            return false;
        }
        if (!Objects.equals(this.__employee_id, o.getEmployeeId())) {
            return false;
        }
        if (!Objects.equals(this.__operate_time, o.getOperateTime())) {
            return false;
        }
        if (!Objects.equals(this.__process_comment, o.getProcessComment())) {
            return false;
        }
        if (!Objects.equals(this.__status, o.getStatus())) {
            return false;
        }
        return true;
    }

    @Override
    public java.lang.String toJSONString() {

        StringBuilder sb = new StringBuilder();
        int count = 0;
        if (getSystemProcessAttentionId() != null) {
            sb.append(__wrapNumber(count++, "systemProcessAttentionId", getSystemProcessAttentionId()));
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
        if (getProcessActivityId() != null) {
            sb.append(__wrapNumber(count++, "processActivityId", getProcessActivityId()));
        }
        if (getProcessId() != null) {
            sb.append(__wrapNumber(count++, "processId", getProcessId()));
        }
        if (getProcessInstanceId() != null) {
            sb.append(__wrapNumber(count++, "processInstanceId", getProcessInstanceId()));
        }
        if (getBackViewName() != null) {
            sb.append(__wrapString(count++, "backViewName", getBackViewName()));
        }
        if (getInstanceActivityId() != null) {
            sb.append(__wrapNumber(count++, "instanceActivityId", getInstanceActivityId()));
        }
        if (getInstanceActivityCreateTime() != null) {
            sb.append(__wrapDate(count++, "instanceActivityCreateTime", getInstanceActivityCreateTime()));
        }
        if (getInstanceActivityStartTime() != null) {
            sb.append(__wrapDate(count++, "instanceActivityStartTime", getInstanceActivityStartTime()));
        }
        if (getEmployeeId() != null) {
            sb.append(__wrapNumber(count++, "employeeId", getEmployeeId()));
        }
        if (getOperateTime() != null) {
            sb.append(__wrapDate(count++, "operateTime", getOperateTime()));
        }
        if (getProcessComment() != null) {
            sb.append(__wrapString(count++, "processComment", getProcessComment()));
        }
        if (getStatus() != null) {
            sb.append(__wrapNumber(count++, "status", getStatus()));
        }
        return sb.toString();
    }

    @Override
    public void setDataFromMap(Map<String, Object> values) {
        Object val;
        if ((val = values.get("systemProcessAttentionId")) != null) {
            setSystemProcessAttentionId(__getInt(val));
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
        if ((val = values.get("processActivityId")) != null) {
            setProcessActivityId(__getInt(val));
        }
        if ((val = values.get("processId")) != null) {
            setProcessId(__getInt(val));
        }
        if ((val = values.get("processInstanceId")) != null) {
            setProcessInstanceId(__getInt(val));
        }
        if ((val = values.get("backViewName")) != null) {
            setBackViewName(__getString(val));
        }
        if ((val = values.get("instanceActivityId")) != null) {
            setInstanceActivityId(__getInt(val));
        }
        if ((val = values.get("instanceActivityCreateTime")) != null) {
            setInstanceActivityCreateTime(__getDate(val));
        }
        if ((val = values.get("instanceActivityStartTime")) != null) {
            setInstanceActivityStartTime(__getDate(val));
        }
        if ((val = values.get("employeeId")) != null) {
            setEmployeeId(__getInt(val));
        }
        if ((val = values.get("operateTime")) != null) {
            setOperateTime(__getDate(val));
        }
        if ((val = values.get("processComment")) != null) {
            setProcessComment(__getString(val));
        }
        if ((val = values.get("status")) != null) {
            setStatus(__getInt(val));
        }
    }

    protected java.lang.Integer __system_process_attention_id;
    protected java.lang.Integer __process_type;
    protected java.lang.Integer __business_id;
    protected java.lang.String __business_name;
    protected java.lang.Integer __process_activity_id;
    protected java.lang.Integer __process_id;
    protected java.lang.Integer __process_instance_id;
    protected java.lang.String __back_view_name;
    protected java.lang.Integer __instance_activity_id;
    protected java.util.Date __instance_activity_create_time;
    protected java.util.Date __instance_activity_start_time;
    protected java.lang.Integer __employee_id;
    protected java.util.Date __operate_time;
    protected java.lang.String __process_comment;
    protected java.lang.Integer __status;
}
