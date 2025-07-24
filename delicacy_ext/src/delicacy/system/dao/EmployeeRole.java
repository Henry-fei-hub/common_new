package delicacy.system.dao;

import delicacy.common.AbstractTable;
import delicacy.common.GenericBase;
import delicacy.system.bean.BaseEmployeeRole;


public class EmployeeRole extends AbstractTable<BaseEmployeeRole>
{

	public EmployeeRole() throws java.sql.SQLException 
	{

		initColumnNames();
	}

	protected final void initColumnNames()  throws java.sql.SQLException {

		__columnCount          = 5;

		initTables();

		__tableName            = "employee_roles";

		__key_columns          = new int[1];
		__key_columns[0] = 0;

		__column_names[0] = BaseEmployeeRole.CS_EMPLOYEE_ROLE_ID;
		__column_names[1] = BaseEmployeeRole.CS_EMPLOYEE_ID;
		__column_names[2] = BaseEmployeeRole.CS_ROLE_ID;
		__column_names[3] = BaseEmployeeRole.CS_DEPARTMENT_ID;
		__column_names[4] = BaseEmployeeRole.CS_IS_DEFAULT;

		resetSelectFlags() ;
		resetInsertFlags() ;
	}

	public void resetInsertFlags(){
		for(int i = 0; i < __columnCount; i ++ ) __insert_flags[i] = true;
		__insert_flags[0] = false;
		__serial_column = 0;
	}

	public void setPrimaryKeyFromBase(BaseEmployeeRole b) {
		clear();
		setEmployeeRoleIdClear(b.getEmployeeRoleId());
	}

	public boolean isPrimaryKeyNull() {
		return getEmployeeRoleId() == null;
	}

	@Override
	public BaseEmployeeRole generateBase(){
		BaseEmployeeRole b = new BaseEmployeeRole();
		setDataToBase(b);
		return b;
	}

	@Override
	public void setDataToBase(BaseEmployeeRole b){
		int count = 0; Object val;
		if((val = __current_data[count++]) != null) b.setEmployeeRoleId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setEmployeeId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setRoleId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setDepartmentId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setIsDefault(GenericBase.__getBoolean(val));
	}

	@Override
	public void setBaseToBuffer(BaseEmployeeRole b, Object[] buff){
		int count = 0;
		buff[count++] = b.getEmployeeRoleId();
		buff[count++] = b.getEmployeeId();
		buff[count++] = b.getRoleId();
		buff[count++] = b.getDepartmentId();
		buff[count++] = b.getIsDefault();
	}

	@Override
	public void setDataFromBase(BaseEmployeeRole b){
		if(b.getEmployeeRoleId() != null) setEmployeeRoleIdClear(b.getEmployeeRoleId());
		if(b.getEmployeeId() != null) setEmployeeId(b.getEmployeeId());
		if(b.getRoleId() != null) setRoleId(b.getRoleId());
		if(b.getDepartmentId() != null) setDepartmentId(b.getDepartmentId());
		if(b.getIsDefault() != null) setIsDefault(b.getIsDefault());
	}

	@Override
	public BaseEmployeeRole generateBaseFromResultSet(java.sql.ResultSet sdr)  throws java.sql.SQLException {

		BaseEmployeeRole b = new BaseEmployeeRole();
		setQueryResultToBase(b, sdr);
		return b;
	}

	@Override
	public void setQueryResultToBase(BaseEmployeeRole __base, java.sql.ResultSet sdr ) throws java.sql.SQLException {
		int count = 1, index = 0;
		java.sql.ResultSetMetaData rsmd = sdr.getMetaData();
		int columnCount = rsmd.getColumnCount();
		Object val;
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setEmployeeRoleId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setEmployeeId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setRoleId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setDepartmentId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setIsDefault(GenericBase.__getBoolean(val));
	}

	public void setEmployeeRoleId(java.lang.Integer val) {
		setCurrentData(0, val);
	}

	public java.lang.Integer getEmployeeRoleId() {
		return GenericBase.__getInt(__current_data[0]);
	}

	public void setEmployeeRoleIdClear(java.lang.Integer val) {
		__backup_data[0] = __current_data[0] =  val;
		__modified_flags[0] = false;
	}

	public void setEmployeeId(java.lang.Integer val) {
		setCurrentData(1, val);
	}

	public java.lang.Integer getEmployeeId() {
		return GenericBase.__getInt(__current_data[1]);
	}

	public void setRoleId(java.lang.Integer val) {
		setCurrentData(2, val);
	}

	public java.lang.Integer getRoleId() {
		return GenericBase.__getInt(__current_data[2]);
	}

	public void setDepartmentId(java.lang.Integer val) {
		setCurrentData(3, val);
	}

	public java.lang.Integer getDepartmentId() {
		return GenericBase.__getInt(__current_data[3]);
	}

	public void setIsDefault(java.lang.Boolean val) {
		setCurrentData(4, val);
	}

	public java.lang.Boolean getIsDefault() {
		return GenericBase.__getBoolean(__current_data[4]);
	}

	public void setConditionEmployeeRoleId(String op, java.lang.Integer val) {
		setConditionEmployeeRoleId(op, val, CONDITION_AND);
	}

	public void setConditionEmployeeRoleId(String op, java.lang.Integer val, String relation) {
		addCondition(0, op, relation, val);
	}

	public void setSelectEmployeeRoleId(boolean val) {
		__select_flags[0] = val;
	}

	public void setConditionEmployeeId(String op, java.lang.Integer val) {
		setConditionEmployeeId(op, val, CONDITION_AND);
	}

	public void setConditionEmployeeId(String op, java.lang.Integer val, String relation) {
		addCondition(1, op, relation, val);
	}

	public void setSelectEmployeeId(boolean val) {
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

	public void setConditionDepartmentId(String op, java.lang.Integer val) {
		setConditionDepartmentId(op, val, CONDITION_AND);
	}

	public void setConditionDepartmentId(String op, java.lang.Integer val, String relation) {
		addCondition(3, op, relation, val);
	}

	public void setSelectDepartmentId(boolean val) {
		__select_flags[3] = val;
	}

	public void setConditionIsDefault(String op, java.lang.Boolean val) {
		setConditionIsDefault(op, val, CONDITION_AND);
	}

	public void setConditionIsDefault(String op, java.lang.Boolean val, String relation) {
		addCondition(4, op, relation, val);
	}

	public void setSelectIsDefault(boolean val) {
		__select_flags[4] = val;
	}


}

