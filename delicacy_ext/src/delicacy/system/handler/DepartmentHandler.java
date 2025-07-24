package delicacy.system.handler;

import org.apache.log4j.Logger;
import delicacy.system.bean.BaseDepartment;
import java.util.List;
import delicacy.system.dao.Department;
import delicacy.connection.ThreadConnection;
import delicacy.common.GenericDao;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import java.io.StringReader;
import java.util.Map;
import delicacy.common.PaginationParameter;
import delicacy.common.BaseCollection;

public class DepartmentHandler implements GenericDao {

	private static final Logger __logger = Logger.getLogger(DepartmentHandler.class);

	public static BaseDepartment getDepartmentById( 
		java.lang.Integer department_id
	) throws Exception
	{
		Department dao = new Department();
		dao.setDepartmentId(department_id);
		if(dao.load()){
			return dao.generateBase();
		}
		return null;
	}

	public static boolean isDepartmentExists( delicacy.system.bean.BaseDepartment bean, String additional ) throws Exception {

		Department dao = new Department();
		setConditions(bean, dao);
		boolean res = dao.isExist(additional);
		return res;
	}

	public static int countDepartment( delicacy.system.bean.BaseDepartment bean, String additional ) throws Exception {

		Department dao = new Department();
		setConditions(bean, dao);
		int res = dao.countRows(additional);
		return res;
	}

	public static BaseCollection<BaseDepartment> queryDepartment( delicacy.system.bean.BaseDepartment bean, int pageNo, int pageLines, String additionalCondition ) throws Exception {
		Department dao = new Department();
		setConditions(bean, dao);
		dao.setCurrentPage(pageNo);
		dao.setPageLines(pageLines);
		java.util.List<BaseDepartment> result = dao.conditionalLoad(additionalCondition);
		BaseCollection<BaseDepartment> col = new BaseCollection<>();
		col.setCollections(result);
		col.setTotalPages(dao.getTotalPages());
		col.setCurrentPage(dao.getCurrentPage());
		col.setPageLines(dao.getPageLines());
		col.setTotalLines(dao.getTotalLines());
		col.setRecordNumber(result.size());
		return col;
	}

	public static BaseDepartment addToDepartment ( BaseDepartment department )  throws Exception {
		return addToDepartment ( department , false);
	}

