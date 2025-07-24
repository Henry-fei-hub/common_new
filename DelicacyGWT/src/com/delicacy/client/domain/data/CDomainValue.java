package com.delicacy.client.domain.data;

import com.delicacy.client.data.GenericCondition;

public class CDomainValue extends GenericCondition {

    public final static java.lang.String CAPTION_SELECT_ID = "select_id";
    public final static java.lang.String CAPTION_LOAD_ON_STARTUP = "load_on_startup";

    public String getSelectId() {
        return this.__select_id;
    }

    public void setSelectId(String value) {
        this.__select_id = value;
    }

    public Boolean getLoadOnStartup() {
        return this.__load_on_startup;
    }

    public void setLoadOnStartup(Boolean value) {
        this.__load_on_startup = value;
    }

    public int getParameterCount() {
        return this.__parameterCount;
    }

    public void setParameterCount(int value) {
        this.__parameterCount = value;
    }

    @Override
    public java.lang.String toJSONString() {

        StringBuilder sb = new StringBuilder();
        sb.append(super.toJSONString());
        if (getSelectId() != null) {
            sb.append(__wrapString(1, "selectId", getSelectId()));
        }
        if (getLoadOnStartup() != null) {
            sb.append(__wrapBoolean(1, "loadOnStartup", getLoadOnStartup()));
        }
        return sb.toString();
    }

    private String __select_id = null;
    private Boolean __load_on_startup = null;
    private int __parameterCount = 2;
}
