package delicacy.system.query;

import org.apache.log4j.Logger;
import delicacy.common.KeyValuePair;
import delicacy.common.AbstractQuery;
import delicacy.common.GenericBase;
import delicacy.common.BaseCollection;
import delicacy.connection.ThreadConnection;
import delicacy.system.bean.BaseMsystemprocesssor;
import delicacy.system.bean.ConditionMsystemprocesssor;

public class QueryMsystemprocesssor extends AbstractQuery<BaseMsystemprocesssor, ConditionMsystemprocesssor>
{

	private static final Logger __logger = Logger.getLogger(QueryMsystemprocesssor.class);

	public QueryMsystemprocesssor() throws java.sql.SQLException 
	{
		setParameterNumber(0);
		setConnection(ThreadConnection.getConnection());
		setOrderByFields("sp.process_id as idd");
	}

	@Override
	public BaseCollection<BaseMsystemprocesssor> executeQuery( KeyValuePair[] replacements, ConditionMsystemprocesssor condition ) throws java.sql.SQLException {
		return executeQuery(replacements
			);
	}

	@Override
	public BaseMsystemprocesssor generateBase(Object[] __data) throws java.sql.SQLException {
		BaseMsystemprocesssor __base = new BaseMsystemprocesssor();
		int count = 0;
		Object val;
		if((val = __data[count++]) != null) __base.setIdd(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setVal(GenericBase.__getString(val));
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

	private final static String __SQLText = "select sp.process_id as idd , spt.process_execute_name as val from system_processes sp left join system_process_types spt on sp.process_type_id = spt.process_type_id" ;
	private final static String RESULTSETFIELDLIST = "idd,val";
	private final static String[] fieldNames = { };
}
