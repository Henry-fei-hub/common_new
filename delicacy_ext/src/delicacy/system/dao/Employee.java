package delicacy.system.dao;

import delicacy.common.AbstractTable;
import delicacy.common.GenericBase;
import delicacy.system.bean.BaseEmployee;


public class Employee extends AbstractTable<BaseEmployee>
{

	public Employee() throws java.sql.SQLException 
	{

		initColumnNames();
	}

	protected final void initColumnNames()  throws java.sql.SQLException {

		__columnCount          = 122;

		initTables();

		__tableName            = "employees";

		__key_columns          = new int[1];
		__key_columns[0] = 0;

		__column_names[0] = BaseEmployee.CS_EMPLOYEE_ID;
		__column_names[1] = BaseEmployee.CS_EMPLOYEE_NO;
		__column_names[2] = BaseEmployee.CS_EMPLOYEE_NAME;
		__column_names[3] = BaseEmployee.CS_DEPARTMENT_ID;
		__column_names[4] = BaseEmployee.CS_ROLE_ID;
		__column_names[5] = BaseEmployee.CS_GRADE_ID;
		__column_names[6] = BaseEmployee.CS_EMPLOYEE_PASSWORD;
		__column_names[7] = BaseEmployee.CS_MOBILE;
		__column_names[8] = BaseEmployee.CS_PHONE;
		__column_names[9] = BaseEmployee.CS_QQ;
		__column_names[10] = BaseEmployee.CS_EMAIL;
		__column_names[11] = BaseEmployee.CS_ONBOARD_DATE;
		__column_names[12] = BaseEmployee.CS_RESIGNATION_DATE;
		__column_names[13] = BaseEmployee.CS_STATUS;
		__column_names[14] = BaseEmployee.CS_USABLE_STATUS;
		__column_names[15] = BaseEmployee.CS_IS_DEPARTMENT;
		__column_names[16] = BaseEmployee.CS_PHOTO;
		__column_names[17] = BaseEmployee.CS_GENDER;
		__column_names[18] = BaseEmployee.CS_AUTOGRAPH;
		__column_names[19] = BaseEmployee.CS_AGE;
		__column_names[20] = BaseEmployee.CS_BIRTH;
		__column_names[21] = BaseEmployee.CS_CARD;
		__column_names[22] = BaseEmployee.CS_ADDRESS;
		__column_names[23] = BaseEmployee.CS_ALTERNATE_FIELD1;
		__column_names[24] = BaseEmployee.CS_ALTERNATE_FIELD2;
		__column_names[25] = BaseEmployee.CS_ALTERNATE_FIELD3;
		__column_names[26] = BaseEmployee.CS_LOCKED;
		__column_names[27] = BaseEmployee.CS_PLATE_ID;
		__column_names[28] = BaseEmployee.CS_DUTY_ID;
		__column_names[29] = BaseEmployee.CS_USER_ACCT;
		__column_names[30] = BaseEmployee.CS_EMPLOYEE_NAME_EN;
		__column_names[31] = BaseEmployee.CS_EDUCATION;
		__column_names[32] = BaseEmployee.CS_DEGREE;
		__column_names[33] = BaseEmployee.CS_NATIONALITY;
		__column_names[34] = BaseEmployee.CS_MARRIED_STATUS;
		__column_names[35] = BaseEmployee.CS_HEALTH;
		__column_names[36] = BaseEmployee.CS_WORK_ADDRESS;
		__column_names[37] = BaseEmployee.CS_REGISTERED_ADDRESS;
		__column_names[38] = BaseEmployee.CS_OA_ID;
		__column_names[39] = BaseEmployee.CS_OA_DEPART;
		__column_names[40] = BaseEmployee.CS_IS_HEADCOUNT;
		__column_names[41] = BaseEmployee.CS_IS_CHECK;
		__column_names[42] = BaseEmployee.CS_DIRECT_LEADER;
		__column_names[43] = BaseEmployee.CS_IS_MANAGER;
		__column_names[44] = BaseEmployee.CS_POLITICAL_FACE;
		__column_names[45] = BaseEmployee.CS_BIRTHPLACE;
		__column_names[46] = BaseEmployee.CS_COUNTRY;
		__column_names[47] = BaseEmployee.CS_ACCOUNT_LOCATION;
		__column_names[48] = BaseEmployee.CS_LANGUAGES;
		__column_names[49] = BaseEmployee.CS_START_WORK_DATE;
		__column_names[50] = BaseEmployee.CS_SOCIAL_COMPUTER_NUMBER;
		__column_names[51] = BaseEmployee.CS_FUND_ACCOUNT;
		__column_names[52] = BaseEmployee.CS_POSITIVE_DATE;
		__column_names[53] = BaseEmployee.CS_TRY_TIME;
		__column_names[54] = BaseEmployee.CS_CONTRACT_START_DATE;
		__column_names[55] = BaseEmployee.CS_CONTRACT_END_DATE;
		__column_names[56] = BaseEmployee.CS_OWNED_COMPANY;
		__column_names[57] = BaseEmployee.CS_JOBS;
		__column_names[58] = BaseEmployee.CS_PERSONAL_BUSINESS_REMARK;
		__column_names[59] = BaseEmployee.CS_SELF_INTRODUCTION;
		__column_names[60] = BaseEmployee.CS_LABOR_ATTACHMENTS;
		__column_names[61] = BaseEmployee.CS_EMERGENCY_CONTACT_PERSON;
		__column_names[62] = BaseEmployee.CS_EMERGENCY_CONTACT_PHONE;
		__column_names[63] = BaseEmployee.CS_HOME_PHONE;
		__column_names[64] = BaseEmployee.CS_NOW_ADDRESS;
		__column_names[65] = BaseEmployee.CS_TRY_TIME_PAY;
		__column_names[66] = BaseEmployee.CS_POSITIVE_PAY;
		__column_names[67] = BaseEmployee.CS_APPLY_EMPLOYEE_ID;
		__column_names[68] = BaseEmployee.CS_BANK_CARD_NUM;
		__column_names[69] = BaseEmployee.CS_FILE_NUMBER;
		__column_names[70] = BaseEmployee.CS_HOUSEHOLD_REGISTRATION;
		__column_names[71] = BaseEmployee.CS_FOREMAN;
		__column_names[72] = BaseEmployee.CS_CARD_ATTACHMENT;
		__column_names[73] = BaseEmployee.CS_TECHNICAL_ATTACHMENT;
		__column_names[74] = BaseEmployee.CS_EDUCATION_PROOF;
		__column_names[75] = BaseEmployee.CS_DEGREE_PROOF;
		__column_names[76] = BaseEmployee.CS_EMPLOYEE_SHIFT;
		__column_names[77] = BaseEmployee.CS_CARD_ADDRESS;
		__column_names[78] = BaseEmployee.CS_ANNUAL_PERFORMANCE;
		__column_names[79] = BaseEmployee.CS_ANNUAL_BONUS;
		__column_names[80] = BaseEmployee.CS_COMPANY_WEIXIN;
		__column_names[81] = BaseEmployee.CS_COMPANY_EMAIL;
		__column_names[82] = BaseEmployee.CS_COMPANY_ID;
		__column_names[83] = BaseEmployee.CS_ECMC_USER_ID;
		__column_names[84] = BaseEmployee.CS_ONBOARD_STATUS;
		__column_names[85] = BaseEmployee.CS_ATTACHMENT_REMARK;
		__column_names[86] = BaseEmployee.CS_WORK_YEAR_TYPE;
		__column_names[87] = BaseEmployee.CS_ISBUSY;
		__column_names[88] = BaseEmployee.CS_FOREIGNER_ID_CARD;
		__column_names[89] = BaseEmployee.CS_REAL_BIRTHDATE;
		__column_names[90] = BaseEmployee.CS_WORK_YEAR;
		__column_names[91] = BaseEmployee.CS_SHENZHEN_HOUSE;
		__column_names[92] = BaseEmployee.CS_TRANS_HOUSEHOLD_DATE;
		__column_names[93] = BaseEmployee.CS_PROFESSIONAL_DIRECTION;
		__column_names[94] = BaseEmployee.CS_RECRUITMENT_SOURCES;
		__column_names[95] = BaseEmployee.CS_WECHAT_NUM;
		__column_names[96] = BaseEmployee.CS_AUTO_SIGN_YEAR;
		__column_names[97] = BaseEmployee.CS_RESUME_ATTACHMENT;
		__column_names[98] = BaseEmployee.CS_SIGNATURE_ATTACHMENT;
		__column_names[99] = BaseEmployee.CS_SOCIAL_ATTACHMENT;
		__column_names[100] = BaseEmployee.CS_FUND_ATTACHMENT;
		__column_names[101] = BaseEmployee.CS_SPARE_ATTACHMENT1;
		__column_names[102] = BaseEmployee.CS_SPARE_ATTACHMENT2;
		__column_names[103] = BaseEmployee.CS_SPARE_ATTACHMENT3;
		__column_names[104] = BaseEmployee.CS_EMPLOYEE_TYPE;
		__column_names[105] = BaseEmployee.CS_IS_LONG_SICK_LEAVE;
		__column_names[106] = BaseEmployee.CS_BASIC_SALARY;
		__column_names[107] = BaseEmployee.CS_JOB_TITLE_ALLOWANCE;
		__column_names[108] = BaseEmployee.CS_POST_ALLOWANCE;
		__column_names[109] = BaseEmployee.CS_OTHER;
		__column_names[110] = BaseEmployee.CS_COMMUNICATION_SUBSIDY;
		__column_names[111] = BaseEmployee.CS_SUBTOTAL_MONTHLY_WAGE;
		__column_names[112] = BaseEmployee.CS_MONTHLY_PREISSUED_BONUS;
		__column_names[113] = BaseEmployee.CS_TOTAL_MONTHLY_REMUNERATION;
		__column_names[114] = BaseEmployee.CS_PHOTO_ID;
		__column_names[115] = BaseEmployee.CS_LABOR_ATTACHMENT_ID;
		__column_names[116] = BaseEmployee.CS_CARD_ATTACHMENT_ID;
		__column_names[117] = BaseEmployee.CS_TECHNICAL_ATTACHMENT_ID;
		__column_names[118] = BaseEmployee.CS_EDUCATION_PROOF_ID;
		__column_names[119] = BaseEmployee.CS_DEGREE_PROOF_ID;
		__column_names[120] = BaseEmployee.CS_BANK_CARD_ATTACHMENT;
		__column_names[121] = BaseEmployee.CS_BANK_CARD_ATTACHMENT_ID;

		resetSelectFlags() ;
		resetInsertFlags() ;
	}

	public void resetInsertFlags(){
		for(int i = 0; i < __columnCount; i ++ ) __insert_flags[i] = true;
		__insert_flags[0] = false;
		__serial_column = 0;
	}

	public void setPrimaryKeyFromBase(BaseEmployee b) {
		clear();
		setEmployeeIdClear(b.getEmployeeId());
	}

	public boolean isPrimaryKeyNull() {
		return getEmployeeId() == null;
	}

	@Override
	public BaseEmployee generateBase(){
		BaseEmployee b = new BaseEmployee();
		setDataToBase(b);
		return b;
	}

