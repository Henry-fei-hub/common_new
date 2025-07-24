/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delicacy.system.executor;

import java.io.StringReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import javax.servlet.http.HttpServletRequest;

import delicacy.bean.utils.SystemLogUtils;
import delicacy.common.BaseCollection;
import delicacy.common.BaseHelpUtils;
import delicacy.common.GenericProcessor;
import delicacy.connection.ThreadConnection;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import delicacy.system.bean.BaseDepartmentRoleFunction;
import delicacy.system.bean.BaseEmployeeFunction;
import delicacy.system.bean.BaseFunction;
import delicacy.system.bean.BaseRoleFunction;
import delicacy.system.dao.EmployeeFunction;
import delicacy.system.dao.Function;
import delicacy.system.dao.RoleFunction;

/**
 * @author lsf
 */
public class UpdateRoleDepartmentEmployeeFunction implements GenericProcessor {
    // 操作类型
    private static final String OPT_TYPE = "optType";
	//日志类型：角色权限
	private static final int LOG_TYPE = 1;
    // 保存人员功能信息
    private static final String EMPLOYEE_DATA = "employeeData";
    // 保存角色功能信息
    private static final String ROLE_DATA = "roleData";
    // 保存部门角色功能信息
    private static final String DEPARTMENT_ROLE_DATA = "departmentRoleData";
    // 获取功能信息
    private static final String FUNCTION_DATA = "functionData";

    @Override
    public String execute(String creteria, HttpServletRequest request) throws Exception {

        @SuppressWarnings("rawtypes")
        JSON parser = new JSON(new StringReader(creteria));
        System.out.println(parser);
        @SuppressWarnings("unchecked")
        Map<String, Object> param = (Map<String, Object>) parser.parse(new BasicHandler());
        // 获取操作类型
        String optType = BaseHelpUtils.getStringValue(param, OPT_TYPE);

        switch (optType) {
            case EMPLOYEE_DATA:
                return dealEmployeeInfo(param);
            case ROLE_DATA:
                return dealRoleInfo(param);
            case DEPARTMENT_ROLE_DATA:
                return dealDepartmentRoleInfo(param);
            case FUNCTION_DATA:
                return getFunctionData(param);
            default:
                BaseCollection bc = new BaseCollection<>();
                return bc.toJSON();
        }

    }


    /**
     * 处理employee_functions表中的数据
     *
     * @param param
     * @return
     * @throws SQLException
     */
    public String dealEmployeeInfo(Map<String, Object> param) {
        List<BaseEmployeeFunction> efList = new ArrayList<>();
        BaseEmployeeFunction efBean;
        EmployeeFunction efDao;
        BaseCollection bc = new BaseCollection<>();
        try {
            efDao = new EmployeeFunction();
            if (BaseHelpUtils.isNullOrEmpty(param.get("employeeId"))) {
                return bc.toJSON(-1, "请选择人员");
            }
            ThreadConnection.beginTransaction();
            efDao.setConditionEmployeeId("=", BaseHelpUtils.getIntValue(param.get("employeeId")));
            efDao.conditionalDelete();

            List<Map<String, Object>> list = (List<Map<String, Object>>) param.get("detailRoleFunction");
            if (!BaseHelpUtils.isNullOrEmpty(list)) {
            	int operateEmployeeId = BaseHelpUtils.getIntValue(param, "operateEmployeeId");
        		String operatorStr = SelectValueCache.getSelectValue("employees", operateEmployeeId+"");
        		if(BaseHelpUtils.isNullOrEmpty(operatorStr)) {
        			operatorStr = operateEmployeeId+""; 
        		}
        		int employeeId = BaseHelpUtils.getIntValue(param, "employeeId");
        		String employeeStr = SelectValueCache.getSelectValue("employees", employeeId+"");
        		if(BaseHelpUtils.isNullOrEmpty(employeeStr)) {
        			employeeStr = employeeId+""; 
        		}
        		StringJoiner sj = new StringJoiner(";");
                for (Map<String, Object> map : list) {
                    efBean = new BaseEmployeeFunction();
                    efBean.setEmployeeId(employeeId);
                    efBean.setFunctionId(BaseHelpUtils.getIntValue(map.get("functionId")));
                    efBean.setApplicationId(BaseHelpUtils.getIntValue(map.get("applicationId")));
                    efList.add(efBean);
                    sj.add(BaseHelpUtils.getString(map.get("functionName")));
                }
                efDao.clear();
                efDao.save(efList);
                String logContent = String.format("职员【%1$s】将职员【%2$s】的个人权限重置为：%3$s", operatorStr, employeeStr, sj.toString());
				SystemLogUtils.saveSystemLog(operateEmployeeId, operatorStr, logContent, LOG_TYPE, null);
            }
            ThreadConnection.commit();
        } catch (SQLException e) {
            ThreadConnection.rollback();
            e.printStackTrace();
        }

        return bc.toJSON(1, "");

    }

