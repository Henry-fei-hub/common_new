package delicacy.system.bean;

import java.util.Map;
import java.util.Objects;
import delicacy.common.GenericBase;
import delicacy.common.BaseFactory;

public class BaseEmployeeFunction extends GenericBase implements BaseFactory<BaseEmployeeFunction>, Comparable<BaseEmployeeFunction> {

    public static BaseEmployeeFunction newInstance() {
        return new BaseEmployeeFunction();
    }

    @Override
    public BaseEmployeeFunction make() {
        BaseEmployeeFunction b = new BaseEmployeeFunction();
        return b;
    }

    public final static java.lang.String CS_EMPLOYEE_FUNCTION_ID = "employee_function_id";
    public final static java.lang.String CS_EMPLOYEE_ID = "employee_id";
    public final static java.lang.String CS_FUNCTION_ID = "function_id";
    public final static java.lang.String CS_APPLICATION_ID = "application_id";

    public final static java.lang.String ALL_CAPTIONS = "员工功能列表编码,员工编码,功能编码,应用系统";

    public java.lang.Integer getEmployeeFunctionId() {
        return this.__employee_function_id;
    }

    public void setEmployeeFunctionId(java.lang.Integer value) {
        this.__employee_function_id = value;
    }

    public java.lang.Integer getEmployeeId() {
        return this.__employee_id;
    }

    public void setEmployeeId(java.lang.Integer value) {
        this.__employee_id = value;
    }

    public java.lang.Integer getFunctionId() {
        return this.__function_id;
    }

    public void setFunctionId(java.lang.Integer value) {
        this.__function_id = value;
    }

    public java.lang.Integer getApplicationId() {
        return this.__application_id;
    }

    public void setApplicationId(java.lang.Integer value) {
        this.__application_id = value;
    }

    public void cloneCopy(BaseEmployeeFunction __bean) {
        __bean.setEmployeeFunctionId(getEmployeeFunctionId());
        __bean.setEmployeeId(getEmployeeId());
        __bean.setFunctionId(getFunctionId());
        __bean.setApplicationId(getApplicationId());
    }

    public java.lang.String toCSVString() {

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
        StringBuilder sb = new StringBuilder();
        sb.append(getEmployeeFunctionId() == null ? "" : getEmployeeFunctionId());
        sb.append(",");
        sb.append(getEmployeeId() == null ? "" : getEmployeeId());
        sb.append(",");
        sb.append(getFunctionId() == null ? "" : getFunctionId());
        sb.append(",");
        sb.append(getApplicationId() == null ? "" : getApplicationId());
        return sb.toString();
    }

    @Override
    public int compareTo(BaseEmployeeFunction o) {
        return __employee_function_id == null ? -1 : __employee_function_id.compareTo(o.getEmployeeFunctionId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.__employee_function_id);
        hash = 97 * hash + Objects.hashCode(this.__employee_id);
        hash = 97 * hash + Objects.hashCode(this.__function_id);
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
        final BaseEmployeeFunction o = (BaseEmployeeFunction) obj;
        if (!Objects.equals(this.__employee_function_id, o.getEmployeeFunctionId())) {
            return false;
        }
        if (!Objects.equals(this.__employee_id, o.getEmployeeId())) {
            return false;
        }
        if (!Objects.equals(this.__function_id, o.getFunctionId())) {
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
        if (getEmployeeFunctionId() != null) {
            sb.append(__wrapNumber(count++, "employeeFunctionId", getEmployeeFunctionId()));
        }
        if (getEmployeeId() != null) {
            sb.append(__wrapNumber(count++, "employeeId", getEmployeeId()));
        }
        if (getFunctionId() != null) {
            sb.append(__wrapNumber(count++, "functionId", getFunctionId()));
        }
        if (getApplicationId() != null) {
            sb.append(__wrapNumber(count++, "applicationId", getApplicationId()));
        }
        return sb.toString();
    }

    @Override
    public void setDataFromMap(Map<String, Object> values) {
        Object val;
        if ((val = values.get("employeeFunctionId")) != null) {
            setEmployeeFunctionId(__getInt(val));
        }
        if ((val = values.get("employeeId")) != null) {
            setEmployeeId(__getInt(val));
        }
        if ((val = values.get("functionId")) != null) {
            setFunctionId(__getInt(val));
        }
        if ((val = values.get("applicationId")) != null) {
            setApplicationId(__getInt(val));
        }
    }

    protected java.lang.Integer __employee_function_id;
    protected java.lang.Integer __employee_id;
    protected java.lang.Integer __function_id;
    protected java.lang.Integer __application_id;
}
