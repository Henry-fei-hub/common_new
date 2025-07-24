package delicacy.system.bean;

import java.util.Map;
import java.util.HashMap;
import java.util.Objects;
import delicacy.common.GenericBase;
import delicacy.common.BaseFactory;

public class BaseSystemProcessType extends GenericBase implements BaseFactory<BaseSystemProcessType>, Comparable<BaseSystemProcessType> 
{


	public static BaseSystemProcessType newInstance(){
		return new BaseSystemProcessType();
	}

	@Override
	public BaseSystemProcessType make(){
		BaseSystemProcessType b = new BaseSystemProcessType();
		return b;
	}

	public final static java.lang.String CS_PROCESS_TYPE_ID = "process_type_id" ;
	public final static java.lang.String CS_PROCESS_TYPE_NAME = "process_type_name" ;
	public final static java.lang.String CS_DESCRIPTION = "description" ;
	public final static java.lang.String CS_PROCESS_EXECUTE_NAME = "process_execute_name" ;
	public final static java.lang.String CS_IS_ENABLE = "is_enable" ;
	public final static java.lang.String CS_PARENT_PROCESS_TYPE_ID = "parent_process_type_id" ;
	public final static java.lang.String CS_ICON = "icon" ;
	public final static java.lang.String CS_IS_ATTENDANCE = "is_attendance" ;
	public final static java.lang.String CS_APPLICATION_ID = "application_id" ;

	public final static java.lang.String ALL_CAPTIONS = "主键编码,流程类型名称,流程类型描述,流程处理程序名,激活状态,父级类型,图标路径,是否参与考勤计算,应用系统编码";

	public java.lang.Integer getProcessTypeId() {
		return this.__process_type_id;
	}

	public void setProcessTypeId( java.lang.Integer value ) {
		this.__process_type_id = value;
	}

	public java.lang.String getProcessTypeName() {
		return this.__process_type_name;
	}

	public void setProcessTypeName( java.lang.String value ) {
		this.__process_type_name = value;
	}

	public java.lang.String getDescription() {
		return this.__description;
	}

	public void setDescription( java.lang.String value ) {
		this.__description = value;
	}

	public java.lang.String getProcessExecuteName() {
		return this.__process_execute_name;
	}

	public void setProcessExecuteName( java.lang.String value ) {
		this.__process_execute_name = value;
	}

	public java.lang.Boolean getIsEnable() {
		return this.__is_enable;
	}

	public void setIsEnable( java.lang.Boolean value ) {
		this.__is_enable = value;
	}

	public java.lang.Integer getParentProcessTypeId() {
		return this.__parent_process_type_id;
	}

	public void setParentProcessTypeId( java.lang.Integer value ) {
		this.__parent_process_type_id = value;
	}

	public java.lang.String getIcon() {
		return this.__icon;
	}

	public void setIcon( java.lang.String value ) {
		this.__icon = value;
	}

	public java.lang.Boolean getIsAttendance() {
		return this.__is_attendance;
	}

	public void setIsAttendance( java.lang.Boolean value ) {
		this.__is_attendance = value;
	}

	public java.lang.Integer getApplicationId() {
		return this.__application_id;
	}

	public void setApplicationId( java.lang.Integer value ) {
		this.__application_id = value;
	}

	public void cloneCopy(BaseSystemProcessType __bean){
		__bean.setProcessTypeId(getProcessTypeId());
		__bean.setProcessTypeName(getProcessTypeName());
		__bean.setDescription(getDescription());
		__bean.setProcessExecuteName(getProcessExecuteName());
		__bean.setIsEnable(getIsEnable());
		__bean.setParentProcessTypeId(getParentProcessTypeId());
		__bean.setIcon(getIcon());
		__bean.setIsAttendance(getIsAttendance());
		__bean.setApplicationId(getApplicationId());
	}

	public java.lang.String toCSVString() {

		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
		StringBuilder sb = new StringBuilder();
		String strProcessTypeId = delicacy.system.executor.SelectValueCache.getSelectValue("system_process_types", String.valueOf(getProcessTypeId()));
		sb.append(strProcessTypeId == null ? "" : strProcessTypeId);
		sb.append(",");
		sb.append(getProcessTypeName() == null ? "" : getProcessTypeName());
		sb.append(",");
		sb.append(getDescription() == null ? "" : getDescription());
		sb.append(",");
		sb.append(getProcessExecuteName() == null ? "" : getProcessExecuteName());
		sb.append(",");
		sb.append(getIsEnable() == null ? "" : getIsEnable());
		sb.append(",");
		sb.append(getParentProcessTypeId() == null ? "" : getParentProcessTypeId());
		sb.append(",");
		sb.append(getIcon() == null ? "" : getIcon());
		sb.append(",");
		sb.append(getIsAttendance() == null ? "" : getIsAttendance());
		sb.append(",");
		String strApplicationId = delicacy.system.executor.SelectValueCache.getSelectValue("app_systems", String.valueOf(getApplicationId()));
		sb.append(strApplicationId == null ? "" : strApplicationId);
		return sb.toString();
	}

