package com.delicacy.client.data;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.view.client.ProvidesKey;

public class BDepartmentGroup extends GenericBase implements BaseFactory<BDepartmentGroup> {

	public static final ProvidesKey<BDepartmentGroup> KEY_PROVIDER = new ProvidesKey<BDepartmentGroup>() {
		@Override
		public Object getKey(BDepartmentGroup item) {
			return item == null ? null : item.getDepartmentGroupId();
		}
	};

	public static BDepartmentGroup newInstance() {
		return new BDepartmentGroup();
	}

	@Override
	public BDepartmentGroup make() {
		BDepartmentGroup b = new BDepartmentGroup();
		return b;
	}

	public final static java.lang.String CAPTION_DEPARTMENT_GROUP_ID = "部门组编码";
	public final static java.lang.String CAPTION_GROUP_NAME = "部门组名";
	public final static java.lang.String CAPTION_IS_COMPANY = "是公司";

	public Integer getDepartmentGroupId() {
		return this.__department_group_id;
	}

	public void setDepartmentGroupId(Integer value) {
		this.__department_group_id = value;
	}

	public String getGroupName() {
		return this.__group_name;
	}

	public void setGroupName(String value) {
		this.__group_name = value;
	}

	public Boolean getIsCompany() {
		return this.__is_company;
	}

	public void setIsCompany(Boolean value) {
		this.__is_company = value;
	}

	@Override
	public java.lang.String toJSONString() {

		StringBuilder sb = new StringBuilder();
		int count = 0;
		if (getDepartmentGroupId() != null) {
			sb.append(__wrapNumber(count++, "departmentGroupId", getDepartmentGroupId()));
		}
		if (getGroupName() != null) {
			sb.append(__wrapString(count++, "groupName", getGroupName()));
		}
		if (getIsCompany() != null) {
			sb.append(__wrapBoolean(count++, "isCompany", getIsCompany()));
		}
		return sb.toString();
	}

	@Override
	public void setDataFromJSON(JSONObject values) {
		JSONValue val;
		if ((val = values.get("departmentGroupId")) != null) {
			setDepartmentGroupId(__getInt(val));
		}
		if ((val = values.get("groupName")) != null) {
			setGroupName(__getString(val));
		}
		if ((val = values.get("isCompany")) != null) {
			setIsCompany(__getBoolean(val));
		}
	}

	protected Integer __department_group_id;
	protected String __group_name;
	protected Boolean __is_company;
}
