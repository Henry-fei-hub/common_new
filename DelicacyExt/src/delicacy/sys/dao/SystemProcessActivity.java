package delicacy.sys.dao;

import delicacy.common.AbstractTable;
import delicacy.common.GenericBase;
import delicacy.sys.bean.BaseSystemProcessActivity;


public class SystemProcessActivity extends AbstractTable<BaseSystemProcessActivity>
{

	public SystemProcessActivity() throws java.sql.SQLException 
	{

		initColumnNames();
	}

	protected final void initColumnNames()  throws java.sql.SQLException {

		__columnCount          = 17;

		initTables();

		__tableName            = "system_process_activities";

		__key_columns          = new int[1];
		__key_columns[0] = 0;

		__column_names[0] = BaseSystemProcessActivity.CS_PROCESS_ACTIVITY_ID;
		__column_names[1] = BaseSystemProcessActivity.CS_PROCESS_ID;
		__column_names[2] = BaseSystemProcessActivity.CS_ACTIVITY_TYPE;
		__column_names[3] = BaseSystemProcessActivity.CS_ACTIVITY_NAME;
		__column_names[4] = BaseSystemProcessActivity.CS_ACTIVITY_SERIAL_NO;
		__column_names[5] = BaseSystemProcessActivity.CS_POSX;
		__column_names[6] = BaseSystemProcessActivity.CS_POSY;
		__column_names[7] = BaseSystemProcessActivity.CS_TIME_OUT_ACTION;
		__column_names[8] = BaseSystemProcessActivity.CS_EXECUTOR_TYPE;
		__column_names[9] = BaseSystemProcessActivity.CS_EMPLOYEE_ID;
		__column_names[10] = BaseSystemProcessActivity.CS_DEPARTMENT_ID;
		__column_names[11] = BaseSystemProcessActivity.CS_ROLE_ID;
		__column_names[12] = BaseSystemProcessActivity.CS_IF_ALLOW_OVER;
		__column_names[13] = BaseSystemProcessActivity.CS_IS_ENABLE;
		__column_names[14] = BaseSystemProcessActivity.CS_POOL_TYPE;
		__column_names[15] = BaseSystemProcessActivity.CS_EXECUTE_NAME;
		__column_names[16] = BaseSystemProcessActivity.CS_ORGANIZATION_ID;

		resetSelectFlags() ;
		resetInsertFlags() ;
	}

	public void resetInsertFlags(){
		for(int i = 0; i < __columnCount; i ++ ) __insert_flags[i] = true;
		__insert_flags[0] = false;
		__serial_column = 0;
	}

	public void setPrimaryKeyFromBase(BaseSystemProcessActivity b) {
		clear();
		setProcessActivityIdClear(b.getProcessActivityId());
	}

	public boolean isPrimaryKeyNull() {
		return getProcessActivityId() == null;
	}

	@Override
	public BaseSystemProcessActivity generateBase(){
		BaseSystemProcessActivity b = new BaseSystemProcessActivity();
		setDataToBase(b);
		return b;
	}

