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

public class CustomDeptRoleProcessor implements GenericProcessor {

    private static final Logger __logger = Logger.getLogger(CustomDeptRoleProcessor.class);

    @Override
    public String execute(String creteria, HttpServletRequest request) throws Exception {
        Map<String, Object> result = null;
        if (!BaseHelpUtils.isNullOrEmpty(creteria)) {
            JSON parser = new JSON(new StringReader(creteria));
            result = (Map<String, Object>) parser.parse(new BasicHandler());
        }
        String employeeId = BaseHelpUtils.getString(result.get("employeeId"));

        Department dao = new Department();
        dao.setConditionEnabled("=", Boolean.TRUE);//启用
        List<BaseDepartment> deptList = dao.conditionalLoad();

        Role role = new Role();
        List<BaseRole> baseRole = role.conditionalLoad();

        RoleFunction roleFun = new RoleFunction();
        List<BaseRoleFunction> roleFunList = roleFun.conditionalLoad();

        //加载的时候判断用户是否有这个角色
        //需要的条件 employeeId deptId Enabled is true  res===> Map<id,BaseEmployeeRole>
        EmployeeRole empRole = new EmployeeRole();
        empRole.setConditionEmployeeId("=", BaseHelpUtils.getIntValue(employeeId));
        List<BaseEmployeeRole> tempEmpRoleList = empRole.conditionalLoad();
        Map<Integer, BaseEmployeeRole> havsmap = new HashMap<>();
        if (tempEmpRoleList.size() > 0) {
            for (BaseEmployeeRole ber : tempEmpRoleList) {
//                System.out.println("EmployeeId="+ber.getEmployeeId()+"  RoleId="+ber.getRoleId()+" isDefault="+ber.getIsDefault());
                havsmap.put(ber.getEmployeeRoleId(), ber);
            }
        }

        List<BaseFunction> ds = new ArrayList<>();//模拟前端显示，将role数据封装进去
        if (deptList.size() > 0) {
            for (BaseDepartment dept : deptList) {
                BaseFunction bf = new BaseFunction();
                bf.setFunctionName(dept.getDepartmentName());
                bf.setFunctionCode("");
                bf.setFunctionId(dept.getDepartmentId());
                bf.setParentId(dept.getParentId());
                ds.add(bf);
            }
        }
        int idx = 100000;
        String tempVal = "";
        List<BaseFunction> nds = new ArrayList<>();
        //根据部门id找到角色id
        Map<Integer, Map<Integer, Integer>> roleMap = new HashMap<>();
        //向每个部门下设置角色
        if (deptList.size() > 0) {
            for (BaseDepartment dept : deptList) {
                Map<Integer, Integer> map = new HashMap<>();
                if (roleFunList.size() > 0) {
                    for (BaseRoleFunction baseRoleFun : roleFunList) {
                        if (Objects.equals(dept.getDepartmentId(), baseRoleFun.getDepartmentId())) {
                            map.put(baseRoleFun.getRoleId(), baseRoleFun.getRoleId());
                        }
                    }
                }
                roleMap.put(dept.getDepartmentId(), map);
            }
        }

        //找到部门下面对应的角色
        if (roleMap.size() > 0) {
            for (Map.Entry<Integer, Map<Integer, Integer>> entry : roleMap.entrySet()) {
                Integer deptId = entry.getKey();
                Map<Integer, Integer> map = entry.getValue();
                if (map.size() > 0) {
                    for (Map.Entry<Integer, Integer> m : map.entrySet()) {
                        Integer roleId = m.getValue();
                        if (baseRole.size() > 0) {
                            for (BaseRole rl : baseRole) {
                                if (Objects.equals(roleId, rl.getRoleId())) {
                                    BaseFunction bf = new BaseFunction();
                                    bf.setFunctionName(rl.getRoleName());
                                    bf.setIsDefault(Boolean.FALSE);
                                    if (havsmap.size() > 0) {
                                        for (Map.Entry<Integer, BaseEmployeeRole> mm : havsmap.entrySet()) {

                                            BaseEmployeeRole be = mm.getValue();
                                            if (Objects.equals(rl.getRoleId(), be.getRoleId()) && Objects.equals(be.getDepartmentId(), deptId) && be.getIsDefault()) {
                                                bf.setIsDefault(Boolean.TRUE);//加载的时候判断用户是否默认这个角色
                                            } else if (!bf.getIsDefault()) {
                                                bf.setIsDefault(Boolean.FALSE);
                                            }
                                        }
                                    }
                                    //这二个参数作用：部门id 角色id
                                    bf.setFunctionCode(roleId + "");
                                    bf.setParentId(deptId);

                                    tempVal = deptId + "" + roleId;
                                    int tempIdx = idx + Integer.parseInt(tempVal);
//                                    System.out.println(idx+"   tempIdx="+tempIdx+" =========tempVal="+tempVal);
                                    bf.setFunctionId(tempIdx);
                                    ds.add(bf);
                                }
                            }
                        }
                    }
                }
            }
        }
        ds.addAll(nds);
        BaseCollection<BaseFunction> bc = new BaseCollection<>();
        bc.setCollections(ds);
        return bc.toJSON();
    }
}
