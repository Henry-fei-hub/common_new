package com.delicacy.client.app.panel;

import com.delicacy.client.PanelFactory;
import com.delicacy.client.app.datasource.DSDepartment;
import com.delicacy.client.app.datasource.DSDepartmentPart;
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
import java.util.logging.Logger;

public class CustomDepartmentPartPanel extends AbstractSearchTree {
    private final Logger __logger = Logger.getLogger("");
    public static class Factory implements PanelFactory {

        private String id;

        @Override
        public Canvas create() {
            CustomDepartmentPartPanel cm = new CustomDepartmentPartPanel();
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
                EmpDepartmentRolePanel.roleTitle.setContents(departmentName+"的角色信息");
                EmpDepartmentRolePanel.role.setDepartmentId(departmentId);
                EmpDepartmentRolePanel.role.startEdit();
                EmpDepartmentRolePanel.existRoleTitle.setContents(departmentName+"下-我拥有的角色信息");
                EmpDepartmentRolePanel.existRole.setDepartmentId(departmentId);
                EmpDepartmentRolePanel.existRole.startEdit();
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
        return true;
    }

    @Override
    public SearchForm generateSearchForm() {
        return null;
    }

    @Override
    public DataSource getDataSource() {
        return DSDepartmentPart.getInstance();
    }

}
