package com.delicacy.client.app.form;

import java.util.LinkedHashMap;
import java.util.logging.Logger;

import com.delicacy.client.BaseHelpUtils;
import com.delicacy.client.BasicPermissionStatic;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.data.KeyValueManager;
import com.smartgwt.client.types.DateItemSelectorFormat;
import com.smartgwt.client.types.TextMatchStyle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.SearchForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.IPickTreeItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

public class MemployeeSearchForm extends SearchForm {
	private final Logger __logger = Logger.getLogger("");
	private final ComboBoxItem plateIdItem;
	private final TextItem employeeNoItem;
	private final TextItem employeeNameItem;
	private final IPickTreeItem departmentIdItem;
	private final ComboBoxItem roleIdItem;
	private final ComboBoxItem gradeIdItem;
	private final ComboBoxItem statusItem;
	private final CheckboxItem isDepartmentItem;
	private final ComboBoxItem genderItem;
	private final ComboBoxItem dutyIdItem;
	private final DateItem onboardDateItem;
	private final DateItem resignationDateItem;
	private final SelectItem onboardYearItem;
	private final SelectItem onboardMonthItem;
	private final SelectItem resiYearItem;
	private final SelectItem resiMonthItem;
	private final SelectItem birthMonthItem;
	private final SelectItem isCheckItem;

	private final TextItem mobileItem;
	private final TextItem emailItem;
	private final TextItem cardItem;
	private final TextItem addressItem;
	private final TextItem educationItem;
	private final TextItem degreeItem;
	private final TextItem nationalityItem;
	private final TextItem marriedStatusItem;
	private final TextItem workaddressItem;
	private final TextItem birthplaceItem;
	private final TextItem accountLocationItem;
	private final TextItem startWorkYearItem;
	private final TextItem socialComputerNumberItem;
	private final TextItem fundAccountItem;
	private final DateItem positiveDateItem;
	private final TextItem trytimeItem;
	private final TextItem contractStartDateItem;
	private final TextItem contractStartMonthItem;
	private final TextItem contractEndYearItem;
	private final TextItem contractEndMonthItem;
	private final TextItem ownedCompanyItem;
	private final TextItem bankCardNumItem;
	private final TextItem companyWeixinItem;
	private final TextItem companyEmailItem;

