package delicacy.system.handler;

import org.apache.log4j.Logger;
import delicacy.system.bean.BaseSystemProcessHumanTask;
import java.util.List;
import delicacy.system.dao.SystemProcessHumanTask;
import delicacy.connection.ThreadConnection;
import delicacy.common.GenericDao;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import java.io.StringReader;
import java.util.Map;
import delicacy.common.PaginationParameter;
import delicacy.common.BaseCollection;

public class SystemProcessHumanTaskHandler implements GenericDao {

	private static final Logger __logger = Logger.getLogger(SystemProcessHumanTaskHandler.class);

	public static BaseSystemProcessHumanTask getSystemProcessHumanTaskById( 
		java.lang.Integer process_human_task_id
	) throws Exception
	{
		SystemProcessHumanTask dao = new SystemProcessHumanTask();
		dao.setProcessHumanTaskId(process_human_task_id);
		if(dao.load()){
			return dao.generateBase();
		}
		return null;
	}

	public static boolean isSystemProcessHumanTaskExists( delicacy.system.bean.BaseSystemProcessHumanTask bean, String additional ) throws Exception {

		SystemProcessHumanTask dao = new SystemProcessHumanTask();
		setConditions(bean, dao);
		boolean res = dao.isExist(additional);
		return res;
	}

	public static int countSystemProcessHumanTask( delicacy.system.bean.BaseSystemProcessHumanTask bean, String additional ) throws Exception {

		SystemProcessHumanTask dao = new SystemProcessHumanTask();
		setConditions(bean, dao);
		int res = dao.countRows(additional);
		return res;
	}

	public static BaseCollection<BaseSystemProcessHumanTask> querySystemProcessHumanTask( delicacy.system.bean.BaseSystemProcessHumanTask bean, int pageNo, int pageLines, String additionalCondition ) throws Exception {
		SystemProcessHumanTask dao = new SystemProcessHumanTask();
		setConditions(bean, dao);
		dao.setCurrentPage(pageNo);
		dao.setPageLines(pageLines);
		java.util.List<BaseSystemProcessHumanTask> result = dao.conditionalLoad(additionalCondition);
		BaseCollection<BaseSystemProcessHumanTask> col = new BaseCollection<>();
		col.setCollections(result);
		col.setTotalPages(dao.getTotalPages());
		col.setCurrentPage(dao.getCurrentPage());
		col.setPageLines(dao.getPageLines());
		col.setTotalLines(dao.getTotalLines());
		col.setRecordNumber(result.size());
		return col;
	}

	public static BaseSystemProcessHumanTask addToSystemProcessHumanTask ( BaseSystemProcessHumanTask systemprocesshumantask )  throws Exception {
		return addToSystemProcessHumanTask ( systemprocesshumantask , false);
	}

