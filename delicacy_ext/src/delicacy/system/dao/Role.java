package delicacy.system.dao;

import delicacy.common.AbstractTable;
import delicacy.common.GenericBase;
import delicacy.system.bean.BaseRole;


public class Role extends AbstractTable<BaseRole>
{

	public Role() throws java.sql.SQLException 
	{

		initColumnNames();
	}

	protected final void initColumnNames()  throws java.sql.SQLException {

		__columnCount          = 7;

		initTables();

		__tableName            = "roles";

		__key_columns          = new int[1];
		__key_columns[0] = 0;

		__column_names[0] = BaseRole.CS_ROLE_ID;
		__column_names[1] = BaseRole.CS_ROLE_NAME;
		__column_names[2] = BaseRole.CS_APPLICATION_ID;
		__column_names[3] = BaseRole.CS_ROLE_TYPE;
		__column_names[4] = BaseRole.CS_ENABLED;
		__column_names[5] = BaseRole.CS_HAS_APPROVAL_RIGHT;
		__column_names[6] = BaseRole.CS_IS_STANDARD;

		resetSelectFlags() ;
		resetInsertFlags() ;
	}

	public void resetInsertFlags(){
		for(int i = 0; i < __columnCount; i ++ ) __insert_flags[i] = true;
		__insert_flags[0] = false;
		__serial_column = 0;
	}

	public void setPrimaryKeyFromBase(BaseRole b) {
		clear();
		setRoleIdClear(b.getRoleId());
	}

	public boolean isPrimaryKeyNull() {
		return getRoleId() == null;
	}

	@Override
	public BaseRole generateBase(){
		BaseRole b = new BaseRole();
		setDataToBase(b);
		return b;
	}

