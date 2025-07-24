package com.delicacy.client.app.form;

import java.util.*;
import com.smartgwt.client.widgets.form.fields.*;
import com.smartgwt.client.widgets.form.SearchForm;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.app.datasource.CDSfunctionffaor;

public class SfunctionffaorSearchForm extends SearchForm
{


	private final TextItem functionCodeItem;
	private final TextItem functionNameItem;
	private final SelectItem applicationIdItem;

	public SfunctionffaorSearchForm() {
		setWidth100();
		setHeight100();
		setDataSource(CDSfunctionffaor.getInstance());
		functionCodeItem = new TextItem("functionCode", "功能编号");
		
		functionNameItem = new TextItem("functionName", "功能名称");
		functionNameItem.setWidth("*");
		applicationIdItem = new SelectItem("applicationId", "应用系统");
//		applicationIdItem.setValueMap(KeyValueManager.getValueMap("applications"));
		KeyValueManager.loadValueMap("applications",applicationIdItem);

		setItems(functionCodeItem, functionNameItem, applicationIdItem);

		setNumCols(4);
		ClientUtil.searchFormProcessAccordingToDevice(this);
	}

}
