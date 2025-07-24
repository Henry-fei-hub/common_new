package com.delicacy.client.app.datasource;

import com.delicacy.client.data.DBDataSource;
import com.delicacy.client.data.KeyValueManager;
import com.smartgwt.client.data.fields.DataSourceDateTimeField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;

public class DSProcessTestThreadTask extends DBDataSource
{


	public static DSProcessTestThreadTask instance = null;

	public static DSProcessTestThreadTask getInstance() {
		if(instance == null) {
			instance = new DSProcessTestThreadTask("DSProcessTestThreadTask");
		}
		return instance;
	}

	private final DataSourceIntegerField threadTaskManageIdField;
	private final DataSourceIntegerField taskTypeField;
	private final DataSourceIntegerField statusField;
	private final DataSourceTextField errorMsgField;
	private final DataSourceIntegerField operatorField;
	private final DataSourceIntegerField operationTypeField;
	private final DataSourceDateTimeField beginTimeField;
	private final DataSourceDateTimeField endTimeField;
	private final DataSourceDateTimeField createTimeField;
	private final DataSourceTextField remarkField;

	public DSProcessTestThreadTask(String dataSourceID) {

		super();
		setID(dataSourceID);
		setActionMode(ACTION_MODE_QUERY);
		setActionName("ProcessTestThreadTask");


		threadTaskManageIdField = new DataSourceIntegerField("threadTaskManageId", "任务ID");
		threadTaskManageIdField.setLength(11);
		threadTaskManageIdField.setPrimaryKey(true);
		threadTaskManageIdField.setRequired(true);
		threadTaskManageIdField.setHidden(false);
		threadTaskManageIdField.setForeignKey("DSProcessTestThreadTask.threadTaskManageId"); 


		taskTypeField = new DataSourceIntegerField("taskType", "任务类型");
		taskTypeField.setLength(11);
		taskTypeField.setRequired(false);
		taskTypeField.setHidden(false);
		taskTypeField.setValueMap(KeyValueManager.getValueMap("system_dictionary_166"));


		statusField = new DataSourceIntegerField("status", "任务状态");
		statusField.setLength(11);
		statusField.setRequired(false);
		statusField.setHidden(false);
		statusField.setValueMap(KeyValueManager.getValueMap("system_dictionary_165"));


		errorMsgField = new DataSourceTextField("errorMsg", "错误信息");
		errorMsgField.setLength(255);
		errorMsgField.setRequired(false);
		errorMsgField.setHidden(false);


		operatorField = new DataSourceIntegerField("operator", "操作人");
		operatorField.setLength(11);
		operatorField.setRequired(false);
		operatorField.setHidden(false);
		operatorField.setValueMap(KeyValueManager.getValueMap("employees"));


		operationTypeField = new DataSourceIntegerField("operationType", "操作类型");
		operationTypeField.setLength(11);
		operationTypeField.setRequired(false);
		operationTypeField.setHidden(false);
		operationTypeField.setValueMap(KeyValueManager.getValueMap("system_dictionary_167"));


		beginTimeField = new DataSourceDateTimeField("beginTime", "开始时间");
		beginTimeField.setRequired(false);
		beginTimeField.setHidden(false);


		endTimeField = new DataSourceDateTimeField("endTime", "结束时间");
		endTimeField.setRequired(false);
		endTimeField.setHidden(false);


		createTimeField = new DataSourceDateTimeField("createTime", "创建时间");
		createTimeField.setRequired(false);
		createTimeField.setHidden(false);


		remarkField = new DataSourceTextField("remark", "备注");
		remarkField.setLength(255);
		remarkField.setRequired(false);
		remarkField.setHidden(false);
		
		
		setFields(threadTaskManageIdField, taskTypeField, statusField, errorMsgField, operatorField, operationTypeField, beginTimeField, endTimeField, createTimeField, remarkField);
	}


}

