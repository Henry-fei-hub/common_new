package delicacy.system.handler;

import org.apache.log4j.Logger;
import delicacy.system.bean.BaseEmployeeRole;
import java.util.List;
import delicacy.system.dao.EmployeeRole;
import delicacy.connection.ThreadConnection;
import delicacy.common.GenericDao;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import java.io.StringReader;
import java.util.Map;
import delicacy.common.PaginationParameter;
import delicacy.common.BaseCollection;

public class EmployeeRoleHandler implements GenericDao {

	private static final Logger __logger = Logger.getLogger(EmployeeRoleHandler.class);

	public static BaseEmployeeRole getEmployeeRoleById( 
		java.lang.Integer employee_role_id
	) throws Exception
	{
		EmployeeRole dao = new EmployeeRole();
		dao.setEmployeeRoleId(employee_role_id);
		if(dao.load()){
			return dao.generateBase();
		}
		return null;
	}

	public static boolean isEmployeeRoleExists( delicacy.system.bean.BaseEmployeeRole bean, String additional ) throws Exception {

		EmployeeRole dao = new EmployeeRole();
		setConditions(bean, dao);
		boolean res = dao.isExist(additional);
		return res;
	}

	public static int countEmployeeRole( delicacy.system.bean.BaseEmployeeRole bean, String additional ) throws Exception {

		EmployeeRole dao = new EmployeeRole();
		setConditions(bean, dao);
		int res = dao.countRows(additional);
		return res;
	}

	public static BaseCollection<BaseEmployeeRole> queryEmployeeRole( delicacy.system.bean.BaseEmployeeRole bean, int pageNo, int pageLines, String additionalCondition ) throws Exception {
		EmployeeRole dao = new EmployeeRole();
		setConditions(bean, dao);
		dao.setCurrentPage(pageNo);
		dao.setPageLines(pageLines);
		java.util.List<BaseEmployeeRole> result = dao.conditionalLoad(additionalCondition);
		BaseCollection<BaseEmployeeRole> col = new BaseCollection<>();
		col.setCollections(result);
		col.setTotalPages(dao.getTotalPages());
		col.setCurrentPage(dao.getCurrentPage());
		col.setPageLines(dao.getPageLines());
		col.setTotalLines(dao.getTotalLines());
		col.setRecordNumber(result.size());
		return col;
	}

	public static BaseEmployeeRole addToEmployeeRole ( BaseEmployeeRole employeerole )  throws Exception {
		return addToEmployeeRole ( employeerole , false);
	}

