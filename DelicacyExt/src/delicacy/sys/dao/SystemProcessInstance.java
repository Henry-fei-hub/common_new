package delicacy.sys.dao;

import delicacy.common.AbstractTable;
import delicacy.common.GenericBase;
import delicacy.sys.bean.BaseSystemProcessInstance;


public class SystemProcessInstance extends AbstractTable<BaseSystemProcessInstance>
{

	public SystemProcessInstance() throws java.sql.SQLException 
	{

		initColumnNames();
	}

	protected final void initColumnNames()  throws java.sql.SQLException {

		__columnCount          = 13;

		initTables();

		__tableName            = "system_process_instances";

		__key_columns          = new int[1];
		__key_columns[0] = 0;

		__column_names[0] = BaseSystemProcessInstance.CS_PROCESS_INSTANCE_ID;
		__column_names[1] = BaseSystemProcessInstance.CS_PROCESS_TYPE;
		__column_names[2] = BaseSystemProcessInstance.CS_BUSINESS_ID;
		__column_names[3] = BaseSystemProcessInstance.CS_BUSINESS_NAME;
		__column_names[4] = BaseSystemProcessInstance.CS_PROCESS_ID;
		__column_names[5] = BaseSystemProcessInstance.CS_PROCESS_INSTANCE_ACTIVITY_ID;
		__column_names[6] = BaseSystemProcessInstance.CS_PROCESS_STATUS;
		__column_names[7] = BaseSystemProcessInstance.CS_EMPLOYEE_ID;
		__column_names[8] = BaseSystemProcessInstance.CS_CREATE_TIME;
		__column_names[9] = BaseSystemProcessInstance.CS_COMPLETE_TIME;
		__column_names[10] = BaseSystemProcessInstance.CS_DELETE_FLAG;
		__column_names[11] = BaseSystemProcessInstance.CS_OTHER_OPERATION;
		__column_names[12] = BaseSystemProcessInstance.CS_ORGANIZATION_ID;

		resetSelectFlags() ;
		resetInsertFlags() ;
	}

	public void resetInsertFlags(){
		for(int i = 0; i < __columnCount; i ++ ) __insert_flags[i] = true;
		__insert_flags[0] = false;
		__serial_column = 0;
	}

	public void setPrimaryKeyFromBase(BaseSystemProcessInstance b) {
		clear();
		setProcessInstanceIdClear(b.getProcessInstanceId());
	}

	public boolean isPrimaryKeyNull() {
		return getProcessInstanceId() == null;
	}

	@Override
	public BaseSystemProcessInstance generateBase(){
		BaseSystemProcessInstance b = new BaseSystemProcessInstance();
		setDataToBase(b);
		return b;
	}

