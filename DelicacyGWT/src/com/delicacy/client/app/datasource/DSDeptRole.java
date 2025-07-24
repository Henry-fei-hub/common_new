package com.delicacy.client.app.datasource;

import com.smartgwt.client.data.fields.*;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.FieldType;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.data.DBDataSource;

public class DSDeptRole extends DBDataSource {

    public static DSDeptRole instance = null;

    public static DSDeptRole getInstance() {
        if (instance == null) {
            instance = new DSDeptRole("DSDeptRole");
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
//    private final DataSourceBooleanField enabledField;
    private final DataSourceBooleanField isDefaultField;

    DSDeptRole(String dataSourceID) {

        super();
        setID(dataSourceID);
        setActionMode(ACTION_MODE_CUSTOM);
        setActionName("CustomDeptRoleProcessor");

        functionIdField = new DataSourceIntegerField("functionId", "功能编码");
        functionIdField.setLength(11);
        functionIdField.setPrimaryKey(true);
        functionIdField.setRequired(true);
        functionIdField.setHidden(false);
//        functionIdField.setValueMap(KeyValueManager.getValueMap("functions"));
        KeyValueManager.loadValueMap("functions",functionIdField);
        functionCodeField = new DataSourceTextField("functionCode", "功能编号");
        functionCodeField.setLength(64);
        functionCodeField.setRequired(false);
        functionCodeField.setHidden(false);

        parentIdField = new DataSourceIntegerField("parentId", "上级功能");
        parentIdField.setLength(11);
        parentIdField.setRequired(false);
        parentIdField.setForeignKey(dataSourceID + ".functionId");
        parentIdField.setRootValue("0");
        parentIdField.setHidden(false);

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
        functionTypeField.setHidden(true);

        privilegeTypeField = new DataSourceIntegerField("privilegeType", "数据权限类型");
        privilegeTypeField.setLength(11);
        privilegeTypeField.setRequired(false);
        privilegeTypeField.setHidden(true);

//        enabledField = new DataSourceBooleanField("enabled", "是否默认");
//        enabledField.setLength(30);
//        enabledField.setRequired(false);
//        enabledField.setHidden(true);
        
        isDefaultField=new DataSourceBooleanField("isDefault", "是否默认");
        isDefaultField.setLength(30);
        isDefaultField.setRequired(false);
        isDefaultField.setHidden(false);

        setFields(functionIdField, functionCodeField, parentIdField, functionNameField, applicationIdField, functionTypeField, privilegeTypeField, isDefaultField);
    }

}
