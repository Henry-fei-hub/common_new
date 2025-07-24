package delicacy.system.bean;

import java.util.Map;
import java.util.HashMap;
import java.util.Objects;
import delicacy.common.GenericBase;
import delicacy.common.BaseFactory;

public class BaseMobileFunction extends GenericBase implements BaseFactory<BaseMobileFunction>, Comparable<BaseMobileFunction> 
{


	public static BaseMobileFunction newInstance(){
		return new BaseMobileFunction();
	}

	@Override
	public BaseMobileFunction make(){
		BaseMobileFunction b = new BaseMobileFunction();
		return b;
	}

	public final static java.lang.String CS_FUNCTION_ID = "function_id" ;
	public final static java.lang.String CS_FUNCTION_CODE = "function_code" ;
	public final static java.lang.String CS_PARENT_ID = "parent_id" ;
	public final static java.lang.String CS_FUNCTION_NAME = "function_name" ;
	public final static java.lang.String CS_APPLICATION_ID = "application_id" ;
	public final static java.lang.String CS_FUNCTION_TYPE = "function_type" ;
	public final static java.lang.String CS_PRIVILEGE_TYPE = "privilege_type" ;
	public final static java.lang.String CS_ENABLED = "enabled" ;
	public final static java.lang.String CS_FUNCTION_STATIC_NAME = "function_static_name" ;
	public final static java.lang.String CS_ICON_NAME = "icon_name" ;
	public final static java.lang.String CS_STYLE_NAME = "style_name" ;
	public final static java.lang.String CS_CLASS_NAME = "class_name" ;
	public final static java.lang.String CS_EXECUTE_PAGE_NAME = "execute_page_name" ;
	public final static java.lang.String CS_MARGIN_TOP = "margin_top" ;
	public final static java.lang.String CS_MARGIN_BOTTOM = "margin_bottom" ;
	public final static java.lang.String CS_ICON_COLOR = "icon_color" ;
	public final static java.lang.String CS_APP_PAGE_NAME = "app_page_name" ;

	public final static java.lang.String ALL_CAPTIONS = "功能编码,功能编号,上级功能,功能名称,应用系统代码,功能类型,数据权限类型,是否有效,,功能图标,功能的样式,功能class样式,该功能菜单对应的页面,头部间距,底部间距,图标颜色,该功能菜单对应的APP页面";

	public java.lang.Integer getFunctionId() {
		return this.__function_id;
	}

	public void setFunctionId( java.lang.Integer value ) {
		this.__function_id = value;
	}

	public java.lang.String getFunctionCode() {
		return this.__function_code;
	}

	public void setFunctionCode( java.lang.String value ) {
		this.__function_code = value;
	}

	public java.lang.Integer getParentId() {
		return this.__parent_id;
	}

	public void setParentId( java.lang.Integer value ) {
		this.__parent_id = value;
	}

	public java.lang.String getFunctionName() {
		return this.__function_name;
	}

	public void setFunctionName( java.lang.String value ) {
		this.__function_name = value;
	}

	public java.lang.Integer getApplicationId() {
		return this.__application_id;
	}

	public void setApplicationId( java.lang.Integer value ) {
		this.__application_id = value;
	}

	public java.lang.Integer getFunctionType() {
		return this.__function_type;
	}

	public void setFunctionType( java.lang.Integer value ) {
		this.__function_type = value;
	}

	public java.lang.Integer getPrivilegeType() {
		return this.__privilege_type;
	}

	public void setPrivilegeType( java.lang.Integer value ) {
		this.__privilege_type = value;
	}

	public java.lang.Boolean getEnabled() {
		return this.__enabled;
	}

	public void setEnabled( java.lang.Boolean value ) {
		this.__enabled = value;
	}

	public java.lang.String getFunctionStaticName() {
		return this.__function_static_name;
	}

	public void setFunctionStaticName( java.lang.String value ) {
		this.__function_static_name = value;
	}

	public java.lang.String getIconName() {
		return this.__icon_name;
	}

	public void setIconName( java.lang.String value ) {
		this.__icon_name = value;
	}

	public java.lang.String getStyleName() {
		return this.__style_name;
	}

	public void setStyleName( java.lang.String value ) {
		this.__style_name = value;
	}

