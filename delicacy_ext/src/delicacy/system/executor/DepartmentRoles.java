package delicacy.system.executor;

import java.io.StringReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import delicacy.common.BaseCollection;
import delicacy.common.BaseHelpUtils;
import delicacy.common.GenericProcessor;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import delicacy.system.bean.BaseDepartment;
import delicacy.system.bean.BaseRole;
import delicacy.system.bean.BaseRoleFunction;
import delicacy.system.dao.Department;
import delicacy.system.dao.Role;
import delicacy.system.dao.RoleFunction;

public class DepartmentRoles implements GenericProcessor {

    @Override
    public String execute(String creteria, HttpServletRequest request) throws Exception {
    	JSON parser = new JSON(new StringReader(creteria));
    	Map<String, Object> param = (Map<String, Object>) parser.parse(new BasicHandler());
    	//获取部门ID
    	int departmentId = BaseHelpUtils.getIntValue(param.get("departmentId"));
    	//设置存放该部门下的角色的map数据集
    	Map<Integer,Integer> roleIdMap = new HashMap<>();
    	//获取所有的角色数据集
    	Role roleDao = new Role();
    	//过滤超级管理员的角色
    	roleDao.setConditionRoleId(">",0);
    	List<BaseRole> roleList = roleDao.conditionalLoad();
    	if(BaseHelpUtils.isNullOrEmpty(roleList) || roleList.isEmpty()){
    		throw new SQLException("未检索到角色数据集");
    	}
    	//获取该部门下的角色数据集
    	RoleFunction roleFunctionDao = new RoleFunction();
    	roleFunctionDao.setConditionDepartmentId("=",departmentId);
    	roleFunctionDao.setConditionRoleId(">",0);
    	List<BaseRoleFunction> roleFunctionList = roleFunctionDao.conditionalLoad();
    	List<BaseRole> ds = new ArrayList<>();
    	if(!BaseHelpUtils.isNullOrEmpty(roleFunctionList) && !roleFunctionList.isEmpty()){
    		for(BaseRoleFunction e : roleFunctionList){
    			//获取角色Id
    			int roleId = BaseHelpUtils.getIntValue(e.getRoleId());
    			//获取用系统id
    			int applicationId = BaseHelpUtils.getIntValue(e.getApplicationId());
    			if(!roleIdMap.containsKey(roleId)){//过滤重复的角色id值
    				roleIdMap.put(roleId,roleId);
    				for(BaseRole bean : roleList){
    					if(roleId == BaseHelpUtils.getIntValue(bean.getRoleId()) && applicationId == BaseHelpUtils.getIntValue(bean.getApplicationId())){
    						ds.add(bean);
    						break;
    					}
    				}
    			}
    		}
    	}
    	
        BaseCollection<BaseRole> bc = new BaseCollection<>();
        bc.setCollections(ds);
        return bc.toJSON();
    }
    
}
