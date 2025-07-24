package delicacy.system.executor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import delicacy.common.BaseCollection;
import delicacy.common.BaseHelpUtils;
import delicacy.common.GenericProcessor;
import delicacy.system.bean.BaseDepartment;
import delicacy.system.bean.BaseRoleFunction;
import delicacy.system.dao.Department;
import delicacy.system.dao.RoleFunction;

public class DepartmentPart implements GenericProcessor {

    @Override
    public String execute(String creteria, HttpServletRequest request) throws Exception {
    	//获取所有有效的部门数据集
    	Department departmentDao = new Department();
    	departmentDao.setConditionEnabled("=", Boolean.TRUE);//启用
        List<BaseDepartment> departmentList = departmentDao.conditionalLoad();
        //如果部门数据为空则抛出异常
        if(BaseHelpUtils.isNullOrEmpty(departmentList) || departmentList.isEmpty()){
        	throw new SQLException("没有可加载的部门信息");
        }
    	//定义存放所有拥有角色的部门及其上级部门id的map集合
    	Map<Integer,Integer> parentAndChildMap = new HashMap<>();
    	//获取到所有拥有角色的部门id
    	RoleFunction roleFunctionDao = new RoleFunction();
    	//只获取部门id>0的数据集
    	roleFunctionDao.setConditionDepartmentId(">",0);
    	List<BaseRoleFunction> roleFunctionList = roleFunctionDao.conditionalLoad();
    	if(!BaseHelpUtils.isNullOrEmpty(roleFunctionList) && !roleFunctionList.isEmpty()){
    		for(BaseRoleFunction e : roleFunctionList){
    			//获取部门id
    			int departmentId = BaseHelpUtils.getIntValue(e.getDepartmentId());
    			getDepartmentIds(parentAndChildMap, departmentId, departmentList);
    		}
    	}
    	List<BaseDepartment> ds = new ArrayList<>();
    	for(BaseDepartment e : departmentList){
    		//获取到部门id
    		int departmentId = BaseHelpUtils.getIntValue(e.getDepartmentId());
    		if(!parentAndChildMap.isEmpty() && parentAndChildMap.containsKey(departmentId)){
    			ds.add(e);
    		}
    	}
    	
        BaseCollection<BaseDepartment> bc = new BaseCollection<>();
        bc.setCollections(ds);
        return bc.toJSON();
    }
    
    /**
     * 获取拥有角色的部门id和其父级部门id数据集
     * @param parentAndChildMap
     * @param departmentId
     * @param departmentList
     */
    private void getDepartmentIds(Map parentAndChildMap,int departmentId,List<BaseDepartment> departmentList){
    	parentAndChildMap.put(departmentId,departmentId);
    	//获取当前部门的父级部门id
    	for(BaseDepartment e : departmentList){
    		if(departmentId == BaseHelpUtils.getIntValue(e.getDepartmentId())){
    			//获取当前部门的父级部门id
    			int parentId = BaseHelpUtils.getIntValue(e.getParentId());
    			if(parentId > 0){
    				getDepartmentIds(parentAndChildMap, parentId, departmentList);
    			}
    		}
    	}
    }

}
