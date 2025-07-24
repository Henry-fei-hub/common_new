package delicacy.system.handler;

import org.apache.log4j.Logger;
import delicacy.system.bean.BaseUser;
import java.util.List;
import delicacy.system.dao.User;
import delicacy.connection.ThreadConnection;
import delicacy.common.GenericDao;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import java.io.StringReader;
import java.util.Map;
import delicacy.common.PaginationParameter;
import delicacy.common.BaseCollection;

public class UserHandler implements GenericDao {

	private static final Logger __logger = Logger.getLogger(UserHandler.class);

	public static BaseUser getUserById( 
		java.lang.Integer user_id
	) throws Exception
	{
		User dao = new User();
		dao.setUserId(user_id);
		if(dao.load()){
			return dao.generateBase();
		}
		return null;
	}

	public static boolean isUserExists( delicacy.system.bean.BaseUser bean, String additional ) throws Exception {

		User dao = new User();
		setConditions(bean, dao);
		boolean res = dao.isExist(additional);
		return res;
	}

	public static int countUser( delicacy.system.bean.BaseUser bean, String additional ) throws Exception {

		User dao = new User();
		setConditions(bean, dao);
		int res = dao.countRows(additional);
		return res;
	}

	public static BaseCollection<BaseUser> queryUser( delicacy.system.bean.BaseUser bean, int pageNo, int pageLines, String additionalCondition ) throws Exception {
		User dao = new User();
		setConditions(bean, dao);
		dao.setCurrentPage(pageNo);
		dao.setPageLines(pageLines);
		java.util.List<BaseUser> result = dao.conditionalLoad(additionalCondition);
		BaseCollection<BaseUser> col = new BaseCollection<>();
		col.setCollections(result);
		col.setTotalPages(dao.getTotalPages());
		col.setCurrentPage(dao.getCurrentPage());
		col.setPageLines(dao.getPageLines());
		col.setTotalLines(dao.getTotalLines());
		col.setRecordNumber(result.size());
		return col;
	}

	public static BaseUser addToUser ( BaseUser user )  throws Exception {
		return addToUser ( user , false);
	}

