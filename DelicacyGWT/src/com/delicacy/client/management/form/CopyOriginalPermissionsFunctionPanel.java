package com.delicacy.client.management.form;

import java.util.*;
import com.delicacy.client.MapUtils;
import com.delicacy.client.ui.AbstractWizadPage;
import com.delicacy.client.data.DBDataSource;
import com.delicacy.client.management.datasource.DSCopyEmployeeAllFunctionToOther;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;
import java.util.logging.Logger;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGridField;
import com.smartgwt.client.widgets.tree.TreeNode;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.widgets.tree.TreeGrid;
public class CopyOriginalPermissionsFunctionPanel extends AbstractWizadPage {

	private static final Logger __logger = Logger.getLogger("");
	private final TreeGrid SourceGrid = new TreeGrid();
	private Integer employeeId;

	public CopyOriginalPermissionsFunctionPanel() {

		VLayout SearchSourceLayout = new VLayout();
		SearchSourceLayout.setWidth100();
		TreeGridField[] fields = new TreeGridField[6];
		int idx = 0;
		fields[idx] = new TreeGridField("functionId");
		fields[idx].setHidden(true);
		idx++;
		fields[idx] = new TreeGridField("functionCode");
		idx++;
		fields[idx] = new TreeGridField("parentId");
		fields[idx].setHidden(true);
		idx++;
		fields[idx] = new TreeGridField("functionName");
		fields[idx].setFrozen(true);
		idx++;
		fields[idx] = new TreeGridField("applicationId");
		idx++;
		fields[idx] = new TreeGridField("enabled");
		fields[idx].setHidden(true);
		idx++;
		fields[idx] = new TreeGridField("empFun");
		idx++;
		fields[idx] = new TreeGridField("roleFun");
		idx++;
		fields[idx] = new TreeGridField("departmentRoleFun");
		idx++;
		SourceGrid.setFields(fields);
		//SourceGrid.setSelectionAppearance(SelectionAppearance.CHECKBOX);
		SourceGrid.setDataSource(DSCopyEmployeeAllFunctionToOther.getInstance());
		SourceGrid.setShowOpenIcons(false);
		SourceGrid.setShowDropIcons(false);
		SourceGrid.setShowSelectedStyle(false);
		SourceGrid.setShowPartialSelection(true);
		// 当为true时表示父级选中时子级自动选中，当子级选中时父级自动选中
		SourceGrid.setCascadeSelection(true);
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
		condition.put("employeeId", employeeId);
		DBDataSource.callOperation("NQ_CopyEmployeeAllFunctionToOther", "find", condition, new DSCallback() {
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
					tree.setIdField("functionId");
					tree.setParentIdField("parentId");
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
	public Set<String> getItemNames() {
		Set<String> res = new HashSet<>();
		res.add("detailRoleFunction");
		return res;
	}

	@Override
	public Map getValuesAsMap() {
		return null;
	}

	@Override
	public String getName() {
		return "";
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}



}
