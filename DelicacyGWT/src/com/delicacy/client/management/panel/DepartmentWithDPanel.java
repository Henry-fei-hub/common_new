package com.delicacy.client.management.panel;

import com.smartgwt.client.widgets.*;
import com.smartgwt.client.widgets.events.*;
import com.smartgwt.client.util.SC;
import com.delicacy.client.ui.LayoutConstant;
import com.delicacy.client.data.DBDataSource;
import com.delicacy.client.data.ClientUtil;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.widgets.form.fields.*;
import com.smartgwt.client.widgets.grid.events.EditCompleteEvent;
import com.smartgwt.client.widgets.grid.events.EditCompleteHandler;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.grid.events.SelectionUpdatedEvent;
import com.smartgwt.client.widgets.grid.events.SelectionUpdatedHandler;
import java.util.Objects;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGridField;
import com.smartgwt.client.widgets.tree.TreeNode;
import com.delicacy.client.ui.AbstractSearchTree;
import com.delicacy.client.PanelFactory;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.delicacy.client.management.datasource.DSDepartmentWithD;
import com.delicacy.client.management.form.DepartmentDetailDepartmentRole;
import com.delicacy.client.management.form.DepartmentRoleDetailRoleFunction;
import com.smartgwt.client.data.*;
import com.smartgwt.client.widgets.form.SearchForm;
import java.util.Map;
import java.util.logging.Logger;

public class DepartmentWithDPanel extends AbstractSearchTree {

	private static final Logger __LOGGER = Logger.getLogger("");
	protected DepartmentDetailDepartmentRole __detailDepartmentRole;

	public DepartmentDetailDepartmentRole getDetailDepartmentRole() {
		return __detailDepartmentRole;
	}

	public void setDetailDepartmentRole(DepartmentDetailDepartmentRole val) {
		__detailDepartmentRole = val;
	}

	protected DepartmentRoleDetailRoleFunction __detailRoleFunction;

	public DepartmentRoleDetailRoleFunction getDetailRoleFunction() {
		return __detailRoleFunction;
	}

	public void setDetailRoleFunction(DepartmentRoleDetailRoleFunction val) {
		__detailRoleFunction = val;
	}

	public static class Factory implements PanelFactory {

		private String id;

		@Override
		public Canvas create() {
			DepartmentWithDPanel cm = new DepartmentWithDPanel();
			id = cm.getID();
			return cm;
		}

		@Override
		public String getID() {
			return id;
		}

		@Override
		public String getDescription() {
			return "DepartmentWithD";
		}

	}

	@Override
	public void init() {
		super.init();
		__controlPosition = LayoutConstant.RIGHT;
		__detailDepartmentRole = new DepartmentDetailDepartmentRole();
		__detailCanvas.add(__detailDepartmentRole);
		__detailRoleFunction = new DepartmentRoleDetailRoleFunction();
		__detailCanvas.add(__detailRoleFunction);
		__detailDepartmentRole.setStartLoadedDetail(new DSCallback() {
			@Override
			public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
				__originalSelectedDepartmentRoles = DBDataSource.convertRecordToListGridRecords(dsResponse.getData());
			}
		});

