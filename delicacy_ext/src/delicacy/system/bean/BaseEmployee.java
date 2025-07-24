package delicacy.system.bean;

import java.util.Map;
import java.util.Objects;
import delicacy.common.GenericBase;
import delicacy.common.BaseFactory;

public class BaseEmployee extends GenericBase implements BaseFactory<BaseEmployee>, Comparable<BaseEmployee> {

    public static BaseEmployee newInstance() {
        return new BaseEmployee();
    }

    @Override
    public BaseEmployee make() {
        BaseEmployee b = new BaseEmployee();
        return b;
    }

    public final static java.lang.String CS_EMPLOYEE_ID = "employee_id";
    public final static java.lang.String CS_EMPLOYEE_NO = "employee_no";
    public final static java.lang.String CS_EMPLOYEE_NAME = "employee_name";
    public final static java.lang.String CS_DEPARTMENT_ID = "department_id";
    public final static java.lang.String CS_ROLE_ID = "role_id";
    public final static java.lang.String CS_GRADE_ID = "grade_id";
    public final static java.lang.String CS_EMPLOYEE_PASSWORD = "employee_password";
    public final static java.lang.String CS_MOBILE = "mobile";
    public final static java.lang.String CS_PHONE = "phone";
    public final static java.lang.String CS_QQ = "qq";
    public final static java.lang.String CS_EMAIL = "email";
    public final static java.lang.String CS_ONBOARD_DATE = "onboard_date";
    public final static java.lang.String CS_RESIGNATION_DATE = "resignation_date";
    public final static java.lang.String CS_STATUS = "status";
    public final static java.lang.String CS_USABLE_STATUS = "usable_status";
    public final static java.lang.String CS_IS_DEPARTMENT = "is_department";
    public final static java.lang.String CS_PHOTO = "photo";
    public final static java.lang.String CS_GENDER = "gender";
    public final static java.lang.String CS_AUTOGRAPH = "autograph";
    public final static java.lang.String CS_AGE = "age";
    public final static java.lang.String CS_BIRTH = "birth";
    public final static java.lang.String CS_CARD = "card";
    public final static java.lang.String CS_ADDRESS = "address";
    public final static java.lang.String CS_ALTERNATE_FIELD1 = "alternate_field1";
    public final static java.lang.String CS_ALTERNATE_FIELD2 = "alternate_field2";
    public final static java.lang.String CS_ALTERNATE_FIELD3 = "alternate_field3";
    public final static java.lang.String CS_LOCKED = "locked";
    public final static java.lang.String CS_PLATE_ID = "plate_id";
    public final static java.lang.String CS_DUTY_ID = "duty_id";
    public final static java.lang.String CS_USER_ACCT = "user_acct";
    public final static java.lang.String CS_EMPLOYEE_NAME_EN = "employee_name_en";
    public final static java.lang.String CS_EDUCATION = "education";
    public final static java.lang.String CS_DEGREE = "degree";
    public final static java.lang.String CS_NATIONALITY = "nationality";
    public final static java.lang.String CS_MARRIED_STATUS = "married_status";
    public final static java.lang.String CS_HEALTH = "health";
    public final static java.lang.String CS_WORK_ADDRESS = "work_address";
    public final static java.lang.String CS_REGISTERED_ADDRESS = "registered_address";
    public final static java.lang.String CS_OA_ID = "oa_id";
    public final static java.lang.String CS_OA_DEPART = "oa_depart";
    public final static java.lang.String CS_IS_HEADCOUNT = "is_headcount";
    public final static java.lang.String CS_IS_CHECK = "is_check";
    public final static java.lang.String CS_DIRECT_LEADER = "direct_leader";
    public final static java.lang.String CS_IS_MANAGER = "is_manager";
    public final static java.lang.String CS_POLITICAL_FACE = "political_face";
    public final static java.lang.String CS_BIRTHPLACE = "birthplace";
    public final static java.lang.String CS_COUNTRY = "country";
    public final static java.lang.String CS_ACCOUNT_LOCATION = "account_location";
    public final static java.lang.String CS_LANGUAGES = "languages";
    public final static java.lang.String CS_START_WORK_DATE = "start_work_date";
    public final static java.lang.String CS_SOCIAL_COMPUTER_NUMBER = "social_computer_number";
    public final static java.lang.String CS_FUND_ACCOUNT = "fund_account";
    public final static java.lang.String CS_POSITIVE_DATE = "positive_date";
    public final static java.lang.String CS_TRY_TIME = "try_time";
    public final static java.lang.String CS_CONTRACT_START_DATE = "contract_start_date";
    public final static java.lang.String CS_CONTRACT_END_DATE = "contract_end_date";
    public final static java.lang.String CS_OWNED_COMPANY = "owned_company";
    public final static java.lang.String CS_JOBS = "jobs";
    public final static java.lang.String CS_PERSONAL_BUSINESS_REMARK = "personal_business_remark";
    public final static java.lang.String CS_SELF_INTRODUCTION = "self_introduction";
    public final static java.lang.String CS_LABOR_ATTACHMENTS = "labor_attachments";
    public final static java.lang.String CS_EMERGENCY_CONTACT_PERSON = "emergency_contact_person";
    public final static java.lang.String CS_EMERGENCY_CONTACT_PHONE = "emergency_contact_phone";
    public final static java.lang.String CS_HOME_PHONE = "home_phone";
    public final static java.lang.String CS_NOW_ADDRESS = "now_address";
    public final static java.lang.String CS_TRY_TIME_PAY = "try_time_pay";
    public final static java.lang.String CS_POSITIVE_PAY = "positive_pay";
    public final static java.lang.String CS_APPLY_EMPLOYEE_ID = "apply_employee_id";
    public final static java.lang.String CS_BANK_CARD_NUM = "bank_card_num";
    public final static java.lang.String CS_FILE_NUMBER = "file_number";
    public final static java.lang.String CS_HOUSEHOLD_REGISTRATION = "household_registration";
    public final static java.lang.String CS_FOREMAN = "foreman";
    public final static java.lang.String CS_CARD_ATTACHMENT = "card_attachment";
    public final static java.lang.String CS_TECHNICAL_ATTACHMENT = "technical_attachment";
    public final static java.lang.String CS_EDUCATION_PROOF = "education_proof";
    public final static java.lang.String CS_DEGREE_PROOF = "degree_proof";
    public final static java.lang.String CS_EMPLOYEE_SHIFT = "employee_shift";
    public final static java.lang.String CS_CARD_ADDRESS = "card_address";
    public final static java.lang.String CS_ANNUAL_PERFORMANCE = "annual_performance";
    public final static java.lang.String CS_ANNUAL_BONUS = "annual_bonus";
    public final static java.lang.String CS_COMPANY_WEIXIN = "company_weixin";
    public final static java.lang.String CS_COMPANY_EMAIL = "company_email";
    public final static java.lang.String CS_COMPANY_ID = "company_id";
    public final static java.lang.String CS_ECMC_USER_ID = "ecmc_user_id";
    public final static java.lang.String CS_ONBOARD_STATUS = "onboard_status";
    public final static java.lang.String CS_ATTACHMENT_REMARK = "attachment_remark";
    public final static java.lang.String CS_WORK_YEAR_TYPE = "work_year_type";
    public final static java.lang.String CS_ISBUSY = "isbusy";
    public final static java.lang.String CS_FOREIGNER_ID_CARD = "foreigner_id_card";
    public final static java.lang.String CS_REAL_BIRTHDATE = "real_birthdate";
    public final static java.lang.String CS_WORK_YEAR = "work_year";
    public final static java.lang.String CS_SHENZHEN_HOUSE = "shenzhen_house";
    public final static java.lang.String CS_TRANS_HOUSEHOLD_DATE = "trans_household_date";
    public final static java.lang.String CS_PROFESSIONAL_DIRECTION = "professional_direction";
    public final static java.lang.String CS_RECRUITMENT_SOURCES = "recruitment_sources";
    public final static java.lang.String CS_WECHAT_NUM = "wechat_num";
    public final static java.lang.String CS_AUTO_SIGN_YEAR = "auto_sign_year";
    public final static java.lang.String CS_RESUME_ATTACHMENT = "resume_attachment";
    public final static java.lang.String CS_SIGNATURE_ATTACHMENT = "signature_attachment";
    public final static java.lang.String CS_SOCIAL_ATTACHMENT = "social_attachment";
    public final static java.lang.String CS_FUND_ATTACHMENT = "fund_attachment";
    public final static java.lang.String CS_SPARE_ATTACHMENT1 = "spare_attachment1";
    public final static java.lang.String CS_SPARE_ATTACHMENT2 = "spare_attachment2";
    public final static java.lang.String CS_SPARE_ATTACHMENT3 = "spare_attachment3";
    public final static java.lang.String CS_EMPLOYEE_TYPE = "employee_type";
    public final static java.lang.String CS_IS_LONG_SICK_LEAVE = "is_long_sick_leave";
    public final static java.lang.String CS_BASIC_SALARY = "basic_salary";
    public final static java.lang.String CS_JOB_TITLE_ALLOWANCE = "job_title_allowance";
    public final static java.lang.String CS_POST_ALLOWANCE = "post_allowance";
    public final static java.lang.String CS_OTHER = "other";
    public final static java.lang.String CS_COMMUNICATION_SUBSIDY = "communication_subsidy";
    public final static java.lang.String CS_SUBTOTAL_MONTHLY_WAGE = "subtotal_monthly_wage";
    public final static java.lang.String CS_MONTHLY_PREISSUED_BONUS = "monthly_preissued_bonus";
    public final static java.lang.String CS_TOTAL_MONTHLY_REMUNERATION = "total_monthly_remuneration";
    public final static java.lang.String CS_PHOTO_ID = "photo_id";
    public final static java.lang.String CS_LABOR_ATTACHMENT_ID = "labor_attachment_id";
    public final static java.lang.String CS_CARD_ATTACHMENT_ID = "card_attachment_id";
    public final static java.lang.String CS_TECHNICAL_ATTACHMENT_ID = "technical_attachment_id";
    public final static java.lang.String CS_EDUCATION_PROOF_ID = "education_proof_id";
    public final static java.lang.String CS_DEGREE_PROOF_ID = "degree_proof_id";
    public final static java.lang.String CS_BANK_CARD_ATTACHMENT = "bank_card_attachment";
    public final static java.lang.String CS_BANK_CARD_ATTACHMENT_ID = "bank_card_attachment_id";

    public final static java.lang.String ALL_CAPTIONS = "员工编码,员工编号,员工姓名,部门,角色,等级,密码,手机,电话,QQ,邮箱,入职日期,离职日期,4删除),是否可用,是否为部门负责人,头像,性别,我的签名,,出生日期,身份证号,家庭地址,备用字段1,备用字段2,备用字段3,是否锁定,板块,职位名称,上级领导,英文名,学历,学位,名族,婚姻状况,身体状况,工作地,注册地,OA编码,OA部门,总部员工,参与考勤,直接领导,是否为超级管理员,政治面貌,籍贯,国籍,户口所在地,外语语种,参加工作时间,社保电脑号,公积金账号,转正日期,试用期,劳动合同开始时间,劳动合同到期时间,所属公司,职务,人事备注,个人介绍,劳动合同附件,紧急联系人,紧急联系人电话,家庭电话,现住地址,试用期工资,转正工资,发起人,银行卡号,档案号,户籍,司龄,身份证附件,职称证明,学历证,学位证,员工排班,身份证地址,年度绩效,年度奖金,企业微信账号,企业邮箱,归属公司,ECMC用户编码,入职职员状态(0实习生，1试用期，2正式员工),附件备注,工龄工资状态(对应字典表115),是否忙碌状态（1空闲，2忙碌）,外籍人士身份号,实际生日,工龄,自有深圳房产(1有,2无),调户补贴首发日期,专业方向,招聘来源,微信号,自动续签年限,入职简历附件,电子签名附件,社保卡附件,公积金卡附件,上传其他附件1,上传其他附件2,上传其他附件3,员工类型,是否是长病假,基本工资,职称津贴,岗位津贴,其他,通讯补贴,月工资小计,月预发奖金,月总薪酬,头像编码,劳动合同附件编码,身份证附件编码,职称证明编码,学历证编码,学位证编码,银行卡附件,银行卡编码";

    public java.lang.Integer getEmployeeId() {
        return this.__employee_id;
    }

    public void setEmployeeId(java.lang.Integer value) {
        this.__employee_id = value;
    }

    public java.lang.String getEmployeeNo() {
        return this.__employee_no;
    }

    public void setEmployeeNo(java.lang.String value) {
        this.__employee_no = value;
    }

    public java.lang.String getEmployeeName() {
        return this.__employee_name;
    }

    public void setEmployeeName(java.lang.String value) {
        this.__employee_name = value;
    }

    public java.lang.Integer getDepartmentId() {
        return this.__department_id;
    }

    public void setDepartmentId(java.lang.Integer value) {
        this.__department_id = value;
    }

    public java.lang.Integer getRoleId() {
        return this.__role_id;
    }

    public void setRoleId(java.lang.Integer value) {
        this.__role_id = value;
    }

    public java.lang.Integer getGradeId() {
        return this.__grade_id;
    }

    public void setGradeId(java.lang.Integer value) {
        this.__grade_id = value;
    }

    public java.lang.String getEmployeePassword() {
        return this.__employee_password;
    }

    public void setEmployeePassword(java.lang.String value) {
        this.__employee_password = value;
    }

    public java.lang.String getMobile() {
        return this.__mobile;
    }

