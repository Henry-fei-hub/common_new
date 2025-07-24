package delicacy.system.executor;

import delicacy.common.BaseCollection;
import delicacy.common.BaseHelpUtils;
import delicacy.common.GenericProcessor;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import delicacy.system.bean.BaseDepartment;
import delicacy.system.bean.BaseEmployeeRole;
import delicacy.system.bean.BaseFunction;
import delicacy.system.bean.BaseRole;
import delicacy.system.bean.BaseRoleFunction;
import delicacy.system.dao.Department;
import delicacy.system.dao.EmployeeRole;
import delicacy.system.dao.Role;
import delicacy.system.dao.RoleFunction;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class CustomEmpSelectRoleProcessor implements GenericProcessor {

    private static final Logger __logger = Logger.getLogger(CustomEmpSelectRoleProcessor.class);

    @Override
    public String execute(String creteria, HttpServletRequest request) throws Exception {
        Map<String, Object> result = null;
        if (!BaseHelpUtils.isNullOrEmpty(creteria)) {
            JSON parser = new JSON(new StringReader(creteria));
            result = (Map<String, Object>) parser.parse(new BasicHandler());
        }
        String employeeId = (String) result.get("employeeId");

        //加载的时候判断用户是否有这个角色
        EmployeeRole empRole = new EmployeeRole();
        empRole.setConditionEmployeeId("=", BaseHelpUtils.getIntValue(employeeId));
        List<BaseEmployeeRole> tempEmpRoleList = empRole.conditionalLoad();

        List<BaseFunction> ds = new ArrayList<>();//模拟前端显示，将role数据封装进去 
        int idx = 100000;
        String tempVal="";
        List<BaseFunction> nds = new ArrayList<>();
        for (BaseEmployeeRole rl : tempEmpRoleList) {
            BaseFunction bf = new BaseFunction();
            bf.setParentId(rl.getRoleId());
            tempVal = rl.getDepartmentId() + "" + rl.getRoleId();
            int tempIdx = idx + Integer.parseInt(tempVal);
//            System.out.println(idx+"  tempIdx=" + tempIdx + " =========tempVal=" + tempVal);
            bf.setFunctionId(tempIdx);
            ds.add(bf);
        }

        ds.addAll(nds);
        BaseCollection<BaseFunction> bc = new BaseCollection<>();
        bc.setCollections(ds);
        return bc.toJSON();
    }
}
