package delicacy.system.handler;

import org.apache.log4j.Logger;
import delicacy.system.bean.BaseSystemProcessDepartment;
import java.util.List;
import delicacy.system.dao.SystemProcessDepartment;
import delicacy.connection.ThreadConnection;
import delicacy.common.GenericDao;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import java.io.StringReader;
import java.util.Map;
import delicacy.common.PaginationParameter;
import delicacy.common.BaseCollection;

public class SystemProcessDepartmentHandler implements GenericDao {

	private static final Logger __logger = Logger.getLogger(SystemProcessDepartmentHandler.class);

	public static BaseSystemProcessDepartment getSystemProcessDepartmentById( 
		java.lang.Integer process_department_id
	) throws Exception
	{
		SystemProcessDepartment dao = new SystemProcessDepartment();
		dao.setProcessDepartmentId(process_department_id);
		if(dao.load()){
			return dao.generateBase();
		}
		return null;
	}

	public static boolean isSystemProcessDepartmentExists( delicacy.system.bean.BaseSystemProcessDepartment bean, String additional ) throws Exception {

		SystemProcessDepartment dao = new SystemProcessDepartment();
		setConditions(bean, dao);
		boolean res = dao.isExist(additional);
		return res;
	}

	public static int countSystemProcessDepartment( delicacy.system.bean.BaseSystemProcessDepartment bean, String additional ) throws Exception {

		SystemProcessDepartment dao = new SystemProcessDepartment();
		setConditions(bean, dao);
		int res = dao.countRows(additional);
		return res;
	}

	public static BaseCollection<BaseSystemProcessDepartment> querySystemProcessDepartment( delicacy.system.bean.BaseSystemProcessDepartment bean, int pageNo, int pageLines, String additionalCondition ) throws Exception {
		SystemProcessDepartment dao = new SystemProcessDepartment();
		setConditions(bean, dao);
		dao.setCurrentPage(pageNo);
		dao.setPageLines(pageLines);
		java.util.List<BaseSystemProcessDepartment> result = dao.conditionalLoad(additionalCondition);
		BaseCollection<BaseSystemProcessDepartment> col = new BaseCollection<>();
		col.setCollections(result);
		col.setTotalPages(dao.getTotalPages());
		col.setCurrentPage(dao.getCurrentPage());
		col.setPageLines(dao.getPageLines());
		col.setTotalLines(dao.getTotalLines());
		col.setRecordNumber(result.size());
		return col;
	}

	public static BaseSystemProcessDepartment addToSystemProcessDepartment ( BaseSystemProcessDepartment systemprocessdepartment )  throws Exception {
		return addToSystemProcessDepartment ( systemprocessdepartment , false);
	}

