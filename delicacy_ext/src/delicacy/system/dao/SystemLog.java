package delicacy.system.dao;

import delicacy.common.AbstractTable;
import delicacy.common.JsonParser;
import delicacy.common.GenericBase;
import delicacy.system.bean.BaseSystemLog;


public class SystemLog extends AbstractTable<BaseSystemLog>
{

	public SystemLog() throws java.sql.SQLException 
	{

		initColumnNames();
	}

	protected final void initColumnNames()  throws java.sql.SQLException {

		__columnCount          = 7;

		initTables();

		__tableName            = "system_logs";

		__key_columns          = new int[1];
		__key_columns[0] = 0;

		__column_names[0] = BaseSystemLog.CS_SYSTEM_LOG_ID;
		__column_names[1] = BaseSystemLog.CS_LOG_TIME;
		__column_names[2] = BaseSystemLog.CS_FROM_HOST;
		__column_names[3] = BaseSystemLog.CS_OPERATOR;
		__column_names[4] = BaseSystemLog.CS_OPERATOR_NAME;
		__column_names[5] = BaseSystemLog.CS_LOG_CONTENT;
		__column_names[6] = BaseSystemLog.CS_LOG_TYPE;

		resetSelectFlags() ;
		resetInsertFlags() ;
	}

	public void resetInsertFlags(){
		for(int i = 0; i < __columnCount; i ++ ) __insert_flags[i] = true;
		__insert_flags[0] = false;
		__serial_column = 0;
	}

	public void setPrimaryKeyFromBase(BaseSystemLog b) {
		clear();
		setSystemLogIdClear(b.getSystemLogId());
	}

	public boolean isPrimaryKeyNull() {
		return getSystemLogId() == null;
	}

	@Override
	public BaseSystemLog generateBase(){
		BaseSystemLog b = new BaseSystemLog();
		setDataToBase(b);
		return b;
	}

