package com.delicacy.client.app.panel;

import com.delicacy.client.PanelFactory;
import com.delicacy.client.data.KeyValueManager;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.Browser;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 *
 * @author lxf
 */
public class DepartmentRoleFunPanel extends VLayout {

    public static CustomRolePanel role ;
    public static CustomDepartmentRolePanel departmentRole ;
    public static CustomFunctionPanel function;
    public static Label roleTitle;
    public static Label departmentRoleTitle;
    public static Label functionTitle;

    public DepartmentRoleFunPanel() {
    	role = new CustomRolePanel();
    	departmentRole = new CustomDepartmentRolePanel();
    	function = new CustomFunctionPanel();
    	roleTitle = new Label("角色信息");
    	departmentRoleTitle = new Label("部门下角色信息");
    	functionTitle = new Label("功能信息");
        setWidth100();
        setHeight100();
        setMembersMargin(5);

        //部门树形gird
        VLayout departmentPanel = new VLayout(5);
        Label departmentTitle = new Label("部门信息");
        departmentTitle.setHeight(50);
        departmentTitle.setAlign(Alignment.CENTER);
        departmentPanel.addMember(departmentTitle);
        departmentPanel.addMember(new CustomDepartmentPanel());

        //角色源
        VLayout rolePanel = new VLayout(5);
        roleTitle.setHeight(50);
        roleTitle.setAlign(Alignment.CENTER);
        rolePanel.addMember(roleTitle);
        role.setDepartmentId(1);
        role.startEdit();
        rolePanel.addMember(role);
        
        //部门下角色
        VLayout departmentRolePanel = new VLayout(5);
        departmentRoleTitle.setHeight(50);
        departmentRoleTitle.setAlign(Alignment.CENTER);
        departmentRolePanel.addMember(departmentRoleTitle);
        //默认加载部门父节点（深圳市杰恩创意设计）的角色信息
        departmentRole.setDepartmentId(1);
        //获取部门名称
        String departmentName = KeyValueManager.getValue("departments","1");
        departmentRoleTitle.setContents(departmentName+"的角色信息");
        departmentRole.startEdit();
        departmentRolePanel.addMember(departmentRole);

        //功能树形gird
        VLayout functionPanel = new VLayout(5);
        functionTitle.setHeight(50);
        functionTitle.setAlign(Alignment.CENTER);
        functionPanel.addMember(functionTitle);
        function.setDepartmentId(-1);
        function.setRoleId(-1);
        function.startEdit();
        functionPanel.addMember(function);

        if (Browser.getIsDesktop()) {
            HLayout departmentRoleFunctionLayout = new HLayout(10);
            departmentRoleFunctionLayout.setWidth100();
            departmentRoleFunctionLayout.setHeight100();
            departmentPanel.setWidth("50%");
            departmentPanel.setHeight100();
            departmentRoleFunctionLayout.addMember(departmentPanel);

            VLayout roleFunctionPanel = new VLayout(10);
            roleFunctionPanel.setWidth("50%");
            roleFunctionPanel.setHeight100();
            
            HLayout departmentRoleLayout = new HLayout(5);
            rolePanel.setWidth("50%");
            rolePanel.setHeight100();
            departmentRoleLayout.addMember(rolePanel);
            departmentRolePanel.setWidth("50%");
            departmentRolePanel.setHeight100();
            departmentRoleLayout.addMember(departmentRolePanel);
            
            roleFunctionPanel.addMember(departmentRoleLayout);
            functionPanel.setHeight("50%");
            functionPanel.setWidth100();
            roleFunctionPanel.addMember(functionPanel);

            departmentRoleFunctionLayout.addMember(roleFunctionPanel);
            addMember(departmentRoleFunctionLayout);
        } else {
            departmentPanel.setWidth100();
            departmentPanel.setHeight("25%");
            addMember(departmentPanel);
            rolePanel.setWidth100();
            rolePanel.setHeight("25%");
            addMember(rolePanel);
            departmentRolePanel.setWidth100();
            departmentRolePanel.setHeight("25%");
            addMember(departmentRolePanel);
            functionPanel.setWidth100();
            functionPanel.setHeight("25%");
            addMember(functionPanel);
        }
    }

    public static class Factory implements PanelFactory {

        private String id;

        @Override
        public Canvas create() {
            DepartmentRoleFunPanel cm = new DepartmentRoleFunPanel();
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
}