	@Override
	public void setDataToBase(BaseEmployee b){
		int count = 0; Object val;
		if((val = __current_data[count++]) != null) b.setEmployeeId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setEmployeeNo(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setEmployeeName(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setDepartmentId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setRoleId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setGradeId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setEmployeePassword(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setMobile(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setPhone(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setQq(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setEmail(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setOnboardDate(GenericBase.__getDateFromSQL(val));
		if((val = __current_data[count++]) != null) b.setResignationDate(GenericBase.__getDateFromSQL(val));
		if((val = __current_data[count++]) != null) b.setStatus(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setUsableStatus(GenericBase.__getBoolean(val));
		if((val = __current_data[count++]) != null) b.setIsDepartment(GenericBase.__getBoolean(val));
		if((val = __current_data[count++]) != null) b.setPhoto(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setGender(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setAutograph(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setAge(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setBirth(GenericBase.__getDateFromSQL(val));
		if((val = __current_data[count++]) != null) b.setCard(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setAddress(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setAlternateField1(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setAlternateField2(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setAlternateField3(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setLocked(GenericBase.__getBoolean(val));
		if((val = __current_data[count++]) != null) b.setPlateId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setDutyId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setUserAcct(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setEmployeeNameEn(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setEducation(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setDegree(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setNationality(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setMarriedStatus(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setHealth(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setWorkAddress(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setRegisteredAddress(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setOaId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setOaDepart(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setIsHeadcount(GenericBase.__getBoolean(val));
		if((val = __current_data[count++]) != null) b.setIsCheck(GenericBase.__getBoolean(val));
		if((val = __current_data[count++]) != null) b.setDirectLeader(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setIsManager(GenericBase.__getBoolean(val));
		if((val = __current_data[count++]) != null) b.setPoliticalFace(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setBirthplace(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setCountry(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setAccountLocation(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setLanguages(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setStartWorkDate(GenericBase.__getDateFromSQL(val));
		if((val = __current_data[count++]) != null) b.setSocialComputerNumber(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setFundAccount(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setPositiveDate(GenericBase.__getDateFromSQL(val));
		if((val = __current_data[count++]) != null) b.setTryTime(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setContractStartDate(GenericBase.__getDateFromSQL(val));
		if((val = __current_data[count++]) != null) b.setContractEndDate(GenericBase.__getDateFromSQL(val));
		if((val = __current_data[count++]) != null) b.setOwnedCompany(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setJobs(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setPersonalBusinessRemark(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setSelfIntroduction(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setLaborAttachments(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setEmergencyContactPerson(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setEmergencyContactPhone(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setHomePhone(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setNowAddress(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setTryTimePay(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setPositivePay(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setApplyEmployeeId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setBankCardNum(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setFileNumber(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setHouseholdRegistration(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setForeman(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setCardAttachment(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setTechnicalAttachment(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setEducationProof(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setDegreeProof(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setEmployeeShift(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setCardAddress(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setAnnualPerformance(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setAnnualBonus(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setCompanyWeixin(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setCompanyEmail(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setCompanyId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setEcmcUserId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setOnboardStatus(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setAttachmentRemark(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setWorkYearType(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setIsbusy(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setForeignerIdCard(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setRealBirthdate(GenericBase.__getDateFromSQL(val));
		if((val = __current_data[count++]) != null) b.setWorkYear(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setShenzhenHouse(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setTransHouseholdDate(GenericBase.__getDateFromSQL(val));
		if((val = __current_data[count++]) != null) b.setProfessionalDirection(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setRecruitmentSources(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setWechatNum(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setAutoSignYear(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setResumeAttachment(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setSignatureAttachment(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setSocialAttachment(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setFundAttachment(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setSpareAttachment1(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setSpareAttachment2(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setSpareAttachment3(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setEmployeeType(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setIsLongSickLeave(GenericBase.__getBoolean(val));
		if((val = __current_data[count++]) != null) b.setBasicSalary(GenericBase.__getDecimal(val));
		if((val = __current_data[count++]) != null) b.setJobTitleAllowance(GenericBase.__getDecimal(val));
		if((val = __current_data[count++]) != null) b.setPostAllowance(GenericBase.__getDecimal(val));
		if((val = __current_data[count++]) != null) b.setOther(GenericBase.__getDecimal(val));
		if((val = __current_data[count++]) != null) b.setCommunicationSubsidy(GenericBase.__getDecimal(val));
		if((val = __current_data[count++]) != null) b.setSubtotalMonthlyWage(GenericBase.__getDecimal(val));
		if((val = __current_data[count++]) != null) b.setMonthlyPreissuedBonus(GenericBase.__getDecimal(val));
		if((val = __current_data[count++]) != null) b.setTotalMonthlyRemuneration(GenericBase.__getDecimal(val));
		if((val = __current_data[count++]) != null) b.setPhotoId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setLaborAttachmentId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setCardAttachmentId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setTechnicalAttachmentId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setEducationProofId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setDegreeProofId(GenericBase.__getInt(val));
		if((val = __current_data[count++]) != null) b.setBankCardAttachment(GenericBase.__getString(val));
		if((val = __current_data[count++]) != null) b.setBankCardAttachmentId(GenericBase.__getInt(val));
	}

	@Override
	public void setBaseToBuffer(BaseEmployee b, Object[] buff){
		int count = 0;
		buff[count++] = b.getEmployeeId();
		buff[count++] = b.getEmployeeNo();
		buff[count++] = b.getEmployeeName();
		buff[count++] = b.getDepartmentId();
		buff[count++] = b.getRoleId();
		buff[count++] = b.getGradeId();
		buff[count++] = b.getEmployeePassword();
		buff[count++] = b.getMobile();
		buff[count++] = b.getPhone();
		buff[count++] = b.getQq();
		buff[count++] = b.getEmail();
		buff[count++] = generateTimestampFromDate(b.getOnboardDate());
		buff[count++] = generateTimestampFromDate(b.getResignationDate());
		buff[count++] = b.getStatus();
		buff[count++] = b.getUsableStatus();
		buff[count++] = b.getIsDepartment();
		buff[count++] = b.getPhoto();
		buff[count++] = b.getGender();
		buff[count++] = b.getAutograph();
		buff[count++] = b.getAge();
		buff[count++] = generateTimestampFromDate(b.getBirth());
		buff[count++] = b.getCard();
		buff[count++] = b.getAddress();
		buff[count++] = b.getAlternateField1();
		buff[count++] = b.getAlternateField2();
		buff[count++] = b.getAlternateField3();
		buff[count++] = b.getLocked();
		buff[count++] = b.getPlateId();
		buff[count++] = b.getDutyId();
		buff[count++] = b.getUserAcct();
		buff[count++] = b.getEmployeeNameEn();
		buff[count++] = b.getEducation();
		buff[count++] = b.getDegree();
		buff[count++] = b.getNationality();
		buff[count++] = b.getMarriedStatus();
		buff[count++] = b.getHealth();
		buff[count++] = b.getWorkAddress();
		buff[count++] = b.getRegisteredAddress();
		buff[count++] = b.getOaId();
		buff[count++] = b.getOaDepart();
		buff[count++] = b.getIsHeadcount();
		buff[count++] = b.getIsCheck();
		buff[count++] = b.getDirectLeader();
		buff[count++] = b.getIsManager();
		buff[count++] = b.getPoliticalFace();
		buff[count++] = b.getBirthplace();
		buff[count++] = b.getCountry();
		buff[count++] = b.getAccountLocation();
		buff[count++] = b.getLanguages();
		buff[count++] = generateTimestampFromDate(b.getStartWorkDate());
		buff[count++] = b.getSocialComputerNumber();
		buff[count++] = b.getFundAccount();
		buff[count++] = generateTimestampFromDate(b.getPositiveDate());
		buff[count++] = b.getTryTime();
		buff[count++] = generateTimestampFromDate(b.getContractStartDate());
		buff[count++] = generateTimestampFromDate(b.getContractEndDate());
		buff[count++] = b.getOwnedCompany();
		buff[count++] = b.getJobs();
		buff[count++] = b.getPersonalBusinessRemark();
		buff[count++] = b.getSelfIntroduction();
		buff[count++] = b.getLaborAttachments();
		buff[count++] = b.getEmergencyContactPerson();
		buff[count++] = b.getEmergencyContactPhone();
		buff[count++] = b.getHomePhone();
		buff[count++] = b.getNowAddress();
		buff[count++] = b.getTryTimePay();
		buff[count++] = b.getPositivePay();
		buff[count++] = b.getApplyEmployeeId();
		buff[count++] = b.getBankCardNum();
		buff[count++] = b.getFileNumber();
		buff[count++] = b.getHouseholdRegistration();
		buff[count++] = b.getForeman();
		buff[count++] = b.getCardAttachment();
		buff[count++] = b.getTechnicalAttachment();
		buff[count++] = b.getEducationProof();
		buff[count++] = b.getDegreeProof();
		buff[count++] = b.getEmployeeShift();
		buff[count++] = b.getCardAddress();
		buff[count++] = b.getAnnualPerformance();
		buff[count++] = b.getAnnualBonus();
		buff[count++] = b.getCompanyWeixin();
		buff[count++] = b.getCompanyEmail();
		buff[count++] = b.getCompanyId();
		buff[count++] = b.getEcmcUserId();
		buff[count++] = b.getOnboardStatus();
		buff[count++] = b.getAttachmentRemark();
		buff[count++] = b.getWorkYearType();
		buff[count++] = b.getIsbusy();
		buff[count++] = b.getForeignerIdCard();
		buff[count++] = generateTimestampFromDate(b.getRealBirthdate());
		buff[count++] = b.getWorkYear();
		buff[count++] = b.getShenzhenHouse();
		buff[count++] = generateTimestampFromDate(b.getTransHouseholdDate());
		buff[count++] = b.getProfessionalDirection();
		buff[count++] = b.getRecruitmentSources();
		buff[count++] = b.getWechatNum();
		buff[count++] = b.getAutoSignYear();
		buff[count++] = b.getResumeAttachment();
		buff[count++] = b.getSignatureAttachment();
		buff[count++] = b.getSocialAttachment();
		buff[count++] = b.getFundAttachment();
		buff[count++] = b.getSpareAttachment1();
		buff[count++] = b.getSpareAttachment2();
		buff[count++] = b.getSpareAttachment3();
		buff[count++] = b.getEmployeeType();
		buff[count++] = b.getIsLongSickLeave();
		buff[count++] = b.getBasicSalary();
		buff[count++] = b.getJobTitleAllowance();
		buff[count++] = b.getPostAllowance();
		buff[count++] = b.getOther();
		buff[count++] = b.getCommunicationSubsidy();
		buff[count++] = b.getSubtotalMonthlyWage();
		buff[count++] = b.getMonthlyPreissuedBonus();
		buff[count++] = b.getTotalMonthlyRemuneration();
		buff[count++] = b.getPhotoId();
		buff[count++] = b.getLaborAttachmentId();
		buff[count++] = b.getCardAttachmentId();
		buff[count++] = b.getTechnicalAttachmentId();
		buff[count++] = b.getEducationProofId();
		buff[count++] = b.getDegreeProofId();
		buff[count++] = b.getBankCardAttachment();
		buff[count++] = b.getBankCardAttachmentId();
	}

	@Override
	public void setDataFromBase(BaseEmployee b){
		if(b.getEmployeeId() != null) setEmployeeIdClear(b.getEmployeeId());
		if(b.getEmployeeNo() != null) setEmployeeNo(b.getEmployeeNo());
		if(b.getEmployeeName() != null) setEmployeeName(b.getEmployeeName());
		if(b.getDepartmentId() != null) setDepartmentId(b.getDepartmentId());
		if(b.getRoleId() != null) setRoleId(b.getRoleId());
		if(b.getGradeId() != null) setGradeId(b.getGradeId());
		if(b.getEmployeePassword() != null) setEmployeePassword(b.getEmployeePassword());
		if(b.getMobile() != null) setMobile(b.getMobile());
		if(b.getPhone() != null) setPhone(b.getPhone());
		if(b.getQq() != null) setQq(b.getQq());
		if(b.getEmail() != null) setEmail(b.getEmail());
		if(b.getOnboardDate() != null) setOnboardDate(b.getOnboardDate());
		if(b.getResignationDate() != null) setResignationDate(b.getResignationDate());
		if(b.getStatus() != null) setStatus(b.getStatus());
		if(b.getUsableStatus() != null) setUsableStatus(b.getUsableStatus());
		if(b.getIsDepartment() != null) setIsDepartment(b.getIsDepartment());
		if(b.getPhoto() != null) setPhoto(b.getPhoto());
		if(b.getGender() != null) setGender(b.getGender());
		if(b.getAutograph() != null) setAutograph(b.getAutograph());
		if(b.getAge() != null) setAge(b.getAge());
		if(b.getBirth() != null) setBirth(b.getBirth());
		if(b.getCard() != null) setCard(b.getCard());
		if(b.getAddress() != null) setAddress(b.getAddress());
		if(b.getAlternateField1() != null) setAlternateField1(b.getAlternateField1());
		if(b.getAlternateField2() != null) setAlternateField2(b.getAlternateField2());
		if(b.getAlternateField3() != null) setAlternateField3(b.getAlternateField3());
		if(b.getLocked() != null) setLocked(b.getLocked());
		if(b.getPlateId() != null) setPlateId(b.getPlateId());
		if(b.getDutyId() != null) setDutyId(b.getDutyId());
		if(b.getUserAcct() != null) setUserAcct(b.getUserAcct());
		if(b.getEmployeeNameEn() != null) setEmployeeNameEn(b.getEmployeeNameEn());
		if(b.getEducation() != null) setEducation(b.getEducation());
		if(b.getDegree() != null) setDegree(b.getDegree());
		if(b.getNationality() != null) setNationality(b.getNationality());
		if(b.getMarriedStatus() != null) setMarriedStatus(b.getMarriedStatus());
		if(b.getHealth() != null) setHealth(b.getHealth());
		if(b.getWorkAddress() != null) setWorkAddress(b.getWorkAddress());
		if(b.getRegisteredAddress() != null) setRegisteredAddress(b.getRegisteredAddress());
		if(b.getOaId() != null) setOaId(b.getOaId());
		if(b.getOaDepart() != null) setOaDepart(b.getOaDepart());
		if(b.getIsHeadcount() != null) setIsHeadcount(b.getIsHeadcount());
		if(b.getIsCheck() != null) setIsCheck(b.getIsCheck());
		if(b.getDirectLeader() != null) setDirectLeader(b.getDirectLeader());
		if(b.getIsManager() != null) setIsManager(b.getIsManager());
		if(b.getPoliticalFace() != null) setPoliticalFace(b.getPoliticalFace());
		if(b.getBirthplace() != null) setBirthplace(b.getBirthplace());
		if(b.getCountry() != null) setCountry(b.getCountry());
		if(b.getAccountLocation() != null) setAccountLocation(b.getAccountLocation());
		if(b.getLanguages() != null) setLanguages(b.getLanguages());
		if(b.getStartWorkDate() != null) setStartWorkDate(b.getStartWorkDate());
		if(b.getSocialComputerNumber() != null) setSocialComputerNumber(b.getSocialComputerNumber());
		if(b.getFundAccount() != null) setFundAccount(b.getFundAccount());
		if(b.getPositiveDate() != null) setPositiveDate(b.getPositiveDate());
		if(b.getTryTime() != null) setTryTime(b.getTryTime());
		if(b.getContractStartDate() != null) setContractStartDate(b.getContractStartDate());
		if(b.getContractEndDate() != null) setContractEndDate(b.getContractEndDate());
		if(b.getOwnedCompany() != null) setOwnedCompany(b.getOwnedCompany());
		if(b.getJobs() != null) setJobs(b.getJobs());
		if(b.getPersonalBusinessRemark() != null) setPersonalBusinessRemark(b.getPersonalBusinessRemark());
		if(b.getSelfIntroduction() != null) setSelfIntroduction(b.getSelfIntroduction());
		if(b.getLaborAttachments() != null) setLaborAttachments(b.getLaborAttachments());
		if(b.getEmergencyContactPerson() != null) setEmergencyContactPerson(b.getEmergencyContactPerson());
		if(b.getEmergencyContactPhone() != null) setEmergencyContactPhone(b.getEmergencyContactPhone());
		if(b.getHomePhone() != null) setHomePhone(b.getHomePhone());
		if(b.getNowAddress() != null) setNowAddress(b.getNowAddress());
		if(b.getTryTimePay() != null) setTryTimePay(b.getTryTimePay());
		if(b.getPositivePay() != null) setPositivePay(b.getPositivePay());
		if(b.getApplyEmployeeId() != null) setApplyEmployeeId(b.getApplyEmployeeId());
		if(b.getBankCardNum() != null) setBankCardNum(b.getBankCardNum());
		if(b.getFileNumber() != null) setFileNumber(b.getFileNumber());
		if(b.getHouseholdRegistration() != null) setHouseholdRegistration(b.getHouseholdRegistration());
		if(b.getForeman() != null) setForeman(b.getForeman());
		if(b.getCardAttachment() != null) setCardAttachment(b.getCardAttachment());
		if(b.getTechnicalAttachment() != null) setTechnicalAttachment(b.getTechnicalAttachment());
		if(b.getEducationProof() != null) setEducationProof(b.getEducationProof());
		if(b.getDegreeProof() != null) setDegreeProof(b.getDegreeProof());
		if(b.getEmployeeShift() != null) setEmployeeShift(b.getEmployeeShift());
		if(b.getCardAddress() != null) setCardAddress(b.getCardAddress());
		if(b.getAnnualPerformance() != null) setAnnualPerformance(b.getAnnualPerformance());
		if(b.getAnnualBonus() != null) setAnnualBonus(b.getAnnualBonus());
		if(b.getCompanyWeixin() != null) setCompanyWeixin(b.getCompanyWeixin());
		if(b.getCompanyEmail() != null) setCompanyEmail(b.getCompanyEmail());
		if(b.getCompanyId() != null) setCompanyId(b.getCompanyId());
		if(b.getEcmcUserId() != null) setEcmcUserId(b.getEcmcUserId());
		if(b.getOnboardStatus() != null) setOnboardStatus(b.getOnboardStatus());
		if(b.getAttachmentRemark() != null) setAttachmentRemark(b.getAttachmentRemark());
		if(b.getWorkYearType() != null) setWorkYearType(b.getWorkYearType());
		if(b.getIsbusy() != null) setIsbusy(b.getIsbusy());
		if(b.getForeignerIdCard() != null) setForeignerIdCard(b.getForeignerIdCard());
		if(b.getRealBirthdate() != null) setRealBirthdate(b.getRealBirthdate());
		if(b.getWorkYear() != null) setWorkYear(b.getWorkYear());
		if(b.getShenzhenHouse() != null) setShenzhenHouse(b.getShenzhenHouse());
		if(b.getTransHouseholdDate() != null) setTransHouseholdDate(b.getTransHouseholdDate());
		if(b.getProfessionalDirection() != null) setProfessionalDirection(b.getProfessionalDirection());
		if(b.getRecruitmentSources() != null) setRecruitmentSources(b.getRecruitmentSources());
		if(b.getWechatNum() != null) setWechatNum(b.getWechatNum());
		if(b.getAutoSignYear() != null) setAutoSignYear(b.getAutoSignYear());
		if(b.getResumeAttachment() != null) setResumeAttachment(b.getResumeAttachment());
		if(b.getSignatureAttachment() != null) setSignatureAttachment(b.getSignatureAttachment());
		if(b.getSocialAttachment() != null) setSocialAttachment(b.getSocialAttachment());
		if(b.getFundAttachment() != null) setFundAttachment(b.getFundAttachment());
		if(b.getSpareAttachment1() != null) setSpareAttachment1(b.getSpareAttachment1());
		if(b.getSpareAttachment2() != null) setSpareAttachment2(b.getSpareAttachment2());
		if(b.getSpareAttachment3() != null) setSpareAttachment3(b.getSpareAttachment3());
		if(b.getEmployeeType() != null) setEmployeeType(b.getEmployeeType());
		if(b.getIsLongSickLeave() != null) setIsLongSickLeave(b.getIsLongSickLeave());
		if(b.getBasicSalary() != null) setBasicSalary(b.getBasicSalary());
		if(b.getJobTitleAllowance() != null) setJobTitleAllowance(b.getJobTitleAllowance());
		if(b.getPostAllowance() != null) setPostAllowance(b.getPostAllowance());
		if(b.getOther() != null) setOther(b.getOther());
		if(b.getCommunicationSubsidy() != null) setCommunicationSubsidy(b.getCommunicationSubsidy());
		if(b.getSubtotalMonthlyWage() != null) setSubtotalMonthlyWage(b.getSubtotalMonthlyWage());
		if(b.getMonthlyPreissuedBonus() != null) setMonthlyPreissuedBonus(b.getMonthlyPreissuedBonus());
		if(b.getTotalMonthlyRemuneration() != null) setTotalMonthlyRemuneration(b.getTotalMonthlyRemuneration());
		if(b.getPhotoId() != null) setPhotoId(b.getPhotoId());
		if(b.getLaborAttachmentId() != null) setLaborAttachmentId(b.getLaborAttachmentId());
		if(b.getCardAttachmentId() != null) setCardAttachmentId(b.getCardAttachmentId());
		if(b.getTechnicalAttachmentId() != null) setTechnicalAttachmentId(b.getTechnicalAttachmentId());
		if(b.getEducationProofId() != null) setEducationProofId(b.getEducationProofId());
		if(b.getDegreeProofId() != null) setDegreeProofId(b.getDegreeProofId());
		if(b.getBankCardAttachment() != null) setBankCardAttachment(b.getBankCardAttachment());
		if(b.getBankCardAttachmentId() != null) setBankCardAttachmentId(b.getBankCardAttachmentId());
	}

	@Override
	public BaseEmployee generateBaseFromResultSet(java.sql.ResultSet sdr)  throws java.sql.SQLException {

		BaseEmployee b = new BaseEmployee();
		setQueryResultToBase(b, sdr);
		return b;
	}

	@Override
	public void setQueryResultToBase(BaseEmployee __base, java.sql.ResultSet sdr ) throws java.sql.SQLException {
		int count = 1, index = 0;
		java.sql.ResultSetMetaData rsmd = sdr.getMetaData();
		int columnCount = rsmd.getColumnCount();
		Object val;
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setEmployeeId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setEmployeeNo(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setEmployeeName(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setDepartmentId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setRoleId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setGradeId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setEmployeePassword(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setMobile(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setPhone(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setQq(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setEmail(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setOnboardDate(GenericBase.__getDateFromSQL(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setResignationDate(GenericBase.__getDateFromSQL(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setStatus(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setUsableStatus(GenericBase.__getBoolean(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setIsDepartment(GenericBase.__getBoolean(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setPhoto(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setGender(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setAutograph(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setAge(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setBirth(GenericBase.__getDateFromSQL(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setCard(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setAddress(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setAlternateField1(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setAlternateField2(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setAlternateField3(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setLocked(GenericBase.__getBoolean(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setPlateId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setDutyId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setUserAcct(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setEmployeeNameEn(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setEducation(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setDegree(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setNationality(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setMarriedStatus(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setHealth(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setWorkAddress(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setRegisteredAddress(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setOaId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setOaDepart(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setIsHeadcount(GenericBase.__getBoolean(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setIsCheck(GenericBase.__getBoolean(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setDirectLeader(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setIsManager(GenericBase.__getBoolean(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setPoliticalFace(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setBirthplace(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setCountry(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setAccountLocation(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setLanguages(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setStartWorkDate(GenericBase.__getDateFromSQL(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setSocialComputerNumber(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setFundAccount(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setPositiveDate(GenericBase.__getDateFromSQL(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setTryTime(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setContractStartDate(GenericBase.__getDateFromSQL(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setContractEndDate(GenericBase.__getDateFromSQL(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setOwnedCompany(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setJobs(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setPersonalBusinessRemark(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setSelfIntroduction(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setLaborAttachments(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setEmergencyContactPerson(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setEmergencyContactPhone(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setHomePhone(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setNowAddress(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setTryTimePay(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setPositivePay(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setApplyEmployeeId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setBankCardNum(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setFileNumber(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setHouseholdRegistration(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setForeman(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setCardAttachment(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setTechnicalAttachment(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setEducationProof(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setDegreeProof(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setEmployeeShift(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setCardAddress(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setAnnualPerformance(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setAnnualBonus(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setCompanyWeixin(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setCompanyEmail(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setCompanyId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setEcmcUserId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setOnboardStatus(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setAttachmentRemark(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setWorkYearType(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setIsbusy(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setForeignerIdCard(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setRealBirthdate(GenericBase.__getDateFromSQL(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setWorkYear(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setShenzhenHouse(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setTransHouseholdDate(GenericBase.__getDateFromSQL(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setProfessionalDirection(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setRecruitmentSources(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setWechatNum(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setAutoSignYear(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setResumeAttachment(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setSignatureAttachment(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setSocialAttachment(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setFundAttachment(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setSpareAttachment1(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setSpareAttachment2(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setSpareAttachment3(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setEmployeeType(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setIsLongSickLeave(GenericBase.__getBoolean(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setBasicSalary(GenericBase.__getDecimal(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setJobTitleAllowance(GenericBase.__getDecimal(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setPostAllowance(GenericBase.__getDecimal(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setOther(GenericBase.__getDecimal(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setCommunicationSubsidy(GenericBase.__getDecimal(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setSubtotalMonthlyWage(GenericBase.__getDecimal(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setMonthlyPreissuedBonus(GenericBase.__getDecimal(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setTotalMonthlyRemuneration(GenericBase.__getDecimal(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setPhotoId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setLaborAttachmentId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setCardAttachmentId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setTechnicalAttachmentId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setEducationProofId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setDegreeProofId(GenericBase.__getInt(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setBankCardAttachment(GenericBase.__getString(val));
		if(__select_flags[index++] && count <= columnCount && (val = sdr.getObject(count++)) != null) __base.setBankCardAttachmentId(GenericBase.__getInt(val));
	}

	public void setEmployeeId(java.lang.Integer val) {
		setCurrentData(0, val);
	}

	public java.lang.Integer getEmployeeId() {
		return GenericBase.__getInt(__current_data[0]);
	}

	public void setEmployeeIdClear(java.lang.Integer val) {
		__backup_data[0] = __current_data[0] =  val;
		__modified_flags[0] = false;
	}

	public void setEmployeeNo(java.lang.String val) {
		setCurrentData(1, val);
	}

	public java.lang.String getEmployeeNo() {
		return GenericBase.__getString(__current_data[1]);
	}

	public void setEmployeeName(java.lang.String val) {
		setCurrentData(2, val);
	}

	public java.lang.String getEmployeeName() {
		return GenericBase.__getString(__current_data[2]);
	}

	public void setDepartmentId(java.lang.Integer val) {
		setCurrentData(3, val);
	}

	public java.lang.Integer getDepartmentId() {
		return GenericBase.__getInt(__current_data[3]);
	}

	public void setRoleId(java.lang.Integer val) {
		setCurrentData(4, val);
	}

	public java.lang.Integer getRoleId() {
		return GenericBase.__getInt(__current_data[4]);
	}

	public void setGradeId(java.lang.Integer val) {
		setCurrentData(5, val);
	}

	public java.lang.Integer getGradeId() {
		return GenericBase.__getInt(__current_data[5]);
	}

	public void setEmployeePassword(java.lang.String val) {
		setCurrentData(6, val);
	}

	public java.lang.String getEmployeePassword() {
		return GenericBase.__getString(__current_data[6]);
	}

	public void setMobile(java.lang.String val) {
		setCurrentData(7, val);
	}

	public java.lang.String getMobile() {
		return GenericBase.__getString(__current_data[7]);
	}

	public void setPhone(java.lang.String val) {
		setCurrentData(8, val);
	}

	public java.lang.String getPhone() {
		return GenericBase.__getString(__current_data[8]);
	}

	public void setQq(java.lang.String val) {
		setCurrentData(9, val);
	}

	public java.lang.String getQq() {
		return GenericBase.__getString(__current_data[9]);
	}

	public void setEmail(java.lang.String val) {
		setCurrentData(10, val);
	}

	public java.lang.String getEmail() {
		return GenericBase.__getString(__current_data[10]);
	}

	public void setOnboardDate(java.util.Date val) {
		setCurrentData(11, generateTimestampFromDate(val));
	}

	public java.util.Date getOnboardDate() {
		return GenericBase.__getDateFromSQL(__current_data[11]);
	}

	public void setResignationDate(java.util.Date val) {
		setCurrentData(12, generateTimestampFromDate(val));
	}

	public java.util.Date getResignationDate() {
		return GenericBase.__getDateFromSQL(__current_data[12]);
	}

	public void setStatus(java.lang.Integer val) {
		setCurrentData(13, val);
	}

	public java.lang.Integer getStatus() {
		return GenericBase.__getInt(__current_data[13]);
	}

	public void setUsableStatus(java.lang.Boolean val) {
		setCurrentData(14, val);
	}

	public java.lang.Boolean getUsableStatus() {
		return GenericBase.__getBoolean(__current_data[14]);
	}

	public void setIsDepartment(java.lang.Boolean val) {
		setCurrentData(15, val);
	}

	public java.lang.Boolean getIsDepartment() {
		return GenericBase.__getBoolean(__current_data[15]);
	}

	public void setPhoto(java.lang.String val) {
		setCurrentData(16, val);
	}

	public java.lang.String getPhoto() {
		return GenericBase.__getString(__current_data[16]);
	}

	public void setGender(java.lang.Integer val) {
		setCurrentData(17, val);
	}

	public java.lang.Integer getGender() {
		return GenericBase.__getInt(__current_data[17]);
	}

	public void setAutograph(java.lang.String val) {
		setCurrentData(18, val);
	}

	public java.lang.String getAutograph() {
		return GenericBase.__getString(__current_data[18]);
	}

	public void setAge(java.lang.Integer val) {
		setCurrentData(19, val);
	}

	public java.lang.Integer getAge() {
		return GenericBase.__getInt(__current_data[19]);
	}

	public void setBirth(java.util.Date val) {
		setCurrentData(20, generateTimestampFromDate(val));
	}

	public java.util.Date getBirth() {
		return GenericBase.__getDateFromSQL(__current_data[20]);
	}

	public void setCard(java.lang.String val) {
		setCurrentData(21, val);
	}

	public java.lang.String getCard() {
		return GenericBase.__getString(__current_data[21]);
	}

	public void setAddress(java.lang.String val) {
		setCurrentData(22, val);
	}

	public java.lang.String getAddress() {
		return GenericBase.__getString(__current_data[22]);
	}

	public void setAlternateField1(java.lang.String val) {
		setCurrentData(23, val);
	}

	public java.lang.String getAlternateField1() {
		return GenericBase.__getString(__current_data[23]);
	}

	public void setAlternateField2(java.lang.String val) {
		setCurrentData(24, val);
	}

	public java.lang.String getAlternateField2() {
		return GenericBase.__getString(__current_data[24]);
	}

	public void setAlternateField3(java.lang.String val) {
		setCurrentData(25, val);
	}

	public java.lang.String getAlternateField3() {
		return GenericBase.__getString(__current_data[25]);
	}

	public void setLocked(java.lang.Boolean val) {
		setCurrentData(26, val);
	}

	public java.lang.Boolean getLocked() {
		return GenericBase.__getBoolean(__current_data[26]);
	}

	public void setPlateId(java.lang.Integer val) {
		setCurrentData(27, val);
	}

	public java.lang.Integer getPlateId() {
		return GenericBase.__getInt(__current_data[27]);
	}

	public void setDutyId(java.lang.Integer val) {
		setCurrentData(28, val);
	}

	public java.lang.Integer getDutyId() {
		return GenericBase.__getInt(__current_data[28]);
	}

	public void setUserAcct(java.lang.String val) {
		setCurrentData(29, val);
	}

	public java.lang.String getUserAcct() {
		return GenericBase.__getString(__current_data[29]);
	}

	public void setEmployeeNameEn(java.lang.String val) {
		setCurrentData(30, val);
	}

	public java.lang.String getEmployeeNameEn() {
		return GenericBase.__getString(__current_data[30]);
	}

	public void setEducation(java.lang.String val) {
		setCurrentData(31, val);
	}

	public java.lang.String getEducation() {
		return GenericBase.__getString(__current_data[31]);
	}

	public void setDegree(java.lang.String val) {
		setCurrentData(32, val);
	}

	public java.lang.String getDegree() {
		return GenericBase.__getString(__current_data[32]);
	}

	public void setNationality(java.lang.String val) {
		setCurrentData(33, val);
	}

	public java.lang.String getNationality() {
		return GenericBase.__getString(__current_data[33]);
	}

	public void setMarriedStatus(java.lang.String val) {
		setCurrentData(34, val);
	}

	public java.lang.String getMarriedStatus() {
		return GenericBase.__getString(__current_data[34]);
	}

	public void setHealth(java.lang.String val) {
		setCurrentData(35, val);
	}

	public java.lang.String getHealth() {
		return GenericBase.__getString(__current_data[35]);
	}

	public void setWorkAddress(java.lang.String val) {
		setCurrentData(36, val);
	}

	public java.lang.String getWorkAddress() {
		return GenericBase.__getString(__current_data[36]);
	}

	public void setRegisteredAddress(java.lang.String val) {
		setCurrentData(37, val);
	}

	public java.lang.String getRegisteredAddress() {
		return GenericBase.__getString(__current_data[37]);
	}

	public void setOaId(java.lang.Integer val) {
		setCurrentData(38, val);
	}

	public java.lang.Integer getOaId() {
		return GenericBase.__getInt(__current_data[38]);
	}

	public void setOaDepart(java.lang.Integer val) {
		setCurrentData(39, val);
	}

	public java.lang.Integer getOaDepart() {
		return GenericBase.__getInt(__current_data[39]);
	}

	public void setIsHeadcount(java.lang.Boolean val) {
		setCurrentData(40, val);
	}

	public java.lang.Boolean getIsHeadcount() {
		return GenericBase.__getBoolean(__current_data[40]);
	}

	public void setIsCheck(java.lang.Boolean val) {
		setCurrentData(41, val);
	}

	public java.lang.Boolean getIsCheck() {
		return GenericBase.__getBoolean(__current_data[41]);
	}

	public void setDirectLeader(java.lang.Integer val) {
		setCurrentData(42, val);
	}

	public java.lang.Integer getDirectLeader() {
		return GenericBase.__getInt(__current_data[42]);
	}

	public void setIsManager(java.lang.Boolean val) {
		setCurrentData(43, val);
	}

	public java.lang.Boolean getIsManager() {
		return GenericBase.__getBoolean(__current_data[43]);
	}

	public void setPoliticalFace(java.lang.Integer val) {
		setCurrentData(44, val);
	}

	public java.lang.Integer getPoliticalFace() {
		return GenericBase.__getInt(__current_data[44]);
	}

	public void setBirthplace(java.lang.String val) {
		setCurrentData(45, val);
	}

	public java.lang.String getBirthplace() {
		return GenericBase.__getString(__current_data[45]);
	}

	public void setCountry(java.lang.String val) {
		setCurrentData(46, val);
	}

	public java.lang.String getCountry() {
		return GenericBase.__getString(__current_data[46]);
	}

	public void setAccountLocation(java.lang.String val) {
		setCurrentData(47, val);
	}

	public java.lang.String getAccountLocation() {
		return GenericBase.__getString(__current_data[47]);
	}

	public void setLanguages(java.lang.String val) {
		setCurrentData(48, val);
	}

	public java.lang.String getLanguages() {
		return GenericBase.__getString(__current_data[48]);
	}

	public void setStartWorkDate(java.util.Date val) {
		setCurrentData(49, generateTimestampFromDate(val));
	}

	public java.util.Date getStartWorkDate() {
		return GenericBase.__getDateFromSQL(__current_data[49]);
	}

	public void setSocialComputerNumber(java.lang.String val) {
		setCurrentData(50, val);
	}

	public java.lang.String getSocialComputerNumber() {
		return GenericBase.__getString(__current_data[50]);
	}

	public void setFundAccount(java.lang.String val) {
		setCurrentData(51, val);
	}

	public java.lang.String getFundAccount() {
		return GenericBase.__getString(__current_data[51]);
	}

	public void setPositiveDate(java.util.Date val) {
		setCurrentData(52, generateTimestampFromDate(val));
	}

	public java.util.Date getPositiveDate() {
		return GenericBase.__getDateFromSQL(__current_data[52]);
	}

	public void setTryTime(java.lang.String val) {
		setCurrentData(53, val);
	}

	public java.lang.String getTryTime() {
		return GenericBase.__getString(__current_data[53]);
	}

	public void setContractStartDate(java.util.Date val) {
		setCurrentData(54, generateTimestampFromDate(val));
	}

	public java.util.Date getContractStartDate() {
		return GenericBase.__getDateFromSQL(__current_data[54]);
	}

	public void setContractEndDate(java.util.Date val) {
		setCurrentData(55, generateTimestampFromDate(val));
	}

	public java.util.Date getContractEndDate() {
		return GenericBase.__getDateFromSQL(__current_data[55]);
	}

	public void setOwnedCompany(java.lang.String val) {
		setCurrentData(56, val);
	}

	public java.lang.String getOwnedCompany() {
		return GenericBase.__getString(__current_data[56]);
	}

	public void setJobs(java.lang.String val) {
		setCurrentData(57, val);
	}

	public java.lang.String getJobs() {
		return GenericBase.__getString(__current_data[57]);
	}

	public void setPersonalBusinessRemark(java.lang.String val) {
		setCurrentData(58, val);
	}

	public java.lang.String getPersonalBusinessRemark() {
		return GenericBase.__getString(__current_data[58]);
	}

	public void setSelfIntroduction(java.lang.String val) {
		setCurrentData(59, val);
	}

	public java.lang.String getSelfIntroduction() {
		return GenericBase.__getString(__current_data[59]);
	}

	public void setLaborAttachments(java.lang.String val) {
		setCurrentData(60, val);
	}

	public java.lang.String getLaborAttachments() {
		return GenericBase.__getString(__current_data[60]);
	}

	public void setEmergencyContactPerson(java.lang.String val) {
		setCurrentData(61, val);
	}

	public java.lang.String getEmergencyContactPerson() {
		return GenericBase.__getString(__current_data[61]);
	}

	public void setEmergencyContactPhone(java.lang.String val) {
		setCurrentData(62, val);
	}

	public java.lang.String getEmergencyContactPhone() {
		return GenericBase.__getString(__current_data[62]);
	}

	public void setHomePhone(java.lang.String val) {
		setCurrentData(63, val);
	}

	public java.lang.String getHomePhone() {
		return GenericBase.__getString(__current_data[63]);
	}

	public void setNowAddress(java.lang.String val) {
		setCurrentData(64, val);
	}

	public java.lang.String getNowAddress() {
		return GenericBase.__getString(__current_data[64]);
	}

	public void setTryTimePay(java.lang.String val) {
		setCurrentData(65, val);
	}

	public java.lang.String getTryTimePay() {
		return GenericBase.__getString(__current_data[65]);
	}

	public void setPositivePay(java.lang.String val) {
		setCurrentData(66, val);
	}

	public java.lang.String getPositivePay() {
		return GenericBase.__getString(__current_data[66]);
	}

	public void setApplyEmployeeId(java.lang.Integer val) {
		setCurrentData(67, val);
	}

	public java.lang.Integer getApplyEmployeeId() {
		return GenericBase.__getInt(__current_data[67]);
	}

	public void setBankCardNum(java.lang.String val) {
		setCurrentData(68, val);
	}

	public java.lang.String getBankCardNum() {
		return GenericBase.__getString(__current_data[68]);
	}

	public void setFileNumber(java.lang.String val) {
		setCurrentData(69, val);
	}

	public java.lang.String getFileNumber() {
		return GenericBase.__getString(__current_data[69]);
	}

	public void setHouseholdRegistration(java.lang.Integer val) {
		setCurrentData(70, val);
	}

	public java.lang.Integer getHouseholdRegistration() {
		return GenericBase.__getInt(__current_data[70]);
	}

	public void setForeman(java.lang.String val) {
		setCurrentData(71, val);
	}

	public java.lang.String getForeman() {
		return GenericBase.__getString(__current_data[71]);
	}

	public void setCardAttachment(java.lang.String val) {
		setCurrentData(72, val);
	}

	public java.lang.String getCardAttachment() {
		return GenericBase.__getString(__current_data[72]);
	}

	public void setTechnicalAttachment(java.lang.String val) {
		setCurrentData(73, val);
	}

	public java.lang.String getTechnicalAttachment() {
		return GenericBase.__getString(__current_data[73]);
	}

	public void setEducationProof(java.lang.String val) {
		setCurrentData(74, val);
	}

	public java.lang.String getEducationProof() {
		return GenericBase.__getString(__current_data[74]);
	}

	public void setDegreeProof(java.lang.String val) {
		setCurrentData(75, val);
	}

	public java.lang.String getDegreeProof() {
		return GenericBase.__getString(__current_data[75]);
	}

	public void setEmployeeShift(java.lang.Integer val) {
		setCurrentData(76, val);
	}

	public java.lang.Integer getEmployeeShift() {
		return GenericBase.__getInt(__current_data[76]);
	}

	public void setCardAddress(java.lang.String val) {
		setCurrentData(77, val);
	}

	public java.lang.String getCardAddress() {
		return GenericBase.__getString(__current_data[77]);
	}

	public void setAnnualPerformance(java.lang.String val) {
		setCurrentData(78, val);
	}

	public java.lang.String getAnnualPerformance() {
		return GenericBase.__getString(__current_data[78]);
	}

	public void setAnnualBonus(java.lang.String val) {
		setCurrentData(79, val);
	}

	public java.lang.String getAnnualBonus() {
		return GenericBase.__getString(__current_data[79]);
	}

	public void setCompanyWeixin(java.lang.String val) {
		setCurrentData(80, val);
	}

	public java.lang.String getCompanyWeixin() {
		return GenericBase.__getString(__current_data[80]);
	}

	public void setCompanyEmail(java.lang.String val) {
		setCurrentData(81, val);
	}

	public java.lang.String getCompanyEmail() {
		return GenericBase.__getString(__current_data[81]);
	}

	public void setCompanyId(java.lang.Integer val) {
		setCurrentData(82, val);
	}

	public java.lang.Integer getCompanyId() {
		return GenericBase.__getInt(__current_data[82]);
	}

	public void setEcmcUserId(java.lang.Integer val) {
		setCurrentData(83, val);
	}

	public java.lang.Integer getEcmcUserId() {
		return GenericBase.__getInt(__current_data[83]);
	}

	public void setOnboardStatus(java.lang.Integer val) {
		setCurrentData(84, val);
	}

	public java.lang.Integer getOnboardStatus() {
		return GenericBase.__getInt(__current_data[84]);
	}

	public void setAttachmentRemark(java.lang.String val) {
		setCurrentData(85, val);
	}

	public java.lang.String getAttachmentRemark() {
		return GenericBase.__getString(__current_data[85]);
	}

	public void setWorkYearType(java.lang.Integer val) {
		setCurrentData(86, val);
	}

	public java.lang.Integer getWorkYearType() {
		return GenericBase.__getInt(__current_data[86]);
	}

	public void setIsbusy(java.lang.Integer val) {
		setCurrentData(87, val);
	}

	public java.lang.Integer getIsbusy() {
		return GenericBase.__getInt(__current_data[87]);
	}

	public void setForeignerIdCard(java.lang.String val) {
		setCurrentData(88, val);
	}

	public java.lang.String getForeignerIdCard() {
		return GenericBase.__getString(__current_data[88]);
	}

	public void setRealBirthdate(java.util.Date val) {
		setCurrentData(89, generateTimestampFromDate(val));
	}

	public java.util.Date getRealBirthdate() {
		return GenericBase.__getDateFromSQL(__current_data[89]);
	}

	public void setWorkYear(java.lang.String val) {
		setCurrentData(90, val);
	}

	public java.lang.String getWorkYear() {
		return GenericBase.__getString(__current_data[90]);
	}

	public void setShenzhenHouse(java.lang.Integer val) {
		setCurrentData(91, val);
	}

	public java.lang.Integer getShenzhenHouse() {
		return GenericBase.__getInt(__current_data[91]);
	}

	public void setTransHouseholdDate(java.util.Date val) {
		setCurrentData(92, generateTimestampFromDate(val));
	}

	public java.util.Date getTransHouseholdDate() {
		return GenericBase.__getDateFromSQL(__current_data[92]);
	}

	public void setProfessionalDirection(java.lang.Integer val) {
		setCurrentData(93, val);
	}

	public java.lang.Integer getProfessionalDirection() {
		return GenericBase.__getInt(__current_data[93]);
	}

	public void setRecruitmentSources(java.lang.Integer val) {
		setCurrentData(94, val);
	}

	public java.lang.Integer getRecruitmentSources() {
		return GenericBase.__getInt(__current_data[94]);
	}

	public void setWechatNum(java.lang.String val) {
		setCurrentData(95, val);
	}

	public java.lang.String getWechatNum() {
		return GenericBase.__getString(__current_data[95]);
	}

	public void setAutoSignYear(java.lang.Integer val) {
		setCurrentData(96, val);
	}

	public java.lang.Integer getAutoSignYear() {
		return GenericBase.__getInt(__current_data[96]);
	}

	public void setResumeAttachment(java.lang.String val) {
		setCurrentData(97, val);
	}

	public java.lang.String getResumeAttachment() {
		return GenericBase.__getString(__current_data[97]);
	}

	public void setSignatureAttachment(java.lang.String val) {
		setCurrentData(98, val);
	}

	public java.lang.String getSignatureAttachment() {
		return GenericBase.__getString(__current_data[98]);
	}

	public void setSocialAttachment(java.lang.String val) {
		setCurrentData(99, val);
	}

	public java.lang.String getSocialAttachment() {
		return GenericBase.__getString(__current_data[99]);
	}

	public void setFundAttachment(java.lang.String val) {
		setCurrentData(100, val);
	}

	public java.lang.String getFundAttachment() {
		return GenericBase.__getString(__current_data[100]);
	}

	public void setSpareAttachment1(java.lang.String val) {
		setCurrentData(101, val);
	}

	public java.lang.String getSpareAttachment1() {
		return GenericBase.__getString(__current_data[101]);
	}

	public void setSpareAttachment2(java.lang.String val) {
		setCurrentData(102, val);
	}

	public java.lang.String getSpareAttachment2() {
		return GenericBase.__getString(__current_data[102]);
	}

	public void setSpareAttachment3(java.lang.String val) {
		setCurrentData(103, val);
	}

	public java.lang.String getSpareAttachment3() {
		return GenericBase.__getString(__current_data[103]);
	}

	public void setEmployeeType(java.lang.Integer val) {
		setCurrentData(104, val);
	}

	public java.lang.Integer getEmployeeType() {
		return GenericBase.__getInt(__current_data[104]);
	}

	public void setIsLongSickLeave(java.lang.Boolean val) {
		setCurrentData(105, val);
	}

	public java.lang.Boolean getIsLongSickLeave() {
		return GenericBase.__getBoolean(__current_data[105]);
	}

	public void setBasicSalary(java.math.BigDecimal val) {
		setCurrentData(106, val);
	}

	public java.math.BigDecimal getBasicSalary() {
		return GenericBase.__getDecimal(__current_data[106]);
	}

	public void setJobTitleAllowance(java.math.BigDecimal val) {
		setCurrentData(107, val);
	}

	public java.math.BigDecimal getJobTitleAllowance() {
		return GenericBase.__getDecimal(__current_data[107]);
	}

	public void setPostAllowance(java.math.BigDecimal val) {
		setCurrentData(108, val);
	}

	public java.math.BigDecimal getPostAllowance() {
		return GenericBase.__getDecimal(__current_data[108]);
	}

	public void setOther(java.math.BigDecimal val) {
		setCurrentData(109, val);
	}

	public java.math.BigDecimal getOther() {
		return GenericBase.__getDecimal(__current_data[109]);
	}

	public void setCommunicationSubsidy(java.math.BigDecimal val) {
		setCurrentData(110, val);
	}

	public java.math.BigDecimal getCommunicationSubsidy() {
		return GenericBase.__getDecimal(__current_data[110]);
	}

	public void setSubtotalMonthlyWage(java.math.BigDecimal val) {
		setCurrentData(111, val);
	}

	public java.math.BigDecimal getSubtotalMonthlyWage() {
		return GenericBase.__getDecimal(__current_data[111]);
	}

	public void setMonthlyPreissuedBonus(java.math.BigDecimal val) {
		setCurrentData(112, val);
	}

	public java.math.BigDecimal getMonthlyPreissuedBonus() {
		return GenericBase.__getDecimal(__current_data[112]);
	}

	public void setTotalMonthlyRemuneration(java.math.BigDecimal val) {
		setCurrentData(113, val);
	}

	public java.math.BigDecimal getTotalMonthlyRemuneration() {
		return GenericBase.__getDecimal(__current_data[113]);
	}

	public void setPhotoId(java.lang.Integer val) {
		setCurrentData(114, val);
	}

	public java.lang.Integer getPhotoId() {
		return GenericBase.__getInt(__current_data[114]);
	}

	public void setLaborAttachmentId(java.lang.Integer val) {
		setCurrentData(115, val);
	}

	public java.lang.Integer getLaborAttachmentId() {
		return GenericBase.__getInt(__current_data[115]);
	}

	public void setCardAttachmentId(java.lang.Integer val) {
		setCurrentData(116, val);
	}

	public java.lang.Integer getCardAttachmentId() {
		return GenericBase.__getInt(__current_data[116]);
	}

	public void setTechnicalAttachmentId(java.lang.Integer val) {
		setCurrentData(117, val);
	}

	public java.lang.Integer getTechnicalAttachmentId() {
		return GenericBase.__getInt(__current_data[117]);
	}

	public void setEducationProofId(java.lang.Integer val) {
		setCurrentData(118, val);
	}

	public java.lang.Integer getEducationProofId() {
		return GenericBase.__getInt(__current_data[118]);
	}

	public void setDegreeProofId(java.lang.Integer val) {
		setCurrentData(119, val);
	}

	public java.lang.Integer getDegreeProofId() {
		return GenericBase.__getInt(__current_data[119]);
	}

	public void setBankCardAttachment(java.lang.String val) {
		setCurrentData(120, val);
	}

	public java.lang.String getBankCardAttachment() {
		return GenericBase.__getString(__current_data[120]);
	}

	public void setBankCardAttachmentId(java.lang.Integer val) {
		setCurrentData(121, val);
	}

	public java.lang.Integer getBankCardAttachmentId() {
		return GenericBase.__getInt(__current_data[121]);
	}

	public void setConditionEmployeeId(String op, java.lang.Integer val) {
		setConditionEmployeeId(op, val, CONDITION_AND);
	}

	public void setConditionEmployeeId(String op, java.lang.Integer val, String relation) {
		addCondition(0, op, relation, val);
	}

	public void setSelectEmployeeId(boolean val) {
		__select_flags[0] = val;
	}

	public void setConditionEmployeeNo(String op, java.lang.String val) {
		setConditionEmployeeNo(op, val, CONDITION_AND);
	}

	public void setConditionEmployeeNo(String op, java.lang.String val, String relation) {
		addCondition(1, op, relation, val);
	}

	public void setSelectEmployeeNo(boolean val) {
		__select_flags[1] = val;
	}

	public void setConditionEmployeeName(String op, java.lang.String val) {
		setConditionEmployeeName(op, val, CONDITION_AND);
	}

	public void setConditionEmployeeName(String op, java.lang.String val, String relation) {
		addCondition(2, op, relation, val);
	}

	public void setSelectEmployeeName(boolean val) {
		__select_flags[2] = val;
	}

	public void setConditionDepartmentId(String op, java.lang.Integer val) {
		setConditionDepartmentId(op, val, CONDITION_AND);
	}

	public void setConditionDepartmentId(String op, java.lang.Integer val, String relation) {
		addCondition(3, op, relation, val);
	}

	public void setSelectDepartmentId(boolean val) {
		__select_flags[3] = val;
	}

	public void setConditionRoleId(String op, java.lang.Integer val) {
		setConditionRoleId(op, val, CONDITION_AND);
	}

	public void setConditionRoleId(String op, java.lang.Integer val, String relation) {
		addCondition(4, op, relation, val);
	}

	public void setSelectRoleId(boolean val) {
		__select_flags[4] = val;
	}

	public void setConditionGradeId(String op, java.lang.Integer val) {
		setConditionGradeId(op, val, CONDITION_AND);
	}

	public void setConditionGradeId(String op, java.lang.Integer val, String relation) {
		addCondition(5, op, relation, val);
	}

	public void setSelectGradeId(boolean val) {
		__select_flags[5] = val;
	}

	public void setConditionEmployeePassword(String op, java.lang.String val) {
		setConditionEmployeePassword(op, val, CONDITION_AND);
	}

	public void setConditionEmployeePassword(String op, java.lang.String val, String relation) {
		addCondition(6, op, relation, val);
	}

	public void setSelectEmployeePassword(boolean val) {
		__select_flags[6] = val;
	}

	public void setConditionMobile(String op, java.lang.String val) {
		setConditionMobile(op, val, CONDITION_AND);
	}

	public void setConditionMobile(String op, java.lang.String val, String relation) {
		addCondition(7, op, relation, val);
	}

	public void setSelectMobile(boolean val) {
		__select_flags[7] = val;
	}

	public void setConditionPhone(String op, java.lang.String val) {
		setConditionPhone(op, val, CONDITION_AND);
	}

	public void setConditionPhone(String op, java.lang.String val, String relation) {
		addCondition(8, op, relation, val);
	}

	public void setSelectPhone(boolean val) {
		__select_flags[8] = val;
	}

	public void setConditionQq(String op, java.lang.String val) {
		setConditionQq(op, val, CONDITION_AND);
	}

	public void setConditionQq(String op, java.lang.String val, String relation) {
		addCondition(9, op, relation, val);
	}

	public void setSelectQq(boolean val) {
		__select_flags[9] = val;
	}

	public void setConditionEmail(String op, java.lang.String val) {
		setConditionEmail(op, val, CONDITION_AND);
	}

	public void setConditionEmail(String op, java.lang.String val, String relation) {
		addCondition(10, op, relation, val);
	}

	public void setSelectEmail(boolean val) {
		__select_flags[10] = val;
	}

	public void setConditionOnboardDate(String op, java.util.Date val) {
		setConditionOnboardDate(op, val, CONDITION_AND);
	}

	public void setConditionOnboardDate(String op, java.util.Date val, String relation) {
		addCondition(11, op, relation, generateTimestampFromDate(val));
	}

	public void setSelectOnboardDate(boolean val) {
		__select_flags[11] = val;
	}

	public void setConditionResignationDate(String op, java.util.Date val) {
		setConditionResignationDate(op, val, CONDITION_AND);
	}

	public void setConditionResignationDate(String op, java.util.Date val, String relation) {
		addCondition(12, op, relation, generateTimestampFromDate(val));
	}

	public void setSelectResignationDate(boolean val) {
		__select_flags[12] = val;
	}

	public void setConditionStatus(String op, java.lang.Integer val) {
		setConditionStatus(op, val, CONDITION_AND);
	}

	public void setConditionStatus(String op, java.lang.Integer val, String relation) {
		addCondition(13, op, relation, val);
	}

	public void setSelectStatus(boolean val) {
		__select_flags[13] = val;
	}

	public void setConditionUsableStatus(String op, java.lang.Boolean val) {
		setConditionUsableStatus(op, val, CONDITION_AND);
	}

	public void setConditionUsableStatus(String op, java.lang.Boolean val, String relation) {
		addCondition(14, op, relation, val);
	}

	public void setSelectUsableStatus(boolean val) {
		__select_flags[14] = val;
	}

	public void setConditionIsDepartment(String op, java.lang.Boolean val) {
		setConditionIsDepartment(op, val, CONDITION_AND);
	}

	public void setConditionIsDepartment(String op, java.lang.Boolean val, String relation) {
		addCondition(15, op, relation, val);
	}

	public void setSelectIsDepartment(boolean val) {
		__select_flags[15] = val;
	}

	public void setConditionPhoto(String op, java.lang.String val) {
		setConditionPhoto(op, val, CONDITION_AND);
	}

	public void setConditionPhoto(String op, java.lang.String val, String relation) {
		addCondition(16, op, relation, val);
	}

	public void setSelectPhoto(boolean val) {
		__select_flags[16] = val;
	}

	public void setConditionGender(String op, java.lang.Integer val) {
		setConditionGender(op, val, CONDITION_AND);
	}

	public void setConditionGender(String op, java.lang.Integer val, String relation) {
		addCondition(17, op, relation, val);
	}

	public void setSelectGender(boolean val) {
		__select_flags[17] = val;
	}

	public void setConditionAutograph(String op, java.lang.String val) {
		setConditionAutograph(op, val, CONDITION_AND);
	}

	public void setConditionAutograph(String op, java.lang.String val, String relation) {
		addCondition(18, op, relation, val);
	}

	public void setSelectAutograph(boolean val) {
		__select_flags[18] = val;
	}

	public void setConditionAge(String op, java.lang.Integer val) {
		setConditionAge(op, val, CONDITION_AND);
	}

	public void setConditionAge(String op, java.lang.Integer val, String relation) {
		addCondition(19, op, relation, val);
	}

	public void setSelectAge(boolean val) {
		__select_flags[19] = val;
	}

	public void setConditionBirth(String op, java.util.Date val) {
		setConditionBirth(op, val, CONDITION_AND);
	}

	public void setConditionBirth(String op, java.util.Date val, String relation) {
		addCondition(20, op, relation, generateTimestampFromDate(val));
	}

	public void setSelectBirth(boolean val) {
		__select_flags[20] = val;
	}

	public void setConditionCard(String op, java.lang.String val) {
		setConditionCard(op, val, CONDITION_AND);
	}

	public void setConditionCard(String op, java.lang.String val, String relation) {
		addCondition(21, op, relation, val);
	}

	public void setSelectCard(boolean val) {
		__select_flags[21] = val;
	}

	public void setConditionAddress(String op, java.lang.String val) {
		setConditionAddress(op, val, CONDITION_AND);
	}

	public void setConditionAddress(String op, java.lang.String val, String relation) {
		addCondition(22, op, relation, val);
	}

	public void setSelectAddress(boolean val) {
		__select_flags[22] = val;
	}

	public void setConditionAlternateField1(String op, java.lang.String val) {
		setConditionAlternateField1(op, val, CONDITION_AND);
	}

	public void setConditionAlternateField1(String op, java.lang.String val, String relation) {
		addCondition(23, op, relation, val);
	}

	public void setSelectAlternateField1(boolean val) {
		__select_flags[23] = val;
	}

	public void setConditionAlternateField2(String op, java.lang.String val) {
		setConditionAlternateField2(op, val, CONDITION_AND);
	}

	public void setConditionAlternateField2(String op, java.lang.String val, String relation) {
		addCondition(24, op, relation, val);
	}

	public void setSelectAlternateField2(boolean val) {
		__select_flags[24] = val;
	}

	public void setConditionAlternateField3(String op, java.lang.String val) {
		setConditionAlternateField3(op, val, CONDITION_AND);
	}

	public void setConditionAlternateField3(String op, java.lang.String val, String relation) {
		addCondition(25, op, relation, val);
	}

	public void setSelectAlternateField3(boolean val) {
		__select_flags[25] = val;
	}

	public void setConditionLocked(String op, java.lang.Boolean val) {
		setConditionLocked(op, val, CONDITION_AND);
	}

	public void setConditionLocked(String op, java.lang.Boolean val, String relation) {
		addCondition(26, op, relation, val);
	}

	public void setSelectLocked(boolean val) {
		__select_flags[26] = val;
	}

	public void setConditionPlateId(String op, java.lang.Integer val) {
		setConditionPlateId(op, val, CONDITION_AND);
	}

	public void setConditionPlateId(String op, java.lang.Integer val, String relation) {
		addCondition(27, op, relation, val);
	}

	public void setSelectPlateId(boolean val) {
		__select_flags[27] = val;
	}

	public void setConditionDutyId(String op, java.lang.Integer val) {
		setConditionDutyId(op, val, CONDITION_AND);
	}

	public void setConditionDutyId(String op, java.lang.Integer val, String relation) {
		addCondition(28, op, relation, val);
	}

	public void setSelectDutyId(boolean val) {
		__select_flags[28] = val;
	}

	public void setConditionUserAcct(String op, java.lang.String val) {
		setConditionUserAcct(op, val, CONDITION_AND);
	}

	public void setConditionUserAcct(String op, java.lang.String val, String relation) {
		addCondition(29, op, relation, val);
	}

	public void setSelectUserAcct(boolean val) {
		__select_flags[29] = val;
	}

	public void setConditionEmployeeNameEn(String op, java.lang.String val) {
		setConditionEmployeeNameEn(op, val, CONDITION_AND);
	}

	public void setConditionEmployeeNameEn(String op, java.lang.String val, String relation) {
		addCondition(30, op, relation, val);
	}

	public void setSelectEmployeeNameEn(boolean val) {
		__select_flags[30] = val;
	}

	public void setConditionEducation(String op, java.lang.String val) {
		setConditionEducation(op, val, CONDITION_AND);
	}

	public void setConditionEducation(String op, java.lang.String val, String relation) {
		addCondition(31, op, relation, val);
	}

	public void setSelectEducation(boolean val) {
		__select_flags[31] = val;
	}

	public void setConditionDegree(String op, java.lang.String val) {
		setConditionDegree(op, val, CONDITION_AND);
	}

	public void setConditionDegree(String op, java.lang.String val, String relation) {
		addCondition(32, op, relation, val);
	}

	public void setSelectDegree(boolean val) {
		__select_flags[32] = val;
	}

	public void setConditionNationality(String op, java.lang.String val) {
		setConditionNationality(op, val, CONDITION_AND);
	}

	public void setConditionNationality(String op, java.lang.String val, String relation) {
		addCondition(33, op, relation, val);
	}

	public void setSelectNationality(boolean val) {
		__select_flags[33] = val;
	}

	public void setConditionMarriedStatus(String op, java.lang.String val) {
		setConditionMarriedStatus(op, val, CONDITION_AND);
	}

	public void setConditionMarriedStatus(String op, java.lang.String val, String relation) {
		addCondition(34, op, relation, val);
	}

	public void setSelectMarriedStatus(boolean val) {
		__select_flags[34] = val;
	}

	public void setConditionHealth(String op, java.lang.String val) {
		setConditionHealth(op, val, CONDITION_AND);
	}

	public void setConditionHealth(String op, java.lang.String val, String relation) {
		addCondition(35, op, relation, val);
	}

	public void setSelectHealth(boolean val) {
		__select_flags[35] = val;
	}

	public void setConditionWorkAddress(String op, java.lang.String val) {
		setConditionWorkAddress(op, val, CONDITION_AND);
	}

	public void setConditionWorkAddress(String op, java.lang.String val, String relation) {
		addCondition(36, op, relation, val);
	}

	public void setSelectWorkAddress(boolean val) {
		__select_flags[36] = val;
	}

	public void setConditionRegisteredAddress(String op, java.lang.String val) {
		setConditionRegisteredAddress(op, val, CONDITION_AND);
	}

	public void setConditionRegisteredAddress(String op, java.lang.String val, String relation) {
		addCondition(37, op, relation, val);
	}

	public void setSelectRegisteredAddress(boolean val) {
		__select_flags[37] = val;
	}

	public void setConditionOaId(String op, java.lang.Integer val) {
		setConditionOaId(op, val, CONDITION_AND);
	}

	public void setConditionOaId(String op, java.lang.Integer val, String relation) {
		addCondition(38, op, relation, val);
	}

	public void setSelectOaId(boolean val) {
		__select_flags[38] = val;
	}

	public void setConditionOaDepart(String op, java.lang.Integer val) {
		setConditionOaDepart(op, val, CONDITION_AND);
	}

	public void setConditionOaDepart(String op, java.lang.Integer val, String relation) {
		addCondition(39, op, relation, val);
	}

	public void setSelectOaDepart(boolean val) {
		__select_flags[39] = val;
	}

	public void setConditionIsHeadcount(String op, java.lang.Boolean val) {
		setConditionIsHeadcount(op, val, CONDITION_AND);
	}

	public void setConditionIsHeadcount(String op, java.lang.Boolean val, String relation) {
		addCondition(40, op, relation, val);
	}

	public void setSelectIsHeadcount(boolean val) {
		__select_flags[40] = val;
	}

	public void setConditionIsCheck(String op, java.lang.Boolean val) {
		setConditionIsCheck(op, val, CONDITION_AND);
	}

	public void setConditionIsCheck(String op, java.lang.Boolean val, String relation) {
		addCondition(41, op, relation, val);
	}

	public void setSelectIsCheck(boolean val) {
		__select_flags[41] = val;
	}

	public void setConditionDirectLeader(String op, java.lang.Integer val) {
		setConditionDirectLeader(op, val, CONDITION_AND);
	}

	public void setConditionDirectLeader(String op, java.lang.Integer val, String relation) {
		addCondition(42, op, relation, val);
	}

	public void setSelectDirectLeader(boolean val) {
		__select_flags[42] = val;
	}

	public void setConditionIsManager(String op, java.lang.Boolean val) {
		setConditionIsManager(op, val, CONDITION_AND);
	}

	public void setConditionIsManager(String op, java.lang.Boolean val, String relation) {
		addCondition(43, op, relation, val);
	}

	public void setSelectIsManager(boolean val) {
		__select_flags[43] = val;
	}

	public void setConditionPoliticalFace(String op, java.lang.Integer val) {
		setConditionPoliticalFace(op, val, CONDITION_AND);
	}

	public void setConditionPoliticalFace(String op, java.lang.Integer val, String relation) {
		addCondition(44, op, relation, val);
	}

	public void setSelectPoliticalFace(boolean val) {
		__select_flags[44] = val;
	}

	public void setConditionBirthplace(String op, java.lang.String val) {
		setConditionBirthplace(op, val, CONDITION_AND);
	}

	public void setConditionBirthplace(String op, java.lang.String val, String relation) {
		addCondition(45, op, relation, val);
	}

	public void setSelectBirthplace(boolean val) {
		__select_flags[45] = val;
	}

	public void setConditionCountry(String op, java.lang.String val) {
		setConditionCountry(op, val, CONDITION_AND);
	}

	public void setConditionCountry(String op, java.lang.String val, String relation) {
		addCondition(46, op, relation, val);
	}

	public void setSelectCountry(boolean val) {
		__select_flags[46] = val;
	}

	public void setConditionAccountLocation(String op, java.lang.String val) {
		setConditionAccountLocation(op, val, CONDITION_AND);
	}

	public void setConditionAccountLocation(String op, java.lang.String val, String relation) {
		addCondition(47, op, relation, val);
	}

	public void setSelectAccountLocation(boolean val) {
		__select_flags[47] = val;
	}

	public void setConditionLanguages(String op, java.lang.String val) {
		setConditionLanguages(op, val, CONDITION_AND);
	}

	public void setConditionLanguages(String op, java.lang.String val, String relation) {
		addCondition(48, op, relation, val);
	}

	public void setSelectLanguages(boolean val) {
		__select_flags[48] = val;
	}

	public void setConditionStartWorkDate(String op, java.util.Date val) {
		setConditionStartWorkDate(op, val, CONDITION_AND);
	}

	public void setConditionStartWorkDate(String op, java.util.Date val, String relation) {
		addCondition(49, op, relation, generateTimestampFromDate(val));
	}

	public void setSelectStartWorkDate(boolean val) {
		__select_flags[49] = val;
	}

	public void setConditionSocialComputerNumber(String op, java.lang.String val) {
		setConditionSocialComputerNumber(op, val, CONDITION_AND);
	}

	public void setConditionSocialComputerNumber(String op, java.lang.String val, String relation) {
		addCondition(50, op, relation, val);
	}

	public void setSelectSocialComputerNumber(boolean val) {
		__select_flags[50] = val;
	}

	public void setConditionFundAccount(String op, java.lang.String val) {
		setConditionFundAccount(op, val, CONDITION_AND);
	}

	public void setConditionFundAccount(String op, java.lang.String val, String relation) {
		addCondition(51, op, relation, val);
	}

	public void setSelectFundAccount(boolean val) {
		__select_flags[51] = val;
	}

	public void setConditionPositiveDate(String op, java.util.Date val) {
		setConditionPositiveDate(op, val, CONDITION_AND);
	}

	public void setConditionPositiveDate(String op, java.util.Date val, String relation) {
		addCondition(52, op, relation, generateTimestampFromDate(val));
	}

	public void setSelectPositiveDate(boolean val) {
		__select_flags[52] = val;
	}

	public void setConditionTryTime(String op, java.lang.String val) {
		setConditionTryTime(op, val, CONDITION_AND);
	}

	public void setConditionTryTime(String op, java.lang.String val, String relation) {
		addCondition(53, op, relation, val);
	}

	public void setSelectTryTime(boolean val) {
		__select_flags[53] = val;
	}

	public void setConditionContractStartDate(String op, java.util.Date val) {
		setConditionContractStartDate(op, val, CONDITION_AND);
	}

	public void setConditionContractStartDate(String op, java.util.Date val, String relation) {
		addCondition(54, op, relation, generateTimestampFromDate(val));
	}

	public void setSelectContractStartDate(boolean val) {
		__select_flags[54] = val;
	}

	public void setConditionContractEndDate(String op, java.util.Date val) {
		setConditionContractEndDate(op, val, CONDITION_AND);
	}

	public void setConditionContractEndDate(String op, java.util.Date val, String relation) {
		addCondition(55, op, relation, generateTimestampFromDate(val));
	}

	public void setSelectContractEndDate(boolean val) {
		__select_flags[55] = val;
	}

	public void setConditionOwnedCompany(String op, java.lang.String val) {
		setConditionOwnedCompany(op, val, CONDITION_AND);
	}

	public void setConditionOwnedCompany(String op, java.lang.String val, String relation) {
		addCondition(56, op, relation, val);
	}

	public void setSelectOwnedCompany(boolean val) {
		__select_flags[56] = val;
	}

	public void setConditionJobs(String op, java.lang.String val) {
		setConditionJobs(op, val, CONDITION_AND);
	}

	public void setConditionJobs(String op, java.lang.String val, String relation) {
		addCondition(57, op, relation, val);
	}

	public void setSelectJobs(boolean val) {
		__select_flags[57] = val;
	}

	public void setConditionPersonalBusinessRemark(String op, java.lang.String val) {
		setConditionPersonalBusinessRemark(op, val, CONDITION_AND);
	}

	public void setConditionPersonalBusinessRemark(String op, java.lang.String val, String relation) {
		addCondition(58, op, relation, val);
	}

	public void setSelectPersonalBusinessRemark(boolean val) {
		__select_flags[58] = val;
	}

	public void setConditionSelfIntroduction(String op, java.lang.String val) {
		setConditionSelfIntroduction(op, val, CONDITION_AND);
	}

	public void setConditionSelfIntroduction(String op, java.lang.String val, String relation) {
		addCondition(59, op, relation, val);
	}

	public void setSelectSelfIntroduction(boolean val) {
		__select_flags[59] = val;
	}

	public void setConditionLaborAttachments(String op, java.lang.String val) {
		setConditionLaborAttachments(op, val, CONDITION_AND);
	}

	public void setConditionLaborAttachments(String op, java.lang.String val, String relation) {
		addCondition(60, op, relation, val);
	}

	public void setSelectLaborAttachments(boolean val) {
		__select_flags[60] = val;
	}

	public void setConditionEmergencyContactPerson(String op, java.lang.String val) {
		setConditionEmergencyContactPerson(op, val, CONDITION_AND);
	}

	public void setConditionEmergencyContactPerson(String op, java.lang.String val, String relation) {
		addCondition(61, op, relation, val);
	}

	public void setSelectEmergencyContactPerson(boolean val) {
		__select_flags[61] = val;
	}

	public void setConditionEmergencyContactPhone(String op, java.lang.String val) {
		setConditionEmergencyContactPhone(op, val, CONDITION_AND);
	}

	public void setConditionEmergencyContactPhone(String op, java.lang.String val, String relation) {
		addCondition(62, op, relation, val);
	}

	public void setSelectEmergencyContactPhone(boolean val) {
		__select_flags[62] = val;
	}

	public void setConditionHomePhone(String op, java.lang.String val) {
		setConditionHomePhone(op, val, CONDITION_AND);
	}

	public void setConditionHomePhone(String op, java.lang.String val, String relation) {
		addCondition(63, op, relation, val);
	}

	public void setSelectHomePhone(boolean val) {
		__select_flags[63] = val;
	}

	public void setConditionNowAddress(String op, java.lang.String val) {
		setConditionNowAddress(op, val, CONDITION_AND);
	}

	public void setConditionNowAddress(String op, java.lang.String val, String relation) {
		addCondition(64, op, relation, val);
	}

	public void setSelectNowAddress(boolean val) {
		__select_flags[64] = val;
	}

	public void setConditionTryTimePay(String op, java.lang.String val) {
		setConditionTryTimePay(op, val, CONDITION_AND);
	}

	public void setConditionTryTimePay(String op, java.lang.String val, String relation) {
		addCondition(65, op, relation, val);
	}

	public void setSelectTryTimePay(boolean val) {
		__select_flags[65] = val;
	}

	public void setConditionPositivePay(String op, java.lang.String val) {
		setConditionPositivePay(op, val, CONDITION_AND);
	}

	public void setConditionPositivePay(String op, java.lang.String val, String relation) {
		addCondition(66, op, relation, val);
	}

	public void setSelectPositivePay(boolean val) {
		__select_flags[66] = val;
	}

	public void setConditionApplyEmployeeId(String op, java.lang.Integer val) {
		setConditionApplyEmployeeId(op, val, CONDITION_AND);
	}

	public void setConditionApplyEmployeeId(String op, java.lang.Integer val, String relation) {
		addCondition(67, op, relation, val);
	}

	public void setSelectApplyEmployeeId(boolean val) {
		__select_flags[67] = val;
	}

	public void setConditionBankCardNum(String op, java.lang.String val) {
		setConditionBankCardNum(op, val, CONDITION_AND);
	}

	public void setConditionBankCardNum(String op, java.lang.String val, String relation) {
		addCondition(68, op, relation, val);
	}

	public void setSelectBankCardNum(boolean val) {
		__select_flags[68] = val;
	}

	public void setConditionFileNumber(String op, java.lang.String val) {
		setConditionFileNumber(op, val, CONDITION_AND);
	}

	public void setConditionFileNumber(String op, java.lang.String val, String relation) {
		addCondition(69, op, relation, val);
	}

	public void setSelectFileNumber(boolean val) {
		__select_flags[69] = val;
	}

	public void setConditionHouseholdRegistration(String op, java.lang.Integer val) {
		setConditionHouseholdRegistration(op, val, CONDITION_AND);
	}

	public void setConditionHouseholdRegistration(String op, java.lang.Integer val, String relation) {
		addCondition(70, op, relation, val);
	}

	public void setSelectHouseholdRegistration(boolean val) {
		__select_flags[70] = val;
	}

	public void setConditionForeman(String op, java.lang.String val) {
		setConditionForeman(op, val, CONDITION_AND);
	}

	public void setConditionForeman(String op, java.lang.String val, String relation) {
		addCondition(71, op, relation, val);
	}

	public void setSelectForeman(boolean val) {
		__select_flags[71] = val;
	}

	public void setConditionCardAttachment(String op, java.lang.String val) {
		setConditionCardAttachment(op, val, CONDITION_AND);
	}

	public void setConditionCardAttachment(String op, java.lang.String val, String relation) {
		addCondition(72, op, relation, val);
	}

	public void setSelectCardAttachment(boolean val) {
		__select_flags[72] = val;
	}

	public void setConditionTechnicalAttachment(String op, java.lang.String val) {
		setConditionTechnicalAttachment(op, val, CONDITION_AND);
	}

	public void setConditionTechnicalAttachment(String op, java.lang.String val, String relation) {
		addCondition(73, op, relation, val);
	}

	public void setSelectTechnicalAttachment(boolean val) {
		__select_flags[73] = val;
	}

	public void setConditionEducationProof(String op, java.lang.String val) {
		setConditionEducationProof(op, val, CONDITION_AND);
	}

	public void setConditionEducationProof(String op, java.lang.String val, String relation) {
		addCondition(74, op, relation, val);
	}

	public void setSelectEducationProof(boolean val) {
		__select_flags[74] = val;
	}

	public void setConditionDegreeProof(String op, java.lang.String val) {
		setConditionDegreeProof(op, val, CONDITION_AND);
	}

	public void setConditionDegreeProof(String op, java.lang.String val, String relation) {
		addCondition(75, op, relation, val);
	}

	public void setSelectDegreeProof(boolean val) {
		__select_flags[75] = val;
	}

	public void setConditionEmployeeShift(String op, java.lang.Integer val) {
		setConditionEmployeeShift(op, val, CONDITION_AND);
	}

	public void setConditionEmployeeShift(String op, java.lang.Integer val, String relation) {
		addCondition(76, op, relation, val);
	}

	public void setSelectEmployeeShift(boolean val) {
		__select_flags[76] = val;
	}

	public void setConditionCardAddress(String op, java.lang.String val) {
		setConditionCardAddress(op, val, CONDITION_AND);
	}

	public void setConditionCardAddress(String op, java.lang.String val, String relation) {
		addCondition(77, op, relation, val);
	}

	public void setSelectCardAddress(boolean val) {
		__select_flags[77] = val;
	}

	public void setConditionAnnualPerformance(String op, java.lang.String val) {
		setConditionAnnualPerformance(op, val, CONDITION_AND);
	}

	public void setConditionAnnualPerformance(String op, java.lang.String val, String relation) {
		addCondition(78, op, relation, val);
	}

	public void setSelectAnnualPerformance(boolean val) {
		__select_flags[78] = val;
	}

	public void setConditionAnnualBonus(String op, java.lang.String val) {
		setConditionAnnualBonus(op, val, CONDITION_AND);
	}

	public void setConditionAnnualBonus(String op, java.lang.String val, String relation) {
		addCondition(79, op, relation, val);
	}

	public void setSelectAnnualBonus(boolean val) {
		__select_flags[79] = val;
	}

	public void setConditionCompanyWeixin(String op, java.lang.String val) {
		setConditionCompanyWeixin(op, val, CONDITION_AND);
	}

	public void setConditionCompanyWeixin(String op, java.lang.String val, String relation) {
		addCondition(80, op, relation, val);
	}

	public void setSelectCompanyWeixin(boolean val) {
		__select_flags[80] = val;
	}

	public void setConditionCompanyEmail(String op, java.lang.String val) {
		setConditionCompanyEmail(op, val, CONDITION_AND);
	}

	public void setConditionCompanyEmail(String op, java.lang.String val, String relation) {
		addCondition(81, op, relation, val);
	}

	public void setSelectCompanyEmail(boolean val) {
		__select_flags[81] = val;
	}

	public void setConditionCompanyId(String op, java.lang.Integer val) {
		setConditionCompanyId(op, val, CONDITION_AND);
	}

	public void setConditionCompanyId(String op, java.lang.Integer val, String relation) {
		addCondition(82, op, relation, val);
	}

	public void setSelectCompanyId(boolean val) {
		__select_flags[82] = val;
	}

	public void setConditionEcmcUserId(String op, java.lang.Integer val) {
		setConditionEcmcUserId(op, val, CONDITION_AND);
	}

	public void setConditionEcmcUserId(String op, java.lang.Integer val, String relation) {
		addCondition(83, op, relation, val);
	}

	public void setSelectEcmcUserId(boolean val) {
		__select_flags[83] = val;
	}

	public void setConditionOnboardStatus(String op, java.lang.Integer val) {
		setConditionOnboardStatus(op, val, CONDITION_AND);
	}

	public void setConditionOnboardStatus(String op, java.lang.Integer val, String relation) {
		addCondition(84, op, relation, val);
	}

	public void setSelectOnboardStatus(boolean val) {
		__select_flags[84] = val;
	}

	public void setConditionAttachmentRemark(String op, java.lang.String val) {
		setConditionAttachmentRemark(op, val, CONDITION_AND);
	}

	public void setConditionAttachmentRemark(String op, java.lang.String val, String relation) {
		addCondition(85, op, relation, val);
	}

	public void setSelectAttachmentRemark(boolean val) {
		__select_flags[85] = val;
	}

	public void setConditionWorkYearType(String op, java.lang.Integer val) {
		setConditionWorkYearType(op, val, CONDITION_AND);
	}

	public void setConditionWorkYearType(String op, java.lang.Integer val, String relation) {
		addCondition(86, op, relation, val);
	}

	public void setSelectWorkYearType(boolean val) {
		__select_flags[86] = val;
	}

	public void setConditionIsbusy(String op, java.lang.Integer val) {
		setConditionIsbusy(op, val, CONDITION_AND);
	}

	public void setConditionIsbusy(String op, java.lang.Integer val, String relation) {
		addCondition(87, op, relation, val);
	}

	public void setSelectIsbusy(boolean val) {
		__select_flags[87] = val;
	}

	public void setConditionForeignerIdCard(String op, java.lang.String val) {
		setConditionForeignerIdCard(op, val, CONDITION_AND);
	}

	public void setConditionForeignerIdCard(String op, java.lang.String val, String relation) {
		addCondition(88, op, relation, val);
	}

	public void setSelectForeignerIdCard(boolean val) {
		__select_flags[88] = val;
	}

	public void setConditionRealBirthdate(String op, java.util.Date val) {
		setConditionRealBirthdate(op, val, CONDITION_AND);
	}

	public void setConditionRealBirthdate(String op, java.util.Date val, String relation) {
		addCondition(89, op, relation, generateTimestampFromDate(val));
	}

	public void setSelectRealBirthdate(boolean val) {
		__select_flags[89] = val;
	}

	public void setConditionWorkYear(String op, java.lang.String val) {
		setConditionWorkYear(op, val, CONDITION_AND);
	}

	public void setConditionWorkYear(String op, java.lang.String val, String relation) {
		addCondition(90, op, relation, val);
	}

	public void setSelectWorkYear(boolean val) {
		__select_flags[90] = val;
	}

	public void setConditionShenzhenHouse(String op, java.lang.Integer val) {
		setConditionShenzhenHouse(op, val, CONDITION_AND);
	}

	public void setConditionShenzhenHouse(String op, java.lang.Integer val, String relation) {
		addCondition(91, op, relation, val);
	}

	public void setSelectShenzhenHouse(boolean val) {
		__select_flags[91] = val;
	}

	public void setConditionTransHouseholdDate(String op, java.util.Date val) {
		setConditionTransHouseholdDate(op, val, CONDITION_AND);
	}

	public void setConditionTransHouseholdDate(String op, java.util.Date val, String relation) {
		addCondition(92, op, relation, generateTimestampFromDate(val));
	}

	public void setSelectTransHouseholdDate(boolean val) {
		__select_flags[92] = val;
	}

	public void setConditionProfessionalDirection(String op, java.lang.Integer val) {
		setConditionProfessionalDirection(op, val, CONDITION_AND);
	}

	public void setConditionProfessionalDirection(String op, java.lang.Integer val, String relation) {
		addCondition(93, op, relation, val);
	}

	public void setSelectProfessionalDirection(boolean val) {
		__select_flags[93] = val;
	}

	public void setConditionRecruitmentSources(String op, java.lang.Integer val) {
		setConditionRecruitmentSources(op, val, CONDITION_AND);
	}

	public void setConditionRecruitmentSources(String op, java.lang.Integer val, String relation) {
		addCondition(94, op, relation, val);
	}

	public void setSelectRecruitmentSources(boolean val) {
		__select_flags[94] = val;
	}

	public void setConditionWechatNum(String op, java.lang.String val) {
		setConditionWechatNum(op, val, CONDITION_AND);
	}

	public void setConditionWechatNum(String op, java.lang.String val, String relation) {
		addCondition(95, op, relation, val);
	}

	public void setSelectWechatNum(boolean val) {
		__select_flags[95] = val;
	}

	public void setConditionAutoSignYear(String op, java.lang.Integer val) {
		setConditionAutoSignYear(op, val, CONDITION_AND);
	}

	public void setConditionAutoSignYear(String op, java.lang.Integer val, String relation) {
		addCondition(96, op, relation, val);
	}

	public void setSelectAutoSignYear(boolean val) {
		__select_flags[96] = val;
	}

	public void setConditionResumeAttachment(String op, java.lang.String val) {
		setConditionResumeAttachment(op, val, CONDITION_AND);
	}

	public void setConditionResumeAttachment(String op, java.lang.String val, String relation) {
		addCondition(97, op, relation, val);
	}

	public void setSelectResumeAttachment(boolean val) {
		__select_flags[97] = val;
	}

	public void setConditionSignatureAttachment(String op, java.lang.String val) {
		setConditionSignatureAttachment(op, val, CONDITION_AND);
	}

	public void setConditionSignatureAttachment(String op, java.lang.String val, String relation) {
		addCondition(98, op, relation, val);
	}

	public void setSelectSignatureAttachment(boolean val) {
		__select_flags[98] = val;
	}

	public void setConditionSocialAttachment(String op, java.lang.String val) {
		setConditionSocialAttachment(op, val, CONDITION_AND);
	}

	public void setConditionSocialAttachment(String op, java.lang.String val, String relation) {
		addCondition(99, op, relation, val);
	}

	public void setSelectSocialAttachment(boolean val) {
		__select_flags[99] = val;
	}

	public void setConditionFundAttachment(String op, java.lang.String val) {
		setConditionFundAttachment(op, val, CONDITION_AND);
	}

	public void setConditionFundAttachment(String op, java.lang.String val, String relation) {
		addCondition(100, op, relation, val);
	}

	public void setSelectFundAttachment(boolean val) {
		__select_flags[100] = val;
	}

	public void setConditionSpareAttachment1(String op, java.lang.String val) {
		setConditionSpareAttachment1(op, val, CONDITION_AND);
	}

	public void setConditionSpareAttachment1(String op, java.lang.String val, String relation) {
		addCondition(101, op, relation, val);
	}

	public void setSelectSpareAttachment1(boolean val) {
		__select_flags[101] = val;
	}

	public void setConditionSpareAttachment2(String op, java.lang.String val) {
		setConditionSpareAttachment2(op, val, CONDITION_AND);
	}

	public void setConditionSpareAttachment2(String op, java.lang.String val, String relation) {
		addCondition(102, op, relation, val);
	}

	public void setSelectSpareAttachment2(boolean val) {
		__select_flags[102] = val;
	}

	public void setConditionSpareAttachment3(String op, java.lang.String val) {
		setConditionSpareAttachment3(op, val, CONDITION_AND);
	}

	public void setConditionSpareAttachment3(String op, java.lang.String val, String relation) {
		addCondition(103, op, relation, val);
	}

	public void setSelectSpareAttachment3(boolean val) {
		__select_flags[103] = val;
	}

	public void setConditionEmployeeType(String op, java.lang.Integer val) {
		setConditionEmployeeType(op, val, CONDITION_AND);
	}

	public void setConditionEmployeeType(String op, java.lang.Integer val, String relation) {
		addCondition(104, op, relation, val);
	}

	public void setSelectEmployeeType(boolean val) {
		__select_flags[104] = val;
	}

	public void setConditionIsLongSickLeave(String op, java.lang.Boolean val) {
		setConditionIsLongSickLeave(op, val, CONDITION_AND);
	}

	public void setConditionIsLongSickLeave(String op, java.lang.Boolean val, String relation) {
		addCondition(105, op, relation, val);
	}

	public void setSelectIsLongSickLeave(boolean val) {
		__select_flags[105] = val;
	}

	public void setConditionBasicSalary(String op, java.math.BigDecimal val) {
		setConditionBasicSalary(op, val, CONDITION_AND);
	}

	public void setConditionBasicSalary(String op, java.math.BigDecimal val, String relation) {
		addCondition(106, op, relation, val);
	}

	public void setSelectBasicSalary(boolean val) {
		__select_flags[106] = val;
	}

	public void setConditionJobTitleAllowance(String op, java.math.BigDecimal val) {
		setConditionJobTitleAllowance(op, val, CONDITION_AND);
	}

	public void setConditionJobTitleAllowance(String op, java.math.BigDecimal val, String relation) {
		addCondition(107, op, relation, val);
	}

	public void setSelectJobTitleAllowance(boolean val) {
		__select_flags[107] = val;
	}

	public void setConditionPostAllowance(String op, java.math.BigDecimal val) {
		setConditionPostAllowance(op, val, CONDITION_AND);
	}

	public void setConditionPostAllowance(String op, java.math.BigDecimal val, String relation) {
		addCondition(108, op, relation, val);
	}

	public void setSelectPostAllowance(boolean val) {
		__select_flags[108] = val;
	}

	public void setConditionOther(String op, java.math.BigDecimal val) {
		setConditionOther(op, val, CONDITION_AND);
	}

	public void setConditionOther(String op, java.math.BigDecimal val, String relation) {
		addCondition(109, op, relation, val);
	}

	public void setSelectOther(boolean val) {
		__select_flags[109] = val;
	}

	public void setConditionCommunicationSubsidy(String op, java.math.BigDecimal val) {
		setConditionCommunicationSubsidy(op, val, CONDITION_AND);
	}

	public void setConditionCommunicationSubsidy(String op, java.math.BigDecimal val, String relation) {
		addCondition(110, op, relation, val);
	}

	public void setSelectCommunicationSubsidy(boolean val) {
		__select_flags[110] = val;
	}

	public void setConditionSubtotalMonthlyWage(String op, java.math.BigDecimal val) {
		setConditionSubtotalMonthlyWage(op, val, CONDITION_AND);
	}

	public void setConditionSubtotalMonthlyWage(String op, java.math.BigDecimal val, String relation) {
		addCondition(111, op, relation, val);
	}

	public void setSelectSubtotalMonthlyWage(boolean val) {
		__select_flags[111] = val;
	}

	public void setConditionMonthlyPreissuedBonus(String op, java.math.BigDecimal val) {
		setConditionMonthlyPreissuedBonus(op, val, CONDITION_AND);
	}

	public void setConditionMonthlyPreissuedBonus(String op, java.math.BigDecimal val, String relation) {
		addCondition(112, op, relation, val);
	}

	public void setSelectMonthlyPreissuedBonus(boolean val) {
		__select_flags[112] = val;
	}

	public void setConditionTotalMonthlyRemuneration(String op, java.math.BigDecimal val) {
		setConditionTotalMonthlyRemuneration(op, val, CONDITION_AND);
	}

	public void setConditionTotalMonthlyRemuneration(String op, java.math.BigDecimal val, String relation) {
		addCondition(113, op, relation, val);
	}

	public void setSelectTotalMonthlyRemuneration(boolean val) {
		__select_flags[113] = val;
	}

	public void setConditionPhotoId(String op, java.lang.Integer val) {
		setConditionPhotoId(op, val, CONDITION_AND);
	}

	public void setConditionPhotoId(String op, java.lang.Integer val, String relation) {
		addCondition(114, op, relation, val);
	}

	public void setSelectPhotoId(boolean val) {
		__select_flags[114] = val;
	}

	public void setConditionLaborAttachmentId(String op, java.lang.Integer val) {
		setConditionLaborAttachmentId(op, val, CONDITION_AND);
	}

	public void setConditionLaborAttachmentId(String op, java.lang.Integer val, String relation) {
		addCondition(115, op, relation, val);
	}

	public void setSelectLaborAttachmentId(boolean val) {
		__select_flags[115] = val;
	}

	public void setConditionCardAttachmentId(String op, java.lang.Integer val) {
		setConditionCardAttachmentId(op, val, CONDITION_AND);
	}

	public void setConditionCardAttachmentId(String op, java.lang.Integer val, String relation) {
		addCondition(116, op, relation, val);
	}

	public void setSelectCardAttachmentId(boolean val) {
		__select_flags[116] = val;
	}

	public void setConditionTechnicalAttachmentId(String op, java.lang.Integer val) {
		setConditionTechnicalAttachmentId(op, val, CONDITION_AND);
	}

	public void setConditionTechnicalAttachmentId(String op, java.lang.Integer val, String relation) {
		addCondition(117, op, relation, val);
	}

	public void setSelectTechnicalAttachmentId(boolean val) {
		__select_flags[117] = val;
	}

	public void setConditionEducationProofId(String op, java.lang.Integer val) {
		setConditionEducationProofId(op, val, CONDITION_AND);
	}

	public void setConditionEducationProofId(String op, java.lang.Integer val, String relation) {
		addCondition(118, op, relation, val);
	}

	public void setSelectEducationProofId(boolean val) {
		__select_flags[118] = val;
	}

	public void setConditionDegreeProofId(String op, java.lang.Integer val) {
		setConditionDegreeProofId(op, val, CONDITION_AND);
	}

	public void setConditionDegreeProofId(String op, java.lang.Integer val, String relation) {
		addCondition(119, op, relation, val);
	}

	public void setSelectDegreeProofId(boolean val) {
		__select_flags[119] = val;
	}

	public void setConditionBankCardAttachment(String op, java.lang.String val) {
		setConditionBankCardAttachment(op, val, CONDITION_AND);
	}

	public void setConditionBankCardAttachment(String op, java.lang.String val, String relation) {
		addCondition(120, op, relation, val);
	}

	public void setSelectBankCardAttachment(boolean val) {
		__select_flags[120] = val;
	}

	public void setConditionBankCardAttachmentId(String op, java.lang.Integer val) {
		setConditionBankCardAttachmentId(op, val, CONDITION_AND);
	}

	public void setConditionBankCardAttachmentId(String op, java.lang.Integer val, String relation) {
		addCondition(121, op, relation, val);
	}

	public void setSelectBankCardAttachmentId(boolean val) {
		__select_flags[121] = val;
	}


}

