package com.delicacy.client.app.datasource;

import com.smartgwt.client.data.fields.*;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.FieldType;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.data.DBDataSource;

public class DSOnLoadDepartmentFunction extends DBDataSource
{


	public static DSOnLoadDepartmentFunction instance = null;

	public static DSOnLoadDepartmentFunction getInstance() {
		if(instance == null) {
			instance = new DSOnLoadDepartmentFunction("DSOnLoadDepartmentFunction");
		}
		return instance;
	}

	private final DataSourceIntegerField employeeIdField;
	private final DataSourceTextField employeeNoField;
	private final DataSourceTextField employeeNameField;
	private final DataSourceIntegerField departmentIdField;
	private final DataSourceTextField departmentNameField;

	public DSOnLoadDepartmentFunction(String dataSourceID) {

		super();
		setID(dataSourceID);
		setActionMode(ACTION_MODE_QUERY);
		setActionName("OnLoadDepartmentFunction");


		employeeIdField = new DataSourceIntegerField("employeeId", "职员id");
		employeeIdField.setLength(11);
		employeeIdField.setRequired(false);
		employeeIdField.setHidden(true);


		employeeNoField = new DataSourceTextField("employeeNo", "员工编号");
		employeeNoField.setLength(64);
		employeeNoField.setRequired(true);
		employeeNoField.setHidden(false);


		employeeNameField = new DataSourceTextField("employeeName", "员工姓名");
		employeeNameField.setLength(64);
		employeeNameField.setRequired(true);
		employeeNameField.setHidden(false);


		departmentIdField = new DataSourceIntegerField("departmentId", "部门");
		departmentIdField.setLength(11);
		departmentIdField.setRequired(false);
		departmentIdField.setHidden(true);


		departmentNameField = new DataSourceTextField("departmentName", "权限归属部门");
		departmentNameField.setLength(64);
		departmentNameField.setRequired(true);
		departmentNameField.setHidden(false);


		setFields(employeeIdField, employeeNoField, employeeNameField, departmentIdField, departmentNameField);
	}


}