    /**
     * 处理role_functions表中的数据
     *
     * @param param
     * @param bfList
     * @return
     * @throws SQLException
     */
    public String dealRoleInfo(Map<String, Object> param) {
        List<BaseRoleFunction> rfList = new ArrayList<>();
        BaseRoleFunction rfBean = null;
        RoleFunction rfDao;
        BaseCollection bc = new BaseCollection<>();
        try {
            rfDao = new RoleFunction();
            if (BaseHelpUtils.isNullOrEmpty(param.get("roleId"))) {
                return bc.toJSON(-1, "请选择角色");
            }
            ThreadConnection.beginTransaction();
            rfDao.setConditionRoleId("=", BaseHelpUtils.getIntValue(param.get("roleId")));
            rfDao.conditionalDelete();

            List<Map<String, Object>> list = (List<Map<String, Object>>) param.get("detailRoleFunction");
            if (!BaseHelpUtils.isNullOrEmpty(list)) {
            	int operateEmployeeId = BaseHelpUtils.getIntValue(param, "operateEmployeeId");
        		String operatorStr = SelectValueCache.getSelectValue("employees", operateEmployeeId+"");
        		if(BaseHelpUtils.isNullOrEmpty(operatorStr)) {
        			operatorStr = operateEmployeeId+""; 
        		}
        		int roleId = BaseHelpUtils.getIntValue(param, "roleId");
        		String roleStr = SelectValueCache.getSelectValue("roles", roleId+"");
        		if(BaseHelpUtils.isNullOrEmpty(roleStr)) {
        			roleStr = roleId+""; 
        		}
        		StringJoiner sj = new StringJoiner(";");
                for (Map<String, Object> map : list) {
                    rfBean = new BaseRoleFunction();
                    rfBean.setRoleId(roleId);
                    rfBean.setFunctionId(BaseHelpUtils.getIntValue(map.get("functionId")));
                    rfBean.setApplicationId(BaseHelpUtils.getIntValue(map.get("applicationId")));
                    rfList.add(rfBean);
                    sj.add(BaseHelpUtils.getString(map.get("functionName")));
                }
                rfDao.clear();
                rfDao.save(rfList);
                String logContent = String.format("职员【%1$s】将角色【%2$s】的权限重置为：%3$s", operatorStr, roleStr, sj.toString());
				SystemLogUtils.saveSystemLog(operateEmployeeId, operatorStr, logContent, LOG_TYPE, null);
            }

            ThreadConnection.commit();
        } catch (SQLException e) {
            ThreadConnection.rollback();
            e.printStackTrace();
        }

        return bc.toJSON(1, "");

    }

