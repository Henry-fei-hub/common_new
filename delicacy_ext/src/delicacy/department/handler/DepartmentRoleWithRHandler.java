package delicacy.department.handler;


import java.io.StringReader;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import delicacy.common.BaseCollection;
import delicacy.common.GenericDao;
import delicacy.common.PaginationParameter;
import delicacy.connection.ThreadConnection;
import delicacy.date.util.TimeSpanCalculator;
import delicacy.department.bean.BaseDepartmentRoleWithR;
import delicacy.department.dao.DepartmentRoleWithR;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;

public class DepartmentRoleWithRHandler implements GenericDao {

	public final static ThreadLocal currentRequest = new ThreadLocal();

	private static final Logger __logger = Logger.getLogger(DepartmentRoleWithRHandler.class);

	public static BaseDepartmentRoleWithR getDepartmentRoleWithRById( java.lang.Integer department_role_id )
	{
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			BaseDepartmentRoleWithR result;
			DepartmentRoleWithR dao = new DepartmentRoleWithR();
			dao.setDepartmentRoleId(department_role_id);
			if(dao.load(true)){
				result = dao.generateBaseExt();
				tsc.recordTime();
				__logger.info(String.format("Get DepartmentRoleWithR By ID time used : [%1$d]", tsc.getLastTime()));
				return result;
			}
			return null;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static boolean isDepartmentRoleWithRExists( delicacy.department.bean.BaseDepartmentRoleWithR bean, String additional ) {

		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			DepartmentRoleWithR dao = new DepartmentRoleWithR();
			setConditions(bean, dao);
			boolean res = dao.isExist(additional);
			tsc.recordTime();
			__logger.info(String.format("Query DepartmentRoleWithR List time used : [%1$d]", tsc.getLastTime()));
			return res;
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public static int countDepartmentRoleWithR( delicacy.department.bean.BaseDepartmentRoleWithR bean, String additional ) {

		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			DepartmentRoleWithR dao = new DepartmentRoleWithR();
			setConditions(bean, dao);
			int res = dao.countRows(additional);
			tsc.recordTime();
			__logger.info(String.format("Query DepartmentRoleWithR List time used : [%1$d]", tsc.getLastTime()));
			return res;
		} catch(Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	public static BaseCollection<BaseDepartmentRoleWithR> queryDepartmentRoleWithR( BaseDepartmentRoleWithR bean, int pageNo, int pageLines, String additionalCondition ) {
		__logger.info(String.format("Current Page No. [%1$d]", pageNo));
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			DepartmentRoleWithR dao = new DepartmentRoleWithR();
			setConditions(bean, dao);
			dao.setCurrentPage(pageNo);
			dao.setPageLines(pageLines);
			List<BaseDepartmentRoleWithR> result = dao.conditionalLoadExt(additionalCondition);
			BaseCollection<BaseDepartmentRoleWithR> col = new BaseCollection<>();
			col.setCollections(result);
			col.setTotalPages(dao.getTotalPages());
			col.setCurrentPage(dao.getCurrentPage());
			col.setPageLines(dao.getPageLines());
			col.setTotalLines(dao.getTotalLines());
			col.setRecordNumber(result.size());
			tsc.recordTime();
			__logger.info(String.format("Query DepartmentRoleWithR List time used : [%1$d]", tsc.getLastTime()));
			return col;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseDepartmentRoleWithR addToDepartmentRoleWithR ( BaseDepartmentRoleWithR departmentrolewithr ) {
		return addToDepartmentRoleWithR ( departmentrolewithr , false);
	}

	public static BaseDepartmentRoleWithR addToDepartmentRoleWithR ( BaseDepartmentRoleWithR departmentrolewithr, boolean singleTransaction ) {
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			if(singleTransaction) ThreadConnection.beginTransaction();
			DepartmentRoleWithR dao = new DepartmentRoleWithR();
			dao.setDataFromBase(departmentrolewithr);
			int result = dao.save();
			if(singleTransaction) ThreadConnection.commit();
			tsc.recordTime();
			__logger.info(String.format("Add to DepartmentRoleWithR time used : [%1$d]", tsc.getLastTime()));
			return result==1?dao.generateBaseExt():null;
		} catch(Exception ex) {
			if(singleTransaction) ThreadConnection.rollback();
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseDepartmentRoleWithR addUpdateDepartmentRoleWithR ( BaseDepartmentRoleWithR departmentrolewithr ) {
		return addUpdateDepartmentRoleWithR ( departmentrolewithr , false);
	}

	public static BaseDepartmentRoleWithR addUpdateDepartmentRoleWithR ( BaseDepartmentRoleWithR departmentrolewithr, boolean singleTransaction  ) {
		if(departmentrolewithr.getDepartmentRoleId() == null) return addToDepartmentRoleWithR(departmentrolewithr);
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			if(singleTransaction) ThreadConnection.beginTransaction();
			DepartmentRoleWithR dao = new DepartmentRoleWithR();
			dao.setDataFromBase(departmentrolewithr);
			int result = 0;
			if(dao.load()) {
				dao.setDataFromBase(departmentrolewithr); 
				if(dao.isThisObjectModified()) { 
					result = dao.update();
				} else result = 1;
			} else result = dao.save();
			if(singleTransaction) ThreadConnection.commit();
			tsc.recordTime();
			__logger.info(String.format("Add to DepartmentRoleWithR time used : [%1$d]", tsc.getLastTime()));
			return result==1?dao.generateBaseExt():null;
		} catch(Exception ex) {
			if(singleTransaction) ThreadConnection.rollback();
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseDepartmentRoleWithR deleteDepartmentRoleWithR ( BaseDepartmentRoleWithR bean ) {
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			DepartmentRoleWithR dao = new DepartmentRoleWithR();
			setDeleteConditions(bean, dao);
			int result = dao.conditionalDelete();
			tsc.recordTime();
			__logger.info(String.format("Delete DepartmentRoleWithR time used : [%1$d]", tsc.getLastTime()));
			return result == 1 ? bean : null ;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseDepartmentRoleWithR updateDepartmentRoleWithR ( BaseDepartmentRoleWithR departmentrolewithr ) {
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			DepartmentRoleWithR dao = new DepartmentRoleWithR();
			dao.setDepartmentRoleId( departmentrolewithr.getDepartmentRoleId() );
			int result = 0;
			if( dao.load() ) {
				dao.setDataFromBase(departmentrolewithr);
				result = dao.update();
			}
			tsc.recordTime();
			__logger.info(String.format("Update DepartmentRoleWithR time used : [%1$d]", tsc.getLastTime()));
			return result == 1 ? departmentrolewithr : null ;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseDepartmentRoleWithR updateDepartmentRoleWithRDirect( BaseDepartmentRoleWithR departmentrolewithr ) {
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			DepartmentRoleWithR dao = new DepartmentRoleWithR();
			int result = 0;
			dao.setDataFromBase(departmentrolewithr);
			result = dao.update();
			tsc.recordTime();
			__logger.info(String.format("Update DepartmentRoleWithR time used : [%1$d]", tsc.getLastTime()));
			return result == 1 ? departmentrolewithr : null ;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static int setDeleteConditions(BaseDepartmentRoleWithR bean, DepartmentRoleWithR dao){
		int count = 0;
		boolean foundKey = false;
		if(bean.getDepartmentRoleId() != null) {
			dao.setDepartmentRoleId(bean.getDepartmentRoleId());
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

	public static int setConditions(BaseDepartmentRoleWithR bean, DepartmentRoleWithR dao){
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
		BaseDepartmentRoleWithR bean = new BaseDepartmentRoleWithR();
		bean.setDataFromJSON(json);
		DepartmentRoleWithR dao = new DepartmentRoleWithR();
		dao.setPrimaryKeyFromBase(bean);
		if(dao.load(true)) {
			dao.setDataToBase(bean);
			return bean.toOneLineJSON(1,null);
		}
		return bean.toOneLineJSON(0,"Record not found.");
	}

	@Override
	public String find(String json, int currentPage, int pageSize, String addtion) throws Exception{
		BaseCollection<BaseDepartmentRoleWithR> rlist = new BaseCollection<>();
		BaseDepartmentRoleWithR bean = new BaseDepartmentRoleWithR();
		JSON parser = new JSON(new StringReader(json));
		Map<String, Object> params = (Map<String, Object>) parser.parse(new BasicHandler());
		bean.setDataFromMap(params);
		PaginationParameter pp = PaginationParameter.generateFromMap(params);
		if(pp != null){
			if(pp.getCurrentPage() > 0) currentPage = pp.getCurrentPage();
			if(pp.getPageSize() > 0) pageSize = pp.getPageSize();
			if(pp.getCondition() != null) addtion = pp.getCondition();
		}
		DepartmentRoleWithR dao = new DepartmentRoleWithR();
		setConditions(bean, dao);
		dao.setCurrentPage(currentPage);
		dao.setPageLines(pageSize);
		List<BaseDepartmentRoleWithR> result = dao.conditionalLoadExt(addtion);
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
		BaseDepartmentRoleWithR bean = new BaseDepartmentRoleWithR();
		bean.setDataFromJSON(json);
		DepartmentRoleWithR dao = new DepartmentRoleWithR();
		ThreadConnection.beginTransaction();
		dao.setDataFromBase(bean);
		int num = dao.save();
		dao.setDataToBase(bean);
		ThreadConnection.commit();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String saveOrUpdate(String json) throws Exception{
		BaseDepartmentRoleWithR bean = new BaseDepartmentRoleWithR();
		bean.setDataFromJSON(json);
		int num = 0;
		DepartmentRoleWithR dao = new DepartmentRoleWithR();
		ThreadConnection.beginTransaction();
		dao.setDataFromBase(bean);
		if(dao.isPrimaryKeyNull()) num = dao.save();
		else if(dao.load()){ dao.setDataFromBase(bean); num = dao.update(); }
		ThreadConnection.commit();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String update(String json) throws Exception{
		BaseDepartmentRoleWithR bean = new BaseDepartmentRoleWithR();
		bean.setDataFromJSON(json);
		DepartmentRoleWithR dao = new DepartmentRoleWithR();
		ThreadConnection.beginTransaction();
		dao.setPrimaryKeyFromBase(bean);
		int num=0;
		if(dao.load()){dao.setDataFromBase(bean); num = dao.update(); }
		ThreadConnection.commit();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String delete(String json) throws Exception{
		BaseDepartmentRoleWithR bean = new BaseDepartmentRoleWithR();
		bean.setDataFromJSON(json);
		DepartmentRoleWithR dao = new DepartmentRoleWithR();
		ThreadConnection.beginTransaction();
		dao.setDataFromBase(bean);
		setDeleteConditions(bean, dao);
		int num = dao.conditionalDelete();
		ThreadConnection.commit();
		return bean.toOneLineJSON(num, null);
	}

}


