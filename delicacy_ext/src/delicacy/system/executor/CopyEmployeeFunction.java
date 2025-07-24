/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delicacy.system.executor;

import java.io.StringReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import javax.servlet.http.HttpServletRequest;

import delicacy.bean.utils.SystemLogUtils;
import delicacy.common.BaseCollection;
import delicacy.common.BaseHelpUtils;
import delicacy.common.GenericProcessor;
import delicacy.connection.ThreadConnection;
import delicacy.functions.bean.BaseCopyEmployeeAllFunctionToOther;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import delicacy.system.bean.BaseDepartmentRole;
import delicacy.system.bean.BaseDepartmentRoleFunction;
import delicacy.system.bean.BaseEmployeeFunction;
import delicacy.system.bean.BaseEmployeeRole;
import delicacy.system.bean.BaseFunction;
import delicacy.system.bean.BaseRole;
import delicacy.system.bean.BaseRoleFunction;
import delicacy.system.dao.DepartmentRole;
import delicacy.system.dao.EmployeeFunction;
import delicacy.system.dao.EmployeeRole;
import delicacy.system.dao.Function;
import delicacy.system.dao.Role;
import delicacy.system.dao.RoleFunction;

/**
 *
 * @author lsf
 */
public class CopyEmployeeFunction implements GenericProcessor {
	// 操作类型
	private static final String OPT_TYPE = "optType";
	//日志类型：角色权限
	private static final int LOG_TYPE = 1;
	// 查询所有的功能信息
	private static final String FIND_ALL_FUNCTION = "findAllFunction";
	// 给另一个员工添加功能信息
	private static final String ADD_FUNCTION_TO_EMPLOYEE = "addFunctionToEmployee";
	// 更新员工功能信息
	private static final String UPDATE_EMPLOYEE_FUNCTION = "updateEmployeeFunction";
	// 添加角色
	private static final String ADD_ROLE = "addRole";
	// 添加部门角色
	private static final String ADD_DEPARTMENT = "addDepartment";

	BaseCollection bc = new BaseCollection<>();

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

		case FIND_ALL_FUNCTION:

			return findAllFunction(param);
		case ADD_FUNCTION_TO_EMPLOYEE:

			return addFunctionToEmployee(param);
		case UPDATE_EMPLOYEE_FUNCTION:

			return updateEmployeeFunction(param);
		case ADD_ROLE:

			return addRole(param);
		case ADD_DEPARTMENT:

			return addDepartment(param);

		default:

