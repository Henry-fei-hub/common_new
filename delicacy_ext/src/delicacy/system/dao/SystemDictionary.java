package delicacy.system.dao;

import delicacy.common.AbstractTable;
import delicacy.common.GenericBase;
import org.apache.log4j.Logger;
import delicacy.system.bean.BaseSystemDictionary;

public class SystemDictionary extends AbstractTable<BaseSystemDictionary> {

	private static final Logger __logger = Logger.getLogger(SystemDictionary.class);

	public SystemDictionary() throws java.sql.SQLException {

		initColumnNames();
	}

	protected final void initColumnNames() throws java.sql.SQLException {

		__columnCount = 10;

		initTables();

		set__TableName("system_dictionary");

		__key_columns = new int[1];
		__key_columns[0] = 0;

		__column_names[0] = BaseSystemDictionary.CS_DICTIONARY_ID;
		__column_names[1] = BaseSystemDictionary.CS_DIC_TYPE_ID;
		__column_names[2] = BaseSystemDictionary.CS_DIC_TYPE_NAME;
		__column_names[3] = BaseSystemDictionary.CS_DIC_TYPE_VALUE_ID;
		__column_names[4] = BaseSystemDictionary.CS_DIC_TYPE_VALUE_NAME;
		__column_names[5] = BaseSystemDictionary.CS_DIC_IS_ENABLE;
		__column_names[6] = BaseSystemDictionary.CS_CREATER_ID;
		__column_names[7] = BaseSystemDictionary.CS_CREATER;
		__column_names[8] = BaseSystemDictionary.CS_EDITOR_ID;
		__column_names[9] = BaseSystemDictionary.CS_EDITOR;

		resetSelectFlags();
		resetInsertFlags();
	}

	public void resetInsertFlags() {
		for (int i = 0; i < __columnCount; i++) {
			__insert_flags[i] = true;
		}
		__insert_flags[0] = false;
		__serial_column = 0;
	}

	public void setPrimaryKeyFromBase(BaseSystemDictionary b) {
		setDictionaryIdClear(b.getDictionaryId());
	}

	public boolean isPrimaryKeyNull() {
		return getDictionaryId() == null;
	}

	@Override
	public BaseSystemDictionary generateBase() {
		BaseSystemDictionary b = new BaseSystemDictionary();
		setDataToBase(b);
		return b;
	}

	@Override
	public void setDataToBase(BaseSystemDictionary b) {
		int count = 0;
		Object val;
		if ((val = __current_data[count++]) != null) {
			b.setDictionaryId(GenericBase.__getInt(val));
		}
		if ((val = __current_data[count++]) != null) {
			b.setDicTypeId(GenericBase.__getInt(val));
		}
		if ((val = __current_data[count++]) != null) {
			b.setDicTypeName(GenericBase.__getString(val));
		}
		if ((val = __current_data[count++]) != null) {
			b.setDicTypeValueId(GenericBase.__getInt(val));
		}
		if ((val = __current_data[count++]) != null) {
			b.setDicTypeValueName(GenericBase.__getString(val));
		}
		if ((val = __current_data[count++]) != null) {
			b.setDicIsEnable(GenericBase.__getBoolean(val));
		}
		if ((val = __current_data[count++]) != null) {
			b.setCreaterId(GenericBase.__getInt(val));
		}
		if ((val = __current_data[count++]) != null) {
			b.setCreater(GenericBase.__getString(val));
		}
		if ((val = __current_data[count++]) != null) {
			b.setEditorId(GenericBase.__getInt(val));
		}
		if ((val = __current_data[count++]) != null) {
			b.setEditor(GenericBase.__getString(val));
		}
	}

	@Override
	public void setBaseToBuffer(BaseSystemDictionary b, Object[] buff) {
		int count = 0;
		buff[count++] = b.getDictionaryId();
		buff[count++] = b.getDicTypeId();
		buff[count++] = b.getDicTypeName();
		buff[count++] = b.getDicTypeValueId();
		buff[count++] = b.getDicTypeValueName();
		buff[count++] = b.getDicIsEnable();
		buff[count++] = b.getCreaterId();
		buff[count++] = b.getCreater();
		buff[count++] = b.getEditorId();
		buff[count++] = b.getEditor();
	}