	public MemployeeSearchForm() {
		setWidth100();
		setHeight100();
		employeeNoItem = new TextItem("employeeNo", "员工编号");
		employeeNoItem.setWidth("*");
		employeeNameItem = new TextItem("employeeName", "员工姓名");
		employeeNameItem.setWidth("*");
		plateIdItem = new ComboBoxItem("plateId", "业务部门");
		plateIdItem.setValueMap(KeyValueManager.getValueMap("system_dictionary_1"));
		plateIdItem.setWidth("*");
		departmentIdItem = new IPickTreeItem("departmentId", "部门");
		departmentIdItem.setWidth("*");
		departmentIdItem.setCanSelectParentItems(true);
		departmentIdItem.setValueTree(KeyValueManager.getTree("departments"));
		departmentIdItem.setValueField("treeId");
		// 控制，页面刚加载时，如果员工有自己所在部门的数据查看权限，则加载该部门数据，否则，加载其所能看到的第一个部门的数据
		if (!ClientUtil.checkIsHavePermission(BasicPermissionStatic.EMPLOYEE_CHECK_ALL_DATA)) {
			String departmentIds = ClientUtil.getRoleDepartmentId();
			String departmentId = ClientUtil.getDepartmentId() + "";
			final String[] ids = departmentIds.split(",");
			boolean status = false;
			if(BaseHelpUtils.isNullOrEmpty(departmentIds)){//说明没有设置管理部门的权限
				departmentIdItem.setDefaultValue(departmentId);
			}else{
				for (String str : ids) {
					if (str.equals(departmentId)) {
						status = true;
					}
				}
				if (status) {
					departmentIdItem.setDefaultValue(departmentId);
				} else {
					departmentIdItem.setDefaultValue(ids[0]);
				}
			}
			departmentIdItem.addChangeHandler(new ChangeHandler() {
				
				@Override
				public void onChange(ChangeEvent event) {
					if (null != event.getValue()) {
						String[] arr = event.getValue().toString().split("/");
						String value = arr[arr.length - 1];
						boolean status = false;
						for (String str : ids) {
							if (str.equals(value)) {
								status = true;
							}
						}
						if (!status) {
							SC.say("对不起，您没有查看这个部门数据的权限");
							event.cancel();
						}
					}						
				}
			});
		}
		roleIdItem = new ComboBoxItem("roleId", "角色");
		roleIdItem.setWidth("*");
//		roleIdItem.setValueMap(KeyValueManager.getValueMap("roles"));
		KeyValueManager.loadValueMap("roles",roleIdItem);
		gradeIdItem = new ComboBoxItem("gradeId", "职级");
		gradeIdItem.setWidth("*");
		gradeIdItem.setValueMap(KeyValueManager.getValueMap("system_dictionary_3"));
		statusItem = new ComboBoxItem("status", "状态");
		statusItem.setDefaultValue(0);// 默认显示在职的
		statusItem.setWidth("*");
		statusItem.setValueMap(KeyValueManager.getValueMap("system_dictionary_11"));
		isDepartmentItem = new CheckboxItem("isDepartment", "是否为部门负责人");
		isDepartmentItem.hide();
		genderItem = new ComboBoxItem("gender", "性别");
		genderItem.setWidth("*");
		genderItem.setValueMap(KeyValueManager.getValueMap("system_dictionary_12"));
		dutyIdItem = new ComboBoxItem("dutyId", "职位名称");
		dutyIdItem.setWidth("*");
		dutyIdItem.setChangeOnKeypress(false);
		dutyIdItem.setTextMatchStyle(TextMatchStyle.SUBSTRING);
		dutyIdItem.setValueMap(KeyValueManager.getValueMap("duties"));
		onboardDateItem = new DateItem("onboardDate","入职日期");
		onboardDateItem.setUseTextField(true);
		onboardDateItem.setSelectorFormat(DateItemSelectorFormat.YEAR_MONTH);
		onboardDateItem.setWidth("*");
		onboardDateItem.setHidden(true);
		
		resignationDateItem = new DateItem("resignationDate","离职日期");
		resignationDateItem.setUseTextField(true);
		resignationDateItem.setSelectorFormat(DateItemSelectorFormat.YEAR_MONTH);
		resignationDateItem.setWidth("*");
		resignationDateItem.setHidden(true);
		
		onboardYearItem = new SelectItem("onboardYear","入职年份");
		onboardYearItem.setWidth("*");
		onboardYearItem.setValueMap(KeyValueManager.getValueMap("system_dictionary_18"));
		
		onboardMonthItem = new SelectItem("onboardMonth","入职月份");
		onboardMonthItem.setWidth("*");
		onboardMonthItem.setValueMap(KeyValueManager.getValueMap("system_dictionary_19"));
		
		resiYearItem = new SelectItem("resiYear","离职年份");
		resiYearItem.setWidth("*");
		resiYearItem.setValueMap(KeyValueManager.getValueMap("system_dictionary_18"));
		
		resiMonthItem = new SelectItem("resiMonth","离职月份");
		resiMonthItem.setWidth("*");
		resiMonthItem.setValueMap(KeyValueManager.getValueMap("system_dictionary_19"));
		
		birthMonthItem = new SelectItem("birthMonth","出生月份");
		birthMonthItem.setWidth("*");
		birthMonthItem.setValueMap(KeyValueManager.getValueMap("system_dictionary_19"));
		
		isCheckItem = new SelectItem("isCheck","参与考勤");
		isCheckItem.setAllowEmptyValue(true);
		LinkedHashMap<Integer,String> map = new LinkedHashMap<>();
		map.put(1,"是");
		map.put(2,"否");
		isCheckItem.setValueMap(map);
		isCheckItem.setWidth("*");

		mobileItem = new TextItem("mobile", "手机");
		mobileItem.setHidden(false);
		mobileItem.setWidth("*");

		emailItem = new TextItem("email", "邮箱");
		emailItem.setHidden(true);
		emailItem.setWidth("*");

		cardItem = new TextItem("card", "身份证号");
		cardItem.setHidden(true);
		cardItem.setWidth("*");

		addressItem = new TextItem("address", "家庭地址");
		addressItem.setHidden(true);
		addressItem.setWidth("*");

		educationItem = new TextItem("education", "学历");
		educationItem.setHidden(true);
		educationItem.setWidth("*");

		degreeItem = new TextItem("degree", "学位");
		degreeItem.setHidden(true);
		degreeItem.setWidth("*");

		nationalityItem = new TextItem("nationality", "民族");
		nationalityItem.setHidden(true);
		nationalityItem.setWidth("*");

		marriedStatusItem = new TextItem("marriedStatus", "婚姻状况");
		marriedStatusItem.setHidden(true);
		marriedStatusItem.setWidth("*");

		workaddressItem = new TextItem("workaddress", "工作地");
		workaddressItem.setHidden(true);
		workaddressItem.setWidth("*");

		birthplaceItem = new TextItem("birthplace", "籍贯");
		birthplaceItem.setHidden(true);
		birthplaceItem.setWidth("*");

		accountLocationItem = new TextItem("accountLocation", "户口所在地");
		accountLocationItem.setHidden(true);
		accountLocationItem.setWidth("*");

		startWorkYearItem = new TextItem("startWorkYear", "undefined");
		startWorkYearItem.setHidden(true);
		startWorkYearItem.setWidth("*");

		socialComputerNumberItem = new TextItem("socialComputerNumber", "社保电脑号");
		socialComputerNumberItem.setHidden(true);
		socialComputerNumberItem.setWidth("*");

		fundAccountItem = new TextItem("fundAccount", "公积金账号");
		fundAccountItem.setHidden(true);
		fundAccountItem.setWidth("*");

		positiveDateItem = new DateItem("positiveDate", "转正日期");
		positiveDateItem.setHidden(true);
		positiveDateItem.setWidth("*");

		trytimeItem = new TextItem("trytime", "试用期");
		trytimeItem.setHidden(true);
		trytimeItem.setWidth("*");

		contractStartDateItem = new TextItem("contractStartDate", "undefined");
		contractStartDateItem.setHidden(true);
		contractStartDateItem.setWidth("*");

		contractStartMonthItem = new TextItem("contractStartMonth", "undefined");
		contractStartMonthItem.setHidden(true);
		contractStartMonthItem.setWidth("*");

		contractEndYearItem = new TextItem("contractEndYear", "undefined");
		contractEndYearItem.setHidden(true);
		contractEndYearItem.setWidth("*");

		contractEndMonthItem = new TextItem("contractEndMonth", "undefined");
		contractEndMonthItem.setHidden(true);
		contractEndMonthItem.setWidth("*");

		ownedCompanyItem = new TextItem("ownedCompany", "所属公司");
		ownedCompanyItem.setHidden(true);
		ownedCompanyItem.setWidth("*");

		bankCardNumItem = new TextItem("bankCardNum", "银行卡号");
		bankCardNumItem.setHidden(true);
		bankCardNumItem.setWidth("*");

		companyWeixinItem = new TextItem("companyWeixin", "企业微信账号");
		companyWeixinItem.setHidden(true);
		companyWeixinItem.setWidth("*");

		companyEmailItem = new TextItem("companyEmail", "企业邮箱");
		companyEmailItem.setHidden(true);
		companyEmailItem.setWidth("*");


		
		setItems(employeeNoItem, employeeNameItem, plateIdItem, departmentIdItem, roleIdItem, gradeIdItem, statusItem,
				isDepartmentItem, genderItem, dutyIdItem,onboardDateItem,resignationDateItem,onboardYearItem,onboardMonthItem,resiYearItem,resiMonthItem,birthMonthItem,isCheckItem,mobileItem, emailItem, cardItem, addressItem, educationItem, degreeItem, nationalityItem, marriedStatusItem, workaddressItem, birthplaceItem, accountLocationItem, startWorkYearItem, socialComputerNumberItem, fundAccountItem, positiveDateItem, trytimeItem, contractStartDateItem, contractStartMonthItem, contractEndYearItem, contractEndMonthItem, ownedCompanyItem, bankCardNumItem, companyWeixinItem, companyEmailItem);
		setNumCols(10);
		ClientUtil.searchFormProcessAccordingToDevice(this);
		
	}

}
