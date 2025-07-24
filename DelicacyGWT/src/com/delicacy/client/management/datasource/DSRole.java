package com.delicacy.client.management.datasource;

import com.smartgwt.client.data.fields.*;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.FieldType;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.data.DBDataSource;

public class DSRole extends DBDataSource
{


	public static DSRole instance = null;

	public static DSRole getInstance() {
		if(instance == null) {
			instance = new DSRole("DSRole");
		}
		return instance;
	}

	private final DataSourceIntegerField roleIdField;
	private final DataSourceTextField roleNameField;
	private final DataSourceIntegerField applicationIdField;
	private final DataSourceIntegerField roleTypeField;
	private final DataSourceBooleanField enabledField;
	private final DataSourceBooleanField hasApprovalRightField;

	public DSRole(String dataSourceID) {

		super();
		setID(dataSourceID);
		setActionMode(ACTION_MODE_TABLE);
		setActionName("Role");


		roleIdField = new DataSourceIntegerField("roleId", "角色编码");
		roleIdField.setLength(11);
		roleIdField.setPrimaryKey(true);
		roleIdField.setRequired(true);
		roleIdField.setHidden(true);


		roleNameField = new DataSourceTextField("roleName", "角色名称");
		roleNameField.setLength(64);
		roleNameField.setRequired(false);
		roleNameField.setHidden(false);


		applicationIdField = new DataSourceIntegerField("applicationId", "应用系统代码");
		applicationIdField.setLength(11);
		applicationIdField.setRequired(false);
		applicationIdField.setHidden(true);


		roleTypeField = new DataSourceIntegerField("roleType", "角色类型");
		roleTypeField.setLength(11);
		roleTypeField.setRequired(false);
		roleTypeField.setHidden(true);


		enabledField = new DataSourceBooleanField("enabled", "是否有效");
		enabledField.setRequired(false);
		enabledField.setHidden(true);


		hasApprovalRightField = new DataSourceBooleanField("hasApprovalRight", "能审批");
		hasApprovalRightField.setRequired(false);
		hasApprovalRightField.setHidden(true);


		setFields(roleIdField, roleNameField, applicationIdField, roleTypeField, enabledField, hasApprovalRightField);
	}


}

