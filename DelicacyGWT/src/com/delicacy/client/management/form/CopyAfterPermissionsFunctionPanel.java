package com.delicacy.client.management.form;

import java.util.*;

import com.delicacy.client.BaseHelpUtils;
import com.delicacy.client.MapUtils;
import com.delicacy.client.ui.AbstractWizadPage;
import com.delicacy.client.ui.DelicacyListGrid;
import com.delicacy.client.ui.LoadingWindow;
import com.delicacy.client.data.DBDataSource;
import com.delicacy.client.management.datasource.DSCopyEmployeeAllFunctionToOther;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.SelectionAppearance;
import java.util.logging.Logger;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGridField;
import com.smartgwt.client.widgets.tree.TreeNode;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.delicacy.client.data.ClientUtil;

public class CopyAfterPermissionsFunctionPanel extends AbstractWizadPage {

	private static final Logger __logger = Logger.getLogger("");
	private DelicacyListGrid grid = new DelicacyListGrid();
	private final TreeGrid SourceGrid = new TreeGrid();
	private Integer afterEmployeeId;
	private VLayout mianPanel;

	public CopyAfterPermissionsFunctionPanel() {
		mianPanel = new VLayout();
		mianPanel.setWidth100();
		mianPanel.setHeight100();

		VLayout SearchSourceLayout = new VLayout();
		SearchSourceLayout.setWidth100();
		SearchSourceLayout.setHeight("93%");
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
		SourceGrid.setSelectionAppearance(SelectionAppearance.CHECKBOX);
		SourceGrid.setDataSource(DSCopyEmployeeAllFunctionToOther.getInstance());
		SourceGrid.setShowOpenIcons(false);
		SourceGrid.setShowDropIcons(false);
		SourceGrid.setShowSelectedStyle(true);
		SourceGrid.setShowPartialSelection(true);
		// 当为true时表示父级选中时子级自动选中，当子级选中时父级自动选中
		SourceGrid.setCascadeSelection(true);
		SourceGrid.addSelectionChangedHandler(new SelectionChangedHandler() {

			@Override
			public void onSelectionChanged(SelectionEvent event) {

				if (!event.getState()) {
					Boolean roleFun = event.getRecord().getAttributeAsBoolean("roleFun");
					Boolean departmentRoleFun = event.getRecord().getAttributeAsBoolean("departmentRoleFun");
					if (roleFun || departmentRoleFun) {
						Tree originalList = SourceGrid.getData();
						Map searchMap = new HashMap<>();
						searchMap.put("functionId", event.getRecord().getAttribute("functionId"));
						searchMap.put("applicationId", event.getRecord().getAttribute("applicationId"));
						int idx = originalList.findIndex(searchMap);
						if (idx == -1) {
							return;
						}
						TreeNode node = SourceGrid.getRecord(idx);
						if (originalList.isFolder(node)) {
							return;
						}
						SourceGrid.selectRecord(idx);
						SC.say("本条数据不能修改");
					}
				}

			}
		});
		SearchSourceLayout.setHeight100();
		SearchSourceLayout.setLayoutTopMargin(10);
		SearchSourceLayout.setLayoutRightMargin(5);
		SearchSourceLayout.setMembersMargin(10);
		SearchSourceLayout.addMember(SourceGrid);
		mianPanel.addMember(SearchSourceLayout);

		HLayout bottomPanel = new HLayout();
		bottomPanel.setWidth100();
		bottomPanel.setLayoutTopMargin(10);
		bottomPanel.setHeight("7%");
		bottomPanel.setAlign(Alignment.RIGHT);
		IButton saveButton = new IButton("保存");
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (BaseHelpUtils.isNullOrEmpty(getValuesAsMap())) {
					SC.say("没有可以保存的数据");
					return;
				}
				Map condition = new HashMap();
				condition.put("afterEmployeeId", afterEmployeeId);
				condition.put("operateEmployeeId", ClientUtil.getUserId());
				condition.put("optType", "updateEmployeeFunction");
				condition.putAll(getValuesAsMap());
				DBDataSource.callOperation("EP_CopyEmployeeFunction", condition, new DSCallback() {

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
		mianPanel.addMember(bottomPanel);
		addMember(mianPanel);
	}

	@Override
	public void startEdit() {
		final LoadingWindow loading = new LoadingWindow();
		Map condition = new HashMap();
		condition.put("afterEmployeeId", afterEmployeeId);
		condition.put("optType", "findAllFunction");
		DBDataSource.callOperation("EP_CopyEmployeeFunction", condition, new DSCallback() {
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
					RecordList finalList = getNeedSelectData();
					if (finalList != null) {
						reloadDetailTableData(finalList);
					}
				}
				loading.destroy();
			}
		});

	}

	/**
	 * 获取需要被选择的数据
	 * 
	 * @return
	 */
	public RecordList getNeedSelectData() {
		RecordList finalList = new RecordList();
		RecordList recordList = SourceGrid.getDataAsRecordList();
		for (int i = 0; i < recordList.getLength(); i++) {
			Boolean empFun = recordList.get(i).getAttributeAsBoolean("empFun");
			Boolean roleFun = recordList.get(i).getAttributeAsBoolean("roleFun");
			Boolean departmentRoleFun = recordList.get(i).getAttributeAsBoolean("departmentRoleFun");
			if (empFun || roleFun || departmentRoleFun) {
				finalList.add(recordList.get(i));
			}
		}
		return finalList;
	}

	/**
	 * 值的回显
	 * 
	 * @param finalList
	 */
	public void reloadDetailTableData(RecordList finalList) {
		int len = finalList.getLength();
		Map searchMap = new HashMap();
		Tree originalList = SourceGrid.getData();
		for (int i = 0; i < len; i++) {
			int functionId = ClientUtil.checkAndGetIntValue(finalList.get(i).getAttribute("functionId"));
			searchMap.put("functionId", functionId);
			searchMap.put("applicationId", finalList.get(i).getAttribute("applicationId"));
			int idx = originalList.findIndex(searchMap);
			if (idx == -1) {
				continue;
			}
			TreeNode node = SourceGrid.getRecord(idx);
			if (originalList.isFolder(node)) {
				continue;
			}
			SourceGrid.selectRecord(idx);
		}
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
		res.add("employeeFunction");
		return res;
	}

	@Override
	public Map getValuesAsMap() {
		Map param = new HashMap();
		ListGridRecord[] selected = SourceGrid.getSelectedRecords();
		List resList = new ArrayList();
		for (ListGridRecord r : selected) {
			Boolean roleFun = r.getAttributeAsBoolean("roleFun");
			Boolean departmentRoleFun = r.getAttributeAsBoolean("departmentRoleFun");
			if ( !roleFun && !departmentRoleFun) {
				Map lm = new HashMap();
				lm.put("functionId", r.getAttribute("functionId"));
				lm.put("applicationId", r.getAttribute("applicationId"));
				lm.put("functionName", r.getAttribute("functionName"));
				resList.add(lm);
			}
		}
		param.put("employeeFunction", resList);
		return param;
	}

	public Integer getAfterEmployeeId() {
		return afterEmployeeId;
	}

	public void setAfterEmployeeId(Integer afterEmployeeId) {
		this.afterEmployeeId = afterEmployeeId;
	}

	@Override
	public String getName() {
		return "";
	}

	public DelicacyListGrid getGrid() {
		return grid;
	}

	public void setGrid(DelicacyListGrid grid) {
		this.grid = grid;
	}

}
