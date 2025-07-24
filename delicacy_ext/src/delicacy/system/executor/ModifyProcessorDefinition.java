package delicacy.system.executor;

import delicacy.common.GenericProcessor;
import delicacy.connection.ThreadConnection;
import delicacy.system.bean.BaseSystemProcessWithSss;
import delicacy.system.dao.SystemProcessWithSss;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author guangxun
 */
public class ModifyProcessorDefinition implements GenericProcessor {

    @Override
    public String execute(String creteria, HttpServletRequest request) throws Exception {
        BaseSystemProcessWithSss bean = new BaseSystemProcessWithSss();
        bean.setDataFromJSON(creteria);
        SystemProcessWithSss dao = new SystemProcessWithSss();
        dao.setDeleteDetailSystemProcessActivity(true);
        dao.setDeleteDetailSystemProcessLink(true);
        ThreadConnection.beginTransaction();
        dao.setPrimaryKeyFromBase(bean);
        if (dao.load()) {
            dao.setDataFromBase(bean);
            dao.update();
        }
        ThreadConnection.commit();
        return dao.generateBaseExt().toOneLineJSON();
    }

}
