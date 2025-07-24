package com.delicacy.client.data;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.view.client.ProvidesKey;

public class BFunction extends GenericBase implements BaseFactory<BFunction> {

	public static final ProvidesKey<BFunction> KEY_PROVIDER = new ProvidesKey<BFunction>() {
		@Override
		public Object getKey(BFunction item) {
			return item == null ? null : item.getFunctionId();
		}
	};

	public static BFunction newInstance() {
		return new BFunction();
	}

	@Override
	public BFunction make() {
		BFunction b = new BFunction();
		return b;
	}

	public final static java.lang.String CAPTION_FUNCTION_ID = "功能编码";
	public final static java.lang.String CAPTION_FUNCTION_CODE = "功能编号";
	public final static java.lang.String CAPTION_PARENT_ID = "上级功能";
	public final static java.lang.String CAPTION_FUNCTION_NAME = "功能名称";
	public final static java.lang.String CAPTION_APPLICATION_ID = "应用系统代码";
	public final static java.lang.String CAPTION_FUNCTION_TYPE = "功能类型";
	public final static java.lang.String CAPTION_PRIVILEGE_TYPE = "数据权限类型";
	public final static java.lang.String CAPTION_ENABLED = "是否有效";

	public Integer getFunctionId() {
		return this.__function_id;
	}

	public void setFunctionId(Integer value) {
		this.__function_id = value;
	}

	public String getFunctionCode() {
		return this.__function_code;
	}

	public void setFunctionCode(String value) {
		this.__function_code = value;
	}

	public Integer getParentId() {
		return this.__parent_id;
	}

	public void setParentId(Integer value) {
		this.__parent_id = value;
	}

	public String getFunctionName() {
		return this.__function_name;
	}

	public void setFunctionName(String value) {
		this.__function_name = value;
	}

	public Integer getApplicationId() {
		return this.__application_id;
	}

	public void setApplicationId(Integer value) {
		this.__application_id = value;
	}

	public Integer getFunctionType() {
		return this.__function_type;
	}

	public void setFunctionType(Integer value) {
		this.__function_type = value;
	}

	public Integer getPrivilegeType() {
		return this.__privilege_type;
	}

	public void setPrivilegeType(Integer value) {
		this.__privilege_type = value;
	}

	public Boolean getEnabled() {
		return this.__enabled;
	}

	public void setEnabled(Boolean value) {
		this.__enabled = value;
	}

	@Override
	public java.lang.String toJSONString() {

		StringBuilder sb = new StringBuilder();
		int count = 0;
		if (getFunctionId() != null) {
			sb.append(__wrapNumber(count++, "functionId", getFunctionId()));
		}
		if (getFunctionCode() != null) {
			sb.append(__wrapString(count++, "functionCode", getFunctionCode()));
		}
		if (getParentId() != null) {
			sb.append(__wrapNumber(count++, "parentId", getParentId()));
		}
		if (getFunctionName() != null) {
			sb.append(__wrapString(count++, "functionName", getFunctionName()));
		}
		if (getApplicationId() != null) {
			sb.append(__wrapNumber(count++, "applicationId", getApplicationId()));
		}
		if (getFunctionType() != null) {
			sb.append(__wrapNumber(count++, "functionType", getFunctionType()));
		}
		if (getPrivilegeType() != null) {
			sb.append(__wrapNumber(count++, "privilegeType", getPrivilegeType()));
		}
		if (getEnabled() != null) {
			sb.append(__wrapBoolean(count++, "enabled", getEnabled()));
		}
		return sb.toString();
	}

	@Override
	public void setDataFromJSON(JSONObject values) {
		JSONValue val;
		if ((val = values.get("functionId")) != null) {
			setFunctionId(__getInt(val));
		}
		if ((val = values.get("functionCode")) != null) {
			setFunctionCode(__getString(val));
		}
		if ((val = values.get("parentId")) != null) {
			setParentId(__getInt(val));
		}
		if ((val = values.get("functionName")) != null) {
			setFunctionName(__getString(val));
		}
		if ((val = values.get("applicationId")) != null) {
			setApplicationId(__getInt(val));
		}
		if ((val = values.get("functionType")) != null) {
			setFunctionType(__getInt(val));
		}
		if ((val = values.get("privilegeType")) != null) {
			setPrivilegeType(__getInt(val));
		}
		if ((val = values.get("enabled")) != null) {
			setEnabled(__getBoolean(val));
		}
	}

	protected Integer __function_id;
	protected String __function_code;
	protected Integer __parent_id;
	protected String __function_name;
	protected Integer __application_id;
	protected Integer __function_type;
	protected Integer __privilege_type;
	protected Boolean __enabled;
}
