package delicacy.system.dao;

import delicacy.common.AbstractTable;
import delicacy.common.GenericBase;
import delicacy.system.bean.BaseMainFunction;


public class MainFunction extends AbstractTable<BaseMainFunction>
{

	public MainFunction() throws java.sql.SQLException 
	{

		initColumnNames();
	}

	protected final void initColumnNames()  throws java.sql.SQLException {

		__columnCount          = 19;

		initTables();

		__tableName            = "main_functions";

		__key_columns          = new int[1];
		__key_columns[0] = 0;

		__column_names[0] = BaseMainFunction.CS_FUNCTION_ID;
		__column_names[1] = BaseMainFunction.CS_FUNCTION_NAME;
		__column_names[2] = BaseMainFunction.CS_PARENT_ID;
		__column_names[3] = BaseMainFunction.CS_FUNCTION_CODE;
		__column_names[4] = BaseMainFunction.CS_APPLICATION_ID;
		__column_names[5] = BaseMainFunction.CS_FUNCTION_TYPE;
		__column_names[6] = BaseMainFunction.CS_PRIVILEGE_TYPE;
		__column_names[7] = BaseMainFunction.CS_ENABLED;
		__column_names[8] = BaseMainFunction.CS_LEAF;
		__column_names[9] = BaseMainFunction.CS_IS_MOBILE;
		__column_names[10] = BaseMainFunction.CS_ICON;
		__column_names[11] = BaseMainFunction.CS_STYLE_NAME;
		__column_names[12] = BaseMainFunction.CS_CLASS_NAME;
		__column_names[13] = BaseMainFunction.CS_ICON_COLOR;
		__column_names[14] = BaseMainFunction.CS_MARGIN_TOP;
		__column_names[15] = BaseMainFunction.CS_MARGIN_BOTTOM;
		__column_names[16] = BaseMainFunction.CS_EXECUTE_NAME;
		__column_names[17] = BaseMainFunction.CS_REMARK;
		__column_names[18] = BaseMainFunction.CS_SEQ_NO;

		resetSelectFlags() ;
		resetInsertFlags() ;
	}

	public void resetInsertFlags(){
		for(int i = 0; i < __columnCount; i ++ ) __insert_flags[i] = true;
		__insert_flags[0] = false;
		__serial_column = 0;
	}

	public void setPrimaryKeyFromBase(BaseMainFunction b) {
		clear();
		setFunctionIdClear(b.getFunctionId());
	}

	public boolean isPrimaryKeyNull() {
		return getFunctionId() == null;
	}

	@Override
	public BaseMainFunction generateBase(){
		BaseMainFunction b = new BaseMainFunction();
		setDataToBase(b);
		return b;
	}

