package com.delicacy.client.app.datasource;

import com.smartgwt.client.data.fields.*;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.FieldType;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.data.DBDataSource;

public class DSSystemProcessList extends DBDataSource
{


	public static DSSystemProcessList instance = null;

	public static DSSystemProcessList getInstance() {
		if(instance == null) {
			instance = new DSSystemProcessList("DSSystemProcessList");
		}
		return instance;
	}

	private final DataSourceIntegerField processIdField;
	private final DataSourceTextField processNameField;
	private final DataSourceTextField descriptionField;

	public DSSystemProcessList(String dataSourceID) {

		super();
		setID(dataSourceID);
		setActionMode(ACTION_MODE_QUERY);
		setActionName("SystemProcessList");


		processIdField = new DataSourceIntegerField("processId", "主键编码");
		processIdField.setLength(11);
		processIdField.setPrimaryKey(true);
		processIdField.setRequired(true);
		processIdField.setHidden(true);


		processNameField = new DataSourceTextField("processName", "流程名称");
		processNameField.setLength(64);
		processNameField.setRequired(false);
		processNameField.setHidden(false);


		descriptionField = new DataSourceTextField("description", "描述");
		descriptionField.setLength(512);
		descriptionField.setRequired(false);
		descriptionField.setHidden(false);


		setFields(processIdField, processNameField, descriptionField);
	}


}

