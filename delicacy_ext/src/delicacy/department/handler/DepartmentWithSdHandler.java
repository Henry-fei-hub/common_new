package delicacy.department.handler;


import delicacy.common.BaseCollection;
import delicacy.common.GenericDao;
import delicacy.common.PaginationParameter;
import delicacy.connection.ThreadConnection;
import delicacy.date.util.TimeSpanCalculator;
import delicacy.department.bean.BaseDepartmentWithSd;
import delicacy.department.dao.DepartmentWithSd;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import org.apache.log4j.Logger;

import java.io.StringReader;
import java.util.List;
import java.util.Map;

public class DepartmentWithSdHandler implements GenericDao {

	public final static ThreadLocal currentRequest = new ThreadLocal();

	private static final Logger __logger = Logger.getLogger(DepartmentWithSdHandler.class);

	public static BaseDepartmentWithSd getDepartmentWithSdById( Integer department_id )
	{
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			BaseDepartmentWithSd result;
			DepartmentWithSd dao = new DepartmentWithSd();
			dao.setDepartmentId(department_id);
			if(dao.load(true)){
				result = dao.generateBaseExt();
				tsc.recordTime();
				__logger.info(String.format("Get DepartmentWithSd By ID time used : [%1$d]", tsc.getLastTime()));
				return result;
			}
			return null;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static boolean isDepartmentWithSdExists( BaseDepartmentWithSd bean, String additional ) {

		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			DepartmentWithSd dao = new DepartmentWithSd();
			setConditions(bean, dao);
			boolean res = dao.isExist(additional);
			tsc.recordTime();
			__logger.info(String.format("Query DepartmentWithSd List time used : [%1$d]", tsc.getLastTime()));
			return res;
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public static int countDepartmentWithSd( BaseDepartmentWithSd bean, String additional ) {

		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			DepartmentWithSd dao = new DepartmentWithSd();
			setConditions(bean, dao);
			int res = dao.countRows(additional);
			tsc.recordTime();
			__logger.info(String.format("Query DepartmentWithSd List time used : [%1$d]", tsc.getLastTime()));
			return res;
		} catch(Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	public static BaseCollection<BaseDepartmentWithSd> queryDepartmentWithSd( BaseDepartmentWithSd bean, int pageNo, int pageLines, String additionalCondition ) {
		__logger.info(String.format("Current Page No. [%1$d]", pageNo));
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			DepartmentWithSd dao = new DepartmentWithSd();
			setConditions(bean, dao);
			dao.setCurrentPage(pageNo);
			dao.setPageLines(pageLines);
			List<BaseDepartmentWithSd> result = dao.conditionalLoadExt(additionalCondition);
			BaseCollection<BaseDepartmentWithSd> col = new BaseCollection<>();
			col.setCollections(result);
			col.setTotalPages(dao.getTotalPages());
			col.setCurrentPage(dao.getCurrentPage());
			col.setPageLines(dao.getPageLines());
			col.setTotalLines(dao.getTotalLines());
			col.setRecordNumber(result.size());
			tsc.recordTime();
			__logger.info(String.format("Query DepartmentWithSd List time used : [%1$d]", tsc.getLastTime()));
			return col;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseDepartmentWithSd addToDepartmentWithSd ( BaseDepartmentWithSd DepartmentWithSd ) {
		return addToDepartmentWithSd ( DepartmentWithSd , false);
	}

	public static BaseDepartmentWithSd addToDepartmentWithSd ( BaseDepartmentWithSd DepartmentWithSd, boolean singleTransaction ) {
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			if(singleTransaction) ThreadConnection.beginTransaction();
			DepartmentWithSd dao = new DepartmentWithSd();
			dao.setDataFromBase(DepartmentWithSd);
			int result = dao.save();
			if(singleTransaction) ThreadConnection.commit();
			tsc.recordTime();
			__logger.info(String.format("Add to DepartmentWithSd time used : [%1$d]", tsc.getLastTime()));
			return result==1?dao.generateBaseExt():null;
		} catch(Exception ex) {
			if(singleTransaction) ThreadConnection.rollback();
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseDepartmentWithSd addUpdateDepartmentWithSd ( BaseDepartmentWithSd DepartmentWithSd ) {
		return addUpdateDepartmentWithSd ( DepartmentWithSd , false);
	}

	public static BaseDepartmentWithSd addUpdateDepartmentWithSd (BaseDepartmentWithSd DepartmentWithSd, boolean singleTransaction  ) {
		if(DepartmentWithSd.getDepartmentId() == null) return addToDepartmentWithSd(DepartmentWithSd);
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			if(singleTransaction) ThreadConnection.beginTransaction();
			DepartmentWithSd dao = new DepartmentWithSd();
			dao.setDataFromBase(DepartmentWithSd);
			int result = 0;
			if(dao.load()) {
				dao.setDataFromBase(DepartmentWithSd);
				if(dao.isThisObjectModified()) {
					result = dao.update();
				} else result = 1;
			} else result = dao.save();
			if(singleTransaction) ThreadConnection.commit();
			tsc.recordTime();
			__logger.info(String.format("Add to DepartmentWithSd time used : [%1$d]", tsc.getLastTime()));
			return result==1?dao.generateBaseExt():null;
		} catch(Exception ex) {
			if(singleTransaction) ThreadConnection.rollback();
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseDepartmentWithSd deleteDepartmentWithSd ( BaseDepartmentWithSd bean ) {
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			DepartmentWithSd dao = new DepartmentWithSd();
			setDeleteConditions(bean, dao);
			int result = dao.conditionalDelete();
			tsc.recordTime();
			__logger.info(String.format("Delete DepartmentWithSd time used : [%1$d]", tsc.getLastTime()));
			return result == 1 ? bean : null ;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseDepartmentWithSd updateDepartmentWithSd ( BaseDepartmentWithSd DepartmentWithSd ) {
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			DepartmentWithSd dao = new DepartmentWithSd();
			dao.setDepartmentId( DepartmentWithSd.getDepartmentId() );
			int result = 0;
			if( dao.load() ) {
				dao.setDataFromBase(DepartmentWithSd);
				result = dao.update();
			}
			tsc.recordTime();
			__logger.info(String.format("Update DepartmentWithSd time used : [%1$d]", tsc.getLastTime()));
			return result == 1 ? DepartmentWithSd : null ;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseDepartmentWithSd updateDepartmentWithSdDirect( BaseDepartmentWithSd DepartmentWithSd ) {
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			DepartmentWithSd dao = new DepartmentWithSd();
			int result = 0;
			dao.setDataFromBase(DepartmentWithSd);
			result = dao.update();
			tsc.recordTime();
			__logger.info(String.format("Update DepartmentWithSd time used : [%1$d]", tsc.getLastTime()));
			return result == 1 ? DepartmentWithSd : null ;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static int setDeleteConditions(BaseDepartmentWithSd bean, DepartmentWithSd dao){
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

	public static int setConditions(BaseDepartmentWithSd bean, DepartmentWithSd dao){
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
		BaseDepartmentWithSd bean = new BaseDepartmentWithSd();
		bean.setDataFromJSON(json);
		DepartmentWithSd dao = new DepartmentWithSd();
		dao.setPrimaryKeyFromBase(bean);
		if(dao.load(true)) {
			dao.setDataToBase(bean);
			return bean.toOneLineJSON(1,null);
		}
		return bean.toOneLineJSON(0,"Record not found.");
	}

	@Override
	public String find(String json, int currentPage, int pageSize, String addtion) throws Exception{
		BaseCollection<BaseDepartmentWithSd> rlist = new BaseCollection<>();
		BaseDepartmentWithSd bean = new BaseDepartmentWithSd();
		JSON parser = new JSON(new StringReader(json));
		Map<String, Object> params = (Map<String, Object>) parser.parse(new BasicHandler());
		bean.setDataFromMap(params);
		PaginationParameter pp = PaginationParameter.generateFromMap(params);
		if(pp != null){
			if(pp.getCurrentPage() > 0) currentPage = pp.getCurrentPage();
			if(pp.getPageSize() > 0) pageSize = pp.getPageSize();
			if(pp.getCondition() != null) addtion = pp.getCondition();
		}
		DepartmentWithSd dao = new DepartmentWithSd();
		setConditions(bean, dao);
		dao.setCurrentPage(currentPage);
		dao.setPageLines(pageSize);
		List<BaseDepartmentWithSd> result = dao.conditionalLoadExt(addtion);
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
		BaseDepartmentWithSd bean = new BaseDepartmentWithSd();
		bean.setDataFromJSON(json);
		DepartmentWithSd dao = new DepartmentWithSd();
		ThreadConnection.beginTransaction();
		dao.setDataFromBase(bean);
		int num = dao.save();
		dao.setDataToBase(bean);
		ThreadConnection.commit();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String saveOrUpdate(String json) throws Exception{
		BaseDepartmentWithSd bean = new BaseDepartmentWithSd();
		bean.setDataFromJSON(json);
		int num = 0;
		DepartmentWithSd dao = new DepartmentWithSd();
		ThreadConnection.beginTransaction();
		dao.setDataFromBase(bean);
		if(dao.isPrimaryKeyNull()) num = dao.save();
		else if(dao.load()){ dao.setDataFromBase(bean); num = dao.update(); }
		ThreadConnection.commit();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String update(String json) throws Exception{
		BaseDepartmentWithSd bean = new BaseDepartmentWithSd();
		bean.setDataFromJSON(json);
		DepartmentWithSd dao = new DepartmentWithSd();
		ThreadConnection.beginTransaction();
		dao.setPrimaryKeyFromBase(bean);
		int num=0;
		if(dao.load()){dao.setDataFromBase(bean); num = dao.update(); }
		ThreadConnection.commit();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String delete(String json) throws Exception{
		BaseDepartmentWithSd bean = new BaseDepartmentWithSd();
		bean.setDataFromJSON(json);
		DepartmentWithSd dao = new DepartmentWithSd();
		ThreadConnection.beginTransaction();
		dao.setDataFromBase(bean);
		setDeleteConditions(bean, dao);
		int num = dao.conditionalDelete();
		ThreadConnection.commit();
		return bean.toOneLineJSON(num, null);
	}

}


