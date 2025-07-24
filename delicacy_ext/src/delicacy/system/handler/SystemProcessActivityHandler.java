package delicacy.system.handler;

import org.apache.log4j.Logger;
import delicacy.system.bean.BaseSystemProcessActivity;
import java.util.List;
import delicacy.system.dao.SystemProcessActivity;
import delicacy.connection.ThreadConnection;
import delicacy.common.GenericDao;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import java.io.StringReader;
import java.util.Map;
import delicacy.common.PaginationParameter;
import delicacy.common.BaseCollection;

public class SystemProcessActivityHandler implements GenericDao {

	private static final Logger __logger = Logger.getLogger(SystemProcessActivityHandler.class);

	public static BaseSystemProcessActivity getSystemProcessActivityById( 
		java.lang.Integer process_activity_id
	) throws Exception
	{
		SystemProcessActivity dao = new SystemProcessActivity();
		dao.setProcessActivityId(process_activity_id);
		if(dao.load()){
			return dao.generateBase();
		}
		return null;
	}

	public static boolean isSystemProcessActivityExists( delicacy.system.bean.BaseSystemProcessActivity bean, String additional ) throws Exception {

		SystemProcessActivity dao = new SystemProcessActivity();
		setConditions(bean, dao);
		boolean res = dao.isExist(additional);
		return res;
	}

	public static int countSystemProcessActivity( delicacy.system.bean.BaseSystemProcessActivity bean, String additional ) throws Exception {

		SystemProcessActivity dao = new SystemProcessActivity();
		setConditions(bean, dao);
		int res = dao.countRows(additional);
		return res;
	}

	public static BaseCollection<BaseSystemProcessActivity> querySystemProcessActivity( delicacy.system.bean.BaseSystemProcessActivity bean, int pageNo, int pageLines, String additionalCondition ) throws Exception {
		SystemProcessActivity dao = new SystemProcessActivity();
		setConditions(bean, dao);
		dao.setCurrentPage(pageNo);
		dao.setPageLines(pageLines);
		java.util.List<BaseSystemProcessActivity> result = dao.conditionalLoad(additionalCondition);
		BaseCollection<BaseSystemProcessActivity> col = new BaseCollection<>();
		col.setCollections(result);
		col.setTotalPages(dao.getTotalPages());
		col.setCurrentPage(dao.getCurrentPage());
		col.setPageLines(dao.getPageLines());
		col.setTotalLines(dao.getTotalLines());
		col.setRecordNumber(result.size());
		return col;
	}

	public static BaseSystemProcessActivity addToSystemProcessActivity ( BaseSystemProcessActivity systemprocessactivity )  throws Exception {
		return addToSystemProcessActivity ( systemprocessactivity , false);
	}

