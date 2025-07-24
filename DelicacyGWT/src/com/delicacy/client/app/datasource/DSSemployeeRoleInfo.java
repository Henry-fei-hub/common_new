package com.delicacy.client.app.datasource;

import com.smartgwt.client.data.fields.*;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.FieldType;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.data.DBDataSource;

public class DSSemployeeRoleInfo extends DBDataSource
{


	public static DSSemployeeRoleInfo instance = null;

	public static DSSemployeeRoleInfo getInstance() {
		if(instance == null) {
			instance = new DSSemployeeRoleInfo("DSSemployeeRoleInfo");
		}
		return instance;
	}

	private final DataSourceIntegerField roleIdField;
	private final DataSourceTextField roleNameField;
	private final DataSourceIntegerField applicationIdField;
	private final DataSourceIntegerField roleTypeField;

	public DSSemployeeRoleInfo(String dataSourceID) {

		super();
		setID(dataSourceID);
		setActionMode(ACTION_MODE_QUERY);
		setActionName("SemployeeRoleInfo");


		roleIdField = new DataSourceIntegerField("roleId", "角色编码");
		roleIdField.setLength(11);
		roleIdField.setPrimaryKey(true);
		roleIdField.setRequired(true);
		roleIdField.setHidden(false);
//		roleIdField.setValueMap(KeyValueManager.getValueMap("roles"));
		KeyValueManager.loadValueMap("roles",roleIdField);

		roleNameField = new DataSourceTextField("roleName", "角色名称");
		roleNameField.setLength(64);
		roleNameField.setRequired(false);
		roleNameField.setHidden(false);


		applicationIdField = new DataSourceIntegerField("applicationId", "应用系统代码");
		applicationIdField.setLength(11);
		applicationIdField.setRequired(false);
		applicationIdField.setHidden(false);
//		applicationIdField.setValueMap(KeyValueManager.getValueMap("functions"));
		KeyValueManager.loadValueMap("functions",applicationIdField);

		roleTypeField = new DataSourceIntegerField("roleType", "角色类型");
		roleTypeField.setLength(11);
		roleTypeField.setRequired(false);
		roleTypeField.setHidden(false);


		setFields(roleIdField, roleNameField, applicationIdField, roleTypeField);
	}


}

