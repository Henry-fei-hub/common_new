package com.delicacy.client.app.panel;

import com.delicacy.client.BaseHelpUtils;
import com.delicacy.client.BasicPermissionStatic;
import com.smartgwt.client.widgets.*;
import com.smartgwt.client.widgets.events.*;
import com.smartgwt.client.util.SC;
import com.delicacy.client.data.DataEditEvent;
import com.delicacy.client.data.DataEditedHandler;
import com.delicacy.client.PanelFactory;
import com.delicacy.client.PermissionControl;
import com.delicacy.client.ui.GenericViewWindow;
import com.delicacy.client.ui.AbstractDetailViewer;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.delicacy.client.app.datasource.DSSfunctionffaor;
import com.delicacy.client.app.form.SfunctionffaorViewer;
import com.delicacy.client.app.form.UpdateSfunctionffaorForm;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.data.DBDataSource;
import com.delicacy.client.ui.AbstractSearchTree;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.smartgwt.client.data.*;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.widgets.form.SearchForm;
import com.smartgwt.client.widgets.grid.events.EditCompleteEvent;
import com.smartgwt.client.widgets.grid.events.EditCompleteHandler;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGridField;
import com.smartgwt.client.widgets.tree.TreeNode;
import java.util.Map;

public class SfunctionffaorPanel extends AbstractSearchTree {

    public static class Factory implements PanelFactory {

        private String id;

        @Override
        public Canvas create() {
            SfunctionffaorPanel cm = new SfunctionffaorPanel();
            id = cm.getID();
            return cm;
        }

        @Override
        public String getID() {
            return id;
        }

        @Override
        public String getDescription() {
            return "Sfunctionffaor";
        }

    }

    @Override
    public void initComponents() {

        TreeGridField nameField = new TreeGridField("functionName");
        nameField.setFrozen(true);
        nameField.setWidth(300);
        TreeGridField functionIdField = new TreeGridField("functionId");
        functionIdField.setCanEdit(false);
        functionIdField.setHidden(true);
        TreeGridField parentIdField = new TreeGridField("parentId");
        parentIdField.setHidden(true);
        TreeGridField codeField = new TreeGridField("functionCode");
        TreeGridField applicationIdField = new TreeGridField("applicationId");
        TreeGridField functionTypeField = new TreeGridField("functionType");
        if(ClientUtil.checkIsHavePermission(BasicPermissionStatic.FUNCTIONS_UPDATE_BUTTON)){
             nameField.setCanEdit(true);
            codeField.setCanEdit(true);
            applicationIdField.setCanEdit(true);
            functionTypeField.setCanEdit(true);
        }else{
            codeField.setCanEdit(false);
            nameField.setCanEdit(false);
            applicationIdField.setCanEdit(false);
            functionTypeField.setCanEdit(false);
        }
        resultGrid.setFields(functionIdField, parentIdField, nameField, codeField, applicationIdField, functionTypeField);
        resultGrid.setShowFilterEditor(true);
        resultGrid.setFilterOnKeypress(true);

        resultGrid.addDropCompleteHandler(new DropCompleteHandler() {
            @Override
            public void onDropComplete(DropCompleteEvent event) {
                Record[] rs = event.getTransferredRecords();
                DBDataSource.printRecord(rs[0]);
                for (Record r : rs) {
                    DBDataSource.callOperation("ST_Function", "update", r.toMap(), new DSCallback() {
                        @Override
                        public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
                            if (dsResponse.getStatus() < 0) {
                                SC.say("failure. " + dsResponse.getErrors().get("errorMsg"));
                            }
                        }
                    });
                }
            }
        });

