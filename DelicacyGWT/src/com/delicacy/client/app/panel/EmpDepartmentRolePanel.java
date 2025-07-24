package com.delicacy.client.app.panel;

import java.util.logging.Logger;

import com.delicacy.client.PanelFactory;
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
public class EmpDepartmentRolePanel extends VLayout {
	private static final Logger __logger = Logger.getLogger("");
    public static CustomRolePartPanel role;
    public static CustomExistRolePartPanel existRole;
    public static CustomEmpDefaultRolePanel defaultRole;//设置默认角色
    public static Label roleTitle;
    public static Label existRoleTitle;
    public static Label defaultRoleTitle;
    
	public EmpDepartmentRolePanel(int employeeId) {
		roleTitle = new Label("角色信息");
		existRoleTitle = new Label("深圳杰恩创意设计下-我拥有的角色信息");
		defaultRoleTitle = new Label("默认角色信息");
		role = new CustomRolePartPanel();
		existRole = new CustomExistRolePartPanel();
		defaultRole = new CustomEmpDefaultRolePanel();
        setHeight100();
        setMembersMargin(5);

        //部门树形gird
        VLayout departmentPanel = new VLayout(5);
        Label departmentTitle = new Label("部门信息");
        departmentTitle.setHeight(50);
        departmentTitle.setAlign(Alignment.CENTER);
        departmentPanel.addMember(departmentTitle);
        departmentPanel.addMember(new CustomDepartmentPartPanel());

        //角色grid
        VLayout rolePanel = new VLayout(5);
        rolePanel.setHeight("40%");
        roleTitle.setHeight(50);
        roleTitle.setAlign(Alignment.CENTER);
        rolePanel.addMember(roleTitle);
        role.setDepartmentId(-1);
        role.setEmployeeId(employeeId);
        role.startEdit();
        rolePanel.addMember(role);
        //拥有的角色面板
        VLayout existRolePanel = new VLayout(5);
        existRolePanel.setHeight("40%");
        existRoleTitle.setHeight(50);
        existRoleTitle.setAlign(Alignment.CENTER);
        existRolePanel.addMember(existRoleTitle);
        existRole.setDepartmentId(1);
        existRole.setEmployeeId(employeeId);
        existRole.startEdit();
        existRolePanel.addMember(existRole);
        //默认角色面板
        VLayout defaultRolePanel = new VLayout(5);
        defaultRolePanel.setHeight("20%");
        defaultRoleTitle.setHeight(50);
        defaultRoleTitle.setAlign(Alignment.CENTER);
        defaultRolePanel.addMember(defaultRoleTitle);
        defaultRole.setDepartmentId(1);
        defaultRole.setEmployeeId(employeeId);
        defaultRole.startEdit();
        defaultRolePanel.addMember(defaultRole);
        
        if (Browser.getIsDesktop()) {
            HLayout departmentRoleFunctionPanel = new HLayout(10);
            departmentRoleFunctionPanel.setWidth100();
            departmentRoleFunctionPanel.setHeight100();
            departmentPanel.setWidth("50%");
            departmentPanel.setHeight100();
            
            VLayout roleAndExistPanel = new VLayout();
            roleAndExistPanel.setWidth("50%");
            roleAndExistPanel.setHeight100();
            roleAndExistPanel.addMember(rolePanel);
            roleAndExistPanel.addMember(existRolePanel);
            roleAndExistPanel.addMember(defaultRolePanel);
            
            departmentRoleFunctionPanel.addMember(departmentPanel);
            departmentRoleFunctionPanel.addMember(roleAndExistPanel);
            
            addMember(departmentRoleFunctionPanel);
        } else {
            departmentPanel.setWidth100();
            departmentPanel.setHeight("30%");
            addMember(departmentPanel);
            
            rolePanel.setWidth100();
            rolePanel.setHeight("30%");
            addMember(rolePanel);
            
            existRolePanel.setWidth100();
            existRolePanel.setHeight("30%");
            addMember(existRolePanel);
            
            defaultRolePanel.setWidth100();
            defaultRolePanel.setHeight("10%");
            addMember(defaultRolePanel);
        }
    }

    public static class Factory implements PanelFactory {

        private String id;

        @Override
        public Canvas create() {
        	EmpDepartmentRolePanel cm = new EmpDepartmentRolePanel(0);
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
