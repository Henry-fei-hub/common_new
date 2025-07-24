package delicacy.system.handler;


import java.util.List;
import delicacy.common.BaseCollection;
import delicacy.connection.ThreadConnection;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import java.io.StringReader;
import java.util.Map;
import delicacy.common.PaginationParameter;
import delicacy.system.bean.BaseSystemProcessInstanceWithSs;
import delicacy.system.dao.SystemProcessInstanceWithSs;
import delicacy.system.dao.SystemProcessInstanceActivity;
import delicacy.system.dao.SystemProcessAttention;
import org.apache.log4j.Logger;
import delicacy.common.GenericDao;
import delicacy.connection.ThreadConnection;
import delicacy.date.util.TimeSpanCalculator;

public class SystemProcessInstanceWithSsHandler implements GenericDao {

	public final static ThreadLocal currentRequest = new ThreadLocal();

	private static final Logger __logger = Logger.getLogger(SystemProcessInstanceWithSsHandler.class);

	public static BaseSystemProcessInstanceWithSs getSystemProcessInstanceWithSsById( java.lang.Integer process_instance_id )
	{
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			BaseSystemProcessInstanceWithSs result;
			SystemProcessInstanceWithSs dao = new SystemProcessInstanceWithSs();
			dao.setProcessInstanceId(process_instance_id);
			if(dao.load(true)){
				result = dao.generateBaseExt();
				tsc.recordTime();
				__logger.info(String.format("Get SystemProcessInstanceWithSs By ID time used : [%1$d]", tsc.getLastTime()));
				return result;
			}
			return null;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static boolean isSystemProcessInstanceWithSsExists( delicacy.system.bean.BaseSystemProcessInstanceWithSs bean, String additional ) {

		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			SystemProcessInstanceWithSs dao = new SystemProcessInstanceWithSs();
			setConditions(bean, dao);
			boolean res = dao.isExist(additional);
			tsc.recordTime();
			__logger.info(String.format("Query SystemProcessInstanceWithSs List time used : [%1$d]", tsc.getLastTime()));
			return res;
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public static int countSystemProcessInstanceWithSs( delicacy.system.bean.BaseSystemProcessInstanceWithSs bean, String additional ) {

		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			SystemProcessInstanceWithSs dao = new SystemProcessInstanceWithSs();
			setConditions(bean, dao);
			int res = dao.countRows(additional);
			tsc.recordTime();
			__logger.info(String.format("Query SystemProcessInstanceWithSs List time used : [%1$d]", tsc.getLastTime()));
			return res;
		} catch(Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	public static BaseCollection<BaseSystemProcessInstanceWithSs> querySystemProcessInstanceWithSs( BaseSystemProcessInstanceWithSs bean, int pageNo, int pageLines, String additionalCondition ) {
		__logger.info(String.format("Current Page No. [%1$d]", pageNo));
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			SystemProcessInstanceWithSs dao = new SystemProcessInstanceWithSs();
			setConditions(bean, dao);
			dao.setCurrentPage(pageNo);
			dao.setPageLines(pageLines);
			List<BaseSystemProcessInstanceWithSs> result = dao.conditionalLoadExt(additionalCondition);
			BaseCollection<BaseSystemProcessInstanceWithSs> col = new BaseCollection<>();
			col.setCollections(result);
			col.setTotalPages(dao.getTotalPages());
			col.setCurrentPage(dao.getCurrentPage());
			col.setPageLines(dao.getPageLines());
			col.setTotalLines(dao.getTotalLines());
			col.setRecordNumber(result.size());
			tsc.recordTime();
			__logger.info(String.format("Query SystemProcessInstanceWithSs List time used : [%1$d]", tsc.getLastTime()));
			return col;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseSystemProcessInstanceWithSs addToSystemProcessInstanceWithSs ( BaseSystemProcessInstanceWithSs systemprocessinstancewithss ) {
		return addToSystemProcessInstanceWithSs ( systemprocessinstancewithss , false);
	}

	public static BaseSystemProcessInstanceWithSs addToSystemProcessInstanceWithSs ( BaseSystemProcessInstanceWithSs systemprocessinstancewithss, boolean singleTransaction ) {
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			if(singleTransaction) ThreadConnection.beginTransaction();
			SystemProcessInstanceWithSs dao = new SystemProcessInstanceWithSs();
			dao.setDataFromBase(systemprocessinstancewithss);
			int result = dao.save();
			if(singleTransaction) ThreadConnection.commit();
			tsc.recordTime();
			__logger.info(String.format("Add to SystemProcessInstanceWithSs time used : [%1$d]", tsc.getLastTime()));
			return result==1?dao.generateBaseExt():null;
		} catch(Exception ex) {
			if(singleTransaction) ThreadConnection.rollback();
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseSystemProcessInstanceWithSs addUpdateSystemProcessInstanceWithSs ( BaseSystemProcessInstanceWithSs systemprocessinstancewithss ) {
		return addUpdateSystemProcessInstanceWithSs ( systemprocessinstancewithss , false);
	}

	public static BaseSystemProcessInstanceWithSs addUpdateSystemProcessInstanceWithSs ( BaseSystemProcessInstanceWithSs systemprocessinstancewithss, boolean singleTransaction  ) {
		if(systemprocessinstancewithss.getProcessInstanceId() == null) return addToSystemProcessInstanceWithSs(systemprocessinstancewithss);
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			if(singleTransaction) ThreadConnection.beginTransaction();
			SystemProcessInstanceWithSs dao = new SystemProcessInstanceWithSs();
			dao.setDataFromBase(systemprocessinstancewithss);
			int result = 0;
			if(dao.load()) {
				dao.setDataFromBase(systemprocessinstancewithss); 
				if(dao.isThisObjectModified()) { 
					result = dao.update();
				} else result = 1;
			} else result = dao.save();
			if(singleTransaction) ThreadConnection.commit();
			tsc.recordTime();
			__logger.info(String.format("Add to SystemProcessInstanceWithSs time used : [%1$d]", tsc.getLastTime()));
			return result==1?dao.generateBaseExt():null;
		} catch(Exception ex) {
			if(singleTransaction) ThreadConnection.rollback();
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseSystemProcessInstanceWithSs deleteSystemProcessInstanceWithSs ( BaseSystemProcessInstanceWithSs bean ) {
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			SystemProcessInstanceWithSs dao = new SystemProcessInstanceWithSs();
			setDeleteConditions(bean, dao);
			int result = dao.conditionalDelete();
			tsc.recordTime();
			__logger.info(String.format("Delete SystemProcessInstanceWithSs time used : [%1$d]", tsc.getLastTime()));
			return result == 1 ? bean : null ;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseSystemProcessInstanceWithSs updateSystemProcessInstanceWithSs ( BaseSystemProcessInstanceWithSs systemprocessinstancewithss ) {
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			SystemProcessInstanceWithSs dao = new SystemProcessInstanceWithSs();
			dao.setProcessInstanceId( systemprocessinstancewithss.getProcessInstanceId() );
			int result = 0;
			if( dao.load() ) {
				dao.setDataFromBase(systemprocessinstancewithss);
				result = dao.update();
			}
			tsc.recordTime();
			__logger.info(String.format("Update SystemProcessInstanceWithSs time used : [%1$d]", tsc.getLastTime()));
			return result == 1 ? systemprocessinstancewithss : null ;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseSystemProcessInstanceWithSs updateSystemProcessInstanceWithSsDirect( BaseSystemProcessInstanceWithSs systemprocessinstancewithss ) {
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			SystemProcessInstanceWithSs dao = new SystemProcessInstanceWithSs();
			int result = 0;
			dao.setDataFromBase(systemprocessinstancewithss);
			result = dao.update();
			tsc.recordTime();
			__logger.info(String.format("Update SystemProcessInstanceWithSs time used : [%1$d]", tsc.getLastTime()));
			return result == 1 ? systemprocessinstancewithss : null ;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static int setDeleteConditions(BaseSystemProcessInstanceWithSs bean, SystemProcessInstanceWithSs dao){
		int count = 0;
		boolean foundKey = false;
		if(bean.getProcessInstanceId() != null) {
			dao.setProcessInstanceId(bean.getProcessInstanceId());
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
		}
		return count;
	}

	public static int setConditions(BaseSystemProcessInstanceWithSs bean, SystemProcessInstanceWithSs dao){
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
		return count;
	}

	@Override
	public String findUsingKey(String json) throws Exception{
		BaseSystemProcessInstanceWithSs bean = new BaseSystemProcessInstanceWithSs();
		bean.setDataFromJSON(json);
		SystemProcessInstanceWithSs dao = new SystemProcessInstanceWithSs();
		dao.setPrimaryKeyFromBase(bean);
		if(dao.load(true)) {
			dao.setDataToBase(bean);
			return bean.toOneLineJSON(1,null);
		}
		return bean.toOneLineJSON(0,"Record not found.");
	}

	@Override
	public String find(String json, int currentPage, int pageSize, String addtion) throws Exception{
		BaseCollection<BaseSystemProcessInstanceWithSs> rlist = new BaseCollection<>();
		BaseSystemProcessInstanceWithSs bean = new BaseSystemProcessInstanceWithSs();
		JSON parser = new JSON(new StringReader(json));
		Map<String, Object> params = (Map<String, Object>) parser.parse(new BasicHandler());
		bean.setDataFromMap(params);
		PaginationParameter pp = PaginationParameter.generateFromMap(params);
		if(pp != null){
			if(pp.getCurrentPage() > 0) currentPage = pp.getCurrentPage();
			if(pp.getPageSize() > 0) pageSize = pp.getPageSize();
			if(pp.getCondition() != null) addtion = pp.getCondition();
		}
		SystemProcessInstanceWithSs dao = new SystemProcessInstanceWithSs();
		setConditions(bean, dao);
		dao.setCurrentPage(currentPage);
		dao.setPageLines(pageSize);
		List<BaseSystemProcessInstanceWithSs> result = dao.conditionalLoadExt(addtion);
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
		BaseSystemProcessInstanceWithSs bean = new BaseSystemProcessInstanceWithSs();
		bean.setDataFromJSON(json);
		SystemProcessInstanceWithSs dao = new SystemProcessInstanceWithSs();
		ThreadConnection.beginTransaction();
		dao.setDataFromBase(bean);
		int num = dao.save();
		dao.setDataToBase(bean);
		ThreadConnection.commit();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String saveOrUpdate(String json) throws Exception{
		BaseSystemProcessInstanceWithSs bean = new BaseSystemProcessInstanceWithSs();
		bean.setDataFromJSON(json);
		int num = 0;
		SystemProcessInstanceWithSs dao = new SystemProcessInstanceWithSs();
		ThreadConnection.beginTransaction();
		dao.setDataFromBase(bean);
		if(dao.isPrimaryKeyNull()) num = dao.save();
		else if(dao.load()){ dao.setDataFromBase(bean); num = dao.update(); }
		ThreadConnection.commit();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String update(String json) throws Exception{
		BaseSystemProcessInstanceWithSs bean = new BaseSystemProcessInstanceWithSs();
		bean.setDataFromJSON(json);
		SystemProcessInstanceWithSs dao = new SystemProcessInstanceWithSs();
		ThreadConnection.beginTransaction();
		dao.setPrimaryKeyFromBase(bean);
		int num=0;
		if(dao.load()){dao.setDataFromBase(bean); num = dao.update(); }
		ThreadConnection.commit();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String delete(String json) throws Exception{
		BaseSystemProcessInstanceWithSs bean = new BaseSystemProcessInstanceWithSs();
		bean.setDataFromJSON(json);
		SystemProcessInstanceWithSs dao = new SystemProcessInstanceWithSs();
		ThreadConnection.beginTransaction();
		dao.setDataFromBase(bean);
		setDeleteConditions(bean, dao);
		int num = dao.conditionalDelete();
		ThreadConnection.commit();
		return bean.toOneLineJSON(num, null);
	}

}


