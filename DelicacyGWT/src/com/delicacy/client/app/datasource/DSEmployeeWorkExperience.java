package com.delicacy.client.app.datasource;

import com.smartgwt.client.data.fields.*;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.FieldType;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.data.DBDataSource;

public class DSEmployeeWorkExperience extends DBDataSource
{


	public static DSEmployeeWorkExperience instance = null;

	public static DSEmployeeWorkExperience getInstance() {
		if(instance == null) {
			instance = new DSEmployeeWorkExperience("DSEmployeeWorkExperience");
		}
		return instance;
	}

	private final DataSourceIntegerField employeeWorkExperienceIdField;
	private final DataSourceIntegerField employeeIdField;
	private final DataSourceTextField workPlaceField;
	private final DataSourceDateField workexStartDateField;
	private final DataSourceDateField workexEndDateField;
	private final DataSourceTextField positionField;
	private final DataSourceTextField reasonOfLeavingField;
	private final DataSourceBooleanField isForeignCompanyField;

	public DSEmployeeWorkExperience(String dataSourceID) {

		super();
		setID(dataSourceID);
		setActionMode(ACTION_MODE_TABLE);
		setActionName("EmployeeWorkExperience");


		employeeWorkExperienceIdField = new DataSourceIntegerField("employeeWorkExperienceId", "主键编码");
		employeeWorkExperienceIdField.setLength(11);
		employeeWorkExperienceIdField.setPrimaryKey(true);
		employeeWorkExperienceIdField.setRequired(true);
		employeeWorkExperienceIdField.setHidden(true);


		employeeIdField = new DataSourceIntegerField("employeeId", "员工id");
		employeeIdField.setLength(11);
		employeeIdField.setRequired(false);
		employeeIdField.setHidden(true);
		employeeIdField.setValueMap(KeyValueManager.getValueMap("employees"));


		workPlaceField = new DataSourceTextField("workPlace", "工作单位");
		workPlaceField.setLength(64);
		workPlaceField.setRequired(false);
		workPlaceField.setHidden(false);


		workexStartDateField = new DataSourceDateField("workexStartDate", "开始日期");
		workexStartDateField.setRequired(false);
		workexStartDateField.setHidden(false);


		workexEndDateField = new DataSourceDateField("workexEndDate", "结束日期");
		workexEndDateField.setRequired(false);
		workexEndDateField.setHidden(false);


		positionField = new DataSourceTextField("position", "职位");
		positionField.setLength(64);
		positionField.setRequired(false);
		positionField.setHidden(false);


		reasonOfLeavingField = new DataSourceTextField("reasonOfLeaving", "离职原因");
		reasonOfLeavingField.setLength(512);
		reasonOfLeavingField.setRequired(false);
		reasonOfLeavingField.setHidden(false);
		
		isForeignCompanyField = new DataSourceBooleanField("isForeignCompany", "外资企业(含港澳台)");
		isForeignCompanyField.setRequired(false);
		isForeignCompanyField.setHidden(false);


		setFields(employeeWorkExperienceIdField, employeeIdField, workPlaceField, workexStartDateField, workexEndDateField, positionField, reasonOfLeavingField,isForeignCompanyField);
	}


}

