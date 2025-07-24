package com.delicacy.client.app.form;

import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.data.KeyValueManager;
import com.smartgwt.client.widgets.form.SearchForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;

public class SemployeeRoleInfoSearchForm extends SearchForm {

	private final TextItem roleNameItem;
    private final SelectItem applicationIdItem;
    private final SelectItem roleTypeItem;

    public SemployeeRoleInfoSearchForm() {
        setWidth100();
        setHeight100();
        
        roleNameItem = new TextItem("roleName", "角色名称");
        roleNameItem.setWidth("*");
        
        roleTypeItem = new SelectItem("roleType", "角色类型");
        roleTypeItem.setWidth("*");
        roleTypeItem.setValueMap(KeyValueManager.getValueMap("system_dictionary_10"));
        
        applicationIdItem = new SelectItem("applicationId", "应用系统代码");
        applicationIdItem.setWidth("*");
//        applicationIdItem.setValueMap(KeyValueManager.getValueMap("applications"));
        KeyValueManager.loadValueMap("applications",applicationIdItem);

        setItems(roleNameItem,roleTypeItem,applicationIdItem);

        setNumCols(7);
        ClientUtil.searchFormProcessAccordingToDevice(this);
    }

}
