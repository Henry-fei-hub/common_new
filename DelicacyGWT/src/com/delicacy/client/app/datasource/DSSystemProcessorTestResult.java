package com.delicacy.client.app.datasource;

import com.smartgwt.client.data.fields.*;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.FieldType;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.data.DBDataSource;

public class DSSystemProcessorTestResult extends DBDataSource
{


	public static DSSystemProcessorTestResult instance = null;

	public static DSSystemProcessorTestResult getInstance() {
		if(instance == null) {
			instance = new DSSystemProcessorTestResult("DSSystemProcessorTestResult");
		}
		return instance;
	}

	private final DataSourceIntegerField processorTestResultIdField;
	private final DataSourceIntegerField drafterField;
	private final DataSourceTextField employeeNoField;
	private final DataSourceTextField employeeNameField;
	private final DataSourceIntegerField departmentIdField;
	private final DataSourceIntegerField plateIdField;
	private final DataSourceIntegerField companyIdField;
	private final DataSourceIntegerField processIdField;
	private final DataSourceIntegerField processTypeIdField;
	private final DataSourceTextField processNameField;
	private final DataSourceTextField descriptionField;
	private final DataSourceTextField testDataField;
	private final DataSourceIntegerField testResultField;
	private final DataSourceTextField errorMsgField;
	private final DataSourceDateTimeField startTimeField;
	private final DataSourceDateTimeField endTimeField;
	private final DataSourceIntegerField threadTaskManageIdField;

	public DSSystemProcessorTestResult(String dataSourceID) {

		super();
		setID(dataSourceID);
		setActionMode(ACTION_MODE_QUERY);
		setActionName("SystemProcessorTestResult");


		processorTestResultIdField = new DataSourceIntegerField("processorTestResultId", "流程测试结果编码");
		processorTestResultIdField.setLength(11);
		processorTestResultIdField.setPrimaryKey(true);
		processorTestResultIdField.setRequired(true);
		processorTestResultIdField.setHidden(true);


		drafterField = new DataSourceIntegerField("drafter", "发起人");
		drafterField.setLength(11);
		drafterField.setRequired(false);
		drafterField.setHidden(true);
		drafterField.setValueMap(KeyValueManager.getValueMap("employees"));


		employeeNoField = new DataSourceTextField("employeeNo", "发起人工号");
		employeeNoField.setLength(64);
		employeeNoField.setRequired(true);
		employeeNoField.setHidden(false);


		employeeNameField = new DataSourceTextField("employeeName", "发起人姓名");
		employeeNameField.setLength(64);
		employeeNameField.setRequired(true);
		employeeNameField.setHidden(false);


		departmentIdField = new DataSourceIntegerField("departmentId", "部门");
		departmentIdField.setLength(11);
		departmentIdField.setRequired(false);
		departmentIdField.setHidden(false);
		departmentIdField.setValueMap(KeyValueManager.getValueMap("departments"));


		plateIdField = new DataSourceIntegerField("plateId", "业务部门");
		plateIdField.setLength(11);
		plateIdField.setRequired(false);
		plateIdField.setHidden(false);
		plateIdField.setValueMap(KeyValueManager.getValueMap("system_dictionary_1"));


		companyIdField = new DataSourceIntegerField("companyId", "归属公司");
		companyIdField.setLength(11);
		companyIdField.setRequired(false);
		companyIdField.setHidden(false);
		companyIdField.setValueMap(KeyValueManager.getValueMap("system_dictionary_26"));


		processIdField = new DataSourceIntegerField("processId", "流程编码");
		processIdField.setLength(11);
		processIdField.setRequired(false);
		processIdField.setHidden(true);


		processTypeIdField = new DataSourceIntegerField("processTypeId", "流程类型");
		processTypeIdField.setLength(11);
		processTypeIdField.setRequired(false);
		processTypeIdField.setHidden(false);
		processTypeIdField.setValueMap(KeyValueManager.getValueMap("system_process_types"));


		processNameField = new DataSourceTextField("processName", "流程名称");
		processNameField.setLength(64);
		processNameField.setRequired(false);
		processNameField.setHidden(false);


		descriptionField = new DataSourceTextField("description", "描述");
		descriptionField.setLength(512);
		descriptionField.setRequired(false);
		descriptionField.setHidden(false);


		testDataField = new DataSourceTextField("testData", "测试数据");
		testDataField.setLength(2048);
		testDataField.setRequired(false);
		testDataField.setHidden(false);


		testResultField = new DataSourceIntegerField("testResult", "执行结果");
		testResultField.setLength(11);
		testResultField.setRequired(false);
		testResultField.setHidden(false);

		java.util.Map<String,String> testResultValues = new java.util.HashMap<String,String>();
		testResultValues.put("1", "成功");
		testResultValues.put("2", "失败");
		testResultField.setValueMap(testResultValues);


		errorMsgField = new DataSourceTextField("errorMsg", "错误信息");
		errorMsgField.setLength(512);
		errorMsgField.setRequired(false);
		errorMsgField.setHidden(false);


		startTimeField = new DataSourceDateTimeField("startTime", "开始时间");
		startTimeField.setRequired(false);
		startTimeField.setHidden(false);


		endTimeField = new DataSourceDateTimeField("endTime", "结束时间");
		endTimeField.setRequired(false);
		endTimeField.setHidden(false);


		threadTaskManageIdField = new DataSourceIntegerField("threadTaskManageId", "任务ID");
		threadTaskManageIdField.setLength(11);
		threadTaskManageIdField.setRequired(false);
		threadTaskManageIdField.setHidden(true);
		threadTaskManageIdField.setForeignKey("DSProcessTestThreadTask.threadTaskManageId"); 


		setFields(processorTestResultIdField, drafterField, employeeNoField, employeeNameField, departmentIdField, plateIdField, companyIdField, processIdField, processTypeIdField, processNameField, descriptionField, testDataField, testResultField, errorMsgField, startTimeField, endTimeField, threadTaskManageIdField);
	}


}

