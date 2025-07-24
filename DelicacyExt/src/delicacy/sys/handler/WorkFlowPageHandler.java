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
import delicacy.sys.bean.BaseWorkFlowPage;
import delicacy.sys.dao.WorkFlowPage;

public class WorkFlowPageHandler implements GenericDao {

	private static final Logger __logger = Logger.getLogger(WorkFlowPageHandler.class);

	public static BaseWorkFlowPage getWorkFlowPageById( 
		java.lang.Integer work_flow_page_id
	) throws Exception
	{
		WorkFlowPage dao = new WorkFlowPage();
		dao.setWorkFlowPageId(work_flow_page_id);
		if(dao.load()){
			return dao.generateBase();
		}
		return null;
	}

	public static boolean isWorkFlowPageExists(BaseWorkFlowPage bean, String additional ) throws Exception {

		WorkFlowPage dao = new WorkFlowPage();
		setConditions(bean, dao);
		boolean res = dao.isExist(additional);
		return res;
	}

	public static int countWorkFlowPage(BaseWorkFlowPage bean, String additional ) throws Exception {

		WorkFlowPage dao = new WorkFlowPage();
		setConditions(bean, dao);
		int res = dao.countRows(additional);
		return res;
	}

	public static BaseCollection<BaseWorkFlowPage> queryWorkFlowPage(BaseWorkFlowPage bean, int pageNo, int pageLines, String additionalCondition ) throws Exception {
		WorkFlowPage dao = new WorkFlowPage();
		setConditions(bean, dao);
		dao.setCurrentPage(pageNo);
		dao.setPageLines(pageLines);
		java.util.List<BaseWorkFlowPage> result = dao.conditionalLoad(additionalCondition);
		BaseCollection<BaseWorkFlowPage> col = new BaseCollection<>();
		col.setCollections(result);
		col.setTotalPages(dao.getTotalPages());
		col.setCurrentPage(dao.getCurrentPage());
		col.setPageLines(dao.getPageLines());
		col.setTotalLines(dao.getTotalLines());
		col.setRecordNumber(result.size());
		return col;
	}

	public static BaseWorkFlowPage addToWorkFlowPage ( BaseWorkFlowPage workflowpage )  throws Exception {
		return addToWorkFlowPage ( workflowpage , false);
	}

