package delicacy.system.handler;


import java.util.List;
import delicacy.common.BaseCollection;
import delicacy.connection.ThreadConnection;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import java.io.StringReader;
import java.util.Map;
import delicacy.common.PaginationParameter;
import delicacy.system.bean.BaseSystemProcessWithSs;
import delicacy.system.dao.SystemProcessWithSs;
import delicacy.system.dao.SystemProcessActivity;
import delicacy.system.dao.SystemProcessLink;
import org.apache.log4j.Logger;
import delicacy.common.GenericDao;
import delicacy.date.util.TimeSpanCalculator;

public class SystemProcessWithSsHandler implements GenericDao {

	public final static ThreadLocal currentRequest = new ThreadLocal();

	private static final Logger __logger = Logger.getLogger(SystemProcessWithSsHandler.class);

	public static BaseSystemProcessWithSs getSystemProcessWithSsById( java.lang.Integer process_id )
	{
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			BaseSystemProcessWithSs result;
			SystemProcessWithSs dao = new SystemProcessWithSs();
			dao.setProcessId(process_id);
			if(dao.load(true)){
				result = dao.generateBaseExt();
				tsc.recordTime();
				__logger.info(String.format("Get SystemProcessWithSs By ID time used : [%1$d]", tsc.getLastTime()));
				return result;
			}
			return null;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static boolean isSystemProcessWithSsExists( delicacy.system.bean.BaseSystemProcessWithSs bean, String additional ) {

		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			SystemProcessWithSs dao = new SystemProcessWithSs();
			setConditions(bean, dao);
			boolean res = dao.isExist(additional);
			tsc.recordTime();
			__logger.info(String.format("Query SystemProcessWithSs List time used : [%1$d]", tsc.getLastTime()));
			return res;
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public static int countSystemProcessWithSs( delicacy.system.bean.BaseSystemProcessWithSs bean, String additional ) {

		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			SystemProcessWithSs dao = new SystemProcessWithSs();
			setConditions(bean, dao);
			int res = dao.countRows(additional);
			tsc.recordTime();
			__logger.info(String.format("Query SystemProcessWithSs List time used : [%1$d]", tsc.getLastTime()));
			return res;
		} catch(Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	public static BaseCollection<BaseSystemProcessWithSs> querySystemProcessWithSs( BaseSystemProcessWithSs bean, int pageNo, int pageLines, String additionalCondition ) {
		__logger.info(String.format("Current Page No. [%1$d]", pageNo));
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			SystemProcessWithSs dao = new SystemProcessWithSs();
			setConditions(bean, dao);
			dao.setCurrentPage(pageNo);
			dao.setPageLines(pageLines);
			List<BaseSystemProcessWithSs> result = dao.conditionalLoadExt(additionalCondition);
			BaseCollection<BaseSystemProcessWithSs> col = new BaseCollection<>();
			col.setCollections(result);
			col.setTotalPages(dao.getTotalPages());
			col.setCurrentPage(dao.getCurrentPage());
			col.setPageLines(dao.getPageLines());
			col.setTotalLines(dao.getTotalLines());
			col.setRecordNumber(result.size());
			tsc.recordTime();
			__logger.info(String.format("Query SystemProcessWithSs List time used : [%1$d]", tsc.getLastTime()));
			return col;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseSystemProcessWithSs addToSystemProcessWithSs ( BaseSystemProcessWithSs systemprocesswithss ) {
		return addToSystemProcessWithSs ( systemprocesswithss , false);
	}

	public static BaseSystemProcessWithSs addToSystemProcessWithSs ( BaseSystemProcessWithSs systemprocesswithss, boolean singleTransaction ) {
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			if(singleTransaction) ThreadConnection.beginTransaction();
			SystemProcessWithSs dao = new SystemProcessWithSs();
			dao.setDataFromBase(systemprocesswithss);
			int result = dao.save();
			if(singleTransaction) ThreadConnection.commit();
			tsc.recordTime();
			__logger.info(String.format("Add to SystemProcessWithSs time used : [%1$d]", tsc.getLastTime()));
			return result==1?dao.generateBaseExt():null;
		} catch(Exception ex) {
			if(singleTransaction) ThreadConnection.rollback();
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseSystemProcessWithSs addUpdateSystemProcessWithSs ( BaseSystemProcessWithSs systemprocesswithss ) {
		return addUpdateSystemProcessWithSs ( systemprocesswithss , false);
	}

	public static BaseSystemProcessWithSs addUpdateSystemProcessWithSs ( BaseSystemProcessWithSs systemprocesswithss, boolean singleTransaction  ) {
		if(systemprocesswithss.getProcessId() == null) return addToSystemProcessWithSs(systemprocesswithss);
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			if(singleTransaction) ThreadConnection.beginTransaction();
			SystemProcessWithSs dao = new SystemProcessWithSs();
			dao.setDataFromBase(systemprocesswithss);
			int result = 0;
			if(dao.load()) {
				dao.setDataFromBase(systemprocesswithss); 
				if(dao.isThisObjectModified()) { 
					result = dao.update();
				} else result = 1;
			} else result = dao.save();
			if(singleTransaction) ThreadConnection.commit();
			tsc.recordTime();
			__logger.info(String.format("Add to SystemProcessWithSs time used : [%1$d]", tsc.getLastTime()));
			return result==1?dao.generateBaseExt():null;
		} catch(Exception ex) {
			if(singleTransaction) ThreadConnection.rollback();
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseSystemProcessWithSs deleteSystemProcessWithSs ( BaseSystemProcessWithSs bean ) {
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			SystemProcessWithSs dao = new SystemProcessWithSs();
			setDeleteConditions(bean, dao);
			int result = dao.conditionalDelete();
			tsc.recordTime();
			__logger.info(String.format("Delete SystemProcessWithSs time used : [%1$d]", tsc.getLastTime()));
			return result == 1 ? bean : null ;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseSystemProcessWithSs updateSystemProcessWithSs ( BaseSystemProcessWithSs systemprocesswithss ) {
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			SystemProcessWithSs dao = new SystemProcessWithSs();
			dao.setProcessId( systemprocesswithss.getProcessId() );
			int result = 0;
			if( dao.load() ) {
				dao.setDataFromBase(systemprocesswithss);
				result = dao.update();
			}
			tsc.recordTime();
			__logger.info(String.format("Update SystemProcessWithSs time used : [%1$d]", tsc.getLastTime()));
			return result == 1 ? systemprocesswithss : null ;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseSystemProcessWithSs updateSystemProcessWithSsDirect( BaseSystemProcessWithSs systemprocesswithss ) {
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			SystemProcessWithSs dao = new SystemProcessWithSs();
			int result = 0;
			dao.setDataFromBase(systemprocesswithss);
			result = dao.update();
			tsc.recordTime();
			__logger.info(String.format("Update SystemProcessWithSs time used : [%1$d]", tsc.getLastTime()));
			return result == 1 ? systemprocesswithss : null ;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static int setDeleteConditions(BaseSystemProcessWithSs bean, SystemProcessWithSs dao){
		int count = 0;
		boolean foundKey = false;
		if(bean.getProcessId() != null) {
			dao.setProcessId(bean.getProcessId());
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

	public static int setConditions(BaseSystemProcessWithSs bean, SystemProcessWithSs dao){
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
		BaseSystemProcessWithSs bean = new BaseSystemProcessWithSs();
		bean.setDataFromJSON(json);
		SystemProcessWithSs dao = new SystemProcessWithSs();
		dao.setPrimaryKeyFromBase(bean);
		if(dao.load(true)) {
			dao.setDataToBase(bean);
			return bean.toOneLineJSON(1,null);
		}
		return bean.toOneLineJSON(0,"Record not found.");
	}

	@Override
	public String find(String json, int currentPage, int pageSize, String addtion) throws Exception{
		BaseCollection<BaseSystemProcessWithSs> rlist = new BaseCollection<>();
		BaseSystemProcessWithSs bean = new BaseSystemProcessWithSs();
		JSON parser = new JSON(new StringReader(json));
		Map<String, Object> params = (Map<String, Object>) parser.parse(new BasicHandler());
		bean.setDataFromMap(params);
		PaginationParameter pp = PaginationParameter.generateFromMap(params);
		if(pp != null){
			if(pp.getCurrentPage() > 0) currentPage = pp.getCurrentPage();
			if(pp.getPageSize() > 0) pageSize = pp.getPageSize();
			if(pp.getCondition() != null) addtion = pp.getCondition();
		}
		SystemProcessWithSs dao = new SystemProcessWithSs();
		setConditions(bean, dao);
		dao.setCurrentPage(currentPage);
		dao.setPageLines(pageSize);
		List<BaseSystemProcessWithSs> result = dao.conditionalLoadExt(addtion);
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
		BaseSystemProcessWithSs bean = new BaseSystemProcessWithSs();
		bean.setDataFromJSON(json);
		SystemProcessWithSs dao = new SystemProcessWithSs();
		dao.setDataFromBase(bean);
		int num = dao.save();
		dao.setDataToBase(bean);
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String saveOrUpdate(String json) throws Exception{
		BaseSystemProcessWithSs bean = new BaseSystemProcessWithSs();
		bean.setDataFromJSON(json);
		int num = 0;
		SystemProcessWithSs dao = new SystemProcessWithSs();
		dao.setDataFromBase(bean);
		if(dao.isPrimaryKeyNull()) num = dao.save();
		else if(dao.load()){ dao.setDataFromBase(bean); num = dao.update(); }
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String update(String json) throws Exception{
		BaseSystemProcessWithSs bean = new BaseSystemProcessWithSs();
		bean.setDataFromJSON(json);
		SystemProcessWithSs dao = new SystemProcessWithSs();
		dao.setPrimaryKeyFromBase(bean);
		int num=0;
		if(dao.load()){dao.setDataFromBase(bean); num = dao.update(); }
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String delete(String json) throws Exception{
		BaseSystemProcessWithSs bean = new BaseSystemProcessWithSs();
		bean.setDataFromJSON(json);
		SystemProcessWithSs dao = new SystemProcessWithSs();
		setDeleteConditions(bean, dao);
		int num = dao.conditionalDelete();
		return bean.toOneLineJSON(num, null);
	}

}


