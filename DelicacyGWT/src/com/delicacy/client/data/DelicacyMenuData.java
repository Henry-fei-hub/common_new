package com.delicacy.client.data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;

import com.delicacy.client.BasicPermissionStatic;
import com.delicacy.client.app.panel.DepartmentPanel;
import com.delicacy.client.app.panel.PermissionManagePanel;
import com.delicacy.client.app.panel.ProcessTestThreadTaskPanel;
import com.delicacy.client.app.panel.RoleWithRPanel;
import com.delicacy.client.app.panel.SfunctionffaorPanel;
import com.delicacy.client.app.panel.SsystemprocessporPanel;
import com.delicacy.client.management.panel.MobileFunctionPanel;
import com.delicacy.client.ui.MenuAppender;
import com.google.gwt.i18n.client.LocaleInfo;

public class DelicacyMenuData {

    private static final Logger __logger = Logger.getLogger("");
    public static Map<String, ExplorerTreeNode> menuMap = new LinkedHashMap<>();
    public static Map<String, ExplorerTreeNode> generalMap = new LinkedHashMap<>();
    public static List<MenuAppender> appenders = new ArrayList<>();

    protected static void generateSystemMenu() {
        String localName = LocaleInfo.getCurrentLocale().getLocaleName();
        //编码总共10位,第一位表示类型:S:系统;A:应用;第二位表示应用类型:M:菜单;B:按钮;D:数据;-00000：表示菜单级别数：第一个0表示一级菜单第二个表示二级，依次类推;-000:表示按钮或者数据权限
        menuMap.put(BasicPermissionStatic.BASIC_DATA_MANAGEMENT, new ExplorerTreeNode(localName.equals("zh_CN") ? "数据管理" : "System Management", "basic_data_managment", "root", "menuicons/tt.png", null, true, "", null));
        menuMap.put(BasicPermissionStatic.ROLE_MANAGEMENT, new ExplorerTreeNode(localName.equals("zh_CN") ? "角色管理" : "Role", "role_managment", "basic_data_managment", "menuicons/j.png",
                new RoleWithRPanel.Factory(), true, "", RoleWithRPanel.class));
        menuMap.put(BasicPermissionStatic.DEPARTMENT_MANAGEMENT, new ExplorerTreeNode(localName.equals("zh_CN") ? "部门管理" : "Department", "deparment_managment", "basic_data_managment", "menuicons/icon-chengjie.png",
                new DepartmentPanel.Factory(), true, "", DepartmentPanel.class));

//        menuMap.put(BasicPermissionStatic.EMPLOYEE_MANAGEMENT, new ExplorerTreeNode(localName.equals("zh_CN") ? "职员管理" : "Employee", "employee_managment", "basic_data_managment", "silk/layout_content.png",
//                new MemployeePanel.Factory(), true, "", MemployeePanel.class));
//        menuMap.put(BasicPermissionStatic.PERMISSION_MANAGEMENT, new ExplorerTreeNode(localName.equals("zh_CN") ? "权限管理" : "Permission", "permission_managment", "basic_data_managment", "silk/layout_content.png",
//                new DepartmentRoleFunPanel.Factory(), true, "", DepartmentRoleFunPanel.class));
        menuMap.put(BasicPermissionStatic.PERMISSION_MANAGEMENT, new ExplorerTreeNode(localName.equals("zh_CN") ? "权限管理" : "Permission", "permission_managment", "basic_data_managment", "menuicons/i.png",
                new PermissionManagePanel.Factory(), true, "", PermissionManagePanel.class));
        menuMap.put(BasicPermissionStatic.WORKFLOW_MANAGEMENT, new ExplorerTreeNode(localName.equals("zh_CN") ? "流程管理" : "Workflow Management", "wf_managment", "root", "menuicons/ee.png", null, true, "", null));
//        menuMap.put(BasicPermissionStatic.WORKFLOW_TYPE_MANAGEMENT, new ExplorerTreeNode(localName.equals("zh_CN") ? "流程类型管理" : "Workflow Type", "wetype_managment", "wf_managment", "silk/layout_content.png",
//                new SystemProcessTypePanel.Factory(), true, "", SystemProcessTypePanel.class));
        menuMap.put(BasicPermissionStatic.WORKFLOW_DEFINITION, new ExplorerTreeNode(localName.equals("zh_CN") ? "流程定义" : "Workflow Manager", "we1_managment", "wf_managment", "menuicons/ee.png",
                new SsystemprocessporPanel.Factory(), true, "", SsystemprocessporPanel.class));
        menuMap.put(BasicPermissionStatic.WORKFLOW_TEST_RESULT, new ExplorerTreeNode(localName.equals("zh_CN") ? "流程测试结果" : "Workflow Test Result", "workflow_test_result", "wf_managment", "menuicons/ee.png",
        		new ProcessTestThreadTaskPanel.Factory(), true, "", ProcessTestThreadTaskPanel.class));

        menuMap.put(BasicPermissionStatic.SYSTEM_MANAGEMENT, new ExplorerTreeNode(localName.equals("zh_CN") ? "系统管理" : "System Manage", "system_management", "root", "menuicons/ff.png", null, true, "", null));

        menuMap.put(BasicPermissionStatic.FUNCTIONS_MANAGEMENT, new ExplorerTreeNode(localName.equals("zh_CN") ? "功能管理" : "Functions", "function_managment", "system_management", "menuicons/icon-dayinjifenhuizong.png",
                new SfunctionffaorPanel.Factory(), true, "", SfunctionffaorPanel.class));
        menuMap.put(BasicPermissionStatic.MOBILE_FUNCTIONS_MANAGEMENT, new ExplorerTreeNode(localName.equals("zh_CN") ? "手机功能菜单" : "Mobild Functions", "mobile_function_managment", "system_management", "menuicons/icon-xinwenmeiti.png",
        		new MobileFunctionPanel.Factory(), true, "", MobileFunctionPanel.class));

    }

    public DelicacyMenuData() {
    }

    public static void appendMenuProcessor(MenuAppender ma) {
        appenders.add(ma);
    }

    public static ExplorerTreeNode[] getData() {
        Set privileges = ClientUtil.getDefaultPrivileges();
        List<ExplorerTreeNode> nodeList = new ArrayList<>();

        for (MenuAppender ma : appenders) {
        	generateSystemMenu();
        	ma.append(menuMap);
        	if (Objects.equals(ClientUtil.getUserNo(), "admin")) {
//            menuMap = MapUtils.sortMapByKey(menuMap, "ASC");
        		if (ma.getPrivileges() != null) {
        			privileges.addAll(ma.getPrivileges());
        		}
        		for (String key : menuMap.keySet()) {
        			nodeList.add(menuMap.get(key));
        		}
        	} else {
        		if (!privileges.isEmpty()) {
//                menuMap = MapUtils.sortMapByKey(menuMap, "ASC");
        			if (ma.getPrivileges() != null) {
        				privileges.addAll(ma.getPrivileges());
        			}
        			for (String key : menuMap.keySet()) {
        				// 如果该权限代码不在这个列表之中，跳过
        				if (!privileges.contains(key)) {
        					continue;
        				}
        				nodeList.add(menuMap.get(key));
        			}
        		}
        	}
        	//添加不用权限控制的菜单
        	if(!generalMap.isEmpty()){
        		for (String key : generalMap.keySet()) {
        			nodeList.add(generalMap.get(key));
        		}
        	}
        }
        return nodeList.toArray(new ExplorerTreeNode[nodeList.size()]);
    }
}
