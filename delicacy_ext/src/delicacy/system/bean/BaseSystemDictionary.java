package delicacy.system.bean;

import java.util.Map;
import delicacy.common.GenericBase;
import delicacy.common.BaseFactory;

public class BaseSystemDictionary extends GenericBase implements BaseFactory<BaseSystemDictionary> {

    public static BaseSystemDictionary newInstance() {
        return new BaseSystemDictionary();
    }

    @Override
    public BaseSystemDictionary make() {
        BaseSystemDictionary b = new BaseSystemDictionary();
        return b;
    }

    public final static java.lang.String CS_DICTIONARY_ID = "dictionary_id";
    public final static java.lang.String CS_DIC_TYPE_ID = "dic_type_id";
    public final static java.lang.String CS_DIC_TYPE_NAME = "dic_type_name";
    public final static java.lang.String CS_DIC_TYPE_VALUE_ID = "dic_type_value_id";
    public final static java.lang.String CS_DIC_TYPE_VALUE_NAME = "dic_type_value_name";
    public final static java.lang.String CS_DIC_IS_ENABLE = "dic_is_enable";
    public final static java.lang.String CS_CREATER_ID = "creater_id";
    public final static java.lang.String CS_CREATER = "creater";
    public final static java.lang.String CS_EDITOR_ID = "editor_id";
    public final static java.lang.String CS_EDITOR = "editor";

    public java.lang.Integer getDictionaryId() {
        return this.__dictionary_id;
    }

    public void setDictionaryId(java.lang.Integer value) {
        this.__dictionary_id = value;
    }

    public java.lang.Integer getDicTypeId() {
        return this.__dic_type_id;
    }

    public void setDicTypeId(java.lang.Integer value) {
        this.__dic_type_id = value;
    }

    public java.lang.String getDicTypeName() {
        return this.__dic_type_name;
    }

    public void setDicTypeName(java.lang.String value) {
        this.__dic_type_name = value;
    }

    public java.lang.Integer getDicTypeValueId() {
        return this.__dic_type_value_id;
    }

    public void setDicTypeValueId(java.lang.Integer value) {
        this.__dic_type_value_id = value;
    }

    public java.lang.String getDicTypeValueName() {
        return this.__dic_type_value_name;
    }

    public void setDicTypeValueName(java.lang.String value) {
        this.__dic_type_value_name = value;
    }

    public java.lang.Boolean getDicIsEnable() {
        return this.__dic_is_enable;
    }

    public void setDicIsEnable(java.lang.Boolean value) {
        this.__dic_is_enable = value;
    }

    public java.lang.Integer getCreaterId() {
        return this.__creater_id;
    }

    public void setCreaterId(java.lang.Integer value) {
        this.__creater_id = value;
    }

    public java.lang.String getCreater() {
        return this.__creater;
    }

    public void setCreater(java.lang.String value) {
        this.__creater = value;
    }

    public java.lang.Integer getEditorId() {
        return this.__editor_id;
    }

    public void setEditorId(java.lang.Integer value) {
        this.__editor_id = value;
    }

    public java.lang.String getEditor() {
        return this.__editor;
    }

    public void setEditor(java.lang.String value) {
        this.__editor = value;
    }

    public void cloneCopy(BaseSystemDictionary __bean) {
        __bean.setDictionaryId(getDictionaryId());
        __bean.setDicTypeId(getDicTypeId());
        __bean.setDicTypeName(getDicTypeName());
        __bean.setDicTypeValueId(getDicTypeValueId());
        __bean.setDicTypeValueName(getDicTypeValueName());
        __bean.setDicIsEnable(getDicIsEnable());
        __bean.setCreaterId(getCreaterId());
        __bean.setCreater(getCreater());
        __bean.setEditorId(getEditorId());
        __bean.setEditor(getEditor());
    }

    @Override
    public java.lang.String toJSONString() {

        StringBuilder sb = new StringBuilder();
        int count = 0;
        if (getDictionaryId() != null) {
            sb.append(__wrapNumber(count++, "dictionaryId", getDictionaryId()));
        }
        if (getDicTypeId() != null) {
            sb.append(__wrapNumber(count++, "dicTypeId", getDicTypeId()));
        }
        if (getDicTypeName() != null) {
            sb.append(__wrapString(count++, "dicTypeName", getDicTypeName()));
        }
        if (getDicTypeValueId() != null) {
            sb.append(__wrapNumber(count++, "dicTypeValueId", getDicTypeValueId()));
        }
        if (getDicTypeValueName() != null) {
            sb.append(__wrapString(count++, "dicTypeValueName", getDicTypeValueName()));
        }
        if (getDicIsEnable() != null) {
            sb.append(__wrapBoolean(count++, "dicIsEnable", getDicIsEnable()));
        }
        if (getCreaterId() != null) {
            sb.append(__wrapNumber(count++, "createrId", getCreaterId()));
        }
        if (getCreater() != null) {
            sb.append(__wrapString(count++, "creater", getCreater()));
        }
        if (getEditorId() != null) {
            sb.append(__wrapNumber(count++, "editorId", getEditorId()));
        }
        if (getEditor() != null) {
            sb.append(__wrapString(count++, "editor", getEditor()));
        }
        return sb.toString();
    }

    @Override
    public void setDataFromMap(Map<String, Object> values) {
        Object val;
        if ((val = values.get("dictionaryId")) != null) {
            setDictionaryId(__getInt(val));
        }
        if ((val = values.get("dicTypeId")) != null) {
            setDicTypeId(__getInt(val));
        }
        if ((val = values.get("dicTypeName")) != null) {
            setDicTypeName(__getString(val));
        }
        if ((val = values.get("dicTypeValueId")) != null) {
            setDicTypeValueId(__getInt(val));
        }
        if ((val = values.get("dicTypeValueName")) != null) {
            setDicTypeValueName(__getString(val));
        }
        if ((val = values.get("dicIsEnable")) != null) {
            setDicIsEnable(__getBoolean(val));
        }
        if ((val = values.get("createrId")) != null) {
            setCreaterId(__getInt(val));
        }
        if ((val = values.get("creater")) != null) {
            setCreater(__getString(val));
        }
        if ((val = values.get("editorId")) != null) {
            setEditorId(__getInt(val));
        }
        if ((val = values.get("editor")) != null) {
            setEditor(__getString(val));
        }
    }

    protected java.lang.Integer __dictionary_id;
    protected java.lang.Integer __dic_type_id;
    protected java.lang.String __dic_type_name;
    protected java.lang.Integer __dic_type_value_id;
    protected java.lang.String __dic_type_value_name;
    protected java.lang.Boolean __dic_is_enable;
    protected java.lang.Integer __creater_id;
    protected java.lang.String __creater;
    protected java.lang.Integer __editor_id;
    protected java.lang.String __editor;
}
