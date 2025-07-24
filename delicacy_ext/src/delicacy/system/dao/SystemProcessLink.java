package delicacy.system.dao;

import delicacy.common.AbstractTable;
import delicacy.common.GenericBase;
import delicacy.system.bean.BaseSystemProcessLink;


public class SystemProcessLink extends AbstractTable<BaseSystemProcessLink>
{

	public SystemProcessLink() throws java.sql.SQLException 
	{

		initColumnNames();
	}

	protected final void initColumnNames()  throws java.sql.SQLException {

		__columnCount          = 5;

		initTables();

		__tableName            = "system_process_links";

		__key_columns          = new int[1];
		__key_columns[0] = 0;

		__column_names[0] = BaseSystemProcessLink.CS_PROCESS_LINK_ID;
		__column_names[1] = BaseSystemProcessLink.CS_PROCESS_ID;
		__column_names[2] = BaseSystemProcessLink.CS_CONDITION;
		__column_names[3] = BaseSystemProcessLink.CS_PROCESS_ACTIVITY_ID;
		__column_names[4] = BaseSystemProcessLink.CS_TO_PROCESS_ACTIVITY_ID;

		resetSelectFlags() ;
		resetInsertFlags() ;
	}

	public void resetInsertFlags(){
		for(int i = 0; i < __columnCount; i ++ ) __insert_flags[i] = true;
		__insert_flags[0] = false;
		__serial_column = 0;
	}

	public void setPrimaryKeyFromBase(BaseSystemProcessLink b) {
		clear();
		setProcessLinkIdClear(b.getProcessLinkId());
	}

	public boolean isPrimaryKeyNull() {
		return getProcessLinkId() == null;
	}

	@Override
	public BaseSystemProcessLink generateBase(){
		BaseSystemProcessLink b = new BaseSystemProcessLink();
		setDataToBase(b);
		return b;
	}

	@Override
	public void setDataToBase(BaseSystemProcessLink b){
		int count = 0; Object val;
		if((val = __current_data[count++]) != null) b.setProcessLinkId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setProcessId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setCondition(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setProcessActivityId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setToProcessActivityId(GenericBase.__getInt(val));
	}

	@Override
	public void setBaseToBuffer(BaseSystemProcessLink b, Object[] buff){
		int count = 0;
		buff[count++] = b.getProcessLinkId();
		buff[count++] = b.getProcessId();
		buff[count++] = b.getCondition();
		buff[count++] = b.getProcessActivityId();
		buff[count++] = b.getToProcessActivityId();
	}

	@Override
	public void setDataFromBase(BaseSystemProcessLink b){
		if(b.getProcessLinkId() != null) setProcessLinkIdClear(b.getProcessLinkId());
		if(b.getProcessId() != null) setProcessId(b.getProcessId());
		if(b.getCondition() != null) setCondition(b.getCondition());
		if(b.getProcessActivityId() != null) setProcessActivityId(b.getProcessActivityId());
		if(b.getToProcessActivityId() != null) setToProcessActivityId(b.getToProcessActivityId());
	}

	@Override
	public BaseSystemProcessLink generateBaseFromResultSet(java.sql.ResultSet sdr)  throws java.sql.SQLException {

		BaseSystemProcessLink b = new BaseSystemProcessLink();
		setQueryResultToBase(b, sdr);
		return b;
	}

	@Override
	public void setQueryResultToBase(BaseSystemProcessLink __base, java.sql.ResultSet sdr ) throws java.sql.SQLException {
		int count = 1, index = 0;
		java.sql.ResultSetMetaData rsmd = sdr.getMetaData();
		int columnCount = rsmd.getColumnCount();
		Object val;
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setProcessLinkId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setProcessId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setCondition(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setProcessActivityId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setToProcessActivityId(GenericBase.__getInt(val));
	}

	public void setProcessLinkId(java.lang.Integer val) {
		setCurrentData(0, val);
	}

	public java.lang.Integer getProcessLinkId() {
		return GenericBase.__getInt(__current_data[0]);
	}

	public void setProcessLinkIdClear(java.lang.Integer val) {
		__backup_data[0] = __current_data[0] =  val;
		__modified_flags[0] = false;
	}

	public void setProcessId(java.lang.Integer val) {
		setCurrentData(1, val);
	}

	public java.lang.Integer getProcessId() {
		return GenericBase.__getInt(__current_data[1]);
	}

	public void setCondition(java.lang.String val) {
		setCurrentData(2, val);
	}

	public java.lang.String getCondition() {
		return GenericBase.__getString(__current_data[2]);
	}

	public void setProcessActivityId(java.lang.Integer val) {
		setCurrentData(3, val);
	}

	public java.lang.Integer getProcessActivityId() {
		return GenericBase.__getInt(__current_data[3]);
	}

	public void setToProcessActivityId(java.lang.Integer val) {
		setCurrentData(4, val);
	}

	public java.lang.Integer getToProcessActivityId() {
		return GenericBase.__getInt(__current_data[4]);
	}

	public void setConditionProcessLinkId(String op, java.lang.Integer val) {
		setConditionProcessLinkId(op, val, CONDITION_AND);
	}

	public void setConditionProcessLinkId(String op, java.lang.Integer val, String relation) {
		addCondition(0, op, relation, val);
	}

	public void setSelectProcessLinkId(boolean val) {
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

	public void setConditionCondition(String op, java.lang.String val) {
		setConditionCondition(op, val, CONDITION_AND);
	}

	public void setConditionCondition(String op, java.lang.String val, String relation) {
		addCondition(2, op, relation, val);
	}

	public void setSelectCondition(boolean val) {
		__select_flags[2] = val;
	}

	public void setConditionProcessActivityId(String op, java.lang.Integer val) {
		setConditionProcessActivityId(op, val, CONDITION_AND);
	}

	public void setConditionProcessActivityId(String op, java.lang.Integer val, String relation) {
		addCondition(3, op, relation, val);
	}

	public void setSelectProcessActivityId(boolean val) {
		__select_flags[3] = val;
	}

	public void setConditionToProcessActivityId(String op, java.lang.Integer val) {
		setConditionToProcessActivityId(op, val, CONDITION_AND);
	}

	public void setConditionToProcessActivityId(String op, java.lang.Integer val, String relation) {
		addCondition(4, op, relation, val);
	}

	public void setSelectToProcessActivityId(boolean val) {
		__select_flags[4] = val;
	}


}

