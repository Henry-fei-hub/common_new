package com.delicacy.client.data;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.view.client.ProvidesKey;

public class BSystemProcessHumanTask extends GenericBase implements BaseFactory<BSystemProcessHumanTask> {

	public static final ProvidesKey<BSystemProcessHumanTask> KEY_PROVIDER = new ProvidesKey<BSystemProcessHumanTask>() {
		@Override
		public Object getKey(BSystemProcessHumanTask item) {
			return item == null ? null : item.getProcessHumanTaskId();
		}
	};

	public static BSystemProcessHumanTask newInstance() {
		return new BSystemProcessHumanTask();
	}

	@Override
	public BSystemProcessHumanTask make() {
		BSystemProcessHumanTask b = new BSystemProcessHumanTask();
		return b;
	}

	public final static java.lang.String CAPTION_PROCESS_HUMAN_TASK_ID = "主键编码";
	public final static java.lang.String CAPTION_BUSINESS_ID = "业务编码";
	public final static java.lang.String CAPTION_BUSINESS_NAME = "业务名称";
	public final static java.lang.String CAPTION_PROCESS_ACTIVITY_ID = "活动编码";
	public final static java.lang.String CAPTION_PROCESS_INSTANCE_ACTIVITY_ID = "活动实列编码";
	public final static java.lang.String CAPTION_PROCESS_ID = "流程编码";
	public final static java.lang.String CAPTION_PROCESS_INSTANCE_ID = "流程实列编码";
	public final static java.lang.String CAPTION_BACK_VIEW_NAME = "返回数据集";
	public final static java.lang.String CAPTION_EMPLOYEE_ID = "处理人";
	public final static java.lang.String CAPTION_OPERATE_TIME = "操作时间";
	public final static java.lang.String CAPTION_COMMIT_IDEAS = "提交意见";
	public final static java.lang.String CAPTION_STATUS = "状态";

	public Integer getProcessHumanTaskId() {
		return this.__process_human_task_id;
	}

	public void setProcessHumanTaskId(Integer value) {
		this.__process_human_task_id = value;
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

	public String getProcessActivityId() {
		return this.__process_activity_id;
	}

	public void setProcessActivityId(String value) {
		this.__process_activity_id = value;
	}

	public Integer getProcessInstanceActivityId() {
		return this.__process_instance_activity_id;
	}

	public void setProcessInstanceActivityId(Integer value) {
		this.__process_instance_activity_id = value;
	}

	public Integer getProcessId() {
		return this.__process_id;
	}

	public void setProcessId(Integer value) {
		this.__process_id = value;
	}

	public Integer getProcessInstanceId() {
		return this.__process_instance_id;
	}

	public void setProcessInstanceId(Integer value) {
		this.__process_instance_id = value;
	}

	public String getBackViewName() {
		return this.__back_view_name;
	}

	public void setBackViewName(String value) {
		this.__back_view_name = value;
	}

	public Integer getEmployeeId() {
		return this.__employee_id;
	}

	public void setEmployeeId(Integer value) {
		this.__employee_id = value;
	}

	public java.util.Date getOperateTime() {
		return this.__operate_time;
	}

	public void setOperateTime(java.util.Date value) {
		this.__operate_time = value;
	}

	public String getCommitIdeas() {
		return this.__commit_ideas;
	}

	public void setCommitIdeas(String value) {
		this.__commit_ideas = value;
	}

	public Integer getStatus() {
		return this.__status;
	}

	public void setStatus(Integer value) {
		this.__status = value;
	}

	@Override
	public java.lang.String toJSONString() {

		StringBuilder sb = new StringBuilder();
		int count = 0;
		if (getProcessHumanTaskId() != null) {
			sb.append(__wrapNumber(count++, "processHumanTaskId", getProcessHumanTaskId()));
		}
		if (getBusinessId() != null) {
			sb.append(__wrapNumber(count++, "businessId", getBusinessId()));
		}
		if (getBusinessName() != null) {
			sb.append(__wrapString(count++, "businessName", getBusinessName()));
		}
		if (getProcessActivityId() != null) {
			sb.append(__wrapString(count++, "processActivityId", getProcessActivityId()));
		}
		if (getProcessInstanceActivityId() != null) {
			sb.append(__wrapNumber(count++, "processInstanceActivityId", getProcessInstanceActivityId()));
		}
		if (getProcessId() != null) {
			sb.append(__wrapNumber(count++, "processId", getProcessId()));
		}
		if (getProcessInstanceId() != null) {
			sb.append(__wrapNumber(count++, "processInstanceId", getProcessInstanceId()));
		}
		if (getBackViewName() != null) {
			sb.append(__wrapString(count++, "backViewName", getBackViewName()));
		}
		if (getEmployeeId() != null) {
			sb.append(__wrapNumber(count++, "employeeId", getEmployeeId()));
		}
		if (getOperateTime() != null) {
			sb.append(__wrapDate(count++, "operateTime", getOperateTime()));
		}
		if (getCommitIdeas() != null) {
			sb.append(__wrapString(count++, "commitIdeas", getCommitIdeas()));
		}
		if (getStatus() != null) {
			sb.append(__wrapNumber(count++, "status", getStatus()));
		}
		return sb.toString();
	}

	@Override
	public void setDataFromJSON(JSONObject values) {
		JSONValue val;
		if ((val = values.get("processHumanTaskId")) != null) {
			setProcessHumanTaskId(__getInt(val));
		}
		if ((val = values.get("businessId")) != null) {
			setBusinessId(__getInt(val));
		}
		if ((val = values.get("businessName")) != null) {
			setBusinessName(__getString(val));
		}
		if ((val = values.get("processActivityId")) != null) {
			setProcessActivityId(__getString(val));
		}
		if ((val = values.get("processInstanceActivityId")) != null) {
			setProcessInstanceActivityId(__getInt(val));
		}
		if ((val = values.get("processId")) != null) {
			setProcessId(__getInt(val));
		}
		if ((val = values.get("processInstanceId")) != null) {
			setProcessInstanceId(__getInt(val));
		}
		if ((val = values.get("backViewName")) != null) {
			setBackViewName(__getString(val));
		}
		if ((val = values.get("employeeId")) != null) {
			setEmployeeId(__getInt(val));
		}
		if ((val = values.get("operateTime")) != null) {
			setOperateTime(__getDate(val));
		}
		if ((val = values.get("commitIdeas")) != null) {
			setCommitIdeas(__getString(val));
		}
		if ((val = values.get("status")) != null) {
			setStatus(__getInt(val));
		}
	}

	protected Integer __process_human_task_id;
	protected Integer __business_id;
	protected String __business_name;
	protected String __process_activity_id;
	protected Integer __process_instance_activity_id;
	protected Integer __process_id;
	protected Integer __process_instance_id;
	protected String __back_view_name;
	protected Integer __employee_id;
	protected java.util.Date __operate_time;
	protected String __commit_ideas;
	protected Integer __status;
}
