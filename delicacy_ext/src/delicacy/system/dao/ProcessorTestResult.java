package delicacy.system.dao;

import delicacy.common.AbstractTable;
import delicacy.common.GenericBase;
import delicacy.system.bean.BaseProcessorTestResult;


public class ProcessorTestResult extends AbstractTable<BaseProcessorTestResult>
{

	public ProcessorTestResult() throws java.sql.SQLException 
	{

		initColumnNames();
	}

	protected final void initColumnNames()  throws java.sql.SQLException {

		__columnCount          = 12;

		initTables();

		__tableName            = "processor_test_results";

		__key_columns          = new int[1];
		__key_columns[0] = 0;

		__column_names[0] = BaseProcessorTestResult.CS_PROCESSOR_TEST_RESULT_ID;
		__column_names[1] = BaseProcessorTestResult.CS_DRAFTER;
		__column_names[2] = BaseProcessorTestResult.CS_DEPARTMENT_ID;
		__column_names[3] = BaseProcessorTestResult.CS_PLATE_ID;
		__column_names[4] = BaseProcessorTestResult.CS_COMPANY_ID;
		__column_names[5] = BaseProcessorTestResult.CS_PROCESS_ID;
		__column_names[6] = BaseProcessorTestResult.CS_TEST_DATA;
		__column_names[7] = BaseProcessorTestResult.CS_TEST_RESULT;
		__column_names[8] = BaseProcessorTestResult.CS_ERROR_MSG;
		__column_names[9] = BaseProcessorTestResult.CS_START_TIME;
		__column_names[10] = BaseProcessorTestResult.CS_END_TIME;
		__column_names[11] = BaseProcessorTestResult.CS_THREAD_TASK_MANAGE_ID;

		resetSelectFlags() ;
		resetInsertFlags() ;
	}

	public void resetInsertFlags(){
		for(int i = 0; i < __columnCount; i ++ ) __insert_flags[i] = true;
		__insert_flags[0] = false;
		__serial_column = 0;
	}

	public void setPrimaryKeyFromBase(BaseProcessorTestResult b) {
		clear();
		setProcessorTestResultIdClear(b.getProcessorTestResultId());
	}

	public boolean isPrimaryKeyNull() {
		return getProcessorTestResultId() == null;
	}

	@Override
	public BaseProcessorTestResult generateBase(){
		BaseProcessorTestResult b = new BaseProcessorTestResult();
		setDataToBase(b);
		return b;
	}