	public java.lang.String getClassName() {
		return this.__class_name;
	}

	public void setClassName( java.lang.String value ) {
		this.__class_name = value;
	}

	public java.lang.String getExecutePageName() {
		return this.__execute_page_name;
	}

	public void setExecutePageName( java.lang.String value ) {
		this.__execute_page_name = value;
	}

	public java.math.BigDecimal getMarginTop() {
		return this.__margin_top;
	}

	public void setMarginTop( java.math.BigDecimal value ) {
		this.__margin_top = value;
	}

	public java.math.BigDecimal getMarginBottom() {
		return this.__margin_bottom;
	}

	public void setMarginBottom( java.math.BigDecimal value ) {
		this.__margin_bottom = value;
	}

	public java.lang.String getIconColor() {
		return this.__icon_color;
	}

	public void setIconColor( java.lang.String value ) {
		this.__icon_color = value;
	}

	public java.lang.String getAppPageName() {
		return this.__app_page_name;
	}

	public void setAppPageName( java.lang.String value ) {
		this.__app_page_name = value;
	}

	public void cloneCopy(BaseMobileFunction __bean){
		__bean.setFunctionId(getFunctionId());
		__bean.setFunctionCode(getFunctionCode());
		__bean.setParentId(getParentId());
		__bean.setFunctionName(getFunctionName());
		__bean.setApplicationId(getApplicationId());
		__bean.setFunctionType(getFunctionType());
		__bean.setPrivilegeType(getPrivilegeType());
		__bean.setEnabled(getEnabled());
		__bean.setFunctionStaticName(getFunctionStaticName());
		__bean.setIconName(getIconName());
		__bean.setStyleName(getStyleName());
		__bean.setClassName(getClassName());
		__bean.setExecutePageName(getExecutePageName());
		__bean.setMarginTop(getMarginTop());
		__bean.setMarginBottom(getMarginBottom());
		__bean.setIconColor(getIconColor());
		__bean.setAppPageName(getAppPageName());
	}

	public java.lang.String toCSVString() {

		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
		StringBuilder sb = new StringBuilder();
		sb.append(getFunctionId() == null ? "" : getFunctionId());
		sb.append(",");
		sb.append(getFunctionCode() == null ? "" : getFunctionCode());
		sb.append(",");
		String strParentId = delicacy.system.executor.SelectValueCache.getSelectValue("contracts_1", String.valueOf(getParentId()));
		sb.append(strParentId == null ? "" : strParentId);
		sb.append(",");
		sb.append(getFunctionName() == null ? "" : getFunctionName());
		sb.append(",");
		String strApplicationId = delicacy.system.executor.SelectValueCache.getSelectValue("app_systems", String.valueOf(getApplicationId()));
		sb.append(strApplicationId == null ? "" : strApplicationId);
		sb.append(",");
		sb.append(getFunctionType() == null ? "" : getFunctionType());
		sb.append(",");
		sb.append(getPrivilegeType() == null ? "" : getPrivilegeType());
		sb.append(",");
		sb.append(getEnabled() == null ? "" : getEnabled());
		sb.append(",");
		sb.append(getFunctionStaticName() == null ? "" : getFunctionStaticName());
		sb.append(",");
		sb.append(getIconName() == null ? "" : getIconName());
		sb.append(",");
		sb.append(getStyleName() == null ? "" : getStyleName());
		sb.append(",");
		sb.append(getClassName() == null ? "" : getClassName());
		sb.append(",");
		sb.append(getExecutePageName() == null ? "" : getExecutePageName());
		sb.append(",");
		sb.append(getMarginTop() == null ? "" : getMarginTop());
		sb.append(",");
		sb.append(getMarginBottom() == null ? "" : getMarginBottom());
		sb.append(",");
		sb.append(getIconColor() == null ? "" : getIconColor());
		sb.append(",");
		sb.append(getAppPageName() == null ? "" : getAppPageName());
		return sb.toString();
	}

