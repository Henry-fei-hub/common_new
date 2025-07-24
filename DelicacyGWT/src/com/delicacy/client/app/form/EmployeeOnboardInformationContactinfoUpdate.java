package com.delicacy.client.app.form;

import java.util.*;
import java.util.logging.Logger;

import com.smartgwt.client.widgets.form.fields.*;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.*;
import com.delicacy.client.BaseHelpUtils;
import com.delicacy.client.app.datasource.DSEmployeeOnboardInformation;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.ui.AbstractWizadPage;

public class EmployeeOnboardInformationContactinfoUpdate extends AbstractWizadPage
{
	private static final Logger logger = Logger.getLogger("");

	private final TextItem emergencyContactPersonItem;
	private final TextItem emergencyContactPhoneItem;
	private final TextItem nowAddressItem;
	private final TextItem cardAddressItem;
	private HLayout allLayout;
	private VLayout leftLayout;
	private VLayout rightLayout;
	private VLayout contactLayout;
	private VLayout gridLayout;
	private EmployeeDetailEmployeeEducationInformation education;
	private EmployeeDetailEmployeeFamilyInformation family;
	private EmployeeDetailEmployeeRewardExperience reward;
	private EmployeeDetailEmployeeTechnicalTitle technical;
	private EmployeeDetailEmployeeWorkExperience work;
	private String shouldNotBeNull = "<font style=\"color:red;font-weight:bold;font-size:13px;\">＊</font>";

	public EmployeeOnboardInformationContactinfoUpdate() {
		DSEmployeeOnboardInformation ds = DSEmployeeOnboardInformation.getInstance();
		__form.setWidth100();
		__form.setHeight100();
		emergencyContactPersonItem = new TextItem("emergencyContactPerson", shouldNotBeNull+"紧急联系人");
		emergencyContactPersonItem.setWidth("*");
		emergencyContactPersonItem.setRequired(true);
		__formItems.add(emergencyContactPersonItem);
		
		emergencyContactPhoneItem = new TextItem("emergencyContactPhone", shouldNotBeNull+"紧急联系人电话");
		emergencyContactPhoneItem.setWidth("*");
		emergencyContactPhoneItem.setRequired(true);
		__formItems.add(emergencyContactPhoneItem);
		
		cardAddressItem = new TextItem("cardAddress", "身份证地址");
		cardAddressItem.setWidth("*");
		__formItems.add(cardAddressItem);
		
		nowAddressItem = new TextItem("nowAddress", "现居住地址");
		nowAddressItem.setWidth("*");
		__formItems.add(nowAddressItem);

		__form.setItems(getFormItemArray());
		__form.setDataSource(ds);
		__form.setNumCols(2);
		ClientUtil.DynamicFormProcessAccordingToDevice(__form);
		setName("联系信息");
		
		allLayout = new HLayout(10);
		allLayout.setWidth100();
		allLayout.setHeight100();
		
		leftLayout = new VLayout(10);
		leftLayout.setHeight100();
		leftLayout.setWidth("50%");
		
		contactLayout = new VLayout(10);
		contactLayout.setHeight("35%");
		contactLayout.setWidth("90%");
		contactLayout.addMember(__form);
		leftLayout.addMember(contactLayout);
		
		education = new EmployeeDetailEmployeeEducationInformation();
		family = new EmployeeDetailEmployeeFamilyInformation();
		gridLayout = new VLayout(10);
		gridLayout.setHeight("65%");
		gridLayout.setWidth100();
		gridLayout.addMember(education);
		gridLayout.addMember(family);
		leftLayout.addMember(gridLayout);
		allLayout.addMember(leftLayout);
		
		
		reward = new EmployeeDetailEmployeeRewardExperience() ;
		technical = new EmployeeDetailEmployeeTechnicalTitle();
		work = new EmployeeDetailEmployeeWorkExperience();
		rightLayout = new VLayout(10);
		rightLayout.setHeight100();
		rightLayout.setWidth("50%");
		rightLayout.addMember(work);
		rightLayout.addMember(reward);
		rightLayout.addMember(technical);
		allLayout.addMember(rightLayout);
		
		setName("联系信息");
		setPageMode(PAGE_MODE_UPDATE);
		addMember(allLayout);
		
		
	}

	@Override
	public void startEdit() {
		if(getRecord() != null) {
			int employeeId = BaseHelpUtils.getIntValue(getRecord().getAttribute("employeeId"));
			__form.editRecord(getRecord());
			education.setPageMode(PAGE_MODE_UPDATE);
			education.setEmployeeId(employeeId);
			education.startEdit();
			family.setPageMode(PAGE_MODE_UPDATE);
			family.setEmployeeId(employeeId);
			family.startEdit();
			reward.setPageMode(PAGE_MODE_UPDATE);
			reward.setEmployeeId(employeeId);
			reward.startEdit();
			technical.setPageMode(PAGE_MODE_UPDATE);
			technical.setEmployeeId(employeeId);
			technical.startEdit();
			work.setPageMode(PAGE_MODE_UPDATE);
			work.setEmployeeId(employeeId);
			work.startEdit();
		}
	}

	@Override
	public boolean checkData() {
		if(!__form.validate()){
			SC.say("请按系统提示修改信息");
			return false;
		}
		if(education.isTheValuesEmpty()){
			SC.say("学历详细信息不能为空");
			return false;
		}
		return true;
	}

	@Override
	public void setValueManage(ValuesManager manager) {
		manager.setDataSource(DSEmployeeOnboardInformation.getInstance());
		manager.addMember(__form);
	}

	@Override
	public java.util.Map getValuesAsMap() {
		Map values = __form.getValues();
		values.putAll(education.getValuesAsMap());
		values.putAll(family.getValuesAsMap());
		values.putAll(reward.getValuesAsMap());
		values.putAll(technical.getValuesAsMap());
		values.putAll(work.getValuesAsMap());
		return values;
	}


}
