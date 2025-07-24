package delicacy.system.dao;

import delicacy.common.AbstractTable;
import delicacy.common.GenericBase;
import delicacy.system.bean.BaseDepartmentId;


public class DepartmentId extends AbstractTable<BaseDepartmentId>
{

	public DepartmentId() throws java.sql.SQLException 
	{

		initColumnNames();
	}

	protected final void initColumnNames()  throws java.sql.SQLException {

		__columnCount          = 3;

		initTables();

		__tableName            = "department_ids";

		__key_columns          = new int[1];
		__key_columns[0] = 0;

		__column_names[0] = BaseDepartmentId.CS_DEPARTMENT_ID_ID;
		__column_names[1] = BaseDepartmentId.CS_DEPARTMENT_ID;
		__column_names[2] = BaseDepartmentId.CS_CHILD_ID;

		resetSelectFlags() ;
		resetInsertFlags() ;
	}

	public void resetInsertFlags(){
		for(int i = 0; i < __columnCount; i ++ ) __insert_flags[i] = true;
		__insert_flags[0] = false;
		__serial_column = 0;
	}

	public void setPrimaryKeyFromBase(BaseDepartmentId b) {
		clear();
		setDepartmentIdIdClear(b.getDepartmentIdId());
	}

	public boolean isPrimaryKeyNull() {
		return getDepartmentIdId() == null;
	}

	@Override
	public BaseDepartmentId generateBase(){
		BaseDepartmentId b = new BaseDepartmentId();
		setDataToBase(b);
		return b;
	}

	@Override
	public void setDataToBase(BaseDepartmentId b){
		int count = 0; Object val;
		if((val = __current_data[count++]) != null) b.setDepartmentIdId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setDepartmentId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setChildId(GenericBase.__getInt(val));
	}

	@Override
	public void setBaseToBuffer(BaseDepartmentId b, Object[] buff){
		int count = 0;
		buff[count++] = b.getDepartmentIdId();
		buff[count++] = b.getDepartmentId();
		buff[count++] = b.getChildId();
	}

	@Override
	public void setDataFromBase(BaseDepartmentId b){
		if(b.getDepartmentIdId() != null) setDepartmentIdIdClear(b.getDepartmentIdId());
		if(b.getDepartmentId() != null) setDepartmentId(b.getDepartmentId());
		if(b.getChildId() != null) setChildId(b.getChildId());
	}

	@Override
	public BaseDepartmentId generateBaseFromResultSet(java.sql.ResultSet sdr)  throws java.sql.SQLException {

		BaseDepartmentId b = new BaseDepartmentId();
		setQueryResultToBase(b, sdr);
		return b;
	}

	@Override
	public void setQueryResultToBase(BaseDepartmentId __base, java.sql.ResultSet sdr ) throws java.sql.SQLException {
		int count = 1, index = 0;
		java.sql.ResultSetMetaData rsmd = sdr.getMetaData();
		int columnCount = rsmd.getColumnCount();
		Object val;
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setDepartmentIdId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setDepartmentId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setChildId(GenericBase.__getInt(val));
	}

	public void setDepartmentIdId(java.lang.Integer val) {
		setCurrentData(0, val);
	}

	public java.lang.Integer getDepartmentIdId() {
		return GenericBase.__getInt(__current_data[0]);
	}

	public void setDepartmentIdIdClear(java.lang.Integer val) {
		__backup_data[0] = __current_data[0] =  val;
		__modified_flags[0] = false;
	}

	public void setDepartmentId(java.lang.Integer val) {
		setCurrentData(1, val);
	}

	public java.lang.Integer getDepartmentId() {
		return GenericBase.__getInt(__current_data[1]);
	}

	public void setChildId(java.lang.Integer val) {
		setCurrentData(2, val);
	}

	public java.lang.Integer getChildId() {
		return GenericBase.__getInt(__current_data[2]);
	}

	public void setConditionDepartmentIdId(String op, java.lang.Integer val) {
		setConditionDepartmentIdId(op, val, CONDITION_AND);
	}

	public void setConditionDepartmentIdId(String op, java.lang.Integer val, String relation) {
		addCondition(0, op, relation, val);
	}

	public void setSelectDepartmentIdId(boolean val) {
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

	public void setConditionChildId(String op, java.lang.Integer val) {
		setConditionChildId(op, val, CONDITION_AND);
	}

	public void setConditionChildId(String op, java.lang.Integer val, String relation) {
		addCondition(2, op, relation, val);
	}

	public void setSelectChildId(boolean val) {
		__select_flags[2] = val;
	}


}

