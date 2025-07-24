package com.delicacy.client.app.panel;

import java.util.Map;
import java.util.logging.Logger;

import com.delicacy.client.ui.AbstractWizadPage;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class PermissionManageDesignPanel extends AbstractWizadPage {
	private final Logger logger = Logger.getLogger("");

	public PermissionManageDesignPanel() {

		HLayout mainLayout = new HLayout(10);
		mainLayout.setWidth100();
		mainLayout.setHeight100();

		TabSet topTabSet = new TabSet();
		topTabSet.setTabBarPosition(Side.TOP);

		Tab tTab1 = new Tab("新增权限");
		FunDepartmentRoleEmployeePanel addPanel = new FunDepartmentRoleEmployeePanel();
		tTab1.setPane(addPanel);

		Tab tTab2 = new Tab("修改权限");
		UpdateFunDepartmentRoleEmployeePanel updatePanel = new UpdateFunDepartmentRoleEmployeePanel();
		tTab2.setPane(updatePanel);

		Tab tTab3 = new Tab("复制权限");
		CopyFunDepartmentRoleEmployeePanel copyPanel = new CopyFunDepartmentRoleEmployeePanel();
		tTab3.setPane(copyPanel);

		topTabSet.addTab(tTab1);
		topTabSet.addTab(tTab2);
		topTabSet.addTab(tTab3);

		mainLayout.addMember(topTabSet);
		addMember(mainLayout);

	}

	@Override
	public Map getValuesAsMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkData() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void startEdit() {
		// TODO Auto-generated method stub

	}
}
