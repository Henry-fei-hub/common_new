package delicacy.sys.dao;

import delicacy.common.AbstractTable;
import delicacy.common.GenericBase;
import delicacy.sys.bean.BaseWorkFlowPage;


public class WorkFlowPage extends AbstractTable<BaseWorkFlowPage>
{

	public WorkFlowPage() throws java.sql.SQLException 
	{

		initColumnNames();
	}

	protected final void initColumnNames()  throws java.sql.SQLException {

		__columnCount          = 4;

		initTables();

		__tableName            = "work_flow_pages";

		__key_columns          = new int[1];
		__key_columns[0] = 0;

		__column_names[0] = BaseWorkFlowPage.CS_WORK_FLOW_PAGE_ID;
		__column_names[1] = BaseWorkFlowPage.CS_EXECUTE_NAME;
		__column_names[2] = BaseWorkFlowPage.CS_PAGE_DIRECTORY;
		__column_names[3] = BaseWorkFlowPage.CS_ORGANIZATION_ID;

		resetSelectFlags() ;
		resetInsertFlags() ;
	}

	public void resetInsertFlags(){
		for(int i = 0; i < __columnCount; i ++ ) __insert_flags[i] = true;
		__insert_flags[0] = false;
		__serial_column = 0;
	}

	public void setPrimaryKeyFromBase(BaseWorkFlowPage b) {
		clear();
		setWorkFlowPageIdClear(b.getWorkFlowPageId());
	}

	public boolean isPrimaryKeyNull() {
		return getWorkFlowPageId() == null;
	}

	@Override
	public BaseWorkFlowPage generateBase(){
		BaseWorkFlowPage b = new BaseWorkFlowPage();
		setDataToBase(b);
		return b;
	}

	@Override
	public void setDataToBase(BaseWorkFlowPage b){
		int count = 0; Object val;
		if((val = __current_data[count++]) != null) b.setWorkFlowPageId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setExecuteName(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setPageDirectory(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setOrganizationId(GenericBase.__getInt(val));
	}

	@Override
	public void setBaseToBuffer(BaseWorkFlowPage b, Object[] buff){
		int count = 0;
		buff[count++] = b.getWorkFlowPageId();
		buff[count++] = b.getExecuteName();
		buff[count++] = b.getPageDirectory();
		buff[count++] = b.getOrganizationId();
	}

	@Override
	public void setDataFromBase(BaseWorkFlowPage b){
		if(b.getWorkFlowPageId() != null) setWorkFlowPageIdClear(b.getWorkFlowPageId());
		if(b.getExecuteName() != null) setExecuteName(b.getExecuteName());
		if(b.getPageDirectory() != null) setPageDirectory(b.getPageDirectory());
		if(b.getOrganizationId() != null) setOrganizationId(b.getOrganizationId());
	}

	@Override
	public BaseWorkFlowPage generateBaseFromResultSet(java.sql.ResultSet sdr)  throws java.sql.SQLException {

		BaseWorkFlowPage b = new BaseWorkFlowPage();
		setQueryResultToBase(b, sdr);
		return b;
	}

	@Override
	public void setQueryResultToBase(BaseWorkFlowPage __base, java.sql.ResultSet sdr ) throws java.sql.SQLException {
		int count = 1, index = 0;
		java.sql.ResultSetMetaData rsmd = sdr.getMetaData();
		int columnCount = rsmd.getColumnCount();
		Object val;
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setWorkFlowPageId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setExecuteName(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setPageDirectory(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setOrganizationId(GenericBase.__getInt(val));
	}

	public void setWorkFlowPageId(java.lang.Integer val) {
		setCurrentData(0, val);
	}

	public java.lang.Integer getWorkFlowPageId() {
		return GenericBase.__getInt(__current_data[0]);
	}

	public void setWorkFlowPageIdClear(java.lang.Integer val) {
		__backup_data[0] = __current_data[0] =  val;
		__modified_flags[0] = false;
	}

	public void setExecuteName(java.lang.String val) {
		setCurrentData(1, val);
	}

	public java.lang.String getExecuteName() {
		return GenericBase.__getString(__current_data[1]);
	}

	public void setPageDirectory(java.lang.String val) {
		setCurrentData(2, val);
	}

	public java.lang.String getPageDirectory() {
		return GenericBase.__getString(__current_data[2]);
	}

	public void setOrganizationId(java.lang.Integer val) {
		setCurrentData(3, val);
	}

	public java.lang.Integer getOrganizationId() {
		return GenericBase.__getInt(__current_data[3]);
	}

	public void setConditionWorkFlowPageId(String op, java.lang.Integer val) {
		setConditionWorkFlowPageId(op, val, CONDITION_AND);
	}

	public void setConditionWorkFlowPageId(String op, java.lang.Integer val, String relation) {
		addCondition(0, op, relation, val);
	}

	public void setSelectWorkFlowPageId(boolean val) {
		__select_flags[0] = val;
	}

	public void setConditionExecuteName(String op, java.lang.String val) {
		setConditionExecuteName(op, val, CONDITION_AND);
	}

	public void setConditionExecuteName(String op, java.lang.String val, String relation) {
		addCondition(1, op, relation, val);
	}

	public void setSelectExecuteName(boolean val) {
		__select_flags[1] = val;
	}

	public void setConditionPageDirectory(String op, java.lang.String val) {
		setConditionPageDirectory(op, val, CONDITION_AND);
	}

	public void setConditionPageDirectory(String op, java.lang.String val, String relation) {
		addCondition(2, op, relation, val);
	}

	public void setSelectPageDirectory(boolean val) {
		__select_flags[2] = val;
	}

	public void setConditionOrganizationId(String op, java.lang.Integer val) {
		setConditionOrganizationId(op, val, CONDITION_AND);
	}

	public void setConditionOrganizationId(String op, java.lang.Integer val, String relation) {
		addCondition(3, op, relation, val);
	}

	public void setSelectOrganizationId(boolean val) {
		__select_flags[3] = val;
	}


}

