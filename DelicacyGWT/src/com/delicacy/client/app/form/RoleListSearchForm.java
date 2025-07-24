package com.delicacy.client.app.form;

import com.delicacy.client.app.datasource.CDRoleList;
import com.delicacy.client.data.ClientUtil;
import com.smartgwt.client.widgets.form.SearchForm;
import com.smartgwt.client.widgets.form.fields.TextItem;

public class RoleListSearchForm extends SearchForm
{


	private final TextItem roleNameItem;

	public RoleListSearchForm() {
		setWidth100();
		setHeight100();
		setDataSource(CDRoleList.getInstance());
		roleNameItem = new TextItem("roleName", "角色名称");
		roleNameItem.setWidth("*");

		setItems(roleNameItem);

		setNumCols(2);
		ClientUtil.searchFormProcessAccordingToDevice(this);
	}

}