	@Override
	public void setDataToBase(BaseMainFunction b){
		int count = 0; Object val;
		if((val = __current_data[count++]) != null) b.setFunctionId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setFunctionName(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setParentId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setFunctionCode(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setApplicationId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setFunctionType(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setPrivilegeType(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setEnabled(GenericBase.__getBoolean(val));
		if((val = __current_data[count++]) != null) b.setLeaf(GenericBase.__getBoolean(val));
		if((val = __current_data[count++]) != null) b.setIsMobile(GenericBase.__getBoolean(val));
		if((val = __current_data[count++]) != null) b.setIcon(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setStyleName(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setClassName(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setIconColor(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setMarginTop(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setMarginBottom(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setExecuteName(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setRemark(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setSeqNo(GenericBase.__getInt(val));
	}

	@Override
	public void setBaseToBuffer(BaseMainFunction b, Object[] buff){
		int count = 0;
		buff[count++] = b.getFunctionId();
		buff[count++] = b.getFunctionName();
		buff[count++] = b.getParentId();
		buff[count++] = b.getFunctionCode();
		buff[count++] = b.getApplicationId();
		buff[count++] = b.getFunctionType();
		buff[count++] = b.getPrivilegeType();
		buff[count++] = b.getEnabled();
		buff[count++] = b.getLeaf();
		buff[count++] = b.getIsMobile();
		buff[count++] = b.getIcon();
		buff[count++] = b.getStyleName();
		buff[count++] = b.getClassName();
		buff[count++] = b.getIconColor();
		buff[count++] = b.getMarginTop();
		buff[count++] = b.getMarginBottom();
		buff[count++] = b.getExecuteName();
		buff[count++] = b.getRemark();
		buff[count++] = b.getSeqNo();
	}

	@Override
	public void setDataFromBase(BaseMainFunction b){
		if(b.getFunctionId() != null) setFunctionIdClear(b.getFunctionId());
		if(b.getFunctionName() != null) setFunctionName(b.getFunctionName());
		if(b.getParentId() != null) setParentId(b.getParentId());
		if(b.getFunctionCode() != null) setFunctionCode(b.getFunctionCode());
		if(b.getApplicationId() != null) setApplicationId(b.getApplicationId());
		if(b.getFunctionType() != null) setFunctionType(b.getFunctionType());
		if(b.getPrivilegeType() != null) setPrivilegeType(b.getPrivilegeType());
		if(b.getEnabled() != null) setEnabled(b.getEnabled());
		if(b.getLeaf() != null) setLeaf(b.getLeaf());
		if(b.getIsMobile() != null) setIsMobile(b.getIsMobile());
		if(b.getIcon() != null) setIcon(b.getIcon());
		if(b.getStyleName() != null) setStyleName(b.getStyleName());
		if(b.getClassName() != null) setClassName(b.getClassName());
		if(b.getIconColor() != null) setIconColor(b.getIconColor());
		if(b.getMarginTop() != null) setMarginTop(b.getMarginTop());
		if(b.getMarginBottom() != null) setMarginBottom(b.getMarginBottom());
		if(b.getExecuteName() != null) setExecuteName(b.getExecuteName());
		if(b.getRemark() != null) setRemark(b.getRemark());
		if(b.getSeqNo() != null) setSeqNo(b.getSeqNo());
	}

	@Override
	public BaseMainFunction generateBaseFromResultSet(java.sql.ResultSet sdr)  throws java.sql.SQLException {

		BaseMainFunction b = new BaseMainFunction();
		setQueryResultToBase(b, sdr);
		return b;
	}

	@Override
	public void setQueryResultToBase(BaseMainFunction __base, java.sql.ResultSet sdr ) throws java.sql.SQLException {
		int count = 1, index = 0;
		java.sql.ResultSetMetaData rsmd = sdr.getMetaData();
		int columnCount = rsmd.getColumnCount();
		Object val;
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setFunctionId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setFunctionName(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setParentId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setFunctionCode(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setApplicationId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setFunctionType(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setPrivilegeType(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setEnabled(GenericBase.__getBoolean(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setLeaf(GenericBase.__getBoolean(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setIsMobile(GenericBase.__getBoolean(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setIcon(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setStyleName(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setClassName(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setIconColor(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setMarginTop(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setMarginBottom(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setExecuteName(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setRemark(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setSeqNo(GenericBase.__getInt(val));
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

	public void setFunctionName(java.lang.String val) {
		setCurrentData(1, val);
	}

	public java.lang.String getFunctionName() {
		return GenericBase.__getString(__current_data[1]);
	}

	public void setParentId(java.lang.Integer val) {
		setCurrentData(2, val);
	}

	public java.lang.Integer getParentId() {
		return GenericBase.__getInt(__current_data[2]);
	}

	public void setFunctionCode(java.lang.String val) {
		setCurrentData(3, val);
	}

	public java.lang.String getFunctionCode() {
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

	public void setLeaf(java.lang.Boolean val) {
		setCurrentData(8, val);
	}

	public java.lang.Boolean getLeaf() {
		return GenericBase.__getBoolean(__current_data[8]);
	}

	public void setIsMobile(java.lang.Boolean val) {
		setCurrentData(9, val);
	}

	public java.lang.Boolean getIsMobile() {
		return GenericBase.__getBoolean(__current_data[9]);
	}

	public void setIcon(java.lang.String val) {
		setCurrentData(10, val);
	}

	public java.lang.String getIcon() {
		return GenericBase.__getString(__current_data[10]);
	}

	public void setStyleName(java.lang.String val) {
		setCurrentData(11, val);
	}

	public java.lang.String getStyleName() {
		return GenericBase.__getString(__current_data[11]);
	}

	public void setClassName(java.lang.String val) {
		setCurrentData(12, val);
	}

	public java.lang.String getClassName() {
		return GenericBase.__getString(__current_data[12]);
	}

	public void setIconColor(java.lang.String val) {
		setCurrentData(13, val);
	}

	public java.lang.String getIconColor() {
		return GenericBase.__getString(__current_data[13]);
	}

	public void setMarginTop(java.lang.Integer val) {
		setCurrentData(14, val);
	}

	public java.lang.Integer getMarginTop() {
		return GenericBase.__getInt(__current_data[14]);
	}

	public void setMarginBottom(java.lang.Integer val) {
		setCurrentData(15, val);
	}

	public java.lang.Integer getMarginBottom() {
		return GenericBase.__getInt(__current_data[15]);
	}

	public void setExecuteName(java.lang.String val) {
		setCurrentData(16, val);
	}

	public java.lang.String getExecuteName() {
		return GenericBase.__getString(__current_data[16]);
	}

	public void setRemark(java.lang.String val) {
		setCurrentData(17, val);
	}

	public java.lang.String getRemark() {
		return GenericBase.__getString(__current_data[17]);
	}

	public void setSeqNo(java.lang.Integer val) {
		setCurrentData(18, val);
	}

	public java.lang.Integer getSeqNo() {
		return GenericBase.__getInt(__current_data[18]);
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

	public void setConditionFunctionName(String op, java.lang.String val) {
		setConditionFunctionName(op, val, CONDITION_AND);
	}

	public void setConditionFunctionName(String op, java.lang.String val, String relation) {
		addCondition(1, op, relation, val);
	}

	public void setSelectFunctionName(boolean val) {
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

	public void setConditionFunctionCode(String op, java.lang.String val) {
		setConditionFunctionCode(op, val, CONDITION_AND);
	}

	public void setConditionFunctionCode(String op, java.lang.String val, String relation) {
		addCondition(3, op, relation, val);
	}

	public void setSelectFunctionCode(boolean val) {
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

	public void setConditionLeaf(String op, java.lang.Boolean val) {
		setConditionLeaf(op, val, CONDITION_AND);
	}

	public void setConditionLeaf(String op, java.lang.Boolean val, String relation) {
		addCondition(8, op, relation, val);
	}

	public void setSelectLeaf(boolean val) {
		__select_flags[8] = val;
	}

	public void setConditionIsMobile(String op, java.lang.Boolean val) {
		setConditionIsMobile(op, val, CONDITION_AND);
	}

	public void setConditionIsMobile(String op, java.lang.Boolean val, String relation) {
		addCondition(9, op, relation, val);
	}

	public void setSelectIsMobile(boolean val) {
		__select_flags[9] = val;
	}

	public void setConditionIcon(String op, java.lang.String val) {
		setConditionIcon(op, val, CONDITION_AND);
	}

	public void setConditionIcon(String op, java.lang.String val, String relation) {
		addCondition(10, op, relation, val);
	}

	public void setSelectIcon(boolean val) {
		__select_flags[10] = val;
	}

	public void setConditionStyleName(String op, java.lang.String val) {
		setConditionStyleName(op, val, CONDITION_AND);
	}

	public void setConditionStyleName(String op, java.lang.String val, String relation) {
		addCondition(11, op, relation, val);
	}

	public void setSelectStyleName(boolean val) {
		__select_flags[11] = val;
	}

	public void setConditionClassName(String op, java.lang.String val) {
		setConditionClassName(op, val, CONDITION_AND);
	}

	public void setConditionClassName(String op, java.lang.String val, String relation) {
		addCondition(12, op, relation, val);
	}

	public void setSelectClassName(boolean val) {
		__select_flags[12] = val;
	}

	public void setConditionIconColor(String op, java.lang.String val) {
		setConditionIconColor(op, val, CONDITION_AND);
	}

	public void setConditionIconColor(String op, java.lang.String val, String relation) {
		addCondition(13, op, relation, val);
	}

	public void setSelectIconColor(boolean val) {
		__select_flags[13] = val;
	}

	public void setConditionMarginTop(String op, java.lang.Integer val) {
		setConditionMarginTop(op, val, CONDITION_AND);
	}

	public void setConditionMarginTop(String op, java.lang.Integer val, String relation) {
		addCondition(14, op, relation, val);
	}

	public void setSelectMarginTop(boolean val) {
		__select_flags[14] = val;
	}

	public void setConditionMarginBottom(String op, java.lang.Integer val) {
		setConditionMarginBottom(op, val, CONDITION_AND);
	}

	public void setConditionMarginBottom(String op, java.lang.Integer val, String relation) {
		addCondition(15, op, relation, val);
	}

	public void setSelectMarginBottom(boolean val) {
		__select_flags[15] = val;
	}

	public void setConditionExecuteName(String op, java.lang.String val) {
		setConditionExecuteName(op, val, CONDITION_AND);
	}

	public void setConditionExecuteName(String op, java.lang.String val, String relation) {
		addCondition(16, op, relation, val);
	}

	public void setSelectExecuteName(boolean val) {
		__select_flags[16] = val;
	}

	public void setConditionRemark(String op, java.lang.String val) {
		setConditionRemark(op, val, CONDITION_AND);
	}

	public void setConditionRemark(String op, java.lang.String val, String relation) {
		addCondition(17, op, relation, val);
	}

	public void setSelectRemark(boolean val) {
		__select_flags[17] = val;
	}

	public void setConditionSeqNo(String op, java.lang.Integer val) {
		setConditionSeqNo(op, val, CONDITION_AND);
	}

	public void setConditionSeqNo(String op, java.lang.Integer val, String relation) {
		addCondition(18, op, relation, val);
	}

	public void setSelectSeqNo(boolean val) {
		__select_flags[18] = val;
	}


}

