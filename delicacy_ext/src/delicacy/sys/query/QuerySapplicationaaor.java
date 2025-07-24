package delicacy.sys.query;

import org.apache.log4j.Logger;
import delicacy.common.KeyValuePair;
import delicacy.common.AbstractQuery;
import delicacy.common.GenericBase;
import delicacy.common.BaseCollection;
import delicacy.connection.ThreadConnection;
import delicacy.sys.bean.BaseSapplicationaaor;
import delicacy.sys.bean.ConditionSapplicationaaor;

public class QuerySapplicationaaor extends AbstractQuery<BaseSapplicationaaor, ConditionSapplicationaaor>
{

	private static final Logger __logger = Logger.getLogger(QuerySapplicationaaor.class);

	public QuerySapplicationaaor() throws java.sql.SQLException 
	{
		setParameterNumber(2);
		setConnection(ThreadConnection.getConnection());
		setOrderByFields("a.application_id");
	}

	@Override
	public BaseCollection<BaseSapplicationaaor> executeQuery( KeyValuePair[] replacements, ConditionSapplicationaaor condition ) throws java.sql.SQLException {
		return executeQuery(replacements, 
				condition.getApplicationId(), 
				condition.getApplicationName()
			);
	}

	@Override
	public BaseSapplicationaaor generateBase(Object[] __data) throws java.sql.SQLException {
		BaseSapplicationaaor __base = new BaseSapplicationaaor();
		int count = 0;
		Object val;
		if((val = __data[count++]) != null) __base.setApplicationId(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setApplicationName(GenericBase.__getString(val));
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

	private final static String __SQLText = "select a.application_id, a.application_name from applications a where a.application_id = ? and a.application_name = ?" ;
	private final static String RESULTSETFIELDLIST = "application_id,application_name";
	private final static String[] fieldNames = { "application_id", "application_name"};
}
