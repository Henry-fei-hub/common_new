package com.delicacy.client.app.datasource;

import com.smartgwt.client.data.fields.*;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.data.DBDataSource;

public class DSDepartmentPart extends DBDataSource {

    public static DSDepartmentPart instance = null;

    public static DSDepartmentPart getInstance() {
        if (instance == null) {
            instance = new DSDepartmentPart("DSDepartment");
        }
        return instance;
    }

    private final DataSourceIntegerField departmentIdField;
    private final DataSourceTextField departmentNameField;
    private final DataSourceTextField abbreviationField;
    private final DataSourceIntegerField managerIdField;
    private final DataSourceTextField managerNameField;
    private final DataSourceIntegerField parentIdField;
    private final DataSourceBooleanField enabledField;
    private final DataSourceIntegerField originalIdField;
    private final DataSourceIntegerField plateIdField;
    private final DataSourceBooleanField isHeadcountField;

    public DSDepartmentPart(String dataSourceID) {

        super();
        setID(dataSourceID);
        setActionMode(ACTION_MODE_CUSTOM);
        setActionName("DepartmentPart");

        departmentIdField = new DataSourceIntegerField("departmentId", "部门编码");
        departmentIdField.setLength(11);
        departmentIdField.setPrimaryKey(true);
        departmentIdField.setRequired(true);
        departmentIdField.setHidden(false);

        departmentNameField = new DataSourceTextField("departmentName", "部门名称");
        departmentNameField.setLength(64);
        departmentNameField.setRequired(true);
        departmentNameField.setHidden(false);

        abbreviationField = new DataSourceTextField("abbreviation", "部门名称缩写");
        abbreviationField.setLength(64);
        abbreviationField.setRequired(false);
        abbreviationField.setHidden(false);

        managerIdField = new DataSourceIntegerField("managerId", "部门负责人");
        managerIdField.setLength(11);
        managerIdField.setRequired(false);
        managerIdField.setHidden(false);
        managerIdField.setValueMap(KeyValueManager.getValueMap("employees"));

        managerNameField = new DataSourceTextField("managerName", "部门负责人姓名");
        managerNameField.setLength(64);
        managerNameField.setRequired(false);
        managerNameField.setHidden(true);

        parentIdField = new DataSourceIntegerField("parentId", "上级部门");
        parentIdField.setLength(11);
        parentIdField.setRequired(false);
        parentIdField.setForeignKey(dataSourceID + ".departmentId");
        parentIdField.setRootValue("0");
        parentIdField.setHidden(false);

        enabledField = new DataSourceBooleanField("enabled", "是否有效");
        enabledField.setRequired(false);
        enabledField.setHidden(false);

        originalIdField = new DataSourceIntegerField("originalId", "");
        originalIdField.setLength(11);
        originalIdField.setRequired(false);
        originalIdField.setHidden(true);

        plateIdField = new DataSourceIntegerField("plateId", "业务部门");
        plateIdField.setLength(11);
        plateIdField.setRequired(false);
        plateIdField.setHidden(false);
        plateIdField.setValueMap(KeyValueManager.getValueMap("system_dictionary_1"));

        isHeadcountField = new DataSourceBooleanField("isHeadcount", "总部");
        isHeadcountField.setRequired(false);
        isHeadcountField.setHidden(false);

        setFields(departmentIdField, departmentNameField, abbreviationField, managerIdField, managerNameField, parentIdField, enabledField, originalIdField, plateIdField, isHeadcountField);
    }

}
