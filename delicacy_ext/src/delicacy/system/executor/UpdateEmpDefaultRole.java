package delicacy.system.executor;

import delicacy.common.BaseCollection;
import delicacy.common.BaseHelpUtils;
import delicacy.common.GenericProcessor;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import delicacy.system.bean.BaseEmployeeRole;
import delicacy.system.bean.BaseRole;
import delicacy.system.dao.EmployeeRole;
import delicacy.system.dao.Role;

import java.io.StringReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class UpdateEmpDefaultRole implements GenericProcessor {

    private static final Logger __logger = Logger.getLogger(UpdateEmpDefaultRole.class);
    
    private static final String SET_DEFAULT_ROLE = "setDefaultRole";
    
    private static final String GET_DEFAULT_ROLE = "getDefaultRole";
    
    /**
     * 
     * @Title: getDefaultRole 
     * @Description: 查询员工默认角色信息
     * @param @param result
     * @param @return
     * @param @throws SQLException   
     * @return String   
     * @throws
     */
    private String getDefaultRole(Map<String, Object> result) throws SQLException{
    	int employeeId = BaseHelpUtils.getIntValue(result,"employeeId");
    	EmployeeRole erDao = new EmployeeRole();
    	erDao.setConditionEmployeeId("=", employeeId);
    	erDao.setConditionIsDefault("=", Boolean.TRUE);
    	List<BaseEmployeeRole> ds = erDao.conditionalLoad();
    	BaseCollection<BaseEmployeeRole> bc = new BaseCollection<>();
    	bc.setCollections(ds);
    	return bc.toJSON(1, "");
    }
    
    /**
     * 
     * @Title: setDefaultRole 
     * @Description: 设置员工默认角色信息
     * @param @param result
     * @param @return
     * @param @throws SQLException   
     * @return String   
     * @throws
     */
    private String setDefaultRole(Map<String, Object> result) throws SQLException{
    	int employeeId = BaseHelpUtils.getIntValue(result,"employeeId");
        int departmentId = BaseHelpUtils.getIntValue(result.get("departmentId"));
        int roleId = BaseHelpUtils.getIntValue(result.get("roleId"));
        EmployeeRole erDao = new EmployeeRole();
        erDao.setConditionEmployeeId("=", employeeId);
        erDao.setConditionDepartmentId("=", departmentId);
        erDao.setConditionRoleId("=", roleId);
        BaseEmployeeRole bean = erDao.executeQueryOneRow();
        if(!bean.getIsDefault()){
        	//清除原有默认角色
        	erDao.clear();
        	erDao.setConditionEmployeeId("=", employeeId);
        	erDao.setConditionIsDefault("=", Boolean.TRUE);
        	erDao.setIsDefault(Boolean.FALSE);
        	erDao.conditionalUpdate();
        	//设置为默认角色
        	erDao.clear();
        	erDao.setIsDefault(Boolean.TRUE);
        	erDao.setConditionEmployeeId("=", employeeId);
        	erDao.setConditionDepartmentId("=", departmentId);
        	erDao.setConditionRoleId("=", roleId);
        	erDao.conditionalUpdate();
        }
        BaseCollection bc = new BaseCollection<>();
        return bc.toJSON(1, "");
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public String execute(String creteria, HttpServletRequest request) throws Exception {
        Map<String, Object> result = null;
        if (!BaseHelpUtils.isNullOrEmpty(creteria)) {
            JSON parser = new JSON(new StringReader(creteria));
            result = (Map<String, Object>) parser.parse(new BasicHandler());
        }
        String optType = BaseHelpUtils.getStringValue(result, "opt_type");
        if(!BaseHelpUtils.isNullOrEmpty(result) && !BaseHelpUtils.isNullOrEmpty(optType)){
        	switch (optType) {
    		case SET_DEFAULT_ROLE:
    			return setDefaultRole(result);
    		case GET_DEFAULT_ROLE:
    			return getDefaultRole(result);
    		}
        }
    	return null;
    }
}