	@Override
	public void setDataFromBase(BaseSystemDictionary b) {
		if (b.getDictionaryId() != null) {
			setDictionaryIdClear(b.getDictionaryId());
		}
		if (b.getDicTypeId() != null) {
			setDicTypeId(b.getDicTypeId());
		}
		if (b.getDicTypeName() != null) {
			setDicTypeName(b.getDicTypeName());
		}
		if (b.getDicTypeValueId() != null) {
			setDicTypeValueId(b.getDicTypeValueId());
		}
		if (b.getDicTypeValueName() != null) {
			setDicTypeValueName(b.getDicTypeValueName());
		}
		if (b.getDicIsEnable() != null) {
			setDicIsEnable(b.getDicIsEnable());
		}
		if (b.getCreaterId() != null) {
			setCreaterId(b.getCreaterId());
		}
		if (b.getCreater() != null) {
			setCreater(b.getCreater());
		}
		if (b.getEditorId() != null) {
			setEditorId(b.getEditorId());
		}
		if (b.getEditor() != null) {
			setEditor(b.getEditor());
		}
	}

	@Override
	public BaseSystemDictionary generateBaseFromResultSet(java.sql.ResultSet sdr) throws java.sql.SQLException {

		BaseSystemDictionary b = new BaseSystemDictionary();
		setQueryResultToBase(b, sdr);
		return b;
	}

	@Override
	public void setQueryResultToBase(BaseSystemDictionary __base, java.sql.ResultSet sdr) throws java.sql.SQLException {
		int count = 1, index = 0;
		Object val;
		if (__select_flags[index++] && (val = sdr.getObject(count++)) != null) {
			__base.setDictionaryId(GenericBase.__getInt(val));
		}
		if (__select_flags[index++] && (val = sdr.getObject(count++)) != null) {
			__base.setDicTypeId(GenericBase.__getInt(val));
		}
		if (__select_flags[index++] && (val = sdr.getObject(count++)) != null) {
			__base.setDicTypeName(GenericBase.__getString(val));
		}
		if (__select_flags[index++] && (val = sdr.getObject(count++)) != null) {
			__base.setDicTypeValueId(GenericBase.__getInt(val));
		}
		if (__select_flags[index++] && (val = sdr.getObject(count++)) != null) {
			__base.setDicTypeValueName(GenericBase.__getString(val));
		}
		if (__select_flags[index++] && (val = sdr.getObject(count++)) != null) {
			__base.setDicIsEnable(GenericBase.__getBoolean(val));
		}
		if (__select_flags[index++] && (val = sdr.getObject(count++)) != null) {
			__base.setCreaterId(GenericBase.__getInt(val));
		}
		if (__select_flags[index++] && (val = sdr.getObject(count++)) != null) {
			__base.setCreater(GenericBase.__getString(val));
		}
		if (__select_flags[index++] && (val = sdr.getObject(count++)) != null) {
			__base.setEditorId(GenericBase.__getInt(val));
		}
		if (__select_flags[index++] && (val = sdr.getObject(count++)) != null) {
			__base.setEditor(GenericBase.__getString(val));
		}
	}

	public void setDictionaryId(java.lang.Integer val) {
		setCurrentData(0, val);
	}

	public java.lang.Integer getDictionaryId() {
		return (java.lang.Integer) __current_data[0];
	}

	public void setDictionaryIdClear(java.lang.Integer val) {
		__backup_data[0] = __current_data[0] = val;
		__modified_flags[0] = false;
	}

	public void setDicTypeId(java.lang.Integer val) {
		setCurrentData(1, val);
	}

	public java.lang.Integer getDicTypeId() {
		return (java.lang.Integer) __current_data[1];
	}

	public void setDicTypeName(java.lang.String val) {
		setCurrentData(2, val);
	}

	public java.lang.String getDicTypeName() {
		return (java.lang.String) __current_data[2];
	}

	public void setDicTypeValueId(java.lang.Integer val) {
		setCurrentData(3, val);
	}

	public java.lang.Integer getDicTypeValueId() {
		return (java.lang.Integer) __current_data[3];
	}

	public void setDicTypeValueName(java.lang.String val) {
		setCurrentData(4, val);
	}

	public java.lang.String getDicTypeValueName() {
		return (java.lang.String) __current_data[4];
	}

	public void setDicIsEnable(java.lang.Boolean val) {
		setCurrentData(5, val);
	}

	public java.lang.Boolean getDicIsEnable() {
		return (java.lang.Boolean) __current_data[5];
	}

