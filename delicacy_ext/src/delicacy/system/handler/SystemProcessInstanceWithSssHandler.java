package delicacy.system.handler;


import java.util.List;
import delicacy.common.BaseCollection;
import delicacy.connection.ThreadConnection;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import java.io.StringReader;
import java.util.Map;
import delicacy.common.PaginationParameter;
import delicacy.system.bean.BaseSystemProcessInstanceWithSss;
import delicacy.system.dao.SystemProcessInstanceWithSss;
import delicacy.system.dao.SystemProcessInstanceActivity;
import delicacy.system.dao.SystemProcessAttention;
import delicacy.system.dao.SystemProcessPooledTask;
import org.apache.log4j.Logger;
import delicacy.common.GenericDao;
import delicacy.connection.ThreadConnection;
import delicacy.date.util.TimeSpanCalculator;

public class SystemProcessInstanceWithSssHandler implements GenericDao {

	public final static ThreadLocal currentRequest = new ThreadLocal();

	private static final Logger __logger = Logger.getLogger(SystemProcessInstanceWithSssHandler.class);

	public static BaseSystemProcessInstanceWithSss getSystemProcessInstanceWithSssById( java.lang.Integer process_instance_id )
	{
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			BaseSystemProcessInstanceWithSss result;
			SystemProcessInstanceWithSss dao = new SystemProcessInstanceWithSss();
			dao.setProcessInstanceId(process_instance_id);
			if(dao.load(true)){
				result = dao.generateBaseExt();
				tsc.recordTime();
				__logger.info(String.format("Get SystemProcessInstanceWithSss By ID time used : [%1$d]", tsc.getLastTime()));
				return result;
			}
			return null;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static boolean isSystemProcessInstanceWithSssExists( delicacy.system.bean.BaseSystemProcessInstanceWithSss bean, String additional ) {

		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			SystemProcessInstanceWithSss dao = new SystemProcessInstanceWithSss();
			setConditions(bean, dao);
			boolean res = dao.isExist(additional);
			tsc.recordTime();
			__logger.info(String.format("Query SystemProcessInstanceWithSss List time used : [%1$d]", tsc.getLastTime()));
			return res;
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public static int countSystemProcessInstanceWithSss( delicacy.system.bean.BaseSystemProcessInstanceWithSss bean, String additional ) {

		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			SystemProcessInstanceWithSss dao = new SystemProcessInstanceWithSss();
			setConditions(bean, dao);
			int res = dao.countRows(additional);
			tsc.recordTime();
			__logger.info(String.format("Query SystemProcessInstanceWithSss List time used : [%1$d]", tsc.getLastTime()));
			return res;
		} catch(Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	public static BaseCollection<BaseSystemProcessInstanceWithSss> querySystemProcessInstanceWithSss( BaseSystemProcessInstanceWithSss bean, int pageNo, int pageLines, String additionalCondition ) {
		__logger.info(String.format("Current Page No. [%1$d]", pageNo));
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			SystemProcessInstanceWithSss dao = new SystemProcessInstanceWithSss();
			setConditions(bean, dao);
			dao.setCurrentPage(pageNo);
			dao.setPageLines(pageLines);
			List<BaseSystemProcessInstanceWithSss> result = dao.conditionalLoadExt(additionalCondition);
			BaseCollection<BaseSystemProcessInstanceWithSss> col = new BaseCollection<>();
			col.setCollections(result);
			col.setTotalPages(dao.getTotalPages());
			col.setCurrentPage(dao.getCurrentPage());
			col.setPageLines(dao.getPageLines());
			col.setTotalLines(dao.getTotalLines());
			col.setRecordNumber(result.size());
			tsc.recordTime();
			__logger.info(String.format("Query SystemProcessInstanceWithSss List time used : [%1$d]", tsc.getLastTime()));
			return col;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseSystemProcessInstanceWithSss addToSystemProcessInstanceWithSss ( BaseSystemProcessInstanceWithSss systemprocessinstancewithsss ) {
		return addToSystemProcessInstanceWithSss ( systemprocessinstancewithsss , false);
	}

	public static BaseSystemProcessInstanceWithSss addToSystemProcessInstanceWithSss ( BaseSystemProcessInstanceWithSss systemprocessinstancewithsss, boolean singleTransaction ) {
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			if(singleTransaction) ThreadConnection.beginTransaction();
			SystemProcessInstanceWithSss dao = new SystemProcessInstanceWithSss();
			dao.setDataFromBase(systemprocessinstancewithsss);
			int result = dao.save();
			if(singleTransaction) ThreadConnection.commit();
			tsc.recordTime();
			__logger.info(String.format("Add to SystemProcessInstanceWithSss time used : [%1$d]", tsc.getLastTime()));
			return result==1?dao.generateBaseExt():null;
		} catch(Exception ex) {
			if(singleTransaction) ThreadConnection.rollback();
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseSystemProcessInstanceWithSss addUpdateSystemProcessInstanceWithSss ( BaseSystemProcessInstanceWithSss systemprocessinstancewithsss ) {
		return addUpdateSystemProcessInstanceWithSss ( systemprocessinstancewithsss , false);
	}

	public static BaseSystemProcessInstanceWithSss addUpdateSystemProcessInstanceWithSss ( BaseSystemProcessInstanceWithSss systemprocessinstancewithsss, boolean singleTransaction  ) {
		if(systemprocessinstancewithsss.getProcessInstanceId() == null) return addToSystemProcessInstanceWithSss(systemprocessinstancewithsss);
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			if(singleTransaction) ThreadConnection.beginTransaction();
			SystemProcessInstanceWithSss dao = new SystemProcessInstanceWithSss();
			dao.setDataFromBase(systemprocessinstancewithsss);
			int result = 0;
			if(dao.load()) {
				dao.setDataFromBase(systemprocessinstancewithsss); 
				if(dao.isThisObjectModified()) { 
					result = dao.update();
				} else result = 1;
			} else result = dao.save();
			if(singleTransaction) ThreadConnection.commit();
			tsc.recordTime();
			__logger.info(String.format("Add to SystemProcessInstanceWithSss time used : [%1$d]", tsc.getLastTime()));
			return result==1?dao.generateBaseExt():null;
		} catch(Exception ex) {
			if(singleTransaction) ThreadConnection.rollback();
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseSystemProcessInstanceWithSss deleteSystemProcessInstanceWithSss ( BaseSystemProcessInstanceWithSss bean ) {
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			SystemProcessInstanceWithSss dao = new SystemProcessInstanceWithSss();
			setDeleteConditions(bean, dao);
			int result = dao.conditionalDelete();
			tsc.recordTime();
			__logger.info(String.format("Delete SystemProcessInstanceWithSss time used : [%1$d]", tsc.getLastTime()));
			return result == 1 ? bean : null ;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseSystemProcessInstanceWithSss updateSystemProcessInstanceWithSss ( BaseSystemProcessInstanceWithSss systemprocessinstancewithsss ) {
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			SystemProcessInstanceWithSss dao = new SystemProcessInstanceWithSss();
			dao.setProcessInstanceId( systemprocessinstancewithsss.getProcessInstanceId() );
			int result = 0;
			if( dao.load() ) {
				dao.setDataFromBase(systemprocessinstancewithsss);
				result = dao.update();
			}
			tsc.recordTime();
			__logger.info(String.format("Update SystemProcessInstanceWithSss time used : [%1$d]", tsc.getLastTime()));
			return result == 1 ? systemprocessinstancewithsss : null ;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseSystemProcessInstanceWithSss updateSystemProcessInstanceWithSssDirect( BaseSystemProcessInstanceWithSss systemprocessinstancewithsss ) {
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			SystemProcessInstanceWithSss dao = new SystemProcessInstanceWithSss();
			int result = 0;
			dao.setDataFromBase(systemprocessinstancewithsss);
			result = dao.update();
			tsc.recordTime();
			__logger.info(String.format("Update SystemProcessInstanceWithSss time used : [%1$d]", tsc.getLastTime()));
			return result == 1 ? systemprocessinstancewithsss : null ;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static int setDeleteConditions(BaseSystemProcessInstanceWithSss bean, SystemProcessInstanceWithSss dao){
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

	public static int setConditions(BaseSystemProcessInstanceWithSss bean, SystemProcessInstanceWithSss dao){
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
		BaseSystemProcessInstanceWithSss bean = new BaseSystemProcessInstanceWithSss();
		bean.setDataFromJSON(json);
		SystemProcessInstanceWithSss dao = new SystemProcessInstanceWithSss();
		dao.setPrimaryKeyFromBase(bean);
		if(dao.load(true)) {
			dao.setDataToBase(bean);
			return bean.toOneLineJSON(1,null);
		}
		return bean.toOneLineJSON(0,"Record not found.");
	}

	@Override
	public String find(String json, int currentPage, int pageSize, String addtion) throws Exception{
		BaseCollection<BaseSystemProcessInstanceWithSss> rlist = new BaseCollection<>();
		BaseSystemProcessInstanceWithSss bean = new BaseSystemProcessInstanceWithSss();
		JSON parser = new JSON(new StringReader(json));
		Map<String, Object> params = (Map<String, Object>) parser.parse(new BasicHandler());
		bean.setDataFromMap(params);
		PaginationParameter pp = PaginationParameter.generateFromMap(params);
		if(pp != null){
			if(pp.getCurrentPage() > 0) currentPage = pp.getCurrentPage();
			if(pp.getPageSize() > 0) pageSize = pp.getPageSize();
			if(pp.getCondition() != null) addtion = pp.getCondition();
		}
		SystemProcessInstanceWithSss dao = new SystemProcessInstanceWithSss();
		setConditions(bean, dao);
		dao.setCurrentPage(currentPage);
		dao.setPageLines(pageSize);
		List<BaseSystemProcessInstanceWithSss> result = dao.conditionalLoadExt(addtion);
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
		BaseSystemProcessInstanceWithSss bean = new BaseSystemProcessInstanceWithSss();
		bean.setDataFromJSON(json);
		SystemProcessInstanceWithSss dao = new SystemProcessInstanceWithSss();
		ThreadConnection.beginTransaction();
		dao.setDataFromBase(bean);
		int num = dao.save();
		dao.setDataToBase(bean);
		ThreadConnection.commit();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String saveOrUpdate(String json) throws Exception{
		BaseSystemProcessInstanceWithSss bean = new BaseSystemProcessInstanceWithSss();
		bean.setDataFromJSON(json);
		int num = 0;
		SystemProcessInstanceWithSss dao = new SystemProcessInstanceWithSss();
		ThreadConnection.beginTransaction();
		dao.setDataFromBase(bean);
		if(dao.isPrimaryKeyNull()) num = dao.save();
		else if(dao.load()){ dao.setDataFromBase(bean); num = dao.update(); }
		ThreadConnection.commit();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String update(String json) throws Exception{
		BaseSystemProcessInstanceWithSss bean = new BaseSystemProcessInstanceWithSss();
		bean.setDataFromJSON(json);
		SystemProcessInstanceWithSss dao = new SystemProcessInstanceWithSss();
		ThreadConnection.beginTransaction();
		dao.setPrimaryKeyFromBase(bean);
		int num=0;
		if(dao.load()){dao.setDataFromBase(bean); num = dao.update(); }
		ThreadConnection.commit();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String delete(String json) throws Exception{
		BaseSystemProcessInstanceWithSss bean = new BaseSystemProcessInstanceWithSss();
		bean.setDataFromJSON(json);
		SystemProcessInstanceWithSss dao = new SystemProcessInstanceWithSss();
		ThreadConnection.beginTransaction();
		dao.setDataFromBase(bean);
		setDeleteConditions(bean, dao);
		int num = dao.conditionalDelete();
		ThreadConnection.commit();
		return bean.toOneLineJSON(num, null);
	}

}


