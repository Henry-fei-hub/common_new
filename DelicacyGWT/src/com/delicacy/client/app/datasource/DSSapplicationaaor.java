package com.delicacy.client.app.datasource;

import com.smartgwt.client.data.fields.*;
import com.delicacy.client.data.DBDataSource;

public class DSSapplicationaaor extends DBDataSource {

	public static DSSapplicationaaor instance = null;

	public static DSSapplicationaaor getInstance() {
		if (instance == null) {
			instance = new DSSapplicationaaor("DSSapplicationaaor");
		}
		return instance;
	}

	private final DataSourceIntegerField applicationIdField;
	private final DataSourceTextField applicationNameField;

	public DSSapplicationaaor(String dataSourceID) {

		super();
		setID(dataSourceID);
		setActionMode(ACTION_MODE_QUERY);
		setActionName("Sapplicationaaor");

		applicationIdField = new DataSourceIntegerField("applicationId", "应用系统代码");
		applicationIdField.setLength(11);
		applicationIdField.setPrimaryKey(true);
		applicationIdField.setRequired(false);
		applicationIdField.setHidden(false);

		applicationNameField = new DataSourceTextField("applicationName", "应用系统名称");
		applicationNameField.setLength(64);
		applicationNameField.setRequired(false);
		applicationNameField.setHidden(false);

		setFields(applicationIdField, applicationNameField);
	}

}
