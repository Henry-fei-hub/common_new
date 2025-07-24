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
import delicacy.sys.bean.BaseSystemProcessAttention;
import delicacy.sys.dao.SystemProcessAttention;

public class SystemProcessAttentionHandler implements GenericDao {

	private static final Logger __logger = Logger.getLogger(SystemProcessAttentionHandler.class);

	public static BaseSystemProcessAttention getSystemProcessAttentionById( 
		java.lang.Integer system_process_attention_id
	) throws Exception
	{
		SystemProcessAttention dao = new SystemProcessAttention();
		dao.setSystemProcessAttentionId(system_process_attention_id);
		if(dao.load()){
			return dao.generateBase();
		}
		return null;
	}

	public static boolean isSystemProcessAttentionExists(BaseSystemProcessAttention bean, String additional ) throws Exception {

		SystemProcessAttention dao = new SystemProcessAttention();
		setConditions(bean, dao);
		boolean res = dao.isExist(additional);
		return res;
	}

	public static int countSystemProcessAttention(BaseSystemProcessAttention bean, String additional ) throws Exception {

		SystemProcessAttention dao = new SystemProcessAttention();
		setConditions(bean, dao);
		int res = dao.countRows(additional);
		return res;
	}

	public static BaseCollection<BaseSystemProcessAttention> querySystemProcessAttention(BaseSystemProcessAttention bean, int pageNo, int pageLines, String additionalCondition ) throws Exception {
		SystemProcessAttention dao = new SystemProcessAttention();
		setConditions(bean, dao);
		dao.setCurrentPage(pageNo);
		dao.setPageLines(pageLines);
		java.util.List<BaseSystemProcessAttention> result = dao.conditionalLoad(additionalCondition);
		BaseCollection<BaseSystemProcessAttention> col = new BaseCollection<>();
		col.setCollections(result);
		col.setTotalPages(dao.getTotalPages());
		col.setCurrentPage(dao.getCurrentPage());
		col.setPageLines(dao.getPageLines());
		col.setTotalLines(dao.getTotalLines());
		col.setRecordNumber(result.size());
		return col;
	}

	public static BaseSystemProcessAttention addToSystemProcessAttention ( BaseSystemProcessAttention systemprocessattention )  throws Exception {
		return addToSystemProcessAttention ( systemprocessattention , false);
	}

