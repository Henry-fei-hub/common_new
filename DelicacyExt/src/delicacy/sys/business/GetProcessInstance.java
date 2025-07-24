package delicacy.sys.business;

import java.io.StringReader;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import delicacy.common.BaseHelpUtils;
import delicacy.common.GenericProcessor;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;


/**
 *
 * @author caogx
 */
public class GetProcessInstance implements GenericProcessor {

    @Override
    public String execute(String creteria, HttpServletRequest request) throws Exception {
        JSON parser = new JSON(new StringReader(creteria));
        Map<String, Object> result = (Map<String, Object>) parser.parse(new BasicHandler());
        int processInstanceId = BaseHelpUtils.getIntValue(result, "processInstanceId");
        ProcessInformation pi = ProcessUtil.getAllInformation(processInstanceId);
        return pi.toOneLineJSON();
    }
    
}
