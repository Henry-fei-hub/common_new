package com.delicacy.client.app.form;

import com.delicacy.client.app.datasource.CDSystemProcessList;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.data.KeyValueManager;
import com.smartgwt.client.widgets.form.SearchForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;

public class SystemProcessListSearchForm extends SearchForm
{


	private final TextItem processNameItem;
	private final SelectItem processTypeIdItem;

	public SystemProcessListSearchForm() {
		setWidth100();
		setHeight100();
		setDataSource(CDSystemProcessList.getInstance());
		processNameItem = new TextItem("processName", "流程名称");
		processNameItem.setWidth("*");
		processTypeIdItem = new SelectItem("processTypeId", "流程类型");
		processTypeIdItem.setWidth("*");
		processTypeIdItem.setValueMap(KeyValueManager.getValueMap("system_process_types"));

		setItems(processNameItem, processTypeIdItem);

		setNumCols(4);
		ClientUtil.searchFormProcessAccordingToDevice(this);
	}

}
