package delicacy.system.executor;

import delicacy.common.GenericProcessor;
import delicacy.connection.ThreadConnection;
import delicacy.system.bean.BaseSystemProcessWithSss;
import delicacy.system.dao.SystemProcessWithSss;
import delicacy.system.handler.SystemProcessWithSssHandler;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author guangxun
 */
public class DeleteProcessorDefinition implements GenericProcessor {

    @Override
    public String execute(String creteria, HttpServletRequest request) throws Exception {
        BaseSystemProcessWithSss bean = new BaseSystemProcessWithSss();
        bean.setDataFromJSON(creteria);
        ThreadConnection.beginTransaction();
        SystemProcessWithSss dao = new SystemProcessWithSss();
        SystemProcessWithSssHandler.setDeleteConditions(bean, dao);
        int num = dao.conditionalDelete();
        ThreadConnection.commit();
        return bean.toOneLineJSON(num, null);
    }
    
}
