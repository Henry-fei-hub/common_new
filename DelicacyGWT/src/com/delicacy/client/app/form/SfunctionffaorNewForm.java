package com.delicacy.client.app.form;

import java.util.*;
import com.smartgwt.client.widgets.form.fields.*;
import com.smartgwt.client.widgets.form.*;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.ui.AbstractWizadPage;
import com.delicacy.client.app.datasource.DSSfunctionffaor;

public class SfunctionffaorNewForm extends AbstractWizadPage {

	private final TextItem functionCodeItem;
	private final TextItem parentIdItem;
	private final TextItem functionNameItem;
	private final SelectItem applicationIdItem;
	private final SelectItem functionTypeItem;
	private final CheckboxItem enabledItem;

	public SfunctionffaorNewForm() {
		DSSfunctionffaor ds = DSSfunctionffaor.getInstance();
		__form.setWidth100();
		__form.setHeight100();
		functionCodeItem = new TextItem("functionCode", "功能编号");
		__formItems.add(functionCodeItem);
		parentIdItem = new TextItem("parentId", "上级功能");
		parentIdItem.setWidth("*");
		__formItems.add(parentIdItem);
		functionNameItem = new TextItem("functionName", "功能名称");
		functionNameItem.setWidth("*");
		__formItems.add(functionNameItem);
		applicationIdItem = new SelectItem("applicationId", "应用系统");
		applicationIdItem.setValueMap((LinkedHashMap) ds.getField("applicationId").getValueMap());
		__formItems.add(applicationIdItem);
		functionTypeItem = new SelectItem("functionType", "功能类型");
		functionTypeItem.setValueMap((LinkedHashMap) ds.getField("functionType").getValueMap());
		__formItems.add(functionTypeItem);
		enabledItem = new CheckboxItem("enabled", "是否有效");
		__formItems.add(enabledItem);

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
		manager.setDataSource(DSSfunctionffaor.getInstance());
		manager.addMember(__form);
	}

	@Override
	public java.util.Map getValuesAsMap() {
		return __form.getValues();
	}

}
