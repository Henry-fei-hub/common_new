package com.delicacy.client.management.form;

import java.util.*;
import com.smartgwt.client.widgets.form.fields.*;
import com.smartgwt.client.widgets.form.SearchForm;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.management.datasource.CDSsystemprocessinstanceor;

public class SsystemprocessinstanceorSearchForm extends SearchForm
{


	private final DateTimeItem minCreateTimeItem;
	private final DateTimeItem maxCreateTimeItem;
	private final PickTreeItem processTypeItem;
	private final TextItem employeeIdItem;

	public SsystemprocessinstanceorSearchForm() {
		setWidth100();
		setHeight100();
		setDataSource(CDSsystemprocessinstanceor.getInstance());
		minCreateTimeItem = new DateTimeItem("minCreateTime", "最早申请时间");
		minCreateTimeItem.setWidth("*");
		minCreateTimeItem.addChangedHandler(new ChangedHandler() {
			@Override
			public void onChanged(ChangedEvent event) {
				Date val = (Date) event.getValue();
				com.delicacy.client.data.ClientUtil.searchDateFormat(val, minCreateTimeItem);
			}
		});
		maxCreateTimeItem = new DateTimeItem("maxCreateTime", "最晚申请时间");
		maxCreateTimeItem.setWidth("*");
		maxCreateTimeItem.addChangedHandler(new ChangedHandler() {
			@Override
			public void onChanged(ChangedEvent event) {
				Date val = (Date) event.getValue();
				com.delicacy.client.data.ClientUtil.searchDateFormat(val, maxCreateTimeItem);
			}
		});
		processTypeItem = new PickTreeItem("processType", "流程类型");
		processTypeItem.setWidth("*");
		processTypeItem.setCanSelectParentItems(true);
		processTypeItem.setValueField("treeId");
		processTypeItem.setValueTree(KeyValueManager.getTree("system_process_types", "0"));
		employeeIdItem = new TextItem("employeeId", "创建人");

		setItems(minCreateTimeItem, maxCreateTimeItem, processTypeItem);

		setNumCols(7);
		ClientUtil.searchFormProcessAccordingToDevice(this);
	}

}
