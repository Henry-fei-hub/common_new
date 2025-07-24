package com.delicacy.client.app.datasource;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.*;
import com.delicacy.client.data.KeyValueManager;

public class CDSfunctionffaor extends DataSource
{


	public static CDSfunctionffaor instance = null;

	public static CDSfunctionffaor getInstance() {
		if(instance == null) {
			instance = new CDSfunctionffaor("CDSfunctionffaor");
		}
		return instance;
	}

	private final DataSourceTextField functionCodeField;
	private final DataSourceTextField functionNameField;
	private final DataSourceIntegerField applicationIdField;

	public CDSfunctionffaor(String dataSourceID) {

		setID(dataSourceID);
		functionCodeField = new DataSourceTextField("functionCode", "功能编号");
		functionCodeField.setRequired(false);
		functionCodeField.setLength(64);
		functionCodeField.setHidden(false);

		functionNameField = new DataSourceTextField("functionName", "功能名称");
		functionNameField.setRequired(false);
		functionNameField.setLength(64);
		functionNameField.setHidden(false);

		applicationIdField = new DataSourceIntegerField("applicationId", "应用系统");
		applicationIdField.setRequired(false);
		applicationIdField.setLength(11);
		applicationIdField.setHidden(false);
//		applicationIdField.setValueMap(KeyValueManager.getValueMap("applications"));
		KeyValueManager.loadValueMap("applications",functionCodeField);

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


		setFields(functionCodeField, functionNameField, applicationIdField, currentPageField, pageLinesField);

		setClientOnly(true);
	}


}

