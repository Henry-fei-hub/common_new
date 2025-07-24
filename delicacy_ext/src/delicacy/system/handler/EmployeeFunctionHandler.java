package delicacy.system.handler;

import org.apache.log4j.Logger;
import delicacy.system.bean.BaseEmployeeFunction;
import java.util.List;
import delicacy.system.dao.EmployeeFunction;
import delicacy.connection.ThreadConnection;
import delicacy.common.GenericDao;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import java.io.StringReader;
import java.util.Map;
import delicacy.common.PaginationParameter;
import delicacy.common.BaseCollection;

public class EmployeeFunctionHandler implements GenericDao {

	private static final Logger __logger = Logger.getLogger(EmployeeFunctionHandler.class);

	public static BaseEmployeeFunction getEmployeeFunctionById( 
		java.lang.Integer employee_function_id
	) throws Exception
	{
		EmployeeFunction dao = new EmployeeFunction();
		dao.setEmployeeFunctionId(employee_function_id);
		if(dao.load()){
			return dao.generateBase();
		}
		return null;
	}

	public static boolean isEmployeeFunctionExists( delicacy.system.bean.BaseEmployeeFunction bean, String additional ) throws Exception {

		EmployeeFunction dao = new EmployeeFunction();
		setConditions(bean, dao);
		boolean res = dao.isExist(additional);
		return res;
	}

	public static int countEmployeeFunction( delicacy.system.bean.BaseEmployeeFunction bean, String additional ) throws Exception {

		EmployeeFunction dao = new EmployeeFunction();
		setConditions(bean, dao);
		int res = dao.countRows(additional);
		return res;
	}

	public static BaseCollection<BaseEmployeeFunction> queryEmployeeFunction( delicacy.system.bean.BaseEmployeeFunction bean, int pageNo, int pageLines, String additionalCondition ) throws Exception {
		EmployeeFunction dao = new EmployeeFunction();
		setConditions(bean, dao);
		dao.setCurrentPage(pageNo);
		dao.setPageLines(pageLines);
		java.util.List<BaseEmployeeFunction> result = dao.conditionalLoad(additionalCondition);
		BaseCollection<BaseEmployeeFunction> col = new BaseCollection<>();
		col.setCollections(result);
		col.setTotalPages(dao.getTotalPages());
		col.setCurrentPage(dao.getCurrentPage());
		col.setPageLines(dao.getPageLines());
		col.setTotalLines(dao.getTotalLines());
		col.setRecordNumber(result.size());
		return col;
	}

	public static BaseEmployeeFunction addToEmployeeFunction ( BaseEmployeeFunction employeefunction )  throws Exception {
		return addToEmployeeFunction ( employeefunction , false);
	}

