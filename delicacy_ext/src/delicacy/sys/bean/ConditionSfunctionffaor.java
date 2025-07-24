package delicacy.sys.bean;

import delicacy.common.GenericCondition;
import java.util.Map;

public class ConditionSfunctionffaor extends GenericCondition {

    public ConditionSfunctionffaor() {
        setParameterCount(4);
    }

    public java.lang.Integer getParentId() {
        return this.__parent_id;
    }

    public void setParentId(java.lang.Integer value) {
        this.__parent_id = value;
    }

    public java.lang.String getFunctionCode() {
        return this.__function_code;
    }

    public void setFunctionCode(java.lang.String value) {
        this.__function_code = value;
    }

    public java.lang.String getFunctionName() {
        return this.__function_name;
    }

    public void setFunctionName(java.lang.String value) {
        this.__function_name = value;
    }

    public java.lang.Integer getApplicationId() {
        return this.__application_id;
    }

    public void setApplicationId(java.lang.Integer value) {
        this.__application_id = value;
    }

    @Override
    public java.lang.String toJSONString() {

        StringBuilder sb = new StringBuilder();
        sb.append(super.toJSONString());
        if (getParentId() != null) {
            sb.append(__wrapNumber(1, "parentId", getParentId()));
        }
        if (getFunctionCode() != null) {
            sb.append(__wrapString(1, "functionCode", getFunctionCode()));
        }
        if (getFunctionName() != null) {
            sb.append(__wrapString(1, "functionName", getFunctionName()));
        }
        if (getApplicationId() != null) {
            sb.append(__wrapNumber(1, "applicationId", getApplicationId()));
        }
        return sb.toString();
    }

    @Override
    public void setDataFromMap(Map<String, Object> values) {
        super.setDataFromMap(values);
        Object val;
        if ((val = values.get("parentId")) != null) {
            setParentId(__getInt(val));
        }
        if ((val = values.get("functionCode")) != null) {
            setFunctionCode(__getString(val));
        }
        if ((val = values.get("functionName")) != null) {
            setFunctionName(__getString(val));
        }
        if ((val = values.get("applicationId")) != null) {
            setApplicationId(__getInt(val));
        }
    }

    private java.lang.Integer __parent_id = null;
    private java.lang.String __function_code = null;
    private java.lang.String __function_name = null;
    private java.lang.Integer __application_id = null;
}