		__detailDepartmentRole.setEndLoadedDetail(new DSCallback() {
			@Override
			public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
				if (dsResponse.getData().length > 0) {
					__selectedDepartmentRole = DBDataSource.convertRecordToListGridRecord(dsResponse.getData()[0]);
					__detailRoleFunction.setRecord(__selectedDepartmentRole);
					__detailRoleFunction.reloadDetailTableData();
				} else {
					__detailRoleFunction.deselectAllSource();
				}
			}
		});
		__needPagenation = false;
	}

	@Override
	public void initComponents() {
		resultGrid.setCanEdit(true);
		TreeGridField[] fields = new TreeGridField[5];
		int idx = 0;
		fields[idx] = new TreeGridField("departmentId");
		fields[idx].setHidden(true);
		idx++;
		fields[idx] = new TreeGridField("departmentName");
		fields[idx].setFrozen(true);
		fields[idx].setCanEdit(true);
		fields[idx].setWidth(300);
		idx++;
		fields[idx] = new TreeGridField("abbreviation");
		idx++;
		fields[idx] = new TreeGridField("managerId");
		ComboBoxItem managerIdcomboBox = new ComboBoxItem();
		fields[idx].setEditorProperties(managerIdcomboBox);
		idx++;
		fields[idx] = new TreeGridField("parentId");
		fields[idx].setHidden(true);
		idx++;
		resultGrid.setFields(fields);
		resultGrid.setShowFilterEditor(true);
		resultGrid.setFilterOnKeypress(true);
		resultGrid.addDropCompleteHandler(new DropCompleteHandler() {
			@Override
			public void onDropComplete(DropCompleteEvent event) {
				Record[] rs = event.getTransferredRecords();
				DBDataSource.printRecord(rs[0]);
				for (Record r : rs) {
					DBDataSource.callOperation("ST_DepartmentWithD", "update", r.toMap(), new DSCallback() {
						@Override
						public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
							if (dsResponse.getStatus() < 0) {
								ClientUtil.displayErrorMessage(dsResponse);
							}
						}
					});
				}
			}
		});

		IButton refreshButton = new IButton("刷新");
		controlLayout.addMember(refreshButton);
		refreshButton.setIcon("[SKIN]/actions/refresh.png");
		refreshButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				commonQuery();
			}
		});

		resultGrid.addEditCompleteHandler(new EditCompleteHandler() {
			@Override
			public void onEditComplete(EditCompleteEvent event) {
				Map params = resultGrid.getRecord(event.getRowNum()).toMap();
				Object parentId = params.get("parentId");
				if (parentId == null) {
					params.put("parentId", "0");
				}
				final int rowNumModified = event.getRowNum();
				DBDataSource.callOperation("ST_DepartmentWithD", "saveOrUpdate", params, new DSCallback() {
					@Override
					public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
						if (dsResponse.getStatus() < 0) {
							ClientUtil.displayErrorMessage(dsResponse);
						} else {
							DBDataSource.copyRecord(dsResponse.getData()[0], resultGrid.getRecord(rowNumModified));
							resultGrid.redraw();
						}
					}
				});
			}
		});

		IButton newButton = new IButton("新建");
		controlLayout.addMember(newButton);
		newButton.setIcon("[SKIN]/actions/add.png");
		newButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Tree tree = resultGrid.getData();
				TreeNode node = new TreeNode();
				node.setIsFolder(true);
				tree.add(node, "/", 0);
				resultGrid.selectSingleRecord(0);
				resultGrid.startEditing(0);
				__detailDepartmentRole.deselectAllSource();
				__detailRoleFunction.deselectAllSource();
			}
		});

		IButton newChildButton = new IButton("新建子项");
		controlLayout.addMember(newChildButton);
		newChildButton.setIcon("[SKIN]/actions/add.png");
		newChildButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				TreeNode currentNode = resultGrid.getSelectedRecord();
				if (currentNode == null) {
					SC.say("Please select a item as parent.");
					return;
				}
				DBDataSource.printRecord(currentNode);
				Tree tree = resultGrid.getData();
				TreeNode newNode = new TreeNode();
				DBDataSource.copyRecordNotInclude(currentNode, newNode, "departmentId", "children", "isFolder");
				newNode.setAttribute("parentId", ClientUtil.checkAndGetIntValue(currentNode.getAttribute("departmentId")));
				DBDataSource.printRecord(newNode);
				newNode = tree.add(newNode, currentNode);
				resultGrid.openFolder(currentNode);
				resultGrid.selectSingleRecord(newNode);
				resultGrid.startEditing(resultGrid.getRowNum(newNode));
				__detailDepartmentRole.deselectAllSource();
				__detailRoleFunction.deselectAllSource();
			}
		});

		IButton removeButton = new IButton("删除");
		controlLayout.addMember(removeButton);
		removeButton.setIcon("[SKIN]/actions/remove.png");
		removeButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (!resultGrid.anySelected()) {
					SC.say("Please select a data to remove.");
				}
				final ListGridRecord selected = resultGrid.getSelectedRecord();
				DBDataSource.callOperation("ST_DepartmentWithD", "delete", selected.toMap(), new DSCallback() {
					@Override
					public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
						if (dsResponse.getStatus() < 0) {
							ClientUtil.displayErrorMessage(dsResponse);
						} else {
							int indexNum = resultGrid.getRowNum(selected);
							resultGrid.removeData(selected);
							resultGrid.selectSingleRecord(indexNum);
						}
					}
				});
			}
		});

		resultGrid.addSelectionUpdatedHandler(new SelectionUpdatedHandler() {
			@Override
			public void onSelectionUpdated(SelectionUpdatedEvent event) {
				ListGridRecord r = resultGrid.getSelectedRecord();
				if (r == null) {
					return;
				}
				if (__selectedDepartmentRole != null && !__detailRoleFunction.isTheValuesEmpty()) {
					Map params = __selectedDepartmentRole.toMap();
					params.putAll(__detailRoleFunction.getValuesAsMap());
					DBDataSource.callOperation("ST_DepartmentRoleWithR", "update", params, new DSCallback() {
						@Override
						public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
							if (dsResponse.getStatus() < 0) {
								ClientUtil.displayErrorMessage(dsResponse);
							}
						}
					});
				}
				__selectedDepartment = r;
				__detailDepartmentRole.setRecord(r);
				__detailDepartmentRole.reloadDetailTableData();
			}
		});

		__detailDepartmentRole.getSourceGrid().addSelectionUpdatedHandler(new SelectionUpdatedHandler() {
			@Override
			public void onSelectionUpdated(SelectionUpdatedEvent event) {
			}
		});

		__detailDepartmentRole.getSourceGrid().addRecordClickHandler(new RecordClickHandler() {
			@Override
			public void onRecordClick(RecordClickEvent event) {
//				__LOGGER.info("----------------------------");
				final ListGridRecord clickedRecord = event.getRecord();
				ListGridRecord departmentWithDRecord = resultGrid.getSelectedRecord();
				clickedRecord.setAttribute("departmentId", departmentWithDRecord.getAttribute("departmentId"));
				if (__selectedDepartmentRole != null && !__detailRoleFunction.isTheValuesEmpty() && !Objects.equals(__selectedDepartmentRole.getAttributeAsInt("roleId"), clickedRecord.getAttributeAsInt("roleId"))) {
					Map params = __selectedDepartmentRole.toMap();
					params.putAll(__detailRoleFunction.getValuesAsMap());
					DBDataSource.callOperation("ST_DepartmentRoleWithR", "update", params, new DSCallback() {
						@Override
						public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
							if (dsResponse.getStatus() < 0) {
								ClientUtil.displayErrorMessage(dsResponse);
							}
						}
					});
				}
				if (event.getFieldNum() == 0) {
					if (isInOriginalList(clickedRecord)) {
						DBDataSource.callOperation("ST_DepartmentRoleWithR", "delete", clickedRecord.toMap(), new DSCallback() {
							@Override
							public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
								if (dsResponse.getStatus() < 0) {
									ClientUtil.displayErrorMessage(dsResponse);
								} else {
									DBDataSource.copyRecord(dsResponse.getData()[0], clickedRecord);
								}
							}
						});
					} else {
						DBDataSource.callOperation("ST_DepartmentRoleWithR", "save", clickedRecord.toMap(), new DSCallback() {
							@Override
							public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
								if (dsResponse.getStatus() < 0) {
									ClientUtil.displayErrorMessage(dsResponse);
								} else {
									DBDataSource.copyRecord(dsResponse.getData()[0], clickedRecord);
								}
							}
						});
						__detailRoleFunction.deselectAllSource();
						__selectedDepartmentRole = clickedRecord;
					}
				} else if (isInOriginalList(clickedRecord)) {
					__selectedDepartmentRole = clickedRecord;
					__detailRoleFunction.setRecord(clickedRecord);
					__detailRoleFunction.reloadDetailTableData();
				}
				__originalSelectedDepartmentRoles = __detailDepartmentRole.getSourceGrid().getSelectedRecords();
			}
		});

		__detailDepartmentRole.startEdit();
		__detailRoleFunction.startEdit();
		commonQuery();
	}

	@Override
	public void fetchDataCallback(DSResponse response, Object rawData) {
		int len = response.getData().length;
		TreeNode[] nodes = new TreeNode[len];
		for (int i = 0; i < len; i++) {
			nodes[i] = new TreeNode();
			DBDataSource.copyRecord(response.getData()[i], nodes[i]);
		}
		Tree tree = new Tree();
		tree.setModelType(TreeModelType.PARENT);
		tree.setRootValue("0");
		tree.setIdField("departmentId");
		tree.setParentIdField("parentId");
		tree.setData(nodes);
		resultGrid.setData(tree);
		resultGrid.selectSingleRecord(0);
		TreeNode selectedNode = resultGrid.getSelectedRecord();
		if (selectedNode != null) {
			resultGrid.openFolder(selectedNode);
		}
	}

	@Override
	public boolean checkSearchCriteria(Criteria criteria) {
		criteria.addCriteria("addtionalCondition", "order by department_id");
		return true;
	}

	private ListGridRecord __selectedDepartment;
	private ListGridRecord __selectedDepartmentRole;
	private ListGridRecord[] __originalSelectedDepartmentRoles;

	private boolean isInOriginalList(ListGridRecord clicked) {
		if (__originalSelectedDepartmentRoles == null || __originalSelectedDepartmentRoles.length == 0) {
			return false;
		}
		for (ListGridRecord r : __originalSelectedDepartmentRoles) {
			if (Objects.equals(r.getAttributeAsInt("roleId"), clicked.getAttributeAsInt("roleId"))) {
				return true;
			}
		}
		return false;
	}

	@Override
	public SearchForm generateSearchForm() {
		return null;
	}

	@Override
	public DataSource getDataSource() {
		return DSDepartmentWithD.getInstance();
	}

}
