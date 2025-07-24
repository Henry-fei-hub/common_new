package com.delicacy.client.app.datasource;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.*;
import com.delicacy.client.data.KeyValueManager;

public class CDSemployeeRoleInfo extends DataSource
{


	public static CDSemployeeRoleInfo instance = null;

	public static CDSemployeeRoleInfo getInstance() {
		if(instance == null) {
			instance = new CDSemployeeRoleInfo("CDSemployeeRoleInfo");
		}
		return instance;
	}

	private final DataSourceIntegerField applicationIdField;
	private final DataSourceTextField roleNameField;

	public CDSemployeeRoleInfo(String dataSourceID) {

		setID(dataSourceID);
		applicationIdField = new DataSourceIntegerField("applicationId", "应用系统代码");
		applicationIdField.setRequired(false);
		applicationIdField.setLength(11);
		applicationIdField.setHidden(false);
//		applicationIdField.setValueMap(KeyValueManager.getValueMap("functions"));
		KeyValueManager.loadValueMap("functions",applicationIdField);
		roleNameField = new DataSourceTextField("roleName", "角色名称");
		roleNameField.setRequired(false);
		roleNameField.setLength(64);
		roleNameField.setHidden(false);

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


		setFields(applicationIdField, roleNameField, currentPageField, pageLinesField);

		setClientOnly(true);
	}


}

