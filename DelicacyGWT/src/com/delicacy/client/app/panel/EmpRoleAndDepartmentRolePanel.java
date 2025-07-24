package com.delicacy.client.app.panel;

import java.util.logging.Logger;

import com.delicacy.client.PanelFactory;
import com.delicacy.client.management.form.EmpAddDepartmentRolePanel;
import com.delicacy.client.management.form.EmpAddRolePanel;
import com.delicacy.client.management.form.MyRolePanel;
import com.delicacy.client.management.form.MyDepartmentRolePanel;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 *
 * @author lsf
 */
public class EmpRoleAndDepartmentRolePanel extends VLayout {
	private static final Logger __logger = Logger.getLogger("");
	public static  EmpAddRolePanel empAddRolePanel;
	public static  EmpAddDepartmentRolePanel empAddDepartmentRolePanel;
	public static  MyRolePanel myRoleDataPanel;
	public static  MyDepartmentRolePanel myDepartmentRoleDataPanel;
	private  Label roleTitle;
	private  Label departmentRoleTitle;
	private  Label MyRoleTitle;
	private  Label MyDepartmentRoleTitle;
	
    private  VLayout mainPanel;
    private  HLayout topPanel;
    private  HLayout bottomPanel;
    private  VLayout rolePanel;
    private  VLayout myRolePanel;
    private  VLayout departmentRolePanel;
    private  VLayout myDepartmentRolePanel;
    
	public EmpRoleAndDepartmentRolePanel(int employeeId,String employeeName) {
		mainPanel = new VLayout();
		mainPanel.setHeight100();
		mainPanel.setWidth100();
		
		topPanel = new HLayout();
		topPanel.setWidth100();
		topPanel.setHeight("50%");
		
		rolePanel = new VLayout();
		rolePanel.setWidth("50%");
		
		roleTitle = new Label("角色信息");
		roleTitle.setWidth100();
		roleTitle.setHeight(50);
		roleTitle.setAlign(Alignment.CENTER);
		rolePanel.addMember(roleTitle);
		empAddRolePanel = new EmpAddRolePanel(employeeId);
		empAddRolePanel.setWidth100();
		empAddRolePanel.startEdit();
		rolePanel.addMember(empAddRolePanel);
		topPanel.addMember(rolePanel);
		
		myRolePanel = new VLayout();
		myRolePanel.setWidth("50%");
		myRolePanel.setLayoutTopMargin(50);
		MyRoleTitle = new Label(employeeName+"的角色信息");
		MyRoleTitle.setWidth100();
		MyRoleTitle.setHeight(50);
		MyRoleTitle.setAlign(Alignment.CENTER);
		myRolePanel.addMember(MyRoleTitle);
		
		myRoleDataPanel = new MyRolePanel(employeeId);
		myRoleDataPanel.setWidth100();
		myRoleDataPanel.startEdit();
		myRolePanel.addMember(myRoleDataPanel);
		topPanel.addMember(myRolePanel);
		
		mainPanel.addMember(topPanel);
		
		
		
		bottomPanel = new HLayout();
		bottomPanel.setWidth100();
		bottomPanel.setHeight("50%");
		
		
		departmentRolePanel = new VLayout();
		departmentRolePanel.setWidth("50%");
		
		departmentRoleTitle =new Label("部门角色信息");
		departmentRoleTitle.setWidth100();
		departmentRoleTitle.setHeight(50);
		departmentRoleTitle.setAlign(Alignment.CENTER);
		departmentRolePanel.addMember(departmentRoleTitle);
		empAddDepartmentRolePanel = new EmpAddDepartmentRolePanel(employeeId);
		empAddDepartmentRolePanel.setWidth100();
		empAddDepartmentRolePanel.startEdit();
		departmentRolePanel.addMember(empAddDepartmentRolePanel);
		bottomPanel.addMember(departmentRolePanel);
		
		myDepartmentRolePanel = new VLayout();
		myDepartmentRolePanel.setWidth("50%");
		myDepartmentRolePanel.setLayoutTopMargin(50);
		MyDepartmentRoleTitle = new Label(employeeName+"的部门角色信息");
		MyDepartmentRoleTitle.setWidth100();
		MyDepartmentRoleTitle.setHeight(50);
		MyDepartmentRoleTitle.setAlign(Alignment.CENTER);
		myDepartmentRolePanel.addMember(MyDepartmentRoleTitle);
		
		myDepartmentRoleDataPanel = new MyDepartmentRolePanel(employeeId);
		myDepartmentRoleDataPanel.setWidth100();
		myDepartmentRoleDataPanel.startEdit();
		myDepartmentRolePanel.addMember(myDepartmentRoleDataPanel);
		bottomPanel.addMember(myDepartmentRolePanel);
		
		mainPanel.addMember(bottomPanel);
		
		
		addMember(mainPanel);
		
	}

    public static class Factory implements PanelFactory {

        private String id;

        @Override
        public Canvas create() {
        	EmpRoleAndDepartmentRolePanel cm = new EmpRoleAndDepartmentRolePanel(0,null);
            id = cm.getID();
            return cm;
        }

        @Override
        public String getID() {
            return id;
        }

        @Override
        public String getDescription() {
            return "RoleAndDepartmentRole";
        }

    }
}
