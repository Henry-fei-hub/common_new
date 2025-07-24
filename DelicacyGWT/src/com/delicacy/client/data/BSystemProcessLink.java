package com.delicacy.client.data;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.view.client.ProvidesKey;

public class BSystemProcessLink extends GenericBase implements BaseFactory<BSystemProcessLink> {

	public static final ProvidesKey<BSystemProcessLink> KEY_PROVIDER = new ProvidesKey<BSystemProcessLink>() {
		@Override
		public Object getKey(BSystemProcessLink item) {
			return item == null ? null : item.getProcessLinkId();
		}
	};

	public static BSystemProcessLink newInstance() {
		return new BSystemProcessLink();
	}

	@Override
	public BSystemProcessLink make() {
		BSystemProcessLink b = new BSystemProcessLink();
		return b;
	}

	public final static java.lang.String CAPTION_PROCESS_LINK_ID = "主键编码";
	public final static java.lang.String CAPTION_PROCESS_ID = "流程编码";
	public final static java.lang.String CAPTION_CONDITION = "条件";
	public final static java.lang.String CAPTION_PROCESS_ACTIVITY_ID = "流程活动编码";
	public final static java.lang.String CAPTION_TO_PROCESS_ACTIVITY_ID = "流向的活动编码";

	public Integer getProcessLinkId() {
		return this.__process_link_id;
	}

	public void setProcessLinkId(Integer value) {
		this.__process_link_id = value;
	}

	public Integer getProcessId() {
		return this.__process_id;
	}

	public void setProcessId(Integer value) {
		this.__process_id = value;
	}

	public String getCondition() {
		return this.__condition;
	}

	public void setCondition(String value) {
		this.__condition = value;
	}

	public Integer getProcessActivityId() {
		return this.__process_activity_id;
	}

	public void setProcessActivityId(Integer value) {
		this.__process_activity_id = value;
	}

	public Integer getToProcessActivityId() {
		return this.__to_process_activity_id;
	}

	public void setToProcessActivityId(Integer value) {
		this.__to_process_activity_id = value;
	}

	@Override
	public java.lang.String toJSONString() {

		StringBuilder sb = new StringBuilder();
		int count = 0;
		if (getProcessLinkId() != null) {
			sb.append(__wrapNumber(count++, "processLinkId", getProcessLinkId()));
		}
		if (getProcessId() != null) {
			sb.append(__wrapNumber(count++, "processId", getProcessId()));
		}
		if (getCondition() != null) {
			sb.append(__wrapString(count++, "condition", getCondition()));
		}
		if (getProcessActivityId() != null) {
			sb.append(__wrapNumber(count++, "processActivityId", getProcessActivityId()));
		}
		if (getToProcessActivityId() != null) {
			sb.append(__wrapNumber(count++, "toProcessActivityId", getToProcessActivityId()));
		}
		return sb.toString();
	}

	@Override
	public void setDataFromJSON(JSONObject values) {
		JSONValue val;
		if ((val = values.get("processLinkId")) != null) {
			setProcessLinkId(__getInt(val));
		}
		if ((val = values.get("processId")) != null) {
			setProcessId(__getInt(val));
		}
		if ((val = values.get("condition")) != null) {
			setCondition(__getString(val));
		}
		if ((val = values.get("processActivityId")) != null) {
			setProcessActivityId(__getInt(val));
		}
		if ((val = values.get("toProcessActivityId")) != null) {
			setToProcessActivityId(__getInt(val));
		}
	}

	protected Integer __process_link_id;
	protected Integer __process_id;
	protected String __condition;
	protected Integer __process_activity_id;
	protected Integer __to_process_activity_id;
}
