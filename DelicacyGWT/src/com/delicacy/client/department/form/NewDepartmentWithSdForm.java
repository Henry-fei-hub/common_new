package com.delicacy.client.department.form;

import com.delicacy.client.app.panel.SetDepartmentProcessPanel;
import com.delicacy.client.app.panel.SetDepartmentRolePanel;
import com.delicacy.client.app.panel.SubjectTypeLinkDepartmentDesignPanel;
import com.delicacy.client.ui.AbstractWizadPage;
import com.delicacy.client.ui.GenericWizadWindow;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NewDepartmentWithSdForm extends GenericWizadWindow
{


	@Override
	public String getActionName(){
		// 需要根据实际业务修改
		return "ST_CustomDepartmentWithSd";
	}

	@Override
	public boolean checkData(Map data){
		return true;
	}

	@Override
	public int getPageCount(){
		return 3;
	}

	@Override
	public List<AbstractWizadPage> getPages(){
		setCallback(new DSCallback() {
			@Override
			public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
				// 请在此编写成功保存后要做的工作
			}
		});
		List<AbstractWizadPage> res = new ArrayList<>();
		res.add(new DepartmentWithSdNewForm());
		res.add(new SetDepartmentProcessPanel());
		res.add(new SetDepartmentRolePanel());
		return res;
	}


}

