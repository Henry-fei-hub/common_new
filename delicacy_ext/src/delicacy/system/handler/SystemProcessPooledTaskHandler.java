package delicacy.system.handler;

import org.apache.log4j.Logger;
import delicacy.system.bean.BaseSystemProcessPooledTask;
import java.util.List;
import delicacy.system.dao.SystemProcessPooledTask;
import delicacy.connection.ThreadConnection;
import delicacy.common.GenericDao;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import java.io.StringReader;
import java.util.Map;
import delicacy.common.PaginationParameter;
import delicacy.common.BaseCollection;

public class SystemProcessPooledTaskHandler implements GenericDao {

	private static final Logger __logger = Logger.getLogger(SystemProcessPooledTaskHandler.class);

	public static BaseSystemProcessPooledTask getSystemProcessPooledTaskById( 
		java.lang.Integer process_pooled_task_id
	) throws Exception
	{
		SystemProcessPooledTask dao = new SystemProcessPooledTask();
		dao.setProcessPooledTaskId(process_pooled_task_id);
		if(dao.load()){
			return dao.generateBase();
		}
		return null;
	}

	public static boolean isSystemProcessPooledTaskExists( delicacy.system.bean.BaseSystemProcessPooledTask bean, String additional ) throws Exception {

		SystemProcessPooledTask dao = new SystemProcessPooledTask();
		setConditions(bean, dao);
		boolean res = dao.isExist(additional);
		return res;
	}

	public static int countSystemProcessPooledTask( delicacy.system.bean.BaseSystemProcessPooledTask bean, String additional ) throws Exception {

		SystemProcessPooledTask dao = new SystemProcessPooledTask();
		setConditions(bean, dao);
		int res = dao.countRows(additional);
		return res;
	}

	public static BaseCollection<BaseSystemProcessPooledTask> querySystemProcessPooledTask( delicacy.system.bean.BaseSystemProcessPooledTask bean, int pageNo, int pageLines, String additionalCondition ) throws Exception {
		SystemProcessPooledTask dao = new SystemProcessPooledTask();
		setConditions(bean, dao);
		dao.setCurrentPage(pageNo);
		dao.setPageLines(pageLines);
		java.util.List<BaseSystemProcessPooledTask> result = dao.conditionalLoad(additionalCondition);
		BaseCollection<BaseSystemProcessPooledTask> col = new BaseCollection<>();
		col.setCollections(result);
		col.setTotalPages(dao.getTotalPages());
		col.setCurrentPage(dao.getCurrentPage());
		col.setPageLines(dao.getPageLines());
		col.setTotalLines(dao.getTotalLines());
		col.setRecordNumber(result.size());
		return col;
	}

	public static BaseSystemProcessPooledTask addToSystemProcessPooledTask ( BaseSystemProcessPooledTask systemprocesspooledtask )  throws Exception {
		return addToSystemProcessPooledTask ( systemprocesspooledtask , false);
	}