	public static BaseSystemProcessActivity addToSystemProcessActivity ( BaseSystemProcessActivity systemprocessactivity, boolean singleTransaction ) throws Exception {
		if(singleTransaction) ThreadConnection.beginTransaction();
		SystemProcessActivity dao = new SystemProcessActivity();
		dao.setDataFromBase(systemprocessactivity);
		int result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static BaseSystemProcessActivity addUpdateSystemProcessActivity ( BaseSystemProcessActivity systemprocessactivity ) throws Exception {
		return addUpdateSystemProcessActivity ( systemprocessactivity , false);
	}

	public static BaseSystemProcessActivity addUpdateSystemProcessActivity ( BaseSystemProcessActivity systemprocessactivity, boolean singleTransaction  ) throws Exception {
		if(systemprocessactivity.getProcessActivityId() == null) return addToSystemProcessActivity(systemprocessactivity);
		if(singleTransaction) ThreadConnection.beginTransaction();
		StringBuilder sb = new StringBuilder();
		SystemProcessActivity dao = new SystemProcessActivity();
		dao.setDataFromBase(systemprocessactivity);
		int result = 0;
		if(dao.load()) {
			dao.setDataFromBase(systemprocessactivity); 
			if(dao.isThisObjectModified()) { 
				result = dao.update();
			} else result = 1;
		} else result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static int deleteSystemProcessActivity ( BaseSystemProcessActivity bean ) throws Exception {
		SystemProcessActivity dao = new SystemProcessActivity();
		if(setDeleteConditions(bean, dao) == 0) return 0;
		int result = dao.conditionalDelete();
		return result ;
	}

	public static BaseSystemProcessActivity updateSystemProcessActivity ( BaseSystemProcessActivity systemprocessactivity ) throws Exception {
		SystemProcessActivity dao = new SystemProcessActivity();
		dao.setProcessActivityId( systemprocessactivity.getProcessActivityId() );
		int result = 0;
		if( dao.load() ) {
			dao.setDataFromBase(systemprocessactivity);
			result = dao.update();
		}
		return result == 1 ? systemprocessactivity : null ;
	}

	public static BaseSystemProcessActivity updateSystemProcessActivityDirect( BaseSystemProcessActivity systemprocessactivity ) throws Exception {
		SystemProcessActivity dao = new SystemProcessActivity();
		int result = 0;
		dao.setDataFromBase(systemprocessactivity);
		result = dao.update();
		return result == 1 ? systemprocessactivity : null ;
	}

	public static int setDeleteConditions(BaseSystemProcessActivity bean, SystemProcessActivity dao){
		int count = 0;
		boolean foundKey = false;
		if(bean.getProcessActivityId() != null) {
			dao.setConditionProcessActivityId("=", bean.getProcessActivityId());
			count++;
			foundKey = true;
		}
		if(!foundKey) {
			if(bean.getProcessId() != null) {
				dao.setConditionProcessId("=", bean.getProcessId());
				count++;
			}
			if(bean.getActivityType() != null) {
				dao.setConditionActivityType("=", bean.getActivityType());
				count++;
			}
			if(bean.getActivityName() != null) {
				dao.setConditionActivityName("=", bean.getActivityName());
				count++;
			}
			if(bean.getActivitySerialNo() != null) {
				dao.setConditionActivitySerialNo("=", bean.getActivitySerialNo());
				count++;
			}
			if(bean.getPosx() != null) {
				dao.setConditionPosx("=", bean.getPosx());
				count++;
			}
			if(bean.getPosy() != null) {
				dao.setConditionPosy("=", bean.getPosy());
				count++;
			}
			if(bean.getTimeOutAction() != null) {
				dao.setConditionTimeOutAction("=", bean.getTimeOutAction());
				count++;
			}
			if(bean.getExecutorType() != null) {
				dao.setConditionExecutorType("=", bean.getExecutorType());
				count++;
			}
			if(bean.getEmployeeId() != null) {
				dao.setConditionEmployeeId("=", bean.getEmployeeId());
				count++;
			}
			if(bean.getDepartmentId() != null) {
				dao.setConditionDepartmentId("=", bean.getDepartmentId());
				count++;
			}
			if(bean.getRoleId() != null) {
				dao.setConditionRoleId("=", bean.getRoleId());
				count++;
			}
			if(bean.getIfAllowOver() != null) {
				dao.setConditionIfAllowOver("=", bean.getIfAllowOver());
				count++;
			}
			if(bean.getIsEnable() != null) {
				dao.setConditionIsEnable("=", bean.getIsEnable());
				count++;
			}
			if(bean.getExecuteName() != null) {
				dao.setConditionExecuteName("=", bean.getExecuteName());
				count++;
			}
			if(bean.getPoolType() != null) {
				dao.setConditionPoolType("=", bean.getPoolType());
				count++;
			}
		}
		return count;
	}

	public static int setConditions(BaseSystemProcessActivity bean, SystemProcessActivity dao){
		int count = 0;
		if(bean.getProcessActivityId() != null) {
			dao.setConditionProcessActivityId("=", bean.getProcessActivityId());
			count++;
		}
		if(bean.getProcessId() != null) {
			dao.setConditionProcessId("=", bean.getProcessId());
			count++;
		}
		if(bean.getActivityType() != null) {
			dao.setConditionActivityType("=", bean.getActivityType());
			count++;
		}
		if(bean.getActivityName() != null) {
			if(bean.getActivityName().indexOf("%") >= 0)
				dao.setConditionActivityName("like", bean.getActivityName());
			else
				dao.setConditionActivityName("=", bean.getActivityName());
			count++;
		}
		if(bean.getActivitySerialNo() != null) {
			dao.setConditionActivitySerialNo("=", bean.getActivitySerialNo());
			count++;
		}
		if(bean.getPosx() != null) {
			dao.setConditionPosx("=", bean.getPosx());
			count++;
		}
		if(bean.getPosy() != null) {
			dao.setConditionPosy("=", bean.getPosy());
			count++;
		}
		if(bean.getTimeOutAction() != null) {
			dao.setConditionTimeOutAction("=", bean.getTimeOutAction());
			count++;
		}
		if(bean.getExecutorType() != null) {
			dao.setConditionExecutorType("=", bean.getExecutorType());
			count++;
		}
		if(bean.getEmployeeId() != null) {
			dao.setConditionEmployeeId("=", bean.getEmployeeId());
			count++;
		}
		if(bean.getDepartmentId() != null) {
			dao.setConditionDepartmentId("=", bean.getDepartmentId());
			count++;
		}
		if(bean.getRoleId() != null) {
			dao.setConditionRoleId("=", bean.getRoleId());
			count++;
		}
		if(bean.getIfAllowOver() != null) {
			dao.setConditionIfAllowOver("=", bean.getIfAllowOver());
			count++;
		}
		if(bean.getIsEnable() != null) {
			dao.setConditionIsEnable("=", bean.getIsEnable());
			count++;
		}
		if(bean.getExecuteName() != null) {
			if(bean.getExecuteName().indexOf("%") >= 0)
				dao.setConditionExecuteName("like", bean.getExecuteName());
			else
				dao.setConditionExecuteName("=", bean.getExecuteName());
			count++;
		}
		if(bean.getPoolType() != null) {
			dao.setConditionPoolType("=", bean.getPoolType());
			count++;
		}
		return count;
	}

	@Override
	public String findUsingKey(String json) throws Exception{
		BaseSystemProcessActivity bean = new BaseSystemProcessActivity();
		bean.setDataFromJSON(json);
		SystemProcessActivity dao = new SystemProcessActivity();
		dao.setPrimaryKeyFromBase(bean);
		if(dao.load()) { dao.setDataToBase(bean); return bean.toOneLineJSON(1,null); }
		return bean.toOneLineJSON(0,"Record not found.");
	}

	@Override
	public String find(String json, int currentPage, int pageSize, String addtion) throws Exception{
		BaseCollection<BaseSystemProcessActivity> rlist = new BaseCollection<>();
		BaseSystemProcessActivity bean = new BaseSystemProcessActivity();
		JSON parser = new JSON(new StringReader(json));
		Map<String, Object> params = (Map<String, Object>) parser.parse(new BasicHandler());
		bean.setDataFromMap(params);
		PaginationParameter pp = PaginationParameter.generateFromMap(params);
		if(pp != null){
			if(pp.getCurrentPage() > 0) currentPage = pp.getCurrentPage();
			if(pp.getPageSize() > 0) pageSize = pp.getPageSize();
			if(pp.getCondition() != null) addtion = pp.getCondition();
		}
		SystemProcessActivity dao = new SystemProcessActivity();
		setConditions(bean, dao);
		dao.setCurrentPage(currentPage);
		dao.setPageLines(pageSize);
		List<BaseSystemProcessActivity> result = dao.conditionalLoad(addtion);
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
		BaseSystemProcessActivity bean = new BaseSystemProcessActivity();
		bean.setDataFromJSON(json);
		SystemProcessActivity dao = new SystemProcessActivity();
		dao.setDataFromBase(bean);
		int num = dao.save();
		dao.setDataToBase(bean);
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String update(String json) throws Exception{
		BaseSystemProcessActivity bean = new BaseSystemProcessActivity();
		bean.setDataFromJSON(json);
		SystemProcessActivity dao = new SystemProcessActivity();
		dao.setPrimaryKeyFromBase(bean);
		int num=0;
		if(dao.load()){dao.setDataFromBase(bean); num = dao.update(); }
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String delete(String json) throws Exception{
		BaseSystemProcessActivity bean = new BaseSystemProcessActivity();
		bean.setDataFromJSON(json);
		SystemProcessActivity dao = new SystemProcessActivity();
		setDeleteConditions(bean, dao);
		int num = dao.conditionalDelete();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String saveOrUpdate(String json) throws Exception{
		BaseSystemProcessActivity bean = new BaseSystemProcessActivity();
		bean.setDataFromJSON(json);
		SystemProcessActivity dao = new SystemProcessActivity();
		dao.setPrimaryKeyFromBase(bean);
		int ret = 0;
		if(dao.isPrimaryKeyNull()) { dao.setDataFromBase(bean); ret = dao.save(); bean = dao.generateBase(); }
		else if(dao.load()) { dao.setDataFromBase(bean); ret = dao.update(); bean = dao.generateBase(); }
		return bean.toOneLineJSON(ret, null);
	}

}


