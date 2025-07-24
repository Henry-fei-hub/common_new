package com.delicacy.client.data;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.view.client.ProvidesKey;

public class BSystemProcessActivity extends GenericBase implements BaseFactory<BSystemProcessActivity> {

	public static final ProvidesKey<BSystemProcessActivity> KEY_PROVIDER = new ProvidesKey<BSystemProcessActivity>() {
		@Override
		public Object getKey(BSystemProcessActivity item) {
			return item == null ? null : item.getProcessActivityId();
		}
	};

	public static BSystemProcessActivity newInstance() {
		return new BSystemProcessActivity();
	}

	@Override
	public BSystemProcessActivity make() {
		BSystemProcessActivity b = new BSystemProcessActivity();
		return b;
	}

	public final static java.lang.String CAPTION_PROCESS_ACTIVITY_ID = "编码";
	public final static java.lang.String CAPTION_PROCESS_ID = "流程编码";
	public final static java.lang.String CAPTION_ACTIVITY_TYPE = "活动节点类型";
	public final static java.lang.String CAPTION_ACTIVITY_NAME = "活动节点名称";
	public final static java.lang.String CAPTION_ACTIVITY_SERIAL_NO = "内部动作序号";
	public final static java.lang.String CAPTION_POSX = "X坐标";
	public final static java.lang.String CAPTION_POSY = "Y坐标";
	public final static java.lang.String CAPTION_TIME_OUT_ACTION = "活动时限";
	public final static java.lang.String CAPTION_EXECUTOR_TYPE = "执行人类型";
	public final static java.lang.String CAPTION_EMPLOYEE_ID = "";
	public final static java.lang.String CAPTION_DEPARTMENT_ID = "该节点是由某个职务的人来做";
	public final static java.lang.String CAPTION_ROLE_ID = "该节点是由某个角色的人来做";
	public final static java.lang.String CAPTION_IF_ALLOW_OVER = "是否允许跳过";
	public final static java.lang.String CAPTION_IS_ENABLE = "是否可用";

	public Integer getProcessActivityId() {
		return this.__process_activity_id;
	}

	public void setProcessActivityId(Integer value) {
		this.__process_activity_id = value;
	}

	public Integer getProcessId() {
		return this.__process_id;
	}

	public void setProcessId(Integer value) {
		this.__process_id = value;
	}

	public Integer getActivityType() {
		return this.__activity_type;
	}

	public void setActivityType(Integer value) {
		this.__activity_type = value;
	}

	public String getActivityName() {
		return this.__activity_name;
	}

	public void setActivityName(String value) {
		this.__activity_name = value;
	}

	public Integer getActivitySerialNo() {
		return this.__activity_serial_no;
	}

	public void setActivitySerialNo(Integer value) {
		this.__activity_serial_no = value;
	}

	public Integer getPosx() {
		return this.__posx;
	}

	public void setPosx(Integer value) {
		this.__posx = value;
	}

	public Integer getPosy() {
		return this.__posy;
	}

	public void setPosy(Integer value) {
		this.__posy = value;
	}

	public Integer getTimeOutAction() {
		return this.__time_out_action;
	}

	public void setTimeOutAction(Integer value) {
		this.__time_out_action = value;
	}

	public Integer getExecutorType() {
		return this.__executor_type;
	}

	public void setExecutorType(Integer value) {
		this.__executor_type = value;
	}

	public Integer getEmployeeId() {
		return this.__employee_id;
	}

	public void setEmployeeId(Integer value) {
		this.__employee_id = value;
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

	public Boolean getIfAllowOver() {
		return this.__if_allow_over;
	}

	public void setIfAllowOver(Boolean value) {
		this.__if_allow_over = value;
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
		if (getProcessActivityId() != null) {
			sb.append(__wrapNumber(count++, "processActivityId", getProcessActivityId()));
		}
		if (getProcessId() != null) {
			sb.append(__wrapNumber(count++, "processId", getProcessId()));
		}
		if (getActivityType() != null) {
			sb.append(__wrapNumber(count++, "activityType", getActivityType()));
		}
		if (getActivityName() != null) {
			sb.append(__wrapString(count++, "activityName", getActivityName()));
		}
		if (getActivitySerialNo() != null) {
			sb.append(__wrapNumber(count++, "activitySerialNo", getActivitySerialNo()));
		}
		if (getPosx() != null) {
			sb.append(__wrapNumber(count++, "posx", getPosx()));
		}
		if (getPosy() != null) {
			sb.append(__wrapNumber(count++, "posy", getPosy()));
		}
		if (getTimeOutAction() != null) {
			sb.append(__wrapNumber(count++, "timeOutAction", getTimeOutAction()));
		}
		if (getExecutorType() != null) {
			sb.append(__wrapNumber(count++, "executorType", getExecutorType()));
		}
		if (getEmployeeId() != null) {
			sb.append(__wrapNumber(count++, "employeeId", getEmployeeId()));
		}
		if (getDepartmentId() != null) {
			sb.append(__wrapNumber(count++, "departmentId", getDepartmentId()));
		}
		if (getRoleId() != null) {
			sb.append(__wrapNumber(count++, "roleId", getRoleId()));
		}
		if (getIfAllowOver() != null) {
			sb.append(__wrapBoolean(count++, "ifAllowOver", getIfAllowOver()));
		}
		if (getIsEnable() != null) {
			sb.append(__wrapBoolean(count++, "isEnable", getIsEnable()));
		}
		return sb.toString();
	}

	@Override
	public void setDataFromJSON(JSONObject values) {
		JSONValue val;
		if ((val = values.get("processActivityId")) != null) {
			setProcessActivityId(__getInt(val));
		}
		if ((val = values.get("processId")) != null) {
			setProcessId(__getInt(val));
		}
		if ((val = values.get("activityType")) != null) {
			setActivityType(__getInt(val));
		}
		if ((val = values.get("activityName")) != null) {
			setActivityName(__getString(val));
		}
		if ((val = values.get("activitySerialNo")) != null) {
			setActivitySerialNo(__getInt(val));
		}
		if ((val = values.get("posx")) != null) {
			setPosx(__getInt(val));
		}
		if ((val = values.get("posy")) != null) {
			setPosy(__getInt(val));
		}
		if ((val = values.get("timeOutAction")) != null) {
			setTimeOutAction(__getInt(val));
		}
		if ((val = values.get("executorType")) != null) {
			setExecutorType(__getInt(val));
		}
		if ((val = values.get("employeeId")) != null) {
			setEmployeeId(__getInt(val));
		}
		if ((val = values.get("departmentId")) != null) {
			setDepartmentId(__getInt(val));
		}
		if ((val = values.get("roleId")) != null) {
			setRoleId(__getInt(val));
		}
		if ((val = values.get("ifAllowOver")) != null) {
			setIfAllowOver(__getBoolean(val));
		}
		if ((val = values.get("isEnable")) != null) {
			setIsEnable(__getBoolean(val));
		}
	}

	protected Integer __process_activity_id;
	protected Integer __process_id;
	protected Integer __activity_type;
	protected String __activity_name;
	protected Integer __activity_serial_no;
	protected Integer __posx;
	protected Integer __posy;
	protected Integer __time_out_action;
	protected Integer __executor_type;
	protected Integer __employee_id;
	protected Integer __department_id;
	protected Integer __role_id;
	protected Boolean __if_allow_over;
	protected Boolean __is_enable;
}
