package com.delicacy.client.domain.data;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.view.client.ProvidesKey;
import com.delicacy.client.data.BaseFactory;
import com.delicacy.client.data.GenericBase;

public class BDomainValue extends GenericBase implements BaseFactory<BDomainValue> {

    public static final ProvidesKey<BDomainValue> KEY_PROVIDER = new ProvidesKey<BDomainValue>() {
        @Override
        public Object getKey(BDomainValue item) {
            return item == null ? null : item.getSelectId();
        }
    };

    public static BDomainValue newInstance() {
        return new BDomainValue();
    }

    @Override
    public BDomainValue make() {
        BDomainValue b = new BDomainValue();
        return b;
    }
    public final static java.lang.String CAPTION_SELECT_ID = "select_id";
    public final static java.lang.String CAPTION_TABLE_NAME = "table_name";
    public final static java.lang.String CAPTION_KEY_COLUMN = "key_column";
    public final static java.lang.String CAPTION_VALUE_COLUMN = "value_column";
    public final static java.lang.String CAPTION_CONDITION_COLUMN = "condition_column";
    public final static java.lang.String CAPTION_ADDITIONAL_CONDITION = "additional_condition";

    public String getSelectId() {
        return this.__select_id;
    }

    public void setSelectId(String value) {
        this.__select_id = value;
    }

    public String getTableName() {
        return this.__table_name;
    }

    public void setTableName(String value) {
        this.__table_name = value;
    }

    public String getKeyColumn() {
        return this.__key_column;
    }

    public void setKeyColumn(String value) {
        this.__key_column = value;
    }

    public String getValueColumn() {
        return this.__value_column;
    }

    public void setValueColumn(String value) {
        this.__value_column = value;
    }

    public String getConditionColumn() {
        return this.__condition_column;
    }

    public void setConditionColumn(String value) {
        this.__condition_column = value;
    }

    public String getAdditionalCondition() {
        return this.__additional_condition;
    }

    public void setAdditionalCondition(String value) {
        this.__additional_condition = value;
    }

    @Override
    public java.lang.String toJSONString() {

        StringBuilder sb = new StringBuilder();
        int count = 0;
        if (getSelectId() != null) {
            sb.append(__wrapString(count++, "selectId", getSelectId()));
        }
        if (getTableName() != null) {
            sb.append(__wrapString(count++, "tableName", getTableName()));
        }
        if (getKeyColumn() != null) {
            sb.append(__wrapString(count++, "keyColumn", getKeyColumn()));
        }
        if (getValueColumn() != null) {
            sb.append(__wrapString(count++, "valueColumn", getValueColumn()));
        }
        if (getConditionColumn() != null) {
            sb.append(__wrapString(count++, "conditionColumn", getConditionColumn()));
        }
        if (getAdditionalCondition() != null) {
            sb.append(__wrapString(count++, "additionalCondition", getAdditionalCondition()));
        }
        return sb.toString();
    }

    @Override
    public java.lang.String toJSON() {

        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(toJSONString());
        sb.append("}");
        return sb.toString();
    }

    @Override
    public void setDataFromJSONString(String json) {
        JSONValue result = JSONParser.parseLenient(json);
        JSONObject jso = result.isObject();
        setDataFromJSON(jso);
    }

    @Override
    public void setDataFromJSON(JSONObject values) {
        JSONValue val;
        val = values.get("selectId");
        if (val != null) {
            setSelectId(val.isString().stringValue());
        }
        val = values.get("tableName");
        if (val != null) {
            setTableName(val.isString().stringValue());
        }
        val = values.get("keyColumn");
        if (val != null) {
            setKeyColumn(val.isString().stringValue());
        }
        val = values.get("valueColumn");
        if (val != null) {
            setValueColumn(val.isString().stringValue());
        }
        val = values.get("conditionColumn");
        if (val != null) {
            setConditionColumn(val.isString().stringValue());
        }
        val = values.get("additionalCondition");
        if (val != null) {
            setAdditionalCondition(val.isString().stringValue());
        }
    }
    protected String __select_id;
    protected String __table_name;
    protected String __key_column;
    protected String __value_column;
    protected String __condition_column;
    protected String __additional_condition;
}
