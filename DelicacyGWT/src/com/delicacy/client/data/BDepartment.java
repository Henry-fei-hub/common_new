package com.delicacy.client.data;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.view.client.ProvidesKey;

public class BDepartment extends GenericBase implements BaseFactory<BDepartment> {

	public static final ProvidesKey<BDepartment> KEY_PROVIDER = new ProvidesKey<BDepartment>() {
		@Override
		public Object getKey(BDepartment item) {
			return item == null ? null : item.getDepartmentId();
		}
	};

	public static BDepartment newInstance() {
		return new BDepartment();
	}

	@Override
	public BDepartment make() {
		BDepartment b = new BDepartment();
		return b;
	}

	public final static java.lang.String CAPTION_DEPARTMENT_ID = "部门编码";
	public final static java.lang.String CAPTION_DEPARTMENT_NAME = "部门名称";
	public final static java.lang.String CAPTION_ABBREVIATION = "部门名称缩写";
	public final static java.lang.String CAPTION_MANAGER_ID = "部门负责编码";
	public final static java.lang.String CAPTION_MANAGER_NAME = "部门负责人姓名";
	public final static java.lang.String CAPTION_PARENT_ID = "上级部门";
	public final static java.lang.String CAPTION_ENABLED = "是否有效";
	public final static java.lang.String CAPTION_ORIGINAL_ID = "";
	public final static java.lang.String CAPTION_PLATE_ID = "板块";
	public final static java.lang.String CAPTION_IS_HEADCOUNT = "总部";

	public Integer getDepartmentId() {
		return this.__department_id;
	}

	public void setDepartmentId(Integer value) {
		this.__department_id = value;
	}

	public String getDepartmentName() {
		return this.__department_name;
	}

	public void setDepartmentName(String value) {
		this.__department_name = value;
	}

	public String getAbbreviation() {
		return this.__abbreviation;
	}

	public void setAbbreviation(String value) {
		this.__abbreviation = value;
	}

	public Integer getManagerId() {
		return this.__manager_id;
	}

	public void setManagerId(Integer value) {
		this.__manager_id = value;
	}

	public String getManagerName() {
		return this.__manager_name;
	}

	public void setManagerName(String value) {
		this.__manager_name = value;
	}

	public Integer getParentId() {
		return this.__parent_id;
	}

	public void setParentId(Integer value) {
		this.__parent_id = value;
	}

	public Boolean getEnabled() {
		return this.__enabled;
	}

	public void setEnabled(Boolean value) {
		this.__enabled = value;
	}

	public Integer getOriginalId() {
		return this.__original_id;
	}

	public void setOriginalId(Integer value) {
		this.__original_id = value;
	}

	public Integer getPlateId() {
		return this.__plate_id;
	}

	public void setPlateId(Integer value) {
		this.__plate_id = value;
	}

	public Boolean getIsHeadcount() {
		return this.__is_headcount;
	}

	public void setIsHeadcount(Boolean value) {
		this.__is_headcount = value;
	}

	@Override
	public java.lang.String toJSONString() {

		StringBuilder sb = new StringBuilder();
		int count = 0;
		if (getDepartmentId() != null) {
			sb.append(__wrapNumber(count++, "departmentId", getDepartmentId()));
		}
		if (getDepartmentName() != null) {
			sb.append(__wrapString(count++, "departmentName", getDepartmentName()));
		}
		if (getAbbreviation() != null) {
			sb.append(__wrapString(count++, "abbreviation", getAbbreviation()));
		}
		if (getManagerId() != null) {
			sb.append(__wrapNumber(count++, "managerId", getManagerId()));
		}
		if (getManagerName() != null) {
			sb.append(__wrapString(count++, "managerName", getManagerName()));
		}
		if (getParentId() != null) {
			sb.append(__wrapNumber(count++, "parentId", getParentId()));
		}
		if (getEnabled() != null) {
			sb.append(__wrapBoolean(count++, "enabled", getEnabled()));
		}
		if (getOriginalId() != null) {
			sb.append(__wrapNumber(count++, "originalId", getOriginalId()));
		}
		if (getPlateId() != null) {
			sb.append(__wrapNumber(count++, "plateId", getPlateId()));
		}
		if (getIsHeadcount() != null) {
			sb.append(__wrapBoolean(count++, "isHeadcount", getIsHeadcount()));
		}
		return sb.toString();
	}

	@Override
	public void setDataFromJSON(JSONObject values) {
		JSONValue val;
		if ((val = values.get("departmentId")) != null) {
			setDepartmentId(__getInt(val));
		}
		if ((val = values.get("departmentName")) != null) {
			setDepartmentName(__getString(val));
		}
		if ((val = values.get("abbreviation")) != null) {
			setAbbreviation(__getString(val));
		}
		if ((val = values.get("managerId")) != null) {
			setManagerId(__getInt(val));
		}
		if ((val = values.get("managerName")) != null) {
			setManagerName(__getString(val));
		}
		if ((val = values.get("parentId")) != null) {
			setParentId(__getInt(val));
		}
		if ((val = values.get("enabled")) != null) {
			setEnabled(__getBoolean(val));
		}
		if ((val = values.get("originalId")) != null) {
			setOriginalId(__getInt(val));
		}
		if ((val = values.get("plateId")) != null) {
			setPlateId(__getInt(val));
		}
		if ((val = values.get("isHeadcount")) != null) {
			setIsHeadcount(__getBoolean(val));
		}
	}

	protected Integer __department_id;
	protected String __department_name;
	protected String __abbreviation;
	protected Integer __manager_id;
	protected String __manager_name;
	protected Integer __parent_id;
	protected Boolean __enabled;
	protected Integer __original_id;
	protected Integer __plate_id;
	protected Boolean __is_headcount;
}
