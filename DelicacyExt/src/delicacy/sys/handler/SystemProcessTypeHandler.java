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
import delicacy.sys.bean.BaseSystemProcessType;
import delicacy.sys.dao.SystemProcessType;

public class SystemProcessTypeHandler implements GenericDao {

	private static final Logger __logger = Logger.getLogger(SystemProcessTypeHandler.class);

	public static BaseSystemProcessType getSystemProcessTypeById( 
		java.lang.Integer process_type_id
	) throws Exception
	{
		SystemProcessType dao = new SystemProcessType();
		dao.setProcessTypeId(process_type_id);
		if(dao.load()){
			return dao.generateBase();
		}
		return null;
	}

	public static boolean isSystemProcessTypeExists(BaseSystemProcessType bean, String additional ) throws Exception {

		SystemProcessType dao = new SystemProcessType();
		setConditions(bean, dao);
		boolean res = dao.isExist(additional);
		return res;
	}

	public static int countSystemProcessType(BaseSystemProcessType bean, String additional ) throws Exception {

		SystemProcessType dao = new SystemProcessType();
		setConditions(bean, dao);
		int res = dao.countRows(additional);
		return res;
	}

	public static BaseCollection<BaseSystemProcessType> querySystemProcessType(BaseSystemProcessType bean, int pageNo, int pageLines, String additionalCondition ) throws Exception {
		SystemProcessType dao = new SystemProcessType();
		setConditions(bean, dao);
		dao.setCurrentPage(pageNo);
		dao.setPageLines(pageLines);
		java.util.List<BaseSystemProcessType> result = dao.conditionalLoad(additionalCondition);
		BaseCollection<BaseSystemProcessType> col = new BaseCollection<>();
		col.setCollections(result);
		col.setTotalPages(dao.getTotalPages());
		col.setCurrentPage(dao.getCurrentPage());
		col.setPageLines(dao.getPageLines());
		col.setTotalLines(dao.getTotalLines());
		col.setRecordNumber(result.size());
		return col;
	}

	public static BaseSystemProcessType addToSystemProcessType ( BaseSystemProcessType systemprocesstype )  throws Exception {
		return addToSystemProcessType ( systemprocesstype , false);
	}

