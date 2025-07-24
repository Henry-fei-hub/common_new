package delicacy.system.executor;

import java.io.StringReader;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import delicacy.common.BaseCollection;
import delicacy.common.BaseHelpUtils;
import delicacy.common.GenericBase;
import delicacy.common.GenericProcessor;
import delicacy.connection.ThreadConnection;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import delicacy.json.ParseException;
import delicacy.system.bean.BaseEmployeeRole;
import delicacy.system.dao.DepartmentRole;
import delicacy.system.dao.DepartmentRoleFunction;
import delicacy.system.dao.EmployeeRole;
import delicacy.system.dao.Role;
import delicacy.system.dao.RoleFunction;

/**
 *
 * @author lsf
 */
public class DeleteRoleManageWithDepartmentAndFunAndEmp implements GenericProcessor {
	@SuppressWarnings("unchecked")
	@Override
	public String execute(String creteria, HttpServletRequest request) throws SQLException, ParseException {
		@SuppressWarnings("rawtypes")
		JSON parser = new JSON(new StringReader(creteria));
		System.out.println(parser);
		BaseCollection<GenericBase> bc = new BaseCollection<>();
		Map<String, Object> param = (Map<String, Object>) parser.parse(new BasicHandler());
		ThreadConnection.beginTransaction();
		Role roleDao = new Role();
		//获取角色ID
		int roleId = BaseHelpUtils.getIntValue(param.get("roleId"));
		if(roleId <= 0){
			throw new SQLException("操作异常：获取角色ID失败");
		}
		// 根据roleId查看employee_roles表里是否存在
		EmployeeRole erDao = new EmployeeRole();
		erDao.setConditionRoleId("=", roleId);
		List<BaseEmployeeRole> list = erDao.conditionalLoad();
		if (!BaseHelpUtils.isNullOrEmpty(list)||list.size()>0) {
			//存在则不能删除该角色
			throw new SQLException("操作异常：该角色有人在使用，不能删除");
		}
		roleDao.setRoleId(roleId);
		if(roleDao.load()){
			roleDao.delete();
		}
		// 根据roleId删除role_functions表里的数据
		RoleFunction rfDao = new RoleFunction();
		rfDao.setConditionRoleId("=", roleId);
		rfDao.conditionalDelete();
		
		//删除部门下角色
		DepartmentRole drDao = new DepartmentRole();
		drDao.setConditionRoleId("=", roleId);
		drDao.conditionalDelete();
		
		//删除部门下角色功能数据
		DepartmentRoleFunction drfDao = new DepartmentRoleFunction();
		drfDao.setConditionRoleId("=",roleId);
		drfDao.conditionalDelete();
		ThreadConnection.commit();
		return bc.toJSON(0, "");

	}

}
