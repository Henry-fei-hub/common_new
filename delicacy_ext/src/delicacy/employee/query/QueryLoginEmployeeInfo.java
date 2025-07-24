package delicacy.employee.query;

import org.apache.log4j.Logger;

import delicacy.common.AbstractQuery;
import delicacy.common.BaseCollection;
import delicacy.common.GenericBase;
import delicacy.common.KeyValuePair;
import delicacy.connection.ThreadConnection;
import delicacy.employee.bean.BaseLoginEmployeeInfo;
import delicacy.employee.bean.ConditionLoginEmployeeInfo;

public class QueryLoginEmployeeInfo extends AbstractQuery<BaseLoginEmployeeInfo, ConditionLoginEmployeeInfo> {

	private static final Logger __logger = Logger.getLogger(QueryLoginEmployeeInfo.class);

	public QueryLoginEmployeeInfo() throws java.sql.SQLException {
		setParameterNumber(5);
		setConnection(ThreadConnection.getConnection());
		setOrderByFields("a.employee_id");
	}

	@Override
	public BaseCollection<BaseLoginEmployeeInfo> executeQuery(KeyValuePair[] replacements,
			ConditionLoginEmployeeInfo condition) throws java.sql.SQLException {
		return executeQuery(replacements, condition.getUserAccount(), condition.getUserAccount(),
				condition.getUserAccount(), condition.getCompanyWeixin(), condition.getStatus());
	}

	@Override
	public BaseLoginEmployeeInfo generateBase(Object[] __data) throws java.sql.SQLException {
		BaseLoginEmployeeInfo __base = new BaseLoginEmployeeInfo();
		int count = 0;
		Object val;
		if ((val = __data[count++]) != null)
			__base.setEmployeeId(GenericBase.__getInt(val));
		if ((val = __data[count++]) != null)
			__base.setEmployeeNo(GenericBase.__getString(val));
		if ((val = __data[count++]) != null)
			__base.setEmployeeName(GenericBase.__getString(val));
		if ((val = __data[count++]) != null)
			__base.setEmployeePassword(GenericBase.__getString(val));
		if ((val = __data[count++]) != null)
			__base.setPhoto(GenericBase.__getString(val));
		if ((val = __data[count++]) != null)
			__base.setDepartmentId(GenericBase.__getInt(val));
		if ((val = __data[count++]) != null)
			__base.setPlateId(GenericBase.__getInt(val));
		if ((val = __data[count++]) != null)
			__base.setRoleId(GenericBase.__getInt(val));
		if((val = __data[count++]) != null)
			__base.setGradeId(GenericBase.__getInt(val));
		if((val = __data[count++]) != null)
			__base.setGradeName(GenericBase.__getString(val));
		if ((val = __data[count++]) != null)
			__base.setCompanyId(GenericBase.__getInt(val));
		if ((val = __data[count++]) != null)
			__base.setDutyId(GenericBase.__getInt(val));
		if ((val = __data[count++]) != null)
			__base.setDutyName(GenericBase.__getString(val));
		if ((val = __data[count++]) != null)
			__base.setCompanyNo(GenericBase.__getString(val));
		return __base;
	}

	@Override
	public int setStatementParameters(int count, java.lang.Object... args) throws java.sql.SQLException {

		int index = 0;
		for (int ii = 0; ii < args.length; ii++) {
			if (args[ii] == null)
				continue;
			index++;
			__logger.info(String.format("%1$s = [%2$s]", fieldNames[ii], args[ii]));
		}
		if (index > 0)
			__logger.info("=================================================");
		if (args[0] != null)
			__statement.setString(count++, GenericBase.__getString(args[0]));
		if (args[1] != null)
			__statement.setString(count++, GenericBase.__getString(args[1]));
		if (args[2] != null)
			__statement.setString(count++, GenericBase.__getString(args[2]));
		if (args[3] != null)
			__statement.setString(count++, GenericBase.__getString(args[3]));
		if (args[4] != null)
			__statement.setInt(count++, GenericBase.__getInt(args[4]));
		return count;
	}

	@Override
	public String getSQLText() {
		__logger.info(__SQLText);
		return __SQLText;
	}

	@Override
	public String getFieldList() {
		return RESULTSETFIELDLIST;
	}

