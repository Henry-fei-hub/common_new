package com.delicacy.client.app.form;

import java.util.*;
import com.smartgwt.client.widgets.form.fields.*;
import com.smartgwt.client.widgets.form.SearchForm;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.app.datasource.CDProcessTestThreadTask;

public class ProcessTestThreadTaskSearchForm extends SearchForm
{


	private final TextItem threadTaskManageIdItem;
//	private final SelectItem taskTypeItem;
	private final SelectItem statusItem;

	public ProcessTestThreadTaskSearchForm() {
		setWidth100();
		setHeight100();
		setDataSource(CDProcessTestThreadTask.getInstance());
		
		threadTaskManageIdItem = new TextItem("threadTaskManageId", "任务ID");
		threadTaskManageIdItem.setWidth("*");
		
//		taskTypeItem = new SelectItem("taskType", "任务类型");
//		taskTypeItem.setValueMap(KeyValueManager.getValueMap("system_dictionary_166"));
		
		statusItem = new SelectItem("status", "任务状态");
		statusItem.setWidth("*");
		statusItem.setValueMap(KeyValueManager.getValueMap("system_dictionary_165"));

		setItems(threadTaskManageIdItem, statusItem);

		setNumCols(4);
		ClientUtil.searchFormProcessAccordingToDevice(this);
	}

}
