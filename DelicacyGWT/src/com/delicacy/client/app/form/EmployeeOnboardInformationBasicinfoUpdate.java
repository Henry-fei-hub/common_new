package com.delicacy.client.app.form;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import com.delicacy.client.BaseHelpUtils;
import com.delicacy.client.ValidateUtils;
import com.delicacy.client.app.datasource.DSEmployeeOnboardInformation;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.data.DBDataSource;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.ui.AbstractWizadPage;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.TextMatchStyle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.ValuesManager;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.IPickTreeItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.EditorExitEvent;
import com.smartgwt.client.widgets.form.fields.events.EditorExitHandler;
import com.smartgwt.client.widgets.form.validator.IsIntegerValidator;
import com.smartgwt.client.widgets.form.validator.IsStringValidator;
import com.smartgwt.client.widgets.form.validator.LengthRangeValidator;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class EmployeeOnboardInformationBasicinfoUpdate extends AbstractWizadPage
{


	private  SelectItem employeeIdItem;
	private  TextItem employeeNoItem;
	private  TextItem employeeNameItem;
	private  TextItem employeeNameEnItem;
	private  TextItem cardItem;
	private  SelectItem genderItem;
	private  DateItem birthItem;
	private  SelectItem politicalFaceItem;
	private  TextItem birthplaceItem;
	private  SelectItem marriedStatusItem;
	private  TextItem healthItem;
	private  TextItem countryItem;
	private  TextItem accountLocationItem;
	private  TextItem languagesItem;
	private  DateItem startWorkDateItem;
	private  ComboBoxItem nationalityItem;
	private  TextItem socialComputerNumberItem;
	private  TextItem fundAccountItem;
	private  TextItem bankCardNumItem;
	private  SelectItem householdRegistrationItem;
	private  TextItem fileNumberItem;
	private  TextItem foremanItem;
	private  TextItem ageItem;
	private  DateItem onboardDateItem;
	private  DateItem positiveDateItem;
	private  TextItem tryTimeItem;
	private  TextItem workAddressItem;
	private  SelectItem statusItem;
	private  DateItem contractStartDateItem;
	private  DateItem contractEndDateItem;
	private  SelectItem ownedCompanyItem;
	private  IPickTreeItem departmentIdItem;
	private  ComboBoxItem dutyIdItem;
	private  ComboBoxItem gradeIdItem;
	private  TextItem jobsItem;
	private  TextItem selfIntroductionItem;
	private  TextItem personalBusinessRemarkItem;
	private  CheckboxItem isCheckItem;
	private  TextItem phoneItem;
	private  TextItem mobileItem;
	private  TextItem emailItem;
	private  TextItem homePhoneItem;
	private  SelectItem educationItem;
	private  SelectItem degreeItem;
	private  SelectItem employeeShiftItem;
	private DateItem resignationDateItem;
	private ComboBoxItem userAcctItem;
	private SelectItem plateIdItem;
	private SelectItem roleIdItem;
	private SelectItem workYearTypeItem;
	private CheckboxItem isLongSickLeaveItem;
	private HLayout allLayout;
	private VLayout leftLayout;
	private VLayout rightLayou;
	private VLayout attachmentsLayou;
	private VLayout payLayou;
	private EmployeeOnboardInformationEmployeeattachmentUpdate attachment;
	private EmployeeOnboardInformationPayinfoUpdate pay;
	private String shouldNotBeNull = "<font style=\"color:red;font-weight:bold;font-size:13px;\">＊</font>";
	
	private static final Logger __logger = Logger.getLogger("");
	
	public EmployeeOnboardInformationBasicinfoUpdate(){}
	
	public EmployeeOnboardInformationBasicinfoUpdate(int operatorId) {
		DSEmployeeOnboardInformation ds = DSEmployeeOnboardInformation.getInstance();
		__form.setWidth100();
		__form.setHeight100();
		
		setOperatorId(operatorId);
		
		employeeIdItem = new SelectItem("employeeId", "员工编码");
//		employeeIdItem.setValueMap(KeyValueManager.getValueMap("employees"));
		KeyValueManager.loadValueMap("employees", employeeIdItem);
		employeeIdItem.setDisabled(true);
		employeeIdItem.setRequired(true);
		employeeIdItem.setHidden(true);
		IsIntegerValidator employeeIdValidator = new IsIntegerValidator();
		employeeIdItem.setValidators(employeeIdValidator);
		__formItems.add(employeeIdItem);
		
		employeeNoItem = new TextItem("employeeNo", shouldNotBeNull + "员工编号");
		employeeNoItem.setRequired(true);
		employeeNoItem.setDisabled(true);
		IsStringValidator employeeNoValidator = new IsStringValidator();
		LengthRangeValidator employeeNoLengthValidator = new LengthRangeValidator();
		employeeNoLengthValidator.setMax(64);
		employeeNoItem.setValidators(employeeNoValidator, employeeNoLengthValidator);
		employeeNoItem.setWidth("*");
		__formItems.add(employeeNoItem);
		
		employeeNameItem = new TextItem("employeeName", shouldNotBeNull + "员工姓名");
		employeeNameItem.setRequired(true);
		IsStringValidator employeeNameValidator = new IsStringValidator();
		LengthRangeValidator employeeNameLengthValidator = new LengthRangeValidator();
		employeeNameLengthValidator.setMax(64);
		employeeNameItem.setValidators(employeeNameValidator, employeeNameLengthValidator);
		employeeNameItem.setWidth("*");
		__formItems.add(employeeNameItem);
		
		employeeNameEnItem = new TextItem("employeeNameEn", "英文名");
		employeeNameEnItem.setWidth("*");
		__formItems.add(employeeNameEnItem);
		
		fileNumberItem = new TextItem("fileNumber", shouldNotBeNull + "档案号");
		fileNumberItem.setWidth("*");
		fileNumberItem.setRequired(true);
		__formItems.add(fileNumberItem);
		
		cardItem = new TextItem("card", shouldNotBeNull + "身份证号");
		cardItem.setWidth("*");
		__formItems.add(cardItem);
		cardItem.addEditorExitHandler(new EditorExitHandler() {
			
			@Override
			public void onEditorExit(EditorExitEvent event) {
				Map<String,Object> params = new HashMap<>();
				params.put("optType", "onIdValidate");
				params.put("id", cardItem.getValue());
				DBDataSource.callOperation("EP_OnHrManageProcess", params, new DSCallback() {
					
					@Override
					public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
						if(dsResponse.getStatus() >= 0){
							Record record = dsResponse.getData()[0];
							Date birthDate = record.getAttributeAsDate("birth");
							birthItem.setValue(birthDate);
							ageItem.setValue(record.getAttribute("age"));
						}else{
							SC.say("提示",dsResponse.getErrors().get("errorMsg").toString());
						}
					}
				});
			}
		});
		
		birthItem = new DateItem("birth", "出生日期");
		birthItem.setWidth("*");
		birthItem.setUseTextField(true);
		birthItem.setUseMask(true);
		__formItems.add(birthItem);
		
		ageItem = new TextItem("age", "年龄");
		ageItem.setWidth("*");
		__formItems.add(ageItem);
		
		genderItem = new SelectItem("gender", shouldNotBeNull + "性别");
		genderItem.setWidth("*");
		genderItem.setRequired(true);
		genderItem.setValueMap(KeyValueManager.getValueMap("system_dictionary_12"));
		__formItems.add(genderItem);
		
		phoneItem = new TextItem("phone", "办公电话");
		phoneItem.setWidth("*");
		__formItems.add(phoneItem);
		
		mobileItem = new TextItem("mobile", shouldNotBeNull + "手机号码");
		mobileItem.setWidth("*");
		mobileItem.setRequired(true);
     	mobileItem.setValidators(ValidateUtils.mobileValidator());
		__formItems.add(mobileItem);
		
		emailItem = new TextItem("companyEmail", "企业邮箱");
		emailItem.setWidth("*");
		emailItem.setDisabled(true);
		__formItems.add(emailItem);
		
		homePhoneItem = new TextItem("homePhone", "家庭电话");
		homePhoneItem.setWidth("*");
		__formItems.add(homePhoneItem);
		
		countryItem = new TextItem("country", "国籍");
		countryItem.setWidth("*");
		__formItems.add(countryItem);
		
		birthplaceItem = new TextItem("birthplace", "籍贯");
		birthplaceItem.setWidth("*");
		__formItems.add(birthplaceItem);
		
		accountLocationItem = new TextItem("accountLocation", shouldNotBeNull + "户口所在地");
		accountLocationItem.setWidth("*");
		accountLocationItem.setRequired(true);
		__formItems.add(accountLocationItem);
		
		householdRegistrationItem = new SelectItem("householdRegistration",shouldNotBeNull + "户籍");
		householdRegistrationItem.setWidth("*");
		householdRegistrationItem.setRequired(true);
		householdRegistrationItem.setValueMap(KeyValueManager.getValueMap("system_dictionary_81"));;
		__formItems.add(householdRegistrationItem);
		
		nationalityItem = new ComboBoxItem("nationality", shouldNotBeNull + "民族");
		nationalityItem.setValueMap(KeyValueManager.getValueMap("nationalities"));
		nationalityItem.setWidth("*");
		nationalityItem.setRequired(true);
		nationalityItem.setTextMatchStyle(TextMatchStyle.SUBSTRING);
		__formItems.add(nationalityItem);

		marriedStatusItem = new SelectItem("marriedStatus",shouldNotBeNull + "婚姻状况");
		marriedStatusItem.setValueMap(KeyValueManager.getValueMap("system_dictionary_77"));
		marriedStatusItem.setWidth("*");
		marriedStatusItem.setRequired(true);
		__formItems.add(marriedStatusItem);

		healthItem = new TextItem("health", "健康状况");
		healthItem.setWidth("*");
		__formItems.add(healthItem);
		
		politicalFaceItem = new SelectItem("politicalFace", "政治面貌");
		politicalFaceItem.setValueMap(KeyValueManager.getValueMap("system_dictionary_76"));
		politicalFaceItem.setWidth("*");
		__formItems.add(politicalFaceItem);
		
		educationItem = new SelectItem("education","学历");
		educationItem.setWidth("*");
		educationItem.setValueMap(KeyValueManager.getValueMap("system_dictionary_80"));
		__formItems.add(educationItem);
		
		degreeItem = new SelectItem("degree","学位");
		degreeItem.setWidth("*");
		degreeItem.setValueMap(KeyValueManager.getValueMap("system_dictionary_78"));
		__formItems.add(degreeItem);
		
		startWorkDateItem = new DateItem("startWorkDate", "参加工作时间");
		startWorkDateItem.setWidth("*");
		startWorkDateItem.setUseTextField(true);
		startWorkDateItem.setUseMask(true);
		__formItems.add(startWorkDateItem);
		
		languagesItem = new TextItem("languages", "外语语种");
		languagesItem.setWidth("*");
		__formItems.add(languagesItem);
		
		onboardDateItem = new DateItem("onboardDate",shouldNotBeNull + "入职日期");
		onboardDateItem.setWidth("*");
		onboardDateItem.setUseTextField(true);
		onboardDateItem.setUseMask(true);
		onboardDateItem.setRequired(true);
		__formItems.add(onboardDateItem);
		onboardDateItem.addChangedHandler(new ChangedHandler() {
			
			@Override
			public void onChanged(ChangedEvent event) {
				Date onboardDate = (Date) onboardDateItem.getValue();
				Date positiveDate = (Date) positiveDateItem.getValue();
				if(null != positiveDate){
					if(onboardDateItem.getValueAsDate().after(positiveDateItem.getValueAsDate())){
						SC.say("入职日期必须小于转正日期");
						onboardDateItem.setValue("");
					}else{
						long days = (((positiveDate.getTime() - onboardDate.getTime())/(24*60*60*1000))%365)/30;
						tryTimeItem.setValue(days+"  个月");	
					}
				}
			}
		});
		
		positiveDateItem = new DateItem("positiveDate", shouldNotBeNull + "转正日期");
		positiveDateItem.setWidth("*");
		positiveDateItem.setRequired(true);
		positiveDateItem.setUseTextField(true);
		positiveDateItem.setUseMask(true);
		__formItems.add(positiveDateItem);
		positiveDateItem.addChangedHandler(new ChangedHandler() {
			
			@Override
			public void onChanged(ChangedEvent event) {
				Date onboardDate = (Date) onboardDateItem.getValue();
				Date positiveDate = (Date) positiveDateItem.getValue();
				if(null != onboardDate){
					if(onboardDateItem.getValueAsDate().after(positiveDateItem.getValueAsDate())){
						SC.say("转正日期必须大于入职日期");
						positiveDateItem.setValue("");
					}else{
						long days = (((positiveDate.getTime() - onboardDate.getTime())/(24*60*60*1000))%365)/30;
						tryTimeItem.setValue(days+"  个月");	
					}	
				}
			}
		});
		
		tryTimeItem = new TextItem("tryTime", "试用期");
		tryTimeItem.setWidth("*");
		__formItems.add(tryTimeItem);
		
		foremanItem = new TextItem("foreman", "司龄");
		foremanItem.setWidth("*");
		__formItems.add(foremanItem);
		if(null != onboardDateItem.getValue()){
			Date today = new Date();
			Date onboardDate = (Date) onboardDateItem.getValue();
			long year = (today.getTime() - onboardDate.getTime())/(24*60*60*1000*365);
			long month = (((today.getTime() - onboardDate.getTime())/(24*60*60*1000))%365)/30;
			if(year > 0){
				foremanItem.setValue(year+ " 年" + month + " 个月");
			}else{
				foremanItem.setValue(month + " 个月");
			}
		}

		workAddressItem = new TextItem("workAddress", "工作地点");
		workAddressItem.setWidth("*");
		__formItems.add(workAddressItem);
		
		statusItem = new SelectItem("onboardStatus", "人事状态");
		statusItem.setValueMap(KeyValueManager.getValueMap("system_dictionary_96"));
		statusItem.setWidth("*");
		__formItems.add(statusItem);
		
		contractStartDateItem = new DateItem("contractStartDate", shouldNotBeNull + "劳动合同开始时间");
		contractStartDateItem.setWidth("*");
		contractStartDateItem.setRequired(true);
		contractStartDateItem.setUseTextField(true);
		contractStartDateItem.setUseMask(true);
		__formItems.add(contractStartDateItem);
		
		contractEndDateItem = new DateItem("contractEndDate", shouldNotBeNull + "劳动合同到期时间");
		contractEndDateItem.setWidth("*");
		contractEndDateItem.setRequired(true);
		contractEndDateItem.setUseTextField(true);
		contractEndDateItem.setUseMask(true);
		__formItems.add(contractEndDateItem);

		ownedCompanyItem = new SelectItem("ownedCompany", shouldNotBeNull + "所属公司");
		ownedCompanyItem.setValueMap(KeyValueManager.getValueMap("company_records"));
		ownedCompanyItem.setWidth("*");
		ownedCompanyItem.setRequired(true);
		__formItems.add(ownedCompanyItem);
		
		departmentIdItem = new IPickTreeItem("departmentId", shouldNotBeNull + "所属部门");
		departmentIdItem.setValueField("treeId");
		departmentIdItem.setRequired(true);
		departmentIdItem.setCanSelectParentItems(true);
		departmentIdItem.setValueTree(KeyValueManager.getTree("departments"));
		departmentIdItem.setWidth("*");
		__formItems.add(departmentIdItem);
		departmentIdItem.addChangedHandler(new ChangedHandler() {
			@Override
			public void onChanged(ChangedEvent event) {
				Map<String,Object> param = new HashMap<>();
				int departmentId = BaseHelpUtils.getIntValue(departmentIdItem.getValue());
				param.put("departmentId",departmentId);
				DBDataSource.callOperation("NQ_DepartmentIdFindPlate", param, new DSCallback() {
					@Override
					public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
						if(dsResponse.getStatus() >= 0){
							Record record = dsResponse.getData()[0];
							int plateId = record.getAttributeAsInt("plateId");
							plateIdItem.setValue(plateId);
						}
					}
				});
			}
		});
		
		plateIdItem = new SelectItem("plateId", shouldNotBeNull + "业务部门");
		plateIdItem.setValueMap(KeyValueManager.getValueMap("plate_records"));
		plateIdItem.setWidth("*");
		plateIdItem.setRequired(true);
		__formItems.add(plateIdItem);

		
		
		dutyIdItem = new ComboBoxItem("dutyId", shouldNotBeNull + "岗位");
		dutyIdItem.setTextMatchStyle(TextMatchStyle.SUBSTRING);
		dutyIdItem.setValueMap(KeyValueManager.getValueMap("duties"));
		dutyIdItem.setWidth("*");
		dutyIdItem.setRequired(true);
		__formItems.add(dutyIdItem);
		
		gradeIdItem = new ComboBoxItem("gradeId", "职级");
		gradeIdItem.setValueMap(KeyValueManager.getValueMap("system_dictionary_3"));
		gradeIdItem.setWidth("*");
		gradeIdItem.setTextMatchStyle(TextMatchStyle.SUBSTRING);
		__formItems.add(gradeIdItem);
		
		jobsItem = new TextItem("jobs", "职务");
		jobsItem.setWidth("*");
		__formItems.add(jobsItem);
		
		isCheckItem = new CheckboxItem("isCheck", "参与考勤");
		isCheckItem.setWidth("*");
		__formItems.add(isCheckItem);
		
		socialComputerNumberItem = new TextItem("socialComputerNumber", "社保电脑号");
		socialComputerNumberItem.setWidth("*");
		__formItems.add(socialComputerNumberItem);
		
		fundAccountItem = new TextItem("fundAccount", "公积金账号");
		fundAccountItem.setWidth("*");
		__formItems.add(fundAccountItem);
		
		bankCardNumItem = new TextItem("bankCardNum", "银行卡号");
		bankCardNumItem.setWidth("*");
		__formItems.add(bankCardNumItem);
		
		employeeShiftItem = new SelectItem("employeeShift", shouldNotBeNull + "员工排班");
		employeeShiftItem.setValueMap(KeyValueManager.getValueMap("system_dictionary_83"));
		employeeShiftItem.setWidth("*");
