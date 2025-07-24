package com.delicacy.client.app.datasource;

import com.smartgwt.client.data.fields.*;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.FieldType;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.data.DBDataSource;

public class DSEmployeeTechnicalTitle extends DBDataSource
{


	public static DSEmployeeTechnicalTitle instance = null;

	public static DSEmployeeTechnicalTitle getInstance() {
		if(instance == null) {
			instance = new DSEmployeeTechnicalTitle("DSEmployeeTechnicalTitle");
		}
		return instance;
	}

	private final DataSourceIntegerField employeeTechnicalTitleIdField;
	private final DataSourceIntegerField employeeIdField;
	private final DataSourceTextField technicalTitlesField;
	private final DataSourceTextField technicalSpecialtyField;
	private final DataSourceTextField technicalLevelField;
	private final DataSourceDateField assessmentTimeField;

	public DSEmployeeTechnicalTitle(String dataSourceID) {

		super();
		setID(dataSourceID);
		setActionMode(ACTION_MODE_TABLE);
		setActionName("EmployeeTechnicalTitle");


		employeeTechnicalTitleIdField = new DataSourceIntegerField("employeeTechnicalTitleId", "主键编码");
		employeeTechnicalTitleIdField.setLength(11);
		employeeTechnicalTitleIdField.setPrimaryKey(true);
		employeeTechnicalTitleIdField.setRequired(true);
		employeeTechnicalTitleIdField.setHidden(true);


		employeeIdField = new DataSourceIntegerField("employeeId", "员工id");
		employeeIdField.setLength(11);
		employeeIdField.setRequired(false);
		employeeIdField.setHidden(true);
		employeeIdField.setValueMap(KeyValueManager.getValueMap("employees"));


		technicalTitlesField = new DataSourceTextField("technicalTitles", "职称");
		technicalTitlesField.setLength(64);
		technicalTitlesField.setRequired(false);
		technicalTitlesField.setHidden(false);


		technicalSpecialtyField = new DataSourceTextField("technicalSpecialty", "专业");
		technicalSpecialtyField.setLength(64);
		technicalSpecialtyField.setRequired(false);
		technicalSpecialtyField.setHidden(false);


		technicalLevelField = new DataSourceTextField("technicalLevel", "级别");
		technicalLevelField.setLength(64);
		technicalLevelField.setRequired(false);
		technicalLevelField.setHidden(false);


		assessmentTimeField = new DataSourceDateField("assessmentTime", "评定时间");
		assessmentTimeField.setRequired(false);
		assessmentTimeField.setHidden(false);


		setFields(employeeTechnicalTitleIdField, employeeIdField, technicalTitlesField, technicalSpecialtyField, technicalLevelField, assessmentTimeField);
	}


}