	@Override
	public int compareTo(BaseMobileFunction o) {
		return __function_id == null ? -1 : __function_id.compareTo(o.getFunctionId());
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 97 * hash + Objects.hashCode(this.__function_id);
		hash = 97 * hash + Objects.hashCode(this.__function_code);
		hash = 97 * hash + Objects.hashCode(this.__parent_id);
		hash = 97 * hash + Objects.hashCode(this.__function_name);
		hash = 97 * hash + Objects.hashCode(this.__application_id);
		hash = 97 * hash + Objects.hashCode(this.__function_type);
		hash = 97 * hash + Objects.hashCode(this.__privilege_type);
		hash = 97 * hash + Objects.hashCode(this.__enabled);
		hash = 97 * hash + Objects.hashCode(this.__function_static_name);
		hash = 97 * hash + Objects.hashCode(this.__icon_name);
		hash = 97 * hash + Objects.hashCode(this.__style_name);
		hash = 97 * hash + Objects.hashCode(this.__class_name);
		hash = 97 * hash + Objects.hashCode(this.__execute_page_name);
		hash = 97 * hash + Objects.hashCode(this.__margin_top);
		hash = 97 * hash + Objects.hashCode(this.__margin_bottom);
		hash = 97 * hash + Objects.hashCode(this.__icon_color);
		hash = 97 * hash + Objects.hashCode(this.__app_page_name);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(getClass() != obj.getClass()) return false;
		final BaseMobileFunction o = (BaseMobileFunction)obj;
		if(!Objects.equals(this.__function_id, o.getFunctionId())) return false;
		if(!Objects.equals(this.__function_code, o.getFunctionCode())) return false;
		if(!Objects.equals(this.__parent_id, o.getParentId())) return false;
		if(!Objects.equals(this.__function_name, o.getFunctionName())) return false;
		if(!Objects.equals(this.__application_id, o.getApplicationId())) return false;
		if(!Objects.equals(this.__function_type, o.getFunctionType())) return false;
		if(!Objects.equals(this.__privilege_type, o.getPrivilegeType())) return false;
		if(!Objects.equals(this.__enabled, o.getEnabled())) return false;
		if(!Objects.equals(this.__function_static_name, o.getFunctionStaticName())) return false;
		if(!Objects.equals(this.__icon_name, o.getIconName())) return false;
		if(!Objects.equals(this.__style_name, o.getStyleName())) return false;
		if(!Objects.equals(this.__class_name, o.getClassName())) return false;
		if(!Objects.equals(this.__execute_page_name, o.getExecutePageName())) return false;
		if(!Objects.equals(this.__margin_top, o.getMarginTop())) return false;
		if(!Objects.equals(this.__margin_bottom, o.getMarginBottom())) return false;
		if(!Objects.equals(this.__icon_color, o.getIconColor())) return false;
		if(!Objects.equals(this.__app_page_name, o.getAppPageName())) return false;
		return true;
	}

	@Override
	public java.lang.String toJSONString() {

		StringBuilder sb = new StringBuilder();
		int count = 0;
		if(getFunctionId() != null) sb.append(__wrapNumber(count++, "functionId", getFunctionId()));
		if(getFunctionCode() != null) sb.append(__wrapString(count++, "functionCode", getFunctionCode()));
		if(getParentId() != null) sb.append(__wrapNumber(count++, "parentId", getParentId()));
		if(getFunctionName() != null) sb.append(__wrapString(count++, "functionName", getFunctionName()));
		if(getApplicationId() != null) sb.append(__wrapNumber(count++, "applicationId", getApplicationId()));
		if(getFunctionType() != null) sb.append(__wrapNumber(count++, "functionType", getFunctionType()));
		if(getPrivilegeType() != null) sb.append(__wrapNumber(count++, "privilegeType", getPrivilegeType()));
		if(getEnabled() != null) sb.append(__wrapBoolean(count++, "enabled", getEnabled()));
		if(getFunctionStaticName() != null) sb.append(__wrapString(count++, "functionStaticName", getFunctionStaticName()));
		if(getIconName() != null) sb.append(__wrapString(count++, "iconName", getIconName()));
		if(getStyleName() != null) sb.append(__wrapString(count++, "styleName", getStyleName()));
		if(getClassName() != null) sb.append(__wrapString(count++, "className", getClassName()));
		if(getExecutePageName() != null) sb.append(__wrapString(count++, "executePageName", getExecutePageName()));
		if(getMarginTop() != null) sb.append(__wrapDecimal(count++, "marginTop", getMarginTop()));
		if(getMarginBottom() != null) sb.append(__wrapDecimal(count++, "marginBottom", getMarginBottom()));
		if(getIconColor() != null) sb.append(__wrapString(count++, "iconColor", getIconColor()));
		if(getAppPageName() != null) sb.append(__wrapString(count++, "appPageName", getAppPageName()));
		return sb.toString();
	}

