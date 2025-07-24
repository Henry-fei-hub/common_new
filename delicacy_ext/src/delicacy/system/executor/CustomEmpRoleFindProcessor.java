package delicacy.system.executor;

import delicacy.common.BaseCollection;
import delicacy.common.BaseHelpUtils;
import delicacy.common.GenericProcessor;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import delicacy.system.bean.BaseEmployeeRole;
import delicacy.system.bean.BaseRole;
import delicacy.system.dao.EmployeeRole;
import delicacy.system.dao.Role;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class CustomEmpRoleFindProcessor implements GenericProcessor {

    private static final Logger __logger = Logger.getLogger(CustomEmpRoleFindProcessor.class);

    @SuppressWarnings("unchecked")
	@Override
    public String execute(String creteria, HttpServletRequest request) throws Exception {
        Map<String, Object> result = null;
        if (!BaseHelpUtils.isNullOrEmpty(creteria)) {
            JSON parser = new JSON(new StringReader(creteria));
            result = (Map<String, Object>) parser.parse(new BasicHandler());
        }
        //获取所有的角色数据集
    	Role roleDao = new Role();
    	//过滤超级管理员的角色
    	roleDao.setConditionRoleId(">",0);
    	List<BaseRole> roleList = roleDao.conditionalLoad();
    	
    	
        int employeeId = BaseHelpUtils.getIntValue(result,"employeeId");
        int departmentId = BaseHelpUtils.getIntValue(result.get("departmentId"));
        //加载的时候判断用户是否有这个角色
        EmployeeRole empRole = new EmployeeRole();
        empRole.setConditionEmployeeId("=", employeeId);
        empRole.setConditionDepartmentId("=", departmentId);
        List<BaseEmployeeRole> tempEmpRoleList = empRole.conditionalLoad();
        //加载自己出自己拥有的角色的roleId
        List<Integer> roleIdList = new ArrayList<>();
    	for (BaseEmployeeRole baseEmployeeRole : tempEmpRoleList) {
    		roleIdList.add(baseEmployeeRole.getRoleId());
		}
        
        List<BaseRole> ds = new ArrayList<>();
        //检索出自己在部门下拥有的角色信息
        for (BaseRole baseRole : roleList) {
			if(roleIdList.contains(baseRole.getRoleId())){
				ds.add(baseRole);
			}
		}

        BaseCollection<BaseRole> bc = new BaseCollection<>();
        bc.setCollections(ds);
        return bc.toJSON();
    }
}
