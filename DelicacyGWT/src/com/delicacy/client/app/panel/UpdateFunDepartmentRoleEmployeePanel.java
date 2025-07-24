package com.delicacy.client.app.panel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import com.delicacy.client.BaseHelpUtils;
import com.delicacy.client.data.DBDataSource;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.management.form.UpdatePermissionsDepartmentRolePanel;
import com.delicacy.client.management.form.UpdatePermissionsEmployeePanel;
import com.delicacy.client.management.form.UpdatePermissionsFunctionPanel;
import com.delicacy.client.management.form.UpdatePermissionsRolePanel;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.IPickTreeItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 *
 * @author lxf
 */
public class UpdateFunDepartmentRoleEmployeePanel extends VLayout {

	private static final Logger __logger = Logger.getLogger("");
	private UpdatePermissionsFunctionPanel functionDetailPanel;
	private UpdatePermissionsEmployeePanel employeeDetailPanel;
	private UpdatePermissionsRolePanel roleDetailPanel;
	private UpdatePermissionsDepartmentRolePanel departmentRoleDetailPanel;
	private List<FormItem> funFormItems = new ArrayList<>();
	private DynamicForm funForm = new DynamicForm();
	private List<FormItem> typeFormItems = new ArrayList<>();
	private DynamicForm typeForm = new DynamicForm();
	private List<FormItem> empFormItems = new ArrayList<>();
	private DynamicForm empForm = new DynamicForm();
	// 人员搜索面板
	private HLayout searchPanel = new HLayout(5);
	private IButton employeeButton = new IButton("搜索");

	private List<FormItem> roleFormItems = new ArrayList<>();
	private DynamicForm roleForm = new DynamicForm();

	// 角色搜索面板
	private HLayout searchRolePanel = new HLayout(5);
	private IButton roleButton = new IButton("搜索");

	private List<FormItem> deRoleFormItems = new ArrayList<>();
	private DynamicForm deRoleForm = new DynamicForm();
	// 部门角色搜索面板
	private HLayout searchDeRolePanel = new HLayout(5);
	private IButton deRoleButton = new IButton("搜索");

	private HLayout main;
	private VLayout RoleDepartmentEmployeePanel;
	// 对象头部面板
	private VLayout titlePanel = new VLayout(5);
	private Label title = new Label();
	int permissionType = 1;
	
	// 主面板
	private VLayout allPanel;
	// 底部面板（放保存和取消按钮）
	private HLayout bottomPanel;

