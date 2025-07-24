package delicacy.system.bean;

import java.util.Map;
import java.util.Objects;
import delicacy.common.GenericBase;
import delicacy.common.BaseFactory;

public class BaseProcessorTestResult extends GenericBase implements BaseFactory<BaseProcessorTestResult>, Comparable<BaseProcessorTestResult> {

    public static BaseProcessorTestResult newInstance() {
        return new BaseProcessorTestResult();
    }

    @Override
    public BaseProcessorTestResult make() {
        BaseProcessorTestResult b = new BaseProcessorTestResult();
        return b;
    }

    public final static java.lang.String CS_PROCESSOR_TEST_RESULT_ID = "processor_test_result_id";
    public final static java.lang.String CS_DRAFTER = "drafter";
    public final static java.lang.String CS_DEPARTMENT_ID = "department_id";
    public final static java.lang.String CS_PLATE_ID = "plate_id";
    public final static java.lang.String CS_COMPANY_ID = "company_id";
    public final static java.lang.String CS_PROCESS_ID = "process_id";
    public final static java.lang.String CS_TEST_DATA = "test_data";
    public final static java.lang.String CS_TEST_RESULT = "test_result";
    public final static java.lang.String CS_ERROR_MSG = "error_msg";
    public final static java.lang.String CS_START_TIME = "start_time";
    public final static java.lang.String CS_END_TIME = "end_time";
    public final static java.lang.String CS_THREAD_TASK_MANAGE_ID = "thread_task_manage_id";

    public final static java.lang.String ALL_CAPTIONS = "流程测试结果编码,发起人,部门,业务部门,归属公司,流程编码,测试数据, 2失败,错误信息,开始时间,结束时间,线程任务编码";

    public java.lang.Integer getProcessorTestResultId() {
        return this.__processor_test_result_id;
    }

    public void setProcessorTestResultId(java.lang.Integer value) {
        this.__processor_test_result_id = value;
    }

    public java.lang.Integer getDrafter() {
        return this.__drafter;
    }

    public void setDrafter(java.lang.Integer value) {
        this.__drafter = value;
    }

    public java.lang.Integer getDepartmentId() {
        return this.__department_id;
    }

    public void setDepartmentId(java.lang.Integer value) {
        this.__department_id = value;
    }

    public java.lang.Integer getPlateId() {
        return this.__plate_id;
    }

    public void setPlateId(java.lang.Integer value) {
        this.__plate_id = value;
    }

    public java.lang.Integer getCompanyId() {
        return this.__company_id;
    }

    public void setCompanyId(java.lang.Integer value) {
        this.__company_id = value;
    }

    public java.lang.Integer getProcessId() {
        return this.__process_id;
    }

    public void setProcessId(java.lang.Integer value) {
        this.__process_id = value;
    }

    public java.lang.String getTestData() {
        return this.__test_data;
    }

    public void setTestData(java.lang.String value) {
        this.__test_data = value;
    }

    public java.lang.Integer getTestResult() {
        return this.__test_result;
    }

    public void setTestResult(java.lang.Integer value) {
        this.__test_result = value;
    }

    public java.lang.String getErrorMsg() {
        return this.__error_msg;
    }

    public void setErrorMsg(java.lang.String value) {
        this.__error_msg = value;
    }

    public java.util.Date getStartTime() {
        return this.__start_time;
    }

    public void setStartTime(java.util.Date value) {
        this.__start_time = value;
    }

    public java.util.Date getEndTime() {
        return this.__end_time;
    }

    public void setEndTime(java.util.Date value) {
        this.__end_time = value;
    }

    public java.lang.Integer getThreadTaskManageId() {
        return this.__thread_task_manage_id;
    }

    public void setThreadTaskManageId(java.lang.Integer value) {
        this.__thread_task_manage_id = value;
    }

