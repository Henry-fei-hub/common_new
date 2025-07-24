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

public class UpdateDepartmentWithSdForm extends GenericWizadWindow
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
		return 4;
	}

	@Override
	public List<AbstractWizadPage> getPages(){
		setCallback(new DSCallback() {
			@Override
			public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
				// Please code the program after save sucessfully.
			}
		});
		setSubaction("update");
		List<AbstractWizadPage> res = new ArrayList<>();
		res.add(new DepartmentWithSdUpdateForm());
		SetDepartmentProcessPanel systemprocessdepartment = new SetDepartmentProcessPanel();
		systemprocessdepartment.setPageMode(AbstractWizadPage.PAGE_MODE_UPDATE);
		res.add(systemprocessdepartment);
		SetDepartmentRolePanel departmentrole = new SetDepartmentRolePanel();
		departmentrole.setPageMode(AbstractWizadPage.PAGE_MODE_UPDATE);
		res.add(departmentrole);
		SubjectTypeLinkDepartmentDesignPanel subjectdepartment = new SubjectTypeLinkDepartmentDesignPanel();
		subjectdepartment.setPageMode(AbstractWizadPage.PAGE_MODE_UPDATE);
		res.add(subjectdepartment);
		return res;
	}


}

