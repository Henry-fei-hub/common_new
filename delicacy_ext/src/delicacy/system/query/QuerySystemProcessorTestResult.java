package delicacy.system.query;

import org.apache.log4j.Logger;
import delicacy.common.KeyValuePair;
import delicacy.common.AbstractQuery;
import delicacy.common.GenericBase;
import delicacy.common.BaseCollection;
import delicacy.connection.ThreadConnection;
import delicacy.system.bean.BaseSystemProcessorTestResult;
import delicacy.system.bean.ConditionSystemProcessorTestResult;

public class QuerySystemProcessorTestResult extends AbstractQuery<BaseSystemProcessorTestResult, ConditionSystemProcessorTestResult>
{

	private static final Logger __logger = Logger.getLogger(QuerySystemProcessorTestResult.class);

	public QuerySystemProcessorTestResult() throws java.sql.SQLException 
	{
		setParameterNumber(3);
		setConnection(ThreadConnection.getConnection());
		setOrderByFields("a.processor_test_result_id");
	}

	@Override
	public BaseCollection<BaseSystemProcessorTestResult> executeQuery( KeyValuePair[] replacements, ConditionSystemProcessorTestResult condition ) throws java.sql.SQLException {
		return executeQuery(replacements, 
				condition.getThreadTaskManageId(), 
				condition.getProcessName(), 
				condition.getTestResult()
			);
	}

	@Override
	public BaseSystemProcessorTestResult generateBase(Object[] __data) throws java.sql.SQLException {
		BaseSystemProcessorTestResult __base = new BaseSystemProcessorTestResult();
		int count = 0;
		Object val;
		if((val = __data[count++]) != null) __base.setProcessorTestResultId(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setDrafter(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setEmployeeNo(GenericBase.__getString(val));
		if((val = __data[count++]) != null) __base.setEmployeeName(GenericBase.__getString(val));
		if((val = __data[count++]) != null) __base.setDepartmentId(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setPlateId(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setCompanyId(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setProcessId(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setProcessTypeId(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setProcessName(GenericBase.__getString(val));
		if((val = __data[count++]) != null) __base.setDescription(GenericBase.__getString(val));
		if((val = __data[count++]) != null) __base.setTestData(GenericBase.__getString(val));
		if((val = __data[count++]) != null) __base.setTestResult(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setErrorMsg(GenericBase.__getString(val));
		if((val = __data[count++]) != null) __base.setStartTime(GenericBase.__getDateFromSQL(val));
		if((val = __data[count++]) != null) __base.setEndTime(GenericBase.__getDateFromSQL(val));
		if((val = __data[count++]) != null) __base.setThreadTaskManageId(GenericBase.__getInt(val));
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
		if(args[2] != null) __statement.setInt(count++, GenericBase.__getInt(args[2]));
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

	private final static String __SQLText = "select a.processor_test_result_id, a.drafter, b.employee_no, b.employee_name, a.department_id, a.plate_id, a.company_id, a.process_id, c.process_type_id, c.process_name, c.description, a.test_data, a.test_result, a.error_msg, a.start_time, a.end_time, a.thread_task_manage_id from processor_test_results a LEFT JOIN employees b on a.drafter = b.employee_id LEFT JOIN system_processes c on a.process_id = c.process_id where a.thread_task_manage_id = ? and c.process_name like ? and a.test_result = ? order by a.test_result desc, a.start_time" ;
	private final static String RESULTSETFIELDLIST = "processor_test_result_id,drafter,employee_no,employee_name,department_id,plate_id,company_id,process_id,process_type_id,process_name,description,test_data,test_result,error_msg,start_time,end_time,thread_task_manage_id";
	private final static String[] fieldNames = { "thread_task_manage_id", "process_name", "test_result"};
}
