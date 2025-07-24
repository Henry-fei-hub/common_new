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
import delicacy.system.dao.Employee;

/**
 *
 * @author Administrator
 */
public class RoleDepartmentEmployee implements GenericProcessor {

    @Override
    public String execute(String creteria, HttpServletRequest request) throws Exception {

		@SuppressWarnings("rawtypes")
		JSON parser = new JSON(new StringReader(creteria));
		System.out.println(parser);
		@SuppressWarnings("unchecked")
		Map<String, Object> param = (Map<String, Object>) parser.parse(new BasicHandler());
		Employee dao = new Employee();
		List<BaseEmployee> ds = new ArrayList<BaseEmployee>();
		dao.setConditionStatus("=",0);
		if (!BaseHelpUtils.isNullOrEmpty(param.get("employeeNo"))) {
			dao.setConditionEmployeeNo("like","%"+ BaseHelpUtils.getString(param.get("employeeNo"))+"%");
		}
		if (!BaseHelpUtils.isNullOrEmpty(param.get("employeeName"))) {
			dao.setConditionEmployeeName("like", "%"+BaseHelpUtils.getString(param.get("employeeName"))+"%");
		}
		if (!BaseHelpUtils.isNullOrEmpty(param.get("departmentId"))&&BaseHelpUtils.getIntValue(param.get("departmentId"))!=0) {
			   ds = dao.conditionalLoad(" department_id IN ( SELECT child_id FROM department_ids WHERE department_id = "+BaseHelpUtils.getIntValue(param.get("departmentId"))+") ORDER BY department_id ASC");
		}else {
			  ds = dao.conditionalLoad();
		}
        BaseCollection<BaseEmployee> bc = new BaseCollection<>();
        bc.setCollections(ds);
        return bc.toJSON();
    }

}
