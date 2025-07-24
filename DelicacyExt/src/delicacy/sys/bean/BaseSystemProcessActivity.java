package delicacy.sys.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import delicacy.common.BaseFactory;
import delicacy.common.GenericBase;

public class BaseSystemProcessActivity extends GenericBase implements BaseFactory<BaseSystemProcessActivity>, Comparable<BaseSystemProcessActivity> 
{


	public static BaseSystemProcessActivity newInstance(){
		return new BaseSystemProcessActivity();
	}

	@Override
	public BaseSystemProcessActivity make(){
		BaseSystemProcessActivity b = new BaseSystemProcessActivity();
		return b;
	}

	public final static java.lang.String CS_PROCESS_ACTIVITY_ID = "process_activity_id" ;
	public final static java.lang.String CS_PROCESS_ID = "process_id" ;
	public final static java.lang.String CS_ACTIVITY_TYPE = "activity_type" ;
	public final static java.lang.String CS_ACTIVITY_NAME = "activity_name" ;
	public final static java.lang.String CS_ACTIVITY_SERIAL_NO = "activity_serial_no" ;
	public final static java.lang.String CS_POSX = "posx" ;
	public final static java.lang.String CS_POSY = "posy" ;
	public final static java.lang.String CS_TIME_OUT_ACTION = "time_out_action" ;
	public final static java.lang.String CS_EXECUTOR_TYPE = "executor_type" ;
	public final static java.lang.String CS_EMPLOYEE_ID = "employee_id" ;
	public final static java.lang.String CS_DEPARTMENT_ID = "department_id" ;
	public final static java.lang.String CS_ROLE_ID = "role_id" ;
	public final static java.lang.String CS_IF_ALLOW_OVER = "if_allow_over" ;
	public final static java.lang.String CS_IS_ENABLE = "is_enable" ;
	public final static java.lang.String CS_POOL_TYPE = "pool_type" ;
	public final static java.lang.String CS_EXECUTE_NAME = "execute_name" ;
	public final static java.lang.String CS_ORGANIZATION_ID = "organization_id" ;

	public final static java.lang.String ALL_CAPTIONS = "编码,流程编码,流程节点类型0开始,1结束,2审核,3,处理,知会,活动节点名称,内部动作序号,X坐标,Y坐标,活动时限,执行人类型,,该节点是由某个职务的人来做,该节点是由某个角色的人来做,是否允许跳过,是否可用,任务池类型0并行,1串行,流程执行程序名,组织机构代码";

	public java.lang.Integer getProcessActivityId() {
		return this.__process_activity_id;
	}

	public void setProcessActivityId( java.lang.Integer value ) {
		this.__process_activity_id = value;
	}

	public java.lang.Integer getProcessId() {
		return this.__process_id;
	}

	public void setProcessId( java.lang.Integer value ) {
		this.__process_id = value;
	}

	public java.lang.Integer getActivityType() {
		return this.__activity_type;
	}

	public void setActivityType( java.lang.Integer value ) {
		this.__activity_type = value;
	}

	public java.lang.String getActivityName() {
		return this.__activity_name;
	}

	public void setActivityName( java.lang.String value ) {
		this.__activity_name = value;
	}

	public java.lang.Integer getActivitySerialNo() {
		return this.__activity_serial_no;
	}

	public void setActivitySerialNo( java.lang.Integer value ) {
		this.__activity_serial_no = value;
	}

	public java.lang.Integer getPosx() {
		return this.__posx;
	}

	public void setPosx( java.lang.Integer value ) {
		this.__posx = value;
	}

	public java.lang.Integer getPosy() {
		return this.__posy;
	}

	public void setPosy( java.lang.Integer value ) {
		this.__posy = value;
	}

	public java.lang.Integer getTimeOutAction() {
		return this.__time_out_action;
	}

	public void setTimeOutAction( java.lang.Integer value ) {
		this.__time_out_action = value;
	}

	public java.lang.Integer getExecutorType() {
		return this.__executor_type;
	}

	public void setExecutorType( java.lang.Integer value ) {
		this.__executor_type = value;
	}

	public java.lang.Integer getEmployeeId() {
		return this.__employee_id;
	}

	public void setEmployeeId( java.lang.Integer value ) {
		this.__employee_id = value;
	}

