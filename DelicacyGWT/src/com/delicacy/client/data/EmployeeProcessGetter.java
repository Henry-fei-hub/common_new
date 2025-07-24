package com.delicacy.client.data;

import com.delicacy.client.MapUtils;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author caogx
 */
public class EmployeeProcessGetter implements DSCallback {

    @Override
    public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
        Map params = new HashMap();
        params.put("employeeId", ClientUtil.getUserId());
        KeyValueManager.getValueMapFromQuery("employee_processes", "EmployeeProcess", MapUtils.toJSON(params));
    }
    
}
