package delicacy.sys.dao;

import delicacy.common.AbstractTable;
import delicacy.common.GenericBase;
import delicacy.sys.bean.BaseSystemProcessType;


public class SystemProcessType extends AbstractTable<BaseSystemProcessType>
{

	public SystemProcessType() throws java.sql.SQLException 
	{

		initColumnNames();
	}

	protected final void initColumnNames()  throws java.sql.SQLException {

		__columnCount          = 9;

		initTables();

		__tableName            = "system_process_types";

		__key_columns          = new int[1];
		__key_columns[0] = 0;

		__column_names[0] = BaseSystemProcessType.CS_PROCESS_TYPE_ID;
		__column_names[1] = BaseSystemProcessType.CS_PROCESS_TYPE_NAME;
		__column_names[2] = BaseSystemProcessType.CS_DESCRIPTION;
		__column_names[3] = BaseSystemProcessType.CS_PROCESS_EXECUTE_NAME;
		__column_names[4] = BaseSystemProcessType.CS_IS_ENABLE;
		__column_names[5] = BaseSystemProcessType.CS_PARENT_PROCESS_TYPE_ID;
		__column_names[6] = BaseSystemProcessType.CS_ICON;
		__column_names[7] = BaseSystemProcessType.CS_IS_ATTENDANCE;
		__column_names[8] = BaseSystemProcessType.CS_ORGANIZATION_ID;

		resetSelectFlags() ;
		resetInsertFlags() ;
	}

	public void resetInsertFlags(){
		for(int i = 0; i < __columnCount; i ++ ) __insert_flags[i] = true;
		__insert_flags[0] = false;
		__serial_column = 0;
	}

	public void setPrimaryKeyFromBase(BaseSystemProcessType b) {
		clear();
		setProcessTypeIdClear(b.getProcessTypeId());
	}

	public boolean isPrimaryKeyNull() {
		return getProcessTypeId() == null;
	}

	@Override
	public BaseSystemProcessType generateBase(){
		BaseSystemProcessType b = new BaseSystemProcessType();
		setDataToBase(b);
		return b;
	}

