package com.delicacy.client.data;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.view.client.ProvidesKey;

public class BDepartmentRole extends GenericBase implements BaseFactory<BDepartmentRole> {

	public static final ProvidesKey<BDepartmentRole> KEY_PROVIDER = new ProvidesKey<BDepartmentRole>() {
		@Override
		public Object getKey(BDepartmentRole item) {
			return item == null ? null : item.getDepartmentRoleId();
		}
	};

	public static BDepartmentRole newInstance() {
		return new BDepartmentRole();
	}

	@Override
	public BDepartmentRole make() {
		BDepartmentRole b = new BDepartmentRole();
		return b;
	}

	public final static java.lang.String CAPTION_DEPARTMENT_ROLE_ID = "部门角色代码";
	public final static java.lang.String CAPTION_DEPARTMENT_ID = "部门";
	public final static java.lang.String CAPTION_ROLE_ID = "角色";

	public Integer getDepartmentRoleId() {
		return this.__department_role_id;
	}

	public void setDepartmentRoleId(Integer value) {
		this.__department_role_id = value;
	}

	public Integer getDepartmentId() {
		return this.__department_id;
	}

	public void setDepartmentId(Integer value) {
		this.__department_id = value;
	}

	public Integer getRoleId() {
		return this.__role_id;
	}

	public void setRoleId(Integer value) {
		this.__role_id = value;
	}

	@Override
	public java.lang.String toJSONString() {

		StringBuilder sb = new StringBuilder();
		int count = 0;
		if (getDepartmentRoleId() != null) {
			sb.append(__wrapNumber(count++, "departmentRoleId", getDepartmentRoleId()));
		}
		if (getDepartmentId() != null) {
			sb.append(__wrapNumber(count++, "departmentId", getDepartmentId()));
		}
		if (getRoleId() != null) {
			sb.append(__wrapNumber(count++, "roleId", getRoleId()));
		}
		return sb.toString();
	}

	@Override
	public void setDataFromJSON(JSONObject values) {
		JSONValue val;
		if ((val = values.get("departmentRoleId")) != null) {
			setDepartmentRoleId(__getInt(val));
		}
		if ((val = values.get("departmentId")) != null) {
			setDepartmentId(__getInt(val));
		}
		if ((val = values.get("roleId")) != null) {
			setRoleId(__getInt(val));
		}
	}

	protected Integer __department_role_id;
	protected Integer __department_id;
	protected Integer __role_id;
}
