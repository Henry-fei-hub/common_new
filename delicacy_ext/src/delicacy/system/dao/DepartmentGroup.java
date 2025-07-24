package delicacy.system.dao;

import delicacy.common.AbstractTable;
import delicacy.common.GenericBase;
import delicacy.system.bean.BaseDepartmentGroup;


public class DepartmentGroup extends AbstractTable<BaseDepartmentGroup>
{

	public DepartmentGroup() throws java.sql.SQLException 
	{

		initColumnNames();
	}

	protected final void initColumnNames()  throws java.sql.SQLException {

		__columnCount          = 3;

		initTables();

		__tableName            = "department_groups";

		__key_columns          = new int[1];
		__key_columns[0] = 0;

		__column_names[0] = BaseDepartmentGroup.CS_DEPARTMENT_GROUP_ID;
		__column_names[1] = BaseDepartmentGroup.CS_GROUP_NAME;
		__column_names[2] = BaseDepartmentGroup.CS_IS_COMPANY;

		resetSelectFlags() ;
		resetInsertFlags() ;
	}

	public void resetInsertFlags(){
		for(int i = 0; i < __columnCount; i ++ ) __insert_flags[i] = true;
		__insert_flags[0] = false;
		__serial_column = 0;
	}

	public void setPrimaryKeyFromBase(BaseDepartmentGroup b) {
		clear();
		setDepartmentGroupIdClear(b.getDepartmentGroupId());
	}

	public boolean isPrimaryKeyNull() {
		return getDepartmentGroupId() == null;
	}

	@Override
	public BaseDepartmentGroup generateBase(){
		BaseDepartmentGroup b = new BaseDepartmentGroup();
		setDataToBase(b);
		return b;
	}

	@Override
	public void setDataToBase(BaseDepartmentGroup b){
		int count = 0; Object val;
		if((val = __current_data[count++]) != null) b.setDepartmentGroupId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setGroupName(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setIsCompany(GenericBase.__getBoolean(val));
	}

	@Override
	public void setBaseToBuffer(BaseDepartmentGroup b, Object[] buff){
		int count = 0;
		buff[count++] = b.getDepartmentGroupId();
		buff[count++] = b.getGroupName();
		buff[count++] = b.getIsCompany();
	}

	@Override
	public void setDataFromBase(BaseDepartmentGroup b){
		if(b.getDepartmentGroupId() != null) setDepartmentGroupIdClear(b.getDepartmentGroupId());
		if(b.getGroupName() != null) setGroupName(b.getGroupName());
		if(b.getIsCompany() != null) setIsCompany(b.getIsCompany());
	}

	@Override
	public BaseDepartmentGroup generateBaseFromResultSet(java.sql.ResultSet sdr)  throws java.sql.SQLException {

		BaseDepartmentGroup b = new BaseDepartmentGroup();
		setQueryResultToBase(b, sdr);
		return b;
	}

	@Override
	public void setQueryResultToBase(BaseDepartmentGroup __base, java.sql.ResultSet sdr ) throws java.sql.SQLException {
		int count = 1, index = 0;
		java.sql.ResultSetMetaData rsmd = sdr.getMetaData();
		int columnCount = rsmd.getColumnCount();
		Object val;
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setDepartmentGroupId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setGroupName(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setIsCompany(GenericBase.__getBoolean(val));
	}

	public void setDepartmentGroupId(java.lang.Integer val) {
		setCurrentData(0, val);
	}

	public java.lang.Integer getDepartmentGroupId() {
		return GenericBase.__getInt(__current_data[0]);
	}

	public void setDepartmentGroupIdClear(java.lang.Integer val) {
		__backup_data[0] = __current_data[0] =  val;
		__modified_flags[0] = false;
	}

	public void setGroupName(java.lang.String val) {
		setCurrentData(1, val);
	}

	public java.lang.String getGroupName() {
		return GenericBase.__getString(__current_data[1]);
	}

	public void setIsCompany(java.lang.Boolean val) {
		setCurrentData(2, val);
	}

	public java.lang.Boolean getIsCompany() {
		return GenericBase.__getBoolean(__current_data[2]);
	}

	public void setConditionDepartmentGroupId(String op, java.lang.Integer val) {
		setConditionDepartmentGroupId(op, val, CONDITION_AND);
	}

	public void setConditionDepartmentGroupId(String op, java.lang.Integer val, String relation) {
		addCondition(0, op, relation, val);
	}

	public void setSelectDepartmentGroupId(boolean val) {
		__select_flags[0] = val;
	}

	public void setConditionGroupName(String op, java.lang.String val) {
		setConditionGroupName(op, val, CONDITION_AND);
	}

	public void setConditionGroupName(String op, java.lang.String val, String relation) {
		addCondition(1, op, relation, val);
	}

	public void setSelectGroupName(boolean val) {
		__select_flags[1] = val;
	}

	public void setConditionIsCompany(String op, java.lang.Boolean val) {
		setConditionIsCompany(op, val, CONDITION_AND);
	}

	public void setConditionIsCompany(String op, java.lang.Boolean val, String relation) {
		addCondition(2, op, relation, val);
	}

	public void setSelectIsCompany(boolean val) {
		__select_flags[2] = val;
	}


}

