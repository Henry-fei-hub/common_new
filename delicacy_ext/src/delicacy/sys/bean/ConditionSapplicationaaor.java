package delicacy.sys.bean;

import delicacy.common.GenericCondition;
import java.util.Map;

public class ConditionSapplicationaaor extends GenericCondition {

    public ConditionSapplicationaaor() {
        setParameterCount(2);
    }

    public java.lang.Integer getApplicationId() {
        return this.__application_id;
    }

    public void setApplicationId(java.lang.Integer value) {
        this.__application_id = value;
    }

    public java.lang.String getApplicationName() {
        return this.__application_name;
    }

    public void setApplicationName(java.lang.String value) {
        this.__application_name = value;
    }

    @Override
    public java.lang.String toJSONString() {

        StringBuilder sb = new StringBuilder();
        sb.append(super.toJSONString());
        if (getApplicationId() != null) {
            sb.append(__wrapNumber(1, "applicationId", getApplicationId()));
        }
        if (getApplicationName() != null) {
            sb.append(__wrapString(1, "applicationName", getApplicationName()));
        }
        return sb.toString();
    }

    @Override
    public void setDataFromMap(Map<String, Object> values) {
        super.setDataFromMap(values);
        Object val;
        if ((val = values.get("applicationId")) != null) {
            setApplicationId(__getInt(val));
        }
        if ((val = values.get("applicationName")) != null) {
            setApplicationName(__getString(val));
        }
    }

    private java.lang.Integer __application_id = null;
    private java.lang.String __application_name = null;
}