    public void setMobile(java.lang.String value) {
        this.__mobile = value;
    }

    public java.lang.String getPhone() {
        return this.__phone;
    }

    public void setPhone(java.lang.String value) {
        this.__phone = value;
    }

    public java.lang.String getQq() {
        return this.__qq;
    }

    public void setQq(java.lang.String value) {
        this.__qq = value;
    }

    public java.lang.String getEmail() {
        return this.__email;
    }

    public void setEmail(java.lang.String value) {
        this.__email = value;
    }

    public java.util.Date getOnboardDate() {
        return this.__onboard_date;
    }

    public void setOnboardDate(java.util.Date value) {
        this.__onboard_date = value;
    }

    public java.util.Date getResignationDate() {
        return this.__resignation_date;
    }

    public void setResignationDate(java.util.Date value) {
        this.__resignation_date = value;
    }

    public java.lang.Integer getStatus() {
        return this.__status;
    }

    public void setStatus(java.lang.Integer value) {
        this.__status = value;
    }

    public java.lang.Boolean getUsableStatus() {
        return this.__usable_status;
    }

    public void setUsableStatus(java.lang.Boolean value) {
        this.__usable_status = value;
    }

    public java.lang.Boolean getIsDepartment() {
        return this.__is_department;
    }

    public void setIsDepartment(java.lang.Boolean value) {
        this.__is_department = value;
    }

    public java.lang.String getPhoto() {
        return this.__photo;
    }

    public void setPhoto(java.lang.String value) {
        this.__photo = value;
    }

    public java.lang.Integer getGender() {
        return this.__gender;
    }

    public void setGender(java.lang.Integer value) {
        this.__gender = value;
    }

    public java.lang.String getAutograph() {
        return this.__autograph;
    }

    public void setAutograph(java.lang.String value) {
        this.__autograph = value;
    }

    public java.lang.Integer getAge() {
        return this.__age;
    }

    public void setAge(java.lang.Integer value) {
        this.__age = value;
    }

    public java.util.Date getBirth() {
        return this.__birth;
    }

    public void setBirth(java.util.Date value) {
        this.__birth = value;
    }

    public java.lang.String getCard() {
        return this.__card;
    }

    public void setCard(java.lang.String value) {
        this.__card = value;
    }

    public java.lang.String getAddress() {
        return this.__address;
    }

    public void setAddress(java.lang.String value) {
        this.__address = value;
    }

    public java.lang.String getAlternateField1() {
        return this.__alternate_field1;
    }

    public void setAlternateField1(java.lang.String value) {
        this.__alternate_field1 = value;
    }

    public java.lang.String getAlternateField2() {
        return this.__alternate_field2;
    }

    public void setAlternateField2(java.lang.String value) {
        this.__alternate_field2 = value;
    }

    public java.lang.String getAlternateField3() {
        return this.__alternate_field3;
    }

    public void setAlternateField3(java.lang.String value) {
        this.__alternate_field3 = value;
    }

    public java.lang.Boolean getLocked() {
        return this.__locked;
    }

    public void setLocked(java.lang.Boolean value) {
        this.__locked = value;
    }

    public java.lang.Integer getPlateId() {
        return this.__plate_id;
    }

    public void setPlateId(java.lang.Integer value) {
        this.__plate_id = value;
    }

    public java.lang.Integer getDutyId() {
        return this.__duty_id;
    }

    public void setDutyId(java.lang.Integer value) {
        this.__duty_id = value;
    }

    public java.lang.String getUserAcct() {
        return this.__user_acct;
    }

    public void setUserAcct(java.lang.String value) {
        this.__user_acct = value;
    }

    public java.lang.String getEmployeeNameEn() {
        return this.__employee_name_en;
    }

    public void setEmployeeNameEn(java.lang.String value) {
        this.__employee_name_en = value;
    }

    public java.lang.String getEducation() {
        return this.__education;
    }

    public void setEducation(java.lang.String value) {
        this.__education = value;
    }

    public java.lang.String getDegree() {
        return this.__degree;
    }

    public void setDegree(java.lang.String value) {
        this.__degree = value;
    }

    public java.lang.String getNationality() {
        return this.__nationality;
    }

    public void setNationality(java.lang.String value) {
        this.__nationality = value;
    }

    public java.lang.String getMarriedStatus() {
        return this.__married_status;
    }

    public void setMarriedStatus(java.lang.String value) {
        this.__married_status = value;
    }

    public java.lang.String getHealth() {
        return this.__health;
    }

    public void setHealth(java.lang.String value) {
        this.__health = value;
    }

    public java.lang.String getWorkAddress() {
        return this.__work_address;
    }

    public void setWorkAddress(java.lang.String value) {
        this.__work_address = value;
    }

    public java.lang.String getRegisteredAddress() {
        return this.__registered_address;
    }

    public void setRegisteredAddress(java.lang.String value) {
        this.__registered_address = value;
    }

    public java.lang.Integer getOaId() {
        return this.__oa_id;
    }

    public void setOaId(java.lang.Integer value) {
        this.__oa_id = value;
    }

    public java.lang.Integer getOaDepart() {
        return this.__oa_depart;
    }

    public void setOaDepart(java.lang.Integer value) {
        this.__oa_depart = value;
    }

    public java.lang.Boolean getIsHeadcount() {
        return this.__is_headcount;
    }

    public void setIsHeadcount(java.lang.Boolean value) {
        this.__is_headcount = value;
    }

    public java.lang.Boolean getIsCheck() {
        return this.__is_check;
    }

    public void setIsCheck(java.lang.Boolean value) {
        this.__is_check = value;
    }

    public java.lang.Integer getDirectLeader() {
        return this.__direct_leader;
    }

    public void setDirectLeader(java.lang.Integer value) {
        this.__direct_leader = value;
    }

    public java.lang.Boolean getIsManager() {
        return this.__is_manager;
    }

    public void setIsManager(java.lang.Boolean value) {
        this.__is_manager = value;
    }

    public java.lang.Integer getPoliticalFace() {
        return this.__political_face;
    }

    public void setPoliticalFace(java.lang.Integer value) {
        this.__political_face = value;
    }

    public java.lang.String getBirthplace() {
        return this.__birthplace;
    }

    public void setBirthplace(java.lang.String value) {
        this.__birthplace = value;
    }

    public java.lang.String getCountry() {
        return this.__country;
    }

    public void setCountry(java.lang.String value) {
        this.__country = value;
    }

    public java.lang.String getAccountLocation() {
        return this.__account_location;
    }

    public void setAccountLocation(java.lang.String value) {
        this.__account_location = value;
    }

    public java.lang.String getLanguages() {
        return this.__languages;
    }

    public void setLanguages(java.lang.String value) {
        this.__languages = value;
    }

    public java.util.Date getStartWorkDate() {
        return this.__start_work_date;
    }

    public void setStartWorkDate(java.util.Date value) {
        this.__start_work_date = value;
    }

    public java.lang.String getSocialComputerNumber() {
        return this.__social_computer_number;
    }

    public void setSocialComputerNumber(java.lang.String value) {
        this.__social_computer_number = value;
    }

    public java.lang.String getFundAccount() {
        return this.__fund_account;
    }

    public void setFundAccount(java.lang.String value) {
        this.__fund_account = value;
    }

    public java.util.Date getPositiveDate() {
        return this.__positive_date;
    }

    public void setPositiveDate(java.util.Date value) {
        this.__positive_date = value;
    }

    public java.lang.String getTryTime() {
        return this.__try_time;
    }

    public void setTryTime(java.lang.String value) {
        this.__try_time = value;
    }

    public java.util.Date getContractStartDate() {
        return this.__contract_start_date;
    }

    public void setContractStartDate(java.util.Date value) {
        this.__contract_start_date = value;
    }

    public java.util.Date getContractEndDate() {
        return this.__contract_end_date;
    }

    public void setContractEndDate(java.util.Date value) {
        this.__contract_end_date = value;
    }

    public java.lang.String getOwnedCompany() {
        return this.__owned_company;
    }

    public void setOwnedCompany(java.lang.String value) {
        this.__owned_company = value;
    }

    public java.lang.String getJobs() {
        return this.__jobs;
    }

    public void setJobs(java.lang.String value) {
        this.__jobs = value;
    }

    public java.lang.String getPersonalBusinessRemark() {
        return this.__personal_business_remark;
    }

    public void setPersonalBusinessRemark(java.lang.String value) {
        this.__personal_business_remark = value;
    }

    public java.lang.String getSelfIntroduction() {
        return this.__self_introduction;
    }

    public void setSelfIntroduction(java.lang.String value) {
        this.__self_introduction = value;
    }

    public java.lang.String getLaborAttachments() {
        return this.__labor_attachments;
    }

    public void setLaborAttachments(java.lang.String value) {
        this.__labor_attachments = value;
    }

    public java.lang.String getEmergencyContactPerson() {
        return this.__emergency_contact_person;
    }

    public void setEmergencyContactPerson(java.lang.String value) {
        this.__emergency_contact_person = value;
    }

    public java.lang.String getEmergencyContactPhone() {
        return this.__emergency_contact_phone;
    }

    public void setEmergencyContactPhone(java.lang.String value) {
        this.__emergency_contact_phone = value;
    }

    public java.lang.String getHomePhone() {
        return this.__home_phone;
    }

    public void setHomePhone(java.lang.String value) {
        this.__home_phone = value;
    }

    public java.lang.String getNowAddress() {
        return this.__now_address;
    }

    public void setNowAddress(java.lang.String value) {
        this.__now_address = value;
    }

    public java.lang.String getTryTimePay() {
        return this.__try_time_pay;
    }

    public void setTryTimePay(java.lang.String value) {
        this.__try_time_pay = value;
    }

    public java.lang.String getPositivePay() {
        return this.__positive_pay;
    }

    public void setPositivePay(java.lang.String value) {
        this.__positive_pay = value;
    }

    public java.lang.Integer getApplyEmployeeId() {
        return this.__apply_employee_id;
    }

    public void setApplyEmployeeId(java.lang.Integer value) {
        this.__apply_employee_id = value;
    }

    public java.lang.String getBankCardNum() {
        return this.__bank_card_num;
    }

    public void setBankCardNum(java.lang.String value) {
        this.__bank_card_num = value;
    }

    public java.lang.String getFileNumber() {
        return this.__file_number;
    }

    public void setFileNumber(java.lang.String value) {
        this.__file_number = value;
    }

    public java.lang.Integer getHouseholdRegistration() {
        return this.__household_registration;
    }

    public void setHouseholdRegistration(java.lang.Integer value) {
        this.__household_registration = value;
    }

    public java.lang.String getForeman() {
        return this.__foreman;
    }

    public void setForeman(java.lang.String value) {
        this.__foreman = value;
    }

    public java.lang.String getCardAttachment() {
        return this.__card_attachment;
    }

    public void setCardAttachment(java.lang.String value) {
        this.__card_attachment = value;
    }

    public java.lang.String getTechnicalAttachment() {
        return this.__technical_attachment;
    }

    public void setTechnicalAttachment(java.lang.String value) {
        this.__technical_attachment = value;
    }

    public java.lang.String getEducationProof() {
        return this.__education_proof;
    }

    public void setEducationProof(java.lang.String value) {
        this.__education_proof = value;
    }

    public java.lang.String getDegreeProof() {
        return this.__degree_proof;
    }

    public void setDegreeProof(java.lang.String value) {
        this.__degree_proof = value;
    }

    public java.lang.Integer getEmployeeShift() {
        return this.__employee_shift;
    }

    public void setEmployeeShift(java.lang.Integer value) {
        this.__employee_shift = value;
    }

    public java.lang.String getCardAddress() {
        return this.__card_address;
    }

    public void setCardAddress(java.lang.String value) {
        this.__card_address = value;
    }

    public java.lang.String getAnnualPerformance() {
        return this.__annual_performance;
    }

    public void setAnnualPerformance(java.lang.String value) {
        this.__annual_performance = value;
    }

    public java.lang.String getAnnualBonus() {
        return this.__annual_bonus;
    }

    public void setAnnualBonus(java.lang.String value) {
        this.__annual_bonus = value;
    }

    public java.lang.String getCompanyWeixin() {
        return this.__company_weixin;
    }

    public void setCompanyWeixin(java.lang.String value) {
        this.__company_weixin = value;
    }

    public java.lang.String getCompanyEmail() {
        return this.__company_email;
    }

    public void setCompanyEmail(java.lang.String value) {
        this.__company_email = value;
    }

    public java.lang.Integer getCompanyId() {
        return this.__company_id;
    }

    public void setCompanyId(java.lang.Integer value) {
        this.__company_id = value;
    }

    public java.lang.Integer getEcmcUserId() {
        return this.__ecmc_user_id;
    }

    public void setEcmcUserId(java.lang.Integer value) {
        this.__ecmc_user_id = value;
    }

    public java.lang.Integer getOnboardStatus() {
        return this.__onboard_status;
    }

    public void setOnboardStatus(java.lang.Integer value) {
        this.__onboard_status = value;
    }

    public java.lang.String getAttachmentRemark() {
        return this.__attachment_remark;
    }

    public void setAttachmentRemark(java.lang.String value) {
        this.__attachment_remark = value;
    }

    public java.lang.Integer getWorkYearType() {
        return this.__work_year_type;
    }

    public void setWorkYearType(java.lang.Integer value) {
        this.__work_year_type = value;
    }

    public java.lang.Integer getIsbusy() {
        return this.__isbusy;
    }