//		employeeShiftItem.setRequired(true);
//		__formItems.add(employeeShiftItem);
//		isCheckItem.addChangedHandler(new ChangedHandler() {
//			@Override
//			public void onChanged(ChangedEvent event) {
//				if(BaseHelpUtils.getBoolean(isCheckItem.getValue())){
//					employeeShiftItem.setDisabled(false);
//					employeeShiftItem.setRequired(true);
//				}else{
//					employeeShiftItem.setDisabled(true);
//					employeeShiftItem.setRequired(false);
//				}				
//			}
//		});
		
		resignationDateItem = new DateItem("resignationDate","离职时间");
		resignationDateItem.setWidth("*");
		resignationDateItem.setUseTextField(true);
		resignationDateItem.setUseMask(true);
		__formItems.add(resignationDateItem);
		
		userAcctItem = new ComboBoxItem("userAcct", "上级领导");
	    userAcctItem.setWidth("*");
//	    userAcctItem.setValueMap(KeyValueManager.getValueMap("employee_names"));
		KeyValueManager.loadValueMap("employee_names",userAcctItem);
	    __formItems.add(userAcctItem);
	    
	    workYearTypeItem = new SelectItem("workYearType","工龄工资状态");
	    workYearTypeItem.setWidth("*");
	    workYearTypeItem.setValueMap(KeyValueManager.getValueMap("system_dictionary_115"));
	    __formItems.add(workYearTypeItem);
	    
	    isLongSickLeaveItem = new CheckboxItem("isLongSickLeave","是否是长病假");
	    isLongSickLeaveItem.setWidth("*");
		__formItems.add(isLongSickLeaveItem);
	    
		selfIntroductionItem = new TextItem("selfIntroduction", "个人介绍及特长爱好");
		selfIntroductionItem.setWidth("*");
		__formItems.add(selfIntroductionItem);
		
		personalBusinessRemarkItem = new TextItem("personalBusinessRemark", "人事备注");
		personalBusinessRemarkItem.setWidth("*");
		personalBusinessRemarkItem.setStartRow(true);
		__formItems.add(personalBusinessRemarkItem);

		__form.setItems(getFormItemArray());
		__form.setDataSource(ds);
		__form.setNumCols(8);
		ClientUtil.DynamicFormProcessAccordingToDevice(__form);
		ownedCompanyItem.setColSpan(4);
		departmentIdItem.setColSpan(2);
		plateIdItem.setColSpan(2);
		personalBusinessRemarkItem.setColSpan(8);
		setPageMode(PAGE_MODE_UPDATE);
		setName("职员基本信息");

		allLayout = new HLayout(10);
		allLayout.setWidth100();
		allLayout.setHeight100();
		
		leftLayout = new VLayout(10);
		leftLayout.setHeight100();
		leftLayout.setWidth("70%");
		leftLayout.addMember(__form);
		allLayout.addMember(leftLayout);
		
		rightLayou = new VLayout(10);
		rightLayou.setHeight100();
		rightLayou.setWidth("30%");
		rightLayou.setLayoutLeftMargin(50);
		
		attachment = new EmployeeOnboardInformationEmployeeattachmentUpdate();
		attachmentsLayou = new VLayout(10);
		attachmentsLayou.setHeight("55%");
		attachmentsLayou.setWidth100();
		attachmentsLayou.addMember(attachment);
		rightLayou.addMember(attachmentsLayou);
		
