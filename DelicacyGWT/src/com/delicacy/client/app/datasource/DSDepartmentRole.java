package com.delicacy.client.app.datasource;

import com.smartgwt.client.data.fields.*;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.data.DBDataSource;

public class DSDepartmentRole extends DBDataSource {

    public static DSDepartmentRole instance = null;

    public static DSDepartmentRole getInstance() {
        if (instance == null) {
            instance = new DSDepartmentRole("DSRoleWithR");
        }
        return instance;
    }

    private final DataSourceIntegerField roleIdField;
    private final DataSourceTextField roleNameField;
    private final DataSourceIntegerField applicationIdField;

    public DSDepartmentRole(String dataSourceID) {

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
        roleNameField.setLength(32);
        roleNameField.setRequired(false);
        roleNameField.setHidden(false);

        applicationIdField = new DataSourceIntegerField("applicationId", "应用系统代码");
        applicationIdField.setLength(11);
        applicationIdField.setRequired(false);
        applicationIdField.setHidden(false);
        applicationIdField.setValueMap(KeyValueManager.getValueMap("domain_values_application_type"));


        setFields(roleIdField,applicationIdField, roleNameField );
    }

}
