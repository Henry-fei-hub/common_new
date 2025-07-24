package com.delicacy.client.data;

import com.google.gwt.json.client.JSONValue;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONArray;

public class GenericCondition extends GenericBase {

    @Override
    public java.lang.String toJSONString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\"currentPage\":");
        sb.append(__currentPage);
        sb.append(",");
        sb.append("\"pageLines\":");
        sb.append(__pageLines);
        sb.append(",");
        sb.append("\"includeDetail\":");
        sb.append(__includeDetail);
        if (__keyValues == null) {
            return sb.toString();
        }
        int count = 0;
        sb.append(",");
        sb.append("\"keyValues\": [");
        for (KeyValue kv : __keyValues) {
            if (count++ > 0) {
                sb.append(", ");
            }
            sb.append(kv.toJSON());
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public void setDataFromJSON(JSONObject values) {
        JSONValue val;
        val = values.get("currentPage");
        if (val != null) {
            setCurrentPage((int) (val.isNumber().doubleValue()));
        }
        val = values.get("pageLines");
        if (val != null) {
            setPageLines((int) (val.isNumber().doubleValue()));
        }
        val = values.get("includeDetail");
        if (val != null) {
            setIncludeDetail(val.isBoolean().booleanValue());
        }
        val = values.get("keyValues");
        if (val == null) {
            return;
        }
        JSONArray objs = val.isArray();
        __keyValues = new KeyValue[objs.size()];
        for (int i = 0; i < objs.size(); i++) {
            __keyValues[i] = new KeyValue();
            __keyValues[i].setDataFromJSON((JSONObject) objs.get(i));
        }
    }

    public int getCurrentPage() {
        return this.__currentPage;
    }

    public void setCurrentPage(int value) {
        this.__currentPage = value;
    }

    public int getPageLines() {
        return this.__pageLines;
    }

    public void setPageLines(int value) {
        this.__pageLines = value;
    }

    public KeyValue[] getKeyValues() {
        return this.__keyValues;
    }

    public void setKeyValues(KeyValue[] value) {
        this.__keyValues = value;
    }

    public boolean isIncludeDetail() {
        return __includeDetail;
    }

    /**
     * @param __includeDetail the __includeDetail to set
     */
    public void setIncludeDetail(boolean __includeDetail) {
        this.__includeDetail = __includeDetail;
    }

    protected boolean __includeDetail = false;
    protected int __currentPage = 0;
    protected int __pageLines = 20;
    protected KeyValue[] __keyValues;

}
