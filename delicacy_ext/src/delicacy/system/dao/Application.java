package delicacy.system.dao;

import delicacy.common.AbstractTable;
import delicacy.common.GenericBase;
import delicacy.system.bean.BaseApplication;


public class Application extends AbstractTable<BaseApplication>
{

	public Application() throws java.sql.SQLException 
	{

		initColumnNames();
	}

	protected final void initColumnNames()  throws java.sql.SQLException {

		__columnCount          = 2;

		initTables();

		__tableName            = "applications";

		__key_columns          = new int[1];
		__key_columns[0] = 0;

		__column_names[0] = BaseApplication.CS_APPLICATION_ID;
		__column_names[1] = BaseApplication.CS_APPLICATION_NAME;

		resetSelectFlags() ;
		resetInsertFlags() ;
	}

	public void resetInsertFlags(){
		for(int i = 0; i < __columnCount; i ++ ) __insert_flags[i] = true;
		__insert_flags[0] = false;
		__serial_column = 0;
	}

	public void setPrimaryKeyFromBase(BaseApplication b) {
		clear();
		setApplicationIdClear(b.getApplicationId());
	}

	public boolean isPrimaryKeyNull() {
		return getApplicationId() == null;
	}

	@Override
	public BaseApplication generateBase(){
		BaseApplication b = new BaseApplication();
		setDataToBase(b);
		return b;
	}

	@Override
	public void setDataToBase(BaseApplication b){
		int count = 0; Object val;
		if((val = __current_data[count++]) != null) b.setApplicationId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setApplicationName(GenericBase.__getString(val));
	}

	@Override
	public void setBaseToBuffer(BaseApplication b, Object[] buff){
		int count = 0;
		buff[count++] = b.getApplicationId();
		buff[count++] = b.getApplicationName();
	}

	@Override
	public void setDataFromBase(BaseApplication b){
		if(b.getApplicationId() != null) setApplicationIdClear(b.getApplicationId());
		if(b.getApplicationName() != null) setApplicationName(b.getApplicationName());
	}

	@Override
	public BaseApplication generateBaseFromResultSet(java.sql.ResultSet sdr)  throws java.sql.SQLException {

		BaseApplication b = new BaseApplication();
		setQueryResultToBase(b, sdr);
		return b;
	}

	@Override
	public void setQueryResultToBase(BaseApplication __base, java.sql.ResultSet sdr ) throws java.sql.SQLException {
		int count = 1, index = 0;
		java.sql.ResultSetMetaData rsmd = sdr.getMetaData();
		int columnCount = rsmd.getColumnCount();
		Object val;
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setApplicationId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setApplicationName(GenericBase.__getString(val));
	}

	public void setApplicationId(java.lang.Integer val) {
		setCurrentData(0, val);
	}

	public java.lang.Integer getApplicationId() {
		return GenericBase.__getInt(__current_data[0]);
	}

	public void setApplicationIdClear(java.lang.Integer val) {
		__backup_data[0] = __current_data[0] =  val;
		__modified_flags[0] = false;
	}

	public void setApplicationName(java.lang.String val) {
		setCurrentData(1, val);
	}

	public java.lang.String getApplicationName() {
		return GenericBase.__getString(__current_data[1]);
	}

	public void setConditionApplicationId(String op, java.lang.Integer val) {
		setConditionApplicationId(op, val, CONDITION_AND);
	}

	public void setConditionApplicationId(String op, java.lang.Integer val, String relation) {
		addCondition(0, op, relation, val);
	}

	public void setSelectApplicationId(boolean val) {
		__select_flags[0] = val;
	}

	public void setConditionApplicationName(String op, java.lang.String val) {
		setConditionApplicationName(op, val, CONDITION_AND);
	}

	public void setConditionApplicationName(String op, java.lang.String val, String relation) {
		addCondition(1, op, relation, val);
	}

	public void setSelectApplicationName(boolean val) {
		__select_flags[1] = val;
	}


}

