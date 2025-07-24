package com.delicacy.client.management.form;

import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.data.KeyValueManager;
import com.smartgwt.client.types.TextMatchStyle;
import com.smartgwt.client.widgets.form.SearchForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.PickTreeItem;

public class SsystemprocessattentionpeorSearchForm extends SearchForm
{


	private final PickTreeItem processTypeItem;
	private final ComboBoxItem employeeIdItem;

	public SsystemprocessattentionpeorSearchForm() {
		setWidth100();
		setHeight100();
//		setDataSource(CDSsystemprocessattentionpeor.getInstance());
		processTypeItem = new PickTreeItem("processType", "流程类型");
		processTypeItem.setWidth("*");
		processTypeItem.setCanSelectParentItems(true);
		processTypeItem.setValueField("treeId");
		processTypeItem.setValueTree(KeyValueManager.getTree("system_process_types", "0"));
		employeeIdItem = new ComboBoxItem("employeeId", "处理人");
		employeeIdItem.hide();
		employeeIdItem.setWidth("*");
		employeeIdItem.setTextMatchStyle(TextMatchStyle.SUBSTRING);
		employeeIdItem.setValueMap(KeyValueManager.getValueMap("employees"));

		setItems(processTypeItem, employeeIdItem);

		setNumCols(5);
		ClientUtil.searchFormProcessAccordingToDevice(this);
	}

}
