package delicacy.system.handler;

import org.apache.log4j.Logger;
import delicacy.system.bean.BaseMobileFunction;
import java.util.List;
import delicacy.system.dao.MobileFunction;
import delicacy.connection.ThreadConnection;
import delicacy.common.GenericDao;
import java.util.Map;
import delicacy.common.PaginationParameter;
import delicacy.common.BaseCollection;

public class MobileFunctionHandler implements GenericDao {

	private static final Logger __logger = Logger.getLogger(MobileFunctionHandler.class);

	public static BaseMobileFunction getMobileFunctionById( 
		java.lang.Integer function_id
	) throws Exception
	{
		MobileFunction dao = new MobileFunction();
		dao.setFunctionId(function_id);
		if(dao.load()){
			return dao.generateBase();
		}
		return null;
	}

	public static boolean isMobileFunctionExists( delicacy.system.bean.BaseMobileFunction bean, String additional ) throws Exception {

		MobileFunction dao = new MobileFunction();
		setConditions(bean, dao);
		boolean res = dao.isExist(additional);
		return res;
	}

	public static int countMobileFunction( delicacy.system.bean.BaseMobileFunction bean, String additional ) throws Exception {

		MobileFunction dao = new MobileFunction();
		setConditions(bean, dao);
		int res = dao.countRows(additional);
		return res;
	}

	public static BaseCollection<BaseMobileFunction> queryMobileFunction( delicacy.system.bean.BaseMobileFunction bean, int pageNo, int pageLines, String additionalCondition ) throws Exception {
		MobileFunction dao = new MobileFunction();
		setConditions(bean, dao);
		dao.setCurrentPage(pageNo);
		dao.setPageLines(pageLines);
		java.util.List<BaseMobileFunction> result = dao.conditionalLoad(additionalCondition);
		BaseCollection<BaseMobileFunction> col = new BaseCollection<>();
		col.setCollections(result);
		col.setTotalPages(dao.getTotalPages());
		col.setCurrentPage(dao.getCurrentPage());
		col.setPageLines(dao.getPageLines());
		col.setTotalLines(dao.getTotalLines());
		col.setRecordNumber(result.size());
		return col;
	}

	public static BaseMobileFunction addToMobileFunction ( BaseMobileFunction mobilefunction )  throws Exception {
		return addToMobileFunction ( mobilefunction , false);
	}