	public UpdateFunDepartmentRoleEmployeePanel() {
		allPanel = new VLayout(10);
		allPanel.setWidth100();
		allPanel.setHeight100();
		
		// 上部主面板
		main = new HLayout(10);
		main.setWidth100();
		main.setHeight("95%");

		bottomPanel = new HLayout(10);
		bottomPanel.setWidth100();
		bottomPanel.setAlign(Alignment.RIGHT);
		bottomPanel.setHeight("5%");

		// 赋权类型面板
		RoleDepartmentEmployeePanel = new VLayout(5);
		RoleDepartmentEmployeePanel.setWidth("50%");
		RoleDepartmentEmployeePanel.setHeight100();

		// 赋权类型头部面板
		VLayout typeTitlePanel = new VLayout(5);
		typeTitlePanel.setWidth100();
		typeTitlePanel.setHeight(50);

		Label typeTitle = new Label("修改对象选择");
		typeTitle.setHeight(50);
		typeTitle.setAlign(Alignment.CENTER);
		typeTitlePanel.addMember(typeTitle);
		RoleDepartmentEmployeePanel.addMember(typeTitlePanel);

		// 类型选择面板
		HLayout typeSearchPanel = new HLayout(5);
		typeSearchPanel.setWidth100();
		typeSearchPanel.setHeight(50);
		typeForm.setWidth("50%");
		typeForm.setHeight(50);
		SelectItem permissionTypeItem = new SelectItem("permissionType", "对象类型");
		permissionTypeItem.setWidth("*");
		permissionTypeItem.setValueMap(KeyValueManager.getValueMap("system_dictionary_162"));
	    permissionTypeItem.addChangedHandler(new ChangedHandler() {
			
			@Override
			public void onChanged(ChangedEvent event) {
				permissionType = BaseHelpUtils.getIntValue(typeForm.getValue("permissionType"));
				if (permissionType == 0) {
					permissionType = 1;
				}
				functionDetailPanel.setPermissionType(permissionType);
				RoleDepartmentEmployeePanel.removeMember(searchDeRolePanel);
				RoleDepartmentEmployeePanel.removeMember(searchRolePanel);
				RoleDepartmentEmployeePanel.removeMember(searchPanel);
				RoleDepartmentEmployeePanel.removeMember(departmentRoleDetailPanel);
				RoleDepartmentEmployeePanel.removeMember(roleDetailPanel);
				RoleDepartmentEmployeePanel.removeMember(employeeDetailPanel);
				selectPermissionTypePanle(permissionType);
				functionDetailPanel.startEdit();

			}
		});
		typeFormItems.add(permissionTypeItem);
		typeForm.setItems(typeFormItems.toArray(new FormItem[typeFormItems.size()]));
		// typeForm.setNumCols(2);
		typeSearchPanel.addMember(typeForm);
		
		RoleDepartmentEmployeePanel.addMember(typeSearchPanel);

		employeeDetailPanel = new UpdatePermissionsEmployeePanel();
		employeeDetailPanel.setWidth100();
		employeeDetailPanel.setHeight100();


		roleDetailPanel = new UpdatePermissionsRolePanel();
		roleDetailPanel.setWidth100();
		roleDetailPanel.setHeight100();
		

		departmentRoleDetailPanel = new UpdatePermissionsDepartmentRolePanel();
		departmentRoleDetailPanel.setWidth100();
		departmentRoleDetailPanel.setHeight100();
		
		
		selectPermissionTypePanle(permissionType);
		main.addMember(RoleDepartmentEmployeePanel);
		
		
		// 功能信息面板
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

				functionDetailPanel.setFunctionName(BaseHelpUtils.getString(funForm.getValue("functionName")));

				functionDetailPanel.setFunctionCode(BaseHelpUtils.getString(funForm.getValue("functionCode")));

				functionDetailPanel.startEdit();
			}
		});
		//搜索功能
		//functionPanel.addMember(funSearchPanel);

		functionDetailPanel = new UpdatePermissionsFunctionPanel();
		functionDetailPanel.setWidth100();
		functionDetailPanel.setHeight100();
		functionDetailPanel.startEdit();
		functionPanel.addMember(functionDetailPanel);
		main.addMember(functionPanel);
        allPanel.addMember(main);
        IButton saveButton = new IButton("保存");
		saveButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Map condition = new HashMap();
				condition.putAll(functionDetailPanel.getValuesAsMap());
				DBDataSource.callOperation("EP_UpdateRoleDepartmentEmployeeFunction", condition, new DSCallback() {

					@Override
					public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
						if (dsResponse.getStatus() >= 0) {
							SC.say("保存成功");
						} else {
							SC.say(BaseHelpUtils.getString(dsResponse.getErrors().get("errorMsg")));
						}

					}
				});

			}
		});
		bottomPanel.addMember(saveButton);

