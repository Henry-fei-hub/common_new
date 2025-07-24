package com.delicacy.client.app.datasource;

import com.delicacy.client.data.DBDataSource;
import com.delicacy.client.data.KeyValueManager;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.fields.DataSourceBooleanField;
import com.smartgwt.client.data.fields.DataSourceDateField;
import com.smartgwt.client.data.fields.DataSourceImageField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.FieldType;

public class DSMemployee extends DBDataSource {

    public static DSMemployee instance = null;

    public static DSMemployee getInstance() {
        if (instance == null) {
            instance = new DSMemployee("DSMemployee");
        }
        return instance;
    }

    private final DataSourceIntegerField employeeIdField;
    private final DataSourceImageField photoField;
    private final DataSourceTextField employeeNoField;
    private final DataSourceTextField employeeNameField;
    private final DataSourceIntegerField departmentIdField;
    private final DataSourceIntegerField roleIdField;
    private final DataSourceTextField gradeIdField;
    private final DataSourceTextField employeePasswordField;
    private final DataSourceTextField mobileField;
    private final DataSourceTextField phoneField;
    private final DataSourceTextField qqField;
    private final DataSourceTextField emailField;
    private final DataSourceDateField onboardDateField;
    private final DataSourceDateField resignationDateField;
    private final DataSourceIntegerField statusField;
    private final DataSourceBooleanField usableStatusField;
    private final DataSourceBooleanField isDepartmentField;
    private final DataSourceIntegerField genderField;
    private final DataSourceTextField autographField;
    private final DataSourceIntegerField ageField;
    private final DataSourceDateField birthField;
    private final DataSourceTextField cardField;
    private final DataSourceTextField addressField;
    private final DataSourceBooleanField lockedField;
    private final DataSourceIntegerField plateIdField;
    private final DataSourceIntegerField dutyIdField;
    private final DataSourceTextField userAcctField;
    private final DataSourceTextField employeeNameEnField;
    private final DataSourceTextField educationField;
    private final DataSourceTextField degreeField;
    private final DataSourceTextField nationalityField;
    private final DataSourceTextField marriedStatusField;
    private final DataSourceTextField healthField;
    private final DataSourceTextField workAddressField;
    private final DataSourceIntegerField ownedCompanyField;
    private final DataSourceTextField registeredAddressField;
    private final DataSourceTextField companyAddressField;
    private final DataSourceTextField companyPhoneField;
    private final DataSourceTextField zipCodeField;
    private final DataSourceTextField employeeRoleNamesField;
    private final DataSourceField detailEmployeeRole;