	public static BaseMobileFunction addToMobileFunction ( BaseMobileFunction mobilefunction, boolean singleTransaction ) throws Exception {
		if(singleTransaction) ThreadConnection.beginTransaction();
		MobileFunction dao = new MobileFunction();
		dao.setDataFromBase(mobilefunction);
		int result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static BaseMobileFunction addUpdateMobileFunction ( BaseMobileFunction mobilefunction ) throws Exception {
		return addUpdateMobileFunction ( mobilefunction , false);
	}

	public static BaseMobileFunction addUpdateMobileFunction ( BaseMobileFunction mobilefunction, boolean singleTransaction  ) throws Exception {
		if(mobilefunction.getFunctionId() == null) return addToMobileFunction(mobilefunction);
		if(singleTransaction) ThreadConnection.beginTransaction();
		StringBuilder sb = new StringBuilder();
		MobileFunction dao = new MobileFunction();
		dao.setDataFromBase(mobilefunction);
		int result = 0;
		if(dao.load()) {
			dao.setDataFromBase(mobilefunction); 
			if(dao.isThisObjectModified()) { 
				result = dao.update();
			} else result = 1;
		} else result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static int deleteMobileFunction ( BaseMobileFunction bean ) throws Exception {
		MobileFunction dao = new MobileFunction();
		if(setDeleteConditions(bean, dao) == 0) return 0;
		int result = dao.conditionalDelete();
		return result ;
	}

	public static BaseMobileFunction updateMobileFunction ( BaseMobileFunction mobilefunction ) throws Exception {
		MobileFunction dao = new MobileFunction();
		dao.setFunctionId( mobilefunction.getFunctionId() );
		int result = 0;
		if( dao.load() ) {
			dao.setDataFromBase(mobilefunction);
			result = dao.update();
		}
		return result == 1 ? mobilefunction : null ;
	}

	public static BaseMobileFunction updateMobileFunctionDirect( BaseMobileFunction mobilefunction ) throws Exception {
		MobileFunction dao = new MobileFunction();
		int result = 0;
		dao.setDataFromBase(mobilefunction);
		result = dao.update();
		return result == 1 ? mobilefunction : null ;
	}

	public static int setDeleteConditions(BaseMobileFunction bean, MobileFunction dao){
		int count = 0;
		boolean foundKey = false;
		if(bean.getFunctionId() != null) {
			dao.setConditionFunctionId("=", bean.getFunctionId());
			count++;
			foundKey = true;
		}
		if(!foundKey) {
			if(bean.getFunctionCode() != null) {
				dao.setConditionFunctionCode("=", bean.getFunctionCode());
				count++;
			}
			if(bean.getParentId() != null) {
				dao.setConditionParentId("=", bean.getParentId());
				count++;
			}
			if(bean.getFunctionName() != null) {
				dao.setConditionFunctionName("=", bean.getFunctionName());
				count++;
			}
			if(bean.getApplicationId() != null) {
				dao.setConditionApplicationId("=", bean.getApplicationId());
				count++;
			}
			if(bean.getFunctionType() != null) {
				dao.setConditionFunctionType("=", bean.getFunctionType());
				count++;
			}
			if(bean.getPrivilegeType() != null) {
				dao.setConditionPrivilegeType("=", bean.getPrivilegeType());
				count++;
			}
			if(bean.getEnabled() != null) {
				dao.setConditionEnabled("=", bean.getEnabled());
				count++;
			}
			if(bean.getFunctionStaticName() != null) {
				dao.setConditionFunctionStaticName("=", bean.getFunctionStaticName());
				count++;
			}
			if(bean.getIconName() != null) {
				dao.setConditionIconName("=", bean.getIconName());
				count++;
			}
			if(bean.getStyleName() != null) {
				dao.setConditionStyleName("=", bean.getStyleName());
				count++;
			}
			if(bean.getClassName() != null) {
				dao.setConditionClassName("=", bean.getClassName());
				count++;
			}
			if(bean.getExecutePageName() != null) {
				dao.setConditionExecutePageName("=", bean.getExecutePageName());
				count++;
			}
			if(bean.getIconColor() != null) {
				dao.setConditionIconColor("=", bean.getIconColor());
				count++;
			}
			if(bean.getAppPageName() != null) {
				dao.setConditionAppPageName("=", bean.getAppPageName());
				count++;
			}
		}
		return count;
	}

	public static int setConditions(BaseMobileFunction bean, MobileFunction dao){
		int count = 0;
		if(bean.getFunctionId() != null) {
			dao.setConditionFunctionId("=", bean.getFunctionId());
			count++;
		}
		if(bean.getFunctionCode() != null) {
			if(bean.getFunctionCode().indexOf("%") >= 0)
				dao.setConditionFunctionCode("like", bean.getFunctionCode());
			else
				dao.setConditionFunctionCode("=", bean.getFunctionCode());
			count++;
		}
		if(bean.getParentId() != null) {
			dao.setConditionParentId("=", bean.getParentId());
			count++;
		}
		if(bean.getFunctionName() != null) {
			if(bean.getFunctionName().indexOf("%") >= 0)
				dao.setConditionFunctionName("like", bean.getFunctionName());
			else
				dao.setConditionFunctionName("=", bean.getFunctionName());
			count++;
		}
		if(bean.getApplicationId() != null) {
			dao.setConditionApplicationId("=", bean.getApplicationId());
			count++;
		}
		if(bean.getFunctionType() != null) {
			dao.setConditionFunctionType("=", bean.getFunctionType());
			count++;
		}
		if(bean.getPrivilegeType() != null) {
			dao.setConditionPrivilegeType("=", bean.getPrivilegeType());
			count++;
		}
		if(bean.getEnabled() != null) {
			dao.setConditionEnabled("=", bean.getEnabled());
			count++;
		}
		if(bean.getFunctionStaticName() != null) {
			if(bean.getFunctionStaticName().indexOf("%") >= 0)
				dao.setConditionFunctionStaticName("like", bean.getFunctionStaticName());
			else
				dao.setConditionFunctionStaticName("=", bean.getFunctionStaticName());
			count++;
		}
		if(bean.getIconName() != null) {
			if(bean.getIconName().indexOf("%") >= 0)
				dao.setConditionIconName("like", bean.getIconName());
			else
				dao.setConditionIconName("=", bean.getIconName());
			count++;
		}
		if(bean.getStyleName() != null) {
			if(bean.getStyleName().indexOf("%") >= 0)
				dao.setConditionStyleName("like", bean.getStyleName());
			else
				dao.setConditionStyleName("=", bean.getStyleName());
			count++;
		}
		if(bean.getClassName() != null) {
			if(bean.getClassName().indexOf("%") >= 0)
				dao.setConditionClassName("like", bean.getClassName());
			else
				dao.setConditionClassName("=", bean.getClassName());
			count++;
		}
		if(bean.getExecutePageName() != null) {
			if(bean.getExecutePageName().indexOf("%") >= 0)
				dao.setConditionExecutePageName("like", bean.getExecutePageName());
			else
				dao.setConditionExecutePageName("=", bean.getExecutePageName());
			count++;
		}
		if(bean.getMarginTop() != null) {
			dao.setConditionMarginTop("=", bean.getMarginTop());
			count++;
		}
		if(bean.getMarginBottom() != null) {
			dao.setConditionMarginBottom("=", bean.getMarginBottom());
			count++;
		}
		if(bean.getIconColor() != null) {
			if(bean.getIconColor().indexOf("%") >= 0)
				dao.setConditionIconColor("like", bean.getIconColor());
			else
				dao.setConditionIconColor("=", bean.getIconColor());
			count++;
		}
		if(bean.getAppPageName() != null) {
			if(bean.getAppPageName().indexOf("%") >= 0)
				dao.setConditionAppPageName("like", bean.getAppPageName());
			else
				dao.setConditionAppPageName("=", bean.getAppPageName());
			count++;
		}
		return count;
	}

	@Override
	public String findUsingKey(String json) throws Exception{
		BaseMobileFunction bean = new BaseMobileFunction();
		bean.setDataFromJSON(json);
		MobileFunction dao = new MobileFunction();
		dao.setPrimaryKeyFromBase(bean);
		if(dao.load()) { dao.setDataToBase(bean); return bean.toOneLineJSON(1,null); }
		return bean.toOneLineJSON(0,"Record not found.");
	}

	@Override
	public String find(String json, int currentPage, int pageSize, String addtion) throws Exception{
		BaseCollection<BaseMobileFunction> rlist = new BaseCollection<>();
		BaseMobileFunction bean = new BaseMobileFunction();
		Map<String, Object> params = (Map<String, Object>) delicacy.common.JsonParser.parse(json);
		bean.setDataFromMap(params);
		PaginationParameter pp = PaginationParameter.generateFromMap(params);
		if(pp != null){
			if(pp.getCurrentPage() > 0) currentPage = pp.getCurrentPage();
			if(pp.getPageSize() > 0) pageSize = pp.getPageSize();
			if(pp.getCondition() != null) addtion = pp.getCondition();
		}
		MobileFunction dao = new MobileFunction();
		setConditions(bean, dao);
		dao.setCurrentPage(currentPage);
		dao.setPageLines(pageSize);
		List<BaseMobileFunction> result = dao.conditionalLoad(addtion);
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
		BaseMobileFunction bean = new BaseMobileFunction();
		bean.setDataFromJSON(json);
		MobileFunction dao = new MobileFunction();
		dao.setDataFromBase(bean);
		int num = dao.save();
		dao.setDataToBase(bean);
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String update(String json) throws Exception{
		BaseMobileFunction bean = new BaseMobileFunction();
		bean.setDataFromJSON(json);
		MobileFunction dao = new MobileFunction();
		dao.setPrimaryKeyFromBase(bean);
		int num=0;
		if(dao.load()){dao.setDataFromBase(bean); num = dao.update(); }
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String delete(String json) throws Exception{
		BaseMobileFunction bean = new BaseMobileFunction();
		bean.setDataFromJSON(json);
		MobileFunction dao = new MobileFunction();
		setDeleteConditions(bean, dao);
		int num = dao.conditionalDelete();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String saveOrUpdate(String json) throws Exception{
		BaseMobileFunction bean = new BaseMobileFunction();
		bean.setDataFromJSON(json);
		MobileFunction dao = new MobileFunction();
		dao.setPrimaryKeyFromBase(bean);
		int ret = 0;
		if(dao.isPrimaryKeyNull()) { dao.setDataFromBase(bean); ret = dao.save(); bean = dao.generateBase(); }
		else if(dao.load()) { dao.setDataFromBase(bean); ret = dao.update(); bean = dao.generateBase(); }
		return bean.toOneLineJSON(ret, null);
	}

}