	public static BaseDepartment addToDepartment ( BaseDepartment department, boolean singleTransaction ) throws Exception {
		if(singleTransaction) ThreadConnection.beginTransaction();
		Department dao = new Department();
		dao.setDataFromBase(department);
		int result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static BaseDepartment addUpdateDepartment ( BaseDepartment department ) throws Exception {
		return addUpdateDepartment ( department , false);
	}

	public static BaseDepartment addUpdateDepartment ( BaseDepartment department, boolean singleTransaction  ) throws Exception {
		if(department.getDepartmentId() == null) return addToDepartment(department);
		if(singleTransaction) ThreadConnection.beginTransaction();
		StringBuilder sb = new StringBuilder();
		Department dao = new Department();
		dao.setDataFromBase(department);
		int result = 0;
		if(dao.load()) {
			dao.setDataFromBase(department); 
			if(dao.isThisObjectModified()) { 
				result = dao.update();
			} else result = 1;
		} else result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static int deleteDepartment ( BaseDepartment bean ) throws Exception {
		Department dao = new Department();
		if(setDeleteConditions(bean, dao) == 0) return 0;
		int result = dao.conditionalDelete();
		return result ;
	}

	public static BaseDepartment updateDepartment ( BaseDepartment department ) throws Exception {
		Department dao = new Department();
		dao.setDepartmentId( department.getDepartmentId() );
		int result = 0;
		if( dao.load() ) {
			dao.setDataFromBase(department);
			result = dao.update();
		}
		return result == 1 ? department : null ;
	}

	public static BaseDepartment updateDepartmentDirect( BaseDepartment department ) throws Exception {
		Department dao = new Department();
		int result = 0;
		dao.setDataFromBase(department);
		result = dao.update();
		return result == 1 ? department : null ;
	}

	public static int setDeleteConditions(BaseDepartment bean, Department dao){
		int count = 0;
		boolean foundKey = false;
		if(bean.getDepartmentId() != null) {
			dao.setConditionDepartmentId("=", bean.getDepartmentId());
			count++;
			foundKey = true;
		}
		if(!foundKey) {
			if(bean.getDepartmentName() != null) {
				dao.setConditionDepartmentName("=", bean.getDepartmentName());
				count++;
			}
			if(bean.getAbbreviation() != null) {
				dao.setConditionAbbreviation("=", bean.getAbbreviation());
				count++;
			}
			if(bean.getManagerId() != null) {
				dao.setConditionManagerId("=", bean.getManagerId());
				count++;
			}
			if(bean.getManagerName() != null) {
				dao.setConditionManagerName("=", bean.getManagerName());
				count++;
			}
			if(bean.getParentId() != null) {
				dao.setConditionParentId("=", bean.getParentId());
				count++;
			}
			if(bean.getEnabled() != null) {
				dao.setConditionEnabled("=", bean.getEnabled());
				count++;
			}
			if(bean.getOriginalId() != null) {
				dao.setConditionOriginalId("=", bean.getOriginalId());
				count++;
			}
			if(bean.getPlateId() != null) {
				dao.setConditionPlateId("=", bean.getPlateId());
				count++;
			}
			if(bean.getIsHeadcount() != null) {
				dao.setConditionIsHeadcount("=", bean.getIsHeadcount());
				count++;
			}
			if(bean.getDepartmentType() != null) {
				dao.setConditionDepartmentType("=", bean.getDepartmentType());
				count++;
			}
			if(bean.getWeixinDepartmentId() != null) {
				dao.setConditionWeixinDepartmentId("=", bean.getWeixinDepartmentId());
				count++;
			}
			if(bean.getEmailDepartmentId() != null) {
				dao.setConditionEmailDepartmentId("=", bean.getEmailDepartmentId());
				count++;
			}
			if(bean.getEcmcDepartmentId() != null) {
				dao.setConditionEcmcDepartmentId("=", bean.getEcmcDepartmentId());
				count++;
			}
			if(bean.getDeleteFlag() != null) {
				dao.setConditionDeleteFlag("=", bean.getDeleteFlag());
				count++;
			}
			if(bean.getIsEnable() != null) {
				dao.setConditionIsEnable("=", bean.getIsEnable());
				count++;
			}
		}
		return count;
	}

	public static int setConditions(BaseDepartment bean, Department dao){
		int count = 0;
		if(bean.getDepartmentId() != null) {
			dao.setConditionDepartmentId("=", bean.getDepartmentId());
			count++;
		}
		if(bean.getDepartmentName() != null) {
			if(bean.getDepartmentName().indexOf("%") >= 0)
				dao.setConditionDepartmentName("like", bean.getDepartmentName());
			else
				dao.setConditionDepartmentName("=", bean.getDepartmentName());
			count++;
		}
		if(bean.getAbbreviation() != null) {
			if(bean.getAbbreviation().indexOf("%") >= 0)
				dao.setConditionAbbreviation("like", bean.getAbbreviation());
			else
				dao.setConditionAbbreviation("=", bean.getAbbreviation());
			count++;
		}
		if(bean.getManagerId() != null) {
			dao.setConditionManagerId("=", bean.getManagerId());
			count++;
		}
		if(bean.getManagerName() != null) {
			if(bean.getManagerName().indexOf("%") >= 0)
				dao.setConditionManagerName("like", bean.getManagerName());
			else
				dao.setConditionManagerName("=", bean.getManagerName());
			count++;
		}
		if(bean.getParentId() != null) {
			dao.setConditionParentId("=", bean.getParentId());
			count++;
		}
		if(bean.getEnabled() != null) {
			dao.setConditionEnabled("=", bean.getEnabled());
			count++;
		}
		if(bean.getOriginalId() != null) {
			dao.setConditionOriginalId("=", bean.getOriginalId());
			count++;
		}
		if(bean.getPlateId() != null) {
			dao.setConditionPlateId("=", bean.getPlateId());
			count++;
		}
		if(bean.getIsHeadcount() != null) {
			dao.setConditionIsHeadcount("=", bean.getIsHeadcount());
			count++;
		}
		if(bean.getDepartmentType() != null) {
			dao.setConditionDepartmentType("=", bean.getDepartmentType());
			count++;
		}
		if(bean.getWeixinDepartmentId() != null) {
			dao.setConditionWeixinDepartmentId("=", bean.getWeixinDepartmentId());
			count++;
		}
		if(bean.getEmailDepartmentId() != null) {
			dao.setConditionEmailDepartmentId("=", bean.getEmailDepartmentId());
			count++;
		}
		if(bean.getEcmcDepartmentId() != null) {
			dao.setConditionEcmcDepartmentId("=", bean.getEcmcDepartmentId());
			count++;
		}
		if(bean.getDeleteFlag() != null) {
			dao.setConditionDeleteFlag("=", bean.getDeleteFlag());
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
		BaseDepartment bean = new BaseDepartment();
		bean.setDataFromJSON(json);
		Department dao = new Department();
		dao.setPrimaryKeyFromBase(bean);
		if(dao.load()) { dao.setDataToBase(bean); return bean.toOneLineJSON(1,null); }
		return bean.toOneLineJSON(0,"Record not found.");
	}

	@Override
	public String find(String json, int currentPage, int pageSize, String addtion) throws Exception{
		BaseCollection<BaseDepartment> rlist = new BaseCollection<>();
		BaseDepartment bean = new BaseDepartment();
		JSON parser = new JSON(new StringReader(json));
		Map<String, Object> params = (Map<String, Object>) parser.parse(new BasicHandler());
		bean.setDataFromMap(params);
		PaginationParameter pp = PaginationParameter.generateFromMap(params);
		if(pp != null){
			if(pp.getCurrentPage() > 0) currentPage = pp.getCurrentPage();
			if(pp.getPageSize() > 0) pageSize = pp.getPageSize();
			if(pp.getCondition() != null) addtion = pp.getCondition();
		}
		Department dao = new Department();
		setConditions(bean, dao);
		dao.setCurrentPage(currentPage);
		dao.setPageLines(pageSize);
		List<BaseDepartment> result = dao.conditionalLoad(addtion);
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
		BaseDepartment bean = new BaseDepartment();
		bean.setDataFromJSON(json);
		Department dao = new Department();
		dao.setDataFromBase(bean);
		int num = dao.save();
		dao.setDataToBase(bean);
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String update(String json) throws Exception{
		BaseDepartment bean = new BaseDepartment();
		bean.setDataFromJSON(json);
		Department dao = new Department();
		dao.setPrimaryKeyFromBase(bean);
		int num=0;
		if(dao.load()){dao.setDataFromBase(bean); num = dao.update(); }
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String delete(String json) throws Exception{
		BaseDepartment bean = new BaseDepartment();
		bean.setDataFromJSON(json);
		Department dao = new Department();
		setDeleteConditions(bean, dao);
		int num = dao.conditionalDelete();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String saveOrUpdate(String json) throws Exception{
		BaseDepartment bean = new BaseDepartment();
		bean.setDataFromJSON(json);
		Department dao = new Department();
		dao.setPrimaryKeyFromBase(bean);
		int ret = 0;
		if(dao.isPrimaryKeyNull()) { dao.setDataFromBase(bean); ret = dao.save(); bean = dao.generateBase(); }
		else if(dao.load()) { dao.setDataFromBase(bean); ret = dao.update(); bean = dao.generateBase(); }
		return bean.toOneLineJSON(ret, null);
	}

}


