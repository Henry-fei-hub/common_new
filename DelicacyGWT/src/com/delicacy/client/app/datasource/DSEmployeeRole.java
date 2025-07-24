package com.delicacy.client.app.datasource;

import com.smartgwt.client.data.fields.*;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.data.DBDataSource;

public class DSEmployeeRole extends DBDataSource {

    public static DSEmployeeRole instance = null;

    public static DSEmployeeRole getInstance() {
        if (instance == null) {
            instance = new DSEmployeeRole("DSEmployeeRole");
        }
        return instance;
    }

    private final DataSourceIntegerField employeeRoleIdField;
    private final DataSourceIntegerField employeeIdField;
    private final DataSourceIntegerField roleIdField;
    private final DataSourceIntegerField departmentIdField;
    private final DataSourceBooleanField isDefaultField;

    public DSEmployeeRole(String dataSourceID) {

        super();
        setID(dataSourceID);
        setActionMode(ACTION_MODE_TABLE);
        setActionName("EmployeeRole");

        employeeRoleIdField = new DataSourceIntegerField("employeeRoleId", "员工角色编码");
        employeeRoleIdField.setLength(11);
        employeeRoleIdField.setPrimaryKey(true);
        employeeRoleIdField.setRequired(true);
        employeeRoleIdField.setHidden(true);

        employeeIdField = new DataSourceIntegerField("employeeId", "员工姓名");
        employeeIdField.setLength(11);
        employeeIdField.setRequired(false);
        employeeIdField.setHidden(true);
        employeeIdField.setValueMap(KeyValueManager.getValueMap("employees"));

        roleIdField = new DataSourceIntegerField("roleId", "角色");
        roleIdField.setLength(11);
        roleIdField.setRequired(true);
        roleIdField.setHidden(false);
//        roleIdField.setValueMap(KeyValueManager.getValueMap("roles"));
        KeyValueManager.loadValueMap("roles",roleIdField);
        departmentIdField = new DataSourceIntegerField("departmentId", "部门");
        departmentIdField.setLength(11);
        departmentIdField.setRequired(true);
        departmentIdField.setHidden(false);
        departmentIdField.setValueMap(KeyValueManager.getValueMap("departments"));

        isDefaultField = new DataSourceBooleanField("isDefault", "是默认角色");
        isDefaultField.setRequired(false);
        isDefaultField.setHidden(false);

        setFields(employeeRoleIdField, employeeIdField,departmentIdField, roleIdField,isDefaultField);
    }

}
