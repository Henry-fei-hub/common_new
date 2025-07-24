package delicacy.functions.query;

import org.apache.log4j.Logger;
import delicacy.common.KeyValuePair;
import delicacy.common.AbstractQuery;
import delicacy.common.GenericBase;
import delicacy.common.BaseCollection;
import delicacy.connection.ThreadConnection;
import delicacy.functions.bean.BaseCopyEmployeeAllFunctionToOther;
import delicacy.functions.bean.ConditionCopyEmployeeAllFunctionToOther;

public class QueryCopyEmployeeAllFunctionToOther extends AbstractQuery<BaseCopyEmployeeAllFunctionToOther, ConditionCopyEmployeeAllFunctionToOther>
{

	private static final Logger __logger = Logger.getLogger(QueryCopyEmployeeAllFunctionToOther.class);

	public QueryCopyEmployeeAllFunctionToOther() throws java.sql.SQLException 
	{
		setParameterNumber(4);
		setConnection(ThreadConnection.getConnection());
		setOrderByFields("a.function_id");
	}

	@Override
	public BaseCollection<BaseCopyEmployeeAllFunctionToOther> executeQuery( KeyValuePair[] replacements, ConditionCopyEmployeeAllFunctionToOther condition ) throws java.sql.SQLException {
		return executeQuery(replacements, 
				condition.getEmployeeId(), 
				condition.getEmployeeId(), 
				condition.getEmployeeId(), 
				condition.getApplicationId()
			);
	}

	@Override
	public BaseCopyEmployeeAllFunctionToOther generateBase(Object[] __data) throws java.sql.SQLException {
		BaseCopyEmployeeAllFunctionToOther __base = new BaseCopyEmployeeAllFunctionToOther();
		int count = 0;
		Object val;
		if((val = __data[count++]) != null) __base.setFunctionId(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setFunctionCode(GenericBase.__getString(val));
		if((val = __data[count++]) != null) __base.setParentId(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setFunctionName(GenericBase.__getString(val));
		if((val = __data[count++]) != null) __base.setApplicationId(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setFunctionType(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setPrivilegeType(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setEnabled(GenericBase.__getBoolean(val));
		if((val = __data[count++]) != null) __base.setFunctionStaticName(GenericBase.__getString(val));
		if((val = __data[count++]) != null) __base.setEmpFun(GenericBase.__getBoolean(val));
		if((val = __data[count++]) != null) __base.setRoleFun(GenericBase.__getBoolean(val));
		if((val = __data[count++]) != null) __base.setDepartmentRoleFun(GenericBase.__getBoolean(val));
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

	private final static String __SQLText = "select a.function_id,b.function_code, b.parent_id,b.function_name, b.application_id,b.function_type, b.privilege_type, b.enabled, b.function_static_name, case when sum(a.emp_fun) = 0 then false else true end as emp_fun, case when sum(a.role_fun) = 0 then false else true end as role_fun, case when sum(a.department_role_fun) = 0 then false else true end as department_role_fun from ( select function_id,1 as emp_fun, 0 as role_fun, 0 as department_role_fun from employee_functions where employee_id = ? union SELECT function_id,0 AS emp_fun,1 AS role_fun,0 AS department_role_fun FROM employee_roles er LEFT JOIN role_functions rf ON er.role_id = rf.role_id WHERE er.employee_id = ? AND er.department_id = 0 UNION SELECT function_id, 0 AS emp_fun, 0 AS role_fun,1 AS department_role_fun FROM employee_roles er  LEFT JOIN department_role_functions drf ON er.department_id = drf.department_id AND er.role_id = drf.role_id WHERE er.department_id <> 0  AND er.employee_id = ?) as a left join functions b on a.function_id = b.function_id where b.application_id = ? AND a.function_id <>0 group by a.function_id,b.function_code, b.parent_id,b.function_name, b.application_id,b.function_type, b.privilege_type, b.enabled, b.function_static_name ORDER BY a.function_id ASC" ;
	private final static String RESULTSETFIELDLIST = "function_id,function_code,parent_id,function_name,application_id,function_type,privilege_type,enabled,function_static_name,emp_fun,role_fun,department_role_fun";
	private final static String[] fieldNames = { "employee_id", "employee_id", "employee_id", "application_id"};
}
