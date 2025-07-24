package delicacy.sys.query;

import org.apache.log4j.Logger;
import delicacy.common.KeyValuePair;
import delicacy.common.AbstractQuery;
import delicacy.common.GenericBase;
import delicacy.common.BaseCollection;
import delicacy.connection.ThreadConnection;
import delicacy.sys.bean.BaseSfunctionffaor;
import delicacy.sys.bean.ConditionSfunctionffaor;

public class QuerySfunctionffaor extends AbstractQuery<BaseSfunctionffaor, ConditionSfunctionffaor>
{

	private static final Logger __logger = Logger.getLogger(QuerySfunctionffaor.class);

	public QuerySfunctionffaor() throws java.sql.SQLException 
	{
		setParameterNumber(4);
		setConnection(ThreadConnection.getConnection());
		setOrderByFields("f.function_id");
	}

	@Override
	public BaseCollection<BaseSfunctionffaor> executeQuery( KeyValuePair[] replacements, ConditionSfunctionffaor condition ) throws java.sql.SQLException {
		return executeQuery(replacements, 
				condition.getParentId(), 
				condition.getFunctionCode(), 
				condition.getFunctionName(), 
				condition.getApplicationId()
			);
	}

	@Override
	public BaseSfunctionffaor generateBase(Object[] __data) throws java.sql.SQLException {
		BaseSfunctionffaor __base = new BaseSfunctionffaor();
		int count = 0;
		Object val;
		if((val = __data[count++]) != null) __base.setFunctionId(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setFunctionCode(GenericBase.__getString(val));
		if((val = __data[count++]) != null) __base.setParentId(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setFunctionName(GenericBase.__getString(val));
		if((val = __data[count++]) != null) __base.setApplicationId(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setFunctionType(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setEnabled(GenericBase.__getBoolean(val));
		return __base;
	}

	@Override
	public int setStatementParameters(int count, java.lang.Object ... args) throws java.sql.SQLException {

		int index = 0;
		for(int ii = 0; ii < args.length; ii++){
			if(args[ii] == null) continue;
			index++;
			__logger.info(String.format("%1$s = [%2$s]", fieldNames[ii], args[ii]));
		}
		if( index > 0 ) __logger.info("=================================================");
		if(args[0] != null) __statement.setInt(count++, GenericBase.__getInt(args[0]));
		if(args[1] != null) __statement.setString(count++, GenericBase.__getString(args[1]));
		if(args[2] != null) __statement.setString(count++, GenericBase.__getString(args[2]));
		if(args[3] != null) __statement.setInt(count++, GenericBase.__getInt(args[3]));
		return count;
	}

	@Override
	public String getSQLText() {
		__logger.info(__SQLText);
		return __SQLText;
	}

	@Override
	public String getFieldList(){
		return RESULTSETFIELDLIST;
	}

	private final static String __SQLText = "select f.function_id, f.function_code, f.parent_id, f.function_name, f.application_id, f.function_type, f.enabled from functions f where f.parent_id = ? and f.function_code = ? and f.function_name = ? and f.application_id = ?" ;
	private final static String RESULTSETFIELDLIST = "function_id,function_code,parent_id,function_name,application_id,function_type,enabled";
	private final static String[] fieldNames = { "parent_id", "function_code", "function_name", "application_id"};
}
