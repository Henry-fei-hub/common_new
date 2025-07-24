package com.delicacy.client.management.form;

import java.util.*;

import com.delicacy.client.BaseHelpUtils;
import com.delicacy.client.MapUtils;
import com.delicacy.client.app.panel.EmpRoleAndDepartmentRolePanel;
import com.delicacy.client.ui.AbstractWizadPage;
import com.delicacy.client.ui.DelicacyListGrid;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.data.DBDataSource;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.management.datasource.DSDepartmentRole;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.DoubleClickEvent;
import com.smartgwt.client.widgets.events.DoubleClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.IPickTreeItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

import java.util.logging.Logger;

public class EmpAddDepartmentRolePanel extends AbstractWizadPage {

	private static final Logger __logger = Logger.getLogger("");
	private DelicacyListGrid grid = new DelicacyListGrid();
	private List<FormItem> deRoleFormItems = new ArrayList<>();
	private DynamicForm deRoleForm = new DynamicForm();
    private MyDepartmentRolePanel myDepartmentRolePanel ;
	public EmpAddDepartmentRolePanel(final int employeeId) {
		myDepartmentRolePanel =new MyDepartmentRolePanel(employeeId);
		VLayout mainPanel = new VLayout();
		mainPanel.setWidth100();
		mainPanel.setHeight100();

		HLayout searchPanel = new HLayout();
		searchPanel.setWidth100();
		searchPanel.setHeight(50);
		searchPanel.setLayoutRightMargin(20);
		deRoleForm.setWidth("75%");
		deRoleForm.setHeight(50);
		TextItem roleNameItem = new TextItem("roleName", "角色名称");
		roleNameItem.setWidth("*");
		deRoleFormItems.add(roleNameItem);
		IPickTreeItem departmentIdItem = new IPickTreeItem("departmentId", "部门");
		departmentIdItem.setWidth("*");
		departmentIdItem.setCanSelectParentItems(true);
		departmentIdItem.setValueTree(KeyValueManager.getTree("all_departments"));
		departmentIdItem.setValueField("treeId");
		deRoleFormItems.add(departmentIdItem);
		deRoleForm.setItems(deRoleFormItems.toArray(new FormItem[deRoleFormItems.size()]));
		deRoleForm.setNumCols(6);
		searchPanel.addMember(deRoleForm);
		IButton deRoleButton = new IButton("搜索");
		deRoleButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Integer departmentId;
				String roleName = BaseHelpUtils.getString(deRoleForm.getValue("roleName"));
				if (BaseHelpUtils.isNullOrEmpty(deRoleForm.getValue("departmentId"))) {
					departmentId = null;
				} else {
					departmentId = BaseHelpUtils.getIntValue(deRoleForm.getValue("departmentId"));
				}
				searchEdit(roleName, departmentId);
			}
		});
		searchPanel.addMember(deRoleButton);
		mainPanel.addMember(searchPanel);

		VLayout SearchSourceLayout = new VLayout();
		SearchSourceLayout.setWidth100();
		ListGridField[] fields = new ListGridField[3];
		int idx = 0;
		fields[idx] = new ListGridField("departmentRoleId");
		fields[idx].setHidden(true);
		idx++;
		fields[idx] = new ListGridField("departmentId");
		fields[idx].setHidden(false);
		idx++;
		fields[idx] = new ListGridField("roleId");
		fields[idx].setHidden(false);
		idx++;

		grid.setFields(fields);
		grid.setDataSource(DSDepartmentRole.getInstance());
		grid.setAutoFitFieldWidths(false);
		grid.addDoubleClickHandler(new DoubleClickHandler() {
			
			@Override
			public void onDoubleClick(DoubleClickEvent event) {
				ListGridRecord[] selected = grid.getSelectedRecords();
				int roleId = ClientUtil.checkAndGetIntValue(selected[0].getAttribute("roleId"));
				int departmentId = ClientUtil.checkAndGetIntValue(selected[0].getAttribute("departmentId"));
				Map condition = new HashMap();
		    	condition.put("optType", "modifyPersonalDepartmentRole");
		    	condition.put("employeeId", employeeId);
		    	condition.put("roleId", roleId);
		    	condition.put("departmentId", departmentId);
		    	condition.put("flag", 1);
		    	condition.put("operateEmployeeId", ClientUtil.getUserId());
				DBDataSource.callOperation("EP_EditorRoleAndDepartmentRoleToEmp", condition, new DSCallback() {
		            @Override
		            public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
		                if (dsResponse.getStatus() >= 0) {
		                	EmpRoleAndDepartmentRolePanel.myDepartmentRoleDataPanel.startEdit();
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
		condition.put("optType", "departmentRoleData");
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
	 * 获取按条件查询的数据
	 */
	public void searchEdit(String roleName, Integer departmentId) {
		Map condition = new HashMap();
		condition.put("roleName", roleName);
		condition.put("departmentId", departmentId);
		condition.put("optType", "departmentRoleData");
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
		res.add("detailDepartmentRole");
		return res;
	}

	@Override
	public Map getValuesAsMap() {
		Map param = new HashMap();
		ListGridRecord[] selected = grid.getSelectedRecords();
		List resList = new ArrayList();
		for (ListGridRecord r : selected) {
			Map lm = new HashMap();
			lm.put("departmentRoleId", r.getAttribute("departmentRoleId"));
			resList.add(lm);
		}
		param.put("detailDepartmentRole", resList);
		return param;
	}

	@Override
	public String getName() {
		return "";
	}

}
