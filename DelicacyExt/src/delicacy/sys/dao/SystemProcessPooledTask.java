package delicacy.sys.dao;

import delicacy.common.AbstractTable;
import delicacy.common.GenericBase;
import delicacy.sys.bean.BaseSystemProcessPooledTask;


public class SystemProcessPooledTask extends AbstractTable<BaseSystemProcessPooledTask>
{

	public SystemProcessPooledTask() throws java.sql.SQLException 
	{

		initColumnNames();
	}

	protected final void initColumnNames()  throws java.sql.SQLException {

		__columnCount          = 22;

		initTables();

		__tableName            = "system_process_pooled_tasks";

		__key_columns          = new int[1];
		__key_columns[0] = 0;

		__column_names[0] = BaseSystemProcessPooledTask.CS_PROCESS_POOLED_TASK_ID;
		__column_names[1] = BaseSystemProcessPooledTask.CS_PROCESS_TYPE;
		__column_names[2] = BaseSystemProcessPooledTask.CS_BUSINESS_ID;
		__column_names[3] = BaseSystemProcessPooledTask.CS_BUSINESS_NAME;
		__column_names[4] = BaseSystemProcessPooledTask.CS_PROCESS_ACTIVITY_ID;
		__column_names[5] = BaseSystemProcessPooledTask.CS_PROCESS_ID;
		__column_names[6] = BaseSystemProcessPooledTask.CS_PROCESS_INSTANCE_ID;
		__column_names[7] = BaseSystemProcessPooledTask.CS_BACK_VIEW_NAME;
		__column_names[8] = BaseSystemProcessPooledTask.CS_ACTIVITY_TYPE;
		__column_names[9] = BaseSystemProcessPooledTask.CS_NODE_TYPE;
		__column_names[10] = BaseSystemProcessPooledTask.CS_ACTIVITY_ID;
		__column_names[11] = BaseSystemProcessPooledTask.CS_INSTANCE_ACTIVITY_ID;
		__column_names[12] = BaseSystemProcessPooledTask.CS_INSTANCE_ACTIVITY_CREATE_TIME;
		__column_names[13] = BaseSystemProcessPooledTask.CS_INSTANCE_ACTIVITY_START_TIME;
		__column_names[14] = BaseSystemProcessPooledTask.CS_EMPLOYEE_ID;
		__column_names[15] = BaseSystemProcessPooledTask.CS_OPERATE_TIME;
		__column_names[16] = BaseSystemProcessPooledTask.CS_PROCESS_COMMENT;
		__column_names[17] = BaseSystemProcessPooledTask.CS_STATUS;
		__column_names[18] = BaseSystemProcessPooledTask.CS_DELETE_FLAG;
		__column_names[19] = BaseSystemProcessPooledTask.CS_MESSAGE_STATUS;
		__column_names[20] = BaseSystemProcessPooledTask.CS_TIME_OUT_MESSAGE_STATUS;
		__column_names[21] = BaseSystemProcessPooledTask.CS_ORGANIZATION_ID;

		resetSelectFlags() ;
		resetInsertFlags() ;
	}

	public void resetInsertFlags(){
		for(int i = 0; i < __columnCount; i ++ ) __insert_flags[i] = true;
		__insert_flags[0] = false;
		__serial_column = 0;
	}

	public void setPrimaryKeyFromBase(BaseSystemProcessPooledTask b) {
		clear();
		setProcessPooledTaskIdClear(b.getProcessPooledTaskId());
	}

	public boolean isPrimaryKeyNull() {
		return getProcessPooledTaskId() == null;
	}

	@Override
	public BaseSystemProcessPooledTask generateBase(){
		BaseSystemProcessPooledTask b = new BaseSystemProcessPooledTask();
		setDataToBase(b);
		return b;
	}

