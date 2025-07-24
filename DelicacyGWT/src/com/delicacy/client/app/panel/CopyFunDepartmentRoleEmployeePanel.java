package com.delicacy.client.app.panel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.delicacy.client.BaseHelpUtils;
import com.delicacy.client.app.datasource.DSMemployee;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.data.DBDataSource;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.management.datasource.DSSelectGridEmployeeValue;
import com.delicacy.client.management.form.CopyAfterPermissionsFunctionPanel;
import com.delicacy.client.management.form.CopyOriginalPermissionsFunctionPanel;
import com.delicacy.client.management.form.CopyPermissionsDepartmentRolePanel;
import com.delicacy.client.management.form.CopyPermissionsRolePanel;
import com.delicacy.client.management.form.UpdatePermissionsFunctionPanel;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.TextMatchStyle;
import com.smartgwt.client.util.BooleanCallback;
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
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 复制功能权限
 * 
 * @author lsf
 *
 */
public class CopyFunDepartmentRoleEmployeePanel extends VLayout {

	private static final Logger __logger = Logger.getLogger("");
	private CopyOriginalPermissionsFunctionPanel copyOriginalPermissionsFunctionPanel;
	private CopyAfterPermissionsFunctionPanel copyAfterPermissionsFunctionPanel;
	private CopyPermissionsRolePanel copyPermissionsRolePanel;
	private CopyPermissionsDepartmentRolePanel copyPermissionsDepartmentRolePanel;
	// 人员权限复制form
	private List<FormItem> empFormItems;
	private DynamicForm empForm;
	// 主面板
	private HLayout mainPanel;
	// 左面板
	private VLayout leftPanel;
	// 选择人员面板
	private HLayout employeePanel;
	
	//角色和部门角色面板
	private HLayout roleAndDepartmentRolePanel;
	//角色面板
	private  VLayout rolePanel;
	//角色标题
	private Label roleTitle;
	//部门角色面板
	private  VLayout departmentRolePanel;
	//角色标题
	private Label departmentRoleTitle;
	
	
	// 原功能信息标题面板
	private HLayout originalFunctionTitlePanel;
	// 原功能信息标题Lable
	private Label originalFunctionTitle;

	// 右面板
	private VLayout rigthPanel;
	// 功能信息标题面板
	private HLayout functionTitilePanel;
	// 功能信息标题Lable
	private Label functionTitle;

