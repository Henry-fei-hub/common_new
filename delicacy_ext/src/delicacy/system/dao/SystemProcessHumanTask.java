package delicacy.system.dao;

import delicacy.common.AbstractTable;
import delicacy.common.GenericBase;
import delicacy.system.bean.BaseSystemProcessHumanTask;


public class SystemProcessHumanTask extends AbstractTable<BaseSystemProcessHumanTask>
{

	public SystemProcessHumanTask() throws java.sql.SQLException 
	{

		initColumnNames();
	}

	protected final void initColumnNames()  throws java.sql.SQLException {

		__columnCount          = 12;

		initTables();

		__tableName            = "system_process_human_tasks";

		__key_columns          = new int[1];
		__key_columns[0] = 0;

		__column_names[0] = BaseSystemProcessHumanTask.CS_PROCESS_HUMAN_TASK_ID;
		__column_names[1] = BaseSystemProcessHumanTask.CS_BUSINESS_ID;
		__column_names[2] = BaseSystemProcessHumanTask.CS_BUSINESS_NAME;
		__column_names[3] = BaseSystemProcessHumanTask.CS_PROCESS_ACTIVITY_ID;
		__column_names[4] = BaseSystemProcessHumanTask.CS_PROCESS_INSTANCE_ACTIVITY_ID;
		__column_names[5] = BaseSystemProcessHumanTask.CS_PROCESS_ID;
		__column_names[6] = BaseSystemProcessHumanTask.CS_PROCESS_INSTANCE_ID;
		__column_names[7] = BaseSystemProcessHumanTask.CS_BACK_VIEW_NAME;
		__column_names[8] = BaseSystemProcessHumanTask.CS_EMPLOYEE_ID;
		__column_names[9] = BaseSystemProcessHumanTask.CS_OPERATE_TIME;
		__column_names[10] = BaseSystemProcessHumanTask.CS_COMMIT_IDEAS;
		__column_names[11] = BaseSystemProcessHumanTask.CS_STATUS;

		resetSelectFlags() ;
		resetInsertFlags() ;
	}

	public void resetInsertFlags(){
		for(int i = 0; i < __columnCount; i ++ ) __insert_flags[i] = true;
		__insert_flags[0] = false;
		__serial_column = 0;
	}

	public void setPrimaryKeyFromBase(BaseSystemProcessHumanTask b) {
		clear();
		setProcessHumanTaskIdClear(b.getProcessHumanTaskId());
	}

	public boolean isPrimaryKeyNull() {
		return getProcessHumanTaskId() == null;
	}

	@Override
	public BaseSystemProcessHumanTask generateBase(){
		BaseSystemProcessHumanTask b = new BaseSystemProcessHumanTask();
		setDataToBase(b);
		return b;
	}

