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
import delicacy.sys.bean.BaseSystemProcessLink;
import delicacy.sys.dao.SystemProcessLink;

public class SystemProcessLinkHandler implements GenericDao {

	private static final Logger __logger = Logger.getLogger(SystemProcessLinkHandler.class);

	public static BaseSystemProcessLink getSystemProcessLinkById( 
		java.lang.Integer process_link_id
	) throws Exception
	{
		SystemProcessLink dao = new SystemProcessLink();
		dao.setProcessLinkId(process_link_id);
		if(dao.load()){
			return dao.generateBase();
		}
		return null;
	}

	public static boolean isSystemProcessLinkExists(BaseSystemProcessLink bean, String additional ) throws Exception {

		SystemProcessLink dao = new SystemProcessLink();
		setConditions(bean, dao);
		boolean res = dao.isExist(additional);
		return res;
	}

	public static int countSystemProcessLink(BaseSystemProcessLink bean, String additional ) throws Exception {

		SystemProcessLink dao = new SystemProcessLink();
		setConditions(bean, dao);
		int res = dao.countRows(additional);
		return res;
	}

	public static BaseCollection<BaseSystemProcessLink> querySystemProcessLink(BaseSystemProcessLink bean, int pageNo, int pageLines, String additionalCondition ) throws Exception {
		SystemProcessLink dao = new SystemProcessLink();
		setConditions(bean, dao);
		dao.setCurrentPage(pageNo);
		dao.setPageLines(pageLines);
		java.util.List<BaseSystemProcessLink> result = dao.conditionalLoad(additionalCondition);
		BaseCollection<BaseSystemProcessLink> col = new BaseCollection<>();
		col.setCollections(result);
		col.setTotalPages(dao.getTotalPages());
		col.setCurrentPage(dao.getCurrentPage());
		col.setPageLines(dao.getPageLines());
		col.setTotalLines(dao.getTotalLines());
		col.setRecordNumber(result.size());
		return col;
	}

	public static BaseSystemProcessLink addToSystemProcessLink ( BaseSystemProcessLink systemprocesslink )  throws Exception {
		return addToSystemProcessLink ( systemprocesslink , false);
	}

