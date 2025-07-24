package delicacy.system.handler;

import org.apache.log4j.Logger;
import java.util.List;
import delicacy.connection.ThreadConnection;
import delicacy.common.GenericDao;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import delicacy.system.bean.BaseRole;
import delicacy.system.dao.Role;

import java.io.StringReader;
import java.util.Map;
import delicacy.common.PaginationParameter;
import delicacy.common.BaseCollection;

public class RoleHandler implements GenericDao {

	private static final Logger __logger = Logger.getLogger(RoleHandler.class);

	public static BaseRole getRoleById( 
		java.lang.Integer role_id
	) throws Exception
	{
		Role dao = new Role();
		dao.setRoleId(role_id);
		if(dao.load()){
			return dao.generateBase();
		}
		return null;
	}

	public static boolean isRoleExists( delicacy.system.bean.BaseRole bean, String additional ) throws Exception {

		Role dao = new Role();
		setConditions(bean, dao);
		boolean res = dao.isExist(additional);
		return res;
	}

	public static int countRole( delicacy.system.bean.BaseRole bean, String additional ) throws Exception {

		Role dao = new Role();
		setConditions(bean, dao);
		int res = dao.countRows(additional);
		return res;
	}

	public static BaseCollection<BaseRole> queryRole( delicacy.system.bean.BaseRole bean, int pageNo, int pageLines, String additionalCondition ) throws Exception {
		Role dao = new Role();
		setConditions(bean, dao);
		dao.setCurrentPage(pageNo);
		dao.setPageLines(pageLines);
		java.util.List<BaseRole> result = dao.conditionalLoad(additionalCondition);
		BaseCollection<BaseRole> col = new BaseCollection<>();
		col.setCollections(result);
		col.setTotalPages(dao.getTotalPages());
		col.setCurrentPage(dao.getCurrentPage());
		col.setPageLines(dao.getPageLines());
		col.setTotalLines(dao.getTotalLines());
		col.setRecordNumber(result.size());
		return col;
	}

	public static BaseRole addToRole ( BaseRole role )  throws Exception {
		return addToRole ( role , false);
	}

