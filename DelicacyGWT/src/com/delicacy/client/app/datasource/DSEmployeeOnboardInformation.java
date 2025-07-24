package com.delicacy.client.app.datasource;

import com.smartgwt.client.data.fields.*;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.FieldType;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.data.DBDataSource;

public class DSEmployeeOnboardInformation extends DBDataSource
{


	public static DSEmployeeOnboardInformation instance = null;

	public static DSEmployeeOnboardInformation getInstance() {
		if(instance == null) {
			instance = new DSEmployeeOnboardInformation("DSEmployeeOnboardInformation");
		}
		return instance;
	}

	private final DataSourceIntegerField employeeIdField;
	private final DataSourceTextField employeeNoField;
	private final DataSourceTextField employeeNameField;
	private final DataSourceIntegerField departmentIdField;
	private final DataSourceIntegerField roleIdField;
	private final DataSourceIntegerField gradeIdField;
	private final DataSourceTextField employeePasswordField;
	private final DataSourceTextField mobileField;
	private final DataSourceTextField phoneField;
	private final DataSourceTextField qqField;
	private final DataSourceTextField emailField;
	private final DataSourceDateField onboardDateField;
	private final DataSourceDateField resignationDateField;
	private final DataSourceIntegerField statusField;
	private final DataSourceBooleanField usableStatusField;
	private final DataSourceBooleanField isDepartmentField;
	private final DataSourceTextField photoField;
	private final DataSourceIntegerField genderField;
	private final DataSourceTextField autographField;
	private final DataSourceIntegerField ageField;
	private final DataSourceDateField birthField;
	private final DataSourceTextField cardField;
	private final DataSourceTextField addressField;
	private final DataSourceTextField alternateField1Field;
	private final DataSourceTextField alternateField2Field;
	private final DataSourceTextField alternateField3Field;
	private final DataSourceBooleanField lockedField;
	private final DataSourceIntegerField plateIdField;
	private final DataSourceIntegerField dutyIdField;
	private final DataSourceTextField userAcctField;
	private final DataSourceTextField employeeNameEnField;
	private final DataSourceTextField educationField;
	private final DataSourceTextField degreeField;
	private final DataSourceTextField nationalityField;
	private final DataSourceTextField marriedStatusField;
	private final DataSourceTextField healthField;
	private final DataSourceTextField workAddressField;
	private final DataSourceTextField registeredAddressField;
	private final DataSourceIntegerField oaIdField;
	private final DataSourceIntegerField oaDepartField;
	private final DataSourceBooleanField isHeadcountField;
	private final DataSourceBooleanField isCheckField;
	private final DataSourceIntegerField directLeaderField;
	private final DataSourceBooleanField isManagerField;
	private final DataSourceIntegerField politicalFaceField;
	private final DataSourceTextField birthplaceField;
	private final DataSourceTextField countryField;
	private final DataSourceTextField accountLocationField;
	private final DataSourceTextField languagesField;
	private final DataSourceDateField startWorkDateField;
	private final DataSourceTextField socialComputerNumberField;
	private final DataSourceTextField fundAccountField;
	private final DataSourceDateField positiveDateField;
	private final DataSourceTextField tryTimeField;
	private final DataSourceDateField contractStartDateField;
	private final DataSourceDateField contractEndDateField;
	private final DataSourceTextField ownedCompanyField;
	private final DataSourceTextField jobsField;
	private final DataSourceTextField personalBusinessRemarkField;
	private final DataSourceTextField selfIntroductionField;
	private final DataSourceTextField emergencyContactPersonField;
	private final DataSourceTextField emergencyContactPhoneField;
	private final DataSourceTextField homePhoneField;
	private final DataSourceTextField nowAddressField;
	private final DataSourceTextField tryTimePayField;
	private final DataSourceTextField positivePayField;
	private final DataSourceIntegerField applyEmployeeIdField;
	private final DataSourceTextField bankCardNumField;
	private final DataSourceTextField fileNumberField;
	private final DataSourceIntegerField householdRegistrationField;
	private final DataSourceTextField foremanField;
	private final DataSourceField detailEmployeeEducationInformation;
	private final DataSourceField detailEmployeeFamilyInformation;
	private final DataSourceField detailEmployeeRewardExperience;
	private final DataSourceField detailEmployeeWorkExperience;
	private final DataSourceField detailEmployeeTechnicalTitle;
	private final DataSourceIntegerField employeeShiftField;
	private final DataSourceTextField cardAddressField;
	private final DataSourceTextField annualPerformanceField;
	private final DataSourceTextField annualBonusField;
	private final DataSourceTextField companyEmailField;
	
