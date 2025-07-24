package com.delicacy.client.app.datasource;

import com.smartgwt.client.data.fields.*;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.FieldType;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.data.DBDataSource;

public class DSEmployeeFamilyInformation extends DBDataSource
{


	public static DSEmployeeFamilyInformation instance = null;

	public static DSEmployeeFamilyInformation getInstance() {
		if(instance == null) {
			instance = new DSEmployeeFamilyInformation("DSEmployeeFamilyInformation");
		}
		return instance;
	}

	private final DataSourceIntegerField employeeFamilyInformationIdField;
	private final DataSourceIntegerField employeeIdField;
	private final DataSourceTextField familyMemberNameField;
	private final DataSourceTextField relationField;
	private final DataSourceTextField familyWorkPlaceField;
	private final DataSourceTextField telephoneField;

	public DSEmployeeFamilyInformation(String dataSourceID) {

		super();
		setID(dataSourceID);
		setActionMode(ACTION_MODE_TABLE);
		setActionName("EmployeeFamilyInformation");


		employeeFamilyInformationIdField = new DataSourceIntegerField("employeeFamilyInformationId", "主键编码");
		employeeFamilyInformationIdField.setLength(11);
		employeeFamilyInformationIdField.setPrimaryKey(true);
		employeeFamilyInformationIdField.setRequired(true);
		employeeFamilyInformationIdField.setHidden(true);


		employeeIdField = new DataSourceIntegerField("employeeId", "员工id");
		employeeIdField.setLength(11);
		employeeIdField.setRequired(false);
		employeeIdField.setHidden(true);
		employeeIdField.setValueMap(KeyValueManager.getValueMap("employees"));


		familyMemberNameField = new DataSourceTextField("familyMemberName", "姓名");
		familyMemberNameField.setLength(64);
		familyMemberNameField.setRequired(false);
		familyMemberNameField.setHidden(false);


		relationField = new DataSourceTextField("relation", "与本人关系");
		relationField.setLength(64);
		relationField.setRequired(false);
		relationField.setHidden(false);


		familyWorkPlaceField = new DataSourceTextField("familyWorkPlace", "工作单位");
		familyWorkPlaceField.setLength(64);
		familyWorkPlaceField.setRequired(false);
		familyWorkPlaceField.setHidden(false);


		telephoneField = new DataSourceTextField("telephone", "联系电话");
		telephoneField.setLength(64);
		telephoneField.setRequired(false);
		telephoneField.setHidden(false);


		setFields(employeeFamilyInformationIdField, employeeIdField, familyMemberNameField, relationField, familyWorkPlaceField, telephoneField);
	}


}

