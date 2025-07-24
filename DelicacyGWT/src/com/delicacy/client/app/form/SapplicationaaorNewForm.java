package com.delicacy.client.app.form;

import com.smartgwt.client.widgets.form.fields.*;
import com.smartgwt.client.widgets.form.*;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.ui.AbstractWizadPage;
import com.delicacy.client.app.datasource.DSSapplicationaaor;

public class SapplicationaaorNewForm extends AbstractWizadPage {

	private final TextItem applicationNameItem;

	public SapplicationaaorNewForm() {
		DSSapplicationaaor ds = DSSapplicationaaor.getInstance();
		__form.setWidth100();
		__form.setHeight100();
		applicationNameItem = new TextItem("applicationName", "应用系统名称");
		applicationNameItem.setWidth("*");
		__formItems.add(applicationNameItem);

		__form.setItems(getFormItemArray());
		__form.setDataSource(ds);
		__form.setNumCols(4);
		ClientUtil.DynamicFormProcessAccordingToDevice(__form);
		addMember(__form);
	}

	@Override
	public boolean checkData() {
		return true;
	}

	@Override
	public void startEdit() {
		if (getRecord() != null) {
			__form.editRecord(getRecord());
		} else {
			__form.editNewRecord();
		}
	}

	@Override
	public void setValueManage(ValuesManager manager) {
		manager.setDataSource(DSSapplicationaaor.getInstance());
		manager.addMember(__form);
	}

	@Override
	public java.util.Map getValuesAsMap() {
		return __form.getValues();
	}

}
