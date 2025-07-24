package com.delicacy.client.app.panel;

import com.delicacy.client.BaseHelpUtils;
import com.delicacy.client.app.datasource.DSCompanyReportIntegralDataSource;
import com.delicacy.client.app.datasource.DSSubjectType;
import com.delicacy.client.data.DBDataSource;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.department.datasource.DSSubjectDepartment;
import com.delicacy.client.ui.AbstractWizadPage;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.AutoFitWidthApproach;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.ValuesManager;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeGridField;
import com.smartgwt.client.widgets.tree.TreeNode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

public class SubjectTypeLinkDepartmentDesignPanel extends AbstractWizadPage {
    private final Logger __logger = Logger.getLogger("");
    private HLayout hLayout;
    private VLayout vLayout;

    //定义左边的汇总数据集的gird开始***********************

    //报销科目grid
    private TreeGrid subjectTypeGrid;
    //定义左边的汇总数据集的gird结束***********************

    private SelectItem departmentSubjectItem;

    private DynamicForm form = new DynamicForm();

    public SubjectTypeLinkDepartmentDesignPanel() {
        hLayout = new HLayout();
        hLayout.setWidth100();
        hLayout.setHeight100();
        hLayout.setBackgroundColor("#e2e2e2");
        hLayout.setMargin(10);
        hLayout.setMembersMargin(20);

        //报销科目grid
        subjectTypeGrid = new TreeGrid();
        subjectTypeGrid.setHeight("100%");
        subjectTypeGrid.setSaveLocally(true);
        subjectTypeGrid.setAutoFetchData(false);
        subjectTypeGrid.setAutoFitWidthApproach(AutoFitWidthApproach.BOTH);
        subjectTypeGrid.setAutoFitFieldWidths(true);
        subjectTypeGrid.setShowHeaderContextMenu(false);
        subjectTypeGrid.setShowHeaderMenuButton(false);
        subjectTypeGrid.setCanEdit(true);
        subjectTypeGrid.setCanReparentNodes(true);
        TreeGridField[] fields = new TreeGridField[5];
        int idx = 0;
        fields[idx] = new TreeGridField(" ");
        fields[idx].setWidth(1);
        fields[idx].setCanEdit(false);
        idx++;
        fields[idx] = new TreeGridField("hasSelected");
        fields[idx].setCanEdit(false);
        fields[idx].setWidth(20);
        idx++;
        fields[idx] = new TreeGridField("subjectName");
        fields[idx].setCanEdit(false);
        idx++;
        fields[idx] = new TreeGridField("subjectTypeId");
        fields[idx].setCanEdit(false);
        fields[idx].setHidden(true);
        idx++;
        fields[idx] = new TreeGridField("parentId");
        fields[idx].setCanEdit(false);
        fields[idx].setHidden(true);
        subjectTypeGrid.setFields(fields);
        subjectTypeGrid.setShowFilterEditor(false);
        subjectTypeGrid.setFilterOnKeypress(false);
        DSSubjectType subjectTypeDataSource = DSSubjectType.getInstance();
        subjectTypeGrid.setDataSource(subjectTypeDataSource);

        vLayout = new VLayout();
        vLayout.setMargin(10);
        vLayout.setMembersMargin(20);
        vLayout.setHeight100();
        vLayout.setWidth("60%");

        TreeGridField[] field1s = new TreeGridField[4];
        int idx1 = 0;
        field1s[idx1] = new TreeGridField(" ");
        field1s[idx1].setWidth(1);
        field1s[idx1].setCanEdit(false);
        idx1++;
        field1s[idx1] = new TreeGridField("hasSelected");
        field1s[idx1].setCanEdit(false);
        field1s[idx1].setWidth(20);
        idx1++;
        field1s[idx1] = new TreeGridField("departmentName");
        field1s[idx1].setCanEdit(false);
        idx1++;

        field1s[idx1] = new TreeGridField("parentId");
        field1s[idx1].setCanEdit(false);
        field1s[idx1].setHidden(true);
        idx1++;

        TreeGridField[] field2s = new TreeGridField[4];
        int idx2 = 0;

        field2s[idx2] = new TreeGridField(" ");
        field2s[idx2].setWidth(1);
        field2s[idx2].setCanEdit(false);
        idx2++;

        field2s[idx2] = new TreeGridField("parentProcessTypeId");
        field2s[idx2].setHidden(true);
        idx2++;

        field2s[idx2] = new TreeGridField("hasSelected");
        field2s[idx2].setWidth(20);
        field2s[idx2].setCanEdit(false);
        idx2++;

        field2s[idx2] = new TreeGridField("processTypeName");
        field2s[idx2].setCanEdit(false);
        idx2++;

        departmentSubjectItem = new SelectItem("dicType", "部门科目类型");
        departmentSubjectItem.setWidth(600);
//        departmentSubjectItem.setValueMap(KeyValueManager.getValueMap("system_dictionary_12"));

        departmentSubjectItem.addChangedHandler(new ChangedHandler() {
            @Override
            public void onChanged(ChangedEvent event) {
                SC.debugger();
                int  subjectTypeId = BaseHelpUtils.getIntValue(event.getValue());

                Map condition = new HashMap<>();
                condition.put("dstId", subjectTypeId);
                if(!BaseHelpUtils.isNullOrEmpty(condition.get("dstId"))){
                    DBDataSource.callOperation("ST_DepartmentSubjectTypeDetail", "find", condition, new DSCallback() {
                        @Override
                        public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
                            SC.debugger();
                            if (dsResponse.getStatus() >= 0) {
                                clearNode();
                                findSelectNode(dsResponse);
                            } else {
                                SC.say(dsResponse.getErrors().get("errorMsg").toString());
                            }
                        }
                    });
                }

            }
        });

