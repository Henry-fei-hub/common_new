package com.delicacy.client.app.datasource;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.*;
import com.delicacy.client.data.KeyValueManager;

public class CDMemployeeNew extends DataSource
{


	public static CDMemployeeNew instance = null;

	public static CDMemployeeNew getInstance() {
		if(instance == null) {
			instance = new CDMemployeeNew("CDMemployeeNew");
		}
		return instance;
	}

	private final DataSourceIntegerField plateIdField;
	private final DataSourceTextField employeeNoField;
	private final DataSourceTextField employeeNameField;
	private final DataSourceIntegerField departmentIdField;
	private final DataSourceIntegerField roleIdField;
	private final DataSourceIntegerField gradeidField;
	private final DataSourceIntegerField statusField;
	private final DataSourceBooleanField isDepartmentField;
	private final DataSourceIntegerField genderField;
	private final DataSourceIntegerField dutyIdField;
	private final DataSourceFloatField onboardYearField;
	private final DataSourceFloatField onboardMonthField;
	private final DataSourceFloatField resiYearField;
	private final DataSourceFloatField resiMonthField;
	private final DataSourceFloatField birthMonthField;
	private final DataSourceBooleanField isCheckField;
	private final DataSourceTextField mobileField;
	private final DataSourceTextField emailField;
	private final DataSourceTextField cardField;
	private final DataSourceTextField addressField;
	private final DataSourceTextField educationField;
	private final DataSourceTextField degreeField;
	private final DataSourceTextField nationalityField;
	private final DataSourceTextField marriedStatusField;
	private final DataSourceTextField workaddressField;
	private final DataSourceTextField birthplaceField;
	private final DataSourceTextField accountLocationField;
	private final DataSourceFloatField startWorkYearField;
	private final DataSourceTextField socialComputerNumberField;
	private final DataSourceTextField fundAccountField;
	private final DataSourceDateField positiveDateField;
	private final DataSourceTextField trytimeField;
	private final DataSourceFloatField contractStartDateField;
	private final DataSourceFloatField contractStartMonthField;
	private final DataSourceFloatField contractEndYearField;
	private final DataSourceFloatField contractEndMonthField;
	private final DataSourceTextField ownedCompanyField;
	private final DataSourceTextField bankCardNumField;
	private final DataSourceTextField companyWeixinField;
	private final DataSourceTextField companyEmailField;