	private final static String __SQLText = "SELECT a.employee_id, a.employee_no, a.employee_name, a.employee_password, a.photo, a.department_id, a.plate_id, a.role_id, a.grade_id, sd1.dic_type_value as grade_name, a.company_id, a.duty_id, d.duty_name, b.company_no FROM employees AS a LEFT JOIN company_records AS b ON a.company_id = b.company_record_id left join system_dictionary sd1 on sd1.dic_type_id = 3 and sd1.dic_type_value_id = a.grade_id left join duties d on d.duty_id = a.duty_id WHERE (a.employee_no = ? or a.mobile = ? or a.company_email = ?) and a.company_weixin = ? and a.status = ? limit 1" ;
	private final static String __originalSQL = "SELECT a.employee_id, a.employee_no, a.employee_name, a.employee_password, a.photo, a.department_id, a.plate_id, a.role_id, a.grade_id, sd1.dic_type_value as grade_name, a.company_id, a.duty_id, d.duty_name, b.company_no FROM employees AS a LEFT JOIN company_records AS b ON a.company_id = b.company_record_id left join system_dictionary sd1 on sd1.dic_type_id = 3 and sd1.dic_type_value_id = a.grade_id left join duties d on d.duty_id = a.duty_id WHERE (a.employee_no = :user_account OR a.mobile = :user_account OR a.company_email = :user_account) AND a.company_weixin = :company_weixin AND a.status = :status limit 1";
	private final static String[] __queryConditions = {
			"[{\"fullCondition\": \"(a.employee_no = :user_account OR a.mobile = :user_account OR a.company_email = :user_account) AND a.company_weixin = :company_weixin AND a.status = :status\",\"startIndex\": 269,\"stopIndex\":424,\"variableNum\": 0,\"variableCount\": 0,\"isor\":false,\"hasVariable\":false,\"hasBracket\":false,\"isVariableFirst\":false,\"topLevel\":true,\"children\": [{\"fullCondition\": \"(a.employee_no = :user_account OR a.mobile = :user_account OR a.company_email = :user_account)\",\"nextToken\":\"and\",\"startIndex\": 269,\"stopIndex\":362,\"variableNum\": 0,\"variableCount\": 0,\"isor\":true,\"hasVariable\":false,\"hasBracket\":true,\"isVariableFirst\":false,\"topLevel\":false,\"children\": [{\"fullCondition\": \"a.employee_no = :user_account\",\"fullFieldName\":\"a.employee_no\",\"operationName\":\"=\",\"variableName\":\"userAccount\",\"nextToken\":\"or\",\"startIndex\": 270,\"stopIndex\":298,\"variableNum\": 0,\"variableCount\": 1,\"isor\":false,\"hasVariable\":true,\"hasBracket\":false,\"isVariableFirst\":false,\"topLevel\":true,\"children\": []},{\"fullCondition\": \"a.mobile = :user_account\",\"fullFieldName\":\"a.mobile\",\"operationName\":\"=\",\"variableName\":\"userAccount\",\"nextToken\":\"or\",\"startIndex\": 303,\"stopIndex\":326,\"variableNum\": 1,\"variableCount\": 1,\"isor\":false,\"hasVariable\":true,\"hasBracket\":false,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []},{\"fullCondition\": \"a.company_email = :user_account\",\"fullFieldName\":\"a.company_email\",\"operationName\":\"=\",\"variableName\":\"userAccount\",\"startIndex\": 331,\"stopIndex\":361,\"variableNum\": 2,\"variableCount\": 1,\"isor\":false,\"hasVariable\":true,\"hasBracket\":false,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []}]},{\"fullCondition\": \"a.company_weixin = :company_weixin\",\"fullFieldName\":\"a.company_weixin\",\"operationName\":\"=\",\"variableName\":\"companyWeixin\",\"nextToken\":\"and\",\"startIndex\": 368,\"stopIndex\":401,\"variableNum\": 3,\"variableCount\": 1,\"isor\":false,\"hasVariable\":true,\"hasBracket\":false,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []},{\"fullCondition\": \"a.status = :status\",\"fullFieldName\":\"a.status\",\"operationName\":\"=\",\"variableName\":\"status\",\"startIndex\": 407,\"stopIndex\":424,\"variableNum\": 4,\"variableCount\": 1,\"isor\":false,\"hasVariable\":true,\"hasBracket\":false,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []}]}]"
	};
	private final static String RESULTSETFIELDLIST = "employee_id,employee_no,employee_name,employee_password,photo,department_id,plate_id,role_id,grade_id,grade_name,company_id,duty_id,duty_name,company_no";
	private final static String[] fieldNames = { "user_account", "user_account", "user_account", "company_weixin", "status"};
}