	@Override
	public void setDataToBase(BaseSystemProcessType b){
		int count = 0; Object val;
		if((val = __current_data[count++]) != null) b.setProcessTypeId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setProcessTypeName(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setDescription(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setProcessExecuteName(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setIsEnable(GenericBase.__getBoolean(val));
		if((val = __current_data[count++]) != null) b.setParentProcessTypeId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setIcon(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setIsAttendance(GenericBase.__getBoolean(val));
		if((val = __current_data[count++]) != null) b.setOrganizationId(GenericBase.__getInt(val));
	}

	@Override
	public void setBaseToBuffer(BaseSystemProcessType b, Object[] buff){
		int count = 0;
		buff[count++] = b.getProcessTypeId();
		buff[count++] = b.getProcessTypeName();
		buff[count++] = b.getDescription();
		buff[count++] = b.getProcessExecuteName();
		buff[count++] = b.getIsEnable();
		buff[count++] = b.getParentProcessTypeId();
		buff[count++] = b.getIcon();
		buff[count++] = b.getIsAttendance();
		buff[count++] = b.getOrganizationId();
	}

	@Override
	public void setDataFromBase(BaseSystemProcessType b){
		if(b.getProcessTypeId() != null) setProcessTypeIdClear(b.getProcessTypeId());
		if(b.getProcessTypeName() != null) setProcessTypeName(b.getProcessTypeName());
		if(b.getDescription() != null) setDescription(b.getDescription());
		if(b.getProcessExecuteName() != null) setProcessExecuteName(b.getProcessExecuteName());
		if(b.getIsEnable() != null) setIsEnable(b.getIsEnable());
		if(b.getParentProcessTypeId() != null) setParentProcessTypeId(b.getParentProcessTypeId());
		if(b.getIcon() != null) setIcon(b.getIcon());
		if(b.getIsAttendance() != null) setIsAttendance(b.getIsAttendance());
		if(b.getOrganizationId() != null) setOrganizationId(b.getOrganizationId());
	}

	@Override
	public BaseSystemProcessType generateBaseFromResultSet(java.sql.ResultSet sdr)  throws java.sql.SQLException {

		BaseSystemProcessType b = new BaseSystemProcessType();
		setQueryResultToBase(b, sdr);
		return b;
	}

	@Override
	public void setQueryResultToBase(BaseSystemProcessType __base, java.sql.ResultSet sdr ) throws java.sql.SQLException {
		int count = 1, index = 0;
		java.sql.ResultSetMetaData rsmd = sdr.getMetaData();
		int columnCount = rsmd.getColumnCount();
		Object val;
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setProcessTypeId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setProcessTypeName(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setDescription(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setProcessExecuteName(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setIsEnable(GenericBase.__getBoolean(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setParentProcessTypeId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setIcon(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setIsAttendance(GenericBase.__getBoolean(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setOrganizationId(GenericBase.__getInt(val));
	}

	public void setProcessTypeId(java.lang.Integer val) {
		setCurrentData(0, val);
	}

	public java.lang.Integer getProcessTypeId() {
		return GenericBase.__getInt(__current_data[0]);
	}

	public void setProcessTypeIdClear(java.lang.Integer val) {
		__backup_data[0] = __current_data[0] =  val;
		__modified_flags[0] = false;
	}

	public void setProcessTypeName(java.lang.String val) {
		setCurrentData(1, val);
	}

	public java.lang.String getProcessTypeName() {
		return GenericBase.__getString(__current_data[1]);
	}

	public void setDescription(java.lang.String val) {
		setCurrentData(2, val);
	}

	public java.lang.String getDescription() {
		return GenericBase.__getString(__current_data[2]);
	}

	public void setProcessExecuteName(java.lang.String val) {
		setCurrentData(3, val);
	}

	public java.lang.String getProcessExecuteName() {
		return GenericBase.__getString(__current_data[3]);
	}

	public void setIsEnable(java.lang.Boolean val) {
		setCurrentData(4, val);
	}

	public java.lang.Boolean getIsEnable() {
		return GenericBase.__getBoolean(__current_data[4]);
	}

	public void setParentProcessTypeId(java.lang.Integer val) {
		setCurrentData(5, val);
	}

	public java.lang.Integer getParentProcessTypeId() {
		return GenericBase.__getInt(__current_data[5]);
	}

	public void setIcon(java.lang.String val) {
		setCurrentData(6, val);
	}

	public java.lang.String getIcon() {
		return GenericBase.__getString(__current_data[6]);
	}

	public void setIsAttendance(java.lang.Boolean val) {
		setCurrentData(7, val);
	}

	public java.lang.Boolean getIsAttendance() {
		return GenericBase.__getBoolean(__current_data[7]);
	}

	public void setOrganizationId(java.lang.Integer val) {
		setCurrentData(8, val);
	}

	public java.lang.Integer getOrganizationId() {
		return GenericBase.__getInt(__current_data[8]);
	}

	public void setConditionProcessTypeId(String op, java.lang.Integer val) {
		setConditionProcessTypeId(op, val, CONDITION_AND);
	}

	public void setConditionProcessTypeId(String op, java.lang.Integer val, String relation) {
		addCondition(0, op, relation, val);
	}

	public void setSelectProcessTypeId(boolean val) {
		__select_flags[0] = val;
	}

	public void setConditionProcessTypeName(String op, java.lang.String val) {
		setConditionProcessTypeName(op, val, CONDITION_AND);
	}

	public void setConditionProcessTypeName(String op, java.lang.String val, String relation) {
		addCondition(1, op, relation, val);
	}

	public void setSelectProcessTypeName(boolean val) {
		__select_flags[1] = val;
	}

	public void setConditionDescription(String op, java.lang.String val) {
		setConditionDescription(op, val, CONDITION_AND);
	}

	public void setConditionDescription(String op, java.lang.String val, String relation) {
		addCondition(2, op, relation, val);
	}

	public void setSelectDescription(boolean val) {
		__select_flags[2] = val;
	}

	public void setConditionProcessExecuteName(String op, java.lang.String val) {
		setConditionProcessExecuteName(op, val, CONDITION_AND);
	}

	public void setConditionProcessExecuteName(String op, java.lang.String val, String relation) {
		addCondition(3, op, relation, val);
	}

	public void setSelectProcessExecuteName(boolean val) {
		__select_flags[3] = val;
	}

	public void setConditionIsEnable(String op, java.lang.Boolean val) {
		setConditionIsEnable(op, val, CONDITION_AND);
	}

	public void setConditionIsEnable(String op, java.lang.Boolean val, String relation) {
		addCondition(4, op, relation, val);
	}

	public void setSelectIsEnable(boolean val) {
		__select_flags[4] = val;
	}

	public void setConditionParentProcessTypeId(String op, java.lang.Integer val) {
		setConditionParentProcessTypeId(op, val, CONDITION_AND);
	}

	public void setConditionParentProcessTypeId(String op, java.lang.Integer val, String relation) {
		addCondition(5, op, relation, val);
	}

	public void setSelectParentProcessTypeId(boolean val) {
		__select_flags[5] = val;
	}

	public void setConditionIcon(String op, java.lang.String val) {
		setConditionIcon(op, val, CONDITION_AND);
	}

	public void setConditionIcon(String op, java.lang.String val, String relation) {
		addCondition(6, op, relation, val);
	}

	public void setSelectIcon(boolean val) {
		__select_flags[6] = val;
	}

	public void setConditionIsAttendance(String op, java.lang.Boolean val) {
		setConditionIsAttendance(op, val, CONDITION_AND);
	}

	public void setConditionIsAttendance(String op, java.lang.Boolean val, String relation) {
		addCondition(7, op, relation, val);
	}

	public void setSelectIsAttendance(boolean val) {
		__select_flags[7] = val;
	}

	public void setConditionOrganizationId(String op, java.lang.Integer val) {
		setConditionOrganizationId(op, val, CONDITION_AND);
	}

	public void setConditionOrganizationId(String op, java.lang.Integer val, String relation) {
		addCondition(8, op, relation, val);
	}

	public void setSelectOrganizationId(boolean val) {
		__select_flags[8] = val;
	}


}

