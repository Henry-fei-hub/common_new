package delicacy.system.executor;

import delicacy.common.GenericProcessor;
import delicacy.system.bean.BaseDepartmentRole;
import delicacy.system.bean.BaseDepartmentRoleWithR;
import delicacy.system.dao.DepartmentRoleWithR;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author guangxun
 */
public class DepartmentRoleFunction implements GenericProcessor {

	@Override
	public String execute(String creteria, HttpServletRequest request) throws Exception {
		BaseDepartmentRoleWithR bean = new BaseDepartmentRoleWithR();
		bean.setDataFromJSON(creteria);
		DepartmentRoleWithR dao = new DepartmentRoleWithR();
		dao.setConditionDepartmentId("=", bean.getDepartmentId());
		dao.setConditionRoleId("=", bean.getRoleId());
		BaseDepartmentRole basebean = dao.executeQueryOneRow();
		if(basebean == null){
			throw new SQLException("Could not found department role");
		}
		dao.setDataFromBase(bean);
		dao.update();
		return dao.generateBaseExt().toOneLineJSON();
	}

	public void test(String creteria)throws Exception {
		BaseDepartmentRoleWithR bean = new BaseDepartmentRoleWithR();
		bean.setDataFromJSON(creteria);
		DepartmentRoleWithR dao = new DepartmentRoleWithR();
		dao.setConditionDepartmentId("=", bean.getDepartmentId());
		dao.setConditionRoleId("=", bean.getRoleId());
		BaseDepartmentRole basebean = dao.executeQueryOneRow();
		if(basebean == null){
			throw new SQLException("Could not found department role");
		}
		dao.setDataFromBase(bean);
		dao.update();
	}
	
	public static void main(String[] args){
		try{
//			String ss = " {\"roleId\":5,\"roleName\":\"设计师\",\"applicationId\":1,\"roleType\":5,\"enabled\":true,\"departmentId\":1,\"departmentRoleId\":77,\"detailRoleFunction\":[{\"functionId\":\"100\",\"applicationId\":\"1\"}, {\"functionId\":\"101\",\"applicationId\":\"1\"}, {\"functionId\":\"129\",\"applicationId\":\"1\"}, {\"functionId\":\"112\",\"applicationId\":\"1\"}, {\"functionId\":\"113\",\"applicationId\":\"1\"}, {\"functionId\":\"114\",\"applicationId\":\"1\"}, {\"functionId\":\"115\",\"applicationId\":\"1\"}, {\"functionId\":\"103\",\"applicationId\":\"1\"}]}";
//			DepartmentRoleFunction ddd = new DepartmentRoleFunction();
//			ddd.test(ss);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
