package delicacy.system.handler;

import org.apache.log4j.Logger;
import delicacy.system.bean.BaseProcessorTestResult;
import java.util.List;
import delicacy.system.dao.ProcessorTestResult;
import delicacy.connection.ThreadConnection;
import delicacy.common.GenericDao;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import java.io.StringReader;
import java.util.Map;
import delicacy.common.PaginationParameter;
import delicacy.common.BaseCollection;

public class ProcessorTestResultHandler implements GenericDao {

	private static final Logger __logger = Logger.getLogger(ProcessorTestResultHandler.class);

	public static BaseProcessorTestResult getProcessorTestResultById( 
		java.lang.Integer processor_test_result_id
	) throws Exception
	{
		ProcessorTestResult dao = new ProcessorTestResult();
		dao.setProcessorTestResultId(processor_test_result_id);
		if(dao.load()){
			return dao.generateBase();
		}
		return null;
	}

	public static boolean isProcessorTestResultExists( delicacy.system.bean.BaseProcessorTestResult bean, String additional ) throws Exception {

		ProcessorTestResult dao = new ProcessorTestResult();
		setConditions(bean, dao);
		boolean res = dao.isExist(additional);
		return res;
	}

	public static int countProcessorTestResult( delicacy.system.bean.BaseProcessorTestResult bean, String additional ) throws Exception {

		ProcessorTestResult dao = new ProcessorTestResult();
		setConditions(bean, dao);
		int res = dao.countRows(additional);
		return res;
	}

	public static BaseCollection<BaseProcessorTestResult> queryProcessorTestResult( delicacy.system.bean.BaseProcessorTestResult bean, int pageNo, int pageLines, String additionalCondition ) throws Exception {
		ProcessorTestResult dao = new ProcessorTestResult();
		setConditions(bean, dao);
		dao.setCurrentPage(pageNo);
		dao.setPageLines(pageLines);
		java.util.List<BaseProcessorTestResult> result = dao.conditionalLoad(additionalCondition);
		BaseCollection<BaseProcessorTestResult> col = new BaseCollection<>();
		col.setCollections(result);
		col.setTotalPages(dao.getTotalPages());
		col.setCurrentPage(dao.getCurrentPage());
		col.setPageLines(dao.getPageLines());
		col.setTotalLines(dao.getTotalLines());
		col.setRecordNumber(result.size());
		return col;
	}

	public static BaseProcessorTestResult addToProcessorTestResult ( BaseProcessorTestResult processortestresult )  throws Exception {
		return addToProcessorTestResult ( processortestresult , false);
	}

