package com.delicacy.client.app.datasource;

import com.smartgwt.client.data.fields.*;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.FieldType;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.data.DBDataSource;

public class DSEmployeeEducationInformation extends DBDataSource
{


	public static DSEmployeeEducationInformation instance = null;

	public static DSEmployeeEducationInformation getInstance() {
		if(instance == null) {
			instance = new DSEmployeeEducationInformation("DSEmployeeEducationInformation");
		}
		return instance;
	}

	private final DataSourceIntegerField employeeEducationInformationIdField;
	private final DataSourceIntegerField employeeIdField;
	private final DataSourceTextField graduatedSchoolField;
	private final DataSourceTextField specialtyField;
	private final DataSourceDateField startDateField;
	private final DataSourceDateField endDateField;
	private final DataSourceIntegerField educationField;
	private final DataSourceIntegerField degreeField;
	private final DataSourceIntegerField learningWayField;

	public DSEmployeeEducationInformation(String dataSourceID) {

		super();
		setID(dataSourceID);
		setActionMode(ACTION_MODE_TABLE);
		setActionName("EmployeeEducationInformation");


		employeeEducationInformationIdField = new DataSourceIntegerField("employeeEducationInformationId", "主键编码");
		employeeEducationInformationIdField.setLength(11);
		employeeEducationInformationIdField.setPrimaryKey(true);
		employeeEducationInformationIdField.setRequired(true);
		employeeEducationInformationIdField.setHidden(true);


		employeeIdField = new DataSourceIntegerField("employeeId", "员工id");
		employeeIdField.setLength(11);
		employeeIdField.setRequired(false);
		employeeIdField.setHidden(true);
		employeeIdField.setValueMap(KeyValueManager.getValueMap("employees"));


		graduatedSchoolField = new DataSourceTextField("graduatedSchool", "毕业院校");
		graduatedSchoolField.setLength(64);
		graduatedSchoolField.setRequired(false);
		graduatedSchoolField.setHidden(false);


		specialtyField = new DataSourceTextField("specialty", "所学专业");
		specialtyField.setLength(64);
		specialtyField.setRequired(false);
		specialtyField.setHidden(false);


		startDateField = new DataSourceDateField("startDate", "开始日期");
		startDateField.setRequired(false);
		startDateField.setHidden(false);


		endDateField = new DataSourceDateField("endDate", "结束日期");
		endDateField.setRequired(false);
		endDateField.setHidden(false);


		educationField = new DataSourceIntegerField("education", "学历");
		educationField.setLength(11);
		educationField.setRequired(false);
		educationField.setHidden(false);
		educationField.setValueMap(KeyValueManager.getValueMap("system_dictionary_80"));


		degreeField = new DataSourceIntegerField("degree", "学位");
		degreeField.setLength(11);
		degreeField.setRequired(false);
		degreeField.setHidden(false);
		degreeField.setValueMap(KeyValueManager.getValueMap("system_dictionary_78"));


		learningWayField = new DataSourceIntegerField("learningWay", "学习形式");
		learningWayField.setLength(11);
		learningWayField.setRequired(false);
		learningWayField.setHidden(false);
		learningWayField.setValueMap(KeyValueManager.getValueMap("system_dictionary_79"));


		setFields(employeeEducationInformationIdField, employeeIdField, graduatedSchoolField, specialtyField, startDateField, endDateField, educationField, degreeField, learningWayField);
	}


}

