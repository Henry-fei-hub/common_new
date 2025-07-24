package com.delicacy.client.app.form;

import com.smartgwt.client.widgets.form.fields.*;
import com.smartgwt.client.widgets.form.validator.*;
import com.smartgwt.client.widgets.form.*;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.ui.AbstractWizadPage;
import com.delicacy.client.app.datasource.DSSapplicationaaor;

public class SapplicationaaorUpdateForm extends AbstractWizadPage {

	private final TextItem applicationIdItem;
	private final TextItem applicationNameItem;

	public SapplicationaaorUpdateForm() {
		DSSapplicationaaor ds = DSSapplicationaaor.getInstance();
		__form.setWidth100();
		__form.setHeight100();
		applicationIdItem = new TextItem("applicationId", "应用系统代码");
		applicationIdItem.setWidth("*");
		applicationIdItem.setDisabled(true);
		applicationIdItem.setRequired(true);
		IsIntegerValidator applicationIdValidator = new IsIntegerValidator();
		applicationIdItem.setValidators(applicationIdValidator);
		__formItems.add(applicationIdItem);
		applicationNameItem = new TextItem("applicationName", "应用系统名称");
		applicationNameItem.setWidth("*");
		__formItems.add(applicationNameItem);

		__form.setItems(getFormItemArray());
		__form.setDataSource(ds);
		__form.setNumCols(4);
		ClientUtil.DynamicFormProcessAccordingToDevice(__form);
		setPageMode(PAGE_MODE_UPDATE);
		addMember(__form);
	}

	@Override
	public void startEdit() {
		if (getRecord() != null) {
			__form.editRecord(getRecord());
		}
	}

	@Override
	public void setValueManage(ValuesManager manager) {
		manager.setDataSource(DSSapplicationaaor.getInstance());
		manager.addMember(__form);
	}

	@Override
	public boolean checkData() {
		return true;
	}

	@Override
	public java.util.Map getValuesAsMap() {
		return __form.getValues();
	}

}
