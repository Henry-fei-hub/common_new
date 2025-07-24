package delicacy.system.handler;

import org.apache.log4j.Logger;
import delicacy.system.bean.BaseMainFunction;
import java.util.List;
import delicacy.system.dao.MainFunction;
import delicacy.connection.ThreadConnection;
import delicacy.common.GenericDao;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import java.io.StringReader;
import java.util.Map;
import delicacy.common.PaginationParameter;
import delicacy.common.BaseCollection;

public class MainFunctionHandler implements GenericDao {

	private static final Logger __logger = Logger.getLogger(MainFunctionHandler.class);

	public static BaseMainFunction getMainFunctionById( 
		java.lang.Integer function_id
	) throws Exception
	{
		MainFunction dao = new MainFunction();
		dao.setFunctionId(function_id);
		if(dao.load()){
			return dao.generateBase();
		}
		return null;
	}

	public static boolean isMainFunctionExists( delicacy.system.bean.BaseMainFunction bean, String additional ) throws Exception {

		MainFunction dao = new MainFunction();
		setConditions(bean, dao);
		boolean res = dao.isExist(additional);
		return res;
	}

	public static int countMainFunction( delicacy.system.bean.BaseMainFunction bean, String additional ) throws Exception {

		MainFunction dao = new MainFunction();
		setConditions(bean, dao);
		int res = dao.countRows(additional);
		return res;
	}

	public static BaseCollection<BaseMainFunction> queryMainFunction( delicacy.system.bean.BaseMainFunction bean, int pageNo, int pageLines, String additionalCondition ) throws Exception {
		MainFunction dao = new MainFunction();
		setConditions(bean, dao);
		dao.setCurrentPage(pageNo);
		dao.setPageLines(pageLines);
		java.util.List<BaseMainFunction> result = dao.conditionalLoad(additionalCondition);
		BaseCollection<BaseMainFunction> col = new BaseCollection<>();
		col.setCollections(result);
		col.setTotalPages(dao.getTotalPages());
		col.setCurrentPage(dao.getCurrentPage());
		col.setPageLines(dao.getPageLines());
		col.setTotalLines(dao.getTotalLines());
		col.setRecordNumber(result.size());
		return col;
	}

	public static BaseMainFunction addToMainFunction ( BaseMainFunction mainfunction )  throws Exception {
		return addToMainFunction ( mainfunction , false);
	}

