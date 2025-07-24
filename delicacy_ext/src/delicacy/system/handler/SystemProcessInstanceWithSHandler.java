package delicacy.system.handler;


import java.util.List;
import delicacy.common.BaseCollection;
import delicacy.connection.ThreadConnection;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import java.io.StringReader;
import java.util.Map;
import delicacy.common.PaginationParameter;
import delicacy.system.bean.BaseSystemProcessInstanceWithS;
import delicacy.system.dao.SystemProcessInstanceWithS;
import delicacy.system.dao.SystemProcessInstanceActivity;
import org.apache.log4j.Logger;
import delicacy.common.GenericDao;
import delicacy.connection.ThreadConnection;
import delicacy.date.util.TimeSpanCalculator;

public class SystemProcessInstanceWithSHandler implements GenericDao {

	public final static ThreadLocal currentRequest = new ThreadLocal();

	private static final Logger __logger = Logger.getLogger(SystemProcessInstanceWithSHandler.class);

	public static BaseSystemProcessInstanceWithS getSystemProcessInstanceWithSById( java.lang.Integer process_instance_id )
	{
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			BaseSystemProcessInstanceWithS result;
			SystemProcessInstanceWithS dao = new SystemProcessInstanceWithS();
			dao.setProcessInstanceId(process_instance_id);
			if(dao.load(true)){
				result = dao.generateBaseExt();
				tsc.recordTime();
				__logger.info(String.format("Get SystemProcessInstanceWithS By ID time used : [%1$d]", tsc.getLastTime()));
				return result;
			}
			return null;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static boolean isSystemProcessInstanceWithSExists( delicacy.system.bean.BaseSystemProcessInstanceWithS bean, String additional ) {

		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			SystemProcessInstanceWithS dao = new SystemProcessInstanceWithS();
			setConditions(bean, dao);
			boolean res = dao.isExist(additional);
			tsc.recordTime();
			__logger.info(String.format("Query SystemProcessInstanceWithS List time used : [%1$d]", tsc.getLastTime()));
			return res;
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public static int countSystemProcessInstanceWithS( delicacy.system.bean.BaseSystemProcessInstanceWithS bean, String additional ) {

		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			SystemProcessInstanceWithS dao = new SystemProcessInstanceWithS();
			setConditions(bean, dao);
			int res = dao.countRows(additional);
			tsc.recordTime();
			__logger.info(String.format("Query SystemProcessInstanceWithS List time used : [%1$d]", tsc.getLastTime()));
			return res;
		} catch(Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	public static BaseCollection<BaseSystemProcessInstanceWithS> querySystemProcessInstanceWithS( BaseSystemProcessInstanceWithS bean, int pageNo, int pageLines, String additionalCondition ) {
		__logger.info(String.format("Current Page No. [%1$d]", pageNo));
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			SystemProcessInstanceWithS dao = new SystemProcessInstanceWithS();
			setConditions(bean, dao);
			dao.setCurrentPage(pageNo);
			dao.setPageLines(pageLines);
			List<BaseSystemProcessInstanceWithS> result = dao.conditionalLoadExt(additionalCondition);
			BaseCollection<BaseSystemProcessInstanceWithS> col = new BaseCollection<>();
			col.setCollections(result);
			col.setTotalPages(dao.getTotalPages());
			col.setCurrentPage(dao.getCurrentPage());
			col.setPageLines(dao.getPageLines());
			col.setTotalLines(dao.getTotalLines());
			col.setRecordNumber(result.size());
			tsc.recordTime();
			__logger.info(String.format("Query SystemProcessInstanceWithS List time used : [%1$d]", tsc.getLastTime()));
			return col;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseSystemProcessInstanceWithS addToSystemProcessInstanceWithS ( BaseSystemProcessInstanceWithS systemprocessinstancewiths ) {
		return addToSystemProcessInstanceWithS ( systemprocessinstancewiths , false);
	}

	public static BaseSystemProcessInstanceWithS addToSystemProcessInstanceWithS ( BaseSystemProcessInstanceWithS systemprocessinstancewiths, boolean singleTransaction ) {
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			if(singleTransaction) ThreadConnection.beginTransaction();
			SystemProcessInstanceWithS dao = new SystemProcessInstanceWithS();
			dao.setDataFromBase(systemprocessinstancewiths);
			int result = dao.save();
			if(singleTransaction) ThreadConnection.commit();
			tsc.recordTime();
			__logger.info(String.format("Add to SystemProcessInstanceWithS time used : [%1$d]", tsc.getLastTime()));
			return result==1?dao.generateBaseExt():null;
		} catch(Exception ex) {
			if(singleTransaction) ThreadConnection.rollback();
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseSystemProcessInstanceWithS addUpdateSystemProcessInstanceWithS ( BaseSystemProcessInstanceWithS systemprocessinstancewiths ) {
		return addUpdateSystemProcessInstanceWithS ( systemprocessinstancewiths , false);
	}

	public static BaseSystemProcessInstanceWithS addUpdateSystemProcessInstanceWithS ( BaseSystemProcessInstanceWithS systemprocessinstancewiths, boolean singleTransaction  ) {
		if(systemprocessinstancewiths.getProcessInstanceId() == null) return addToSystemProcessInstanceWithS(systemprocessinstancewiths);
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			if(singleTransaction) ThreadConnection.beginTransaction();
			SystemProcessInstanceWithS dao = new SystemProcessInstanceWithS();
			dao.setDataFromBase(systemprocessinstancewiths);
			int result = 0;
			if(dao.load()) {
				dao.setDataFromBase(systemprocessinstancewiths); 
				if(dao.isThisObjectModified()) { 
					result = dao.update();
				} else result = 1;
			} else result = dao.save();
			if(singleTransaction) ThreadConnection.commit();
			tsc.recordTime();
			__logger.info(String.format("Add to SystemProcessInstanceWithS time used : [%1$d]", tsc.getLastTime()));
			return result==1?dao.generateBaseExt():null;
		} catch(Exception ex) {
			if(singleTransaction) ThreadConnection.rollback();
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseSystemProcessInstanceWithS deleteSystemProcessInstanceWithS ( BaseSystemProcessInstanceWithS bean ) {
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			SystemProcessInstanceWithS dao = new SystemProcessInstanceWithS();
			setDeleteConditions(bean, dao);
			int result = dao.conditionalDelete();
			tsc.recordTime();
			__logger.info(String.format("Delete SystemProcessInstanceWithS time used : [%1$d]", tsc.getLastTime()));
			return result == 1 ? bean : null ;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseSystemProcessInstanceWithS updateSystemProcessInstanceWithS ( BaseSystemProcessInstanceWithS systemprocessinstancewiths ) {
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			SystemProcessInstanceWithS dao = new SystemProcessInstanceWithS();
			dao.setProcessInstanceId( systemprocessinstancewiths.getProcessInstanceId() );
			int result = 0;
			if( dao.load() ) {
				dao.setDataFromBase(systemprocessinstancewiths);
				result = dao.update();
			}
			tsc.recordTime();
			__logger.info(String.format("Update SystemProcessInstanceWithS time used : [%1$d]", tsc.getLastTime()));
			return result == 1 ? systemprocessinstancewiths : null ;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseSystemProcessInstanceWithS updateSystemProcessInstanceWithSDirect( BaseSystemProcessInstanceWithS systemprocessinstancewiths ) {
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			SystemProcessInstanceWithS dao = new SystemProcessInstanceWithS();
			int result = 0;
			dao.setDataFromBase(systemprocessinstancewiths);
			result = dao.update();
			tsc.recordTime();
			__logger.info(String.format("Update SystemProcessInstanceWithS time used : [%1$d]", tsc.getLastTime()));
			return result == 1 ? systemprocessinstancewiths : null ;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static int setDeleteConditions(BaseSystemProcessInstanceWithS bean, SystemProcessInstanceWithS dao){
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

	public static int setConditions(BaseSystemProcessInstanceWithS bean, SystemProcessInstanceWithS dao){
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
		BaseSystemProcessInstanceWithS bean = new BaseSystemProcessInstanceWithS();
		bean.setDataFromJSON(json);
		SystemProcessInstanceWithS dao = new SystemProcessInstanceWithS();
		dao.setPrimaryKeyFromBase(bean);
		if(dao.load(true)) {
			dao.setDataToBase(bean);
			return bean.toOneLineJSON(1,null);
		}
		return bean.toOneLineJSON(0,"Record not found.");
	}

	@Override
	public String find(String json, int currentPage, int pageSize, String addtion) throws Exception{
		BaseCollection<BaseSystemProcessInstanceWithS> rlist = new BaseCollection<>();
		BaseSystemProcessInstanceWithS bean = new BaseSystemProcessInstanceWithS();
		JSON parser = new JSON(new StringReader(json));
		Map<String, Object> params = (Map<String, Object>) parser.parse(new BasicHandler());
		bean.setDataFromMap(params);
		PaginationParameter pp = PaginationParameter.generateFromMap(params);
		if(pp != null){
			if(pp.getCurrentPage() > 0) currentPage = pp.getCurrentPage();
			if(pp.getPageSize() > 0) pageSize = pp.getPageSize();
			if(pp.getCondition() != null) addtion = pp.getCondition();
		}
		SystemProcessInstanceWithS dao = new SystemProcessInstanceWithS();
		setConditions(bean, dao);
		dao.setCurrentPage(currentPage);
		dao.setPageLines(pageSize);
		List<BaseSystemProcessInstanceWithS> result = dao.conditionalLoadExt(addtion);
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
		BaseSystemProcessInstanceWithS bean = new BaseSystemProcessInstanceWithS();
		bean.setDataFromJSON(json);
		SystemProcessInstanceWithS dao = new SystemProcessInstanceWithS();
		ThreadConnection.beginTransaction();
		dao.setDataFromBase(bean);
		int num = dao.save();
		dao.setDataToBase(bean);
		ThreadConnection.commit();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String saveOrUpdate(String json) throws Exception{
		BaseSystemProcessInstanceWithS bean = new BaseSystemProcessInstanceWithS();
		bean.setDataFromJSON(json);
		int num = 0;
		SystemProcessInstanceWithS dao = new SystemProcessInstanceWithS();
		ThreadConnection.beginTransaction();
		dao.setDataFromBase(bean);
		if(dao.isPrimaryKeyNull()) num = dao.save();
		else if(dao.load()){ dao.setDataFromBase(bean); num = dao.update(); }
		ThreadConnection.commit();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String update(String json) throws Exception{
		BaseSystemProcessInstanceWithS bean = new BaseSystemProcessInstanceWithS();
		bean.setDataFromJSON(json);
		SystemProcessInstanceWithS dao = new SystemProcessInstanceWithS();
		ThreadConnection.beginTransaction();
		dao.setPrimaryKeyFromBase(bean);
		int num=0;
		if(dao.load()){dao.setDataFromBase(bean); num = dao.update(); }
		ThreadConnection.commit();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String delete(String json) throws Exception{
		BaseSystemProcessInstanceWithS bean = new BaseSystemProcessInstanceWithS();
		bean.setDataFromJSON(json);
		SystemProcessInstanceWithS dao = new SystemProcessInstanceWithS();
		ThreadConnection.beginTransaction();
		dao.setDataFromBase(bean);
		setDeleteConditions(bean, dao);
		int num = dao.conditionalDelete();
		ThreadConnection.commit();
		return bean.toOneLineJSON(num, null);
	}

}


