package com.delicacy.client.app.datasource;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.*;
import com.delicacy.client.data.KeyValueManager;

public class CDSsystemprocesspor extends DataSource
{


	public static CDSsystemprocesspor instance = null;

	public static CDSsystemprocesspor getInstance() {
		if(instance == null) {
			instance = new CDSsystemprocesspor("CDSsystemprocesspor");
		}
		return instance;
	}

	private final DataSourceIntegerField processTypeIdField;

	public CDSsystemprocesspor(String dataSourceID) {

		setID(dataSourceID);
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


		setFields(processTypeIdField, currentPageField, pageLinesField);

		setClientOnly(true);
	}


}