	@Override
	public void setDataToBase(BaseSystemProcessHumanTask b){
		int count = 0; Object val;
		if((val = __current_data[count++]) != null) b.setProcessHumanTaskId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setBusinessId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setBusinessName(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setProcessActivityId(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setProcessInstanceActivityId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setProcessId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setProcessInstanceId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setBackViewName(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setEmployeeId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setOperateTime(GenericBase.__getDateFromSQL(val));
		if((val = __current_data[count++]) != null) b.setCommitIdeas(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setStatus(GenericBase.__getInt(val));
	}

	@Override
	public void setBaseToBuffer(BaseSystemProcessHumanTask b, Object[] buff){
		int count = 0;
		buff[count++] = b.getProcessHumanTaskId();
		buff[count++] = b.getBusinessId();
		buff[count++] = b.getBusinessName();
		buff[count++] = b.getProcessActivityId();
		buff[count++] = b.getProcessInstanceActivityId();
		buff[count++] = b.getProcessId();
		buff[count++] = b.getProcessInstanceId();
		buff[count++] = b.getBackViewName();
		buff[count++] = b.getEmployeeId();
		buff[count++] = generateTimestampFromDate(b.getOperateTime());
		buff[count++] = b.getCommitIdeas();
		buff[count++] = b.getStatus();
	}

	@Override
	public void setDataFromBase(BaseSystemProcessHumanTask b){
		if(b.getProcessHumanTaskId() != null) setProcessHumanTaskIdClear(b.getProcessHumanTaskId());
		if(b.getBusinessId() != null) setBusinessId(b.getBusinessId());
		if(b.getBusinessName() != null) setBusinessName(b.getBusinessName());
		if(b.getProcessActivityId() != null) setProcessActivityId(b.getProcessActivityId());
		if(b.getProcessInstanceActivityId() != null) setProcessInstanceActivityId(b.getProcessInstanceActivityId());
		if(b.getProcessId() != null) setProcessId(b.getProcessId());
		if(b.getProcessInstanceId() != null) setProcessInstanceId(b.getProcessInstanceId());
		if(b.getBackViewName() != null) setBackViewName(b.getBackViewName());
		if(b.getEmployeeId() != null) setEmployeeId(b.getEmployeeId());
		if(b.getOperateTime() != null) setOperateTime(b.getOperateTime());
		if(b.getCommitIdeas() != null) setCommitIdeas(b.getCommitIdeas());
		if(b.getStatus() != null) setStatus(b.getStatus());
	}

	@Override
	public BaseSystemProcessHumanTask generateBaseFromResultSet(java.sql.ResultSet sdr)  throws java.sql.SQLException {

		BaseSystemProcessHumanTask b = new BaseSystemProcessHumanTask();
		setQueryResultToBase(b, sdr);
		return b;
	}

	@Override
	public void setQueryResultToBase(BaseSystemProcessHumanTask __base, java.sql.ResultSet sdr ) throws java.sql.SQLException {
		int count = 1, index = 0;
		java.sql.ResultSetMetaData rsmd = sdr.getMetaData();
		int columnCount = rsmd.getColumnCount();
		Object val;
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setProcessHumanTaskId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setBusinessId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setBusinessName(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setProcessActivityId(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setProcessInstanceActivityId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setProcessId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setProcessInstanceId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setBackViewName(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setEmployeeId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setOperateTime(GenericBase.__getDateFromSQL(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setCommitIdeas(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setStatus(GenericBase.__getInt(val));
	}

	public void setProcessHumanTaskId(java.lang.Integer val) {
		setCurrentData(0, val);
	}

	public java.lang.Integer getProcessHumanTaskId() {
		return GenericBase.__getInt(__current_data[0]);
	}

	public void setProcessHumanTaskIdClear(java.lang.Integer val) {
		__backup_data[0] = __current_data[0] =  val;
		__modified_flags[0] = false;
	}

	public void setBusinessId(java.lang.Integer val) {
		setCurrentData(1, val);
	}

	public java.lang.Integer getBusinessId() {
		return GenericBase.__getInt(__current_data[1]);
	}

	public void setBusinessName(java.lang.String val) {
		setCurrentData(2, val);
	}

	public java.lang.String getBusinessName() {
		return GenericBase.__getString(__current_data[2]);
	}

	public void setProcessActivityId(java.lang.String val) {
		setCurrentData(3, val);
	}

	public java.lang.String getProcessActivityId() {
		return GenericBase.__getString(__current_data[3]);
	}

	public void setProcessInstanceActivityId(java.lang.Integer val) {
		setCurrentData(4, val);
	}

	public java.lang.Integer getProcessInstanceActivityId() {
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

	public void setEmployeeId(java.lang.Integer val) {
		setCurrentData(8, val);
	}

	public java.lang.Integer getEmployeeId() {
		return GenericBase.__getInt(__current_data[8]);
	}

	public void setOperateTime(java.util.Date val) {
		setCurrentData(9, generateTimestampFromDate(val));
	}

	public java.util.Date getOperateTime() {
		return GenericBase.__getDateFromSQL(__current_data[9]);
	}

	public void setCommitIdeas(java.lang.String val) {
		setCurrentData(10, val);
	}

	public java.lang.String getCommitIdeas() {
		return GenericBase.__getString(__current_data[10]);
	}

	public void setStatus(java.lang.Integer val) {
		setCurrentData(11, val);
	}

	public java.lang.Integer getStatus() {
		return GenericBase.__getInt(__current_data[11]);
	}

	public void setConditionProcessHumanTaskId(String op, java.lang.Integer val) {
		setConditionProcessHumanTaskId(op, val, CONDITION_AND);
	}

	public void setConditionProcessHumanTaskId(String op, java.lang.Integer val, String relation) {
		addCondition(0, op, relation, val);
	}

	public void setSelectProcessHumanTaskId(boolean val) {
		__select_flags[0] = val;
	}

	public void setConditionBusinessId(String op, java.lang.Integer val) {
		setConditionBusinessId(op, val, CONDITION_AND);
	}

	public void setConditionBusinessId(String op, java.lang.Integer val, String relation) {
		addCondition(1, op, relation, val);
	}

	public void setSelectBusinessId(boolean val) {
		__select_flags[1] = val;
	}

	public void setConditionBusinessName(String op, java.lang.String val) {
		setConditionBusinessName(op, val, CONDITION_AND);
	}

	public void setConditionBusinessName(String op, java.lang.String val, String relation) {
		addCondition(2, op, relation, val);
	}

	public void setSelectBusinessName(boolean val) {
		__select_flags[2] = val;
	}

	public void setConditionProcessActivityId(String op, java.lang.String val) {
		setConditionProcessActivityId(op, val, CONDITION_AND);
	}

	public void setConditionProcessActivityId(String op, java.lang.String val, String relation) {
		addCondition(3, op, relation, val);
	}

	public void setSelectProcessActivityId(boolean val) {
		__select_flags[3] = val;
	}

	public void setConditionProcessInstanceActivityId(String op, java.lang.Integer val) {
		setConditionProcessInstanceActivityId(op, val, CONDITION_AND);
	}

	public void setConditionProcessInstanceActivityId(String op, java.lang.Integer val, String relation) {
		addCondition(4, op, relation, val);
	}

	public void setSelectProcessInstanceActivityId(boolean val) {
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

	public void setConditionEmployeeId(String op, java.lang.Integer val) {
		setConditionEmployeeId(op, val, CONDITION_AND);
	}

	public void setConditionEmployeeId(String op, java.lang.Integer val, String relation) {
		addCondition(8, op, relation, val);
	}

	public void setSelectEmployeeId(boolean val) {
		__select_flags[8] = val;
	}

	public void setConditionOperateTime(String op, java.util.Date val) {
		setConditionOperateTime(op, val, CONDITION_AND);
	}

	public void setConditionOperateTime(String op, java.util.Date val, String relation) {
		addCondition(9, op, relation, generateTimestampFromDate(val));
	}

	public void setSelectOperateTime(boolean val) {
		__select_flags[9] = val;
	}

	public void setConditionCommitIdeas(String op, java.lang.String val) {
		setConditionCommitIdeas(op, val, CONDITION_AND);
	}

	public void setConditionCommitIdeas(String op, java.lang.String val, String relation) {
		addCondition(10, op, relation, val);
	}

	public void setSelectCommitIdeas(boolean val) {
		__select_flags[10] = val;
	}

	public void setConditionStatus(String op, java.lang.Integer val) {
		setConditionStatus(op, val, CONDITION_AND);
	}

	public void setConditionStatus(String op, java.lang.Integer val, String relation) {
		addCondition(11, op, relation, val);
	}

	public void setSelectStatus(boolean val) {
		__select_flags[11] = val;
	}


}

