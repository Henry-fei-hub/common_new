package delicacy.system.dao;

import delicacy.common.AbstractTable;
import delicacy.common.GenericBase;
import delicacy.system.bean.BaseDepartmentRoleFunction;


public class DepartmentRoleFunction extends AbstractTable<BaseDepartmentRoleFunction>
{

	public DepartmentRoleFunction() throws java.sql.SQLException 
	{

		initColumnNames();
	}

	protected final void initColumnNames()  throws java.sql.SQLException {

		__columnCount          = 5;

		initTables();

		__tableName            = "department_role_functions";

		__key_columns          = new int[1];
		__key_columns[0] = 0;

		__column_names[0] = BaseDepartmentRoleFunction.CS_DEPARTMENT_ROLE_FUNCTION_ID;
		__column_names[1] = BaseDepartmentRoleFunction.CS_FUNCTION_ID;
		__column_names[2] = BaseDepartmentRoleFunction.CS_APPLICATION_ID;
		__column_names[3] = BaseDepartmentRoleFunction.CS_ROLE_ID;
		__column_names[4] = BaseDepartmentRoleFunction.CS_DEPARTMENT_ID;

		resetSelectFlags() ;
		resetInsertFlags() ;
	}

	public void resetInsertFlags(){
		for(int i = 0; i < __columnCount; i ++ ) __insert_flags[i] = true;
		__insert_flags[0] = false;
		__serial_column = 0;
	}

	public void setPrimaryKeyFromBase(BaseDepartmentRoleFunction b) {
		clear();
		setDepartmentRoleFunctionIdClear(b.getDepartmentRoleFunctionId());
	}

	public boolean isPrimaryKeyNull() {
		return getDepartmentRoleFunctionId() == null;
	}

	@Override
	public BaseDepartmentRoleFunction generateBase(){
		BaseDepartmentRoleFunction b = new BaseDepartmentRoleFunction();
		setDataToBase(b);
		return b;
	}

	@Override
	public void setDataToBase(BaseDepartmentRoleFunction b){
		int count = 0; Object val;
		if((val = __current_data[count++]) != null) b.setDepartmentRoleFunctionId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setFunctionId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setApplicationId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setRoleId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setDepartmentId(GenericBase.__getInt(val));
	}

	@Override
	public void setBaseToBuffer(BaseDepartmentRoleFunction b, Object[] buff){
		int count = 0;
		buff[count++] = b.getDepartmentRoleFunctionId();
		buff[count++] = b.getFunctionId();
		buff[count++] = b.getApplicationId();
		buff[count++] = b.getRoleId();
		buff[count++] = b.getDepartmentId();
	}

	@Override
	public void setDataFromBase(BaseDepartmentRoleFunction b){
		if(b.getDepartmentRoleFunctionId() != null) setDepartmentRoleFunctionIdClear(b.getDepartmentRoleFunctionId());
		if(b.getFunctionId() != null) setFunctionId(b.getFunctionId());
		if(b.getApplicationId() != null) setApplicationId(b.getApplicationId());
		if(b.getRoleId() != null) setRoleId(b.getRoleId());
		if(b.getDepartmentId() != null) setDepartmentId(b.getDepartmentId());
	}

	@Override
	public BaseDepartmentRoleFunction generateBaseFromResultSet(java.sql.ResultSet sdr)  throws java.sql.SQLException {

		BaseDepartmentRoleFunction b = new BaseDepartmentRoleFunction();
		setQueryResultToBase(b, sdr);
		return b;
	}

	@Override
	public void setQueryResultToBase(BaseDepartmentRoleFunction __base, java.sql.ResultSet sdr ) throws java.sql.SQLException {
		int count = 1, index = 0;
		java.sql.ResultSetMetaData rsmd = sdr.getMetaData();
		int columnCount = rsmd.getColumnCount();
		Object val;
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setDepartmentRoleFunctionId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setFunctionId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setApplicationId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setRoleId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setDepartmentId(GenericBase.__getInt(val));
	}

	public void setDepartmentRoleFunctionId(java.lang.Integer val) {
		setCurrentData(0, val);
	}

	public java.lang.Integer getDepartmentRoleFunctionId() {
		return GenericBase.__getInt(__current_data[0]);
	}

	public void setDepartmentRoleFunctionIdClear(java.lang.Integer val) {
		__backup_data[0] = __current_data[0] =  val;
		__modified_flags[0] = false;
	}

	public void setFunctionId(java.lang.Integer val) {
		setCurrentData(1, val);
	}

	public java.lang.Integer getFunctionId() {
		return GenericBase.__getInt(__current_data[1]);
	}

	public void setApplicationId(java.lang.Integer val) {
		setCurrentData(2, val);
	}

	public java.lang.Integer getApplicationId() {
		return GenericBase.__getInt(__current_data[2]);
	}

	public void setRoleId(java.lang.Integer val) {
		setCurrentData(3, val);
	}

	public java.lang.Integer getRoleId() {
		return GenericBase.__getInt(__current_data[3]);
	}

	public void setDepartmentId(java.lang.Integer val) {
		setCurrentData(4, val);
	}

	public java.lang.Integer getDepartmentId() {
		return GenericBase.__getInt(__current_data[4]);
	}

	public void setConditionDepartmentRoleFunctionId(String op, java.lang.Integer val) {
		setConditionDepartmentRoleFunctionId(op, val, CONDITION_AND);
	}

	public void setConditionDepartmentRoleFunctionId(String op, java.lang.Integer val, String relation) {
		addCondition(0, op, relation, val);
	}

	public void setSelectDepartmentRoleFunctionId(boolean val) {
		__select_flags[0] = val;
	}

	public void setConditionFunctionId(String op, java.lang.Integer val) {
		setConditionFunctionId(op, val, CONDITION_AND);
	}

	public void setConditionFunctionId(String op, java.lang.Integer val, String relation) {
		addCondition(1, op, relation, val);
	}

	public void setSelectFunctionId(boolean val) {
		__select_flags[1] = val;
	}

	public void setConditionApplicationId(String op, java.lang.Integer val) {
		setConditionApplicationId(op, val, CONDITION_AND);
	}

	public void setConditionApplicationId(String op, java.lang.Integer val, String relation) {
		addCondition(2, op, relation, val);
	}

	public void setSelectApplicationId(boolean val) {
		__select_flags[2] = val;
	}

	public void setConditionRoleId(String op, java.lang.Integer val) {
		setConditionRoleId(op, val, CONDITION_AND);
	}

	public void setConditionRoleId(String op, java.lang.Integer val, String relation) {
		addCondition(3, op, relation, val);
	}

	public void setSelectRoleId(boolean val) {
		__select_flags[3] = val;
	}

	public void setConditionDepartmentId(String op, java.lang.Integer val) {
		setConditionDepartmentId(op, val, CONDITION_AND);
	}

	public void setConditionDepartmentId(String op, java.lang.Integer val, String relation) {
		addCondition(4, op, relation, val);
	}

	public void setSelectDepartmentId(boolean val) {
		__select_flags[4] = val;
	}


}

