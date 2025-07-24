package delicacy.system.handler;


import java.util.List;
import delicacy.common.BaseCollection;
import delicacy.connection.ThreadConnection;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import java.io.StringReader;
import java.util.Map;
import delicacy.common.PaginationParameter;
import delicacy.system.bean.BaseDepartmentWithD;
import delicacy.system.dao.DepartmentWithD;
import delicacy.system.bean.BaseDepartmentRole;
import delicacy.system.dao.DepartmentRole;
import org.apache.log4j.Logger;
import delicacy.common.GenericDao;
import delicacy.connection.ThreadConnection;
import delicacy.date.util.TimeSpanCalculator;

public class DepartmentWithDHandler implements GenericDao {

	public final static ThreadLocal currentRequest = new ThreadLocal();

	private static final Logger __logger = Logger.getLogger(DepartmentWithDHandler.class);

	public static BaseDepartmentWithD getDepartmentWithDById( java.lang.Integer department_id )
	{
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			BaseDepartmentWithD result;
			DepartmentWithD dao = new DepartmentWithD();
			dao.setDepartmentId(department_id);
			if(dao.load(true)){
				result = dao.generateBaseExt();
				tsc.recordTime();
				__logger.info(String.format("Get DepartmentWithD By ID time used : [%1$d]", tsc.getLastTime()));
				return result;
			}
			return null;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static boolean isDepartmentWithDExists( delicacy.system.bean.BaseDepartmentWithD bean, String additional ) {

		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			DepartmentWithD dao = new DepartmentWithD();
			setConditions(bean, dao);
			boolean res = dao.isExist(additional);
			tsc.recordTime();
			__logger.info(String.format("Query DepartmentWithD List time used : [%1$d]", tsc.getLastTime()));
			return res;
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public static int countDepartmentWithD( delicacy.system.bean.BaseDepartmentWithD bean, String additional ) {

		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			DepartmentWithD dao = new DepartmentWithD();
			setConditions(bean, dao);
			int res = dao.countRows(additional);
			tsc.recordTime();
			__logger.info(String.format("Query DepartmentWithD List time used : [%1$d]", tsc.getLastTime()));
			return res;
		} catch(Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	public static BaseCollection<BaseDepartmentWithD> queryDepartmentWithD( BaseDepartmentWithD bean, int pageNo, int pageLines, String additionalCondition ) {
		__logger.info(String.format("Current Page No. [%1$d]", pageNo));
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			DepartmentWithD dao = new DepartmentWithD();
			setConditions(bean, dao);
			dao.setCurrentPage(pageNo);
			dao.setPageLines(pageLines);
			List<BaseDepartmentWithD> result = dao.conditionalLoadExt(additionalCondition);
			BaseCollection<BaseDepartmentWithD> col = new BaseCollection<>();
			col.setCollections(result);
			col.setTotalPages(dao.getTotalPages());
			col.setCurrentPage(dao.getCurrentPage());
			col.setPageLines(dao.getPageLines());
			col.setTotalLines(dao.getTotalLines());
			col.setRecordNumber(result.size());
			tsc.recordTime();
			__logger.info(String.format("Query DepartmentWithD List time used : [%1$d]", tsc.getLastTime()));
			return col;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseDepartmentWithD addToDepartmentWithD ( BaseDepartmentWithD departmentwithd ) {
		return addToDepartmentWithD ( departmentwithd , false);
	}

	public static BaseDepartmentWithD addToDepartmentWithD ( BaseDepartmentWithD departmentwithd, boolean singleTransaction ) {
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			if(singleTransaction) ThreadConnection.beginTransaction();
			DepartmentWithD dao = new DepartmentWithD();
			dao.setDataFromBase(departmentwithd);
			int result = dao.save();
			if(singleTransaction) ThreadConnection.commit();
			tsc.recordTime();
			__logger.info(String.format("Add to DepartmentWithD time used : [%1$d]", tsc.getLastTime()));
			return result==1?dao.generateBaseExt():null;
		} catch(Exception ex) {
			if(singleTransaction) ThreadConnection.rollback();
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseDepartmentWithD addUpdateDepartmentWithD ( BaseDepartmentWithD departmentwithd ) {
		return addUpdateDepartmentWithD ( departmentwithd , false);
	}

	public static BaseDepartmentWithD addUpdateDepartmentWithD ( BaseDepartmentWithD departmentwithd, boolean singleTransaction  ) {
		if(departmentwithd.getDepartmentId() == null) return addToDepartmentWithD(departmentwithd);
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			if(singleTransaction) ThreadConnection.beginTransaction();
			DepartmentWithD dao = new DepartmentWithD();
			dao.setDataFromBase(departmentwithd);
			int result = 0;
			if(dao.load()) {
				dao.setDataFromBase(departmentwithd); 
				if(dao.isThisObjectModified()) { 
					result = dao.update();
				} else result = 1;
			} else result = dao.save();
			if(singleTransaction) ThreadConnection.commit();
			tsc.recordTime();
			__logger.info(String.format("Add to DepartmentWithD time used : [%1$d]", tsc.getLastTime()));
			return result==1?dao.generateBaseExt():null;
		} catch(Exception ex) {
			if(singleTransaction) ThreadConnection.rollback();
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseDepartmentWithD deleteDepartmentWithD ( BaseDepartmentWithD bean ) {
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			DepartmentWithD dao = new DepartmentWithD();
			setDeleteConditions(bean, dao);
			int result = dao.conditionalDelete();
			tsc.recordTime();
			__logger.info(String.format("Delete DepartmentWithD time used : [%1$d]", tsc.getLastTime()));
			return result == 1 ? bean : null ;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseDepartmentWithD updateDepartmentWithD ( BaseDepartmentWithD departmentwithd ) {
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			DepartmentWithD dao = new DepartmentWithD();
			dao.setDepartmentId( departmentwithd.getDepartmentId() );
			int result = 0;
			if( dao.load() ) {
				dao.setDataFromBase(departmentwithd);
				result = dao.update();
			}
			tsc.recordTime();
			__logger.info(String.format("Update DepartmentWithD time used : [%1$d]", tsc.getLastTime()));
			return result == 1 ? departmentwithd : null ;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseDepartmentWithD updateDepartmentWithDDirect( BaseDepartmentWithD departmentwithd ) {
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			DepartmentWithD dao = new DepartmentWithD();
			int result = 0;
			dao.setDataFromBase(departmentwithd);
			result = dao.update();
			tsc.recordTime();
			__logger.info(String.format("Update DepartmentWithD time used : [%1$d]", tsc.getLastTime()));
			return result == 1 ? departmentwithd : null ;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static int setDeleteConditions(BaseDepartmentWithD bean, DepartmentWithD dao){
		int count = 0;
		boolean foundKey = false;
		if(bean.getDepartmentId() != null) {
			dao.setDepartmentId(bean.getDepartmentId());
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
		}
		return count;
	}

	public static int setConditions(BaseDepartmentWithD bean, DepartmentWithD dao){
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
		return count;
	}

	@Override
	public String findUsingKey(String json) throws Exception{
		BaseDepartmentWithD bean = new BaseDepartmentWithD();
		bean.setDataFromJSON(json);
		DepartmentWithD dao = new DepartmentWithD();
		dao.setPrimaryKeyFromBase(bean);
		if(dao.load(true)) {
			dao.setDataToBase(bean);
			return bean.toOneLineJSON(1,null);
		}
		return bean.toOneLineJSON(0,"Record not found.");
	}

	@Override
	public String find(String json, int currentPage, int pageSize, String addtion) throws Exception{
		BaseCollection<BaseDepartmentWithD> rlist = new BaseCollection<>();
		BaseDepartmentWithD bean = new BaseDepartmentWithD();
		JSON parser = new JSON(new StringReader(json));
		Map<String, Object> params = (Map<String, Object>) parser.parse(new BasicHandler());
		bean.setDataFromMap(params);
		PaginationParameter pp = PaginationParameter.generateFromMap(params);
		if(pp != null){
			if(pp.getCurrentPage() > 0) currentPage = pp.getCurrentPage();
			if(pp.getPageSize() > 0) pageSize = pp.getPageSize();
			if(pp.getCondition() != null) addtion = pp.getCondition();
		}
		DepartmentWithD dao = new DepartmentWithD();
		setConditions(bean, dao);
		dao.setCurrentPage(currentPage);
		dao.setPageLines(pageSize);
		List<BaseDepartmentWithD> result = dao.conditionalLoadExt(addtion);
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
		BaseDepartmentWithD bean = new BaseDepartmentWithD();
		bean.setDataFromJSON(json);
		DepartmentWithD dao = new DepartmentWithD();
		ThreadConnection.beginTransaction();
		dao.setDataFromBase(bean);
		int num = dao.save();
		dao.setDataToBase(bean);
		ThreadConnection.commit();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String saveOrUpdate(String json) throws Exception{
		BaseDepartmentWithD bean = new BaseDepartmentWithD();
		bean.setDataFromJSON(json);
		int num = 0;
		DepartmentWithD dao = new DepartmentWithD();
		ThreadConnection.beginTransaction();
		dao.setDataFromBase(bean);
		if(dao.isPrimaryKeyNull()) num = dao.save();
		else if(dao.load()){ dao.setDataFromBase(bean); num = dao.update(); }
		ThreadConnection.commit();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String update(String json) throws Exception{
		BaseDepartmentWithD bean = new BaseDepartmentWithD();
		bean.setDataFromJSON(json);
		DepartmentWithD dao = new DepartmentWithD();
		ThreadConnection.beginTransaction();
		dao.setPrimaryKeyFromBase(bean);
		int num=0;
		if(dao.load()){dao.setDataFromBase(bean); num = dao.update(); }
		ThreadConnection.commit();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String delete(String json) throws Exception{
		BaseDepartmentWithD bean = new BaseDepartmentWithD();
		bean.setDataFromJSON(json);
		DepartmentWithD dao = new DepartmentWithD();
		ThreadConnection.beginTransaction();
		dao.setDataFromBase(bean);
		setDeleteConditions(bean, dao);
		int num = dao.conditionalDelete();
		ThreadConnection.commit();
		return bean.toOneLineJSON(num, null);
	}

}