//		if(operatorId != 534 && operatorId != 56 && operatorId != 37 && operatorId != 1161 && operatorId != 81){
//			pay = new EmployeeOnboardInformationPayinfoUpdate();
//			payLayou = new VLayout(10);
//			payLayou.setHeight("40%");
//			payLayou.setWidth("80%");
//			payLayou.addMember(pay);
//			rightLayou.addMember(payLayou);
//		}
		allLayout.addMember(rightLayou);
		addMember(allLayout);
	}
	
	@Override
	public Set<String> getItemNames() {
		Set<String> set = super.getItemNames();
		Set<String> attSet = attachment.getItemNames();
//		Set<String> paySet = pay.getItemNames();
		set.addAll(attSet);
//		set.addAll(paySet);
		return set;
	}

	@Override
	public void startEdit() {
		if(getRecord() != null) {
			Record record = getRecord();
			__form.editRecord(record);
			attachment.setRecord(record);
			attachment.startEdit();
//			pay.setRecord(record);
//			pay.startEdit();
		}
	}

	@Override
	public boolean checkData() {
		if(!__form.validate()){
			SC.say("请按系统提示修改信息");
			return false;
		}
		if(onboardDateItem.getValueAsDate().after(positiveDateItem.getValueAsDate())){
			SC.say("入职日期必须小于转正日期");
			return false;
		}
		if(contractStartDateItem.getValueAsDate().after(contractEndDateItem.getValueAsDate())){
			SC.say("劳动合同开始时间必须小于劳动合同到期时间");
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
		Map<String,Object> values = __form.getValues();
		
		Map<String,Object> attachmentMap = attachment.getValuesAsMap();
		
		if(!BaseHelpUtils.isNullOrEmpty(values) && values.size()>0){
			for(String name : values.keySet()){
				attachmentMap.put(name, values.get(name));
			}
		}
//		Map<String,Object> payMap = pay.getValuesAsMap();
//		if(!BaseHelpUtils.isNullOrEmpty(payMap) && payMap.size()>0){
//			for(String name : payMap.keySet()){
//				values.put(name, payMap.get(name));
//			}
//		}
		return attachmentMap;
	}
	
	public void makeEmployeeNo(){
		if(BaseHelpUtils.isNullOrEmpty(ownedCompanyItem.getValue()) || BaseHelpUtils.isNullOrEmpty(plateIdItem.getValue())){
			return;
		}else{
			int ownedCompany = BaseHelpUtils.getIntValue(ownedCompanyItem.getValue());
			int plateId = BaseHelpUtils.getIntValue(plateIdItem.getValue());
			Map<String,Object> params = new HashMap<>();
			params.put("companyRecordId",ownedCompany);
			params.put("plateId",plateId);
			DBDataSource.callOperation("NQ_MakeEmployeeNo", params, new DSCallback() {
				@Override
				public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
					if(dsResponse.getStatus() >= 0){
						Record record = dsResponse.getData()[0];
						String companyCode = BaseHelpUtils.getString(record.getAttribute("companyCode"));
						String plateCode = BaseHelpUtils.getString(record.getAttribute("plateCode"));
						String num = BaseHelpUtils.getString(record.getAttribute("num"));
						if(num.length() == 0){
							num = "001";
						}else if(num.length() == 1){
							num = "00" + num;
						}else if(num.length() == 2){
							num = "0" + num;
						}
						employeeNoItem.setValue(companyCode+plateCode+num);
					}
				}
			});
		}
	}
	
	int operatorId;

	public int getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
	}
	
	
}
