package delicacy.system.executor;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import delicacy.common.BaseHelpUtils;
import delicacy.common.DES;
import delicacy.common.GenericProcessor;
import delicacy.system.bean.BaseEmployee;
import delicacy.system.dao.Employee;

/**
 * 职员初始化密码
 * @author lxf
 */
public class EmployeePwdReset implements GenericProcessor {
    private static Logger __logger = Logger.getLogger("");

    @Override
    public String execute(String creteria, HttpServletRequest request) throws Exception {
        int result = -1;
        BaseEmployee bean = new BaseEmployee();
        bean.setDataFromJSON(creteria);
        Employee dao = new Employee();
        dao.setPrimaryKeyFromBase(bean);
        if(dao.load()){
        	//获取身份证号
        	String card = dao.getCard();
        	String pwd = "";
        	if(!BaseHelpUtils.isNullOrEmpty(card) && card.length() == 18){
        		//获取身份证后六位
        		pwd = card.substring(card.length()-6);
        	}else{
        		pwd = dao.getEmployeeNo();
        	}
            dao.setEmployeePassword(DES.string2MD5(pwd));
            dao.update();
            result = 0;
        }else{
            throw new SQLException(String.format("没有发现员工，工号 %1$d", bean.getEmployeeId()));
        }
        return bean.toOneLineJSON(result, null);
    }

}