	@Override
	public int compareTo(BaseSystemProcessType o) {
		return __process_type_id == null ? -1 : __process_type_id.compareTo(o.getProcessTypeId());
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 97 * hash + Objects.hashCode(this.__process_type_id);
		hash = 97 * hash + Objects.hashCode(this.__process_type_name);
		hash = 97 * hash + Objects.hashCode(this.__description);
		hash = 97 * hash + Objects.hashCode(this.__process_execute_name);
		hash = 97 * hash + Objects.hashCode(this.__is_enable);
		hash = 97 * hash + Objects.hashCode(this.__parent_process_type_id);
		hash = 97 * hash + Objects.hashCode(this.__icon);
		hash = 97 * hash + Objects.hashCode(this.__is_attendance);
		hash = 97 * hash + Objects.hashCode(this.__application_id);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(getClass() != obj.getClass()) return false;
		final BaseSystemProcessType o = (BaseSystemProcessType)obj;
		if(!Objects.equals(this.__process_type_id, o.getProcessTypeId())) return false;
		if(!Objects.equals(this.__process_type_name, o.getProcessTypeName())) return false;
		if(!Objects.equals(this.__description, o.getDescription())) return false;
		if(!Objects.equals(this.__process_execute_name, o.getProcessExecuteName())) return false;
		if(!Objects.equals(this.__is_enable, o.getIsEnable())) return false;
		if(!Objects.equals(this.__parent_process_type_id, o.getParentProcessTypeId())) return false;
		if(!Objects.equals(this.__icon, o.getIcon())) return false;
		if(!Objects.equals(this.__is_attendance, o.getIsAttendance())) return false;
		if(!Objects.equals(this.__application_id, o.getApplicationId())) return false;
		return true;
	}

	@Override
	public java.lang.String toJSONString() {

		StringBuilder sb = new StringBuilder();
		int count = 0;
		if(getProcessTypeId() != null) sb.append(__wrapNumber(count++, "processTypeId", getProcessTypeId()));
		if(getProcessTypeName() != null) sb.append(__wrapString(count++, "processTypeName", getProcessTypeName()));
		if(getDescription() != null) sb.append(__wrapString(count++, "description", getDescription()));
		if(getProcessExecuteName() != null) sb.append(__wrapString(count++, "processExecuteName", getProcessExecuteName()));
		if(getIsEnable() != null) sb.append(__wrapBoolean(count++, "isEnable", getIsEnable()));
		if(getParentProcessTypeId() != null) sb.append(__wrapNumber(count++, "parentProcessTypeId", getParentProcessTypeId()));
		if(getIcon() != null) sb.append(__wrapString(count++, "icon", getIcon()));
		if(getIsAttendance() != null) sb.append(__wrapBoolean(count++, "isAttendance", getIsAttendance()));
		if(getApplicationId() != null) sb.append(__wrapNumber(count++, "applicationId", getApplicationId()));
		return sb.toString();
	}

	public Map<String, Object> toMap() {
		Map<String, Object> res = new HashMap<>();
		if(getProcessTypeId() != null) res.put("processTypeId", getProcessTypeId());
		if(getProcessTypeName() != null) res.put("processTypeName", getProcessTypeName());
		if(getDescription() != null) res.put("description", getDescription());
		if(getProcessExecuteName() != null) res.put("processExecuteName", getProcessExecuteName());
		if(getIsEnable() != null) res.put("isEnable", getIsEnable());
		if(getParentProcessTypeId() != null) res.put("parentProcessTypeId", getParentProcessTypeId());
		if(getIcon() != null) res.put("icon", getIcon());
		if(getIsAttendance() != null) res.put("isAttendance", getIsAttendance());
		if(getApplicationId() != null) res.put("applicationId", getApplicationId());
		return res;
	}

	@Override
	public void setDataFromMap(Map<String, Object> values){
		Object val;
		if((val = values.get("processTypeId")) != null) setProcessTypeId(__getInt(val)); 
		if((val = values.get("processTypeName")) != null) setProcessTypeName(__getString(val));
		if((val = values.get("description")) != null) setDescription(__getString(val));
		if((val = values.get("processExecuteName")) != null) setProcessExecuteName(__getString(val));
		if((val = values.get("isEnable")) != null) setIsEnable(__getBoolean(val));
		if((val = values.get("parentProcessTypeId")) != null) setParentProcessTypeId(__getInt(val)); 
		if((val = values.get("icon")) != null) setIcon(__getString(val));
		if((val = values.get("isAttendance")) != null) setIsAttendance(__getBoolean(val));
		if((val = values.get("applicationId")) != null) setApplicationId(__getInt(val)); 
	}

	protected java.lang.Integer  __process_type_id ;
	protected java.lang.String  __process_type_name ;
	protected java.lang.String  __description ;
	protected java.lang.String  __process_execute_name ;
	protected java.lang.Boolean  __is_enable ;
	protected java.lang.Integer  __parent_process_type_id ;
	protected java.lang.String  __icon ;
	protected java.lang.Boolean  __is_attendance ;
	protected java.lang.Integer  __application_id ;
}
