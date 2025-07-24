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
import com.delicacy.client.app.datasource.DSRoleWithR;
import com.delicacy.client.data.ClientUtil;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.tree.events.NodeClickEvent;
import com.smartgwt.client.widgets.tree.events.NodeClickHandler;

public class CustomExistRolePartPanel extends AbstractWizadPage {

	private static final Logger __logger = Logger.getLogger("");
	private final DelicacyListGrid grid = new DelicacyListGrid(true);
	private final TreeGrid SourceGrid = new TreeGrid();
	private Map selectedMap = null;

	public CustomExistRolePartPanel() {

		VLayout SearchSourceLayout = new VLayout();
		SearchSourceLayout.setWidth100();
		TreeGridField[] fields = new TreeGridField[6];
		int idx = 0;
		fields[idx] = new TreeGridField("roleId");
		fields[idx].setHidden(true);
		idx++;
		// fields[idx] = new TreeGridField("functionCode");
		// idx++;
		// fields[idx] = new TreeGridField("parentId");
		// fields[idx].setHidden(true);
		// idx++;
		fields[idx] = new TreeGridField("applicationId");
		idx++;
		fields[idx] = new TreeGridField("roleName");
		fields[idx].setFrozen(true);
		idx++;
		fields[idx] = new TreeGridField("enabled");
		fields[idx].setHidden(Boolean.TRUE);
		idx++;
		SourceGrid.setFields(fields);
		SourceGrid.setSelectionAppearance(SelectionAppearance.ROW_STYLE);
		SourceGrid.setDataSource(DSRoleWithR.getInstance());
		SourceGrid.setShowOpenIcons(false);
		SourceGrid.setShowDropIcons(false);
		SourceGrid.setShowSelectedStyle(false);
		SourceGrid.setShowPartialSelection(true);
		SourceGrid.setCanRemoveRecords(true);
		// 当为true时表示父级选中时子级自动选中，当子级选中时父级自动选中
		SourceGrid.setCascadeSelection(false);
		SourceGrid.addRemoveRecordClickHandler(new RemoveRecordClickHandler() {
			@Override
			public void onRemoveRecordClick(RemoveRecordClickEvent event) {
				int rowNum = event.getRowNum();
				ListGridRecord record = SourceGrid.getRecord(rowNum);
				// 获取到角色id
				final int roleId = ClientUtil.checkAndGetIntValue(record.getAttribute("roleId"));
				final int applicationId = ClientUtil.checkAndGetIntValue(record.getAttribute("applicationId"));
				final String roleName = record.getAttribute("roleName");
				// 检索职员，没有没有指定人员，则返回
				if (employeeId == 0) {
					SC.say("提示", "未指定职员,请指定职员");
					return;
				}
				// 先检索部门,如果没有指定部门，则返回
				if (departmentId == -1 || departmentId == 0) {
					SC.say("提示", "未指定部门,请指定部门");
					return;
				}
				onDepartmentRoleUpdate(1, roleId, applicationId);
			}
		});
        
		// 点击节点事件
		SourceGrid.addDoubleClickHandler(new DoubleClickHandler() {
			@Override
			public void onDoubleClick(DoubleClickEvent event) {
				ListGridRecord[] selected = SourceGrid.getSelectedRecords();
//						TreeNode node = event.getNode();
				final int roleId = ClientUtil.checkAndGetIntValue(selected[0].getAttribute("roleId"));
				// 检索职员，没有没有指定人员，则返回
				if (employeeId == 0) {
					SC.say("提示", "未指定职员,请指定职员");
					return;
				}
//				// 先检索部门,如果没有指定部门，则返回
				if (departmentId == -1 || departmentId == 0) {
					SC.say("提示", "未指定部门,请指定部门");
					return;
				}
				//检索该人员是否已有默认的角色，如果存在则提示是否变更
				ListGridRecord[] records = CustomEmpDefaultRolePanel.SourceGrid.getRecords();
				if(records.length > 0){
					SC.ask("提示", "该人员已有默认角色，您确定变更吗?", new BooleanCallback() {
						@Override
						public void execute(Boolean value) {
							if(value){
								setEmpDefautlRole(roleId);
							}
						}
					});
				}else{
					setEmpDefautlRole(roleId);
				}
			}
		});

		SearchSourceLayout.setHeight100();
		SearchSourceLayout.setLayoutTopMargin(10);
		SearchSourceLayout.setLayoutRightMargin(5);
		SearchSourceLayout.setMembersMargin(10);
		SearchSourceLayout.addMember(SourceGrid);
		addMember(SearchSourceLayout);
	}