    public void setIsbusy(java.lang.Integer value) {
        this.__isbusy = value;
    }

    public java.lang.String getForeignerIdCard() {
        return this.__foreigner_id_card;
    }

    public void setForeignerIdCard(java.lang.String value) {
        this.__foreigner_id_card = value;
    }

    public java.util.Date getRealBirthdate() {
        return this.__real_birthdate;
    }

    public void setRealBirthdate(java.util.Date value) {
        this.__real_birthdate = value;
    }

    public java.lang.String getWorkYear() {
        return this.__work_year;
    }

    public void setWorkYear(java.lang.String value) {
        this.__work_year = value;
    }

    public java.lang.Integer getShenzhenHouse() {
        return this.__shenzhen_house;
    }

    public void setShenzhenHouse(java.lang.Integer value) {
        this.__shenzhen_house = value;
    }

    public java.util.Date getTransHouseholdDate() {
        return this.__trans_household_date;
    }

    public void setTransHouseholdDate(java.util.Date value) {
        this.__trans_household_date = value;
    }

    public java.lang.Integer getProfessionalDirection() {
        return this.__professional_direction;
    }

    public void setProfessionalDirection(java.lang.Integer value) {
        this.__professional_direction = value;
    }

    public java.lang.Integer getRecruitmentSources() {
        return this.__recruitment_sources;
    }

    public void setRecruitmentSources(java.lang.Integer value) {
        this.__recruitment_sources = value;
    }

    public java.lang.String getWechatNum() {
        return this.__wechat_num;
    }

    public void setWechatNum(java.lang.String value) {
        this.__wechat_num = value;
    }

    public java.lang.Integer getAutoSignYear() {
        return this.__auto_sign_year;
    }

    public void setAutoSignYear(java.lang.Integer value) {
        this.__auto_sign_year = value;
    }

    public java.lang.String getResumeAttachment() {
        return this.__resume_attachment;
    }

    public void setResumeAttachment(java.lang.String value) {
        this.__resume_attachment = value;
    }

    public java.lang.String getSignatureAttachment() {
        return this.__signature_attachment;
    }

    public void setSignatureAttachment(java.lang.String value) {
        this.__signature_attachment = value;
    }

    public java.lang.String getSocialAttachment() {
        return this.__social_attachment;
    }

    public void setSocialAttachment(java.lang.String value) {
        this.__social_attachment = value;
    }

    public java.lang.String getFundAttachment() {
        return this.__fund_attachment;
    }

    public void setFundAttachment(java.lang.String value) {
        this.__fund_attachment = value;
    }

    public java.lang.String getSpareAttachment1() {
        return this.__spare_attachment1;
    }

    public void setSpareAttachment1(java.lang.String value) {
        this.__spare_attachment1 = value;
    }

    public java.lang.String getSpareAttachment2() {
        return this.__spare_attachment2;
    }

    public void setSpareAttachment2(java.lang.String value) {
        this.__spare_attachment2 = value;
    }

    public java.lang.String getSpareAttachment3() {
        return this.__spare_attachment3;
    }

    public void setSpareAttachment3(java.lang.String value) {
        this.__spare_attachment3 = value;
    }

    public java.lang.Integer getEmployeeType() {
        return this.__employee_type;
    }

    public void setEmployeeType(java.lang.Integer value) {
        this.__employee_type = value;
    }

    public java.lang.Boolean getIsLongSickLeave() {
        return this.__is_long_sick_leave;
    }

    public void setIsLongSickLeave(java.lang.Boolean value) {
        this.__is_long_sick_leave = value;
    }

    public java.math.BigDecimal getBasicSalary() {
        return this.__basic_salary;
    }

    public void setBasicSalary(java.math.BigDecimal value) {
        this.__basic_salary = value;
    }

    public java.math.BigDecimal getJobTitleAllowance() {
        return this.__job_title_allowance;
    }

    public void setJobTitleAllowance(java.math.BigDecimal value) {
        this.__job_title_allowance = value;
    }

    public java.math.BigDecimal getPostAllowance() {
        return this.__post_allowance;
    }

    public void setPostAllowance(java.math.BigDecimal value) {
        this.__post_allowance = value;
    }

    public java.math.BigDecimal getOther() {
        return this.__other;
    }

    public void setOther(java.math.BigDecimal value) {
        this.__other = value;
    }

    public java.math.BigDecimal getCommunicationSubsidy() {
        return this.__communication_subsidy;
    }

    public void setCommunicationSubsidy(java.math.BigDecimal value) {
        this.__communication_subsidy = value;
    }

    public java.math.BigDecimal getSubtotalMonthlyWage() {
        return this.__subtotal_monthly_wage;
    }

    public void setSubtotalMonthlyWage(java.math.BigDecimal value) {
        this.__subtotal_monthly_wage = value;
    }

    public java.math.BigDecimal getMonthlyPreissuedBonus() {
        return this.__monthly_preissued_bonus;
    }

    public void setMonthlyPreissuedBonus(java.math.BigDecimal value) {
        this.__monthly_preissued_bonus = value;
    }

    public java.math.BigDecimal getTotalMonthlyRemuneration() {
        return this.__total_monthly_remuneration;
    }

    public void setTotalMonthlyRemuneration(java.math.BigDecimal value) {
        this.__total_monthly_remuneration = value;
    }

    public java.lang.Integer getPhotoId() {
        return this.__photo_id;
    }

    public void setPhotoId(java.lang.Integer value) {
        this.__photo_id = value;
    }

    public java.lang.Integer getLaborAttachmentId() {
        return this.__labor_attachment_id;
    }

    public void setLaborAttachmentId(java.lang.Integer value) {
        this.__labor_attachment_id = value;
    }

    public java.lang.Integer getCardAttachmentId() {
        return this.__card_attachment_id;
    }

    public void setCardAttachmentId(java.lang.Integer value) {
        this.__card_attachment_id = value;
    }

    public java.lang.Integer getTechnicalAttachmentId() {
        return this.__technical_attachment_id;
    }

    public void setTechnicalAttachmentId(java.lang.Integer value) {
        this.__technical_attachment_id = value;
    }

    public java.lang.Integer getEducationProofId() {
        return this.__education_proof_id;
    }

    public void setEducationProofId(java.lang.Integer value) {
        this.__education_proof_id = value;
    }

    public java.lang.Integer getDegreeProofId() {
        return this.__degree_proof_id;
    }

    public void setDegreeProofId(java.lang.Integer value) {
        this.__degree_proof_id = value;
    }

    public java.lang.String getBankCardAttachment() {
        return this.__bank_card_attachment;
    }

    public void setBankCardAttachment(java.lang.String value) {
        this.__bank_card_attachment = value;
    }

    public java.lang.Integer getBankCardAttachmentId() {
        return this.__bank_card_attachment_id;
    }

    public void setBankCardAttachmentId(java.lang.Integer value) {
        this.__bank_card_attachment_id = value;
    }

    public void cloneCopy(BaseEmployee __bean) {
        __bean.setEmployeeId(getEmployeeId());
        __bean.setEmployeeNo(getEmployeeNo());
        __bean.setEmployeeName(getEmployeeName());
        __bean.setDepartmentId(getDepartmentId());
        __bean.setRoleId(getRoleId());
        __bean.setGradeId(getGradeId());
        __bean.setEmployeePassword(getEmployeePassword());
        __bean.setMobile(getMobile());
        __bean.setPhone(getPhone());
        __bean.setQq(getQq());
        __bean.setEmail(getEmail());
        __bean.setOnboardDate(getOnboardDate());
        __bean.setResignationDate(getResignationDate());
        __bean.setStatus(getStatus());
        __bean.setUsableStatus(getUsableStatus());
        __bean.setIsDepartment(getIsDepartment());
        __bean.setPhoto(getPhoto());
        __bean.setGender(getGender());
        __bean.setAutograph(getAutograph());
        __bean.setAge(getAge());
        __bean.setBirth(getBirth());
        __bean.setCard(getCard());
        __bean.setAddress(getAddress());
        __bean.setAlternateField1(getAlternateField1());
        __bean.setAlternateField2(getAlternateField2());
        __bean.setAlternateField3(getAlternateField3());
        __bean.setLocked(getLocked());
        __bean.setPlateId(getPlateId());
        __bean.setDutyId(getDutyId());
        __bean.setUserAcct(getUserAcct());
        __bean.setEmployeeNameEn(getEmployeeNameEn());
        __bean.setEducation(getEducation());
        __bean.setDegree(getDegree());
        __bean.setNationality(getNationality());
        __bean.setMarriedStatus(getMarriedStatus());
        __bean.setHealth(getHealth());
        __bean.setWorkAddress(getWorkAddress());
        __bean.setRegisteredAddress(getRegisteredAddress());
        __bean.setOaId(getOaId());
        __bean.setOaDepart(getOaDepart());
        __bean.setIsHeadcount(getIsHeadcount());
        __bean.setIsCheck(getIsCheck());
        __bean.setDirectLeader(getDirectLeader());
        __bean.setIsManager(getIsManager());
        __bean.setPoliticalFace(getPoliticalFace());
        __bean.setBirthplace(getBirthplace());
        __bean.setCountry(getCountry());
        __bean.setAccountLocation(getAccountLocation());
        __bean.setLanguages(getLanguages());
        __bean.setStartWorkDate(getStartWorkDate());
        __bean.setSocialComputerNumber(getSocialComputerNumber());
        __bean.setFundAccount(getFundAccount());
        __bean.setPositiveDate(getPositiveDate());
        __bean.setTryTime(getTryTime());
        __bean.setContractStartDate(getContractStartDate());
        __bean.setContractEndDate(getContractEndDate());
        __bean.setOwnedCompany(getOwnedCompany());
        __bean.setJobs(getJobs());
        __bean.setPersonalBusinessRemark(getPersonalBusinessRemark());
        __bean.setSelfIntroduction(getSelfIntroduction());
        __bean.setLaborAttachments(getLaborAttachments());
        __bean.setEmergencyContactPerson(getEmergencyContactPerson());
        __bean.setEmergencyContactPhone(getEmergencyContactPhone());
        __bean.setHomePhone(getHomePhone());
        __bean.setNowAddress(getNowAddress());
        __bean.setTryTimePay(getTryTimePay());
        __bean.setPositivePay(getPositivePay());
        __bean.setApplyEmployeeId(getApplyEmployeeId());
        __bean.setBankCardNum(getBankCardNum());
        __bean.setFileNumber(getFileNumber());
        __bean.setHouseholdRegistration(getHouseholdRegistration());
        __bean.setForeman(getForeman());
        __bean.setCardAttachment(getCardAttachment());
        __bean.setTechnicalAttachment(getTechnicalAttachment());
        __bean.setEducationProof(getEducationProof());
        __bean.setDegreeProof(getDegreeProof());
        __bean.setEmployeeShift(getEmployeeShift());
        __bean.setCardAddress(getCardAddress());
        __bean.setAnnualPerformance(getAnnualPerformance());
        __bean.setAnnualBonus(getAnnualBonus());
        __bean.setCompanyWeixin(getCompanyWeixin());
        __bean.setCompanyEmail(getCompanyEmail());
        __bean.setCompanyId(getCompanyId());
        __bean.setEcmcUserId(getEcmcUserId());
        __bean.setOnboardStatus(getOnboardStatus());
        __bean.setAttachmentRemark(getAttachmentRemark());
        __bean.setWorkYearType(getWorkYearType());
        __bean.setIsbusy(getIsbusy());
        __bean.setForeignerIdCard(getForeignerIdCard());
        __bean.setRealBirthdate(getRealBirthdate());
        __bean.setWorkYear(getWorkYear());
        __bean.setShenzhenHouse(getShenzhenHouse());
        __bean.setTransHouseholdDate(getTransHouseholdDate());
        __bean.setProfessionalDirection(getProfessionalDirection());
        __bean.setRecruitmentSources(getRecruitmentSources());
        __bean.setWechatNum(getWechatNum());
        __bean.setAutoSignYear(getAutoSignYear());
        __bean.setResumeAttachment(getResumeAttachment());
        __bean.setSignatureAttachment(getSignatureAttachment());
        __bean.setSocialAttachment(getSocialAttachment());
        __bean.setFundAttachment(getFundAttachment());
        __bean.setSpareAttachment1(getSpareAttachment1());
        __bean.setSpareAttachment2(getSpareAttachment2());
        __bean.setSpareAttachment3(getSpareAttachment3());
        __bean.setEmployeeType(getEmployeeType());
        __bean.setIsLongSickLeave(getIsLongSickLeave());
        __bean.setBasicSalary(getBasicSalary());
        __bean.setJobTitleAllowance(getJobTitleAllowance());
        __bean.setPostAllowance(getPostAllowance());
        __bean.setOther(getOther());
        __bean.setCommunicationSubsidy(getCommunicationSubsidy());
        __bean.setSubtotalMonthlyWage(getSubtotalMonthlyWage());
        __bean.setMonthlyPreissuedBonus(getMonthlyPreissuedBonus());
        __bean.setTotalMonthlyRemuneration(getTotalMonthlyRemuneration());
        __bean.setPhotoId(getPhotoId());
        __bean.setLaborAttachmentId(getLaborAttachmentId());
        __bean.setCardAttachmentId(getCardAttachmentId());
        __bean.setTechnicalAttachmentId(getTechnicalAttachmentId());
        __bean.setEducationProofId(getEducationProofId());
        __bean.setDegreeProofId(getDegreeProofId());
        __bean.setBankCardAttachment(getBankCardAttachment());
        __bean.setBankCardAttachmentId(getBankCardAttachmentId());
    }

