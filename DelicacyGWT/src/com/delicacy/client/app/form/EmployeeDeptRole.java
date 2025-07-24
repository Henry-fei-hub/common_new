package com.delicacy.client.app.form;

import com.delicacy.client.BaseHelpUtils;
import com.delicacy.client.MapUtils;
import com.delicacy.client.app.datasource.DSDeptRole;
import com.delicacy.client.data.DBDataSource;
import com.delicacy.client.ui.AbstractWizadPage;
import com.delicacy.client.ui.DelicacyListGrid;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.EditCompleteEvent;
import com.smartgwt.client.widgets.grid.events.EditCompleteHandler;
import com.smartgwt.client.widgets.grid.events.EditorExitEvent;
import com.smartgwt.client.widgets.grid.events.EditorExitHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeGridField;
import com.smartgwt.client.widgets.tree.TreeNode;
import java.util.*;
import java.util.logging.Logger;

public class EmployeeDeptRole extends AbstractWizadPage {

    private static final Logger __logger = Logger.getLogger("EmployeeDeptRole");
    private final DelicacyListGrid grid = new DelicacyListGrid(true);
    private final TreeGrid SourceGrid = new TreeGrid();

    public EmployeeDeptRole() {
        VLayout SearchSourceLayout = new VLayout();
        SearchSourceLayout.setWidth100();
        TreeGridField[] fields = new TreeGridField[3];
        int idx = 0;
        fields[idx] = new TreeGridField("functionId");
        fields[idx].setHidden(true);
        idx++;
//        fields[idx] = new TreeGridField("functionCode");
//        idx++;
        fields[idx] = new TreeGridField("functionName");
        fields[idx].setFrozen(true);
        idx++;
//        fields[idx] = new TreeGridField("applicationId");
//        idx++;
        fields[idx] = new TreeGridField("isDefault");
        fields[idx].setCanEdit(true);
        idx++;
        SourceGrid.setFields(fields);
        SourceGrid.setSelectionAppearance(SelectionAppearance.CHECKBOX);
        SourceGrid.setDataSource(DSDeptRole.getInstance());
        SourceGrid.setShowOpenIcons(false);
        SourceGrid.setShowDropIcons(false);
        SourceGrid.setShowSelectedStyle(false);
        SourceGrid.setShowPartialSelection(true);
        SourceGrid.setCascadeSelection(true);
        SourceGrid.setShowFilterEditor(true);

        SearchSourceLayout.setHeight100();
        SearchSourceLayout.setLayoutTopMargin(10);
        SearchSourceLayout.setLayoutRightMargin(5);
        SearchSourceLayout.setMembersMargin(10);
        SearchSourceLayout.addMember(SourceGrid);
        addMember(SearchSourceLayout);

        SourceGrid.addEditorExitHandler(new EditorExitHandler() {

            @Override
            public void onEditorExit(EditorExitEvent event) {
//                __logger.info("isDefault=========" + event.getRecord().getAttribute("isDefault"));
//                __logger.info("parentId=========" + event.getRecord().getAttribute("parentId"));
//                __logger.info("roleId=========" + event.getRecord().getAttribute("functionCode"));
                
                String isDefault=BaseHelpUtils.getString(event.getRecord().getAttribute("isDefault"));
                String deptId=BaseHelpUtils.getString(event.getRecord().getAttribute("parentId"));
                String roleId=BaseHelpUtils.getString(event.getRecord().getAttribute("functionCode"));
                event.getRecord().setAttribute("isDefault", !BaseHelpUtils.getBoolean(isDefault));
                ListGridRecord[] selected = SourceGrid.getSelectedRecords();
                for (ListGridRecord r : selected) {
                    if (BaseHelpUtils.getString(r.getAttribute("functionCode")).equals(roleId)&&
                            BaseHelpUtils.getString(r.getAttribute("parentId")).equals(deptId)) {
                        r.setAttribute("isDefault", !BaseHelpUtils.getBoolean(isDefault));
                    }
                }
            }
        });
        
//        SourceGrid.addCellMouseUpHandler(new CellMouseUpHandler() {
//
//            @Override
//            public void onCellMouseUp(CellMouseUpEvent event) {
//                __logger.info("isDefault=========" + event.getRecord().getAttribute("isDefault"));
//                __logger.info("parentId=========" + event.getRecord().getAttribute("parentId"));
//                 __logger.info("roleId=========" + event.getRecord().getAttribute("functionCode"));
//                String isDefault=BaseHelpUtils.getString(event.getRecord().getAttribute("isDefault"));
//                String deptId=BaseHelpUtils.getString(event.getRecord().getAttribute("parentId"));
//                String roleId=BaseHelpUtils.getString(event.getRecord().getAttribute("functionCode"));
//                ListGridRecord[] selected = SourceGrid.getSelectedRecords();
//                for (ListGridRecord r : selected) {
//                    if (BaseHelpUtils.getString(r.getAttribute("functionCode")).equals(roleId)&&
//                            BaseHelpUtils.getString(r.getAttribute("parentId")).equals(deptId)) {
//                        r.setAttribute("isDefault", !BaseHelpUtils.getBoolean(isDefault));
//                    }
//                }
//            }
//        });

        //点击触发的函数,保存数据
//        SourceGrid.addNodeClickHandler(new NodeClickHandler() {
//
//            @Override
//            public void onNodeClick(NodeClickEvent event) {
//                __logger.info("isDefault=========" + event.getNode().getAttribute("isDefault"));
////                __logger.info("roleId=========" + event.getNode().getAttribute("functionCode"));
////                __logger.info("enabled=========" + event.getNode().getAttribute("enabled"));
////                Map condition = new HashMap();
////                condition.put("employeeId", getRecord().getAttribute("employeeId"));
////                condition.put("deptId", event.getNode().getAttribute("parentId"));
////                condition.put("roleId", event.getNode().getAttribute("functionCode"));
////                DBDataSource.callOperation("EP_DefaultSetEmpRole", "find", condition, new DSCallback() {
////                    @Override
////                    public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
////                        if (dsResponse.getStatus() >= 0) {
////                            startEdit();
////
////                        }
////                    }
////                });
//            }
//        });
    }