	@Override
	public void setDataToBase(BaseProcessorTestResult b){
		int count = 0; Object val;
		if((val = __current_data[count++]) != null) b.setProcessorTestResultId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setDrafter(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setDepartmentId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setPlateId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setCompanyId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setProcessId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setTestData(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setTestResult(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setErrorMsg(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setStartTime(GenericBase.__getDateFromSQL(val));
		if((val = __current_data[count++]) != null) b.setEndTime(GenericBase.__getDateFromSQL(val));
		if((val = __current_data[count++]) != null) b.setThreadTaskManageId(GenericBase.__getInt(val));
	}

	@Override
	public void setBaseToBuffer(BaseProcessorTestResult b, Object[] buff){
		int count = 0;
		buff[count++] = b.getProcessorTestResultId();
		buff[count++] = b.getDrafter();
		buff[count++] = b.getDepartmentId();
		buff[count++] = b.getPlateId();
		buff[count++] = b.getCompanyId();
		buff[count++] = b.getProcessId();
		buff[count++] = b.getTestData();
		buff[count++] = b.getTestResult();
		buff[count++] = b.getErrorMsg();
		buff[count++] = generateTimestampFromDate(b.getStartTime());
		buff[count++] = generateTimestampFromDate(b.getEndTime());
		buff[count++] = b.getThreadTaskManageId();
	}

	@Override
	public void setDataFromBase(BaseProcessorTestResult b){
		if(b.getProcessorTestResultId() != null) setProcessorTestResultIdClear(b.getProcessorTestResultId());
		if(b.getDrafter() != null) setDrafter(b.getDrafter());
		if(b.getDepartmentId() != null) setDepartmentId(b.getDepartmentId());
		if(b.getPlateId() != null) setPlateId(b.getPlateId());
		if(b.getCompanyId() != null) setCompanyId(b.getCompanyId());
		if(b.getProcessId() != null) setProcessId(b.getProcessId());
		if(b.getTestData() != null) setTestData(b.getTestData());
		if(b.getTestResult() != null) setTestResult(b.getTestResult());
		if(b.getErrorMsg() != null) setErrorMsg(b.getErrorMsg());
		if(b.getStartTime() != null) setStartTime(b.getStartTime());
		if(b.getEndTime() != null) setEndTime(b.getEndTime());
		if(b.getThreadTaskManageId() != null) setThreadTaskManageId(b.getThreadTaskManageId());
	}

	@Override
	public BaseProcessorTestResult generateBaseFromResultSet(java.sql.ResultSet sdr)  throws java.sql.SQLException {

		BaseProcessorTestResult b = new BaseProcessorTestResult();
		setQueryResultToBase(b, sdr);
		return b;
	}

	@Override
	public void setQueryResultToBase(BaseProcessorTestResult __base, java.sql.ResultSet sdr ) throws java.sql.SQLException {
		int count = 1, index = 0;
		java.sql.ResultSetMetaData rsmd = sdr.getMetaData();
		int columnCount = rsmd.getColumnCount();
		Object val;
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setProcessorTestResultId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setDrafter(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setDepartmentId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setPlateId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setCompanyId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setProcessId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setTestData(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setTestResult(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setErrorMsg(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setStartTime(GenericBase.__getDateFromSQL(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setEndTime(GenericBase.__getDateFromSQL(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setThreadTaskManageId(GenericBase.__getInt(val));
	}

	public void setProcessorTestResultId(java.lang.Integer val) {
		setCurrentData(0, val);
	}

	public java.lang.Integer getProcessorTestResultId() {
		return GenericBase.__getInt(__current_data[0]);
	}

	public void setProcessorTestResultIdClear(java.lang.Integer val) {
		__backup_data[0] = __current_data[0] =  val;
		__modified_flags[0] = false;
	}

	public void setDrafter(java.lang.Integer val) {
		setCurrentData(1, val);
	}

	public java.lang.Integer getDrafter() {
		return GenericBase.__getInt(__current_data[1]);
	}

	public void setDepartmentId(java.lang.Integer val) {
		setCurrentData(2, val);
	}

	public java.lang.Integer getDepartmentId() {
		return GenericBase.__getInt(__current_data[2]);
	}

	public void setPlateId(java.lang.Integer val) {
		setCurrentData(3, val);
	}

	public java.lang.Integer getPlateId() {
		return GenericBase.__getInt(__current_data[3]);
	}

	public void setCompanyId(java.lang.Integer val) {
		setCurrentData(4, val);
	}

	public java.lang.Integer getCompanyId() {
		return GenericBase.__getInt(__current_data[4]);
	}

	public void setProcessId(java.lang.Integer val) {
		setCurrentData(5, val);
	}

	public java.lang.Integer getProcessId() {
		return GenericBase.__getInt(__current_data[5]);
	}

	public void setTestData(java.lang.String val) {
		setCurrentData(6, val);
	}

	public java.lang.String getTestData() {
		return GenericBase.__getString(__current_data[6]);
	}

	public void setTestResult(java.lang.Integer val) {
		setCurrentData(7, val);
	}

	public java.lang.Integer getTestResult() {
		return GenericBase.__getInt(__current_data[7]);
	}

	public void setErrorMsg(java.lang.String val) {
		setCurrentData(8, val);
	}

	public java.lang.String getErrorMsg() {
		return GenericBase.__getString(__current_data[8]);
	}

	public void setStartTime(java.util.Date val) {
		setCurrentData(9, generateTimestampFromDate(val));
	}

	public java.util.Date getStartTime() {
		return GenericBase.__getDateFromSQL(__current_data[9]);
	}

	public void setEndTime(java.util.Date val) {
		setCurrentData(10, generateTimestampFromDate(val));
	}

	public java.util.Date getEndTime() {
		return GenericBase.__getDateFromSQL(__current_data[10]);
	}

	public void setThreadTaskManageId(java.lang.Integer val) {
		setCurrentData(11, val);
	}

	public java.lang.Integer getThreadTaskManageId() {
		return GenericBase.__getInt(__current_data[11]);
	}

	public void setConditionProcessorTestResultId(String op, java.lang.Integer val) {
		setConditionProcessorTestResultId(op, val, CONDITION_AND);
	}

	public void setConditionProcessorTestResultId(String op, java.lang.Integer val, String relation) {
		addCondition(0, op, relation, val);
	}

	public void setSelectProcessorTestResultId(boolean val) {
		__select_flags[0] = val;
	}

	public void setConditionDrafter(String op, java.lang.Integer val) {
		setConditionDrafter(op, val, CONDITION_AND);
	}

	public void setConditionDrafter(String op, java.lang.Integer val, String relation) {
		addCondition(1, op, relation, val);
	}

	public void setSelectDrafter(boolean val) {
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

	public void setConditionPlateId(String op, java.lang.Integer val) {
		setConditionPlateId(op, val, CONDITION_AND);
	}

	public void setConditionPlateId(String op, java.lang.Integer val, String relation) {
		addCondition(3, op, relation, val);
	}

	public void setSelectPlateId(boolean val) {
		__select_flags[3] = val;
	}

	public void setConditionCompanyId(String op, java.lang.Integer val) {
		setConditionCompanyId(op, val, CONDITION_AND);
	}

	public void setConditionCompanyId(String op, java.lang.Integer val, String relation) {
		addCondition(4, op, relation, val);
	}

	public void setSelectCompanyId(boolean val) {
		__select_flags[4] = val;
	}

	public void setConditionProcessId(String op, java.lang.Integer val) {
		setConditionProcessId(op, val, CONDITION_AND);
	}

	public void setConditionProcessId(String op, java.lang.Integer val, String relation) {
		addCondition(5, op, relation, val);
	}

	public void setSelectProcessId(boolean val) {
		__select_flags[5] = val;
	}

	public void setConditionTestData(String op, java.lang.String val) {
		setConditionTestData(op, val, CONDITION_AND);
	}

	public void setConditionTestData(String op, java.lang.String val, String relation) {
		addCondition(6, op, relation, val);
	}

	public void setSelectTestData(boolean val) {
		__select_flags[6] = val;
	}

	public void setConditionTestResult(String op, java.lang.Integer val) {
		setConditionTestResult(op, val, CONDITION_AND);
	}

	public void setConditionTestResult(String op, java.lang.Integer val, String relation) {
		addCondition(7, op, relation, val);
	}

	public void setSelectTestResult(boolean val) {
		__select_flags[7] = val;
	}

	public void setConditionErrorMsg(String op, java.lang.String val) {
		setConditionErrorMsg(op, val, CONDITION_AND);
	}

	public void setConditionErrorMsg(String op, java.lang.String val, String relation) {
		addCondition(8, op, relation, val);
	}

	public void setSelectErrorMsg(boolean val) {
		__select_flags[8] = val;
	}

	public void setConditionStartTime(String op, java.util.Date val) {
		setConditionStartTime(op, val, CONDITION_AND);
	}

	public void setConditionStartTime(String op, java.util.Date val, String relation) {
		addCondition(9, op, relation, generateTimestampFromDate(val));
	}

	public void setSelectStartTime(boolean val) {
		__select_flags[9] = val;
	}

	public void setConditionEndTime(String op, java.util.Date val) {
		setConditionEndTime(op, val, CONDITION_AND);
	}

	public void setConditionEndTime(String op, java.util.Date val, String relation) {
		addCondition(10, op, relation, generateTimestampFromDate(val));
	}

	public void setSelectEndTime(boolean val) {
		__select_flags[10] = val;
	}

	public void setConditionThreadTaskManageId(String op, java.lang.Integer val) {
		setConditionThreadTaskManageId(op, val, CONDITION_AND);
	}

	public void setConditionThreadTaskManageId(String op, java.lang.Integer val, String relation) {
		addCondition(11, op, relation, val);
	}

	public void setSelectThreadTaskManageId(boolean val) {
		__select_flags[11] = val;
	}


}

