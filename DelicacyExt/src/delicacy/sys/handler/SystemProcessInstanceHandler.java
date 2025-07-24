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
import delicacy.sys.bean.BaseSystemProcessInstance;
import delicacy.sys.dao.SystemProcessInstance;

public class SystemProcessInstanceHandler implements GenericDao {

	private static final Logger __logger = Logger.getLogger(SystemProcessInstanceHandler.class);

	public static BaseSystemProcessInstance getSystemProcessInstanceById( 
		java.lang.Integer process_instance_id
	) throws Exception
	{
		SystemProcessInstance dao = new SystemProcessInstance();
		dao.setProcessInstanceId(process_instance_id);
		if(dao.load()){
			return dao.generateBase();
		}
		return null;
	}

	public static boolean isSystemProcessInstanceExists(BaseSystemProcessInstance bean, String additional ) throws Exception {

		SystemProcessInstance dao = new SystemProcessInstance();
		setConditions(bean, dao);
		boolean res = dao.isExist(additional);
		return res;
	}

	public static int countSystemProcessInstance(BaseSystemProcessInstance bean, String additional ) throws Exception {

		SystemProcessInstance dao = new SystemProcessInstance();
		setConditions(bean, dao);
		int res = dao.countRows(additional);
		return res;
	}

	public static BaseCollection<BaseSystemProcessInstance> querySystemProcessInstance(BaseSystemProcessInstance bean, int pageNo, int pageLines, String additionalCondition ) throws Exception {
		SystemProcessInstance dao = new SystemProcessInstance();
		setConditions(bean, dao);
		dao.setCurrentPage(pageNo);
		dao.setPageLines(pageLines);
		java.util.List<BaseSystemProcessInstance> result = dao.conditionalLoad(additionalCondition);
		BaseCollection<BaseSystemProcessInstance> col = new BaseCollection<>();
		col.setCollections(result);
		col.setTotalPages(dao.getTotalPages());
		col.setCurrentPage(dao.getCurrentPage());
		col.setPageLines(dao.getPageLines());
		col.setTotalLines(dao.getTotalLines());
		col.setRecordNumber(result.size());
		return col;
	}

	public static BaseSystemProcessInstance addToSystemProcessInstance ( BaseSystemProcessInstance systemprocessinstance )  throws Exception {
		return addToSystemProcessInstance ( systemprocessinstance , false);
	}

