package delicacy.sys.handler;

import java.io.StringReader;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import delicacy.common.BaseCollection;
import delicacy.common.GenericDao;
import delicacy.common.PaginationParameter;
import delicacy.connection.ThreadConnection;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import delicacy.sys.bean.BaseSystemProcessInstanceActivity;
import delicacy.sys.dao.SystemProcessInstanceActivity;

public class SystemProcessInstanceActivityHandler implements GenericDao {

	private static final Logger __logger = Logger.getLogger(SystemProcessInstanceActivityHandler.class);

	public static BaseSystemProcessInstanceActivity getSystemProcessInstanceActivityById( 
		java.lang.Integer process_instance_activity_id
	) throws Exception
	{
		SystemProcessInstanceActivity dao = new SystemProcessInstanceActivity();
		dao.setProcessInstanceActivityId(process_instance_activity_id);
		if(dao.load()){
			return dao.generateBase();
		}
		return null;
	}

	public static boolean isSystemProcessInstanceActivityExists(BaseSystemProcessInstanceActivity bean, String additional ) throws Exception {

		SystemProcessInstanceActivity dao = new SystemProcessInstanceActivity();
		setConditions(bean, dao);
		boolean res = dao.isExist(additional);
		return res;
	}

	public static int countSystemProcessInstanceActivity(BaseSystemProcessInstanceActivity bean, String additional ) throws Exception {

		SystemProcessInstanceActivity dao = new SystemProcessInstanceActivity();
		setConditions(bean, dao);
		int res = dao.countRows(additional);
		return res;
	}

	public static BaseCollection<BaseSystemProcessInstanceActivity> querySystemProcessInstanceActivity(BaseSystemProcessInstanceActivity bean, int pageNo, int pageLines, String additionalCondition ) throws Exception {
		SystemProcessInstanceActivity dao = new SystemProcessInstanceActivity();
		setConditions(bean, dao);
		dao.setCurrentPage(pageNo);
		dao.setPageLines(pageLines);
		java.util.List<BaseSystemProcessInstanceActivity> result = dao.conditionalLoad(additionalCondition);
		BaseCollection<BaseSystemProcessInstanceActivity> col = new BaseCollection<>();
		col.setCollections(result);
		col.setTotalPages(dao.getTotalPages());
		col.setCurrentPage(dao.getCurrentPage());
		col.setPageLines(dao.getPageLines());
		col.setTotalLines(dao.getTotalLines());
		col.setRecordNumber(result.size());
		return col;
	}

	public static BaseSystemProcessInstanceActivity addToSystemProcessInstanceActivity ( BaseSystemProcessInstanceActivity systemprocessinstanceactivity )  throws Exception {
		return addToSystemProcessInstanceActivity ( systemprocessinstanceactivity , false);
	}

