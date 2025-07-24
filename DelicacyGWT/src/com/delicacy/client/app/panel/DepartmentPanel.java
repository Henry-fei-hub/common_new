package com.delicacy.client.app.panel;

import java.util.Map;
import java.util.logging.Logger;

import com.delicacy.client.BaseHelpUtils;
import com.delicacy.client.BasicPermissionStatic;
import com.delicacy.client.PanelFactory;
import com.delicacy.client.PermissionControl;
import com.delicacy.client.app.datasource.DSDepartment;
import com.delicacy.client.app.form.NewFunctionWithDepartmentForm;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.data.DBDataSource;
import com.delicacy.client.data.DataEditEvent;
import com.delicacy.client.data.DataEditedHandler;
import com.delicacy.client.department.form.NewDepartmentWithSdForm;
import com.delicacy.client.department.form.UpdateDepartmentWithSdForm;
import com.delicacy.client.ui.AbstractSearchTree;
import com.delicacy.client.ui.PopupWindow;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.DropCompleteEvent;
import com.smartgwt.client.widgets.events.DropCompleteHandler;
import com.smartgwt.client.widgets.form.SearchForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.EditCompleteEvent;
import com.smartgwt.client.widgets.grid.events.EditCompleteHandler;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGridField;
import com.smartgwt.client.widgets.tree.TreeNode;

public class DepartmentPanel extends AbstractSearchTree {
	private final Logger __logger = Logger.getLogger("");
    public static class Factory implements PanelFactory {

        private String id;

        @Override
        public Canvas create() {
            DepartmentPanel cm = new DepartmentPanel();
            id = cm.getID();
            return cm;
        }

        @Override
        public String getID() {
            return id;
        }

        @Override
        public String getDescription() {
            return "Department";
        }

    }

