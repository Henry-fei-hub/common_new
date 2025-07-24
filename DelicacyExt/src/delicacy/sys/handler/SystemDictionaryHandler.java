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
import delicacy.sys.bean.BaseSystemDictionary;
import delicacy.sys.dao.SystemDictionary;

public class SystemDictionaryHandler implements GenericDao {

	private static final Logger __logger = Logger.getLogger(SystemDictionaryHandler.class);

	public static BaseSystemDictionary getSystemDictionaryById( 
		java.lang.Integer dictionary_id
	) throws Exception
	{
		SystemDictionary dao = new SystemDictionary();
		dao.setDictionaryId(dictionary_id);
		if(dao.load()){
			return dao.generateBase();
		}
		return null;
	}

	public static boolean isSystemDictionaryExists(BaseSystemDictionary bean, String additional ) throws Exception {

		SystemDictionary dao = new SystemDictionary();
		setConditions(bean, dao);
		boolean res = dao.isExist(additional);
		return res;
	}

	public static int countSystemDictionary(BaseSystemDictionary bean, String additional ) throws Exception {

		SystemDictionary dao = new SystemDictionary();
		setConditions(bean, dao);
		int res = dao.countRows(additional);
		return res;
	}

	public static BaseCollection<BaseSystemDictionary> querySystemDictionary(BaseSystemDictionary bean, int pageNo, int pageLines, String additionalCondition ) throws Exception {
		SystemDictionary dao = new SystemDictionary();
		setConditions(bean, dao);
		dao.setCurrentPage(pageNo);
		dao.setPageLines(pageLines);
		java.util.List<BaseSystemDictionary> result = dao.conditionalLoad(additionalCondition);
		BaseCollection<BaseSystemDictionary> col = new BaseCollection<>();
		col.setCollections(result);
		col.setTotalPages(dao.getTotalPages());
		col.setCurrentPage(dao.getCurrentPage());
		col.setPageLines(dao.getPageLines());
		col.setTotalLines(dao.getTotalLines());
		col.setRecordNumber(result.size());
		return col;
	}

	public static BaseSystemDictionary addToSystemDictionary ( BaseSystemDictionary systemdictionary )  throws Exception {
		return addToSystemDictionary ( systemdictionary , false);
	}

