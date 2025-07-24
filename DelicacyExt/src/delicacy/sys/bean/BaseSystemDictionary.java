package delicacy.sys.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import delicacy.common.BaseFactory;
import delicacy.common.GenericBase;

public class BaseSystemDictionary extends GenericBase implements BaseFactory<BaseSystemDictionary>, Comparable<BaseSystemDictionary> 
{


	public static BaseSystemDictionary newInstance(){
		return new BaseSystemDictionary();
	}

	@Override
	public BaseSystemDictionary make(){
		BaseSystemDictionary b = new BaseSystemDictionary();
		return b;
	}

	public final static java.lang.String CS_DICTIONARY_ID = "dictionary_id" ;
	public final static java.lang.String CS_DIC_TYPE_ID = "dic_type_id" ;
	public final static java.lang.String CS_DIC_TYPE_NAME = "dic_type_name" ;
	public final static java.lang.String CS_DIC_TYPE_VALUE_ID = "dic_type_value_id" ;
	public final static java.lang.String CS_DIC_TYPE_VALUE = "dic_type_value" ;
	public final static java.lang.String CS_DIC_IS_ENABLE = "dic_is_enable" ;
	public final static java.lang.String CS_PARENT_ID = "parent_id" ;
	public final static java.lang.String CS_MANAGEMENT_TYPE = "management_type" ;
	public final static java.lang.String CS_ORGANIZATION_ID = "organization_id" ;

	public final static java.lang.String ALL_CAPTIONS = "系统编码,列表数据代码,列表数据名称,数据编码,数据值,是否启用,父代码,,组织机构代码";

	public java.lang.Integer getDictionaryId() {
		return this.__dictionary_id;
	}

	public void setDictionaryId( java.lang.Integer value ) {
		this.__dictionary_id = value;
	}

	public java.lang.Integer getDicTypeId() {
		return this.__dic_type_id;
	}

	public void setDicTypeId( java.lang.Integer value ) {
		this.__dic_type_id = value;
	}

	public java.lang.String getDicTypeName() {
		return this.__dic_type_name;
	}

	public void setDicTypeName( java.lang.String value ) {
		this.__dic_type_name = value;
	}

	public java.lang.Integer getDicTypeValueId() {
		return this.__dic_type_value_id;
	}

	public void setDicTypeValueId( java.lang.Integer value ) {
		this.__dic_type_value_id = value;
	}

	public java.lang.String getDicTypeValue() {
		return this.__dic_type_value;
	}

	public void setDicTypeValue( java.lang.String value ) {
		this.__dic_type_value = value;
	}

	public java.lang.Boolean getDicIsEnable() {
		return this.__dic_is_enable;
	}

	public void setDicIsEnable( java.lang.Boolean value ) {
		this.__dic_is_enable = value;
	}

	public java.lang.Integer getParentId() {
		return this.__parent_id;
	}

	public void setParentId( java.lang.Integer value ) {
		this.__parent_id = value;
	}

	public java.lang.Integer getManagementType() {
		return this.__management_type;
	}

	public void setManagementType( java.lang.Integer value ) {
		this.__management_type = value;
	}

	public java.lang.Integer getOrganizationId() {
		return this.__organization_id;
	}

	public void setOrganizationId( java.lang.Integer value ) {
		this.__organization_id = value;
	}

	public void cloneCopy(BaseSystemDictionary __bean){
		__bean.setDictionaryId(getDictionaryId());
		__bean.setDicTypeId(getDicTypeId());
		__bean.setDicTypeName(getDicTypeName());
		__bean.setDicTypeValueId(getDicTypeValueId());
		__bean.setDicTypeValue(getDicTypeValue());
		__bean.setDicIsEnable(getDicIsEnable());
		__bean.setParentId(getParentId());
		__bean.setManagementType(getManagementType());
		__bean.setOrganizationId(getOrganizationId());
	}

	public java.lang.String toCSVString() {

		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
		StringBuilder sb = new StringBuilder();
		sb.append(getDictionaryId() == null ? "" : getDictionaryId());
		sb.append(",");
		sb.append(getDicTypeId() == null ? "" : getDicTypeId());
		sb.append(",");
		sb.append(getDicTypeName() == null ? "" : getDicTypeName());
		sb.append(",");
		sb.append(getDicTypeValueId() == null ? "" : getDicTypeValueId());
		sb.append(",");
		sb.append(getDicTypeValue() == null ? "" : getDicTypeValue());
		sb.append(",");
		sb.append(getDicIsEnable() == null ? "" : getDicIsEnable());
		sb.append(",");
		sb.append(getParentId() == null ? "" : getParentId());
		sb.append(",");
		sb.append(getManagementType() == null ? "" : getManagementType());
		sb.append(",");
		sb.append(getOrganizationId() == null ? "" : getOrganizationId());
		return sb.toString();
	}