	public static BaseSystemProcessType addToSystemProcessType ( BaseSystemProcessType systemprocesstype, boolean singleTransaction ) throws Exception {
		if(singleTransaction) ThreadConnection.beginTransaction();
		SystemProcessType dao = new SystemProcessType();
		dao.setDataFromBase(systemprocesstype);
		int result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static BaseSystemProcessType addUpdateSystemProcessType ( BaseSystemProcessType systemprocesstype ) throws Exception {
		return addUpdateSystemProcessType ( systemprocesstype , false);
	}

	public static BaseSystemProcessType addUpdateSystemProcessType ( BaseSystemProcessType systemprocesstype, boolean singleTransaction  ) throws Exception {
		if(systemprocesstype.getProcessTypeId() == null) return addToSystemProcessType(systemprocesstype);
		if(singleTransaction) ThreadConnection.beginTransaction();
		StringBuilder sb = new StringBuilder();
		SystemProcessType dao = new SystemProcessType();
		dao.setDataFromBase(systemprocesstype);
		int result = 0;
		if(dao.load()) {
			dao.setDataFromBase(systemprocesstype); 
			if(dao.isThisObjectModified()) { 
				result = dao.update();
			} else result = 1;
		} else result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static int deleteSystemProcessType ( BaseSystemProcessType bean ) throws Exception {
		SystemProcessType dao = new SystemProcessType();
		if(setDeleteConditions(bean, dao) == 0) return 0;
		int result = dao.conditionalDelete();
		return result ;
	}

	public static BaseSystemProcessType updateSystemProcessType ( BaseSystemProcessType systemprocesstype ) throws Exception {
		SystemProcessType dao = new SystemProcessType();
		dao.setProcessTypeId( systemprocesstype.getProcessTypeId() );
		int result = 0;
		if( dao.load() ) {
			dao.setDataFromBase(systemprocesstype);
			result = dao.update();
		}
		return result == 1 ? systemprocesstype : null ;
	}

	public static BaseSystemProcessType updateSystemProcessTypeDirect( BaseSystemProcessType systemprocesstype ) throws Exception {
		SystemProcessType dao = new SystemProcessType();
		int result = 0;
		dao.setDataFromBase(systemprocesstype);
		result = dao.update();
		return result == 1 ? systemprocesstype : null ;
	}

	public static int setDeleteConditions(BaseSystemProcessType bean, SystemProcessType dao){
		int count = 0;
		boolean foundKey = false;
		if(bean.getProcessTypeId() != null) {
			dao.setConditionProcessTypeId("=", bean.getProcessTypeId());
			count++;
			foundKey = true;
		}
		if(!foundKey) {
			if(bean.getProcessTypeName() != null) {
				dao.setConditionProcessTypeName("=", bean.getProcessTypeName());
				count++;
			}
			if(bean.getDescription() != null) {
				dao.setConditionDescription("=", bean.getDescription());
				count++;
			}
			if(bean.getProcessExecuteName() != null) {
				dao.setConditionProcessExecuteName("=", bean.getProcessExecuteName());
				count++;
			}
			if(bean.getIsEnable() != null) {
				dao.setConditionIsEnable("=", bean.getIsEnable());
				count++;
			}
			if(bean.getParentProcessTypeId() != null) {
				dao.setConditionParentProcessTypeId("=", bean.getParentProcessTypeId());
				count++;
			}
			if(bean.getIcon() != null) {
				dao.setConditionIcon("=", bean.getIcon());
				count++;
			}
			if(bean.getIsAttendance() != null) {
				dao.setConditionIsAttendance("=", bean.getIsAttendance());
				count++;
			}
			if(bean.getOrganizationId() != null) {
				dao.setConditionOrganizationId("=", bean.getOrganizationId());
				count++;
			}
		}
		return count;
	}

	public static int setConditions(BaseSystemProcessType bean, SystemProcessType dao){
		int count = 0;
		if(bean.getProcessTypeId() != null) {
			dao.setConditionProcessTypeId("=", bean.getProcessTypeId());
			count++;
		}
		if(bean.getProcessTypeName() != null) {
			if(bean.getProcessTypeName().indexOf("%") >= 0)
				dao.setConditionProcessTypeName("like", bean.getProcessTypeName());
			else
				dao.setConditionProcessTypeName("=", bean.getProcessTypeName());
			count++;
		}
		if(bean.getDescription() != null) {
			if(bean.getDescription().indexOf("%") >= 0)
				dao.setConditionDescription("like", bean.getDescription());
			else
				dao.setConditionDescription("=", bean.getDescription());
			count++;
		}
		if(bean.getProcessExecuteName() != null) {
			if(bean.getProcessExecuteName().indexOf("%") >= 0)
				dao.setConditionProcessExecuteName("like", bean.getProcessExecuteName());
			else
				dao.setConditionProcessExecuteName("=", bean.getProcessExecuteName());
			count++;
		}
		if(bean.getIsEnable() != null) {
			dao.setConditionIsEnable("=", bean.getIsEnable());
			count++;
		}
		if(bean.getParentProcessTypeId() != null) {
			dao.setConditionParentProcessTypeId("=", bean.getParentProcessTypeId());
			count++;
		}
		if(bean.getIcon() != null) {
			if(bean.getIcon().indexOf("%") >= 0)
				dao.setConditionIcon("like", bean.getIcon());
			else
				dao.setConditionIcon("=", bean.getIcon());
			count++;
		}
		if(bean.getIsAttendance() != null) {
			dao.setConditionIsAttendance("=", bean.getIsAttendance());
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
		BaseSystemProcessType bean = new BaseSystemProcessType();
		bean.setDataFromJSON(json);
		SystemProcessType dao = new SystemProcessType();
		dao.setPrimaryKeyFromBase(bean);
		if(dao.load()) { dao.setDataToBase(bean); return bean.toOneLineJSON(1,null); }
		return bean.toOneLineJSON(0,"Record not found.");
	}

	@Override
	public String find(String json, int currentPage, int pageSize, String addtion) throws Exception{
		BaseCollection<BaseSystemProcessType> rlist = new BaseCollection<>();
		BaseSystemProcessType bean = new BaseSystemProcessType();
		JSON parser = new JSON(new StringReader(json));
		Map<String, Object> params = (Map<String, Object>) parser.parse(new BasicHandler());
		bean.setDataFromMap(params);
		PaginationParameter pp = PaginationParameter.generateFromMap(params);
		if(pp != null){
			if(pp.getCurrentPage() > 0) currentPage = pp.getCurrentPage();
			if(pp.getPageSize() > 0) pageSize = pp.getPageSize();
			if(pp.getCondition() != null) addtion = pp.getCondition();
		}
		SystemProcessType dao = new SystemProcessType();
		setConditions(bean, dao);
		dao.setCurrentPage(currentPage);
		dao.setPageLines(pageSize);
		List<BaseSystemProcessType> result = dao.conditionalLoad(addtion);
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
		BaseSystemProcessType bean = new BaseSystemProcessType();
		bean.setDataFromJSON(json);
		SystemProcessType dao = new SystemProcessType();
		dao.setDataFromBase(bean);
		int num = dao.save();
		dao.setDataToBase(bean);
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String update(String json) throws Exception{
		BaseSystemProcessType bean = new BaseSystemProcessType();
		bean.setDataFromJSON(json);
		SystemProcessType dao = new SystemProcessType();
		dao.setPrimaryKeyFromBase(bean);
		int num=0;
		if(dao.load()){dao.setDataFromBase(bean); num = dao.update(); }
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String delete(String json) throws Exception{
		BaseSystemProcessType bean = new BaseSystemProcessType();
		bean.setDataFromJSON(json);
		SystemProcessType dao = new SystemProcessType();
		setDeleteConditions(bean, dao);
		int num = dao.conditionalDelete();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String saveOrUpdate(String json) throws Exception{
		BaseSystemProcessType bean = new BaseSystemProcessType();
		bean.setDataFromJSON(json);
		SystemProcessType dao = new SystemProcessType();
		dao.setPrimaryKeyFromBase(bean);
		int ret = 0;
		if(dao.isPrimaryKeyNull()) { dao.setDataFromBase(bean); ret = dao.save(); bean = dao.generateBase(); }
		else if(dao.load()) { dao.setDataFromBase(bean); ret = dao.update(); bean = dao.generateBase(); }
		return bean.toOneLineJSON(ret, null);
	}

}


