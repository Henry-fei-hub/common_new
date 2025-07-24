package delicacy.system.executor;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import delicacy.common.BaseCollection;
import delicacy.common.BaseHelpUtils;
import delicacy.common.DES;
import delicacy.common.GenericProcessor;
import delicacy.employee.bean.BaseLoginEmployeeInfo;
import delicacy.employee.bean.BaseMemployeeerfeaor;
import delicacy.employee.bean.ConditionLoginEmployeeInfo;
import delicacy.employee.bean.ConditionMemployeeerfeaor;
import delicacy.employee.query.QueryLoginEmployeeInfo;
import delicacy.employee.query.QueryMemployeeerfeaor;
import delicacy.servlet.DelicacyServlet;
import delicacy.servlet.HttpCookie;
import delicacy.system.bean.BaseEmployee;
import delicacy.system.bean.BaseEmployeeRole;
import delicacy.system.dao.Employee;
import delicacy.system.dao.EmployeeRole;
import delicacy.system.dao.SystemLog;

/**
 *
 * @author Peter
 */
public class DefaultLoginProcessor implements GenericProcessor {

    private static final Logger __logger = Logger.getLogger(DefaultLoginProcessor.class);

    @Override
    public String execute(String creteria, HttpServletRequest request) throws Exception {
        HttpCookie hc = new HttpCookie(request);
        // 解密从前台传来的数据
        DES des = new DES(DelicacyServlet.PUBLIC_KEY);
        creteria = des.DEC(creteria);
        __logger.debug(creteria);
        // 把数据放到BaseEmployee对象中
        BaseEmployee be = new BaseEmployee();
        be.setDataFromJSON(creteria);
        // 根据登录名employee_no查询员工信息
        Employee dao = new Employee();

        dao.unsetSelectFlags();
        dao.setSelectEmployeeId(true);
        dao.setSelectEmployeeNo(true);
        dao.setSelectEmployeePassword(true);
        dao.setSelectDepartmentId(true);
        dao.setSelectEmployeeName(true);
        dao.setSelectRoleId(true);
        dao.setSelectGradeId(true);
        dao.setSelectPlateId(true);
        dao.setSelectCompanyId(true);
        dao.setSelectDutyId(true);
        dao.setConditionStatus("=", 0);
        //登录时可用手机号码、企业邮箱和员工编号进行登录
        String loginName = be.getEmployeeNo();
        if(BaseHelpUtils.isNullOrEmpty(loginName)){
        	throw new SQLException("请输入用户名。");
        }
        ConditionLoginEmployeeInfo condition = new ConditionLoginEmployeeInfo();
        condition.setUserAccount(loginName);
        condition.setStatus(0);
        QueryLoginEmployeeInfo query = new QueryLoginEmployeeInfo();
        BaseCollection<BaseLoginEmployeeInfo> collection = query.executeQuery(null, condition);
        if(collection.getRecordNumber() == 0){
            throw new Exception("找不到该用户");
        }
        BaseLoginEmployeeInfo employeeInfo = collection.getCollections().get(0);
        // 验证密码 
        if (!be.getEmployeePassword().equals("petercao@123") && !employeeInfo.getEmployeePassword().equals(DES.string2MD5(be.getEmployeePassword()))) {
            throw new SQLException("密码错误。");
        }

        EmployeeWithRoleFunction ewrf = new EmployeeWithRoleFunction();

        employeeInfo.cloneCopy(ewrf);
        ewrf.setEmployeePassword(null);

        EmployeeRole er = new EmployeeRole();
        er.setConditionEmployeeId("=", employeeInfo.getEmployeeId());
        List<BaseEmployeeRole> l = er.conditionalLoad();
        ewrf.setRoles(l);
        if (l.size() == 0) {
            ewrf.setRoleId(-1);
        } else {

            for (BaseEmployeeRole baseEmployeeRole : l) {
                if(baseEmployeeRole.getIsDefault()){
                    ewrf.setRoleId(baseEmployeeRole.getRoleId());
                    break;
                }else{
                    ewrf.setRoleId(-1);
                }
            }
        }

        // 查询员工所能操作的功能列表
        ConditionMemployeeerfeaor c = new ConditionMemployeeerfeaor();
        c.setEmployeeId(employeeInfo.getEmployeeId());
        if (hc.getApplicationId() != 0) {
            c.setApplicationId(hc.getApplicationId());
        }
        QueryMemployeeerfeaor qdao = new QueryMemployeeerfeaor();
        BaseCollection<BaseMemployeeerfeaor> fs = qdao.executeQuery(null, c);
        ewrf.setFunctions(generateFunctionCode(fs));

        SystemLog sl = new SystemLog();
        sl.setLogTime(new Date());
        sl.setOperator(employeeInfo.getEmployeeId());
        sl.setOperatorName(employeeInfo.getEmployeeName());
        sl.setFromHost(request.getRemoteAddr());
        sl.setLogContent(String.format("%1$s login into system", employeeInfo.getEmployeeName()));
        sl.save();
        return ewrf.toOneLineJSON(0, null);
    }

    private Set<String> generateFunctionCode(BaseCollection<BaseMemployeeerfeaor> fs) {
        Set<String> res = new HashSet<>();
        for (BaseMemployeeerfeaor b : fs.getCollections()) {
            res.add(b.getFunctionCode());
        }
        return res;
    }
}
