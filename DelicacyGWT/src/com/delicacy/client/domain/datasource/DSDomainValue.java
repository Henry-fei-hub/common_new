package com.delicacy.client.domain.datasource;

import com.smartgwt.client.data.fields.*;
import com.delicacy.client.data.DBDataSource;

public class DSDomainValue extends DBDataSource {

    public static DSDomainValue instance = null;

    public static DSDomainValue getInstance() {
        if (instance == null) {
            instance = new DSDomainValue("DSDomainValue");
        }
        return instance;
    }

    private final DataSourceTextField selectIdField;
    private final DataSourceTextField tableNameField;
    private final DataSourceTextField keyColumnField;
    private final DataSourceTextField valueColumnField;
    private final DataSourceTextField conditionColumnField;
    private final DataSourceTextField additionalConditionField;

    public DSDomainValue(String dataSourceID) {

        super();
        setID(dataSourceID);
        setActionMode(ACTION_MODE_QUERY);
        setActionName("DomainValue");

        selectIdField = new DataSourceTextField("selectId", "selectId");
        selectIdField.setLength(64);
        selectIdField.setRequired(true);
        selectIdField.setHidden(false);

        tableNameField = new DataSourceTextField("tableName", "tableName");
        tableNameField.setLength(64);
        tableNameField.setRequired(true);
        tableNameField.setHidden(false);

        keyColumnField = new DataSourceTextField("keyColumn", "keyColumn");
        keyColumnField.setLength(64);
        keyColumnField.setRequired(true);
        keyColumnField.setHidden(false);

        valueColumnField = new DataSourceTextField("valueColumn", "valueColumn");
        valueColumnField.setLength(64);
        valueColumnField.setRequired(true);
        valueColumnField.setHidden(false);

        conditionColumnField = new DataSourceTextField("conditionColumn", "conditionColumn");
        conditionColumnField.setLength(64);
        conditionColumnField.setRequired(true);
        conditionColumnField.setHidden(false);

        additionalConditionField = new DataSourceTextField("additionalCondition", "additionalCondition");
        additionalConditionField.setLength(128);
        additionalConditionField.setRequired(true);
        additionalConditionField.setHidden(false);

        setFields(selectIdField, tableNameField, keyColumnField, valueColumnField, conditionColumnField, additionalConditionField);

    }

}
