package delicacy.system.dao;

import delicacy.common.AbstractTable;
import delicacy.common.GenericBase;
import delicacy.system.bean.BaseDepartment;


public class Department extends AbstractTable<BaseDepartment>
{

	public Department() throws java.sql.SQLException 
	{

		initColumnNames();
	}

	protected final void initColumnNames()  throws java.sql.SQLException {

		__columnCount          = 16;

		initTables();

		__tableName            = "departments";

		__key_columns          = new int[1];
		__key_columns[0] = 0;

		__column_names[0] = BaseDepartment.CS_DEPARTMENT_ID;
		__column_names[1] = BaseDepartment.CS_DEPARTMENT_NAME;
		__column_names[2] = BaseDepartment.CS_ABBREVIATION;
		__column_names[3] = BaseDepartment.CS_MANAGER_ID;
		__column_names[4] = BaseDepartment.CS_MANAGER_NAME;
		__column_names[5] = BaseDepartment.CS_PARENT_ID;
		__column_names[6] = BaseDepartment.CS_ENABLED;
		__column_names[7] = BaseDepartment.CS_ORIGINAL_ID;
		__column_names[8] = BaseDepartment.CS_PLATE_ID;
		__column_names[9] = BaseDepartment.CS_IS_HEADCOUNT;
		__column_names[10] = BaseDepartment.CS_DEPARTMENT_TYPE;
		__column_names[11] = BaseDepartment.CS_WEIXIN_DEPARTMENT_ID;
		__column_names[12] = BaseDepartment.CS_EMAIL_DEPARTMENT_ID;
		__column_names[13] = BaseDepartment.CS_ECMC_DEPARTMENT_ID;
		__column_names[14] = BaseDepartment.CS_DELETE_FLAG;
		__column_names[15] = BaseDepartment.CS_IS_ENABLE;

		resetSelectFlags() ;
		resetInsertFlags() ;
	}

	public void resetInsertFlags(){
		for(int i = 0; i < __columnCount; i ++ ) __insert_flags[i] = true;
		__insert_flags[0] = false;
		__serial_column = 0;
	}

	public void setPrimaryKeyFromBase(BaseDepartment b) {
		clear();
		setDepartmentIdClear(b.getDepartmentId());
	}

	public boolean isPrimaryKeyNull() {
		return getDepartmentId() == null;
	}

	@Override
	public BaseDepartment generateBase(){
		BaseDepartment b = new BaseDepartment();
		setDataToBase(b);
		return b;
	}