	public static BaseSystemProcessLink addToSystemProcessLink ( BaseSystemProcessLink systemprocesslink, boolean singleTransaction ) throws Exception {
		if(singleTransaction) ThreadConnection.beginTransaction();
		SystemProcessLink dao = new SystemProcessLink();
		dao.setDataFromBase(systemprocesslink);
		int result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static BaseSystemProcessLink addUpdateSystemProcessLink ( BaseSystemProcessLink systemprocesslink ) throws Exception {
		return addUpdateSystemProcessLink ( systemprocesslink , false);
	}

	public static BaseSystemProcessLink addUpdateSystemProcessLink ( BaseSystemProcessLink systemprocesslink, boolean singleTransaction  ) throws Exception {
		if(systemprocesslink.getProcessLinkId() == null) return addToSystemProcessLink(systemprocesslink);
		if(singleTransaction) ThreadConnection.beginTransaction();
		StringBuilder sb = new StringBuilder();
		SystemProcessLink dao = new SystemProcessLink();
		dao.setDataFromBase(systemprocesslink);
		int result = 0;
		if(dao.load()) {
			dao.setDataFromBase(systemprocesslink); 
			if(dao.isThisObjectModified()) { 
				result = dao.update();
			} else result = 1;
		} else result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static int deleteSystemProcessLink ( BaseSystemProcessLink bean ) throws Exception {
		SystemProcessLink dao = new SystemProcessLink();
		if(setDeleteConditions(bean, dao) == 0) return 0;
		int result = dao.conditionalDelete();
		return result ;
	}

	public static BaseSystemProcessLink updateSystemProcessLink ( BaseSystemProcessLink systemprocesslink ) throws Exception {
		SystemProcessLink dao = new SystemProcessLink();
		dao.setProcessLinkId( systemprocesslink.getProcessLinkId() );
		int result = 0;
		if( dao.load() ) {
			dao.setDataFromBase(systemprocesslink);
			result = dao.update();
		}
		return result == 1 ? systemprocesslink : null ;
	}

	public static BaseSystemProcessLink updateSystemProcessLinkDirect( BaseSystemProcessLink systemprocesslink ) throws Exception {
		SystemProcessLink dao = new SystemProcessLink();
		int result = 0;
		dao.setDataFromBase(systemprocesslink);
		result = dao.update();
		return result == 1 ? systemprocesslink : null ;
	}

	public static int setDeleteConditions(BaseSystemProcessLink bean, SystemProcessLink dao){
		int count = 0;
		boolean foundKey = false;
		if(bean.getProcessLinkId() != null) {
			dao.setConditionProcessLinkId("=", bean.getProcessLinkId());
			count++;
			foundKey = true;
		}
		if(!foundKey) {
			if(bean.getProcessId() != null) {
				dao.setConditionProcessId("=", bean.getProcessId());
				count++;
			}
			if(bean.getCondition() != null) {
				dao.setConditionCondition("=", bean.getCondition());
				count++;
			}
			if(bean.getProcessActivityId() != null) {
				dao.setConditionProcessActivityId("=", bean.getProcessActivityId());
				count++;
			}
			if(bean.getToProcessActivityId() != null) {
				dao.setConditionToProcessActivityId("=", bean.getToProcessActivityId());
				count++;
			}
			if(bean.getOrganizationId() != null) {
				dao.setConditionOrganizationId("=", bean.getOrganizationId());
				count++;
			}
		}
		return count;
	}

	public static int setConditions(BaseSystemProcessLink bean, SystemProcessLink dao){
		int count = 0;
		if(bean.getProcessLinkId() != null) {
			dao.setConditionProcessLinkId("=", bean.getProcessLinkId());
			count++;
		}
		if(bean.getProcessId() != null) {
			dao.setConditionProcessId("=", bean.getProcessId());
			count++;
		}
		if(bean.getCondition() != null) {
			if(bean.getCondition().indexOf("%") >= 0)
				dao.setConditionCondition("like", bean.getCondition());
			else
				dao.setConditionCondition("=", bean.getCondition());
			count++;
		}
		if(bean.getProcessActivityId() != null) {
			dao.setConditionProcessActivityId("=", bean.getProcessActivityId());
			count++;
		}
		if(bean.getToProcessActivityId() != null) {
			dao.setConditionToProcessActivityId("=", bean.getToProcessActivityId());
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
		BaseSystemProcessLink bean = new BaseSystemProcessLink();
		bean.setDataFromJSON(json);
		SystemProcessLink dao = new SystemProcessLink();
		dao.setPrimaryKeyFromBase(bean);
		if(dao.load()) { dao.setDataToBase(bean); return bean.toOneLineJSON(1,null); }
		return bean.toOneLineJSON(0,"Record not found.");
	}

	@Override
	public String find(String json, int currentPage, int pageSize, String addtion) throws Exception{
		BaseCollection<BaseSystemProcessLink> rlist = new BaseCollection<>();
		BaseSystemProcessLink bean = new BaseSystemProcessLink();
		JSON parser = new JSON(new StringReader(json));
		Map<String, Object> params = (Map<String, Object>) parser.parse(new BasicHandler());
		bean.setDataFromMap(params);
		PaginationParameter pp = PaginationParameter.generateFromMap(params);
		if(pp != null){
			if(pp.getCurrentPage() > 0) currentPage = pp.getCurrentPage();
			if(pp.getPageSize() > 0) pageSize = pp.getPageSize();
			if(pp.getCondition() != null) addtion = pp.getCondition();
		}
		SystemProcessLink dao = new SystemProcessLink();
		setConditions(bean, dao);
		dao.setCurrentPage(currentPage);
		dao.setPageLines(pageSize);
		List<BaseSystemProcessLink> result = dao.conditionalLoad(addtion);
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
		BaseSystemProcessLink bean = new BaseSystemProcessLink();
		bean.setDataFromJSON(json);
		SystemProcessLink dao = new SystemProcessLink();
		dao.setDataFromBase(bean);
		int num = dao.save();
		dao.setDataToBase(bean);
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String update(String json) throws Exception{
		BaseSystemProcessLink bean = new BaseSystemProcessLink();
		bean.setDataFromJSON(json);
		SystemProcessLink dao = new SystemProcessLink();
		dao.setPrimaryKeyFromBase(bean);
		int num=0;
		if(dao.load()){dao.setDataFromBase(bean); num = dao.update(); }
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String delete(String json) throws Exception{
		BaseSystemProcessLink bean = new BaseSystemProcessLink();
		bean.setDataFromJSON(json);
		SystemProcessLink dao = new SystemProcessLink();
		setDeleteConditions(bean, dao);
		int num = dao.conditionalDelete();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String saveOrUpdate(String json) throws Exception{
		BaseSystemProcessLink bean = new BaseSystemProcessLink();
		bean.setDataFromJSON(json);
		SystemProcessLink dao = new SystemProcessLink();
		dao.setPrimaryKeyFromBase(bean);
		int ret = 0;
		if(dao.isPrimaryKeyNull()) { dao.setDataFromBase(bean); ret = dao.save(); bean = dao.generateBase(); }
		else if(dao.load()) { dao.setDataFromBase(bean); ret = dao.update(); bean = dao.generateBase(); }
		return bean.toOneLineJSON(ret, null);
	}

}


