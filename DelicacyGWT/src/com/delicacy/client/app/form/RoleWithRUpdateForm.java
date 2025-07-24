package com.delicacy.client.app.form;

import java.util.*;
import com.smartgwt.client.widgets.form.fields.*;
import com.smartgwt.client.widgets.form.validator.*;
import com.smartgwt.client.widgets.form.*;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.ui.AbstractWizadPage;
import com.delicacy.client.app.datasource.DSRoleWithR;
import com.delicacy.client.data.KeyValueManager;

public class RoleWithRUpdateForm extends AbstractWizadPage {

    private final TextItem roleIdItem;
    private final TextItem roleNameItem;
    private final SelectItem applicationIdItem;
    private final SelectItem roleTypeItem;
    private final CheckboxItem enabledItem;
    private final CheckboxItem hasApprovalRightItem;

    public RoleWithRUpdateForm() {
        DSRoleWithR ds = DSRoleWithR.getInstance();
        __form.setWidth100();
        __form.setHeight100();
        roleIdItem = new TextItem("roleId", "角色编码");
        roleIdItem.setWidth("*");
        roleIdItem.setDisabled(true);
        roleIdItem.setRequired(true);
        roleIdItem.hide();
        IsIntegerValidator roleIdValidator = new IsIntegerValidator();
        roleIdItem.setValidators(roleIdValidator);
        __formItems.add(roleIdItem);
        roleNameItem = new TextItem("roleName", "角色名称");
        roleNameItem.setWidth("*");
        __formItems.add(roleNameItem);
        applicationIdItem = new SelectItem("applicationId", "应用系统代码");
        applicationIdItem.setWidth("*");
        applicationIdItem.setValueMap((LinkedHashMap) ds.getField("applicationId").getValueMap());
        __formItems.add(applicationIdItem);
        roleTypeItem = new SelectItem("roleType", "角色类型");
        roleTypeItem.setWidth("*");
        roleTypeItem.setValueMap(KeyValueManager.getValueMap("system_dictionary_10"));
        __formItems.add(roleTypeItem);
        enabledItem = new CheckboxItem("enabled", "是否有效");
        __formItems.add(enabledItem);
        hasApprovalRightItem = new CheckboxItem("hasApprovalRight", "能审批");
        __formItems.add(hasApprovalRightItem);

        __form.setItems(getFormItemArray());
        __form.setDataSource(ds);
        __form.setNumCols(2);
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
        manager.setDataSource(DSRoleWithR.getInstance());
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
