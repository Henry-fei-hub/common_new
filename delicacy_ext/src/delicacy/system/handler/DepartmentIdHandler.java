package delicacy.system.handler;

import org.apache.log4j.Logger;
import delicacy.system.bean.BaseDepartmentId;
import java.util.List;
import delicacy.system.dao.DepartmentId;
import delicacy.connection.ThreadConnection;
import delicacy.common.GenericDao;
import delicacy.common.BaseCollection;

public class DepartmentIdHandler implements GenericDao {

	private static final Logger __logger = Logger.getLogger(DepartmentIdHandler.class);

	public static BaseDepartmentId getDepartmentIdById( 
		java.lang.Integer department_id_id
	) throws Exception
	{
		DepartmentId dao = new DepartmentId();
		dao.setDepartmentIdId(department_id_id);
		if(dao.load()){
			return dao.generateBase();
		}
		return null;
	}

	public static boolean isDepartmentIdExists( delicacy.system.bean.BaseDepartmentId bean, String additional ) throws Exception {

		DepartmentId dao = new DepartmentId();
		setConditions(bean, dao);
		boolean res = dao.isExist(additional);
		return res;
	}

	public static int countDepartmentId( delicacy.system.bean.BaseDepartmentId bean, String additional ) throws Exception {

		DepartmentId dao = new DepartmentId();
		setConditions(bean, dao);
		int res = dao.countRows(additional);
		return res;
	}

	public static BaseCollection<BaseDepartmentId> queryDepartmentId( delicacy.system.bean.BaseDepartmentId bean, int pageNo, int pageLines, String additionalCondition ) throws Exception {
		DepartmentId dao = new DepartmentId();
		setConditions(bean, dao);
		dao.setCurrentPage(pageNo);
		dao.setPageLines(pageLines);
		java.util.List<BaseDepartmentId> result = dao.conditionalLoad(additionalCondition);
		BaseCollection<BaseDepartmentId> col = new BaseCollection<>();
		col.setCollections(result);
		col.setTotalPages(dao.getTotalPages());
		col.setCurrentPage(dao.getCurrentPage());
		col.setPageLines(dao.getPageLines());
		col.setTotalLines(dao.getTotalLines());
		col.setRecordNumber(result.size());
		return col;
	}

	public static BaseDepartmentId addToDepartmentId ( BaseDepartmentId departmentid )  throws Exception {
		return addToDepartmentId ( departmentid , false);
	}

