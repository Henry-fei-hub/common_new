package com.delicacy.client.app.datasource;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.*;

public class CDSapplicationaaor extends DataSource {

	public static CDSapplicationaaor instance = null;

	public static CDSapplicationaaor getInstance() {
		if (instance == null) {
			instance = new CDSapplicationaaor("CDSapplicationaaor");
		}
		return instance;
	}

	private final DataSourceIntegerField applicationIdField;
	private final DataSourceTextField applicationNameField;

	public CDSapplicationaaor(String dataSourceID) {

		setID(dataSourceID);
		applicationIdField = new DataSourceIntegerField("applicationId", "系统代码");
		applicationIdField.setRequired(true);
		applicationIdField.setLength(11);
		applicationIdField.setPrimaryKey(true);
		applicationIdField.setHidden(true);

		applicationNameField = new DataSourceTextField("applicationName", "系统名称");
		applicationNameField.setRequired(false);
		applicationNameField.setLength(256);
		applicationNameField.setHidden(false);

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

		setFields(applicationIdField, applicationNameField, currentPageField, pageLinesField);

		setClientOnly(true);
	}

}
