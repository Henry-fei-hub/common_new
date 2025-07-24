package com.delicacy.client.app.form;

import java.util.*;
import com.smartgwt.client.widgets.form.fields.*;
import com.smartgwt.client.widgets.form.validator.*;
import com.smartgwt.client.types.TextMatchStyle;
import com.smartgwt.client.widgets.form.*;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.ui.AbstractWizadPage;
import com.delicacy.client.app.datasource.DSMemployee;
import com.delicacy.client.data.KeyValueManager;

public class MemployeeMaininfoNew extends AbstractWizadPage {

    private final SelectItem employeeIdItem;
    private final TextItem photoItem;
    private final TextItem employeeNoItem;
    private final TextItem employeeNameItem;
    private final SelectItem plateIdItem;
    private final IPickTreeItem departmentIdItem;
    private final SelectItem roleIdItem;
    private final TextItem employeeRoleNamesItem;
    private final ComboBoxItem userAcctItem;
    private final SelectItem gradeIdItem;
    private final CheckboxItem isDepartmentItem;
    private final ComboBoxItem shiftManageIdItem;
    private final CheckboxItem isCheckItem;

    public MemployeeMaininfoNew() {
        DSMemployee ds = DSMemployee.getInstance();
        __form.setWidth100();
        __form.setHeight100();
        employeeIdItem = new SelectItem("employeeId", "员工编码");
        employeeIdItem.setValueMap((LinkedHashMap) ds.getField("employeeId").getValueMap());
        employeeIdItem.hide();
        __formItems.add(employeeIdItem);
        photoItem = new TextItem("photo", "头像");
        photoItem.hide();
        photoItem.setWidth("*");
        __formItems.add(photoItem);
        employeeNoItem = new TextItem("employeeNo", "登陆名");
        employeeNoItem.setWidth("*");
        employeeNoItem.setRequired(true);
        IsStringValidator employeeNoValidator = new IsStringValidator();
        LengthRangeValidator employeeNoLengthValidator = new LengthRangeValidator();
        employeeNoLengthValidator.setMax(64);
        employeeNoItem.setValidators(employeeNoValidator, employeeNoLengthValidator);
        __formItems.add(employeeNoItem);
        employeeNameItem = new TextItem("employeeName", "员工姓名");
        employeeNameItem.setWidth("*");
        employeeNameItem.setRequired(true);
        IsStringValidator employeeNameValidator = new IsStringValidator();
        LengthRangeValidator employeeNameLengthValidator = new LengthRangeValidator();
        employeeNameLengthValidator.setMax(64);
        employeeNameItem.setValidators(employeeNameValidator, employeeNameLengthValidator);
        __formItems.add(employeeNameItem);
        plateIdItem = new SelectItem("plateId", "业务部门");
        plateIdItem.setWidth("*");
        plateIdItem.setValueMap((LinkedHashMap) ds.getField("plateId").getValueMap());
        __formItems.add(plateIdItem);
        departmentIdItem = new IPickTreeItem("departmentId", "部门");
        departmentIdItem.setWidth("*");
        departmentIdItem.setCanSelectParentItems(true);
        departmentIdItem.setValueTree(KeyValueManager.getTree("departments"));
        __formItems.add(departmentIdItem);
        roleIdItem = new SelectItem("roleId", "角色编码");
        roleIdItem.hide();
        roleIdItem.setValueMap((LinkedHashMap) ds.getField("roleId").getValueMap());
        __formItems.add(roleIdItem);
        employeeRoleNamesItem = new TextItem("employeeRoleNames", "角色");
        employeeRoleNamesItem.setWidth("*");
        employeeRoleNamesItem.hide();
        employeeRoleNamesItem.setRequired(true);
        IsStringValidator employeeRoleNamesValidator = new IsStringValidator();
        LengthRangeValidator employeeRoleNamesLengthValidator = new LengthRangeValidator();
        employeeRoleNamesLengthValidator.setMax(64);
        employeeRoleNamesItem.setValidators(employeeRoleNamesValidator, employeeRoleNamesLengthValidator);
        __formItems.add(employeeRoleNamesItem);
        userAcctItem = new ComboBoxItem("userAcct", "上级领导");
        userAcctItem.setWidth("*");
//        userAcctItem.setValueMap(KeyValueManager.getValueMap("employee_names"));
        KeyValueManager.loadValueMap("employee_names",userAcctItem);
        __formItems.add(userAcctItem);
        gradeIdItem = new SelectItem("gradeId", "职级");
        gradeIdItem.setWidth("*");
        gradeIdItem.setValueMap((LinkedHashMap) ds.getField("gradeId").getValueMap());
        __formItems.add(gradeIdItem);
        
        shiftManageIdItem = new ComboBoxItem("shiftManageId", "班次");
        shiftManageIdItem.setWidth("*");
        shiftManageIdItem.setChangeOnKeypress(false);
        shiftManageIdItem.setTextMatchStyle(TextMatchStyle.SUBSTRING);
//        shiftManageIdItem.setValueMap(KeyValueManager.getValueMap("shift_manages"));
        KeyValueManager.loadValueMap("shift_manages",shiftManageIdItem);
        __formItems.add(shiftManageIdItem);
        
        isDepartmentItem = new CheckboxItem("isDepartment", "是否为部门负责人");
        __formItems.add(isDepartmentItem);
        
        isCheckItem = new CheckboxItem("isCheck", "是否参与考勤");
        __formItems.add(isCheckItem);

        __form.setItems(getFormItemArray());
        __form.setDataSource(ds);
        __form.setNumCols(4);
        ClientUtil.DynamicFormProcessAccordingToDevice(__form);
        setName("主要信息");
        addMember(__form);
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
    public boolean checkData() {
        return true;
    }

    @Override
    public void setValueManage(ValuesManager manager) {
        manager.setDataSource(DSMemployee.getInstance());
        manager.addMember(__form);
    }

    @Override
    public java.util.Map getValuesAsMap() {
        Map values = __form.getValues();
        values.put("operateEmployeeId", ClientUtil.getUserId());
        ClientUtil.departmentParameterProcess(values);
        return values;
    }

}