	public CDMemployeeNew(String dataSourceID) {

		setID(dataSourceID);
		plateIdField = new DataSourceIntegerField("plateId", "板块");
		plateIdField.setRequired(false);
		plateIdField.setLength(11);
		plateIdField.setHidden(false);

		employeeNoField = new DataSourceTextField("employeeNo", "员工编号");
		employeeNoField.setRequired(true);
		employeeNoField.setLength(64);
		employeeNoField.setHidden(false);

		employeeNameField = new DataSourceTextField("employeeName", "员工姓名");
		employeeNameField.setRequired(true);
		employeeNameField.setLength(64);
		employeeNameField.setHidden(false);
		employeeNameField.setValueMap(KeyValueManager.getValueMap("departments"));

		departmentIdField = new DataSourceIntegerField("departmentId", "部门");
		departmentIdField.setRequired(false);
		departmentIdField.setLength(11);
		departmentIdField.setHidden(false);
		departmentIdField.setValueMap(KeyValueManager.getValueMap("departments"));

		roleIdField = new DataSourceIntegerField("roleId", "角色编码");
		roleIdField.setRequired(false);
		roleIdField.setLength(11);
		roleIdField.setHidden(false);

		gradeidField = new DataSourceIntegerField("gradeid", "等级");
		gradeidField.setRequired(false);
		gradeidField.setLength(11);
		gradeidField.setHidden(false);

		statusField = new DataSourceIntegerField("status", "4删除)");
		statusField.setRequired(false);
		statusField.setLength(11);
		statusField.setHidden(false);

		isDepartmentField = new DataSourceBooleanField("isDepartment", "是否为部门负责人");
		isDepartmentField.setRequired(false);
		isDepartmentField.setHidden(false);

		genderField = new DataSourceIntegerField("gender", "性别");
		genderField.setRequired(false);
		genderField.setLength(11);
		genderField.setHidden(false);

		dutyIdField = new DataSourceIntegerField("dutyId", "职位名称");
		dutyIdField.setRequired(false);
		dutyIdField.setLength(11);
		dutyIdField.setHidden(false);

		onboardYearField = new DataSourceFloatField("onboardYear", "undefined");
		onboardYearField.setRequired(true);
		onboardYearField.setLength(0);
		onboardYearField.setHidden(false);

		onboardMonthField = new DataSourceFloatField("onboardMonth", "undefined");
		onboardMonthField.setRequired(true);
		onboardMonthField.setLength(0);
		onboardMonthField.setHidden(false);

		resiYearField = new DataSourceFloatField("resiYear", "undefined");
		resiYearField.setRequired(true);
		resiYearField.setLength(0);
		resiYearField.setHidden(false);

		resiMonthField = new DataSourceFloatField("resiMonth", "undefined");
		resiMonthField.setRequired(true);
		resiMonthField.setLength(0);
		resiMonthField.setHidden(false);

		birthMonthField = new DataSourceFloatField("birthMonth", "undefined");
		birthMonthField.setRequired(true);
		birthMonthField.setLength(0);
		birthMonthField.setHidden(false);

		isCheckField = new DataSourceBooleanField("isCheck", "参与考勤");
		isCheckField.setRequired(false);
		isCheckField.setHidden(false);

		mobileField = new DataSourceTextField("mobile", "手机");
		mobileField.setRequired(false);
		mobileField.setLength(64);
		mobileField.setHidden(false);

		emailField = new DataSourceTextField("email", "邮箱");
		emailField.setRequired(false);
		emailField.setLength(64);
		emailField.setHidden(false);

		cardField = new DataSourceTextField("card", "身份证号");
		cardField.setRequired(false);
		cardField.setLength(64);
		cardField.setHidden(false);

		addressField = new DataSourceTextField("address", "家庭地址");
		addressField.setRequired(false);
		addressField.setLength(512);
		addressField.setHidden(false);

		educationField = new DataSourceTextField("education", "学历");
		educationField.setRequired(false);
		educationField.setLength(64);
		educationField.setHidden(false);

		degreeField = new DataSourceTextField("degree", "学位");
		degreeField.setRequired(false);
		degreeField.setLength(64);
		degreeField.setHidden(false);

		nationalityField = new DataSourceTextField("nationality", "民族");
		nationalityField.setRequired(false);
		nationalityField.setLength(64);
		nationalityField.setHidden(false);

		marriedStatusField = new DataSourceTextField("marriedStatus", "婚姻状况");
		marriedStatusField.setRequired(false);
		marriedStatusField.setLength(64);
		marriedStatusField.setHidden(false);

		workaddressField = new DataSourceTextField("workaddress", "工作地");
		workaddressField.setRequired(false);
		workaddressField.setLength(128);
		workaddressField.setHidden(false);

		birthplaceField = new DataSourceTextField("birthplace", "籍贯");
		birthplaceField.setRequired(false);
		birthplaceField.setLength(64);
		birthplaceField.setHidden(false);

		accountLocationField = new DataSourceTextField("accountLocation", "户口所在地");
		accountLocationField.setRequired(false);
		accountLocationField.setLength(64);
		accountLocationField.setHidden(false);

		startWorkYearField = new DataSourceFloatField("startWorkYear", "undefined");
		startWorkYearField.setRequired(true);
		startWorkYearField.setLength(0);
		startWorkYearField.setHidden(false);

		socialComputerNumberField = new DataSourceTextField("socialComputerNumber", "社保电脑号");
		socialComputerNumberField.setRequired(false);
		socialComputerNumberField.setLength(64);
		socialComputerNumberField.setHidden(false);

		fundAccountField = new DataSourceTextField("fundAccount", "公积金账号");
		fundAccountField.setRequired(false);
		fundAccountField.setLength(64);
		fundAccountField.setHidden(false);

		positiveDateField = new DataSourceDateField("positiveDate", "转正日期");
		positiveDateField.setRequired(false);
		positiveDateField.setHidden(false);

		trytimeField = new DataSourceTextField("trytime", "试用期");
		trytimeField.setRequired(false);
		trytimeField.setLength(64);
		trytimeField.setHidden(false);

		contractStartDateField = new DataSourceFloatField("contractStartDate", "undefined");
		contractStartDateField.setRequired(true);
		contractStartDateField.setLength(0);
		contractStartDateField.setHidden(false);

		contractStartMonthField = new DataSourceFloatField("contractStartMonth", "undefined");
		contractStartMonthField.setRequired(true);
		contractStartMonthField.setLength(0);
		contractStartMonthField.setHidden(false);

		contractEndYearField = new DataSourceFloatField("contractEndYear", "undefined");
		contractEndYearField.setRequired(true);
		contractEndYearField.setLength(0);
		contractEndYearField.setHidden(false);

		contractEndMonthField = new DataSourceFloatField("contractEndMonth", "undefined");
		contractEndMonthField.setRequired(true);
		contractEndMonthField.setLength(0);
		contractEndMonthField.setHidden(false);

		ownedCompanyField = new DataSourceTextField("ownedCompany", "所属公司");
		ownedCompanyField.setRequired(false);
		ownedCompanyField.setLength(64);
		ownedCompanyField.setHidden(false);

		bankCardNumField = new DataSourceTextField("bankCardNum", "银行卡号");
		bankCardNumField.setRequired(false);
		bankCardNumField.setLength(64);
		bankCardNumField.setHidden(false);

		companyWeixinField = new DataSourceTextField("companyWeixin", "企业微信账号");
		companyWeixinField.setRequired(false);
		companyWeixinField.setLength(255);
		companyWeixinField.setHidden(false);

		companyEmailField = new DataSourceTextField("companyEmail", "企业邮箱");
		companyEmailField.setRequired(false);
		companyEmailField.setLength(255);
		companyEmailField.setHidden(false);

		DataSourceIntegerField currentPageField
			= new DataSourceIntegerField("currentPage", "当前页");
		currentPageField.setRequired(true);
		currentPageField.setLength(10);
		currentPageField.setHidden(true);

		DataSourceIntegerField pageLinesField
			= new DataSourceIntegerField("pageLines", "每页行数");
		pageLinesField.setRequired(true);
		pageLinesField.setLength(10);
		pageLinesField.setHidden(true);


		setFields(plateIdField, employeeNoField, employeeNameField, departmentIdField, roleIdField, gradeidField, statusField, isDepartmentField, genderField, dutyIdField, onboardYearField, onboardMonthField, resiYearField, resiMonthField, birthMonthField, isCheckField, mobileField, emailField, cardField, addressField, educationField, degreeField, nationalityField, marriedStatusField, workaddressField, birthplaceField, accountLocationField, startWorkYearField, socialComputerNumberField, fundAccountField, positiveDateField, trytimeField, contractStartDateField, contractStartMonthField, contractEndYearField, contractEndMonthField, ownedCompanyField, bankCardNumField, companyWeixinField, companyEmailField, currentPageField, pageLinesField);

		setClientOnly(true);
	}


}

