package delicacy.system.query;

import org.apache.log4j.Logger;
import delicacy.common.KeyValuePair;
import delicacy.common.AbstractQuery;
import delicacy.common.GenericBase;
import delicacy.common.BaseCollection;
import delicacy.connection.ThreadConnection;
import delicacy.system.bean.BaseEmployeeProcess;
import delicacy.system.bean.ConditionEmployeeProcess;

public class QueryEmployeeProcess extends AbstractQuery<BaseEmployeeProcess, ConditionEmployeeProcess>
{

	private static final Logger __logger = Logger.getLogger(QueryEmployeeProcess.class);

	public QueryEmployeeProcess() throws java.sql.SQLException 
	{
		setParameterNumber(2);
		setConnection(ThreadConnection.getConnection());
		setOrderByFields("p.process_id idd");
	}

	@Override
	public BaseCollection<BaseEmployeeProcess> executeQuery( KeyValuePair[] replacements, ConditionEmployeeProcess condition ) throws java.sql.SQLException {
		return executeQuery(replacements, 
				condition.getEmployeeId(),
				condition.getProcessTypeId()
			);
	}

	@Override
	public BaseEmployeeProcess generateBase(Object[] __data) throws java.sql.SQLException {
		BaseEmployeeProcess __base = new BaseEmployeeProcess();
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
		if(args[0] != null) __statement.setInt(count++, GenericBase.__getInt(args[0]));
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

	private final static String __SQLText = "select p.process_id idd, p.process_name val from employees e left join system_process_departments pd on e.department_id = pd.department_id left join system_processes p on p.process_id = pd.process_id where e.employee_id = ? and p.process_type_id = ? order by p.process_id" ;
	private final static String RESULTSETFIELDLIST = "idd,val";
	private final static String[] fieldNames = { "employee_id","process_type_id"};
}
