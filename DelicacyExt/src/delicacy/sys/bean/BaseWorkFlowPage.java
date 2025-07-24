package delicacy.sys.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import delicacy.common.BaseFactory;
import delicacy.common.GenericBase;

public class BaseWorkFlowPage extends GenericBase implements BaseFactory<BaseWorkFlowPage>, Comparable<BaseWorkFlowPage> 
{


	public static BaseWorkFlowPage newInstance(){
		return new BaseWorkFlowPage();
	}

	@Override
	public BaseWorkFlowPage make(){
		BaseWorkFlowPage b = new BaseWorkFlowPage();
		return b;
	}

	public final static java.lang.String CS_WORK_FLOW_PAGE_ID = "work_flow_page_id" ;
	public final static java.lang.String CS_EXECUTE_NAME = "execute_name" ;
	public final static java.lang.String CS_PAGE_DIRECTORY = "page_directory" ;
	public final static java.lang.String CS_ORGANIZATION_ID = "organization_id" ;

	public final static java.lang.String ALL_CAPTIONS = "主键编码,执行程序,访问页面目录,组织架构代码";

	public java.lang.Integer getWorkFlowPageId() {
		return this.__work_flow_page_id;
	}

	public void setWorkFlowPageId( java.lang.Integer value ) {
		this.__work_flow_page_id = value;
	}

	public java.lang.String getExecuteName() {
		return this.__execute_name;
	}

	public void setExecuteName( java.lang.String value ) {
		this.__execute_name = value;
	}

	public java.lang.String getPageDirectory() {
		return this.__page_directory;
	}

	public void setPageDirectory( java.lang.String value ) {
		this.__page_directory = value;
	}

	public java.lang.Integer getOrganizationId() {
		return this.__organization_id;
	}

	public void setOrganizationId( java.lang.Integer value ) {
		this.__organization_id = value;
	}

	public void cloneCopy(BaseWorkFlowPage __bean){
		__bean.setWorkFlowPageId(getWorkFlowPageId());
		__bean.setExecuteName(getExecuteName());
		__bean.setPageDirectory(getPageDirectory());
		__bean.setOrganizationId(getOrganizationId());
	}

	public java.lang.String toCSVString() {

		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
		StringBuilder sb = new StringBuilder();
		sb.append(getWorkFlowPageId() == null ? "" : getWorkFlowPageId());
		sb.append(",");
		sb.append(getExecuteName() == null ? "" : getExecuteName());
		sb.append(",");
		sb.append(getPageDirectory() == null ? "" : getPageDirectory());
		sb.append(",");
		String strOrganizationId = delicacy.system.executor.SelectValueCache.getSelectValue("banks", String.valueOf(getOrganizationId()));
		sb.append(strOrganizationId == null ? "" : strOrganizationId);
		return sb.toString();
	}

	@Override
	public int compareTo(BaseWorkFlowPage o) {
		return __work_flow_page_id == null ? -1 : __work_flow_page_id.compareTo(o.getWorkFlowPageId());
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 97 * hash + Objects.hashCode(this.__work_flow_page_id);
		hash = 97 * hash + Objects.hashCode(this.__execute_name);
		hash = 97 * hash + Objects.hashCode(this.__page_directory);
		hash = 97 * hash + Objects.hashCode(this.__organization_id);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(getClass() != obj.getClass()) return false;
		final BaseWorkFlowPage o = (BaseWorkFlowPage)obj;
		if(!Objects.equals(this.__work_flow_page_id, o.getWorkFlowPageId())) return false;
		if(!Objects.equals(this.__execute_name, o.getExecuteName())) return false;
		if(!Objects.equals(this.__page_directory, o.getPageDirectory())) return false;
		if(!Objects.equals(this.__organization_id, o.getOrganizationId())) return false;
		return true;
	}

	@Override
	public java.lang.String toJSONString() {

		StringBuilder sb = new StringBuilder();
		int count = 0;
		if(getWorkFlowPageId() != null) sb.append(__wrapNumber(count++, "workFlowPageId", getWorkFlowPageId()));
		if(getExecuteName() != null) sb.append(__wrapString(count++, "executeName", getExecuteName()));
		if(getPageDirectory() != null) sb.append(__wrapString(count++, "pageDirectory", getPageDirectory()));
		if(getOrganizationId() != null) sb.append(__wrapNumber(count++, "organizationId", getOrganizationId()));
		return sb.toString();
	}

	public Map<String, Object> toMap() {
		Map<String, Object> res = new HashMap<>();
		if(getWorkFlowPageId() != null) res.put("workFlowPageId", getWorkFlowPageId());
		if(getExecuteName() != null) res.put("executeName", getExecuteName());
		if(getPageDirectory() != null) res.put("pageDirectory", getPageDirectory());
		if(getOrganizationId() != null) res.put("organizationId", getOrganizationId());
		return res;
	}

	@Override
	public void setDataFromMap(Map<String, Object> values){
		Object val;
		if((val = values.get("workFlowPageId")) != null) setWorkFlowPageId(__getInt(val)); 
		if((val = values.get("executeName")) != null) setExecuteName(__getString(val));
		if((val = values.get("pageDirectory")) != null) setPageDirectory(__getString(val));
		if((val = values.get("organizationId")) != null) setOrganizationId(__getInt(val)); 
	}

	protected java.lang.Integer  __work_flow_page_id ;
	protected java.lang.String  __execute_name ;
	protected java.lang.String  __page_directory ;
	protected java.lang.Integer  __organization_id ;
}
