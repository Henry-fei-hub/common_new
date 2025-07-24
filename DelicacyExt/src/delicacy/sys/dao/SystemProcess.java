package delicacy.sys.dao;

import delicacy.common.AbstractTable;
import delicacy.common.GenericBase;
import delicacy.sys.bean.BaseSystemProcess;


public class SystemProcess extends AbstractTable<BaseSystemProcess>
{

	public SystemProcess() throws java.sql.SQLException 
	{

		initColumnNames();
	}

	protected final void initColumnNames()  throws java.sql.SQLException {

		__columnCount          = 13;

		initTables();

		__tableName            = "system_processes";

		__key_columns          = new int[1];
		__key_columns[0] = 0;

		__column_names[0] = BaseSystemProcess.CS_PROCESS_ID;
		__column_names[1] = BaseSystemProcess.CS_PROCESS_TYPE_ID;
		__column_names[2] = BaseSystemProcess.CS_DEPARTMENT_ID;
		__column_names[3] = BaseSystemProcess.CS_INCLUDE_DEPARTMENT_ID;
		__column_names[4] = BaseSystemProcess.CS_CREATE_EMPLOYEE_ID;
		__column_names[5] = BaseSystemProcess.CS_CREATE_TIME;
		__column_names[6] = BaseSystemProcess.CS_PROCESS_NAME;
		__column_names[7] = BaseSystemProcess.CS_COUNTERSIGN;
		__column_names[8] = BaseSystemProcess.CS_HOLD_DEPARTMENT_ID;
		__column_names[9] = BaseSystemProcess.CS_HOLD_DUTY_ID;
		__column_names[10] = BaseSystemProcess.CS_DESCRIPTION;
		__column_names[11] = BaseSystemProcess.CS_ENABLE;
		__column_names[12] = BaseSystemProcess.CS_ORGANIZATION_ID;

		resetSelectFlags() ;
		resetInsertFlags() ;
	}

	public void resetInsertFlags(){
		for(int i = 0; i < __columnCount; i ++ ) __insert_flags[i] = true;
		__insert_flags[0] = false;
		__serial_column = 0;
	}

	public void setPrimaryKeyFromBase(BaseSystemProcess b) {
		clear();
		setProcessIdClear(b.getProcessId());
	}

	public boolean isPrimaryKeyNull() {
		return getProcessId() == null;
	}

	@Override
	public BaseSystemProcess generateBase(){
		BaseSystemProcess b = new BaseSystemProcess();
		setDataToBase(b);
		return b;
	}