			return bc.toJSON(1, "");
		}

	}

	/**
	 * 查询所有的功能信息并将个人功能信息进行标志
	 * 
	 * @param param
	 * @return
	 * @throws SQLException
	 */
	public String findAllFunction(Map<String, Object> param) throws SQLException {

		if (BaseHelpUtils.isNullOrEmpty(param.get("afterEmployeeId"))) {
			return bc.toJSON(-1, "未选择复制员工");
		}
		int afterEmployeeId = BaseHelpUtils.getIntValue(param.get("afterEmployeeId"));

		// 1、查询所有的功能列表信息
		List<BaseFunction> fList = new ArrayList<>();
		Function fDao = new Function();
		fList = fDao.conditionalLoad();

		// 根据员工编号查询个人的功能信息
		List<BaseCopyEmployeeAllFunctionToOther> originalList = findPersonalFunction(afterEmployeeId);
		// 将个人功能信息与全部的功能信息合并
		List<BaseCopyEmployeeAllFunctionToOther> finalList = mergePersonalFunction(originalList, fList);

		bc.setCollections(finalList);
		return bc.toJSON();

	}

	/**
	 * 根据赋权对象查询相对应的功能信息
	 * 
	 * @param param
	 * @return
	 * @throws SQLException
	 */
	public List<BaseCopyEmployeeAllFunctionToOther> findPersonalFunction(int employeeId) throws SQLException {
		List<BaseCopyEmployeeAllFunctionToOther> originalList = new ArrayList<>();
		BaseCopyEmployeeAllFunctionToOther bean = null;
		// 1、查询 employee_functions
		List<BaseEmployeeFunction> efList = new ArrayList<>();
		EmployeeFunction efDao = new EmployeeFunction();
		efDao.setConditionEmployeeId("=", employeeId);
		efList = efDao.conditionalLoad();
		// 2、查询role_functions
		List<BaseRoleFunction> rfList = new ArrayList<>();
		RoleFunction rfDao = new RoleFunction();
		rfList = rfDao.conditionalLoad(" role_id in (SELECT eff.role_id FROM employee_roles eff WHERE eff.employee_id ="
				+ employeeId + " AND eff.department_id =0) AND department_id=0");
		// 3、查询department_role_functions
		List<BaseDepartmentRoleFunction> drfList = new ArrayList<>();
		delicacy.system.dao.DepartmentRoleFunction drfDao = new delicacy.system.dao.DepartmentRoleFunction();
		drfList = drfDao.conditionalLoad(
				"(role_id,department_id) IN (SELECT err.role_id,err.department_id FROM employee_roles err WHERE err.employee_id ="+employeeId +" AND err.department_id != 0 )");
		for (BaseEmployeeFunction baseEmployeeFunction : efList) {
			bean = new BaseCopyEmployeeAllFunctionToOther();
			bean.setFunctionId(baseEmployeeFunction.getFunctionId());
			bean.setEmpFun(Boolean.TRUE);
			originalList.add(bean);
		}
		for (BaseRoleFunction baseRoleFunction : rfList) {
			bean = new BaseCopyEmployeeAllFunctionToOther();
			bean.setFunctionId(baseRoleFunction.getFunctionId());
			bean.setRoleFun(Boolean.TRUE);
			originalList.add(bean);
		}
		for (BaseDepartmentRoleFunction baseDepartmentRoleFunction : drfList) {
			bean = new BaseCopyEmployeeAllFunctionToOther();
			bean.setFunctionId(baseDepartmentRoleFunction.getFunctionId());
			bean.setDepartmentRoleFun(Boolean.TRUE);
			originalList.add(bean);
		}
		return originalList;
	}

	/**
	 * 将个人功能信息与全部的功能信息合并
	 * 
	 * @param personalList
	 * @param fList
	 * @return
	 */
	public List<BaseCopyEmployeeAllFunctionToOther> mergePersonalFunction(
			List<BaseCopyEmployeeAllFunctionToOther> personalList, List<BaseFunction> fList) {
		List<BaseCopyEmployeeAllFunctionToOther> allList = new ArrayList<>();
		BaseCopyEmployeeAllFunctionToOther bean = null;
		for (BaseFunction baseFunction : fList) {
			bean = new BaseCopyEmployeeAllFunctionToOther();
			bean.setFunctionId(baseFunction.getFunctionId());
			bean.setFunctionCode(baseFunction.getFunctionCode());
			bean.setParentId(baseFunction.getParentId());
			bean.setFunctionName(baseFunction.getFunctionName());
			bean.setApplicationId(baseFunction.getApplicationId());
			bean.setEnabled(baseFunction.getEnabled());
			for (BaseCopyEmployeeAllFunctionToOther copyBean : personalList) {
				if (baseFunction.getFunctionId().equals(copyBean.getFunctionId())
						&& !BaseHelpUtils.isNullOrEmpty(copyBean.getEmpFun()) && copyBean.getEmpFun()) {
					bean.setEmpFun(Boolean.TRUE);
				}
				if (baseFunction.getFunctionId().equals(copyBean.getFunctionId())
						&& !BaseHelpUtils.isNullOrEmpty(copyBean.getRoleFun()) && copyBean.getRoleFun()) {
					bean.setRoleFun(Boolean.TRUE);
				}
				if (baseFunction.getFunctionId().equals(copyBean.getFunctionId())
						&& !BaseHelpUtils.isNullOrEmpty(copyBean.getDepartmentRoleFun())
						&& copyBean.getDepartmentRoleFun()) {
					bean.setDepartmentRoleFun(Boolean.TRUE);
				}
			}
			allList.add(bean);
		}
		return allList;
	}

	/**
	 * 给员工添加功能
	 * 
	 * @param param
	 * @return
	 */
	public String addFunctionToEmployee(Map<String, Object> param) {
		int employeeId = BaseHelpUtils.getIntValue(param.get("employeeId"));
		int otherEmployeeId = BaseHelpUtils.getIntValue(param.get("otherEmployeeId"));
		try {
			StringJoiner roleSJ = new StringJoiner(";");
			StringJoiner departmentRoleSJ = new StringJoiner(";");
			StringJoiner functionSJ = new StringJoiner(";");
			ThreadConnection.beginTransaction();
			EmployeeRole erDao = new EmployeeRole();
			// 插入角色
			List<Map<String, Object>> roleList = (List<Map<String, Object>>) param.get("detailRole");
			if (!BaseHelpUtils.isNullOrEmpty(roleList)) {
				for (Map<String, Object> map : roleList) {
					int roleId = BaseHelpUtils.getIntValue(map.get("roleId"));
					if (!checkRoleAndDepartmentRole(erDao, otherEmployeeId, 0, roleId)) {
						erDao.setEmployeeId(otherEmployeeId);
						erDao.setDepartmentId(0);
						erDao.setRoleId(roleId);
						erDao.save();
						erDao.clear();
						String roleStr = SelectValueCache.getSelectValue("roles", roleId+"");
						if(BaseHelpUtils.isNullOrEmpty(roleStr)) {
							roleSJ.add(roleId+""); 
						}else {
							roleSJ.add(roleStr);
						}
					}

				}
			}
			// 插入部门角色 detailDepartmentRole
			List<Map<String, Object>> deRoleList = (List<Map<String, Object>>) param.get("detailDepartmentRole");
			if (!BaseHelpUtils.isNullOrEmpty(deRoleList)) {
				DepartmentRole derDao = new DepartmentRole();
				BaseDepartmentRole deRbean = null;
				for (Map<String, Object> map : deRoleList) {
					// 根据部门角色Id查询出该部门角色的角色Id和部门id
					derDao.setConditionDepartmentRoleId("=", BaseHelpUtils.getIntValue(map.get("departmentRoleId")));
					deRbean = derDao.executeQueryOneRow();
					derDao.clear();
					if (!checkRoleAndDepartmentRole(erDao, otherEmployeeId, deRbean.getDepartmentId(),
							deRbean.getRoleId())) {
						erDao.setEmployeeId(otherEmployeeId);
						erDao.setDepartmentId(deRbean.getDepartmentId());
						erDao.setRoleId(deRbean.getRoleId());
						erDao.save();
						erDao.clear();
						String departmentStr = SelectValueCache.getSelectValue("all_departments", deRbean.getDepartmentId()+"");
						if(BaseHelpUtils.isNullOrEmpty(departmentStr)) {
							departmentStr = deRbean.getDepartmentId()+""; 
						}
						String roleStr = SelectValueCache.getSelectValue("roles", deRbean.getRoleId()+"");
						if(BaseHelpUtils.isNullOrEmpty(roleStr)) {
							roleStr = deRbean.getRoleId()+""; 
						}
						departmentRoleSJ.add(String.format("%1$s-%2$s", departmentStr, roleStr));
					}

				}
			}

			// 1、查询 employee_functions
			List<BaseEmployeeFunction> efList = new ArrayList<>();
			EmployeeFunction efDao = new EmployeeFunction();
			efDao.setConditionEmployeeId("=", employeeId);
			efList = efDao.conditionalLoad();
			if (!BaseHelpUtils.isNullOrEmpty(efList)) {
				efDao.clear();
				efDao.setConditionEmployeeId("=", otherEmployeeId);
				List<BaseEmployeeFunction> afterList = efDao.conditionalLoad();
				for (BaseEmployeeFunction baseEmployeeFunction : efList) {
					if (!checkEmployeeByFunction(afterList, baseEmployeeFunction.getFunctionId())) {
						efDao.clear();
						efDao.setEmployeeId(otherEmployeeId);
						efDao.setFunctionId(baseEmployeeFunction.getFunctionId());
						efDao.setApplicationId(baseEmployeeFunction.getApplicationId());
						efDao.save();
						String functionStr = SelectValueCache.getSelectValue("functions", baseEmployeeFunction.getFunctionId()+"");
						if(BaseHelpUtils.isNullOrEmpty(functionStr)) {
							functionStr = baseEmployeeFunction.getFunctionId()+""; 
						}
						functionSJ.add(functionStr);
					}
				}
			}
			// 2、查询员工的角色
			List<BaseEmployeeRole> erList = new ArrayList<>();
			erDao.clear();
			erDao.setConditionEmployeeId("=", employeeId);
			erDao.setConditionDepartmentId("=", 0);
			erList = erDao.conditionalLoad();
			if (!BaseHelpUtils.isNullOrEmpty(erList)) {
				erDao.clear();
				erDao.setConditionEmployeeId("=", otherEmployeeId);
				erDao.setConditionDepartmentId("=", 0);
				List<BaseEmployeeRole> afterList = erDao.conditionalLoad();
				// 查询所有角色功能信息
				RoleFunction rfDao = new RoleFunction();
				List<BaseRoleFunction> rolefList = rfDao.conditionalLoad();
				for (BaseEmployeeRole baseEmployeeRole : erList) {

					if (!checkEmployeeByRoleId(afterList, rolefList, baseEmployeeRole.getRoleId())) {
						erDao.clear();
						erDao.setEmployeeId(otherEmployeeId);
						erDao.setRoleId(baseEmployeeRole.getRoleId());
						erDao.setDepartmentId(0);
						erDao.save();
						String roleStr = SelectValueCache.getSelectValue("roles", baseEmployeeRole.getRoleId()+"");
						if(BaseHelpUtils.isNullOrEmpty(roleStr)) {
							roleSJ.add(baseEmployeeRole.getRoleId()+""); 
						}else {
							roleSJ.add(roleStr);
						}
					}
				}
			}
			// 3、查询员工的部门角色
			List<BaseDepartmentRole> drList = new ArrayList<>();
			DepartmentRole drDao = new DepartmentRole();
			drList = drDao.conditionalLoad(
					"(role_id,department_id) IN (SELECT err.role_id,err.department_id FROM employee_roles err WHERE err.employee_id ="+employeeId +" AND err.department_id != 0 )");
			if (!BaseHelpUtils.isNullOrEmpty(drList)) {
				drDao.clear();
				List<BaseDepartmentRole> departmentRoleList = drDao.conditionalLoad(
						"(role_id,department_id) IN (SELECT err.role_id,err.department_id FROM employee_roles err WHERE err.employee_id ="+otherEmployeeId +" AND err.department_id != 0 )");

				delicacy.system.dao.DepartmentRoleFunction drfDao = new delicacy.system.dao.DepartmentRoleFunction();
				List<BaseDepartmentRoleFunction> departmentRolefList = drfDao.conditionalLoad();

				for (BaseDepartmentRole baseDepartmentRole : drList) {
					if (!checkEmployeeByDepartmentRoleId(departmentRoleList, departmentRolefList,
							baseDepartmentRole)) {
						erDao.clear();
						erDao.setEmployeeId(otherEmployeeId);
						erDao.setRoleId(baseDepartmentRole.getRoleId());
						erDao.setDepartmentId(baseDepartmentRole.getDepartmentId());
						erDao.save();
						String departmentStr = SelectValueCache.getSelectValue("all_departments", baseDepartmentRole.getDepartmentId()+"");
						if(BaseHelpUtils.isNullOrEmpty(departmentStr)) {
							departmentStr = baseDepartmentRole.getDepartmentId()+""; 
						}
						String roleStr = SelectValueCache.getSelectValue("roles", baseDepartmentRole.getRoleId()+"");
						if(BaseHelpUtils.isNullOrEmpty(roleStr)) {
							roleStr = baseDepartmentRole.getRoleId()+""; 
						}
						departmentRoleSJ.add(String.format("%1$s-%2$s", departmentStr, roleStr));
					}
				}
			}
			int operateEmployeeId = BaseHelpUtils.getIntValue(param, "operateEmployeeId");
			String operatorStr = SelectValueCache.getSelectValue("employees", operateEmployeeId+"");
			if(BaseHelpUtils.isNullOrEmpty(operatorStr)) {
				operatorStr = operateEmployeeId+""; 
			}
			String employeeStr = SelectValueCache.getSelectValue("employees", otherEmployeeId+"");
			if(BaseHelpUtils.isNullOrEmpty(employeeStr)) {
				employeeStr = otherEmployeeId+""; 
			}
			StringBuilder sb = new StringBuilder();
			sb.append("职员【").append(operatorStr).append("】通过复制权限为职员【").append(employeeStr).append("】");
			if(roleSJ.length() > 0) {
				sb.append("添加了以下角色：").append(roleSJ.toString()).append("。");
			}
			if(departmentRoleSJ.length() > 0) {
				sb.append("添加了以下部门角色：").append(departmentRoleSJ.toString()).append("。");
			}
			if(functionSJ.length() > 0) {
				sb.append("添加了以下权限：").append(functionSJ.toString()).append("。");
			}
			SystemLogUtils.saveSystemLog(operateEmployeeId, operatorStr, sb.toString(), LOG_TYPE, null);
			ThreadConnection.commit();
		} catch (SQLException e) {
			ThreadConnection.rollback();
			e.printStackTrace();
		}
		return bc.toJSON(1, "");
	}

	/**
	 * 判断某个角色或部门角色是否存在
	 * 
	 * @param erDao
	 * @param employeeId
	 * @param departmentId
	 * @param roleId
	 * @return
	 * @throws SQLException
	 */
	public Boolean checkRoleAndDepartmentRole(EmployeeRole erDao, int employeeId, int departmentId, int roleId)
			throws SQLException {
		erDao.setConditionEmployeeId("=", employeeId);
		erDao.setConditionDepartmentId("=", departmentId);
		erDao.setConditionRoleId("=", roleId);
		List<BaseEmployeeRole> list = erDao.conditionalLoad();
		erDao.clear();
		if (!BaseHelpUtils.isNullOrEmpty(list)) {
			return true;
		}
		return false;

	}

	/**
	 * 判断员工是否有某个功能
	 * 
	 * @param otherEmployeeId
	 * @param functionId
	 * @return
	 * @throws SQLException
	 */
	public Boolean checkEmployeeByFunction(List<BaseEmployeeFunction> afterList, Integer functionId)
			throws SQLException {
		if (!BaseHelpUtils.isNullOrEmpty(afterList)) {
			for (BaseEmployeeFunction baseEmployeeFunction : afterList) {
				if (baseEmployeeFunction.getFunctionId().equals(functionId)) {
					return true;
				}
			}
		}

		return false;

	}

	/**
	 * 判断员工是否有某个角色
	 * 
	 * @param otherEmployeeId
	 * @param roleId
	 * @return
	 * @throws SQLException
	 */
	public Boolean checkEmployeeByRoleId(List<BaseEmployeeRole> afterList, List<BaseRoleFunction> rolefList,
			Integer roleId) throws SQLException {

		if (!BaseHelpUtils.isNullOrEmpty(afterList)) {
			// 如果该员工已经拥有这个角色则返回true
			for (BaseEmployeeRole baseEmployeeRole : afterList) {
				if (baseEmployeeRole.getRoleId().equals(roleId)) {
					return true;
				}
			}
		}
		// 如果该角色有功能信息则返回true
		if (!checkRoleIsHaveFunction(rolefList, roleId)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断某个角色是否有功能信息
	 * 
	 * @param rolefList
	 * @param roleId
	 * @return
	 * @throws SQLException
	 */
	public Boolean checkRoleIsHaveFunction(List<BaseRoleFunction> rolefList, Integer roleId) throws SQLException {
		if (!BaseHelpUtils.isNullOrEmpty(rolefList)) {
			for (BaseRoleFunction baseRoleFunction : rolefList) {
				if (baseRoleFunction.getRoleId().equals(roleId)) {
					return true;
				}
			}
		}
		return false;

	}

	/**
	 * 判断员工是否有某个部门角色
	 * 
	 * @param otherEmployeeId
	 * @param departmentRoleId
	 * @return
	 * @throws SQLException
	 */
	public Boolean checkEmployeeByDepartmentRoleId(List<BaseDepartmentRole> drList,
			List<BaseDepartmentRoleFunction> departmentRolefList, BaseDepartmentRole bean) throws SQLException {

		if (!BaseHelpUtils.isNullOrEmpty(drList)) {
			for (BaseDepartmentRole baseDepartmentRole : drList) {
				if (bean.getRoleId().equals(baseDepartmentRole.getRoleId()) && bean.getDepartmentId().equals(baseDepartmentRole.getDepartmentId())) {
					return true;
				}
			}
		}

		// 如果该部门角色有功能信息则返回true
		if (!checkDepartmentRoleIsHaveFunction(departmentRolefList, bean)) {
			return true;
		}

		return false;
	}

	/**
	 * 判断某个部门角色是否有功能信息
	 * 
	 * @param rolefList
	 * @param roleId
	 * @return
	 * @throws SQLException
	 */
	public Boolean checkDepartmentRoleIsHaveFunction(List<BaseDepartmentRoleFunction> departmentRolefList,
			BaseDepartmentRole bean) throws SQLException {
		if (!BaseHelpUtils.isNullOrEmpty(departmentRolefList)) {
			for (BaseDepartmentRoleFunction baseDepartmentRoleFunction : departmentRolefList) {
				if (bean.getRoleId().equals(baseDepartmentRoleFunction.getRoleId()) && bean.getDepartmentId().equals(baseDepartmentRoleFunction.getDepartmentId())) {
					return true;
				}
			}
		}
		return false;

	}

	/**
	 * 更新员工功能信息
	 * 
	 * @param param
	 * @return
	 */
	public String updateEmployeeFunction(Map<String, Object> param) {
		List<BaseEmployeeFunction> efList = new ArrayList<>();
		BaseEmployeeFunction efBean = null;
		EmployeeFunction efDao;
		try {
			ThreadConnection.beginTransaction();
			efDao = new EmployeeFunction();
			if (BaseHelpUtils.isNullOrEmpty(param.get("afterEmployeeId"))) {
				return bc.toJSON(-1, "没有选择复制人员");
			}
			int employeeId = BaseHelpUtils.getIntValue(param.get("afterEmployeeId"));
			efDao.setConditionEmployeeId("=", employeeId);
			efDao.conditionalDelete();

			List<Map<String, Object>> list = (List<Map<String, Object>>) param.get("employeeFunction");
			if (!BaseHelpUtils.isNullOrEmpty(list)) {
				int operateEmployeeId = BaseHelpUtils.getIntValue(param, "operateEmployeeId");
				String operatorStr = SelectValueCache.getSelectValue("employees", operateEmployeeId+"");
				if(BaseHelpUtils.isNullOrEmpty(operatorStr)) {
					operatorStr = operateEmployeeId+""; 
				}
				String employeeStr = SelectValueCache.getSelectValue("employees", employeeId+"");
				if(BaseHelpUtils.isNullOrEmpty(employeeStr)) {
					employeeStr = employeeId+""; 
				}
				StringJoiner sj = new StringJoiner(";");
				for (Map<String, Object> map : list) {
					efBean = new BaseEmployeeFunction();
					efBean.setEmployeeId(BaseHelpUtils.getIntValue(param.get("afterEmployeeId")));
					efBean.setFunctionId(BaseHelpUtils.getIntValue(map.get("functionId")));
					efBean.setApplicationId(BaseHelpUtils.getIntValue(map.get("applicationId")));
					efList.add(efBean);
					sj.add(BaseHelpUtils.getString(map.get("functionName")));
				}
				efDao.clear();
				efDao.save(efList);
				String logContent = String.format("职员【%1$s】通过复制权限的保存按钮为职员【%2$s】新增了以下个人权限：%3$s", operatorStr, employeeStr, sj.toString());
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
	 * 添加角色
	 * 
	 * @param emoloyeeId
	 * @return
	 * @throws SQLException
	 */
	public String addRole(Map<String, Object> param) throws SQLException {
		if (BaseHelpUtils.isNullOrEmpty(param.get("employeeId"))) {
			return bc.toJSON(-1, "未选择被复制员工");
		}
		int employeeId = BaseHelpUtils.getIntValue(param.get("employeeId"));
		// 1.查询出该员工的角色
		EmployeeRole erDao = new EmployeeRole();
		erDao.setConditionEmployeeId("=", employeeId);
		erDao.setConditionDepartmentId("=", 0);
		List<BaseEmployeeRole> erList = erDao.conditionalLoad();
		// 2获取所有的角色功能信息
		RoleFunction rfDao = new RoleFunction();
		List<BaseRoleFunction> rfList = rfDao.conditionalLoad();
		// 3获取没有功能信息的角色
		List<BaseRole> rList = new ArrayList<>();
		Role roleDao = new Role();
		rList = roleDao.conditionalLoad("role_id in (" + getRoleId(erList, rfList) + ")");
		bc.setCollections(rList);
		return bc.toJSON();
	}

	/**
	 * 获取没有功能信息的角色ID
	 * 
	 * @param erList
	 * @param rfList
	 * @return
	 */
	public String getRoleId(List<BaseEmployeeRole> erList, List<BaseRoleFunction> rfList) {
		String roleIds = "";
		Boolean flag = false;
		if (BaseHelpUtils.isNullOrEmpty(erList)) {
			return null;
		}
		for (BaseEmployeeRole baseEmployeeRole : erList) {

			if (!BaseHelpUtils.isNullOrEmpty(rfList)) {
				for (BaseRoleFunction baseRoleFunction : rfList) {
					if (baseEmployeeRole.getRoleId().equals(baseRoleFunction.getRoleId())) {
						flag = true;
						break;
					}
				}
			}
			if (!flag) {
				roleIds = roleIds + baseEmployeeRole.getRoleId() + ",";
			}
			flag = false;
		}

		if (!BaseHelpUtils.isNullOrEmpty(roleIds)) {
			roleIds = roleIds.substring(0, roleIds.length() - 1);
		} else {
			roleIds = null;
		}

		return roleIds;

	}

	/**
	 * 添加部门角色
	 * 
	 * @param emoloyeeId
	 * @return
	 * @throws SQLException
	 */
	public String addDepartment(Map<String, Object> param) throws SQLException {
		if (BaseHelpUtils.isNullOrEmpty(param.get("employeeId"))) {
			return bc.toJSON(-1, "未选择被复制员工");
		}
		int employeeId = BaseHelpUtils.getIntValue(param.get("employeeId"));
		// 1.查询出该员工的部门角色
		EmployeeRole erDao = new EmployeeRole();
		erDao.setConditionEmployeeId("=", employeeId);
		erDao.setConditionDepartmentId("!=", 0);
		List<BaseEmployeeRole> erList = erDao.conditionalLoad();

		if (BaseHelpUtils.isNullOrEmpty(erList)) {
			return null;
		}

		List<BaseDepartmentRole> baseDepartmentRoleList = new ArrayList<>();

		DepartmentRole dRoleDao = new DepartmentRole();
		BaseDepartmentRole bean = null;
		for (BaseEmployeeRole baseEmployeeRole : erList) {

			dRoleDao.setConditionDepartmentId("=", baseEmployeeRole.getDepartmentId());
			dRoleDao.setConditionRoleId("=", baseEmployeeRole.getRoleId());
			bean = dRoleDao.executeQueryOneRow();
			if (!BaseHelpUtils.isNullOrEmpty(bean)) {
				baseDepartmentRoleList.add(bean);
			}
			dRoleDao.clear();
		}

		// 2获取所有的部门角色功能信息
		delicacy.system.dao.DepartmentRoleFunction drfDao = new delicacy.system.dao.DepartmentRoleFunction();
		List<BaseDepartmentRoleFunction> departmentRolefList = drfDao.conditionalLoad();

		// 3获取没有功能信息的部门角色
		List<BaseDepartmentRole> drList = new ArrayList<>();
		BaseDepartmentRole drBean = null;
		List<Map<String, Object>> listMap = getDepartmentRoleId(baseDepartmentRoleList, departmentRolefList);
		if (!BaseHelpUtils.isNullOrEmpty(listMap)) {
			for (Map<String, Object> map : listMap) {
				dRoleDao.setConditionRoleId("=", BaseHelpUtils.getIntValue(map.get("roleId")));
				dRoleDao.setConditionDepartmentId("=", BaseHelpUtils.getIntValue(map.get("departmentId")));
				drBean = dRoleDao.executeQueryOneRow();
				dRoleDao.clear();
				drList.add(drBean);
			}
			bc.setCollections(drList);
		}
		return bc.toJSON();
	}

	/**
	 * 获取没有功能信息的角色ID
	 * 
	 * @param erList
	 * @param rfList
	 * @return
	 */
	public List<Map<String, Object>> getDepartmentRoleId(List<BaseDepartmentRole> baseDepartmentRoleList,
			List<BaseDepartmentRoleFunction> departmentRolefList) {
		Map<String, Object> map = null;
		List<Map<String, Object>> list = new ArrayList<>();
		Boolean flag = false;
		if (BaseHelpUtils.isNullOrEmpty(baseDepartmentRoleList)) {
			return null;
		}
		for (BaseDepartmentRole baseDepartmentRole : baseDepartmentRoleList) {

			if (!BaseHelpUtils.isNullOrEmpty(departmentRolefList)) {
				for (BaseDepartmentRoleFunction baseDepartmentRoleFunction : departmentRolefList) {
					if (baseDepartmentRole.getRoleId().equals(baseDepartmentRoleFunction.getRoleId()) && baseDepartmentRole.getDepartmentId().equals(baseDepartmentRoleFunction.getDepartmentId())) {
						flag = true;
						break;
					}
				}
			}
			if (!flag) {
				map = new HashMap<>();
				map.put("roleId", baseDepartmentRole.getRoleId());
				map.put("departmentId", baseDepartmentRole.getDepartmentId());
				list.add(map);
			}
			flag = false;
		}

		return list;

	}

	public static void main(String[] args) throws Exception {
		DepartmentRole drDao = new DepartmentRole();
		List<BaseDepartmentRole> departmentRoleList = drDao.conditionalLoad(
				"(role_id,department_id) IN (SELECT err.role_id,err.department_id FROM employee_roles err WHERE err.employee_id = 36 AND err.department_id != 0 )");
	System.out.println(departmentRoleList.size());
	}

}
