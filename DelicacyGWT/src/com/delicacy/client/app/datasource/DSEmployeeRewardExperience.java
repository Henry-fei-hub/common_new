package com.delicacy.client.app.datasource;

import com.smartgwt.client.data.fields.*;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.FieldType;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.data.DBDataSource;

public class DSEmployeeRewardExperience extends DBDataSource
{


	public static DSEmployeeRewardExperience instance = null;

	public static DSEmployeeRewardExperience getInstance() {
		if(instance == null) {
			instance = new DSEmployeeRewardExperience("DSEmployeeRewardExperience");
		}
		return instance;
	}

	private final DataSourceIntegerField employeeRewardExperienceIdField;
	private final DataSourceIntegerField employeeIdField;
	private final DataSourceTextField rewardNameField;
	private final DataSourceTextField rewardDescriptionField;
	private final DataSourceDateField rewardDateField;
	private final DataSourceTextField rewardUnitField;

	public DSEmployeeRewardExperience(String dataSourceID) {

		super();
		setID(dataSourceID);
		setActionMode(ACTION_MODE_TABLE);
		setActionName("EmployeeRewardExperience");


		employeeRewardExperienceIdField = new DataSourceIntegerField("employeeRewardExperienceId", "主键编码");
		employeeRewardExperienceIdField.setLength(11);
		employeeRewardExperienceIdField.setPrimaryKey(true);
		employeeRewardExperienceIdField.setRequired(true);
		employeeRewardExperienceIdField.setHidden(true);


		employeeIdField = new DataSourceIntegerField("employeeId", "员工id");
		employeeIdField.setLength(11);
		employeeIdField.setRequired(false);
		employeeIdField.setHidden(true);
		employeeIdField.setValueMap(KeyValueManager.getValueMap("employees"));


		rewardNameField = new DataSourceTextField("rewardName", "名称");
		rewardNameField.setLength(64);
		rewardNameField.setRequired(false);
		rewardNameField.setHidden(false);


		rewardDescriptionField = new DataSourceTextField("rewardDescription", "奖惩说明");
		rewardDescriptionField.setLength(512);
		rewardDescriptionField.setRequired(false);
		rewardDescriptionField.setHidden(false);


		rewardDateField = new DataSourceDateField("rewardDate", "奖惩日期");
		rewardDateField.setRequired(false);
		rewardDateField.setHidden(false);


		rewardUnitField = new DataSourceTextField("rewardUnit", "授予单位");
		rewardUnitField.setLength(64);
		rewardUnitField.setRequired(false);
		rewardUnitField.setHidden(false);


		setFields(employeeRewardExperienceIdField, employeeIdField, rewardNameField, rewardDescriptionField, rewardDateField, rewardUnitField);
	}


}

