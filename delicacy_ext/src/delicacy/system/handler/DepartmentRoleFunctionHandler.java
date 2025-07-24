package delicacy.system.handler;

import org.apache.log4j.Logger;
import delicacy.system.bean.BaseDepartmentRoleFunction;
import java.util.List;
import delicacy.system.dao.DepartmentRoleFunction;
import delicacy.connection.ThreadConnection;
import delicacy.common.GenericDao;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import java.io.StringReader;
import java.util.Map;
import delicacy.common.PaginationParameter;
import delicacy.common.BaseCollection;

public class DepartmentRoleFunctionHandler implements GenericDao {

	private static final Logger __logger = Logger.getLogger(DepartmentRoleFunctionHandler.class);

	public static BaseDepartmentRoleFunction getDepartmentRoleFunctionById( 
		java.lang.Integer department_role_function_id
	) throws Exception
	{
		DepartmentRoleFunction dao = new DepartmentRoleFunction();
		dao.setDepartmentRoleFunctionId(department_role_function_id);
		if(dao.load()){
			return dao.generateBase();
		}
		return null;
	}

	public static boolean isDepartmentRoleFunctionExists( delicacy.system.bean.BaseDepartmentRoleFunction bean, String additional ) throws Exception {

		DepartmentRoleFunction dao = new DepartmentRoleFunction();
		setConditions(bean, dao);
		boolean res = dao.isExist(additional);
		return res;
	}

	public static int countDepartmentRoleFunction( delicacy.system.bean.BaseDepartmentRoleFunction bean, String additional ) throws Exception {

		DepartmentRoleFunction dao = new DepartmentRoleFunction();
		setConditions(bean, dao);
		int res = dao.countRows(additional);
		return res;
	}

	public static BaseCollection<BaseDepartmentRoleFunction> queryDepartmentRoleFunction( delicacy.system.bean.BaseDepartmentRoleFunction bean, int pageNo, int pageLines, String additionalCondition ) throws Exception {
		DepartmentRoleFunction dao = new DepartmentRoleFunction();
		setConditions(bean, dao);
		dao.setCurrentPage(pageNo);
		dao.setPageLines(pageLines);
		java.util.List<BaseDepartmentRoleFunction> result = dao.conditionalLoad(additionalCondition);
		BaseCollection<BaseDepartmentRoleFunction> col = new BaseCollection<>();
		col.setCollections(result);
		col.setTotalPages(dao.getTotalPages());
		col.setCurrentPage(dao.getCurrentPage());
		col.setPageLines(dao.getPageLines());
		col.setTotalLines(dao.getTotalLines());
		col.setRecordNumber(result.size());
		return col;
	}

	public static BaseDepartmentRoleFunction addToDepartmentRoleFunction ( BaseDepartmentRoleFunction departmentrolefunction )  throws Exception {
		return addToDepartmentRoleFunction ( departmentrolefunction , false);
	}