	@Override
	public void setDataToBase(BaseSystemProcessPooledTask b){
		int count = 0; Object val;
		if((val = __current_data[count++]) != null) b.setProcessPooledTaskId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setProcessType(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setBusinessId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setBusinessName(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setProcessActivityId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setProcessId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setProcessInstanceId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setBackViewName(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setActivityType(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setNodeType(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setActivityId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setInstanceActivityId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setInstanceActivityCreateTime(GenericBase.__getDateFromSQL(val));
		if((val = __current_data[count++]) != null) b.setInstanceActivityStartTime(GenericBase.__getDateFromSQL(val));
		if((val = __current_data[count++]) != null) b.setEmployeeId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setOperateTime(GenericBase.__getDateFromSQL(val));
		if((val = __current_data[count++]) != null) b.setProcessComment(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setStatus(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setDeleteFlag(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setMessageStatus(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setTimeOutMessageStatus(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setOrganizationId(GenericBase.__getInt(val));
	}

	@Override
	public void setBaseToBuffer(BaseSystemProcessPooledTask b, Object[] buff){
		int count = 0;
		buff[count++] = b.getProcessPooledTaskId();
		buff[count++] = b.getProcessType();
		buff[count++] = b.getBusinessId();
		buff[count++] = b.getBusinessName();
		buff[count++] = b.getProcessActivityId();
		buff[count++] = b.getProcessId();
		buff[count++] = b.getProcessInstanceId();
		buff[count++] = b.getBackViewName();
		buff[count++] = b.getActivityType();
		buff[count++] = b.getNodeType();
		buff[count++] = b.getActivityId();
		buff[count++] = b.getInstanceActivityId();
		buff[count++] = generateTimestampFromDate(b.getInstanceActivityCreateTime());
		buff[count++] = generateTimestampFromDate(b.getInstanceActivityStartTime());
		buff[count++] = b.getEmployeeId();
		buff[count++] = generateTimestampFromDate(b.getOperateTime());
		buff[count++] = b.getProcessComment();
		buff[count++] = b.getStatus();
		buff[count++] = b.getDeleteFlag();
		buff[count++] = b.getMessageStatus();
		buff[count++] = b.getTimeOutMessageStatus();
		buff[count++] = b.getOrganizationId();
	}

	@Override
	public void setDataFromBase(BaseSystemProcessPooledTask b){
		if(b.getProcessPooledTaskId() != null) setProcessPooledTaskIdClear(b.getProcessPooledTaskId());
		if(b.getProcessType() != null) setProcessType(b.getProcessType());
		if(b.getBusinessId() != null) setBusinessId(b.getBusinessId());
		if(b.getBusinessName() != null) setBusinessName(b.getBusinessName());
		if(b.getProcessActivityId() != null) setProcessActivityId(b.getProcessActivityId());
		if(b.getProcessId() != null) setProcessId(b.getProcessId());
		if(b.getProcessInstanceId() != null) setProcessInstanceId(b.getProcessInstanceId());
		if(b.getBackViewName() != null) setBackViewName(b.getBackViewName());
		if(b.getActivityType() != null) setActivityType(b.getActivityType());
		if(b.getNodeType() != null) setNodeType(b.getNodeType());
		if(b.getActivityId() != null) setActivityId(b.getActivityId());
		if(b.getInstanceActivityId() != null) setInstanceActivityId(b.getInstanceActivityId());
		if(b.getInstanceActivityCreateTime() != null) setInstanceActivityCreateTime(b.getInstanceActivityCreateTime());
		if(b.getInstanceActivityStartTime() != null) setInstanceActivityStartTime(b.getInstanceActivityStartTime());
		if(b.getEmployeeId() != null) setEmployeeId(b.getEmployeeId());
		if(b.getOperateTime() != null) setOperateTime(b.getOperateTime());
		if(b.getProcessComment() != null) setProcessComment(b.getProcessComment());
		if(b.getStatus() != null) setStatus(b.getStatus());
		if(b.getDeleteFlag() != null) setDeleteFlag(b.getDeleteFlag());
		if(b.getMessageStatus() != null) setMessageStatus(b.getMessageStatus());
		if(b.getTimeOutMessageStatus() != null) setTimeOutMessageStatus(b.getTimeOutMessageStatus());
		if(b.getOrganizationId() != null) setOrganizationId(b.getOrganizationId());
	}

	@Override
	public BaseSystemProcessPooledTask generateBaseFromResultSet(java.sql.ResultSet sdr)  throws java.sql.SQLException {

		BaseSystemProcessPooledTask b = new BaseSystemProcessPooledTask();
		setQueryResultToBase(b, sdr);
		return b;
	}

	@Override
	public void setQueryResultToBase(BaseSystemProcessPooledTask __base, java.sql.ResultSet sdr ) throws java.sql.SQLException {
		int count = 1, index = 0;
		java.sql.ResultSetMetaData rsmd = sdr.getMetaData();
		int columnCount = rsmd.getColumnCount();
		Object val;
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setProcessPooledTaskId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setProcessType(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setBusinessId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setBusinessName(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setProcessActivityId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setProcessId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setProcessInstanceId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setBackViewName(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setActivityType(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setNodeType(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setActivityId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setInstanceActivityId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setInstanceActivityCreateTime(GenericBase.__getDateFromSQL(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setInstanceActivityStartTime(GenericBase.__getDateFromSQL(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setEmployeeId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setOperateTime(GenericBase.__getDateFromSQL(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setProcessComment(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setStatus(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setDeleteFlag(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setMessageStatus(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setTimeOutMessageStatus(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setOrganizationId(GenericBase.__getInt(val));
	}

	public void setProcessPooledTaskId(java.lang.Integer val) {
		setCurrentData(0, val);
	}

	public java.lang.Integer getProcessPooledTaskId() {
		return GenericBase.__getInt(__current_data[0]);
	}

	public void setProcessPooledTaskIdClear(java.lang.Integer val) {
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

	public void setProcessActivityId(java.lang.Integer val) {
		setCurrentData(4, val);
	}

	public java.lang.Integer getProcessActivityId() {
		return GenericBase.__getInt(__current_data[4]);
	}

	public void setProcessId(java.lang.Integer val) {
		setCurrentData(5, val);
	}

	public java.lang.Integer getProcessId() {
		return GenericBase.__getInt(__current_data[5]);
	}

	public void setProcessInstanceId(java.lang.Integer val) {
		setCurrentData(6, val);
	}

	public java.lang.Integer getProcessInstanceId() {
		return GenericBase.__getInt(__current_data[6]);
	}

	public void setBackViewName(java.lang.String val) {
		setCurrentData(7, val);
	}

	public java.lang.String getBackViewName() {
		return GenericBase.__getString(__current_data[7]);
	}

	public void setActivityType(java.lang.Integer val) {
		setCurrentData(8, val);
	}

	public java.lang.Integer getActivityType() {
		return GenericBase.__getInt(__current_data[8]);
	}

	public void setNodeType(java.lang.Integer val) {
		setCurrentData(9, val);
	}

	public java.lang.Integer getNodeType() {
		return GenericBase.__getInt(__current_data[9]);
	}

	public void setActivityId(java.lang.Integer val) {
		setCurrentData(10, val);
	}

	public java.lang.Integer getActivityId() {
		return GenericBase.__getInt(__current_data[10]);
	}

	public void setInstanceActivityId(java.lang.Integer val) {
		setCurrentData(11, val);
	}

	public java.lang.Integer getInstanceActivityId() {
		return GenericBase.__getInt(__current_data[11]);
	}

	public void setInstanceActivityCreateTime(java.util.Date val) {
		setCurrentData(12, generateTimestampFromDate(val));
	}

	public java.util.Date getInstanceActivityCreateTime() {
		return GenericBase.__getDateFromSQL(__current_data[12]);
	}

	public void setInstanceActivityStartTime(java.util.Date val) {
		setCurrentData(13, generateTimestampFromDate(val));
	}

	public java.util.Date getInstanceActivityStartTime() {
		return GenericBase.__getDateFromSQL(__current_data[13]);
	}

	public void setEmployeeId(java.lang.Integer val) {
		setCurrentData(14, val);
	}

	public java.lang.Integer getEmployeeId() {
		return GenericBase.__getInt(__current_data[14]);
	}

	public void setOperateTime(java.util.Date val) {
		setCurrentData(15, generateTimestampFromDate(val));
	}

	public java.util.Date getOperateTime() {
		return GenericBase.__getDateFromSQL(__current_data[15]);
	}

	public void setProcessComment(java.lang.String val) {
		setCurrentData(16, val);
	}

	public java.lang.String getProcessComment() {
		return GenericBase.__getString(__current_data[16]);
	}

	public void setStatus(java.lang.Integer val) {
		setCurrentData(17, val);
	}

	public java.lang.Integer getStatus() {
		return GenericBase.__getInt(__current_data[17]);
	}

	public void setDeleteFlag(java.lang.Integer val) {
		setCurrentData(18, val);
	}

	public java.lang.Integer getDeleteFlag() {
		return GenericBase.__getInt(__current_data[18]);
	}

	public void setMessageStatus(java.lang.Integer val) {
		setCurrentData(19, val);
	}

	public java.lang.Integer getMessageStatus() {
		return GenericBase.__getInt(__current_data[19]);
	}

	public void setTimeOutMessageStatus(java.lang.Integer val) {
		setCurrentData(20, val);
	}

	public java.lang.Integer getTimeOutMessageStatus() {
		return GenericBase.__getInt(__current_data[20]);
	}

	public void setOrganizationId(java.lang.Integer val) {
		setCurrentData(21, val);
	}

	public java.lang.Integer getOrganizationId() {
		return GenericBase.__getInt(__current_data[21]);
	}

	public void setConditionProcessPooledTaskId(String op, java.lang.Integer val) {
		setConditionProcessPooledTaskId(op, val, CONDITION_AND);
	}

	public void setConditionProcessPooledTaskId(String op, java.lang.Integer val, String relation) {
		addCondition(0, op, relation, val);
	}

	public void setSelectProcessPooledTaskId(boolean val) {
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

	public void setConditionProcessActivityId(String op, java.lang.Integer val) {
		setConditionProcessActivityId(op, val, CONDITION_AND);
	}

	public void setConditionProcessActivityId(String op, java.lang.Integer val, String relation) {
		addCondition(4, op, relation, val);
	}

	public void setSelectProcessActivityId(boolean val) {
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

	public void setConditionProcessInstanceId(String op, java.lang.Integer val) {
		setConditionProcessInstanceId(op, val, CONDITION_AND);
	}

	public void setConditionProcessInstanceId(String op, java.lang.Integer val, String relation) {
		addCondition(6, op, relation, val);
	}

	public void setSelectProcessInstanceId(boolean val) {
		__select_flags[6] = val;
	}

	public void setConditionBackViewName(String op, java.lang.String val) {
		setConditionBackViewName(op, val, CONDITION_AND);
	}

	public void setConditionBackViewName(String op, java.lang.String val, String relation) {
		addCondition(7, op, relation, val);
	}

	public void setSelectBackViewName(boolean val) {
		__select_flags[7] = val;
	}

	public void setConditionActivityType(String op, java.lang.Integer val) {
		setConditionActivityType(op, val, CONDITION_AND);
	}

	public void setConditionActivityType(String op, java.lang.Integer val, String relation) {
		addCondition(8, op, relation, val);
	}

	public void setSelectActivityType(boolean val) {
		__select_flags[8] = val;
	}

	public void setConditionNodeType(String op, java.lang.Integer val) {
		setConditionNodeType(op, val, CONDITION_AND);
	}

	public void setConditionNodeType(String op, java.lang.Integer val, String relation) {
		addCondition(9, op, relation, val);
	}

	public void setSelectNodeType(boolean val) {
		__select_flags[9] = val;
	}

	public void setConditionActivityId(String op, java.lang.Integer val) {
		setConditionActivityId(op, val, CONDITION_AND);
	}

	public void setConditionActivityId(String op, java.lang.Integer val, String relation) {
		addCondition(10, op, relation, val);
	}

	public void setSelectActivityId(boolean val) {
		__select_flags[10] = val;
	}

	public void setConditionInstanceActivityId(String op, java.lang.Integer val) {
		setConditionInstanceActivityId(op, val, CONDITION_AND);
	}

	public void setConditionInstanceActivityId(String op, java.lang.Integer val, String relation) {
		addCondition(11, op, relation, val);
	}

	public void setSelectInstanceActivityId(boolean val) {
		__select_flags[11] = val;
	}

	public void setConditionInstanceActivityCreateTime(String op, java.util.Date val) {
		setConditionInstanceActivityCreateTime(op, val, CONDITION_AND);
	}

	public void setConditionInstanceActivityCreateTime(String op, java.util.Date val, String relation) {
		addCondition(12, op, relation, generateTimestampFromDate(val));
	}

	public void setSelectInstanceActivityCreateTime(boolean val) {
		__select_flags[12] = val;
	}

	public void setConditionInstanceActivityStartTime(String op, java.util.Date val) {
		setConditionInstanceActivityStartTime(op, val, CONDITION_AND);
	}

	public void setConditionInstanceActivityStartTime(String op, java.util.Date val, String relation) {
		addCondition(13, op, relation, generateTimestampFromDate(val));
	}

	public void setSelectInstanceActivityStartTime(boolean val) {
		__select_flags[13] = val;
	}

	public void setConditionEmployeeId(String op, java.lang.Integer val) {
		setConditionEmployeeId(op, val, CONDITION_AND);
	}

	public void setConditionEmployeeId(String op, java.lang.Integer val, String relation) {
		addCondition(14, op, relation, val);
	}

	public void setSelectEmployeeId(boolean val) {
		__select_flags[14] = val;
	}

	public void setConditionOperateTime(String op, java.util.Date val) {
		setConditionOperateTime(op, val, CONDITION_AND);
	}

	public void setConditionOperateTime(String op, java.util.Date val, String relation) {
		addCondition(15, op, relation, generateTimestampFromDate(val));
	}

	public void setSelectOperateTime(boolean val) {
		__select_flags[15] = val;
	}

	public void setConditionProcessComment(String op, java.lang.String val) {
		setConditionProcessComment(op, val, CONDITION_AND);
	}

	public void setConditionProcessComment(String op, java.lang.String val, String relation) {
		addCondition(16, op, relation, val);
	}

	public void setSelectProcessComment(boolean val) {
		__select_flags[16] = val;
	}

	public void setConditionStatus(String op, java.lang.Integer val) {
		setConditionStatus(op, val, CONDITION_AND);
	}

	public void setConditionStatus(String op, java.lang.Integer val, String relation) {
		addCondition(17, op, relation, val);
	}

	public void setSelectStatus(boolean val) {
		__select_flags[17] = val;
	}

	public void setConditionDeleteFlag(String op, java.lang.Integer val) {
		setConditionDeleteFlag(op, val, CONDITION_AND);
	}

	public void setConditionDeleteFlag(String op, java.lang.Integer val, String relation) {
		addCondition(18, op, relation, val);
	}

	public void setSelectDeleteFlag(boolean val) {
		__select_flags[18] = val;
	}

	public void setConditionMessageStatus(String op, java.lang.Integer val) {
		setConditionMessageStatus(op, val, CONDITION_AND);
	}

	public void setConditionMessageStatus(String op, java.lang.Integer val, String relation) {
		addCondition(19, op, relation, val);
	}

	public void setSelectMessageStatus(boolean val) {
		__select_flags[19] = val;
	}

	public void setConditionTimeOutMessageStatus(String op, java.lang.Integer val) {
		setConditionTimeOutMessageStatus(op, val, CONDITION_AND);
	}

	public void setConditionTimeOutMessageStatus(String op, java.lang.Integer val, String relation) {
		addCondition(20, op, relation, val);
	}

	public void setSelectTimeOutMessageStatus(boolean val) {
		__select_flags[20] = val;
	}

	public void setConditionOrganizationId(String op, java.lang.Integer val) {
		setConditionOrganizationId(op, val, CONDITION_AND);
	}

	public void setConditionOrganizationId(String op, java.lang.Integer val, String relation) {
		addCondition(21, op, relation, val);
	}

	public void setSelectOrganizationId(boolean val) {
		__select_flags[21] = val;
	}


}

