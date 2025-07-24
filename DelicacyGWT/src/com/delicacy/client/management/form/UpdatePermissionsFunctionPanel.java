package com.delicacy.client.management.form;

import com.delicacy.client.BaseHelpUtils;
import com.delicacy.client.MapUtils;
import com.delicacy.client.app.datasource.DSFunction;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.data.DBDataSource;
import com.delicacy.client.ui.AbstractWizadPage;
import com.delicacy.client.ui.DelicacyListGrid;
import com.delicacy.client.ui.LoadingWindow;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeGridField;
import com.smartgwt.client.widgets.tree.TreeNode;

import java.util.*;
import java.util.logging.Logger;

public class UpdatePermissionsFunctionPanel extends AbstractWizadPage {

	private static final Logger __logger = Logger.getLogger("");
	private DelicacyListGrid grid = new DelicacyListGrid();
	private final TreeGrid SourceGrid = new TreeGrid();
	private int permissionType = 1;
	private Map selectedMap = null;
	private String functionCode;
	private String functionName;

	public UpdatePermissionsFunctionPanel() {

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
		SourceGrid.setFields(fields);
		SourceGrid.setSelectionAppearance(SelectionAppearance.CHECKBOX);
		SourceGrid.setDataSource(DSFunction.getInstance());
		SourceGrid.setShowOpenIcons(false);
		SourceGrid.setShowDropIcons(false);
		SourceGrid.setShowSelectedStyle(true);
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
		condition.put("functionCode", functionCode);
		condition.put("functionName", functionName);
		long start = System.currentTimeMillis();
		DBDataSource.callOperation("EP_SerachRoleFunction", "find", condition, new DSCallback() {
			@Override
			public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
				long end = System.currentTimeMillis();
				__logger.info("功能信息-EP_SerachRoleFunction接口消耗时间：" + (end - start) + " ms（" + start + " - " + end + "）");

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
					if (grid.getSelectedRecord() != null) {
						reloadDetailTableData();
					}
				}
			}
		});
	}

	public void reloadDetailTableData() {
		final LoadingWindow loading = new LoadingWindow();
		Map condition = new HashMap();
		if (permissionType == 1) {
			condition.put("employeeId", grid.getSelectedRecord().getAttributeAsInt("employeeId"));
		}
		if (permissionType == 2) {
			condition.put("roleId", grid.getSelectedRecord().getAttributeAsInt("roleId"));
		}
		if (permissionType == 3) {
		//	condition.put("departmentRoleId", grid.getSelectedRecord().getAttributeAsInt("departmentRoleId"));
			condition.put("departmentRole", grid.getSelectedRecord().getAttributeAsInt("roleId"));
			condition.put("departmentId", grid.getSelectedRecord().getAttributeAsInt("departmentId"));
		}
		condition.put("optType", "functionData");
		long start = System.currentTimeMillis();
		DBDataSource.callOperation("EP_UpdateRoleDepartmentEmployeeFunction", condition, new DSCallback() {
			@Override
			public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
				long end = System.currentTimeMillis();
				__logger.info("功能信息-EP_UpdateRoleDepartmentEmployeeFunction接口消耗时间：" + (end - start) + " ms（" + start + " - " + end + "）");

				if (dsResponse.getStatus() >= 0) {
					int len = dsResponse.getData().length;
					if (len == 0) {
						loading.destroy();
						return;
					}
					Map searchMap = new HashMap();
					Tree originalList = SourceGrid.getData();
					List<Integer> idxList = new ArrayList<>();
					for (int i = 0; i < len; i++) {
						int functionId = ClientUtil.checkAndGetIntValue(dsResponse.getData()[i].getAttribute("functionId"));
						searchMap.put("functionId", functionId);
						searchMap.put("applicationId", dsResponse.getData()[i].getAttribute("applicationId"));
						int idx = originalList.findIndex(searchMap);
						if (idx == -1) {
							continue;
						}
						TreeNode node = SourceGrid.getRecord(idx);
						if (originalList.isFolder(node)) {
							continue;
						}
						idxList.add(idx);
					}
					long thirdEnd = System.currentTimeMillis();
					__logger.info("功能信息-获取所有要勾选的树节点消耗时间：" + (thirdEnd - end) + " ms（" + end + " - " + thirdEnd + "）");
					if (idxList.size() > 0) {
						int[] idxArr = new int[idxList.size()];
						for (int i = 0; i < idxList.size(); i++) {
							idxArr[i] = idxList.get(i);
						}
						SourceGrid.selectRecords(idxArr);
					}
				}
				long secondEnd = System.currentTimeMillis();
				__logger.info("功能信息-页面渲染消耗时间：" + (secondEnd - end) + " ms（" + end + " - " + secondEnd + "）");
				loading.destroy();
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
		Map param = new HashMap();
		if (permissionType == 1) {
			param.put("optType", "employeeData");
			Record record = grid.getSelectedRecord();
			if (!BaseHelpUtils.isNullOrEmpty(record)) {
				param.put("employeeId", record.getAttributeAsInt("employeeId"));
			}

		}
		if (permissionType == 2) {
			param.put("optType", "roleData");
			Record record = grid.getSelectedRecord();
			if (!BaseHelpUtils.isNullOrEmpty(record)) {
				param.put("roleId", record.getAttributeAsInt("roleId"));
			}

		}
		if (permissionType == 3) {
			param.put("optType", "departmentRoleData");
			Record record = grid.getSelectedRecord();
			if (!BaseHelpUtils.isNullOrEmpty(record)) {
//				param.put("departmentRoleId", record.getAttributeAsInt("departmentRoleId"));
				param.put("roleId", record.getAttributeAsInt("roleId"));
				param.put("departmentId", record.getAttributeAsInt("departmentId"));
			}
			
		}
		ListGridRecord[] selected = SourceGrid.getSelectedRecords();
		List resList = new ArrayList();
		for (ListGridRecord r : selected) {
			Map lm = new HashMap();
			lm.put("functionId", r.getAttribute("functionId"));
			lm.put("functionName", r.getAttribute("functionName"));
			lm.put("applicationId", r.getAttribute("applicationId"));
			resList.add(lm);
		}
		param.put("detailRoleFunction", resList);
		param.put("operateEmployeeId", ClientUtil.getUserId());
		return param;
	}

	@Override
	public String getName() {
		return "";
	}

	// 初始化
	private int roleId = -1;
	private int departmentId;

	/**
	 * @return the roleId
	 */
	public int getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId
	 *            the roleId to set
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
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

	public String getFunctionCode() {
		return functionCode;
	}

	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public TreeGrid getSourceGrid() {
		return SourceGrid;
	}

	public DelicacyListGrid getGrid() {
		return grid;
	}

	public void setGrid(DelicacyListGrid grid) {
		this.grid = grid;
	}

	public int getPermissionType() {
		return permissionType;
	}

	public void setPermissionType(int permissionType) {
		this.permissionType = permissionType;
	}

}