	public static BaseSystemProcessInstance addToSystemProcessInstance ( BaseSystemProcessInstance systemprocessinstance, boolean singleTransaction ) throws Exception {
		if(singleTransaction) ThreadConnection.beginTransaction();
		SystemProcessInstance dao = new SystemProcessInstance();
		dao.setDataFromBase(systemprocessinstance);
		int result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static BaseSystemProcessInstance addUpdateSystemProcessInstance ( BaseSystemProcessInstance systemprocessinstance ) throws Exception {
		return addUpdateSystemProcessInstance ( systemprocessinstance , false);
	}

	public static BaseSystemProcessInstance addUpdateSystemProcessInstance ( BaseSystemProcessInstance systemprocessinstance, boolean singleTransaction  ) throws Exception {
		if(systemprocessinstance.getProcessInstanceId() == null) return addToSystemProcessInstance(systemprocessinstance);
		if(singleTransaction) ThreadConnection.beginTransaction();
		StringBuilder sb = new StringBuilder();
		SystemProcessInstance dao = new SystemProcessInstance();
		dao.setDataFromBase(systemprocessinstance);
		int result = 0;
		if(dao.load()) {
			dao.setDataFromBase(systemprocessinstance); 
			if(dao.isThisObjectModified()) { 
				result = dao.update();
			} else result = 1;
		} else result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static int deleteSystemProcessInstance ( BaseSystemProcessInstance bean ) throws Exception {
		SystemProcessInstance dao = new SystemProcessInstance();
		if(setDeleteConditions(bean, dao) == 0) return 0;
		int result = dao.conditionalDelete();
		return result ;
	}

	public static BaseSystemProcessInstance updateSystemProcessInstance ( BaseSystemProcessInstance systemprocessinstance ) throws Exception {
		SystemProcessInstance dao = new SystemProcessInstance();
		dao.setProcessInstanceId( systemprocessinstance.getProcessInstanceId() );
		int result = 0;
		if( dao.load() ) {
			dao.setDataFromBase(systemprocessinstance);
			result = dao.update();
		}
		return result == 1 ? systemprocessinstance : null ;
	}

	public static BaseSystemProcessInstance updateSystemProcessInstanceDirect( BaseSystemProcessInstance systemprocessinstance ) throws Exception {
		SystemProcessInstance dao = new SystemProcessInstance();
		int result = 0;
		dao.setDataFromBase(systemprocessinstance);
		result = dao.update();
		return result == 1 ? systemprocessinstance : null ;
	}

	public static int setDeleteConditions(BaseSystemProcessInstance bean, SystemProcessInstance dao){
		int count = 0;
		boolean foundKey = false;
		if(bean.getProcessInstanceId() != null) {
			dao.setConditionProcessInstanceId("=", bean.getProcessInstanceId());
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
			if(bean.getProcessId() != null) {
				dao.setConditionProcessId("=", bean.getProcessId());
				count++;
			}
			if(bean.getProcessInstanceActivityId() != null) {
				dao.setConditionProcessInstanceActivityId("=", bean.getProcessInstanceActivityId());
				count++;
			}
			if(bean.getProcessStatus() != null) {
				dao.setConditionProcessStatus("=", bean.getProcessStatus());
				count++;
			}
			if(bean.getEmployeeId() != null) {
				dao.setConditionEmployeeId("=", bean.getEmployeeId());
				count++;
			}
			if(bean.getDeleteFlag() != null) {
				dao.setConditionDeleteFlag("=", bean.getDeleteFlag());
				count++;
			}
			if(bean.getOtherOperation() != null) {
				dao.setConditionOtherOperation("=", bean.getOtherOperation());
				count++;
			}
			if(bean.getOrganizationId() != null) {
				dao.setConditionOrganizationId("=", bean.getOrganizationId());
				count++;
			}
		}
		return count;
	}

	public static int setConditions(BaseSystemProcessInstance bean, SystemProcessInstance dao){
		int count = 0;
		if(bean.getProcessInstanceId() != null) {
			dao.setConditionProcessInstanceId("=", bean.getProcessInstanceId());
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
		if(bean.getProcessId() != null) {
			dao.setConditionProcessId("=", bean.getProcessId());
			count++;
		}
		if(bean.getProcessInstanceActivityId() != null) {
			dao.setConditionProcessInstanceActivityId("=", bean.getProcessInstanceActivityId());
			count++;
		}
		if(bean.getProcessStatus() != null) {
			dao.setConditionProcessStatus("=", bean.getProcessStatus());
			count++;
		}
		if(bean.getEmployeeId() != null) {
			dao.setConditionEmployeeId("=", bean.getEmployeeId());
			count++;
		}
		if(bean.getCreateTime() != null) {
			dao.setConditionCreateTime(">=", bean.getCreateTime());
			count++;
		}
		if(bean.getCompleteTime() != null) {
			dao.setConditionCompleteTime(">=", bean.getCompleteTime());
			count++;
		}
		if(bean.getDeleteFlag() != null) {
			dao.setConditionDeleteFlag("=", bean.getDeleteFlag());
			count++;
		}
		if(bean.getOtherOperation() != null) {
			dao.setConditionOtherOperation("=", bean.getOtherOperation());
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
		BaseSystemProcessInstance bean = new BaseSystemProcessInstance();
		bean.setDataFromJSON(json);
		SystemProcessInstance dao = new SystemProcessInstance();
		dao.setPrimaryKeyFromBase(bean);
		if(dao.load()) { dao.setDataToBase(bean); return bean.toOneLineJSON(1,null); }
		return bean.toOneLineJSON(0,"Record not found.");
	}

	@Override
	public String find(String json, int currentPage, int pageSize, String addtion) throws Exception{
		BaseCollection<BaseSystemProcessInstance> rlist = new BaseCollection<>();
		BaseSystemProcessInstance bean = new BaseSystemProcessInstance();
		JSON parser = new JSON(new StringReader(json));
		Map<String, Object> params = (Map<String, Object>) parser.parse(new BasicHandler());
		bean.setDataFromMap(params);
		PaginationParameter pp = PaginationParameter.generateFromMap(params);
		if(pp != null){
			if(pp.getCurrentPage() > 0) currentPage = pp.getCurrentPage();
			if(pp.getPageSize() > 0) pageSize = pp.getPageSize();
			if(pp.getCondition() != null) addtion = pp.getCondition();
		}
		SystemProcessInstance dao = new SystemProcessInstance();
		setConditions(bean, dao);
		dao.setCurrentPage(currentPage);
		dao.setPageLines(pageSize);
		List<BaseSystemProcessInstance> result = dao.conditionalLoad(addtion);
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
		BaseSystemProcessInstance bean = new BaseSystemProcessInstance();
		bean.setDataFromJSON(json);
		SystemProcessInstance dao = new SystemProcessInstance();
		dao.setDataFromBase(bean);
		int num = dao.save();
		dao.setDataToBase(bean);
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String update(String json) throws Exception{
		BaseSystemProcessInstance bean = new BaseSystemProcessInstance();
		bean.setDataFromJSON(json);
		SystemProcessInstance dao = new SystemProcessInstance();
		dao.setPrimaryKeyFromBase(bean);
		int num=0;
		if(dao.load()){dao.setDataFromBase(bean); num = dao.update(); }
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String delete(String json) throws Exception{
		BaseSystemProcessInstance bean = new BaseSystemProcessInstance();
		bean.setDataFromJSON(json);
		SystemProcessInstance dao = new SystemProcessInstance();
		setDeleteConditions(bean, dao);
		int num = dao.conditionalDelete();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String saveOrUpdate(String json) throws Exception{
		BaseSystemProcessInstance bean = new BaseSystemProcessInstance();
		bean.setDataFromJSON(json);
		SystemProcessInstance dao = new SystemProcessInstance();
		dao.setPrimaryKeyFromBase(bean);
		int ret = 0;
		if(dao.isPrimaryKeyNull()) { dao.setDataFromBase(bean); ret = dao.save(); bean = dao.generateBase(); }
		else if(dao.load()) { dao.setDataFromBase(bean); ret = dao.update(); bean = dao.generateBase(); }
		return bean.toOneLineJSON(ret, null);
	}

}