	public static BaseEmployeeFunction addToEmployeeFunction ( BaseEmployeeFunction employeefunction, boolean singleTransaction ) throws Exception {
		if(singleTransaction) ThreadConnection.beginTransaction();
		EmployeeFunction dao = new EmployeeFunction();
		dao.setDataFromBase(employeefunction);
		int result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static BaseEmployeeFunction addUpdateEmployeeFunction ( BaseEmployeeFunction employeefunction ) throws Exception {
		return addUpdateEmployeeFunction ( employeefunction , false);
	}

	public static BaseEmployeeFunction addUpdateEmployeeFunction ( BaseEmployeeFunction employeefunction, boolean singleTransaction  ) throws Exception {
		if(employeefunction.getEmployeeFunctionId() == null) return addToEmployeeFunction(employeefunction);
		if(singleTransaction) ThreadConnection.beginTransaction();
		StringBuilder sb = new StringBuilder();
		EmployeeFunction dao = new EmployeeFunction();
		dao.setDataFromBase(employeefunction);
		int result = 0;
		if(dao.load()) {
			dao.setDataFromBase(employeefunction); 
			if(dao.isThisObjectModified()) { 
				result = dao.update();
			} else result = 1;
		} else result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static int deleteEmployeeFunction ( BaseEmployeeFunction bean ) throws Exception {
		EmployeeFunction dao = new EmployeeFunction();
		if(setDeleteConditions(bean, dao) == 0) return 0;
		int result = dao.conditionalDelete();
		return result ;
	}

	public static BaseEmployeeFunction updateEmployeeFunction ( BaseEmployeeFunction employeefunction ) throws Exception {
		EmployeeFunction dao = new EmployeeFunction();
		dao.setEmployeeFunctionId( employeefunction.getEmployeeFunctionId() );
		int result = 0;
		if( dao.load() ) {
			dao.setDataFromBase(employeefunction);
			result = dao.update();
		}
		return result == 1 ? employeefunction : null ;
	}

	public static BaseEmployeeFunction updateEmployeeFunctionDirect( BaseEmployeeFunction employeefunction ) throws Exception {
		EmployeeFunction dao = new EmployeeFunction();
		int result = 0;
		dao.setDataFromBase(employeefunction);
		result = dao.update();
		return result == 1 ? employeefunction : null ;
	}

	public static int setDeleteConditions(BaseEmployeeFunction bean, EmployeeFunction dao){
		int count = 0;
		boolean foundKey = false;
		if(bean.getEmployeeFunctionId() != null) {
			dao.setConditionEmployeeFunctionId("=", bean.getEmployeeFunctionId());
			count++;
			foundKey = true;
		}
		if(!foundKey) {
			if(bean.getEmployeeId() != null) {
				dao.setConditionEmployeeId("=", bean.getEmployeeId());
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
		}
		return count;
	}

	public static int setConditions(BaseEmployeeFunction bean, EmployeeFunction dao){
		int count = 0;
		if(bean.getEmployeeFunctionId() != null) {
			dao.setConditionEmployeeFunctionId("=", bean.getEmployeeFunctionId());
			count++;
		}
		if(bean.getEmployeeId() != null) {
			dao.setConditionEmployeeId("=", bean.getEmployeeId());
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
		return count;
	}

	@Override
	public String findUsingKey(String json) throws Exception{
		BaseEmployeeFunction bean = new BaseEmployeeFunction();
		bean.setDataFromJSON(json);
		EmployeeFunction dao = new EmployeeFunction();
		dao.setPrimaryKeyFromBase(bean);
		if(dao.load()) { dao.setDataToBase(bean); return bean.toOneLineJSON(1,null); }
		return bean.toOneLineJSON(0,"Record not found.");
	}

	@Override
	public String find(String json, int currentPage, int pageSize, String addtion) throws Exception{
		BaseCollection<BaseEmployeeFunction> rlist = new BaseCollection<>();
		BaseEmployeeFunction bean = new BaseEmployeeFunction();
		JSON parser = new JSON(new StringReader(json));
		Map<String, Object> params = (Map<String, Object>) parser.parse(new BasicHandler());
		bean.setDataFromMap(params);
		PaginationParameter pp = PaginationParameter.generateFromMap(params);
		if(pp != null){
			if(pp.getCurrentPage() > 0) currentPage = pp.getCurrentPage();
			if(pp.getPageSize() > 0) pageSize = pp.getPageSize();
			if(pp.getCondition() != null) addtion = pp.getCondition();
		}
		EmployeeFunction dao = new EmployeeFunction();
		setConditions(bean, dao);
		dao.setCurrentPage(currentPage);
		dao.setPageLines(pageSize);
		List<BaseEmployeeFunction> result = dao.conditionalLoad(addtion);
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
		BaseEmployeeFunction bean = new BaseEmployeeFunction();
		bean.setDataFromJSON(json);
		EmployeeFunction dao = new EmployeeFunction();
		dao.setDataFromBase(bean);
		int num = dao.save();
		dao.setDataToBase(bean);
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String update(String json) throws Exception{
		BaseEmployeeFunction bean = new BaseEmployeeFunction();
		bean.setDataFromJSON(json);
		EmployeeFunction dao = new EmployeeFunction();
		dao.setPrimaryKeyFromBase(bean);
		int num=0;
		if(dao.load()){dao.setDataFromBase(bean); num = dao.update(); }
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String delete(String json) throws Exception{
		BaseEmployeeFunction bean = new BaseEmployeeFunction();
		bean.setDataFromJSON(json);
		EmployeeFunction dao = new EmployeeFunction();
		setDeleteConditions(bean, dao);
		int num = dao.conditionalDelete();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String saveOrUpdate(String json) throws Exception{
		BaseEmployeeFunction bean = new BaseEmployeeFunction();
		bean.setDataFromJSON(json);
		EmployeeFunction dao = new EmployeeFunction();
		dao.setPrimaryKeyFromBase(bean);
		int ret = 0;
		if(dao.isPrimaryKeyNull()) { dao.setDataFromBase(bean); ret = dao.save(); bean = dao.generateBase(); }
		else if(dao.load()) { dao.setDataFromBase(bean); ret = dao.update(); bean = dao.generateBase(); }
		return bean.toOneLineJSON(ret, null);
	}

}


