package delicacy.system.handler;

import org.apache.log4j.Logger;
import delicacy.system.bean.BaseSystemLog;
import java.util.List;
import delicacy.system.dao.SystemLog;
import delicacy.connection.ThreadConnection;
import delicacy.common.GenericDao;
import java.util.Map;
import delicacy.common.PaginationParameter;
import delicacy.common.BaseCollection;

public class SystemLogHandler implements GenericDao {

	private static final Logger __logger = Logger.getLogger(SystemLogHandler.class);

	public static BaseSystemLog getSystemLogById( 
		java.lang.Integer system_log_id
	) throws Exception
	{
		SystemLog dao = new SystemLog();
		dao.setSystemLogId(system_log_id);
		if(dao.load()){
			return dao.generateBase();
		}
		return null;
	}

	public static boolean isSystemLogExists( delicacy.system.bean.BaseSystemLog bean, String additional ) throws Exception {

		SystemLog dao = new SystemLog();
		setConditions(bean, dao);
		boolean res = dao.isExist(additional);
		return res;
	}

	public static int countSystemLog( delicacy.system.bean.BaseSystemLog bean, String additional ) throws Exception {

		SystemLog dao = new SystemLog();
		setConditions(bean, dao);
		int res = dao.countRows(additional);
		return res;
	}

	public static BaseCollection<BaseSystemLog> querySystemLog( delicacy.system.bean.BaseSystemLog bean, int pageNo, int pageLines, String additionalCondition ) throws Exception {
		SystemLog dao = new SystemLog();
		setConditions(bean, dao);
		dao.setCurrentPage(pageNo);
		dao.setPageLines(pageLines);
		java.util.List<BaseSystemLog> result = dao.conditionalLoad(additionalCondition);
		BaseCollection<BaseSystemLog> col = new BaseCollection<>();
		col.setCollections(result);
		col.setTotalPages(dao.getTotalPages());
		col.setCurrentPage(dao.getCurrentPage());
		col.setPageLines(dao.getPageLines());
		col.setTotalLines(dao.getTotalLines());
		col.setRecordNumber(result.size());
		return col;
	}

	public static BaseSystemLog addToSystemLog ( BaseSystemLog systemlog )  throws Exception {
		return addToSystemLog ( systemlog , false);
	}

