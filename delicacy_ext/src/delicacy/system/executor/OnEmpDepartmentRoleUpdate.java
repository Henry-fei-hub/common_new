package delicacy.system.executor;

import delicacy.common.BaseCollection;
import delicacy.common.BaseHelpUtils;
import delicacy.common.GenericProcessor;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import delicacy.system.dao.EmployeeRole;
import delicacy.system.dao.RoleFunction;
import java.io.StringReader;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 * 人员角色更新
 * @author lxf
 */
public class OnEmpDepartmentRoleUpdate implements GenericProcessor {
    private static Logger __logger = Logger.getLogger("");

    @Override
    public String execute(String creteria, HttpServletRequest request) throws Exception {
        int result = -1;
        JSON parser = new JSON(new StringReader(creteria));
        Map<String, Object> param = (Map<String, Object>) parser.parse(new BasicHandler());
        int flag = BaseHelpUtils.getIntValue(param,"flag");
        int roleId = BaseHelpUtils.getIntValue(param,"roleId");
        int departmentId = BaseHelpUtils.getIntValue(param,"departmentId");
        int employeeId = BaseHelpUtils.getIntValue(param,"employeeId");
        EmployeeRole dao = new EmployeeRole();
        if(flag == 1){//表示取消操作，即删除
            dao.setConditionDepartmentId("=", departmentId);
            dao.setConditionRoleId("=", roleId);
            dao.setConditionEmployeeId("=",employeeId);
            dao.conditionalDelete();
            result = 0;
        }else if(flag == 2){//表示选中操作，即新增
            dao.setDepartmentId(departmentId);
            dao.setRoleId(roleId);
            dao.setEmployeeId(employeeId);
            dao.save();
            result = 0;
        }
        BaseCollection bc = new BaseCollection();
        return bc.toJSON(result,null);
    }

}
