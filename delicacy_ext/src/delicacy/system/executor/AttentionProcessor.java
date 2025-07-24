package delicacy.system.executor;

import delicacy.common.GenericProcessor;
import delicacy.servlet.HttpCookie;
import delicacy.system.bean.BaseSystemProcessAttention;
import delicacy.system.dao.SystemProcessAttention;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author caogx
 */
public class AttentionProcessor implements GenericProcessor {

    @Override
    public String execute(String creteria, HttpServletRequest request) throws Exception {
        HttpCookie cookies = new HttpCookie(request);
        //获取当前批阅的审批人，即当前登录人
        int employeeId = cookies.getOperatorId();
        BaseSystemProcessAttention bspa = new BaseSystemProcessAttention();
        bspa.setDataFromJSON(creteria);
        SystemProcessAttention spa = new SystemProcessAttention();
        spa.setPrimaryKeyFromBase(bspa);
        spa.load();
        spa.setDataFromBase(bspa);
        spa.setEmployeeId(employeeId);
        spa.setStatus(SystemProcessConstants.ATTENTION_STATUS_DONE);
        spa.update();
        return spa.generateBase().toOneLineJSON();
    }
    
}
