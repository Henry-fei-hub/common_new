package com.delicacy.client.management.form;

import java.util.*;

import com.delicacy.client.BaseHelpUtils;
import com.delicacy.client.MapUtils;
import com.delicacy.client.ui.AbstractWizadPage;
import com.delicacy.client.ui.DelicacyListGrid;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.data.DBDataSource;
import com.delicacy.client.data.KeyValueManager;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.DoubleClickEvent;
import com.smartgwt.client.widgets.events.DoubleClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;


import java.util.logging.Logger;
import com.delicacy.client.app.datasource.DSRoleWithR;
import com.delicacy.client.app.panel.EmpRoleAndDepartmentRolePanel;


public class EmpAddRolePanel extends AbstractWizadPage {

	private static final Logger __logger = Logger.getLogger("");
	private DynamicForm roleForm = new DynamicForm();
	private List<FormItem> roleFormItems = new ArrayList<>();
	private DelicacyListGrid grid = new DelicacyListGrid();
    private MyRolePanel myRolePanel;
	public EmpAddRolePanel(final int employeeId) {
		myRolePanel = new MyRolePanel(employeeId);
		VLayout mainPanel = new VLayout();
		mainPanel.setWidth100();
		mainPanel.setHeight100();

		HLayout searchPanel = new HLayout();
		searchPanel.setWidth100();
		searchPanel.setHeight(50);
		searchPanel.setLayoutRightMargin(20);
		roleForm.setWidth("75%");
		roleForm.setHeight(50);
		TextItem roleNameItem = new TextItem("roleName", "角色名称");
		roleNameItem.setWidth("*");
		roleFormItems.add(roleNameItem);
		ComboBoxItem roleTypeItem = new ComboBoxItem("roleType", "角色类型");
		roleTypeItem.setWidth("*");
		roleTypeItem.setValueMap(KeyValueManager.getValueMap("system_dictionary_10"));
		roleFormItems.add(roleTypeItem);

		ComboBoxItem applicationIdItem = new ComboBoxItem("applicationId", "应用系统");
		applicationIdItem.setWidth("*");
		applicationIdItem.setValueMap(KeyValueManager.getValueMap("domain_values_application_type"));
		roleFormItems.add(applicationIdItem);
		roleForm.setItems(roleFormItems.toArray(new FormItem[roleFormItems.size()]));
		roleForm.setNumCols(6);

		searchPanel.addMember(roleForm);
		IButton roleButton = new IButton("搜索");
		roleButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				String roleName = BaseHelpUtils.getString(roleForm.getValue("roleName"));
				Integer roleType;
				Integer applicationId;
				if (!BaseHelpUtils.isNullOrEmpty(roleForm.getValue("roleType"))) {
					roleType = BaseHelpUtils.getIntValue(roleForm.getValue("roleType"));
				} else {
					roleType =null;
				}
				if (!BaseHelpUtils.isNullOrEmpty(roleForm.getValue("applicationId"))) {
					applicationId = BaseHelpUtils.getIntValue(roleForm.getValue("applicationId"));
				} else {
					applicationId = null;
				}
				searchEdit(roleName, roleType, applicationId);
			}
		});
		searchPanel.addMember(roleButton);
		mainPanel.addMember(searchPanel);

		VLayout SearchSourceLayout = new VLayout();
		SearchSourceLayout.setWidth100();
		ListGridField[] fields = new ListGridField[4];
		int idx = 0;
		fields[idx] = new ListGridField("roleId");
		fields[idx].setHidden(true);
		idx++;
		fields[idx] = new ListGridField("roleName");
		fields[idx].setHidden(false);
		idx++;
		fields[idx] = new ListGridField("roleType");
		fields[idx].setHidden(false);
		idx++;
		fields[idx] = new ListGridField("applicationId");
		fields[idx].setHidden(false);
		idx++;

		grid.setFields(fields);
		grid.setDataSource(DSRoleWithR.getInstance());
		grid.setAutoFitFieldWidths(false);
		grid.addDoubleClickHandler(new DoubleClickHandler() {
			
			@Override
			public void onDoubleClick(DoubleClickEvent event) {
				ListGridRecord[] selected = grid.getSelectedRecords();
				int roleId = ClientUtil.checkAndGetIntValue(selected[0].getAttribute("roleId"));
				Map condition = new HashMap();
		    	condition.put("optType", "modifyPersonalRole");
		    	condition.put("employeeId", employeeId);
		    	condition.put("roleId", roleId);
		    	condition.put("flag", 1);
		    	condition.put("operateEmployeeId", ClientUtil.getUserId());
				DBDataSource.callOperation("EP_EditorRoleAndDepartmentRoleToEmp", condition, new DSCallback() {
		            @Override
		            public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
		                if (dsResponse.getStatus() >= 0) {
		                	EmpRoleAndDepartmentRolePanel.myRoleDataPanel.startEdit();
		                }
		            }
		        });
				
			}
		});
		SearchSourceLayout.setHeight100();
		SearchSourceLayout.setLayoutTopMargin(10);
		SearchSourceLayout.setLayoutRightMargin(5);
		SearchSourceLayout.setMembersMargin(10);
		SearchSourceLayout.addMember(grid);
		mainPanel.addMember(SearchSourceLayout);
		addMember(mainPanel);
	}

	@Override
	public void startEdit() {
		Map condition = new HashMap();
		condition.put("optType", "roleData");
		DBDataSource.callOperation("EP_SerachRoleDepartmentEmployee", "find", condition, new DSCallback() {
			@Override
			public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
				if (dsResponse.getStatus() >= 0) {
					grid.setData(dsResponse.getData());

				}
			}
		});
	}

	/**
	 * 根据条件查询出乡关数据
	 * 
	 */
	public void searchEdit(String roleName, Integer roleType, Integer applicationId) {
		Map condition = new HashMap();
		condition.put("roleName", roleName);
		condition.put("roleType", roleType);
		condition.put("applicationId", applicationId);
		condition.put("optType", "roleData");
		DBDataSource.callOperation("EP_SerachRoleDepartmentEmployee", "find", condition, new DSCallback() {
			@Override
			public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
				if (dsResponse.getStatus() >= 0) {
					grid.setData(dsResponse.getData());
				}
			}
		});
	}

	@Override
	public boolean checkData() {
		for (ListGridRecord r : grid.getSelectedRecords()) {
			__logger.info(MapUtils.convertRecordToMap(grid.getDataSource(), r).toString());
		}
		return true;
	}

	@Override
	public Set<String> getItemNames() {
		Set<String> res = new HashSet<>();
		res.add("detailRole");
		return res;
	}

	@Override
	public Map getValuesAsMap() {
		Map param = new HashMap();
		ListGridRecord[] selected = grid.getSelectedRecords();
		List resList = new ArrayList();
		for (ListGridRecord r : selected) {
			Map lm = new HashMap();
			lm.put("roleId", r.getAttribute("roleId"));
			resList.add(lm);
		}
		param.put("detailRole", resList);
		return param;
	}

	@Override
	public String getName() {
		return "";
	}

}
