package com.delicacy.client.app.panel;

import com.delicacy.client.BasicPermissionStatic;
import java.util.*;
import com.delicacy.client.MapUtils;
import com.delicacy.client.ui.AbstractWizadPage;
import com.delicacy.client.ui.DelicacyListGrid;
import com.delicacy.client.data.DBDataSource;
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
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.tree.events.NodeClickEvent;
import com.smartgwt.client.widgets.tree.events.NodeClickHandler;

public class CustomFunctionPanel extends AbstractWizadPage {

    private static final Logger __logger = Logger.getLogger("");
    private final DelicacyListGrid grid = new DelicacyListGrid(true);
    private final TreeGrid SourceGrid = new TreeGrid();
    private Map selectedMap = null;

    public CustomFunctionPanel() {

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
        idx++;
        SourceGrid.setFields(fields);
        SourceGrid.setSelectionAppearance(SelectionAppearance.CHECKBOX);
        SourceGrid.setDataSource(DSFunction.getInstance());
        SourceGrid.setShowOpenIcons(false);
        SourceGrid.setShowDropIcons(false);
        SourceGrid.setShowSelectedStyle(false);
        SourceGrid.setShowPartialSelection(true);
        //当为true时表示父级选中时子级自动选中，当子级选中时父级自动选中
        SourceGrid.setCascadeSelection(true);
        //点击节点事件
        SourceGrid.addNodeClickHandler(new NodeClickHandler() {
            @Override
            public void onNodeClick(NodeClickEvent event) {
                TreeNode node = event.getNode();
                final int functionId = ClientUtil.checkAndGetIntValue(node.getAttribute("functionId"));
                final int parentId = ClientUtil.checkAndGetIntValue(node.getAttribute("parentId"));
                final int applicationId = ClientUtil.checkAndGetIntValue(node.getAttribute("applicationId"));
                //检索部门,如果没有指定部门，则返回
                if(departmentId == 0 || departmentId == -1){
                    SC.say("提示","未指定部门,请先指定部门");
                    return;
                }
                //检索角色,如果没有指定角色，则返回
                if(roleId == 0 || roleId == -1){
                    SC.say("提示","未指定角色,请先指定角色");
                    return;
                }
                //权限控制，只有拥有操作权限才能更新操作，否则只有查看权限
                if(ClientUtil.checkIsHavePermission(BasicPermissionStatic.PERMISSION_OPERATE_MANAGEMENT)){
                    //检索当前点击的角色是否选中，如果存在selectedMap中，则表示目前是选中的状态，即当前点击的操作是取消操作
                    int flag = 0;//flag:1:表示取消操作;2:表示选中操作
                    if(selectedMap.containsKey(functionId)){//表示取消操作
                        flag = 1;
                    }else{//表示选中操作
                        flag = 2;
                    }
                    Map param = new LinkedHashMap();
                    param.put("flag", flag);
                    param.put("departmentId", departmentId);
                    param.put("roleId", roleId);
                    param.put("parentId", parentId);
                    param.put("functionId", functionId);
                    param.put("applicationId", applicationId);
                    String message = MapUtils.toJSON(param);
                    //更新方法
                    DBDataSource.callOperation("EP_OnRoleFunctionUpdate", "update", message, new DSCallback() {
                        @Override
                        public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
                            if (dsResponse.getStatus() >= 0) {
                                //执行成功后重新刷新当前表格
                                DepartmentRoleFunPanel.function.setDepartmentId(departmentId);
                                DepartmentRoleFunPanel.function.setRoleId(roleId);
                                DepartmentRoleFunPanel.function.startEdit();
                            } else {
                                Map errors = dsResponse.getErrors();
                                SC.say("operate failure" + errors);
                            }
                        }
                    });
                }else{
                    DepartmentRoleFunPanel.function.setDepartmentId(departmentId);
                    DepartmentRoleFunPanel.function.setRoleId(roleId);
                    DepartmentRoleFunPanel.function.startEdit();
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
        DBDataSource.callOperation("ST_Function", "find", new HashMap(), new DSCallback() {
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
                    reloadDetailTableData();
                }
            }
        });
    }

    public void reloadDetailTableData() {
        //初始化选中的功能Map
        selectedMap = new HashMap();
        Map condition = new HashMap();
        condition.put("departmentId", departmentId);
        condition.put("roleId",roleId);
        DBDataSource.callOperation("ST_RoleFunction", "find", condition, new DSCallback() {
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
                        int functionId = ClientUtil.checkAndGetIntValue(dsResponse.getData()[i].getAttribute("functionId"));
                        searchMap.put("functionId", functionId);
                        searchMap.put("applicationId", dsResponse.getData()[i].getAttribute("applicationId"));
                        int idx = originalList.findIndex(searchMap);
                        if (idx == -1) {
                            continue;
                        }
                        //把已选择的功能放入map中
                        selectedMap.put(functionId, functionId);
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
            resList.add(lm);
        }
        param.put("detailRoleFunction", resList);
        return param;
    }

    @Override
    public String getName() {
        return "";
    }
    
    //初始化
    private int roleId = -1;
    private int departmentId;

    /**
     * @return the roleId
     */
    public int getRoleId() {
        return roleId;
    }

    /**
     * @param roleId the roleId to set
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
     * @param departmentId the departmentId to set
     */
    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

}