    public void cloneCopy(BaseProcessorTestResult __bean) {
        __bean.setProcessorTestResultId(getProcessorTestResultId());
        __bean.setDrafter(getDrafter());
        __bean.setDepartmentId(getDepartmentId());
        __bean.setPlateId(getPlateId());
        __bean.setCompanyId(getCompanyId());
        __bean.setProcessId(getProcessId());
        __bean.setTestData(getTestData());
        __bean.setTestResult(getTestResult());
        __bean.setErrorMsg(getErrorMsg());
        __bean.setStartTime(getStartTime());
        __bean.setEndTime(getEndTime());
        __bean.setThreadTaskManageId(getThreadTaskManageId());
    }

    public java.lang.String toCSVString() {

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
        StringBuilder sb = new StringBuilder();
        sb.append(getProcessorTestResultId() == null ? "" : getProcessorTestResultId());
        sb.append(",");
        String strDrafter = delicacy.system.executor.SelectValueCache.getSelectValue("employees", String.valueOf(getDrafter()));
        sb.append(strDrafter == null ? "" : strDrafter);
        sb.append(",");
        String strDepartmentId = delicacy.system.executor.SelectValueCache.getSelectValue("departments", String.valueOf(getDepartmentId()));
        sb.append(strDepartmentId == null ? "" : strDepartmentId);
        sb.append(",");
        String strPlateId = delicacy.system.executor.SelectValueCache.getSelectValue("system_dictionary_1", String.valueOf(getPlateId()));
        sb.append(strPlateId == null ? "" : strPlateId);
        sb.append(",");
        String strCompanyId = delicacy.system.executor.SelectValueCache.getSelectValue("system_dictionary_26", String.valueOf(getCompanyId()));
        sb.append(strCompanyId == null ? "" : strCompanyId);
        sb.append(",");
        sb.append(getProcessId() == null ? "" : getProcessId());
        sb.append(",");
        sb.append(getTestData() == null ? "" : getTestData());
        sb.append(",");
        String strTestResult = delicacy.system.executor.SelectValueCache.getSelectValue("", String.valueOf(getTestResult()));
        sb.append(strTestResult == null ? "" : strTestResult);
        sb.append(",");
        sb.append(getErrorMsg() == null ? "" : getErrorMsg());
        sb.append(",");
        sb.append(getStartTime() == null ? "" : sdf.format(getStartTime()));
        sb.append(",");
        sb.append(getEndTime() == null ? "" : sdf.format(getEndTime()));
        sb.append(",");
        sb.append(getThreadTaskManageId() == null ? "" : getThreadTaskManageId());
        return sb.toString();
    }

    @Override
    public int compareTo(BaseProcessorTestResult o) {
        return __processor_test_result_id == null ? -1 : __processor_test_result_id.compareTo(o.getProcessorTestResultId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.__processor_test_result_id);
        hash = 97 * hash + Objects.hashCode(this.__drafter);
        hash = 97 * hash + Objects.hashCode(this.__department_id);
        hash = 97 * hash + Objects.hashCode(this.__plate_id);
        hash = 97 * hash + Objects.hashCode(this.__company_id);
        hash = 97 * hash + Objects.hashCode(this.__process_id);
        hash = 97 * hash + Objects.hashCode(this.__test_data);
        hash = 97 * hash + Objects.hashCode(this.__test_result);
        hash = 97 * hash + Objects.hashCode(this.__error_msg);
        hash = 97 * hash + Objects.hashCode(this.__start_time);
        hash = 97 * hash + Objects.hashCode(this.__end_time);
        hash = 97 * hash + Objects.hashCode(this.__thread_task_manage_id);
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
        final BaseProcessorTestResult o = (BaseProcessorTestResult) obj;
        if (!Objects.equals(this.__processor_test_result_id, o.getProcessorTestResultId())) {
            return false;
        }
        if (!Objects.equals(this.__drafter, o.getDrafter())) {
            return false;
        }
        if (!Objects.equals(this.__department_id, o.getDepartmentId())) {
            return false;
        }
        if (!Objects.equals(this.__plate_id, o.getPlateId())) {
            return false;
        }
        if (!Objects.equals(this.__company_id, o.getCompanyId())) {
            return false;
        }
        if (!Objects.equals(this.__process_id, o.getProcessId())) {
            return false;
        }
        if (!Objects.equals(this.__test_data, o.getTestData())) {
            return false;
        }
        if (!Objects.equals(this.__test_result, o.getTestResult())) {
            return false;
        }
        if (!Objects.equals(this.__error_msg, o.getErrorMsg())) {
            return false;
        }
        if (!Objects.equals(this.__start_time, o.getStartTime())) {
            return false;
        }
        if (!Objects.equals(this.__end_time, o.getEndTime())) {
            return false;
        }
        if (!Objects.equals(this.__thread_task_manage_id, o.getThreadTaskManageId())) {
            return false;
        }
        return true;
    }

