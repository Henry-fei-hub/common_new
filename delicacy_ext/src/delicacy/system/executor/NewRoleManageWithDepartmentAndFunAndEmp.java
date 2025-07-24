package delicacy.system.executor;

import java.io.StringReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import delicacy.common.BaseCollection;
import delicacy.common.BaseHelpUtils;
import delicacy.common.GenericBase;
import delicacy.common.GenericProcessor;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import delicacy.system.bean.BaseDepartmentRole;
import delicacy.system.bean.BaseEmployeeRole;
import delicacy.system.bean.BaseRole;
import delicacy.system.dao.DepartmentRole;
import delicacy.system.dao.Employee;
import delicacy.system.dao.EmployeeRole;
import delicacy.system.dao.Role;

/**
 *
 * @author lsf
 */
public class NewRoleManageWithDepartmentAndFunAndEmp implements GenericProcessor {
	// {"roleName":"测试","applicationId":1,"roleType":1,"enabled":true,
	// "detailRoleEmployee":[{"deparmentId":"1"}, {"departmentId":"57"}, {"departmentId":"39"}, {"departmentId":"10438"}],
	//"detailRoleFunction":[{"functionId":"7","applicationId":"1"}, {"functionId":"36","applicationId":"1"},{"functionId":"23","applicationId":"1"}],
	//"detaiRoleDepartment":[{"departmentId":"1"}, {"departmentId":"98"}]}
	@SuppressWarnings("unchecked")
	@Override
	public String execute(String creteria, HttpServletRequest request) throws Exception {
		@SuppressWarnings("rawtypes")
		JSON parser = new JSON(new StringReader(creteria));
		System.out.println(parser);
		Map<String, Object> param = (Map<String, Object>) parser.parse(new BasicHandler());
		
		//往roles表里插入数据
		BaseRole roleBean = new BaseRole();
		Role roleDao = new Role();
		
		//获取角色名称
		String roleName = BaseHelpUtils.getString(param.get("roleName"));
		if (BaseHelpUtils.isNullOrEmpty(roleName)) {
			throw new SQLException("操作异常：角色名称不可为空");
		}
		//检索该角色名是否已存在
		roleDao.clear();
		roleDao.setConditionRoleName("=",roleName);
		if(roleDao.isExist()){
			throw new SQLException("操作异常：角色名称已存在");
		}
		roleBean.setDataFromMap(param);
		roleDao.clear();
		roleDao.setDataFromBase(roleBean);
		roleDao.save();
		int roleId = roleDao.getRoleId();
		//往department_roles表里插入数据
		if (!BaseHelpUtils.isNullOrEmpty(param.get("detaiRoleDepartment"))) {
			BaseDepartmentRole drBean = null;
			DepartmentRole drDao = 	new DepartmentRole();
			List< BaseDepartmentRole> drList = new ArrayList<BaseDepartmentRole>();
		    List<Map<String, Object>> list =(List<Map<String, Object>>) param.get("detaiRoleDepartment");
		    for (Map<String, Object> map : list) {
		    	drBean = new BaseDepartmentRole();
		    	if (roleId>0) {
		    		drBean.setRoleId(roleId);
		    		drBean.setDepartmentId(BaseHelpUtils.getIntValue(map.get("departmentId")));
		    		drList.add(drBean);
				}
			}
		    if (drList.size()>0) {
		    	drDao.save(drList);
			}
		    
		}

		//往employee_roles表里插入数据
        if (!BaseHelpUtils.isNullOrEmpty(param.get("detailRoleEmployee"))) {
        	int employeeId ;
        	BaseEmployeeRole erBean = null;
        	EmployeeRole erDao = new EmployeeRole();
        	Employee eDao = new Employee();
        	DepartmentRole dDao = new DepartmentRole();
		    List<Map<String, Object>> list =(List<Map<String, Object>>) param.get("detailRoleEmployee");
		    if(!BaseHelpUtils.isNullOrEmpty(list) && list.size() > 0){
		    	for (Map<String, Object> map : list) {
		    		erBean = new BaseEmployeeRole();
		    		employeeId = BaseHelpUtils.getIntValue(map.get("employeeId"));
		    		erBean.setRoleId(roleId);
		    		erBean.setEmployeeId(employeeId);
		    		erDao.clear();
		    		erDao.setDataFromBase(erBean);
		    		erDao.save();
		    		//获取该人员归属部门
		    		eDao.clear();
		    		eDao.setEmployeeId(employeeId);
		    		if(eDao.load()){
		    			int depId = BaseHelpUtils.getIntValue(eDao.getDepartmentId());
		    			if(depId > 0){
		    				//默认将该角色赋予该人员，并绑定为该人员归属部门
		    				erDao.clear();
		    				erDao.setEmployeeId(employeeId);
		    				erDao.setDepartmentId(depId);
							erDao.setRoleId(roleId);
							erDao.save();
		    				//默认将该角色赋予该人员归属部门下
		    				dDao.clear();
							dDao.setConditionDepartmentId("=",depId);
							dDao.setConditionRoleId("=", roleId);
							//部门下有角色则不插入数据
							if (dDao.isExist()) {
								continue;
							}
							dDao.clear();
							dDao.setDepartmentId(depId);
		    				dDao.setRoleId(roleId);
		    				dDao.save();
		    			}
		    		}
		    	}
		    }
		}
        BaseCollection<GenericBase> bc = new BaseCollection<>();
        return bc.toJSON(1, "");

	}
//	public static void main(String[] args) throws Exception {
//		String string = "{ 'roleName':'测试123','applicationId':1,'roleType':1,'enabled':true,'detaiRoleDepartment':[{'departmentId':'1'}, {'departmentId':'98'}],"
//				+ "'detailRoleEmployee':[{'deparmentId':'1'}, {'departmentId':'57'}, {'departmentId':'39'}, {'departmentId':'10438'}],"
//				+ "'detailRoleFunction':[{'functionId':'7','applicationId':'1'}, {'functionId':'36','applicationId':'1'},{'functionId':'23','applicationId':'1'}]}";
//		new NewRoleManageWithDepartmentAndFunAndEmp().execute(string,null);
//	}

}