	public static BaseDepartmentRoleFunction addToDepartmentRoleFunction ( BaseDepartmentRoleFunction departmentrolefunction, boolean singleTransaction ) throws Exception {
		if(singleTransaction) ThreadConnection.beginTransaction();
		DepartmentRoleFunction dao = new DepartmentRoleFunction();
		dao.setDataFromBase(departmentrolefunction);
		int result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static BaseDepartmentRoleFunction addUpdateDepartmentRoleFunction ( BaseDepartmentRoleFunction departmentrolefunction ) throws Exception {
		return addUpdateDepartmentRoleFunction ( departmentrolefunction , false);
	}

	public static BaseDepartmentRoleFunction addUpdateDepartmentRoleFunction ( BaseDepartmentRoleFunction departmentrolefunction, boolean singleTransaction  ) throws Exception {
		if(departmentrolefunction.getDepartmentRoleFunctionId() == null) return addToDepartmentRoleFunction(departmentrolefunction);
		if(singleTransaction) ThreadConnection.beginTransaction();
		StringBuilder sb = new StringBuilder();
		DepartmentRoleFunction dao = new DepartmentRoleFunction();
		dao.setDataFromBase(departmentrolefunction);
		int result = 0;
		if(dao.load()) {
			dao.setDataFromBase(departmentrolefunction); 
			if(dao.isThisObjectModified()) { 
				result = dao.update();
			} else result = 1;
		} else result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static int deleteDepartmentRoleFunction ( BaseDepartmentRoleFunction bean ) throws Exception {
		DepartmentRoleFunction dao = new DepartmentRoleFunction();
		if(setDeleteConditions(bean, dao) == 0) return 0;
		int result = dao.conditionalDelete();
		return result ;
	}

	public static BaseDepartmentRoleFunction updateDepartmentRoleFunction ( BaseDepartmentRoleFunction departmentrolefunction ) throws Exception {
		DepartmentRoleFunction dao = new DepartmentRoleFunction();
		dao.setDepartmentRoleFunctionId( departmentrolefunction.getDepartmentRoleFunctionId() );
		int result = 0;
		if( dao.load() ) {
			dao.setDataFromBase(departmentrolefunction);
			result = dao.update();
		}
		return result == 1 ? departmentrolefunction : null ;
	}

	public static BaseDepartmentRoleFunction updateDepartmentRoleFunctionDirect( BaseDepartmentRoleFunction departmentrolefunction ) throws Exception {
		DepartmentRoleFunction dao = new DepartmentRoleFunction();
		int result = 0;
		dao.setDataFromBase(departmentrolefunction);
		result = dao.update();
		return result == 1 ? departmentrolefunction : null ;
	}

	public static int setDeleteConditions(BaseDepartmentRoleFunction bean, DepartmentRoleFunction dao){
		int count = 0;
		boolean foundKey = false;
		if(bean.getDepartmentRoleFunctionId() != null) {
			dao.setConditionDepartmentRoleFunctionId("=", bean.getDepartmentRoleFunctionId());
			count++;
			foundKey = true;
		}
		if(!foundKey) {
			if(bean.getFunctionId() != null) {
				dao.setConditionFunctionId("=", bean.getFunctionId());
				count++;
			}
			if(bean.getApplicationId() != null) {
				dao.setConditionApplicationId("=", bean.getApplicationId());
				count++;
			}
			if(bean.getRoleId() != null) {
				dao.setConditionRoleId("=", bean.getRoleId());
				count++;
			}
			if(bean.getDepartmentId() != null) {
				dao.setConditionDepartmentId("=", bean.getDepartmentId());
				count++;
			}
		}
		return count;
	}

	public static int setConditions(BaseDepartmentRoleFunction bean, DepartmentRoleFunction dao){
		int count = 0;
		if(bean.getDepartmentRoleFunctionId() != null) {
			dao.setConditionDepartmentRoleFunctionId("=", bean.getDepartmentRoleFunctionId());
			count++;
		}
		if(bean.getFunctionId() != null) {
			dao.setConditionFunctionId("=", bean.getFunctionId());
			count++;
		}
		if(bean.getApplicationId() != null) {
			dao.setConditionApplicationId("=", bean.getApplicationId());
			count++;
		}
		if(bean.getRoleId() != null) {
			dao.setConditionRoleId("=", bean.getRoleId());
			count++;
		}
		if(bean.getDepartmentId() != null) {
			dao.setConditionDepartmentId("=", bean.getDepartmentId());
			count++;
		}
		return count;
	}

	@Override
	public String findUsingKey(String json) throws Exception{
		BaseDepartmentRoleFunction bean = new BaseDepartmentRoleFunction();
		bean.setDataFromJSON(json);
		DepartmentRoleFunction dao = new DepartmentRoleFunction();
		dao.setPrimaryKeyFromBase(bean);
		if(dao.load()) { dao.setDataToBase(bean); return bean.toOneLineJSON(1,null); }
		return bean.toOneLineJSON(0,"Record not found.");
	}

	@Override
	public String find(String json, int currentPage, int pageSize, String addtion) throws Exception{
		BaseCollection<BaseDepartmentRoleFunction> rlist = new BaseCollection<>();
		BaseDepartmentRoleFunction bean = new BaseDepartmentRoleFunction();
		JSON parser = new JSON(new StringReader(json));
		Map<String, Object> params = (Map<String, Object>) parser.parse(new BasicHandler());
		bean.setDataFromMap(params);
		PaginationParameter pp = PaginationParameter.generateFromMap(params);
		if(pp != null){
			if(pp.getCurrentPage() > 0) currentPage = pp.getCurrentPage();
			if(pp.getPageSize() > 0) pageSize = pp.getPageSize();
			if(pp.getCondition() != null) addtion = pp.getCondition();
		}
		DepartmentRoleFunction dao = new DepartmentRoleFunction();
		setConditions(bean, dao);
		dao.setCurrentPage(currentPage);
		dao.setPageLines(pageSize);
		List<BaseDepartmentRoleFunction> result = dao.conditionalLoad(addtion);
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
		BaseDepartmentRoleFunction bean = new BaseDepartmentRoleFunction();
		bean.setDataFromJSON(json);
		DepartmentRoleFunction dao = new DepartmentRoleFunction();
		dao.setDataFromBase(bean);
		int num = dao.save();
		dao.setDataToBase(bean);
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String update(String json) throws Exception{
		BaseDepartmentRoleFunction bean = new BaseDepartmentRoleFunction();
		bean.setDataFromJSON(json);
		DepartmentRoleFunction dao = new DepartmentRoleFunction();
		dao.setPrimaryKeyFromBase(bean);
		int num=0;
		if(dao.load()){dao.setDataFromBase(bean); num = dao.update(); }
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String delete(String json) throws Exception{
		BaseDepartmentRoleFunction bean = new BaseDepartmentRoleFunction();
		bean.setDataFromJSON(json);
		DepartmentRoleFunction dao = new DepartmentRoleFunction();
		setDeleteConditions(bean, dao);
		int num = dao.conditionalDelete();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String saveOrUpdate(String json) throws Exception{
		BaseDepartmentRoleFunction bean = new BaseDepartmentRoleFunction();
		bean.setDataFromJSON(json);
		DepartmentRoleFunction dao = new DepartmentRoleFunction();
		dao.setPrimaryKeyFromBase(bean);
		int ret = 0;
		if(dao.isPrimaryKeyNull()) { dao.setDataFromBase(bean); ret = dao.save(); bean = dao.generateBase(); }
		else if(dao.load()) { dao.setDataFromBase(bean); ret = dao.update(); bean = dao.generateBase(); }
		return bean.toOneLineJSON(ret, null);
	}

}


