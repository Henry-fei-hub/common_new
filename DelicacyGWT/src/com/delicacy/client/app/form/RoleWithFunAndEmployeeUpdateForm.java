package com.delicacy.client.app.form;

import java.util.*;
import java.util.logging.Logger;

import com.smartgwt.client.widgets.form.fields.*;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.*;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.ui.AbstractWizadPage;
import com.delicacy.client.ui.DelicacyListGrid;
import com.google.gwt.aria.client.Role;
import com.delicacy.client.BaseHelpUtils;
import com.delicacy.client.app.datasource.DSRoleWithR;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.management.form.EmployeePanel;
import com.delicacy.client.management.form.FunctionPanel;
import com.delicacy.client.management.form.RoleDetaiRoleDepartment;
import com.delicacy.client.management.form.SystemProcessDetailSystemProcessDepartment;

/**
 * @author J&A
 */
public class RoleWithFunAndEmployeeUpdateForm extends AbstractWizadPage {

	private static final Logger __logger = Logger.getLogger("");
	private EmployeePanel employeedetailPanel;
	private FunctionPanel functiondetailPanel;
	protected List<FormItem> empFormItems = new ArrayList<>();
	protected DynamicForm empForm = new DynamicForm();
	protected List<FormItem> funFormItems = new ArrayList<>();
	protected DynamicForm funForm = new DynamicForm();

	public RoleWithFunAndEmployeeUpdateForm() {
		HLayout main = new HLayout(10);
		main.setHeight100();
		main.setWidth100();

		VLayout functionPanel = new VLayout(5);
		functionPanel.setWidth("50%");
		functionPanel.setHeight100();

		// function头部面板
		VLayout funTitlePanel = new VLayout(5);
		funTitlePanel.setWidth100();
		funTitlePanel.setHeight(50);

		Label functionTitle = new Label("功能信息");
		functionTitle.setHeight(50);
		functionTitle.setAlign(Alignment.CENTER);

		funTitlePanel.addMember(functionTitle);
		functionPanel.addMember(funTitlePanel);

		// 搜索面板
		HLayout funSearchPanel = new HLayout(5);
		funSearchPanel.setWidth100();
		funSearchPanel.setHeight(50);
		funForm.setWidth("75%");
		funForm.setHeight(50);
		TextItem functionNameItem = new TextItem("functionName", "功能名称");
		functionNameItem.setWidth("*");
		funFormItems.add(functionNameItem);
		TextItem functionCodeItem = new TextItem("functionCode", "功能编码");
		functionCodeItem.setWidth("*");
		funFormItems.add(functionCodeItem);

		funForm.setItems(funFormItems.toArray(new FormItem[funFormItems.size()]));
		funForm.setNumCols(4);
		funSearchPanel.addMember(funForm);
		IButton searchButton = new IButton("搜索");
		funSearchPanel.addMember(searchButton);
		searchButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				functiondetailPanel.setFunctionName(BaseHelpUtils.getString(funForm.getValue("functionName")));

				functiondetailPanel.setFunctionCode(BaseHelpUtils.getString(funForm.getValue("functionCode")));

				functiondetailPanel.startEdit();
			}
		});
		//搜索功能已弃用
		//functionPanel.addMember(funSearchPanel);

		functiondetailPanel = new FunctionPanel();
		functiondetailPanel.setWidth100();
		functiondetailPanel.setHeight100();
		functionPanel.addMember(functiondetailPanel);
		main.addMember(functionPanel);

		VLayout employeePanel = new VLayout(5);
		employeePanel.setWidth("50%");
		employeePanel.setHeight100();

		// 头部面板
		VLayout titlePanel = new VLayout(5);
		titlePanel.setWidth100();
		titlePanel.setHeight(50);

		Label employeeTitle = new Label("人员信息");
		employeeTitle.setHeight(50);
		employeeTitle.setAlign(Alignment.CENTER);

		titlePanel.addMember(employeeTitle);
		employeePanel.addMember(titlePanel);

		// 搜索面板
		HLayout searchPanel = new HLayout(5);
		searchPanel.setWidth100();
		searchPanel.setHeight(50);
		empForm.setWidth("75%");
		empForm.setHeight(50);
		TextItem employeeNoItem = new TextItem("employeeNo", "员工编号");
		employeeNoItem.setWidth("*");
		empFormItems.add(employeeNoItem);
		TextItem employeeNameItem = new TextItem("employeeName", "员工姓名");
		employeeNameItem.setWidth("*");
		empFormItems.add(employeeNameItem);
		IPickTreeItem departmentIdItem = new IPickTreeItem("departmentId", "部门");
		departmentIdItem.setWidth("*");
		departmentIdItem.setCanSelectParentItems(true);
		departmentIdItem.setValueTree(KeyValueManager.getTree("departments"));
		departmentIdItem.setValueField("treeId");
		empFormItems.add(departmentIdItem);

		empForm.setItems(empFormItems.toArray(new FormItem[empFormItems.size()]));
		empForm.setNumCols(6);
		searchPanel.addMember(empForm);
		IButton button = new IButton("搜索");
		searchPanel.addMember(button);
		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				employeedetailPanel.setEmployeeNo(BaseHelpUtils.getString(empForm.getValue("employeeNo")));

				employeedetailPanel.setEmployeeName(BaseHelpUtils.getString(empForm.getValue("employeeName")));
				if (BaseHelpUtils.isNullOrEmpty(empForm.getValue("departmentId"))) {
					employeedetailPanel.setDepartmentId(null);
				}else {
					employeedetailPanel.setDepartmentId(BaseHelpUtils.getIntValue(empForm.getValue("departmentId")));
				}
				employeedetailPanel.findChildIdByDepartmenId();
			//	employeedetailPanel.startEdit();
			}
		});

		// button.setWidth("25%");
		// ClientUtil.searchFormProcessAccordingToDevice(searchForm);
		employeePanel.addMember(searchPanel);

		employeedetailPanel = new EmployeePanel();
		employeedetailPanel.setWidth100();
		employeedetailPanel.setHeight100();
		employeePanel.addMember(employeedetailPanel);
		main.addMember(employeePanel);
		addMember(main);
		setPageMode(PAGE_MODE_UPDATE);
	}

	@Override
	public boolean checkData() {
		return true;
	}

	@Override
	public void startEdit() {
		if (getRecord() != null) {
			__form.editRecord(getRecord());
			employeedetailPanel.setRecord(getRecord());
			functiondetailPanel.setRecord(getRecord());
		} else {
			__form.editNewRecord();
		}
		employeedetailPanel.startEdit();
		functiondetailPanel.startEdit();

	}

	@Override
	public String getName() {
		return "角色";
	}

	@Override
	public void setValueManage(ValuesManager manager) {
		// manager.setDataSource(DSRoleWithR.getInstance());
		// manager.addMember(__form);
	}

	@Override
	public java.util.Map getValuesAsMap() {
		Map vals = new HashMap();
		vals.putAll(employeedetailPanel.getValuesAsMap());
		vals.putAll(functiondetailPanel.getValuesAsMap());
		return vals;
	}

	public EmployeePanel getEmployeedetailPanel() {
		return employeedetailPanel;
	}

	public void setEmployeedetailPanel(EmployeePanel employeedetailPanel) {
		this.employeedetailPanel = employeedetailPanel;
	}

	public FunctionPanel getFunctiondetailPanel() {
		return functiondetailPanel;
	}

	public void setFunctiondetailPanel(FunctionPanel functiondetailPanel) {
		this.functiondetailPanel = functiondetailPanel;
	}

}
