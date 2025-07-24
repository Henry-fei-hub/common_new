package com.delicacy.client.app.datasource;

import com.smartgwt.client.data.fields.*;
import com.delicacy.client.data.DBDataSource;

public class DSSystemProcessType extends DBDataSource {

    public static DSSystemProcessType instance = null;

    public static DSSystemProcessType getInstance() {
        if (instance == null) {
            instance = new DSSystemProcessType("DSSystemProcessType");
        }
        return instance;
    }

    private final DataSourceIntegerField processTypeIdField;
    private final DataSourceTextField processTypeNameField;
    private final DataSourceTextField descriptionField;
    private final DataSourceTextField processExecuteNameField;
    private final DataSourceBooleanField isEnableField;
    private final DataSourceIntegerField parentProcessTypeIdField;

    public DSSystemProcessType(String dataSourceID) {

        super();
        setID(dataSourceID);
        setActionMode(ACTION_MODE_TABLE);
        setActionName("SystemProcessType");

        processTypeIdField = new DataSourceIntegerField("processTypeId", "主键编码");
        processTypeIdField.setLength(11);
        processTypeIdField.setPrimaryKey(true);
        processTypeIdField.setRequired(true);
        processTypeIdField.setHidden(false);

        processTypeNameField = new DataSourceTextField("processTypeName", "流程类型名称");
        processTypeNameField.setLength(64);
        processTypeNameField.setRequired(false);
        processTypeNameField.setHidden(false);

        descriptionField = new DataSourceTextField("description", "流程类型描述");
        descriptionField.setLength(128);
        descriptionField.setRequired(false);
        descriptionField.setHidden(false);

        processExecuteNameField = new DataSourceTextField("processExecuteName", "流程处理程序名");
        processExecuteNameField.setLength(64);
        processExecuteNameField.setRequired(false);
        processExecuteNameField.setHidden(false);

        isEnableField = new DataSourceBooleanField("isEnable", "激活状态");
        isEnableField.setRequired(false);
        isEnableField.setHidden(false);

        parentProcessTypeIdField = new DataSourceIntegerField("parentProcessTypeId", "父级类型");
        parentProcessTypeIdField.setLength(11);
        parentProcessTypeIdField.setRequired(false);
        parentProcessTypeIdField.setForeignKey(dataSourceID + ".processTypeId");
        parentProcessTypeIdField.setRootValue("0");
        parentProcessTypeIdField.setHidden(false);

        setFields(processTypeIdField, processTypeNameField, descriptionField, processExecuteNameField, isEnableField, parentProcessTypeIdField);
    }

}