    @Override
    public void initComponents() {
        //行数据是否可编辑
        if(ClientUtil.checkIsHavePermission(BasicPermissionStatic.DEPRATMENT_UPDATE_BUTTON)){
            resultGrid.setCanEdit(true);
        }else{
            resultGrid.setCanEdit(false);
        }
        TreeGridField[] fields = new TreeGridField[8];
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
        fields[idx] = new TreeGridField("plateId");
        idx++;
        fields[idx] = new TreeGridField("isHeadcount");
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
                    DBDataSource.callOperation("ST_CustomDepartment", "update", r.toMap(), new DSCallback() {
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
                DBDataSource.callOperation("ST_CustomDepartment", "saveOrUpdate", params, new DSCallback() {
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

        IButton newButton = PermissionControl.createPermissionButton("新建", BasicPermissionStatic.DEPRATMENT_ADD_BUTTON);
        controlLayout.addMember(newButton);
        newButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                final Tree tree = resultGrid.getData();
                NewDepartmentWithSdForm detailForm = new NewDepartmentWithSdForm();
				detailForm.setTitle("新建");
				detailForm.addDataEditedHandler(new DataEditedHandler(){
					@Override
					public void onDataEdited(DataEditEvent event) {
						Record data = event.getData();
						TreeNode newNode = new TreeNode();
			            DBDataSource.copyRecord(data, newNode);
						newNode.setIsFolder(true);
		                tree.add(newNode, "/", 0);
		                resultGrid.openFolder(newNode);
		                resultGrid.selectSingleRecord(newNode);
					}
				});
				Record record = new Record();
				record.setAttribute("parentId", 0);
				detailForm.setRecord(record);
				detailForm.setSearchForm(searchForm);
				detailForm.initComponents();
				detailForm.startEdit();
				detailForm.setWidth100();
				detailForm.setHeight100();
				detailForm.centerInPage();
				detailForm.show();
            }
        });

        IButton newChildButton = PermissionControl.createPermissionButton("新建子项", BasicPermissionStatic.DEPRATMENT_ADD_SON_BUTTON);
        controlLayout.addMember(newChildButton);
        newChildButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                final TreeNode currentNode = resultGrid.getSelectedRecord();
                if (currentNode == null) {
                    SC.say("Please select a item as parent.");
                    return;
                }
                final Tree tree = resultGrid.getData();
            	NewDepartmentWithSdForm detailForm = new NewDepartmentWithSdForm();
				detailForm.setTitle("新建");
				detailForm.addDataEditedHandler(new DataEditedHandler(){
					@Override
					public void onDataEdited(DataEditEvent event) {
						Record data = event.getData();
						TreeNode newNode = new TreeNode();
			            DBDataSource.copyRecord(data, newNode);
		                tree.add(newNode, currentNode);
		                resultGrid.openFolder(currentNode);
		                resultGrid.selectSingleRecord(newNode);
					}
				});
				Record record = new Record();
				record.setAttribute("parentId", currentNode.getAttribute("departmentId"));
				detailForm.setRecord(record);
				detailForm.setSearchForm(searchForm);
				detailForm.initComponents();
				detailForm.startEdit();
				detailForm.setWidth100();
				detailForm.setHeight100();
				detailForm.centerInPage();
				detailForm.show();
            }
        });
        
        IButton updateButton = PermissionControl.createPermissionButton("修改", BasicPermissionStatic.DEPRATMENT_UPDATE_BUTTON);
        controlLayout.addMember(updateButton);
        updateButton.addClickHandler(new ClickHandler() {
        	@Override
        	public void onClick(ClickEvent event) {
        		final Record selected1 = resultGrid.getSelectedRecord();
        		UpdateDepartmentWithSdForm detailForm = new UpdateDepartmentWithSdForm();
        		detailForm.setTitle("修改");
        		detailForm.addDataEditedHandler(new DataEditedHandler(){
        			@Override
        			public void onDataEdited(DataEditEvent event) {
        				TreeNode currentNode = resultGrid.getSelectedRecord();
			            DBDataSource.copyRecord(event.getData(), currentNode);
			            resultGrid.redraw();
        				resultGrid.selectSingleRecord(event.getData());
        				
        			}
        		});
        		detailForm.setSearchForm(searchForm);
        		detailForm.setRecord(selected1);
        		detailForm.setCurrentPage(getCurrentPage());
        		detailForm.initComponents();
        		detailForm.startEdit();
        		detailForm.setWidth100();
        		detailForm.setHeight100();
        		detailForm.centerInPage();
        		detailForm.show();
        		setCurrentPage(detailForm.getCurrentPage());
        	}
        });
        IButton removeButton = PermissionControl.createPermissionButton("删除", BasicPermissionStatic.DEPRATMENT_DELETE_BUTTON);
        controlLayout.addMember(removeButton);
        removeButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (!resultGrid.anySelected()) {
                    SC.say("Please select a data to remove.");
                }
                final ListGridRecord selected = resultGrid.getSelectedRecord();
                //获取部门id，只有存在部门id才可做删除，否则刷新
                int departmentId = BaseHelpUtils.getIntValue(selected.getAttribute("departmentId"));
                if(departmentId > 0){
                	DBDataSource.callOperation("ST_CustomDepartment", "delete", selected.toMap(), new DSCallback() {
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
                }else{
                	commonQuery();
                }
            }
        });
        
        IButton resetButton = PermissionControl.createPermissionButton("成员转移", BasicPermissionStatic.DEPRATMENT_CHANGE_BUTTON);
        controlLayout.addMember(resetButton);
        resetButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (!resultGrid.anySelected()) {
                    SC.say("Please select a data");
                }
                final ListGridRecord selected = resultGrid.getSelectedRecord();
                //获取部门id，只有存在部门id才可做删除，否则刷新
                int departmentId = BaseHelpUtils.getIntValue(selected.getAttribute("departmentId"));
                if(departmentId > 0){
                	PopupWindow pw = new PopupWindow();
                	DepartmentDesignPanel panel = new DepartmentDesignPanel();
                	panel.setOldDepartmentId(departmentId);
                	panel.setParentWindow(pw);
    				pw.addItem(panel);
    				panel.initComponents();
    				pw.setTitle("部门下成员转移");
    				pw.setWidth("60%");
    				pw.setHeight("60%");
    				pw.centerInPage();
    				pw.show();
                }else{
                	SC.say("提示","请选择要转移人员的部门");
                }
            }
        });
        
        IButton operation4Button = PermissionControl.createPermissionButton("部门权限编辑", BasicPermissionStatic.DEPARTMENT_FUNCTION_BUTTION);
        controlLayout.addMember(operation4Button);
        operation4Button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                GWT.runAsync(new RunAsyncCallback() {
                    @Override
                    public void onFailure(Throwable reason) {
                        SC.say("failure to download");
                    }

                    @Override
                    public void onSuccess() {
                    	NewFunctionWithDepartmentForm detailForm = new NewFunctionWithDepartmentForm();
                        detailForm.setTitle("部门权限编辑");
                        detailForm.setSearchForm(searchForm);
                        detailForm.initComponents();
                        detailForm.ss();
                        detailForm.startEdit();
                        detailForm.setWidth("60%");
                        detailForm.setHeight("80%");
                        detailForm.centerInPage();
                        detailForm.show();
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
        criteria.addCriteria("deleteFlag", 0);
        return true;
    }

    @Override
    public SearchForm generateSearchForm() {
        return null;
    }

    @Override
    public DataSource getDataSource() {
        return DSDepartment.getInstance();
    }

}
