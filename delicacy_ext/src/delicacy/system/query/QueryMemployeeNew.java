package delicacy.system.query;

import org.apache.log4j.Logger;
import delicacy.common.KeyValuePair;
import delicacy.common.AbstractQuery;
import delicacy.common.GenericBase;
import delicacy.common.BaseCollection;
import delicacy.connection.ThreadConnection;
import delicacy.system.bean.BaseMemployeeNew;
import delicacy.system.bean.ConditionMemployeeNew;

public class QueryMemployeeNew extends AbstractQuery<BaseMemployeeNew, ConditionMemployeeNew>
{

	private static final Logger __logger = Logger.getLogger(QueryMemployeeNew.class);

	public QueryMemployeeNew() throws java.sql.SQLException 
	{
		setParameterNumber(39);
		setConnection(ThreadConnection.getConnection());
		setOrderByFields("e.employee_id");
	}

	@Override
	public BaseCollection<BaseMemployeeNew> executeQuery( KeyValuePair[] replacements, ConditionMemployeeNew condition ) throws java.sql.SQLException {
		if(__queryConditions != null && __queryConditions.length > 0) return execute(replacements, condition);
		if(condition.getCurrentPage() > 0) {
			setCurrentPage(condition.getCurrentPage());
			setPageLines(condition.getPageLines());
		}
		return executeQuery(replacements, 
				condition.getPlateId(), 
				condition.getEmployeeNo(), 
				condition.getEmployeeName(), 
				condition.getDepartmentId(), 
				condition.getGradeid(), 
				condition.getStatus(), 
				condition.getIsDepartment(), 
				condition.getGender(), 
				condition.getDutyId(), 
				condition.getOnboardYear(), 
				condition.getOnboardMonth(), 
				condition.getResiYear(), 
				condition.getResiMonth(), 
				condition.getBirthMonth(), 
				condition.getIsCheck(), 
				condition.getMobile(), 
				condition.getEmail(), 
				condition.getCard(), 
				condition.getAddress(), 
				condition.getEducation(), 
				condition.getDegree(), 
				condition.getNationality(), 
				condition.getMarriedStatus(), 
				condition.getWorkaddress(), 
				condition.getBirthplace(), 
				condition.getAccountLocation(), 
				condition.getStartWorkYear(), 
				condition.getSocialComputerNumber(), 
				condition.getFundAccount(), 
				condition.getPositiveDate(), 
				condition.getTrytime(), 
				condition.getContractStartDate(), 
				condition.getContractStartMonth(), 
				condition.getContractEndYear(), 
				condition.getContractEndMonth(), 
				condition.getOwnedCompany(), 
				condition.getBankCardNum(), 
				condition.getCompanyWeixin(), 
				condition.getCompanyEmail()
			);
	}

	public BaseCollection<BaseMemployeeNew> runQuery( KeyValuePair[] replacements, ConditionMemployeeNew condition ) throws java.sql.SQLException {
		if(condition.getCurrentPage() > 0) {
			setCurrentPage(condition.getCurrentPage());
			setPageLines(condition.getPageLines());
		}
		return runQuery(replacements, 
				condition.getPlateId(), 
				condition.getEmployeeNo(), 
				condition.getEmployeeName(), 
				condition.getDepartmentId(), 
				condition.getGradeid(), 
				condition.getStatus(), 
				condition.getIsDepartment(), 
				condition.getGender(), 
				condition.getDutyId(), 
				condition.getOnboardYear(), 
				condition.getOnboardMonth(), 
				condition.getResiYear(), 
				condition.getResiMonth(), 
				condition.getBirthMonth(), 
				condition.getIsCheck(), 
				condition.getMobile(), 
				condition.getEmail(), 
				condition.getCard(), 
				condition.getAddress(), 
				condition.getEducation(), 
				condition.getDegree(), 
				condition.getNationality(), 
				condition.getMarriedStatus(), 
				condition.getWorkaddress(), 
				condition.getBirthplace(), 
				condition.getAccountLocation(), 
				condition.getStartWorkYear(), 
				condition.getSocialComputerNumber(), 
				condition.getFundAccount(), 
				condition.getPositiveDate(), 
				condition.getTrytime(), 
				condition.getContractStartDate(), 
				condition.getContractStartMonth(), 
				condition.getContractEndYear(), 
				condition.getContractEndMonth(), 
				condition.getOwnedCompany(), 
				condition.getBankCardNum(), 
				condition.getCompanyWeixin(), 
				condition.getCompanyEmail()
			);
	}