    public DSMemployee(String dataSourceID) {

        super();
        setID(dataSourceID);
        setActionMode(ACTION_MODE_QUERY);
        setActionName("MemployeeTow");

        employeeIdField = new DataSourceIntegerField("employeeId", "考勤编码");
        employeeIdField.setLength(11);
        employeeIdField.setRequired(false);
        employeeIdField.setHidden(false);
//        employeeIdField.setValueMap(KeyValueManager.getValueMap("employees"));

        photoField = new DataSourceImageField("photo", "头像");
        photoField.setLength(512);
        photoField.setRequired(false);
        photoField.setHidden(false);
        photoField.setImageHeight(60);
        photoField.setImageWidth(60);

        employeeNoField = new DataSourceTextField("employeeNo", "员工编号");
        employeeNoField.setLength(64);
        employeeNoField.setRequired(true);
        employeeNoField.setHidden(false);

        employeeNameField = new DataSourceTextField("employeeName", "员工姓名");
        employeeNameField.setLength(64);
        employeeNameField.setRequired(true);
        employeeNameField.setHidden(false);
        
        userAcctField = new DataSourceTextField("userAcct", "上级领导");
        userAcctField.setLength(64);
        userAcctField.setRequired(false);
        userAcctField.setHidden(false);
        
        plateIdField = new DataSourceIntegerField("plateId", "业务部门");
        plateIdField.setLength(11);
        plateIdField.setRequired(false);
        plateIdField.setHidden(false);
        plateIdField.setValueMap(KeyValueManager.getValueMap("system_dictionary_1"));

        departmentIdField = new DataSourceIntegerField("departmentId", "归属部门");
        departmentIdField.setLength(11);
        departmentIdField.setRequired(false);
        departmentIdField.setHidden(false);
        departmentIdField.setValueMap(KeyValueManager.getValueMap("departments"));
        
        employeeRoleNamesField = new DataSourceTextField("employeeRoleNames", "角色");
        employeeRoleNamesField.setLength(64);
        employeeRoleNamesField.setRequired(true);
        employeeRoleNamesField.setHidden(true);

        roleIdField = new DataSourceIntegerField("roleId", "角色编码");
        roleIdField.setLength(11);
        roleIdField.setRequired(false);
        roleIdField.setHidden(true);
//        roleIdField.setValueMap(KeyValueManager.getValueMap("roles"));
        KeyValueManager.loadValueMap("roles",roleIdField);

        gradeIdField = new DataSourceTextField("gradeId", "职级");
        gradeIdField.setLength(11);
        gradeIdField.setRequired(false);
        gradeIdField.setHidden(false);
        gradeIdField.setValueMap(KeyValueManager.getValueMap("system_dictionary_3"));
        
        statusField = new DataSourceIntegerField("status", "状态");
        statusField.setLength(11);
        statusField.setRequired(false);
        statusField.setHidden(false);
        statusField.setValueMap(KeyValueManager.getValueMap("system_dictionary_11"));
        
        genderField = new DataSourceIntegerField("gender", "性别");
        genderField.setLength(11);
        genderField.setRequired(false);
        genderField.setHidden(false);
        genderField.setValueMap(KeyValueManager.getValueMap("system_dictionary_12"));

        employeePasswordField = new DataSourceTextField("employeePassword", "密码");
        employeePasswordField.setLength(64);
        employeePasswordField.setRequired(false);
        employeePasswordField.setHidden(true);

        mobileField = new DataSourceTextField("mobile", "手机");
        mobileField.setLength(64);
        mobileField.setRequired(false);
        mobileField.setHidden(true);

        phoneField = new DataSourceTextField("phone", "电话");
        phoneField.setLength(64);
        phoneField.setRequired(false);
        phoneField.setHidden(true);

        qqField = new DataSourceTextField("qq", "QQ");
        qqField.setLength(64);
        qqField.setRequired(false);
        qqField.setHidden(true);

        emailField = new DataSourceTextField("email", "邮箱");
        emailField.setLength(64);
        emailField.setRequired(false);
        emailField.setHidden(true);

        onboardDateField = new DataSourceDateField("onboardDate", "入职日期");
        onboardDateField.setRequired(false);
        onboardDateField.setHidden(true);

        resignationDateField = new DataSourceDateField("resignationDate", "离职日期");
        resignationDateField.setRequired(false);
        resignationDateField.setHidden(true);

        usableStatusField = new DataSourceBooleanField("usableStatus", "是否可用");
        usableStatusField.setRequired(false);
        usableStatusField.setHidden(true);

        isDepartmentField = new DataSourceBooleanField("isDepartment", "是否为部门负责人");
        isDepartmentField.setRequired(false);
        isDepartmentField.setHidden(true);

        autographField = new DataSourceTextField("autograph", "我的签名");
        autographField.setLength(512);
        autographField.setRequired(false);
        autographField.setHidden(true);

        ageField = new DataSourceIntegerField("age", "age");
        ageField.setLength(11);
        ageField.setRequired(false);
        ageField.setHidden(true);

        birthField = new DataSourceDateField("birth", "出生日期");
        birthField.setRequired(false);
        birthField.setHidden(true);

        cardField = new DataSourceTextField("card", "身份证号");
        cardField.setLength(64);
        cardField.setRequired(false);
        cardField.setHidden(true);

        addressField = new DataSourceTextField("address", "家庭地址");
        addressField.setLength(512);
        addressField.setRequired(false);
        addressField.setHidden(true);

        lockedField = new DataSourceBooleanField("locked", "是否锁定");
        lockedField.setRequired(false);
        lockedField.setHidden(true);

        dutyIdField = new DataSourceIntegerField("dutyId", "职位名称");
        dutyIdField.setLength(11);
        dutyIdField.setRequired(false);
        dutyIdField.setHidden(false);
        dutyIdField.setValueMap(KeyValueManager.getValueMap("duties"));

        employeeNameEnField = new DataSourceTextField("employeeNameEn", "英文名");
        employeeNameEnField.setLength(64);
        employeeNameEnField.setRequired(false);
        employeeNameEnField.setHidden(true);

        educationField = new DataSourceTextField("education", "学历");
        educationField.setLength(64);
        educationField.setRequired(false);
        educationField.setHidden(true);

        degreeField = new DataSourceTextField("degree", "学位");
        degreeField.setLength(64);
        degreeField.setRequired(false);
        degreeField.setHidden(true);

        nationalityField = new DataSourceTextField("nationality", "民族");
        nationalityField.setLength(64);
        nationalityField.setRequired(false);
        nationalityField.setHidden(true);

        marriedStatusField = new DataSourceTextField("marriedStatus", "婚姻状况");
        marriedStatusField.setLength(64);
        marriedStatusField.setRequired(false);
        marriedStatusField.setHidden(true);

        healthField = new DataSourceTextField("health", "身体状况");
        healthField.setLength(64);
        healthField.setRequired(false);
        healthField.setHidden(true);

        workAddressField = new DataSourceTextField("workAddress", "工作地");
        workAddressField.setLength(128);
        workAddressField.setRequired(false);
        workAddressField.setHidden(true);

        registeredAddressField = new DataSourceTextField("registeredAddress", "注册地");
        registeredAddressField.setLength(128);
        registeredAddressField.setRequired(false);
        registeredAddressField.setHidden(true);

        ownedCompanyField = new DataSourceIntegerField("ownedCompany", "公司编号");
        ownedCompanyField.setLength(128);
        ownedCompanyField.setRequired(false);
        ownedCompanyField.setHidden(true);
        ownedCompanyField.setValueMap(KeyValueManager.getValueMap("company_records"));

        companyAddressField = new DataSourceTextField("companyAddress", "公司地址");
        companyAddressField.setLength(128);
        companyAddressField.setRequired(false);
        companyAddressField.setHidden(true);

        companyPhoneField = new DataSourceTextField("companyPhone", "公司电话");
        companyPhoneField.setLength(128);
        companyPhoneField.setRequired(false);
        companyPhoneField.setHidden(true);

        zipCodeField = new DataSourceTextField("zipCode", "邮编");
        zipCodeField.setLength(128);
        zipCodeField.setRequired(false);
        zipCodeField.setHidden(true);

        detailEmployeeRole = new DataSourceField("detailEmployeeRole", FieldType.ANY);
        detailEmployeeRole.setChildrenProperty(true);
        detailEmployeeRole.setChildTagName("EmployeeRole");
        detailEmployeeRole.setRequired(false);
        detailEmployeeRole.setHidden(true);

        setFields( photoField,employeeIdField, employeeNoField, employeeNameField,userAcctField,plateIdField, departmentIdField, dutyIdField, employeeRoleNamesField, roleIdField, gradeIdField, statusField,genderField,employeePasswordField, mobileField, phoneField, qqField, emailField, onboardDateField, resignationDateField,  usableStatusField, isDepartmentField, autographField, ageField, birthField, cardField, addressField, lockedField, employeeNameEnField, educationField, degreeField, nationalityField, marriedStatusField, healthField, workAddressField, registeredAddressField,companyAddressField,ownedCompanyField,companyPhoneField,zipCodeField,  detailEmployeeRole);
    }

}