	public static BaseSystemLog addToSystemLog ( BaseSystemLog systemlog, boolean singleTransaction ) throws Exception {
		if(singleTransaction) ThreadConnection.beginTransaction();
		SystemLog dao = new SystemLog();
		dao.setDataFromBase(systemlog);
		int result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static BaseSystemLog addUpdateSystemLog ( BaseSystemLog systemlog ) throws Exception {
		return addUpdateSystemLog ( systemlog , false);
	}

	public static BaseSystemLog addUpdateSystemLog ( BaseSystemLog systemlog, boolean singleTransaction  ) throws Exception {
		if(systemlog.getSystemLogId() == null) return addToSystemLog(systemlog);
		if(singleTransaction) ThreadConnection.beginTransaction();
		StringBuilder sb = new StringBuilder();
		SystemLog dao = new SystemLog();
		dao.setDataFromBase(systemlog);
		int result = 0;
		if(dao.load()) {
			dao.setDataFromBase(systemlog); 
			if(dao.isThisObjectModified()) { 
				result = dao.update();
			} else result = 1;
		} else result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static int deleteSystemLog ( BaseSystemLog bean ) throws Exception {
		SystemLog dao = new SystemLog();
		if(setDeleteConditions(bean, dao) == 0) return 0;
		int result = dao.conditionalDelete();
		return result ;
	}

	public static BaseSystemLog updateSystemLog ( BaseSystemLog systemlog ) throws Exception {
		SystemLog dao = new SystemLog();
		dao.setSystemLogId( systemlog.getSystemLogId() );
		int result = 0;
		if( dao.load() ) {
			dao.setDataFromBase(systemlog);
			result = dao.update();
		}
		return result == 1 ? systemlog : null ;
	}

	public static BaseSystemLog updateSystemLogDirect( BaseSystemLog systemlog ) throws Exception {
		SystemLog dao = new SystemLog();
		int result = 0;
		dao.setDataFromBase(systemlog);
		result = dao.update();
		return result == 1 ? systemlog : null ;
	}

	public static int setDeleteConditions(BaseSystemLog bean, SystemLog dao){
		int count = 0;
		boolean foundKey = false;
		if(bean.getSystemLogId() != null) {
			dao.setConditionSystemLogId("=", bean.getSystemLogId());
			count++;
			foundKey = true;
		}
		if(!foundKey) {
			if(bean.getFromHost() != null) {
				dao.setConditionFromHost("=", bean.getFromHost());
				count++;
			}
			if(bean.getOperator() != null) {
				dao.setConditionOperator("=", bean.getOperator());
				count++;
			}
			if(bean.getOperatorName() != null) {
				dao.setConditionOperatorName("=", bean.getOperatorName());
				count++;
			}
			if(bean.getLogContent() != null) {
				dao.setConditionLogContent("=", bean.getLogContent());
				count++;
			}
			if(bean.getLogType() != null) {
				dao.setConditionLogType("=", bean.getLogType());
				count++;
			}
		}
		return count;
	}

	public static int setConditions(BaseSystemLog bean, SystemLog dao){
		int count = 0;
		if(bean.getSystemLogId() != null) {
			dao.setConditionSystemLogId("=", bean.getSystemLogId());
			count++;
		}
		if(bean.getLogTime() != null) {
			dao.setConditionLogTime(">=", bean.getLogTime());
			count++;
		}
		if(bean.getFromHost() != null) {
			if(bean.getFromHost().indexOf("%") >= 0)
				dao.setConditionFromHost("like", bean.getFromHost());
			else
				dao.setConditionFromHost("=", bean.getFromHost());
			count++;
		}
		if(bean.getOperator() != null) {
			dao.setConditionOperator("=", bean.getOperator());
			count++;
		}
		if(bean.getOperatorName() != null) {
			if(bean.getOperatorName().indexOf("%") >= 0)
				dao.setConditionOperatorName("like", bean.getOperatorName());
			else
				dao.setConditionOperatorName("=", bean.getOperatorName());
			count++;
		}
		if(bean.getLogContent() != null) {
			if(bean.getLogContent().indexOf("%") >= 0)
				dao.setConditionLogContent("like", bean.getLogContent());
			else
				dao.setConditionLogContent("=", bean.getLogContent());
			count++;
		}
		if(bean.getLogType() != null) {
			dao.setConditionLogType("=", bean.getLogType());
			count++;
		}
		return count;
	}

	@Override
	public String findUsingKey(String json) throws Exception{
		BaseSystemLog bean = new BaseSystemLog();
		bean.setDataFromJSON(json);
		SystemLog dao = new SystemLog();
		dao.setPrimaryKeyFromBase(bean);
		if(dao.load()) { dao.setDataToBase(bean); return bean.toOneLineJSON(1,null); }
		return bean.toOneLineJSON(0,"Record not found.");
	}

	@Override
	public String find(String json, int currentPage, int pageSize, String addtion) throws Exception{
		BaseCollection<BaseSystemLog> rlist = new BaseCollection<>();
		BaseSystemLog bean = new BaseSystemLog();
		Map<String, Object> params = (Map<String, Object>) delicacy.common.JsonParser.parse(json);
		bean.setDataFromMap(params);
		PaginationParameter pp = PaginationParameter.generateFromMap(params);
		if(pp != null){
			if(pp.getCurrentPage() > 0) currentPage = pp.getCurrentPage();
			if(pp.getPageSize() > 0) pageSize = pp.getPageSize();
			if(pp.getCondition() != null) addtion = pp.getCondition();
		}
		SystemLog dao = new SystemLog();
		setConditions(bean, dao);
		dao.setCurrentPage(currentPage);
		dao.setPageLines(pageSize);
		List<BaseSystemLog> result = dao.conditionalLoad(addtion);
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
		BaseSystemLog bean = new BaseSystemLog();
		bean.setDataFromJSON(json);
		SystemLog dao = new SystemLog();
		dao.setDataFromBase(bean);
		int num = dao.save();
		dao.setDataToBase(bean);
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String update(String json) throws Exception{
		BaseSystemLog bean = new BaseSystemLog();
		bean.setDataFromJSON(json);
		SystemLog dao = new SystemLog();
		dao.setPrimaryKeyFromBase(bean);
		int num=0;
		if(dao.load()){dao.setDataFromBase(bean); num = dao.update(); }
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String delete(String json) throws Exception{
		BaseSystemLog bean = new BaseSystemLog();
		bean.setDataFromJSON(json);
		SystemLog dao = new SystemLog();
		setDeleteConditions(bean, dao);
		int num = dao.conditionalDelete();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String saveOrUpdate(String json) throws Exception{
		BaseSystemLog bean = new BaseSystemLog();
		bean.setDataFromJSON(json);
		SystemLog dao = new SystemLog();
		dao.setPrimaryKeyFromBase(bean);
		int ret = 0;
		if(dao.isPrimaryKeyNull()) { dao.setDataFromBase(bean); ret = dao.save(); bean = dao.generateBase(); }
		else if(dao.load()) { dao.setDataFromBase(bean); ret = dao.update(); bean = dao.generateBase(); }
		return bean.toOneLineJSON(ret, null);
	}

}