	public static BaseSystemProcessHumanTask addToSystemProcessHumanTask ( BaseSystemProcessHumanTask systemprocesshumantask, boolean singleTransaction ) throws Exception {
		if(singleTransaction) ThreadConnection.beginTransaction();
		SystemProcessHumanTask dao = new SystemProcessHumanTask();
		dao.setDataFromBase(systemprocesshumantask);
		int result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static BaseSystemProcessHumanTask addUpdateSystemProcessHumanTask ( BaseSystemProcessHumanTask systemprocesshumantask ) throws Exception {
		return addUpdateSystemProcessHumanTask ( systemprocesshumantask , false);
	}

	public static BaseSystemProcessHumanTask addUpdateSystemProcessHumanTask ( BaseSystemProcessHumanTask systemprocesshumantask, boolean singleTransaction  ) throws Exception {
		if(systemprocesshumantask.getProcessHumanTaskId() == null) return addToSystemProcessHumanTask(systemprocesshumantask);
		if(singleTransaction) ThreadConnection.beginTransaction();
		StringBuilder sb = new StringBuilder();
		SystemProcessHumanTask dao = new SystemProcessHumanTask();
		dao.setDataFromBase(systemprocesshumantask);
		int result = 0;
		if(dao.load()) {
			dao.setDataFromBase(systemprocesshumantask); 
			if(dao.isThisObjectModified()) { 
				result = dao.update();
			} else result = 1;
		} else result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static int deleteSystemProcessHumanTask ( BaseSystemProcessHumanTask bean ) throws Exception {
		SystemProcessHumanTask dao = new SystemProcessHumanTask();
		if(setDeleteConditions(bean, dao) == 0) return 0;
		int result = dao.conditionalDelete();
		return result ;
	}

	public static BaseSystemProcessHumanTask updateSystemProcessHumanTask ( BaseSystemProcessHumanTask systemprocesshumantask ) throws Exception {
		SystemProcessHumanTask dao = new SystemProcessHumanTask();
		dao.setProcessHumanTaskId( systemprocesshumantask.getProcessHumanTaskId() );
		int result = 0;
		if( dao.load() ) {
			dao.setDataFromBase(systemprocesshumantask);
			result = dao.update();
		}
		return result == 1 ? systemprocesshumantask : null ;
	}

	public static BaseSystemProcessHumanTask updateSystemProcessHumanTaskDirect( BaseSystemProcessHumanTask systemprocesshumantask ) throws Exception {
		SystemProcessHumanTask dao = new SystemProcessHumanTask();
		int result = 0;
		dao.setDataFromBase(systemprocesshumantask);
		result = dao.update();
		return result == 1 ? systemprocesshumantask : null ;
	}

	public static int setDeleteConditions(BaseSystemProcessHumanTask bean, SystemProcessHumanTask dao){
		int count = 0;
		boolean foundKey = false;
		if(bean.getProcessHumanTaskId() != null) {
			dao.setConditionProcessHumanTaskId("=", bean.getProcessHumanTaskId());
			count++;
			foundKey = true;
		}
		if(!foundKey) {
			if(bean.getBusinessId() != null) {
				dao.setConditionBusinessId("=", bean.getBusinessId());
				count++;
			}
			if(bean.getBusinessName() != null) {
				dao.setConditionBusinessName("=", bean.getBusinessName());
				count++;
			}
			if(bean.getProcessActivityId() != null) {
				dao.setConditionProcessActivityId("=", bean.getProcessActivityId());
				count++;
			}
			if(bean.getProcessInstanceActivityId() != null) {
				dao.setConditionProcessInstanceActivityId("=", bean.getProcessInstanceActivityId());
				count++;
			}
			if(bean.getProcessId() != null) {
				dao.setConditionProcessId("=", bean.getProcessId());
				count++;
			}
			if(bean.getProcessInstanceId() != null) {
				dao.setConditionProcessInstanceId("=", bean.getProcessInstanceId());
				count++;
			}
			if(bean.getBackViewName() != null) {
				dao.setConditionBackViewName("=", bean.getBackViewName());
				count++;
			}
			if(bean.getEmployeeId() != null) {
				dao.setConditionEmployeeId("=", bean.getEmployeeId());
				count++;
			}
			if(bean.getCommitIdeas() != null) {
				dao.setConditionCommitIdeas("=", bean.getCommitIdeas());
				count++;
			}
			if(bean.getStatus() != null) {
				dao.setConditionStatus("=", bean.getStatus());
				count++;
			}
		}
		return count;
	}

	public static int setConditions(BaseSystemProcessHumanTask bean, SystemProcessHumanTask dao){
		int count = 0;
		if(bean.getProcessHumanTaskId() != null) {
			dao.setConditionProcessHumanTaskId("=", bean.getProcessHumanTaskId());
			count++;
		}
		if(bean.getBusinessId() != null) {
			dao.setConditionBusinessId("=", bean.getBusinessId());
			count++;
		}
		if(bean.getBusinessName() != null) {
			if(bean.getBusinessName().indexOf("%") >= 0)
				dao.setConditionBusinessName("like", bean.getBusinessName());
			else
				dao.setConditionBusinessName("=", bean.getBusinessName());
			count++;
		}
		if(bean.getProcessActivityId() != null) {
			if(bean.getProcessActivityId().indexOf("%") >= 0)
				dao.setConditionProcessActivityId("like", bean.getProcessActivityId());
			else
				dao.setConditionProcessActivityId("=", bean.getProcessActivityId());
			count++;
		}
		if(bean.getProcessInstanceActivityId() != null) {
			dao.setConditionProcessInstanceActivityId("=", bean.getProcessInstanceActivityId());
			count++;
		}
		if(bean.getProcessId() != null) {
			dao.setConditionProcessId("=", bean.getProcessId());
			count++;
		}
		if(bean.getProcessInstanceId() != null) {
			dao.setConditionProcessInstanceId("=", bean.getProcessInstanceId());
			count++;
		}
		if(bean.getBackViewName() != null) {
			if(bean.getBackViewName().indexOf("%") >= 0)
				dao.setConditionBackViewName("like", bean.getBackViewName());
			else
				dao.setConditionBackViewName("=", bean.getBackViewName());
			count++;
		}
		if(bean.getEmployeeId() != null) {
			dao.setConditionEmployeeId("=", bean.getEmployeeId());
			count++;
		}
		if(bean.getOperateTime() != null) {
			dao.setConditionOperateTime(">=", bean.getOperateTime());
			count++;
		}
		if(bean.getCommitIdeas() != null) {
			if(bean.getCommitIdeas().indexOf("%") >= 0)
				dao.setConditionCommitIdeas("like", bean.getCommitIdeas());
			else
				dao.setConditionCommitIdeas("=", bean.getCommitIdeas());
			count++;
		}
		if(bean.getStatus() != null) {
			dao.setConditionStatus("=", bean.getStatus());
			count++;
		}
		return count;
	}

	@Override
	public String findUsingKey(String json) throws Exception{
		BaseSystemProcessHumanTask bean = new BaseSystemProcessHumanTask();
		bean.setDataFromJSON(json);
		SystemProcessHumanTask dao = new SystemProcessHumanTask();
		dao.setPrimaryKeyFromBase(bean);
		if(dao.load()) { dao.setDataToBase(bean); return bean.toOneLineJSON(1,null); }
		return bean.toOneLineJSON(0,"Record not found.");
	}

	@Override
	public String find(String json, int currentPage, int pageSize, String addtion) throws Exception{
		BaseCollection<BaseSystemProcessHumanTask> rlist = new BaseCollection<>();
		BaseSystemProcessHumanTask bean = new BaseSystemProcessHumanTask();
		JSON parser = new JSON(new StringReader(json));
		Map<String, Object> params = (Map<String, Object>) parser.parse(new BasicHandler());
		bean.setDataFromMap(params);
		PaginationParameter pp = PaginationParameter.generateFromMap(params);
		if(pp != null){
			if(pp.getCurrentPage() > 0) currentPage = pp.getCurrentPage();
			if(pp.getPageSize() > 0) pageSize = pp.getPageSize();
			if(pp.getCondition() != null) addtion = pp.getCondition();
		}
		SystemProcessHumanTask dao = new SystemProcessHumanTask();
		setConditions(bean, dao);
		dao.setCurrentPage(currentPage);
		dao.setPageLines(pageSize);
		List<BaseSystemProcessHumanTask> result = dao.conditionalLoad(addtion);
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
		BaseSystemProcessHumanTask bean = new BaseSystemProcessHumanTask();
		bean.setDataFromJSON(json);
		SystemProcessHumanTask dao = new SystemProcessHumanTask();
		dao.setDataFromBase(bean);
		int num = dao.save();
		dao.setDataToBase(bean);
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String update(String json) throws Exception{
		BaseSystemProcessHumanTask bean = new BaseSystemProcessHumanTask();
		bean.setDataFromJSON(json);
		SystemProcessHumanTask dao = new SystemProcessHumanTask();
		dao.setPrimaryKeyFromBase(bean);
		int num=0;
		if(dao.load()){dao.setDataFromBase(bean); num = dao.update(); }
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String delete(String json) throws Exception{
		BaseSystemProcessHumanTask bean = new BaseSystemProcessHumanTask();
		bean.setDataFromJSON(json);
		SystemProcessHumanTask dao = new SystemProcessHumanTask();
		setDeleteConditions(bean, dao);
		int num = dao.conditionalDelete();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String saveOrUpdate(String json) throws Exception{
		BaseSystemProcessHumanTask bean = new BaseSystemProcessHumanTask();
		bean.setDataFromJSON(json);
		SystemProcessHumanTask dao = new SystemProcessHumanTask();
		dao.setPrimaryKeyFromBase(bean);
		int ret = 0;
		if(dao.isPrimaryKeyNull()) { dao.setDataFromBase(bean); ret = dao.save(); bean = dao.generateBase(); }
		else if(dao.load()) { dao.setDataFromBase(bean); ret = dao.update(); bean = dao.generateBase(); }
		return bean.toOneLineJSON(ret, null);
	}

}


