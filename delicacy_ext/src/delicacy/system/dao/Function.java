package delicacy.system.dao;

import delicacy.common.AbstractTable;
import delicacy.common.GenericBase;
import delicacy.system.bean.BaseFunction;


public class Function extends AbstractTable<BaseFunction>
{

	public Function() throws java.sql.SQLException 
	{

		initColumnNames();
	}

	protected final void initColumnNames()  throws java.sql.SQLException {

		__columnCount          = 8;

		initTables();

		__tableName            = "functions";

		__key_columns          = new int[1];
		__key_columns[0] = 0;

		__column_names[0] = BaseFunction.CS_FUNCTION_ID;
		__column_names[1] = BaseFunction.CS_FUNCTION_CODE;
		__column_names[2] = BaseFunction.CS_PARENT_ID;
		__column_names[3] = BaseFunction.CS_FUNCTION_NAME;
		__column_names[4] = BaseFunction.CS_APPLICATION_ID;
		__column_names[5] = BaseFunction.CS_FUNCTION_TYPE;
		__column_names[6] = BaseFunction.CS_PRIVILEGE_TYPE;
		__column_names[7] = BaseFunction.CS_ENABLED;

		resetSelectFlags() ;
		resetInsertFlags() ;
	}

	public void resetInsertFlags(){
		for(int i = 0; i < __columnCount; i ++ ) __insert_flags[i] = true;
		__insert_flags[0] = false;
		__serial_column = 0;
	}

	public void setPrimaryKeyFromBase(BaseFunction b) {
		clear();
		setFunctionIdClear(b.getFunctionId());
	}

	public boolean isPrimaryKeyNull() {
		return getFunctionId() == null;
	}

	@Override
	public BaseFunction generateBase(){
		BaseFunction b = new BaseFunction();
		setDataToBase(b);
		return b;
	}

