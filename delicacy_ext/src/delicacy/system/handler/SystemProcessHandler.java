package delicacy.system.handler;

import org.apache.log4j.Logger;
import delicacy.system.bean.BaseSystemProcess;
import java.util.List;
import delicacy.system.dao.SystemProcess;
import delicacy.connection.ThreadConnection;
import delicacy.common.GenericDao;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import java.io.StringReader;
import java.util.Map;
import delicacy.common.PaginationParameter;
import delicacy.common.BaseCollection;

public class SystemProcessHandler implements GenericDao {

	private static final Logger __logger = Logger.getLogger(SystemProcessHandler.class);

	public static BaseSystemProcess getSystemProcessById( 
		java.lang.Integer process_id
	) throws Exception
	{
		SystemProcess dao = new SystemProcess();
		dao.setProcessId(process_id);
		if(dao.load()){
			return dao.generateBase();
		}
		return null;
	}

	public static boolean isSystemProcessExists( delicacy.system.bean.BaseSystemProcess bean, String additional ) throws Exception {

		SystemProcess dao = new SystemProcess();
		setConditions(bean, dao);
		boolean res = dao.isExist(additional);
		return res;
	}

	public static int countSystemProcess( delicacy.system.bean.BaseSystemProcess bean, String additional ) throws Exception {

		SystemProcess dao = new SystemProcess();
		setConditions(bean, dao);
		int res = dao.countRows(additional);
		return res;
	}

	public static BaseCollection<BaseSystemProcess> querySystemProcess( delicacy.system.bean.BaseSystemProcess bean, int pageNo, int pageLines, String additionalCondition ) throws Exception {
		SystemProcess dao = new SystemProcess();
		setConditions(bean, dao);
		dao.setCurrentPage(pageNo);
		dao.setPageLines(pageLines);
		java.util.List<BaseSystemProcess> result = dao.conditionalLoad(additionalCondition);
		BaseCollection<BaseSystemProcess> col = new BaseCollection<>();
		col.setCollections(result);
		col.setTotalPages(dao.getTotalPages());
		col.setCurrentPage(dao.getCurrentPage());
		col.setPageLines(dao.getPageLines());
		col.setTotalLines(dao.getTotalLines());
		col.setRecordNumber(result.size());
		return col;
	}

	public static BaseSystemProcess addToSystemProcess ( BaseSystemProcess systemprocess )  throws Exception {
		return addToSystemProcess ( systemprocess , false);
	}

