package com.delicacy.client.data;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.view.client.ProvidesKey;

public class BSystemProcessInstance extends GenericBase implements BaseFactory<BSystemProcessInstance> {

	public static final ProvidesKey<BSystemProcessInstance> KEY_PROVIDER = new ProvidesKey<BSystemProcessInstance>() {
		@Override
		public Object getKey(BSystemProcessInstance item) {
			return item == null ? null : item.getProcessInstanceId();
		}
	};

	public static BSystemProcessInstance newInstance() {
		return new BSystemProcessInstance();
	}

	@Override
	public BSystemProcessInstance make() {
		BSystemProcessInstance b = new BSystemProcessInstance();
		return b;
	}

	public final static java.lang.String CAPTION_PROCESS_INSTANCE_ID = "主键编码";
	public final static java.lang.String CAPTION_PROCESS_TYPE = "流程类型";
	public final static java.lang.String CAPTION_BUSINESS_ID = "业务编码";
	public final static java.lang.String CAPTION_BUSINESS_NAME = "业务名称";
	public final static java.lang.String CAPTION_PROCESS_ID = "流程编码";
	public final static java.lang.String CAPTION_PROCESS_ACTIVITY_ID = "当前活动节点";
	public final static java.lang.String CAPTION_PROCESS_INSTANCE_ACTIVITY_ID = "活动实列编码";
	public final static java.lang.String CAPTION_PROCESS_STATUS = "流程状态";
	public final static java.lang.String CAPTION_EMPLOYEE_ID = "创建人";
	public final static java.lang.String CAPTION_CREATE_TIME = "创建时间";

	public Integer getProcessInstanceId() {
		return this.__process_instance_id;
	}

	public void setProcessInstanceId(Integer value) {
		this.__process_instance_id = value;
	}

	public Integer getProcessType() {
		return this.__process_type;
	}

	public void setProcessType(Integer value) {
		this.__process_type = value;
	}

	public Integer getBusinessId() {
		return this.__business_id;
	}

	public void setBusinessId(Integer value) {
		this.__business_id = value;
	}

	public String getBusinessName() {
		return this.__business_name;
	}

	public void setBusinessName(String value) {
		this.__business_name = value;
	}

	public Integer getProcessId() {
		return this.__process_id;
	}

	public void setProcessId(Integer value) {
		this.__process_id = value;
	}

	public Integer getProcessActivityId() {
		return this.__process_activity_id;
	}

	public void setProcessActivityId(Integer value) {
		this.__process_activity_id = value;
	}

	public Integer getProcessInstanceActivityId() {
		return this.__process_instance_activity_id;
	}

	public void setProcessInstanceActivityId(Integer value) {
		this.__process_instance_activity_id = value;
	}

	public Integer getProcessStatus() {
		return this.__process_status;
	}

	public void setProcessStatus(Integer value) {
		this.__process_status = value;
	}

	public Integer getEmployeeId() {
		return this.__employee_id;
	}

	public void setEmployeeId(Integer value) {
		this.__employee_id = value;
	}

	public java.util.Date getCreateTime() {
		return this.__create_time;
	}

	public void setCreateTime(java.util.Date value) {
		this.__create_time = value;
	}

	@Override
	public java.lang.String toJSONString() {

		StringBuilder sb = new StringBuilder();
		int count = 0;
		if (getProcessInstanceId() != null) {
			sb.append(__wrapNumber(count++, "processInstanceId", getProcessInstanceId()));
		}
		if (getProcessType() != null) {
			sb.append(__wrapNumber(count++, "processType", getProcessType()));
		}
		if (getBusinessId() != null) {
			sb.append(__wrapNumber(count++, "businessId", getBusinessId()));
		}
		if (getBusinessName() != null) {
			sb.append(__wrapString(count++, "businessName", getBusinessName()));
		}
		if (getProcessId() != null) {
			sb.append(__wrapNumber(count++, "processId", getProcessId()));
		}
		if (getProcessActivityId() != null) {
			sb.append(__wrapNumber(count++, "processActivityId", getProcessActivityId()));
		}
		if (getProcessInstanceActivityId() != null) {
			sb.append(__wrapNumber(count++, "processInstanceActivityId", getProcessInstanceActivityId()));
		}
		if (getProcessStatus() != null) {
			sb.append(__wrapNumber(count++, "processStatus", getProcessStatus()));
		}
		if (getEmployeeId() != null) {
			sb.append(__wrapNumber(count++, "employeeId", getEmployeeId()));
		}
		if (getCreateTime() != null) {
			sb.append(__wrapDate(count++, "createTime", getCreateTime()));
		}
		return sb.toString();
	}

	@Override
	public void setDataFromJSON(JSONObject values) {
		JSONValue val;
		if ((val = values.get("processInstanceId")) != null) {
			setProcessInstanceId(__getInt(val));
		}
		if ((val = values.get("processType")) != null) {
			setProcessType(__getInt(val));
		}
		if ((val = values.get("businessId")) != null) {
			setBusinessId(__getInt(val));
		}
		if ((val = values.get("businessName")) != null) {
			setBusinessName(__getString(val));
		}
		if ((val = values.get("processId")) != null) {
			setProcessId(__getInt(val));
		}
		if ((val = values.get("processActivityId")) != null) {
			setProcessActivityId(__getInt(val));
		}
		if ((val = values.get("processInstanceActivityId")) != null) {
			setProcessInstanceActivityId(__getInt(val));
		}
		if ((val = values.get("processStatus")) != null) {
			setProcessStatus(__getInt(val));
		}
		if ((val = values.get("employeeId")) != null) {
			setEmployeeId(__getInt(val));
		}
		if ((val = values.get("createTime")) != null) {
			setCreateTime(__getDate(val));
		}
	}

	protected Integer __process_instance_id;
	protected Integer __process_type;
	protected Integer __business_id;
	protected String __business_name;
	protected Integer __process_id;
	protected Integer __process_activity_id;
	protected Integer __process_instance_activity_id;
	protected Integer __process_status;
	protected Integer __employee_id;
	protected java.util.Date __create_time;
}
