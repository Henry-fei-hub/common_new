package com.delicacy.client.data;

import com.google.gwt.core.client.JsonUtils;
import java.util.List;
import java.util.ArrayList;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONNumber;

public class BCollection<T extends GenericBase> {

    public double getCurrentPage() {
        return this.__currentPage;
    }

    public void setCurrentPage(double value) {
        this.__currentPage = value;
    }

    public double getRecordNumber() {
        return this.__recordNumber;
    }

    public void setRecordNumber(double value) {
        this.__recordNumber = value;
    }

    public double getPageLines() {
        return this.__pageLines;
    }

    public void setPageLines(double value) {
        this.__pageLines = value;
    }

    public double getTotalPages() {
        return this.__totalPages;
    }

    public void setTotalPages(double value) {
        this.__totalPages = value;
    }

    public double getTotalLines() {
        return this.__totalLines;
    }

    public void setTotalLines(double value) {
        this.__totalLines = value;
    }

    public double getStatus() {
        return this.__status;
    }

    public void setStatus(double value) {
        this.__status = value;
    }

    public String getMessage() {
        return this.__message;
    }

    public void setMessage(String value) {
        this.__message = value;
    }

    public List<T> getData() {
        return this.__data;
    }

    public void setData(List<T> value) {
        this.__data = value;
    }

    public void setDataFromJSON(BaseFactory<T> factory, String json) {
        JSONValue result = JSONParser.parseLenient(JsonUtils.escapeJsonForEval(json));
        JSONObject jso = result.isObject();
        for (String kk : jso.keySet()) {
            if (!kk.equals("ResultSet")) {
                continue;
            }
            JSONObject valueSet = (JSONObject) jso.get(kk);
            for (String key : valueSet.keySet()) {
                switch (key) {
                    case "Result":
                        List<T> cols = new ArrayList<>();
                        JSONArray objs = (JSONArray) valueSet.get(key);
                        for (int i = 0; i < objs.size(); i++) {
                            T bc = factory.make();
                            bc.setDataFromJSON((JSONObject) objs.get(i));
                            cols.add(bc);
                        }
                        setData(cols);
                        break;
                    case "status":
                        setStatus(((JSONNumber) valueSet.get(key)).doubleValue());
                        break;
                    case "errors":
                        setMessage(((JSONString) valueSet.get(key)).stringValue());
                        break;
                    case "totalPages":
                        setTotalPages(((JSONNumber) valueSet.get(key)).doubleValue());
                        break;
                    case "totalResultsAvailable":
                        setTotalLines(((JSONNumber) valueSet.get(key)).doubleValue());
                        break;
                    case "currentPage":
                        setCurrentPage(((JSONNumber) valueSet.get(key)).doubleValue());
                        break;
                    case "recordNumber":
                        setRecordNumber(((JSONNumber) valueSet.get(key)).doubleValue());
                        break;
                    case "pageLines":
                        setPageLines(((JSONNumber) valueSet.get(key)).doubleValue());
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public java.lang.String toJSON() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"ResultSet\":{\"status\":");
        sb.append(getStatus());
        sb.append(",\"errors\":\"");
        sb.append(getMessage());
        sb.append("\",\"totalPages\":");
        sb.append(__totalPages);
        sb.append(",\"totalResultsAvailable\":");
        sb.append(__totalLines);
        sb.append(",\"currentPage\":");
        sb.append(__currentPage);
        sb.append(",\"recordNumber\":");
        sb.append(__recordNumber);
        sb.append(",\"pageLines\":");
        sb.append(__pageLines);
        sb.append(",\"Result\":[");
        if (__data != null && !__data.isEmpty()) {
            int count = 0;
            for (T dataLine : __data) {
                if (count > 0) {
                    sb.append(",");
                }
                sb.append(dataLine.toJSON());
                count++;
            }
        }
        sb.append("]}}");
        return sb.toString();
    }

    private double __status = 0;
    private String __message;
    private double __currentPage = 0;
    private double __pageLines = 0;
    private double __totalPages = 0;
    private double __recordNumber = 0;
    private double __totalLines = 0;

    private List<T> __data = null;
}
