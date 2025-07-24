package delicacy.system.custom.handler;

import org.apache.log4j.Logger;
import delicacy.system.bean.BaseFunction;
import delicacy.system.dao.Function;
import delicacy.connection.ThreadConnection;
import delicacy.common.BaseHelpUtils;
import delicacy.system.dao.RoleFunction;
import delicacy.system.handler.FunctionHandler;

public class CustomFunctionHandler extends FunctionHandler {

    private static final Logger __logger = Logger.getLogger(CustomFunctionHandler.class);

    @Override
    public String save(String json) throws Exception {
        ThreadConnection.beginTransaction();
        BaseFunction bean = new BaseFunction();
        bean.setDataFromJSON(json);
        Function dao = new Function();
        dao.setDataFromBase(bean);
        int num = dao.save();
        dao.setDataToBase(bean);
        //新增的同时把新增的功能赋给超级管理员admin的角色(超级管理员)
        RoleFunction rfDao = new RoleFunction();
        rfDao.setRoleId(0);
        rfDao.setFunctionId(BaseHelpUtils.getIntValue(dao.getFunctionId()));
        rfDao.save();
        ThreadConnection.commit();
        return bean.toOneLineJSON(num, null);
    }

    @Override
    public String saveOrUpdate(String json) throws Exception {
        BaseFunction bean = new BaseFunction();
        bean.setDataFromJSON(json);
        Function dao = new Function();
        dao.setPrimaryKeyFromBase(bean);
        int ret = 0;
        if (dao.isPrimaryKeyNull()) {
            dao.setDataFromBase(bean);
            ret = dao.save();
            bean = dao.generateBase();
            //新增的同时把新增的功能赋给超级管理员admin的角色(超级管理员)
            RoleFunction rfDao = new RoleFunction();
            rfDao.setRoleId(0);
            rfDao.setFunctionId(BaseHelpUtils.getIntValue(dao.getFunctionId()));
            rfDao.save();
        } else if (dao.load()) {
            dao.setDataFromBase(bean);
            ret = dao.update();
            bean = dao.generateBase();
        }
        return bean.toOneLineJSON(ret, null);
    }

    @Override
    public String delete(String json) throws Exception {
        ThreadConnection.beginTransaction();
        BaseFunction bean = new BaseFunction();
        bean.setDataFromJSON(json);
        Function dao = new Function();
        setDeleteConditions(bean, dao);
        int num = dao.conditionalDelete();
        //删除功能的同时，删除角色功能关联表的数据
        int functionId = BaseHelpUtils.getIntValue(bean.getFunctionId());
        RoleFunction rfDao = new RoleFunction();
        rfDao.setConditionFunctionId("=", functionId);
        rfDao.conditionalDelete();
        ThreadConnection.commit();
        return bean.toOneLineJSON(num, null);
    }

}