	@Override
	public void setDataToBase(BaseSystemProcessActivity b){
		int count = 0; Object val;
		if((val = __current_data[count++]) != null) b.setProcessActivityId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setProcessId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setActivityType(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setActivityName(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setActivitySerialNo(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setPosx(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setPosy(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setTimeOutAction(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setExecutorType(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setEmployeeId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setDepartmentId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setRoleId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setIfAllowOver(GenericBase.__getBoolean(val));
		if((val = __current_data[count++]) != null) b.setIsEnable(GenericBase.__getBoolean(val));
		if((val = __current_data[count++]) != null) b.setPoolType(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setExecuteName(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setOrganizationId(GenericBase.__getInt(val));
	}

	@Override
	public void setBaseToBuffer(BaseSystemProcessActivity b, Object[] buff){
		int count = 0;
		buff[count++] = b.getProcessActivityId();
		buff[count++] = b.getProcessId();
		buff[count++] = b.getActivityType();
		buff[count++] = b.getActivityName();
		buff[count++] = b.getActivitySerialNo();
		buff[count++] = b.getPosx();
		buff[count++] = b.getPosy();
		buff[count++] = b.getTimeOutAction();
		buff[count++] = b.getExecutorType();
		buff[count++] = b.getEmployeeId();
		buff[count++] = b.getDepartmentId();
		buff[count++] = b.getRoleId();
		buff[count++] = b.getIfAllowOver();
		buff[count++] = b.getIsEnable();
		buff[count++] = b.getPoolType();
		buff[count++] = b.getExecuteName();
		buff[count++] = b.getOrganizationId();
	}

	@Override
	public void setDataFromBase(BaseSystemProcessActivity b){
		if(b.getProcessActivityId() != null) setProcessActivityIdClear(b.getProcessActivityId());
		if(b.getProcessId() != null) setProcessId(b.getProcessId());
		if(b.getActivityType() != null) setActivityType(b.getActivityType());
		if(b.getActivityName() != null) setActivityName(b.getActivityName());
		if(b.getActivitySerialNo() != null) setActivitySerialNo(b.getActivitySerialNo());
		if(b.getPosx() != null) setPosx(b.getPosx());
		if(b.getPosy() != null) setPosy(b.getPosy());
		if(b.getTimeOutAction() != null) setTimeOutAction(b.getTimeOutAction());
		if(b.getExecutorType() != null) setExecutorType(b.getExecutorType());
		if(b.getEmployeeId() != null) setEmployeeId(b.getEmployeeId());
		if(b.getDepartmentId() != null) setDepartmentId(b.getDepartmentId());
		if(b.getRoleId() != null) setRoleId(b.getRoleId());
		if(b.getIfAllowOver() != null) setIfAllowOver(b.getIfAllowOver());
		if(b.getIsEnable() != null) setIsEnable(b.getIsEnable());
		if(b.getPoolType() != null) setPoolType(b.getPoolType());
		if(b.getExecuteName() != null) setExecuteName(b.getExecuteName());
		if(b.getOrganizationId() != null) setOrganizationId(b.getOrganizationId());
	}

	@Override
	public BaseSystemProcessActivity generateBaseFromResultSet(java.sql.ResultSet sdr)  throws java.sql.SQLException {

		BaseSystemProcessActivity b = new BaseSystemProcessActivity();
		setQueryResultToBase(b, sdr);
		return b;
	}

	@Override
	public void setQueryResultToBase(BaseSystemProcessActivity __base, java.sql.ResultSet sdr ) throws java.sql.SQLException {
		int count = 1, index = 0;
		java.sql.ResultSetMetaData rsmd = sdr.getMetaData();
		int columnCount = rsmd.getColumnCount();
		Object val;
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setProcessActivityId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setProcessId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setActivityType(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setActivityName(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setActivitySerialNo(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setPosx(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setPosy(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setTimeOutAction(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setExecutorType(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setEmployeeId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setDepartmentId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setRoleId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setIfAllowOver(GenericBase.__getBoolean(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setIsEnable(GenericBase.__getBoolean(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setPoolType(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setExecuteName(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setOrganizationId(GenericBase.__getInt(val));
	}

	public void setProcessActivityId(java.lang.Integer val) {
		setCurrentData(0, val);
	}

	public java.lang.Integer getProcessActivityId() {
		return GenericBase.__getInt(__current_data[0]);
	}

	public void setProcessActivityIdClear(java.lang.Integer val) {
		__backup_data[0] = __current_data[0] =  val;
		__modified_flags[0] = false;
	}

	public void setProcessId(java.lang.Integer val) {
		setCurrentData(1, val);
	}

	public java.lang.Integer getProcessId() {
		return GenericBase.__getInt(__current_data[1]);
	}

	public void setActivityType(java.lang.Integer val) {
		setCurrentData(2, val);
	}

	public java.lang.Integer getActivityType() {
		return GenericBase.__getInt(__current_data[2]);
	}

	public void setActivityName(java.lang.String val) {
		setCurrentData(3, val);
	}

	public java.lang.String getActivityName() {
		return GenericBase.__getString(__current_data[3]);
	}

	public void setActivitySerialNo(java.lang.Integer val) {
		setCurrentData(4, val);
	}

	public java.lang.Integer getActivitySerialNo() {
		return GenericBase.__getInt(__current_data[4]);
	}

	public void setPosx(java.lang.Integer val) {
		setCurrentData(5, val);
	}

	public java.lang.Integer getPosx() {
		return GenericBase.__getInt(__current_data[5]);
	}

	public void setPosy(java.lang.Integer val) {
		setCurrentData(6, val);
	}

	public java.lang.Integer getPosy() {
		return GenericBase.__getInt(__current_data[6]);
	}

	public void setTimeOutAction(java.lang.Integer val) {
		setCurrentData(7, val);
	}

	public java.lang.Integer getTimeOutAction() {
		return GenericBase.__getInt(__current_data[7]);
	}

	public void setExecutorType(java.lang.Integer val) {
		setCurrentData(8, val);
	}

	public java.lang.Integer getExecutorType() {
		return GenericBase.__getInt(__current_data[8]);
	}

	public void setEmployeeId(java.lang.Integer val) {
		setCurrentData(9, val);
	}

	public java.lang.Integer getEmployeeId() {
		return GenericBase.__getInt(__current_data[9]);
	}

	public void setDepartmentId(java.lang.Integer val) {
		setCurrentData(10, val);
	}

	public java.lang.Integer getDepartmentId() {
		return GenericBase.__getInt(__current_data[10]);
	}

	public void setRoleId(java.lang.Integer val) {
		setCurrentData(11, val);
	}

	public java.lang.Integer getRoleId() {
		return GenericBase.__getInt(__current_data[11]);
	}

	public void setIfAllowOver(java.lang.Boolean val) {
		setCurrentData(12, val);
	}

	public java.lang.Boolean getIfAllowOver() {
		return GenericBase.__getBoolean(__current_data[12]);
	}

	public void setIsEnable(java.lang.Boolean val) {
		setCurrentData(13, val);
	}

	public java.lang.Boolean getIsEnable() {
		return GenericBase.__getBoolean(__current_data[13]);
	}

	public void setPoolType(java.lang.Integer val) {
		setCurrentData(14, val);
	}

	public java.lang.Integer getPoolType() {
		return GenericBase.__getInt(__current_data[14]);
	}

	public void setExecuteName(java.lang.String val) {
		setCurrentData(15, val);
	}

	public java.lang.String getExecuteName() {
		return GenericBase.__getString(__current_data[15]);
	}

	public void setOrganizationId(java.lang.Integer val) {
		setCurrentData(16, val);
	}

	public java.lang.Integer getOrganizationId() {
		return GenericBase.__getInt(__current_data[16]);
	}

	public void setConditionProcessActivityId(String op, java.lang.Integer val) {
		setConditionProcessActivityId(op, val, CONDITION_AND);
	}

	public void setConditionProcessActivityId(String op, java.lang.Integer val, String relation) {
		addCondition(0, op, relation, val);
	}

	public void setSelectProcessActivityId(boolean val) {
		__select_flags[0] = val;
	}

	public void setConditionProcessId(String op, java.lang.Integer val) {
		setConditionProcessId(op, val, CONDITION_AND);
	}

	public void setConditionProcessId(String op, java.lang.Integer val, String relation) {
		addCondition(1, op, relation, val);
	}

	public void setSelectProcessId(boolean val) {
		__select_flags[1] = val;
	}

	public void setConditionActivityType(String op, java.lang.Integer val) {
		setConditionActivityType(op, val, CONDITION_AND);
	}

	public void setConditionActivityType(String op, java.lang.Integer val, String relation) {
		addCondition(2, op, relation, val);
	}

	public void setSelectActivityType(boolean val) {
		__select_flags[2] = val;
	}

	public void setConditionActivityName(String op, java.lang.String val) {
		setConditionActivityName(op, val, CONDITION_AND);
	}

	public void setConditionActivityName(String op, java.lang.String val, String relation) {
		addCondition(3, op, relation, val);
	}

	public void setSelectActivityName(boolean val) {
		__select_flags[3] = val;
	}

	public void setConditionActivitySerialNo(String op, java.lang.Integer val) {
		setConditionActivitySerialNo(op, val, CONDITION_AND);
	}

	public void setConditionActivitySerialNo(String op, java.lang.Integer val, String relation) {
		addCondition(4, op, relation, val);
	}

	public void setSelectActivitySerialNo(boolean val) {
		__select_flags[4] = val;
	}

	public void setConditionPosx(String op, java.lang.Integer val) {
		setConditionPosx(op, val, CONDITION_AND);
	}

	public void setConditionPosx(String op, java.lang.Integer val, String relation) {
		addCondition(5, op, relation, val);
	}

	public void setSelectPosx(boolean val) {
		__select_flags[5] = val;
	}

	public void setConditionPosy(String op, java.lang.Integer val) {
		setConditionPosy(op, val, CONDITION_AND);
	}

	public void setConditionPosy(String op, java.lang.Integer val, String relation) {
		addCondition(6, op, relation, val);
	}

	public void setSelectPosy(boolean val) {
		__select_flags[6] = val;
	}

	public void setConditionTimeOutAction(String op, java.lang.Integer val) {
		setConditionTimeOutAction(op, val, CONDITION_AND);
	}

	public void setConditionTimeOutAction(String op, java.lang.Integer val, String relation) {
		addCondition(7, op, relation, val);
	}

	public void setSelectTimeOutAction(boolean val) {
		__select_flags[7] = val;
	}

	public void setConditionExecutorType(String op, java.lang.Integer val) {
		setConditionExecutorType(op, val, CONDITION_AND);
	}

	public void setConditionExecutorType(String op, java.lang.Integer val, String relation) {
		addCondition(8, op, relation, val);
	}

	public void setSelectExecutorType(boolean val) {
		__select_flags[8] = val;
	}

	public void setConditionEmployeeId(String op, java.lang.Integer val) {
		setConditionEmployeeId(op, val, CONDITION_AND);
	}

	public void setConditionEmployeeId(String op, java.lang.Integer val, String relation) {
		addCondition(9, op, relation, val);
	}

	public void setSelectEmployeeId(boolean val) {
		__select_flags[9] = val;
	}

	public void setConditionDepartmentId(String op, java.lang.Integer val) {
		setConditionDepartmentId(op, val, CONDITION_AND);
	}

	public void setConditionDepartmentId(String op, java.lang.Integer val, String relation) {
		addCondition(10, op, relation, val);
	}

	public void setSelectDepartmentId(boolean val) {
		__select_flags[10] = val;
	}

	public void setConditionRoleId(String op, java.lang.Integer val) {
		setConditionRoleId(op, val, CONDITION_AND);
	}

	public void setConditionRoleId(String op, java.lang.Integer val, String relation) {
		addCondition(11, op, relation, val);
	}

	public void setSelectRoleId(boolean val) {
		__select_flags[11] = val;
	}

	public void setConditionIfAllowOver(String op, java.lang.Boolean val) {
		setConditionIfAllowOver(op, val, CONDITION_AND);
	}

	public void setConditionIfAllowOver(String op, java.lang.Boolean val, String relation) {
		addCondition(12, op, relation, val);
	}

	public void setSelectIfAllowOver(boolean val) {
		__select_flags[12] = val;
	}

	public void setConditionIsEnable(String op, java.lang.Boolean val) {
		setConditionIsEnable(op, val, CONDITION_AND);
	}

	public void setConditionIsEnable(String op, java.lang.Boolean val, String relation) {
		addCondition(13, op, relation, val);
	}

	public void setSelectIsEnable(boolean val) {
		__select_flags[13] = val;
	}

	public void setConditionPoolType(String op, java.lang.Integer val) {
		setConditionPoolType(op, val, CONDITION_AND);
	}

	public void setConditionPoolType(String op, java.lang.Integer val, String relation) {
		addCondition(14, op, relation, val);
	}

	public void setSelectPoolType(boolean val) {
		__select_flags[14] = val;
	}

	public void setConditionExecuteName(String op, java.lang.String val) {
		setConditionExecuteName(op, val, CONDITION_AND);
	}

	public void setConditionExecuteName(String op, java.lang.String val, String relation) {
		addCondition(15, op, relation, val);
	}

	public void setSelectExecuteName(boolean val) {
		__select_flags[15] = val;
	}

	public void setConditionOrganizationId(String op, java.lang.Integer val) {
		setConditionOrganizationId(op, val, CONDITION_AND);
	}

	public void setConditionOrganizationId(String op, java.lang.Integer val, String relation) {
		addCondition(16, op, relation, val);
	}

	public void setSelectOrganizationId(boolean val) {
		__select_flags[16] = val;
	}


}