	public static BaseMainFunction addToMainFunction ( BaseMainFunction mainfunction, boolean singleTransaction ) throws Exception {
		if(singleTransaction) ThreadConnection.beginTransaction();
		MainFunction dao = new MainFunction();
		dao.setDataFromBase(mainfunction);
		int result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static BaseMainFunction addUpdateMainFunction ( BaseMainFunction mainfunction ) throws Exception {
		return addUpdateMainFunction ( mainfunction , false);
	}

	public static BaseMainFunction addUpdateMainFunction ( BaseMainFunction mainfunction, boolean singleTransaction  ) throws Exception {
		if(mainfunction.getFunctionId() == null) return addToMainFunction(mainfunction);
		if(singleTransaction) ThreadConnection.beginTransaction();
		StringBuilder sb = new StringBuilder();
		MainFunction dao = new MainFunction();
		dao.setDataFromBase(mainfunction);
		int result = 0;
		if(dao.load()) {
			dao.setDataFromBase(mainfunction); 
			if(dao.isThisObjectModified()) { 
				result = dao.update();
			} else result = 1;
		} else result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static int deleteMainFunction ( BaseMainFunction bean ) throws Exception {
		MainFunction dao = new MainFunction();
		if(setDeleteConditions(bean, dao) == 0) return 0;
		int result = dao.conditionalDelete();
		return result ;
	}

	public static BaseMainFunction updateMainFunction ( BaseMainFunction mainfunction ) throws Exception {
		MainFunction dao = new MainFunction();
		dao.setFunctionId( mainfunction.getFunctionId() );
		int result = 0;
		if( dao.load() ) {
			dao.setDataFromBase(mainfunction);
			result = dao.update();
		}
		return result == 1 ? mainfunction : null ;
	}

	public static BaseMainFunction updateMainFunctionDirect( BaseMainFunction mainfunction ) throws Exception {
		MainFunction dao = new MainFunction();
		int result = 0;
		dao.setDataFromBase(mainfunction);
		result = dao.update();
		return result == 1 ? mainfunction : null ;
	}

	public static int setDeleteConditions(BaseMainFunction bean, MainFunction dao){
		int count = 0;
		boolean foundKey = false;
		if(bean.getFunctionId() != null) {
			dao.setConditionFunctionId("=", bean.getFunctionId());
			count++;
			foundKey = true;
		}
		if(!foundKey) {
			if(bean.getFunctionName() != null) {
				dao.setConditionFunctionName("=", bean.getFunctionName());
				count++;
			}
			if(bean.getParentId() != null) {
				dao.setConditionParentId("=", bean.getParentId());
				count++;
			}
			if(bean.getFunctionCode() != null) {
				dao.setConditionFunctionCode("=", bean.getFunctionCode());
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
			if(bean.getLeaf() != null) {
				dao.setConditionLeaf("=", bean.getLeaf());
				count++;
			}
			if(bean.getIsMobile() != null) {
				dao.setConditionIsMobile("=", bean.getIsMobile());
				count++;
			}
			if(bean.getIcon() != null) {
				dao.setConditionIcon("=", bean.getIcon());
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
			if(bean.getIconColor() != null) {
				dao.setConditionIconColor("=", bean.getIconColor());
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
			if(bean.getExecuteName() != null) {
				dao.setConditionExecuteName("=", bean.getExecuteName());
				count++;
			}
			if(bean.getRemark() != null) {
				dao.setConditionRemark("=", bean.getRemark());
				count++;
			}
			if(bean.getSeqNo() != null) {
				dao.setConditionSeqNo("=", bean.getSeqNo());
				count++;
			}
		}
		return count;
	}

	public static int setConditions(BaseMainFunction bean, MainFunction dao){
		int count = 0;
		if(bean.getFunctionId() != null) {
			dao.setConditionFunctionId("=", bean.getFunctionId());
			count++;
		}
		if(bean.getFunctionName() != null) {
			if(bean.getFunctionName().indexOf("%") >= 0)
				dao.setConditionFunctionName("like", bean.getFunctionName());
			else
				dao.setConditionFunctionName("=", bean.getFunctionName());
			count++;
		}
		if(bean.getParentId() != null) {
			dao.setConditionParentId("=", bean.getParentId());
			count++;
		}
		if(bean.getFunctionCode() != null) {
			if(bean.getFunctionCode().indexOf("%") >= 0)
				dao.setConditionFunctionCode("like", bean.getFunctionCode());
			else
				dao.setConditionFunctionCode("=", bean.getFunctionCode());
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
		if(bean.getLeaf() != null) {
			dao.setConditionLeaf("=", bean.getLeaf());
			count++;
		}
		if(bean.getIsMobile() != null) {
			dao.setConditionIsMobile("=", bean.getIsMobile());
			count++;
		}
		if(bean.getIcon() != null) {
			if(bean.getIcon().indexOf("%") >= 0)
				dao.setConditionIcon("like", bean.getIcon());
			else
				dao.setConditionIcon("=", bean.getIcon());
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
		if(bean.getIconColor() != null) {
			if(bean.getIconColor().indexOf("%") >= 0)
				dao.setConditionIconColor("like", bean.getIconColor());
			else
				dao.setConditionIconColor("=", bean.getIconColor());
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
		if(bean.getExecuteName() != null) {
			if(bean.getExecuteName().indexOf("%") >= 0)
				dao.setConditionExecuteName("like", bean.getExecuteName());
			else
				dao.setConditionExecuteName("=", bean.getExecuteName());
			count++;
		}
		if(bean.getRemark() != null) {
			if(bean.getRemark().indexOf("%") >= 0)
				dao.setConditionRemark("like", bean.getRemark());
			else
				dao.setConditionRemark("=", bean.getRemark());
			count++;
		}
		if(bean.getSeqNo() != null) {
			dao.setConditionSeqNo("=", bean.getSeqNo());
			count++;
		}
		return count;
	}

	@Override
	public String findUsingKey(String json) throws Exception{
		BaseMainFunction bean = new BaseMainFunction();
		bean.setDataFromJSON(json);
		MainFunction dao = new MainFunction();
		dao.setPrimaryKeyFromBase(bean);
		if(dao.load()) { dao.setDataToBase(bean); return bean.toOneLineJSON(1,null); }
		return bean.toOneLineJSON(0,"Record not found.");
	}

	@Override
	public String find(String json, int currentPage, int pageSize, String addtion) throws Exception{
		BaseCollection<BaseMainFunction> rlist = new BaseCollection<>();
		BaseMainFunction bean = new BaseMainFunction();
		JSON parser = new JSON(new StringReader(json));
		Map<String, Object> params = (Map<String, Object>) parser.parse(new BasicHandler());
		bean.setDataFromMap(params);
		PaginationParameter pp = PaginationParameter.generateFromMap(params);
		if(pp != null){
			if(pp.getCurrentPage() > 0) currentPage = pp.getCurrentPage();
			if(pp.getPageSize() > 0) pageSize = pp.getPageSize();
			if(pp.getCondition() != null) addtion = pp.getCondition();
		}
		MainFunction dao = new MainFunction();
		setConditions(bean, dao);
		dao.setCurrentPage(currentPage);
		dao.setPageLines(pageSize);
		List<BaseMainFunction> result = dao.conditionalLoad(addtion);
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
		BaseMainFunction bean = new BaseMainFunction();
		bean.setDataFromJSON(json);
		MainFunction dao = new MainFunction();
		dao.setDataFromBase(bean);
		int num = dao.save();
		dao.setDataToBase(bean);
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String update(String json) throws Exception{
		BaseMainFunction bean = new BaseMainFunction();
		bean.setDataFromJSON(json);
		MainFunction dao = new MainFunction();
		dao.setPrimaryKeyFromBase(bean);
		int num=0;
		if(dao.load()){dao.setDataFromBase(bean); num = dao.update(); }
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String delete(String json) throws Exception{
		BaseMainFunction bean = new BaseMainFunction();
		bean.setDataFromJSON(json);
		MainFunction dao = new MainFunction();
		setDeleteConditions(bean, dao);
		int num = dao.conditionalDelete();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String saveOrUpdate(String json) throws Exception{
		BaseMainFunction bean = new BaseMainFunction();
		bean.setDataFromJSON(json);
		MainFunction dao = new MainFunction();
		dao.setPrimaryKeyFromBase(bean);
		int ret = 0;
		if(dao.isPrimaryKeyNull()) { dao.setDataFromBase(bean); ret = dao.save(); bean = dao.generateBase(); }
		else if(dao.load()) { dao.setDataFromBase(bean); ret = dao.update(); bean = dao.generateBase(); }
		return bean.toOneLineJSON(ret, null);
	}

}


