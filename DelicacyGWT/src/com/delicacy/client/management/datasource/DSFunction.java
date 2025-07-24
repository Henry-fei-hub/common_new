package com.delicacy.client.management.datasource;

import com.smartgwt.client.data.fields.*;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.FieldType;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.data.DBDataSource;

public class DSFunction extends DBDataSource
{


	public static DSFunction instance = null;

	public static DSFunction getInstance() {
		if(instance == null) {
			instance = new DSFunction("DSFunction");
		}
		return instance;
	}

	private final DataSourceIntegerField functionIdField;
	private final DataSourceTextField functionCodeField;
	private final DataSourceIntegerField parentIdField;
	private final DataSourceTextField functionNameField;
	private final DataSourceIntegerField applicationIdField;
	private final DataSourceIntegerField functionTypeField;
	private final DataSourceIntegerField privilegeTypeField;
	private final DataSourceBooleanField enabledField;

	public DSFunction(String dataSourceID) {

		super();
		setID(dataSourceID);
		setActionMode(ACTION_MODE_TABLE);
		setActionName("Function");


		functionIdField = new DataSourceIntegerField("functionId", "功能编码");
		functionIdField.setLength(11);
		functionIdField.setPrimaryKey(true);
		functionIdField.setRequired(true);
		functionIdField.setHidden(false);


		functionCodeField = new DataSourceTextField("functionCode", "功能编号");
		functionCodeField.setLength(64);
		functionCodeField.setRequired(false);
		functionCodeField.setHidden(true);
//		functionCodeField.setValueMap(KeyValueManager.getValueMap("applications"));
		KeyValueManager.loadValueMap("applications",functionCodeField);


		parentIdField = new DataSourceIntegerField("parentId", "上级功能");
		parentIdField.setLength(11);
		parentIdField.setRequired(false);
		parentIdField.setForeignKey(dataSourceID+".functionId");
		parentIdField.setRootValue("0");
		parentIdField.setHidden(false);


		functionNameField = new DataSourceTextField("functionName", "功能名称");
		functionNameField.setLength(64);
		functionNameField.setRequired(false);
		functionNameField.setHidden(false);


		applicationIdField = new DataSourceIntegerField("applicationId", "应用系统代码");
		applicationIdField.setLength(11);
		applicationIdField.setRequired(false);
		applicationIdField.setHidden(true);


		functionTypeField = new DataSourceIntegerField("functionType", "功能类型");
		functionTypeField.setLength(11);
		functionTypeField.setRequired(false);
		functionTypeField.setHidden(true);
		functionTypeField.setValueMap(KeyValueManager.getValueMap("domain_values_function_type"));


		privilegeTypeField = new DataSourceIntegerField("privilegeType", "数据权限类型");
		privilegeTypeField.setLength(11);
		privilegeTypeField.setRequired(false);
		privilegeTypeField.setHidden(true);


		enabledField = new DataSourceBooleanField("enabled", "是否有效");
		enabledField.setRequired(false);
		enabledField.setHidden(true);


		setFields(functionIdField, functionCodeField, parentIdField, functionNameField, applicationIdField, functionTypeField, privilegeTypeField, enabledField);
	}


}

