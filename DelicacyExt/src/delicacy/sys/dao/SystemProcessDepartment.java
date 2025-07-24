package delicacy.sys.dao;

import delicacy.common.AbstractTable;
import delicacy.common.GenericBase;
import delicacy.sys.bean.BaseSystemProcessDepartment;


public class SystemProcessDepartment extends AbstractTable<BaseSystemProcessDepartment>
{

	public SystemProcessDepartment() throws java.sql.SQLException 
	{

		initColumnNames();
	}

	protected final void initColumnNames()  throws java.sql.SQLException {

		__columnCount          = 5;

		initTables();

		__tableName            = "system_process_departments";

		__key_columns          = new int[1];
		__key_columns[0] = 0;

		__column_names[0] = BaseSystemProcessDepartment.CS_PROCESS_DEPARTMENT_ID;
		__column_names[1] = BaseSystemProcessDepartment.CS_PROCESS_ID;
		__column_names[2] = BaseSystemProcessDepartment.CS_DEPARTMENT_ID;
		__column_names[3] = BaseSystemProcessDepartment.CS_IS_ENABLE;
		__column_names[4] = BaseSystemProcessDepartment.CS_ORGANIZATION_ID;

		resetSelectFlags() ;
		resetInsertFlags() ;
	}

	public void resetInsertFlags(){
		for(int i = 0; i < __columnCount; i ++ ) __insert_flags[i] = true;
		__insert_flags[0] = false;
		__serial_column = 0;
	}

	public void setPrimaryKeyFromBase(BaseSystemProcessDepartment b) {
		clear();
		setProcessDepartmentIdClear(b.getProcessDepartmentId());
	}

	public boolean isPrimaryKeyNull() {
		return getProcessDepartmentId() == null;
	}

	@Override
	public BaseSystemProcessDepartment generateBase(){
		BaseSystemProcessDepartment b = new BaseSystemProcessDepartment();
		setDataToBase(b);
		return b;
	}

	@Override
	public void setDataToBase(BaseSystemProcessDepartment b){
		int count = 0; Object val;
		if((val = __current_data[count++]) != null) b.setProcessDepartmentId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setProcessId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setDepartmentId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setIsEnable(GenericBase.__getBoolean(val));
		if((val = __current_data[count++]) != null) b.setOrganizationId(GenericBase.__getInt(val));
	}

	@Override
	public void setBaseToBuffer(BaseSystemProcessDepartment b, Object[] buff){
		int count = 0;
		buff[count++] = b.getProcessDepartmentId();
		buff[count++] = b.getProcessId();
		buff[count++] = b.getDepartmentId();
		buff[count++] = b.getIsEnable();
		buff[count++] = b.getOrganizationId();
	}

	@Override
	public void setDataFromBase(BaseSystemProcessDepartment b){
		if(b.getProcessDepartmentId() != null) setProcessDepartmentIdClear(b.getProcessDepartmentId());
		if(b.getProcessId() != null) setProcessId(b.getProcessId());
		if(b.getDepartmentId() != null) setDepartmentId(b.getDepartmentId());
		if(b.getIsEnable() != null) setIsEnable(b.getIsEnable());
		if(b.getOrganizationId() != null) setOrganizationId(b.getOrganizationId());
	}

	@Override
	public BaseSystemProcessDepartment generateBaseFromResultSet(java.sql.ResultSet sdr)  throws java.sql.SQLException {

		BaseSystemProcessDepartment b = new BaseSystemProcessDepartment();
		setQueryResultToBase(b, sdr);
		return b;
	}

	@Override
	public void setQueryResultToBase(BaseSystemProcessDepartment __base, java.sql.ResultSet sdr ) throws java.sql.SQLException {
		int count = 1, index = 0;
		java.sql.ResultSetMetaData rsmd = sdr.getMetaData();
		int columnCount = rsmd.getColumnCount();
		Object val;
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setProcessDepartmentId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setProcessId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setDepartmentId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setIsEnable(GenericBase.__getBoolean(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setOrganizationId(GenericBase.__getInt(val));
	}

	public void setProcessDepartmentId(java.lang.Integer val) {
		setCurrentData(0, val);
	}

	public java.lang.Integer getProcessDepartmentId() {
		return GenericBase.__getInt(__current_data[0]);
	}

	public void setProcessDepartmentIdClear(java.lang.Integer val) {
		__backup_data[0] = __current_data[0] =  val;
		__modified_flags[0] = false;
	}

	public void setProcessId(java.lang.Integer val) {
		setCurrentData(1, val);
	}

	public java.lang.Integer getProcessId() {
		return GenericBase.__getInt(__current_data[1]);
	}

	public void setDepartmentId(java.lang.Integer val) {
		setCurrentData(2, val);
	}

	public java.lang.Integer getDepartmentId() {
		return GenericBase.__getInt(__current_data[2]);
	}

	public void setIsEnable(java.lang.Boolean val) {
		setCurrentData(3, val);
	}

	public java.lang.Boolean getIsEnable() {
		return GenericBase.__getBoolean(__current_data[3]);
	}

	public void setOrganizationId(java.lang.Integer val) {
		setCurrentData(4, val);
	}

	public java.lang.Integer getOrganizationId() {
		return GenericBase.__getInt(__current_data[4]);
	}

	public void setConditionProcessDepartmentId(String op, java.lang.Integer val) {
		setConditionProcessDepartmentId(op, val, CONDITION_AND);
	}

	public void setConditionProcessDepartmentId(String op, java.lang.Integer val, String relation) {
		addCondition(0, op, relation, val);
	}

	public void setSelectProcessDepartmentId(boolean val) {
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

	public void setConditionDepartmentId(String op, java.lang.Integer val) {
		setConditionDepartmentId(op, val, CONDITION_AND);
	}

	public void setConditionDepartmentId(String op, java.lang.Integer val, String relation) {
		addCondition(2, op, relation, val);
	}

	public void setSelectDepartmentId(boolean val) {
		__select_flags[2] = val;
	}

	public void setConditionIsEnable(String op, java.lang.Boolean val) {
		setConditionIsEnable(op, val, CONDITION_AND);
	}

	public void setConditionIsEnable(String op, java.lang.Boolean val, String relation) {
		addCondition(3, op, relation, val);
	}

	public void setSelectIsEnable(boolean val) {
		__select_flags[3] = val;
	}

	public void setConditionOrganizationId(String op, java.lang.Integer val) {
		setConditionOrganizationId(op, val, CONDITION_AND);
	}

	public void setConditionOrganizationId(String op, java.lang.Integer val, String relation) {
		addCondition(4, op, relation, val);
	}

	public void setSelectOrganizationId(boolean val) {
		__select_flags[4] = val;
	}


}