	public static BaseSystemDictionary addToSystemDictionary ( BaseSystemDictionary systemdictionary, boolean singleTransaction ) throws Exception {
		if(singleTransaction) ThreadConnection.beginTransaction();
		SystemDictionary dao = new SystemDictionary();
		dao.setDataFromBase(systemdictionary);
		int result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static BaseSystemDictionary addUpdateSystemDictionary ( BaseSystemDictionary systemdictionary ) throws Exception {
		return addUpdateSystemDictionary ( systemdictionary , false);
	}

	public static BaseSystemDictionary addUpdateSystemDictionary ( BaseSystemDictionary systemdictionary, boolean singleTransaction  ) throws Exception {
		if(systemdictionary.getDictionaryId() == null) return addToSystemDictionary(systemdictionary);
		if(singleTransaction) ThreadConnection.beginTransaction();
		StringBuilder sb = new StringBuilder();
		SystemDictionary dao = new SystemDictionary();
		dao.setDataFromBase(systemdictionary);
		int result = 0;
		if(dao.load()) {
			dao.setDataFromBase(systemdictionary); 
			if(dao.isThisObjectModified()) { 
				result = dao.update();
			} else result = 1;
		} else result = dao.save();
		if(singleTransaction) ThreadConnection.commit();
		return result==1?dao.generateBase():null;
	}

	public static int deleteSystemDictionary ( BaseSystemDictionary bean ) throws Exception {
		SystemDictionary dao = new SystemDictionary();
		if(setDeleteConditions(bean, dao) == 0) return 0;
		int result = dao.conditionalDelete();
		return result ;
	}

	public static BaseSystemDictionary updateSystemDictionary ( BaseSystemDictionary systemdictionary ) throws Exception {
		SystemDictionary dao = new SystemDictionary();
		dao.setDictionaryId( systemdictionary.getDictionaryId() );
		int result = 0;
		if( dao.load() ) {
			dao.setDataFromBase(systemdictionary);
			result = dao.update();
		}
		return result == 1 ? systemdictionary : null ;
	}

	public static BaseSystemDictionary updateSystemDictionaryDirect( BaseSystemDictionary systemdictionary ) throws Exception {
		SystemDictionary dao = new SystemDictionary();
		int result = 0;
		dao.setDataFromBase(systemdictionary);
		result = dao.update();
		return result == 1 ? systemdictionary : null ;
	}

	public static int setDeleteConditions(BaseSystemDictionary bean, SystemDictionary dao){
		int count = 0;
		boolean foundKey = false;
		if(bean.getDictionaryId() != null) {
			dao.setConditionDictionaryId("=", bean.getDictionaryId());
			count++;
			foundKey = true;
		}
		if(!foundKey) {
			if(bean.getDicTypeId() != null) {
				dao.setConditionDicTypeId("=", bean.getDicTypeId());
				count++;
			}
			if(bean.getDicTypeName() != null) {
				dao.setConditionDicTypeName("=", bean.getDicTypeName());
				count++;
			}
			if(bean.getDicTypeValueId() != null) {
				dao.setConditionDicTypeValueId("=", bean.getDicTypeValueId());
				count++;
			}
			if(bean.getDicTypeValue() != null) {
				dao.setConditionDicTypeValue("=", bean.getDicTypeValue());
				count++;
			}
			if(bean.getDicIsEnable() != null) {
				dao.setConditionDicIsEnable("=", bean.getDicIsEnable());
				count++;
			}
			if(bean.getParentId() != null) {
				dao.setConditionParentId("=", bean.getParentId());
				count++;
			}
			if(bean.getManagementType() != null) {
				dao.setConditionManagementType("=", bean.getManagementType());
				count++;
			}
			if(bean.getOrganizationId() != null) {
				dao.setConditionOrganizationId("=", bean.getOrganizationId());
				count++;
			}
		}
		return count;
	}

	public static int setConditions(BaseSystemDictionary bean, SystemDictionary dao){
		int count = 0;
		if(bean.getDictionaryId() != null) {
			dao.setConditionDictionaryId("=", bean.getDictionaryId());
			count++;
		}
		if(bean.getDicTypeId() != null) {
			dao.setConditionDicTypeId("=", bean.getDicTypeId());
			count++;
		}
		if(bean.getDicTypeName() != null) {
			if(bean.getDicTypeName().indexOf("%") >= 0)
				dao.setConditionDicTypeName("like", bean.getDicTypeName());
			else
				dao.setConditionDicTypeName("=", bean.getDicTypeName());
			count++;
		}
		if(bean.getDicTypeValueId() != null) {
			dao.setConditionDicTypeValueId("=", bean.getDicTypeValueId());
			count++;
		}
		if(bean.getDicTypeValue() != null) {
			if(bean.getDicTypeValue().indexOf("%") >= 0)
				dao.setConditionDicTypeValue("like", bean.getDicTypeValue());
			else
				dao.setConditionDicTypeValue("=", bean.getDicTypeValue());
			count++;
		}
		if(bean.getDicIsEnable() != null) {
			dao.setConditionDicIsEnable("=", bean.getDicIsEnable());
			count++;
		}
		if(bean.getParentId() != null) {
			dao.setConditionParentId("=", bean.getParentId());
			count++;
		}
		if(bean.getManagementType() != null) {
			dao.setConditionManagementType("=", bean.getManagementType());
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
		BaseSystemDictionary bean = new BaseSystemDictionary();
		bean.setDataFromJSON(json);
		SystemDictionary dao = new SystemDictionary();
		dao.setPrimaryKeyFromBase(bean);
		if(dao.load()) { dao.setDataToBase(bean); return bean.toOneLineJSON(1,null); }
		return bean.toOneLineJSON(0,"Record not found.");
	}

	@Override
	public String find(String json, int currentPage, int pageSize, String addtion) throws Exception{
		BaseCollection<BaseSystemDictionary> rlist = new BaseCollection<>();
		BaseSystemDictionary bean = new BaseSystemDictionary();
		JSON parser = new JSON(new StringReader(json));
		Map<String, Object> params = (Map<String, Object>) parser.parse(new BasicHandler());
		bean.setDataFromMap(params);
		PaginationParameter pp = PaginationParameter.generateFromMap(params);
		if(pp != null){
			if(pp.getCurrentPage() > 0) currentPage = pp.getCurrentPage();
			if(pp.getPageSize() > 0) pageSize = pp.getPageSize();
			if(pp.getCondition() != null) addtion = pp.getCondition();
		}
		SystemDictionary dao = new SystemDictionary();
		setConditions(bean, dao);
		dao.setCurrentPage(currentPage);
		dao.setPageLines(pageSize);
		List<BaseSystemDictionary> result = dao.conditionalLoad(addtion);
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
		BaseSystemDictionary bean = new BaseSystemDictionary();
		bean.setDataFromJSON(json);
		SystemDictionary dao = new SystemDictionary();
		dao.setDataFromBase(bean);
		int num = dao.save();
		dao.setDataToBase(bean);
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String update(String json) throws Exception{
		BaseSystemDictionary bean = new BaseSystemDictionary();
		bean.setDataFromJSON(json);
		SystemDictionary dao = new SystemDictionary();
		dao.setPrimaryKeyFromBase(bean);
		int num=0;
		if(dao.load()){dao.setDataFromBase(bean); num = dao.update(); }
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String delete(String json) throws Exception{
		BaseSystemDictionary bean = new BaseSystemDictionary();
		bean.setDataFromJSON(json);
		SystemDictionary dao = new SystemDictionary();
		setDeleteConditions(bean, dao);
		int num = dao.conditionalDelete();
		return bean.toOneLineJSON(num, null);
	}

	@Override
	public String saveOrUpdate(String json) throws Exception{
		BaseSystemDictionary bean = new BaseSystemDictionary();
		bean.setDataFromJSON(json);
		SystemDictionary dao = new SystemDictionary();
		dao.setPrimaryKeyFromBase(bean);
		int ret = 0;
		if(dao.isPrimaryKeyNull()) { dao.setDataFromBase(bean); ret = dao.save(); bean = dao.generateBase(); }
		else if(dao.load()) { dao.setDataFromBase(bean); ret = dao.update(); bean = dao.generateBase(); }
		return bean.toOneLineJSON(ret, null);
	}

}