	public static BaseProcessorTestResult addToProcessorTestResult ( BaseProcessorTestResult processortestresult, boolean singleTransaction ) throws Exception {
		if(singleTransaction) ThreadConnection.beginTransaction();
		ProcessorTestResult dao = new ProcessorTestResult();
		dao.setDataFromBase(processortestresult);
		int result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static BaseProcessorTestResult addUpdateProcessorTestResult ( BaseProcessorTestResult processortestresult ) throws Exception {
		return addUpdateProcessorTestResult ( processortestresult , false);
	}

	public static BaseProcessorTestResult addUpdateProcessorTestResult ( BaseProcessorTestResult processortestresult, boolean singleTransaction  ) throws Exception {
		if(processortestresult.getProcessorTestResultId() == null) return addToProcessorTestResult(processortestresult);
		if(singleTransaction) ThreadConnection.beginTransaction();
		StringBuilder sb = new StringBuilder();
		ProcessorTestResult dao = new ProcessorTestResult();
		dao.setDataFromBase(processortestresult);
		int result = 0;
		if(dao.load()) {
			dao.setDataFromBase(processortestresult); 
			if(dao.isThisObjectModified()) { 
				result = dao.update();
			} else result = 1;
		} else result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static int deleteProcessorTestResult ( BaseProcessorTestResult bean ) throws Exception {
		ProcessorTestResult dao = new ProcessorTestResult();
		if(setDeleteConditions(bean, dao) == 0) return 0;
		int result = dao.conditionalDelete();
		return result ;
	}

	public static BaseProcessorTestResult updateProcessorTestResult ( BaseProcessorTestResult processortestresult ) throws Exception {
		ProcessorTestResult dao = new ProcessorTestResult();
		dao.setProcessorTestResultId( processortestresult.getProcessorTestResultId() );
		int result = 0;
		if( dao.load() ) {
			dao.setDataFromBase(processortestresult);
			result = dao.update();
		}
		return result == 1 ? processortestresult : null ;
	}

	public static BaseProcessorTestResult updateProcessorTestResultDirect( BaseProcessorTestResult processortestresult ) throws Exception {
		ProcessorTestResult dao = new ProcessorTestResult();
		int result = 0;
		dao.setDataFromBase(processortestresult);
		result = dao.update();
		return result == 1 ? processortestresult : null ;
	}

	public static int setDeleteConditions(BaseProcessorTestResult bean, ProcessorTestResult dao){
		int count = 0;
		boolean foundKey = false;
		if(bean.getProcessorTestResultId() != null) {
			dao.setConditionProcessorTestResultId("=", bean.getProcessorTestResultId());
			count++;
			foundKey = true;
		}
		if(!foundKey) {
			if(bean.getDrafter() != null) {
				dao.setConditionDrafter("=", bean.getDrafter());
				count++;
			}
			if(bean.getDepartmentId() != null) {
				dao.setConditionDepartmentId("=", bean.getDepartmentId());
				count++;
			}
			if(bean.getPlateId() != null) {
				dao.setConditionPlateId("=", bean.getPlateId());
				count++;
			}
			if(bean.getCompanyId() != null) {
				dao.setConditionCompanyId("=", bean.getCompanyId());
				count++;
			}
			if(bean.getProcessId() != null) {
				dao.setConditionProcessId("=", bean.getProcessId());
				count++;
			}
			if(bean.getTestData() != null) {
				dao.setConditionTestData("=", bean.getTestData());
				count++;
			}
			if(bean.getTestResult() != null) {
				dao.setConditionTestResult("=", bean.getTestResult());
				count++;
			}
			if(bean.getErrorMsg() != null) {
				dao.setConditionErrorMsg("=", bean.getErrorMsg());
				count++;
			}
			if(bean.getThreadTaskManageId() != null) {
				dao.setConditionThreadTaskManageId("=", bean.getThreadTaskManageId());
				count++;
			}
		}
		return count;
	}

	public static int setConditions(BaseProcessorTestResult bean, ProcessorTestResult dao){
		int count = 0;
		if(bean.getProcessorTestResultId() != null) {
			dao.setConditionProcessorTestResultId("=", bean.getProcessorTestResultId());
			count++;
		}
		if(bean.getDrafter() != null) {
			dao.setConditionDrafter("=", bean.getDrafter());
			count++;
		}
		if(bean.getDepartmentId() != null) {
			dao.setConditionDepartmentId("=", bean.getDepartmentId());
			count++;
		}
		if(bean.getPlateId() != null) {
			dao.setConditionPlateId("=", bean.getPlateId());
			count++;
		}
		if(bean.getCompanyId() != null) {
			dao.setConditionCompanyId("=", bean.getCompanyId());
			count++;
		}
		if(bean.getProcessId() != null) {
			dao.setConditionProcessId("=", bean.getProcessId());
			count++;
		}
		if(bean.getTestData() != null) {
			if(bean.getTestData().indexOf("%") >= 0)
				dao.setConditionTestData("like", bean.getTestData());
			else
				dao.setConditionTestData("=", bean.getTestData());
			count++;
		}
		if(bean.getTestResult() != null) {
			dao.setConditionTestResult("=", bean.getTestResult());
			count++;
		}
		if(bean.getErrorMsg() != null) {
			if(bean.getErrorMsg().indexOf("%") >= 0)
				dao.setConditionErrorMsg("like", bean.getErrorMsg());
			else
				dao.setConditionErrorMsg("=", bean.getErrorMsg());
			count++;
		}
		if(bean.getStartTime() != null) {
			dao.setConditionStartTime(">=", bean.getStartTime());
			count++;
		}
		if(bean.getEndTime() != null) {
			dao.setConditionEndTime(">=", bean.getEndTime());
			count++;
		}
		if(bean.getThreadTaskManageId() != null) {
			dao.setConditionThreadTaskManageId("=", bean.getThreadTaskManageId());
			count++;
		}
		return count;
	}

	@Override
	public String findUsingKey(String json) throws Exception{
		BaseProcessorTestResult bean = new BaseProcessorTestResult();
		bean.setDataFromJSON(json);
		ProcessorTestResult dao = new ProcessorTestResult();
		dao.setPrimaryKeyFromBase(bean);
		if(dao.load()) { dao.setDataToBase(bean); return bean.toOneLineJSON(1,null); }
		return bean.toOneLineJSON(0,"Record not found.");
	}

	@Override
	public String find(String json, int currentPage, int pageSize, String addtion) throws Exception{
		BaseCollection<BaseProcessorTestResult> rlist = new BaseCollection<>();
		BaseProcessorTestResult bean = new BaseProcessorTestResult();
		JSON parser = new JSON(new StringReader(json));
		Map<String, Object> params = (Map<String, Object>) parser.parse(new BasicHandler());
		bean.setDataFromMap(params);
		PaginationParameter pp = PaginationParameter.generateFromMap(params);
		if(pp != null){
			if(pp.getCurrentPage() > 0) currentPage = pp.getCurrentPage();
			if(pp.getPageSize() > 0) pageSize = pp.getPageSize();
			if(pp.getCondition() != null) addtion = pp.getCondition();
		}
		ProcessorTestResult dao = new ProcessorTestResult();
		setConditions(bean, dao);
		dao.setCurrentPage(currentPage);
		dao.setPageLines(pageSize);
		List<BaseProcessorTestResult> result = dao.conditionalLoad(addtion);
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
		BaseProcessorTestResult bean = new BaseProcessorTestResult();
		bean.setDataFromJSON(json);
		ProcessorTestResult dao = new ProcessorTestResult();
		dao.setDataFromBase(bean);
		int num = dao.save();
		dao.setDataToBase(bean);
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String update(String json) throws Exception{
		BaseProcessorTestResult bean = new BaseProcessorTestResult();
		bean.setDataFromJSON(json);
		ProcessorTestResult dao = new ProcessorTestResult();
		dao.setPrimaryKeyFromBase(bean);
		int num=0;
		if(dao.load()){dao.setDataFromBase(bean); num = dao.update(); }
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String delete(String json) throws Exception{
		BaseProcessorTestResult bean = new BaseProcessorTestResult();
		bean.setDataFromJSON(json);
		ProcessorTestResult dao = new ProcessorTestResult();
		setDeleteConditions(bean, dao);
		int num = dao.conditionalDelete();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String saveOrUpdate(String json) throws Exception{
		BaseProcessorTestResult bean = new BaseProcessorTestResult();
		bean.setDataFromJSON(json);
		ProcessorTestResult dao = new ProcessorTestResult();
		dao.setPrimaryKeyFromBase(bean);
		int ret = 0;
		if(dao.isPrimaryKeyNull()) { dao.setDataFromBase(bean); ret = dao.save(); bean = dao.generateBase(); }
		else if(dao.load()) { dao.setDataFromBase(bean); ret = dao.update(); bean = dao.generateBase(); }
		return bean.toOneLineJSON(ret, null);
	}

}