	public CopyFunDepartmentRoleEmployeePanel() {
		// 主面板
		mainPanel = new HLayout(10);
		mainPanel.setWidth100();
		mainPanel.setHeight100();

		// 左边面板
		leftPanel = new VLayout(5);
		leftPanel.setWidth("50%");
		leftPanel.setHeight100();

		Label employeeTitle = new Label("人员权限复制操作");
		employeeTitle.setHeight(50);
		employeeTitle.setWidth100();
		employeeTitle.setAlign(Alignment.CENTER);
		leftPanel.addMember(employeeTitle);

		// 人员权限复制
		employeePanel = new HLayout(10);
		employeePanel.setHeight(50);

		empForm = new DynamicForm();
		empForm.setWidth("75%");
		empForm.setHeight(50);
		ListGridField eNoField = new ListGridField("employeeNo");
		ListGridField eNameField = new ListGridField("employeeName");
		ListGridField departmentIdField = new ListGridField("departmentId");
		eNameField.setCanFilter(true);
		Criteria c = new Criteria();
		c.addCriteria("status", 0);
		final ComboBoxItem originalEmployeeItem = new ComboBoxItem("originalEmployee", "被复制对象");
		originalEmployeeItem.setWidth("*");
		// employeeNoItem.setMultiple(true);
		originalEmployeeItem.setChangeOnKeypress(false);
		originalEmployeeItem.setTextMatchStyle(TextMatchStyle.SUBSTRING);
		originalEmployeeItem.setOptionDataSource(DSSelectGridEmployeeValue.getInstance());
		originalEmployeeItem.setOptionCriteria(c);
		originalEmployeeItem.setValueField("employeeId");
		originalEmployeeItem.setDisplayField("employeeName");
		originalEmployeeItem.setUseClientFiltering(true);
		originalEmployeeItem.setPickListFields(eNoField, eNameField, departmentIdField);
		originalEmployeeItem.addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				if (BaseHelpUtils.isNullOrEmpty(originalEmployeeItem.getValue())) {
					return;
				}
				copyOriginalPermissionsFunctionPanel.setEmployeeId(BaseHelpUtils.getIntValue(originalEmployeeItem.getValue()));
				copyOriginalPermissionsFunctionPanel.startEdit();
				copyPermissionsRolePanel.setEmployeeId(BaseHelpUtils.getIntValue(originalEmployeeItem.getValue()));
				copyPermissionsRolePanel.startEdit();
				copyPermissionsDepartmentRolePanel.setEmployeeId(BaseHelpUtils.getIntValue(originalEmployeeItem.getValue()));
				copyPermissionsDepartmentRolePanel.startEdit();
				
				originalFunctionTitle.setContents(originalEmployeeItem.getDisplayValue() + "的功能信息");
				roleTitle.setContents(originalEmployeeItem.getDisplayValue() +"的角色(没有功能信息)");
				departmentRoleTitle.setContents(originalEmployeeItem.getDisplayValue() +"的部门角色(没有功能信息)");
			}
		});
		empFormItems = new ArrayList<>();
		empFormItems.add(originalEmployeeItem);
		final ComboBoxItem employeeItem = new ComboBoxItem("employee", "复制对象");
		employeeItem.setWidth("*");
		employeeItem.setChangeOnKeypress(false);
		employeeItem.setTextMatchStyle(TextMatchStyle.SUBSTRING);
		employeeItem.setOptionDataSource(DSSelectGridEmployeeValue.getInstance());
		employeeItem.setOptionCriteria(c);
		employeeItem.setValueField("employeeId");
		employeeItem.setDisplayField("employeeName");
		employeeItem.setUseClientFiltering(true);
		employeeItem.setPickListFields(eNoField, eNameField, departmentIdField);
		empFormItems.add(employeeItem);
		empForm.setItems(empFormItems.toArray(new FormItem[empFormItems.size()]));
		empForm.setNumCols(4);
		employeePanel.addMember(empForm);
		IButton employeeButton = new IButton("复制");
		employeeButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (BaseHelpUtils.isNullOrEmpty(employeeItem.getValue())
						|| BaseHelpUtils.isNullOrEmpty(originalEmployeeItem.getValue())) {
					SC.say("请选择相关人员进行操作");
					return;
				}
				if (employeeItem.getValue().equals(originalEmployeeItem.getValue())) {
					SC.say("不能选择同一个员工");
					return;
				}
				SC.confirm("提示","您确定需要复制相关权限?", new BooleanCallback() {
					@Override
					public void execute(Boolean value) {
						if (value) {
							Map<String, Object> condition = new HashMap<>();
							condition.put("employeeId", BaseHelpUtils.getIntValue(originalEmployeeItem.getValue()));
							condition.put("otherEmployeeId", BaseHelpUtils.getIntValue(employeeItem.getValue()));
							condition.put("operateEmployeeId", ClientUtil.getUserId());
							condition.put("optType", "addFunctionToEmployee");
							condition.putAll(copyPermissionsRolePanel.getValuesAsMap());
							condition.putAll(copyPermissionsDepartmentRolePanel.getValuesAsMap());
							DBDataSource.callOperation("EP_CopyEmployeeFunction", condition, new DSCallback() {
								@Override
								public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
									if (dsResponse.getStatus() >= 0) {
										functionTitle.setContents(employeeItem.getDisplayValue() + "的功能信息");
										rigthPanel.removeMember(copyAfterPermissionsFunctionPanel);
										copyAfterPermissionsFunctionPanel = new CopyAfterPermissionsFunctionPanel();
										copyAfterPermissionsFunctionPanel.setWidth100();
										copyAfterPermissionsFunctionPanel.setHeight100();
										rigthPanel.addMember(copyAfterPermissionsFunctionPanel);
										copyAfterPermissionsFunctionPanel.setAfterEmployeeId(BaseHelpUtils.getIntValue(employeeItem.getValue()));
										copyAfterPermissionsFunctionPanel.startEdit();
									}
									
								}
							});
							
						}
						
					}
				});

