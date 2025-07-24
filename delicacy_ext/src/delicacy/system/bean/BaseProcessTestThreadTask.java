package delicacy.system.bean;

import java.util.Map;
import java.util.Objects;
import delicacy.common.GenericBase;
import delicacy.common.BaseFactory;

public class BaseProcessTestThreadTask extends GenericBase implements BaseFactory<BaseProcessTestThreadTask>, Comparable<BaseProcessTestThreadTask> {

    public static BaseProcessTestThreadTask newInstance() {
        return new BaseProcessTestThreadTask();
    }

    @Override
    public BaseProcessTestThreadTask make() {
        BaseProcessTestThreadTask b = new BaseProcessTestThreadTask();
        return b;
    }

    public final static java.lang.String CS_THREAD_TASK_MANAGE_ID = "thread_task_manage_id";
    public final static java.lang.String CS_TASK_TYPE = "task_type";
    public final static java.lang.String CS_STATUS = "status";
    public final static java.lang.String CS_ERROR_MSG = "error_msg";
    public final static java.lang.String CS_OPERATOR = "operator";
    public final static java.lang.String CS_OPERATION_TYPE = "operation_type";
    public final static java.lang.String CS_BEGIN_TIME = "begin_time";
    public final static java.lang.String CS_END_TIME = "end_time";
    public final static java.lang.String CS_CREATE_TIME = "create_time";
    public final static java.lang.String CS_REMARK = "remark";

    public final static java.lang.String ALL_CAPTIONS = "任务ID,任务类型,任务状态,错误信息,操作人,操作类型,开始时间,结束时间,创建时间,备注";

    public java.lang.Integer getThreadTaskManageId() {
        return this.__thread_task_manage_id;
    }

    public void setThreadTaskManageId(java.lang.Integer value) {
        this.__thread_task_manage_id = value;
    }

    public java.lang.Integer getTaskType() {
        return this.__task_type;
    }

    public void setTaskType(java.lang.Integer value) {
        this.__task_type = value;
    }

    public java.lang.Integer getStatus() {
        return this.__status;
    }

    public void setStatus(java.lang.Integer value) {
        this.__status = value;
    }

    public java.lang.String getErrorMsg() {
        return this.__error_msg;
    }

    public void setErrorMsg(java.lang.String value) {
        this.__error_msg = value;
    }

    public java.lang.Integer getOperator() {
        return this.__operator;
    }

    public void setOperator(java.lang.Integer value) {
        this.__operator = value;
    }

    public java.lang.Integer getOperationType() {
        return this.__operation_type;
    }

    public void setOperationType(java.lang.Integer value) {
        this.__operation_type = value;
    }

    public java.util.Date getBeginTime() {
        return this.__begin_time;
    }

    public void setBeginTime(java.util.Date value) {
        this.__begin_time = value;
    }

    public java.util.Date getEndTime() {
        return this.__end_time;
    }

    public void setEndTime(java.util.Date value) {
        this.__end_time = value;
    }

    public java.util.Date getCreateTime() {
        return this.__create_time;
    }

    public void setCreateTime(java.util.Date value) {
        this.__create_time = value;
    }

    public java.lang.String getRemark() {
        return this.__remark;
    }

    public void setRemark(java.lang.String value) {
        this.__remark = value;
    }

    public void cloneCopy(BaseProcessTestThreadTask __bean) {
        __bean.setThreadTaskManageId(getThreadTaskManageId());
        __bean.setTaskType(getTaskType());
        __bean.setStatus(getStatus());
        __bean.setErrorMsg(getErrorMsg());
        __bean.setOperator(getOperator());
        __bean.setOperationType(getOperationType());
        __bean.setBeginTime(getBeginTime());
        __bean.setEndTime(getEndTime());
        __bean.setCreateTime(getCreateTime());
        __bean.setRemark(getRemark());
    }

    public java.lang.String toCSVString() {

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
        StringBuilder sb = new StringBuilder();
        sb.append(getThreadTaskManageId() == null ? "" : getThreadTaskManageId());
        sb.append(",");
        String strTaskType = delicacy.system.executor.SelectValueCache.getSelectValue("system_dictionary_166", String.valueOf(getTaskType()));
        sb.append(strTaskType == null ? "" : strTaskType);
        sb.append(",");
        String strStatus = delicacy.system.executor.SelectValueCache.getSelectValue("system_dictionary_165", String.valueOf(getStatus()));
        sb.append(strStatus == null ? "" : strStatus);
        sb.append(",");
        sb.append(getErrorMsg() == null ? "" : getErrorMsg());
        sb.append(",");
        String strOperator = delicacy.system.executor.SelectValueCache.getSelectValue("employees", String.valueOf(getOperator()));
        sb.append(strOperator == null ? "" : strOperator);
        sb.append(",");
        String strOperationType = delicacy.system.executor.SelectValueCache.getSelectValue("system_dictionary_167", String.valueOf(getOperationType()));
        sb.append(strOperationType == null ? "" : strOperationType);
        sb.append(",");
        sb.append(getBeginTime() == null ? "" : sdf.format(getBeginTime()));
        sb.append(",");
        sb.append(getEndTime() == null ? "" : sdf.format(getEndTime()));
        sb.append(",");
        sb.append(getCreateTime() == null ? "" : sdf.format(getCreateTime()));
        sb.append(",");
        sb.append(getRemark() == null ? "" : getRemark());
        return sb.toString();
    }

