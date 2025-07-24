package com.delicacy.client.app.form;

import java.util.LinkedHashMap;
import java.util.Map;

import com.delicacy.client.app.datasource.DSRoleWithR;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.ui.AbstractWizadPage;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.ValuesManager;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.VLayout;

public class RoleWithRModifyUpdateForm extends AbstractWizadPage {

    private final TextItem roleNameItem;
    private final SelectItem applicationIdItem;
    private final SelectItem roleTypeItem;
    private final CheckboxItem enabledItem;

    @SuppressWarnings("rawtypes")
	public RoleWithRModifyUpdateForm() {
    	VLayout main = new VLayout(10);
        main.setHeight100();
        main.setWidth100();
        
        DSRoleWithR ds = DSRoleWithR.getInstance();
        __form.setWidth100();
        __form.setHeight("40%");
        
        roleNameItem = new TextItem("roleName", "角色名称");
        roleNameItem.setWidth("*");
        __formItems.add(roleNameItem);
        
        roleTypeItem = new SelectItem("roleType", "角色类型");
        roleTypeItem.setWidth("*");
        roleTypeItem.setValueMap(KeyValueManager.getValueMap("system_dictionary_10"));
        __formItems.add(roleTypeItem);
        
        applicationIdItem = new SelectItem("applicationId", "应用系统代码");
        applicationIdItem.setWidth("*");
        applicationIdItem.setValueMap((LinkedHashMap) ds.getField("applicationId").getValueMap());
        __formItems.add(applicationIdItem);
        
        enabledItem = new CheckboxItem("enabled", "是否有效");
        enabledItem.setDefaultValue(true);
        __formItems.add(enabledItem);
        
        __form.setItems(getFormItemArray());
        __form.setDataSource(ds);
        __form.setNumCols(2);
        ClientUtil.DynamicFormProcessAccordingToDevice(__form);
        main.addMember(__form);
        Label bel = new Label();
        bel.setHeight("10%");
        bel.setContents("<font style=\"color:red;font-weight:bold;font-size:16px;\">提示：如需赋予该角色給部门或者人员请点击【下一步】进行设置</font>");
        main.addMember(bel);
        
        Label bel1 = new Label();
        bel1.setHeight("50%");
        main.addMember(bel1);
        addMember(main);
        setPageMode(PAGE_MODE_UPDATE);
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
    public String getName() {
        return "角色设置";
    }

    @Override
    public void setValueManage(ValuesManager manager) {
        manager.setDataSource(DSRoleWithR.getInstance());
        manager.addMember(__form);
    }

    @SuppressWarnings("rawtypes")
	@Override
    public java.util.Map getValuesAsMap() {
    	   Map vals = __form.getValues();
           return vals;
    }

}

