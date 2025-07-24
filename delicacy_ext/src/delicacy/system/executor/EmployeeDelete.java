package delicacy.system.executor;

import delicacy.common.GenericProcessor;
import delicacy.connection.ThreadConnection;
import delicacy.system.bean.BaseEmployee;
import delicacy.system.dao.Employee;
import delicacy.system.dao.EmployeeRole;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *职员删除
 * @author lxf
 */
public class EmployeeDelete implements GenericProcessor {
    private static Logger __logger = Logger.getLogger("");
    @Override
    public String execute(String creteria, HttpServletRequest request) throws Exception {
        ThreadConnection.beginTransaction();
        //删除职员表里的数据
        BaseEmployee bean = new BaseEmployee();
        bean.setDataFromJSON(creteria);
        Employee dao = new Employee();
        //根据职员ID删除
        dao.setPrimaryKeyFromBase(bean);
        dao.delete();
        //删除职员角色关联表的数据
        int employeeId = bean.getEmployeeId();
        EmployeeRole daoe = new EmployeeRole();
        daoe.setConditionEmployeeId("=", employeeId);
        daoe.conditionalDelete();
        ThreadConnection.commit();
        return bean.toOneLineJSON(0, null);
    }

}
