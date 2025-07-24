package com.delicacy.client.app.datasource;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.*;
import com.delicacy.client.data.KeyValueManager;

public class CDSystemProcessList extends DataSource
{


	public static CDSystemProcessList instance = null;

	public static CDSystemProcessList getInstance() {
		if(instance == null) {
			instance = new CDSystemProcessList("CDSystemProcessList");
		}
		return instance;
	}

	private final DataSourceTextField processNameField;
	private final DataSourceIntegerField processTypeIdField;

	public CDSystemProcessList(String dataSourceID) {

		setID(dataSourceID);
		processNameField = new DataSourceTextField("processName", "流程名称");
		processNameField.setRequired(false);
		processNameField.setLength(64);
		processNameField.setHidden(false);

		processTypeIdField = new DataSourceIntegerField("processTypeId", "流程类型");
		processTypeIdField.setRequired(false);
		processTypeIdField.setLength(11);
		processTypeIdField.setHidden(false);
		processTypeIdField.setValueMap(KeyValueManager.getValueMap("system_process_types"));

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


		setFields(processNameField, processTypeIdField, currentPageField, pageLinesField);

		setClientOnly(true);
	}


}

