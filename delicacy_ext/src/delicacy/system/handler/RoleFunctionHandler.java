package delicacy.system.handler;

import org.apache.log4j.Logger;
import delicacy.system.bean.BaseRoleFunction;
import java.util.List;
import delicacy.system.dao.RoleFunction;
import delicacy.connection.ThreadConnection;
import delicacy.common.GenericDao;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import java.io.StringReader;
import java.util.Map;
import delicacy.common.PaginationParameter;
import delicacy.common.BaseCollection;

public class RoleFunctionHandler implements GenericDao {

	private static final Logger __logger = Logger.getLogger(RoleFunctionHandler.class);

	public static BaseRoleFunction getRoleFunctionById( 
		java.lang.Integer role_function_id
	) throws Exception
	{
		RoleFunction dao = new RoleFunction();
		dao.setRoleFunctionId(role_function_id);
		if(dao.load()){
			return dao.generateBase();
		}
		return null;
	}

	public static boolean isRoleFunctionExists( delicacy.system.bean.BaseRoleFunction bean, String additional ) throws Exception {

		RoleFunction dao = new RoleFunction();
		setConditions(bean, dao);
		boolean res = dao.isExist(additional);
		return res;
	}

	public static int countRoleFunction( delicacy.system.bean.BaseRoleFunction bean, String additional ) throws Exception {

		RoleFunction dao = new RoleFunction();
		setConditions(bean, dao);
		int res = dao.countRows(additional);
		return res;
	}

	public static BaseCollection<BaseRoleFunction> queryRoleFunction( delicacy.system.bean.BaseRoleFunction bean, int pageNo, int pageLines, String additionalCondition ) throws Exception {
		RoleFunction dao = new RoleFunction();
		setConditions(bean, dao);
		dao.setCurrentPage(pageNo);
		dao.setPageLines(pageLines);
		java.util.List<BaseRoleFunction> result = dao.conditionalLoad(additionalCondition);
		BaseCollection<BaseRoleFunction> col = new BaseCollection<>();
		col.setCollections(result);
		col.setTotalPages(dao.getTotalPages());
		col.setCurrentPage(dao.getCurrentPage());
		col.setPageLines(dao.getPageLines());
		col.setTotalLines(dao.getTotalLines());
		col.setRecordNumber(result.size());
		return col;
	}

	public static BaseRoleFunction addToRoleFunction ( BaseRoleFunction rolefunction )  throws Exception {
		return addToRoleFunction ( rolefunction , false);
	}

