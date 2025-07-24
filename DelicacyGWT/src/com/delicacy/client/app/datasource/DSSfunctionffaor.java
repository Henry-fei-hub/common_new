package com.delicacy.client.app.datasource;

import com.smartgwt.client.data.fields.*;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.data.DBDataSource;

public class DSSfunctionffaor extends DBDataSource {

	public static DSSfunctionffaor instance = null;

	public static DSSfunctionffaor getInstance() {
		if (instance == null) {
			instance = new DSSfunctionffaor("DSSfunctionffaor");
		}
		return instance;
	}

	private final DataSourceIntegerField functionIdField;
	private final DataSourceTextField functionCodeField;
	private final DataSourceIntegerField parentIdField;
	private final DataSourceTextField functionNameField;
	private final DataSourceIntegerField applicationIdField;
	private final DataSourceIntegerField functionTypeField;
	private final DataSourceBooleanField enabledField;

	public DSSfunctionffaor(String dataSourceID) {

		super();
		setID(dataSourceID);
		setActionMode(ACTION_MODE_TABLE);
		setActionName("Function");

		functionIdField = new DataSourceIntegerField("functionId", "功能编码");
		functionIdField.setLength(11);
		functionIdField.setPrimaryKey(true);
		functionIdField.setRequired(false);
		functionIdField.setHidden(false);

		functionCodeField = new DataSourceTextField("functionCode", "功能编号");
		functionCodeField.setLength(64);
		functionCodeField.setRequired(false);
		functionCodeField.setHidden(false);

		parentIdField = new DataSourceIntegerField("parentId", "上级功能");
		parentIdField.setLength(11);
		parentIdField.setRequired(false);
		parentIdField.setHidden(false);
		parentIdField.setForeignKey(dataSourceID+".functionId");
		parentIdField.setRootValue(0);

		functionNameField = new DataSourceTextField("functionName", "功能名称");
		functionNameField.setLength(64);
		functionNameField.setRequired(false);
		functionNameField.setHidden(false);

		applicationIdField = new DataSourceIntegerField("applicationId", "应用系统");
		applicationIdField.setLength(11);
		applicationIdField.setRequired(false);
		applicationIdField.setHidden(false);
		applicationIdField.setValueMap(KeyValueManager.getValueMap("domain_values_application_type"));

		functionTypeField = new DataSourceIntegerField("functionType", "功能类型");
		functionTypeField.setLength(11);
		functionTypeField.setRequired(false);
		functionTypeField.setHidden(false);
		functionTypeField.setValueMap(KeyValueManager.getValueMap("domain_values_function_type"));

		enabledField = new DataSourceBooleanField("enabled", "是否有效");
		enabledField.setRequired(false);
		enabledField.setHidden(false);

		setFields(functionIdField, functionCodeField, parentIdField, functionNameField, applicationIdField, functionTypeField, enabledField);
	}

}
