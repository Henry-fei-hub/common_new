package delicacy.system.custom.handler;

import delicacy.common.BaseCollection;
import delicacy.common.BaseHelpUtils;
import delicacy.common.PaginationParameter;
import delicacy.connection.ThreadConnection;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import delicacy.system.bean.BaseRoleWithR;
import delicacy.system.dao.EmployeeRole;
import delicacy.system.dao.RoleFunction;
import delicacy.system.dao.RoleWithR;
import delicacy.system.handler.RoleWithRHandler;
import static delicacy.system.handler.RoleWithRHandler.setConditions;
import java.io.StringReader;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

public class CustomRoleWithRHandler extends RoleWithRHandler {

    private static final Logger __logger = Logger.getLogger(CustomRoleWithRHandler.class);

    @Override
    public String find(String json, int currentPage, int pageSize, String addtion) throws Exception {
        BaseCollection<BaseRoleWithR> rlist = new BaseCollection<>();
        BaseRoleWithR bean = new BaseRoleWithR();
        JSON parser = new JSON(new StringReader(json));
        Map<String, Object> params = (Map<String, Object>) parser.parse(new BasicHandler());
        bean.setDataFromMap(params);
        PaginationParameter pp = PaginationParameter.generateFromMap(params);
        if (pp != null) {
            if (pp.getCurrentPage() > 0) {
                currentPage = pp.getCurrentPage();
            }
            if (pp.getPageSize() > 0) {
                pageSize = pp.getPageSize();
            }
            if (pp.getCondition() != null) {
                addtion = pp.getCondition();
            }
        }
        RoleWithR dao = new RoleWithR();
        setConditions(bean, dao);
        //过滤超级管理员的角色
        dao.setConditionRoleId(">", 0);
        dao.setCurrentPage(currentPage);
        dao.setPageLines(pageSize);
        List<BaseRoleWithR> result = dao.conditionalLoadExt(addtion);
        rlist.setCollections(result);
        rlist.setTotalPages(dao.getTotalPages());
        rlist.setCurrentPage(dao.getCurrentPage());
        rlist.setPageLines(dao.getPageLines());
        rlist.setTotalLines(dao.getTotalLines());
        rlist.setRecordNumber(result.size());
        return rlist.toJSON(null);
    }

    @Override
    public String delete(String json) throws Exception {
        ThreadConnection.beginTransaction();
        BaseRoleWithR bean = new BaseRoleWithR();
        bean.setDataFromJSON(json);
        RoleWithR dao = new RoleWithR();
        setDeleteConditions(bean, dao);
        int num = dao.conditionalDelete();
        //删除角色的同时，删除角色人员管理表
        int roleId = BaseHelpUtils.getIntValue(bean.getRoleId());
        EmployeeRole erDao = new EmployeeRole();
        erDao.setConditionRoleId("=", roleId);
        erDao.conditionalDelete();
        //删除角色功能关联表
        RoleFunction rfDao = new RoleFunction();
        rfDao.setConditionRoleId("=", roleId);
        rfDao.conditionalDelete();
        ThreadConnection.commit();
        return bean.toOneLineJSON(num, null);
    }

}