	public static BaseEmployeeRole addToEmployeeRole ( BaseEmployeeRole employeerole, boolean singleTransaction ) throws Exception {
		if(singleTransaction) ThreadConnection.beginTransaction();
		EmployeeRole dao = new EmployeeRole();
		dao.setDataFromBase(employeerole);
		int result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static BaseEmployeeRole addUpdateEmployeeRole ( BaseEmployeeRole employeerole ) throws Exception {
		return addUpdateEmployeeRole ( employeerole , false);
	}

	public static BaseEmployeeRole addUpdateEmployeeRole ( BaseEmployeeRole employeerole, boolean singleTransaction  ) throws Exception {
		if(employeerole.getEmployeeRoleId() == null) return addToEmployeeRole(employeerole);
		if(singleTransaction) ThreadConnection.beginTransaction();
		StringBuilder sb = new StringBuilder();
		EmployeeRole dao = new EmployeeRole();
		dao.setDataFromBase(employeerole);
		int result = 0;
		if(dao.load()) {
			dao.setDataFromBase(employeerole); 
			if(dao.isThisObjectModified()) { 
				result = dao.update();
			} else result = 1;
		} else result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static int deleteEmployeeRole ( BaseEmployeeRole bean ) throws Exception {
		EmployeeRole dao = new EmployeeRole();
		if(setDeleteConditions(bean, dao) == 0) return 0;
		int result = dao.conditionalDelete();
		return result ;
	}

	public static BaseEmployeeRole updateEmployeeRole ( BaseEmployeeRole employeerole ) throws Exception {
		EmployeeRole dao = new EmployeeRole();
		dao.setEmployeeRoleId( employeerole.getEmployeeRoleId() );
		int result = 0;
		if( dao.load() ) {
			dao.setDataFromBase(employeerole);
			result = dao.update();
		}
		return result == 1 ? employeerole : null ;
	}

	public static BaseEmployeeRole updateEmployeeRoleDirect( BaseEmployeeRole employeerole ) throws Exception {
		EmployeeRole dao = new EmployeeRole();
		int result = 0;
		dao.setDataFromBase(employeerole);
		result = dao.update();
		return result == 1 ? employeerole : null ;
	}

	public static int setDeleteConditions(BaseEmployeeRole bean, EmployeeRole dao){
		int count = 0;
		boolean foundKey = false;
		if(bean.getEmployeeRoleId() != null) {
			dao.setConditionEmployeeRoleId("=", bean.getEmployeeRoleId());
			count++;
			foundKey = true;
		}
		if(!foundKey) {
			if(bean.getEmployeeId() != null) {
				dao.setConditionEmployeeId("=", bean.getEmployeeId());
				count++;
			}
			if(bean.getRoleId() != null) {
				dao.setConditionRoleId("=", bean.getRoleId());
				count++;
			}
			if(bean.getDepartmentId() != null) {
				dao.setConditionDepartmentId("=", bean.getDepartmentId());
				count++;
			}
			if(bean.getIsDefault() != null) {
				dao.setConditionIsDefault("=", bean.getIsDefault());
				count++;
			}
		}
		return count;
	}

	public static int setConditions(BaseEmployeeRole bean, EmployeeRole dao){
		int count = 0;
		if(bean.getEmployeeRoleId() != null) {
			dao.setConditionEmployeeRoleId("=", bean.getEmployeeRoleId());
			count++;
		}
		if(bean.getEmployeeId() != null) {
			dao.setConditionEmployeeId("=", bean.getEmployeeId());
			count++;
		}
		if(bean.getRoleId() != null) {
			dao.setConditionRoleId("=", bean.getRoleId());
			count++;
		}
		if(bean.getDepartmentId() != null) {
			dao.setConditionDepartmentId("=", bean.getDepartmentId());
			count++;
		}
		if(bean.getIsDefault() != null) {
			dao.setConditionIsDefault("=", bean.getIsDefault());
			count++;
		}
		return count;
	}

	@Override
	public String findUsingKey(String json) throws Exception{
		BaseEmployeeRole bean = new BaseEmployeeRole();
		bean.setDataFromJSON(json);
		EmployeeRole dao = new EmployeeRole();
		dao.setPrimaryKeyFromBase(bean);
		if(dao.load()) { dao.setDataToBase(bean); return bean.toOneLineJSON(1,null); }
		return bean.toOneLineJSON(0,"Record not found.");
	}

	@Override
	public String find(String json, int currentPage, int pageSize, String addtion) throws Exception{
		BaseCollection<BaseEmployeeRole> rlist = new BaseCollection<>();
		BaseEmployeeRole bean = new BaseEmployeeRole();
		JSON parser = new JSON(new StringReader(json));
		Map<String, Object> params = (Map<String, Object>) parser.parse(new BasicHandler());
		bean.setDataFromMap(params);
		PaginationParameter pp = PaginationParameter.generateFromMap(params);
		if(pp != null){
			if(pp.getCurrentPage() > 0) currentPage = pp.getCurrentPage();
			if(pp.getPageSize() > 0) pageSize = pp.getPageSize();
			if(pp.getCondition() != null) addtion = pp.getCondition();
		}
		EmployeeRole dao = new EmployeeRole();
		setConditions(bean, dao);
		dao.setCurrentPage(currentPage);
		dao.setPageLines(pageSize);
		List<BaseEmployeeRole> result = dao.conditionalLoad(addtion);
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
		BaseEmployeeRole bean = new BaseEmployeeRole();
		bean.setDataFromJSON(json);
		EmployeeRole dao = new EmployeeRole();
		dao.setDataFromBase(bean);
		int num = dao.save();
		dao.setDataToBase(bean);
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String update(String json) throws Exception{
		BaseEmployeeRole bean = new BaseEmployeeRole();
		bean.setDataFromJSON(json);
		EmployeeRole dao = new EmployeeRole();
		dao.setPrimaryKeyFromBase(bean);
		int num=0;
		if(dao.load()){dao.setDataFromBase(bean); num = dao.update(); }
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String delete(String json) throws Exception{
		BaseEmployeeRole bean = new BaseEmployeeRole();
		bean.setDataFromJSON(json);
		EmployeeRole dao = new EmployeeRole();
		setDeleteConditions(bean, dao);
		int num = dao.conditionalDelete();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String saveOrUpdate(String json) throws Exception{
		BaseEmployeeRole bean = new BaseEmployeeRole();
		bean.setDataFromJSON(json);
		EmployeeRole dao = new EmployeeRole();
		dao.setPrimaryKeyFromBase(bean);
		int ret = 0;
		if(dao.isPrimaryKeyNull()) { dao.setDataFromBase(bean); ret = dao.save(); bean = dao.generateBase(); }
		else if(dao.load()) { dao.setDataFromBase(bean); ret = dao.update(); bean = dao.generateBase(); }
		return bean.toOneLineJSON(ret, null);
	}

}