	public static BaseSystemProcessAttention addToSystemProcessAttention ( BaseSystemProcessAttention systemprocessattention, boolean singleTransaction ) throws Exception {
		if(singleTransaction) ThreadConnection.beginTransaction();
		SystemProcessAttention dao = new SystemProcessAttention();
		dao.setDataFromBase(systemprocessattention);
		int result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static BaseSystemProcessAttention addUpdateSystemProcessAttention ( BaseSystemProcessAttention systemprocessattention ) throws Exception {
		return addUpdateSystemProcessAttention ( systemprocessattention , false);
	}

	public static BaseSystemProcessAttention addUpdateSystemProcessAttention ( BaseSystemProcessAttention systemprocessattention, boolean singleTransaction  ) throws Exception {
		if(systemprocessattention.getSystemProcessAttentionId() == null) return addToSystemProcessAttention(systemprocessattention);
		if(singleTransaction) ThreadConnection.beginTransaction();
		StringBuilder sb = new StringBuilder();
		SystemProcessAttention dao = new SystemProcessAttention();
		dao.setDataFromBase(systemprocessattention);
		int result = 0;
		if(dao.load()) {
			dao.setDataFromBase(systemprocessattention); 
			if(dao.isThisObjectModified()) { 
				result = dao.update();
			} else result = 1;
		} else result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static int deleteSystemProcessAttention ( BaseSystemProcessAttention bean ) throws Exception {
		SystemProcessAttention dao = new SystemProcessAttention();
		if(setDeleteConditions(bean, dao) == 0) return 0;
		int result = dao.conditionalDelete();
		return result ;
	}

	public static BaseSystemProcessAttention updateSystemProcessAttention ( BaseSystemProcessAttention systemprocessattention ) throws Exception {
		SystemProcessAttention dao = new SystemProcessAttention();
		dao.setSystemProcessAttentionId( systemprocessattention.getSystemProcessAttentionId() );
		int result = 0;
		if( dao.load() ) {
			dao.setDataFromBase(systemprocessattention);
			result = dao.update();
		}
		return result == 1 ? systemprocessattention : null ;
	}

	public static BaseSystemProcessAttention updateSystemProcessAttentionDirect( BaseSystemProcessAttention systemprocessattention ) throws Exception {
		SystemProcessAttention dao = new SystemProcessAttention();
		int result = 0;
		dao.setDataFromBase(systemprocessattention);
		result = dao.update();
		return result == 1 ? systemprocessattention : null ;
	}

	public static int setDeleteConditions(BaseSystemProcessAttention bean, SystemProcessAttention dao){
		int count = 0;
		boolean foundKey = false;
		if(bean.getSystemProcessAttentionId() != null) {
			dao.setConditionSystemProcessAttentionId("=", bean.getSystemProcessAttentionId());
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
			if(bean.getInstanceActivityId() != null) {
				dao.setConditionInstanceActivityId("=", bean.getInstanceActivityId());
				count++;
			}
			if(bean.getActivityId() != null) {
				dao.setConditionActivityId("=", bean.getActivityId());
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
			if(bean.getOrganizationId() != null) {
				dao.setConditionOrganizationId("=", bean.getOrganizationId());
				count++;
			}
		}
		return count;
	}

	public static int setConditions(BaseSystemProcessAttention bean, SystemProcessAttention dao){
		int count = 0;
		if(bean.getSystemProcessAttentionId() != null) {
			dao.setConditionSystemProcessAttentionId("=", bean.getSystemProcessAttentionId());
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
		if(bean.getInstanceActivityId() != null) {
			dao.setConditionInstanceActivityId("=", bean.getInstanceActivityId());
			count++;
		}
		if(bean.getActivityId() != null) {
			dao.setConditionActivityId("=", bean.getActivityId());
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
		if(bean.getOrganizationId() != null) {
			dao.setConditionOrganizationId("=", bean.getOrganizationId());
			count++;
		}
		return count;
	}

	@Override
	public String findUsingKey(String json) throws Exception{
		BaseSystemProcessAttention bean = new BaseSystemProcessAttention();
		bean.setDataFromJSON(json);
		SystemProcessAttention dao = new SystemProcessAttention();
		dao.setPrimaryKeyFromBase(bean);
		if(dao.load()) { dao.setDataToBase(bean); return bean.toOneLineJSON(1,null); }
		return bean.toOneLineJSON(0,"Record not found.");
	}

	@Override
	public String find(String json, int currentPage, int pageSize, String addtion) throws Exception{
		BaseCollection<BaseSystemProcessAttention> rlist = new BaseCollection<>();
		BaseSystemProcessAttention bean = new BaseSystemProcessAttention();
		JSON parser = new JSON(new StringReader(json));
		Map<String, Object> params = (Map<String, Object>) parser.parse(new BasicHandler());
		bean.setDataFromMap(params);
		PaginationParameter pp = PaginationParameter.generateFromMap(params);
		if(pp != null){
			if(pp.getCurrentPage() > 0) currentPage = pp.getCurrentPage();
			if(pp.getPageSize() > 0) pageSize = pp.getPageSize();
			if(pp.getCondition() != null) addtion = pp.getCondition();
		}
		SystemProcessAttention dao = new SystemProcessAttention();
		setConditions(bean, dao);
		dao.setCurrentPage(currentPage);
		dao.setPageLines(pageSize);
		List<BaseSystemProcessAttention> result = dao.conditionalLoad(addtion);
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
		BaseSystemProcessAttention bean = new BaseSystemProcessAttention();
		bean.setDataFromJSON(json);
		SystemProcessAttention dao = new SystemProcessAttention();
		dao.setDataFromBase(bean);
		int num = dao.save();
		dao.setDataToBase(bean);
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String update(String json) throws Exception{
		BaseSystemProcessAttention bean = new BaseSystemProcessAttention();
		bean.setDataFromJSON(json);
		SystemProcessAttention dao = new SystemProcessAttention();
		dao.setPrimaryKeyFromBase(bean);
		int num=0;
		if(dao.load()){dao.setDataFromBase(bean); num = dao.update(); }
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String delete(String json) throws Exception{
		BaseSystemProcessAttention bean = new BaseSystemProcessAttention();
		bean.setDataFromJSON(json);
		SystemProcessAttention dao = new SystemProcessAttention();
		setDeleteConditions(bean, dao);
		int num = dao.conditionalDelete();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String saveOrUpdate(String json) throws Exception{
		BaseSystemProcessAttention bean = new BaseSystemProcessAttention();
		bean.setDataFromJSON(json);
		SystemProcessAttention dao = new SystemProcessAttention();
		dao.setPrimaryKeyFromBase(bean);
		int ret = 0;
		if(dao.isPrimaryKeyNull()) { dao.setDataFromBase(bean); ret = dao.save(); bean = dao.generateBase(); }
		else if(dao.load()) { dao.setDataFromBase(bean); ret = dao.update(); bean = dao.generateBase(); }
		return bean.toOneLineJSON(ret, null);
	}

}