	public static BaseDepartmentId addToDepartmentId ( BaseDepartmentId departmentid, boolean singleTransaction ) throws Exception {
		if(singleTransaction) ThreadConnection.beginTransaction();
		DepartmentId dao = new DepartmentId();
		dao.setDataFromBase(departmentid);
		int result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static BaseDepartmentId addUpdateDepartmentId ( BaseDepartmentId departmentid ) throws Exception {
		return addUpdateDepartmentId ( departmentid , false);
	}

	public static BaseDepartmentId addUpdateDepartmentId ( BaseDepartmentId departmentid, boolean singleTransaction  ) throws Exception {
		if(departmentid.getDepartmentIdId() == null) return addToDepartmentId(departmentid);
		if(singleTransaction) ThreadConnection.beginTransaction();
		StringBuilder sb = new StringBuilder();
		DepartmentId dao = new DepartmentId();
		dao.setDataFromBase(departmentid);
		int result = 0;
		if(dao.load()) {
			dao.setDataFromBase(departmentid); 
			if(dao.isThisObjectModified()) { 
				result = dao.update();
			} else result = 1;
		} else result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static int deleteDepartmentId ( BaseDepartmentId bean ) throws Exception {
		DepartmentId dao = new DepartmentId();
		if(setDeleteConditions(bean, dao) == 0) return 0;
		int result = dao.conditionalDelete();
		return result ;
	}

	public static BaseDepartmentId updateDepartmentId ( BaseDepartmentId departmentid ) throws Exception {
		DepartmentId dao = new DepartmentId();
		dao.setDepartmentIdId( departmentid.getDepartmentIdId() );
		int result = 0;
		if( dao.load() ) {
			dao.setDataFromBase(departmentid);
			result = dao.update();
		}
		return result == 1 ? departmentid : null ;
	}

	public static BaseDepartmentId updateDepartmentIdDirect( BaseDepartmentId departmentid ) throws Exception {
		DepartmentId dao = new DepartmentId();
		int result = 0;
		dao.setDataFromBase(departmentid);
		result = dao.update();
		return result == 1 ? departmentid : null ;
	}

	public static int setDeleteConditions(BaseDepartmentId bean, DepartmentId dao){
		int count = 0;
		boolean foundKey = false;
		if(bean.getDepartmentIdId() != null) {
			dao.setConditionDepartmentIdId("=", bean.getDepartmentIdId());
			count++;
			foundKey = true;
		}
		if(!foundKey) {
			if(bean.getDepartmentId() != null) {
				dao.setConditionDepartmentId("=", bean.getDepartmentId());
				count++;
			}
			if(bean.getChildId() != null) {
				dao.setConditionChildId("=", bean.getChildId());
				count++;
			}
		}
		return count;
	}

	public static int setConditions(BaseDepartmentId bean, DepartmentId dao){
		int count = 0;
		if(bean.getDepartmentIdId() != null) {
			dao.setConditionDepartmentIdId("=", bean.getDepartmentIdId());
			count++;
		}
		if(bean.getDepartmentId() != null) {
			dao.setConditionDepartmentId("=", bean.getDepartmentId());
			count++;
		}
		if(bean.getChildId() != null) {
			dao.setConditionChildId("=", bean.getChildId());
			count++;
		}
		return count;
	}

	@Override
	public String findUsingKey(String json) throws Exception{
		BaseDepartmentId bean = new BaseDepartmentId();
		bean.setDataFromJSON(json);
		DepartmentId dao = new DepartmentId();
		dao.setPrimaryKeyFromBase(bean);
		if(dao.load()) { dao.setDataToBase(bean); return bean.toOneLineJSON(1,null); }
		return bean.toOneLineJSON(0,"Record not found.");
	}

	@Override
	public String find(String json, int currentPage, int pageSize, String addtion) throws Exception{
		BaseCollection<BaseDepartmentId> rlist = new BaseCollection<>();
		BaseDepartmentId bean = new BaseDepartmentId();
		bean.setDataFromJSON(json);
		DepartmentId dao = new DepartmentId();
		setConditions(bean, dao);
		dao.setCurrentPage(currentPage);
		dao.setPageLines(pageSize);
		List<BaseDepartmentId> result = dao.conditionalLoad(addtion);
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
		BaseDepartmentId bean = new BaseDepartmentId();
		bean.setDataFromJSON(json);
		DepartmentId dao = new DepartmentId();
		dao.setDataFromBase(bean);
		int num = dao.save();
		dao.setDataToBase(bean);
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String update(String json) throws Exception{
		BaseDepartmentId bean = new BaseDepartmentId();
		bean.setDataFromJSON(json);
		DepartmentId dao = new DepartmentId();
		dao.setPrimaryKeyFromBase(bean);
		int num=0;
		if(dao.load()){dao.setDataFromBase(bean); num = dao.update(); }
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String delete(String json) throws Exception{
		BaseDepartmentId bean = new BaseDepartmentId();
		bean.setDataFromJSON(json);
		DepartmentId dao = new DepartmentId();
		setDeleteConditions(bean, dao);
		int num = dao.conditionalDelete();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String saveOrUpdate(String json) throws Exception{
		BaseDepartmentId bean = new BaseDepartmentId();
		bean.setDataFromJSON(json);
		DepartmentId dao = new DepartmentId();
		dao.setPrimaryKeyFromBase(bean);
		int ret = 0;
		if(dao.isPrimaryKeyNull()) { dao.setDataFromBase(bean); ret = dao.save(); bean = dao.generateBase(); }
		else if(dao.load()) { dao.setDataFromBase(bean); ret = dao.update(); bean = dao.generateBase(); }
		return bean.toOneLineJSON(ret, null);
	}

}


