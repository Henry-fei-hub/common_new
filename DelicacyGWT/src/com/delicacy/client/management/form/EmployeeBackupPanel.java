package com.delicacy.client.management.form;

import java.util.*;
import com.delicacy.client.MapUtils;
import com.delicacy.client.ui.AbstractWizadPage;
import com.delicacy.client.ui.DelicacyListGrid;
import com.delicacy.client.data.DBDataSource;
import com.delicacy.client.management.datasource.DSDepartment;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.types.SelectionAppearance;
import java.util.logging.Logger;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGridField;
import com.smartgwt.client.widgets.tree.TreeNode;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.delicacy.client.app.datasource.DSFunction;
import com.delicacy.client.data.ClientUtil;


public class EmployeeBackupPanel extends AbstractWizadPage {

    private static final Logger __logger = Logger.getLogger("");
    private final DelicacyListGrid grid = new DelicacyListGrid(true);
    private final TreeGrid SourceGrid = new TreeGrid();
    private Map selectedMap = null;

    public EmployeeBackupPanel() {

        VLayout SearchSourceLayout = new VLayout();
        SearchSourceLayout.setWidth100();
        TreeGridField[] fields = new TreeGridField[6];
        int idx = 0;
        fields[idx] = new TreeGridField("departmentId");
        fields[idx].setHidden(true);
        idx++;
        fields[idx] = new TreeGridField("departmentName","部门名称");
        fields[idx].setFrozen(true);
        idx++;
        fields[idx] = new TreeGridField("parentId");
        fields[idx].setHidden(true);
        idx++;
        SourceGrid.setFields(fields);
        SourceGrid.setSelectionAppearance(SelectionAppearance.CHECKBOX);
   //     SourceGrid.setDataSource(DSDepartment.getInstance());
        SourceGrid.setShowOpenIcons(false);
        SourceGrid.setShowDropIcons(false);
        SourceGrid.setShowSelectedStyle(false);
        SourceGrid.setShowPartialSelection(true);
        //当为true时表示父级选中时子级自动选中，当子级选中时父级自动选中
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
		DBDataSource.callOperation("EP_RoleDepartmentEmployee", "find", new HashMap(), new DSCallback() {
            @Override
            public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
                if (dsResponse.getStatus() >= 0) {
                    int len = dsResponse.getData().length;
                    TreeNode[] nodes = new TreeNode[len];
                    for (int i = 0; i < len; i++) {
                        nodes[i] = new TreeNode();
                        //设置图标
                        String parentId=dsResponse.getData()[i].getAttribute("departmentId");
                        if(!"".equals(parentId)&&Integer.parseInt(parentId)>=10000){
                            nodes[i].setIcon("/images/photos/photo_view.png");
                        }
                        DBDataSource.copyRecord(dsResponse.getData()[i], nodes[i]);
                    }
                    Tree tree = new Tree();
                    tree.setModelType(TreeModelType.PARENT);
                    tree.setRootValue("0");
                    tree.setIdField("departmentId");
                    tree.setParentIdField("parentId");
                    tree.setData(nodes);
                    tree.openAll();
                    SourceGrid.setData(tree);
                    if (getRecord() != null) {
                        reloadDetailTableData();
                    }
                }
            }
        });
    }

    public void reloadDetailTableData() {
        //初始化选中的功能Map
        selectedMap = new HashMap();
        Map condition = new HashMap();
        condition.put("roleId",getRecord().getAttribute("roleId"));
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
                        int employeeId = ClientUtil.checkAndGetIntValue(dsResponse.getData()[i].getAttribute("employeeId"));
                        searchMap.put("departmentId", employeeId+10000);
                        
                        int idx = originalList.findIndex(searchMap);
                        if (idx == -1) {
                            continue;
                        }
                        //把已选择的功能放入map中
                        selectedMap.put(employeeId, employeeId);
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
        res.add("detailRoleEmployee");
        return res;
    }

    @Override
    public Map getValuesAsMap() {
        Map param = new HashMap();
        ListGridRecord[] selected = SourceGrid.getSelectedRecords();
        List resList = new ArrayList();
        for (ListGridRecord r : selected) {
            Map lm = new HashMap();
            lm.put("departmentId", r.getAttribute("departmentId"));
            resList.add(lm);
        }
        param.put("detailRoleEmployee", resList);
        return param;
    }

    @Override
    public String getName() {
        return "";
    }
  
}
