package delicacy.system.bean;

import delicacy.common.GenericCondition;
import java.util.Map;

public class ConditionMemployeeNew extends GenericCondition{

	public ConditionMemployeeNew(){
		setParameterCount(40);
	}

	public Integer getPlateId() {
		return this.__plate_id;
	}

	public void setPlateId( Integer value ) {
		this.__plate_id = value;
	}

	public String getEmployeeNo() {
		return this.__employee_no == null ? null : (this.__employee_no.indexOf("%") >= 0 ? this.__employee_no : "%"+this.__employee_no+"%");
	}

	public void setEmployeeNo( String value ) {
		this.__employee_no = value;
	}

	public String getEmployeeName() {
		return this.__employee_name == null ? null : (this.__employee_name.indexOf("%") >= 0 ? this.__employee_name : "%"+this.__employee_name+"%");
	}

	public void setEmployeeName( String value ) {
		this.__employee_name = value;
	}

	public Integer getDepartmentId() {
		return this.__department_id;
	}

	public void setDepartmentId( Integer value ) {
		this.__department_id = value;
	}

	public Integer getRoleId() {
		return this.__role_id;
	}

	public void setRoleId( Integer value ) {
		this.__role_id = value;
	}

	public Integer getGradeid() {
		return this.__gradeid;
	}

	public void setGradeid( Integer value ) {
		this.__gradeid = value;
	}

	public Integer getStatus() {
		return this.__status;
	}

	public void setStatus( Integer value ) {
		this.__status = value;
	}

	public Boolean getIsDepartment() {
		return this.__is_department;
	}

	public void setIsDepartment( Boolean value ) {
		this.__is_department = value;
	}

	public Integer getGender() {
		return this.__gender;
	}

	public void setGender( Integer value ) {
		this.__gender = value;
	}

	public Integer getDutyId() {
		return this.__duty_id;
	}

	public void setDutyId( Integer value ) {
		this.__duty_id = value;
	}

	public Double getOnboardYear() {
		return this.__onboard_year;
	}

	public void setOnboardYear( Double value ) {
		this.__onboard_year = value;
	}

	public Double getOnboardMonth() {
		return this.__onboard_month;
	}

	public void setOnboardMonth( Double value ) {
		this.__onboard_month = value;
	}

	public Double getResiYear() {
		return this.__resi_year;
	}

	public void setResiYear( Double value ) {
		this.__resi_year = value;
	}

	public Double getResiMonth() {
		return this.__resi_month;
	}

	public void setResiMonth( Double value ) {
		this.__resi_month = value;
	}

	public Double getBirthMonth() {
		return this.__birth_month;
	}

	public void setBirthMonth( Double value ) {
		this.__birth_month = value;
	}

	public Boolean getIsCheck() {
		return this.__is_check;
	}

	public void setIsCheck( Boolean value ) {
		this.__is_check = value;
	}

	public String getMobile() {
		return this.__mobile;
	}

	public void setMobile( String value ) {
		this.__mobile = value;
	}

	public String getEmail() {
		return this.__email == null ? null : (this.__email.indexOf("%") >= 0 ? this.__email : "%"+this.__email+"%");
	}

	public void setEmail( String value ) {
		this.__email = value;
	}

	public String getCard() {
		return this.__card == null ? null : (this.__card.indexOf("%") >= 0 ? this.__card : "%"+this.__card+"%");
	}

	public void setCard( String value ) {
		this.__card = value;
	}

	public String getAddress() {
		return this.__address == null ? null : (this.__address.indexOf("%") >= 0 ? this.__address : "%"+this.__address+"%");
	}

	public void setAddress( String value ) {
		this.__address = value;
	}

	public String getEducation() {
		return this.__education;
	}

	public void setEducation( String value ) {
		this.__education = value;
	}

	public String getDegree() {
		return this.__degree;
	}

	public void setDegree( String value ) {
		this.__degree = value;
	}

	public String getNationality() {
		return this.__nationality;
	}

