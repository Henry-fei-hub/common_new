package delicacy.system.bean;

import java.util.Map;
import java.util.Objects;
import delicacy.common.GenericBase;
import delicacy.common.BaseFactory;

public class BaseApplication extends GenericBase implements BaseFactory<BaseApplication>, Comparable<BaseApplication> 
{


	public static BaseApplication newInstance(){
		return new BaseApplication();
	}

	@Override
	public BaseApplication make(){
		BaseApplication b = new BaseApplication();
		return b;
	}

	public final static java.lang.String CS_APPLICATION_ID = "application_id" ;
	public final static java.lang.String CS_APPLICATION_NAME = "application_name" ;

	public final static java.lang.String ALL_CAPTIONS = "应用系统代码,应用系统名称";

	public java.lang.Integer getApplicationId() {
		return this.__application_id;
	}

	public void setApplicationId( java.lang.Integer value ) {
		this.__application_id = value;
	}

	public java.lang.String getApplicationName() {
		return this.__application_name;
	}

	public void setApplicationName( java.lang.String value ) {
		this.__application_name = value;
	}

	public void cloneCopy(BaseApplication __bean){
		__bean.setApplicationId(getApplicationId());
		__bean.setApplicationName(getApplicationName());
	}

	public java.lang.String toCSVString() {

		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
		StringBuilder sb = new StringBuilder();
		sb.append(getApplicationId() == null ? "" : getApplicationId());
		sb.append(",");
		sb.append(getApplicationName() == null ? "" : getApplicationName());
		return sb.toString();
	}

	@Override
	public int compareTo(BaseApplication o) {
		return __application_id == null ? -1 : __application_id.compareTo(o.getApplicationId());
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 97 * hash + Objects.hashCode(this.__application_id);
		hash = 97 * hash + Objects.hashCode(this.__application_name);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(getClass() != obj.getClass()) return false;
		final BaseApplication o = (BaseApplication)obj;
		if(!Objects.equals(this.__application_id, o.getApplicationId())) return false;
		if(!Objects.equals(this.__application_name, o.getApplicationName())) return false;
		return true;
	}

	@Override
	public java.lang.String toJSONString() {

		StringBuilder sb = new StringBuilder();
		int count = 0;
		if(getApplicationId() != null) sb.append(__wrapNumber(count++, "applicationId", getApplicationId()));
		if(getApplicationName() != null) sb.append(__wrapString(count++, "applicationName", getApplicationName()));
		return sb.toString();
	}

	@Override
	public void setDataFromMap(Map<String, Object> values){
		Object val;
		if((val = values.get("applicationId")) != null) setApplicationId(__getInt(val)); 
		if((val = values.get("applicationName")) != null) setApplicationName(__getString(val));
	}

	protected java.lang.Integer  __application_id ;
	protected java.lang.String  __application_name ;
}
