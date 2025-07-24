package com.delicacy.client.app.datasource;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.*;
import com.delicacy.client.data.KeyValueManager;

public class CDSystemProcessorTestResult extends DataSource
{


	public static CDSystemProcessorTestResult instance = null;

	public static CDSystemProcessorTestResult getInstance() {
		if(instance == null) {
			instance = new CDSystemProcessorTestResult("CDSystemProcessorTestResult");
		}
		return instance;
	}

	private final DataSourceIntegerField threadTaskManageIdField;
	private final DataSourceTextField processNameField;
	private final DataSourceIntegerField testResultField;

	public CDSystemProcessorTestResult(String dataSourceID) {

		setID(dataSourceID);
		threadTaskManageIdField = new DataSourceIntegerField("threadTaskManageId", "任务ID");
		threadTaskManageIdField.setRequired(false);
		threadTaskManageIdField.setLength(11);
		threadTaskManageIdField.setHidden(false);

		processNameField = new DataSourceTextField("processName", "流程名称");
		processNameField.setRequired(false);
		processNameField.setLength(64);
		processNameField.setHidden(false);

		testResultField = new DataSourceIntegerField("testResult", "执行结果");
		testResultField.setRequired(false);
		testResultField.setLength(11);
		testResultField.setHidden(false);

		java.util.Map<String,String> testResultValues = new java.util.HashMap<String,String>();
		testResultValues.put("2", "失败");
		testResultValues.put("1", "成功");
		testResultField.setValueMap(testResultValues);

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


		setFields(threadTaskManageIdField, processNameField, testResultField, currentPageField, pageLinesField);

		setClientOnly(true);
	}


}

