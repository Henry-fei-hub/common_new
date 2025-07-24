/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delicacy.system.executor;

import java.io.StringReader;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import delicacy.bean.utils.SystemLogUtils;
import delicacy.common.BaseCollection;
import delicacy.common.BaseHelpUtils;
import delicacy.common.GenericProcessor;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import delicacy.system.bean.BaseEmployeeRole;
import delicacy.system.bean.BaseRole;
import delicacy.system.dao.EmployeeRole;
import delicacy.system.dao.Role;
import delicacy.system.dao.SystemLog;

/**
 *
 * @author lsf
 */
public class EditorRoleAndDepartmentRoleToEmp implements GenericProcessor {
	// 操作类型
	private static final String OPT_TYPE = "optType";
	//日志类型：角色权限
	private static final int LOG_TYPE = 1;
	// 查询个人的角色信息
	private static final String FIND_PERSONAL_ROLE = "findPersonalRole";
	// 查询个人的部门角色信息
	private static final String FIND_PERSONAL_DEPARTMENT_ROLE = "findPersonalDepartmentRole";
	// 修改个人的角色信息
	private static final String MODIFY_PERSONAL_ROLE = "modifyPersonalRole";
	// 修改个人的部门角色信息
	private static final String MODIFY_PERSONAL_DEPARTMENT_ROLE = "modifyPersonalDepartmentRole";
	//设置默认角色
	private static final String ON_SET_DEFAULT_ROLE = "onSetDefaultRole";
	
	

	BaseCollection bc = new BaseCollection<>();

	@Override
	public String execute(String creteria, HttpServletRequest request) throws Exception {

		@SuppressWarnings("rawtypes")
		JSON parser = new JSON(new StringReader(creteria));
		System.out.println(parser);
		@SuppressWarnings("unchecked")
		Map<String, Object> params = (Map<String, Object>) parser.parse(new BasicHandler());
		// 获取操作类型
		String optType = BaseHelpUtils.getStringValue(params, OPT_TYPE);

		switch (optType) {

		case FIND_PERSONAL_ROLE:
			return findPersonalRole(params);
		case FIND_PERSONAL_DEPARTMENT_ROLE:
			return findPersonalDepartmentRole(params);
		case MODIFY_PERSONAL_ROLE:
			return modifyPersonalRole(params);
		case MODIFY_PERSONAL_DEPARTMENT_ROLE:
			return modifyPersonalDepartmentRole(params);
		case ON_SET_DEFAULT_ROLE:
			return onSetDefaultRole(params);
		default:
			return bc.toJSON(1, "");
		}
	}
	