	public static BaseSystemProcess addToSystemProcess ( BaseSystemProcess systemprocess, boolean singleTransaction ) throws Exception {
		if(singleTransaction) ThreadConnection.beginTransaction();
		SystemProcess dao = new SystemProcess();
		dao.setDataFromBase(systemprocess);
		int result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static BaseSystemProcess addUpdateSystemProcess ( BaseSystemProcess systemprocess ) throws Exception {
		return addUpdateSystemProcess ( systemprocess , false);
	}

	public static BaseSystemProcess addUpdateSystemProcess ( BaseSystemProcess systemprocess, boolean singleTransaction  ) throws Exception {
		if(systemprocess.getProcessId() == null) return addToSystemProcess(systemprocess);
		if(singleTransaction) ThreadConnection.beginTransaction();
		StringBuilder sb = new StringBuilder();
		SystemProcess dao = new SystemProcess();
		dao.setDataFromBase(systemprocess);
		int result = 0;
		if(dao.load()) {
			dao.setDataFromBase(systemprocess); 
			if(dao.isThisObjectModified()) { 
				result = dao.update();
			} else result = 1;
		} else result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static int deleteSystemProcess ( BaseSystemProcess bean ) throws Exception {
		SystemProcess dao = new SystemProcess();
		if(setDeleteConditions(bean, dao) == 0) return 0;
		int result = dao.conditionalDelete();
		return result ;
	}

	public static BaseSystemProcess updateSystemProcess ( BaseSystemProcess systemprocess ) throws Exception {
		SystemProcess dao = new SystemProcess();
		dao.setProcessId( systemprocess.getProcessId() );
		int result = 0;
		if( dao.load() ) {
			dao.setDataFromBase(systemprocess);
			result = dao.update();
		}
		return result == 1 ? systemprocess : null ;
	}

	public static BaseSystemProcess updateSystemProcessDirect( BaseSystemProcess systemprocess ) throws Exception {
		SystemProcess dao = new SystemProcess();
		int result = 0;
		dao.setDataFromBase(systemprocess);
		result = dao.update();
		return result == 1 ? systemprocess : null ;
	}

	public static int setDeleteConditions(BaseSystemProcess bean, SystemProcess dao){
		int count = 0;
		boolean foundKey = false;
		if(bean.getProcessId() != null) {
			dao.setConditionProcessId("=", bean.getProcessId());
			count++;
			foundKey = true;
		}
		if(!foundKey) {
			if(bean.getProcessTypeId() != null) {
				dao.setConditionProcessTypeId("=", bean.getProcessTypeId());
				count++;
			}
			if(bean.getDepartmentId() != null) {
				dao.setConditionDepartmentId("=", bean.getDepartmentId());
				count++;
			}
			if(bean.getIncludeDepartmentId() != null) {
				dao.setConditionIncludeDepartmentId("=", bean.getIncludeDepartmentId());
				count++;
			}
			if(bean.getCreateEmployeeId() != null) {
				dao.setConditionCreateEmployeeId("=", bean.getCreateEmployeeId());
				count++;
			}
			if(bean.getProcessName() != null) {
				dao.setConditionProcessName("=", bean.getProcessName());
				count++;
			}
			if(bean.getCountersign() != null) {
				dao.setConditionCountersign("=", bean.getCountersign());
				count++;
			}
			if(bean.getHoldDepartmentId() != null) {
				dao.setConditionHoldDepartmentId("=", bean.getHoldDepartmentId());
				count++;
			}
			if(bean.getHoldDutyId() != null) {
				dao.setConditionHoldDutyId("=", bean.getHoldDutyId());
				count++;
			}
			if(bean.getDescription() != null) {
				dao.setConditionDescription("=", bean.getDescription());
				count++;
			}
			if(bean.getEnable() != null) {
				dao.setConditionEnable("=", bean.getEnable());
				count++;
			}
		}
		return count;
	}

	public static int setConditions(BaseSystemProcess bean, SystemProcess dao){
		int count = 0;
		if(bean.getProcessId() != null) {
			dao.setConditionProcessId("=", bean.getProcessId());
			count++;
		}
		if(bean.getProcessTypeId() != null) {
			dao.setConditionProcessTypeId("=", bean.getProcessTypeId());
			count++;
		}
		if(bean.getDepartmentId() != null) {
			dao.setConditionDepartmentId("=", bean.getDepartmentId());
			count++;
		}
		if(bean.getIncludeDepartmentId() != null) {
			dao.setConditionIncludeDepartmentId("=", bean.getIncludeDepartmentId());
			count++;
		}
		if(bean.getCreateEmployeeId() != null) {
			dao.setConditionCreateEmployeeId("=", bean.getCreateEmployeeId());
			count++;
		}
		if(bean.getCreateTime() != null) {
			dao.setConditionCreateTime(">=", bean.getCreateTime());
			count++;
		}
		if(bean.getProcessName() != null) {
			if(bean.getProcessName().indexOf("%") >= 0)
				dao.setConditionProcessName("like", bean.getProcessName());
			else
				dao.setConditionProcessName("=", bean.getProcessName());
			count++;
		}
		if(bean.getCountersign() != null) {
			dao.setConditionCountersign("=", bean.getCountersign());
			count++;
		}
		if(bean.getHoldDepartmentId() != null) {
			dao.setConditionHoldDepartmentId("=", bean.getHoldDepartmentId());
			count++;
		}
		if(bean.getHoldDutyId() != null) {
			dao.setConditionHoldDutyId("=", bean.getHoldDutyId());
			count++;
		}
		if(bean.getDescription() != null) {
			if(bean.getDescription().indexOf("%") >= 0)
				dao.setConditionDescription("like", bean.getDescription());
			else
				dao.setConditionDescription("=", bean.getDescription());
			count++;
		}
		if(bean.getEnable() != null) {
			dao.setConditionEnable("=", bean.getEnable());
			count++;
		}
		return count;
	}

	@Override
	public String findUsingKey(String json) throws Exception{
		BaseSystemProcess bean = new BaseSystemProcess();
		bean.setDataFromJSON(json);
		SystemProcess dao = new SystemProcess();
		dao.setPrimaryKeyFromBase(bean);
		if(dao.load()) { dao.setDataToBase(bean); return bean.toOneLineJSON(1,null); }
		return bean.toOneLineJSON(0,"Record not found.");
	}

	@Override
	public String find(String json, int currentPage, int pageSize, String addtion) throws Exception{
		BaseCollection<BaseSystemProcess> rlist = new BaseCollection<>();
		BaseSystemProcess bean = new BaseSystemProcess();
		JSON parser = new JSON(new StringReader(json));
		Map<String, Object> params = (Map<String, Object>) parser.parse(new BasicHandler());
		bean.setDataFromMap(params);
		PaginationParameter pp = PaginationParameter.generateFromMap(params);
		if(pp != null){
			if(pp.getCurrentPage() > 0) currentPage = pp.getCurrentPage();
			if(pp.getPageSize() > 0) pageSize = pp.getPageSize();
			if(pp.getCondition() != null) addtion = pp.getCondition();
		}
		SystemProcess dao = new SystemProcess();
		setConditions(bean, dao);
		dao.setCurrentPage(currentPage);
		dao.setPageLines(pageSize);
		List<BaseSystemProcess> result = dao.conditionalLoad(addtion);
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
		BaseSystemProcess bean = new BaseSystemProcess();
		bean.setDataFromJSON(json);
		SystemProcess dao = new SystemProcess();
		dao.setDataFromBase(bean);
		int num = dao.save();
		dao.setDataToBase(bean);
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String update(String json) throws Exception{
		BaseSystemProcess bean = new BaseSystemProcess();
		bean.setDataFromJSON(json);
		SystemProcess dao = new SystemProcess();
		dao.setPrimaryKeyFromBase(bean);
		int num=0;
		if(dao.load()){dao.setDataFromBase(bean); num = dao.update(); }
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String delete(String json) throws Exception{
		BaseSystemProcess bean = new BaseSystemProcess();
		bean.setDataFromJSON(json);
		SystemProcess dao = new SystemProcess();
		setDeleteConditions(bean, dao);
		int num = dao.conditionalDelete();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String saveOrUpdate(String json) throws Exception{
		BaseSystemProcess bean = new BaseSystemProcess();
		bean.setDataFromJSON(json);
		SystemProcess dao = new SystemProcess();
		dao.setPrimaryKeyFromBase(bean);
		int ret = 0;
		if(dao.isPrimaryKeyNull()) { dao.setDataFromBase(bean); ret = dao.save(); bean = dao.generateBase(); }
		else if(dao.load()) { dao.setDataFromBase(bean); ret = dao.update(); bean = dao.generateBase(); }
		return bean.toOneLineJSON(ret, null);
	}

}


