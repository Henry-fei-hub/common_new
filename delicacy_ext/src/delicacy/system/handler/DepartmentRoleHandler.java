package delicacy.system.handler;

import org.apache.log4j.Logger;
import delicacy.system.bean.BaseDepartmentRole;
import java.util.List;
import delicacy.system.dao.DepartmentRole;
import delicacy.connection.ThreadConnection;
import delicacy.common.GenericDao;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import java.io.StringReader;
import java.util.Map;
import delicacy.common.PaginationParameter;
import delicacy.common.BaseCollection;

public class DepartmentRoleHandler implements GenericDao {

	private static final Logger __logger = Logger.getLogger(DepartmentRoleHandler.class);

	public static BaseDepartmentRole getDepartmentRoleById( 
		java.lang.Integer department_role_id
	) throws Exception
	{
		DepartmentRole dao = new DepartmentRole();
		dao.setDepartmentRoleId(department_role_id);
		if(dao.load()){
			return dao.generateBase();
		}
		return null;
	}

	public static boolean isDepartmentRoleExists( delicacy.system.bean.BaseDepartmentRole bean, String additional ) throws Exception {

		DepartmentRole dao = new DepartmentRole();
		setConditions(bean, dao);
		boolean res = dao.isExist(additional);
		return res;
	}

	public static int countDepartmentRole( delicacy.system.bean.BaseDepartmentRole bean, String additional ) throws Exception {

		DepartmentRole dao = new DepartmentRole();
		setConditions(bean, dao);
		int res = dao.countRows(additional);
		return res;
	}

	public static BaseCollection<BaseDepartmentRole> queryDepartmentRole( delicacy.system.bean.BaseDepartmentRole bean, int pageNo, int pageLines, String additionalCondition ) throws Exception {
		DepartmentRole dao = new DepartmentRole();
		setConditions(bean, dao);
		dao.setCurrentPage(pageNo);
		dao.setPageLines(pageLines);
		java.util.List<BaseDepartmentRole> result = dao.conditionalLoad(additionalCondition);
		BaseCollection<BaseDepartmentRole> col = new BaseCollection<>();
		col.setCollections(result);
		col.setTotalPages(dao.getTotalPages());
		col.setCurrentPage(dao.getCurrentPage());
		col.setPageLines(dao.getPageLines());
		col.setTotalLines(dao.getTotalLines());
		col.setRecordNumber(result.size());
		return col;
	}

	public static BaseDepartmentRole addToDepartmentRole ( BaseDepartmentRole departmentrole )  throws Exception {
		return addToDepartmentRole ( departmentrole , false);
	}