	/**
	 * 设置默认角色
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public String onSetDefaultRole(Map<String, Object>  params) throws SQLException{
		//获取部门Id和角色Id和人员Id
		int employeeId = BaseHelpUtils.getIntValue(params, "employeeId");
		int departmentId= BaseHelpUtils.getIntValue(params, "departmentId");
		int roleId = BaseHelpUtils.getIntValue(params, "roleId");
		if(employeeId == 0){
			throw new SQLException("操作异常：未获取到职员编码信息");
		}
		if(departmentId == 0){
			throw new SQLException("操作异常：未获取到部门编码信息");
		}
		if(roleId == 0){
			throw new SQLException("操作异常：未获取到角色编码信息");
		}
		EmployeeRole erDao = new EmployeeRole();
		erDao.setConditionEmployeeId("=",employeeId);
		erDao.setConditionIsDefault("=",Boolean.TRUE);
		if(erDao.isExist()){
			erDao.setIsDefault(Boolean.FALSE);
			erDao.conditionalUpdate();
		}
		erDao.clear();
		erDao.setConditionEmployeeId("=",employeeId);
		erDao.setConditionDepartmentId("=",departmentId);
		erDao.setConditionRoleId("=",roleId);
		if(erDao.isExist()){
			erDao.setIsDefault(Boolean.TRUE);
			erDao.conditionalUpdate();
		}
		return bc.toJSON(0,"");
	}

	/**
	 * 查询个人的角色信息
	 * 
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public String findPersonalRole(Map<String, Object>  params) throws SQLException {
		int employeeId = BaseHelpUtils.getIntValue(params.get("employeeId"));
		// 1.查询出个人的角色id
		EmployeeRole erDao = new EmployeeRole();
		erDao.setConditionDepartmentId("=", 0);
		erDao.setConditionEmployeeId("=", employeeId);
		List<BaseEmployeeRole> erList = erDao.conditionalLoad();
		String roleIds = "";
		if (BaseHelpUtils.isNullOrEmpty(erList)) {
			return null;
		}
		for (BaseEmployeeRole baseEmployeeRole : erList) {
			roleIds = roleIds + baseEmployeeRole.getRoleId() + ",";
		}
		roleIds = roleIds.substring(0, roleIds.length() - 1);

		Role rDao = new Role();
		List<BaseRole> rList = rDao.conditionalLoad("role_id in (" + roleIds + ")");
		bc.setCollections(rList);
		return bc.toJSON();
	}

	/**
	 * 查询个人的部门角色信息
	 * 
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public String findPersonalDepartmentRole(Map<String, Object>  params) throws SQLException {
		int employeeId = BaseHelpUtils.getIntValue(params.get("employeeId"));
		EmployeeRole erDao = new EmployeeRole();
		erDao.setConditionDepartmentId("!=", 0);
		erDao.setConditionEmployeeId("=", employeeId);
		List<BaseEmployeeRole> erList = erDao.conditionalLoad(" order by department_id asc,role_id asc");
		bc.setCollections(erList);
		return bc.toJSON();
	}

	/**
	 * 修个人的角色信息
	 * 
	 * @param param
	 * @return
	 * @throws SQLException
	 */
	public String modifyPersonalRole(Map<String, Object> params) throws SQLException {

		int employeeId = BaseHelpUtils.getIntValue(params.get("employeeId"));
		int roleId = BaseHelpUtils.getIntValue(params.get("roleId"));
		// 1表示新增，2表示删除
		int flag = BaseHelpUtils.getIntValue(params.get("flag"));
		int operateEmployeeId = BaseHelpUtils.getIntValue(params, "operateEmployeeId");
		String operatorStr = SelectValueCache.getSelectValue("employees", operateEmployeeId+"");
		if(BaseHelpUtils.isNullOrEmpty(operatorStr)) {
			operatorStr = operateEmployeeId+""; 
		}
		String employeeStr = SelectValueCache.getSelectValue("employees", employeeId+"");
		if(BaseHelpUtils.isNullOrEmpty(employeeStr)) {
			employeeStr = employeeId+""; 
		}
		String roleStr = SelectValueCache.getSelectValue("roles", roleId+"");
		if(BaseHelpUtils.isNullOrEmpty(roleStr)) {
			roleStr = roleId+""; 
		}
		
		EmployeeRole erDao = new EmployeeRole();
		if (flag == 1) {
			erDao.setConditionEmployeeId("=", employeeId);
			erDao.setConditionDepartmentId("=", 0);
			erDao.setConditionRoleId("=", roleId);
			List<BaseEmployeeRole> erList = erDao.conditionalLoad();
			if (BaseHelpUtils.isNullOrEmpty(erList)) {
				erDao.clear();
				erDao.setEmployeeId(employeeId);
				erDao.setRoleId(roleId);
				erDao.setDepartmentId(0);
				erDao.save();
				String logContent = String.format("职员【%1$s】为职员【%2$s】新增了角色【%3$s】", operatorStr, employeeStr, roleStr);
				SystemLogUtils.saveSystemLog(operateEmployeeId, operatorStr, logContent, LOG_TYPE, null);
			}
		}
		if (flag == 2) {
			erDao.setConditionEmployeeId("=", employeeId);
			erDao.setConditionDepartmentId("=", 0);
			erDao.setConditionRoleId("=", roleId);
			int num = erDao.conditionalDelete();
			if(num != 0) {
				String logContent = String.format("职员【%1$s】为职员【%2$s】删除了角色【%3$s】", operatorStr, employeeStr, roleStr);
				SystemLogUtils.saveSystemLog(operateEmployeeId, operatorStr, logContent, LOG_TYPE, null);
			}
		}
		return bc.toJSON(1, "");
	}
	
