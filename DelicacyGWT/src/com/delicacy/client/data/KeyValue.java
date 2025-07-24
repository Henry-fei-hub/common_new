package com.delicacy.client.data;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

public class KeyValue extends GenericBase {

	public KeyValue() {
	}

	public KeyValue(String pkey, String pvalue, String pp) {
		__key = pkey;
		__value = pvalue;
		__parent = pp;
	}

	public String getKey() {
		return this.__key;
	}

	public void setKey(String value) {
		this.__key = value;
	}

	public String getValue() {
		return this.__value;
	}

	public void setValue(String value) {
		this.__value = value;
	}

	public String getParent() {
		return this.__parent;
	}

	public void setParent(String value) {
		this.__parent = value;
	}

	@Override
	public String toJSONString() {
		StringBuilder sb = new StringBuilder();
		int count = 0;
		if (__key != null) {
			sb.append(__wrapString(count++, "key", __key));
		}
		if (__value != null) {
			sb.append(__wrapString(count++, "value", __value));
		}
		if (__parent != null) {
			sb.append(__wrapString(count++, "condition", __parent));
		}
		return sb.toString();
	}

	/**
	 *
	 * @param values
	 */
	@Override
	public void setDataFromJSON(JSONObject values) {
		JSONValue val;
		val = values.get("key");
		if (val != null) {
			setKey(val.isString().stringValue());
		}
		val = values.get("value");
		if (val != null) {
			setValue(val.isString().stringValue());
		}
		val = values.get("condition");
		if (val != null) {
			setParent(val.isString().stringValue());
		}
	}

	private String __key;
	private String __value;
	private String __parent;
}