    @Override
    public java.lang.String toJSONString() {

        StringBuilder sb = new StringBuilder();
        int count = 0;
        if (getProcessorTestResultId() != null) {
            sb.append(__wrapNumber(count++, "processorTestResultId", getProcessorTestResultId()));
        }
        if (getDrafter() != null) {
            sb.append(__wrapNumber(count++, "drafter", getDrafter()));
        }
        if (getDepartmentId() != null) {
            sb.append(__wrapNumber(count++, "departmentId", getDepartmentId()));
        }
        if (getPlateId() != null) {
            sb.append(__wrapNumber(count++, "plateId", getPlateId()));
        }
        if (getCompanyId() != null) {
            sb.append(__wrapNumber(count++, "companyId", getCompanyId()));
        }
        if (getProcessId() != null) {
            sb.append(__wrapNumber(count++, "processId", getProcessId()));
        }
        if (getTestData() != null) {
            sb.append(__wrapString(count++, "testData", getTestData()));
        }
        if (getTestResult() != null) {
            sb.append(__wrapNumber(count++, "testResult", getTestResult()));
        }
        if (getErrorMsg() != null) {
            sb.append(__wrapString(count++, "errorMsg", getErrorMsg()));
        }
        if (getStartTime() != null) {
            sb.append(__wrapDate(count++, "startTime", getStartTime()));
        }
        if (getEndTime() != null) {
            sb.append(__wrapDate(count++, "endTime", getEndTime()));
        }
        if (getThreadTaskManageId() != null) {
            sb.append(__wrapNumber(count++, "threadTaskManageId", getThreadTaskManageId()));
        }
        return sb.toString();
    }

    @Override
    public void setDataFromMap(Map<String, Object> values) {
        Object val;
        if ((val = values.get("processorTestResultId")) != null) {
            setProcessorTestResultId(__getInt(val));
        }
        if ((val = values.get("drafter")) != null) {
            setDrafter(__getInt(val));
        }
        if ((val = values.get("departmentId")) != null) {
            setDepartmentId(__getInt(val));
        }
        if ((val = values.get("plateId")) != null) {
            setPlateId(__getInt(val));
        }
        if ((val = values.get("companyId")) != null) {
            setCompanyId(__getInt(val));
        }
        if ((val = values.get("processId")) != null) {
            setProcessId(__getInt(val));
        }
        if ((val = values.get("testData")) != null) {
            setTestData(__getString(val));
        }
        if ((val = values.get("testResult")) != null) {
            setTestResult(__getInt(val));
        }
        if ((val = values.get("errorMsg")) != null) {
            setErrorMsg(__getString(val));
        }
        if ((val = values.get("startTime")) != null) {
            setStartTime(__getDate(val));
        }
        if ((val = values.get("endTime")) != null) {
            setEndTime(__getDate(val));
        }
        if ((val = values.get("threadTaskManageId")) != null) {
            setThreadTaskManageId(__getInt(val));
        }
    }

    protected java.lang.Integer __processor_test_result_id;
    protected java.lang.Integer __drafter;
    protected java.lang.Integer __department_id;
    protected java.lang.Integer __plate_id;
    protected java.lang.Integer __company_id;
    protected java.lang.Integer __process_id;
    protected java.lang.String __test_data;
    protected java.lang.Integer __test_result;
    protected java.lang.String __error_msg;
    protected java.util.Date __start_time;
    protected java.util.Date __end_time;
    protected java.lang.Integer __thread_task_manage_id;
}
