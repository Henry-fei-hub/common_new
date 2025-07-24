package com.delicacy.client.app.datasource;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;

public class CDRoleList extends DataSource
{


	public static CDRoleList instance = null;

	public static CDRoleList getInstance() {
		if(instance == null) {
			instance = new CDRoleList("CDRoleList");
		}
		return instance;
	}

	private final DataSourceTextField roleNameField;

	public CDRoleList(String dataSourceID) {

		setID(dataSourceID);
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


		setFields(roleNameField, currentPageField, pageLinesField);

		setClientOnly(true);
	}


}