	public static BaseRole addToRole ( BaseRole role, boolean singleTransaction ) throws Exception {
		if(singleTransaction) ThreadConnection.beginTransaction();
		Role dao = new Role();
		dao.setDataFromBase(role);
		int result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static BaseRole addUpdateRole ( BaseRole role ) throws Exception {
		return addUpdateRole ( role , false);
	}

	public static BaseRole addUpdateRole ( BaseRole role, boolean singleTransaction  ) throws Exception {
		if(role.getRoleId() == null) return addToRole(role);
		if(singleTransaction) ThreadConnection.beginTransaction();
		StringBuilder sb = new StringBuilder();
		Role dao = new Role();
		dao.setDataFromBase(role);
		int result = 0;
		if(dao.load()) {
			dao.setDataFromBase(role); 
			if(dao.isThisObjectModified()) { 
				result = dao.update();
			} else result = 1;
		} else result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static int deleteRole ( BaseRole bean ) throws Exception {
		Role dao = new Role();
		if(setDeleteConditions(bean, dao) == 0) return 0;
		int result = dao.conditionalDelete();
		return result ;
	}

	public static BaseRole updateRole ( BaseRole role ) throws Exception {
		Role dao = new Role();
		dao.setRoleId( role.getRoleId() );
		int result = 0;
		if( dao.load() ) {
			dao.setDataFromBase(role);
			result = dao.update();
		}
		return result == 1 ? role : null ;
	}

	public static BaseRole updateRoleDirect( BaseRole role ) throws Exception {
		Role dao = new Role();
		int result = 0;
		dao.setDataFromBase(role);
		result = dao.update();
		return result == 1 ? role : null ;
	}

	public static int setDeleteConditions(BaseRole bean, Role dao){
		int count = 0;
		boolean foundKey = false;
		if(bean.getRoleId() != null) {
			dao.setConditionRoleId("=", bean.getRoleId());
			count++;
			foundKey = true;
		}
		if(!foundKey) {
			if(bean.getRoleName() != null) {
				dao.setConditionRoleName("=", bean.getRoleName());
				count++;
			}
			if(bean.getApplicationId() != null) {
				dao.setConditionApplicationId("=", bean.getApplicationId());
				count++;
			}
			if(bean.getRoleType() != null) {
				dao.setConditionRoleType("=", bean.getRoleType());
				count++;
			}
			if(bean.getEnabled() != null) {
				dao.setConditionEnabled("=", bean.getEnabled());
				count++;
			}
			if(bean.getHasApprovalRight() != null) {
				dao.setConditionHasApprovalRight("=", bean.getHasApprovalRight());
				count++;
			}
			if(bean.getIsStandard() != null) {
				dao.setConditionIsStandard("=", bean.getIsStandard());
				count++;
			}
		}
		return count;
	}

	public static int setConditions(BaseRole bean, Role dao){
		int count = 0;
		if(bean.getRoleId() != null) {
			dao.setConditionRoleId("=", bean.getRoleId());
			count++;
		}
		if(bean.getRoleName() != null) {
			if(bean.getRoleName().indexOf("%") >= 0)
				dao.setConditionRoleName("like", bean.getRoleName());
			else
				dao.setConditionRoleName("=", bean.getRoleName());
			count++;
		}
		if(bean.getApplicationId() != null) {
			dao.setConditionApplicationId("=", bean.getApplicationId());
			count++;
		}
		if(bean.getRoleType() != null) {
			dao.setConditionRoleType("=", bean.getRoleType());
			count++;
		}
		if(bean.getEnabled() != null) {
			dao.setConditionEnabled("=", bean.getEnabled());
			count++;
		}
		if(bean.getHasApprovalRight() != null) {
			dao.setConditionHasApprovalRight("=", bean.getHasApprovalRight());
			count++;
		}
		if(bean.getIsStandard() != null) {
			dao.setConditionIsStandard("=", bean.getIsStandard());
			count++;
		}
		return count;
	}

	@Override
	public String findUsingKey(String json) throws Exception{
		BaseRole bean = new BaseRole();
		bean.setDataFromJSON(json);
		Role dao = new Role();
		dao.setPrimaryKeyFromBase(bean);
		if(dao.load()) { dao.setDataToBase(bean); return bean.toOneLineJSON(1,null); }
		return bean.toOneLineJSON(0,"Record not found.");
	}

	@Override
	public String find(String json, int currentPage, int pageSize, String addtion) throws Exception{
		BaseCollection<BaseRole> rlist = new BaseCollection<>();
		BaseRole bean = new BaseRole();
		JSON parser = new JSON(new StringReader(json));
		Map<String, Object> params = (Map<String, Object>) parser.parse(new BasicHandler());
		bean.setDataFromMap(params);
		PaginationParameter pp = PaginationParameter.generateFromMap(params);
		if(pp != null){
			if(pp.getCurrentPage() > 0) currentPage = pp.getCurrentPage();
			if(pp.getPageSize() > 0) pageSize = pp.getPageSize();
			if(pp.getCondition() != null) addtion = pp.getCondition();
		}
		Role dao = new Role();
		setConditions(bean, dao);
		dao.setCurrentPage(currentPage);
		dao.setPageLines(pageSize);
		List<BaseRole> result = dao.conditionalLoad(addtion);
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
		BaseRole bean = new BaseRole();
		bean.setDataFromJSON(json);
		Role dao = new Role();
		dao.setDataFromBase(bean);
		int num = dao.save();
		dao.setDataToBase(bean);
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String update(String json) throws Exception{
		BaseRole bean = new BaseRole();
		bean.setDataFromJSON(json);
		Role dao = new Role();
		dao.setPrimaryKeyFromBase(bean);
		int num=0;
		if(dao.load()){dao.setDataFromBase(bean); num = dao.update(); }
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String delete(String json) throws Exception{
		BaseRole bean = new BaseRole();
		bean.setDataFromJSON(json);
		Role dao = new Role();
		setDeleteConditions(bean, dao);
		int num = dao.conditionalDelete();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String saveOrUpdate(String json) throws Exception{
		BaseRole bean = new BaseRole();
		bean.setDataFromJSON(json);
		Role dao = new Role();
		dao.setPrimaryKeyFromBase(bean);
		int ret = 0;
		if(dao.isPrimaryKeyNull()) { dao.setDataFromBase(bean); ret = dao.save(); bean = dao.generateBase(); }
		else if(dao.load()) { dao.setDataFromBase(bean); ret = dao.update(); bean = dao.generateBase(); }
		return bean.toOneLineJSON(ret, null);
	}

}