	public static BaseSystemProcessPooledTask addToSystemProcessPooledTask ( BaseSystemProcessPooledTask systemprocesspooledtask, boolean singleTransaction ) throws Exception {
		if(singleTransaction) ThreadConnection.beginTransaction();
		SystemProcessPooledTask dao = new SystemProcessPooledTask();
		dao.setDataFromBase(systemprocesspooledtask);
		int result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static BaseSystemProcessPooledTask addUpdateSystemProcessPooledTask ( BaseSystemProcessPooledTask systemprocesspooledtask ) throws Exception {
		return addUpdateSystemProcessPooledTask ( systemprocesspooledtask , false);
	}

	public static BaseSystemProcessPooledTask addUpdateSystemProcessPooledTask ( BaseSystemProcessPooledTask systemprocesspooledtask, boolean singleTransaction  ) throws Exception {
		if(systemprocesspooledtask.getProcessPooledTaskId() == null) return addToSystemProcessPooledTask(systemprocesspooledtask);
		if(singleTransaction) ThreadConnection.beginTransaction();
		StringBuilder sb = new StringBuilder();
		SystemProcessPooledTask dao = new SystemProcessPooledTask();
		dao.setDataFromBase(systemprocesspooledtask);
		int result = 0;
		if(dao.load()) {
			dao.setDataFromBase(systemprocesspooledtask); 
			if(dao.isThisObjectModified()) { 
				result = dao.update();
			} else result = 1;
		} else result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static int deleteSystemProcessPooledTask ( BaseSystemProcessPooledTask bean ) throws Exception {
		SystemProcessPooledTask dao = new SystemProcessPooledTask();
		if(setDeleteConditions(bean, dao) == 0) return 0;
		int result = dao.conditionalDelete();
		return result ;
	}

	public static BaseSystemProcessPooledTask updateSystemProcessPooledTask ( BaseSystemProcessPooledTask systemprocesspooledtask ) throws Exception {
		SystemProcessPooledTask dao = new SystemProcessPooledTask();
		dao.setProcessPooledTaskId( systemprocesspooledtask.getProcessPooledTaskId() );
		int result = 0;
		if( dao.load() ) {
			dao.setDataFromBase(systemprocesspooledtask);
			result = dao.update();
		}
		return result == 1 ? systemprocesspooledtask : null ;
	}

	public static BaseSystemProcessPooledTask updateSystemProcessPooledTaskDirect( BaseSystemProcessPooledTask systemprocesspooledtask ) throws Exception {
		SystemProcessPooledTask dao = new SystemProcessPooledTask();
		int result = 0;
		dao.setDataFromBase(systemprocesspooledtask);
		result = dao.update();
		return result == 1 ? systemprocesspooledtask : null ;
	}

	public static int setDeleteConditions(BaseSystemProcessPooledTask bean, SystemProcessPooledTask dao){
		int count = 0;
		boolean foundKey = false;
		if(bean.getProcessPooledTaskId() != null) {
			dao.setConditionProcessPooledTaskId("=", bean.getProcessPooledTaskId());
			count++;
			foundKey = true;
		}
		if(!foundKey) {
			if(bean.getProcessType() != null) {
				dao.setConditionProcessType("=", bean.getProcessType());
				count++;
			}
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
			if(bean.getActivityType() != null) {
				dao.setConditionActivityType("=", bean.getActivityType());
				count++;
			}
			if(bean.getNodeType() != null) {
				dao.setConditionNodeType("=", bean.getNodeType());
				count++;
			}
			if(bean.getActivityId() != null) {
				dao.setConditionActivityId("=", bean.getActivityId());
				count++;
			}
			if(bean.getInstanceActivityId() != null) {
				dao.setConditionInstanceActivityId("=", bean.getInstanceActivityId());
				count++;
			}
			if(bean.getEmployeeId() != null) {
				dao.setConditionEmployeeId("=", bean.getEmployeeId());
				count++;
			}
			if(bean.getProcessComment() != null) {
				dao.setConditionProcessComment("=", bean.getProcessComment());
				count++;
			}
			if(bean.getStatus() != null) {
				dao.setConditionStatus("=", bean.getStatus());
				count++;
			}
			if(bean.getDeleteFlag() != null) {
				dao.setConditionDeleteFlag("=", bean.getDeleteFlag());
				count++;
			}
			if(bean.getMessageStatus() != null) {
				dao.setConditionMessageStatus("=", bean.getMessageStatus());
				count++;
			}
		}
		return count;
	}

	public static int setConditions(BaseSystemProcessPooledTask bean, SystemProcessPooledTask dao){
		int count = 0;
		if(bean.getProcessPooledTaskId() != null) {
			dao.setConditionProcessPooledTaskId("=", bean.getProcessPooledTaskId());
			count++;
		}
		if(bean.getProcessType() != null) {
			dao.setConditionProcessType("=", bean.getProcessType());
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
			dao.setConditionProcessActivityId("=", bean.getProcessActivityId());
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
		if(bean.getActivityType() != null) {
			dao.setConditionActivityType("=", bean.getActivityType());
			count++;
		}
		if(bean.getNodeType() != null) {
			dao.setConditionNodeType("=", bean.getNodeType());
			count++;
		}
		if(bean.getActivityId() != null) {
			dao.setConditionActivityId("=", bean.getActivityId());
			count++;
		}
		if(bean.getInstanceActivityId() != null) {
			dao.setConditionInstanceActivityId("=", bean.getInstanceActivityId());
			count++;
		}
		if(bean.getInstanceActivityCreateTime() != null) {
			dao.setConditionInstanceActivityCreateTime(">=", bean.getInstanceActivityCreateTime());
			count++;
		}
		if(bean.getInstanceActivityStartTime() != null) {
			dao.setConditionInstanceActivityStartTime(">=", bean.getInstanceActivityStartTime());
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
		if(bean.getProcessComment() != null) {
			if(bean.getProcessComment().indexOf("%") >= 0)
				dao.setConditionProcessComment("like", bean.getProcessComment());
			else
				dao.setConditionProcessComment("=", bean.getProcessComment());
			count++;
		}
		if(bean.getStatus() != null) {
			dao.setConditionStatus("=", bean.getStatus());
			count++;
		}
		if(bean.getDeleteFlag() != null) {
			dao.setConditionDeleteFlag("=", bean.getDeleteFlag());
			count++;
		}
		if(bean.getMessageStatus() != null) {
			dao.setConditionMessageStatus("=", bean.getMessageStatus());
			count++;
		}
		return count;
	}

	@Override
	public String findUsingKey(String json) throws Exception{
		BaseSystemProcessPooledTask bean = new BaseSystemProcessPooledTask();
		bean.setDataFromJSON(json);
		SystemProcessPooledTask dao = new SystemProcessPooledTask();
		dao.setPrimaryKeyFromBase(bean);
		if(dao.load()) { dao.setDataToBase(bean); return bean.toOneLineJSON(1,null); }
		return bean.toOneLineJSON(0,"Record not found.");
	}

	@Override
	public String find(String json, int currentPage, int pageSize, String addtion) throws Exception{
		BaseCollection<BaseSystemProcessPooledTask> rlist = new BaseCollection<>();
		BaseSystemProcessPooledTask bean = new BaseSystemProcessPooledTask();
		JSON parser = new JSON(new StringReader(json));
		Map<String, Object> params = (Map<String, Object>) parser.parse(new BasicHandler());
		bean.setDataFromMap(params);
		PaginationParameter pp = PaginationParameter.generateFromMap(params);
		if(pp != null){
			if(pp.getCurrentPage() > 0) currentPage = pp.getCurrentPage();
			if(pp.getPageSize() > 0) pageSize = pp.getPageSize();
			if(pp.getCondition() != null) addtion = pp.getCondition();
		}
		SystemProcessPooledTask dao = new SystemProcessPooledTask();
		setConditions(bean, dao);
		dao.setCurrentPage(currentPage);
		dao.setPageLines(pageSize);
		List<BaseSystemProcessPooledTask> result = dao.conditionalLoad(addtion);
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
		BaseSystemProcessPooledTask bean = new BaseSystemProcessPooledTask();
		bean.setDataFromJSON(json);
		SystemProcessPooledTask dao = new SystemProcessPooledTask();
		dao.setDataFromBase(bean);
		int num = dao.save();
		dao.setDataToBase(bean);
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String update(String json) throws Exception{
		BaseSystemProcessPooledTask bean = new BaseSystemProcessPooledTask();
		bean.setDataFromJSON(json);
		SystemProcessPooledTask dao = new SystemProcessPooledTask();
		dao.setPrimaryKeyFromBase(bean);
		int num=0;
		if(dao.load()){dao.setDataFromBase(bean); num = dao.update(); }
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String delete(String json) throws Exception{
		BaseSystemProcessPooledTask bean = new BaseSystemProcessPooledTask();
		bean.setDataFromJSON(json);
		SystemProcessPooledTask dao = new SystemProcessPooledTask();
		setDeleteConditions(bean, dao);
		int num = dao.conditionalDelete();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String saveOrUpdate(String json) throws Exception{
		BaseSystemProcessPooledTask bean = new BaseSystemProcessPooledTask();
		bean.setDataFromJSON(json);
		SystemProcessPooledTask dao = new SystemProcessPooledTask();
		dao.setPrimaryKeyFromBase(bean);
		int ret = 0;
		if(dao.isPrimaryKeyNull()) { dao.setDataFromBase(bean); ret = dao.save(); bean = dao.generateBase(); }
		else if(dao.load()) { dao.setDataFromBase(bean); ret = dao.update(); bean = dao.generateBase(); }
		return bean.toOneLineJSON(ret, null);
	}

}