	public static BaseWorkFlowPage addToWorkFlowPage ( BaseWorkFlowPage workflowpage, boolean singleTransaction ) throws Exception {
		if(singleTransaction) ThreadConnection.beginTransaction();
		WorkFlowPage dao = new WorkFlowPage();
		dao.setDataFromBase(workflowpage);
		int result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static BaseWorkFlowPage addUpdateWorkFlowPage ( BaseWorkFlowPage workflowpage ) throws Exception {
		return addUpdateWorkFlowPage ( workflowpage , false);
	}

	public static BaseWorkFlowPage addUpdateWorkFlowPage ( BaseWorkFlowPage workflowpage, boolean singleTransaction  ) throws Exception {
		if(workflowpage.getWorkFlowPageId() == null) return addToWorkFlowPage(workflowpage);
		if(singleTransaction) ThreadConnection.beginTransaction();
		StringBuilder sb = new StringBuilder();
		WorkFlowPage dao = new WorkFlowPage();
		dao.setDataFromBase(workflowpage);
		int result = 0;
		if(dao.load()) {
			dao.setDataFromBase(workflowpage); 
			if(dao.isThisObjectModified()) { 
				result = dao.update();
			} else result = 1;
		} else result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static int deleteWorkFlowPage ( BaseWorkFlowPage bean ) throws Exception {
		WorkFlowPage dao = new WorkFlowPage();
		if(setDeleteConditions(bean, dao) == 0) return 0;
		int result = dao.conditionalDelete();
		return result ;
	}

	public static BaseWorkFlowPage updateWorkFlowPage ( BaseWorkFlowPage workflowpage ) throws Exception {
		WorkFlowPage dao = new WorkFlowPage();
		dao.setWorkFlowPageId( workflowpage.getWorkFlowPageId() );
		int result = 0;
		if( dao.load() ) {
			dao.setDataFromBase(workflowpage);
			result = dao.update();
		}
		return result == 1 ? workflowpage : null ;
	}

	public static BaseWorkFlowPage updateWorkFlowPageDirect( BaseWorkFlowPage workflowpage ) throws Exception {
		WorkFlowPage dao = new WorkFlowPage();
		int result = 0;
		dao.setDataFromBase(workflowpage);
		result = dao.update();
		return result == 1 ? workflowpage : null ;
	}

	public static int setDeleteConditions(BaseWorkFlowPage bean, WorkFlowPage dao){
		int count = 0;
		boolean foundKey = false;
		if(bean.getWorkFlowPageId() != null) {
			dao.setConditionWorkFlowPageId("=", bean.getWorkFlowPageId());
			count++;
			foundKey = true;
		}
		if(!foundKey) {
			if(bean.getExecuteName() != null) {
				dao.setConditionExecuteName("=", bean.getExecuteName());
				count++;
			}
			if(bean.getPageDirectory() != null) {
				dao.setConditionPageDirectory("=", bean.getPageDirectory());
				count++;
			}
			if(bean.getOrganizationId() != null) {
				dao.setConditionOrganizationId("=", bean.getOrganizationId());
				count++;
			}
		}
		return count;
	}

	public static int setConditions(BaseWorkFlowPage bean, WorkFlowPage dao){
		int count = 0;
		if(bean.getWorkFlowPageId() != null) {
			dao.setConditionWorkFlowPageId("=", bean.getWorkFlowPageId());
			count++;
		}
		if(bean.getExecuteName() != null) {
			if(bean.getExecuteName().indexOf("%") >= 0)
				dao.setConditionExecuteName("like", bean.getExecuteName());
			else
				dao.setConditionExecuteName("=", bean.getExecuteName());
			count++;
		}
		if(bean.getPageDirectory() != null) {
			if(bean.getPageDirectory().indexOf("%") >= 0)
				dao.setConditionPageDirectory("like", bean.getPageDirectory());
			else
				dao.setConditionPageDirectory("=", bean.getPageDirectory());
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
		BaseWorkFlowPage bean = new BaseWorkFlowPage();
		bean.setDataFromJSON(json);
		WorkFlowPage dao = new WorkFlowPage();
		dao.setPrimaryKeyFromBase(bean);
		if(dao.load()) { dao.setDataToBase(bean); return bean.toOneLineJSON(1,null); }
		return bean.toOneLineJSON(0,"Record not found.");
	}

	@Override
	public String find(String json, int currentPage, int pageSize, String addtion) throws Exception{
		BaseCollection<BaseWorkFlowPage> rlist = new BaseCollection<>();
		BaseWorkFlowPage bean = new BaseWorkFlowPage();
		JSON parser = new JSON(new StringReader(json));
		Map<String, Object> params = (Map<String, Object>) parser.parse(new BasicHandler());
		bean.setDataFromMap(params);
		PaginationParameter pp = PaginationParameter.generateFromMap(params);
		if(pp != null){
			if(pp.getCurrentPage() > 0) currentPage = pp.getCurrentPage();
			if(pp.getPageSize() > 0) pageSize = pp.getPageSize();
			if(pp.getCondition() != null) addtion = pp.getCondition();
		}
		WorkFlowPage dao = new WorkFlowPage();
		setConditions(bean, dao);
		dao.setCurrentPage(currentPage);
		dao.setPageLines(pageSize);
		List<BaseWorkFlowPage> result = dao.conditionalLoad(addtion);
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
		BaseWorkFlowPage bean = new BaseWorkFlowPage();
		bean.setDataFromJSON(json);
		WorkFlowPage dao = new WorkFlowPage();
		dao.setDataFromBase(bean);
		int num = dao.save();
		dao.setDataToBase(bean);
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String update(String json) throws Exception{
		BaseWorkFlowPage bean = new BaseWorkFlowPage();
		bean.setDataFromJSON(json);
		WorkFlowPage dao = new WorkFlowPage();
		dao.setPrimaryKeyFromBase(bean);
		int num=0;
		if(dao.load()){dao.setDataFromBase(bean); num = dao.update(); }
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String delete(String json) throws Exception{
		BaseWorkFlowPage bean = new BaseWorkFlowPage();
		bean.setDataFromJSON(json);
		WorkFlowPage dao = new WorkFlowPage();
		setDeleteConditions(bean, dao);
		int num = dao.conditionalDelete();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String saveOrUpdate(String json) throws Exception{
		BaseWorkFlowPage bean = new BaseWorkFlowPage();
		bean.setDataFromJSON(json);
		WorkFlowPage dao = new WorkFlowPage();
		dao.setPrimaryKeyFromBase(bean);
		int ret = 0;
		if(dao.isPrimaryKeyNull()) { dao.setDataFromBase(bean); ret = dao.save(); bean = dao.generateBase(); }
		else if(dao.load()) { dao.setDataFromBase(bean); ret = dao.update(); bean = dao.generateBase(); }
		return bean.toOneLineJSON(ret, null);
	}

}


