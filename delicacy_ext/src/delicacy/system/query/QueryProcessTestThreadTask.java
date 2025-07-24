package delicacy.system.query;

import org.apache.log4j.Logger;
import delicacy.common.KeyValuePair;
import delicacy.common.AbstractQuery;
import delicacy.common.GenericBase;
import delicacy.common.BaseCollection;
import delicacy.connection.ThreadConnection;
import delicacy.system.bean.BaseProcessTestThreadTask;
import delicacy.system.bean.ConditionProcessTestThreadTask;

public class QueryProcessTestThreadTask extends AbstractQuery<BaseProcessTestThreadTask, ConditionProcessTestThreadTask>
{

	private static final Logger __logger = Logger.getLogger(QueryProcessTestThreadTask.class);

	public QueryProcessTestThreadTask() throws java.sql.SQLException 
	{
		setParameterNumber(3);
		setConnection(ThreadConnection.getConnection());
		setOrderByFields("thread_task_manage_id");
	}

	@Override
	public BaseCollection<BaseProcessTestThreadTask> executeQuery( KeyValuePair[] replacements, ConditionProcessTestThreadTask condition ) throws java.sql.SQLException {
		return executeQuery(replacements, 
				condition.getThreadTaskManageId(), 
				condition.getTaskType(), 
				condition.getStatus()
			);
	}

	@Override
	public BaseProcessTestThreadTask generateBase(Object[] __data) throws java.sql.SQLException {
		BaseProcessTestThreadTask __base = new BaseProcessTestThreadTask();
		int count = 0;
		Object val;
		if((val = __data[count++]) != null) __base.setThreadTaskManageId(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setTaskType(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setStatus(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setErrorMsg(GenericBase.__getString(val));
		if((val = __data[count++]) != null) __base.setOperator(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setOperationType(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setBeginTime(GenericBase.__getDateFromSQL(val));
		if((val = __data[count++]) != null) __base.setEndTime(GenericBase.__getDateFromSQL(val));
		if((val = __data[count++]) != null) __base.setCreateTime(GenericBase.__getDateFromSQL(val));
		if((val = __data[count++]) != null) __base.setRemark(GenericBase.__getString(val));
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

	private final static String __SQLText = "select thread_task_manage_id, task_type, status, error_msg, operator, operation_type, begin_time, end_time, create_time, remark from thread_task_manages where thread_task_manage_id = ? and task_type = ? and status = ? order by create_time desc" ;
	private final static String RESULTSETFIELDLIST = "thread_task_manage_id,task_type,status,error_msg,operator,operation_type,begin_time,end_time,create_time,remark";
	private final static String[] fieldNames = { "thread_task_manage_id", "task_type", "status"};
}
