package com.delicacy.client.app.form;

import com.smartgwt.client.widgets.form.fields.*;
import com.smartgwt.client.widgets.form.SearchForm;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.app.datasource.CDSapplicationaaor;

public class SapplicationaaorSearchForm extends SearchForm {

	private final TextItem applicationIdItem;
	private final TextItem applicationNameItem;

	public SapplicationaaorSearchForm() {
		setWidth100();
		setHeight100();
		setDataSource(CDSapplicationaaor.getInstance());
		applicationIdItem = new TextItem("applicationId", "系统代码");
		applicationIdItem.setWidth("*");
		applicationNameItem = new TextItem("applicationName", "系统名称");
		applicationNameItem.setWidth("*");

		setItems(applicationIdItem, applicationNameItem);

		setNumCols(4);
		ClientUtil.searchFormProcessAccordingToDevice(this);
	}

}
