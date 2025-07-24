package delicacy.system.executor;

import delicacy.common.BaseCollection;
import delicacy.common.BaseHelpUtils;
import delicacy.common.GenericProcessor;
import delicacy.connection.ThreadConnection;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import delicacy.system.bean.BaseFunction;
import delicacy.system.bean.BaseRoleFunction;
import delicacy.system.dao.Function;
import delicacy.system.dao.RoleFunction;
import java.io.StringReader;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 * 角色功能更新
 * @author lxf
 */
public class OnRoleFunctionUpdate implements GenericProcessor {
    private static Logger __logger = Logger.getLogger("");

    @Override
    public String execute(String creteria, HttpServletRequest request) throws Exception {
        ThreadConnection.beginTransaction();
        int result = -1;
        JSON parser = new JSON(new StringReader(creteria));
        Map<String, Object> param = (Map<String, Object>) parser.parse(new BasicHandler());
        int flag = BaseHelpUtils.getIntValue(param,"flag");
        int roleId = BaseHelpUtils.getIntValue(param,"roleId");
        int departmentId = BaseHelpUtils.getIntValue(param,"departmentId");
        int parentId = BaseHelpUtils.getIntValue(param,"parentId");
        int functionId = BaseHelpUtils.getIntValue(param,"functionId");
        int applicationId = BaseHelpUtils.getIntValue(param,"applicationId");
        RoleFunction dao = new RoleFunction();
        Function fDao = new Function();
        //检索当前功能下的子级功能
        fDao.setConditionParentId("=",functionId);
        List<BaseFunction> listBean = fDao.conditionalLoad();
        if(flag == 1){//表示取消操作，即删除
            //删除当前功能数据
            delete(departmentId, roleId, functionId, applicationId,listBean);
            //删除当前功能的子级功能的数据
//            if(!BaseHelpUtils.isNullOrEmpty(listBean) && !listBean.isEmpty()){
//                for(BaseFunction e : listBean){
//                    delete(departmentId, roleId, BaseHelpUtils.getIntValue(e.getFunctionId()), applicationId);
//                }
//            }
            //检索父级功能,如果父级功能下还有别的功能，则不删除，如果不存在则删除
            fDao.clear();
            fDao.setConditionParentId("=",parentId);
            fDao.setConditionFunctionId("<>",functionId);
            List<BaseFunction> resultList = fDao.conditionalLoad();
            Object[] ids = new Object[resultList.size()];
            if(!BaseHelpUtils.isNullOrEmpty(resultList) && !resultList.isEmpty()){
                int i = 0;
                for(BaseFunction e : resultList){
                    ids[i] = BaseHelpUtils.getIntValue(e.getFunctionId());
                    i++;
                }
                //根据当前父级功能下的子级功能id集合去RoleFunction表中检索数据
                dao.clear();
                dao.addCondition(BaseRoleFunction.CS_FUNCTION_ID,"in", ids);
                BaseRoleFunction bean = dao.executeQueryOneRow();
                if(BaseHelpUtils.isNullOrEmpty(bean)){//表示当前父级功能没有选择别的子级功能，则直接删除
                    resultList = null;
                    delete(departmentId, roleId, parentId, applicationId,resultList);
                }
                
            }else{//表示当前父级功能没有别的子级功能，则直接删除
                resultList = null;
                delete(departmentId, roleId, parentId, applicationId,resultList);
            }
            result = 0;
        }else if(flag == 2){//表示选中操作，即新增
            save(departmentId, roleId, functionId, applicationId,parentId);
            //检索当前功能的子级功能，如果存在则也新增
            if(!BaseHelpUtils.isNullOrEmpty(listBean) && !listBean.isEmpty()){
                for(BaseFunction e : listBean){
                    save(departmentId, roleId, BaseHelpUtils.getIntValue(e.getFunctionId()), applicationId,0);
                }
            }
            //检索当前父级功能在表RoleFunction中是否存在记录，如果不存在，则新增
//            dao.clear();
//            dao.setConditionDepartmentId("=",departmentId);
//            dao.setConditionRoleId("=",roleId);
//            dao.setConditionApplicationId("=",applicationId);
//            dao.setConditionFunctionId("=",parentId);
//            BaseRoleFunction bean = dao.executeQueryOneRow();
//            if(BaseHelpUtils.isNullOrEmpty(bean)){
//                save(departmentId, roleId, parentId, applicationId);
//            }
            result = 0;
        }
        ThreadConnection.commit();
        BaseCollection bc = new BaseCollection();
        return bc.toJSON(result,null);
    }
    
    /**
     * 新增部门、角色、功能数据
     * @param departmentId
     * @param roleId
     * @param functionId
     * @param applicationId
     * @param parentId
     * @throws SQLException 
     */
    public void save(int departmentId,int roleId,int functionId,int applicationId,int parentId) throws SQLException{
        RoleFunction dao = new RoleFunction();
        dao.setDepartmentId(departmentId);
        dao.setRoleId(roleId);
        dao.setFunctionId(functionId);
        dao.setApplicationId(applicationId);
        dao.save();
        if(parentId > 0){
            //根据当前父级功能去检索数据，如果不存在，则保存
            dao.clear();
            dao.setConditionDepartmentId("=", departmentId);
            dao.setConditionRoleId("=",roleId);
            dao.setConditionFunctionId("=",parentId);
            dao.setConditionApplicationId("=",applicationId);
            BaseRoleFunction bean = dao.executeQueryOneRow();
            //如果父级功能不存在，则插入
            if(BaseHelpUtils.isNullOrEmpty(bean)){
                Function fdao = new Function();
                fdao.setConditionFunctionId("=",parentId);
                BaseFunction fbean = fdao.executeQueryOneRow();
                int parent = 0;
                //根据父级功能id去检索其父级id，如果存在，则往上递归处理
                if(!BaseHelpUtils.isNullOrEmpty(fbean)){
                    parent = BaseHelpUtils.getIntValue(fbean.getParentId());
                }
                save(departmentId, roleId, parentId, applicationId, parent);
            }
        }
    }
    
    /**
     * 删除部门、角色、功能数据
     * @param departmentId
     * @param roleId
     * @param functionId
     * @param applicationId
     * @param childList
     * @throws SQLException 
     */
    public void delete(int departmentId,int roleId,int functionId,int applicationId,List<BaseFunction> childList) throws SQLException{
        RoleFunction dao = new RoleFunction();
        dao.setConditionDepartmentId("=", departmentId);
        dao.setConditionRoleId("=", roleId);
        dao.setConditionFunctionId("=", functionId);
        dao.setConditionApplicationId("=",applicationId);
        dao.conditionalDelete();
        //删除子级功能
        if(!BaseHelpUtils.isNullOrEmpty(childList) && !childList.isEmpty()){
            for(BaseFunction e : childList){
                int childFunctionId = BaseHelpUtils.getIntValue(e.getFunctionId());
                //获取当前功能的子级功能
                Function fdao = new Function();
                fdao.setConditionParentId("=",childFunctionId);
                List<BaseFunction> child = fdao.conditionalLoad();
                delete(departmentId, roleId, childFunctionId, applicationId, child);
            }
        }
    }

}