        resultGrid.addEditCompleteHandler(new EditCompleteHandler() {
            @Override
            public void onEditComplete(EditCompleteEvent event) {
                Map params = resultGrid.getRecord(event.getRowNum()).toMap();
                Object parentId = params.get("parentId");
                if (parentId == null) {
                    params.put("parentId", 0);
                }
                final int rowNumModified = event.getRowNum();
                DBDataSource.callOperation("ST_Function", "saveOrUpdate", params, new DSCallback() {
                    @Override
                    public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
                        if (dsResponse.getStatus() < 0) {
                            SC.say("failure. " + dsResponse.getErrors().get("errorMsg"));
                        } else {
                            DBDataSource.copyRecord(dsResponse.getData()[0], resultGrid.getRecord(rowNumModified));
                            resultGrid.redraw();
                        }
                    }

                });
            }

        });

        IButton refreshButton = new IButton("刷新");
        controlLayout.addMember(refreshButton);
        refreshButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                commonQuery();
            }
        });

        IButton newButton = PermissionControl.createPermissionButton("新建", BasicPermissionStatic.FUNCTIONS_ADD_BUTTON);
        controlLayout.addMember(newButton);
        newButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                Tree tree = resultGrid.getData();
                TreeNode node = new TreeNode();
                node.setIsFolder(true);
                tree.add(node, "/", 0);
                resultGrid.selectSingleRecord(0);
                resultGrid.startEditing(0);
            }
        });
        IButton newChildButton = PermissionControl.createPermissionButton("新建子项", BasicPermissionStatic.FUNCTIONS_ADD_SON_BUTTON);
        controlLayout.addMember(newChildButton);
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
                DBDataSource.copyRecordNotInclude(currentNode, newNode, "functionId", "children", "isFolder");
                newNode.setAttribute("parentId", ClientUtil.checkAndGetIntValue(currentNode.getAttribute("functionId")));
                DBDataSource.printRecord(newNode);
                newNode = tree.add(newNode, currentNode);
                resultGrid.openFolder(currentNode);
                resultGrid.selectSingleRecord(newNode);
                resultGrid.startEditing(resultGrid.getRowNum(newNode));
            }
        });
        IButton modifyButton = PermissionControl.createPermissionButton("修改", BasicPermissionStatic.FUNCTIONS_UPDATE_BUTTON);
        controlLayout.addMember(modifyButton);
        modifyButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                GWT.runAsync(new RunAsyncCallback() {
                    @Override
                    public void onFailure(Throwable reason) {
                        SC.say("failure to download code");
                    }

                    @Override
                    public void onSuccess() {
                        if (!resultGrid.anySelected()) {
                            SC.say("请选择一条数据修改");
                            return;
                        }
                        ListGridRecord[] selected = resultGrid.getSelectedRecords();
                        UpdateSfunctionffaorForm detailForm = new UpdateSfunctionffaorForm();
                        detailForm.setTitle("修改");
                        detailForm.addDataEditedHandler(new DataEditedHandler() {
                            @Override
                            public void onDataEdited(DataEditEvent event) {
                                TreeNode node = resultGrid.getSelectedRecord();
                                DBDataSource.copyRecord(event.getData(), node);
                                resultGrid.redraw();
                            }
                        });
                        detailForm.setSearchForm(searchForm);
                        detailForm.setRecord(selected[0]);
                        detailForm.initComponents();
                        detailForm.startEdit();
                        detailForm.setWidth100();
                        detailForm.setHeight100();
                        detailForm.centerInPage();
                        detailForm.show();
                    }
                });
            }
        });
        IButton removeButton = PermissionControl.createPermissionButton("删除", BasicPermissionStatic.FUNCTIONS_DELETE_BUTTON);
        controlLayout.addMember(removeButton);
        removeButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (!resultGrid.anySelected()) {
                    SC.say("请选择一条数据修改");
                    return;
                }
                final ListGridRecord selected = resultGrid.getSelectedRecord();
                if(BaseHelpUtils.isNullOrEmpty(selected.getAttribute("functionId"))){
                	SC.say("数据错误! 请刷新后重新再进行操作");
                	return;
                }
                DBDataSource.callOperation("ST_Function", "delete", selected.toMap(), new DSCallback() {
                    @Override
                    public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
                        if (dsResponse.getStatus() < 0) {
                            SC.say("failure. " + dsResponse.getErrors().get("errorMsg"));
                        } else {
                            int indexNum = resultGrid.getRowNum(selected);
                            resultGrid.removeData(selected);
                            resultGrid.selectSingleRecord(indexNum);
                        }
                    }

                });
            }
        });
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
        Tree functionTree = new Tree();
        functionTree.setModelType(TreeModelType.PARENT);
        functionTree.setRootValue(0);
        functionTree.setIdField("functionId");
        functionTree.setParentIdField("parentId");
        functionTree.setData(nodes);
        resultGrid.setData(functionTree);
        resultGrid.selectSingleRecord(0);
        TreeNode selectedNode = resultGrid.getSelectedRecord();
        resultGrid.openFolder(selectedNode);
    }

    public void showDetail() {
        GWT.runAsync(new RunAsyncCallback() {
            @Override
            public void onFailure(Throwable reason) {
                SC.say("failure to download code");
            }

            @Override
            public void onSuccess() {
                if (!resultGrid.anySelected()) {
                    SC.say("请选择一条数据");
                    return;
                }
                GenericViewWindow detail = new GenericViewWindow();
                detail.setTitle("Sfunctionffaor");
                detail.setWidth100();
                detail.setHeight100();
                SfunctionffaorViewer detailForm = new SfunctionffaorViewer();
                detailForm.setParentSearchForm(searchForm);
                detailForm.setLayoutMode(AbstractDetailViewer.COMPONENT_LAYOUT_TAB);
                detailForm.initComponents();
                detailForm.viewSelectedData(resultGrid);
                detail.setContent(detailForm);
                detail.centerInPage();
                detail.show();
            }
        });
    }

    @Override
    public SearchForm generateSearchForm() {
//		return new SfunctionffaorSearchForm();
        return null;
    }

    @Override
    public DataSource getDataSource() {
        return DSSfunctionffaor.getInstance();
    }

    @Override
    public boolean checkSearchCriteria(Criteria criteria) {
        criteria.addCriteria("addtionalCondition", "order by function_code");
        return true;
    }

}
