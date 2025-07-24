package delicacy.system.handler;

import org.apache.log4j.Logger;
import delicacy.system.bean.BaseFunction;
import java.util.List;
import delicacy.system.dao.Function;
import delicacy.connection.ThreadConnection;
import delicacy.common.GenericDao;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import java.io.StringReader;
import java.util.Map;
import delicacy.common.PaginationParameter;
import delicacy.common.BaseCollection;

public class FunctionHandler implements GenericDao {

	private static final Logger __logger = Logger.getLogger(FunctionHandler.class);

	public static BaseFunction getFunctionById(
		java.lang.Integer function_id
	) throws Exception {
		Function dao = new Function();
		dao.setFunctionId(function_id);
		if (dao.load()) {
			return dao.generateBase();
		}
		return null;
	}

	public static boolean isFunctionExists(delicacy.system.bean.BaseFunction bean, String additional) throws Exception {

		Function dao = new Function();
		setConditions(bean, dao);
		boolean res = dao.isExist(additional);
		return res;
	}

	public static int countFunction(delicacy.system.bean.BaseFunction bean, String additional) throws Exception {

		Function dao = new Function();
		setConditions(bean, dao);
		int res = dao.countRows(additional);
		return res;
	}

	public static BaseCollection<BaseFunction> queryFunction(delicacy.system.bean.BaseFunction bean, int pageNo, int pageLines, String additionalCondition) throws Exception {
		Function dao = new Function();
		setConditions(bean, dao);
		dao.setCurrentPage(pageNo);
		dao.setPageLines(pageLines);
		java.util.List<BaseFunction> result = dao.conditionalLoad(additionalCondition);
		BaseCollection<BaseFunction> col = new BaseCollection<>();
		col.setCollections(result);
		col.setTotalPages(dao.getTotalPages());
		col.setCurrentPage(dao.getCurrentPage());
		col.setPageLines(dao.getPageLines());
		col.setTotalLines(dao.getTotalLines());
		col.setRecordNumber(result.size());
		return col;
	}

	public static BaseFunction addToFunction(BaseFunction function) throws Exception {
		return addToFunction(function, false);
	}

	public static BaseFunction addToFunction(BaseFunction function, boolean singleTransaction) throws Exception {
		if (singleTransaction) {
			ThreadConnection.beginTransaction();
		}
		Function dao = new Function();
		dao.setDataFromBase(function);
		int result = dao.save();
		if (singleTransaction) {
			ThreadConnection.commit();
		}
		return result == 1 ? dao.generateBase() : null;
	}

	public static BaseFunction addUpdateFunction(BaseFunction function) throws Exception {
		return addUpdateFunction(function, false);
	}

	public static BaseFunction addUpdateFunction(BaseFunction function, boolean singleTransaction) throws Exception {
		if (function.getFunctionId() == null) {
			return addToFunction(function);
		}
		if (singleTransaction) {
			ThreadConnection.beginTransaction();
		}
		StringBuilder sb = new StringBuilder();
		Function dao = new Function();
		dao.setDataFromBase(function);
		int result = 0;
		if (dao.load()) {
			dao.setDataFromBase(function);
			if (dao.isThisObjectModified()) {
				result = dao.update();
			} else {
				result = 1;
			}
		} else {
			result = dao.save();
		}
		if (singleTransaction) {
			ThreadConnection.commit();
		}
		return result == 1 ? dao.generateBase() : null;
	}

	public static int deleteFunction(BaseFunction bean) throws Exception {
		Function dao = new Function();
		if (setDeleteConditions(bean, dao) == 0) {
			return 0;
		}
		int result = dao.conditionalDelete();
		return result;
	}

	public static BaseFunction updateFunction(BaseFunction function) throws Exception {
		Function dao = new Function();
		dao.setFunctionId(function.getFunctionId());
		int result = 0;
		if (dao.load()) {
			dao.setDataFromBase(function);
			result = dao.update();
		}
		return result == 1 ? function : null;
	}

	public static BaseFunction updateFunctionDirect(BaseFunction function) throws Exception {
		Function dao = new Function();
		int result = 0;
		dao.setDataFromBase(function);
		result = dao.update();
		return result == 1 ? function : null;
	}

	public static int setDeleteConditions(BaseFunction bean, Function dao) {
		int count = 0;
		boolean foundKey = false;
		if (bean.getFunctionId() != null) {
			dao.setConditionFunctionId("=", bean.getFunctionId());
			count++;
			foundKey = true;
		}
		if (!foundKey) {
			if (bean.getFunctionCode() != null) {
				dao.setConditionFunctionCode("=", bean.getFunctionCode());
				count++;
			}
			if (bean.getParentId() != null) {
				dao.setConditionParentId("=", bean.getParentId());
				count++;
			}
			if (bean.getFunctionName() != null) {
				dao.setConditionFunctionName("=", bean.getFunctionName());
				count++;
			}
			if (bean.getApplicationId() != null) {
				dao.setConditionApplicationId("=", bean.getApplicationId());
				count++;
			}
			if (bean.getFunctionType() != null) {
				dao.setConditionFunctionType("=", bean.getFunctionType());
				count++;
			}
			if (bean.getPrivilegeType() != null) {
				dao.setConditionPrivilegeType("=", bean.getPrivilegeType());
				count++;
			}
			if (bean.getEnabled() != null) {
				dao.setConditionEnabled("=", bean.getEnabled());
				count++;
			}
		}
		return count;
	}

