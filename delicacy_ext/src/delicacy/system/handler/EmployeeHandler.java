package delicacy.system.handler;

import org.apache.log4j.Logger;
import delicacy.system.bean.BaseEmployee;
import java.util.List;
import delicacy.system.dao.Employee;
import delicacy.connection.ThreadConnection;
import delicacy.common.GenericDao;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import java.io.StringReader;
import java.util.Map;
import delicacy.common.PaginationParameter;
import delicacy.common.BaseCollection;

public class EmployeeHandler implements GenericDao {

	private static final Logger __logger = Logger.getLogger(EmployeeHandler.class);

	public static BaseEmployee getEmployeeById( 
		java.lang.Integer employee_id
	) throws Exception
	{
		Employee dao = new Employee();
		dao.setEmployeeId(employee_id);
		if(dao.load()){
			return dao.generateBase();
		}
		return null;
	}

	public static boolean isEmployeeExists( delicacy.system.bean.BaseEmployee bean, String additional ) throws Exception {

		Employee dao = new Employee();
		setConditions(bean, dao);
		boolean res = dao.isExist(additional);
		return res;
	}

	public static int countEmployee( delicacy.system.bean.BaseEmployee bean, String additional ) throws Exception {

		Employee dao = new Employee();
		setConditions(bean, dao);
		int res = dao.countRows(additional);
		return res;
	}

	public static BaseCollection<BaseEmployee> queryEmployee( delicacy.system.bean.BaseEmployee bean, int pageNo, int pageLines, String additionalCondition ) throws Exception {
		Employee dao = new Employee();
		setConditions(bean, dao);
		dao.setCurrentPage(pageNo);
		dao.setPageLines(pageLines);
		java.util.List<BaseEmployee> result = dao.conditionalLoad(additionalCondition);
		BaseCollection<BaseEmployee> col = new BaseCollection<>();
		col.setCollections(result);
		col.setTotalPages(dao.getTotalPages());
		col.setCurrentPage(dao.getCurrentPage());
		col.setPageLines(dao.getPageLines());
		col.setTotalLines(dao.getTotalLines());
		col.setRecordNumber(result.size());
		return col;
	}

	public static BaseEmployee addToEmployee ( BaseEmployee employee )  throws Exception {
		return addToEmployee ( employee , false);
	}

