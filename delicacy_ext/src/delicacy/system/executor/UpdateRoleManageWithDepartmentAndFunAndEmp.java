package delicacy.system.executor;

import java.io.StringReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import delicacy.common.BaseCollection;
import delicacy.common.BaseHelpUtils;
import delicacy.common.GenericProcessor;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import delicacy.system.bean.BaseDepartmentRole;
import delicacy.system.bean.BaseEmployeeRole;
import delicacy.system.bean.BaseRole;
import delicacy.system.bean.BaseRoleFunction;
import delicacy.system.dao.DepartmentRole;
import delicacy.system.dao.Employee;
import delicacy.system.dao.EmployeeRole;
import delicacy.system.dao.Role;
import delicacy.system.dao.RoleFunction;

/**
 *
 * @author lsf
 */
public class UpdateRoleManageWithDepartmentAndFunAndEmp implements GenericProcessor {
	// {"roleName":"测试","applicationId":1,"roleType":1,"enabled":true,
	// "detailRoleEmployee":[{"deparmentId":"1"}, {"departmentId":"57"},
	// {"departmentId":"39"}, {"departmentId":"10438"}],
	// "detailRoleFunction":[{"functionId":"7","applicationId":"1"},
	// {"functionId":"36","applicationId":"1"},{"functionId":"23","applicationId":"1"}],
	// "detaiRoleDepartment":[{"departmentId":"1"}, {"departmentId":"98"}]}
	@SuppressWarnings("rawtypes")
	@Override
	public String execute(String creteria, HttpServletRequest request) throws Exception {
		JSON parser = new JSON(new StringReader(creteria));
		System.out.println(parser);
		@SuppressWarnings("unchecked")
		Map<String, Object> param = (Map<String, Object>) parser.parse(new BasicHandler());
		// 更新roles表里的数据
		BaseRole roleBean = new BaseRole();
		Role roleDao = new Role();
		int roleId = BaseHelpUtils.getIntValue(param.get("roleId"));
		if (roleId <= 0) {
			throw new SQLException("操作异常：获取角色ID失败");
		}
		roleBean.setRoleId(roleId);
		//获取角色名称
		String roleName = BaseHelpUtils.getString(param.get("roleName"));
		if (BaseHelpUtils.isNullOrEmpty(roleName)) {
			throw new SQLException("操作异常：角色名称不可为空");
		}
		roleBean.setDataFromMap(param);
		roleDao.setDataFromBase(roleBean);
		roleDao.update();

		//更新部门角色
		upadteDepartmentRole(roleId,param);
        //更新角色功能
//		upadteRoleFunction(roleId,param);
		//更新人员角色
		upadteEmployeeRole(roleId,param);
	
		BaseCollection bc = new BaseCollection<>();
		return bc.toJSON(1, "");

	}
	