	public static BaseRoleFunction addToRoleFunction ( BaseRoleFunction rolefunction, boolean singleTransaction ) throws Exception {
		if(singleTransaction) ThreadConnection.beginTransaction();
		RoleFunction dao = new RoleFunction();
		dao.setDataFromBase(rolefunction);
		int result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static BaseRoleFunction addUpdateRoleFunction ( BaseRoleFunction rolefunction ) throws Exception {
		return addUpdateRoleFunction ( rolefunction , false);
	}

	public static BaseRoleFunction addUpdateRoleFunction ( BaseRoleFunction rolefunction, boolean singleTransaction  ) throws Exception {
		if(rolefunction.getRoleFunctionId() == null) return addToRoleFunction(rolefunction);
		if(singleTransaction) ThreadConnection.beginTransaction();
		StringBuilder sb = new StringBuilder();
		RoleFunction dao = new RoleFunction();
		dao.setDataFromBase(rolefunction);
		int result = 0;
		if(dao.load()) {
			dao.setDataFromBase(rolefunction); 
			if(dao.isThisObjectModified()) { 
				result = dao.update();
			} else result = 1;
		} else result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static int deleteRoleFunction ( BaseRoleFunction bean ) throws Exception {
		RoleFunction dao = new RoleFunction();
		if(setDeleteConditions(bean, dao) == 0) return 0;
		int result = dao.conditionalDelete();
		return result ;
	}

	public static BaseRoleFunction updateRoleFunction ( BaseRoleFunction rolefunction ) throws Exception {
		RoleFunction dao = new RoleFunction();
		dao.setRoleFunctionId( rolefunction.getRoleFunctionId() );
		int result = 0;
		if( dao.load() ) {
			dao.setDataFromBase(rolefunction);
			result = dao.update();
		}
		return result == 1 ? rolefunction : null ;
	}

	public static BaseRoleFunction updateRoleFunctionDirect( BaseRoleFunction rolefunction ) throws Exception {
		RoleFunction dao = new RoleFunction();
		int result = 0;
		dao.setDataFromBase(rolefunction);
		result = dao.update();
		return result == 1 ? rolefunction : null ;
	}

	public static int setDeleteConditions(BaseRoleFunction bean, RoleFunction dao){
		int count = 0;
		boolean foundKey = false;
		if(bean.getRoleFunctionId() != null) {
			dao.setConditionRoleFunctionId("=", bean.getRoleFunctionId());
			count++;
			foundKey = true;
		}
		if(!foundKey) {
			if(bean.getRoleId() != null) {
				dao.setConditionRoleId("=", bean.getRoleId());
				count++;
			}
			if(bean.getFunctionId() != null) {
				dao.setConditionFunctionId("=", bean.getFunctionId());
				count++;
			}
			if(bean.getDepartmentId() != null) {
				dao.setConditionDepartmentId("=", bean.getDepartmentId());
				count++;
			}
			if(bean.getApplicationId() != null) {
				dao.setConditionApplicationId("=", bean.getApplicationId());
				count++;
			}
		}
		return count;
	}

	public static int setConditions(BaseRoleFunction bean, RoleFunction dao){
		int count = 0;
		if(bean.getRoleFunctionId() != null) {
			dao.setConditionRoleFunctionId("=", bean.getRoleFunctionId());
			count++;
		}
		if(bean.getRoleId() != null) {
			dao.setConditionRoleId("=", bean.getRoleId());
			count++;
		}
		if(bean.getFunctionId() != null) {
			dao.setConditionFunctionId("=", bean.getFunctionId());
			count++;
		}
		if(bean.getDepartmentId() != null) {
			dao.setConditionDepartmentId("=", bean.getDepartmentId());
			count++;
		}
		if(bean.getApplicationId() != null) {
			dao.setConditionApplicationId("=", bean.getApplicationId());
			count++;
		}
		return count;
	}

	@Override
	public String findUsingKey(String json) throws Exception{
		BaseRoleFunction bean = new BaseRoleFunction();
		bean.setDataFromJSON(json);
		RoleFunction dao = new RoleFunction();
		dao.setPrimaryKeyFromBase(bean);
		if(dao.load()) { dao.setDataToBase(bean); return bean.toOneLineJSON(1,null); }
		return bean.toOneLineJSON(0,"Record not found.");
	}

	@Override
	public String find(String json, int currentPage, int pageSize, String addtion) throws Exception{
		BaseCollection<BaseRoleFunction> rlist = new BaseCollection<>();
		BaseRoleFunction bean = new BaseRoleFunction();
		JSON parser = new JSON(new StringReader(json));
		Map<String, Object> params = (Map<String, Object>) parser.parse(new BasicHandler());
		bean.setDataFromMap(params);
		PaginationParameter pp = PaginationParameter.generateFromMap(params);
		if(pp != null){
			if(pp.getCurrentPage() > 0) currentPage = pp.getCurrentPage();
			if(pp.getPageSize() > 0) pageSize = pp.getPageSize();
			if(pp.getCondition() != null) addtion = pp.getCondition();
		}
		RoleFunction dao = new RoleFunction();
		setConditions(bean, dao);
		dao.setCurrentPage(currentPage);
		dao.setPageLines(pageSize);
		List<BaseRoleFunction> result = dao.conditionalLoad(addtion);
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
		BaseRoleFunction bean = new BaseRoleFunction();
		bean.setDataFromJSON(json);
		RoleFunction dao = new RoleFunction();
		dao.setDataFromBase(bean);
		int num = dao.save();
		dao.setDataToBase(bean);
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String update(String json) throws Exception{
		BaseRoleFunction bean = new BaseRoleFunction();
		bean.setDataFromJSON(json);
		RoleFunction dao = new RoleFunction();
		dao.setPrimaryKeyFromBase(bean);
		int num=0;
		if(dao.load()){dao.setDataFromBase(bean); num = dao.update(); }
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String delete(String json) throws Exception{
		BaseRoleFunction bean = new BaseRoleFunction();
		bean.setDataFromJSON(json);
		RoleFunction dao = new RoleFunction();
		setDeleteConditions(bean, dao);
		int num = dao.conditionalDelete();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String saveOrUpdate(String json) throws Exception{
		BaseRoleFunction bean = new BaseRoleFunction();
		bean.setDataFromJSON(json);
		RoleFunction dao = new RoleFunction();
		dao.setPrimaryKeyFromBase(bean);
		int ret = 0;
		if(dao.isPrimaryKeyNull()) { dao.setDataFromBase(bean); ret = dao.save(); bean = dao.generateBase(); }
		else if(dao.load()) { dao.setDataFromBase(bean); ret = dao.update(); bean = dao.generateBase(); }
		return bean.toOneLineJSON(ret, null);
	}

}