	public static BaseEmployee addToEmployee ( BaseEmployee employee, boolean singleTransaction ) throws Exception {
		if(singleTransaction) ThreadConnection.beginTransaction();
		Employee dao = new Employee();
		dao.setDataFromBase(employee);
		int result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static BaseEmployee addUpdateEmployee ( BaseEmployee employee ) throws Exception {
		return addUpdateEmployee ( employee , false);
	}

	public static BaseEmployee addUpdateEmployee ( BaseEmployee employee, boolean singleTransaction  ) throws Exception {
		if(employee.getEmployeeId() == null) return addToEmployee(employee);
		if(singleTransaction) ThreadConnection.beginTransaction();
		StringBuilder sb = new StringBuilder();
		Employee dao = new Employee();
		dao.setDataFromBase(employee);
		int result = 0;
		if(dao.load()) {
			dao.setDataFromBase(employee); 
			if(dao.isThisObjectModified()) { 
				result = dao.update();
			} else result = 1;
		} else result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static int deleteEmployee ( BaseEmployee bean ) throws Exception {
		Employee dao = new Employee();
		if(setDeleteConditions(bean, dao) == 0) return 0;
		int result = dao.conditionalDelete();
		return result ;
	}

	public static BaseEmployee updateEmployee ( BaseEmployee employee ) throws Exception {
		Employee dao = new Employee();
		dao.setEmployeeId( employee.getEmployeeId() );
		int result = 0;
		if( dao.load() ) {
			dao.setDataFromBase(employee);
			result = dao.update();
		}
		return result == 1 ? employee : null ;
	}

	public static BaseEmployee updateEmployeeDirect( BaseEmployee employee ) throws Exception {
		Employee dao = new Employee();
		int result = 0;
		dao.setDataFromBase(employee);
		result = dao.update();
		return result == 1 ? employee : null ;
	}

	public static int setDeleteConditions(BaseEmployee bean, Employee dao){
		int count = 0;
		boolean foundKey = false;
		if(bean.getEmployeeId() != null) {
			dao.setConditionEmployeeId("=", bean.getEmployeeId());
			count++;
			foundKey = true;
		}
		if(!foundKey) {
			if(bean.getEmployeeNo() != null) {
				dao.setConditionEmployeeNo("=", bean.getEmployeeNo());
				count++;
			}
			if(bean.getEmployeeName() != null) {
				dao.setConditionEmployeeName("=", bean.getEmployeeName());
				count++;
			}
			if(bean.getDepartmentId() != null) {
				dao.setConditionDepartmentId("=", bean.getDepartmentId());
				count++;
			}
			if(bean.getRoleId() != null) {
				dao.setConditionRoleId("=", bean.getRoleId());
				count++;
			}
			if(bean.getGradeId() != null) {
				dao.setConditionGradeId("=", bean.getGradeId());
				count++;
			}
			if(bean.getEmployeePassword() != null) {
				dao.setConditionEmployeePassword("=", bean.getEmployeePassword());
				count++;
			}
			if(bean.getMobile() != null) {
				dao.setConditionMobile("=", bean.getMobile());
				count++;
			}
			if(bean.getPhone() != null) {
				dao.setConditionPhone("=", bean.getPhone());
				count++;
			}
			if(bean.getQq() != null) {
				dao.setConditionQq("=", bean.getQq());
				count++;
			}
			if(bean.getEmail() != null) {
				dao.setConditionEmail("=", bean.getEmail());
				count++;
			}
			if(bean.getStatus() != null) {
				dao.setConditionStatus("=", bean.getStatus());
				count++;
			}
			if(bean.getUsableStatus() != null) {
				dao.setConditionUsableStatus("=", bean.getUsableStatus());
				count++;
			}
			if(bean.getIsDepartment() != null) {
				dao.setConditionIsDepartment("=", bean.getIsDepartment());
				count++;
			}
			if(bean.getPhoto() != null) {
				dao.setConditionPhoto("=", bean.getPhoto());
				count++;
			}
			if(bean.getGender() != null) {
				dao.setConditionGender("=", bean.getGender());
				count++;
			}
			if(bean.getAutograph() != null) {
				dao.setConditionAutograph("=", bean.getAutograph());
				count++;
			}
			if(bean.getAge() != null) {
				dao.setConditionAge("=", bean.getAge());
				count++;
			}
			if(bean.getCard() != null) {
				dao.setConditionCard("=", bean.getCard());
				count++;
			}
			if(bean.getAddress() != null) {
				dao.setConditionAddress("=", bean.getAddress());
				count++;
			}
			if(bean.getAlternateField1() != null) {
				dao.setConditionAlternateField1("=", bean.getAlternateField1());
				count++;
			}
			if(bean.getAlternateField2() != null) {
				dao.setConditionAlternateField2("=", bean.getAlternateField2());
				count++;
			}
			if(bean.getAlternateField3() != null) {
				dao.setConditionAlternateField3("=", bean.getAlternateField3());
				count++;
			}
			if(bean.getLocked() != null) {
				dao.setConditionLocked("=", bean.getLocked());
				count++;
			}
			if(bean.getPlateId() != null) {
				dao.setConditionPlateId("=", bean.getPlateId());
				count++;
			}
			if(bean.getDutyId() != null) {
				dao.setConditionDutyId("=", bean.getDutyId());
				count++;
			}
			if(bean.getUserAcct() != null) {
				dao.setConditionUserAcct("=", bean.getUserAcct());
				count++;
			}
			if(bean.getEmployeeNameEn() != null) {
				dao.setConditionEmployeeNameEn("=", bean.getEmployeeNameEn());
				count++;
			}
			if(bean.getEducation() != null) {
				dao.setConditionEducation("=", bean.getEducation());
				count++;
			}
			if(bean.getDegree() != null) {
				dao.setConditionDegree("=", bean.getDegree());
				count++;
			}
			if(bean.getNationality() != null) {
				dao.setConditionNationality("=", bean.getNationality());
				count++;
			}
			if(bean.getMarriedStatus() != null) {
				dao.setConditionMarriedStatus("=", bean.getMarriedStatus());
				count++;
			}
			if(bean.getHealth() != null) {
				dao.setConditionHealth("=", bean.getHealth());
				count++;
			}
			if(bean.getWorkAddress() != null) {
				dao.setConditionWorkAddress("=", bean.getWorkAddress());
				count++;
			}
			if(bean.getRegisteredAddress() != null) {
				dao.setConditionRegisteredAddress("=", bean.getRegisteredAddress());
				count++;
			}
			if(bean.getOaId() != null) {
				dao.setConditionOaId("=", bean.getOaId());
				count++;
			}
			if(bean.getOaDepart() != null) {
				dao.setConditionOaDepart("=", bean.getOaDepart());
				count++;
			}
			if(bean.getIsHeadcount() != null) {
				dao.setConditionIsHeadcount("=", bean.getIsHeadcount());
				count++;
			}
			if(bean.getIsCheck() != null) {
				dao.setConditionIsCheck("=", bean.getIsCheck());
				count++;
			}
			if(bean.getDirectLeader() != null) {
				dao.setConditionDirectLeader("=", bean.getDirectLeader());
				count++;
			}
			if(bean.getIsManager() != null) {
				dao.setConditionIsManager("=", bean.getIsManager());
				count++;
			}
			if(bean.getPoliticalFace() != null) {
				dao.setConditionPoliticalFace("=", bean.getPoliticalFace());
				count++;
			}
			if(bean.getBirthplace() != null) {
				dao.setConditionBirthplace("=", bean.getBirthplace());
				count++;
			}
			if(bean.getCountry() != null) {
				dao.setConditionCountry("=", bean.getCountry());
				count++;
			}
			if(bean.getAccountLocation() != null) {
				dao.setConditionAccountLocation("=", bean.getAccountLocation());
				count++;
			}
			if(bean.getLanguages() != null) {
				dao.setConditionLanguages("=", bean.getLanguages());
				count++;
			}
			if(bean.getSocialComputerNumber() != null) {
				dao.setConditionSocialComputerNumber("=", bean.getSocialComputerNumber());
				count++;
			}
			if(bean.getFundAccount() != null) {
				dao.setConditionFundAccount("=", bean.getFundAccount());
				count++;
			}
			if(bean.getTryTime() != null) {
				dao.setConditionTryTime("=", bean.getTryTime());
				count++;
			}
			if(bean.getOwnedCompany() != null) {
				dao.setConditionOwnedCompany("=", bean.getOwnedCompany());
				count++;
			}
			if(bean.getJobs() != null) {
				dao.setConditionJobs("=", bean.getJobs());
				count++;
			}
			if(bean.getPersonalBusinessRemark() != null) {
				dao.setConditionPersonalBusinessRemark("=", bean.getPersonalBusinessRemark());
				count++;
			}
			if(bean.getSelfIntroduction() != null) {
				dao.setConditionSelfIntroduction("=", bean.getSelfIntroduction());
				count++;
			}
			if(bean.getLaborAttachments() != null) {
				dao.setConditionLaborAttachments("=", bean.getLaborAttachments());
				count++;
			}
			if(bean.getEmergencyContactPerson() != null) {
				dao.setConditionEmergencyContactPerson("=", bean.getEmergencyContactPerson());
				count++;
			}
			if(bean.getEmergencyContactPhone() != null) {
				dao.setConditionEmergencyContactPhone("=", bean.getEmergencyContactPhone());
				count++;
			}
			if(bean.getHomePhone() != null) {
				dao.setConditionHomePhone("=", bean.getHomePhone());
				count++;
			}
			if(bean.getNowAddress() != null) {
				dao.setConditionNowAddress("=", bean.getNowAddress());
				count++;
			}
			if(bean.getTryTimePay() != null) {
				dao.setConditionTryTimePay("=", bean.getTryTimePay());
				count++;
			}
			if(bean.getPositivePay() != null) {
				dao.setConditionPositivePay("=", bean.getPositivePay());
				count++;
			}
			if(bean.getApplyEmployeeId() != null) {
				dao.setConditionApplyEmployeeId("=", bean.getApplyEmployeeId());
				count++;
			}
			if(bean.getBankCardNum() != null) {
				dao.setConditionBankCardNum("=", bean.getBankCardNum());
				count++;
			}
			if(bean.getFileNumber() != null) {
				dao.setConditionFileNumber("=", bean.getFileNumber());
				count++;
			}
			if(bean.getHouseholdRegistration() != null) {
				dao.setConditionHouseholdRegistration("=", bean.getHouseholdRegistration());
				count++;
			}
			if(bean.getForeman() != null) {
				dao.setConditionForeman("=", bean.getForeman());
				count++;
			}
			if(bean.getCardAttachment() != null) {
				dao.setConditionCardAttachment("=", bean.getCardAttachment());
				count++;
			}
			if(bean.getTechnicalAttachment() != null) {
				dao.setConditionTechnicalAttachment("=", bean.getTechnicalAttachment());
				count++;
			}
			if(bean.getEducationProof() != null) {
				dao.setConditionEducationProof("=", bean.getEducationProof());
				count++;
			}
			if(bean.getDegreeProof() != null) {
				dao.setConditionDegreeProof("=", bean.getDegreeProof());
				count++;
			}
			if(bean.getEmployeeShift() != null) {
				dao.setConditionEmployeeShift("=", bean.getEmployeeShift());
				count++;
			}
			if(bean.getCardAddress() != null) {
				dao.setConditionCardAddress("=", bean.getCardAddress());
				count++;
			}
			if(bean.getAnnualPerformance() != null) {
				dao.setConditionAnnualPerformance("=", bean.getAnnualPerformance());
				count++;
			}
			if(bean.getAnnualBonus() != null) {
				dao.setConditionAnnualBonus("=", bean.getAnnualBonus());
				count++;
			}
			if(bean.getCompanyWeixin() != null) {
				dao.setConditionCompanyWeixin("=", bean.getCompanyWeixin());
				count++;
			}
			if(bean.getCompanyEmail() != null) {
				dao.setConditionCompanyEmail("=", bean.getCompanyEmail());
				count++;
			}
			if(bean.getCompanyId() != null) {
				dao.setConditionCompanyId("=", bean.getCompanyId());
				count++;
			}
			if(bean.getEcmcUserId() != null) {
				dao.setConditionEcmcUserId("=", bean.getEcmcUserId());
				count++;
			}
			if(bean.getOnboardStatus() != null) {
				dao.setConditionOnboardStatus("=", bean.getOnboardStatus());
				count++;
			}
			if(bean.getAttachmentRemark() != null) {
				dao.setConditionAttachmentRemark("=", bean.getAttachmentRemark());
				count++;
			}
			if(bean.getWorkYearType() != null) {
				dao.setConditionWorkYearType("=", bean.getWorkYearType());
				count++;
			}
			if(bean.getIsbusy() != null) {
				dao.setConditionIsbusy("=", bean.getIsbusy());
				count++;
			}
			if(bean.getForeignerIdCard() != null) {
				dao.setConditionForeignerIdCard("=", bean.getForeignerIdCard());
				count++;
			}
			if(bean.getWorkYear() != null) {
				dao.setConditionWorkYear("=", bean.getWorkYear());
				count++;
			}
			if(bean.getShenzhenHouse() != null) {
				dao.setConditionShenzhenHouse("=", bean.getShenzhenHouse());
				count++;
			}
			if(bean.getProfessionalDirection() != null) {
				dao.setConditionProfessionalDirection("=", bean.getProfessionalDirection());
				count++;
			}
			if(bean.getRecruitmentSources() != null) {
				dao.setConditionRecruitmentSources("=", bean.getRecruitmentSources());
				count++;
			}
			if(bean.getWechatNum() != null) {
				dao.setConditionWechatNum("=", bean.getWechatNum());
				count++;
			}
			if(bean.getAutoSignYear() != null) {
				dao.setConditionAutoSignYear("=", bean.getAutoSignYear());
				count++;
			}
			if(bean.getResumeAttachment() != null) {
				dao.setConditionResumeAttachment("=", bean.getResumeAttachment());
				count++;
			}
			if(bean.getSignatureAttachment() != null) {
				dao.setConditionSignatureAttachment("=", bean.getSignatureAttachment());
				count++;
			}
			if(bean.getSocialAttachment() != null) {
				dao.setConditionSocialAttachment("=", bean.getSocialAttachment());
				count++;
			}
			if(bean.getFundAttachment() != null) {
				dao.setConditionFundAttachment("=", bean.getFundAttachment());
				count++;
			}
			if(bean.getSpareAttachment1() != null) {
				dao.setConditionSpareAttachment1("=", bean.getSpareAttachment1());
				count++;
			}
			if(bean.getSpareAttachment2() != null) {
				dao.setConditionSpareAttachment2("=", bean.getSpareAttachment2());
				count++;
			}
			if(bean.getSpareAttachment3() != null) {
				dao.setConditionSpareAttachment3("=", bean.getSpareAttachment3());
				count++;
			}
			if(bean.getEmployeeType() != null) {
				dao.setConditionEmployeeType("=", bean.getEmployeeType());
				count++;
			}
			if(bean.getIsLongSickLeave() != null) {
				dao.setConditionIsLongSickLeave("=", bean.getIsLongSickLeave());
				count++;
			}
			if(bean.getPhotoId() != null) {
				dao.setConditionPhotoId("=", bean.getPhotoId());
				count++;
			}
			if(bean.getLaborAttachmentId() != null) {
				dao.setConditionLaborAttachmentId("=", bean.getLaborAttachmentId());
				count++;
			}
			if(bean.getCardAttachmentId() != null) {
				dao.setConditionCardAttachmentId("=", bean.getCardAttachmentId());
				count++;
			}
			if(bean.getTechnicalAttachmentId() != null) {
				dao.setConditionTechnicalAttachmentId("=", bean.getTechnicalAttachmentId());
				count++;
			}
			if(bean.getEducationProofId() != null) {
				dao.setConditionEducationProofId("=", bean.getEducationProofId());
				count++;
			}
			if(bean.getDegreeProofId() != null) {
				dao.setConditionDegreeProofId("=", bean.getDegreeProofId());
				count++;
			}
		}
		return count;
	}

	public static int setConditions(BaseEmployee bean, Employee dao){
		int count = 0;
		if(bean.getEmployeeId() != null) {
			dao.setConditionEmployeeId("=", bean.getEmployeeId());
			count++;
		}
		if(bean.getEmployeeNo() != null) {
			if(bean.getEmployeeNo().indexOf("%") >= 0)
				dao.setConditionEmployeeNo("like", bean.getEmployeeNo());
			else
				dao.setConditionEmployeeNo("=", bean.getEmployeeNo());
			count++;
		}
		if(bean.getEmployeeName() != null) {
			if(bean.getEmployeeName().indexOf("%") >= 0)
				dao.setConditionEmployeeName("like", bean.getEmployeeName());
			else
				dao.setConditionEmployeeName("=", bean.getEmployeeName());
			count++;
		}
		if(bean.getDepartmentId() != null) {
			dao.setConditionDepartmentId("=", bean.getDepartmentId());
			count++;
		}
		if(bean.getRoleId() != null) {
			dao.setConditionRoleId("=", bean.getRoleId());
			count++;
		}
		if(bean.getGradeId() != null) {
			dao.setConditionGradeId("=", bean.getGradeId());
			count++;
		}
		if(bean.getEmployeePassword() != null) {
			if(bean.getEmployeePassword().indexOf("%") >= 0)
				dao.setConditionEmployeePassword("like", bean.getEmployeePassword());
			else
				dao.setConditionEmployeePassword("=", bean.getEmployeePassword());
			count++;
		}
		if(bean.getMobile() != null) {
			if(bean.getMobile().indexOf("%") >= 0)
				dao.setConditionMobile("like", bean.getMobile());
			else
				dao.setConditionMobile("=", bean.getMobile());
			count++;
		}
		if(bean.getPhone() != null) {
			if(bean.getPhone().indexOf("%") >= 0)
				dao.setConditionPhone("like", bean.getPhone());
			else
				dao.setConditionPhone("=", bean.getPhone());
			count++;
		}
		if(bean.getQq() != null) {
			if(bean.getQq().indexOf("%") >= 0)
				dao.setConditionQq("like", bean.getQq());
			else
				dao.setConditionQq("=", bean.getQq());
			count++;
		}
		if(bean.getEmail() != null) {
			if(bean.getEmail().indexOf("%") >= 0)
				dao.setConditionEmail("like", bean.getEmail());
			else
				dao.setConditionEmail("=", bean.getEmail());
			count++;
		}
		if(bean.getOnboardDate() != null) {
			dao.setConditionOnboardDate(">=", bean.getOnboardDate());
			count++;
		}
		if(bean.getResignationDate() != null) {
			dao.setConditionResignationDate(">=", bean.getResignationDate());
			count++;
		}
		if(bean.getStatus() != null) {
			dao.setConditionStatus("=", bean.getStatus());
			count++;
		}
		if(bean.getUsableStatus() != null) {
			dao.setConditionUsableStatus("=", bean.getUsableStatus());
			count++;
		}
		if(bean.getIsDepartment() != null) {
			dao.setConditionIsDepartment("=", bean.getIsDepartment());
			count++;
		}
		if(bean.getPhoto() != null) {
			if(bean.getPhoto().indexOf("%") >= 0)
				dao.setConditionPhoto("like", bean.getPhoto());
			else
				dao.setConditionPhoto("=", bean.getPhoto());
			count++;
		}
		if(bean.getGender() != null) {
			dao.setConditionGender("=", bean.getGender());
			count++;
		}
		if(bean.getAutograph() != null) {
			if(bean.getAutograph().indexOf("%") >= 0)
				dao.setConditionAutograph("like", bean.getAutograph());
			else
				dao.setConditionAutograph("=", bean.getAutograph());
			count++;
		}
		if(bean.getAge() != null) {
			dao.setConditionAge("=", bean.getAge());
			count++;
		}
		if(bean.getBirth() != null) {
			dao.setConditionBirth(">=", bean.getBirth());
			count++;
		}
		if(bean.getCard() != null) {
			if(bean.getCard().indexOf("%") >= 0)
				dao.setConditionCard("like", bean.getCard());
			else
				dao.setConditionCard("=", bean.getCard());
			count++;
		}
		if(bean.getAddress() != null) {
			if(bean.getAddress().indexOf("%") >= 0)
				dao.setConditionAddress("like", bean.getAddress());
			else
				dao.setConditionAddress("=", bean.getAddress());
			count++;
		}
		if(bean.getAlternateField1() != null) {
			if(bean.getAlternateField1().indexOf("%") >= 0)
				dao.setConditionAlternateField1("like", bean.getAlternateField1());
			else
				dao.setConditionAlternateField1("=", bean.getAlternateField1());
			count++;
		}
		if(bean.getAlternateField2() != null) {
			if(bean.getAlternateField2().indexOf("%") >= 0)
				dao.setConditionAlternateField2("like", bean.getAlternateField2());
			else
				dao.setConditionAlternateField2("=", bean.getAlternateField2());
			count++;
		}
		if(bean.getAlternateField3() != null) {
			if(bean.getAlternateField3().indexOf("%") >= 0)
				dao.setConditionAlternateField3("like", bean.getAlternateField3());
			else
				dao.setConditionAlternateField3("=", bean.getAlternateField3());
			count++;
		}
		if(bean.getLocked() != null) {
			dao.setConditionLocked("=", bean.getLocked());
			count++;
		}
		if(bean.getPlateId() != null) {
			dao.setConditionPlateId("=", bean.getPlateId());
			count++;
		}
		if(bean.getDutyId() != null) {
			dao.setConditionDutyId("=", bean.getDutyId());
			count++;
		}
		if(bean.getUserAcct() != null) {
			if(bean.getUserAcct().indexOf("%") >= 0)
				dao.setConditionUserAcct("like", bean.getUserAcct());
			else
				dao.setConditionUserAcct("=", bean.getUserAcct());
			count++;
		}
		if(bean.getEmployeeNameEn() != null) {
			if(bean.getEmployeeNameEn().indexOf("%") >= 0)
				dao.setConditionEmployeeNameEn("like", bean.getEmployeeNameEn());
			else
				dao.setConditionEmployeeNameEn("=", bean.getEmployeeNameEn());
			count++;
		}
		if(bean.getEducation() != null) {
			if(bean.getEducation().indexOf("%") >= 0)
				dao.setConditionEducation("like", bean.getEducation());
			else
				dao.setConditionEducation("=", bean.getEducation());
			count++;
		}
		if(bean.getDegree() != null) {
			if(bean.getDegree().indexOf("%") >= 0)
				dao.setConditionDegree("like", bean.getDegree());
			else
				dao.setConditionDegree("=", bean.getDegree());
			count++;
		}
		if(bean.getNationality() != null) {
			if(bean.getNationality().indexOf("%") >= 0)
				dao.setConditionNationality("like", bean.getNationality());
			else
				dao.setConditionNationality("=", bean.getNationality());
			count++;
		}
		if(bean.getMarriedStatus() != null) {
			if(bean.getMarriedStatus().indexOf("%") >= 0)
				dao.setConditionMarriedStatus("like", bean.getMarriedStatus());
			else
				dao.setConditionMarriedStatus("=", bean.getMarriedStatus());
			count++;
		}
		if(bean.getHealth() != null) {
			if(bean.getHealth().indexOf("%") >= 0)
				dao.setConditionHealth("like", bean.getHealth());
			else
				dao.setConditionHealth("=", bean.getHealth());
			count++;
		}
		if(bean.getWorkAddress() != null) {
			if(bean.getWorkAddress().indexOf("%") >= 0)
				dao.setConditionWorkAddress("like", bean.getWorkAddress());
			else
				dao.setConditionWorkAddress("=", bean.getWorkAddress());
			count++;
		}
		if(bean.getRegisteredAddress() != null) {
			if(bean.getRegisteredAddress().indexOf("%") >= 0)
				dao.setConditionRegisteredAddress("like", bean.getRegisteredAddress());
			else
				dao.setConditionRegisteredAddress("=", bean.getRegisteredAddress());
			count++;
		}
		if(bean.getOaId() != null) {
			dao.setConditionOaId("=", bean.getOaId());
			count++;
		}
		if(bean.getOaDepart() != null) {
			dao.setConditionOaDepart("=", bean.getOaDepart());
			count++;
		}
		if(bean.getIsHeadcount() != null) {
			dao.setConditionIsHeadcount("=", bean.getIsHeadcount());
			count++;
		}
		if(bean.getIsCheck() != null) {
			dao.setConditionIsCheck("=", bean.getIsCheck());
			count++;
		}
		if(bean.getDirectLeader() != null) {
			dao.setConditionDirectLeader("=", bean.getDirectLeader());
			count++;
		}
		if(bean.getIsManager() != null) {
			dao.setConditionIsManager("=", bean.getIsManager());
			count++;
		}
		if(bean.getPoliticalFace() != null) {
			dao.setConditionPoliticalFace("=", bean.getPoliticalFace());
			count++;
		}
		if(bean.getBirthplace() != null) {
			if(bean.getBirthplace().indexOf("%") >= 0)
				dao.setConditionBirthplace("like", bean.getBirthplace());
			else
				dao.setConditionBirthplace("=", bean.getBirthplace());
			count++;
		}
		if(bean.getCountry() != null) {
			if(bean.getCountry().indexOf("%") >= 0)
				dao.setConditionCountry("like", bean.getCountry());
			else
				dao.setConditionCountry("=", bean.getCountry());
			count++;
		}
		if(bean.getAccountLocation() != null) {
			if(bean.getAccountLocation().indexOf("%") >= 0)
				dao.setConditionAccountLocation("like", bean.getAccountLocation());
			else
				dao.setConditionAccountLocation("=", bean.getAccountLocation());
			count++;
		}
		if(bean.getLanguages() != null) {
			if(bean.getLanguages().indexOf("%") >= 0)
				dao.setConditionLanguages("like", bean.getLanguages());
			else
				dao.setConditionLanguages("=", bean.getLanguages());
			count++;
		}
		if(bean.getStartWorkDate() != null) {
			dao.setConditionStartWorkDate(">=", bean.getStartWorkDate());
			count++;
		}
		if(bean.getSocialComputerNumber() != null) {
			if(bean.getSocialComputerNumber().indexOf("%") >= 0)
				dao.setConditionSocialComputerNumber("like", bean.getSocialComputerNumber());
			else
				dao.setConditionSocialComputerNumber("=", bean.getSocialComputerNumber());
			count++;
		}
		if(bean.getFundAccount() != null) {
			if(bean.getFundAccount().indexOf("%") >= 0)
				dao.setConditionFundAccount("like", bean.getFundAccount());
			else
				dao.setConditionFundAccount("=", bean.getFundAccount());
			count++;
		}
		if(bean.getPositiveDate() != null) {
			dao.setConditionPositiveDate(">=", bean.getPositiveDate());
			count++;
		}
		if(bean.getTryTime() != null) {
			if(bean.getTryTime().indexOf("%") >= 0)
				dao.setConditionTryTime("like", bean.getTryTime());
			else
				dao.setConditionTryTime("=", bean.getTryTime());
			count++;
		}
		if(bean.getContractStartDate() != null) {
			dao.setConditionContractStartDate(">=", bean.getContractStartDate());
			count++;
		}
		if(bean.getContractEndDate() != null) {
			dao.setConditionContractEndDate(">=", bean.getContractEndDate());
			count++;
		}
		if(bean.getOwnedCompany() != null) {
			if(bean.getOwnedCompany().indexOf("%") >= 0)
				dao.setConditionOwnedCompany("like", bean.getOwnedCompany());
			else
				dao.setConditionOwnedCompany("=", bean.getOwnedCompany());
			count++;
		}
		if(bean.getJobs() != null) {
			if(bean.getJobs().indexOf("%") >= 0)
				dao.setConditionJobs("like", bean.getJobs());
			else
				dao.setConditionJobs("=", bean.getJobs());
			count++;
		}
		if(bean.getPersonalBusinessRemark() != null) {
			if(bean.getPersonalBusinessRemark().indexOf("%") >= 0)
				dao.setConditionPersonalBusinessRemark("like", bean.getPersonalBusinessRemark());
			else
				dao.setConditionPersonalBusinessRemark("=", bean.getPersonalBusinessRemark());
			count++;
		}
		if(bean.getSelfIntroduction() != null) {
			if(bean.getSelfIntroduction().indexOf("%") >= 0)
				dao.setConditionSelfIntroduction("like", bean.getSelfIntroduction());
			else
				dao.setConditionSelfIntroduction("=", bean.getSelfIntroduction());
			count++;
		}
		if(bean.getLaborAttachments() != null) {
			if(bean.getLaborAttachments().indexOf("%") >= 0)
				dao.setConditionLaborAttachments("like", bean.getLaborAttachments());
			else
				dao.setConditionLaborAttachments("=", bean.getLaborAttachments());
			count++;
		}
		if(bean.getEmergencyContactPerson() != null) {
			if(bean.getEmergencyContactPerson().indexOf("%") >= 0)
				dao.setConditionEmergencyContactPerson("like", bean.getEmergencyContactPerson());
			else
				dao.setConditionEmergencyContactPerson("=", bean.getEmergencyContactPerson());
			count++;
		}
		if(bean.getEmergencyContactPhone() != null) {
			if(bean.getEmergencyContactPhone().indexOf("%") >= 0)
				dao.setConditionEmergencyContactPhone("like", bean.getEmergencyContactPhone());
			else
				dao.setConditionEmergencyContactPhone("=", bean.getEmergencyContactPhone());
			count++;
		}
		if(bean.getHomePhone() != null) {
			if(bean.getHomePhone().indexOf("%") >= 0)
				dao.setConditionHomePhone("like", bean.getHomePhone());
			else
				dao.setConditionHomePhone("=", bean.getHomePhone());
			count++;
		}
		if(bean.getNowAddress() != null) {
			if(bean.getNowAddress().indexOf("%") >= 0)
				dao.setConditionNowAddress("like", bean.getNowAddress());
			else
				dao.setConditionNowAddress("=", bean.getNowAddress());
			count++;
		}
		if(bean.getTryTimePay() != null) {
			if(bean.getTryTimePay().indexOf("%") >= 0)
				dao.setConditionTryTimePay("like", bean.getTryTimePay());
			else
				dao.setConditionTryTimePay("=", bean.getTryTimePay());
			count++;
		}
		if(bean.getPositivePay() != null) {
			if(bean.getPositivePay().indexOf("%") >= 0)
				dao.setConditionPositivePay("like", bean.getPositivePay());
			else
				dao.setConditionPositivePay("=", bean.getPositivePay());
			count++;
		}
		if(bean.getApplyEmployeeId() != null) {
			dao.setConditionApplyEmployeeId("=", bean.getApplyEmployeeId());
			count++;
		}
		if(bean.getBankCardNum() != null) {
			if(bean.getBankCardNum().indexOf("%") >= 0)
				dao.setConditionBankCardNum("like", bean.getBankCardNum());
			else
				dao.setConditionBankCardNum("=", bean.getBankCardNum());
			count++;
		}
		if(bean.getFileNumber() != null) {
			if(bean.getFileNumber().indexOf("%") >= 0)
				dao.setConditionFileNumber("like", bean.getFileNumber());
			else
				dao.setConditionFileNumber("=", bean.getFileNumber());
			count++;
		}
		if(bean.getHouseholdRegistration() != null) {
			dao.setConditionHouseholdRegistration("=", bean.getHouseholdRegistration());
			count++;
		}
		if(bean.getForeman() != null) {
			if(bean.getForeman().indexOf("%") >= 0)
				dao.setConditionForeman("like", bean.getForeman());
			else
				dao.setConditionForeman("=", bean.getForeman());
			count++;
		}
		if(bean.getCardAttachment() != null) {
			if(bean.getCardAttachment().indexOf("%") >= 0)
				dao.setConditionCardAttachment("like", bean.getCardAttachment());
			else
				dao.setConditionCardAttachment("=", bean.getCardAttachment());
			count++;
		}
		if(bean.getTechnicalAttachment() != null) {
			if(bean.getTechnicalAttachment().indexOf("%") >= 0)
				dao.setConditionTechnicalAttachment("like", bean.getTechnicalAttachment());
			else
				dao.setConditionTechnicalAttachment("=", bean.getTechnicalAttachment());
			count++;
		}
		if(bean.getEducationProof() != null) {
			if(bean.getEducationProof().indexOf("%") >= 0)
				dao.setConditionEducationProof("like", bean.getEducationProof());
			else
				dao.setConditionEducationProof("=", bean.getEducationProof());
			count++;
		}
		if(bean.getDegreeProof() != null) {
			if(bean.getDegreeProof().indexOf("%") >= 0)
				dao.setConditionDegreeProof("like", bean.getDegreeProof());
			else
				dao.setConditionDegreeProof("=", bean.getDegreeProof());
			count++;
		}
		if(bean.getEmployeeShift() != null) {
			dao.setConditionEmployeeShift("=", bean.getEmployeeShift());
			count++;
		}
		if(bean.getCardAddress() != null) {
			if(bean.getCardAddress().indexOf("%") >= 0)
				dao.setConditionCardAddress("like", bean.getCardAddress());
			else
				dao.setConditionCardAddress("=", bean.getCardAddress());
			count++;
		}
		if(bean.getAnnualPerformance() != null) {
			if(bean.getAnnualPerformance().indexOf("%") >= 0)
				dao.setConditionAnnualPerformance("like", bean.getAnnualPerformance());
			else
				dao.setConditionAnnualPerformance("=", bean.getAnnualPerformance());
			count++;
		}
		if(bean.getAnnualBonus() != null) {
			if(bean.getAnnualBonus().indexOf("%") >= 0)
				dao.setConditionAnnualBonus("like", bean.getAnnualBonus());
			else
				dao.setConditionAnnualBonus("=", bean.getAnnualBonus());
			count++;
		}
		if(bean.getCompanyWeixin() != null) {
			if(bean.getCompanyWeixin().indexOf("%") >= 0)
				dao.setConditionCompanyWeixin("like", bean.getCompanyWeixin());
			else
				dao.setConditionCompanyWeixin("=", bean.getCompanyWeixin());
			count++;
		}
		if(bean.getCompanyEmail() != null) {
			if(bean.getCompanyEmail().indexOf("%") >= 0)
				dao.setConditionCompanyEmail("like", bean.getCompanyEmail());
			else
				dao.setConditionCompanyEmail("=", bean.getCompanyEmail());
			count++;
		}
		if(bean.getCompanyId() != null) {
			dao.setConditionCompanyId("=", bean.getCompanyId());
			count++;
		}
		if(bean.getEcmcUserId() != null) {
			dao.setConditionEcmcUserId("=", bean.getEcmcUserId());
			count++;
		}
		if(bean.getOnboardStatus() != null) {
			dao.setConditionOnboardStatus("=", bean.getOnboardStatus());
			count++;
		}
		if(bean.getAttachmentRemark() != null) {
			if(bean.getAttachmentRemark().indexOf("%") >= 0)
				dao.setConditionAttachmentRemark("like", bean.getAttachmentRemark());
			else
				dao.setConditionAttachmentRemark("=", bean.getAttachmentRemark());
			count++;
		}
		if(bean.getWorkYearType() != null) {
			dao.setConditionWorkYearType("=", bean.getWorkYearType());
			count++;
		}
		if(bean.getIsbusy() != null) {
			dao.setConditionIsbusy("=", bean.getIsbusy());
			count++;
		}
		if(bean.getForeignerIdCard() != null) {
			if(bean.getForeignerIdCard().indexOf("%") >= 0)
				dao.setConditionForeignerIdCard("like", bean.getForeignerIdCard());
			else
				dao.setConditionForeignerIdCard("=", bean.getForeignerIdCard());
			count++;
		}
		if(bean.getRealBirthdate() != null) {
			dao.setConditionRealBirthdate(">=", bean.getRealBirthdate());
			count++;
		}
		if(bean.getWorkYear() != null) {
			if(bean.getWorkYear().indexOf("%") >= 0)
				dao.setConditionWorkYear("like", bean.getWorkYear());
			else
				dao.setConditionWorkYear("=", bean.getWorkYear());
			count++;
		}
		if(bean.getShenzhenHouse() != null) {
			dao.setConditionShenzhenHouse("=", bean.getShenzhenHouse());
			count++;
		}
		if(bean.getTransHouseholdDate() != null) {
			dao.setConditionTransHouseholdDate(">=", bean.getTransHouseholdDate());
			count++;
		}
		if(bean.getProfessionalDirection() != null) {
			dao.setConditionProfessionalDirection("=", bean.getProfessionalDirection());
			count++;
		}
		if(bean.getRecruitmentSources() != null) {
			dao.setConditionRecruitmentSources("=", bean.getRecruitmentSources());
			count++;
		}
		if(bean.getWechatNum() != null) {
			if(bean.getWechatNum().indexOf("%") >= 0)
				dao.setConditionWechatNum("like", bean.getWechatNum());
			else
				dao.setConditionWechatNum("=", bean.getWechatNum());
			count++;
		}
		if(bean.getAutoSignYear() != null) {
			dao.setConditionAutoSignYear("=", bean.getAutoSignYear());
			count++;
		}
		if(bean.getResumeAttachment() != null) {
			if(bean.getResumeAttachment().indexOf("%") >= 0)
				dao.setConditionResumeAttachment("like", bean.getResumeAttachment());
			else
				dao.setConditionResumeAttachment("=", bean.getResumeAttachment());
			count++;
		}
		if(bean.getSignatureAttachment() != null) {
			if(bean.getSignatureAttachment().indexOf("%") >= 0)
				dao.setConditionSignatureAttachment("like", bean.getSignatureAttachment());
			else
				dao.setConditionSignatureAttachment("=", bean.getSignatureAttachment());
			count++;
		}
		if(bean.getSocialAttachment() != null) {
			if(bean.getSocialAttachment().indexOf("%") >= 0)
				dao.setConditionSocialAttachment("like", bean.getSocialAttachment());
			else
				dao.setConditionSocialAttachment("=", bean.getSocialAttachment());
			count++;
		}
		if(bean.getFundAttachment() != null) {
			if(bean.getFundAttachment().indexOf("%") >= 0)
				dao.setConditionFundAttachment("like", bean.getFundAttachment());
			else
				dao.setConditionFundAttachment("=", bean.getFundAttachment());
			count++;
		}
		if(bean.getSpareAttachment1() != null) {
			if(bean.getSpareAttachment1().indexOf("%") >= 0)
				dao.setConditionSpareAttachment1("like", bean.getSpareAttachment1());
			else
				dao.setConditionSpareAttachment1("=", bean.getSpareAttachment1());
			count++;
		}
		if(bean.getSpareAttachment2() != null) {
			if(bean.getSpareAttachment2().indexOf("%") >= 0)
				dao.setConditionSpareAttachment2("like", bean.getSpareAttachment2());
			else
				dao.setConditionSpareAttachment2("=", bean.getSpareAttachment2());
			count++;
		}
		if(bean.getSpareAttachment3() != null) {
			if(bean.getSpareAttachment3().indexOf("%") >= 0)
				dao.setConditionSpareAttachment3("like", bean.getSpareAttachment3());
			else
				dao.setConditionSpareAttachment3("=", bean.getSpareAttachment3());
			count++;
		}
		if(bean.getEmployeeType() != null) {
			dao.setConditionEmployeeType("=", bean.getEmployeeType());
			count++;
		}
		if(bean.getIsLongSickLeave() != null) {
			dao.setConditionIsLongSickLeave("=", bean.getIsLongSickLeave());
			count++;
		}
		if(bean.getBasicSalary() != null) {
			dao.setConditionBasicSalary("=", bean.getBasicSalary());
			count++;
		}
		if(bean.getJobTitleAllowance() != null) {
			dao.setConditionJobTitleAllowance("=", bean.getJobTitleAllowance());
			count++;
		}
		if(bean.getPostAllowance() != null) {
			dao.setConditionPostAllowance("=", bean.getPostAllowance());
			count++;
		}
		if(bean.getOther() != null) {
			dao.setConditionOther("=", bean.getOther());
			count++;
		}
		if(bean.getCommunicationSubsidy() != null) {
			dao.setConditionCommunicationSubsidy("=", bean.getCommunicationSubsidy());
			count++;
		}
		if(bean.getSubtotalMonthlyWage() != null) {
			dao.setConditionSubtotalMonthlyWage("=", bean.getSubtotalMonthlyWage());
			count++;
		}
		if(bean.getMonthlyPreissuedBonus() != null) {
			dao.setConditionMonthlyPreissuedBonus("=", bean.getMonthlyPreissuedBonus());
			count++;
		}
		if(bean.getTotalMonthlyRemuneration() != null) {
			dao.setConditionTotalMonthlyRemuneration("=", bean.getTotalMonthlyRemuneration());
			count++;
		}
		if(bean.getPhotoId() != null) {
			dao.setConditionPhotoId("=", bean.getPhotoId());
			count++;
		}
		if(bean.getLaborAttachmentId() != null) {
			dao.setConditionLaborAttachmentId("=", bean.getLaborAttachmentId());
			count++;
		}
		if(bean.getCardAttachmentId() != null) {
			dao.setConditionCardAttachmentId("=", bean.getCardAttachmentId());
			count++;
		}
		if(bean.getTechnicalAttachmentId() != null) {
			dao.setConditionTechnicalAttachmentId("=", bean.getTechnicalAttachmentId());
			count++;
		}
		if(bean.getEducationProofId() != null) {
			dao.setConditionEducationProofId("=", bean.getEducationProofId());
			count++;
		}
		if(bean.getDegreeProofId() != null) {
			dao.setConditionDegreeProofId("=", bean.getDegreeProofId());
			count++;
		}
		return count;
	}

	@Override
	public String findUsingKey(String json) throws Exception{
		BaseEmployee bean = new BaseEmployee();
		bean.setDataFromJSON(json);
		Employee dao = new Employee();
		dao.setPrimaryKeyFromBase(bean);
		if(dao.load()) { dao.setDataToBase(bean); return bean.toOneLineJSON(1,null); }
		return bean.toOneLineJSON(0,"Record not found.");
	}

	@Override
	public String find(String json, int currentPage, int pageSize, String addtion) throws Exception{
		BaseCollection<BaseEmployee> rlist = new BaseCollection<>();
		BaseEmployee bean = new BaseEmployee();
		JSON parser = new JSON(new StringReader(json));
		Map<String, Object> params = (Map<String, Object>) parser.parse(new BasicHandler());
		bean.setDataFromMap(params);
		PaginationParameter pp = PaginationParameter.generateFromMap(params);
		if(pp != null){
			if(pp.getCurrentPage() > 0) currentPage = pp.getCurrentPage();
			if(pp.getPageSize() > 0) pageSize = pp.getPageSize();
			if(pp.getCondition() != null) addtion = pp.getCondition();
		}
		Employee dao = new Employee();
		setConditions(bean, dao);
		dao.setCurrentPage(currentPage);
		dao.setPageLines(pageSize);
		List<BaseEmployee> result = dao.conditionalLoad(addtion);
		rlist.setCollections(result);
		rlist.setTotalPages(dao.getTotalPages());
		rlist.setCurrentPage(dao.getCurrentPage());
		rlist.setPageLines(dao.getPageLines());
		rlist.setTotalLines(dao.getTotalLines());
		rlist.setRecordNumber(result.size());
		return rlist.toJSON(null);
	}

	@Override
	public String save(String json) throws Exception{
		BaseEmployee bean = new BaseEmployee();
		bean.setDataFromJSON(json);
		Employee dao = new Employee();
		dao.setDataFromBase(bean);
		int num = dao.save();
		dao.setDataToBase(bean);
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String update(String json) throws Exception{
		BaseEmployee bean = new BaseEmployee();
		bean.setDataFromJSON(json);
		Employee dao = new Employee();
		dao.setPrimaryKeyFromBase(bean);
		int num=0;
		if(dao.load()){dao.setDataFromBase(bean); num = dao.update(); }
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String delete(String json) throws Exception{
		BaseEmployee bean = new BaseEmployee();
		bean.setDataFromJSON(json);
		Employee dao = new Employee();
		setDeleteConditions(bean, dao);
		int num = dao.conditionalDelete();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String saveOrUpdate(String json) throws Exception{
		BaseEmployee bean = new BaseEmployee();
		bean.setDataFromJSON(json);
		Employee dao = new Employee();
		dao.setPrimaryKeyFromBase(bean);
		int ret = 0;
		if(dao.isPrimaryKeyNull()) { dao.setDataFromBase(bean); ret = dao.save(); bean = dao.generateBase(); }
		else if(dao.load()) { dao.setDataFromBase(bean); ret = dao.update(); bean = dao.generateBase(); }
		return bean.toOneLineJSON(ret, null);
	}

}


