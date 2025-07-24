/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delicacy.system.executor;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import delicacy.common.BaseCollection;
import delicacy.common.BaseHelpUtils;
import delicacy.common.GenericProcessor;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import delicacy.system.bean.BaseEmployee;
import delicacy.system.bean.BaseEmployeeRole;
import delicacy.system.dao.Employee;
import delicacy.system.dao.EmployeeRole;

/**
 *
 * @author Administrator
 */
public class RoleEmployeePanlProcess implements GenericProcessor {

    @Override
    public String execute(String creteria, HttpServletRequest request) throws Exception {

		@SuppressWarnings("rawtypes")
		JSON parser = new JSON(new StringReader(creteria));
		System.out.println(parser);
		@SuppressWarnings("unchecked")
		Map<String, Object> param = (Map<String, Object>) parser.parse(new BasicHandler());
		Employee dao = new Employee();
		EmployeeRole erDao = new EmployeeRole();
		if (BaseHelpUtils.isNullOrEmpty(param.get("roleId"))) {
			return null;
		}
		int roleId = BaseHelpUtils.getIntValue(param.get("roleId"));
		erDao.setSelectEmployeeId(true);
		erDao.setConditionRoleId("=", roleId);
		List<BaseEmployeeRole> list = erDao.conditionalLoad();
		
		if (BaseHelpUtils.isNullOrEmpty(list)) {
			return null;
		}
		String employeeIds="";
		for (int i = 0; i < list.size(); i++) {
			if (i==list.size()-1) {
				employeeIds = employeeIds+list.get(i).getEmployeeId();
			}else{
				employeeIds = employeeIds+list.get(i).getEmployeeId()+",";
			}
		
		}
        List<BaseEmployee> ds = dao.conditionalLoad("employee_id in ("+employeeIds+")");
        BaseCollection<BaseEmployee> bc = new BaseCollection<>();
        bc.setCollections(ds);
        return bc.toJSON();
    }
    

}
