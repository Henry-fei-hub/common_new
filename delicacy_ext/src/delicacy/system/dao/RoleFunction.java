package delicacy.system.dao;

import delicacy.common.AbstractTable;
import delicacy.common.GenericBase;
import delicacy.system.bean.BaseRoleFunction;


public class RoleFunction extends AbstractTable<BaseRoleFunction>
{

	public RoleFunction() throws java.sql.SQLException 
	{

		initColumnNames();
	}

	protected final void initColumnNames()  throws java.sql.SQLException {

		__columnCount          = 5;

		initTables();

		__tableName            = "role_functions";

		__key_columns          = new int[1];
		__key_columns[0] = 0;

		__column_names[0] = BaseRoleFunction.CS_ROLE_FUNCTION_ID;
		__column_names[1] = BaseRoleFunction.CS_ROLE_ID;
		__column_names[2] = BaseRoleFunction.CS_FUNCTION_ID;
		__column_names[3] = BaseRoleFunction.CS_DEPARTMENT_ID;
		__column_names[4] = BaseRoleFunction.CS_APPLICATION_ID;

		resetSelectFlags() ;
		resetInsertFlags() ;
	}

	public void resetInsertFlags(){
		for(int i = 0; i < __columnCount; i ++ ) __insert_flags[i] = true;
		__insert_flags[0] = false;
		__serial_column = 0;
	}

	public void setPrimaryKeyFromBase(BaseRoleFunction b) {
		clear();
		setRoleFunctionIdClear(b.getRoleFunctionId());
	}

	public boolean isPrimaryKeyNull() {
		return getRoleFunctionId() == null;
	}

	@Override
	public BaseRoleFunction generateBase(){
		BaseRoleFunction b = new BaseRoleFunction();
		setDataToBase(b);
		return b;
	}

	@Override
	public void setDataToBase(BaseRoleFunction b){
		int count = 0; Object val;
		if((val = __current_data[count++]) != null) b.setRoleFunctionId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setRoleId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setFunctionId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setDepartmentId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setApplicationId(GenericBase.__getInt(val));
	}

	@Override
	public void setBaseToBuffer(BaseRoleFunction b, Object[] buff){
		int count = 0;
		buff[count++] = b.getRoleFunctionId();
		buff[count++] = b.getRoleId();
		buff[count++] = b.getFunctionId();
		buff[count++] = b.getDepartmentId();
		buff[count++] = b.getApplicationId();
	}

	@Override
	public void setDataFromBase(BaseRoleFunction b){
		if(b.getRoleFunctionId() != null) setRoleFunctionIdClear(b.getRoleFunctionId());
		if(b.getRoleId() != null) setRoleId(b.getRoleId());
		if(b.getFunctionId() != null) setFunctionId(b.getFunctionId());
		if(b.getDepartmentId() != null) setDepartmentId(b.getDepartmentId());
		if(b.getApplicationId() != null) setApplicationId(b.getApplicationId());
	}

	@Override
	public BaseRoleFunction generateBaseFromResultSet(java.sql.ResultSet sdr)  throws java.sql.SQLException {

		BaseRoleFunction b = new BaseRoleFunction();
		setQueryResultToBase(b, sdr);
		return b;
	}

	@Override
	public void setQueryResultToBase(BaseRoleFunction __base, java.sql.ResultSet sdr ) throws java.sql.SQLException {
		int count = 1, index = 0;
		java.sql.ResultSetMetaData rsmd = sdr.getMetaData();
		int columnCount = rsmd.getColumnCount();
		Object val;
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setRoleFunctionId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setRoleId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setFunctionId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setDepartmentId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setApplicationId(GenericBase.__getInt(val));
	}

	public void setRoleFunctionId(java.lang.Integer val) {
		setCurrentData(0, val);
	}

	public java.lang.Integer getRoleFunctionId() {
		return GenericBase.__getInt(__current_data[0]);
	}

	public void setRoleFunctionIdClear(java.lang.Integer val) {
		__backup_data[0] = __current_data[0] =  val;
		__modified_flags[0] = false;
	}

	public void setRoleId(java.lang.Integer val) {
		setCurrentData(1, val);
	}

	public java.lang.Integer getRoleId() {
		return GenericBase.__getInt(__current_data[1]);
	}

	public void setFunctionId(java.lang.Integer val) {
		setCurrentData(2, val);
	}

	public java.lang.Integer getFunctionId() {
		return GenericBase.__getInt(__current_data[2]);
	}

	public void setDepartmentId(java.lang.Integer val) {
		setCurrentData(3, val);
	}

	public java.lang.Integer getDepartmentId() {
		return GenericBase.__getInt(__current_data[3]);
	}

	public void setApplicationId(java.lang.Integer val) {
		setCurrentData(4, val);
	}

	public java.lang.Integer getApplicationId() {
		return GenericBase.__getInt(__current_data[4]);
	}

	public void setConditionRoleFunctionId(String op, java.lang.Integer val) {
		setConditionRoleFunctionId(op, val, CONDITION_AND);
	}

	public void setConditionRoleFunctionId(String op, java.lang.Integer val, String relation) {
		addCondition(0, op, relation, val);
	}

	public void setSelectRoleFunctionId(boolean val) {
		__select_flags[0] = val;
	}

	public void setConditionRoleId(String op, java.lang.Integer val) {
		setConditionRoleId(op, val, CONDITION_AND);
	}

	public void setConditionRoleId(String op, java.lang.Integer val, String relation) {
		addCondition(1, op, relation, val);
	}

	public void setSelectRoleId(boolean val) {
		__select_flags[1] = val;
	}

	public void setConditionFunctionId(String op, java.lang.Integer val) {
		setConditionFunctionId(op, val, CONDITION_AND);
	}

	public void setConditionFunctionId(String op, java.lang.Integer val, String relation) {
		addCondition(2, op, relation, val);
	}

	public void setSelectFunctionId(boolean val) {
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

	public void setConditionApplicationId(String op, java.lang.Integer val) {
		setConditionApplicationId(op, val, CONDITION_AND);
	}

	public void setConditionApplicationId(String op, java.lang.Integer val, String relation) {
		addCondition(4, op, relation, val);
	}

	public void setSelectApplicationId(boolean val) {
		__select_flags[4] = val;
	}


}