    public java.lang.String toCSVString() {

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
        StringBuilder sb = new StringBuilder();
        sb.append(getEmployeeId() == null ? "" : getEmployeeId());
        sb.append(",");
        sb.append(getEmployeeNo() == null ? "" : getEmployeeNo());
        sb.append(",");
        sb.append(getEmployeeName() == null ? "" : getEmployeeName());
        sb.append(",");
        sb.append(getDepartmentId() == null ? "" : getDepartmentId());
        sb.append(",");
        sb.append(getRoleId() == null ? "" : getRoleId());
        sb.append(",");
        sb.append(getGradeId() == null ? "" : getGradeId());
        sb.append(",");
        sb.append(getEmployeePassword() == null ? "" : getEmployeePassword());
        sb.append(",");
        sb.append(getMobile() == null ? "" : getMobile());
        sb.append(",");
        sb.append(getPhone() == null ? "" : getPhone());
        sb.append(",");
        sb.append(getQq() == null ? "" : getQq());
        sb.append(",");
        sb.append(getEmail() == null ? "" : getEmail());
        sb.append(",");
        sb.append(getOnboardDate() == null ? "" : sdf.format(getOnboardDate()));
        sb.append(",");
        sb.append(getResignationDate() == null ? "" : sdf.format(getResignationDate()));
        sb.append(",");
        sb.append(getStatus() == null ? "" : getStatus());
        sb.append(",");
        sb.append(getUsableStatus() == null ? "" : getUsableStatus());
        sb.append(",");
        sb.append(getIsDepartment() == null ? "" : getIsDepartment());
        sb.append(",");
        sb.append(getPhoto() == null ? "" : getPhoto());
        sb.append(",");
        sb.append(getGender() == null ? "" : getGender());
        sb.append(",");
        sb.append(getAutograph() == null ? "" : getAutograph());
        sb.append(",");
        sb.append(getAge() == null ? "" : getAge());
        sb.append(",");
        sb.append(getBirth() == null ? "" : sdf.format(getBirth()));
        sb.append(",");
        sb.append(getCard() == null ? "" : getCard());
        sb.append(",");
        sb.append(getAddress() == null ? "" : getAddress());
        sb.append(",");
        sb.append(getAlternateField1() == null ? "" : getAlternateField1());
        sb.append(",");
        sb.append(getAlternateField2() == null ? "" : getAlternateField2());
        sb.append(",");
        sb.append(getAlternateField3() == null ? "" : getAlternateField3());
        sb.append(",");
        sb.append(getLocked() == null ? "" : getLocked());
        sb.append(",");
        sb.append(getPlateId() == null ? "" : getPlateId());
        sb.append(",");
        sb.append(getDutyId() == null ? "" : getDutyId());
        sb.append(",");
        sb.append(getUserAcct() == null ? "" : getUserAcct());
        sb.append(",");
        sb.append(getEmployeeNameEn() == null ? "" : getEmployeeNameEn());
        sb.append(",");
        sb.append(getEducation() == null ? "" : getEducation());
        sb.append(",");
        sb.append(getDegree() == null ? "" : getDegree());
        sb.append(",");
        sb.append(getNationality() == null ? "" : getNationality());
        sb.append(",");
        sb.append(getMarriedStatus() == null ? "" : getMarriedStatus());
        sb.append(",");
        sb.append(getHealth() == null ? "" : getHealth());
        sb.append(",");
        sb.append(getWorkAddress() == null ? "" : getWorkAddress());
        sb.append(",");
        sb.append(getRegisteredAddress() == null ? "" : getRegisteredAddress());
        sb.append(",");
        sb.append(getOaId() == null ? "" : getOaId());
        sb.append(",");
        sb.append(getOaDepart() == null ? "" : getOaDepart());
        sb.append(",");
        sb.append(getIsHeadcount() == null ? "" : getIsHeadcount());
        sb.append(",");
        sb.append(getIsCheck() == null ? "" : getIsCheck());
        sb.append(",");
        sb.append(getDirectLeader() == null ? "" : getDirectLeader());
        sb.append(",");
        sb.append(getIsManager() == null ? "" : getIsManager());
        sb.append(",");
        sb.append(getPoliticalFace() == null ? "" : getPoliticalFace());
        sb.append(",");
        sb.append(getBirthplace() == null ? "" : getBirthplace());
        sb.append(",");
        sb.append(getCountry() == null ? "" : getCountry());
        sb.append(",");
        sb.append(getAccountLocation() == null ? "" : getAccountLocation());
        sb.append(",");
        sb.append(getLanguages() == null ? "" : getLanguages());
        sb.append(",");
        sb.append(getStartWorkDate() == null ? "" : sdf.format(getStartWorkDate()));
        sb.append(",");
        sb.append(getSocialComputerNumber() == null ? "" : getSocialComputerNumber());
        sb.append(",");
        sb.append(getFundAccount() == null ? "" : getFundAccount());
        sb.append(",");
        sb.append(getPositiveDate() == null ? "" : sdf.format(getPositiveDate()));
        sb.append(",");
        sb.append(getTryTime() == null ? "" : getTryTime());
        sb.append(",");
        sb.append(getContractStartDate() == null ? "" : sdf.format(getContractStartDate()));
        sb.append(",");
        sb.append(getContractEndDate() == null ? "" : sdf.format(getContractEndDate()));
        sb.append(",");
        sb.append(getOwnedCompany() == null ? "" : getOwnedCompany());
        sb.append(",");
        sb.append(getJobs() == null ? "" : getJobs());
        sb.append(",");
        sb.append(getPersonalBusinessRemark() == null ? "" : getPersonalBusinessRemark());
        sb.append(",");
        sb.append(getSelfIntroduction() == null ? "" : getSelfIntroduction());
        sb.append(",");
        sb.append(getLaborAttachments() == null ? "" : getLaborAttachments());
        sb.append(",");
        sb.append(getEmergencyContactPerson() == null ? "" : getEmergencyContactPerson());
        sb.append(",");
        sb.append(getEmergencyContactPhone() == null ? "" : getEmergencyContactPhone());
        sb.append(",");
        sb.append(getHomePhone() == null ? "" : getHomePhone());
        sb.append(",");
        sb.append(getNowAddress() == null ? "" : getNowAddress());
        sb.append(",");
        sb.append(getTryTimePay() == null ? "" : getTryTimePay());
        sb.append(",");
        sb.append(getPositivePay() == null ? "" : getPositivePay());
        sb.append(",");
        sb.append(getApplyEmployeeId() == null ? "" : getApplyEmployeeId());
        sb.append(",");
        sb.append(getBankCardNum() == null ? "" : getBankCardNum());
        sb.append(",");
        sb.append(getFileNumber() == null ? "" : getFileNumber());
        sb.append(",");
        sb.append(getHouseholdRegistration() == null ? "" : getHouseholdRegistration());
        sb.append(",");
        sb.append(getForeman() == null ? "" : getForeman());
        sb.append(",");
        sb.append(getCardAttachment() == null ? "" : getCardAttachment());
        sb.append(",");
        sb.append(getTechnicalAttachment() == null ? "" : getTechnicalAttachment());
        sb.append(",");
        sb.append(getEducationProof() == null ? "" : getEducationProof());
        sb.append(",");
        sb.append(getDegreeProof() == null ? "" : getDegreeProof());
        sb.append(",");
        sb.append(getEmployeeShift() == null ? "" : getEmployeeShift());
        sb.append(",");
        sb.append(getCardAddress() == null ? "" : getCardAddress());
        sb.append(",");
        sb.append(getAnnualPerformance() == null ? "" : getAnnualPerformance());
        sb.append(",");
        sb.append(getAnnualBonus() == null ? "" : getAnnualBonus());
        sb.append(",");
        sb.append(getCompanyWeixin() == null ? "" : getCompanyWeixin());
        sb.append(",");
        sb.append(getCompanyEmail() == null ? "" : getCompanyEmail());
        sb.append(",");
        sb.append(getCompanyId() == null ? "" : getCompanyId());
        sb.append(",");
        sb.append(getEcmcUserId() == null ? "" : getEcmcUserId());
        sb.append(",");
        sb.append(getOnboardStatus() == null ? "" : getOnboardStatus());
        sb.append(",");
        sb.append(getAttachmentRemark() == null ? "" : getAttachmentRemark());
        sb.append(",");
        sb.append(getWorkYearType() == null ? "" : getWorkYearType());
        sb.append(",");
        sb.append(getIsbusy() == null ? "" : getIsbusy());
        sb.append(",");
        sb.append(getForeignerIdCard() == null ? "" : getForeignerIdCard());
        sb.append(",");
        sb.append(getRealBirthdate() == null ? "" : sdf.format(getRealBirthdate()));
        sb.append(",");
        sb.append(getWorkYear() == null ? "" : getWorkYear());
        sb.append(",");
        sb.append(getShenzhenHouse() == null ? "" : getShenzhenHouse());
        sb.append(",");
        sb.append(getTransHouseholdDate() == null ? "" : sdf.format(getTransHouseholdDate()));
        sb.append(",");
        sb.append(getProfessionalDirection() == null ? "" : getProfessionalDirection());
        sb.append(",");
        sb.append(getRecruitmentSources() == null ? "" : getRecruitmentSources());
        sb.append(",");
        sb.append(getWechatNum() == null ? "" : getWechatNum());
        sb.append(",");
        sb.append(getAutoSignYear() == null ? "" : getAutoSignYear());
        sb.append(",");
        sb.append(getResumeAttachment() == null ? "" : getResumeAttachment());
        sb.append(",");
        sb.append(getSignatureAttachment() == null ? "" : getSignatureAttachment());
        sb.append(",");
        sb.append(getSocialAttachment() == null ? "" : getSocialAttachment());
        sb.append(",");
        sb.append(getFundAttachment() == null ? "" : getFundAttachment());
        sb.append(",");
        sb.append(getSpareAttachment1() == null ? "" : getSpareAttachment1());
        sb.append(",");
        sb.append(getSpareAttachment2() == null ? "" : getSpareAttachment2());
        sb.append(",");
        sb.append(getSpareAttachment3() == null ? "" : getSpareAttachment3());
        sb.append(",");
        sb.append(getEmployeeType() == null ? "" : getEmployeeType());
        sb.append(",");
        sb.append(getIsLongSickLeave() == null ? "" : getIsLongSickLeave());
        sb.append(",");
        sb.append(getBasicSalary() == null ? "" : getBasicSalary());
        sb.append(",");
        sb.append(getJobTitleAllowance() == null ? "" : getJobTitleAllowance());
        sb.append(",");
        sb.append(getPostAllowance() == null ? "" : getPostAllowance());
        sb.append(",");
        sb.append(getOther() == null ? "" : getOther());
        sb.append(",");
        sb.append(getCommunicationSubsidy() == null ? "" : getCommunicationSubsidy());
        sb.append(",");
        sb.append(getSubtotalMonthlyWage() == null ? "" : getSubtotalMonthlyWage());
        sb.append(",");
        sb.append(getMonthlyPreissuedBonus() == null ? "" : getMonthlyPreissuedBonus());
        sb.append(",");
        sb.append(getTotalMonthlyRemuneration() == null ? "" : getTotalMonthlyRemuneration());
        sb.append(",");
        sb.append(getPhotoId() == null ? "" : getPhotoId());
        sb.append(",");
        sb.append(getLaborAttachmentId() == null ? "" : getLaborAttachmentId());
        sb.append(",");
        sb.append(getCardAttachmentId() == null ? "" : getCardAttachmentId());
        sb.append(",");
        sb.append(getTechnicalAttachmentId() == null ? "" : getTechnicalAttachmentId());
        sb.append(",");
        sb.append(getEducationProofId() == null ? "" : getEducationProofId());
        sb.append(",");
        sb.append(getDegreeProofId() == null ? "" : getDegreeProofId());
        sb.append(",");
        sb.append(getBankCardAttachment() == null ? "" : getBankCardAttachment());
        sb.append(",");
        sb.append(getBankCardAttachmentId() == null ? "" : getBankCardAttachmentId());
        return sb.toString();
    }

