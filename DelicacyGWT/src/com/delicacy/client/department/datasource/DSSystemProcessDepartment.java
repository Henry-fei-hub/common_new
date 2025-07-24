package com.delicacy.client.department.datasource;

import com.smartgwt.client.data.fields.*;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.FieldType;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.data.DBDataSource;

public class DSSystemProcessDepartment extends DBDataSource
{


	public static DSSystemProcessDepartment instance = null;

	public static DSSystemProcessDepartment getInstance() {
		if(instance == null) {
			instance = new DSSystemProcessDepartment("DSSystemProcessDepartment");
		}
		return instance;
	}

	private final DataSourceIntegerField processDepartmentIdField;
	private final DataSourceIntegerField processIdField;
	private final DataSourceIntegerField departmentIdField;
	private final DataSourceBooleanField isEnableField;

	public DSSystemProcessDepartment(String dataSourceID) {

		super();
		setID(dataSourceID);
		setActionMode(ACTION_MODE_TABLE);
		setActionName("SystemProcessDepartment");


		processDepartmentIdField = new DataSourceIntegerField("processDepartmentId", "编码");
		processDepartmentIdField.setLength(11);
		processDepartmentIdField.setPrimaryKey(true);
		processDepartmentIdField.setRequired(true);
		processDepartmentIdField.setHidden(false);


		processIdField = new DataSourceIntegerField("processId", "流程编码");
		processIdField.setLength(11);
		processIdField.setRequired(false);
		processIdField.setHidden(false);


		departmentIdField = new DataSourceIntegerField("departmentId", "部门编码");
		departmentIdField.setLength(11);
		departmentIdField.setRequired(false);
		departmentIdField.setHidden(false);
		departmentIdField.setValueMap(KeyValueManager.getValueMap("departments"));


		isEnableField = new DataSourceBooleanField("isEnable", "是否可用");
		isEnableField.setRequired(false);
		isEnableField.setHidden(false);


		setFields(processDepartmentIdField, processIdField, departmentIdField, isEnableField);
	}


}