	@Override
	public void setDataToBase(BaseSystemLog b){
		int count = 0; Object val;
		if((val = __current_data[count++]) != null) b.setSystemLogId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setLogTime(GenericBase.__getDateFromSQL(val));
		if((val = __current_data[count++]) != null) b.setFromHost(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setOperator(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setOperatorName(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setLogContent(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setLogType(GenericBase.__getInt(val));
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
			if(val != null && !val.isEmpty()) setSystemLogId(GenericBase.__getInt(val));
		}
		if((num = __inputNames[count++]) != null) {
			val = values[num];
			if(val != null && !val.isEmpty()) setLogTime(GenericBase.__getDate(val));
		}
		if((num = __inputNames[count++]) != null) {
			val = values[num];
			if(val != null && !val.isEmpty()) setFromHost(GenericBase.__getString(val));
		}
		if((num = __inputNames[count++]) != null) {
			val = values[num];
			if(val != null && !val.isEmpty()) setOperator(GenericBase.__getInt(val));
		}
		if((num = __inputNames[count++]) != null) {
			val = values[num];
			if(val != null && !val.isEmpty()) setOperatorName(GenericBase.__getString(val));
		}
		if((num = __inputNames[count++]) != null) {
			val = values[num];
			if(val != null && !val.isEmpty()) setLogContent(GenericBase.__getString(val));
		}
		if((num = __inputNames[count++]) != null) {
			val = values[num];
			if(val != null && !val.isEmpty()) setLogType(GenericBase.__getInt(val));
		}
	}

	@Override
	public void setBaseToBuffer(BaseSystemLog b, Object[] buff){
		int count = 0;
		buff[count++] = b.getSystemLogId();
		buff[count++] = generateTimestampFromDate(b.getLogTime());
		buff[count++] = b.getFromHost();
		buff[count++] = b.getOperator();
		buff[count++] = b.getOperatorName();
		buff[count++] = b.getLogContent();
		buff[count++] = b.getLogType();
	}

	@Override
	public void setDataFromBase(BaseSystemLog b){
		if(b.getSystemLogId() != null) setSystemLogIdClear(b.getSystemLogId());
		if(b.getLogTime() != null) setLogTime(b.getLogTime());
		if(b.getFromHost() != null) setFromHost(b.getFromHost());
		if(b.getOperator() != null) setOperator(b.getOperator());
		if(b.getOperatorName() != null) setOperatorName(b.getOperatorName());
		if(b.getLogContent() != null) setLogContent(b.getLogContent());
		if(b.getLogType() != null) setLogType(b.getLogType());
	}

	@Override
	public BaseSystemLog generateBaseFromResultSet(java.sql.ResultSet sdr)  throws java.sql.SQLException {

		BaseSystemLog b = new BaseSystemLog();
		setQueryResultToBase(b, sdr);
		return b;
	}

	@Override
	public void setQueryResultToBase(BaseSystemLog __base, java.sql.ResultSet sdr ) throws java.sql.SQLException {
		int count = 1, index = 0;
		java.sql.ResultSetMetaData rsmd = sdr.getMetaData();
		int columnCount = rsmd.getColumnCount();
		Object val;
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setSystemLogId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setLogTime(GenericBase.__getDateFromSQL(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setFromHost(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setOperator(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setOperatorName(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setLogContent(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setLogType(GenericBase.__getInt(val));
	}

	public void setSystemLogId(java.lang.Integer val) {
		setCurrentData(0, val);
	}

	public java.lang.Integer getSystemLogId() {
		return GenericBase.__getInt(__current_data[0]);
	}

	public void setSystemLogIdClear(java.lang.Integer val) {
		__backup_data[0] = __current_data[0] =  val;
		__modified_flags[0] = false;
	}

	public void setLogTime(java.util.Date val) {
		setCurrentData(1, generateTimestampFromDate(val));
	}

	public java.util.Date getLogTime() {
		return GenericBase.__getDateFromSQL(__current_data[1]);
	}

	public void setFromHost(java.lang.String val) {
		setCurrentData(2, val);
	}

	public java.lang.String getFromHost() {
		return GenericBase.__getString(__current_data[2]);
	}

	public void setOperator(java.lang.Integer val) {
		setCurrentData(3, val);
	}

	public java.lang.Integer getOperator() {
		return GenericBase.__getInt(__current_data[3]);
	}

	public void setOperatorName(java.lang.String val) {
		setCurrentData(4, val);
	}

	public java.lang.String getOperatorName() {
		return GenericBase.__getString(__current_data[4]);
	}

	public void setLogContent(java.lang.String val) {
		setCurrentData(5, val);
	}

	public java.lang.String getLogContent() {
		return GenericBase.__getString(__current_data[5]);
	}

	public void setLogType(java.lang.Integer val) {
		setCurrentData(6, val);
	}

	public java.lang.Integer getLogType() {
		return GenericBase.__getInt(__current_data[6]);
	}

	public void setConditionSystemLogId(String op, java.lang.Integer val) {
		addSingleCondition(0, op, val);
	}

	public void setConditionSystemLogId(String op, java.lang.Integer val, String relation) {
		addCondition(0, op, relation, new Object[] { val });
	}

	public void setSelectSystemLogId(boolean val) {
		__select_flags[0] = val;
	}

	public void setSystemLogIdExpression(String val) {
		__dataExpressions[0] = val;
	}

	public void setConditionLogTime(String op, java.util.Date val) {
		addSingleCondition(1, op, val);
	}

	public void setConditionLogTime(String op, java.util.Date val, String relation) {
		addCondition(1, op, relation, new Object[] { generateTimestampFromDate(val) });
	}

	public void setSelectLogTime(boolean val) {
		__select_flags[1] = val;
	}

	public void setLogTimeExpression(String val) {
		__dataExpressions[1] = val;
	}

	public void setConditionFromHost(String op, java.lang.String val) {
		addSingleCondition(2, op, val);
	}

	public void setConditionFromHost(String op, java.lang.String val, String relation) {
		addCondition(2, op, relation, new Object[] { val });
	}

	public void setSelectFromHost(boolean val) {
		__select_flags[2] = val;
	}

	public void setFromHostExpression(String val) {
		__dataExpressions[2] = val;
	}

	public void setConditionOperator(String op, java.lang.Integer val) {
		addSingleCondition(3, op, val);
	}

	public void setConditionOperator(String op, java.lang.Integer val, String relation) {
		addCondition(3, op, relation, new Object[] { val });
	}

	public void setSelectOperator(boolean val) {
		__select_flags[3] = val;
	}

	public void setOperatorExpression(String val) {
		__dataExpressions[3] = val;
	}

	public void setConditionOperatorName(String op, java.lang.String val) {
		addSingleCondition(4, op, val);
	}

	public void setConditionOperatorName(String op, java.lang.String val, String relation) {
		addCondition(4, op, relation, new Object[] { val });
	}

	public void setSelectOperatorName(boolean val) {
		__select_flags[4] = val;
	}

	public void setOperatorNameExpression(String val) {
		__dataExpressions[4] = val;
	}

	public void setConditionLogContent(String op, java.lang.String val) {
		addSingleCondition(5, op, val);
	}

	public void setConditionLogContent(String op, java.lang.String val, String relation) {
		addCondition(5, op, relation, new Object[] { val });
	}

	public void setSelectLogContent(boolean val) {
		__select_flags[5] = val;
	}

	public void setLogContentExpression(String val) {
		__dataExpressions[5] = val;
	}

	public void setConditionLogType(String op, java.lang.Integer val) {
		addSingleCondition(6, op, val);
	}

	public void setConditionLogType(String op, java.lang.Integer val, String relation) {
		addCondition(6, op, relation, new Object[] { val });
	}

	public void setSelectLogType(boolean val) {
		__select_flags[6] = val;
	}

	public void setLogTypeExpression(String val) {
		__dataExpressions[6] = val;
	}


}