    /**
     * 处理department_role_functions表中的数据
     *
     * @param param
     * @param bfList
     * @return
     * @throws SQLException
     */
    public String dealDepartmentRoleInfo(Map<String, Object> param) {
        BaseDepartmentRoleFunction drfBean = null;
        delicacy.system.dao.DepartmentRoleFunction drfDao;
        List<BaseDepartmentRoleFunction> drfList = new ArrayList<>();
        BaseCollection bc = new BaseCollection<>();
        try {
            drfDao = new delicacy.system.dao.DepartmentRoleFunction();
            if (BaseHelpUtils.isNullOrEmpty(param.get("roleId")) || BaseHelpUtils.isNullOrEmpty(param.get("departmentId"))) {
                return bc.toJSON(-1, "请选择部门角色");
            }
            ThreadConnection.beginTransaction();
            drfDao.setConditionRoleId("=", BaseHelpUtils.getIntValue(param.get("roleId")));
            drfDao.setConditionDepartmentId("=", BaseHelpUtils.getIntValue(param.get("departmentId")));
            //	drfDao.setConditionDepartmentRoleId("=", BaseHelpUtils.getIntValue(param.get("departmentRoleId")));
            drfDao.conditionalDelete();

            List<Map<String, Object>> list = (List<Map<String, Object>>) param.get("detailRoleFunction");
            if (!BaseHelpUtils.isNullOrEmpty(list)) {
            	int operateEmployeeId = BaseHelpUtils.getIntValue(param, "operateEmployeeId");
        		String operatorStr = SelectValueCache.getSelectValue("employees", operateEmployeeId+"");
        		if(BaseHelpUtils.isNullOrEmpty(operatorStr)) {
        			operatorStr = operateEmployeeId+""; 
        		}
        		int roleId = BaseHelpUtils.getIntValue(param, "roleId");
        		String roleStr = SelectValueCache.getSelectValue("roles", roleId+"");
        		if(BaseHelpUtils.isNullOrEmpty(roleStr)) {
        			roleStr = roleId+""; 
        		}
        		int departmentId = BaseHelpUtils.getIntValue(param, "departmentId");
        		String departmentStr = SelectValueCache.getSelectValue("all_departments", departmentId+"");
        		if(BaseHelpUtils.isNullOrEmpty(departmentStr)) {
        			departmentStr = departmentId+""; 
        		}
        		StringJoiner sj = new StringJoiner(";");
                for (Map<String, Object> map : list) {
                    drfBean = new BaseDepartmentRoleFunction();
                    drfBean.setRoleId(roleId);
                    drfBean.setDepartmentId(departmentId);
                    //drfBean.setDepartmentRoleId(BaseHelpUtils.getIntValue(param.get("departmentRoleId")));
                    drfBean.setFunctionId(BaseHelpUtils.getIntValue(map.get("functionId")));
                    drfBean.setApplicationId(BaseHelpUtils.getIntValue(map.get("applicationId")));
                    drfList.add(drfBean);
                    sj.add(BaseHelpUtils.getString(map.get("functionName")));
                }
                drfDao.clear();
                drfDao.save(drfList);
                String logContent = String.format("职员【%1$s】将部门【%2$s】下的角色【%3$s】的权限重置为：%4$s", operatorStr, departmentStr, roleStr, sj.toString());
				SystemLogUtils.saveSystemLog(operateEmployeeId, operatorStr, logContent, LOG_TYPE, null);
            }

            ThreadConnection.commit();
        } catch (SQLException e) {
            ThreadConnection.rollback();
            e.printStackTrace();
        }


        return bc.toJSON(1, "");
    }

    /**
     * 根据赋权对象查询相对应的功能信息
     *
     * @param param
     * @return
     * @throws SQLException
     */
    public String getFunctionData(Map<String, Object> param) throws SQLException {

        BaseCollection bc = new BaseCollection<>();
        if (!BaseHelpUtils.isNullOrEmpty(param.get("employeeId"))) {
            // 查询表employee_functions数据
            EmployeeFunction efDao = new EmployeeFunction();
            efDao.setConditionEmployeeId("=", BaseHelpUtils.getIntValue(param.get("employeeId")));
            List<BaseEmployeeFunction> efList = efDao.conditionalLoad();
            bc.setCollections(efList);
            return bc.toJSON();

        }
        if (!BaseHelpUtils.isNullOrEmpty(param.get("roleId"))) {
            // 查询表role_functions数据
            RoleFunction rfDao = new RoleFunction();
            rfDao.setConditionRoleId("=", BaseHelpUtils.getIntValue(param.get("roleId")));
            List<BaseRoleFunction> rfList = rfDao.conditionalLoad();
            bc.setCollections(rfList);
            return bc.toJSON();
        }
        if (!BaseHelpUtils.isNullOrEmpty(param.get("departmentRole")) && !BaseHelpUtils.isNullOrEmpty(param.get("departmentId"))) {
            // 查询表department_role_functions数据
            delicacy.system.dao.DepartmentRoleFunction drfDao = new delicacy.system.dao.DepartmentRoleFunction();
//			drfDao.setConditionDepartmentRoleId("=", BaseHelpUtils.getIntValue(param.get("departmentRoleId")));
            drfDao.setConditionRoleId("=", BaseHelpUtils.getIntValue(param.get("departmentRole")));
            drfDao.setConditionDepartmentId("=", BaseHelpUtils.getIntValue(param.get("departmentId")));
            List<BaseDepartmentRoleFunction> drfList = drfDao.conditionalLoad();
            bc.setCollections(drfList);
            return bc.toJSON();
        }
        return null;
    }



    public static void main(String[] args) throws Exception {
        // String string =
        // "{'detailRoleFunction':[{'functionId':'7','applicationId':'1'},
        // {'functionId':'36','applicationId':'1'},{'functionId':'23','applicationId':'1'}]}";
        //
        // // Map<String, Object> param = new HashMap<>();
        // // param.put("roleName", "测");
        // // param.put("departmentId", 1);
        // new UpdateRoleDepartmentEmployeeFunction().execute(string, null);
        // EmployeeFunction efDao = new EmployeeFunction();
        // efDao.setEmployeeId(1);
        // efDao.setFunctionId(1);
        // efDao.setApplicationId(0);
        // efDao.save();
    }

}
