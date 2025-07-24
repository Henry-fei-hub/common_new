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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

import javax.servlet.http.HttpServletRequest;

import delicacy.bean.utils.SystemLogUtils;
import delicacy.common.BaseCollection;
import delicacy.common.BaseHelpUtils;
import delicacy.common.GenericBase;
import delicacy.common.GenericProcessor;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import delicacy.system.dao.DepartmentRoleFunction;
import delicacy.system.bean.BaseDepartmentRoleFunction;
import delicacy.system.bean.BaseEmployeeFunction;
import delicacy.system.bean.BaseFunction;
import delicacy.system.bean.BaseRoleFunction;
import delicacy.system.dao.EmployeeFunction;
import delicacy.system.dao.RoleFunction;

/**
 *
 * @author lsf
 */
public class SaveRoleDepartmentEmployeeFunction implements GenericProcessor {
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
		if (BaseHelpUtils.isNullOrEmpty(param.get("detailRoleFunction"))) {
			return bc.toJSON(-1, "请选择功能信息");
		}
		List<BaseFunction> bfList = GenericBase.__getList(param.get("detailRoleFunction"), BaseFunction.newInstance());
		switch (optType) {
		case EMPLOYEE_DATA:
			return saveEmployeeInfo(param, bfList);
		case ROLE_DATA:
			return saveRoleInfo(param, bfList);
		case DEPARTMENT_ROLE_DATA:
			return saveDepartmentRoleInfo(param, bfList);
		default:
			return bc.toJSON(1, "");
		}
	}

	/**
	 * 往表employee_functions表中插入数据
	 * 
	 * @param param
	 * @param bfList
	 * @return
	 * @throws SQLException
	 */
	public String saveEmployeeInfo(Map<String, Object> param, List<BaseFunction> bfList) throws SQLException {
		if (null == bfList || bfList.isEmpty()) {
			return bc.toJSON(-1, "请选择功能信息");
		}
		List<BaseEmployeeFunction> list = GenericBase.__getList(param.get("detailRoleEmployee"), BaseEmployeeFunction.newInstance());
		if (null == list || list.isEmpty()) {
			return bc.toJSON(-1, "请选择人员");
		}
		Map<Integer, Integer> functionMap = new HashMap<>();
		Map<Integer, String> functionNameMap = new HashMap<>();
		for(BaseFunction fBean : bfList) {
			functionMap.put(fBean.getFunctionId(), fBean.getApplicationId());
			functionNameMap.put(fBean.getFunctionId(), fBean.getFunctionName());
		}
		Set<Integer> employeeSet = new HashSet<>();
		for(BaseEmployeeFunction eBean : list) {
			employeeSet.add(eBean.getEmployeeId());
		}
		EmployeeFunction efDao = new EmployeeFunction();
		efDao.addCondition(BaseEmployeeFunction.CS_EMPLOYEE_ID, "in", employeeSet.toArray());
		efDao.addCondition(BaseEmployeeFunction.CS_FUNCTION_ID, "in", functionMap.keySet().toArray());
		List<BaseEmployeeFunction> efList = efDao.conditionalLoad();
		Map<Integer, Set<Integer>> map = new HashMap<>();
		for(BaseEmployeeFunction bean : efList) {
			Set<Integer> set = map.get(bean.getFunctionId());
			if(null == set) {
				set = new HashSet<>();
			}
			set.add(bean.getEmployeeId());
			map.put(bean.getFunctionId(), set);
		}
		List<BaseEmployeeFunction> saveList = new ArrayList<>();
		int operateEmployeeId = BaseHelpUtils.getIntValue(param, "operateEmployeeId");
		String operatorStr = SelectValueCache.getSelectValue("employees", operateEmployeeId+"");
		if(BaseHelpUtils.isNullOrEmpty(operatorStr)) {
			operatorStr = operateEmployeeId+""; 
		}
		for(Integer functionId : functionMap.keySet()) {
			Set<Integer> set = map.get(functionId);
			boolean setIsNotEmpty = true;
			if(null == set || set.isEmpty()) {
				setIsNotEmpty = false;
			}
			StringJoiner employeeSJ = new StringJoiner(";");
			for(Integer employeeId : employeeSet) {
				if(setIsNotEmpty && set.contains(employeeId)) {
					continue;
				}
				BaseEmployeeFunction efBean = new BaseEmployeeFunction();
				efBean.setFunctionId(functionId);
				efBean.setEmployeeId(employeeId);
				efBean.setApplicationId(functionMap.get(functionId));
				saveList.add(efBean);
				String employeeStr = SelectValueCache.getSelectValue("employees", employeeId+"");
				if(BaseHelpUtils.isNullOrEmpty(employeeStr)) {
					employeeStr = employeeId+""; 
				}
				employeeSJ.add(employeeStr);
			}
			if(employeeSJ.length() > 0) {
				String logContent = String.format("职员【%1$s】将权限【%2$s】分配给了以下职员：%3$s", operatorStr, functionNameMap.get(functionId), employeeSJ.toString());
				SystemLogUtils.saveSystemLog(operateEmployeeId, operatorStr, logContent, LOG_TYPE, null);
			}
		}
		if (!saveList.isEmpty()) {
			efDao.save(saveList);
		}
		return bc.toJSON(1, "");
	}

	/**
	 * 往表role_functions表中插入数据
	 * 
	 * @param param
	 * @param bfList
	 * @return
	 * @throws SQLException
	 */
	public String saveRoleInfo(Map<String, Object> param, List<BaseFunction> bfList) throws SQLException {
		if (null == bfList || bfList.isEmpty()) {
			return bc.toJSON(-1, "请选择功能信息");
		}
		List<BaseRoleFunction> list =GenericBase.__getList(param.get("detailRole"), BaseRoleFunction.newInstance());
		if (null == list || list.isEmpty()) {
			return bc.toJSON(-1, "请选择角色");
		}
		Map<Integer, Integer> functionMap = new HashMap<>();
		Map<Integer, String> functionNameMap = new HashMap<>();
		for(BaseFunction fBean : bfList) {
			functionMap.put(fBean.getFunctionId(), fBean.getApplicationId());
			functionNameMap.put(fBean.getFunctionId(), fBean.getFunctionName());
		}
		Set<Integer> roleSet = new HashSet<>();
		for(BaseRoleFunction rBean : list) {
			roleSet.add(rBean.getRoleId());
		}
		RoleFunction rfDao = new RoleFunction();
		rfDao.addCondition(BaseRoleFunction.CS_ROLE_ID, "in", roleSet.toArray());
		rfDao.addCondition(BaseRoleFunction.CS_FUNCTION_ID, "in", functionMap.keySet().toArray());
		List<BaseRoleFunction> rfList = rfDao.conditionalLoad();
		Map<Integer, Set<Integer>> map = new HashMap<>();
		for(BaseRoleFunction bean : rfList) {
			Set<Integer> set = map.get(bean.getFunctionId());
			if(null == set) {
				set = new HashSet<>();
			}
			set.add(bean.getRoleId());
			map.put(bean.getFunctionId(), set);
		}
		List<BaseRoleFunction> saveList = new ArrayList<>();
		int operateEmployeeId = BaseHelpUtils.getIntValue(param, "operateEmployeeId");
		String operatorStr = SelectValueCache.getSelectValue("employees", operateEmployeeId+"");
		if(BaseHelpUtils.isNullOrEmpty(operatorStr)) {
			operatorStr = operateEmployeeId+""; 
		}
		for(Integer functionId : functionMap.keySet()) {
			Set<Integer> set = map.get(functionId);
			boolean setIsNotEmpty = true;
			if(null == set || set.isEmpty()) {
				setIsNotEmpty = false;
			}
			StringJoiner roleSJ = new StringJoiner(";");
			for(Integer roleId : roleSet) {
				if(setIsNotEmpty && set.contains(roleId)) {
					continue;
				}
				BaseRoleFunction rfBean = new BaseRoleFunction();
				rfBean.setFunctionId(functionId);
				rfBean.setRoleId(roleId);
				rfBean.setApplicationId(functionMap.get(functionId));
				saveList.add(rfBean);
				String roleStr = SelectValueCache.getSelectValue("roles", roleId+"");
				if(BaseHelpUtils.isNullOrEmpty(roleStr)) {
					roleStr = roleId+""; 
				}
				roleSJ.add(roleStr);
			}
			if(roleSJ.length() > 0) {
				String logContent = String.format("职员【%1$s】将权限【%2$s】分配给了以下角色：%3$s", operatorStr, functionNameMap.get(functionId), roleSJ.toString());
				SystemLogUtils.saveSystemLog(operateEmployeeId, operatorStr, logContent, LOG_TYPE, null);
			}
		}
		if (!saveList.isEmpty()) {
			rfDao.save(saveList);
		}
		return bc.toJSON(1, "");
	}

	/**
	 * 往表department_role_functions表中插入数据
	 * 
	 * @param param
	 * @param bfList
	 * @return
	 * @throws SQLException
	 */
	public String saveDepartmentRoleInfo(Map<String, Object> param, List<BaseFunction> bfList) throws SQLException {
		if (BaseHelpUtils.isNullOrEmpty(bfList)) {
			return bc.toJSON(-1, "请选择功能信息");
		}
		if (BaseHelpUtils.isNullOrEmpty(param.get("detailDepartmentRole"))) {
			return bc.toJSON(-1, "请选择部门角色");
		}
		Map<Integer, Integer> functionMap = new HashMap<>();
		Map<Integer, String> functionNameMap = new HashMap<>();
		for(BaseFunction fBean : bfList) {
			functionMap.put(fBean.getFunctionId(), fBean.getApplicationId());
			functionNameMap.put(fBean.getFunctionId(), fBean.getFunctionName());
		}
		List<BaseDepartmentRoleFunction> list = GenericBase.__getList(param.get("detailDepartmentRole"), BaseDepartmentRoleFunction.newInstance());
		DepartmentRoleFunction  drfDao = new DepartmentRoleFunction();
		drfDao.addCondition(BaseDepartmentRoleFunction.CS_FUNCTION_ID, "in", functionMap.keySet().toArray());
		List<BaseDepartmentRoleFunction> drfList = drfDao.conditionalLoad();
		Map<Integer, List<BaseDepartmentRoleFunction>> map = new HashMap<>();
		for(BaseDepartmentRoleFunction drfBean : drfList) {
			List<BaseDepartmentRoleFunction> tempList = map.get(drfBean.getFunctionId());
			if(null == tempList || tempList.isEmpty()) {
				tempList = new ArrayList<>();
			}
			tempList.add(drfBean);
			map.put(drfBean.getFunctionId(), tempList);
		}
		List<BaseDepartmentRoleFunction> saveList = new ArrayList<>();
		int operateEmployeeId = BaseHelpUtils.getIntValue(param, "operateEmployeeId");
		String operatorStr = SelectValueCache.getSelectValue("employees", operateEmployeeId+"");
		if(BaseHelpUtils.isNullOrEmpty(operatorStr)) {
			operatorStr = operateEmployeeId+""; 
		}
		for(Integer functionId : functionMap.keySet()) {
			List<BaseDepartmentRoleFunction> tempList = map.get(functionId);
			boolean listIsNotEmpty = true;
			if(null == tempList || tempList.isEmpty()) {
				listIsNotEmpty = false;
			}
			StringJoiner departmentRoleSJ = new StringJoiner(";");
			for(BaseDepartmentRoleFunction drfBean : list) {//前端传过来的部门角色数据
				boolean found = false;
				if(listIsNotEmpty) {
					for(BaseDepartmentRoleFunction tempBean : tempList) {//数据库中已保存的部门角色数据
						if(drfBean.getRoleId().equals(tempBean.getRoleId()) && drfBean.getDepartmentId().equals(tempBean.getDepartmentId())) {
							found = true;
							break;
						}
					}
				}
				if(!found) {
					BaseDepartmentRoleFunction bean = new BaseDepartmentRoleFunction();
					bean.setFunctionId(functionId);
					bean.setRoleId(drfBean.getRoleId());
					bean.setDepartmentId(drfBean.getDepartmentId());
					bean.setApplicationId(functionMap.get(functionId));
					saveList.add(bean);
					String departmentStr = SelectValueCache.getSelectValue("all_departments", drfBean.getDepartmentId()+"");
					if(BaseHelpUtils.isNullOrEmpty(departmentStr)) {
						departmentStr = drfBean.getDepartmentId()+""; 
					}
					String roleStr = SelectValueCache.getSelectValue("roles", drfBean.getRoleId()+"");
					if(BaseHelpUtils.isNullOrEmpty(roleStr)) {
						roleStr = drfBean.getDepartmentId()+""; 
					}
					departmentRoleSJ.add(String.format("%1$s-%2$s", departmentStr, roleStr));
				}
			}
			if(departmentRoleSJ.length() > 0) {
				String logContent = String.format("职员【%1$s】将权限【%2$s】分配给了以下部门角色：%3$s", operatorStr, functionNameMap.get(functionId), departmentRoleSJ.toString());
				SystemLogUtils.saveSystemLog(operateEmployeeId, operatorStr, logContent, LOG_TYPE, null);
			}
		}
		if(!saveList.isEmpty()) {
			drfDao.save(saveList);
		}
		return bc.toJSON(1, "");
	}
	
	/**
	 * 根据功能id和部门角色id判断该条信息是否存在
	 * 
	 * @param functionId
	 * @param departmentRoleId
	 * @return
	 * @throws SQLException
	 */
	public Boolean checkDepartmentRoleFunctions(int functionId, int roleId, int departmentId) throws SQLException {
		// 查询出role_functions中的所有数据
		
		delicacy.system.dao.DepartmentRoleFunction drfDao = new delicacy.system.dao.DepartmentRoleFunction();
		List<BaseDepartmentRoleFunction> drfList = drfDao.conditionalLoad();
		if (BaseHelpUtils.isNullOrEmpty(drfList)) {
			return false;
		}
		for (BaseDepartmentRoleFunction baseDepartmentRoleFunction : drfList) {
			if(Objects.equals(baseDepartmentRoleFunction.getFunctionId(), functionId)
					&& Objects.equals(baseDepartmentRoleFunction.getDepartmentId(), departmentId)
						&& Objects.equals(baseDepartmentRoleFunction.getRoleId(), roleId)){
				return true;
			}
		}
		return false;

	}

}
