package delicacy.system.dao;

import delicacy.common.AbstractTable;
import delicacy.common.GenericBase;
import delicacy.system.bean.BaseDepartmentRole;


public class DepartmentRole extends AbstractTable<BaseDepartmentRole>
{

	public DepartmentRole() throws java.sql.SQLException 
	{

		initColumnNames();
	}

	protected final void initColumnNames()  throws java.sql.SQLException {

		__columnCount          = 3;

		initTables();

		__tableName            = "department_roles";

		__key_columns          = new int[1];
		__key_columns[0] = 0;

		__column_names[0] = BaseDepartmentRole.CS_DEPARTMENT_ROLE_ID;
		__column_names[1] = BaseDepartmentRole.CS_DEPARTMENT_ID;
		__column_names[2] = BaseDepartmentRole.CS_ROLE_ID;

		resetSelectFlags() ;
		resetInsertFlags() ;
	}

	public void resetInsertFlags(){
		for(int i = 0; i < __columnCount; i ++ ) __insert_flags[i] = true;
		__insert_flags[0] = false;
		__serial_column = 0;
	}

	public void setPrimaryKeyFromBase(BaseDepartmentRole b) {
		clear();
		setDepartmentRoleIdClear(b.getDepartmentRoleId());
	}

	public boolean isPrimaryKeyNull() {
		return getDepartmentRoleId() == null;
	}

	@Override
	public BaseDepartmentRole generateBase(){
		BaseDepartmentRole b = new BaseDepartmentRole();
		setDataToBase(b);
		return b;
	}

	@Override
	public void setDataToBase(BaseDepartmentRole b){
		int count = 0; Object val;
		if((val = __current_data[count++]) != null) b.setDepartmentRoleId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setDepartmentId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setRoleId(GenericBase.__getInt(val));
	}

	@Override
	public void setBaseToBuffer(BaseDepartmentRole b, Object[] buff){
		int count = 0;
		buff[count++] = b.getDepartmentRoleId();
		buff[count++] = b.getDepartmentId();
		buff[count++] = b.getRoleId();
	}

	@Override
	public void setDataFromBase(BaseDepartmentRole b){
		if(b.getDepartmentRoleId() != null) setDepartmentRoleIdClear(b.getDepartmentRoleId());
		if(b.getDepartmentId() != null) setDepartmentId(b.getDepartmentId());
		if(b.getRoleId() != null) setRoleId(b.getRoleId());
	}

	@Override
	public BaseDepartmentRole generateBaseFromResultSet(java.sql.ResultSet sdr)  throws java.sql.SQLException {

		BaseDepartmentRole b = new BaseDepartmentRole();
		setQueryResultToBase(b, sdr);
		return b;
	}

	@Override
	public void setQueryResultToBase(BaseDepartmentRole __base, java.sql.ResultSet sdr ) throws java.sql.SQLException {
		int count = 1, index = 0;
		java.sql.ResultSetMetaData rsmd = sdr.getMetaData();
		int columnCount = rsmd.getColumnCount();
		Object val;
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setDepartmentRoleId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setDepartmentId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setRoleId(GenericBase.__getInt(val));
	}

	public void setDepartmentRoleId(java.lang.Integer val) {
		setCurrentData(0, val);
	}

	public java.lang.Integer getDepartmentRoleId() {
		return GenericBase.__getInt(__current_data[0]);
	}

	public void setDepartmentRoleIdClear(java.lang.Integer val) {
		__backup_data[0] = __current_data[0] =  val;
		__modified_flags[0] = false;
	}

	public void setDepartmentId(java.lang.Integer val) {
		setCurrentData(1, val);
	}

	public java.lang.Integer getDepartmentId() {
		return GenericBase.__getInt(__current_data[1]);
	}

	public void setRoleId(java.lang.Integer val) {
		setCurrentData(2, val);
	}

	public java.lang.Integer getRoleId() {
		return GenericBase.__getInt(__current_data[2]);
	}

	public void setConditionDepartmentRoleId(String op, java.lang.Integer val) {
		setConditionDepartmentRoleId(op, val, CONDITION_AND);
	}

	public void setConditionDepartmentRoleId(String op, java.lang.Integer val, String relation) {
		addCondition(0, op, relation, val);
	}

	public void setSelectDepartmentRoleId(boolean val) {
		__select_flags[0] = val;
	}

	public void setConditionDepartmentId(String op, java.lang.Integer val) {
		setConditionDepartmentId(op, val, CONDITION_AND);
	}

	public void setConditionDepartmentId(String op, java.lang.Integer val, String relation) {
		addCondition(1, op, relation, val);
	}

	public void setSelectDepartmentId(boolean val) {
		__select_flags[1] = val;
	}

	public void setConditionRoleId(String op, java.lang.Integer val) {
		setConditionRoleId(op, val, CONDITION_AND);
	}

	public void setConditionRoleId(String op, java.lang.Integer val, String relation) {
		addCondition(2, op, relation, val);
	}

	public void setSelectRoleId(boolean val) {
		__select_flags[2] = val;
	}


}