        form.setItems(departmentSubjectItem);

        vLayout.addMember(form);
        vLayout.addMember(subjectTypeGrid);

//        hLayout.addMember(subjectTypeGrid);
        hLayout.addMember(vLayout);

        addMember(hLayout);

        subjectTypeGrid.addCellClickHandler(new CellClickHandler() {
            @Override
            public void onCellClick(CellClickEvent event) {
                if (subjectTypeId == 0) {
                    SC.say("提示", "未指定报销科目,请选择一条报销科目");
                    return;
                } else {
                    TreeNode currentNode = subjectTypeGrid.getRecord(event.getRowNum());
                    boolean hasSelected = BaseHelpUtils.getBoolean(currentNode.getAttribute("hasSelected"));
                    onDepartmentGridClick(currentNode, hasSelected);
                }
            }
        });

//        processTypeGrid.addCellClickHandler(new CellClickHandler() {
//			@Override
//			public void onCellClick(CellClickEvent event) {
//				if(subjectTypeId == 0){
//            		SC.say("提示","未指定报销科目,请选择一条报销科目");
//            		return;
//            	}else{
//            		TreeNode currentNode = processTypeGrid.getRecord(event.getRowNum());
//            		boolean hasSelected = BaseHelpUtils.getBoolean(currentNode.getAttribute("hasSelected"));
//            		onProcessTypeGridClick(currentNode,hasSelected);
//            	}
//			}
//		});

    }

    /**
     * 递归取消节点
     *
     * @param tree
     * @param node
     * @param param
     */
    public void cancelSubjectTypeLinkProcessType(Tree tree, TreeNode node, Map<Integer, Integer> param) {
        //获取所有子级节点
        TreeNode[] childNodes = tree.getChildren(node);
        if (childNodes.length > 0) {
            for (TreeNode childNode : childNodes) {
                //获取是否选中的状态值
                boolean hasSelected = BaseHelpUtils.getBoolean(childNode.getAttribute("hasSelected"));
                if (hasSelected) {//如果是选中，则需取消
                    childNode.setAttribute("hasSelected", Boolean.FALSE);
//    				processTypeGrid.updateData(childNode);
                    param.put(BaseHelpUtils.getIntValue(childNode.getAttribute("processTypeId")), subjectTypeId);
                    cancelSubjectTypeLinkProcessType(tree, childNode, param);
                }
            }
        }
    }

    /**
     * 递归选中节点
     *
     * @param tree
     * @param node
     * @param param
     */
    public void selectSubjectTypeLinkProcessType(Tree tree, TreeNode node, Map<Integer, Integer> param) {
        //获取所有子级节点
        TreeNode[] childNodes = tree.getChildren(node);
        if (childNodes.length > 0) {
            for (TreeNode childNode : childNodes) {
                //获取是否选中的状态值
                boolean hasSelected = BaseHelpUtils.getBoolean(childNode.getAttribute("hasSelected"));
                if (!hasSelected) {//如果是没有选中，则需选中
                    childNode.setAttribute("hasSelected", Boolean.TRUE);
//    				processTypeGrid.updateData(childNode);
                    param.put(BaseHelpUtils.getIntValue(childNode.getAttribute("processTypeId")), subjectTypeId);
                    selectSubjectTypeLinkProcessType(tree, childNode, param);
                }
            }
        }
    }

    /**
     * 递归取消节点
     *
     * @param tree
     * @param node
     * @param param
     */
    public void cancelSubjectTypeLinkDepartment(Tree tree, TreeNode node, Map<Integer, Integer> param) {
        //获取所有子级节点
        TreeNode[] childNodes = tree.getChildren(node);
        if (childNodes.length > 0) {
            for (TreeNode childNode : childNodes) {
                //获取是否选中的状态值
                boolean hasSelected = BaseHelpUtils.getBoolean(childNode.getAttribute("hasSelected"));
                if (hasSelected) {//如果是选中，则需取消
                    childNode.setAttribute("hasSelected", Boolean.FALSE);
                    subjectTypeGrid.updateData(childNode);
                    param.put(BaseHelpUtils.getIntValue(childNode.getAttribute("departmentId")), subjectTypeId);
                    cancelSubjectTypeLinkDepartment(tree, childNode, param);
                }
            }
        }
    }

    //部门操作
    public void onDepartmentGridClick(TreeNode currentNode, boolean hasSelected) {
        SC.debugger();
        Tree tree = subjectTypeGrid.getData();
        if (!hasSelected) {//选中操作
            //定义一个map来存放选中的id,key为流程列表的id，value为科目id
            Map<Integer, Integer> param = new HashMap<>();
            currentNode.setAttribute("hasSelected", Boolean.TRUE);
            subjectTypeGrid.updateData(currentNode);
            param.put(BaseHelpUtils.getIntValue(currentNode.getAttribute("subjectTypeId")), subjectTypeId);
            //检索父级,如果父级有存在未选中的，则选中
            TreeNode[] parentNodes = tree.getParents(currentNode);
            if (parentNodes.length > 0) {
                for (TreeNode parentNode : parentNodes) {
                    //获取当前节点是否选中
                    boolean hasSelectedParent = BaseHelpUtils.getBoolean(parentNode.getAttribute("hasSelected"));
                    if (!hasSelectedParent) {//如果没有选中，则选中更新
                        parentNode.setAttribute("hasSelected", Boolean.TRUE);
                        subjectTypeGrid.updateData(parentNode);
                        param.put(BaseHelpUtils.getIntValue(parentNode.getAttribute("subjectTypeId")), subjectTypeId);
                    }
                }
            }
            selectSubjectTypeLinkDepartment(tree, currentNode,param);
        } else {//取消操作
            //定义一个map来存放选中的id,key为流程列表的id，value为科目id
            Map<Integer, Integer> param = new HashMap<>();
            currentNode.setAttribute("hasSelected", Boolean.FALSE);
            subjectTypeGrid.updateData(currentNode);
            param.put(BaseHelpUtils.getIntValue(currentNode.getAttribute("subjectTypeId")), subjectTypeId);
            //检索父级，并判断父级的子级是否有选中，如果选中，则不需做任何操作，如果子级没有选中的，则取消，并向上依次类推
            boolean canForeach = true;
            TreeNode node = currentNode;
            while (canForeach) {
                //获取当前节点是否是跟节点，如果是跟节点，则跳出
                int parentId = BaseHelpUtils.getIntValue(node.getAttribute("parentId"));
                if (parentId == 0) {//当前节点是根节点，则不需做任何操作
                    canForeach = false;
                } else {//当前节点不是根节点，则需判断父级节点是否有选中子级，如果没有选中的子级，则需取消父级节点
                    TreeNode parentNode = tree.getParent(node);
                    //检索父级节点的其它子级节点
                    TreeNode[] childNodesOfParentNode = tree.getChildren(parentNode);
                    if (childNodesOfParentNode.length > 0) {
                        //初始化该父级下的子级没有选中的
                        boolean childHasSelected = false;
                        for (TreeNode childNodeOfParentNode : childNodesOfParentNode) {
                            if (BaseHelpUtils.getBoolean(childNodeOfParentNode.getAttribute("hasSelected"))) {
                                canForeach = false;
                                childHasSelected = true;
                                break;
                            }
                        }
                        if (!childHasSelected) {//表示没有选中的
                            parentNode.setAttribute("hasSelected", Boolean.FALSE);
                            subjectTypeGrid.updateData(parentNode);
                            param.put(BaseHelpUtils.getIntValue(parentNode.getAttribute("subjectTypeId")), subjectTypeId);
                            node = parentNode;
                        }
                    }
                }
            }
            cancelSubjectTypeLinkDepartment(tree, currentNode,param);
        }
        subjectTypeGrid.redraw();
    }


    /**
     * 递归选中节点
     *
     * @param tree
     * @param node
     * @param param
     */
    public void selectSubjectTypeLinkDepartment(Tree tree, TreeNode node, Map<Integer, Integer> param) {
        //获取所有子级节点
        TreeNode[] childNodes = tree.getChildren(node);
        if (childNodes.length > 0) {
            for (TreeNode childNode : childNodes) {
                //获取是否选中的状态值
                boolean hasSelected = BaseHelpUtils.getBoolean(childNode.getAttribute("hasSelected"));
                if (!hasSelected) {//如果是没有选中，则需选中
                    childNode.setAttribute("hasSelected", Boolean.TRUE);
                    subjectTypeGrid.updateData(childNode);
                    param.put(BaseHelpUtils.getIntValue(childNode.getAttribute("departmentId")), subjectTypeId);
                    selectSubjectTypeLinkDepartment(tree, childNode, param);
                }
            }
        }
    }

    @Override
    public void startEdit() {
        onLoadData();
    }

    //搜索方法
    private void onLoadData() {
        //加载报销科目信息
        loadSubjectType();
        loadDepartmentSubjectType();
    }

    //加载业态表信息
    public void loadSubjectType() {
        SC.debugger();
        String operationName;
        DSSubjectType ds = DSSubjectType.getInstance();
        operationName = ds.getActionMode() + ds.getActionName();
        Map condition = new HashMap<>();
        DBDataSource.callOperation(operationName, "find", condition, new DSCallback() {
            @Override
            public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
                if (dsResponse.getStatus() >= 0) {
                    fetchDataCallback(dsResponse, data);
                } else {
                    SC.say(dsResponse.getErrors().get("errorMsg").toString());
                }
            }
        });
    }


    //加载部门科目类型
    public void loadDepartmentSubjectType(){
        SC.debugger();
        Map<String,Object> provinceMap = new HashMap<>();
        provinceMap.put("isDelete", false);
        DBDataSource.callOperation("ST_DepartmentSubjectType","find", provinceMap, new DSCallback() {
            @Override
            public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
                if(dsResponse.getStatus() >= 0){
                    Record[] subjectRecords = dsResponse.getData();
                    LinkedHashMap<String, String> subjectMap = new LinkedHashMap<>();
                    for(Record e : subjectRecords){
                        subjectMap.put(e.getAttribute("id"), e.getAttribute("name"));
                    }
                    departmentSubjectItem.setValueMap(subjectMap);
                }
            }
        });
    }


    public void fetchDataCallback(DSResponse response, Object rawData) {
        SC.debugger();
        int len = response.getData().length;
        TreeNode[] nodes = new TreeNode[len];
        for (int i = 0; i < len; i++) {
            nodes[i] = new TreeNode();
            DBDataSource.copyRecord(response.getData()[i], nodes[i]);
        }
        Tree tree = new Tree();
        tree.setModelType(TreeModelType.PARENT);
        tree.setRootValue("0");
        tree.setIdField("subjectTypeId");
        tree.setParentIdField("parentId");
        tree.setData(nodes);
        subjectTypeGrid.setData(tree);
        subjectTypeGrid.selectSingleRecord(0);
        TreeNode selectedNode = subjectTypeGrid.getSelectedRecord();
        if (selectedNode != null) {
            subjectTypeGrid.openFolder(selectedNode);
            int subjectTypeIdValue = BaseHelpUtils.getIntValue(selectedNode.getAttribute("subjectTypeId"));
            setSubjectTypeId(subjectTypeIdValue);
        }

        String operationName;
        Map condition = new HashMap<>();
        DSSubjectDepartment ds = DSSubjectDepartment.getInstance();
        operationName = ds.getActionMode() + ds.getActionName();
        condition.put("departmentId", getRecord().getAttribute("departmentId"));
        if(!BaseHelpUtils.isNullOrEmpty(condition.get("departmentId"))){
            DBDataSource.callOperation(operationName, "find", condition, new DSCallback() {
                @Override
                public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
                    if (dsResponse.getStatus() >= 0) {
                        findSelectNode(dsResponse);
                    } else {
                        SC.say(dsResponse.getErrors().get("errorMsg").toString());
                    }
                }
            });
        }
    }

    public void clearNode() {
        TreeNode[] nodeall =  subjectTypeGrid.getData().getAllNodes();
        for (int i = 0; i < nodeall.length; i++) {
            nodeall[i].setAttribute("hasSelected", Boolean.FALSE);
        }
        subjectTypeGrid.redraw();
    }


    public void findSelectNode(DSResponse response) {
        SC.debugger();
        int len = response.getData().length;
        TreeNode[] nodes = new TreeNode[len];
        for (int i = 0; i < len; i++) {
            nodes[i] = new TreeNode();
            DBDataSource.copyRecord(response.getData()[i], nodes[i]);
        }
        TreeNode[] nodeall = subjectTypeGrid.getData().getAllNodes();
        for (int i = 0; i < nodeall.length; i++) {
            for (int k = 0; k < nodes.length; k++) {
                if (BaseHelpUtils.getIntValue(nodeall[i].getAttribute("subjectTypeId"))==BaseHelpUtils.getIntValue(nodes[k].getAttribute("subjectTypeId"))){
                    nodeall[i].setAttribute("hasSelected", Boolean.TRUE);
                    break;
                }
            }
        }
        subjectTypeGrid.redraw();
    }


    @Override
    public void setValueManage(ValuesManager manager) {
        manager.setDataSource(DSCompanyReportIntegralDataSource.getInstance());
        manager.addMember(__form);
    }

    @Override
    public boolean checkData() {
        return true;
    }

    @Override
    public Map getValuesAsMap() {
        SC.debugger();
        Map param = __form.getValues();
//        ListGridRecord[] rows = subjectTypeGrid.getRecords();
        TreeNode[] nodeall = subjectTypeGrid.getData().getAllNodes();
        TreeNode[] selectrows = new TreeNode[nodeall.length];
        int s = 0;
        for (int i = 0; i < nodeall.length; i++) {
            boolean iss = BaseHelpUtils.getBoolean(nodeall[i].getAttribute("hasSelected"));
            if (iss) {
                selectrows[s++] = nodeall[i];
            }
        }
        param.put("detailSubjectDepartment",selectrows);
        return param;
    }

    public String getActionName() {
        // 需要根据实际业务修改
        return "ST_SubjectType";
    }

    public int subjectTypeId;

    public int getSubjectTypeId() {
        return subjectTypeId;
    }

    public void setSubjectTypeId(int subjectTypeId) {
        this.subjectTypeId = subjectTypeId;
    }

}
