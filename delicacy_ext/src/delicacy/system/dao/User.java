package delicacy.system.dao;

import delicacy.common.AbstractTable;
import delicacy.common.GenericBase;
import delicacy.system.bean.BaseUser;


public class User extends AbstractTable<BaseUser>
{

	public User() throws java.sql.SQLException 
	{

		initColumnNames();
	}

	protected final void initColumnNames()  throws java.sql.SQLException {

		__columnCount          = 17;

		initTables();

		__tableName            = "users";

		__key_columns          = new int[1];
		__key_columns[0] = 0;

		__column_names[0] = BaseUser.CS_USER_ID;
		__column_names[1] = BaseUser.CS_USER_NO;
		__column_names[2] = BaseUser.CS_USER_NAME;
		__column_names[3] = BaseUser.CS_MOBILE_PHONE;
		__column_names[4] = BaseUser.CS_PHONE_NUMBER;
		__column_names[5] = BaseUser.CS_EMAIL;
		__column_names[6] = BaseUser.CS_COMPANY_ID;
		__column_names[7] = BaseUser.CS_COMPANY_NO;
		__column_names[8] = BaseUser.CS_DEPARTMENT;
		__column_names[9] = BaseUser.CS_JOB;
		__column_names[10] = BaseUser.CS_EMPLOYEE_ID;
		__column_names[11] = BaseUser.CS_ECMC_WORKSPACE;
		__column_names[12] = BaseUser.CS_USER_TYPE;
		__column_names[13] = BaseUser.CS_USER_STATUS;
		__column_names[14] = BaseUser.CS_CREATE_TIME;
		__column_names[15] = BaseUser.CS_DELETE_TIME;
		__column_names[16] = BaseUser.CS_DELETE_FLAG;

		resetSelectFlags() ;
		resetInsertFlags() ;
	}

	public void resetInsertFlags(){
		for(int i = 0; i < __columnCount; i ++ ) __insert_flags[i] = true;
		__insert_flags[0] = false;
		__serial_column = 0;
	}

	public void setPrimaryKeyFromBase(BaseUser b) {
		clear();
		setUserIdClear(b.getUserId());
	}

	public boolean isPrimaryKeyNull() {
		return getUserId() == null;
	}

	@Override
	public BaseUser generateBase(){
		BaseUser b = new BaseUser();
		setDataToBase(b);
		return b;
	}

