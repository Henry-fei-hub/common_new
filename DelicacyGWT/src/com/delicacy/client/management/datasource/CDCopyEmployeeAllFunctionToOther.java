package com.delicacy.client.management.datasource;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.*;
import com.delicacy.client.data.KeyValueManager;

public class CDCopyEmployeeAllFunctionToOther extends DataSource
{


	public static CDCopyEmployeeAllFunctionToOther instance = null;

	public static CDCopyEmployeeAllFunctionToOther getInstance() {
		if(instance == null) {
			instance = new CDCopyEmployeeAllFunctionToOther("CDCopyEmployeeAllFunctionToOther");
		}
		return instance;
	}

	private final DataSourceIntegerField employeeIdField;

	public CDCopyEmployeeAllFunctionToOther(String dataSourceID) {

		setID(dataSourceID);
		employeeIdField = new DataSourceIntegerField("employeeId", "员工编码");
		employeeIdField.setRequired(false);
		employeeIdField.setLength(11);
		employeeIdField.setHidden(false);

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


		setFields(employeeIdField, currentPageField, pageLinesField);

		setClientOnly(true);
	}


}

