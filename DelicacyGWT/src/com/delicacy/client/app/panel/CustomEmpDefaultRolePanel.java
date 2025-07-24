package com.delicacy.client.app.panel;

import java.util.*;
import com.delicacy.client.MapUtils;
import com.delicacy.client.ui.AbstractWizadPage;
import com.delicacy.client.ui.DelicacyListGrid;
import com.delicacy.client.data.DBDataSource;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.widgets.events.DoubleClickEvent;
import com.smartgwt.client.widgets.events.DoubleClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RemoveRecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RemoveRecordClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.types.SelectionAppearance;
import java.util.logging.Logger;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGridField;
import com.smartgwt.client.widgets.tree.TreeNode;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.delicacy.client.app.datasource.DSDefaultRoleWithR;
import com.delicacy.client.app.datasource.DSRoleWithR;
import com.delicacy.client.data.ClientUtil;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.tree.events.NodeClickEvent;
import com.smartgwt.client.widgets.tree.events.NodeClickHandler;

public class CustomEmpDefaultRolePanel extends AbstractWizadPage {

	private static final Logger __logger = Logger.getLogger("");
	public static TreeGrid SourceGrid;

	public CustomEmpDefaultRolePanel() {
		SourceGrid = new TreeGrid();
		VLayout SearchSourceLayout = new VLayout();
		SearchSourceLayout.setWidth100();
		TreeGridField[] fields = new TreeGridField[6];
		int idx = 0;
		fields[idx] = new TreeGridField("departmentId");
		idx++;
		fields[idx] = new TreeGridField("roleId");
		idx++;
		SourceGrid.setFields(fields);
		SourceGrid.setSelectionAppearance(SelectionAppearance.ROW_STYLE);
		SourceGrid.setDataSource(DSDefaultRoleWithR.getInstance());
		SourceGrid.setShowOpenIcons(false);
		SourceGrid.setShowDropIcons(false);
		SourceGrid.setShowSelectedStyle(false);
		SourceGrid.setShowPartialSelection(true);
		// 当为true时表示父级选中时子级自动选中，当子级选中时父级自动选中
		SourceGrid.setCascadeSelection(false);
        

		SearchSourceLayout.setHeight100();
		SearchSourceLayout.setLayoutTopMargin(10);
		SearchSourceLayout.setLayoutRightMargin(5);
		SearchSourceLayout.setMembersMargin(10);
		SearchSourceLayout.addMember(SourceGrid);
		addMember(SearchSourceLayout);
	}

	@Override
	public void startEdit() {
		Map condition = new HashMap();
		condition.put("departmentId", departmentId);
		condition.put("employeeId", employeeId);
		condition.put("opt_type", "getDefaultRole");
		DBDataSource.callOperation("EP_UpdateEmpDefaultRole", "find", condition, new DSCallback() {
			@Override
			public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
				if (dsResponse.getStatus() >= 0) {
					int len = dsResponse.getData().length;
					TreeNode[] nodes = new TreeNode[len];
					for (int i = 0; i < len; i++) {
						nodes[i] = new TreeNode();
						DBDataSource.copyRecord(dsResponse.getData()[i], nodes[i]);
					}
					Tree tree = new Tree();
					tree.setModelType(TreeModelType.PARENT);
					tree.setRootValue("0");
					tree.setIdField("roleId");
					tree.setData(nodes);
					tree.openAll();
					SourceGrid.setData(tree);
				}
			}
		});
	}


	@Override
	public boolean checkData() {
		for (ListGridRecord r : SourceGrid.getSelectedRecords()) {
			__logger.info(MapUtils.convertRecordToMap(SourceGrid.getDataSource(), r).toString());
		}
		return true;
	}

	@Override
	public Map getValuesAsMap() {
		Map param = new HashMap();
		ListGridRecord[] selected = SourceGrid.getSelectedRecords();
		List resList = new ArrayList();
		for (ListGridRecord r : selected) {
			Map lm = new HashMap();
			lm.put("roleId", r.getAttribute("roleId"));
			lm.put("departmentId", r.getAttribute("departmentId"));
			resList.add(lm);
		}
		param.put("detailEmployeeRole", resList);
		return param;
	}

	@Override
	public String getName() {
		return "";
	}

	private int departmentId;
	private int employeeId;

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the departmentId
	 */
	public int getDepartmentId() {
		return departmentId;
	}

	/**
	 * @param departmentId
	 *            the departmentId to set
	 */
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

}