//		IButton cancelButton = new IButton("取消");
//		cancelButton.addClickHandler(new ClickHandler() {
//
//			@Override
//			public void onClick(ClickEvent event) {
//
//			}
//		});
//		bottomPanel.addMember(cancelButton);
		allPanel.addMember(bottomPanel);
		addMember(allPanel);
		
		employeeDetailPanel.setFunctionDetailPanel(functionDetailPanel);
		roleDetailPanel.setFunctionDetailPanel(functionDetailPanel);
		departmentRoleDetailPanel.setFunctionDetailPanel(functionDetailPanel);

	}

	public void selectPermissionTypePanle(int permissionType) {

		if (permissionType == 1) {

			titleLable("人员");
			// 人员搜索面板
			searchPanel.setWidth100();
			searchPanel.setHeight(50);
			empForm.setWidth("75%");
			empForm.setHeight(50);
			TextItem employeeNoItem = new TextItem("employeeNo", "员工编号");
			employeeNoItem.setWidth("*");
			empFormItems.clear();
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
			empForm.clear();
			empForm.setItems(empFormItems.toArray(new FormItem[empFormItems.size()]));
			empForm.setNumCols(6);
			searchPanel.removeMember(empForm);
			searchPanel.addMember(empForm);
			searchPanel.removeMember(employeeButton);
			searchPanel.addMember(employeeButton);
			employeeButton.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {

					employeeDetailPanel.setEmployeeNo(BaseHelpUtils.getString(empForm.getValue("employeeNo")));

					employeeDetailPanel.setEmployeeName(BaseHelpUtils.getString(empForm.getValue("employeeName")));

					employeeDetailPanel.setDepartmentId(BaseHelpUtils.getIntValue(empForm.getValue("departmentId")));

					employeeDetailPanel.startEdit();
				}
			});

			RoleDepartmentEmployeePanel.addMember(searchPanel);
			employeeDetailPanel.startEdit();
			RoleDepartmentEmployeePanel.addMember(employeeDetailPanel);

		}
		if (permissionType == 2) {
			titleLable("角色");
			// 角色搜索面板
			searchRolePanel.setWidth100();
			searchRolePanel.setHeight(50);
			roleForm.setWidth("75%");
			roleForm.setHeight(50);
			TextItem roleNameItem = new TextItem("roleName", "角色名称");
			roleNameItem.setWidth("*");
			roleFormItems.clear();
			roleFormItems.add(roleNameItem);
			ComboBoxItem roleTypeItem = new ComboBoxItem("roleType", "角色类型");
			roleTypeItem.setWidth("*");
			roleTypeItem.setValueMap(KeyValueManager.getValueMap("system_dictionary_10"));
			roleFormItems.add(roleTypeItem);
			ComboBoxItem applicationIdItem = new ComboBoxItem("applicationId", "应用系统");
			applicationIdItem.setWidth("*");
			applicationIdItem.setValueMap(KeyValueManager.getValueMap("domain_values_application_type"));
			roleFormItems.add(applicationIdItem);
			roleForm.clear();
			roleForm.setItems(roleFormItems.toArray(new FormItem[roleFormItems.size()]));
			roleForm.setNumCols(6);
			searchRolePanel.removeMember(roleForm);
			searchRolePanel.addMember(roleForm);
			searchRolePanel.removeMember(roleButton);
			searchRolePanel.addMember(roleButton);
			roleButton.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {

					roleDetailPanel.setRoleName(BaseHelpUtils.getString(roleForm.getValue("roleName")));

					if (!BaseHelpUtils.isNullOrEmpty(roleForm.getValue("roleType"))) {
						roleDetailPanel.setRoleType(BaseHelpUtils.getIntValue(roleForm.getValue("roleType")));
					} else {
						roleDetailPanel.setRoleType(null);
					}
					if (!BaseHelpUtils.isNullOrEmpty(roleForm.getValue("applicationId"))) {
						roleDetailPanel.setApplicationId(BaseHelpUtils.getIntValue(roleForm.getValue("applicationId")));
					} else {
						roleDetailPanel.setApplicationId(null);
					}
					roleDetailPanel.startEdit();
				}
			});

			RoleDepartmentEmployeePanel.addMember(searchRolePanel);
			roleDetailPanel.startEdit();
			RoleDepartmentEmployeePanel.addMember(roleDetailPanel);

		}
		if (permissionType == 3) {
			titleLable("部门角色");
			// 人员搜索面板
			searchDeRolePanel.setWidth100();
			searchDeRolePanel.setHeight(50);
			deRoleForm.setWidth("75%");
			deRoleForm.setHeight(50);
			TextItem roleNameItem = new TextItem("roleName", "角色名称");
			roleNameItem.setWidth("*");
			deRoleFormItems.clear();
			deRoleFormItems.add(roleNameItem);
			IPickTreeItem departmentIdItem = new IPickTreeItem("departmentId", "部门");
			departmentIdItem.setWidth("*");
			departmentIdItem.setCanSelectParentItems(true);
			departmentIdItem.setValueTree(KeyValueManager.getTree("departments"));
			departmentIdItem.setValueField("treeId");
			deRoleFormItems.add(departmentIdItem);
			deRoleForm.clear();
			deRoleForm.setItems(deRoleFormItems.toArray(new FormItem[deRoleFormItems.size()]));
			deRoleForm.setNumCols(6);
			searchDeRolePanel.removeMember(deRoleForm);
			searchDeRolePanel.addMember(deRoleForm);
			searchDeRolePanel.removeMember(deRoleButton);
			searchDeRolePanel.addMember(deRoleButton);
			deRoleButton.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {

					departmentRoleDetailPanel.setRoleName(BaseHelpUtils.getString(deRoleForm.getValue("roleName")));

					departmentRoleDetailPanel
							.setDepartmentId(BaseHelpUtils.getIntValue(deRoleForm.getValue("departmentId")));

					departmentRoleDetailPanel.startEdit();
				}
			});

			RoleDepartmentEmployeePanel.addMember(searchDeRolePanel);

			departmentRoleDetailPanel.startEdit();
			RoleDepartmentEmployeePanel.addMember(departmentRoleDetailPanel);
		}
	}

	public void titleLable(String typeValue) {
		// 对象头部面板
		titlePanel.setWidth100();
		titlePanel.setHeight(50);
		title.setContents(typeValue + "信息");
		title.setHeight(50);
		title.setAlign(Alignment.CENTER);
		titlePanel.removeMember(title);
		titlePanel.addMember(title);
		RoleDepartmentEmployeePanel.removeMember(titlePanel);
		RoleDepartmentEmployeePanel.addMember(titlePanel);

	}

//	public static class Factory implements PanelFactory {
//
//		private String id;
//
//		@Override
//		public Canvas create() {
//			FunDepartmentRoleEmployeePanel cm = new FunDepartmentRoleEmployeePanel();
//			id = cm.getID();
//			return cm;
//		}
//
//		@Override
//		public String getID() {
//			return id;
//		}
//
//		@Override
//		public String getDescription() {
//			return "Department";
//		}
//
//	}
}
