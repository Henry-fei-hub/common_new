package com.delicacy.client.data;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.view.client.ProvidesKey;

public class BSystemProcessType extends GenericBase implements BaseFactory<BSystemProcessType> {

	public static final ProvidesKey<BSystemProcessType> KEY_PROVIDER = new ProvidesKey<BSystemProcessType>() {
		@Override
		public Object getKey(BSystemProcessType item) {
			return item == null ? null : item.getProcessTypeId();
		}
	};

	public static BSystemProcessType newInstance() {
		return new BSystemProcessType();
	}

	@Override
	public BSystemProcessType make() {
		BSystemProcessType b = new BSystemProcessType();
		return b;
	}

	public final static java.lang.String CAPTION_PROCESS_TYPE_ID = "主键编码";
	public final static java.lang.String CAPTION_PROCESS_TYPE_NAME = "流程类型名称";
	public final static java.lang.String CAPTION_DESCRIPTION = "流程类型描述";
	public final static java.lang.String CAPTION_PROCESS_EXECUTE_NAME = "流程处理程序名";
	public final static java.lang.String CAPTION_IS_ENABLE = "激活状态";

	public Integer getProcessTypeId() {
		return this.__process_type_id;
	}

	public void setProcessTypeId(Integer value) {
		this.__process_type_id = value;
	}

	public String getProcessTypeName() {
		return this.__process_type_name;
	}

	public void setProcessTypeName(String value) {
		this.__process_type_name = value;
	}

	public String getDescription() {
		return this.__description;
	}

	public void setDescription(String value) {
		this.__description = value;
	}

	public String getProcessExecuteName() {
		return this.__process_execute_name;
	}

	public void setProcessExecuteName(String value) {
		this.__process_execute_name = value;
	}

	public Boolean getIsEnable() {
		return this.__is_enable;
	}

	public void setIsEnable(Boolean value) {
		this.__is_enable = value;
	}

	@Override
	public java.lang.String toJSONString() {

		StringBuilder sb = new StringBuilder();
		int count = 0;
		if (getProcessTypeId() != null) {
			sb.append(__wrapNumber(count++, "processTypeId", getProcessTypeId()));
		}
		if (getProcessTypeName() != null) {
			sb.append(__wrapString(count++, "processTypeName", getProcessTypeName()));
		}
		if (getDescription() != null) {
			sb.append(__wrapString(count++, "description", getDescription()));
		}
		if (getProcessExecuteName() != null) {
			sb.append(__wrapString(count++, "processExecuteName", getProcessExecuteName()));
		}
		if (getIsEnable() != null) {
			sb.append(__wrapBoolean(count++, "isEnable", getIsEnable()));
		}
		return sb.toString();
	}

	@Override
	public void setDataFromJSON(JSONObject values) {
		JSONValue val;
		if ((val = values.get("processTypeId")) != null) {
			setProcessTypeId(__getInt(val));
		}
		if ((val = values.get("processTypeName")) != null) {
			setProcessTypeName(__getString(val));
		}
		if ((val = values.get("description")) != null) {
			setDescription(__getString(val));
		}
		if ((val = values.get("processExecuteName")) != null) {
			setProcessExecuteName(__getString(val));
		}
		if ((val = values.get("isEnable")) != null) {
			setIsEnable(__getBoolean(val));
		}
	}

	protected Integer __process_type_id;
	protected String __process_type_name;
	protected String __description;
	protected String __process_execute_name;
	protected Boolean __is_enable;
}