	public BaseCollection<BaseMemployeeNew> execute( KeyValuePair[] replacements, ConditionMemployeeNew condition ) throws java.sql.SQLException {
		if(__queryConditions == null || __queryConditions.length == 0) return executeQuery(replacements, condition);
		if(condition.getCurrentPage() > 0) {
			setCurrentPage(condition.getCurrentPage());
			setPageLines(condition.getPageLines());
		}
		return execute(condition, replacements, 
				condition.getPlateId(), 
				condition.getEmployeeNo(), 
				condition.getEmployeeName(), 
				condition.getDepartmentId(), 
				condition.getGradeid(), 
				condition.getStatus(), 
				condition.getIsDepartment(), 
				condition.getGender(), 
				condition.getDutyId(), 
				condition.getOnboardYear(), 
				condition.getOnboardMonth(), 
				condition.getResiYear(), 
				condition.getResiMonth(), 
				condition.getBirthMonth(), 
				condition.getIsCheck(), 
				condition.getMobile(), 
				condition.getEmail(), 
				condition.getCard(), 
				condition.getAddress(), 
				condition.getEducation(), 
				condition.getDegree(), 
				condition.getNationality(), 
				condition.getMarriedStatus(), 
				condition.getWorkaddress(), 
				condition.getBirthplace(), 
				condition.getAccountLocation(), 
				condition.getStartWorkYear(), 
				condition.getSocialComputerNumber(), 
				condition.getFundAccount(), 
				condition.getPositiveDate(), 
				condition.getTrytime(), 
				condition.getContractStartDate(), 
				condition.getContractStartMonth(), 
				condition.getContractEndYear(), 
				condition.getContractEndMonth(), 
				condition.getOwnedCompany(), 
				condition.getBankCardNum(), 
				condition.getCompanyWeixin(), 
				condition.getCompanyEmail()
			);
	}

