package delicacy.department.query;

import org.apache.log4j.Logger;
import delicacy.common.KeyValuePair;
import delicacy.common.AbstractQuery;
import delicacy.common.GenericBase;
import delicacy.common.BaseCollection;
import delicacy.connection.ThreadConnection;
import delicacy.department.bean.BaseSystemProcessList;
import delicacy.department.bean.ConditionSystemProcessList;

public class QuerySystemProcessList extends AbstractQuery<BaseSystemProcessList, ConditionSystemProcessList>
{

	private static final Logger __logger = Logger.getLogger(QuerySystemProcessList.class);

	public QuerySystemProcessList() throws java.sql.SQLException 
	{
		setParameterNumber(2);
		setConnection(ThreadConnection.getConnection());
		setOrderByFields("sp.process_id");
	}

	@Override
	public BaseCollection<BaseSystemProcessList> executeQuery( KeyValuePair[] replacements, ConditionSystemProcessList condition ) throws java.sql.SQLException {
		return executeQuery(replacements, 
				condition.getProcessName(), 
				condition.getProcessTypeId()
			);
	}

	@Override
	public BaseSystemProcessList generateBase(Object[] __data) throws java.sql.SQLException {
		BaseSystemProcessList __base = new BaseSystemProcessList();
		int count = 0;
		Object val;
		if((val = __data[count++]) != null) __base.setProcessId(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setProcessName(GenericBase.__getString(val));
		if((val = __data[count++]) != null) __base.setDescription(GenericBase.__getString(val));
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
		if(args[0] != null) __statement.setString(count++, GenericBase.__getString(args[0]));
		if(args[1] != null) __statement.setInt(count++, GenericBase.__getInt(args[1]));
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

	private final static String __SQLText = "select sp.process_id, sp.process_name, sp.description from system_processes sp where sp.process_name like ? and sp.process_type_id = ? and sp.enable = true" ;
	private final static String RESULTSETFIELDLIST = "process_id,process_name,description";
	private final static String[] fieldNames = { "process_name", "process_type_id"};
}