	public static int setConditions(BaseFunction bean, Function dao) {
		int count = 0;
		if (bean.getFunctionId() != null) {
			dao.setConditionFunctionId("=", bean.getFunctionId());
			count++;
		}
		if (bean.getFunctionCode() != null) {
			if (bean.getFunctionCode().indexOf("%") >= 0) {
				dao.setConditionFunctionCode("like", bean.getFunctionCode());
			} else {
				dao.setConditionFunctionCode("=", bean.getFunctionCode());
			}
			count++;
		}
		if (bean.getParentId() != null) {
			dao.setConditionParentId("=", bean.getParentId());
			count++;
		}
		if (bean.getFunctionName() != null) {
			if (bean.getFunctionName().indexOf("%") >= 0) {
				dao.setConditionFunctionName("like", bean.getFunctionName());
			} else {
				dao.setConditionFunctionName("=", bean.getFunctionName());
			}
			count++;
		}
		if (bean.getApplicationId() != null) {
			dao.setConditionApplicationId("=", bean.getApplicationId());
			count++;
		}
		if (bean.getFunctionType() != null) {
			dao.setConditionFunctionType("=", bean.getFunctionType());
			count++;
		}
		if (bean.getPrivilegeType() != null) {
			dao.setConditionPrivilegeType("=", bean.getPrivilegeType());
			count++;
		}
		if (bean.getEnabled() != null) {
			dao.setConditionEnabled("=", bean.getEnabled());
			count++;
		}
		return count;
	}

	@Override
	public String findUsingKey(String json) throws Exception {
		BaseFunction bean = new BaseFunction();
		bean.setDataFromJSON(json);
		Function dao = new Function();
		dao.setPrimaryKeyFromBase(bean);
		if (dao.load()) {
			dao.setDataToBase(bean);
			return bean.toOneLineJSON(1, null);
		}
		return bean.toOneLineJSON(0, "Record not found.");
	}

	@Override
	public String find(String json, int currentPage, int pageSize, String addtion) throws Exception {
		BaseCollection<BaseFunction> rlist = new BaseCollection<>();
		BaseFunction bean = new BaseFunction();
		JSON parser = new JSON(new StringReader(json));
		Map<String, Object> params = (Map<String, Object>) parser.parse(new BasicHandler());
		bean.setDataFromMap(params);
		PaginationParameter pp = PaginationParameter.generateFromMap(params);
		if (pp != null) {
			if (pp.getCurrentPage() > 0) {
				currentPage = pp.getCurrentPage();
			}
			if (pp.getPageSize() > 0) {
				pageSize = pp.getPageSize();
			}
			if (pp.getCondition() != null) {
				addtion = pp.getCondition();
			}
		}
		Function dao = new Function();
		setConditions(bean, dao);
		dao.setCurrentPage(currentPage);
		dao.setPageLines(pageSize);
		List<BaseFunction> result = dao.conditionalLoad(addtion);
		rlist.setCollections(result);
		rlist.setTotalPages(dao.getTotalPages());
		rlist.setCurrentPage(dao.getCurrentPage());
		rlist.setPageLines(dao.getPageLines());
		rlist.setTotalLines(dao.getTotalLines());
		rlist.setRecordNumber(result.size());
		return rlist.toJSON(null);
	}

	@Override
	public String save(String json) throws Exception {
		BaseFunction bean = new BaseFunction();
		bean.setDataFromJSON(json);
		Function dao = new Function();
		dao.setDataFromBase(bean);
		int num = dao.save();
		dao.setDataToBase(bean);
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String update(String json) throws Exception {
		BaseFunction bean = new BaseFunction();
		bean.setDataFromJSON(json);
		Function dao = new Function();
		dao.setPrimaryKeyFromBase(bean);
		int num = 0;
		if (dao.load()) {
			dao.setDataFromBase(bean);
			num = dao.update();
		}
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String delete(String json) throws Exception {
		BaseFunction bean = new BaseFunction();
		bean.setDataFromJSON(json);
		Function dao = new Function();
		setDeleteConditions(bean, dao);
		int num = dao.conditionalDelete();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String saveOrUpdate(String json) throws Exception {
		BaseFunction bean = new BaseFunction();
		bean.setDataFromJSON(json);
		Function dao = new Function();
		dao.setPrimaryKeyFromBase(bean);
		int ret = 0;
		if (dao.isPrimaryKeyNull()) {
			dao.setDataFromBase(bean);
			ret = dao.save();
			bean = dao.generateBase();
		} else if (dao.load()) {
			dao.setDataFromBase(bean);
			ret = dao.update();
			bean = dao.generateBase();
		}
		return bean.toOneLineJSON(ret, null);
	}

}