	public java.lang.Integer getDepartmentId() {
		return this.__department_id;
	}

	public void setDepartmentId( java.lang.Integer value ) {
		this.__department_id = value;
	}

	public java.lang.Integer getRoleId() {
		return this.__role_id;
	}

	public void setRoleId( java.lang.Integer value ) {
		this.__role_id = value;
	}

	public java.lang.Boolean getIfAllowOver() {
		return this.__if_allow_over;
	}

	public void setIfAllowOver( java.lang.Boolean value ) {
		this.__if_allow_over = value;
	}

	public java.lang.Boolean getIsEnable() {
		return this.__is_enable;
	}

	public void setIsEnable( java.lang.Boolean value ) {
		this.__is_enable = value;
	}

	public java.lang.Integer getPoolType() {
		return this.__pool_type;
	}

	public void setPoolType( java.lang.Integer value ) {
		this.__pool_type = value;
	}

	public java.lang.String getExecuteName() {
		return this.__execute_name;
	}

	public void setExecuteName( java.lang.String value ) {
		this.__execute_name = value;
	}

	public java.lang.Integer getOrganizationId() {
		return this.__organization_id;
	}

	public void setOrganizationId( java.lang.Integer value ) {
		this.__organization_id = value;
	}

	public void cloneCopy(BaseSystemProcessActivity __bean){
		__bean.setProcessActivityId(getProcessActivityId());
		__bean.setProcessId(getProcessId());
		__bean.setActivityType(getActivityType());
		__bean.setActivityName(getActivityName());
		__bean.setActivitySerialNo(getActivitySerialNo());
		__bean.setPosx(getPosx());
		__bean.setPosy(getPosy());
		__bean.setTimeOutAction(getTimeOutAction());
		__bean.setExecutorType(getExecutorType());
		__bean.setEmployeeId(getEmployeeId());
		__bean.setDepartmentId(getDepartmentId());
		__bean.setRoleId(getRoleId());
		__bean.setIfAllowOver(getIfAllowOver());
		__bean.setIsEnable(getIsEnable());
		__bean.setPoolType(getPoolType());
		__bean.setExecuteName(getExecuteName());
		__bean.setOrganizationId(getOrganizationId());
	}

	public java.lang.String toCSVString() {

		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
		StringBuilder sb = new StringBuilder();
		sb.append(getProcessActivityId() == null ? "" : getProcessActivityId());
		sb.append(",");
		sb.append(getProcessId() == null ? "" : getProcessId());
		sb.append(",");
		sb.append(getActivityType() == null ? "" : getActivityType());
		sb.append(",");
		sb.append(getActivityName() == null ? "" : getActivityName());
		sb.append(",");
		sb.append(getActivitySerialNo() == null ? "" : getActivitySerialNo());
		sb.append(",");
		sb.append(getPosx() == null ? "" : getPosx());
		sb.append(",");
		sb.append(getPosy() == null ? "" : getPosy());
		sb.append(",");
		sb.append(getTimeOutAction() == null ? "" : getTimeOutAction());
		sb.append(",");
		sb.append(getExecutorType() == null ? "" : getExecutorType());
		sb.append(",");
		sb.append(getEmployeeId() == null ? "" : getEmployeeId());
		sb.append(",");
		sb.append(getDepartmentId() == null ? "" : getDepartmentId());
		sb.append(",");
		sb.append(getRoleId() == null ? "" : getRoleId());
		sb.append(",");
		sb.append(getIfAllowOver() == null ? "" : getIfAllowOver());
		sb.append(",");
		sb.append(getIsEnable() == null ? "" : getIsEnable());
		sb.append(",");
		sb.append(getPoolType() == null ? "" : getPoolType());
		sb.append(",");
		sb.append(getExecuteName() == null ? "" : getExecuteName());
		sb.append(",");
		sb.append(getOrganizationId() == null ? "" : getOrganizationId());
		return sb.toString();
	}

