package delicacy.system.handler;

import org.apache.log4j.Logger;
import delicacy.system.bean.BaseApplication;
import java.util.List;
import delicacy.system.dao.Application;
import delicacy.connection.ThreadConnection;
import delicacy.common.GenericDao;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import java.io.StringReader;
import java.util.Map;
import delicacy.common.PaginationParameter;
import delicacy.common.BaseCollection;

public class ApplicationHandler implements GenericDao {

	private static final Logger __logger = Logger.getLogger(ApplicationHandler.class);

	public static BaseApplication getApplicationById( 
		java.lang.Integer application_id
	) throws Exception
	{
		Application dao = new Application();
		dao.setApplicationId(application_id);
		if(dao.load()){
			return dao.generateBase();
		}
		return null;
	}

	public static boolean isApplicationExists( delicacy.system.bean.BaseApplication bean, String additional ) throws Exception {

		Application dao = new Application();
		setConditions(bean, dao);
		boolean res = dao.isExist(additional);
		return res;
	}

	public static int countApplication( delicacy.system.bean.BaseApplication bean, String additional ) throws Exception {

		Application dao = new Application();
		setConditions(bean, dao);
		int res = dao.countRows(additional);
		return res;
	}

	public static BaseCollection<BaseApplication> queryApplication( delicacy.system.bean.BaseApplication bean, int pageNo, int pageLines, String additionalCondition ) throws Exception {
		Application dao = new Application();
		setConditions(bean, dao);
		dao.setCurrentPage(pageNo);
		dao.setPageLines(pageLines);
		java.util.List<BaseApplication> result = dao.conditionalLoad(additionalCondition);
		BaseCollection<BaseApplication> col = new BaseCollection<>();
		col.setCollections(result);
		col.setTotalPages(dao.getTotalPages());
		col.setCurrentPage(dao.getCurrentPage());
		col.setPageLines(dao.getPageLines());
		col.setTotalLines(dao.getTotalLines());
		col.setRecordNumber(result.size());
		return col;
	}

	public static BaseApplication addToApplication ( BaseApplication application )  throws Exception {
		return addToApplication ( application , false);
	}

	public static BaseApplication addToApplication ( BaseApplication application, boolean singleTransaction ) throws Exception {
		if(singleTransaction) ThreadConnection.beginTransaction();
		Application dao = new Application();
		dao.setDataFromBase(application);
		int result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static BaseApplication addUpdateApplication ( BaseApplication application ) throws Exception {
		return addUpdateApplication ( application , false);
	}

	public static BaseApplication addUpdateApplication ( BaseApplication application, boolean singleTransaction  ) throws Exception {
		if(application.getApplicationId() == null) return addToApplication(application);
		if(singleTransaction) ThreadConnection.beginTransaction();
		StringBuilder sb = new StringBuilder();
		Application dao = new Application();
		dao.setDataFromBase(application);
		int result = 0;
		if(dao.load()) {
			dao.setDataFromBase(application); 
			if(dao.isThisObjectModified()) { 
				result = dao.update();
			} else result = 1;
		} else result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static int deleteApplication ( BaseApplication bean ) throws Exception {
		Application dao = new Application();
		if(setDeleteConditions(bean, dao) == 0) return 0;
		int result = dao.conditionalDelete();
		return result ;
	}

	public static BaseApplication updateApplication ( BaseApplication application ) throws Exception {
		Application dao = new Application();
		dao.setApplicationId( application.getApplicationId() );
		int result = 0;
		if( dao.load() ) {
			dao.setDataFromBase(application);
			result = dao.update();
		}
		return result == 1 ? application : null ;
	}

	public static BaseApplication updateApplicationDirect( BaseApplication application ) throws Exception {
		Application dao = new Application();
		int result = 0;
		dao.setDataFromBase(application);
		result = dao.update();
		return result == 1 ? application : null ;
	}

	public static int setDeleteConditions(BaseApplication bean, Application dao){
		int count = 0;
		boolean foundKey = false;
		if(bean.getApplicationId() != null) {
			dao.setConditionApplicationId("=", bean.getApplicationId());
			count++;
			foundKey = true;
		}
		if(!foundKey) {
			if(bean.getApplicationName() != null) {
				dao.setConditionApplicationName("=", bean.getApplicationName());
				count++;
			}
		}
		return count;
	}

	public static int setConditions(BaseApplication bean, Application dao){
		int count = 0;
		if(bean.getApplicationId() != null) {
			dao.setConditionApplicationId("=", bean.getApplicationId());
			count++;
		}
		if(bean.getApplicationName() != null) {
			if(bean.getApplicationName().indexOf("%") >= 0)
				dao.setConditionApplicationName("like", bean.getApplicationName());
			else
				dao.setConditionApplicationName("=", bean.getApplicationName());
			count++;
		}
		return count;
	}

	@Override
	public String findUsingKey(String json) throws Exception{
		BaseApplication bean = new BaseApplication();
		bean.setDataFromJSON(json);
		Application dao = new Application();
		dao.setPrimaryKeyFromBase(bean);
		if(dao.load()) { dao.setDataToBase(bean); return bean.toOneLineJSON(1,null); }
		return bean.toOneLineJSON(0,"Record not found.");
	}

	@Override
	public String find(String json, int currentPage, int pageSize, String addtion) throws Exception{
		BaseCollection<BaseApplication> rlist = new BaseCollection<>();
		BaseApplication bean = new BaseApplication();
		JSON parser = new JSON(new StringReader(json));
		Map<String, Object> params = (Map<String, Object>) parser.parse(new BasicHandler());
		bean.setDataFromMap(params);
		PaginationParameter pp = PaginationParameter.generateFromMap(params);
		if(pp != null){
			if(pp.getCurrentPage() > 0) currentPage = pp.getCurrentPage();
			if(pp.getPageSize() > 0) pageSize = pp.getPageSize();
			if(pp.getCondition() != null) addtion = pp.getCondition();
		}
		Application dao = new Application();
		setConditions(bean, dao);
		dao.setCurrentPage(currentPage);
		dao.setPageLines(pageSize);
		List<BaseApplication> result = dao.conditionalLoad(addtion);
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
		BaseApplication bean = new BaseApplication();
		bean.setDataFromJSON(json);
		Application dao = new Application();
		dao.setDataFromBase(bean);
		int num = dao.save();
		dao.setDataToBase(bean);
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String update(String json) throws Exception{
		BaseApplication bean = new BaseApplication();
		bean.setDataFromJSON(json);
		Application dao = new Application();
		dao.setPrimaryKeyFromBase(bean);
		int num=0;
		if(dao.load()){dao.setDataFromBase(bean); num = dao.update(); }
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String delete(String json) throws Exception{
		BaseApplication bean = new BaseApplication();
		bean.setDataFromJSON(json);
		Application dao = new Application();
		setDeleteConditions(bean, dao);
		int num = dao.conditionalDelete();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String saveOrUpdate(String json) throws Exception{
		BaseApplication bean = new BaseApplication();
		bean.setDataFromJSON(json);
		Application dao = new Application();
		dao.setPrimaryKeyFromBase(bean);
		int ret = 0;
		if(dao.isPrimaryKeyNull()) { dao.setDataFromBase(bean); ret = dao.save(); bean = dao.generateBase(); }
		else if(dao.load()) { dao.setDataFromBase(bean); ret = dao.update(); bean = dao.generateBase(); }
		return bean.toOneLineJSON(ret, null);
	}

}