	@Override
	public void setDataToBase(BaseSystemProcess b){
		int count = 0; Object val;
		if((val = __current_data[count++]) != null) b.setProcessId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setProcessTypeId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setDepartmentId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setIncludeDepartmentId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setCreateEmployeeId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setCreateTime(GenericBase.__getDateFromSQL(val));
		if((val = __current_data[count++]) != null) b.setProcessName(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setCountersign(GenericBase.__getBoolean(val));
		if((val = __current_data[count++]) != null) b.setHoldDepartmentId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setHoldDutyId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setDescription(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setEnable(GenericBase.__getBoolean(val));
		if((val = __current_data[count++]) != null) b.setOrganizationId(GenericBase.__getInt(val));
	}

	@Override
	public void setBaseToBuffer(BaseSystemProcess b, Object[] buff){
		int count = 0;
		buff[count++] = b.getProcessId();
		buff[count++] = b.getProcessTypeId();
		buff[count++] = b.getDepartmentId();
		buff[count++] = b.getIncludeDepartmentId();
		buff[count++] = b.getCreateEmployeeId();
		buff[count++] = generateTimestampFromDate(b.getCreateTime());
		buff[count++] = b.getProcessName();
		buff[count++] = b.getCountersign();
		buff[count++] = b.getHoldDepartmentId();
		buff[count++] = b.getHoldDutyId();
		buff[count++] = b.getDescription();
		buff[count++] = b.getEnable();
		buff[count++] = b.getOrganizationId();
	}

	@Override
	public void setDataFromBase(BaseSystemProcess b){
		if(b.getProcessId() != null) setProcessIdClear(b.getProcessId());
		if(b.getProcessTypeId() != null) setProcessTypeId(b.getProcessTypeId());
		if(b.getDepartmentId() != null) setDepartmentId(b.getDepartmentId());
		if(b.getIncludeDepartmentId() != null) setIncludeDepartmentId(b.getIncludeDepartmentId());
		if(b.getCreateEmployeeId() != null) setCreateEmployeeId(b.getCreateEmployeeId());
		if(b.getCreateTime() != null) setCreateTime(b.getCreateTime());
		if(b.getProcessName() != null) setProcessName(b.getProcessName());
		if(b.getCountersign() != null) setCountersign(b.getCountersign());
		if(b.getHoldDepartmentId() != null) setHoldDepartmentId(b.getHoldDepartmentId());
		if(b.getHoldDutyId() != null) setHoldDutyId(b.getHoldDutyId());
		if(b.getDescription() != null) setDescription(b.getDescription());
		if(b.getEnable() != null) setEnable(b.getEnable());
		if(b.getOrganizationId() != null) setOrganizationId(b.getOrganizationId());
	}

	@Override
	public BaseSystemProcess generateBaseFromResultSet(java.sql.ResultSet sdr)  throws java.sql.SQLException {

		BaseSystemProcess b = new BaseSystemProcess();
		setQueryResultToBase(b, sdr);
		return b;
	}

	@Override
	public void setQueryResultToBase(BaseSystemProcess __base, java.sql.ResultSet sdr ) throws java.sql.SQLException {
		int count = 1, index = 0;
		java.sql.ResultSetMetaData rsmd = sdr.getMetaData();
		int columnCount = rsmd.getColumnCount();
		Object val;
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setProcessId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setProcessTypeId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setDepartmentId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setIncludeDepartmentId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setCreateEmployeeId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setCreateTime(GenericBase.__getDateFromSQL(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setProcessName(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setCountersign(GenericBase.__getBoolean(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setHoldDepartmentId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setHoldDutyId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setDescription(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setEnable(GenericBase.__getBoolean(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setOrganizationId(GenericBase.__getInt(val));
	}

	public void setProcessId(java.lang.Integer val) {
		setCurrentData(0, val);
	}

	public java.lang.Integer getProcessId() {
		return GenericBase.__getInt(__current_data[0]);
	}

	public void setProcessIdClear(java.lang.Integer val) {
		__backup_data[0] = __current_data[0] =  val;
		__modified_flags[0] = false;
	}

	public void setProcessTypeId(java.lang.Integer val) {
		setCurrentData(1, val);
	}

	public java.lang.Integer getProcessTypeId() {
		return GenericBase.__getInt(__current_data[1]);
	}

	public void setDepartmentId(java.lang.Integer val) {
		setCurrentData(2, val);
	}

	public java.lang.Integer getDepartmentId() {
		return GenericBase.__getInt(__current_data[2]);
	}

	public void setIncludeDepartmentId(java.lang.Integer val) {
		setCurrentData(3, val);
	}

	public java.lang.Integer getIncludeDepartmentId() {
		return GenericBase.__getInt(__current_data[3]);
	}

	public void setCreateEmployeeId(java.lang.Integer val) {
		setCurrentData(4, val);
	}

	public java.lang.Integer getCreateEmployeeId() {
		return GenericBase.__getInt(__current_data[4]);
	}

	public void setCreateTime(java.util.Date val) {
		setCurrentData(5, generateTimestampFromDate(val));
	}

	public java.util.Date getCreateTime() {
		return GenericBase.__getDateFromSQL(__current_data[5]);
	}

	public void setProcessName(java.lang.String val) {
		setCurrentData(6, val);
	}

	public java.lang.String getProcessName() {
		return GenericBase.__getString(__current_data[6]);
	}

	public void setCountersign(java.lang.Boolean val) {
		setCurrentData(7, val);
	}

	public java.lang.Boolean getCountersign() {
		return GenericBase.__getBoolean(__current_data[7]);
	}

	public void setHoldDepartmentId(java.lang.Integer val) {
		setCurrentData(8, val);
	}

	public java.lang.Integer getHoldDepartmentId() {
		return GenericBase.__getInt(__current_data[8]);
	}

	public void setHoldDutyId(java.lang.Integer val) {
		setCurrentData(9, val);
	}

	public java.lang.Integer getHoldDutyId() {
		return GenericBase.__getInt(__current_data[9]);
	}

	public void setDescription(java.lang.String val) {
		setCurrentData(10, val);
	}

	public java.lang.String getDescription() {
		return GenericBase.__getString(__current_data[10]);
	}

	public void setEnable(java.lang.Boolean val) {
		setCurrentData(11, val);
	}

	public java.lang.Boolean getEnable() {
		return GenericBase.__getBoolean(__current_data[11]);
	}

	public void setOrganizationId(java.lang.Integer val) {
		setCurrentData(12, val);
	}

	public java.lang.Integer getOrganizationId() {
		return GenericBase.__getInt(__current_data[12]);
	}

	public void setConditionProcessId(String op, java.lang.Integer val) {
		setConditionProcessId(op, val, CONDITION_AND);
	}

	public void setConditionProcessId(String op, java.lang.Integer val, String relation) {
		addCondition(0, op, relation, val);
	}

	public void setSelectProcessId(boolean val) {
		__select_flags[0] = val;
	}

	public void setConditionProcessTypeId(String op, java.lang.Integer val) {
		setConditionProcessTypeId(op, val, CONDITION_AND);
	}

	public void setConditionProcessTypeId(String op, java.lang.Integer val, String relation) {
		addCondition(1, op, relation, val);
	}

	public void setSelectProcessTypeId(boolean val) {
		__select_flags[1] = val;
	}

	public void setConditionDepartmentId(String op, java.lang.Integer val) {
		setConditionDepartmentId(op, val, CONDITION_AND);
	}

	public void setConditionDepartmentId(String op, java.lang.Integer val, String relation) {
		addCondition(2, op, relation, val);
	}

	public void setSelectDepartmentId(boolean val) {
		__select_flags[2] = val;
	}

	public void setConditionIncludeDepartmentId(String op, java.lang.Integer val) {
		setConditionIncludeDepartmentId(op, val, CONDITION_AND);
	}

	public void setConditionIncludeDepartmentId(String op, java.lang.Integer val, String relation) {
		addCondition(3, op, relation, val);
	}

	public void setSelectIncludeDepartmentId(boolean val) {
		__select_flags[3] = val;
	}

	public void setConditionCreateEmployeeId(String op, java.lang.Integer val) {
		setConditionCreateEmployeeId(op, val, CONDITION_AND);
	}

	public void setConditionCreateEmployeeId(String op, java.lang.Integer val, String relation) {
		addCondition(4, op, relation, val);
	}

	public void setSelectCreateEmployeeId(boolean val) {
		__select_flags[4] = val;
	}

	public void setConditionCreateTime(String op, java.util.Date val) {
		setConditionCreateTime(op, val, CONDITION_AND);
	}

	public void setConditionCreateTime(String op, java.util.Date val, String relation) {
		addCondition(5, op, relation, generateTimestampFromDate(val));
	}

	public void setSelectCreateTime(boolean val) {
		__select_flags[5] = val;
	}

	public void setConditionProcessName(String op, java.lang.String val) {
		setConditionProcessName(op, val, CONDITION_AND);
	}

	public void setConditionProcessName(String op, java.lang.String val, String relation) {
		addCondition(6, op, relation, val);
	}

	public void setSelectProcessName(boolean val) {
		__select_flags[6] = val;
	}

	public void setConditionCountersign(String op, java.lang.Boolean val) {
		setConditionCountersign(op, val, CONDITION_AND);
	}

	public void setConditionCountersign(String op, java.lang.Boolean val, String relation) {
		addCondition(7, op, relation, val);
	}

	public void setSelectCountersign(boolean val) {
		__select_flags[7] = val;
	}

	public void setConditionHoldDepartmentId(String op, java.lang.Integer val) {
		setConditionHoldDepartmentId(op, val, CONDITION_AND);
	}

	public void setConditionHoldDepartmentId(String op, java.lang.Integer val, String relation) {
		addCondition(8, op, relation, val);
	}

	public void setSelectHoldDepartmentId(boolean val) {
		__select_flags[8] = val;
	}

	public void setConditionHoldDutyId(String op, java.lang.Integer val) {
		setConditionHoldDutyId(op, val, CONDITION_AND);
	}

	public void setConditionHoldDutyId(String op, java.lang.Integer val, String relation) {
		addCondition(9, op, relation, val);
	}

	public void setSelectHoldDutyId(boolean val) {
		__select_flags[9] = val;
	}

	public void setConditionDescription(String op, java.lang.String val) {
		setConditionDescription(op, val, CONDITION_AND);
	}

	public void setConditionDescription(String op, java.lang.String val, String relation) {
		addCondition(10, op, relation, val);
	}

	public void setSelectDescription(boolean val) {
		__select_flags[10] = val;
	}

	public void setConditionEnable(String op, java.lang.Boolean val) {
		setConditionEnable(op, val, CONDITION_AND);
	}

	public void setConditionEnable(String op, java.lang.Boolean val, String relation) {
		addCondition(11, op, relation, val);
	}

	public void setSelectEnable(boolean val) {
		__select_flags[11] = val;
	}

	public void setConditionOrganizationId(String op, java.lang.Integer val) {
		setConditionOrganizationId(op, val, CONDITION_AND);
	}

	public void setConditionOrganizationId(String op, java.lang.Integer val, String relation) {
		addCondition(12, op, relation, val);
	}

	public void setSelectOrganizationId(boolean val) {
		__select_flags[12] = val;
	}


}