	@Override
	public int compareTo(BaseSystemProcessActivity o) {
		return __process_activity_id == null ? -1 : __process_activity_id.compareTo(o.getProcessActivityId());
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 97 * hash + Objects.hashCode(this.__process_activity_id);
		hash = 97 * hash + Objects.hashCode(this.__process_id);
		hash = 97 * hash + Objects.hashCode(this.__activity_type);
		hash = 97 * hash + Objects.hashCode(this.__activity_name);
		hash = 97 * hash + Objects.hashCode(this.__activity_serial_no);
		hash = 97 * hash + Objects.hashCode(this.__posx);
		hash = 97 * hash + Objects.hashCode(this.__posy);
		hash = 97 * hash + Objects.hashCode(this.__time_out_action);
		hash = 97 * hash + Objects.hashCode(this.__executor_type);
		hash = 97 * hash + Objects.hashCode(this.__employee_id);
		hash = 97 * hash + Objects.hashCode(this.__department_id);
		hash = 97 * hash + Objects.hashCode(this.__role_id);
		hash = 97 * hash + Objects.hashCode(this.__if_allow_over);
		hash = 97 * hash + Objects.hashCode(this.__is_enable);
		hash = 97 * hash + Objects.hashCode(this.__pool_type);
		hash = 97 * hash + Objects.hashCode(this.__execute_name);
		hash = 97 * hash + Objects.hashCode(this.__organization_id);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(getClass() != obj.getClass()) return false;
		final BaseSystemProcessActivity o = (BaseSystemProcessActivity)obj;
		if(!Objects.equals(this.__process_activity_id, o.getProcessActivityId())) return false;
		if(!Objects.equals(this.__process_id, o.getProcessId())) return false;
		if(!Objects.equals(this.__activity_type, o.getActivityType())) return false;
		if(!Objects.equals(this.__activity_name, o.getActivityName())) return false;
		if(!Objects.equals(this.__activity_serial_no, o.getActivitySerialNo())) return false;
		if(!Objects.equals(this.__posx, o.getPosx())) return false;
		if(!Objects.equals(this.__posy, o.getPosy())) return false;
		if(!Objects.equals(this.__time_out_action, o.getTimeOutAction())) return false;
		if(!Objects.equals(this.__executor_type, o.getExecutorType())) return false;
		if(!Objects.equals(this.__employee_id, o.getEmployeeId())) return false;
		if(!Objects.equals(this.__department_id, o.getDepartmentId())) return false;
		if(!Objects.equals(this.__role_id, o.getRoleId())) return false;
		if(!Objects.equals(this.__if_allow_over, o.getIfAllowOver())) return false;
		if(!Objects.equals(this.__is_enable, o.getIsEnable())) return false;
		if(!Objects.equals(this.__pool_type, o.getPoolType())) return false;
		if(!Objects.equals(this.__execute_name, o.getExecuteName())) return false;
		if(!Objects.equals(this.__organization_id, o.getOrganizationId())) return false;
		return true;
	}

	@Override
	public java.lang.String toJSONString() {

		StringBuilder sb = new StringBuilder();
		int count = 0;
		if(getProcessActivityId() != null) sb.append(__wrapNumber(count++, "processActivityId", getProcessActivityId()));
		if(getProcessId() != null) sb.append(__wrapNumber(count++, "processId", getProcessId()));
		if(getActivityType() != null) sb.append(__wrapNumber(count++, "activityType", getActivityType()));
		if(getActivityName() != null) sb.append(__wrapString(count++, "activityName", getActivityName()));
		if(getActivitySerialNo() != null) sb.append(__wrapNumber(count++, "activitySerialNo", getActivitySerialNo()));
		if(getPosx() != null) sb.append(__wrapNumber(count++, "posx", getPosx()));
		if(getPosy() != null) sb.append(__wrapNumber(count++, "posy", getPosy()));
		if(getTimeOutAction() != null) sb.append(__wrapNumber(count++, "timeOutAction", getTimeOutAction()));
		if(getExecutorType() != null) sb.append(__wrapNumber(count++, "executorType", getExecutorType()));
		if(getEmployeeId() != null) sb.append(__wrapNumber(count++, "employeeId", getEmployeeId()));
		if(getDepartmentId() != null) sb.append(__wrapNumber(count++, "departmentId", getDepartmentId()));
		if(getRoleId() != null) sb.append(__wrapNumber(count++, "roleId", getRoleId()));
		if(getIfAllowOver() != null) sb.append(__wrapBoolean(count++, "ifAllowOver", getIfAllowOver()));
		if(getIsEnable() != null) sb.append(__wrapBoolean(count++, "isEnable", getIsEnable()));
		if(getPoolType() != null) sb.append(__wrapNumber(count++, "poolType", getPoolType()));
		if(getExecuteName() != null) sb.append(__wrapString(count++, "executeName", getExecuteName()));
		if(getOrganizationId() != null) sb.append(__wrapNumber(count++, "organizationId", getOrganizationId()));
		return sb.toString();
	}

