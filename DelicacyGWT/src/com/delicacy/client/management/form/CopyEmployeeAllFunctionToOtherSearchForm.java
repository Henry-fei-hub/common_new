package com.delicacy.client.management.form;

import java.util.*;
import com.smartgwt.client.widgets.form.fields.*;
import com.smartgwt.client.widgets.form.SearchForm;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.management.datasource.CDCopyEmployeeAllFunctionToOther;

public class CopyEmployeeAllFunctionToOtherSearchForm extends SearchForm
{


	private final TextItem employeeIdItem;

	public CopyEmployeeAllFunctionToOtherSearchForm() {
		setWidth100();
		setHeight100();
		setDataSource(CDCopyEmployeeAllFunctionToOther.getInstance());
		employeeIdItem = new TextItem("employeeId", "员工编码");

		setItems(employeeIdItem);

		setNumCols(2);
		ClientUtil.searchFormProcessAccordingToDevice(this);
	}

}
