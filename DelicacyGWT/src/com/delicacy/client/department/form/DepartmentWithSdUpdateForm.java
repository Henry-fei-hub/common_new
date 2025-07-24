package com.delicacy.client.department.form;

import com.delicacy.client.BaseHelpUtils;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.department.datasource.DSDepartmentWithSd;
import com.delicacy.client.ui.AbstractWizadPage;
import com.smartgwt.client.types.TextMatchStyle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.ValuesManager;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

public class DepartmentWithSdUpdateForm extends AbstractWizadPage
{


	private final SelectItem departmentIdItem;
	private final TextItem departmentNameItem;
	private final TextItem abbreviationItem;
	private final ComboBoxItem managerIdItem;
	private final TextItem managerNameItem;
	private final SelectItem parentIdItem;
	private final CheckboxItem enabledItem;
	private final TextItem originalIdItem;
	private final ComboBoxItem plateIdItem;
	private final CheckboxItem isHeadcountItem;
	private final SelectItem departmentTypeItem;
	private final TextItem weixinDepartmentIdItem;
	private final TextItem emailDepartmentIdItem;
	private final TextItem ecmcDepartmentIdItem;
	private final TextItem deleteFlagItem;
	private final CheckboxItem isEnableItem;

	public DepartmentWithSdUpdateForm() {
		DSDepartmentWithSd ds = DSDepartmentWithSd.getInstance();
		__form.setWidth100();
		__form.setHeight100();
		
		departmentIdItem = new SelectItem("departmentId", "部门编码");
		departmentIdItem.hide();
		__formItems.add(departmentIdItem);
		
		departmentNameItem = new TextItem("departmentName", "部门名称");
		departmentNameItem.setWidth("*");
		__formItems.add(departmentNameItem);
		
		abbreviationItem = new TextItem("abbreviation", "部门名称缩写");
		abbreviationItem.setWidth("*");
		__formItems.add(abbreviationItem);
		
		managerIdItem = new ComboBoxItem("managerId", "部门负责人");
		managerIdItem.setWidth("*");
		managerIdItem.setChangeOnKeypress(false);
		managerIdItem.setTextMatchStyle(TextMatchStyle.SUBSTRING);
		managerIdItem.setValueMap(KeyValueManager.getValueMap("employee_lists"));
		managerIdItem.addChangedHandler(new ChangedHandler() {
			
			@Override
			public void onChanged(ChangedEvent event) {
				managerNameItem.setValue(managerIdItem.getDisplayValue());
			}
		});
		__formItems.add(managerIdItem);
		
		managerNameItem = new TextItem("managerName", "部门负责人姓名");
		managerNameItem.hide();
		__formItems.add(managerNameItem);
		
		parentIdItem = new SelectItem("parentId", "上级部门");
		parentIdItem.setWidth("*");
		parentIdItem.setCanEdit(false);
		parentIdItem.setValueMap(KeyValueManager.getValueMap("departments"));
		__formItems.add(parentIdItem);
		
		enabledItem = new CheckboxItem("enabled", "是否有效");
		__formItems.add(enabledItem);
		
		originalIdItem = new TextItem("originalId", "original_id");
		originalIdItem.hide();
		__formItems.add(originalIdItem);
		
		plateIdItem = new ComboBoxItem("plateId", "业务部门");
		plateIdItem.setWidth("*");
		plateIdItem.setValueMap(KeyValueManager.getValueMap("system_dictionary_1"));
		plateIdItem.setChangeOnKeypress(false);
		plateIdItem.setTextMatchStyle(TextMatchStyle.SUBSTRING);
		__formItems.add(plateIdItem);
		
		isHeadcountItem = new CheckboxItem("isHeadcount", "总部");
		__formItems.add(isHeadcountItem);
		
		departmentTypeItem = new SelectItem("departmentType", "部门类型");
		departmentTypeItem.setWidth("*");
		departmentTypeItem.setValueMap(KeyValueManager.getValueMap("system_dictionary_93"));
		__formItems.add(departmentTypeItem);
		
		weixinDepartmentIdItem = new TextItem("weixinDepartmentId", "微信部门编码");
		weixinDepartmentIdItem.hide();
		__formItems.add(weixinDepartmentIdItem);
		
		emailDepartmentIdItem = new TextItem("emailDepartmentId", "企业邮箱部门编码");
		emailDepartmentIdItem.hide();
		__formItems.add(emailDepartmentIdItem);
		
		ecmcDepartmentIdItem = new TextItem("ecmcDepartmentId", "ECMC部门编码");
		ecmcDepartmentIdItem.hide();
		__formItems.add(ecmcDepartmentIdItem);
		
		deleteFlagItem = new TextItem("deleteFlag", "0 未删除  1 已删除");
		deleteFlagItem.hide();
		__formItems.add(deleteFlagItem);
		
		isEnableItem = new CheckboxItem("isEnable", "is_enable");
		isEnableItem.hide();
		__formItems.add(isEnableItem);

		__form.setItems(getFormItemArray());
		__form.setDataSource(ds);
		__form.setNumCols(4);
		ClientUtil.DynamicFormProcessAccordingToDevice(__form);
		setPageMode(PAGE_MODE_UPDATE);
		addMember(__form);
	}

	@Override
	public void startEdit() {
		if(getRecord() != null) __form.editRecord(getRecord());
	}

	@Override
	public void setValueManage(ValuesManager manager) {
		manager.setDataSource(DSDepartmentWithSd.getInstance());
		manager.addMember(__form);
	}

	@Override
	public boolean checkData() {
		if(BaseHelpUtils.isNullOrEmpty(departmentNameItem.getValue())) {
			SC.say("部门名称不能为空");
			return false;
		}
		return true;
	}

	@Override
	public java.util.Map getValuesAsMap() {
		return __form.getValues();
	}


}