	@Override
	public void setDataToBase(BaseDepartment b){
		int count = 0; Object val;
		if((val = __current_data[count++]) != null) b.setDepartmentId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setDepartmentName(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setAbbreviation(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setManagerId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setManagerName(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setParentId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setEnabled(GenericBase.__getBoolean(val));
		if((val = __current_data[count++]) != null) b.setOriginalId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setPlateId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setIsHeadcount(GenericBase.__getBoolean(val));
		if((val = __current_data[count++]) != null) b.setDepartmentType(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setWeixinDepartmentId(GenericBase.__getLong(val));
		if((val = __current_data[count++]) != null) b.setEmailDepartmentId(GenericBase.__getLong(val));
		if((val = __current_data[count++]) != null) b.setEcmcDepartmentId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setDeleteFlag(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setIsEnable(GenericBase.__getBoolean(val));
	}

	@Override
	public void setBaseToBuffer(BaseDepartment b, Object[] buff){
		int count = 0;
		buff[count++] = b.getDepartmentId();
		buff[count++] = b.getDepartmentName();
		buff[count++] = b.getAbbreviation();
		buff[count++] = b.getManagerId();
		buff[count++] = b.getManagerName();
		buff[count++] = b.getParentId();
		buff[count++] = b.getEnabled();
		buff[count++] = b.getOriginalId();
		buff[count++] = b.getPlateId();
		buff[count++] = b.getIsHeadcount();
		buff[count++] = b.getDepartmentType();
		buff[count++] = b.getWeixinDepartmentId();
		buff[count++] = b.getEmailDepartmentId();
		buff[count++] = b.getEcmcDepartmentId();
		buff[count++] = b.getDeleteFlag();
		buff[count++] = b.getIsEnable();
	}

	@Override
	public void setDataFromBase(BaseDepartment b){
		if(b.getDepartmentId() != null) setDepartmentIdClear(b.getDepartmentId());
		if(b.getDepartmentName() != null) setDepartmentName(b.getDepartmentName());
		if(b.getAbbreviation() != null) setAbbreviation(b.getAbbreviation());
		if(b.getManagerId() != null) setManagerId(b.getManagerId());
		if(b.getManagerName() != null) setManagerName(b.getManagerName());
		if(b.getParentId() != null) setParentId(b.getParentId());
		if(b.getEnabled() != null) setEnabled(b.getEnabled());
		if(b.getOriginalId() != null) setOriginalId(b.getOriginalId());
		if(b.getPlateId() != null) setPlateId(b.getPlateId());
		if(b.getIsHeadcount() != null) setIsHeadcount(b.getIsHeadcount());
		if(b.getDepartmentType() != null) setDepartmentType(b.getDepartmentType());
		if(b.getWeixinDepartmentId() != null) setWeixinDepartmentId(b.getWeixinDepartmentId());
		if(b.getEmailDepartmentId() != null) setEmailDepartmentId(b.getEmailDepartmentId());
		if(b.getEcmcDepartmentId() != null) setEcmcDepartmentId(b.getEcmcDepartmentId());
		if(b.getDeleteFlag() != null) setDeleteFlag(b.getDeleteFlag());
		if(b.getIsEnable() != null) setIsEnable(b.getIsEnable());
	}

	@Override
	public BaseDepartment generateBaseFromResultSet(java.sql.ResultSet sdr)  throws java.sql.SQLException {

		BaseDepartment b = new BaseDepartment();
		setQueryResultToBase(b, sdr);
		return b;
	}

	@Override
	public void setQueryResultToBase(BaseDepartment __base, java.sql.ResultSet sdr ) throws java.sql.SQLException {
		int count = 1, index = 0;
		java.sql.ResultSetMetaData rsmd = sdr.getMetaData();
		int columnCount = rsmd.getColumnCount();
		Object val;
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setDepartmentId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setDepartmentName(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setAbbreviation(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setManagerId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setManagerName(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setParentId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setEnabled(GenericBase.__getBoolean(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setOriginalId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setPlateId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setIsHeadcount(GenericBase.__getBoolean(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setDepartmentType(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setWeixinDepartmentId(GenericBase.__getLong(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setEmailDepartmentId(GenericBase.__getLong(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setEcmcDepartmentId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setDeleteFlag(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setIsEnable(GenericBase.__getBoolean(val));
	}

	public void setDepartmentId(java.lang.Integer val) {
		setCurrentData(0, val);
	}

	public java.lang.Integer getDepartmentId() {
		return GenericBase.__getInt(__current_data[0]);
	}

	public void setDepartmentIdClear(java.lang.Integer val) {
		__backup_data[0] = __current_data[0] =  val;
		__modified_flags[0] = false;
	}

	public void setDepartmentName(java.lang.String val) {
		setCurrentData(1, val);
	}

	public java.lang.String getDepartmentName() {
		return GenericBase.__getString(__current_data[1]);
	}

	public void setAbbreviation(java.lang.String val) {
		setCurrentData(2, val);
	}

	public java.lang.String getAbbreviation() {
		return GenericBase.__getString(__current_data[2]);
	}

	public void setManagerId(java.lang.Integer val) {
		setCurrentData(3, val);
	}

	public java.lang.Integer getManagerId() {
		return GenericBase.__getInt(__current_data[3]);
	}

	public void setManagerName(java.lang.String val) {
		setCurrentData(4, val);
	}

	public java.lang.String getManagerName() {
		return GenericBase.__getString(__current_data[4]);
	}

	public void setParentId(java.lang.Integer val) {
		setCurrentData(5, val);
	}

	public java.lang.Integer getParentId() {
		return GenericBase.__getInt(__current_data[5]);
	}

	public void setEnabled(java.lang.Boolean val) {
		setCurrentData(6, val);
	}

	public java.lang.Boolean getEnabled() {
		return GenericBase.__getBoolean(__current_data[6]);
	}

	public void setOriginalId(java.lang.Integer val) {
		setCurrentData(7, val);
	}

	public java.lang.Integer getOriginalId() {
		return GenericBase.__getInt(__current_data[7]);
	}

	public void setPlateId(java.lang.Integer val) {
		setCurrentData(8, val);
	}

	public java.lang.Integer getPlateId() {
		return GenericBase.__getInt(__current_data[8]);
	}

	public void setIsHeadcount(java.lang.Boolean val) {
		setCurrentData(9, val);
	}

	public java.lang.Boolean getIsHeadcount() {
		return GenericBase.__getBoolean(__current_data[9]);
	}

	public void setDepartmentType(java.lang.Integer val) {
		setCurrentData(10, val);
	}

	public java.lang.Integer getDepartmentType() {
		return GenericBase.__getInt(__current_data[10]);
	}

	public void setWeixinDepartmentId(java.lang.Long val) {
		setCurrentData(11, val);
	}

	public java.lang.Long getWeixinDepartmentId() {
		return GenericBase.__getLong(__current_data[11]);
	}

	public void setEmailDepartmentId(java.lang.Long val) {
		setCurrentData(12, val);
	}

	public java.lang.Long getEmailDepartmentId() {
		return GenericBase.__getLong(__current_data[12]);
	}

	public void setEcmcDepartmentId(java.lang.Integer val) {
		setCurrentData(13, val);
	}

	public java.lang.Integer getEcmcDepartmentId() {
		return GenericBase.__getInt(__current_data[13]);
	}

	public void setDeleteFlag(java.lang.Integer val) {
		setCurrentData(14, val);
	}

	public java.lang.Integer getDeleteFlag() {
		return GenericBase.__getInt(__current_data[14]);
	}

	public void setIsEnable(java.lang.Boolean val) {
		setCurrentData(15, val);
	}

	public java.lang.Boolean getIsEnable() {
		return GenericBase.__getBoolean(__current_data[15]);
	}

	public void setConditionDepartmentId(String op, java.lang.Integer val) {
		setConditionDepartmentId(op, val, CONDITION_AND);
	}

	public void setConditionDepartmentId(String op, java.lang.Integer val, String relation) {
		addCondition(0, op, relation, val);
	}

	public void setSelectDepartmentId(boolean val) {
		__select_flags[0] = val;
	}

	public void setConditionDepartmentName(String op, java.lang.String val) {
		setConditionDepartmentName(op, val, CONDITION_AND);
	}

	public void setConditionDepartmentName(String op, java.lang.String val, String relation) {
		addCondition(1, op, relation, val);
	}

	public void setSelectDepartmentName(boolean val) {
		__select_flags[1] = val;
	}

	public void setConditionAbbreviation(String op, java.lang.String val) {
		setConditionAbbreviation(op, val, CONDITION_AND);
	}

	public void setConditionAbbreviation(String op, java.lang.String val, String relation) {
		addCondition(2, op, relation, val);
	}

	public void setSelectAbbreviation(boolean val) {
		__select_flags[2] = val;
	}

	public void setConditionManagerId(String op, java.lang.Integer val) {
		setConditionManagerId(op, val, CONDITION_AND);
	}

	public void setConditionManagerId(String op, java.lang.Integer val, String relation) {
		addCondition(3, op, relation, val);
	}

	public void setSelectManagerId(boolean val) {
		__select_flags[3] = val;
	}

	public void setConditionManagerName(String op, java.lang.String val) {
		setConditionManagerName(op, val, CONDITION_AND);
	}

	public void setConditionManagerName(String op, java.lang.String val, String relation) {
		addCondition(4, op, relation, val);
	}

	public void setSelectManagerName(boolean val) {
		__select_flags[4] = val;
	}

	public void setConditionParentId(String op, java.lang.Integer val) {
		setConditionParentId(op, val, CONDITION_AND);
	}

	public void setConditionParentId(String op, java.lang.Integer val, String relation) {
		addCondition(5, op, relation, val);
	}

	public void setSelectParentId(boolean val) {
		__select_flags[5] = val;
	}

	public void setConditionEnabled(String op, java.lang.Boolean val) {
		setConditionEnabled(op, val, CONDITION_AND);
	}

	public void setConditionEnabled(String op, java.lang.Boolean val, String relation) {
		addCondition(6, op, relation, val);
	}

	public void setSelectEnabled(boolean val) {
		__select_flags[6] = val;
	}

	public void setConditionOriginalId(String op, java.lang.Integer val) {
		setConditionOriginalId(op, val, CONDITION_AND);
	}

	public void setConditionOriginalId(String op, java.lang.Integer val, String relation) {
		addCondition(7, op, relation, val);
	}

	public void setSelectOriginalId(boolean val) {
		__select_flags[7] = val;
	}

	public void setConditionPlateId(String op, java.lang.Integer val) {
		setConditionPlateId(op, val, CONDITION_AND);
	}

	public void setConditionPlateId(String op, java.lang.Integer val, String relation) {
		addCondition(8, op, relation, val);
	}

	public void setSelectPlateId(boolean val) {
		__select_flags[8] = val;
	}

	public void setConditionIsHeadcount(String op, java.lang.Boolean val) {
		setConditionIsHeadcount(op, val, CONDITION_AND);
	}

	public void setConditionIsHeadcount(String op, java.lang.Boolean val, String relation) {
		addCondition(9, op, relation, val);
	}

	public void setSelectIsHeadcount(boolean val) {
		__select_flags[9] = val;
	}

	public void setConditionDepartmentType(String op, java.lang.Integer val) {
		setConditionDepartmentType(op, val, CONDITION_AND);
	}

	public void setConditionDepartmentType(String op, java.lang.Integer val, String relation) {
		addCondition(10, op, relation, val);
	}

	public void setSelectDepartmentType(boolean val) {
		__select_flags[10] = val;
	}

	public void setConditionWeixinDepartmentId(String op, java.lang.Long val) {
		setConditionWeixinDepartmentId(op, val, CONDITION_AND);
	}

	public void setConditionWeixinDepartmentId(String op, java.lang.Long val, String relation) {
		addCondition(11, op, relation, val);
	}

	public void setSelectWeixinDepartmentId(boolean val) {
		__select_flags[11] = val;
	}

	public void setConditionEmailDepartmentId(String op, java.lang.Long val) {
		setConditionEmailDepartmentId(op, val, CONDITION_AND);
	}

	public void setConditionEmailDepartmentId(String op, java.lang.Long val, String relation) {
		addCondition(12, op, relation, val);
	}

	public void setSelectEmailDepartmentId(boolean val) {
		__select_flags[12] = val;
	}

	public void setConditionEcmcDepartmentId(String op, java.lang.Integer val) {
		setConditionEcmcDepartmentId(op, val, CONDITION_AND);
	}

	public void setConditionEcmcDepartmentId(String op, java.lang.Integer val, String relation) {
		addCondition(13, op, relation, val);
	}

	public void setSelectEcmcDepartmentId(boolean val) {
		__select_flags[13] = val;
	}

	public void setConditionDeleteFlag(String op, java.lang.Integer val) {
		setConditionDeleteFlag(op, val, CONDITION_AND);
	}

	public void setConditionDeleteFlag(String op, java.lang.Integer val, String relation) {
		addCondition(14, op, relation, val);
	}

	public void setSelectDeleteFlag(boolean val) {
		__select_flags[14] = val;
	}

	public void setConditionIsEnable(String op, java.lang.Boolean val) {
		setConditionIsEnable(op, val, CONDITION_AND);
	}

	public void setConditionIsEnable(String op, java.lang.Boolean val, String relation) {
		addCondition(15, op, relation, val);
	}

	public void setSelectIsEnable(boolean val) {
		__select_flags[15] = val;
	}


}