	public void setNationality( String value ) {
		this.__nationality = value;
	}

	public String getMarriedStatus() {
		return this.__married_status;
	}

	public void setMarriedStatus( String value ) {
		this.__married_status = value;
	}

	public String getWorkaddress() {
		return this.__workaddress;
	}

	public void setWorkaddress( String value ) {
		this.__workaddress = value;
	}

	public String getBirthplace() {
		return this.__birthplace;
	}

	public void setBirthplace( String value ) {
		this.__birthplace = value;
	}

	public String getAccountLocation() {
		return this.__account_location;
	}

	public void setAccountLocation( String value ) {
		this.__account_location = value;
	}

	public Double getStartWorkYear() {
		return this.__start_work_year;
	}

	public void setStartWorkYear( Double value ) {
		this.__start_work_year = value;
	}

	public String getSocialComputerNumber() {
		return this.__social_computer_number;
	}

	public void setSocialComputerNumber( String value ) {
		this.__social_computer_number = value;
	}

	public String getFundAccount() {
		return this.__fund_account;
	}

	public void setFundAccount( String value ) {
		this.__fund_account = value;
	}

	public java.util.Date getPositiveDate() {
		return this.__positive_date;
	}

	public void setPositiveDate( java.util.Date value ) {
		this.__positive_date = value;
	}

	public String getTrytime() {
		return this.__trytime;
	}

	public void setTrytime( String value ) {
		this.__trytime = value;
	}

	public Double getContractStartDate() {
		return this.__contract_start_date;
	}

	public void setContractStartDate( Double value ) {
		this.__contract_start_date = value;
	}

	public Double getContractStartMonth() {
		return this.__contract_start_month;
	}

	public void setContractStartMonth( Double value ) {
		this.__contract_start_month = value;
	}

	public Double getContractEndYear() {
		return this.__contract_end_year;
	}

	public void setContractEndYear( Double value ) {
		this.__contract_end_year = value;
	}

	public Double getContractEndMonth() {
		return this.__contract_end_month;
	}

	public void setContractEndMonth( Double value ) {
		this.__contract_end_month = value;
	}

	public String getOwnedCompany() {
		return this.__owned_company;
	}

	public void setOwnedCompany( String value ) {
		this.__owned_company = value;
	}

	public String getBankCardNum() {
		return this.__bank_card_num;
	}

	public void setBankCardNum( String value ) {
		this.__bank_card_num = value;
	}

	public String getCompanyWeixin() {
		return this.__company_weixin;
	}

	public void setCompanyWeixin( String value ) {
		this.__company_weixin = value;
	}

	public String getCompanyEmail() {
		return this.__company_email;
	}

	public void setCompanyEmail( String value ) {
		this.__company_email = value;
	}