	@Override
	public int compareTo(BaseSystemDictionary o) {
		return __dictionary_id == null ? -1 : __dictionary_id.compareTo(o.getDictionaryId());
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 97 * hash + Objects.hashCode(this.__dictionary_id);
		hash = 97 * hash + Objects.hashCode(this.__dic_type_id);
		hash = 97 * hash + Objects.hashCode(this.__dic_type_name);
		hash = 97 * hash + Objects.hashCode(this.__dic_type_value_id);
		hash = 97 * hash + Objects.hashCode(this.__dic_type_value);
		hash = 97 * hash + Objects.hashCode(this.__dic_is_enable);
		hash = 97 * hash + Objects.hashCode(this.__parent_id);
		hash = 97 * hash + Objects.hashCode(this.__management_type);
		hash = 97 * hash + Objects.hashCode(this.__organization_id);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(getClass() != obj.getClass()) return false;
		final BaseSystemDictionary o = (BaseSystemDictionary)obj;
		if(!Objects.equals(this.__dictionary_id, o.getDictionaryId())) return false;
		if(!Objects.equals(this.__dic_type_id, o.getDicTypeId())) return false;
		if(!Objects.equals(this.__dic_type_name, o.getDicTypeName())) return false;
		if(!Objects.equals(this.__dic_type_value_id, o.getDicTypeValueId())) return false;
		if(!Objects.equals(this.__dic_type_value, o.getDicTypeValue())) return false;
		if(!Objects.equals(this.__dic_is_enable, o.getDicIsEnable())) return false;
		if(!Objects.equals(this.__parent_id, o.getParentId())) return false;
		if(!Objects.equals(this.__management_type, o.getManagementType())) return false;
		if(!Objects.equals(this.__organization_id, o.getOrganizationId())) return false;
		return true;
	}

	@Override
	public java.lang.String toJSONString() {

		StringBuilder sb = new StringBuilder();
		int count = 0;
		if(getDictionaryId() != null) sb.append(__wrapNumber(count++, "dictionaryId", getDictionaryId()));
		if(getDicTypeId() != null) sb.append(__wrapNumber(count++, "dicTypeId", getDicTypeId()));
		if(getDicTypeName() != null) sb.append(__wrapString(count++, "dicTypeName", getDicTypeName()));
		if(getDicTypeValueId() != null) sb.append(__wrapNumber(count++, "dicTypeValueId", getDicTypeValueId()));
		if(getDicTypeValue() != null) sb.append(__wrapString(count++, "dicTypeValue", getDicTypeValue()));
		if(getDicIsEnable() != null) sb.append(__wrapBoolean(count++, "dicIsEnable", getDicIsEnable()));
		if(getParentId() != null) sb.append(__wrapNumber(count++, "parentId", getParentId()));
		if(getManagementType() != null) sb.append(__wrapNumber(count++, "managementType", getManagementType()));
		if(getOrganizationId() != null) sb.append(__wrapNumber(count++, "organizationId", getOrganizationId()));
		return sb.toString();
	}

	public Map<String, Object> toMap() {
		Map<String, Object> res = new HashMap<>();
		if(getDictionaryId() != null) res.put("dictionaryId", getDictionaryId());
		if(getDicTypeId() != null) res.put("dicTypeId", getDicTypeId());
		if(getDicTypeName() != null) res.put("dicTypeName", getDicTypeName());
		if(getDicTypeValueId() != null) res.put("dicTypeValueId", getDicTypeValueId());
		if(getDicTypeValue() != null) res.put("dicTypeValue", getDicTypeValue());
		if(getDicIsEnable() != null) res.put("dicIsEnable", getDicIsEnable());
		if(getParentId() != null) res.put("parentId", getParentId());
		if(getManagementType() != null) res.put("managementType", getManagementType());
		if(getOrganizationId() != null) res.put("organizationId", getOrganizationId());
		return res;
	}

	@Override
	public void setDataFromMap(Map<String, Object> values){
		Object val;
		if((val = values.get("dictionaryId")) != null) setDictionaryId(__getInt(val)); 
		if((val = values.get("dicTypeId")) != null) setDicTypeId(__getInt(val)); 
		if((val = values.get("dicTypeName")) != null) setDicTypeName(__getString(val));
		if((val = values.get("dicTypeValueId")) != null) setDicTypeValueId(__getInt(val)); 
		if((val = values.get("dicTypeValue")) != null) setDicTypeValue(__getString(val));
		if((val = values.get("dicIsEnable")) != null) setDicIsEnable(__getBoolean(val));
		if((val = values.get("parentId")) != null) setParentId(__getInt(val)); 
		if((val = values.get("managementType")) != null) setManagementType(__getInt(val)); 
		if((val = values.get("organizationId")) != null) setOrganizationId(__getInt(val)); 
	}

	protected java.lang.Integer  __dictionary_id ;
	protected java.lang.Integer  __dic_type_id ;
	protected java.lang.String  __dic_type_name ;
	protected java.lang.Integer  __dic_type_value_id ;
	protected java.lang.String  __dic_type_value ;
	protected java.lang.Boolean  __dic_is_enable ;
	protected java.lang.Integer  __parent_id ;
	protected java.lang.Integer  __management_type ;
	protected java.lang.Integer  __organization_id ;
}