	@Override
	public void setDataToBase(BaseUser b){
		int count = 0; Object val;
		if((val = __current_data[count++]) != null) b.setUserId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setUserNo(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setUserName(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setMobilePhone(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setPhoneNumber(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setEmail(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setCompanyId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setCompanyNo(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setDepartment(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setJob(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setEmployeeId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setEcmcWorkspace(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setUserType(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setUserStatus(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setCreateTime(GenericBase.__getDateFromSQL(val));
		if((val = __current_data[count++]) != null) b.setDeleteTime(GenericBase.__getDateFromSQL(val));
		if((val = __current_data[count++]) != null) b.setDeleteFlag(GenericBase.__getInt(val));
	}

	@Override
	public void setBaseToBuffer(BaseUser b, Object[] buff){
		int count = 0;
		buff[count++] = b.getUserId();
		buff[count++] = b.getUserNo();
		buff[count++] = b.getUserName();
		buff[count++] = b.getMobilePhone();
		buff[count++] = b.getPhoneNumber();
		buff[count++] = b.getEmail();
		buff[count++] = b.getCompanyId();
		buff[count++] = b.getCompanyNo();
		buff[count++] = b.getDepartment();
		buff[count++] = b.getJob();
		buff[count++] = b.getEmployeeId();
		buff[count++] = b.getEcmcWorkspace();
		buff[count++] = b.getUserType();
		buff[count++] = b.getUserStatus();
		buff[count++] = generateTimestampFromDate(b.getCreateTime());
		buff[count++] = generateTimestampFromDate(b.getDeleteTime());
		buff[count++] = b.getDeleteFlag();
	}

	@Override
	public void setDataFromBase(BaseUser b){
		if(b.getUserId() != null) setUserIdClear(b.getUserId());
		if(b.getUserNo() != null) setUserNo(b.getUserNo());
		if(b.getUserName() != null) setUserName(b.getUserName());
		if(b.getMobilePhone() != null) setMobilePhone(b.getMobilePhone());
		if(b.getPhoneNumber() != null) setPhoneNumber(b.getPhoneNumber());
		if(b.getEmail() != null) setEmail(b.getEmail());
		if(b.getCompanyId() != null) setCompanyId(b.getCompanyId());
		if(b.getCompanyNo() != null) setCompanyNo(b.getCompanyNo());
		if(b.getDepartment() != null) setDepartment(b.getDepartment());
		if(b.getJob() != null) setJob(b.getJob());
		if(b.getEmployeeId() != null) setEmployeeId(b.getEmployeeId());
		if(b.getEcmcWorkspace() != null) setEcmcWorkspace(b.getEcmcWorkspace());
		if(b.getUserType() != null) setUserType(b.getUserType());
		if(b.getUserStatus() != null) setUserStatus(b.getUserStatus());
		if(b.getCreateTime() != null) setCreateTime(b.getCreateTime());
		if(b.getDeleteTime() != null) setDeleteTime(b.getDeleteTime());
		if(b.getDeleteFlag() != null) setDeleteFlag(b.getDeleteFlag());
	}

	@Override
	public BaseUser generateBaseFromResultSet(java.sql.ResultSet sdr)  throws java.sql.SQLException {

		BaseUser b = new BaseUser();
		setQueryResultToBase(b, sdr);
		return b;
	}

	@Override
	public void setQueryResultToBase(BaseUser __base, java.sql.ResultSet sdr ) throws java.sql.SQLException {
		int count = 1, index = 0;
		java.sql.ResultSetMetaData rsmd = sdr.getMetaData();
		int columnCount = rsmd.getColumnCount();
		Object val;
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setUserId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setUserNo(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setUserName(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setMobilePhone(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setPhoneNumber(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setEmail(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setCompanyId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setCompanyNo(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setDepartment(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setJob(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setEmployeeId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setEcmcWorkspace(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setUserType(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setUserStatus(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setCreateTime(GenericBase.__getDateFromSQL(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setDeleteTime(GenericBase.__getDateFromSQL(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setDeleteFlag(GenericBase.__getInt(val));
	}

	public void setUserId(java.lang.Integer val) {
		setCurrentData(0, val);
	}

	public java.lang.Integer getUserId() {
		return GenericBase.__getInt(__current_data[0]);
	}

	public void setUserIdClear(java.lang.Integer val) {
		__backup_data[0] = __current_data[0] =  val;
		__modified_flags[0] = false;
	}

	public void setUserNo(java.lang.String val) {
		setCurrentData(1, val);
	}

	public java.lang.String getUserNo() {
		return GenericBase.__getString(__current_data[1]);
	}

	public void setUserName(java.lang.String val) {
		setCurrentData(2, val);
	}

	public java.lang.String getUserName() {
		return GenericBase.__getString(__current_data[2]);
	}

	public void setMobilePhone(java.lang.String val) {
		setCurrentData(3, val);
	}

	public java.lang.String getMobilePhone() {
		return GenericBase.__getString(__current_data[3]);
	}

	public void setPhoneNumber(java.lang.String val) {
		setCurrentData(4, val);
	}

	public java.lang.String getPhoneNumber() {
		return GenericBase.__getString(__current_data[4]);
	}

	public void setEmail(java.lang.String val) {
		setCurrentData(5, val);
	}

	public java.lang.String getEmail() {
		return GenericBase.__getString(__current_data[5]);
	}

	public void setCompanyId(java.lang.Integer val) {
		setCurrentData(6, val);
	}

	public java.lang.Integer getCompanyId() {
		return GenericBase.__getInt(__current_data[6]);
	}
	
	public void setCompanyNo(java.lang.String val) {
		setCurrentData(7, val);
	}
	
	public java.lang.String getCompanyNo() {
		return GenericBase.__getString(__current_data[7]);
	}

	public void setDepartment(java.lang.String val) {
		setCurrentData(8, val);
	}

	public java.lang.String getDepartment() {
		return GenericBase.__getString(__current_data[8]);
	}

	public void setJob(java.lang.String val) {
		setCurrentData(9, val);
	}

	public java.lang.String getJob() {
		return GenericBase.__getString(__current_data[9]);
	}

	public void setEmployeeId(java.lang.Integer val) {
		setCurrentData(10, val);
	}

	public java.lang.Integer getEmployeeId() {
		return GenericBase.__getInt(__current_data[10]);
	}

	public void setEcmcWorkspace(java.lang.String val) {
		setCurrentData(11, val);
	}

	public java.lang.String getEcmcWorkspace() {
		return GenericBase.__getString(__current_data[11]);
	}

	public void setUserType(java.lang.Integer val) {
		setCurrentData(12, val);
	}

	public java.lang.Integer getUserType() {
		return GenericBase.__getInt(__current_data[12]);
	}

	public void setUserStatus(java.lang.Integer val) {
		setCurrentData(13, val);
	}

	public java.lang.Integer getUserStatus() {
		return GenericBase.__getInt(__current_data[13]);
	}

	public void setCreateTime(java.util.Date val) {
		setCurrentData(14, generateTimestampFromDate(val));
	}

	public java.util.Date getCreateTime() {
		return GenericBase.__getDateFromSQL(__current_data[14]);
	}

	public void setDeleteTime(java.util.Date val) {
		setCurrentData(15, generateTimestampFromDate(val));
	}

	public java.util.Date getDeleteTime() {
		return GenericBase.__getDateFromSQL(__current_data[15]);
	}

	public void setDeleteFlag(java.lang.Integer val) {
		setCurrentData(16, val);
	}

	public java.lang.Integer getDeleteFlag() {
		return GenericBase.__getInt(__current_data[16]);
	}

	public void setConditionUserId(String op, java.lang.Integer val) {
		setConditionUserId(op, val, CONDITION_AND);
	}

	public void setConditionUserId(String op, java.lang.Integer val, String relation) {
		addCondition(0, op, relation, val);
	}

	public void setSelectUserId(boolean val) {
		__select_flags[0] = val;
	}

	public void setConditionUserNo(String op, java.lang.String val) {
		setConditionUserNo(op, val, CONDITION_AND);
	}

	public void setConditionUserNo(String op, java.lang.String val, String relation) {
		addCondition(1, op, relation, val);
	}

	public void setSelectUserNo(boolean val) {
		__select_flags[1] = val;
	}

	public void setConditionUserName(String op, java.lang.String val) {
		setConditionUserName(op, val, CONDITION_AND);
	}

	public void setConditionUserName(String op, java.lang.String val, String relation) {
		addCondition(2, op, relation, val);
	}

	public void setSelectUserName(boolean val) {
		__select_flags[2] = val;
	}

	public void setConditionMobilePhone(String op, java.lang.String val) {
		setConditionMobilePhone(op, val, CONDITION_AND);
	}

	public void setConditionMobilePhone(String op, java.lang.String val, String relation) {
		addCondition(3, op, relation, val);
	}

	public void setSelectMobilePhone(boolean val) {
		__select_flags[3] = val;
	}

	public void setConditionPhoneNumber(String op, java.lang.String val) {
		setConditionPhoneNumber(op, val, CONDITION_AND);
	}

	public void setConditionPhoneNumber(String op, java.lang.String val, String relation) {
		addCondition(4, op, relation, val);
	}

	public void setSelectPhoneNumber(boolean val) {
		__select_flags[4] = val;
	}

	public void setConditionEmail(String op, java.lang.String val) {
		setConditionEmail(op, val, CONDITION_AND);
	}

	public void setConditionEmail(String op, java.lang.String val, String relation) {
		addCondition(5, op, relation, val);
	}

	public void setSelectEmail(boolean val) {
		__select_flags[5] = val;
	}

	public void setConditionCompanyId(String op, java.lang.Integer val) {
		setConditionCompanyId(op, val, CONDITION_AND);
	}

	public void setConditionCompanyId(String op, java.lang.Integer val, String relation) {
		addCondition(6, op, relation, val);
	}
	
	public void setSelectCompanyId(boolean val) {
		__select_flags[6] = val;
	}
	
	public void setConditionCompanyNo(String op, java.lang.String val) {
		setConditionCompanyNo(op, val, CONDITION_AND);
	}
	
	public void setConditionCompanyNo(String op, java.lang.String val, String relation) {
		addCondition(7, op, relation, val);
	}
	
	public void setSelectCompanyNo(boolean val) {
		__select_flags[7] = val;
	}

	public void setConditionDepartment(String op, java.lang.String val) {
		setConditionDepartment(op, val, CONDITION_AND);
	}

	public void setConditionDepartment(String op, java.lang.String val, String relation) {
		addCondition(8, op, relation, val);
	}

	public void setSelectDepartment(boolean val) {
		__select_flags[8] = val;
	}

	public void setConditionJob(String op, java.lang.String val) {
		setConditionJob(op, val, CONDITION_AND);
	}

	public void setConditionJob(String op, java.lang.String val, String relation) {
		addCondition(9, op, relation, val);
	}

	public void setSelectJob(boolean val) {
		__select_flags[9] = val;
	}

	public void setConditionEmployeeId(String op, java.lang.Integer val) {
		setConditionEmployeeId(op, val, CONDITION_AND);
	}

	public void setConditionEmployeeId(String op, java.lang.Integer val, String relation) {
		addCondition(10, op, relation, val);
	}

	public void setSelectEmployeeId(boolean val) {
		__select_flags[10] = val;
	}

	public void setConditionEcmcWorkspace(String op, java.lang.String val) {
		setConditionEcmcWorkspace(op, val, CONDITION_AND);
	}

	public void setConditionEcmcWorkspace(String op, java.lang.String val, String relation) {
		addCondition(11, op, relation, val);
	}

	public void setSelectEcmcWorkspace(boolean val) {
		__select_flags[11] = val;
	}

	public void setConditionUserType(String op, java.lang.Integer val) {
		setConditionUserType(op, val, CONDITION_AND);
	}

	public void setConditionUserType(String op, java.lang.Integer val, String relation) {
		addCondition(12, op, relation, val);
	}

	public void setSelectUserType(boolean val) {
		__select_flags[12] = val;
	}

	public void setConditionUserStatus(String op, java.lang.Integer val) {
		setConditionUserStatus(op, val, CONDITION_AND);
	}

	public void setConditionUserStatus(String op, java.lang.Integer val, String relation) {
		addCondition(13, op, relation, val);
	}

	public void setSelectUserStatus(boolean val) {
		__select_flags[13] = val;
	}

	public void setConditionCreateTime(String op, java.util.Date val) {
		setConditionCreateTime(op, val, CONDITION_AND);
	}

	public void setConditionCreateTime(String op, java.util.Date val, String relation) {
		addCondition(14, op, relation, generateTimestampFromDate(val));
	}

	public void setSelectCreateTime(boolean val) {
		__select_flags[14] = val;
	}

	public void setConditionDeleteTime(String op, java.util.Date val) {
		setConditionDeleteTime(op, val, CONDITION_AND);
	}

	public void setConditionDeleteTime(String op, java.util.Date val, String relation) {
		addCondition(15, op, relation, generateTimestampFromDate(val));
	}

	public void setSelectDeleteTime(boolean val) {
		__select_flags[15] = val;
	}

	public void setConditionDeleteFlag(String op, java.lang.Integer val) {
		setConditionDeleteFlag(op, val, CONDITION_AND);
	}

	public void setConditionDeleteFlag(String op, java.lang.Integer val, String relation) {
		addCondition(16, op, relation, val);
	}

	public void setSelectDeleteFlag(boolean val) {
		__select_flags[16] = val;
	}


}