	@Override
	public String toJSONString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toJSONString());
		if(getPlateId() != null) sb.append(__wrapNumber(1, "plateId", getPlateId()));
		if(getEmployeeNo() != null) sb.append(__wrapString(1, "employeeNo", getEmployeeNo()));
		if(getEmployeeName() != null) sb.append(__wrapString(1, "employeeName", getEmployeeName()));
		if(getDepartmentId() != null) sb.append(__wrapNumber(1, "departmentId", getDepartmentId()));
		if(getRoleId() != null) sb.append(__wrapNumber(1, "roleId", getRoleId()));
		if(getGradeid() != null) sb.append(__wrapNumber(1, "gradeid", getGradeid()));
		if(getStatus() != null) sb.append(__wrapNumber(1, "status", getStatus()));
		if(getIsDepartment() != null) sb.append(__wrapBoolean(1, "isDepartment", getIsDepartment()));
		if(getGender() != null) sb.append(__wrapNumber(1, "gender", getGender()));
		if(getDutyId() != null) sb.append(__wrapNumber(1, "dutyId", getDutyId()));
		if(getOnboardYear() != null) sb.append(__wrapNumber(1, "onboardYear", getOnboardYear()));
		if(getOnboardMonth() != null) sb.append(__wrapNumber(1, "onboardMonth", getOnboardMonth()));
		if(getResiYear() != null) sb.append(__wrapNumber(1, "resiYear", getResiYear()));
		if(getResiMonth() != null) sb.append(__wrapNumber(1, "resiMonth", getResiMonth()));
		if(getBirthMonth() != null) sb.append(__wrapNumber(1, "birthMonth", getBirthMonth()));
		if(getIsCheck() != null) sb.append(__wrapBoolean(1, "isCheck", getIsCheck()));
		if(getMobile() != null) sb.append(__wrapString(1, "mobile", getMobile()));
		if(getEmail() != null) sb.append(__wrapString(1, "email", getEmail()));
		if(getCard() != null) sb.append(__wrapString(1, "card", getCard()));
		if(getAddress() != null) sb.append(__wrapString(1, "address", getAddress()));
		if(getEducation() != null) sb.append(__wrapString(1, "education", getEducation()));
		if(getDegree() != null) sb.append(__wrapString(1, "degree", getDegree()));
		if(getNationality() != null) sb.append(__wrapString(1, "nationality", getNationality()));
		if(getMarriedStatus() != null) sb.append(__wrapString(1, "marriedStatus", getMarriedStatus()));
		if(getWorkaddress() != null) sb.append(__wrapString(1, "workaddress", getWorkaddress()));
		if(getBirthplace() != null) sb.append(__wrapString(1, "birthplace", getBirthplace()));
		if(getAccountLocation() != null) sb.append(__wrapString(1, "accountLocation", getAccountLocation()));
		if(getStartWorkYear() != null) sb.append(__wrapNumber(1, "startWorkYear", getStartWorkYear()));
		if(getSocialComputerNumber() != null) sb.append(__wrapString(1, "socialComputerNumber", getSocialComputerNumber()));
		if(getFundAccount() != null) sb.append(__wrapString(1, "fundAccount", getFundAccount()));
		if(getPositiveDate() != null) sb.append(__wrapDate(1, "positiveDate", getPositiveDate()));
		if(getTrytime() != null) sb.append(__wrapString(1, "trytime", getTrytime()));
		if(getContractStartDate() != null) sb.append(__wrapNumber(1, "contractStartDate", getContractStartDate()));
		if(getContractStartMonth() != null) sb.append(__wrapNumber(1, "contractStartMonth", getContractStartMonth()));
		if(getContractEndYear() != null) sb.append(__wrapNumber(1, "contractEndYear", getContractEndYear()));
		if(getContractEndMonth() != null) sb.append(__wrapNumber(1, "contractEndMonth", getContractEndMonth()));
		if(getOwnedCompany() != null) sb.append(__wrapString(1, "ownedCompany", getOwnedCompany()));
		if(getBankCardNum() != null) sb.append(__wrapString(1, "bankCardNum", getBankCardNum()));
		if(getCompanyWeixin() != null) sb.append(__wrapString(1, "companyWeixin", getCompanyWeixin()));
		if(getCompanyEmail() != null) sb.append(__wrapString(1, "companyEmail", getCompanyEmail()));
		return sb.toString();
	}

	@Override
	public void setDataFromMap(Map<String, Object> values){
		super.setDataFromMap(values);
		Object val;
		if((val = values.get("plateId")) != null) setPlateId(__getInt(val)); 
		if((val = values.get("employeeNo")) != null) setEmployeeNo(__getString(val));
		if((val = values.get("employeeName")) != null) setEmployeeName(__getString(val));
		if((val = values.get("departmentId")) != null) setDepartmentId(__getInt(val)); 
		if((val = values.get("roleId")) != null) setRoleId(__getInt(val)); 
		if((val = values.get("gradeid")) != null) setGradeid(__getInt(val)); 
		if((val = values.get("status")) != null) setStatus(__getInt(val)); 
		if((val = values.get("isDepartment")) != null) setIsDepartment(__getBoolean(val));
		if((val = values.get("gender")) != null) setGender(__getInt(val)); 
		if((val = values.get("dutyId")) != null) setDutyId(__getInt(val)); 
		if((val = values.get("onboardYear")) != null) setOnboardYear(__getDouble(val)); 
		if((val = values.get("onboardMonth")) != null) setOnboardMonth(__getDouble(val)); 
		if((val = values.get("resiYear")) != null) setResiYear(__getDouble(val)); 
		if((val = values.get("resiMonth")) != null) setResiMonth(__getDouble(val)); 
		if((val = values.get("birthMonth")) != null) setBirthMonth(__getDouble(val)); 
		if((val = values.get("isCheck")) != null) setIsCheck(__getBoolean(val));
		if((val = values.get("mobile")) != null) setMobile(__getString(val));
		if((val = values.get("email")) != null) setEmail(__getString(val));
		if((val = values.get("card")) != null) setCard(__getString(val));
		if((val = values.get("address")) != null) setAddress(__getString(val));
		if((val = values.get("education")) != null) setEducation(__getString(val));
		if((val = values.get("degree")) != null) setDegree(__getString(val));
		if((val = values.get("nationality")) != null) setNationality(__getString(val));
		if((val = values.get("marriedStatus")) != null) setMarriedStatus(__getString(val));
		if((val = values.get("workaddress")) != null) setWorkaddress(__getString(val));
		if((val = values.get("birthplace")) != null) setBirthplace(__getString(val));
		if((val = values.get("accountLocation")) != null) setAccountLocation(__getString(val));
		if((val = values.get("startWorkYear")) != null) setStartWorkYear(__getDouble(val)); 
		if((val = values.get("socialComputerNumber")) != null) setSocialComputerNumber(__getString(val));
		if((val = values.get("fundAccount")) != null) setFundAccount(__getString(val));
		if((val = values.get("positiveDate")) != null) setPositiveDate(__getDate(val)); 
		if((val = values.get("trytime")) != null) setTrytime(__getString(val));
		if((val = values.get("contractStartDate")) != null) setContractStartDate(__getDouble(val)); 
		if((val = values.get("contractStartMonth")) != null) setContractStartMonth(__getDouble(val)); 
		if((val = values.get("contractEndYear")) != null) setContractEndYear(__getDouble(val)); 
		if((val = values.get("contractEndMonth")) != null) setContractEndMonth(__getDouble(val)); 
		if((val = values.get("ownedCompany")) != null) setOwnedCompany(__getString(val));
		if((val = values.get("bankCardNum")) != null) setBankCardNum(__getString(val));
		if((val = values.get("companyWeixin")) != null) setCompanyWeixin(__getString(val));
		if((val = values.get("companyEmail")) != null) setCompanyEmail(__getString(val));
	}

	private Integer __plate_id = null;
	private String __employee_no = null;
	private String __employee_name = null;
	private Integer __department_id = null;
	private Integer __role_id = null;
	private Integer __gradeid = null;
	private Integer __status = null;
	private Boolean __is_department = null;
	private Integer __gender = null;
	private Integer __duty_id = null;
	private Double __onboard_year = null;
	private Double __onboard_month = null;
	private Double __resi_year = null;
	private Double __resi_month = null;
	private Double __birth_month = null;
	private Boolean __is_check = null;
	private String __mobile = null;
	private String __email = null;
	private String __card = null;
	private String __address = null;
	private String __education = null;
	private String __degree = null;
	private String __nationality = null;
	private String __married_status = null;
	private String __workaddress = null;
	private String __birthplace = null;
	private String __account_location = null;
	private Double __start_work_year = null;
	private String __social_computer_number = null;
	private String __fund_account = null;
	private java.util.Date __positive_date = null;
	private String __trytime = null;
	private Double __contract_start_date = null;
	private Double __contract_start_month = null;
	private Double __contract_end_year = null;
	private Double __contract_end_month = null;
	private String __owned_company = null;
	private String __bank_card_num = null;
	private String __company_weixin = null;
	private String __company_email = null;
}