	@Override
	public void setDataToBase(BaseFunction b){
		int count = 0; Object val;
		if((val = __current_data[count++]) != null) b.setFunctionId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setFunctionCode(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setParentId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setFunctionName(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setApplicationId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setFunctionType(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setPrivilegeType(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setEnabled(GenericBase.__getBoolean(val));
	}

	@Override
	public void setBaseToBuffer(BaseFunction b, Object[] buff){
		int count = 0;
		buff[count++] = b.getFunctionId();
		buff[count++] = b.getFunctionCode();
		buff[count++] = b.getParentId();
		buff[count++] = b.getFunctionName();
		buff[count++] = b.getApplicationId();
		buff[count++] = b.getFunctionType();
		buff[count++] = b.getPrivilegeType();
		buff[count++] = b.getEnabled();
	}

	@Override
	public void setDataFromBase(BaseFunction b){
		if(b.getFunctionId() != null) setFunctionIdClear(b.getFunctionId());
		if(b.getFunctionCode() != null) setFunctionCode(b.getFunctionCode());
		if(b.getParentId() != null) setParentId(b.getParentId());
		if(b.getFunctionName() != null) setFunctionName(b.getFunctionName());
		if(b.getApplicationId() != null) setApplicationId(b.getApplicationId());
		if(b.getFunctionType() != null) setFunctionType(b.getFunctionType());
		if(b.getPrivilegeType() != null) setPrivilegeType(b.getPrivilegeType());
		if(b.getEnabled() != null) setEnabled(b.getEnabled());
	}

	@Override
	public BaseFunction generateBaseFromResultSet(java.sql.ResultSet sdr)  throws java.sql.SQLException {

		BaseFunction b = new BaseFunction();
		setQueryResultToBase(b, sdr);
		return b;
	}

	@Override
	public void setQueryResultToBase(BaseFunction __base, java.sql.ResultSet sdr ) throws java.sql.SQLException {
		int count = 1, index = 0;
		java.sql.ResultSetMetaData rsmd = sdr.getMetaData();
		int columnCount = rsmd.getColumnCount();
		Object val;
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setFunctionId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setFunctionCode(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setParentId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setFunctionName(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setApplicationId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setFunctionType(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setPrivilegeType(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setEnabled(GenericBase.__getBoolean(val));
	}

	public void setFunctionId(java.lang.Integer val) {
		setCurrentData(0, val);
	}

	public java.lang.Integer getFunctionId() {
		return GenericBase.__getInt(__current_data[0]);
	}

	public void setFunctionIdClear(java.lang.Integer val) {
		__backup_data[0] = __current_data[0] =  val;
		__modified_flags[0] = false;
	}

	public void setFunctionCode(java.lang.String val) {
		setCurrentData(1, val);
	}

	public java.lang.String getFunctionCode() {
		return GenericBase.__getString(__current_data[1]);
	}

	public void setParentId(java.lang.Integer val) {
		setCurrentData(2, val);
	}

	public java.lang.Integer getParentId() {
		return GenericBase.__getInt(__current_data[2]);
	}

	public void setFunctionName(java.lang.String val) {
		setCurrentData(3, val);
	}

	public java.lang.String getFunctionName() {
		return GenericBase.__getString(__current_data[3]);
	}

	public void setApplicationId(java.lang.Integer val) {
		setCurrentData(4, val);
	}

	public java.lang.Integer getApplicationId() {
		return GenericBase.__getInt(__current_data[4]);
	}

	public void setFunctionType(java.lang.Integer val) {
		setCurrentData(5, val);
	}

	public java.lang.Integer getFunctionType() {
		return GenericBase.__getInt(__current_data[5]);
	}

	public void setPrivilegeType(java.lang.Integer val) {
		setCurrentData(6, val);
	}

	public java.lang.Integer getPrivilegeType() {
		return GenericBase.__getInt(__current_data[6]);
	}

	public void setEnabled(java.lang.Boolean val) {
		setCurrentData(7, val);
	}

	public java.lang.Boolean getEnabled() {
		return GenericBase.__getBoolean(__current_data[7]);
	}

	public void setConditionFunctionId(String op, java.lang.Integer val) {
		setConditionFunctionId(op, val, CONDITION_AND);
	}

	public void setConditionFunctionId(String op, java.lang.Integer val, String relation) {
		addCondition(0, op, relation, val);
	}

	public void setSelectFunctionId(boolean val) {
		__select_flags[0] = val;
	}

	public void setConditionFunctionCode(String op, java.lang.String val) {
		setConditionFunctionCode(op, val, CONDITION_AND);
	}

	public void setConditionFunctionCode(String op, java.lang.String val, String relation) {
		addCondition(1, op, relation, val);
	}

	public void setSelectFunctionCode(boolean val) {
		__select_flags[1] = val;
	}

	public void setConditionParentId(String op, java.lang.Integer val) {
		setConditionParentId(op, val, CONDITION_AND);
	}

	public void setConditionParentId(String op, java.lang.Integer val, String relation) {
		addCondition(2, op, relation, val);
	}

	public void setSelectParentId(boolean val) {
		__select_flags[2] = val;
	}

	public void setConditionFunctionName(String op, java.lang.String val) {
		setConditionFunctionName(op, val, CONDITION_AND);
	}

	public void setConditionFunctionName(String op, java.lang.String val, String relation) {
		addCondition(3, op, relation, val);
	}

	public void setSelectFunctionName(boolean val) {
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

	public void setConditionFunctionType(String op, java.lang.Integer val) {
		setConditionFunctionType(op, val, CONDITION_AND);
	}

	public void setConditionFunctionType(String op, java.lang.Integer val, String relation) {
		addCondition(5, op, relation, val);
	}

	public void setSelectFunctionType(boolean val) {
		__select_flags[5] = val;
	}

	public void setConditionPrivilegeType(String op, java.lang.Integer val) {
		setConditionPrivilegeType(op, val, CONDITION_AND);
	}

	public void setConditionPrivilegeType(String op, java.lang.Integer val, String relation) {
		addCondition(6, op, relation, val);
	}

	public void setSelectPrivilegeType(boolean val) {
		__select_flags[6] = val;
	}

	public void setConditionEnabled(String op, java.lang.Boolean val) {
		setConditionEnabled(op, val, CONDITION_AND);
	}

	public void setConditionEnabled(String op, java.lang.Boolean val, String relation) {
		addCondition(7, op, relation, val);
	}

	public void setSelectEnabled(boolean val) {
		__select_flags[7] = val;
	}


}

