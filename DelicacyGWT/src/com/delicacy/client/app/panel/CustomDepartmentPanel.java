package com.delicacy.client.app.panel;

import com.delicacy.client.PanelFactory;
import com.delicacy.client.app.datasource.DSDepartment;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.data.DBDataSource;
import com.delicacy.client.ui.AbstractSearchTree;
import com.smartgwt.client.data.*;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.widgets.*;
import com.smartgwt.client.widgets.events.*;
import com.smartgwt.client.widgets.form.SearchForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGridField;
import com.smartgwt.client.widgets.tree.TreeNode;

import java.util.Map;
import java.util.logging.Logger;

public class CustomDepartmentPanel extends AbstractSearchTree {
    private final Logger __logger = Logger.getLogger("");
    public static class Factory implements PanelFactory {

        private String id;

        @Override
        public Canvas create() {
            CustomDepartmentPanel cm = new CustomDepartmentPanel();
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
        resultGrid.setCanEdit(false);
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

        IButton refreshButton = new IButton("刷新");
        refreshButton.setIcon("[SKIN]/actions/refresh.png");
        controlLayout.addMember(refreshButton);
        refreshButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                commonQuery();
            }
        });
        
        resultGrid.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                ListGridRecord[] selected = resultGrid.getSelectedRecords();
                int departmentId = ClientUtil.checkAndGetIntValue(selected[0].getAttribute("departmentId"));
                String departmentName = selected[0].getAttribute("departmentName");
                DepartmentRoleFunPanel.role.setDepartmentId(departmentId);
                DepartmentRoleFunPanel.departmentRoleTitle.setContents(departmentName+"的角色信息");
                DepartmentRoleFunPanel.departmentRole.setDepartmentId(departmentId);
                DepartmentRoleFunPanel.departmentRole.startEdit();
                //初始化功能列表
                DepartmentRoleFunPanel.functionTitle.setContents("功能信息");
                DepartmentRoleFunPanel.function.setRoleId(-1);
                DepartmentRoleFunPanel.function.startEdit();
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
	public boolean checkSearchCriteria(Map criteria) {
    	criteria.put("deleteFlag", 0);
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