	@Override
	public void setDataToBase(BaseRole b){
		int count = 0; Object val;
		if((val = __current_data[count++]) != null) b.setRoleId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setRoleName(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setApplicationId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setRoleType(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setEnabled(GenericBase.__getBoolean(val));
		if((val = __current_data[count++]) != null) b.setHasApprovalRight(GenericBase.__getBoolean(val));
		if((val = __current_data[count++]) != null) b.setIsStandard(GenericBase.__getBoolean(val));
	}

	@Override
	public void setBaseToBuffer(BaseRole b, Object[] buff){
		int count = 0;
		buff[count++] = b.getRoleId();
		buff[count++] = b.getRoleName();
		buff[count++] = b.getApplicationId();
		buff[count++] = b.getRoleType();
		buff[count++] = b.getEnabled();
		buff[count++] = b.getHasApprovalRight();
		buff[count++] = b.getIsStandard();
	}

	@Override
	public void setDataFromBase(BaseRole b){
		if(b.getRoleId() != null) setRoleIdClear(b.getRoleId());
		if(b.getRoleName() != null) setRoleName(b.getRoleName());
		if(b.getApplicationId() != null) setApplicationId(b.getApplicationId());
		if(b.getRoleType() != null) setRoleType(b.getRoleType());
		if(b.getEnabled() != null) setEnabled(b.getEnabled());
		if(b.getHasApprovalRight() != null) setHasApprovalRight(b.getHasApprovalRight());
		if(b.getIsStandard() != null) setIsStandard(b.getIsStandard());
	}

	@Override
	public BaseRole generateBaseFromResultSet(java.sql.ResultSet sdr)  throws java.sql.SQLException {

		BaseRole b = new BaseRole();
		setQueryResultToBase(b, sdr);
		return b;
	}

	@Override
	public void setQueryResultToBase(BaseRole __base, java.sql.ResultSet sdr ) throws java.sql.SQLException {
		int count = 1, index = 0;
		java.sql.ResultSetMetaData rsmd = sdr.getMetaData();
		int columnCount = rsmd.getColumnCount();
		Object val;
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setRoleId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setRoleName(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setApplicationId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setRoleType(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setEnabled(GenericBase.__getBoolean(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setHasApprovalRight(GenericBase.__getBoolean(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setIsStandard(GenericBase.__getBoolean(val));
	}

	public void setRoleId(java.lang.Integer val) {
		setCurrentData(0, val);
	}

	public java.lang.Integer getRoleId() {
		return GenericBase.__getInt(__current_data[0]);
	}

	public void setRoleIdClear(java.lang.Integer val) {
		__backup_data[0] = __current_data[0] =  val;
		__modified_flags[0] = false;
	}

	public void setRoleName(java.lang.String val) {
		setCurrentData(1, val);
	}

	public java.lang.String getRoleName() {
		return GenericBase.__getString(__current_data[1]);
	}

	public void setApplicationId(java.lang.Integer val) {
		setCurrentData(2, val);
	}

	public java.lang.Integer getApplicationId() {
		return GenericBase.__getInt(__current_data[2]);
	}

	public void setRoleType(java.lang.Integer val) {
		setCurrentData(3, val);
	}

	public java.lang.Integer getRoleType() {
		return GenericBase.__getInt(__current_data[3]);
	}

	public void setEnabled(java.lang.Boolean val) {
		setCurrentData(4, val);
	}

	public java.lang.Boolean getEnabled() {
		return GenericBase.__getBoolean(__current_data[4]);
	}

	public void setHasApprovalRight(java.lang.Boolean val) {
		setCurrentData(5, val);
	}

	public java.lang.Boolean getHasApprovalRight() {
		return GenericBase.__getBoolean(__current_data[5]);
	}

	public void setIsStandard(java.lang.Boolean val) {
		setCurrentData(6, val);
	}

	public java.lang.Boolean getIsStandard() {
		return GenericBase.__getBoolean(__current_data[6]);
	}

	public void setConditionRoleId(String op, java.lang.Integer val) {
		setConditionRoleId(op, val, CONDITION_AND);
	}

	public void setConditionRoleId(String op, java.lang.Integer val, String relation) {
		addCondition(0, op, relation, val);
	}

	public void setSelectRoleId(boolean val) {
		__select_flags[0] = val;
	}

	public void setConditionRoleName(String op, java.lang.String val) {
		setConditionRoleName(op, val, CONDITION_AND);
	}

	public void setConditionRoleName(String op, java.lang.String val, String relation) {
		addCondition(1, op, relation, val);
	}

	public void setSelectRoleName(boolean val) {
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

	public void setConditionRoleType(String op, java.lang.Integer val) {
		setConditionRoleType(op, val, CONDITION_AND);
	}

	public void setConditionRoleType(String op, java.lang.Integer val, String relation) {
		addCondition(3, op, relation, val);
	}

	public void setSelectRoleType(boolean val) {
		__select_flags[3] = val;
	}

	public void setConditionEnabled(String op, java.lang.Boolean val) {
		setConditionEnabled(op, val, CONDITION_AND);
	}

	public void setConditionEnabled(String op, java.lang.Boolean val, String relation) {
		addCondition(4, op, relation, val);
	}

	public void setSelectEnabled(boolean val) {
		__select_flags[4] = val;
	}

	public void setConditionHasApprovalRight(String op, java.lang.Boolean val) {
		setConditionHasApprovalRight(op, val, CONDITION_AND);
	}

	public void setConditionHasApprovalRight(String op, java.lang.Boolean val, String relation) {
		addCondition(5, op, relation, val);
	}

	public void setSelectHasApprovalRight(boolean val) {
		__select_flags[5] = val;
	}

	public void setConditionIsStandard(String op, java.lang.Boolean val) {
		setConditionIsStandard(op, val, CONDITION_AND);
	}

	public void setConditionIsStandard(String op, java.lang.Boolean val, String relation) {
		addCondition(6, op, relation, val);
	}

	public void setSelectIsStandard(boolean val) {
		__select_flags[6] = val;
	}


}