    @Override
    public void startEdit() {
        //点击了那个人员的id
        Map m = new HashMap<>();
        m.put("employeeId", getRecord().getAttribute("employeeId"));
        DBDataSource.callOperation("EP_CustomDeptRoleProcessor", "find", m, new DSCallback() {
            @Override
            public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
                if (dsResponse.getStatus() >= 0) {
                    int len = dsResponse.getData().length;
                    TreeNode[] nodes = new TreeNode[len];
                    for (int i = 0; i < len; i++) {
                        nodes[i] = new TreeNode();
                        //设置图标
                        String roleId = dsResponse.getData()[i].getAttribute("functionId");
                        if (!"".equals(roleId) && Integer.parseInt(roleId) >= 100000) {
                            nodes[i].setIcon("/images/photos/role_view.png");
                        }
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
                    reloadDetailTableData();
                }
            }
        });
    }

    public void reloadDetailTableData() {
        Map condition = new HashMap();
        condition.put("employeeId", getRecord().getAttribute("employeeId"));
        DBDataSource.callOperation("EP_CustomEmpSelectRoleProcessor", "find", condition, new DSCallback() {
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
                        searchMap.put("functionId", dsResponse.getData()[i].getAttribute("functionId"));
                        searchMap.put("applicationId", dsResponse.getData()[i].getAttribute("applicationId"));
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
            lm.put("functionId", r.getAttribute("functionId"));
            lm.put("applicationId", r.getAttribute("applicationId"));

            lm.put("employeeId", getRecord().getAttribute("employeeId"));
            lm.put("deptId", r.getAttribute("parentId"));
            lm.put("roleId", r.getAttribute("functionCode"));
            lm.put("isDefault", r.getAttribute("isDefault"));
            resList.add(lm);
            __logger.info("params  = " + MapUtils.toJSON(lm));
        }

        param.put("detailRoleFunction", resList);
        return param;
    }

    @Override
    public String getName() {
        return "";
    }

}