	/**
	 * 修改个人的部门角色信息
	 * 
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public String modifyPersonalDepartmentRole(Map<String, Object> params) throws SQLException {
		int employeeId = BaseHelpUtils.getIntValue(params.get("employeeId"));
		int roleId = BaseHelpUtils.getIntValue(params.get("roleId"));
		int departmentId = BaseHelpUtils.getIntValue(params.get("departmentId"));
		// 1表示新增，2表示删除
		int flag = BaseHelpUtils.getIntValue(params.get("flag"));
		
		int operateEmployeeId = BaseHelpUtils.getIntValue(params, "operateEmployeeId");
		String operatorStr = SelectValueCache.getSelectValue("employees", operateEmployeeId+"");
		if(BaseHelpUtils.isNullOrEmpty(operatorStr)) {
			operatorStr = operateEmployeeId+""; 
		}
		String employeeStr = SelectValueCache.getSelectValue("employees", employeeId+"");
		if(BaseHelpUtils.isNullOrEmpty(employeeStr)) {
			employeeStr = employeeId+""; 
		}
		String roleStr = SelectValueCache.getSelectValue("roles", roleId+"");
		if(BaseHelpUtils.isNullOrEmpty(roleStr)) {
			roleStr = roleId+""; 
		}
		String departmentStr = SelectValueCache.getSelectValue("all_departments", departmentId+"");
		if(BaseHelpUtils.isNullOrEmpty(departmentStr)) {
			departmentStr = departmentId+""; 
		}
		
		EmployeeRole erDao = new EmployeeRole();
		if (flag == 1) {
			// 1.查询出该员工的部门角色
			erDao.setConditionEmployeeId("=", employeeId);
			erDao.setConditionDepartmentId("=", departmentId);
			erDao.setConditionRoleId("=", roleId);
			List<BaseEmployeeRole> erList = erDao.conditionalLoad();
			if (BaseHelpUtils.isNullOrEmpty(erList)) {
				erDao.clear();
				erDao.setEmployeeId(employeeId);
				erDao.setRoleId(roleId);
				erDao.setDepartmentId(departmentId);
				erDao.save();
				String logContent = String.format("职员【%1$s】为职员【%2$s】新增了部门【%3$s】角色【%4$s】", operatorStr, employeeStr, departmentStr, roleStr);
				SystemLogUtils.saveSystemLog(operateEmployeeId, operatorStr, logContent, LOG_TYPE, null);
			}
		}
		if (flag == 2) {
			erDao.setConditionEmployeeId("=", employeeId);
			erDao.setConditionDepartmentId("=", departmentId);
			erDao.setConditionRoleId("=", roleId);
			int num = erDao.conditionalDelete();
			if(num != 0) {
				String logContent = String.format("职员【%1$s】为职员【%2$s】删除了部门【%3$s】角色【%4$s】", operatorStr, employeeStr, departmentStr, roleStr);
				SystemLogUtils.saveSystemLog(operateEmployeeId, operatorStr, logContent, LOG_TYPE, null);
			}
		}
		return bc.toJSON(1,"");
	}

//	public static void main(String[] args) throws Exception {
//		Map<String, Object> map = new HashMap<>();
//		map.put("employeeId", 438);
//		new EditorRoleAndDepartmentRoleToEmp().findPersonalDepartmentRole(map);
//		System.out.println("qwqw");
//
//	}

}