	public static BaseUser addToUser ( BaseUser user, boolean singleTransaction ) throws Exception {
		if(singleTransaction) ThreadConnection.beginTransaction();
		User dao = new User();
		dao.setDataFromBase(user);
		int result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static BaseUser addUpdateUser ( BaseUser user ) throws Exception {
		return addUpdateUser ( user , false);
	}

	public static BaseUser addUpdateUser ( BaseUser user, boolean singleTransaction  ) throws Exception {
		if(user.getUserId() == null) return addToUser(user);
		if(singleTransaction) ThreadConnection.beginTransaction();
		StringBuilder sb = new StringBuilder();
		User dao = new User();
		dao.setDataFromBase(user);
		int result = 0;
		if(dao.load()) {
			dao.setDataFromBase(user); 
			if(dao.isThisObjectModified()) { 
				result = dao.update();
			} else result = 1;
		} else result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static int deleteUser ( BaseUser bean ) throws Exception {
		User dao = new User();
		if(setDeleteConditions(bean, dao) == 0) return 0;
		int result = dao.conditionalDelete();
		return result ;
	}

	public static BaseUser updateUser ( BaseUser user ) throws Exception {
		User dao = new User();
		dao.setUserId( user.getUserId() );
		int result = 0;
		if( dao.load() ) {
			dao.setDataFromBase(user);
			result = dao.update();
		}
		return result == 1 ? user : null ;
	}

	public static BaseUser updateUserDirect( BaseUser user ) throws Exception {
		User dao = new User();
		int result = 0;
		dao.setDataFromBase(user);
		result = dao.update();
		return result == 1 ? user : null ;
	}

	public static int setDeleteConditions(BaseUser bean, User dao){
		int count = 0;
		boolean foundKey = false;
		if(bean.getUserId() != null) {
			dao.setConditionUserId("=", bean.getUserId());
			count++;
			foundKey = true;
		}
		if(!foundKey) {
			if(bean.getUserNo() != null) {
				dao.setConditionUserNo("=", bean.getUserNo());
				count++;
			}
			if(bean.getUserName() != null) {
				dao.setConditionUserName("=", bean.getUserName());
				count++;
			}
			if(bean.getMobilePhone() != null) {
				dao.setConditionMobilePhone("=", bean.getMobilePhone());
				count++;
			}
			if(bean.getPhoneNumber() != null) {
				dao.setConditionPhoneNumber("=", bean.getPhoneNumber());
				count++;
			}
			if(bean.getEmail() != null) {
				dao.setConditionEmail("=", bean.getEmail());
				count++;
			}
			if(bean.getCompanyId() != null) {
				dao.setConditionCompanyId("=", bean.getCompanyId());
				count++;
			}
			if(bean.getCompanyNo() != null) {
				dao.setConditionCompanyNo("=", bean.getCompanyNo());
				count++;
			}
			if(bean.getDepartment() != null) {
				dao.setConditionDepartment("=", bean.getDepartment());
				count++;
			}
			if(bean.getJob() != null) {
				dao.setConditionJob("=", bean.getJob());
				count++;
			}
			if(bean.getEmployeeId() != null) {
				dao.setConditionEmployeeId("=", bean.getEmployeeId());
				count++;
			}
			if(bean.getEcmcWorkspace() != null) {
				dao.setConditionEcmcWorkspace("=", bean.getEcmcWorkspace());
				count++;
			}
			if(bean.getUserType() != null) {
				dao.setConditionUserType("=", bean.getUserType());
				count++;
			}
			if(bean.getUserStatus() != null) {
				dao.setConditionUserStatus("=", bean.getUserStatus());
				count++;
			}
			if(bean.getDeleteFlag() != null) {
				dao.setConditionDeleteFlag("=", bean.getDeleteFlag());
				count++;
			}
		}
		return count;
	}

	public static int setConditions(BaseUser bean, User dao){
		int count = 0;
		if(bean.getUserId() != null) {
			dao.setConditionUserId("=", bean.getUserId());
			count++;
		}
		if(bean.getUserNo() != null) {
			if(bean.getUserNo().indexOf("%") >= 0)
				dao.setConditionUserNo("like", bean.getUserNo());
			else
				dao.setConditionUserNo("=", bean.getUserNo());
			count++;
		}
		if(bean.getUserName() != null) {
			if(bean.getUserName().indexOf("%") >= 0)
				dao.setConditionUserName("like", bean.getUserName());
			else
				dao.setConditionUserName("=", bean.getUserName());
			count++;
		}
		if(bean.getMobilePhone() != null) {
			if(bean.getMobilePhone().indexOf("%") >= 0)
				dao.setConditionMobilePhone("like", bean.getMobilePhone());
			else
				dao.setConditionMobilePhone("=", bean.getMobilePhone());
			count++;
		}
		if(bean.getPhoneNumber() != null) {
			if(bean.getPhoneNumber().indexOf("%") >= 0)
				dao.setConditionPhoneNumber("like", bean.getPhoneNumber());
			else
				dao.setConditionPhoneNumber("=", bean.getPhoneNumber());
			count++;
		}
		if(bean.getEmail() != null) {
			if(bean.getEmail().indexOf("%") >= 0)
				dao.setConditionEmail("like", bean.getEmail());
			else
				dao.setConditionEmail("=", bean.getEmail());
			count++;
		}
		if(bean.getCompanyId() != null) {
			dao.setConditionCompanyId("=", bean.getCompanyId());
			count++;
		}
		if(bean.getCompanyNo() != null) {
			if(bean.getCompanyNo().indexOf("%") >= 0)
				dao.setConditionCompanyNo("like", bean.getCompanyNo());
			else
				dao.setConditionCompanyNo("=", bean.getCompanyNo());
			count++;
		}
		if(bean.getDepartment() != null) {
			if(bean.getDepartment().indexOf("%") >= 0)
				dao.setConditionDepartment("like", bean.getDepartment());
			else
				dao.setConditionDepartment("=", bean.getDepartment());
			count++;
		}
		if(bean.getJob() != null) {
			if(bean.getJob().indexOf("%") >= 0)
				dao.setConditionJob("like", bean.getJob());
			else
				dao.setConditionJob("=", bean.getJob());
			count++;
		}
		if(bean.getEmployeeId() != null) {
			dao.setConditionEmployeeId("=", bean.getEmployeeId());
			count++;
		}
		if(bean.getEcmcWorkspace() != null) {
			if(bean.getEcmcWorkspace().indexOf("%") >= 0)
				dao.setConditionEcmcWorkspace("like", bean.getEcmcWorkspace());
			else
				dao.setConditionEcmcWorkspace("=", bean.getEcmcWorkspace());
			count++;
		}
		if(bean.getUserType() != null) {
			dao.setConditionUserType("=", bean.getUserType());
			count++;
		}
		if(bean.getUserStatus() != null) {
			dao.setConditionUserStatus("=", bean.getUserStatus());
			count++;
		}
		if(bean.getCreateTime() != null) {
			dao.setConditionCreateTime(">=", bean.getCreateTime());
			count++;
		}
		if(bean.getDeleteTime() != null) {
			dao.setConditionDeleteTime(">=", bean.getDeleteTime());
			count++;
		}
		if(bean.getDeleteFlag() != null) {
			dao.setConditionDeleteFlag("=", bean.getDeleteFlag());
			count++;
		}
		return count;
	}

	@Override
	public String findUsingKey(String json) throws Exception{
		BaseUser bean = new BaseUser();
		bean.setDataFromJSON(json);
		User dao = new User();
		dao.setPrimaryKeyFromBase(bean);
		if(dao.load()) { dao.setDataToBase(bean); return bean.toOneLineJSON(1,null); }
		return bean.toOneLineJSON(0,"Record not found.");
	}

	@Override
	public String find(String json, int currentPage, int pageSize, String addtion) throws Exception{
		BaseCollection<BaseUser> rlist = new BaseCollection<>();
		BaseUser bean = new BaseUser();
		JSON parser = new JSON(new StringReader(json));
		Map<String, Object> params = (Map<String, Object>) parser.parse(new BasicHandler());
		bean.setDataFromMap(params);
		PaginationParameter pp = PaginationParameter.generateFromMap(params);
		if(pp != null){
			if(pp.getCurrentPage() > 0) currentPage = pp.getCurrentPage();
			if(pp.getPageSize() > 0) pageSize = pp.getPageSize();
			if(pp.getCondition() != null) addtion = pp.getCondition();
		}
		User dao = new User();
		setConditions(bean, dao);
		dao.setCurrentPage(currentPage);
		dao.setPageLines(pageSize);
		List<BaseUser> result = dao.conditionalLoad(addtion);
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
		BaseUser bean = new BaseUser();
		bean.setDataFromJSON(json);
		User dao = new User();
		dao.setDataFromBase(bean);
		int num = dao.save();
		dao.setDataToBase(bean);
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String update(String json) throws Exception{
		BaseUser bean = new BaseUser();
		bean.setDataFromJSON(json);
		User dao = new User();
		dao.setPrimaryKeyFromBase(bean);
		int num=0;
		if(dao.load()){dao.setDataFromBase(bean); num = dao.update(); }
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String delete(String json) throws Exception{
		BaseUser bean = new BaseUser();
		bean.setDataFromJSON(json);
		User dao = new User();
		setDeleteConditions(bean, dao);
		int num = dao.conditionalDelete();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String saveOrUpdate(String json) throws Exception{
		BaseUser bean = new BaseUser();
		bean.setDataFromJSON(json);
		User dao = new User();
		dao.setPrimaryKeyFromBase(bean);
		int ret = 0;
		if(dao.isPrimaryKeyNull()) { dao.setDataFromBase(bean); ret = dao.save(); bean = dao.generateBase(); }
		else if(dao.load()) { dao.setDataFromBase(bean); ret = dao.update(); bean = dao.generateBase(); }
		return bean.toOneLineJSON(ret, null);
	}

}


