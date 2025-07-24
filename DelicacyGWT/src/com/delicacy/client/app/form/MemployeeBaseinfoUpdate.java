package com.delicacy.client.app.form;

import java.util.LinkedHashMap;

import com.delicacy.client.app.datasource.DSMemployee;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.ui.AbstractWizadPage;
import com.smartgwt.client.widgets.form.ValuesManager;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;

public class MemployeeBaseinfoUpdate extends AbstractWizadPage {

    private final TextItem mobileItem;
    private final TextItem phoneItem;
    private final TextItem qqItem;
    private final TextItem emailItem;
    private final DateItem onboardDateItem;
    private final DateItem resignationDateItem;
    private final TextItem degreeItem;
    private final TextItem educationItem;
    private final SelectItem genderItem;
    private final TextItem marriedStatusItem;
    private final DateItem birthItem;
    private final TextItem ageItem;
    private final TextItem cardItem;
    private final TextItem addressItem;
    private final TextItem autographItem;
    private final TextItem nationalityItem;
    private final SelectItem statusItem;
    private final CheckboxItem usableStatusItem;
    private final TextItem workAddressItem;
    private final TextItem registeredAddressItem;
    private final TextItem employeePasswordItem;
    private final CheckboxItem lockedItem;
    private final TextItem employeeNameEnItem;
    private final TextItem healthItem;

    public MemployeeBaseinfoUpdate() {
        DSMemployee ds = DSMemployee.getInstance();
        __form.setWidth100();
        __form.setHeight100();
        mobileItem = new TextItem("mobile", "手机");
        mobileItem.setWidth("*");
        __formItems.add(mobileItem);
        phoneItem = new TextItem("phone", "电话");
        phoneItem.setWidth("*");
        __formItems.add(phoneItem);
        qqItem = new TextItem("qq", "QQ");
        qqItem.setWidth("*");
        __formItems.add(qqItem);
        emailItem = new TextItem("email", "邮箱");
        emailItem.setWidth("*");
        __formItems.add(emailItem);
        degreeItem = new TextItem("degree", "学位");
        degreeItem.setWidth("*");
        __formItems.add(degreeItem);
        educationItem = new TextItem("education", "学历");
        educationItem.setWidth("*");
        __formItems.add(educationItem);
        genderItem = new SelectItem("gender", "性别");
        genderItem.setWidth("*");
        genderItem.setValueMap((LinkedHashMap) ds.getField("gender").getValueMap());
        __formItems.add(genderItem);
        marriedStatusItem = new TextItem("marriedStatus", "婚姻状况");
        marriedStatusItem.setWidth("*");
        __formItems.add(marriedStatusItem);
        onboardDateItem = new DateItem("onboardDate", "入职日期");
        __formItems.add(onboardDateItem);
        resignationDateItem = new DateItem("resignationDate", "离职日期");
        resignationDateItem.hide();
        __formItems.add(resignationDateItem);
        birthItem = new DateItem("birth", "出生日期");
        __formItems.add(birthItem);
        ageItem = new TextItem("age", "age");
        ageItem.hide();
        ageItem.setWidth("*");
        __formItems.add(ageItem);
        cardItem = new TextItem("card", "身份证号");
        cardItem.setWidth("*");
        __formItems.add(cardItem);
        addressItem = new TextItem("address", "家庭地址");
        addressItem.setWidth("*");
        __formItems.add(addressItem);
        autographItem = new TextItem("autograph", "我的签名");
        autographItem.setWidth("*");
        __formItems.add(autographItem);
        nationalityItem = new TextItem("nationality", "民族");
        nationalityItem.setWidth("*");
        __formItems.add(nationalityItem);
        statusItem = new SelectItem("status", "状态");
        statusItem.hide();
        statusItem.setValueMap((LinkedHashMap) ds.getField("status").getValueMap());
        __formItems.add(statusItem);
        workAddressItem = new TextItem("workAddress", "工作地");
        workAddressItem.setWidth("*");
        __formItems.add(workAddressItem);
        registeredAddressItem = new TextItem("registeredAddress", "注册地");
        registeredAddressItem.setWidth("*");
        __formItems.add(registeredAddressItem);
        employeePasswordItem = new TextItem("employeePassword", "密码");
        employeePasswordItem.hide();
        employeePasswordItem.setWidth("*");
        __formItems.add(employeePasswordItem);
         usableStatusItem = new CheckboxItem("usableStatus", "是否可用");
        __formItems.add(usableStatusItem);
        lockedItem = new CheckboxItem("locked", "是否锁定");
        __formItems.add(lockedItem);
        employeeNameEnItem = new TextItem("employeeNameEn", "英文名");
        employeeNameEnItem.setWidth("*");
        __formItems.add(employeeNameEnItem);
        healthItem = new TextItem("health", "身体状况");
        healthItem.setWidth("*");
        __formItems.add(healthItem);

        __form.setItems(getFormItemArray());
        __form.setDataSource(ds);
        __form.setNumCols(4);
        ClientUtil.DynamicFormProcessAccordingToDevice(__form);
        setPageMode(PAGE_MODE_UPDATE);
        setName("基础信息");
        addMember(__form);
    }

    @Override
    public void setValueManage(ValuesManager manager) {
        manager.setDataSource(DSMemployee.getInstance());
        manager.addMember(__form);
    }

    @Override
    public boolean checkData() {
        return true;
    }

    @Override
    public void startEdit() {
        if (getRecord() != null) {
            __form.editRecord(getRecord());
        }
    }

    @Override
    public java.util.Map getValuesAsMap() {
        return __form.getValues();
    }

}