	@Override
	public void setDataToBase(BaseSystemProcessInstance b){
		int count = 0; Object val;
		if((val = __current_data[count++]) != null) b.setProcessInstanceId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setProcessType(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setBusinessId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setBusinessName(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setProcessId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setProcessInstanceActivityId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setProcessStatus(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setEmployeeId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setCreateTime(GenericBase.__getDateFromSQL(val));
		if((val = __current_data[count++]) != null) b.setCompleteTime(GenericBase.__getDateFromSQL(val));
		if((val = __current_data[count++]) != null) b.setDeleteFlag(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setOtherOperation(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setOrganizationId(GenericBase.__getInt(val));
	}

	@Override
	public void setBaseToBuffer(BaseSystemProcessInstance b, Object[] buff){
		int count = 0;
		buff[count++] = b.getProcessInstanceId();
		buff[count++] = b.getProcessType();
		buff[count++] = b.getBusinessId();
		buff[count++] = b.getBusinessName();
		buff[count++] = b.getProcessId();
		buff[count++] = b.getProcessInstanceActivityId();
		buff[count++] = b.getProcessStatus();
		buff[count++] = b.getEmployeeId();
		buff[count++] = generateTimestampFromDate(b.getCreateTime());
		buff[count++] = generateTimestampFromDate(b.getCompleteTime());
		buff[count++] = b.getDeleteFlag();
		buff[count++] = b.getOtherOperation();
		buff[count++] = b.getOrganizationId();
	}

	@Override
	public void setDataFromBase(BaseSystemProcessInstance b){
		if(b.getProcessInstanceId() != null) setProcessInstanceIdClear(b.getProcessInstanceId());
		if(b.getProcessType() != null) setProcessType(b.getProcessType());
		if(b.getBusinessId() != null) setBusinessId(b.getBusinessId());
		if(b.getBusinessName() != null) setBusinessName(b.getBusinessName());
		if(b.getProcessId() != null) setProcessId(b.getProcessId());
		if(b.getProcessInstanceActivityId() != null) setProcessInstanceActivityId(b.getProcessInstanceActivityId());
		if(b.getProcessStatus() != null) setProcessStatus(b.getProcessStatus());
		if(b.getEmployeeId() != null) setEmployeeId(b.getEmployeeId());
		if(b.getCreateTime() != null) setCreateTime(b.getCreateTime());
		if(b.getCompleteTime() != null) setCompleteTime(b.getCompleteTime());
		if(b.getDeleteFlag() != null) setDeleteFlag(b.getDeleteFlag());
		if(b.getOtherOperation() != null) setOtherOperation(b.getOtherOperation());
		if(b.getOrganizationId() != null) setOrganizationId(b.getOrganizationId());
	}

	@Override
	public BaseSystemProcessInstance generateBaseFromResultSet(java.sql.ResultSet sdr)  throws java.sql.SQLException {

		BaseSystemProcessInstance b = new BaseSystemProcessInstance();
		setQueryResultToBase(b, sdr);
		return b;
	}

	@Override
	public void setQueryResultToBase(BaseSystemProcessInstance __base, java.sql.ResultSet sdr ) throws java.sql.SQLException {
		int count = 1, index = 0;
		java.sql.ResultSetMetaData rsmd = sdr.getMetaData();
		int columnCount = rsmd.getColumnCount();
		Object val;
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setProcessInstanceId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setProcessType(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setBusinessId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setBusinessName(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setProcessId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setProcessInstanceActivityId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setProcessStatus(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setEmployeeId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setCreateTime(GenericBase.__getDateFromSQL(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setCompleteTime(GenericBase.__getDateFromSQL(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setDeleteFlag(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setOtherOperation(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setOrganizationId(GenericBase.__getInt(val));
	}

	public void setProcessInstanceId(java.lang.Integer val) {
		setCurrentData(0, val);
	}

	public java.lang.Integer getProcessInstanceId() {
		return GenericBase.__getInt(__current_data[0]);
	}

	public void setProcessInstanceIdClear(java.lang.Integer val) {
		__backup_data[0] = __current_data[0] =  val;
		__modified_flags[0] = false;
	}

	public void setProcessType(java.lang.Integer val) {
		setCurrentData(1, val);
	}

	public java.lang.Integer getProcessType() {
		return GenericBase.__getInt(__current_data[1]);
	}

	public void setBusinessId(java.lang.Integer val) {
		setCurrentData(2, val);
	}

	public java.lang.Integer getBusinessId() {
		return GenericBase.__getInt(__current_data[2]);
	}

	public void setBusinessName(java.lang.String val) {
		setCurrentData(3, val);
	}

	public java.lang.String getBusinessName() {
		return GenericBase.__getString(__current_data[3]);
	}

	public void setProcessId(java.lang.Integer val) {
		setCurrentData(4, val);
	}

	public java.lang.Integer getProcessId() {
		return GenericBase.__getInt(__current_data[4]);
	}

	public void setProcessInstanceActivityId(java.lang.Integer val) {
		setCurrentData(5, val);
	}

	public java.lang.Integer getProcessInstanceActivityId() {
		return GenericBase.__getInt(__current_data[5]);
	}

	public void setProcessStatus(java.lang.Integer val) {
		setCurrentData(6, val);
	}

	public java.lang.Integer getProcessStatus() {
		return GenericBase.__getInt(__current_data[6]);
	}

	public void setEmployeeId(java.lang.Integer val) {
		setCurrentData(7, val);
	}

	public java.lang.Integer getEmployeeId() {
		return GenericBase.__getInt(__current_data[7]);
	}

	public void setCreateTime(java.util.Date val) {
		setCurrentData(8, generateTimestampFromDate(val));
	}

	public java.util.Date getCreateTime() {
		return GenericBase.__getDateFromSQL(__current_data[8]);
	}

	public void setCompleteTime(java.util.Date val) {
		setCurrentData(9, generateTimestampFromDate(val));
	}

	public java.util.Date getCompleteTime() {
		return GenericBase.__getDateFromSQL(__current_data[9]);
	}

	public void setDeleteFlag(java.lang.Integer val) {
		setCurrentData(10, val);
	}

	public java.lang.Integer getDeleteFlag() {
		return GenericBase.__getInt(__current_data[10]);
	}

	public void setOtherOperation(java.lang.Integer val) {
		setCurrentData(11, val);
	}

	public java.lang.Integer getOtherOperation() {
		return GenericBase.__getInt(__current_data[11]);
	}

	public void setOrganizationId(java.lang.Integer val) {
		setCurrentData(12, val);
	}

	public java.lang.Integer getOrganizationId() {
		return GenericBase.__getInt(__current_data[12]);
	}

	public void setConditionProcessInstanceId(String op, java.lang.Integer val) {
		setConditionProcessInstanceId(op, val, CONDITION_AND);
	}

	public void setConditionProcessInstanceId(String op, java.lang.Integer val, String relation) {
		addCondition(0, op, relation, val);
	}

	public void setSelectProcessInstanceId(boolean val) {
		__select_flags[0] = val;
	}

	public void setConditionProcessType(String op, java.lang.Integer val) {
		setConditionProcessType(op, val, CONDITION_AND);
	}

	public void setConditionProcessType(String op, java.lang.Integer val, String relation) {
		addCondition(1, op, relation, val);
	}

	public void setSelectProcessType(boolean val) {
		__select_flags[1] = val;
	}

	public void setConditionBusinessId(String op, java.lang.Integer val) {
		setConditionBusinessId(op, val, CONDITION_AND);
	}

	public void setConditionBusinessId(String op, java.lang.Integer val, String relation) {
		addCondition(2, op, relation, val);
	}

	public void setSelectBusinessId(boolean val) {
		__select_flags[2] = val;
	}

	public void setConditionBusinessName(String op, java.lang.String val) {
		setConditionBusinessName(op, val, CONDITION_AND);
	}

	public void setConditionBusinessName(String op, java.lang.String val, String relation) {
		addCondition(3, op, relation, val);
	}

	public void setSelectBusinessName(boolean val) {
		__select_flags[3] = val;
	}

	public void setConditionProcessId(String op, java.lang.Integer val) {
		setConditionProcessId(op, val, CONDITION_AND);
	}

	public void setConditionProcessId(String op, java.lang.Integer val, String relation) {
		addCondition(4, op, relation, val);
	}

	public void setSelectProcessId(boolean val) {
		__select_flags[4] = val;
	}

	public void setConditionProcessInstanceActivityId(String op, java.lang.Integer val) {
		setConditionProcessInstanceActivityId(op, val, CONDITION_AND);
	}

	public void setConditionProcessInstanceActivityId(String op, java.lang.Integer val, String relation) {
		addCondition(5, op, relation, val);
	}

	public void setSelectProcessInstanceActivityId(boolean val) {
		__select_flags[5] = val;
	}

	public void setConditionProcessStatus(String op, java.lang.Integer val) {
		setConditionProcessStatus(op, val, CONDITION_AND);
	}

	public void setConditionProcessStatus(String op, java.lang.Integer val, String relation) {
		addCondition(6, op, relation, val);
	}

	public void setSelectProcessStatus(boolean val) {
		__select_flags[6] = val;
	}

	public void setConditionEmployeeId(String op, java.lang.Integer val) {
		setConditionEmployeeId(op, val, CONDITION_AND);
	}

	public void setConditionEmployeeId(String op, java.lang.Integer val, String relation) {
		addCondition(7, op, relation, val);
	}

	public void setSelectEmployeeId(boolean val) {
		__select_flags[7] = val;
	}

	public void setConditionCreateTime(String op, java.util.Date val) {
		setConditionCreateTime(op, val, CONDITION_AND);
	}

	public void setConditionCreateTime(String op, java.util.Date val, String relation) {
		addCondition(8, op, relation, generateTimestampFromDate(val));
	}

	public void setSelectCreateTime(boolean val) {
		__select_flags[8] = val;
	}

	public void setConditionCompleteTime(String op, java.util.Date val) {
		setConditionCompleteTime(op, val, CONDITION_AND);
	}

	public void setConditionCompleteTime(String op, java.util.Date val, String relation) {
		addCondition(9, op, relation, generateTimestampFromDate(val));
	}

	public void setSelectCompleteTime(boolean val) {
		__select_flags[9] = val;
	}

	public void setConditionDeleteFlag(String op, java.lang.Integer val) {
		setConditionDeleteFlag(op, val, CONDITION_AND);
	}

	public void setConditionDeleteFlag(String op, java.lang.Integer val, String relation) {
		addCondition(10, op, relation, val);
	}

	public void setSelectDeleteFlag(boolean val) {
		__select_flags[10] = val;
	}

	public void setConditionOtherOperation(String op, java.lang.Integer val) {
		setConditionOtherOperation(op, val, CONDITION_AND);
	}

	public void setConditionOtherOperation(String op, java.lang.Integer val, String relation) {
		addCondition(11, op, relation, val);
	}

	public void setSelectOtherOperation(boolean val) {
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

