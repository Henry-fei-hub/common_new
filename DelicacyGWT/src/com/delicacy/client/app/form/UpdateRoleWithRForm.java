package com.delicacy.client.app.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.delicacy.client.ui.AbstractWizadPage;
import com.delicacy.client.ui.GenericWizadWindow;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.util.SC;

public class UpdateRoleWithRForm extends GenericWizadWindow {

    @Override
    public String getActionName() {
        // 需要根据实际业务修改
      //  return "ST_RoleWithR";
    	return	"EP_UpdateRoleManageWithDepartmentAndFunAndEmp";
    }

    @Override
    public boolean checkData(Map data) {
        return true;
    }

    @Override
    public int getPageCount() {
        return 3;
    }

    @Override
    public List<AbstractWizadPage> getPages() {
	    setCallback(new DSCallback() {
	        @Override
	        public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
	            // 请在此编写成功保存后要做的工作
	        	SC.say("修改成功");
	        }
	    });
	    setSubaction("update");
	    List<AbstractWizadPage> res = new ArrayList<>();
	    res.add(new RoleWithRModifyUpdateForm());
	    res.add(new BindRoleToDepartmentForm());
	    res.add(new BindRoleToEmployeeForm());
        return res;
    }

}