//				__logger.info("原员工id" + originalEmployeeItem.getValue());
//				__logger.info("复制给员工id" + employeeItem.getValue());
			}
		});
		employeePanel.addMember(employeeButton);
		leftPanel.addMember(employeePanel);

		roleAndDepartmentRolePanel = new HLayout();
		roleAndDepartmentRolePanel.setHeight("40%");
		roleAndDepartmentRolePanel.setWidth100();
		
		rolePanel = new VLayout();
		rolePanel.setHeight100();
		rolePanel.setWidth("50%");
		roleTitle = new Label("角色(没有功能信息)");
		roleTitle.setWidth100();
		roleTitle.setHeight(30);
		roleTitle.setAlign(Alignment.CENTER);
		rolePanel.addMember(roleTitle);
		
		copyPermissionsRolePanel = new CopyPermissionsRolePanel();
		copyPermissionsRolePanel.setWidth100();
		copyPermissionsRolePanel.setHeight100();
		rolePanel.addMember(copyPermissionsRolePanel);
		
		roleAndDepartmentRolePanel.addMember(rolePanel);
		
		
		
		departmentRolePanel = new VLayout();
		departmentRolePanel.setHeight100();
		departmentRolePanel.setWidth("50%");
		departmentRoleTitle = new Label("部门角色(没有功能信息)");
		departmentRoleTitle.setWidth100();
		departmentRoleTitle.setHeight(30);
		departmentRoleTitle.setAlign(Alignment.CENTER);
		departmentRolePanel.addMember(departmentRoleTitle);
		
		copyPermissionsDepartmentRolePanel = new CopyPermissionsDepartmentRolePanel();
		copyPermissionsDepartmentRolePanel.setWidth100();
		copyPermissionsDepartmentRolePanel.setHeight100();
		departmentRolePanel.addMember(copyPermissionsDepartmentRolePanel);
		
		roleAndDepartmentRolePanel.addMember(departmentRolePanel);
		leftPanel.addMember(roleAndDepartmentRolePanel);
		
		
		// 原功能信息
		originalFunctionTitlePanel = new HLayout(10);
		originalFunctionTitlePanel.setHeight(30);
		originalFunctionTitlePanel.setAlign(Alignment.CENTER);
		originalFunctionTitle = new Label("功能信息");
		originalFunctionTitle.setHeight(30);
		originalFunctionTitle.setWidth100();
		originalFunctionTitle.setAlign(Alignment.CENTER);
		originalFunctionTitlePanel.addMember(originalFunctionTitle);
		leftPanel.addMember(originalFunctionTitlePanel);

		copyOriginalPermissionsFunctionPanel = new CopyOriginalPermissionsFunctionPanel();
		copyOriginalPermissionsFunctionPanel.setWidth100();
		copyOriginalPermissionsFunctionPanel.setHeight100();
		leftPanel.addMember(copyOriginalPermissionsFunctionPanel);

		mainPanel.addMember(leftPanel);

		rigthPanel = new VLayout(5);
		rigthPanel.setWidth("50%");
		rigthPanel.setHeight100();

		functionTitilePanel = new HLayout(10);
		functionTitilePanel.setHeight(50);
		functionTitilePanel.setAlign(Alignment.CENTER);
		functionTitle = new Label("功能信息");
		functionTitle.setHeight(50);
		functionTitle.setWidth100();
		functionTitle.setAlign(Alignment.CENTER);
		functionTitilePanel.addMember(functionTitle);
		rigthPanel.addMember(functionTitilePanel);

		copyAfterPermissionsFunctionPanel = new CopyAfterPermissionsFunctionPanel();
		copyAfterPermissionsFunctionPanel.setWidth100();
		copyAfterPermissionsFunctionPanel.setHeight100();
		rigthPanel.addMember(copyAfterPermissionsFunctionPanel);

		mainPanel.addMember(rigthPanel);
		addMember(mainPanel);

	}
}
