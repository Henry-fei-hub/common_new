package delicacy.system.bean;

import java.util.Map;
import java.util.HashMap;
import java.util.Objects;
import delicacy.common.GenericBase;
import delicacy.common.BaseFactory;

public class BaseSystemLog extends GenericBase implements BaseFactory<BaseSystemLog>, Comparable<BaseSystemLog> 
{


	public static BaseSystemLog newInstance(){
		return new BaseSystemLog();
	}

	@Override
	public BaseSystemLog make(){
		BaseSystemLog b = new BaseSystemLog();
		return b;
	}

	public final static java.lang.String CS_SYSTEM_LOG_ID = "system_log_id" ;
	public final static java.lang.String CS_LOG_TIME = "log_time" ;
	public final static java.lang.String CS_FROM_HOST = "from_host" ;
	public final static java.lang.String CS_OPERATOR = "operator" ;
	public final static java.lang.String CS_OPERATOR_NAME = "operator_name" ;
	public final static java.lang.String CS_LOG_CONTENT = "log_content" ;
	public final static java.lang.String CS_LOG_TYPE = "log_type" ;

	public final static java.lang.String ALL_CAPTIONS = "编码,记录时间,操作机器,操作人,名字,内容,日志类型";

	public java.lang.Integer getSystemLogId() {
		return this.__system_log_id;
	}

	public void setSystemLogId( java.lang.Integer value ) {
		this.__system_log_id = value;
	}

	public java.util.Date getLogTime() {
		return this.__log_time;
	}

	public void setLogTime( java.util.Date value ) {
		this.__log_time = value;
	}

	public java.lang.String getFromHost() {
		return this.__from_host;
	}

	public void setFromHost( java.lang.String value ) {
		this.__from_host = value;
	}

	public java.lang.Integer getOperator() {
		return this.__operator;
	}

	public void setOperator( java.lang.Integer value ) {
		this.__operator = value;
	}

	public java.lang.String getOperatorName() {
		return this.__operator_name;
	}

	public void setOperatorName( java.lang.String value ) {
		this.__operator_name = value;
	}

	public java.lang.String getLogContent() {
		return this.__log_content;
	}

	public void setLogContent( java.lang.String value ) {
		this.__log_content = value;
	}

	public java.lang.Integer getLogType() {
		return this.__log_type;
	}

	public void setLogType( java.lang.Integer value ) {
		this.__log_type = value;
	}

	public void cloneCopy(BaseSystemLog __bean){
		__bean.setSystemLogId(getSystemLogId());
		__bean.setLogTime(getLogTime());
		__bean.setFromHost(getFromHost());
		__bean.setOperator(getOperator());
		__bean.setOperatorName(getOperatorName());
		__bean.setLogContent(getLogContent());
		__bean.setLogType(getLogType());
	}

	public java.lang.String toCSVString() {

		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
		StringBuilder sb = new StringBuilder();
		sb.append(getSystemLogId() == null ? "" : getSystemLogId());
		sb.append(",");
		sb.append(getLogTime() == null ? "" : sdf.format(getLogTime()));
		sb.append(",");
		sb.append(getFromHost() == null ? "" : getFromHost());
		sb.append(",");
		String strOperator = delicacy.system.executor.SelectValueCache.getSelectValue("employees", String.valueOf(getOperator()));
		sb.append(strOperator == null ? "" : strOperator);
		sb.append(",");
		sb.append(getOperatorName() == null ? "" : getOperatorName());
		sb.append(",");
		sb.append(getLogContent() == null ? "" : getLogContent());
		sb.append(",");
		sb.append(getLogType() == null ? "" : getLogType());
		return sb.toString();
	}

	@Override
	public int compareTo(BaseSystemLog o) {
		return __system_log_id == null ? -1 : __system_log_id.compareTo(o.getSystemLogId());
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 97 * hash + Objects.hashCode(this.__system_log_id);
		hash = 97 * hash + Objects.hashCode(this.__log_time);
		hash = 97 * hash + Objects.hashCode(this.__from_host);
		hash = 97 * hash + Objects.hashCode(this.__operator);
		hash = 97 * hash + Objects.hashCode(this.__operator_name);
		hash = 97 * hash + Objects.hashCode(this.__log_content);
		hash = 97 * hash + Objects.hashCode(this.__log_type);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(getClass() != obj.getClass()) return false;
		final BaseSystemLog o = (BaseSystemLog)obj;
		if(!Objects.equals(this.__system_log_id, o.getSystemLogId())) return false;
		if(!Objects.equals(this.__log_time, o.getLogTime())) return false;
		if(!Objects.equals(this.__from_host, o.getFromHost())) return false;
		if(!Objects.equals(this.__operator, o.getOperator())) return false;
		if(!Objects.equals(this.__operator_name, o.getOperatorName())) return false;
		if(!Objects.equals(this.__log_content, o.getLogContent())) return false;
		if(!Objects.equals(this.__log_type, o.getLogType())) return false;
		return true;
	}

	@Override
	public java.lang.String toJSONString() {

		StringBuilder sb = new StringBuilder();
		int count = 0;
		if(getSystemLogId() != null) sb.append(__wrapNumber(count++, "systemLogId", getSystemLogId()));
		if(getLogTime() != null) sb.append(__wrapDate(count++, "logTime", getLogTime()));
		if(getFromHost() != null) sb.append(__wrapString(count++, "fromHost", getFromHost()));
		if(getOperator() != null) sb.append(__wrapNumber(count++, "operator", getOperator()));
		if(getOperatorName() != null) sb.append(__wrapString(count++, "operatorName", getOperatorName()));
		if(getLogContent() != null) sb.append(__wrapString(count++, "logContent", getLogContent()));
		if(getLogType() != null) sb.append(__wrapNumber(count++, "logType", getLogType()));
		return sb.toString();
	}

	public Map<String, Object> toMap() {
		Map<String, Object> res = new HashMap<>();
		if(getSystemLogId() != null) res.put("systemLogId", getSystemLogId());
		if(getLogTime() != null) res.put("logTime", getLogTime());
		if(getFromHost() != null) res.put("fromHost", getFromHost());
		if(getOperator() != null) res.put("operator", getOperator());
		if(getOperatorName() != null) res.put("operatorName", getOperatorName());
		if(getLogContent() != null) res.put("logContent", getLogContent());
		if(getLogType() != null) res.put("logType", getLogType());
		return res;
	}

	@Override
	public void setDataFromMap(Map<String, Object> values){
		Object val;
		if((val = values.get("systemLogId")) != null) setSystemLogId(__getInt(val)); 
		if((val = values.get("logTime")) != null) setLogTime(__getDate(val)); 
		if((val = values.get("fromHost")) != null) setFromHost(__getString(val));
		if((val = values.get("operator")) != null) setOperator(__getInt(val)); 
		if((val = values.get("operatorName")) != null) setOperatorName(__getString(val));
		if((val = values.get("logContent")) != null) setLogContent(__getString(val));
		if((val = values.get("logType")) != null) setLogType(__getInt(val)); 
	}

	protected java.lang.Integer  __system_log_id ;
	protected java.util.Date  __log_time ;
	protected java.lang.String  __from_host ;
	protected java.lang.Integer  __operator ;
	protected java.lang.String  __operator_name ;
	protected java.lang.String  __log_content ;
	protected java.lang.Integer  __log_type ;
}
