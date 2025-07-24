package com.delicacy.client.app.datasource;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.*;
import com.delicacy.client.data.KeyValueManager;

public class CDProcessTestThreadTask extends DataSource
{


	public static CDProcessTestThreadTask instance = null;

	public static CDProcessTestThreadTask getInstance() {
		if(instance == null) {
			instance = new CDProcessTestThreadTask("CDProcessTestThreadTask");
		}
		return instance;
	}

	private final DataSourceIntegerField threadTaskManageIdField;
	private final DataSourceIntegerField taskTypeField;
	private final DataSourceIntegerField statusField;

	public CDProcessTestThreadTask(String dataSourceID) {

		setID(dataSourceID);
		threadTaskManageIdField = new DataSourceIntegerField("threadTaskManageId", "任务ID");
		threadTaskManageIdField.setRequired(true);
		threadTaskManageIdField.setLength(11);
		threadTaskManageIdField.setPrimaryKey(true);
		threadTaskManageIdField.setHidden(true);

		taskTypeField = new DataSourceIntegerField("taskType", "任务类型");
		taskTypeField.setRequired(false);
		taskTypeField.setLength(11);
		taskTypeField.setHidden(false);
		taskTypeField.setValueMap(KeyValueManager.getValueMap("system_dictionary"));

		statusField = new DataSourceIntegerField("status", "任务状态");
		statusField.setRequired(false);
		statusField.setLength(11);
		statusField.setHidden(false);
		statusField.setValueMap(KeyValueManager.getValueMap("system_dictionary"));

		DataSourceIntegerField currentPageField
			= new DataSourceIntegerField("currentPage", "当前页");
		currentPageField.setRequired(true);
		currentPageField.setLength(10);
		currentPageField.setHidden(true);

		DataSourceIntegerField pageLinesField
			= new DataSourceIntegerField("pageLines", "每页行数");
		pageLinesField.setRequired(true);
		pageLinesField.setLength(10);
		pageLinesField.setHidden(true);


		setFields(threadTaskManageIdField, taskTypeField, statusField, currentPageField, pageLinesField);

		setClientOnly(true);
	}


}

