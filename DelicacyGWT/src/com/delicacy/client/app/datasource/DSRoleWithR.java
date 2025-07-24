package com.delicacy.client.app.datasource;

import com.delicacy.client.data.DBDataSource;
import com.delicacy.client.data.KeyValueManager;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.fields.DataSourceBooleanField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.FieldType;

public class DSRoleWithR extends DBDataSource {

    public static DSRoleWithR instance = null;

    public static DSRoleWithR getInstance() {
        if (instance == null) {
            instance = new DSRoleWithR("DSRoleWithR");
        }
        return instance;
    }

    private final DataSourceIntegerField roleIdField;
    private final DataSourceTextField roleNameField;
    private final DataSourceIntegerField applicationIdField;
    private final DataSourceIntegerField roleTypeField;
    private final DataSourceBooleanField enabledField;
    private final DataSourceBooleanField hasApprovalRightField;
    private final DataSourceField detailRoleFunction;

    public DSRoleWithR(String dataSourceID) {

        super();
        setID(dataSourceID);
        setActionMode(ACTION_MODE_TABLE);
        setActionName("RoleWithR");

        roleIdField = new DataSourceIntegerField("roleId", "角色编码");
        roleIdField.setLength(11);
        roleIdField.setPrimaryKey(true);
        roleIdField.setRequired(true);
        roleIdField.setHidden(true);

        roleNameField = new DataSourceTextField("roleName", "角色名称");
        roleNameField.setLength(64);
        roleNameField.setRequired(false);
        roleNameField.setHidden(false);

        applicationIdField = new DataSourceIntegerField("applicationId", "应用系统代码");
        applicationIdField.setLength(11);
        applicationIdField.setRequired(false);
        applicationIdField.setHidden(false);
        applicationIdField.setValueMap(KeyValueManager.getValueMap("domain_values_application_type"));

        roleTypeField = new DataSourceIntegerField("roleType", "角色类型");
        roleTypeField.setLength(11);
        roleTypeField.setRequired(false);
        roleTypeField.setHidden(false);
        roleTypeField.setValueMap(KeyValueManager.getValueMap("system_dictionary_10"));

        enabledField = new DataSourceBooleanField("enabled", "是否有效");
        enabledField.setRequired(false);
        enabledField.setHidden(true);

        hasApprovalRightField = new DataSourceBooleanField("hasApprovalRight", "能审批");
        hasApprovalRightField.setRequired(false);
        hasApprovalRightField.setHidden(true);

        detailRoleFunction = new DataSourceField("detailRoleFunction", FieldType.ANY);
        detailRoleFunction.setChildrenProperty(true);
        detailRoleFunction.setChildTagName("RoleFunction");
        detailRoleFunction.setRequired(false);
        detailRoleFunction.setHidden(true);

        setFields(roleIdField, roleNameField, applicationIdField, roleTypeField, enabledField, hasApprovalRightField, detailRoleFunction);
    }

}