	private final DataSourceTextField cardAttachmentField;
	private final DataSourceTextField technicalAttachmentField;
	private final DataSourceTextField laborAttachmentsField;
	private final DataSourceTextField educationProofField;
	private final DataSourceTextField degreeProofField;
	private final DataSourceIntegerField laborAttachmentIdField;
	private final DataSourceIntegerField cardAttachmentIdField;
	private final DataSourceIntegerField technicalAttachmentIdField;
	private final DataSourceIntegerField educationProofIdField;
	private final DataSourceIntegerField degreeProofIdField;
	private final DataSourceTextField bankCardAttachmentField;
	private final DataSourceIntegerField bankCardAttachmentIdField;
	private final DataSourceTextField workYearTypeField;
	private final DataSourceIntegerField professionalSequenceField;
	private final DataSourceIntegerField cityLevelField;

	public DSEmployeeOnboardInformation(String dataSourceID) {

		super();
		setID(dataSourceID);
		setActionMode(ACTION_MODE_QUERY);
		setActionName("EmployeeOnboardInformation");


		employeeIdField = new DataSourceIntegerField("employeeId", "员工编码");
		employeeIdField.setLength(11);
		employeeIdField.setPrimaryKey(true);
		employeeIdField.setRequired(true);
		employeeIdField.setHidden(true);
		employeeIdField.setValueMap(KeyValueManager.getValueMap("employees"));


		employeeNoField = new DataSourceTextField("employeeNo", "员工编号");
		employeeNoField.setLength(64);
		employeeNoField.setRequired(true);
		employeeNoField.setHidden(false);


		employeeNameField = new DataSourceTextField("employeeName", "员工姓名");
		employeeNameField.setLength(64);
		employeeNameField.setRequired(true);
		employeeNameField.setHidden(false);


		departmentIdField = new DataSourceIntegerField("departmentId", "所属部门");
		departmentIdField.setLength(11);
		departmentIdField.setRequired(false);
		departmentIdField.setHidden(false);
		departmentIdField.setValueMap(KeyValueManager.getValueMap("departments"));


		roleIdField = new DataSourceIntegerField("roleId", "角色");
		roleIdField.setLength(11);
		roleIdField.setRequired(false);
		roleIdField.setHidden(true);
//		roleIdField.setValueMap(KeyValueManager.getValueMap("roles"));
		KeyValueManager.loadValueMap("roles",roleIdField);
		gradeIdField = new DataSourceIntegerField("gradeId", "职级");
		gradeIdField.setLength(11);
		gradeIdField.setRequired(false);
		gradeIdField.setHidden(false);
		gradeIdField.setValueMap(KeyValueManager.getValueMap("system_dictionary_3"));


		employeePasswordField = new DataSourceTextField("employeePassword", "密码");
		employeePasswordField.setLength(64);
		employeePasswordField.setRequired(false);
		employeePasswordField.setHidden(true);


		mobileField = new DataSourceTextField("mobile", "手机号码");
		mobileField.setLength(64);
		mobileField.setRequired(false);
		mobileField.setHidden(false);


		phoneField = new DataSourceTextField("phone", "办公电话");
		phoneField.setLength(64);
		phoneField.setRequired(false);
		phoneField.setHidden(false);


		qqField = new DataSourceTextField("qq", "QQ");
		qqField.setLength(64);
		qqField.setRequired(false);
		qqField.setHidden(true);


		emailField = new DataSourceTextField("email", "办公邮箱");
		emailField.setLength(64);
		emailField.setRequired(false);
		emailField.setHidden(false);


		onboardDateField = new DataSourceDateField("onboardDate", "入职日期");
		onboardDateField.setRequired(false);
		onboardDateField.setHidden(false);


		resignationDateField = new DataSourceDateField("resignationDate", "离职日期");
		resignationDateField.setRequired(false);
		resignationDateField.setHidden(false);


		statusField = new DataSourceIntegerField("status", "人事状态");
		statusField.setLength(11);
		statusField.setRequired(false);
		statusField.setHidden(false);
		statusField.setValueMap(KeyValueManager.getValueMap("system_dictionary_11"));


		usableStatusField = new DataSourceBooleanField("usableStatus", "是否可用");
		usableStatusField.setRequired(false);
		usableStatusField.setHidden(true);


		isDepartmentField = new DataSourceBooleanField("isDepartment", "是否为部门负责人");
		isDepartmentField.setRequired(false);
		isDepartmentField.setHidden(true);


		photoField = new DataSourceTextField("photo", "头像");
		photoField.setLength(512);
		photoField.setRequired(false);
		photoField.setHidden(true);


		genderField = new DataSourceIntegerField("gender", "性别");
		genderField.setLength(11);
		genderField.setRequired(false);
		genderField.setHidden(false);
		genderField.setValueMap(KeyValueManager.getValueMap("system_dictionary_12"));

		autographField = new DataSourceTextField("autograph", "我的签名");
		autographField.setLength(512);
		autographField.setRequired(false);
		autographField.setHidden(true);


		ageField = new DataSourceIntegerField("age", "年龄");
		ageField.setLength(11);
		ageField.setRequired(false);
		ageField.setHidden(false);


		birthField = new DataSourceDateField("birth", "出生日期");
		birthField.setRequired(false);
		birthField.setHidden(false);


		cardField = new DataSourceTextField("card", "身份证号");
		cardField.setLength(64);
		cardField.setRequired(false);
		cardField.setHidden(false);


		addressField = new DataSourceTextField("address", "家庭地址");
		addressField.setLength(512);
		addressField.setRequired(false);
		addressField.setHidden(true);


		alternateField1Field = new DataSourceTextField("alternateField1", "备用字段1");
		alternateField1Field.setLength(512);
		alternateField1Field.setRequired(false);
		alternateField1Field.setHidden(true);


		alternateField2Field = new DataSourceTextField("alternateField2", "备用字段2");
		alternateField2Field.setLength(512);
		alternateField2Field.setRequired(false);
		alternateField2Field.setHidden(true);


		alternateField3Field = new DataSourceTextField("alternateField3", "备用字段3");
		alternateField3Field.setLength(512);
		alternateField3Field.setRequired(false);
		alternateField3Field.setHidden(true);


		lockedField = new DataSourceBooleanField("locked", "是否锁定");
		lockedField.setRequired(false);
		lockedField.setHidden(true);


		plateIdField = new DataSourceIntegerField("plateId", "业务部门");
		plateIdField.setLength(11);
		plateIdField.setRequired(false);
		plateIdField.setHidden(true);
		plateIdField.setValueMap(KeyValueManager.getValueMap("plate_records"));


		dutyIdField = new DataSourceIntegerField("dutyId", "岗位");
		dutyIdField.setLength(11);
		dutyIdField.setRequired(false);
		dutyIdField.setHidden(false);
		dutyIdField.setValueMap(KeyValueManager.getValueMap("duties"));


		userAcctField = new DataSourceTextField("userAcct", "上级领导");
		userAcctField.setLength(64);
		userAcctField.setRequired(false);
		userAcctField.setHidden(true);


		employeeNameEnField = new DataSourceTextField("employeeNameEn", "英文名");
		employeeNameEnField.setLength(64);
		employeeNameEnField.setRequired(false);
		employeeNameEnField.setHidden(false);


		educationField = new DataSourceTextField("education", "学历");
		educationField.setLength(64);
		educationField.setRequired(false);
		educationField.setHidden(false);
		educationField.setValueMap(KeyValueManager.getValueMap("system_dictionary_80"));

		degreeField = new DataSourceTextField("degree", "学位");
		degreeField.setLength(64);
		degreeField.setRequired(false);
		degreeField.setHidden(false);
		degreeField.setValueMap(KeyValueManager.getValueMap("system_dictionary_78"));

		nationalityField = new DataSourceTextField("nationality", "民族");
		nationalityField.setLength(64);
		nationalityField.setRequired(false);
		nationalityField.setHidden(false);
		nationalityField.setValueMap(KeyValueManager.getValueMap("nationalities"));

		marriedStatusField = new DataSourceTextField("marriedStatus", "婚姻状况");
		marriedStatusField.setLength(64);
		marriedStatusField.setRequired(false);
		marriedStatusField.setHidden(false);
		marriedStatusField.setValueMap(KeyValueManager.getValueMap("system_dictionary_77"));

		healthField = new DataSourceTextField("health", "健康状况");
		healthField.setLength(64);
		healthField.setRequired(false);
		healthField.setHidden(false);


		workAddressField = new DataSourceTextField("workAddress", "工作地点");
		workAddressField.setLength(128);
		workAddressField.setRequired(false);
		workAddressField.setHidden(false);


		registeredAddressField = new DataSourceTextField("registeredAddress", "注册地");
		registeredAddressField.setLength(128);
		registeredAddressField.setRequired(false);
		registeredAddressField.setHidden(true);


		oaIdField = new DataSourceIntegerField("oaId", "OA编码");
		oaIdField.setLength(11);
		oaIdField.setRequired(false);
		oaIdField.setHidden(true);


		oaDepartField = new DataSourceIntegerField("oaDepart", "OA部门");
		oaDepartField.setLength(11);
		oaDepartField.setRequired(false);
		oaDepartField.setHidden(true);


		isHeadcountField = new DataSourceBooleanField("isHeadcount", "总部员工");
		isHeadcountField.setRequired(false);
		isHeadcountField.setHidden(true);


		isCheckField = new DataSourceBooleanField("isCheck", "参与考勤");
		isCheckField.setRequired(false);
		isCheckField.setHidden(false);


		directLeaderField = new DataSourceIntegerField("directLeader", "直接领导");
		directLeaderField.setLength(11);
		directLeaderField.setRequired(false);
		directLeaderField.setHidden(true);


		isManagerField = new DataSourceBooleanField("isManager", "是否为超级管理员");
		isManagerField.setRequired(false);
		isManagerField.setHidden(true);


		politicalFaceField = new DataSourceIntegerField("politicalFace", "政治面貌");
		politicalFaceField.setLength(11);
		politicalFaceField.setRequired(false);
		politicalFaceField.setHidden(false);
		politicalFaceField.setValueMap(KeyValueManager.getValueMap("system_dictionary_76"));

		birthplaceField = new DataSourceTextField("birthplace", "籍贯");
		birthplaceField.setLength(64);
		birthplaceField.setRequired(false);
		birthplaceField.setHidden(false);


		countryField = new DataSourceTextField("country", "国籍");
		countryField.setLength(64);
		countryField.setRequired(false);
		countryField.setHidden(false);


		accountLocationField = new DataSourceTextField("accountLocation", "户口所在地");
		accountLocationField.setLength(64);
		accountLocationField.setRequired(false);
		accountLocationField.setHidden(false);


		languagesField = new DataSourceTextField("languages", "外语语种");
		languagesField.setLength(64);
		languagesField.setRequired(false);
		languagesField.setHidden(false);


		startWorkDateField = new DataSourceDateField("startWorkDate", "参加工作时间");
		startWorkDateField.setRequired(false);
		startWorkDateField.setHidden(false);


		socialComputerNumberField = new DataSourceTextField("socialComputerNumber", "社保电脑号");
		socialComputerNumberField.setLength(64);
		socialComputerNumberField.setRequired(false);
		socialComputerNumberField.setHidden(false);


		fundAccountField = new DataSourceTextField("fundAccount", "公积金账号");
		fundAccountField.setLength(64);
		fundAccountField.setRequired(false);
		fundAccountField.setHidden(false);


		positiveDateField = new DataSourceDateField("positiveDate", "转正日期");
		positiveDateField.setRequired(false);
		positiveDateField.setHidden(false);


		tryTimeField = new DataSourceTextField("tryTime", "试用期");
		tryTimeField.setLength(64);
		tryTimeField.setRequired(false);
		tryTimeField.setHidden(false);


		contractStartDateField = new DataSourceDateField("contractStartDate", "劳动合同开始时间");
		contractStartDateField.setRequired(false);
		contractStartDateField.setHidden(false);


		contractEndDateField = new DataSourceDateField("contractEndDate", "劳动合同到期时间");
		contractEndDateField.setRequired(false);
		contractEndDateField.setHidden(false);


		ownedCompanyField = new DataSourceTextField("ownedCompany", "所属公司");
		ownedCompanyField.setLength(64);
		ownedCompanyField.setRequired(false);
		ownedCompanyField.setHidden(false);
		ownedCompanyField.setValueMap(KeyValueManager.getValueMap("company_records"));

		jobsField = new DataSourceTextField("jobs", "职务");
		jobsField.setLength(64);
		jobsField.setRequired(false);
		jobsField.setHidden(false);


		personalBusinessRemarkField = new DataSourceTextField("personalBusinessRemark", "人事备注");
		personalBusinessRemarkField.setLength(64);
		personalBusinessRemarkField.setRequired(false);
		personalBusinessRemarkField.setHidden(false);


		selfIntroductionField = new DataSourceTextField("selfIntroduction", "个人介绍");
		selfIntroductionField.setLength(512);
		selfIntroductionField.setRequired(false);
		selfIntroductionField.setHidden(false);


		emergencyContactPersonField = new DataSourceTextField("emergencyContactPerson", "紧急联系人");
		emergencyContactPersonField.setLength(64);
		emergencyContactPersonField.setRequired(false);
		emergencyContactPersonField.setHidden(true);


		emergencyContactPhoneField = new DataSourceTextField("emergencyContactPhone", "紧急联系人电话");
		emergencyContactPhoneField.setLength(64);
		emergencyContactPhoneField.setRequired(false);
		emergencyContactPhoneField.setHidden(true);


		homePhoneField = new DataSourceTextField("homePhone", "家庭电话");
		homePhoneField.setLength(64);
		homePhoneField.setRequired(false);
		homePhoneField.setHidden(false);


		nowAddressField = new DataSourceTextField("nowAddress", "现住地址");
		nowAddressField.setLength(512);
		nowAddressField.setRequired(false);
		nowAddressField.setHidden(false);


		tryTimePayField = new DataSourceTextField("tryTimePay", "试用期工资");
		tryTimePayField.setLength(64);
		tryTimePayField.setRequired(false);
		tryTimePayField.setHidden(false);


		positivePayField = new DataSourceTextField("positivePay", "转正工资");
		positivePayField.setLength(64);
		positivePayField.setRequired(false);
		positivePayField.setHidden(false);


		applyEmployeeIdField = new DataSourceIntegerField("applyEmployeeId", "发起人");
		applyEmployeeIdField.setLength(11);
		applyEmployeeIdField.setRequired(false);
		applyEmployeeIdField.setHidden(true);
		applyEmployeeIdField.setValueMap(KeyValueManager.getValueMap("employees"));


		bankCardNumField = new DataSourceTextField("bankCardNum", "银行卡号");
		bankCardNumField.setLength(64);
		bankCardNumField.setRequired(false);
		bankCardNumField.setHidden(false);

		workYearTypeField = new DataSourceTextField("workYearType" , "年假的封顶天数");
		workYearTypeField.setLength(64);
		workYearTypeField.setRequired(false);
		workYearTypeField.setHidden(false);
		workYearTypeField.setValueMap(KeyValueManager.getValueMap("system_dictionary_115"));


		fileNumberField = new DataSourceTextField("fileNumber", "档案号");
		fileNumberField.setLength(64);
		fileNumberField.setRequired(false);
		fileNumberField.setHidden(false);


		householdRegistrationField = new DataSourceIntegerField("householdRegistration", "户籍");
		householdRegistrationField.setLength(11);
		householdRegistrationField.setRequired(false);
		householdRegistrationField.setHidden(false);
		householdRegistrationField.setValueMap(KeyValueManager.getValueMap("system_dictionary_81"));

		foremanField = new DataSourceTextField("foreman", "司龄");
		foremanField.setLength(32);
		foremanField.setRequired(false);
		foremanField.setHidden(false);


		cardAttachmentField = new DataSourceTextField("cardAttachment", "身份证附件");
		cardAttachmentField.setLength(128);
		cardAttachmentField.setRequired(false);
		cardAttachmentField.setHidden(true);


		laborAttachmentsField = new DataSourceTextField("laborAttachments", "劳动合同附件");
		laborAttachmentsField.setLength(128);
		laborAttachmentsField.setRequired(false);
		laborAttachmentsField.setHidden(true);

		educationProofField = new DataSourceTextField("educationProof", "学历证");
		educationProofField.setLength(128);
		educationProofField.setRequired(false);
		educationProofField.setHidden(true);

		degreeProofField = new DataSourceTextField("degreeProof", "学位证");
		degreeProofField.setLength(128);
		degreeProofField.setRequired(false);
		degreeProofField.setHidden(true);
		
		employeeShiftField = new DataSourceIntegerField("employeeShift","员工排班");
		employeeShiftField.setLength(11);
		employeeShiftField.setRequired(false);
		employeeShiftField.setHidden(false);
		employeeShiftField.setValueMap(KeyValueManager.getValueMap("shift_manages"));
		
		cardAddressField = new DataSourceTextField("cardAddress", "身份证地址");
		cardAddressField.setLength(128);
		cardAddressField.setRequired(false);
		cardAddressField.setHidden(false);
		
		annualPerformanceField = new DataSourceTextField("annualPerformance", "年度绩效");
		annualPerformanceField.setLength(64);
		annualPerformanceField.setRequired(true);
		annualPerformanceField.setHidden(false);
		
		annualBonusField = new DataSourceTextField("annualBonus", "年度奖金");
		annualBonusField.setLength(64);
		annualBonusField.setRequired(true);
		annualBonusField.setHidden(false);
		
		technicalAttachmentField = new DataSourceTextField("technicalAttachment", "职称证附件");
		technicalAttachmentField.setLength(128);
		technicalAttachmentField.setRequired(true);
		technicalAttachmentField.setHidden(false);
		
		bankCardAttachmentField = new DataSourceTextField("bankCardAttachment", "职称证附件");
		bankCardAttachmentField.setLength(128);
		bankCardAttachmentField.setRequired(true);
		bankCardAttachmentField.setHidden(false);
		
		companyEmailField = new DataSourceTextField("companyEmail", "企业邮箱");
		companyEmailField.setLength(128);
//		companyEmailField.setRequired(true);
		companyEmailField.setHidden(false);
		
		laborAttachmentIdField = new DataSourceIntegerField("laborAttachmentId", "劳动合同附件ID");
		laborAttachmentIdField.setLength(11);
		laborAttachmentIdField.setRequired(false);
		laborAttachmentIdField.setHidden(true);
		
		cardAttachmentIdField = new DataSourceIntegerField("cardAttachmentId", "身份证附件ID");
		cardAttachmentIdField.setLength(11);
		cardAttachmentIdField.setRequired(false);
		cardAttachmentIdField.setHidden(true);
		
		technicalAttachmentIdField = new DataSourceIntegerField("technicalAttachmentId", "职称证明ID");
		technicalAttachmentIdField.setLength(11);
		technicalAttachmentIdField.setRequired(false);
		technicalAttachmentIdField.setHidden(true);
		
		educationProofIdField = new DataSourceIntegerField("educationProofId", "学历证ID");
		educationProofIdField.setLength(11);
		educationProofIdField.setRequired(false);
		educationProofIdField.setHidden(true);
		
		degreeProofIdField = new DataSourceIntegerField("degreeProofId", "学位证ID");
		degreeProofIdField.setLength(11);
		degreeProofIdField.setRequired(false);
		degreeProofIdField.setHidden(true);
		

		bankCardAttachmentIdField = new DataSourceIntegerField("bankCardAttachmentId", "银行卡附件ID");
		bankCardAttachmentIdField.setLength(11);
		bankCardAttachmentIdField.setRequired(false);
		bankCardAttachmentIdField.setHidden(true);


		professionalSequenceField = new DataSourceIntegerField("professionalSequence","专业序列");
		professionalSequenceField.setLength(11);
		professionalSequenceField.setRequired(false);
		professionalSequenceField.setHidden(false);
		professionalSequenceField.setValueMap(KeyValueManager.getValueMap("system_dictionary_378"));

		cityLevelField = new DataSourceIntegerField("cityLevel","城市等级");
		cityLevelField.setLength(11);
		cityLevelField.setRequired(false);
		cityLevelField.setHidden(false);
		cityLevelField.setValueMap(KeyValueManager.getValueMap("system_dictionary_384"));
		
		
		detailEmployeeEducationInformation = new DataSourceField("detailEmployeeEducationInformation", FieldType.ANY);
		detailEmployeeEducationInformation.setChildrenProperty(true);
		detailEmployeeEducationInformation.setChildTagName("EmployeeEducationInformation");
		detailEmployeeEducationInformation.setRequired(false);
		detailEmployeeEducationInformation.setHidden(true);
		detailEmployeeFamilyInformation = new DataSourceField("detailEmployeeFamilyInformation", FieldType.ANY);
		detailEmployeeFamilyInformation.setChildrenProperty(true);
		detailEmployeeFamilyInformation.setChildTagName("EmployeeFamilyInformation");
		detailEmployeeFamilyInformation.setRequired(false);
		detailEmployeeFamilyInformation.setHidden(true);
		detailEmployeeRewardExperience = new DataSourceField("detailEmployeeRewardExperience", FieldType.ANY);
		detailEmployeeRewardExperience.setChildrenProperty(true);
		detailEmployeeRewardExperience.setChildTagName("EmployeeRewardExperience");
		detailEmployeeRewardExperience.setRequired(false);
		detailEmployeeRewardExperience.setHidden(true);
		detailEmployeeWorkExperience = new DataSourceField("detailEmployeeWorkExperience", FieldType.ANY);
		detailEmployeeWorkExperience.setChildrenProperty(true);
		detailEmployeeWorkExperience.setChildTagName("EmployeeWorkExperience");
		detailEmployeeWorkExperience.setRequired(false);
		detailEmployeeWorkExperience.setHidden(true);
		detailEmployeeTechnicalTitle = new DataSourceField("detailEmployeeTechnicalTitle", FieldType.ANY);
		detailEmployeeTechnicalTitle.setChildrenProperty(true);
		detailEmployeeTechnicalTitle.setChildTagName("EmployeeTechnicalTitle");
		detailEmployeeTechnicalTitle.setRequired(false);
		detailEmployeeTechnicalTitle.setHidden(true);

		setFields(employeeIdField, employeeNoField, employeeNameField, departmentIdField, roleIdField, gradeIdField, employeePasswordField, mobileField, phoneField, qqField, emailField, onboardDateField, resignationDateField, statusField, usableStatusField, isDepartmentField, photoField, genderField, autographField, ageField, birthField, cardField, addressField, alternateField1Field, alternateField2Field, alternateField3Field, lockedField, plateIdField, dutyIdField, userAcctField, employeeNameEnField, educationField, degreeField, nationalityField, marriedStatusField, healthField, workAddressField, registeredAddressField, oaIdField, oaDepartField, isHeadcountField, isCheckField, directLeaderField, isManagerField, politicalFaceField, birthplaceField, countryField, accountLocationField, languagesField, startWorkDateField, socialComputerNumberField, fundAccountField, positiveDateField, tryTimeField, contractStartDateField, contractEndDateField, ownedCompanyField, jobsField, personalBusinessRemarkField, selfIntroductionField, emergencyContactPersonField, emergencyContactPhoneField, homePhoneField, nowAddressField, tryTimePayField, positivePayField, applyEmployeeIdField, bankCardNumField,workYearTypeField, fileNumberField, householdRegistrationField, foremanField, cardAttachmentField, laborAttachmentsField, educationProofField,  degreeProofField, employeeShiftField,cardAddressField,annualPerformanceField,annualBonusField,companyEmailField,technicalAttachmentField,bankCardAttachmentField, detailEmployeeEducationInformation, detailEmployeeFamilyInformation, detailEmployeeRewardExperience, detailEmployeeWorkExperience, detailEmployeeTechnicalTitle,laborAttachmentIdField,cardAttachmentIdField,technicalAttachmentIdField,educationProofIdField,degreeProofIdField,bankCardAttachmentIdField,professionalSequenceField,cityLevelField);
	}


}