	public Map<String, Object> toMap() {
		Map<String, Object> res = new HashMap<>();
		if(getFunctionId() != null) res.put("functionId", getFunctionId());
		if(getFunctionCode() != null) res.put("functionCode", getFunctionCode());
		if(getParentId() != null) res.put("parentId", getParentId());
		if(getFunctionName() != null) res.put("functionName", getFunctionName());
		if(getApplicationId() != null) res.put("applicationId", getApplicationId());
		if(getFunctionType() != null) res.put("functionType", getFunctionType());
		if(getPrivilegeType() != null) res.put("privilegeType", getPrivilegeType());
		if(getEnabled() != null) res.put("enabled", getEnabled());
		if(getFunctionStaticName() != null) res.put("functionStaticName", getFunctionStaticName());
		if(getIconName() != null) res.put("iconName", getIconName());
		if(getStyleName() != null) res.put("styleName", getStyleName());
		if(getClassName() != null) res.put("className", getClassName());
		if(getExecutePageName() != null) res.put("executePageName", getExecutePageName());
		if(getMarginTop() != null) res.put("marginTop", getMarginTop());
		if(getMarginBottom() != null) res.put("marginBottom", getMarginBottom());
		if(getIconColor() != null) res.put("iconColor", getIconColor());
		if(getAppPageName() != null) res.put("appPageName", getAppPageName());
		return res;
	}

	@Override
	public void setDataFromMap(Map<String, Object> values){
		Object val;
		if((val = values.get("functionId")) != null) setFunctionId(__getInt(val)); 
		if((val = values.get("functionCode")) != null) setFunctionCode(__getString(val));
		if((val = values.get("parentId")) != null) setParentId(__getInt(val)); 
		if((val = values.get("functionName")) != null) setFunctionName(__getString(val));
		if((val = values.get("applicationId")) != null) setApplicationId(__getInt(val)); 
		if((val = values.get("functionType")) != null) setFunctionType(__getInt(val)); 
		if((val = values.get("privilegeType")) != null) setPrivilegeType(__getInt(val)); 
		if((val = values.get("enabled")) != null) setEnabled(__getBoolean(val));
		if((val = values.get("functionStaticName")) != null) setFunctionStaticName(__getString(val));
		if((val = values.get("iconName")) != null) setIconName(__getString(val));
		if((val = values.get("styleName")) != null) setStyleName(__getString(val));
		if((val = values.get("className")) != null) setClassName(__getString(val));
		if((val = values.get("executePageName")) != null) setExecutePageName(__getString(val));
		if((val = values.get("marginTop")) != null) setMarginTop(__getDecimal(val));  
		if((val = values.get("marginBottom")) != null) setMarginBottom(__getDecimal(val));  
		if((val = values.get("iconColor")) != null) setIconColor(__getString(val));
		if((val = values.get("appPageName")) != null) setAppPageName(__getString(val));
	}

	protected java.lang.Integer  __function_id ;
	protected java.lang.String  __function_code ;
	protected java.lang.Integer  __parent_id ;
	protected java.lang.String  __function_name ;
	protected java.lang.Integer  __application_id ;
	protected java.lang.Integer  __function_type ;
	protected java.lang.Integer  __privilege_type ;
	protected java.lang.Boolean  __enabled ;
	protected java.lang.String  __function_static_name ;
	protected java.lang.String  __icon_name ;
	protected java.lang.String  __style_name ;
	protected java.lang.String  __class_name ;
	protected java.lang.String  __execute_page_name ;
	protected java.math.BigDecimal  __margin_top ;
	protected java.math.BigDecimal  __margin_bottom ;
	protected java.lang.String  __icon_color ;
	protected java.lang.String  __app_page_name ;
}
