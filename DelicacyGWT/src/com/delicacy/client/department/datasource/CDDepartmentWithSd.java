package com.delicacy.client.department.datasource;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.*;
import com.delicacy.client.data.KeyValueManager;

public class CDDepartmentWithSd extends DataSource
{


	public static CDDepartmentWithSd instance = null;

	public static CDDepartmentWithSd getInstance() {
		if(instance == null) {
			instance = new CDDepartmentWithSd("CDDepartmentWithSd");
		}
		return instance;
	}

	private final DataSourceTextField managerNameField;
	private final DataSourceIntegerField managerIdField;
	private final DataSourceTextField departmentNameField;

	public CDDepartmentWithSd(String dataSourceID) {

		setID(dataSourceID);
		managerNameField = new DataSourceTextField("managerName", "部门负责人姓名");
		managerNameField.setRequired(false);
		managerNameField.setLength(64);
		managerNameField.setHidden(false);

		managerIdField = new DataSourceIntegerField("managerId", "部门负责编码");
		managerIdField.setRequired(false);
		managerIdField.setLength(11);
		managerIdField.setHidden(false);

		departmentNameField = new DataSourceTextField("departmentName", "部门名称");
		departmentNameField.setRequired(true);
		departmentNameField.setLength(64);
		departmentNameField.setHidden(false);

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


		setFields(managerNameField, managerIdField, departmentNameField, currentPageField, pageLinesField);

		setClientOnly(true);
	}


}

