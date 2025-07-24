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
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import delicacy.common.BaseCollection;
import delicacy.common.BaseHelpUtils;
import delicacy.common.GenericProcessor;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import delicacy.system.bean.BaseDepartmentRole;
import delicacy.system.bean.BaseEmployee;
import delicacy.system.bean.BaseRole;
import delicacy.system.dao.DepartmentRole;
import delicacy.system.dao.Employee;
import delicacy.system.dao.Role;

/**
 *
 * @author lsf
 */
public class SerachRoleDepartmentEmployee implements GenericProcessor {
	// 操作类型
	private static final String OPT_TYPE = "optType";
	// 搜索人员信息
	private static final String EMPLOYEE_DATA = "employeeData";
	// 搜索角色信息
	private static final String ROLE_DATA = "roleData";
	// 搜索部门角色信息
	private static final String DEPARTMENT_ROLE_DATA = "departmentRoleData";

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

			return getEmployeeInfo(param);
		case ROLE_DATA:

			return getRoleInfo(param);
		case DEPARTMENT_ROLE_DATA:

			return getDepartmentRoleInfo(param);

		default:
			return null;
		}

	}

	public String getEmployeeInfo(Map<String, Object> param) throws SQLException {
		Employee dao = new Employee();
		List<BaseEmployee> ds = new ArrayList<BaseEmployee>();
		dao.setConditionStatus("=", 0);
		if (!BaseHelpUtils.isNullOrEmpty(param.get("employeeNo"))) {
			dao.setConditionEmployeeNo("like", "%" + BaseHelpUtils.getString(param.get("employeeNo")) + "%");
		}
		if (!BaseHelpUtils.isNullOrEmpty(param.get("employeeName"))) {
			dao.setConditionEmployeeName("like", "%" + BaseHelpUtils.getString(param.get("employeeName")) + "%");
		}
		if (!BaseHelpUtils.isNullOrEmpty(param.get("departmentId"))
				&& BaseHelpUtils.getIntValue(param.get("departmentId")) != 0) {
			ds = dao.conditionalLoad(" department_id IN ( SELECT child_id FROM department_ids WHERE department_id = "
					+ BaseHelpUtils.getIntValue(param.get("departmentId")) + ") ORDER BY department_id ASC");
		} else {
			ds = dao.conditionalLoad();
		}
		BaseCollection<BaseEmployee> bc = new BaseCollection<>();
		bc.setCollections(ds);
		return bc.toJSON();

	}

	public String getRoleInfo(Map<String, Object> param) throws SQLException {
		Role dao = new Role();
		List<BaseRole> ds = new ArrayList<BaseRole>();
		// dao.setConditionEnabled("=",Boolean.TRUE);
		if (!BaseHelpUtils.isNullOrEmpty(param.get("roleName"))) {
			dao.setConditionRoleName("like", "%" + BaseHelpUtils.getString(param.get("roleName")) + "%");
		}
		if (!BaseHelpUtils.isNullOrEmpty(param.get("roleType"))) {
			dao.setConditionRoleType("=", BaseHelpUtils.getIntValue(param.get("roleType")));
		}
		if (!BaseHelpUtils.isNullOrEmpty(param.get("applicationId"))) {
			dao.setConditionApplicationId("=", BaseHelpUtils.getIntValue(param.get("applicationId")));
		}
		ds = dao.conditionalLoad();
		BaseCollection<BaseRole> bc = new BaseCollection<>();
		bc.setCollections(ds);
		return bc.toJSON();

	}

	public String getDepartmentRoleInfo(Map<String, Object> param) throws SQLException {
		Role dao = new Role();
		String roleIds = "";
		String sql = "";
		DepartmentRole drDao = new DepartmentRole();
		List<BaseDepartmentRole> ds = new ArrayList<BaseDepartmentRole>();
		List<BaseRole> rList = new ArrayList<BaseRole>();
		// dao.setConditionEnabled("=",Boolean.TRUE);
		if (!BaseHelpUtils.isNullOrEmpty(param.get("roleName"))) {
			dao.setConditionRoleName("like", "%" + BaseHelpUtils.getString(param.get("roleName")) + "%");
			rList = dao.conditionalLoad();
			if (!BaseHelpUtils.isNullOrEmpty(rList)) {
				for (int i = 0; i < rList.size(); i++) {
					if (i == rList.size() - 1) {
						roleIds = roleIds + rList.get(i).getRoleId();
					} else {
						roleIds = roleIds + rList.get(i).getRoleId() + ",";
					}
				}
			}
			if (!BaseHelpUtils.isNullOrEmpty(roleIds)) {
				sql = " role_id in (" + roleIds + ")";
			}
		}

		if (!BaseHelpUtils.isNullOrEmpty(param.get("departmentId"))
				&& BaseHelpUtils.getIntValue(param.get("departmentId")) != 0) {
			if (BaseHelpUtils.isNullOrEmpty(sql)) {
				sql = " department_id IN ( SELECT child_id FROM department_ids WHERE department_id = "
						+ BaseHelpUtils.getIntValue(param.get("departmentId")) + ") ORDER BY department_id ASC";
			} else {
				sql = sql + " and department_id IN ( SELECT child_id FROM department_ids WHERE department_id = "
						+ BaseHelpUtils.getIntValue(param.get("departmentId")) + ") ORDER BY department_id ASC";
			}
		}
		if (BaseHelpUtils.isNullOrEmpty(sql)) {
			ds = drDao.conditionalLoad();
		} else {
			ds = drDao.conditionalLoad(sql);
		}
		BaseCollection<BaseDepartmentRole> bc = new BaseCollection<>();
		bc.setCollections(ds);
		return bc.toJSON();

	}
	
	public static void main(String[] args) throws SQLException {
		Map<String, Object> param = new HashMap<>();
		param.put("roleName", "测");
		param.put("departmentId", 1);
		new SerachRoleDepartmentEmployee().getDepartmentRoleInfo(param);
		
	}

}
