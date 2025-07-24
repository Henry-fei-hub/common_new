package delicacy.system.dao;

import delicacy.common.AbstractTable;
import delicacy.common.GenericBase;
import delicacy.system.bean.BaseEmployeeFunction;


public class EmployeeFunction extends AbstractTable<BaseEmployeeFunction>
{

	public EmployeeFunction() throws java.sql.SQLException 
	{

		initColumnNames();
	}

	protected final void initColumnNames()  throws java.sql.SQLException {

		__columnCount          = 4;

		initTables();

		__tableName            = "employee_functions";

		__key_columns          = new int[1];
		__key_columns[0] = 0;

		__column_names[0] = BaseEmployeeFunction.CS_EMPLOYEE_FUNCTION_ID;
		__column_names[1] = BaseEmployeeFunction.CS_EMPLOYEE_ID;
		__column_names[2] = BaseEmployeeFunction.CS_FUNCTION_ID;
		__column_names[3] = BaseEmployeeFunction.CS_APPLICATION_ID;

		resetSelectFlags() ;
		resetInsertFlags() ;
	}

	public void resetInsertFlags(){
		for(int i = 0; i < __columnCount; i ++ ) __insert_flags[i] = true;
		__insert_flags[0] = false;
		__serial_column = 0;
	}

	public void setPrimaryKeyFromBase(BaseEmployeeFunction b) {
		clear();
		setEmployeeFunctionIdClear(b.getEmployeeFunctionId());
	}

	public boolean isPrimaryKeyNull() {
		return getEmployeeFunctionId() == null;
	}

	@Override
	public BaseEmployeeFunction generateBase(){
		BaseEmployeeFunction b = new BaseEmployeeFunction();
		setDataToBase(b);
		return b;
	}

	@Override
	public void setDataToBase(BaseEmployeeFunction b){
		int count = 0; Object val;
		if((val = __current_data[count++]) != null) b.setEmployeeFunctionId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setEmployeeId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setFunctionId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setApplicationId(GenericBase.__getInt(val));
	}

	@Override
	public void setBaseToBuffer(BaseEmployeeFunction b, Object[] buff){
		int count = 0;
		buff[count++] = b.getEmployeeFunctionId();
		buff[count++] = b.getEmployeeId();
		buff[count++] = b.getFunctionId();
		buff[count++] = b.getApplicationId();
	}

	@Override
	public void setDataFromBase(BaseEmployeeFunction b){
		if(b.getEmployeeFunctionId() != null) setEmployeeFunctionIdClear(b.getEmployeeFunctionId());
		if(b.getEmployeeId() != null) setEmployeeId(b.getEmployeeId());
		if(b.getFunctionId() != null) setFunctionId(b.getFunctionId());
		if(b.getApplicationId() != null) setApplicationId(b.getApplicationId());
	}

	@Override
	public BaseEmployeeFunction generateBaseFromResultSet(java.sql.ResultSet sdr)  throws java.sql.SQLException {

		BaseEmployeeFunction b = new BaseEmployeeFunction();
		setQueryResultToBase(b, sdr);
		return b;
	}

	@Override
	public void setQueryResultToBase(BaseEmployeeFunction __base, java.sql.ResultSet sdr ) throws java.sql.SQLException {
		int count = 1, index = 0;
		java.sql.ResultSetMetaData rsmd = sdr.getMetaData();
		int columnCount = rsmd.getColumnCount();
		Object val;
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setEmployeeFunctionId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setEmployeeId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setFunctionId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setApplicationId(GenericBase.__getInt(val));
	}

	public void setEmployeeFunctionId(java.lang.Integer val) {
		setCurrentData(0, val);
	}

	public java.lang.Integer getEmployeeFunctionId() {
		return GenericBase.__getInt(__current_data[0]);
	}

	public void setEmployeeFunctionIdClear(java.lang.Integer val) {
		__backup_data[0] = __current_data[0] =  val;
		__modified_flags[0] = false;
	}

	public void setEmployeeId(java.lang.Integer val) {
		setCurrentData(1, val);
	}

	public java.lang.Integer getEmployeeId() {
		return GenericBase.__getInt(__current_data[1]);
	}

	public void setFunctionId(java.lang.Integer val) {
		setCurrentData(2, val);
	}

	public java.lang.Integer getFunctionId() {
		return GenericBase.__getInt(__current_data[2]);
	}

	public void setApplicationId(java.lang.Integer val) {
		setCurrentData(3, val);
	}

	public java.lang.Integer getApplicationId() {
		return GenericBase.__getInt(__current_data[3]);
	}

	public void setConditionEmployeeFunctionId(String op, java.lang.Integer val) {
		setConditionEmployeeFunctionId(op, val, CONDITION_AND);
	}

	public void setConditionEmployeeFunctionId(String op, java.lang.Integer val, String relation) {
		addCondition(0, op, relation, val);
	}

	public void setSelectEmployeeFunctionId(boolean val) {
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

	public void setConditionFunctionId(String op, java.lang.Integer val) {
		setConditionFunctionId(op, val, CONDITION_AND);
	}

	public void setConditionFunctionId(String op, java.lang.Integer val, String relation) {
		addCondition(2, op, relation, val);
	}

	public void setSelectFunctionId(boolean val) {
		__select_flags[2] = val;
	}

	public void setConditionApplicationId(String op, java.lang.Integer val) {
		setConditionApplicationId(op, val, CONDITION_AND);
	}

	public void setConditionApplicationId(String op, java.lang.Integer val, String relation) {
		addCondition(3, op, relation, val);
	}

	public void setSelectApplicationId(boolean val) {
		__select_flags[3] = val;
	}


}

