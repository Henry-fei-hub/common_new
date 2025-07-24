package com.delicacy.client.data;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.view.client.ProvidesKey;

public class BRoleFunction extends GenericBase implements BaseFactory<BRoleFunction> {

	public static final ProvidesKey<BRoleFunction> KEY_PROVIDER = new ProvidesKey<BRoleFunction>() {
		@Override
		public Object getKey(BRoleFunction item) {
			return item == null ? null : item.getRoleFunctionId();
		}
	};

	public static BRoleFunction newInstance() {
		return new BRoleFunction();
	}

	@Override
	public BRoleFunction make() {
		BRoleFunction b = new BRoleFunction();
		return b;
	}

	public final static java.lang.String CAPTION_ROLE_FUNCTION_ID = "角色功能编码";
	public final static java.lang.String CAPTION_ROLE_ID = "角色编码";
	public final static java.lang.String CAPTION_FUNCTION_ID = "功能编码";

	public Integer getRoleFunctionId() {
		return this.__role_function_id;
	}

	public void setRoleFunctionId(Integer value) {
		this.__role_function_id = value;
	}

	public Integer getRoleId() {
		return this.__role_id;
	}

	public void setRoleId(Integer value) {
		this.__role_id = value;
	}

	public Integer getFunctionId() {
		return this.__function_id;
	}

	public void setFunctionId(Integer value) {
		this.__function_id = value;
	}

	@Override
	public java.lang.String toJSONString() {

		StringBuilder sb = new StringBuilder();
		int count = 0;
		if (getRoleFunctionId() != null) {
			sb.append(__wrapNumber(count++, "roleFunctionId", getRoleFunctionId()));
		}
		if (getRoleId() != null) {
			sb.append(__wrapNumber(count++, "roleId", getRoleId()));
		}
		if (getFunctionId() != null) {
			sb.append(__wrapNumber(count++, "functionId", getFunctionId()));
		}
		return sb.toString();
	}

	@Override
	public void setDataFromJSON(JSONObject values) {
		JSONValue val;
		if ((val = values.get("roleFunctionId")) != null) {
			setRoleFunctionId(__getInt(val));
		}
		if ((val = values.get("roleId")) != null) {
			setRoleId(__getInt(val));
		}
		if ((val = values.get("functionId")) != null) {
			setFunctionId(__getInt(val));
		}
	}

	protected Integer __role_function_id;
	protected Integer __role_id;
	protected Integer __function_id;
}
