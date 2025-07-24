package com.delicacy.client.data;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.view.client.ProvidesKey;

public class BSystemProcess extends GenericBase implements BaseFactory<BSystemProcess> {

	public static final ProvidesKey<BSystemProcess> KEY_PROVIDER = new ProvidesKey<BSystemProcess>() {
		@Override
		public Object getKey(BSystemProcess item) {
			return item == null ? null : item.getProcessId();
		}
	};

	public static BSystemProcess newInstance() {
		return new BSystemProcess();
	}

	@Override
	public BSystemProcess make() {
		BSystemProcess b = new BSystemProcess();
		return b;
	}

	public final static java.lang.String CAPTION_PROCESS_ID = "主键编码";
	public final static java.lang.String CAPTION_PROCESS_TYPE_ID = "流程类型编码";
	public final static java.lang.String CAPTION_DEPARTMENT_ID = "归属部门";
	public final static java.lang.String CAPTION_INCLUDE_DEPARTMENT_ID = "归属部门(包括下级部门)";
	public final static java.lang.String CAPTION_CREATE_EMPLOYEE_ID = "创建人";
	public final static java.lang.String CAPTION_CREATE_TIME = "创建时间";
	public final static java.lang.String CAPTION_PROCESS_NAME = "流程名称";
	public final static java.lang.String CAPTION_COUNTERSIGN = "是否会签";
	public final static java.lang.String CAPTION_HOLD_DEPARTMENT_ID = "归档部门";
	public final static java.lang.String CAPTION_HOLD_DUTY_ID = "归档职务";
	public final static java.lang.String CAPTION_DESCRIPTION = "描述";
	public final static java.lang.String CAPTION_ENABLE = "是否可用";

	public Integer getProcessId() {
		return this.__process_id;
	}

	public void setProcessId(Integer value) {
		this.__process_id = value;
	}

	public Integer getProcessTypeId() {
		return this.__process_type_id;
	}

	public void setProcessTypeId(Integer value) {
		this.__process_type_id = value;
	}

	public Integer getDepartmentId() {
		return this.__department_id;
	}

	public void setDepartmentId(Integer value) {
		this.__department_id = value;
	}

	public Integer getIncludeDepartmentId() {
		return this.__include_department_id;
	}

	public void setIncludeDepartmentId(Integer value) {
		this.__include_department_id = value;
	}

	public Integer getCreateEmployeeId() {
		return this.__create_employee_id;
	}

	public void setCreateEmployeeId(Integer value) {
		this.__create_employee_id = value;
	}

	public java.util.Date getCreateTime() {
		return this.__create_time;
	}

	public void setCreateTime(java.util.Date value) {
		this.__create_time = value;
	}

	public String getProcessName() {
		return this.__process_name;
	}

	public void setProcessName(String value) {
		this.__process_name = value;
	}

	public Boolean getCountersign() {
		return this.__countersign;
	}

	public void setCountersign(Boolean value) {
		this.__countersign = value;
	}

	public Integer getHoldDepartmentId() {
		return this.__hold_department_id;
	}

	public void setHoldDepartmentId(Integer value) {
		this.__hold_department_id = value;
	}

	public Integer getHoldDutyId() {
		return this.__hold_duty_id;
	}

	public void setHoldDutyId(Integer value) {
		this.__hold_duty_id = value;
	}

	public String getDescription() {
		return this.__description;
	}

	public void setDescription(String value) {
		this.__description = value;
	}

	public Boolean getEnable() {
		return this.__enable;
	}

	public void setEnable(Boolean value) {
		this.__enable = value;
	}

	@Override
	public java.lang.String toJSONString() {

		StringBuilder sb = new StringBuilder();
		int count = 0;
		if (getProcessId() != null) {
			sb.append(__wrapNumber(count++, "processId", getProcessId()));
		}
		if (getProcessTypeId() != null) {
			sb.append(__wrapNumber(count++, "processTypeId", getProcessTypeId()));
		}
		if (getDepartmentId() != null) {
			sb.append(__wrapNumber(count++, "departmentId", getDepartmentId()));
		}
		if (getIncludeDepartmentId() != null) {
			sb.append(__wrapNumber(count++, "includeDepartmentId", getIncludeDepartmentId()));
		}
		if (getCreateEmployeeId() != null) {
			sb.append(__wrapNumber(count++, "createEmployeeId", getCreateEmployeeId()));
		}
		if (getCreateTime() != null) {
			sb.append(__wrapDate(count++, "createTime", getCreateTime()));
		}
		if (getProcessName() != null) {
			sb.append(__wrapString(count++, "processName", getProcessName()));
		}
		if (getCountersign() != null) {
			sb.append(__wrapBoolean(count++, "countersign", getCountersign()));
		}
		if (getHoldDepartmentId() != null) {
			sb.append(__wrapNumber(count++, "holdDepartmentId", getHoldDepartmentId()));
		}
		if (getHoldDutyId() != null) {
			sb.append(__wrapNumber(count++, "holdDutyId", getHoldDutyId()));
		}
		if (getDescription() != null) {
			sb.append(__wrapString(count++, "description", getDescription()));
		}
		if (getEnable() != null) {
			sb.append(__wrapBoolean(count++, "enable", getEnable()));
		}
		return sb.toString();
	}

	@Override
	public void setDataFromJSON(JSONObject values) {
		JSONValue val;
		if ((val = values.get("processId")) != null) {
			setProcessId(__getInt(val));
		}
		if ((val = values.get("processTypeId")) != null) {
			setProcessTypeId(__getInt(val));
		}
		if ((val = values.get("departmentId")) != null) {
			setDepartmentId(__getInt(val));
		}
		if ((val = values.get("includeDepartmentId")) != null) {
			setIncludeDepartmentId(__getInt(val));
		}
		if ((val = values.get("createEmployeeId")) != null) {
			setCreateEmployeeId(__getInt(val));
		}
		if ((val = values.get("createTime")) != null) {
			setCreateTime(__getDate(val));
		}
		if ((val = values.get("processName")) != null) {
			setProcessName(__getString(val));
		}
		if ((val = values.get("countersign")) != null) {
			setCountersign(__getBoolean(val));
		}
		if ((val = values.get("holdDepartmentId")) != null) {
			setHoldDepartmentId(__getInt(val));
		}
		if ((val = values.get("holdDutyId")) != null) {
			setHoldDutyId(__getInt(val));
		}
		if ((val = values.get("description")) != null) {
			setDescription(__getString(val));
		}
		if ((val = values.get("enable")) != null) {
			setEnable(__getBoolean(val));
		}
	}

	protected Integer __process_id;
	protected Integer __process_type_id;
	protected Integer __department_id;
	protected Integer __include_department_id;
	protected Integer __create_employee_id;
	protected java.util.Date __create_time;
	protected String __process_name;
	protected Boolean __countersign;
	protected Integer __hold_department_id;
	protected Integer __hold_duty_id;
	protected String __description;
	protected Boolean __enable;
}