	public void setCreaterId(java.lang.Integer val) {
		setCurrentData(6, val);
	}

	public java.lang.Integer getCreaterId() {
		return (java.lang.Integer) __current_data[6];
	}

	public void setCreater(java.lang.String val) {
		setCurrentData(7, val);
	}

	public java.lang.String getCreater() {
		return (java.lang.String) __current_data[7];
	}

	public void setEditorId(java.lang.Integer val) {
		setCurrentData(8, val);
	}

	public java.lang.Integer getEditorId() {
		return (java.lang.Integer) __current_data[8];
	}

	public void setEditor(java.lang.String val) {
		setCurrentData(9, val);
	}

	public java.lang.String getEditor() {
		return (java.lang.String) __current_data[9];
	}

	public void setConditionDictionaryId(String op, java.lang.Integer val) {
		setConditionDictionaryId(op, val, CONDITION_AND);
	}

	public void setConditionDictionaryId(String op, java.lang.Integer val, String relation) {
		addCondition(0, op, relation, val);
	}

	public void setSelectDictionaryId(boolean val) {
		__select_flags[0] = val;
	}

	public void setConditionDicTypeId(String op, java.lang.Integer val) {
		setConditionDicTypeId(op, val, CONDITION_AND);
	}

	public void setConditionDicTypeId(String op, java.lang.Integer val, String relation) {
		addCondition(1, op, relation, val);
	}

	public void setSelectDicTypeId(boolean val) {
		__select_flags[1] = val;
	}

	public void setConditionDicTypeName(String op, java.lang.String val) {
		setConditionDicTypeName(op, val, CONDITION_AND);
	}

	public void setConditionDicTypeName(String op, java.lang.String val, String relation) {
		addCondition(2, op, relation, val);
	}

	public void setSelectDicTypeName(boolean val) {
		__select_flags[2] = val;
	}

	public void setConditionDicTypeValueId(String op, java.lang.Integer val) {
		setConditionDicTypeValueId(op, val, CONDITION_AND);
	}

	public void setConditionDicTypeValueId(String op, java.lang.Integer val, String relation) {
		addCondition(3, op, relation, val);
	}

	public void setSelectDicTypeValueId(boolean val) {
		__select_flags[3] = val;
	}

	public void setConditionDicTypeValueName(String op, java.lang.String val) {
		setConditionDicTypeValueName(op, val, CONDITION_AND);
	}

	public void setConditionDicTypeValueName(String op, java.lang.String val, String relation) {
		addCondition(4, op, relation, val);
	}

	public void setSelectDicTypeValueName(boolean val) {
		__select_flags[4] = val;
	}

	public void setConditionDicIsEnable(String op, java.lang.Boolean val) {
		setConditionDicIsEnable(op, val, CONDITION_AND);
	}

	public void setConditionDicIsEnable(String op, java.lang.Boolean val, String relation) {
		addCondition(5, op, relation, val);
	}

	public void setSelectDicIsEnable(boolean val) {
		__select_flags[5] = val;
	}

	public void setConditionCreaterId(String op, java.lang.Integer val) {
		setConditionCreaterId(op, val, CONDITION_AND);
	}

	public void setConditionCreaterId(String op, java.lang.Integer val, String relation) {
		addCondition(6, op, relation, val);
	}

	public void setSelectCreaterId(boolean val) {
		__select_flags[6] = val;
	}

	public void setConditionCreater(String op, java.lang.String val) {
		setConditionCreater(op, val, CONDITION_AND);
	}

	public void setConditionCreater(String op, java.lang.String val, String relation) {
		addCondition(7, op, relation, val);
	}

	public void setSelectCreater(boolean val) {
		__select_flags[7] = val;
	}

	public void setConditionEditorId(String op, java.lang.Integer val) {
		setConditionEditorId(op, val, CONDITION_AND);
	}

	public void setConditionEditorId(String op, java.lang.Integer val, String relation) {
		addCondition(8, op, relation, val);
	}

	public void setSelectEditorId(boolean val) {
		__select_flags[8] = val;
	}

	public void setConditionEditor(String op, java.lang.String val) {
		setConditionEditor(op, val, CONDITION_AND);
	}

	public void setConditionEditor(String op, java.lang.String val, String relation) {
		addCondition(9, op, relation, val);
	}

	public void setSelectEditor(boolean val) {
		__select_flags[9] = val;
	}

}