	@Override
	public BaseMemployeeNew generateBase(Object[] __data) throws java.sql.SQLException {
		BaseMemployeeNew __base = new BaseMemployeeNew();
		int count = 0;
		Object val;
		if((val = __data[count++]) != null) __base.setEmployeeId(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setPhoto(GenericBase.__getString(val));
		if((val = __data[count++]) != null) __base.setEmployeeNo(GenericBase.__getString(val));
		if((val = __data[count++]) != null) __base.setEmployeeName(GenericBase.__getString(val));
		if((val = __data[count++]) != null) __base.setDepartmentId(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setRoleId(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setGradeId(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setEmployeePassword(GenericBase.__getString(val));
		if((val = __data[count++]) != null) __base.setMobile(GenericBase.__getString(val));
		if((val = __data[count++]) != null) __base.setPhone(GenericBase.__getString(val));
		if((val = __data[count++]) != null) __base.setQq(GenericBase.__getString(val));
		if((val = __data[count++]) != null) __base.setEmail(GenericBase.__getString(val));
		if((val = __data[count++]) != null) __base.setOnboardDate(GenericBase.__getDateFromSQL(val));
		if((val = __data[count++]) != null) __base.setResignationDate(GenericBase.__getDateFromSQL(val));
		if((val = __data[count++]) != null) __base.setStatus(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setUsableStatus(GenericBase.__getBoolean(val));
		if((val = __data[count++]) != null) __base.setIsDepartment(GenericBase.__getBoolean(val));
		if((val = __data[count++]) != null) __base.setGender(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setAutograph(GenericBase.__getString(val));
		if((val = __data[count++]) != null) __base.setAge(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setBirth(GenericBase.__getDateFromSQL(val));
		if((val = __data[count++]) != null) __base.setCard(GenericBase.__getString(val));
		if((val = __data[count++]) != null) __base.setAddress(GenericBase.__getString(val));
		if((val = __data[count++]) != null) __base.setLocked(GenericBase.__getBoolean(val));
		if((val = __data[count++]) != null) __base.setPlateId(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setDutyId(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setUserAcct(GenericBase.__getString(val));
		if((val = __data[count++]) != null) __base.setEmployeeNameEn(GenericBase.__getString(val));
		if((val = __data[count++]) != null) __base.setEducation(GenericBase.__getString(val));
		if((val = __data[count++]) != null) __base.setDegree(GenericBase.__getString(val));
		if((val = __data[count++]) != null) __base.setNationality(GenericBase.__getString(val));
		if((val = __data[count++]) != null) __base.setMarriedStatus(GenericBase.__getString(val));
		if((val = __data[count++]) != null) __base.setHealth(GenericBase.__getString(val));
		if((val = __data[count++]) != null) __base.setWorkAddress(GenericBase.__getString(val));
		if((val = __data[count++]) != null) __base.setRegisteredAddress(GenericBase.__getString(val));
		if((val = __data[count++]) != null) __base.setEmployeeRoleNames(GenericBase.__getString(val));
		if((val = __data[count++]) != null) __base.setOwnedCompany(GenericBase.__getString(val));
		if((val = __data[count++]) != null) __base.setCompanyAddress(GenericBase.__getString(val));
		if((val = __data[count++]) != null) __base.setCompanyPhone(GenericBase.__getString(val));
		if((val = __data[count++]) != null) __base.setZipCode(GenericBase.__getString(val));
		return __base;
	}

	@Override
	public int setStatementParameters(int count, Object ... args) throws java.sql.SQLException {

		int index = 0;
		for(int ii = 0; ii < args.length; ii++){
			if(args[ii] == null) continue;
			index++;
			__logger.info(String.format("%1$s = [%2$s]", fieldNames[ii], args[ii]));
		}
		if( index > 0 ) __logger.info("=================================================");
		if(args[0] != null) __statement.setInt(count++, GenericBase.__getInt(args[0]));
		if(args[1] != null) __statement.setString(count++, GenericBase.__getString(args[1]));
		if(args[2] != null) __statement.setString(count++, GenericBase.__getString(args[2]));
		if(args[3] != null) __statement.setInt(count++, GenericBase.__getInt(args[3]));
		if(args[4] != null) __statement.setInt(count++, GenericBase.__getInt(args[4]));
		if(args[5] != null) __statement.setInt(count++, GenericBase.__getInt(args[5]));
		if(args[6] != null) __statement.setBoolean(count++, GenericBase.__getBoolean(args[6]));
		if(args[7] != null) __statement.setInt(count++, GenericBase.__getInt(args[7]));
		if(args[8] != null) __statement.setInt(count++, GenericBase.__getInt(args[8]));
		if(args[9] != null) __statement.setInt(count++, GenericBase.__getInt(args[9]));
		if(args[10] != null) __statement.setInt(count++, GenericBase.__getInt(args[10]));
		if(args[11] != null) __statement.setInt(count++, GenericBase.__getInt(args[11]));
		if(args[12] != null) __statement.setInt(count++, GenericBase.__getInt(args[12]));
		if(args[13] != null) __statement.setInt(count++, GenericBase.__getInt(args[13]));
		if(args[14] != null) __statement.setBoolean(count++, GenericBase.__getBoolean(args[14]));
		if(args[15] != null) __statement.setString(count++, GenericBase.__getString(args[15]));
		if(args[16] != null) __statement.setString(count++, GenericBase.__getString(args[16]));
		if(args[17] != null) __statement.setString(count++, GenericBase.__getString(args[17]));
		if(args[18] != null) __statement.setString(count++, GenericBase.__getString(args[18]));
		if(args[19] != null) __statement.setString(count++, GenericBase.__getString(args[19]));
		if(args[20] != null) __statement.setString(count++, GenericBase.__getString(args[20]));
		if(args[21] != null) __statement.setString(count++, GenericBase.__getString(args[21]));
		if(args[22] != null) __statement.setString(count++, GenericBase.__getString(args[22]));
		if(args[23] != null) __statement.setString(count++, GenericBase.__getString(args[23]));
		if(args[24] != null) __statement.setString(count++, GenericBase.__getString(args[24]));
		if(args[25] != null) __statement.setString(count++, GenericBase.__getString(args[25]));
		if(args[26] != null) __statement.setInt(count++, GenericBase.__getInt(args[26]));
		if(args[27] != null) __statement.setString(count++, GenericBase.__getString(args[27]));
		if(args[28] != null) __statement.setString(count++, GenericBase.__getString(args[28]));
		if(args[29] != null) __statement.setTimestamp(count++, generateTimestampFromDate((java.util.Date)args[29]));
		if(args[30] != null) __statement.setString(count++, GenericBase.__getString(args[30]));
		if(args[31] != null) __statement.setInt(count++, GenericBase.__getInt(args[31]));
		if(args[32] != null) __statement.setInt(count++, GenericBase.__getInt(args[32]));
		if(args[33] != null) __statement.setInt(count++, GenericBase.__getInt(args[33]));
		if(args[34] != null) __statement.setInt(count++, GenericBase.__getInt(args[34]));
		if(args[35] != null) __statement.setString(count++, GenericBase.__getString(args[35]));
		if(args[36] != null) __statement.setString(count++, GenericBase.__getString(args[36]));
		if(args[37] != null) __statement.setString(count++, GenericBase.__getString(args[37]));
		if(args[38] != null) __statement.setString(count++, GenericBase.__getString(args[38]));
		return count;
	}

	@Override
	public String getSQLText() {
		return __SQLText;
	}

	@Override
	public String getOriginalSQLText() {
		return __originalSQL;
	}

	@Override
	public String[] getQueryStringArray()  {
		return __queryConditions;
	}

	@Override
	public String getFieldList(){
		return RESULTSETFIELDLIST;
	}

	@Override
	public String[] getConditionNames(){
		return fieldNames;
	}

	private final static String __SQLText = "SELECT e.employee_id, CASE WHEN e.photo IS NULL THEN CONCAT ( s.image_path, 'photo_view.png' ) ELSE e.photo END AS photo, e.employee_no, e.employee_name, e.department_id, e.role_id, e.grade_id, e.employee_password, e.mobile, e.phone, e.qq, e.email, e.onboard_date, e.resignation_date, e.status, e.usable_status, e.is_department, e.gender, e.autograph, e.age, e.birth, e.card, e.address, e.locked, e.plate_id, e.duty_id, e.user_acct, e.employee_name_en, e.education, e.DEGREE, e.nationality, e.married_status, e.health, e.work_address, e.registered_address, ( SELECT array_to_string( ARRAY ( SELECT b.role_name FROM employee_roles A LEFT JOIN roles b ON A.role_id = b.role_id WHERE A.employee_id = e.employee_id AND b.application_id = 1 ), ',' ) ) AS employee_role_names, e.owned_company, cr.address as company_address, cr.phone as company_phone, cr.zip_code FROM employees e LEFT JOIN system_config s ON s.system_config_id = 1 LEFT JOIN company_records cr ON cr.company_record_id = e.company_id WHERE e.is_manager = FALSE and e.plate_id = ? and e.employee_no like ? and e.employee_name like ? and e.department_id IN ( SELECT child_id FROM department_ids WHERE department_id = ? ) and 2 = 2 and e.grade_id = ? and e.status = ? and e.is_department = ? and e.gender = ? and e.duty_id = ? and ( EXTRACT ( YEAR FROM onboard_date )) = ? and ( EXTRACT ( MONTH FROM onboard_date )) = ? and ( EXTRACT ( YEAR FROM resignation_date )) = ? and ( EXTRACT ( MONTH FROM resignation_date )) = ? and ( EXTRACT ( MONTH FROM birth )) = ? and e.is_check = ? and e.mobile = ? and e.email like ? and e.card like ? and e.address like ? and e.education = ? and e.DEGREE = ? and e.nationality = ? and e.married_status = ? and e.work_address = ? and e.birthplace = ? and e.account_location = ? and ( EXTRACT ( YEAR FROM start_work_date )) = ? and e.social_computer_number = ? and e.fund_account = ? and e.positive_date > ? and e.try_time = ? and ( EXTRACT ( YEAR FROM contract_start_date )) = ? and ( EXTRACT ( MONTH FROM contract_start_date )) = ? and ( EXTRACT ( YEAR FROM contract_end_date )) = ? and ( EXTRACT ( MONTH FROM contract_end_date )) = ? and e.owned_company = ? and e.bank_card_num = ? and e.company_weixin = ? and e.company_email = ? ORDER BY e.plate_id ASC, e.department_id ASC" ;
	private final static String __originalSQL = "SELECT e.employee_id, CASE WHEN e.photo IS NULL THEN CONCAT ( s.image_path, 'photo_view.png' ) ELSE e.photo END AS photo, e.employee_no, e.employee_name, e.department_id, e.role_id, e.grade_id, e.employee_password, e.mobile, e.phone, e.qq, e.email, e.onboard_date, e.resignation_date, e.status, e.usable_status, e.is_department, e.gender, e.autograph, e.age, e.birth, e.card, e.address, e.locked, e.plate_id, e.duty_id, e.user_acct, e.employee_name_en, e.education, e.DEGREE, e.nationality, e.married_status, e.health, e.work_address, e.registered_address, ( SELECT array_to_string( ARRAY ( SELECT b.role_name FROM employee_roles A LEFT JOIN roles b ON A.role_id = b.role_id WHERE A.employee_id = e.employee_id AND b.application_id = 1 ), ',' ) ) AS employee_role_names, e.owned_company, cr.address as company_address, cr.phone as company_phone, cr.zip_code FROM employees e LEFT JOIN system_config s ON s.system_config_id = 1 LEFT JOIN company_records cr ON cr.company_record_id = e.company_id WHERE e.is_manager = FALSE AND e.plate_id = :plate_id AND e.employee_no LIKE :employee_no AND e.employee_name LIKE :employee_name AND e.department_id IN ( SELECT child_id FROM department_ids WHERE department_id = :department_id ) AND 2 = 2 AND e.grade_id = :gradeId AND e.status = :status AND e.is_department = :is_department AND e.gender = :gender AND e.duty_id = :duty_id AND ( EXTRACT ( YEAR FROM onboard_date )) = :onboard_year AND ( EXTRACT ( MONTH FROM onboard_date )) = :onboard_month AND ( EXTRACT ( YEAR FROM resignation_date )) = :resi_year AND ( EXTRACT ( MONTH FROM resignation_date )) = :resi_month AND ( EXTRACT ( MONTH FROM birth )) = :birth_month AND e.is_check = :is_check AND e.mobile = :mobile AND e.email LIKE :email AND e.card LIKE :card AND e.address LIKE :address AND e.education = :education AND e.DEGREE = :DEGREE AND e.nationality = :nationality AND e.married_status = :married_status AND e.work_address = :workAddress AND e.birthplace = :birthplace AND e.account_location = :account_location AND ( EXTRACT ( YEAR FROM start_work_date )) = :start_work_year AND e.social_computer_number = :social_computer_number AND e.fund_account = :fund_account AND e.positive_date > :positive_date AND e.try_time = :tryTime AND ( EXTRACT ( YEAR FROM contract_start_date )) = :contract_start_date AND ( EXTRACT ( MONTH FROM contract_start_date )) = :contract_start_month AND ( EXTRACT ( YEAR FROM contract_end_date )) = :contract_end_year AND ( EXTRACT ( MONTH FROM contract_end_date )) = :contract_end_month AND e.owned_company = :owned_company AND e.bank_card_num = :bank_card_num AND e.company_weixin = :company_weixin AND e.company_email = :company_email ORDER BY e.plate_id ASC, e.department_id ASC";
	private final static String[] __queryConditions = {
		"[{\"fullCondition\": \"e.is_manager = FALSE AND e.plate_id = :plate_id AND e.employee_no LIKE :employee_no AND e.employee_name LIKE :employee_name AND e.department_id IN ( SELECT child_id FROM department_ids WHERE department_id = :department_id ) AND 2 = 2 AND e.grade_id = :gradeId AND e.status = :status AND e.is_department = :is_department AND e.gender = :gender AND e.duty_id = :duty_id AND ( EXTRACT ( YEAR FROM onboard_date )) = :onboard_year AND ( EXTRACT ( MONTH FROM onboard_date )) = :onboard_month AND ( EXTRACT ( YEAR FROM resignation_date )) = :resi_year AND ( EXTRACT ( MONTH FROM resignation_date )) = :resi_month AND ( EXTRACT ( MONTH FROM birth )) = :birth_month AND e.is_check = :is_check AND e.mobile = :mobile AND e.email LIKE :email AND e.card LIKE :card AND e.address LIKE :address AND e.education = :education AND e.DEGREE = :DEGREE AND e.nationality = :nationality AND e.married_status = :married_status AND e.work_address = :workAddress AND e.birthplace = :birthplace AND e.account_location = :account_location AND ( EXTRACT ( YEAR FROM start_work_date )) = :start_work_year AND e.social_computer_number = :social_computer_number AND e.fund_account = :fund_account AND e.positive_date > :positive_date AND e.try_time = :tryTime AND ( EXTRACT ( YEAR FROM contract_start_date )) = :contract_start_date AND ( EXTRACT ( MONTH FROM contract_start_date )) = :contract_start_month AND ( EXTRACT ( YEAR FROM contract_end_date )) = :contract_end_year AND ( EXTRACT ( MONTH FROM contract_end_date )) = :contract_end_month AND e.owned_company = :owned_company AND e.bank_card_num = :bank_card_num AND e.company_weixin = :company_weixin AND e.company_email = :company_email\",\"startIndex\": 1001,\"stopIndex\":2663,\"variableNum\": 0,\"variableCount\": 0,\"isor\":false,\"hasVariable\":false,\"hasBracket\":false,\"isVariableFirst\":false,\"topLevel\":true,\"children\": [{\"fullCondition\": \"e.is_manager = FALSE\",\"nextToken\":\"and\",\"startIndex\": 1001,\"stopIndex\":1020,\"variableNum\": 0,\"variableCount\": 0,\"isor\":false,\"hasVariable\":false,\"hasBracket\":false,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []},{\"fullCondition\": \"e.plate_id = :plate_id\",\"fullFieldName\":\"e.plate_id\",\"operationName\":\"=\",\"variableName\":\"plateId\",\"nextToken\":\"and\",\"startIndex\": 1026,\"stopIndex\":1047,\"variableNum\": 0,\"variableCount\": 1,\"isor\":false,\"hasVariable\":true,\"hasBracket\":false,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []},{\"fullCondition\": \"e.employee_no LIKE :employee_no\",\"fullFieldName\":\"e.employee_no\",\"operationName\":\"like\",\"variableName\":\"employeeNo\",\"nextToken\":\"and\",\"startIndex\": 1053,\"stopIndex\":1083,\"variableNum\": 1,\"variableCount\": 1,\"isor\":false,\"hasVariable\":true,\"hasBracket\":false,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []},{\"fullCondition\": \"e.employee_name LIKE :employee_name\",\"fullFieldName\":\"e.employee_name\",\"operationName\":\"like\",\"variableName\":\"employeeName\",\"nextToken\":\"and\",\"startIndex\": 1089,\"stopIndex\":1123,\"variableNum\": 2,\"variableCount\": 1,\"isor\":false,\"hasVariable\":true,\"hasBracket\":false,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []},{\"fullCondition\": \"e.department_id IN ( SELECT child_id FROM department_ids WHERE department_id = :department_id )\",\"nextToken\":\"and\",\"startIndex\": 1129,\"stopIndex\":1223,\"variableNum\": 0,\"variableCount\": 0,\"isor\":false,\"hasVariable\":false,\"hasBracket\":false,\"isVariableFirst\":false,\"topLevel\":false,\"children\": [{\"fullCondition\": \"department_id = :department_id\",\"startIndex\": 1192,\"stopIndex\":1221,\"variableNum\": 0,\"variableCount\": 0,\"isor\":false,\"hasVariable\":false,\"hasBracket\":false,\"isVariableFirst\":false,\"topLevel\":true,\"children\": [{\"fullCondition\": \"department_id = :department_id\",\"fullFieldName\":\"department_id\",\"operationName\":\"=\",\"variableName\":\"departmentId\",\"startIndex\": 1192,\"stopIndex\":1221,\"variableNum\": 3,\"variableCount\": 1,\"isor\":false,\"hasVariable\":true,\"hasBracket\":false,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []}]}]},{\"fullCondition\": \"2 = 2\",\"nextToken\":\"and\",\"startIndex\": 1229,\"stopIndex\":1233,\"variableNum\": 0,\"variableCount\": 0,\"isor\":false,\"hasVariable\":false,\"hasBracket\":false,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []},{\"fullCondition\": \"e.grade_id = :gradeId\",\"fullFieldName\":\"e.grade_id\",\"operationName\":\"=\",\"variableName\":\"gradeid\",\"nextToken\":\"and\",\"startIndex\": 1239,\"stopIndex\":1259,\"variableNum\": 4,\"variableCount\": 1,\"isor\":false,\"hasVariable\":true,\"hasBracket\":false,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []},{\"fullCondition\": \"e.status = :status\",\"fullFieldName\":\"e.status\",\"operationName\":\"=\",\"variableName\":\"status\",\"nextToken\":\"and\",\"startIndex\": 1265,\"stopIndex\":1282,\"variableNum\": 5,\"variableCount\": 1,\"isor\":false,\"hasVariable\":true,\"hasBracket\":false,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []},{\"fullCondition\": \"e.is_department = :is_department\",\"fullFieldName\":\"e.is_department\",\"operationName\":\"=\",\"variableName\":\"isDepartment\",\"nextToken\":\"and\",\"startIndex\": 1288,\"stopIndex\":1319,\"variableNum\": 6,\"variableCount\": 1,\"isor\":false,\"hasVariable\":true,\"hasBracket\":false,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []},{\"fullCondition\": \"e.gender = :gender\",\"fullFieldName\":\"e.gender\",\"operationName\":\"=\",\"variableName\":\"gender\",\"nextToken\":\"and\",\"startIndex\": 1325,\"stopIndex\":1342,\"variableNum\": 7,\"variableCount\": 1,\"isor\":false,\"hasVariable\":true,\"hasBracket\":false,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []},{\"fullCondition\": \"e.duty_id = :duty_id\",\"fullFieldName\":\"e.duty_id\",\"operationName\":\"=\",\"variableName\":\"dutyId\",\"nextToken\":\"and\",\"startIndex\": 1348,\"stopIndex\":1367,\"variableNum\": 8,\"variableCount\": 1,\"isor\":false,\"hasVariable\":true,\"hasBracket\":false,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []},{\"fullCondition\": \"( EXTRACT ( YEAR FROM onboard_date )) = :onboard_year\",\"fullFieldName\":\"( EXTRACT ( YEAR FROM onboard_date ))\",\"operationName\":\"=\",\"variableName\":\"onboardYear\",\"nextToken\":\"and\",\"startIndex\": 1373,\"stopIndex\":1425,\"variableNum\": 9,\"variableCount\": 1,\"isor\":false,\"hasVariable\":true,\"hasBracket\":true,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []},{\"fullCondition\": \"( EXTRACT ( MONTH FROM onboard_date )) = :onboard_month\",\"fullFieldName\":\"( EXTRACT ( MONTH FROM onboard_date ))\",\"operationName\":\"=\",\"variableName\":\"onboardMonth\",\"nextToken\":\"and\",\"startIndex\": 1431,\"stopIndex\":1485,\"variableNum\": 10,\"variableCount\": 1,\"isor\":false,\"hasVariable\":true,\"hasBracket\":true,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []},{\"fullCondition\": \"( EXTRACT ( YEAR FROM resignation_date )) = :resi_year\",\"fullFieldName\":\"( EXTRACT ( YEAR FROM resignation_date ))\",\"operationName\":\"=\",\"variableName\":\"resiYear\",\"nextToken\":\"and\",\"startIndex\": 1491,\"stopIndex\":1544,\"variableNum\": 11,\"variableCount\": 1,\"isor\":false,\"hasVariable\":true,\"hasBracket\":true,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []},{\"fullCondition\": \"( EXTRACT ( MONTH FROM resignation_date )) = :resi_month\",\"fullFieldName\":\"( EXTRACT ( MONTH FROM resignation_date ))\",\"operationName\":\"=\",\"variableName\":\"resiMonth\",\"nextToken\":\"and\",\"startIndex\": 1550,\"stopIndex\":1605,\"variableNum\": 12,\"variableCount\": 1,\"isor\":false,\"hasVariable\":true,\"hasBracket\":true,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []},{\"fullCondition\": \"( EXTRACT ( MONTH FROM birth )) = :birth_month\",\"fullFieldName\":\"( EXTRACT ( MONTH FROM birth ))\",\"operationName\":\"=\",\"variableName\":\"birthMonth\",\"nextToken\":\"and\",\"startIndex\": 1611,\"stopIndex\":1656,\"variableNum\": 13,\"variableCount\": 1,\"isor\":false,\"hasVariable\":true,\"hasBracket\":true,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []},{\"fullCondition\": \"e.is_check = :is_check\",\"fullFieldName\":\"e.is_check\",\"operationName\":\"=\",\"variableName\":\"isCheck\",\"nextToken\":\"and\",\"startIndex\": 1662,\"stopIndex\":1683,\"variableNum\": 14,\"variableCount\": 1,\"isor\":false,\"hasVariable\":true,\"hasBracket\":false,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []},{\"fullCondition\": \"e.mobile = :mobile\",\"fullFieldName\":\"e.mobile\",\"operationName\":\"=\",\"variableName\":\"mobile\",\"nextToken\":\"and\",\"startIndex\": 1689,\"stopIndex\":1706,\"variableNum\": 15,\"variableCount\": 1,\"isor\":false,\"hasVariable\":true,\"hasBracket\":false,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []},{\"fullCondition\": \"e.email LIKE :email\",\"fullFieldName\":\"e.email\",\"operationName\":\"like\",\"variableName\":\"email\",\"nextToken\":\"and\",\"startIndex\": 1712,\"stopIndex\":1730,\"variableNum\": 16,\"variableCount\": 1,\"isor\":false,\"hasVariable\":true,\"hasBracket\":false,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []},{\"fullCondition\": \"e.card LIKE :card\",\"fullFieldName\":\"e.card\",\"operationName\":\"like\",\"variableName\":\"card\",\"nextToken\":\"and\",\"startIndex\": 1736,\"stopIndex\":1752,\"variableNum\": 17,\"variableCount\": 1,\"isor\":false,\"hasVariable\":true,\"hasBracket\":false,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []},{\"fullCondition\": \"e.address LIKE :address\",\"fullFieldName\":\"e.address\",\"operationName\":\"like\",\"variableName\":\"address\",\"nextToken\":\"and\",\"startIndex\": 1758,\"stopIndex\":1780,\"variableNum\": 18,\"variableCount\": 1,\"isor\":false,\"hasVariable\":true,\"hasBracket\":false,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []},{\"fullCondition\": \"e.education = :education\",\"fullFieldName\":\"e.education\",\"operationName\":\"=\",\"variableName\":\"education\",\"nextToken\":\"and\",\"startIndex\": 1786,\"stopIndex\":1809,\"variableNum\": 19,\"variableCount\": 1,\"isor\":false,\"hasVariable\":true,\"hasBracket\":false,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []},{\"fullCondition\": \"e.DEGREE = :DEGREE\",\"fullFieldName\":\"e.DEGREE\",\"operationName\":\"=\",\"variableName\":\"degree\",\"nextToken\":\"and\",\"startIndex\": 1815,\"stopIndex\":1832,\"variableNum\": 20,\"variableCount\": 1,\"isor\":false,\"hasVariable\":true,\"hasBracket\":false,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []},{\"fullCondition\": \"e.nationality = :nationality\",\"fullFieldName\":\"e.nationality\",\"operationName\":\"=\",\"variableName\":\"nationality\",\"nextToken\":\"and\",\"startIndex\": 1838,\"stopIndex\":1865,\"variableNum\": 21,\"variableCount\": 1,\"isor\":false,\"hasVariable\":true,\"hasBracket\":false,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []},{\"fullCondition\": \"e.married_status = :married_status\",\"fullFieldName\":\"e.married_status\",\"operationName\":\"=\",\"variableName\":\"marriedStatus\",\"nextToken\":\"and\",\"startIndex\": 1871,\"stopIndex\":1904,\"variableNum\": 22,\"variableCount\": 1,\"isor\":false,\"hasVariable\":true,\"hasBracket\":false,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []},{\"fullCondition\": \"e.work_address = :workAddress\",\"fullFieldName\":\"e.work_address\",\"operationName\":\"=\",\"variableName\":\"workaddress\",\"nextToken\":\"and\",\"startIndex\": 1910,\"stopIndex\":1938,\"variableNum\": 23,\"variableCount\": 1,\"isor\":false,\"hasVariable\":true,\"hasBracket\":false,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []},{\"fullCondition\": \"e.birthplace = :birthplace\",\"fullFieldName\":\"e.birthplace\",\"operationName\":\"=\",\"variableName\":\"birthplace\",\"nextToken\":\"and\",\"startIndex\": 1944,\"stopIndex\":1969,\"variableNum\": 24,\"variableCount\": 1,\"isor\":false,\"hasVariable\":true,\"hasBracket\":false,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []},{\"fullCondition\": \"e.account_location = :account_location\",\"fullFieldName\":\"e.account_location\",\"operationName\":\"=\",\"variableName\":\"accountLocation\",\"nextToken\":\"and\",\"startIndex\": 1975,\"stopIndex\":2012,\"variableNum\": 25,\"variableCount\": 1,\"isor\":false,\"hasVariable\":true,\"hasBracket\":false,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []},{\"fullCondition\": \"( EXTRACT ( YEAR FROM start_work_date )) = :start_work_year\",\"fullFieldName\":\"( EXTRACT ( YEAR FROM start_work_date ))\",\"operationName\":\"=\",\"variableName\":\"startWorkYear\",\"nextToken\":\"and\",\"startIndex\": 2018,\"stopIndex\":2076,\"variableNum\": 26,\"variableCount\": 1,\"isor\":false,\"hasVariable\":true,\"hasBracket\":true,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []},{\"fullCondition\": \"e.social_computer_number = :social_computer_number\",\"fullFieldName\":\"e.social_computer_number\",\"operationName\":\"=\",\"variableName\":\"socialComputerNumber\",\"nextToken\":\"and\",\"startIndex\": 2082,\"stopIndex\":2131,\"variableNum\": 27,\"variableCount\": 1,\"isor\":false,\"hasVariable\":true,\"hasBracket\":false,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []},{\"fullCondition\": \"e.fund_account = :fund_account\",\"fullFieldName\":\"e.fund_account\",\"operationName\":\"=\",\"variableName\":\"fundAccount\",\"nextToken\":\"and\",\"startIndex\": 2137,\"stopIndex\":2166,\"variableNum\": 28,\"variableCount\": 1,\"isor\":false,\"hasVariable\":true,\"hasBracket\":false,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []},{\"fullCondition\": \"e.positive_date > :positive_date\",\"fullFieldName\":\"e.positive_date\",\"operationName\":\">\",\"variableName\":\"positiveDate\",\"nextToken\":\"and\",\"startIndex\": 2172,\"stopIndex\":2203,\"variableNum\": 29,\"variableCount\": 1,\"isor\":false,\"hasVariable\":true,\"hasBracket\":false,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []},{\"fullCondition\": \"e.try_time = :tryTime\",\"fullFieldName\":\"e.try_time\",\"operationName\":\"=\",\"variableName\":\"trytime\",\"nextToken\":\"and\",\"startIndex\": 2209,\"stopIndex\":2229,\"variableNum\": 30,\"variableCount\": 1,\"isor\":false,\"hasVariable\":true,\"hasBracket\":false,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []},{\"fullCondition\": \"( EXTRACT ( YEAR FROM contract_start_date )) = :contract_start_date\",\"fullFieldName\":\"( EXTRACT ( YEAR FROM contract_start_date ))\",\"operationName\":\"=\",\"variableName\":\"contractStartDate\",\"nextToken\":\"and\",\"startIndex\": 2235,\"stopIndex\":2301,\"variableNum\": 31,\"variableCount\": 1,\"isor\":false,\"hasVariable\":true,\"hasBracket\":true,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []},{\"fullCondition\": \"( EXTRACT ( MONTH FROM contract_start_date )) = :contract_start_month\",\"fullFieldName\":\"( EXTRACT ( MONTH FROM contract_start_date ))\",\"operationName\":\"=\",\"variableName\":\"contractStartMonth\",\"nextToken\":\"and\",\"startIndex\": 2307,\"stopIndex\":2375,\"variableNum\": 32,\"variableCount\": 1,\"isor\":false,\"hasVariable\":true,\"hasBracket\":true,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []},{\"fullCondition\": \"( EXTRACT ( YEAR FROM contract_end_date )) = :contract_end_year\",\"fullFieldName\":\"( EXTRACT ( YEAR FROM contract_end_date ))\",\"operationName\":\"=\",\"variableName\":\"contractEndYear\",\"nextToken\":\"and\",\"startIndex\": 2381,\"stopIndex\":2443,\"variableNum\": 33,\"variableCount\": 1,\"isor\":false,\"hasVariable\":true,\"hasBracket\":true,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []},{\"fullCondition\": \"( EXTRACT ( MONTH FROM contract_end_date )) = :contract_end_month\",\"fullFieldName\":\"( EXTRACT ( MONTH FROM contract_end_date ))\",\"operationName\":\"=\",\"variableName\":\"contractEndMonth\",\"nextToken\":\"and\",\"startIndex\": 2449,\"stopIndex\":2513,\"variableNum\": 34,\"variableCount\": 1,\"isor\":false,\"hasVariable\":true,\"hasBracket\":true,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []},{\"fullCondition\": \"e.owned_company = :owned_company\",\"fullFieldName\":\"e.owned_company\",\"operationName\":\"=\",\"variableName\":\"ownedCompany\",\"nextToken\":\"and\",\"startIndex\": 2519,\"stopIndex\":2550,\"variableNum\": 35,\"variableCount\": 1,\"isor\":false,\"hasVariable\":true,\"hasBracket\":false,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []},{\"fullCondition\": \"e.bank_card_num = :bank_card_num\",\"fullFieldName\":\"e.bank_card_num\",\"operationName\":\"=\",\"variableName\":\"bankCardNum\",\"nextToken\":\"and\",\"startIndex\": 2556,\"stopIndex\":2587,\"variableNum\": 36,\"variableCount\": 1,\"isor\":false,\"hasVariable\":true,\"hasBracket\":false,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []},{\"fullCondition\": \"e.company_weixin = :company_weixin\",\"fullFieldName\":\"e.company_weixin\",\"operationName\":\"=\",\"variableName\":\"companyWeixin\",\"nextToken\":\"and\",\"startIndex\": 2593,\"stopIndex\":2626,\"variableNum\": 37,\"variableCount\": 1,\"isor\":false,\"hasVariable\":true,\"hasBracket\":false,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []},{\"fullCondition\": \"e.company_email = :company_email\",\"fullFieldName\":\"e.company_email\",\"operationName\":\"=\",\"variableName\":\"companyEmail\",\"startIndex\": 2632,\"stopIndex\":2663,\"variableNum\": 38,\"variableCount\": 1,\"isor\":false,\"hasVariable\":true,\"hasBracket\":false,\"isVariableFirst\":false,\"topLevel\":false,\"children\": []}]}]"
	};
	private final static String RESULTSETFIELDLIST = "employee_id,photo,employee_no,employee_name,department_id,role_id,grade_id,employee_password,mobile,phone,qq,email,onboard_date,resignation_date,status,usable_status,is_department,gender,autograph,age,birth,card,address,locked,plate_id,duty_id,user_acct,employee_name_en,education,degree,nationality,married_status,health,work_address,registered_address,employee_role_names,owned_company,company_address,company_phone,zip_code";
	private final static String[] fieldNames = { "plate_id", "employee_no", "employee_name", "department_id", "gradeid", "status", "is_department", "gender", "duty_id", "onboard_year", "onboard_month", "resi_year", "resi_month", "birth_month", "is_check", "mobile", "email", "card", "address", "education", "degree", "nationality", "married_status", "workaddress", "birthplace", "account_location", "start_work_year", "social_computer_number", "fund_account", "positive_date", "trytime", "contract_start_date", "contract_start_month", "contract_end_year", "contract_end_month", "owned_company", "bank_card_num", "company_weixin", "company_email"};
}