    @Override
    public int compareTo(BaseEmployee o) {
        return __employee_id == null ? -1 : __employee_id.compareTo(o.getEmployeeId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.__employee_id);
        hash = 97 * hash + Objects.hashCode(this.__employee_no);
        hash = 97 * hash + Objects.hashCode(this.__employee_name);
        hash = 97 * hash + Objects.hashCode(this.__department_id);
        hash = 97 * hash + Objects.hashCode(this.__role_id);
        hash = 97 * hash + Objects.hashCode(this.__grade_id);
        hash = 97 * hash + Objects.hashCode(this.__employee_password);
        hash = 97 * hash + Objects.hashCode(this.__mobile);
        hash = 97 * hash + Objects.hashCode(this.__phone);
        hash = 97 * hash + Objects.hashCode(this.__qq);
        hash = 97 * hash + Objects.hashCode(this.__email);
        hash = 97 * hash + Objects.hashCode(this.__onboard_date);
        hash = 97 * hash + Objects.hashCode(this.__resignation_date);
        hash = 97 * hash + Objects.hashCode(this.__status);
        hash = 97 * hash + Objects.hashCode(this.__usable_status);
        hash = 97 * hash + Objects.hashCode(this.__is_department);
        hash = 97 * hash + Objects.hashCode(this.__photo);
        hash = 97 * hash + Objects.hashCode(this.__gender);
        hash = 97 * hash + Objects.hashCode(this.__autograph);
        hash = 97 * hash + Objects.hashCode(this.__age);
        hash = 97 * hash + Objects.hashCode(this.__birth);
        hash = 97 * hash + Objects.hashCode(this.__card);
        hash = 97 * hash + Objects.hashCode(this.__address);
        hash = 97 * hash + Objects.hashCode(this.__alternate_field1);
        hash = 97 * hash + Objects.hashCode(this.__alternate_field2);
        hash = 97 * hash + Objects.hashCode(this.__alternate_field3);
        hash = 97 * hash + Objects.hashCode(this.__locked);
        hash = 97 * hash + Objects.hashCode(this.__plate_id);
        hash = 97 * hash + Objects.hashCode(this.__duty_id);
        hash = 97 * hash + Objects.hashCode(this.__user_acct);
        hash = 97 * hash + Objects.hashCode(this.__employee_name_en);
        hash = 97 * hash + Objects.hashCode(this.__education);
        hash = 97 * hash + Objects.hashCode(this.__degree);
        hash = 97 * hash + Objects.hashCode(this.__nationality);
        hash = 97 * hash + Objects.hashCode(this.__married_status);
        hash = 97 * hash + Objects.hashCode(this.__health);
        hash = 97 * hash + Objects.hashCode(this.__work_address);
        hash = 97 * hash + Objects.hashCode(this.__registered_address);
        hash = 97 * hash + Objects.hashCode(this.__oa_id);
        hash = 97 * hash + Objects.hashCode(this.__oa_depart);
        hash = 97 * hash + Objects.hashCode(this.__is_headcount);
        hash = 97 * hash + Objects.hashCode(this.__is_check);
        hash = 97 * hash + Objects.hashCode(this.__direct_leader);
        hash = 97 * hash + Objects.hashCode(this.__is_manager);
        hash = 97 * hash + Objects.hashCode(this.__political_face);
        hash = 97 * hash + Objects.hashCode(this.__birthplace);
        hash = 97 * hash + Objects.hashCode(this.__country);
        hash = 97 * hash + Objects.hashCode(this.__account_location);
        hash = 97 * hash + Objects.hashCode(this.__languages);
        hash = 97 * hash + Objects.hashCode(this.__start_work_date);
        hash = 97 * hash + Objects.hashCode(this.__social_computer_number);
        hash = 97 * hash + Objects.hashCode(this.__fund_account);
        hash = 97 * hash + Objects.hashCode(this.__positive_date);
        hash = 97 * hash + Objects.hashCode(this.__try_time);
        hash = 97 * hash + Objects.hashCode(this.__contract_start_date);
        hash = 97 * hash + Objects.hashCode(this.__contract_end_date);
        hash = 97 * hash + Objects.hashCode(this.__owned_company);
        hash = 97 * hash + Objects.hashCode(this.__jobs);
        hash = 97 * hash + Objects.hashCode(this.__personal_business_remark);
        hash = 97 * hash + Objects.hashCode(this.__self_introduction);
        hash = 97 * hash + Objects.hashCode(this.__labor_attachments);
        hash = 97 * hash + Objects.hashCode(this.__emergency_contact_person);
        hash = 97 * hash + Objects.hashCode(this.__emergency_contact_phone);
        hash = 97 * hash + Objects.hashCode(this.__home_phone);
        hash = 97 * hash + Objects.hashCode(this.__now_address);
        hash = 97 * hash + Objects.hashCode(this.__try_time_pay);
        hash = 97 * hash + Objects.hashCode(this.__positive_pay);
        hash = 97 * hash + Objects.hashCode(this.__apply_employee_id);
        hash = 97 * hash + Objects.hashCode(this.__bank_card_num);
        hash = 97 * hash + Objects.hashCode(this.__file_number);
        hash = 97 * hash + Objects.hashCode(this.__household_registration);
        hash = 97 * hash + Objects.hashCode(this.__foreman);
        hash = 97 * hash + Objects.hashCode(this.__card_attachment);
        hash = 97 * hash + Objects.hashCode(this.__technical_attachment);
        hash = 97 * hash + Objects.hashCode(this.__education_proof);
        hash = 97 * hash + Objects.hashCode(this.__degree_proof);
        hash = 97 * hash + Objects.hashCode(this.__employee_shift);
        hash = 97 * hash + Objects.hashCode(this.__card_address);
        hash = 97 * hash + Objects.hashCode(this.__annual_performance);
        hash = 97 * hash + Objects.hashCode(this.__annual_bonus);
        hash = 97 * hash + Objects.hashCode(this.__company_weixin);
        hash = 97 * hash + Objects.hashCode(this.__company_email);
        hash = 97 * hash + Objects.hashCode(this.__company_id);
        hash = 97 * hash + Objects.hashCode(this.__ecmc_user_id);
        hash = 97 * hash + Objects.hashCode(this.__onboard_status);
        hash = 97 * hash + Objects.hashCode(this.__attachment_remark);
        hash = 97 * hash + Objects.hashCode(this.__work_year_type);
        hash = 97 * hash + Objects.hashCode(this.__isbusy);
        hash = 97 * hash + Objects.hashCode(this.__foreigner_id_card);
        hash = 97 * hash + Objects.hashCode(this.__real_birthdate);
        hash = 97 * hash + Objects.hashCode(this.__work_year);
        hash = 97 * hash + Objects.hashCode(this.__shenzhen_house);
        hash = 97 * hash + Objects.hashCode(this.__trans_household_date);
        hash = 97 * hash + Objects.hashCode(this.__professional_direction);
        hash = 97 * hash + Objects.hashCode(this.__recruitment_sources);
        hash = 97 * hash + Objects.hashCode(this.__wechat_num);
        hash = 97 * hash + Objects.hashCode(this.__auto_sign_year);
        hash = 97 * hash + Objects.hashCode(this.__resume_attachment);
        hash = 97 * hash + Objects.hashCode(this.__signature_attachment);
        hash = 97 * hash + Objects.hashCode(this.__social_attachment);
        hash = 97 * hash + Objects.hashCode(this.__fund_attachment);
        hash = 97 * hash + Objects.hashCode(this.__spare_attachment1);
        hash = 97 * hash + Objects.hashCode(this.__spare_attachment2);
        hash = 97 * hash + Objects.hashCode(this.__spare_attachment3);
        hash = 97 * hash + Objects.hashCode(this.__employee_type);
        hash = 97 * hash + Objects.hashCode(this.__is_long_sick_leave);
        hash = 97 * hash + Objects.hashCode(this.__basic_salary);
        hash = 97 * hash + Objects.hashCode(this.__job_title_allowance);
        hash = 97 * hash + Objects.hashCode(this.__post_allowance);
        hash = 97 * hash + Objects.hashCode(this.__other);
        hash = 97 * hash + Objects.hashCode(this.__communication_subsidy);
        hash = 97 * hash + Objects.hashCode(this.__subtotal_monthly_wage);
        hash = 97 * hash + Objects.hashCode(this.__monthly_preissued_bonus);
        hash = 97 * hash + Objects.hashCode(this.__total_monthly_remuneration);
        hash = 97 * hash + Objects.hashCode(this.__photo_id);
        hash = 97 * hash + Objects.hashCode(this.__labor_attachment_id);
        hash = 97 * hash + Objects.hashCode(this.__card_attachment_id);
        hash = 97 * hash + Objects.hashCode(this.__technical_attachment_id);
        hash = 97 * hash + Objects.hashCode(this.__education_proof_id);
        hash = 97 * hash + Objects.hashCode(this.__degree_proof_id);
        hash = 97 * hash + Objects.hashCode(this.__bank_card_attachment);
        hash = 97 * hash + Objects.hashCode(this.__bank_card_attachment_id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BaseEmployee o = (BaseEmployee) obj;
        if (!Objects.equals(this.__employee_id, o.getEmployeeId())) {
            return false;
        }
        if (!Objects.equals(this.__employee_no, o.getEmployeeNo())) {
            return false;
        }
        if (!Objects.equals(this.__employee_name, o.getEmployeeName())) {
            return false;
        }
        if (!Objects.equals(this.__department_id, o.getDepartmentId())) {
            return false;
        }
        if (!Objects.equals(this.__role_id, o.getRoleId())) {
            return false;
        }
        if (!Objects.equals(this.__grade_id, o.getGradeId())) {
            return false;
        }
        if (!Objects.equals(this.__employee_password, o.getEmployeePassword())) {
            return false;
        }
        if (!Objects.equals(this.__mobile, o.getMobile())) {
            return false;
        }
        if (!Objects.equals(this.__phone, o.getPhone())) {
            return false;
        }
        if (!Objects.equals(this.__qq, o.getQq())) {
            return false;
        }
        if (!Objects.equals(this.__email, o.getEmail())) {
            return false;
        }
        if (!Objects.equals(this.__onboard_date, o.getOnboardDate())) {
            return false;
        }
        if (!Objects.equals(this.__resignation_date, o.getResignationDate())) {
            return false;
        }
        if (!Objects.equals(this.__status, o.getStatus())) {
            return false;
        }
        if (!Objects.equals(this.__usable_status, o.getUsableStatus())) {
            return false;
        }
        if (!Objects.equals(this.__is_department, o.getIsDepartment())) {
            return false;
        }
        if (!Objects.equals(this.__photo, o.getPhoto())) {
            return false;
        }
        if (!Objects.equals(this.__gender, o.getGender())) {
            return false;
        }
        if (!Objects.equals(this.__autograph, o.getAutograph())) {
            return false;
        }
        if (!Objects.equals(this.__age, o.getAge())) {
            return false;
        }
        if (!Objects.equals(this.__birth, o.getBirth())) {
            return false;
        }
        if (!Objects.equals(this.__card, o.getCard())) {
            return false;
        }
        if (!Objects.equals(this.__address, o.getAddress())) {
            return false;
        }
        if (!Objects.equals(this.__alternate_field1, o.getAlternateField1())) {
            return false;
        }
        if (!Objects.equals(this.__alternate_field2, o.getAlternateField2())) {
            return false;
        }
        if (!Objects.equals(this.__alternate_field3, o.getAlternateField3())) {
            return false;
        }
        if (!Objects.equals(this.__locked, o.getLocked())) {
            return false;
        }
        if (!Objects.equals(this.__plate_id, o.getPlateId())) {
            return false;
        }
        if (!Objects.equals(this.__duty_id, o.getDutyId())) {
            return false;
        }
        if (!Objects.equals(this.__user_acct, o.getUserAcct())) {
            return false;
        }
        if (!Objects.equals(this.__employee_name_en, o.getEmployeeNameEn())) {
            return false;
        }
        if (!Objects.equals(this.__education, o.getEducation())) {
            return false;
        }
        if (!Objects.equals(this.__degree, o.getDegree())) {
            return false;
        }
        if (!Objects.equals(this.__nationality, o.getNationality())) {
            return false;
        }
        if (!Objects.equals(this.__married_status, o.getMarriedStatus())) {
            return false;
        }
        if (!Objects.equals(this.__health, o.getHealth())) {
            return false;
        }
        if (!Objects.equals(this.__work_address, o.getWorkAddress())) {
            return false;
        }
        if (!Objects.equals(this.__registered_address, o.getRegisteredAddress())) {
            return false;
        }
        if (!Objects.equals(this.__oa_id, o.getOaId())) {
            return false;
        }
        if (!Objects.equals(this.__oa_depart, o.getOaDepart())) {
            return false;
        }
        if (!Objects.equals(this.__is_headcount, o.getIsHeadcount())) {
            return false;
        }
        if (!Objects.equals(this.__is_check, o.getIsCheck())) {
            return false;
        }
        if (!Objects.equals(this.__direct_leader, o.getDirectLeader())) {
            return false;
        }
        if (!Objects.equals(this.__is_manager, o.getIsManager())) {
            return false;
        }
        if (!Objects.equals(this.__political_face, o.getPoliticalFace())) {
            return false;
        }
        if (!Objects.equals(this.__birthplace, o.getBirthplace())) {
            return false;
        }
        if (!Objects.equals(this.__country, o.getCountry())) {
            return false;
        }
        if (!Objects.equals(this.__account_location, o.getAccountLocation())) {
            return false;
        }
        if (!Objects.equals(this.__languages, o.getLanguages())) {
            return false;
        }
        if (!Objects.equals(this.__start_work_date, o.getStartWorkDate())) {
            return false;
        }
        if (!Objects.equals(this.__social_computer_number, o.getSocialComputerNumber())) {
            return false;
        }
        if (!Objects.equals(this.__fund_account, o.getFundAccount())) {
            return false;
        }
        if (!Objects.equals(this.__positive_date, o.getPositiveDate())) {
            return false;
        }
        if (!Objects.equals(this.__try_time, o.getTryTime())) {
            return false;
        }
        if (!Objects.equals(this.__contract_start_date, o.getContractStartDate())) {
            return false;
        }
        if (!Objects.equals(this.__contract_end_date, o.getContractEndDate())) {
            return false;
        }
        if (!Objects.equals(this.__owned_company, o.getOwnedCompany())) {
            return false;
        }
        if (!Objects.equals(this.__jobs, o.getJobs())) {
            return false;
        }
        if (!Objects.equals(this.__personal_business_remark, o.getPersonalBusinessRemark())) {
            return false;
        }
        if (!Objects.equals(this.__self_introduction, o.getSelfIntroduction())) {
            return false;
        }
        if (!Objects.equals(this.__labor_attachments, o.getLaborAttachments())) {
            return false;
        }
        if (!Objects.equals(this.__emergency_contact_person, o.getEmergencyContactPerson())) {
            return false;
        }
        if (!Objects.equals(this.__emergency_contact_phone, o.getEmergencyContactPhone())) {
            return false;
        }
        if (!Objects.equals(this.__home_phone, o.getHomePhone())) {
            return false;
        }
        if (!Objects.equals(this.__now_address, o.getNowAddress())) {
            return false;
        }
        if (!Objects.equals(this.__try_time_pay, o.getTryTimePay())) {
            return false;
        }
        if (!Objects.equals(this.__positive_pay, o.getPositivePay())) {
            return false;
        }
        if (!Objects.equals(this.__apply_employee_id, o.getApplyEmployeeId())) {
            return false;
        }
        if (!Objects.equals(this.__bank_card_num, o.getBankCardNum())) {
            return false;
        }
        if (!Objects.equals(this.__file_number, o.getFileNumber())) {
            return false;
        }
        if (!Objects.equals(this.__household_registration, o.getHouseholdRegistration())) {
            return false;
        }
        if (!Objects.equals(this.__foreman, o.getForeman())) {
            return false;
        }
        if (!Objects.equals(this.__card_attachment, o.getCardAttachment())) {
            return false;
        }
        if (!Objects.equals(this.__technical_attachment, o.getTechnicalAttachment())) {
            return false;
        }
        if (!Objects.equals(this.__education_proof, o.getEducationProof())) {
            return false;
        }
        if (!Objects.equals(this.__degree_proof, o.getDegreeProof())) {
            return false;
        }
        if (!Objects.equals(this.__employee_shift, o.getEmployeeShift())) {
            return false;
        }
        if (!Objects.equals(this.__card_address, o.getCardAddress())) {
            return false;
        }
        if (!Objects.equals(this.__annual_performance, o.getAnnualPerformance())) {
            return false;
        }
        if (!Objects.equals(this.__annual_bonus, o.getAnnualBonus())) {
            return false;
        }
        if (!Objects.equals(this.__company_weixin, o.getCompanyWeixin())) {
            return false;
        }
        if (!Objects.equals(this.__company_email, o.getCompanyEmail())) {
            return false;
        }
        if (!Objects.equals(this.__company_id, o.getCompanyId())) {
            return false;
        }
        if (!Objects.equals(this.__ecmc_user_id, o.getEcmcUserId())) {
            return false;
        }
        if (!Objects.equals(this.__onboard_status, o.getOnboardStatus())) {
            return false;
        }
        if (!Objects.equals(this.__attachment_remark, o.getAttachmentRemark())) {
            return false;
        }
        if (!Objects.equals(this.__work_year_type, o.getWorkYearType())) {
            return false;
        }
        if (!Objects.equals(this.__isbusy, o.getIsbusy())) {
            return false;
        }
        if (!Objects.equals(this.__foreigner_id_card, o.getForeignerIdCard())) {
            return false;
        }
        if (!Objects.equals(this.__real_birthdate, o.getRealBirthdate())) {
            return false;
        }
        if (!Objects.equals(this.__work_year, o.getWorkYear())) {
            return false;
        }
        if (!Objects.equals(this.__shenzhen_house, o.getShenzhenHouse())) {
            return false;
        }
        if (!Objects.equals(this.__trans_household_date, o.getTransHouseholdDate())) {
            return false;
        }
        if (!Objects.equals(this.__professional_direction, o.getProfessionalDirection())) {
            return false;
        }
        if (!Objects.equals(this.__recruitment_sources, o.getRecruitmentSources())) {
            return false;
        }
        if (!Objects.equals(this.__wechat_num, o.getWechatNum())) {
            return false;
        }
        if (!Objects.equals(this.__auto_sign_year, o.getAutoSignYear())) {
            return false;
        }
        if (!Objects.equals(this.__resume_attachment, o.getResumeAttachment())) {
            return false;
        }
        if (!Objects.equals(this.__signature_attachment, o.getSignatureAttachment())) {
            return false;
        }
        if (!Objects.equals(this.__social_attachment, o.getSocialAttachment())) {
            return false;
        }
        if (!Objects.equals(this.__fund_attachment, o.getFundAttachment())) {
            return false;
        }
        if (!Objects.equals(this.__spare_attachment1, o.getSpareAttachment1())) {
            return false;
        }
        if (!Objects.equals(this.__spare_attachment2, o.getSpareAttachment2())) {
            return false;
        }
        if (!Objects.equals(this.__spare_attachment3, o.getSpareAttachment3())) {
            return false;
        }
        if (!Objects.equals(this.__employee_type, o.getEmployeeType())) {
            return false;
        }
        if (!Objects.equals(this.__is_long_sick_leave, o.getIsLongSickLeave())) {
            return false;
        }
        if (!Objects.equals(this.__basic_salary, o.getBasicSalary())) {
            return false;
        }
        if (!Objects.equals(this.__job_title_allowance, o.getJobTitleAllowance())) {
            return false;
        }
        if (!Objects.equals(this.__post_allowance, o.getPostAllowance())) {
            return false;
        }
        if (!Objects.equals(this.__other, o.getOther())) {
            return false;
        }
        if (!Objects.equals(this.__communication_subsidy, o.getCommunicationSubsidy())) {
            return false;
        }
        if (!Objects.equals(this.__subtotal_monthly_wage, o.getSubtotalMonthlyWage())) {
            return false;
        }
        if (!Objects.equals(this.__monthly_preissued_bonus, o.getMonthlyPreissuedBonus())) {
            return false;
        }
        if (!Objects.equals(this.__total_monthly_remuneration, o.getTotalMonthlyRemuneration())) {
            return false;
        }
        if (!Objects.equals(this.__photo_id, o.getPhotoId())) {
            return false;
        }
        if (!Objects.equals(this.__labor_attachment_id, o.getLaborAttachmentId())) {
            return false;
        }
        if (!Objects.equals(this.__card_attachment_id, o.getCardAttachmentId())) {
            return false;
        }
        if (!Objects.equals(this.__technical_attachment_id, o.getTechnicalAttachmentId())) {
            return false;
        }
        if (!Objects.equals(this.__education_proof_id, o.getEducationProofId())) {
            return false;
        }
        if (!Objects.equals(this.__degree_proof_id, o.getDegreeProofId())) {
            return false;
        }
        if (!Objects.equals(this.__bank_card_attachment, o.getBankCardAttachment())) {
            return false;
        }
        if (!Objects.equals(this.__bank_card_attachment_id, o.getBankCardAttachmentId())) {
            return false;
        }
        return true;
    }

    @Override
    public java.lang.String toJSONString() {

        StringBuilder sb = new StringBuilder();
        int count = 0;
        if (getEmployeeId() != null) {
            sb.append(__wrapNumber(count++, "employeeId", getEmployeeId()));
        }
        if (getEmployeeNo() != null) {
            sb.append(__wrapString(count++, "employeeNo", getEmployeeNo()));
        }
        if (getEmployeeName() != null) {
            sb.append(__wrapString(count++, "employeeName", getEmployeeName()));
        }
        if (getDepartmentId() != null) {
            sb.append(__wrapNumber(count++, "departmentId", getDepartmentId()));
        }
        if (getRoleId() != null) {
            sb.append(__wrapNumber(count++, "roleId", getRoleId()));
        }
        if (getGradeId() != null) {
            sb.append(__wrapNumber(count++, "gradeId", getGradeId()));
        }
        if (getEmployeePassword() != null) {
            sb.append(__wrapString(count++, "employeePassword", getEmployeePassword()));
        }
        if (getMobile() != null) {
            sb.append(__wrapString(count++, "mobile", getMobile()));
        }
        if (getPhone() != null) {
            sb.append(__wrapString(count++, "phone", getPhone()));
        }
        if (getQq() != null) {
            sb.append(__wrapString(count++, "qq", getQq()));
        }
        if (getEmail() != null) {
            sb.append(__wrapString(count++, "email", getEmail()));
        }
        if (getOnboardDate() != null) {
            sb.append(__wrapDate(count++, "onboardDate", getOnboardDate()));
        }
        if (getResignationDate() != null) {
            sb.append(__wrapDate(count++, "resignationDate", getResignationDate()));
        }
        if (getStatus() != null) {
            sb.append(__wrapNumber(count++, "status", getStatus()));
        }
        if (getUsableStatus() != null) {
            sb.append(__wrapBoolean(count++, "usableStatus", getUsableStatus()));
        }
        if (getIsDepartment() != null) {
            sb.append(__wrapBoolean(count++, "isDepartment", getIsDepartment()));
        }
        if (getPhoto() != null) {
            sb.append(__wrapString(count++, "photo", getPhoto()));
        }
        if (getGender() != null) {
            sb.append(__wrapNumber(count++, "gender", getGender()));
        }
        if (getAutograph() != null) {
            sb.append(__wrapString(count++, "autograph", getAutograph()));
        }
        if (getAge() != null) {
            sb.append(__wrapNumber(count++, "age", getAge()));
        }
        if (getBirth() != null) {
            sb.append(__wrapDate(count++, "birth", getBirth()));
        }
        if (getCard() != null) {
            sb.append(__wrapString(count++, "card", getCard()));
        }
        if (getAddress() != null) {
            sb.append(__wrapString(count++, "address", getAddress()));
        }
        if (getAlternateField1() != null) {
            sb.append(__wrapString(count++, "alternateField1", getAlternateField1()));
        }
        if (getAlternateField2() != null) {
            sb.append(__wrapString(count++, "alternateField2", getAlternateField2()));
        }
        if (getAlternateField3() != null) {
            sb.append(__wrapString(count++, "alternateField3", getAlternateField3()));
        }
        if (getLocked() != null) {
            sb.append(__wrapBoolean(count++, "locked", getLocked()));
        }
        if (getPlateId() != null) {
            sb.append(__wrapNumber(count++, "plateId", getPlateId()));
        }
        if (getDutyId() != null) {
            sb.append(__wrapNumber(count++, "dutyId", getDutyId()));
        }
        if (getUserAcct() != null) {
            sb.append(__wrapString(count++, "userAcct", getUserAcct()));
        }
        if (getEmployeeNameEn() != null) {
            sb.append(__wrapString(count++, "employeeNameEn", getEmployeeNameEn()));
        }
        if (getEducation() != null) {
            sb.append(__wrapString(count++, "education", getEducation()));
        }
        if (getDegree() != null) {
            sb.append(__wrapString(count++, "degree", getDegree()));
        }
        if (getNationality() != null) {
            sb.append(__wrapString(count++, "nationality", getNationality()));
        }
        if (getMarriedStatus() != null) {
            sb.append(__wrapString(count++, "marriedStatus", getMarriedStatus()));
        }
        if (getHealth() != null) {
            sb.append(__wrapString(count++, "health", getHealth()));
        }
        if (getWorkAddress() != null) {
            sb.append(__wrapString(count++, "workAddress", getWorkAddress()));
        }
        if (getRegisteredAddress() != null) {
            sb.append(__wrapString(count++, "registeredAddress", getRegisteredAddress()));
        }
        if (getOaId() != null) {
            sb.append(__wrapNumber(count++, "oaId", getOaId()));
        }
        if (getOaDepart() != null) {
            sb.append(__wrapNumber(count++, "oaDepart", getOaDepart()));
        }
        if (getIsHeadcount() != null) {
            sb.append(__wrapBoolean(count++, "isHeadcount", getIsHeadcount()));
        }
        if (getIsCheck() != null) {
            sb.append(__wrapBoolean(count++, "isCheck", getIsCheck()));
        }
        if (getDirectLeader() != null) {
            sb.append(__wrapNumber(count++, "directLeader", getDirectLeader()));
        }
        if (getIsManager() != null) {
            sb.append(__wrapBoolean(count++, "isManager", getIsManager()));
        }
        if (getPoliticalFace() != null) {
            sb.append(__wrapNumber(count++, "politicalFace", getPoliticalFace()));
        }
        if (getBirthplace() != null) {
            sb.append(__wrapString(count++, "birthplace", getBirthplace()));
        }
        if (getCountry() != null) {
            sb.append(__wrapString(count++, "country", getCountry()));
        }
        if (getAccountLocation() != null) {
            sb.append(__wrapString(count++, "accountLocation", getAccountLocation()));
        }
        if (getLanguages() != null) {
            sb.append(__wrapString(count++, "languages", getLanguages()));
        }
        if (getStartWorkDate() != null) {
            sb.append(__wrapDate(count++, "startWorkDate", getStartWorkDate()));
        }
        if (getSocialComputerNumber() != null) {
            sb.append(__wrapString(count++, "socialComputerNumber", getSocialComputerNumber()));
        }
        if (getFundAccount() != null) {
            sb.append(__wrapString(count++, "fundAccount", getFundAccount()));
        }
        if (getPositiveDate() != null) {
            sb.append(__wrapDate(count++, "positiveDate", getPositiveDate()));
        }
        if (getTryTime() != null) {
            sb.append(__wrapString(count++, "tryTime", getTryTime()));
        }
        if (getContractStartDate() != null) {
            sb.append(__wrapDate(count++, "contractStartDate", getContractStartDate()));
        }
        if (getContractEndDate() != null) {
            sb.append(__wrapDate(count++, "contractEndDate", getContractEndDate()));
        }
        if (getOwnedCompany() != null) {
            sb.append(__wrapString(count++, "ownedCompany", getOwnedCompany()));
        }
        if (getJobs() != null) {
            sb.append(__wrapString(count++, "jobs", getJobs()));
        }
        if (getPersonalBusinessRemark() != null) {
            sb.append(__wrapString(count++, "personalBusinessRemark", getPersonalBusinessRemark()));
        }
        if (getSelfIntroduction() != null) {
            sb.append(__wrapString(count++, "selfIntroduction", getSelfIntroduction()));
        }
        if (getLaborAttachments() != null) {
            sb.append(__wrapString(count++, "laborAttachments", getLaborAttachments()));
        }
        if (getEmergencyContactPerson() != null) {
            sb.append(__wrapString(count++, "emergencyContactPerson", getEmergencyContactPerson()));
        }
        if (getEmergencyContactPhone() != null) {
            sb.append(__wrapString(count++, "emergencyContactPhone", getEmergencyContactPhone()));
        }
        if (getHomePhone() != null) {
            sb.append(__wrapString(count++, "homePhone", getHomePhone()));
        }
        if (getNowAddress() != null) {
            sb.append(__wrapString(count++, "nowAddress", getNowAddress()));
        }
        if (getTryTimePay() != null) {
            sb.append(__wrapString(count++, "tryTimePay", getTryTimePay()));
        }
        if (getPositivePay() != null) {
            sb.append(__wrapString(count++, "positivePay", getPositivePay()));
        }
        if (getApplyEmployeeId() != null) {
            sb.append(__wrapNumber(count++, "applyEmployeeId", getApplyEmployeeId()));
        }
        if (getBankCardNum() != null) {
            sb.append(__wrapString(count++, "bankCardNum", getBankCardNum()));
        }
        if (getFileNumber() != null) {
            sb.append(__wrapString(count++, "fileNumber", getFileNumber()));
        }
        if (getHouseholdRegistration() != null) {
            sb.append(__wrapNumber(count++, "householdRegistration", getHouseholdRegistration()));
        }
        if (getForeman() != null) {
            sb.append(__wrapString(count++, "foreman", getForeman()));
        }
        if (getCardAttachment() != null) {
            sb.append(__wrapString(count++, "cardAttachment", getCardAttachment()));
        }
        if (getTechnicalAttachment() != null) {
            sb.append(__wrapString(count++, "technicalAttachment", getTechnicalAttachment()));
        }
        if (getEducationProof() != null) {
            sb.append(__wrapString(count++, "educationProof", getEducationProof()));
        }
        if (getDegreeProof() != null) {
            sb.append(__wrapString(count++, "degreeProof", getDegreeProof()));
        }
        if (getEmployeeShift() != null) {
            sb.append(__wrapNumber(count++, "employeeShift", getEmployeeShift()));
        }
        if (getCardAddress() != null) {
            sb.append(__wrapString(count++, "cardAddress", getCardAddress()));
        }
        if (getAnnualPerformance() != null) {
            sb.append(__wrapString(count++, "annualPerformance", getAnnualPerformance()));
        }
        if (getAnnualBonus() != null) {
            sb.append(__wrapString(count++, "annualBonus", getAnnualBonus()));
        }
        if (getCompanyWeixin() != null) {
            sb.append(__wrapString(count++, "companyWeixin", getCompanyWeixin()));
        }
        if (getCompanyEmail() != null) {
            sb.append(__wrapString(count++, "companyEmail", getCompanyEmail()));
        }
        if (getCompanyId() != null) {
            sb.append(__wrapNumber(count++, "companyId", getCompanyId()));
        }
        if (getEcmcUserId() != null) {
            sb.append(__wrapNumber(count++, "ecmcUserId", getEcmcUserId()));
        }
        if (getOnboardStatus() != null) {
            sb.append(__wrapNumber(count++, "onboardStatus", getOnboardStatus()));
        }
        if (getAttachmentRemark() != null) {
            sb.append(__wrapString(count++, "attachmentRemark", getAttachmentRemark()));
        }
        if (getWorkYearType() != null) {
            sb.append(__wrapNumber(count++, "workYearType", getWorkYearType()));
        }
        if (getIsbusy() != null) {
            sb.append(__wrapNumber(count++, "isbusy", getIsbusy()));
        }
        if (getForeignerIdCard() != null) {
            sb.append(__wrapString(count++, "foreignerIdCard", getForeignerIdCard()));
        }
        if (getRealBirthdate() != null) {
            sb.append(__wrapDate(count++, "realBirthdate", getRealBirthdate()));
        }
        if (getWorkYear() != null) {
            sb.append(__wrapString(count++, "workYear", getWorkYear()));
        }
        if (getShenzhenHouse() != null) {
            sb.append(__wrapNumber(count++, "shenzhenHouse", getShenzhenHouse()));
        }
        if (getTransHouseholdDate() != null) {
            sb.append(__wrapDate(count++, "transHouseholdDate", getTransHouseholdDate()));
        }
        if (getProfessionalDirection() != null) {
            sb.append(__wrapNumber(count++, "professionalDirection", getProfessionalDirection()));
        }
        if (getRecruitmentSources() != null) {
            sb.append(__wrapNumber(count++, "recruitmentSources", getRecruitmentSources()));
        }
        if (getWechatNum() != null) {
            sb.append(__wrapString(count++, "wechatNum", getWechatNum()));
        }
        if (getAutoSignYear() != null) {
            sb.append(__wrapNumber(count++, "autoSignYear", getAutoSignYear()));
        }
        if (getResumeAttachment() != null) {
            sb.append(__wrapString(count++, "resumeAttachment", getResumeAttachment()));
        }
        if (getSignatureAttachment() != null) {
            sb.append(__wrapString(count++, "signatureAttachment", getSignatureAttachment()));
        }
        if (getSocialAttachment() != null) {
            sb.append(__wrapString(count++, "socialAttachment", getSocialAttachment()));
        }
        if (getFundAttachment() != null) {
            sb.append(__wrapString(count++, "fundAttachment", getFundAttachment()));
        }
        if (getSpareAttachment1() != null) {
            sb.append(__wrapString(count++, "spareAttachment1", getSpareAttachment1()));
        }
        if (getSpareAttachment2() != null) {
            sb.append(__wrapString(count++, "spareAttachment2", getSpareAttachment2()));
        }
        if (getSpareAttachment3() != null) {
            sb.append(__wrapString(count++, "spareAttachment3", getSpareAttachment3()));
        }
        if (getEmployeeType() != null) {
            sb.append(__wrapNumber(count++, "employeeType", getEmployeeType()));
        }
        if (getIsLongSickLeave() != null) {
            sb.append(__wrapBoolean(count++, "isLongSickLeave", getIsLongSickLeave()));
        }
        if (getBasicSalary() != null) {
            sb.append(__wrapDecimal(count++, "basicSalary", getBasicSalary()));
        }
        if (getJobTitleAllowance() != null) {
            sb.append(__wrapDecimal(count++, "jobTitleAllowance", getJobTitleAllowance()));
        }
        if (getPostAllowance() != null) {
            sb.append(__wrapDecimal(count++, "postAllowance", getPostAllowance()));
        }
        if (getOther() != null) {
            sb.append(__wrapDecimal(count++, "other", getOther()));
        }
        if (getCommunicationSubsidy() != null) {
            sb.append(__wrapDecimal(count++, "communicationSubsidy", getCommunicationSubsidy()));
        }
        if (getSubtotalMonthlyWage() != null) {
            sb.append(__wrapDecimal(count++, "subtotalMonthlyWage", getSubtotalMonthlyWage()));
        }
        if (getMonthlyPreissuedBonus() != null) {
            sb.append(__wrapDecimal(count++, "monthlyPreissuedBonus", getMonthlyPreissuedBonus()));
        }
        if (getTotalMonthlyRemuneration() != null) {
            sb.append(__wrapDecimal(count++, "totalMonthlyRemuneration", getTotalMonthlyRemuneration()));
        }
        if (getPhotoId() != null) {
            sb.append(__wrapNumber(count++, "photoId", getPhotoId()));
        }
        if (getLaborAttachmentId() != null) {
            sb.append(__wrapNumber(count++, "laborAttachmentId", getLaborAttachmentId()));
        }
        if (getCardAttachmentId() != null) {
            sb.append(__wrapNumber(count++, "cardAttachmentId", getCardAttachmentId()));
        }
        if (getTechnicalAttachmentId() != null) {
            sb.append(__wrapNumber(count++, "technicalAttachmentId", getTechnicalAttachmentId()));
        }
        if (getEducationProofId() != null) {
            sb.append(__wrapNumber(count++, "educationProofId", getEducationProofId()));
        }
        if (getDegreeProofId() != null) {
            sb.append(__wrapNumber(count++, "degreeProofId", getDegreeProofId()));
        }
        if (getBankCardAttachment() != null) {
            sb.append(__wrapString(count++, "bankCardAttachment", getBankCardAttachment()));
        }
        if (getBankCardAttachmentId() != null) {
            sb.append(__wrapNumber(count++, "bankCardAttachmentId", getBankCardAttachmentId()));
        }
        return sb.toString();
    }

    @Override
    public void setDataFromMap(Map<String, Object> values) {
        Object val;
        if ((val = values.get("employeeId")) != null) {
            setEmployeeId(__getInt(val));
        }
        if ((val = values.get("employeeNo")) != null) {
            setEmployeeNo(__getString(val));
        }
        if ((val = values.get("employeeName")) != null) {
            setEmployeeName(__getString(val));
        }
        if ((val = values.get("departmentId")) != null) {
            setDepartmentId(__getInt(val));
        }
        if ((val = values.get("roleId")) != null) {
            setRoleId(__getInt(val));
        }
        if ((val = values.get("gradeId")) != null) {
            setGradeId(__getInt(val));
        }
        if ((val = values.get("employeePassword")) != null) {
            setEmployeePassword(__getString(val));
        }
        if ((val = values.get("mobile")) != null) {
            setMobile(__getString(val));
        }
        if ((val = values.get("phone")) != null) {
            setPhone(__getString(val));
        }
        if ((val = values.get("qq")) != null) {
            setQq(__getString(val));
        }
        if ((val = values.get("email")) != null) {
            setEmail(__getString(val));
        }
        if ((val = values.get("onboardDate")) != null) {
            setOnboardDate(__getDate(val));
        }
        if ((val = values.get("resignationDate")) != null) {
            setResignationDate(__getDate(val));
        }
        if ((val = values.get("status")) != null) {
            setStatus(__getInt(val));
        }
        if ((val = values.get("usableStatus")) != null) {
            setUsableStatus(__getBoolean(val));
        }
        if ((val = values.get("isDepartment")) != null) {
            setIsDepartment(__getBoolean(val));
        }
        if ((val = values.get("photo")) != null) {
            setPhoto(__getString(val));
        }
        if ((val = values.get("gender")) != null) {
            setGender(__getInt(val));
        }
        if ((val = values.get("autograph")) != null) {
            setAutograph(__getString(val));
        }
        if ((val = values.get("age")) != null) {
            setAge(__getInt(val));
        }
        if ((val = values.get("birth")) != null) {
            setBirth(__getDate(val));
        }
        if ((val = values.get("card")) != null) {
            setCard(__getString(val));
        }
        if ((val = values.get("address")) != null) {
            setAddress(__getString(val));
        }
        if ((val = values.get("alternateField1")) != null) {
            setAlternateField1(__getString(val));
        }
        if ((val = values.get("alternateField2")) != null) {
            setAlternateField2(__getString(val));
        }
        if ((val = values.get("alternateField3")) != null) {
            setAlternateField3(__getString(val));
        }
        if ((val = values.get("locked")) != null) {
            setLocked(__getBoolean(val));
        }
        if ((val = values.get("plateId")) != null) {
            setPlateId(__getInt(val));
        }
        if ((val = values.get("dutyId")) != null) {
            setDutyId(__getInt(val));
        }
        if ((val = values.get("userAcct")) != null) {
            setUserAcct(__getString(val));
        }
        if ((val = values.get("employeeNameEn")) != null) {
            setEmployeeNameEn(__getString(val));
        }
        if ((val = values.get("education")) != null) {
            setEducation(__getString(val));
        }
        if ((val = values.get("degree")) != null) {
            setDegree(__getString(val));
        }
        if ((val = values.get("nationality")) != null) {
            setNationality(__getString(val));
        }
        if ((val = values.get("marriedStatus")) != null) {
            setMarriedStatus(__getString(val));
        }
        if ((val = values.get("health")) != null) {
            setHealth(__getString(val));
        }
        if ((val = values.get("workAddress")) != null) {
            setWorkAddress(__getString(val));
        }
        if ((val = values.get("registeredAddress")) != null) {
            setRegisteredAddress(__getString(val));
        }
        if ((val = values.get("oaId")) != null) {
            setOaId(__getInt(val));
        }
        if ((val = values.get("oaDepart")) != null) {
            setOaDepart(__getInt(val));
        }
        if ((val = values.get("isHeadcount")) != null) {
            setIsHeadcount(__getBoolean(val));
        }
        if ((val = values.get("isCheck")) != null) {
            setIsCheck(__getBoolean(val));
        }
        if ((val = values.get("directLeader")) != null) {
            setDirectLeader(__getInt(val));
        }
        if ((val = values.get("isManager")) != null) {
            setIsManager(__getBoolean(val));
        }
        if ((val = values.get("politicalFace")) != null) {
            setPoliticalFace(__getInt(val));
        }
        if ((val = values.get("birthplace")) != null) {
            setBirthplace(__getString(val));
        }
        if ((val = values.get("country")) != null) {
            setCountry(__getString(val));
        }
        if ((val = values.get("accountLocation")) != null) {
            setAccountLocation(__getString(val));
        }
        if ((val = values.get("languages")) != null) {
            setLanguages(__getString(val));
        }
        if ((val = values.get("startWorkDate")) != null) {
            setStartWorkDate(__getDate(val));
        }
        if ((val = values.get("socialComputerNumber")) != null) {
            setSocialComputerNumber(__getString(val));
        }
        if ((val = values.get("fundAccount")) != null) {
            setFundAccount(__getString(val));
        }
        if ((val = values.get("positiveDate")) != null) {
            setPositiveDate(__getDate(val));
        }
        if ((val = values.get("tryTime")) != null) {
            setTryTime(__getString(val));
        }
        if ((val = values.get("contractStartDate")) != null) {
            setContractStartDate(__getDate(val));
        }
        if ((val = values.get("contractEndDate")) != null) {
            setContractEndDate(__getDate(val));
        }
        if ((val = values.get("ownedCompany")) != null) {
            setOwnedCompany(__getString(val));
        }
        if ((val = values.get("jobs")) != null) {
            setJobs(__getString(val));
        }
        if ((val = values.get("personalBusinessRemark")) != null) {
            setPersonalBusinessRemark(__getString(val));
        }
        if ((val = values.get("selfIntroduction")) != null) {
            setSelfIntroduction(__getString(val));
        }
        if ((val = values.get("laborAttachments")) != null) {
            setLaborAttachments(__getString(val));
        }
        if ((val = values.get("emergencyContactPerson")) != null) {
            setEmergencyContactPerson(__getString(val));
        }
        if ((val = values.get("emergencyContactPhone")) != null) {
            setEmergencyContactPhone(__getString(val));
        }
        if ((val = values.get("homePhone")) != null) {
            setHomePhone(__getString(val));
        }
        if ((val = values.get("nowAddress")) != null) {
            setNowAddress(__getString(val));
        }
        if ((val = values.get("tryTimePay")) != null) {
            setTryTimePay(__getString(val));
        }
        if ((val = values.get("positivePay")) != null) {
            setPositivePay(__getString(val));
        }
        if ((val = values.get("applyEmployeeId")) != null) {
            setApplyEmployeeId(__getInt(val));
        }
        if ((val = values.get("bankCardNum")) != null) {
            setBankCardNum(__getString(val));
        }
        if ((val = values.get("fileNumber")) != null) {
            setFileNumber(__getString(val));
        }
        if ((val = values.get("householdRegistration")) != null) {
            setHouseholdRegistration(__getInt(val));
        }
        if ((val = values.get("foreman")) != null) {
            setForeman(__getString(val));
        }
        if ((val = values.get("cardAttachment")) != null) {
            setCardAttachment(__getString(val));
        }
        if ((val = values.get("technicalAttachment")) != null) {
            setTechnicalAttachment(__getString(val));
        }
        if ((val = values.get("educationProof")) != null) {
            setEducationProof(__getString(val));
        }
        if ((val = values.get("degreeProof")) != null) {
            setDegreeProof(__getString(val));
        }
        if ((val = values.get("employeeShift")) != null) {
            setEmployeeShift(__getInt(val));
        }
        if ((val = values.get("cardAddress")) != null) {
            setCardAddress(__getString(val));
        }
        if ((val = values.get("annualPerformance")) != null) {
            setAnnualPerformance(__getString(val));
        }
        if ((val = values.get("annualBonus")) != null) {
            setAnnualBonus(__getString(val));
        }
        if ((val = values.get("companyWeixin")) != null) {
            setCompanyWeixin(__getString(val));
        }
        if ((val = values.get("companyEmail")) != null) {
            setCompanyEmail(__getString(val));
        }
        if ((val = values.get("companyId")) != null) {
            setCompanyId(__getInt(val));
        }
        if ((val = values.get("ecmcUserId")) != null) {
            setEcmcUserId(__getInt(val));
        }
        if ((val = values.get("onboardStatus")) != null) {
            setOnboardStatus(__getInt(val));
        }
        if ((val = values.get("attachmentRemark")) != null) {
            setAttachmentRemark(__getString(val));
        }
        if ((val = values.get("workYearType")) != null) {
            setWorkYearType(__getInt(val));
        }
        if ((val = values.get("isbusy")) != null) {
            setIsbusy(__getInt(val));
        }
        if ((val = values.get("foreignerIdCard")) != null) {
            setForeignerIdCard(__getString(val));
        }
        if ((val = values.get("realBirthdate")) != null) {
            setRealBirthdate(__getDate(val));
        }
        if ((val = values.get("workYear")) != null) {
            setWorkYear(__getString(val));
        }
        if ((val = values.get("shenzhenHouse")) != null) {
            setShenzhenHouse(__getInt(val));
        }
        if ((val = values.get("transHouseholdDate")) != null) {
            setTransHouseholdDate(__getDate(val));
        }
        if ((val = values.get("professionalDirection")) != null) {
            setProfessionalDirection(__getInt(val));
        }
        if ((val = values.get("recruitmentSources")) != null) {
            setRecruitmentSources(__getInt(val));
        }
        if ((val = values.get("wechatNum")) != null) {
            setWechatNum(__getString(val));
        }
        if ((val = values.get("autoSignYear")) != null) {
            setAutoSignYear(__getInt(val));
        }
        if ((val = values.get("resumeAttachment")) != null) {
            setResumeAttachment(__getString(val));
        }
        if ((val = values.get("signatureAttachment")) != null) {
            setSignatureAttachment(__getString(val));
        }
        if ((val = values.get("socialAttachment")) != null) {
            setSocialAttachment(__getString(val));
        }
        if ((val = values.get("fundAttachment")) != null) {
            setFundAttachment(__getString(val));
        }
        if ((val = values.get("spareAttachment1")) != null) {
            setSpareAttachment1(__getString(val));
        }
        if ((val = values.get("spareAttachment2")) != null) {
            setSpareAttachment2(__getString(val));
        }
        if ((val = values.get("spareAttachment3")) != null) {
            setSpareAttachment3(__getString(val));
        }
        if ((val = values.get("employeeType")) != null) {
            setEmployeeType(__getInt(val));
        }
        if ((val = values.get("isLongSickLeave")) != null) {
            setIsLongSickLeave(__getBoolean(val));
        }
        if ((val = values.get("basicSalary")) != null) {
            setBasicSalary(__getDecimal(val));
        }
        if ((val = values.get("jobTitleAllowance")) != null) {
            setJobTitleAllowance(__getDecimal(val));
        }
        if ((val = values.get("postAllowance")) != null) {
            setPostAllowance(__getDecimal(val));
        }
        if ((val = values.get("other")) != null) {
            setOther(__getDecimal(val));
        }
        if ((val = values.get("communicationSubsidy")) != null) {
            setCommunicationSubsidy(__getDecimal(val));
        }
        if ((val = values.get("subtotalMonthlyWage")) != null) {
            setSubtotalMonthlyWage(__getDecimal(val));
        }
        if ((val = values.get("monthlyPreissuedBonus")) != null) {
            setMonthlyPreissuedBonus(__getDecimal(val));
        }
        if ((val = values.get("totalMonthlyRemuneration")) != null) {
            setTotalMonthlyRemuneration(__getDecimal(val));
        }
        if ((val = values.get("photoId")) != null) {
            setPhotoId(__getInt(val));
        }
        if ((val = values.get("laborAttachmentId")) != null) {
            setLaborAttachmentId(__getInt(val));
        }
        if ((val = values.get("cardAttachmentId")) != null) {
            setCardAttachmentId(__getInt(val));
        }
        if ((val = values.get("technicalAttachmentId")) != null) {
            setTechnicalAttachmentId(__getInt(val));
        }
        if ((val = values.get("educationProofId")) != null) {
            setEducationProofId(__getInt(val));
        }
        if ((val = values.get("degreeProofId")) != null) {
            setDegreeProofId(__getInt(val));
        }
        if ((val = values.get("bankCardAttachment")) != null) {
            setBankCardAttachment(__getString(val));
        }
        if ((val = values.get("bankCardAttachmentId")) != null) {
            setBankCardAttachmentId(__getInt(val));
        }
    }

    protected java.lang.Integer __employee_id;
    protected java.lang.String __employee_no;
    protected java.lang.String __employee_name;
    protected java.lang.Integer __department_id;
    protected java.lang.Integer __role_id;
    protected java.lang.Integer __grade_id;
    protected java.lang.String __employee_password;
    protected java.lang.String __mobile;
    protected java.lang.String __phone;
    protected java.lang.String __qq;
    protected java.lang.String __email;
    protected java.util.Date __onboard_date;
    protected java.util.Date __resignation_date;
    protected java.lang.Integer __status;
    protected java.lang.Boolean __usable_status;
    protected java.lang.Boolean __is_department;
    protected java.lang.String __photo;
    protected java.lang.Integer __gender;
    protected java.lang.String __autograph;
    protected java.lang.Integer __age;
    protected java.util.Date __birth;
    protected java.lang.String __card;
    protected java.lang.String __address;
    protected java.lang.String __alternate_field1;
    protected java.lang.String __alternate_field2;
    protected java.lang.String __alternate_field3;
    protected java.lang.Boolean __locked;
    protected java.lang.Integer __plate_id;
    protected java.lang.Integer __duty_id;
    protected java.lang.String __user_acct;
    protected java.lang.String __employee_name_en;
    protected java.lang.String __education;
    protected java.lang.String __degree;
    protected java.lang.String __nationality;
    protected java.lang.String __married_status;
    protected java.lang.String __health;
    protected java.lang.String __work_address;
    protected java.lang.String __registered_address;
    protected java.lang.Integer __oa_id;
    protected java.lang.Integer __oa_depart;
    protected java.lang.Boolean __is_headcount;
    protected java.lang.Boolean __is_check;
    protected java.lang.Integer __direct_leader;
    protected java.lang.Boolean __is_manager;
    protected java.lang.Integer __political_face;
    protected java.lang.String __birthplace;
    protected java.lang.String __country;
    protected java.lang.String __account_location;
    protected java.lang.String __languages;
    protected java.util.Date __start_work_date;
    protected java.lang.String __social_computer_number;
    protected java.lang.String __fund_account;
    protected java.util.Date __positive_date;
    protected java.lang.String __try_time;
    protected java.util.Date __contract_start_date;
    protected java.util.Date __contract_end_date;
    protected java.lang.String __owned_company;
    protected java.lang.String __jobs;
    protected java.lang.String __personal_business_remark;
    protected java.lang.String __self_introduction;
    protected java.lang.String __labor_attachments;
    protected java.lang.String __emergency_contact_person;
    protected java.lang.String __emergency_contact_phone;
    protected java.lang.String __home_phone;
    protected java.lang.String __now_address;
    protected java.lang.String __try_time_pay;
    protected java.lang.String __positive_pay;
    protected java.lang.Integer __apply_employee_id;
    protected java.lang.String __bank_card_num;
    protected java.lang.String __file_number;
    protected java.lang.Integer __household_registration;
    protected java.lang.String __foreman;
    protected java.lang.String __card_attachment;
    protected java.lang.String __technical_attachment;
    protected java.lang.String __education_proof;
    protected java.lang.String __degree_proof;
    protected java.lang.Integer __employee_shift;
    protected java.lang.String __card_address;
    protected java.lang.String __annual_performance;
    protected java.lang.String __annual_bonus;
    protected java.lang.String __company_weixin;
    protected java.lang.String __company_email;
    protected java.lang.Integer __company_id;
    protected java.lang.Integer __ecmc_user_id;
    protected java.lang.Integer __onboard_status;
    protected java.lang.String __attachment_remark;
    protected java.lang.Integer __work_year_type;
    protected java.lang.Integer __isbusy;
    protected java.lang.String __foreigner_id_card;
    protected java.util.Date __real_birthdate;
    protected java.lang.String __work_year;
    protected java.lang.Integer __shenzhen_house;
    protected java.util.Date __trans_household_date;
    protected java.lang.Integer __professional_direction;
    protected java.lang.Integer __recruitment_sources;
    protected java.lang.String __wechat_num;
    protected java.lang.Integer __auto_sign_year;
    protected java.lang.String __resume_attachment;
    protected java.lang.String __signature_attachment;
    protected java.lang.String __social_attachment;
    protected java.lang.String __fund_attachment;
    protected java.lang.String __spare_attachment1;
    protected java.lang.String __spare_attachment2;
    protected java.lang.String __spare_attachment3;
    protected java.lang.Integer __employee_type;
    protected java.lang.Boolean __is_long_sick_leave;
    protected java.math.BigDecimal __basic_salary;
    protected java.math.BigDecimal __job_title_allowance;
    protected java.math.BigDecimal __post_allowance;
    protected java.math.BigDecimal __other;
    protected java.math.BigDecimal __communication_subsidy;
    protected java.math.BigDecimal __subtotal_monthly_wage;
    protected java.math.BigDecimal __monthly_preissued_bonus;
    protected java.math.BigDecimal __total_monthly_remuneration;
    protected java.lang.Integer __photo_id;
    protected java.lang.Integer __labor_attachment_id;
    protected java.lang.Integer __card_attachment_id;
    protected java.lang.Integer __technical_attachment_id;
    protected java.lang.Integer __education_proof_id;
    protected java.lang.Integer __degree_proof_id;
    protected java.lang.String __bank_card_attachment;
    protected java.lang.Integer __bank_card_attachment_id;
}