	/**
	 * 更新部门角色
	 * @param roleId
	 * @param param
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public void upadteDepartmentRole(int roleId, Map<String, Object> param) throws SQLException {
		// 根据roleId查询数据
		DepartmentRole drDao = new DepartmentRole();
		drDao.setConditionRoleId("=", roleId);
		List<BaseDepartmentRole> bdrList = drDao.conditionalLoad();

		// 是否添加到list
		Boolean saveFlag = true;
		// 是否删除
		Boolean deleteFlag = true;
		if (!BaseHelpUtils.isNullOrEmpty(param.get("detaiRoleDepartment"))) {
			BaseDepartmentRole drBean = null;
			drDao.clear();
			List<BaseDepartmentRole> drList = new ArrayList<BaseDepartmentRole>();
			List<Map<String, Object>> list = (List<Map<String, Object>>) param.get("detaiRoleDepartment");

			// 如果查询到的结果为空则直接插入数据
			if (BaseHelpUtils.isNullOrEmpty(bdrList) || bdrList.size() == 0) {
				for (Map<String, Object> map : list) {
					drBean = new BaseDepartmentRole();
					drBean.setRoleId(roleId);
					drBean.setDepartmentId(BaseHelpUtils.getIntValue(map.get("departmentId")));
					drList.add(drBean);
				}
				if (drList.size() > 0) {
					drDao.save(drList);
				}
			} else {
				// 保存数据库中不存在的数据
				for (Map<String, Object> map : list) {
					int departmentId = BaseHelpUtils.getIntValue(map, "departmentId");
					for (BaseDepartmentRole baseDepartmentRole : bdrList) {
						if (baseDepartmentRole.getDepartmentId() == departmentId) {
							saveFlag = false;
							break;
						}
					}
					// 如果数据不存在则添加到drList
					if (saveFlag) {
						drBean = new BaseDepartmentRole();
						drBean.setRoleId(roleId);
						drBean.setDepartmentId(departmentId);
						drList.add(drBean);
					}
					saveFlag = true;
				}
				if (drList.size() > 0) {
					drDao.save(drList);
				}
				// 删除传过来的数据没有数据库中存在的数据
				for (BaseDepartmentRole baseDepartmentRole : bdrList) {
					int departmentId = BaseHelpUtils.getIntValue(baseDepartmentRole.getDepartmentId());
					for (Map<String, Object> map : list) {
						if (departmentId == BaseHelpUtils.getIntValue(map.get("departmentId"))) {
							deleteFlag = false;
							break;
						}
					}
					if (deleteFlag) {
						drDao.clear();
						drDao.setConditionRoleId("=", roleId);
						drDao.setConditionDepartmentId("=",departmentId);
						drDao.conditionalDelete();
					}
					deleteFlag = true;
				}

			}

		} else {
			drDao.clear();
			drDao.setConditionRoleId("=", roleId);
			drDao.conditionalDelete();
		}
	}

	/**
	 * 更新角色功能
	 * @param roleId
	 * @param param
	 * @throws SQLException
	 */
	public void upadteRoleFunction(int roleId, Map param) throws SQLException {
	
		// 根据roleId查询数据
		RoleFunction rfDao = new RoleFunction();
		rfDao.setConditionRoleId("=", roleId);
		List<BaseRoleFunction> brfList = rfDao.conditionalLoad();

		// 是否添加到list
		Boolean saveFlag = true;
		// 是否删除
		Boolean deleteFlag = true;
		if (!BaseHelpUtils.isNullOrEmpty(param.get("detailRoleFunction"))) {
			BaseRoleFunction rfBean = null;
			rfDao.clear();
			List<BaseRoleFunction> rfList = new ArrayList<>();
			List<Map<String, Object>> list = (List<Map<String, Object>>) param.get("detailRoleFunction");

			// 如果查询到的结果为空则直接插入数据
			if (BaseHelpUtils.isNullOrEmpty(brfList) || brfList.size() == 0) {
				for (Map<String, Object> map : list) {
					rfBean = new BaseRoleFunction();
					if (roleId > 0) {
						rfBean.setRoleId(roleId);
						rfBean.setFunctionId(BaseHelpUtils.getIntValue(map.get("functionId")));
						rfBean.setApplicationId(BaseHelpUtils.getIntValue(map.get("applicationId")));
						rfList.add(rfBean);
					}
				}
				if (rfList.size() > 0) {
					rfDao.save(rfList);
				}
			} else {
				// 保存数据库中不存在的数据
				for (Map<String, Object> map : list) {
					for (BaseRoleFunction baseRoleFunction : brfList) {
						if (baseRoleFunction.getFunctionId() == BaseHelpUtils.getIntValue(map.get("functionId"))) {
							saveFlag = false;
							break;
						}
					}
					// 如果数据不存在则添加到rfList
					if (saveFlag) {
						rfBean = new BaseRoleFunction();
						rfBean.setRoleId(roleId);
						rfBean.setFunctionId(BaseHelpUtils.getIntValue(map.get("functionId")));
						rfBean.setApplicationId(BaseHelpUtils.getIntValue(map.get("applicationId")));
						rfList.add(rfBean);
					}
					saveFlag = true;
				}
				if (rfList.size() > 0) {
					rfDao.save(rfList);
				}
				// 删除传过来的数据没有数据库中存在的数据
				for (BaseRoleFunction baseRoleFunction : brfList) {
					for (Map<String, Object> map : list) {
						if (baseRoleFunction.getFunctionId() == BaseHelpUtils.getIntValue(map.get("functionId"))) {
							deleteFlag = false;
							break;
						}
					}
					if (deleteFlag) {
						rfDao.clear();
						rfDao.setConditionRoleId("=", roleId);
						rfDao.setConditionFunctionId("=", baseRoleFunction.getFunctionId());
						rfDao.conditionalDelete();
					}
					deleteFlag = true;
				}

			}

		} else {
			rfDao.clear();
			rfDao.setConditionRoleId("=", roleId);
			rfDao.conditionalDelete();
		}
	}

