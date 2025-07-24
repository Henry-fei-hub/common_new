package com.delicacy.client.app.datasource;

import com.smartgwt.client.data.fields.*;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.data.DBDataSource;

public class DSSystemProcessActivity extends DBDataSource {

    public static DSSystemProcessActivity instance = null;

    public static DSSystemProcessActivity getInstance() {
        if (instance == null) {
            instance = new DSSystemProcessActivity("DSSystemProcessActivity");
        }
        return instance;
    }

    private final DataSourceIntegerField processActivityIdField;
    private final DataSourceIntegerField processIdField;
    private final DataSourceIntegerField activityTypeField;
    private final DataSourceTextField activityNameField;
    private final DataSourceTextField executeNameField;
    private final DataSourceIntegerField activitySerialNoField;
    private final DataSourceIntegerField posxField;
    private final DataSourceIntegerField posyField;
    private final DataSourceIntegerField timeOutActionField;
    private final DataSourceIntegerField executorTypeField;
    private final DataSourceIntegerField employeeIdField;
    private final DataSourceIntegerField departmentIdField;
    private final DataSourceIntegerField roleIdField;
    private final DataSourceIntegerField poolTypeField;
    private final DataSourceBooleanField ifAllowOverField;
    private final DataSourceBooleanField isEnableField;

    public DSSystemProcessActivity(String dataSourceID) {

        super();
        setID(dataSourceID);
        setActionMode(ACTION_MODE_TABLE);
        setActionName("SystemProcessActivity");

        processActivityIdField = new DataSourceIntegerField("processActivityId", "编码");
        processActivityIdField.setLength(11);
        processActivityIdField.setPrimaryKey(true);
        processActivityIdField.setRequired(true);
        processActivityIdField.setHidden(false);

        processIdField = new DataSourceIntegerField("processId", "流程编码");
        processIdField.setLength(11);
        processIdField.setRequired(false);
        processIdField.setHidden(false);

        activityTypeField = new DataSourceIntegerField("activityType", "活动节点类型");
        activityTypeField.setLength(11);
        activityTypeField.setRequired(false);
        activityTypeField.setHidden(false);
        
        poolTypeField = new DataSourceIntegerField("poolType", "任务池类型");
        poolTypeField.setLength(11);
        poolTypeField.setRequired(false);
        poolTypeField.setHidden(false);

        activityNameField = new DataSourceTextField("activityName", "活动节点名称");
        activityNameField.setLength(64);
        activityNameField.setRequired(false);
        activityNameField.setHidden(false);
        
        executeNameField = new DataSourceTextField("executeName", "处理名称");
        executeNameField.setLength(64);
        executeNameField.setRequired(false);
        executeNameField.setHidden(false);

        activitySerialNoField = new DataSourceIntegerField("activitySerialNo", "内部动作序号");
        activitySerialNoField.setLength(11);
        activitySerialNoField.setRequired(false);
        activitySerialNoField.setHidden(false);

        posxField = new DataSourceIntegerField("posx", "X坐标");
        posxField.setLength(11);
        posxField.setRequired(false);
        posxField.setHidden(false);

        posyField = new DataSourceIntegerField("posy", "Y坐标");
        posyField.setLength(11);
        posyField.setRequired(false);
        posyField.setHidden(false);

        timeOutActionField = new DataSourceIntegerField("timeOutAction", "活动时限");
        timeOutActionField.setLength(11);
        timeOutActionField.setRequired(false);
        timeOutActionField.setHidden(false);

        executorTypeField = new DataSourceIntegerField("executorType", "执行人类型");
        executorTypeField.setLength(11);
        executorTypeField.setRequired(false);
        executorTypeField.setHidden(false);

        employeeIdField = new DataSourceIntegerField("employeeId", "");
        employeeIdField.setLength(11);
        employeeIdField.setRequired(false);
        employeeIdField.setHidden(false);

        departmentIdField = new DataSourceIntegerField("departmentId", "该节点是由某个职务的人来做");
        departmentIdField.setLength(11);
        departmentIdField.setRequired(false);
        departmentIdField.setHidden(false);
        departmentIdField.setValueMap(KeyValueManager.getValueMap("departments"));

        roleIdField = new DataSourceIntegerField("roleId", "该节点是由某个角色的人来做");
        roleIdField.setLength(11);
        roleIdField.setRequired(false);
        roleIdField.setHidden(false);
        roleIdField.setValueMap(KeyValueManager.getValueMap("departments"));

        ifAllowOverField = new DataSourceBooleanField("ifAllowOver", "是否允许跳过");
        ifAllowOverField.setRequired(false);
        ifAllowOverField.setHidden(false);

        isEnableField = new DataSourceBooleanField("isEnable", "是否可用");
        isEnableField.setRequired(false);
        isEnableField.setHidden(false);

        setFields(processActivityIdField, processIdField, activityTypeField, activityNameField, activitySerialNoField, posxField, posyField, timeOutActionField, executorTypeField, employeeIdField, departmentIdField, roleIdField, executeNameField, poolTypeField, ifAllowOverField, isEnableField);
    }

}
