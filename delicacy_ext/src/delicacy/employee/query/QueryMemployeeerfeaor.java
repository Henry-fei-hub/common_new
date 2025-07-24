package delicacy.employee.query;

import org.apache.log4j.Logger;
import delicacy.common.KeyValuePair;
import delicacy.common.AbstractQuery;
import delicacy.common.GenericBase;
import delicacy.common.BaseCollection;
import delicacy.connection.ThreadConnection;
import delicacy.employee.bean.BaseMemployeeerfeaor;
import delicacy.employee.bean.ConditionMemployeeerfeaor;

public class QueryMemployeeerfeaor extends AbstractQuery<BaseMemployeeerfeaor, ConditionMemployeeerfeaor>
{

	private static final Logger __logger = Logger.getLogger(QueryMemployeeerfeaor.class);

	public QueryMemployeeerfeaor() throws java.sql.SQLException 
	{
		setParameterNumber(2);
		setConnection(ThreadConnection.getConnection());
		setOrderByFields("f.function_code");
	}

	@Override
	public BaseCollection<BaseMemployeeerfeaor> executeQuery( KeyValuePair[] replacements, ConditionMemployeeerfeaor condition ) throws java.sql.SQLException {
		return executeQuery(replacements, 
				condition.getEmployeeId(), 
				condition.getApplicationId()
			);
	}

	@Override
	public BaseMemployeeerfeaor generateBase(Object[] __data) throws java.sql.SQLException {
		BaseMemployeeerfeaor __base = new BaseMemployeeerfeaor();
		int count = 0;
		Object val;
		if((val = __data[count++]) != null) __base.setFunctionCode(GenericBase.__getString(val));
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

	private final static String __SQLText = "select f.function_code from employees e left join employee_roles er on e.employee_id = er.employee_id left join role_functions rf on er.role_id = rf.role_id and er.department_id = rf.department_id left join functions f on rf.function_id = f.function_id where e.employee_id = ? and (f.application_id = 0 or f.application_id = ?)" ;
	private final static String RESULTSETFIELDLIST = "function_code";
	private final static String[] fieldNames = { "employee_id", "application_id"};
}
