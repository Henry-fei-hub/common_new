package com.delicacy.client.app.datasource;

import com.smartgwt.client.data.fields.*;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.FieldType;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.data.DBDataSource;

public class DSDefaultRoleWithR extends DBDataSource {

    public static DSDefaultRoleWithR instance = null;

    public static DSDefaultRoleWithR getInstance() {
        if (instance == null) {
            instance = new DSDefaultRoleWithR("EmployeeRole");
        }
        return instance;
    }

    private final DataSourceIntegerField roleIdField;
    private final DataSourceIntegerField departmentField;

    public DSDefaultRoleWithR(String dataSourceID) {

        super();
        setID(dataSourceID);
        setActionMode(ACTION_MODE_TABLE);
        setActionName("EmployeeRole");

        roleIdField = new DataSourceIntegerField("roleId", "角色名称");
        roleIdField.setLength(11);
        roleIdField.setRequired(true);
        roleIdField.setHidden(false);
//        roleIdField.setValueMap(KeyValueManager.getValueMap("roles"));
        KeyValueManager.loadValueMap("roles",roleIdField);

        departmentField = new DataSourceIntegerField("departmentId", "部门名称");
        departmentField.setLength(11);
        departmentField.setRequired(false);
        departmentField.setHidden(false);
        departmentField.setValueMap(KeyValueManager.getValueMap("departments"));


        setFields(departmentField, roleIdField);
    }

}