	public Map<String, Object> toMap() {
		Map<String, Object> res = new HashMap<>();
		if(getProcessActivityId() != null) res.put("processActivityId", getProcessActivityId());
		if(getProcessId() != null) res.put("processId", getProcessId());
		if(getActivityType() != null) res.put("activityType", getActivityType());
		if(getActivityName() != null) res.put("activityName", getActivityName());
		if(getActivitySerialNo() != null) res.put("activitySerialNo", getActivitySerialNo());
		if(getPosx() != null) res.put("posx", getPosx());
		if(getPosy() != null) res.put("posy", getPosy());
		if(getTimeOutAction() != null) res.put("timeOutAction", getTimeOutAction());
		if(getExecutorType() != null) res.put("executorType", getExecutorType());
		if(getEmployeeId() != null) res.put("employeeId", getEmployeeId());
		if(getDepartmentId() != null) res.put("departmentId", getDepartmentId());
		if(getRoleId() != null) res.put("roleId", getRoleId());
		if(getIfAllowOver() != null) res.put("ifAllowOver", getIfAllowOver());
		if(getIsEnable() != null) res.put("isEnable", getIsEnable());
		if(getPoolType() != null) res.put("poolType", getPoolType());
		if(getExecuteName() != null) res.put("executeName", getExecuteName());
		if(getOrganizationId() != null) res.put("organizationId", getOrganizationId());
		return res;
	}

	@Override
	public void setDataFromMap(Map<String, Object> values){
		Object val;
		if((val = values.get("processActivityId")) != null) setProcessActivityId(__getInt(val)); 
		if((val = values.get("processId")) != null) setProcessId(__getInt(val)); 
		if((val = values.get("activityType")) != null) setActivityType(__getInt(val)); 
		if((val = values.get("activityName")) != null) setActivityName(__getString(val));
		if((val = values.get("activitySerialNo")) != null) setActivitySerialNo(__getInt(val)); 
		if((val = values.get("posx")) != null) setPosx(__getInt(val)); 
		if((val = values.get("posy")) != null) setPosy(__getInt(val)); 
		if((val = values.get("timeOutAction")) != null) setTimeOutAction(__getInt(val)); 
		if((val = values.get("executorType")) != null) setExecutorType(__getInt(val)); 
		if((val = values.get("employeeId")) != null) setEmployeeId(__getInt(val)); 
		if((val = values.get("departmentId")) != null) setDepartmentId(__getInt(val)); 
		if((val = values.get("roleId")) != null) setRoleId(__getInt(val)); 
		if((val = values.get("ifAllowOver")) != null) setIfAllowOver(__getBoolean(val));
		if((val = values.get("isEnable")) != null) setIsEnable(__getBoolean(val));
		if((val = values.get("poolType")) != null) setPoolType(__getInt(val)); 
		if((val = values.get("executeName")) != null) setExecuteName(__getString(val));
		if((val = values.get("organizationId")) != null) setOrganizationId(__getInt(val)); 
	}

	protected java.lang.Integer  __process_activity_id ;
	protected java.lang.Integer  __process_id ;
	protected java.lang.Integer  __activity_type ;
	protected java.lang.String  __activity_name ;
	protected java.lang.Integer  __activity_serial_no ;
	protected java.lang.Integer  __posx ;
	protected java.lang.Integer  __posy ;
	protected java.lang.Integer  __time_out_action ;
	protected java.lang.Integer  __executor_type ;
	protected java.lang.Integer  __employee_id ;
	protected java.lang.Integer  __department_id ;
	protected java.lang.Integer  __role_id ;
	protected java.lang.Boolean  __if_allow_over ;
	protected java.lang.Boolean  __is_enable ;
	protected java.lang.Integer  __pool_type ;
	protected java.lang.String  __execute_name ;
	protected java.lang.Integer  __organization_id ;
}
