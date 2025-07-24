/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delicacy.system.custom.handler;

import delicacy.bean.utils.DeptRole;
import delicacy.common.BaseHelpUtils;
import delicacy.connection.ThreadConnection;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import delicacy.system.bean.BaseEmployee;
import delicacy.system.bean.BaseEmployeeRole;
import delicacy.system.bean.BaseEmployeeWithE;
import delicacy.system.dao.Employee;
import delicacy.system.dao.EmployeeRole;
import delicacy.system.dao.EmployeeWithE;
import delicacy.system.handler.EmployeeWithEHandler;
import java.io.StringReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author wull
 */
public class CustomEmployeeWidthEHandler extends EmployeeWithEHandler {

    @Override
    public String save(String json) throws Exception {
        Map<String, Object> result = null;
        if (!BaseHelpUtils.isNullOrEmpty(json)) {
            JSON parser = new JSON(new StringReader(json));
            result = (Map<String, Object>) parser.parse(new BasicHandler());
        }
        //开启事务
        ThreadConnection.beginTransaction();
        BaseEmployee bean = new BaseEmployee();
        bean.setDataFromJSON(json);
        Employee dao = new Employee();
        dao.setDataFromBase(bean);
        int num = dao.save();
        dao.setDataToBase(bean);

        addAndIsDelDeptRole(result, BaseHelpUtils.getIntValue(dao.getEmployeeId()));
        ThreadConnection.commit();
        return bean.toOneLineJSON(num, null);
    }

    @Override
    public String saveOrUpdate(String json) throws Exception {
        BaseEmployee bean = new BaseEmployee();
        bean.setDataFromJSON(json);
        int num = 0;
        Employee dao = new Employee();
        dao.setDataFromBase(bean);
        if (dao.isPrimaryKeyNull()) {
            System.out.println("saveOrUpdate save=" + json);
            num = dao.save();
        } else if (dao.load()) {
            System.out.println("saveOrUpdate update=" + json);
            dao.setDataFromBase(bean);
            num = dao.update();
        }
        return bean.toOneLineJSON(num, null);
    }

    @Override
    public String update(String json) throws Exception {
//        BaseEmployeeWithE bean = new BaseEmployeeWithE();
//        bean.setDataFromJSON(json);
//        EmployeeWithE dao = new EmployeeWithE();
//        dao.setPrimaryKeyFromBase(bean);
//        int num = 0;
//        if (dao.load()) {
//            dao.setDataFromBase(bean);
//            num = dao.update();
//        }
//        return bean.toOneLineJSON(num, null);
        Map<String, Object> result = null;
        if (!BaseHelpUtils.isNullOrEmpty(json)) {
            JSON parser = new JSON(new StringReader(json));
            result = (Map<String, Object>) parser.parse(new BasicHandler());
        }

        //开启事务
        ThreadConnection.beginTransaction();
        BaseEmployee bean = new BaseEmployee();
        bean.setDataFromJSON(json);
        Employee dao = new Employee();
        dao.setPrimaryKeyFromBase(bean);
        int num = 0;
        if (dao.load()) {
            dao.setDataFromBase(bean);
            num = dao.update();
        }
//        addAndIsDelDeptRole(result, BaseHelpUtils.getIntValue(dao.getEmployeeId()));
        ThreadConnection.commit();
        return bean.toOneLineJSON(num, null);
    }

    private boolean addAndIsDelDeptRole(Map<String, Object> result, int employeeId__) throws SQLException {
        //获取角色列表
        List<DeptRole> drList = new ArrayList<>();
        List<Map> ll = (List<Map>) result.get("detailRoleFunction");
        if (ll.size() > 0) {
            for (Map map : ll) {
//                String employeeId = (String) map.get("employeeId");
                String deptId = (String) map.get("deptId");
                String roleId = (String) map.get("roleId");
                Boolean isDefault = BaseHelpUtils.getBoolean(map.get("isDefault"));
                if (!BaseHelpUtils.isNullOrEmpty(employeeId__) && !BaseHelpUtils.isNullOrEmpty(deptId) && !BaseHelpUtils.isNullOrEmpty(roleId)) {
                    DeptRole drRole = new DeptRole();
                    drRole.setEmployeeId(employeeId__+"");
                    drRole.setDeptId(deptId);
                    drRole.setRoleId(roleId);
                    drRole.setIsDefault(isDefault);
                    drList.add(drRole);
                }
            }
        }
        //添加人员角色
        if (drList.size() > 0) {
            //先清除该人员的角色,再添加
            EmployeeRole employeeRoleDel = new EmployeeRole();
            employeeRoleDel.setConditionEmployeeId("=", employeeId__);
            employeeRoleDel.conditionalDelete();

            for (DeptRole dr : drList) {
                EmployeeRole employeeRole = new EmployeeRole();
                BaseEmployeeRole er = new BaseEmployeeRole();
                er.setEmployeeId(BaseHelpUtils.getIntValue(dr.getEmployeeId()));
                er.setDepartmentId(BaseHelpUtils.getIntValue(dr.getDeptId()));
                er.setRoleId(BaseHelpUtils.getIntValue(dr.getRoleId()));
                er.setIsDefault(dr.isIsDefault());
                employeeRole.setDataFromBase(er);
                employeeRole.save();
            }
            return true;
        }
        return false;
    }
}