	@Override
	public void startEdit() {
		// 初始化加载所有的角色信息，即当部门id=-1时
		if (departmentId == -1) {
//			DBDataSource.callOperation("ST_Role", "find", new HashMap(), new DSCallback() {
//				@Override
//				public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
//					if (dsResponse.getStatus() >= 0) {
//						int len = dsResponse.getData().length;
//						TreeNode[] nodes = new TreeNode[len];
//						for (int i = 0; i < len; i++) {
//							nodes[i] = new TreeNode();
//							DBDataSource.copyRecord(dsResponse.getData()[i], nodes[i]);
//						}
//						Tree tree = new Tree();
//						tree.setModelType(TreeModelType.PARENT);
//						tree.setRootValue("0");
//						tree.setIdField("roleId");
//						tree.setData(nodes);
//						tree.openAll();
//						SourceGrid.setData(tree);
//					}
//				}
//			});
		} else if (departmentId > 0) {
			Map condition = new HashMap();
			condition.put("departmentId", departmentId);
			condition.put("employeeId", employeeId);
			DBDataSource.callOperation("EP_CustomEmpRoleFindProcessor", "find", condition, new DSCallback() {
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
						reloadDetailTableData();
					}
				}
			});

		}
	}

	public void reloadDetailTableData() {
		// 初始化选中的角色Map
		selectedMap = new HashMap();
		Map condition = new HashMap();
		condition.put("departmentId", departmentId);
		condition.put("employeeId", employeeId);
		DBDataSource.callOperation("ST_EmployeeRole", "find", condition, new DSCallback() {
			@Override
			public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
				if (dsResponse.getStatus() >= 0) {
					int len = dsResponse.getData().length;
					if (len == 0) {
						return;
					}
					Map searchMap = new HashMap();
					Tree originalList = SourceGrid.getData();
					for (int i = 0; i < len; i++) {
						int roleId = ClientUtil.checkAndGetIntValue(dsResponse.getData()[i].getAttribute("roleId"));
						searchMap.put("roleId", roleId);
						int idx = originalList.findIndex(searchMap);
						if (idx == -1) {
							continue;
						}
						TreeNode node = SourceGrid.getRecord(idx);
						if (originalList.isFolder(node)) {
							continue;
						}
//						SourceGrid.selectRecord(idx);
						// 把已选择的角色放入map中
						selectedMap.put(roleId, roleId);
					}
				}
			}
		});
	}
	
	/**
	 * 
	 * @Title: setEmpDefautlRole 
	 * @Description: 设置员工默认角色
	 * @param @param roleId   
	 * @return void   
	 * @throws
	 */
	private void setEmpDefautlRole( final int roleId){
		Map param = new LinkedHashMap();
		param.put("departmentId", departmentId);
		param.put("roleId", roleId);
		param.put("employeeId", employeeId);
		param.put("opt_type", "setDefaultRole");
		String message = MapUtils.toJSON(param);
		// 更新方法
		DBDataSource.callOperation("EP_UpdateEmpDefaultRole", "update", message, new DSCallback() {
			@Override
			public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
				if (dsResponse.getStatus() >= 0) {
					// 执行成功后重新刷新当前表格
					EmpDepartmentRolePanel.role.setDepartmentId(departmentId);
					EmpDepartmentRolePanel.role.startEdit();
					EmpDepartmentRolePanel.existRole.setDepartmentId(departmentId);
					EmpDepartmentRolePanel.existRole.startEdit();
					EmpDepartmentRolePanel.defaultRole.setDepartmentId(departmentId);
					EmpDepartmentRolePanel.defaultRole.startEdit();
				} else {
					Map errors = dsResponse.getErrors();
					SC.say("operate failure" + errors);
				}
			}
		});
	}
	
	/**
	 * 点击移除角色gird时进行更新操作
	 * 
	 * @param flag:1:表示取消操作;2:表示选中操作
	 * @param roleId
	 * @param applicationId
	 */
	public void onDepartmentRoleUpdate(int flag, final int roleId, final int applicationId) {
		Map param = new LinkedHashMap();
		param.put("flag", flag);
		param.put("departmentId", departmentId);
		param.put("roleId", roleId);
		param.put("employeeId", employeeId);
		String message = MapUtils.toJSON(param);
		// 更新方法
		DBDataSource.callOperation("EP_OnEmpDepartmentRoleUpdate", "update", message, new DSCallback() {
			@Override
			public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
				if (dsResponse.getStatus() >= 0) {
					// 执行成功后重新刷新当前表格
					EmpDepartmentRolePanel.role.setDepartmentId(departmentId);
					EmpDepartmentRolePanel.role.startEdit();
					EmpDepartmentRolePanel.existRole.setDepartmentId(departmentId);
					EmpDepartmentRolePanel.existRole.startEdit();
					EmpDepartmentRolePanel.defaultRole.setDepartmentId(departmentId);
					EmpDepartmentRolePanel.defaultRole.startEdit();
				} else {
					Map errors = dsResponse.getErrors();
					SC.say("operate failure" + errors);
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