	/**
	 * 更新人员角色
	 * @param roleId
	 * @param param
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public void upadteEmployeeRole(int roleId, Map<String, Object> param) throws SQLException {
		// 根据roleId查询数据
		EmployeeRole erDao = new EmployeeRole();
		erDao.setConditionRoleId("=", roleId);
		List<BaseEmployeeRole> berList = erDao.conditionalLoad();
		// 是否添加到list
		Boolean saveFlag = true;
		// 是否删除
		Boolean deleteFlag = true;
		if (!BaseHelpUtils.isNullOrEmpty(param.get("detailRoleEmployee"))) {
			Employee eDao = new Employee();
			DepartmentRole dDao = new DepartmentRole();
			BaseEmployeeRole erBean = null;
			erDao.clear();
			List<BaseEmployeeRole> erList = new ArrayList<BaseEmployeeRole>();
			List<Map<String, Object>> list = (List<Map<String, Object>>) param.get("detailRoleEmployee");
			// 如果查询到的结果为空则直接插入数据
			if (BaseHelpUtils.isNullOrEmpty(berList) || berList.size() == 0) {
				for (Map<String, Object> map : list) {
					erBean = new BaseEmployeeRole();
					erBean.setRoleId(roleId);
					erBean.setEmployeeId(BaseHelpUtils.getIntValue(map.get("employeeId")));
					erList.add(erBean);
				}
			} else {
				// 保存数据库中不存在的数据
				for (Map<String, Object> map : list) {
					for (BaseEmployeeRole baseEmployeeRole : berList) {
						if (baseEmployeeRole.getEmployeeId() == BaseHelpUtils.getIntValue(map.get("employeeId"))) {
							saveFlag = false;
							break;
						}
					}
					// 如果数据不存在则添加到erList
					if (saveFlag) {
						erBean = new BaseEmployeeRole();
						erBean.setRoleId(roleId);
						erBean.setEmployeeId(BaseHelpUtils.getIntValue(map.get("employeeId")));
						erList.add(erBean);
					}
					saveFlag = true;
				}
				// 删除传过来的数据没有数据库中存在的数据
				for (BaseEmployeeRole baseEmployeeRole  : berList) {
					for (Map<String, Object> map : list) {
						if (baseEmployeeRole.getEmployeeId() == BaseHelpUtils.getIntValue(map.get("employeeId"))) {
							deleteFlag = false;
							break;
						}
					}
					if (deleteFlag) {
						erDao.clear();
						erDao.setConditionRoleId("=", roleId);
						erDao.setConditionEmployeeId("=", baseEmployeeRole.getEmployeeId());
						erDao.conditionalDelete();
					}
					deleteFlag = true;
				}

			}
			if (erList.size() > 0) {
				erDao.save(erList);
				for(BaseEmployeeRole e : erList){
					//获取职员Id
					int empId = BaseHelpUtils.getIntValue(e.getEmployeeId());
					eDao.clear();
					eDao.setEmployeeId(empId);
					if(eDao.load()){
						//获取当前人员归属部门Id
						int depId = BaseHelpUtils.getIntValue(eDao.getDepartmentId());
						//查询该人员在归属部门下是否有该角色，如果不存在，则加入一条
						erDao.clear();
						erDao.setConditionEmployeeId("=",empId);
						erDao.setConditionDepartmentId("=",depId);
						erDao.setConditionRoleId("=",roleId);
						if(!erDao.isExist()){
							erBean = new BaseEmployeeRole();
							erBean.setRoleId(roleId);
							erBean.setEmployeeId(empId);
							erBean.setDepartmentId(depId);
							erDao.clear();
							erDao.setDataFromBase(erBean);
							erDao.save();
							//检查改角色在该部门下是否存在该角色，如果不存在，则插入
							dDao.clear();
							dDao.setConditionDepartmentId("=",depId);
							dDao.setConditionRoleId("=",roleId);
							if(!dDao.isExist()){
								dDao.setDepartmentId(depId);
								dDao.setRoleId(roleId);
								dDao.save();
							}
						}
					}
				}
				
			}

		} else {
			erDao.clear();
			erDao.setConditionRoleId("=", roleId);
			erDao.conditionalDelete();
		}
	}

}
