package delicacy.system.query;

import org.apache.log4j.Logger;
import delicacy.common.KeyValuePair;
import delicacy.common.AbstractQuery;
import delicacy.common.GenericBase;
import delicacy.common.BaseCollection;
import delicacy.connection.ThreadConnection;
import delicacy.system.bean.BaseSsystemprocesspor;
import delicacy.system.bean.ConditionSsystemprocesspor;

public class QuerySsystemprocesspor extends AbstractQuery<BaseSsystemprocesspor, ConditionSsystemprocesspor>
{

	private static final Logger __logger = Logger.getLogger(QuerySsystemprocesspor.class);

	public QuerySsystemprocesspor() throws java.sql.SQLException 
	{
		setParameterNumber(1);
		setConnection(ThreadConnection.getConnection());
		setOrderByFields("sp.process_id");
	}

	@Override
	public BaseCollection<BaseSsystemprocesspor> executeQuery( KeyValuePair[] replacements, ConditionSsystemprocesspor condition ) throws java.sql.SQLException {
		return executeQuery(replacements, 
				condition.getProcessTypeId()
			);
	}

	@Override
	public BaseSsystemprocesspor generateBase(Object[] __data) throws java.sql.SQLException {
		BaseSsystemprocesspor __base = new BaseSsystemprocesspor();
		int count = 0;
		Object val;
		if((val = __data[count++]) != null) __base.setProcessId(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setProcessTypeId(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setDepartmentId(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setIncludeDepartmentId(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setCreateEmployeeId(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setCreateTime(GenericBase.__getDateFromSQL(val));
		if((val = __data[count++]) != null) __base.setProcessName(GenericBase.__getString(val));
		if((val = __data[count++]) != null) __base.setCountersign(GenericBase.__getBoolean(val));
		if((val = __data[count++]) != null) __base.setHoldDepartmentId(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setHoldDutyId(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setDescription(GenericBase.__getString(val));
		if((val = __data[count++]) != null) __base.setEnable(GenericBase.__getBoolean(val));
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

	private final static String __SQLText = "select sp.process_id, sp.process_type_id, sp.department_id, sp.include_department_id, sp.create_employee_id, sp.create_time, sp.process_name, sp.countersign, sp.hold_department_id, sp.hold_duty_id, sp.description, sp.enable from system_processes sp where sp.process_type_id = ? order by sp.process_id DESC" ;
	private final static String RESULTSETFIELDLIST = "process_id,process_type_id,department_id,include_department_id,create_employee_id,create_time,process_name,countersign,hold_department_id,hold_duty_id,description,enable";
	private final static String[] fieldNames = { "process_type_id"};
}
