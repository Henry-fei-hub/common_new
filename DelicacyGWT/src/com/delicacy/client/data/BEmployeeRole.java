package com.delicacy.client.data;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.view.client.ProvidesKey;

public class BEmployeeRole extends GenericBase implements BaseFactory<BEmployeeRole> {

	public static final ProvidesKey<BEmployeeRole> KEY_PROVIDER = new ProvidesKey<BEmployeeRole>() {
		@Override
		public Object getKey(BEmployeeRole item) {
			return item == null ? null : item.getEmployeeRoleId();
		}
	};

	public static BEmployeeRole newInstance() {
		return new BEmployeeRole();
	}

	@Override
	public BEmployeeRole make() {
		BEmployeeRole b = new BEmployeeRole();
		return b;
	}

	public final static java.lang.String CAPTION_EMPLOYEE_ROLE_ID = "员工角色编码";
	public final static java.lang.String CAPTION_EMPLOYEE_ID = "员工编码";
	public final static java.lang.String CAPTION_ROLE_ID = "角色编码";
	public final static java.lang.String CAPTION_DEPARTMENT_ID = "部门";
	public final static java.lang.String CAPTION_IS_DEFAULT = "是默认角色";

	public Integer getEmployeeRoleId() {
		return this.__employee_role_id;
	}

	public void setEmployeeRoleId(Integer value) {
		this.__employee_role_id = value;
	}

	public Integer getEmployeeId() {
		return this.__employee_id;
	}

	public void setEmployeeId(Integer value) {
		this.__employee_id = value;
	}

	public Integer getRoleId() {
		return this.__role_id;
	}

	public void setRoleId(Integer value) {
		this.__role_id = value;
	}

	public Integer getDepartmentId() {
		return this.__department_id;
	}

	public void setDepartmentId(Integer value) {
		this.__department_id = value;
	}

	public Boolean getIsDefault() {
		return this.__is_default;
	}

	public void setIsDefault(Boolean value) {
		this.__is_default = value;
	}

	@Override
	public java.lang.String toJSONString() {

		StringBuilder sb = new StringBuilder();
		int count = 0;
		if (getEmployeeRoleId() != null) {
			sb.append(__wrapNumber(count++, "employeeRoleId", getEmployeeRoleId()));
		}
		if (getEmployeeId() != null) {
			sb.append(__wrapNumber(count++, "employeeId", getEmployeeId()));
		}
		if (getRoleId() != null) {
			sb.append(__wrapNumber(count++, "roleId", getRoleId()));
		}
		if (getDepartmentId() != null) {
			sb.append(__wrapNumber(count++, "departmentId", getDepartmentId()));
		}
		if (getIsDefault() != null) {
			sb.append(__wrapBoolean(count++, "isDefault", getIsDefault()));
		}
		return sb.toString();
	}

	@Override
	public void setDataFromJSON(JSONObject values) {
		JSONValue val;
		if ((val = values.get("employeeRoleId")) != null) {
			setEmployeeRoleId(__getInt(val));
		}
		if ((val = values.get("employeeId")) != null) {
			setEmployeeId(__getInt(val));
		}
		if ((val = values.get("roleId")) != null) {
			setRoleId(__getInt(val));
		}
		if ((val = values.get("departmentId")) != null) {
			setDepartmentId(__getInt(val));
		}
		if ((val = values.get("isDefault")) != null) {
			setIsDefault(__getBoolean(val));
		}
	}

	protected Integer __employee_role_id;
	protected Integer __employee_id;
	protected Integer __role_id;
	protected Integer __department_id;
	protected Boolean __is_default;
}
