package com.delicacy.client.app.form;

import java.util.*;
import com.smartgwt.client.widgets.form.fields.*;
import com.smartgwt.client.widgets.form.SearchForm;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.app.datasource.CDSystemProcessorTestResult;

public class SystemProcessorTestResultSearchForm extends SearchForm
{


	private final TextItem threadTaskManageIdItem;
	private final TextItem processNameItem;
	private final SelectItem testResultItem;

	public SystemProcessorTestResultSearchForm() {
		setWidth100();
		setHeight100();
		setDataSource(CDSystemProcessorTestResult.getInstance());
		threadTaskManageIdItem = new TextItem("threadTaskManageId", "任务ID");
		processNameItem = new TextItem("processName", "流程名称");
		testResultItem = new SelectItem("testResult", "执行结果");
		testResultItem.setValueMap((LinkedHashMap)getDataSource().getField("testResult").getValueMap());

		setItems(threadTaskManageIdItem, processNameItem, testResultItem);

		setNumCols(6);
		ClientUtil.searchFormProcessAccordingToDevice(this);
	}

}