	public static BaseDepartmentRole addToDepartmentRole ( BaseDepartmentRole departmentrole, boolean singleTransaction ) throws Exception {
		if(singleTransaction) ThreadConnection.beginTransaction();
		DepartmentRole dao = new DepartmentRole();
		dao.setDataFromBase(departmentrole);
		int result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static BaseDepartmentRole addUpdateDepartmentRole ( BaseDepartmentRole departmentrole ) throws Exception {
		return addUpdateDepartmentRole ( departmentrole , false);
	}

	public static BaseDepartmentRole addUpdateDepartmentRole ( BaseDepartmentRole departmentrole, boolean singleTransaction  ) throws Exception {
		if(departmentrole.getDepartmentRoleId() == null) return addToDepartmentRole(departmentrole);
		if(singleTransaction) ThreadConnection.beginTransaction();
		StringBuilder sb = new StringBuilder();
		DepartmentRole dao = new DepartmentRole();
		dao.setDataFromBase(departmentrole);
		int result = 0;
		if(dao.load()) {
			dao.setDataFromBase(departmentrole); 
			if(dao.isThisObjectModified()) { 
				result = dao.update();
			} else result = 1;
		} else result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static int deleteDepartmentRole ( BaseDepartmentRole bean ) throws Exception {
		DepartmentRole dao = new DepartmentRole();
		if(setDeleteConditions(bean, dao) == 0) return 0;
		int result = dao.conditionalDelete();
		return result ;
	}

	public static BaseDepartmentRole updateDepartmentRole ( BaseDepartmentRole departmentrole ) throws Exception {
		DepartmentRole dao = new DepartmentRole();
		dao.setDepartmentRoleId( departmentrole.getDepartmentRoleId() );
		int result = 0;
		if( dao.load() ) {
			dao.setDataFromBase(departmentrole);
			result = dao.update();
		}
		return result == 1 ? departmentrole : null ;
	}

	public static BaseDepartmentRole updateDepartmentRoleDirect( BaseDepartmentRole departmentrole ) throws Exception {
		DepartmentRole dao = new DepartmentRole();
		int result = 0;
		dao.setDataFromBase(departmentrole);
		result = dao.update();
		return result == 1 ? departmentrole : null ;
	}

	public static int setDeleteConditions(BaseDepartmentRole bean, DepartmentRole dao){
		int count = 0;
		boolean foundKey = false;
		if(bean.getDepartmentRoleId() != null) {
			dao.setConditionDepartmentRoleId("=", bean.getDepartmentRoleId());
			count++;
			foundKey = true;
		}
		if(!foundKey) {
			if(bean.getDepartmentId() != null) {
				dao.setConditionDepartmentId("=", bean.getDepartmentId());
				count++;
			}
			if(bean.getRoleId() != null) {
				dao.setConditionRoleId("=", bean.getRoleId());
				count++;
			}
		}
		return count;
	}

	public static int setConditions(BaseDepartmentRole bean, DepartmentRole dao){
		int count = 0;
		if(bean.getDepartmentRoleId() != null) {
			dao.setConditionDepartmentRoleId("=", bean.getDepartmentRoleId());
			count++;
		}
		if(bean.getDepartmentId() != null) {
			dao.setConditionDepartmentId("=", bean.getDepartmentId());
			count++;
		}
		if(bean.getRoleId() != null) {
			dao.setConditionRoleId("=", bean.getRoleId());
			count++;
		}
		return count;
	}

	@Override
	public String findUsingKey(String json) throws Exception{
		BaseDepartmentRole bean = new BaseDepartmentRole();
		bean.setDataFromJSON(json);
		DepartmentRole dao = new DepartmentRole();
		dao.setPrimaryKeyFromBase(bean);
		if(dao.load()) { dao.setDataToBase(bean); return bean.toOneLineJSON(1,null); }
		return bean.toOneLineJSON(0,"Record not found.");
	}

	@Override
	public String find(String json, int currentPage, int pageSize, String addtion) throws Exception{
		BaseCollection<BaseDepartmentRole> rlist = new BaseCollection<>();
		BaseDepartmentRole bean = new BaseDepartmentRole();
		JSON parser = new JSON(new StringReader(json));
		Map<String, Object> params = (Map<String, Object>) parser.parse(new BasicHandler());
		bean.setDataFromMap(params);
		PaginationParameter pp = PaginationParameter.generateFromMap(params);
		if(pp != null){
			if(pp.getCurrentPage() > 0) currentPage = pp.getCurrentPage();
			if(pp.getPageSize() > 0) pageSize = pp.getPageSize();
			if(pp.getCondition() != null) addtion = pp.getCondition();
		}
		DepartmentRole dao = new DepartmentRole();
		setConditions(bean, dao);
		dao.setCurrentPage(currentPage);
		dao.setPageLines(pageSize);
		List<BaseDepartmentRole> result = dao.conditionalLoad(addtion);
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
		BaseDepartmentRole bean = new BaseDepartmentRole();
		bean.setDataFromJSON(json);
		DepartmentRole dao = new DepartmentRole();
		dao.setDataFromBase(bean);
		int num = dao.save();
		dao.setDataToBase(bean);
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String update(String json) throws Exception{
		BaseDepartmentRole bean = new BaseDepartmentRole();
		bean.setDataFromJSON(json);
		DepartmentRole dao = new DepartmentRole();
		dao.setPrimaryKeyFromBase(bean);
		int num=0;
		if(dao.load()){dao.setDataFromBase(bean); num = dao.update(); }
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String delete(String json) throws Exception{
		BaseDepartmentRole bean = new BaseDepartmentRole();
		bean.setDataFromJSON(json);
		DepartmentRole dao = new DepartmentRole();
		setDeleteConditions(bean, dao);
		int num = dao.conditionalDelete();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String saveOrUpdate(String json) throws Exception{
		BaseDepartmentRole bean = new BaseDepartmentRole();
		bean.setDataFromJSON(json);
		DepartmentRole dao = new DepartmentRole();
		dao.setPrimaryKeyFromBase(bean);
		int ret = 0;
		if(dao.isPrimaryKeyNull()) { dao.setDataFromBase(bean); ret = dao.save(); bean = dao.generateBase(); }
		else if(dao.load()) { dao.setDataFromBase(bean); ret = dao.update(); bean = dao.generateBase(); }
		return bean.toOneLineJSON(ret, null);
	}

}


