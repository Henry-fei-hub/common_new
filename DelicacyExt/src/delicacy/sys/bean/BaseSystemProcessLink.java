package delicacy.sys.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import delicacy.common.BaseFactory;
import delicacy.common.GenericBase;

public class BaseSystemProcessLink extends GenericBase implements BaseFactory<BaseSystemProcessLink>, Comparable<BaseSystemProcessLink> 
{


	public static BaseSystemProcessLink newInstance(){
		return new BaseSystemProcessLink();
	}

	@Override
	public BaseSystemProcessLink make(){
		BaseSystemProcessLink b = new BaseSystemProcessLink();
		return b;
	}

	public final static java.lang.String CS_PROCESS_LINK_ID = "process_link_id" ;
	public final static java.lang.String CS_PROCESS_ID = "process_id" ;
	public final static java.lang.String CS_CONDITION = "condition" ;
	public final static java.lang.String CS_PROCESS_ACTIVITY_ID = "process_activity_id" ;
	public final static java.lang.String CS_TO_PROCESS_ACTIVITY_ID = "to_process_activity_id" ;
	public final static java.lang.String CS_ORGANIZATION_ID = "organization_id" ;

	public final static java.lang.String ALL_CAPTIONS = "主键编码,流程编码,条件,流程活动编码,流向的活动编码,组织机构代码";

	public java.lang.Integer getProcessLinkId() {
		return this.__process_link_id;
	}

	public void setProcessLinkId( java.lang.Integer value ) {
		this.__process_link_id = value;
	}

	public java.lang.Integer getProcessId() {
		return this.__process_id;
	}

	public void setProcessId( java.lang.Integer value ) {
		this.__process_id = value;
	}

	public java.lang.String getCondition() {
		return this.__condition;
	}

	public void setCondition( java.lang.String value ) {
		this.__condition = value;
	}

	public java.lang.Integer getProcessActivityId() {
		return this.__process_activity_id;
	}

	public void setProcessActivityId( java.lang.Integer value ) {
		this.__process_activity_id = value;
	}

	public java.lang.Integer getToProcessActivityId() {
		return this.__to_process_activity_id;
	}

	public void setToProcessActivityId( java.lang.Integer value ) {
		this.__to_process_activity_id = value;
	}

	public java.lang.Integer getOrganizationId() {
		return this.__organization_id;
	}

	public void setOrganizationId( java.lang.Integer value ) {
		this.__organization_id = value;
	}

	public void cloneCopy(BaseSystemProcessLink __bean){
		__bean.setProcessLinkId(getProcessLinkId());
		__bean.setProcessId(getProcessId());
		__bean.setCondition(getCondition());
		__bean.setProcessActivityId(getProcessActivityId());
		__bean.setToProcessActivityId(getToProcessActivityId());
		__bean.setOrganizationId(getOrganizationId());
	}

	public java.lang.String toCSVString() {

		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
		StringBuilder sb = new StringBuilder();
		sb.append(getProcessLinkId() == null ? "" : getProcessLinkId());
		sb.append(",");
		sb.append(getProcessId() == null ? "" : getProcessId());
		sb.append(",");
		sb.append(getCondition() == null ? "" : getCondition());
		sb.append(",");
		sb.append(getProcessActivityId() == null ? "" : getProcessActivityId());
		sb.append(",");
		sb.append(getToProcessActivityId() == null ? "" : getToProcessActivityId());
		sb.append(",");
		sb.append(getOrganizationId() == null ? "" : getOrganizationId());
		return sb.toString();
	}

	@Override
	public int compareTo(BaseSystemProcessLink o) {
		return __process_link_id == null ? -1 : __process_link_id.compareTo(o.getProcessLinkId());
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 97 * hash + Objects.hashCode(this.__process_link_id);
		hash = 97 * hash + Objects.hashCode(this.__process_id);
		hash = 97 * hash + Objects.hashCode(this.__condition);
		hash = 97 * hash + Objects.hashCode(this.__process_activity_id);
		hash = 97 * hash + Objects.hashCode(this.__to_process_activity_id);
		hash = 97 * hash + Objects.hashCode(this.__organization_id);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(getClass() != obj.getClass()) return false;
		final BaseSystemProcessLink o = (BaseSystemProcessLink)obj;
		if(!Objects.equals(this.__process_link_id, o.getProcessLinkId())) return false;
		if(!Objects.equals(this.__process_id, o.getProcessId())) return false;
		if(!Objects.equals(this.__condition, o.getCondition())) return false;
		if(!Objects.equals(this.__process_activity_id, o.getProcessActivityId())) return false;
		if(!Objects.equals(this.__to_process_activity_id, o.getToProcessActivityId())) return false;
		if(!Objects.equals(this.__organization_id, o.getOrganizationId())) return false;
		return true;
	}

	@Override
	public java.lang.String toJSONString() {

		StringBuilder sb = new StringBuilder();
		int count = 0;
		if(getProcessLinkId() != null) sb.append(__wrapNumber(count++, "processLinkId", getProcessLinkId()));
		if(getProcessId() != null) sb.append(__wrapNumber(count++, "processId", getProcessId()));
		if(getCondition() != null) sb.append(__wrapString(count++, "condition", getCondition()));
		if(getProcessActivityId() != null) sb.append(__wrapNumber(count++, "processActivityId", getProcessActivityId()));
		if(getToProcessActivityId() != null) sb.append(__wrapNumber(count++, "toProcessActivityId", getToProcessActivityId()));
		if(getOrganizationId() != null) sb.append(__wrapNumber(count++, "organizationId", getOrganizationId()));
		return sb.toString();
	}

	public Map<String, Object> toMap() {
		Map<String, Object> res = new HashMap<>();
		if(getProcessLinkId() != null) res.put("processLinkId", getProcessLinkId());
		if(getProcessId() != null) res.put("processId", getProcessId());
		if(getCondition() != null) res.put("condition", getCondition());
		if(getProcessActivityId() != null) res.put("processActivityId", getProcessActivityId());
		if(getToProcessActivityId() != null) res.put("toProcessActivityId", getToProcessActivityId());
		if(getOrganizationId() != null) res.put("organizationId", getOrganizationId());
		return res;
	}

	@Override
	public void setDataFromMap(Map<String, Object> values){
		Object val;
		if((val = values.get("processLinkId")) != null) setProcessLinkId(__getInt(val)); 
		if((val = values.get("processId")) != null) setProcessId(__getInt(val)); 
		if((val = values.get("condition")) != null) setCondition(__getString(val));
		if((val = values.get("processActivityId")) != null) setProcessActivityId(__getInt(val)); 
		if((val = values.get("toProcessActivityId")) != null) setToProcessActivityId(__getInt(val)); 
		if((val = values.get("organizationId")) != null) setOrganizationId(__getInt(val)); 
	}

	protected java.lang.Integer  __process_link_id ;
	protected java.lang.Integer  __process_id ;
	protected java.lang.String  __condition ;
	protected java.lang.Integer  __process_activity_id ;
	protected java.lang.Integer  __to_process_activity_id ;
	protected java.lang.Integer  __organization_id ;
}
