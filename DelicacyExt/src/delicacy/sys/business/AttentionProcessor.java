package delicacy.sys.business;

import common.utils.UserInfoUtils;
import delicacy.common.BaseCollection;
import delicacy.common.BaseHelpUtils;
import delicacy.common.GenericBase;
import delicacy.common.GenericProcessor;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import delicacy.sys.bean.BaseSystemProcessAttention;
import delicacy.sys.dao.SystemProcessAttention;

import javax.servlet.http.HttpServletRequest;
import java.io.StringReader;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author caogx
 */
public class AttentionProcessor implements GenericProcessor {

    @Override
    public String execute(String creteria, HttpServletRequest request) throws Exception {
        //获取当前批阅的审批人，即当前登录人
        int employeeId = UserInfoUtils.getEmployeeId(request);
        JSON parser = new JSON(new StringReader(creteria));
        Map<String, Object> result = (Map<String, Object>) parser.parse(new BasicHandler());
        List<BaseSystemProcessAttention> list = GenericBase.__getList(result.get("attentionList"),BaseSystemProcessAttention.newInstance());
        if(BaseHelpUtils.isNullOrEmpty(list) || list.size() == 0){
            throw new SQLException("操作异常：未获取到处理的数据集");
        }
        SystemProcessAttention dao = new SystemProcessAttention();
        for(BaseSystemProcessAttention e : list){
            //获取住建id
            int id = BaseHelpUtils.getIntValue(e.getSystemProcessAttentionId());
            int status = BaseHelpUtils.getIntValue(e.getStatus());
            if(status == SystemProcessConstants.ATTENTION_STATUS_DONE){
                continue;
            }
            dao.clear();
            dao.setSystemProcessAttentionId(id);
            if(dao.load()){
                dao.setEmployeeId(employeeId);
                dao.setStatus(SystemProcessConstants.ATTENTION_STATUS_DONE);
                dao.update();
            }
        }
        BaseCollection<GenericBase> bc = new BaseCollection<>();
        return bc.toJSON(0,null);
    }
    
}