	public static BaseSystemProcessDepartment addToSystemProcessDepartment ( BaseSystemProcessDepartment systemprocessdepartment, boolean singleTransaction ) throws Exception {
		if(singleTransaction) ThreadConnection.beginTransaction();
		SystemProcessDepartment dao = new SystemProcessDepartment();
		dao.setDataFromBase(systemprocessdepartment);
		int result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static BaseSystemProcessDepartment addUpdateSystemProcessDepartment ( BaseSystemProcessDepartment systemprocessdepartment ) throws Exception {
		return addUpdateSystemProcessDepartment ( systemprocessdepartment , false);
	}

	public static BaseSystemProcessDepartment addUpdateSystemProcessDepartment ( BaseSystemProcessDepartment systemprocessdepartment, boolean singleTransaction  ) throws Exception {
		if(systemprocessdepartment.getProcessDepartmentId() == null) return addToSystemProcessDepartment(systemprocessdepartment);
		if(singleTransaction) ThreadConnection.beginTransaction();
		StringBuilder sb = new StringBuilder();
		SystemProcessDepartment dao = new SystemProcessDepartment();
		dao.setDataFromBase(systemprocessdepartment);
		int result = 0;
		if(dao.load()) {
			dao.setDataFromBase(systemprocessdepartment); 
			if(dao.isThisObjectModified()) { 
				result = dao.update();
			} else result = 1;
		} else result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static int deleteSystemProcessDepartment ( BaseSystemProcessDepartment bean ) throws Exception {
		SystemProcessDepartment dao = new SystemProcessDepartment();
		if(setDeleteConditions(bean, dao) == 0) return 0;
		int result = dao.conditionalDelete();
		return result ;
	}

	public static BaseSystemProcessDepartment updateSystemProcessDepartment ( BaseSystemProcessDepartment systemprocessdepartment ) throws Exception {
		SystemProcessDepartment dao = new SystemProcessDepartment();
		dao.setProcessDepartmentId( systemprocessdepartment.getProcessDepartmentId() );
		int result = 0;
		if( dao.load() ) {
			dao.setDataFromBase(systemprocessdepartment);
			result = dao.update();
		}
		return result == 1 ? systemprocessdepartment : null ;
	}

	public static BaseSystemProcessDepartment updateSystemProcessDepartmentDirect( BaseSystemProcessDepartment systemprocessdepartment ) throws Exception {
		SystemProcessDepartment dao = new SystemProcessDepartment();
		int result = 0;
		dao.setDataFromBase(systemprocessdepartment);
		result = dao.update();
		return result == 1 ? systemprocessdepartment : null ;
	}

	public static int setDeleteConditions(BaseSystemProcessDepartment bean, SystemProcessDepartment dao){
		int count = 0;
		boolean foundKey = false;
		if(bean.getProcessDepartmentId() != null) {
			dao.setConditionProcessDepartmentId("=", bean.getProcessDepartmentId());
			count++;
			foundKey = true;
		}
		if(!foundKey) {
			if(bean.getProcessId() != null) {
				dao.setConditionProcessId("=", bean.getProcessId());
				count++;
			}
			if(bean.getDepartmentId() != null) {
				dao.setConditionDepartmentId("=", bean.getDepartmentId());
				count++;
			}
			if(bean.getIsEnable() != null) {
				dao.setConditionIsEnable("=", bean.getIsEnable());
				count++;
			}
		}
		return count;
	}

	public static int setConditions(BaseSystemProcessDepartment bean, SystemProcessDepartment dao){
		int count = 0;
		if(bean.getProcessDepartmentId() != null) {
			dao.setConditionProcessDepartmentId("=", bean.getProcessDepartmentId());
			count++;
		}
		if(bean.getProcessId() != null) {
			dao.setConditionProcessId("=", bean.getProcessId());
			count++;
		}
		if(bean.getDepartmentId() != null) {
			dao.setConditionDepartmentId("=", bean.getDepartmentId());
			count++;
		}
		if(bean.getIsEnable() != null) {
			dao.setConditionIsEnable("=", bean.getIsEnable());
			count++;
		}
		return count;
	}

	@Override
	public String findUsingKey(String json) throws Exception{
		BaseSystemProcessDepartment bean = new BaseSystemProcessDepartment();
		bean.setDataFromJSON(json);
		SystemProcessDepartment dao = new SystemProcessDepartment();
		dao.setPrimaryKeyFromBase(bean);
		if(dao.load()) { dao.setDataToBase(bean); return bean.toOneLineJSON(1,null); }
		return bean.toOneLineJSON(0,"Record not found.");
	}

	@Override
	public String find(String json, int currentPage, int pageSize, String addtion) throws Exception{
		BaseCollection<BaseSystemProcessDepartment> rlist = new BaseCollection<>();
		BaseSystemProcessDepartment bean = new BaseSystemProcessDepartment();
		JSON parser = new JSON(new StringReader(json));
		Map<String, Object> params = (Map<String, Object>) parser.parse(new BasicHandler());
		bean.setDataFromMap(params);
		PaginationParameter pp = PaginationParameter.generateFromMap(params);
		if(pp != null){
			if(pp.getCurrentPage() > 0) currentPage = pp.getCurrentPage();
			if(pp.getPageSize() > 0) pageSize = pp.getPageSize();
			if(pp.getCondition() != null) addtion = pp.getCondition();
		}
		SystemProcessDepartment dao = new SystemProcessDepartment();
		setConditions(bean, dao);
		dao.setCurrentPage(currentPage);
		dao.setPageLines(pageSize);
		List<BaseSystemProcessDepartment> result = dao.conditionalLoad(addtion);
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
		BaseSystemProcessDepartment bean = new BaseSystemProcessDepartment();
		bean.setDataFromJSON(json);
		SystemProcessDepartment dao = new SystemProcessDepartment();
		dao.setDataFromBase(bean);
		int num = dao.save();
		dao.setDataToBase(bean);
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String update(String json) throws Exception{
		BaseSystemProcessDepartment bean = new BaseSystemProcessDepartment();
		bean.setDataFromJSON(json);
		SystemProcessDepartment dao = new SystemProcessDepartment();
		dao.setPrimaryKeyFromBase(bean);
		int num=0;
		if(dao.load()){dao.setDataFromBase(bean); num = dao.update(); }
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String delete(String json) throws Exception{
		BaseSystemProcessDepartment bean = new BaseSystemProcessDepartment();
		bean.setDataFromJSON(json);
		SystemProcessDepartment dao = new SystemProcessDepartment();
		setDeleteConditions(bean, dao);
		int num = dao.conditionalDelete();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String saveOrUpdate(String json) throws Exception{
		BaseSystemProcessDepartment bean = new BaseSystemProcessDepartment();
		bean.setDataFromJSON(json);
		SystemProcessDepartment dao = new SystemProcessDepartment();
		dao.setPrimaryKeyFromBase(bean);
		int ret = 0;
		if(dao.isPrimaryKeyNull()) { dao.setDataFromBase(bean); ret = dao.save(); bean = dao.generateBase(); }
		else if(dao.load()) { dao.setDataFromBase(bean); ret = dao.update(); bean = dao.generateBase(); }
		return bean.toOneLineJSON(ret, null);
	}

}


