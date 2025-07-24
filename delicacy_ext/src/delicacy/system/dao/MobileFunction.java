package delicacy.system.dao;

import delicacy.common.AbstractTable;
import delicacy.common.JsonParser;
import delicacy.common.GenericBase;
import delicacy.system.bean.BaseMobileFunction;


public class MobileFunction extends AbstractTable<BaseMobileFunction>
{

	public MobileFunction() throws java.sql.SQLException 
	{

		initColumnNames();
	}

	protected final void initColumnNames()  throws java.sql.SQLException {

		__columnCount          = 17;

		initTables();

		__tableName            = "mobile_functions";

		__key_columns          = new int[1];
		__key_columns[0] = 0;

		__column_names[0] = BaseMobileFunction.CS_FUNCTION_ID;
		__column_names[1] = BaseMobileFunction.CS_FUNCTION_CODE;
		__column_names[2] = BaseMobileFunction.CS_PARENT_ID;
		__column_names[3] = BaseMobileFunction.CS_FUNCTION_NAME;
		__column_names[4] = BaseMobileFunction.CS_APPLICATION_ID;
		__column_names[5] = BaseMobileFunction.CS_FUNCTION_TYPE;
		__column_names[6] = BaseMobileFunction.CS_PRIVILEGE_TYPE;
		__column_names[7] = BaseMobileFunction.CS_ENABLED;
		__column_names[8] = BaseMobileFunction.CS_FUNCTION_STATIC_NAME;
		__column_names[9] = BaseMobileFunction.CS_ICON_NAME;
		__column_names[10] = BaseMobileFunction.CS_STYLE_NAME;
		__column_names[11] = BaseMobileFunction.CS_CLASS_NAME;
		__column_names[12] = BaseMobileFunction.CS_EXECUTE_PAGE_NAME;
		__column_names[13] = BaseMobileFunction.CS_MARGIN_TOP;
		__column_names[14] = BaseMobileFunction.CS_MARGIN_BOTTOM;
		__column_names[15] = BaseMobileFunction.CS_ICON_COLOR;
		__column_names[16] = BaseMobileFunction.CS_APP_PAGE_NAME;

		resetSelectFlags() ;
		resetInsertFlags() ;
	}

	public void resetInsertFlags(){
		for(int i = 0; i < __columnCount; i ++ ) __insert_flags[i] = true;
		__insert_flags[0] = false;
		__serial_column = 0;
	}

	public void setPrimaryKeyFromBase(BaseMobileFunction b) {
		clear();
		setFunctionIdClear(b.getFunctionId());
	}

	public boolean isPrimaryKeyNull() {
		return getFunctionId() == null;
	}

	@Override
	public BaseMobileFunction generateBase(){
		BaseMobileFunction b = new BaseMobileFunction();
		setDataToBase(b);
		return b;
	}

