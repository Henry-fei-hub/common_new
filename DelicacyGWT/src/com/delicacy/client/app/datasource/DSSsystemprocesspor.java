package com.delicacy.client.app.datasource;

import com.smartgwt.client.data.fields.*;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.FieldType;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.data.DBDataSource;

public class DSSsystemprocesspor extends DBDataSource
{


	public static DSSsystemprocesspor instance = null;

	public static DSSsystemprocesspor getInstance() {
		if(instance == null) {
			instance = new DSSsystemprocesspor("DSSsystemprocesspor");
		}
		return instance;
	}

	private final DataSourceIntegerField processIdField;
	private final DataSourceIntegerField processTypeIdField;
	private final DataSourceIntegerField departmentIdField;
	private final DataSourceIntegerField includeDepartmentIdField;
	private final DataSourceIntegerField createEmployeeIdField;
	private final DataSourceDateTimeField createTimeField;
	private final DataSourceTextField processNameField;
	private final DataSourceBooleanField countersignField;
	private final DataSourceIntegerField holdDepartmentIdField;
	private final DataSourceIntegerField holdDutyIdField;
	private final DataSourceTextField descriptionField;
	private final DataSourceBooleanField enableField;
	private final DataSourceField detailSystemProcessActivity;

	public DSSsystemprocesspor(String dataSourceID) {

		super();
		setID(dataSourceID);
		setActionMode(ACTION_MODE_QUERY);
		setActionName("Ssystemprocesspor");


		processIdField = new DataSourceIntegerField("processId", "主键编码");
		processIdField.setLength(11);
		processIdField.setPrimaryKey(true);
		processIdField.setRequired(true);
		processIdField.setHidden(true);


		processTypeIdField = new DataSourceIntegerField("processTypeId", "流程类型编码");
		processTypeIdField.setLength(11);
		processTypeIdField.setRequired(false);
		processTypeIdField.setHidden(false);
		processTypeIdField.setValueMap(KeyValueManager.getValueMap("system_process_types"));


		departmentIdField = new DataSourceIntegerField("departmentId", "归属部门");
		departmentIdField.setLength(11);
		departmentIdField.setRequired(false);
		departmentIdField.setHidden(false);
		departmentIdField.setValueMap(KeyValueManager.getValueMap("departments"));


		includeDepartmentIdField = new DataSourceIntegerField("includeDepartmentId", "归属部门(包括下级部门)");
		includeDepartmentIdField.setLength(11);
		includeDepartmentIdField.setRequired(false);
		includeDepartmentIdField.setHidden(false);
		includeDepartmentIdField.setValueMap(KeyValueManager.getValueMap("departments"));


		createEmployeeIdField = new DataSourceIntegerField("createEmployeeId", "创建人");
		createEmployeeIdField.setLength(11);
		createEmployeeIdField.setRequired(false);
		createEmployeeIdField.setHidden(false);
		createEmployeeIdField.setValueMap(KeyValueManager.getValueMap("employees"));


		createTimeField = new DataSourceDateTimeField("createTime", "创建时间");
		createTimeField.setRequired(false);
		createTimeField.setHidden(false);


		processNameField = new DataSourceTextField("processName", "流程名称");
		processNameField.setLength(64);
		processNameField.setRequired(false);
		processNameField.setHidden(false);


		countersignField = new DataSourceBooleanField("countersign", "是否会签");
		countersignField.setRequired(false);
		countersignField.setHidden(false);


		holdDepartmentIdField = new DataSourceIntegerField("holdDepartmentId", "归档部门");
		holdDepartmentIdField.setLength(11);
		holdDepartmentIdField.setRequired(false);
		holdDepartmentIdField.setHidden(false);
		holdDepartmentIdField.setValueMap(KeyValueManager.getValueMap("departments"));


		holdDutyIdField = new DataSourceIntegerField("holdDutyId", "归档职务");
		holdDutyIdField.setLength(11);
		holdDutyIdField.setRequired(false);
		holdDutyIdField.setHidden(false);
//		holdDutyIdField.setValueMap(KeyValueManager.getValueMap("roles"));
		KeyValueManager.loadValueMap("roles",holdDutyIdField);


		descriptionField = new DataSourceTextField("description", "描述");
		descriptionField.setLength(512);
		descriptionField.setRequired(false);
		descriptionField.setHidden(false);


		enableField = new DataSourceBooleanField("enable", "是否可用");
		enableField.setRequired(false);
		enableField.setHidden(false);

		detailSystemProcessActivity = new DataSourceField("detailSystemProcessActivity", FieldType.ANY);
		detailSystemProcessActivity.setChildrenProperty(true);
		detailSystemProcessActivity.setChildTagName("SystemProcessActivity");
		detailSystemProcessActivity.setRequired(false);
		detailSystemProcessActivity.setHidden(true);

		setFields(processIdField, processTypeIdField, departmentIdField, includeDepartmentIdField, createEmployeeIdField, createTimeField, processNameField, countersignField, holdDepartmentIdField, holdDutyIdField, descriptionField, enableField, detailSystemProcessActivity);
	}


}

