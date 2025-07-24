package com.delicacy.client.data;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.view.client.ProvidesKey;

public class BApplication extends GenericBase implements BaseFactory<BApplication> {

	public static final ProvidesKey<BApplication> KEY_PROVIDER = new ProvidesKey<BApplication>() {
		@Override
		public Object getKey(BApplication item) {
			return item == null ? null : item.getApplicationId();
		}
	};

	public static BApplication newInstance() {
		return new BApplication();
	}

	@Override
	public BApplication make() {
		BApplication b = new BApplication();
		return b;
	}

	public final static java.lang.String CAPTION_APPLICATION_ID = "应用系统代码";
	public final static java.lang.String CAPTION_APPLICATION_NAME = "应用系统名称";

	public Integer getApplicationId() {
		return this.__application_id;
	}

	public void setApplicationId(Integer value) {
		this.__application_id = value;
	}

	public String getApplicationName() {
		return this.__application_name;
	}

	public void setApplicationName(String value) {
		this.__application_name = value;
	}

	@Override
	public java.lang.String toJSONString() {

		StringBuilder sb = new StringBuilder();
		int count = 0;
		if (getApplicationId() != null) {
			sb.append(__wrapNumber(count++, "applicationId", getApplicationId()));
		}
		if (getApplicationName() != null) {
			sb.append(__wrapString(count++, "applicationName", getApplicationName()));
		}
		return sb.toString();
	}

	@Override
	public void setDataFromJSON(JSONObject values) {
		JSONValue val;
		if ((val = values.get("applicationId")) != null) {
			setApplicationId(__getInt(val));
		}
		if ((val = values.get("applicationName")) != null) {
			setApplicationName(__getString(val));
		}
	}

	protected Integer __application_id;
	protected String __application_name;
}