    @Override
    public int compareTo(BaseProcessTestThreadTask o) {
        return __thread_task_manage_id == null ? -1 : __thread_task_manage_id.compareTo(o.getThreadTaskManageId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.__thread_task_manage_id);
        hash = 97 * hash + Objects.hashCode(this.__task_type);
        hash = 97 * hash + Objects.hashCode(this.__status);
        hash = 97 * hash + Objects.hashCode(this.__error_msg);
        hash = 97 * hash + Objects.hashCode(this.__operator);
        hash = 97 * hash + Objects.hashCode(this.__operation_type);
        hash = 97 * hash + Objects.hashCode(this.__begin_time);
        hash = 97 * hash + Objects.hashCode(this.__end_time);
        hash = 97 * hash + Objects.hashCode(this.__create_time);
        hash = 97 * hash + Objects.hashCode(this.__remark);
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
        final BaseProcessTestThreadTask o = (BaseProcessTestThreadTask) obj;
        if (!Objects.equals(this.__thread_task_manage_id, o.getThreadTaskManageId())) {
            return false;
        }
        if (!Objects.equals(this.__task_type, o.getTaskType())) {
            return false;
        }
        if (!Objects.equals(this.__status, o.getStatus())) {
            return false;
        }
        if (!Objects.equals(this.__error_msg, o.getErrorMsg())) {
            return false;
        }
        if (!Objects.equals(this.__operator, o.getOperator())) {
            return false;
        }
        if (!Objects.equals(this.__operation_type, o.getOperationType())) {
            return false;
        }
        if (!Objects.equals(this.__begin_time, o.getBeginTime())) {
            return false;
        }
        if (!Objects.equals(this.__end_time, o.getEndTime())) {
            return false;
        }
        if (!Objects.equals(this.__create_time, o.getCreateTime())) {
            return false;
        }
        if (!Objects.equals(this.__remark, o.getRemark())) {
            return false;
        }
        return true;
    }

    @Override
    public java.lang.String toJSONString() {

        StringBuilder sb = new StringBuilder();
        int count = 0;
        if (getThreadTaskManageId() != null) {
            sb.append(__wrapNumber(count++, "threadTaskManageId", getThreadTaskManageId()));
        }
        if (getTaskType() != null) {
            sb.append(__wrapNumber(count++, "taskType", getTaskType()));
        }
        if (getStatus() != null) {
            sb.append(__wrapNumber(count++, "status", getStatus()));
        }
        if (getErrorMsg() != null) {
            sb.append(__wrapString(count++, "errorMsg", getErrorMsg()));
        }
        if (getOperator() != null) {
            sb.append(__wrapNumber(count++, "operator", getOperator()));
        }
        if (getOperationType() != null) {
            sb.append(__wrapNumber(count++, "operationType", getOperationType()));
        }
        if (getBeginTime() != null) {
            sb.append(__wrapDate(count++, "beginTime", getBeginTime()));
        }
        if (getEndTime() != null) {
            sb.append(__wrapDate(count++, "endTime", getEndTime()));
        }
        if (getCreateTime() != null) {
            sb.append(__wrapDate(count++, "createTime", getCreateTime()));
        }
        if (getRemark() != null) {
            sb.append(__wrapString(count++, "remark", getRemark()));
        }
        return sb.toString();
    }

    @Override
    public void setDataFromMap(Map<String, Object> values) {
        Object val;
        if ((val = values.get("threadTaskManageId")) != null) {
            setThreadTaskManageId(__getInt(val));
        }
        if ((val = values.get("taskType")) != null) {
            setTaskType(__getInt(val));
        }
        if ((val = values.get("status")) != null) {
            setStatus(__getInt(val));
        }
        if ((val = values.get("errorMsg")) != null) {
            setErrorMsg(__getString(val));
        }
        if ((val = values.get("operator")) != null) {
            setOperator(__getInt(val));
        }
        if ((val = values.get("operationType")) != null) {
            setOperationType(__getInt(val));
        }
        if ((val = values.get("beginTime")) != null) {
            setBeginTime(__getDate(val));
        }
        if ((val = values.get("endTime")) != null) {
            setEndTime(__getDate(val));
        }
        if ((val = values.get("createTime")) != null) {
            setCreateTime(__getDate(val));
        }
        if ((val = values.get("remark")) != null) {
            setRemark(__getString(val));
        }
    }

    protected java.lang.Integer __thread_task_manage_id;
    protected java.lang.Integer __task_type;
    protected java.lang.Integer __status;
    protected java.lang.String __error_msg;
    protected java.lang.Integer __operator;
    protected java.lang.Integer __operation_type;
    protected java.util.Date __begin_time;
    protected java.util.Date __end_time;
    protected java.util.Date __create_time;
    protected java.lang.String __remark;
}