	@Override
	public void setDataToBase(BaseMobileFunction b){
		int count = 0; Object val;
		if((val = __current_data[count++]) != null) b.setFunctionId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setFunctionCode(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setParentId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setFunctionName(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setApplicationId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setFunctionType(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setPrivilegeType(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setEnabled(GenericBase.__getBoolean(val));
		if((val = __current_data[count++]) != null) b.setFunctionStaticName(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setIconName(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setStyleName(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setClassName(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setExecutePageName(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setMarginTop(GenericBase.__getDecimal(val));
		if((val = __current_data[count++]) != null) b.setMarginBottom(GenericBase.__getDecimal(val));
		if((val = __current_data[count++]) != null) b.setIconColor(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setAppPageName(GenericBase.__getString(val));
	}

	public void setDataFromCSV(String csvLine){
		setDataFromCSV(csvLine, null);
	}

	public void setDataFromCSV(String csvLine, String names){
		int count = 0; String val;
		Integer num = null;
		setInputNames(names);
		String[] values = JsonParser.csvLine(csvLine, ',');
		if((num = __inputNames[count++]) != null) {
			val = values[num];
			if(val != null && !val.isEmpty()) setFunctionId(GenericBase.__getInt(val));
		}
		if((num = __inputNames[count++]) != null) {
			val = values[num];
			if(val != null && !val.isEmpty()) setFunctionCode(GenericBase.__getString(val));
		}
		if((num = __inputNames[count++]) != null) {
			val = values[num];
			if(val != null && !val.isEmpty()) setParentId(GenericBase.__getInt(val));
		}
		if((num = __inputNames[count++]) != null) {
			val = values[num];
			if(val != null && !val.isEmpty()) setFunctionName(GenericBase.__getString(val));
		}
		if((num = __inputNames[count++]) != null) {
			val = values[num];
			if(val != null && !val.isEmpty()) setApplicationId(GenericBase.__getInt(val));
		}
		if((num = __inputNames[count++]) != null) {
			val = values[num];
			if(val != null && !val.isEmpty()) setFunctionType(GenericBase.__getInt(val));
		}
		if((num = __inputNames[count++]) != null) {
			val = values[num];
			if(val != null && !val.isEmpty()) setPrivilegeType(GenericBase.__getInt(val));
		}
		if((num = __inputNames[count++]) != null) {
			val = values[num];
			if(val != null && !val.isEmpty()) setEnabled(GenericBase.__getBoolean(val));
		}
		if((num = __inputNames[count++]) != null) {
			val = values[num];
			if(val != null && !val.isEmpty()) setFunctionStaticName(GenericBase.__getString(val));
		}
		if((num = __inputNames[count++]) != null) {
			val = values[num];
			if(val != null && !val.isEmpty()) setIconName(GenericBase.__getString(val));
		}
		if((num = __inputNames[count++]) != null) {
			val = values[num];
			if(val != null && !val.isEmpty()) setStyleName(GenericBase.__getString(val));
		}
		if((num = __inputNames[count++]) != null) {
			val = values[num];
			if(val != null && !val.isEmpty()) setClassName(GenericBase.__getString(val));
		}
		if((num = __inputNames[count++]) != null) {
			val = values[num];
			if(val != null && !val.isEmpty()) setExecutePageName(GenericBase.__getString(val));
		}
		if((num = __inputNames[count++]) != null) {
			val = values[num];
			if(val != null && !val.isEmpty()) setMarginTop(GenericBase.__getDecimal(val));
		}
		if((num = __inputNames[count++]) != null) {
			val = values[num];
			if(val != null && !val.isEmpty()) setMarginBottom(GenericBase.__getDecimal(val));
		}
		if((num = __inputNames[count++]) != null) {
			val = values[num];
			if(val != null && !val.isEmpty()) setIconColor(GenericBase.__getString(val));
		}
		if((num = __inputNames[count++]) != null) {
			val = values[num];
			if(val != null && !val.isEmpty()) setAppPageName(GenericBase.__getString(val));
		}
	}

	@Override
	public void setBaseToBuffer(BaseMobileFunction b, Object[] buff){
		int count = 0;
		buff[count++] = b.getFunctionId();
		buff[count++] = b.getFunctionCode();
		buff[count++] = b.getParentId();
		buff[count++] = b.getFunctionName();
		buff[count++] = b.getApplicationId();
		buff[count++] = b.getFunctionType();
		buff[count++] = b.getPrivilegeType();
		buff[count++] = b.getEnabled();
		buff[count++] = b.getFunctionStaticName();
		buff[count++] = b.getIconName();
		buff[count++] = b.getStyleName();
		buff[count++] = b.getClassName();
		buff[count++] = b.getExecutePageName();
		buff[count++] = b.getMarginTop();
		buff[count++] = b.getMarginBottom();
		buff[count++] = b.getIconColor();
		buff[count++] = b.getAppPageName();
	}

	@Override
	public void setDataFromBase(BaseMobileFunction b){
		if(b.getFunctionId() != null) setFunctionIdClear(b.getFunctionId());
		if(b.getFunctionCode() != null) setFunctionCode(b.getFunctionCode());
		if(b.getParentId() != null) setParentId(b.getParentId());
		if(b.getFunctionName() != null) setFunctionName(b.getFunctionName());
		if(b.getApplicationId() != null) setApplicationId(b.getApplicationId());
		if(b.getFunctionType() != null) setFunctionType(b.getFunctionType());
		if(b.getPrivilegeType() != null) setPrivilegeType(b.getPrivilegeType());
		if(b.getEnabled() != null) setEnabled(b.getEnabled());
		if(b.getFunctionStaticName() != null) setFunctionStaticName(b.getFunctionStaticName());
		if(b.getIconName() != null) setIconName(b.getIconName());
		if(b.getStyleName() != null) setStyleName(b.getStyleName());
		if(b.getClassName() != null) setClassName(b.getClassName());
		if(b.getExecutePageName() != null) setExecutePageName(b.getExecutePageName());
		if(b.getMarginTop() != null) setMarginTop(b.getMarginTop());
		if(b.getMarginBottom() != null) setMarginBottom(b.getMarginBottom());
		if(b.getIconColor() != null) setIconColor(b.getIconColor());
		if(b.getAppPageName() != null) setAppPageName(b.getAppPageName());
	}

	@Override
	public BaseMobileFunction generateBaseFromResultSet(java.sql.ResultSet sdr)  throws java.sql.SQLException {

		BaseMobileFunction b = new BaseMobileFunction();
		setQueryResultToBase(b, sdr);
		return b;
	}

	@Override
	public void setQueryResultToBase(BaseMobileFunction __base, java.sql.ResultSet sdr ) throws java.sql.SQLException {
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
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setFunctionStaticName(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setIconName(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setStyleName(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setClassName(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setExecutePageName(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setMarginTop(GenericBase.__getDecimal(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setMarginBottom(GenericBase.__getDecimal(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setIconColor(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setAppPageName(GenericBase.__getString(val));
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

	public void setFunctionStaticName(java.lang.String val) {
		setCurrentData(8, val);
	}

	public java.lang.String getFunctionStaticName() {
		return GenericBase.__getString(__current_data[8]);
	}

	public void setIconName(java.lang.String val) {
		setCurrentData(9, val);
	}

	public java.lang.String getIconName() {
		return GenericBase.__getString(__current_data[9]);
	}

	public void setStyleName(java.lang.String val) {
		setCurrentData(10, val);
	}

	public java.lang.String getStyleName() {
		return GenericBase.__getString(__current_data[10]);
	}

	public void setClassName(java.lang.String val) {
		setCurrentData(11, val);
	}

	public java.lang.String getClassName() {
		return GenericBase.__getString(__current_data[11]);
	}

	public void setExecutePageName(java.lang.String val) {
		setCurrentData(12, val);
	}

	public java.lang.String getExecutePageName() {
		return GenericBase.__getString(__current_data[12]);
	}

	public void setMarginTop(java.math.BigDecimal val) {
		setCurrentData(13, val);
	}

	public java.math.BigDecimal getMarginTop() {
		return GenericBase.__getDecimal(__current_data[13]);
	}

	public void setMarginBottom(java.math.BigDecimal val) {
		setCurrentData(14, val);
	}

	public java.math.BigDecimal getMarginBottom() {
		return GenericBase.__getDecimal(__current_data[14]);
	}

	public void setIconColor(java.lang.String val) {
		setCurrentData(15, val);
	}

	public java.lang.String getIconColor() {
		return GenericBase.__getString(__current_data[15]);
	}

	public void setAppPageName(java.lang.String val) {
		setCurrentData(16, val);
	}

	public java.lang.String getAppPageName() {
		return GenericBase.__getString(__current_data[16]);
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

	public void setFunctionIdExpression(String val) {
		__dataExpressions[0] = val;
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

	public void setFunctionCodeExpression(String val) {
		__dataExpressions[1] = val;
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

	public void setParentIdExpression(String val) {
		__dataExpressions[2] = val;
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

	public void setFunctionNameExpression(String val) {
		__dataExpressions[3] = val;
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

	public void setApplicationIdExpression(String val) {
		__dataExpressions[4] = val;
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

	public void setFunctionTypeExpression(String val) {
		__dataExpressions[5] = val;
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

	public void setPrivilegeTypeExpression(String val) {
		__dataExpressions[6] = val;
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

	public void setEnabledExpression(String val) {
		__dataExpressions[7] = val;
	}

	public void setConditionFunctionStaticName(String op, java.lang.String val) {
		setConditionFunctionStaticName(op, val, CONDITION_AND);
	}

	public void setConditionFunctionStaticName(String op, java.lang.String val, String relation) {
		addCondition(8, op, relation, val);
	}

	public void setSelectFunctionStaticName(boolean val) {
		__select_flags[8] = val;
	}

	public void setFunctionStaticNameExpression(String val) {
		__dataExpressions[8] = val;
	}

	public void setConditionIconName(String op, java.lang.String val) {
		setConditionIconName(op, val, CONDITION_AND);
	}

	public void setConditionIconName(String op, java.lang.String val, String relation) {
		addCondition(9, op, relation, val);
	}

	public void setSelectIconName(boolean val) {
		__select_flags[9] = val;
	}

	public void setIconNameExpression(String val) {
		__dataExpressions[9] = val;
	}

	public void setConditionStyleName(String op, java.lang.String val) {
		setConditionStyleName(op, val, CONDITION_AND);
	}

	public void setConditionStyleName(String op, java.lang.String val, String relation) {
		addCondition(10, op, relation, val);
	}

	public void setSelectStyleName(boolean val) {
		__select_flags[10] = val;
	}

	public void setStyleNameExpression(String val) {
		__dataExpressions[10] = val;
	}

	public void setConditionClassName(String op, java.lang.String val) {
		setConditionClassName(op, val, CONDITION_AND);
	}

	public void setConditionClassName(String op, java.lang.String val, String relation) {
		addCondition(11, op, relation, val);
	}

	public void setSelectClassName(boolean val) {
		__select_flags[11] = val;
	}

	public void setClassNameExpression(String val) {
		__dataExpressions[11] = val;
	}

	public void setConditionExecutePageName(String op, java.lang.String val) {
		setConditionExecutePageName(op, val, CONDITION_AND);
	}

	public void setConditionExecutePageName(String op, java.lang.String val, String relation) {
		addCondition(12, op, relation, val);
	}

	public void setSelectExecutePageName(boolean val) {
		__select_flags[12] = val;
	}

	public void setExecutePageNameExpression(String val) {
		__dataExpressions[12] = val;
	}

	public void setConditionMarginTop(String op, java.math.BigDecimal val) {
		setConditionMarginTop(op, val, CONDITION_AND);
	}

	public void setConditionMarginTop(String op, java.math.BigDecimal val, String relation) {
		addCondition(13, op, relation, val);
	}

	public void setSelectMarginTop(boolean val) {
		__select_flags[13] = val;
	}

	public void setMarginTopExpression(String val) {
		__dataExpressions[13] = val;
	}

	public void setConditionMarginBottom(String op, java.math.BigDecimal val) {
		setConditionMarginBottom(op, val, CONDITION_AND);
	}

	public void setConditionMarginBottom(String op, java.math.BigDecimal val, String relation) {
		addCondition(14, op, relation, val);
	}

	public void setSelectMarginBottom(boolean val) {
		__select_flags[14] = val;
	}

	public void setMarginBottomExpression(String val) {
		__dataExpressions[14] = val;
	}

	public void setConditionIconColor(String op, java.lang.String val) {
		setConditionIconColor(op, val, CONDITION_AND);
	}

	public void setConditionIconColor(String op, java.lang.String val, String relation) {
		addCondition(15, op, relation, val);
	}

	public void setSelectIconColor(boolean val) {
		__select_flags[15] = val;
	}

	public void setIconColorExpression(String val) {
		__dataExpressions[15] = val;
	}

	public void setConditionAppPageName(String op, java.lang.String val) {
		setConditionAppPageName(op, val, CONDITION_AND);
	}

	public void setConditionAppPageName(String op, java.lang.String val, String relation) {
		addCondition(16, op, relation, val);
	}

	public void setSelectAppPageName(boolean val) {
		__select_flags[16] = val;
	}

	public void setAppPageNameExpression(String val) {
		__dataExpressions[16] = val;
	}


}

