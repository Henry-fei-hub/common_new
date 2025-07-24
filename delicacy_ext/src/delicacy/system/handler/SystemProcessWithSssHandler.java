package delicacy.system.handler;


import java.util.List;
import delicacy.common.BaseCollection;
import delicacy.connection.ThreadConnection;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import java.io.StringReader;
import java.util.Map;
import delicacy.common.PaginationParameter;
import delicacy.system.bean.BaseSystemProcessWithSss;
import delicacy.system.dao.SystemProcessWithSss;
import delicacy.system.dao.SystemProcessDepartment;
import delicacy.system.dao.SystemProcessActivity;
import delicacy.system.dao.SystemProcessLink;
import org.apache.log4j.Logger;
import delicacy.common.GenericDao;
import delicacy.connection.ThreadConnection;
import delicacy.date.util.TimeSpanCalculator;

public class SystemProcessWithSssHandler implements GenericDao {

	public final static ThreadLocal currentRequest = new ThreadLocal();

	private static final Logger __logger = Logger.getLogger(SystemProcessWithSssHandler.class);

	public static BaseSystemProcessWithSss getSystemProcessWithSssById( java.lang.Integer process_id )
	{
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			BaseSystemProcessWithSss result;
			SystemProcessWithSss dao = new SystemProcessWithSss();
			dao.setProcessId(process_id);
			if(dao.load(true)){
				result = dao.generateBaseExt();
				tsc.recordTime();
				__logger.info(String.format("Get SystemProcessWithSss By ID time used : [%1$d]", tsc.getLastTime()));
				return result;
			}
			return null;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static boolean isSystemProcessWithSssExists( delicacy.system.bean.BaseSystemProcessWithSss bean, String additional ) {

		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			SystemProcessWithSss dao = new SystemProcessWithSss();
			setConditions(bean, dao);
			boolean res = dao.isExist(additional);
			tsc.recordTime();
			__logger.info(String.format("Query SystemProcessWithSss List time used : [%1$d]", tsc.getLastTime()));
			return res;
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public static int countSystemProcessWithSss( delicacy.system.bean.BaseSystemProcessWithSss bean, String additional ) {

		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			SystemProcessWithSss dao = new SystemProcessWithSss();
			setConditions(bean, dao);
			int res = dao.countRows(additional);
			tsc.recordTime();
			__logger.info(String.format("Query SystemProcessWithSss List time used : [%1$d]", tsc.getLastTime()));
			return res;
		} catch(Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	public static BaseCollection<BaseSystemProcessWithSss> querySystemProcessWithSss( BaseSystemProcessWithSss bean, int pageNo, int pageLines, String additionalCondition ) {
		__logger.info(String.format("Current Page No. [%1$d]", pageNo));
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			SystemProcessWithSss dao = new SystemProcessWithSss();
			setConditions(bean, dao);
			dao.setCurrentPage(pageNo);
			dao.setPageLines(pageLines);
			List<BaseSystemProcessWithSss> result = dao.conditionalLoadExt(additionalCondition);
			BaseCollection<BaseSystemProcessWithSss> col = new BaseCollection<>();
			col.setCollections(result);
			col.setTotalPages(dao.getTotalPages());
			col.setCurrentPage(dao.getCurrentPage());
			col.setPageLines(dao.getPageLines());
			col.setTotalLines(dao.getTotalLines());
			col.setRecordNumber(result.size());
			tsc.recordTime();
			__logger.info(String.format("Query SystemProcessWithSss List time used : [%1$d]", tsc.getLastTime()));
			return col;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseSystemProcessWithSss addToSystemProcessWithSss ( BaseSystemProcessWithSss systemprocesswithsss ) {
		return addToSystemProcessWithSss ( systemprocesswithsss , false);
	}

	public static BaseSystemProcessWithSss addToSystemProcessWithSss ( BaseSystemProcessWithSss systemprocesswithsss, boolean singleTransaction ) {
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			if(singleTransaction) ThreadConnection.beginTransaction();
			SystemProcessWithSss dao = new SystemProcessWithSss();
			dao.setDataFromBase(systemprocesswithsss);
			int result = dao.save();
			if(singleTransaction) ThreadConnection.commit();
			tsc.recordTime();
			__logger.info(String.format("Add to SystemProcessWithSss time used : [%1$d]", tsc.getLastTime()));
			return result==1?dao.generateBaseExt():null;
		} catch(Exception ex) {
			if(singleTransaction) ThreadConnection.rollback();
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseSystemProcessWithSss addUpdateSystemProcessWithSss ( BaseSystemProcessWithSss systemprocesswithsss ) {
		return addUpdateSystemProcessWithSss ( systemprocesswithsss , false);
	}

	public static BaseSystemProcessWithSss addUpdateSystemProcessWithSss ( BaseSystemProcessWithSss systemprocesswithsss, boolean singleTransaction  ) {
		if(systemprocesswithsss.getProcessId() == null) return addToSystemProcessWithSss(systemprocesswithsss);
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			if(singleTransaction) ThreadConnection.beginTransaction();
			SystemProcessWithSss dao = new SystemProcessWithSss();
			dao.setDataFromBase(systemprocesswithsss);
			int result = 0;
			if(dao.load()) {
				dao.setDataFromBase(systemprocesswithsss); 
				if(dao.isThisObjectModified()) { 
					result = dao.update();
				} else result = 1;
			} else result = dao.save();
			if(singleTransaction) ThreadConnection.commit();
			tsc.recordTime();
			__logger.info(String.format("Add to SystemProcessWithSss time used : [%1$d]", tsc.getLastTime()));
			return result==1?dao.generateBaseExt():null;
		} catch(Exception ex) {
			if(singleTransaction) ThreadConnection.rollback();
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseSystemProcessWithSss deleteSystemProcessWithSss ( BaseSystemProcessWithSss bean ) {
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			SystemProcessWithSss dao = new SystemProcessWithSss();
			setDeleteConditions(bean, dao);
			int result = dao.conditionalDelete();
			tsc.recordTime();
			__logger.info(String.format("Delete SystemProcessWithSss time used : [%1$d]", tsc.getLastTime()));
			return result == 1 ? bean : null ;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseSystemProcessWithSss updateSystemProcessWithSss ( BaseSystemProcessWithSss systemprocesswithsss ) {
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			SystemProcessWithSss dao = new SystemProcessWithSss();
			dao.setProcessId( systemprocesswithsss.getProcessId() );
			int result = 0;
			if( dao.load() ) {
				dao.setDataFromBase(systemprocesswithsss);
				result = dao.update();
			}
			tsc.recordTime();
			__logger.info(String.format("Update SystemProcessWithSss time used : [%1$d]", tsc.getLastTime()));
			return result == 1 ? systemprocesswithsss : null ;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseSystemProcessWithSss updateSystemProcessWithSssDirect( BaseSystemProcessWithSss systemprocesswithsss ) {
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			SystemProcessWithSss dao = new SystemProcessWithSss();
			int result = 0;
			dao.setDataFromBase(systemprocesswithsss);
			result = dao.update();
			tsc.recordTime();
			__logger.info(String.format("Update SystemProcessWithSss time used : [%1$d]", tsc.getLastTime()));
			return result == 1 ? systemprocesswithsss : null ;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static int setDeleteConditions(BaseSystemProcessWithSss bean, SystemProcessWithSss dao){
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

	public static int setConditions(BaseSystemProcessWithSss bean, SystemProcessWithSss dao){
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
		BaseSystemProcessWithSss bean = new BaseSystemProcessWithSss();
		bean.setDataFromJSON(json);
		SystemProcessWithSss dao = new SystemProcessWithSss();
		dao.setPrimaryKeyFromBase(bean);
		if(dao.load(true)) {
			dao.setDataToBase(bean);
			return bean.toOneLineJSON(1,null);
		}
		return bean.toOneLineJSON(0,"Record not found.");
	}

	@Override
	public String find(String json, int currentPage, int pageSize, String addtion) throws Exception{
		BaseCollection<BaseSystemProcessWithSss> rlist = new BaseCollection<>();
		BaseSystemProcessWithSss bean = new BaseSystemProcessWithSss();
		JSON parser = new JSON(new StringReader(json));
		Map<String, Object> params = (Map<String, Object>) parser.parse(new BasicHandler());
		bean.setDataFromMap(params);
		PaginationParameter pp = PaginationParameter.generateFromMap(params);
		if(pp != null){
			if(pp.getCurrentPage() > 0) currentPage = pp.getCurrentPage();
			if(pp.getPageSize() > 0) pageSize = pp.getPageSize();
			if(pp.getCondition() != null) addtion = pp.getCondition();
		}
		SystemProcessWithSss dao = new SystemProcessWithSss();
		setConditions(bean, dao);
		dao.setCurrentPage(currentPage);
		dao.setPageLines(pageSize);
		List<BaseSystemProcessWithSss> result = dao.conditionalLoadExt(addtion);
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
		BaseSystemProcessWithSss bean = new BaseSystemProcessWithSss();
		bean.setDataFromJSON(json);
		SystemProcessWithSss dao = new SystemProcessWithSss();
		ThreadConnection.beginTransaction();
		dao.setDataFromBase(bean);
		int num = dao.save();
		dao.setDataToBase(bean);
		ThreadConnection.commit();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String saveOrUpdate(String json) throws Exception{
		BaseSystemProcessWithSss bean = new BaseSystemProcessWithSss();
		bean.setDataFromJSON(json);
		int num = 0;
		SystemProcessWithSss dao = new SystemProcessWithSss();
		ThreadConnection.beginTransaction();
		dao.setDataFromBase(bean);
		if(dao.isPrimaryKeyNull()) num = dao.save();
		else if(dao.load()){ dao.setDataFromBase(bean); num = dao.update(); }
		ThreadConnection.commit();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String update(String json) throws Exception{
		BaseSystemProcessWithSss bean = new BaseSystemProcessWithSss();
		bean.setDataFromJSON(json);
		SystemProcessWithSss dao = new SystemProcessWithSss();
		ThreadConnection.beginTransaction();
		dao.setPrimaryKeyFromBase(bean);
		int num=0;
		if(dao.load()){dao.setDataFromBase(bean); num = dao.update(); }
		ThreadConnection.commit();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String delete(String json) throws Exception{
		BaseSystemProcessWithSss bean = new BaseSystemProcessWithSss();
		bean.setDataFromJSON(json);
		SystemProcessWithSss dao = new SystemProcessWithSss();
		ThreadConnection.beginTransaction();
		dao.setDataFromBase(bean);
		setDeleteConditions(bean, dao);
		int num = dao.conditionalDelete();
		ThreadConnection.commit();
		return bean.toOneLineJSON(num, null);
	}

}


