package delicacy.system.handler;


import java.util.List;
import delicacy.common.BaseCollection;
import delicacy.connection.ThreadConnection;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import java.io.StringReader;
import java.util.Map;
import delicacy.common.PaginationParameter;
import delicacy.system.bean.BaseRoleWithR;
import delicacy.system.dao.RoleWithR;
import org.apache.log4j.Logger;
import delicacy.common.GenericDao;
import delicacy.date.util.TimeSpanCalculator;

public class RoleWithRHandler implements GenericDao {
	public final static ThreadLocal currentRequest = new ThreadLocal();
	private static final Logger __logger = Logger.getLogger(RoleWithRHandler.class);
	public static BaseRoleWithR getRoleWithRById( java.lang.Integer role_id )
	{
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			BaseRoleWithR result;
			RoleWithR dao = new RoleWithR();
			dao.setRoleId(role_id);
			if(dao.load(true)){
				result = dao.generateBaseExt();
				tsc.recordTime();
				__logger.info(String.format("Get RoleWithR By ID time used : [%1$d]", tsc.getLastTime()));
				return result;
			}
			return null;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static boolean isRoleWithRExists( delicacy.system.bean.BaseRoleWithR bean, String additional ) {

		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			RoleWithR dao = new RoleWithR();
			setConditions(bean, dao);
			boolean res = dao.isExist(additional);
			tsc.recordTime();
			__logger.info(String.format("Query RoleWithR List time used : [%1$d]", tsc.getLastTime()));
			return res;
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public static int countRoleWithR( delicacy.system.bean.BaseRoleWithR bean, String additional ) {

		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			RoleWithR dao = new RoleWithR();
			setConditions(bean, dao);
			int res = dao.countRows(additional);
			tsc.recordTime();
			__logger.info(String.format("Query RoleWithR List time used : [%1$d]", tsc.getLastTime()));
			return res;
		} catch(Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	public static BaseCollection<BaseRoleWithR> queryRoleWithR( BaseRoleWithR bean, int pageNo, int pageLines, String additionalCondition ) {
		__logger.info(String.format("Current Page No. [%1$d]", pageNo));
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			RoleWithR dao = new RoleWithR();
			setConditions(bean, dao);
			dao.setCurrentPage(pageNo);
			dao.setPageLines(pageLines);
			List<BaseRoleWithR> result = dao.conditionalLoadExt(additionalCondition);
			BaseCollection<BaseRoleWithR> col = new BaseCollection<>();
			col.setCollections(result);
			col.setTotalPages(dao.getTotalPages());
			col.setCurrentPage(dao.getCurrentPage());
			col.setPageLines(dao.getPageLines());
			col.setTotalLines(dao.getTotalLines());
			col.setRecordNumber(result.size());
			tsc.recordTime();
			__logger.info(String.format("Query RoleWithR List time used : [%1$d]", tsc.getLastTime()));
			return col;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseRoleWithR addToRoleWithR ( BaseRoleWithR rolewithr ) {
		return addToRoleWithR ( rolewithr , false);
	}

	public static BaseRoleWithR addToRoleWithR ( BaseRoleWithR rolewithr, boolean singleTransaction ) {
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			if(singleTransaction) ThreadConnection.beginTransaction();
			RoleWithR dao = new RoleWithR();
			dao.setDataFromBase(rolewithr);
			int result = dao.save();
			if(singleTransaction) ThreadConnection.commit();
			tsc.recordTime();
			__logger.info(String.format("Add to RoleWithR time used : [%1$d]", tsc.getLastTime()));
			return result==1?dao.generateBaseExt():null;
		} catch(Exception ex) {
			if(singleTransaction) ThreadConnection.rollback();
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseRoleWithR addUpdateRoleWithR ( BaseRoleWithR rolewithr ) {
		return addUpdateRoleWithR ( rolewithr , false);
	}

	public static BaseRoleWithR addUpdateRoleWithR ( BaseRoleWithR rolewithr, boolean singleTransaction  ) {
		if(rolewithr.getRoleId() == null) return addToRoleWithR(rolewithr);
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			if(singleTransaction) ThreadConnection.beginTransaction();
			RoleWithR dao = new RoleWithR();
			dao.setDataFromBase(rolewithr);
			int result = 0;
			if(dao.load()) {
				dao.setDataFromBase(rolewithr); 
				if(dao.isThisObjectModified()) { 
					result = dao.update();
				} else result = 1;
			} else result = dao.save();
			if(singleTransaction) ThreadConnection.commit();
			tsc.recordTime();
			__logger.info(String.format("Add to RoleWithR time used : [%1$d]", tsc.getLastTime()));
			return result==1?dao.generateBaseExt():null;
		} catch(Exception ex) {
			if(singleTransaction) ThreadConnection.rollback();
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseRoleWithR deleteRoleWithR ( BaseRoleWithR bean ) {
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			RoleWithR dao = new RoleWithR();
			setDeleteConditions(bean, dao);
			int result = dao.conditionalDelete();
			tsc.recordTime();
			__logger.info(String.format("Delete RoleWithR time used : [%1$d]", tsc.getLastTime()));
			return result == 1 ? bean : null ;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseRoleWithR updateRoleWithR ( BaseRoleWithR rolewithr ) {
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			RoleWithR dao = new RoleWithR();
			dao.setRoleId( rolewithr.getRoleId() );
			int result = 0;
			if( dao.load() ) {
				dao.setDataFromBase(rolewithr);
				result = dao.update();
			}
			tsc.recordTime();
			__logger.info(String.format("Update RoleWithR time used : [%1$d]", tsc.getLastTime()));
			return result == 1 ? rolewithr : null ;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static BaseRoleWithR updateRoleWithRDirect( BaseRoleWithR rolewithr ) {
		try{
			TimeSpanCalculator tsc = new TimeSpanCalculator();
			tsc.recordTime();
			RoleWithR dao = new RoleWithR();
			int result = 0;
			dao.setDataFromBase(rolewithr);
			result = dao.update();
			tsc.recordTime();
			__logger.info(String.format("Update RoleWithR time used : [%1$d]", tsc.getLastTime()));
			return result == 1 ? rolewithr : null ;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static int setDeleteConditions(BaseRoleWithR bean, RoleWithR dao){
		int count = 0;
		boolean foundKey = false;
		if(bean.getRoleId() != null) {
			dao.setRoleId(bean.getRoleId());
			dao.setConditionRoleId("=", bean.getRoleId());
			count++;
			foundKey = true;
		}
		if(!foundKey) {
			if(bean.getRoleName() != null) {
				dao.setConditionRoleName("=", bean.getRoleName());
				count++;
			}
			if(bean.getApplicationId() != null) {
				dao.setConditionApplicationId("=", bean.getApplicationId());
				count++;
			}
			if(bean.getRoleType() != null) {
				dao.setConditionRoleType("=", bean.getRoleType());
				count++;
			}
			if(bean.getEnabled() != null) {
				dao.setConditionEnabled("=", bean.getEnabled());
				count++;
			}
			if(bean.getHasApprovalRight() != null) {
				dao.setConditionHasApprovalRight("=", bean.getHasApprovalRight());
				count++;
			}
		}
		return count;
	}

	public static int setConditions(BaseRoleWithR bean, RoleWithR dao){
		int count = 0;
		if(bean.getRoleId() != null) {
			dao.setConditionRoleId("=", bean.getRoleId());
			count++;
		}
		if(bean.getRoleName() != null) {
			if(bean.getRoleName().indexOf("%") >= 0)
				dao.setConditionRoleName("like", bean.getRoleName());
			else
				dao.setConditionRoleName("=", bean.getRoleName());
			count++;
		}
		if(bean.getApplicationId() != null) {
			dao.setConditionApplicationId("=", bean.getApplicationId());
			count++;
		}
		if(bean.getRoleType() != null) {
			dao.setConditionRoleType("=", bean.getRoleType());
			count++;
		}
		if(bean.getEnabled() != null) {
			dao.setConditionEnabled("=", bean.getEnabled());
			count++;
		}
		if(bean.getHasApprovalRight() != null) {
			dao.setConditionHasApprovalRight("=", bean.getHasApprovalRight());
			count++;
		}
		return count;
	}

	@Override
	public String findUsingKey(String json) throws Exception{
		BaseRoleWithR bean = new BaseRoleWithR();
		bean.setDataFromJSON(json);
		RoleWithR dao = new RoleWithR();
		dao.setPrimaryKeyFromBase(bean);
		if(dao.load(true)) {
			dao.setDataToBase(bean);
			return bean.toOneLineJSON(1,null);
		}
		return bean.toOneLineJSON(0,"Record not found.");
	}

	@Override
	public String find(String json, int currentPage, int pageSize, String addtion) throws Exception{
		BaseCollection<BaseRoleWithR> rlist = new BaseCollection<>();
		BaseRoleWithR bean = new BaseRoleWithR();
		JSON parser = new JSON(new StringReader(json));
		Map<String, Object> params = (Map<String, Object>) parser.parse(new BasicHandler());
		bean.setDataFromMap(params);
		PaginationParameter pp = PaginationParameter.generateFromMap(params);
		if(pp != null){
			if(pp.getCurrentPage() > 0) currentPage = pp.getCurrentPage();
			if(pp.getPageSize() > 0) pageSize = pp.getPageSize();
			if(pp.getCondition() != null) addtion = pp.getCondition();
		}
		RoleWithR dao = new RoleWithR();
		setConditions(bean, dao);
                //过滤超级管理员的角色
                dao.setConditionRoleId(">",0);
		dao.setCurrentPage(currentPage);
		dao.setPageLines(pageSize);
		List<BaseRoleWithR> result = dao.conditionalLoadExt(addtion);
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
		BaseRoleWithR bean = new BaseRoleWithR();
		bean.setDataFromJSON(json);
		RoleWithR dao = new RoleWithR();
		dao.setDataFromBase(bean);
		int num = dao.save();
		dao.setDataToBase(bean);
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String saveOrUpdate(String json) throws Exception{
		BaseRoleWithR bean = new BaseRoleWithR();
		bean.setDataFromJSON(json);
		int num = 0;
		RoleWithR dao = new RoleWithR();
		dao.setDataFromBase(bean);
		if(dao.isPrimaryKeyNull()) num = dao.save();
		else if(dao.load()){ dao.setDataFromBase(bean); num = dao.update(); }
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String update(String json) throws Exception{
		BaseRoleWithR bean = new BaseRoleWithR();
		bean.setDataFromJSON(json);
		RoleWithR dao = new RoleWithR();
		dao.setPrimaryKeyFromBase(bean);
		int num=0;
		if(dao.load()){dao.setDataFromBase(bean); num = dao.update(); }
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String delete(String json) throws Exception{
		BaseRoleWithR bean = new BaseRoleWithR();
		bean.setDataFromJSON(json);
		RoleWithR dao = new RoleWithR();
		setDeleteConditions(bean, dao);
		int num = dao.conditionalDelete();
		return bean.toOneLineJSON(num, null);
	}

}


