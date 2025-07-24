package com.delicacy.client.app.datasource;

import com.smartgwt.client.data.fields.*;
import com.delicacy.client.data.DBDataSource;

public class DSSystemProcessLink extends DBDataSource {

    public static DSSystemProcessLink instance = null;

    public static DSSystemProcessLink getInstance() {
        if (instance == null) {
            instance = new DSSystemProcessLink("DSSystemProcessLink");
        }
        return instance;
    }

    private final DataSourceIntegerField processLinkIdField;
    private final DataSourceIntegerField processIdField;
    private final DataSourceTextField conditionField;
    private final DataSourceIntegerField processActivityIdField;
    private final DataSourceIntegerField toProcessActivityIdField;

    public DSSystemProcessLink(String dataSourceID) {

        super();
        setID(dataSourceID);
        setActionMode(ACTION_MODE_TABLE);
        setActionName("SystemProcessLink");

        processLinkIdField = new DataSourceIntegerField("processLinkId", "主键编码");
        processLinkIdField.setLength(11);
        processLinkIdField.setPrimaryKey(true);
        processLinkIdField.setRequired(true);
        processLinkIdField.setHidden(false);

        processIdField = new DataSourceIntegerField("processId", "流程编码");
        processIdField.setLength(11);
        processIdField.setRequired(false);
        processIdField.setHidden(false);

        conditionField = new DataSourceTextField("condition", "条件");
        conditionField.setLength(64);
        conditionField.setRequired(false);
        conditionField.setHidden(false);

        processActivityIdField = new DataSourceIntegerField("processActivityId", "流程活动编码");
        processActivityIdField.setLength(11);
        processActivityIdField.setRequired(false);
        processActivityIdField.setHidden(false);

        toProcessActivityIdField = new DataSourceIntegerField("toProcessActivityId", "流向的活动编码");
        toProcessActivityIdField.setLength(11);
        toProcessActivityIdField.setRequired(false);
        toProcessActivityIdField.setHidden(false);

        setFields(processLinkIdField, processIdField, conditionField, processActivityIdField, toProcessActivityIdField);
    }

}
