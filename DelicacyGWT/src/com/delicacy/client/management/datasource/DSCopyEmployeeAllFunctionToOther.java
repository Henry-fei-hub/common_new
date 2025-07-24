package com.delicacy.client.management.datasource;

import com.smartgwt.client.data.fields.*;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.FieldType;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.data.DBDataSource;

public class DSCopyEmployeeAllFunctionToOther extends DBDataSource
{


	public static DSCopyEmployeeAllFunctionToOther instance = null;

	public static DSCopyEmployeeAllFunctionToOther getInstance() {
		if(instance == null) {
			instance = new DSCopyEmployeeAllFunctionToOther("DSCopyEmployeeAllFunctionToOther");
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
	private final DataSourceTextField functionStaticNameField;
	private final DataSourceBooleanField empFunField;
	private final DataSourceBooleanField roleFunField;
	private final DataSourceBooleanField departmentRoleFunField;

	public DSCopyEmployeeAllFunctionToOther(String dataSourceID) {

		super();
		setID(dataSourceID);
		setActionMode(ACTION_MODE_QUERY);
		setActionName("CopyEmployeeAllFunctionToOther");


		functionIdField = new DataSourceIntegerField("functionId", "功能编码");
		functionIdField.setLength(11);
		functionIdField.setPrimaryKey(true);
		functionIdField.setRequired(true);
		functionIdField.setHidden(true);


		functionCodeField = new DataSourceTextField("functionCode", "功能编号");
		functionCodeField.setLength(64);
		functionCodeField.setRequired(false);
		functionCodeField.setHidden(false);


		parentIdField = new DataSourceIntegerField("parentId", "上级功能");
		parentIdField.setLength(11);
		parentIdField.setRequired(false);
		parentIdField.setHidden(true);


		functionNameField = new DataSourceTextField("functionName", "功能名称");
		functionNameField.setLength(64);
		functionNameField.setRequired(false);
		functionNameField.setHidden(false);


		applicationIdField = new DataSourceIntegerField("applicationId", "应用系统代码");
		applicationIdField.setLength(11);
		applicationIdField.setRequired(false);
		applicationIdField.setHidden(false);
		applicationIdField.setValueMap(KeyValueManager.getValueMap("domain_values_application_type"));


		functionTypeField = new DataSourceIntegerField("functionType", "功能类型");
		functionTypeField.setLength(11);
		functionTypeField.setRequired(false);
		functionTypeField.setHidden(false);


		privilegeTypeField = new DataSourceIntegerField("privilegeType", "数据权限类型");
		privilegeTypeField.setLength(11);
		privilegeTypeField.setRequired(false);
		privilegeTypeField.setHidden(true);


		enabledField = new DataSourceBooleanField("enabled", "是否有效");
		enabledField.setRequired(false);
		enabledField.setHidden(true);


		functionStaticNameField = new DataSourceTextField("functionStaticName", "function_static_name");
		functionStaticNameField.setLength(255);
		functionStaticNameField.setRequired(false);
		functionStaticNameField.setHidden(true);


		empFunField = new DataSourceBooleanField("empFun", "来自于人员");
		empFunField.setRequired(true);
		empFunField.setHidden(false);


		roleFunField = new DataSourceBooleanField("roleFun", "来自于角色");
		roleFunField.setRequired(true);
		roleFunField.setHidden(false);


		departmentRoleFunField = new DataSourceBooleanField("departmentRoleFun", "来自于部门角色");
		departmentRoleFunField.setRequired(true);
		departmentRoleFunField.setHidden(false);


		setFields(functionIdField, functionCodeField, parentIdField, functionNameField, applicationIdField, functionTypeField, privilegeTypeField, enabledField, functionStaticNameField, empFunField, roleFunField, departmentRoleFunField);
	}


}

