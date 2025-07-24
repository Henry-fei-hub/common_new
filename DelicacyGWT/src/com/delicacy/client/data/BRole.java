package com.delicacy.client.data;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.view.client.ProvidesKey;

public class BRole extends GenericBase implements BaseFactory<BRole> {

	public static final ProvidesKey<BRole> KEY_PROVIDER = new ProvidesKey<BRole>() {
		@Override
		public Object getKey(BRole item) {
			return item == null ? null : item.getRoleId();
		}
	};

	public static BRole newInstance() {
		return new BRole();
	}

	@Override
	public BRole make() {
		BRole b = new BRole();
		return b;
	}

	public final static java.lang.String CAPTION_ROLE_ID = "角色编码";
	public final static java.lang.String CAPTION_ROLE_NAME = "角色名称";
	public final static java.lang.String CAPTION_APPLICATION_ID = "应用系统代码";
	public final static java.lang.String CAPTION_ROLE_TYPE = "角色类型";
	public final static java.lang.String CAPTION_ENABLED = "是否有效";
	public final static java.lang.String CAPTION_HAS_APPROVAL_RIGHT = "能审批";

	public Integer getRoleId() {
		return this.__role_id;
	}

	public void setRoleId(Integer value) {
		this.__role_id = value;
	}

	public String getRoleName() {
		return this.__role_name;
	}

	public void setRoleName(String value) {
		this.__role_name = value;
	}

	public Integer getApplicationId() {
		return this.__application_id;
	}

	public void setApplicationId(Integer value) {
		this.__application_id = value;
	}

	public Integer getRoleType() {
		return this.__role_type;
	}

	public void setRoleType(Integer value) {
		this.__role_type = value;
	}

	public Boolean getEnabled() {
		return this.__enabled;
	}

	public void setEnabled(Boolean value) {
		this.__enabled = value;
	}

	public Boolean getHasApprovalRight() {
		return this.__has_approval_right;
	}

	public void setHasApprovalRight(Boolean value) {
		this.__has_approval_right = value;
	}

	@Override
	public java.lang.String toJSONString() {

		StringBuilder sb = new StringBuilder();
		int count = 0;
		if (getRoleId() != null) {
			sb.append(__wrapNumber(count++, "roleId", getRoleId()));
		}
		if (getRoleName() != null) {
			sb.append(__wrapString(count++, "roleName", getRoleName()));
		}
		if (getApplicationId() != null) {
			sb.append(__wrapNumber(count++, "applicationId", getApplicationId()));
		}
		if (getRoleType() != null) {
			sb.append(__wrapNumber(count++, "roleType", getRoleType()));
		}
		if (getEnabled() != null) {
			sb.append(__wrapBoolean(count++, "enabled", getEnabled()));
		}
		if (getHasApprovalRight() != null) {
			sb.append(__wrapBoolean(count++, "hasApprovalRight", getHasApprovalRight()));
		}
		return sb.toString();
	}

	@Override
	public void setDataFromJSON(JSONObject values) {
		JSONValue val;
		if ((val = values.get("roleId")) != null) {
			setRoleId(__getInt(val));
		}
		if ((val = values.get("roleName")) != null) {
			setRoleName(__getString(val));
		}
		if ((val = values.get("applicationId")) != null) {
			setApplicationId(__getInt(val));
		}
		if ((val = values.get("roleType")) != null) {
			setRoleType(__getInt(val));
		}
		if ((val = values.get("enabled")) != null) {
			setEnabled(__getBoolean(val));
		}
		if ((val = values.get("hasApprovalRight")) != null) {
			setHasApprovalRight(__getBoolean(val));
		}
	}

	protected Integer __role_id;
	protected String __role_name;
	protected Integer __application_id;
	protected Integer __role_type;
	protected Boolean __enabled;
	protected Boolean __has_approval_right;
}