	public static BaseSystemProcessInstanceActivity addToSystemProcessInstanceActivity ( BaseSystemProcessInstanceActivity systemprocessinstanceactivity, boolean singleTransaction ) throws Exception {
		if(singleTransaction) ThreadConnection.beginTransaction();
		SystemProcessInstanceActivity dao = new SystemProcessInstanceActivity();
		dao.setDataFromBase(systemprocessinstanceactivity);
		int result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static BaseSystemProcessInstanceActivity addUpdateSystemProcessInstanceActivity ( BaseSystemProcessInstanceActivity systemprocessinstanceactivity ) throws Exception {
		return addUpdateSystemProcessInstanceActivity ( systemprocessinstanceactivity , false);
	}

	public static BaseSystemProcessInstanceActivity addUpdateSystemProcessInstanceActivity ( BaseSystemProcessInstanceActivity systemprocessinstanceactivity, boolean singleTransaction  ) throws Exception {
		if(systemprocessinstanceactivity.getProcessInstanceActivityId() == null) return addToSystemProcessInstanceActivity(systemprocessinstanceactivity);
		if(singleTransaction) ThreadConnection.beginTransaction();
		StringBuilder sb = new StringBuilder();
		SystemProcessInstanceActivity dao = new SystemProcessInstanceActivity();
		dao.setDataFromBase(systemprocessinstanceactivity);
		int result = 0;
		if(dao.load()) {
			dao.setDataFromBase(systemprocessinstanceactivity); 
			if(dao.isThisObjectModified()) { 
				result = dao.update();
			} else result = 1;
		} else result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static int deleteSystemProcessInstanceActivity ( BaseSystemProcessInstanceActivity bean ) throws Exception {
		SystemProcessInstanceActivity dao = new SystemProcessInstanceActivity();
		if(setDeleteConditions(bean, dao) == 0) return 0;
		int result = dao.conditionalDelete();
		return result ;
	}

	public static BaseSystemProcessInstanceActivity updateSystemProcessInstanceActivity ( BaseSystemProcessInstanceActivity systemprocessinstanceactivity ) throws Exception {
		SystemProcessInstanceActivity dao = new SystemProcessInstanceActivity();
		dao.setProcessInstanceActivityId( systemprocessinstanceactivity.getProcessInstanceActivityId() );
		int result = 0;
		if( dao.load() ) {
			dao.setDataFromBase(systemprocessinstanceactivity);
			result = dao.update();
		}
		return result == 1 ? systemprocessinstanceactivity : null ;
	}

	public static BaseSystemProcessInstanceActivity updateSystemProcessInstanceActivityDirect( BaseSystemProcessInstanceActivity systemprocessinstanceactivity ) throws Exception {
		SystemProcessInstanceActivity dao = new SystemProcessInstanceActivity();
		int result = 0;
		dao.setDataFromBase(systemprocessinstanceactivity);
		result = dao.update();
		return result == 1 ? systemprocessinstanceactivity : null ;
	}

	public static int setDeleteConditions(BaseSystemProcessInstanceActivity bean, SystemProcessInstanceActivity dao){
		int count = 0;
		boolean foundKey = false;
		if(bean.getProcessInstanceActivityId() != null) {
			dao.setConditionProcessInstanceActivityId("=", bean.getProcessInstanceActivityId());
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
			if(bean.getNextActivityId() != null) {
				dao.setConditionNextActivityId("=", bean.getNextActivityId());
				count++;
			}
			if(bean.getMainActivityId() != null) {
				dao.setConditionMainActivityId("=", bean.getMainActivityId());
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
			if(bean.getTimeOutMessageStatus() != null) {
				dao.setConditionTimeOutMessageStatus("=", bean.getTimeOutMessageStatus());
				count++;
			}
			if(bean.getOrganizationId() != null) {
				dao.setConditionOrganizationId("=", bean.getOrganizationId());
				count++;
			}
		}
		return count;
	}

	public static int setConditions(BaseSystemProcessInstanceActivity bean, SystemProcessInstanceActivity dao){
		int count = 0;
		if(bean.getProcessInstanceActivityId() != null) {
			dao.setConditionProcessInstanceActivityId("=", bean.getProcessInstanceActivityId());
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
		if(bean.getNextActivityId() != null) {
			dao.setConditionNextActivityId("=", bean.getNextActivityId());
			count++;
		}
		if(bean.getMainActivityId() != null) {
			dao.setConditionMainActivityId("=", bean.getMainActivityId());
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
		if(bean.getTimeOutMessageStatus() != null) {
			dao.setConditionTimeOutMessageStatus("=", bean.getTimeOutMessageStatus());
			count++;
		}
		if(bean.getOrganizationId() != null) {
			dao.setConditionOrganizationId("=", bean.getOrganizationId());
			count++;
		}
		return count;
	}

	@Override
	public String findUsingKey(String json) throws Exception{
		BaseSystemProcessInstanceActivity bean = new BaseSystemProcessInstanceActivity();
		bean.setDataFromJSON(json);
		SystemProcessInstanceActivity dao = new SystemProcessInstanceActivity();
		dao.setPrimaryKeyFromBase(bean);
		if(dao.load()) { dao.setDataToBase(bean); return bean.toOneLineJSON(1,null); }
		return bean.toOneLineJSON(0,"Record not found.");
	}

	@Override
	public String find(String json, int currentPage, int pageSize, String addtion) throws Exception{
		BaseCollection<BaseSystemProcessInstanceActivity> rlist = new BaseCollection<>();
		BaseSystemProcessInstanceActivity bean = new BaseSystemProcessInstanceActivity();
		JSON parser = new JSON(new StringReader(json));
		Map<String, Object> params = (Map<String, Object>) parser.parse(new BasicHandler());
		bean.setDataFromMap(params);
		PaginationParameter pp = PaginationParameter.generateFromMap(params);
		if(pp != null){
			if(pp.getCurrentPage() > 0) currentPage = pp.getCurrentPage();
			if(pp.getPageSize() > 0) pageSize = pp.getPageSize();
			if(pp.getCondition() != null) addtion = pp.getCondition();
		}
		SystemProcessInstanceActivity dao = new SystemProcessInstanceActivity();
		setConditions(bean, dao);
		dao.setCurrentPage(currentPage);
		dao.setPageLines(pageSize);
		List<BaseSystemProcessInstanceActivity> result = dao.conditionalLoad(addtion);
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
		BaseSystemProcessInstanceActivity bean = new BaseSystemProcessInstanceActivity();
		bean.setDataFromJSON(json);
		SystemProcessInstanceActivity dao = new SystemProcessInstanceActivity();
		dao.setDataFromBase(bean);
		int num = dao.save();
		dao.setDataToBase(bean);
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String update(String json) throws Exception{
		BaseSystemProcessInstanceActivity bean = new BaseSystemProcessInstanceActivity();
		bean.setDataFromJSON(json);
		SystemProcessInstanceActivity dao = new SystemProcessInstanceActivity();
		dao.setPrimaryKeyFromBase(bean);
		int num=0;
		if(dao.load()){dao.setDataFromBase(bean); num = dao.update(); }
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String delete(String json) throws Exception{
		BaseSystemProcessInstanceActivity bean = new BaseSystemProcessInstanceActivity();
		bean.setDataFromJSON(json);
		SystemProcessInstanceActivity dao = new SystemProcessInstanceActivity();
		setDeleteConditions(bean, dao);
		int num = dao.conditionalDelete();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String saveOrUpdate(String json) throws Exception{
		BaseSystemProcessInstanceActivity bean = new BaseSystemProcessInstanceActivity();
		bean.setDataFromJSON(json);
		SystemProcessInstanceActivity dao = new SystemProcessInstanceActivity();
		dao.setPrimaryKeyFromBase(bean);
		int ret = 0;
		if(dao.isPrimaryKeyNull()) { dao.setDataFromBase(bean); ret = dao.save(); bean = dao.generateBase(); }
		else if(dao.load()) { dao.setDataFromBase(bean); ret = dao.update(); bean = dao.generateBase(); }
		return bean.toOneLineJSON(ret, null);
	}

}


