package com.delicacy.client.app.form;

import com.delicacy.client.BaseHelpUtils;
import com.delicacy.client.BasicPermissionStatic;
import com.delicacy.client.app.datasource.*;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.data.DBDataSource;
import com.delicacy.client.data.GroupColumn;
import com.delicacy.client.management.datasource.DSCopyEmployeeAllFunctionToOther;
import com.delicacy.client.management.form.CopyOriginalPermissionsFunctionPanel;
import com.delicacy.client.ui.AbstractDetailViewer;
import com.delicacy.client.ui.DelicacyListGrid;
import com.delicacy.client.ui.GenericViewWindow;
import com.smartgwt.client.data.*;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.events.DoubleClickEvent;
import com.smartgwt.client.widgets.events.DoubleClickHandler;
import com.smartgwt.client.widgets.form.SearchForm;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class MemployeeViewer extends AbstractDetailViewer {
	private static final Logger __logger = Logger.getLogger("");
    private final DelicacyListGrid employeeRolesGrid = new DelicacyListGrid();
    private final DelicacyListGrid employeeEducationInformationsGrid = new DelicacyListGrid();
	private final DelicacyListGrid employeeFamilyInformationsGrid = new DelicacyListGrid();
	private final DelicacyListGrid employeeRewardExperiencesGrid = new DelicacyListGrid();
	private final DelicacyListGrid employeeWorkExperiencesGrid = new DelicacyListGrid();
	private final DelicacyListGrid employeeTechnicalTitlesGrid = new DelicacyListGrid();
	private final TreeGrid employeeFunctionsGrid = new TreeGrid();
	private final DelicacyListGrid departmentFunctionEmployeesGrid = new DelicacyListGrid();
	private final DelicacyListGrid departmentAttachmentsEmployeesGrid = new DelicacyListGrid();
	private CopyOriginalPermissionsFunctionPanel copyOriginalPermissionsFunctionPanel;
    private SearchForm __parentSearchForm;
    private ListGrid grid = new ListGrid();
    public MemployeeViewer() {
    	
    }
    public MemployeeViewer(ListGrid listGrid) {
    	grid = listGrid;
    }
    @Override
    public void initComponents() {
        super.initComponents();
//        TreeGridField[] fields = new TreeGridField[6];
//		int idx = 0;
//		fields[idx] = new TreeGridField("functionId");
//		fields[idx].setHidden(true);
//		idx++;
//		fields[idx] = new TreeGridField("functionCode");
//		idx++;
//		fields[idx] = new TreeGridField("parentId");
//		fields[idx].setHidden(true);
//		idx++;
//		fields[idx] = new TreeGridField("functionName");
//		fields[idx].setFrozen(true);
//		idx++;
//		fields[idx] = new TreeGridField("applicationId");
//		idx++;
//		fields[idx] = new TreeGridField("enabled");
//		fields[idx].setHidden(true);
//		idx++;
//		fields[idx] = new TreeGridField("empFun");
//		idx++;
//		fields[idx] = new TreeGridField("roleFun");
//		idx++;
//		fields[idx] = new TreeGridField("departmentRoleFun");
//		idx++;
//		employeeFunctionsGrid.setFields(fields);
		employeeFunctionsGrid.setDataSource(DSCopyEmployeeAllFunctionToOther.getInstance());
        
        employeeRolesGrid.setDataSource(DSEmployeeRole.getInstance());
        employeeRolesGrid.addDoubleClickHandler(new DoubleClickHandler() {
            @Override
            public void onDoubleClick(DoubleClickEvent event) {
                GenericViewWindow detail = new GenericViewWindow();
                detail.setTitle("员工角色");
                detail.setWidth100();
                detail.setHeight100();
                EmployeeRoleViewer viewer = new EmployeeRoleViewer();
                viewer.setLayoutMode(AbstractDetailViewer.COMPONENT_LAYOUT_NORMAL);
                viewer.initComponents();
                viewer.viewSelectedData((ListGrid) event.getSource());
                detail.setContent(viewer);
                detail.centerInPage();
                detail.show();
            }
        });
        employeeEducationInformationsGrid.setDataSource(DSEmployeeEducationInformation.getInstance());
		employeeEducationInformationsGrid.addDoubleClickHandler(new DoubleClickHandler(){
			@Override
			public void onDoubleClick(DoubleClickEvent event) {
				GenericViewWindow detail = new GenericViewWindow();
				detail.setTitle("职员学历信息表");
				detail.setWidth(700); detail.setHeight(500); 
				EmployeeEducationInformationViewer viewer = new EmployeeEducationInformationViewer();
				viewer.setLayoutMode(AbstractDetailViewer.COMPONENT_LAYOUT_NORMAL);
				viewer.initComponents();
				viewer.viewSelectedData((ListGrid)event.getSource());
				detail.setContent(viewer);
				detail.centerInPage();detail.show();
			}
		});
		employeeFamilyInformationsGrid.setDataSource(DSEmployeeFamilyInformation.getInstance());
		employeeFamilyInformationsGrid.addDoubleClickHandler(new DoubleClickHandler(){
			@Override
			public void onDoubleClick(DoubleClickEvent event) {
				GenericViewWindow detail = new GenericViewWindow();
				detail.setTitle("职员家庭成员表");
				detail.setWidth(700); detail.setHeight(500); 
				EmployeeFamilyInformationViewer viewer = new EmployeeFamilyInformationViewer();
				viewer.setLayoutMode(AbstractDetailViewer.COMPONENT_LAYOUT_NORMAL);
				viewer.initComponents();
				viewer.viewSelectedData((ListGrid)event.getSource());
				detail.setContent(viewer);
				detail.centerInPage();detail.show();
			}
		});
		employeeRewardExperiencesGrid.setDataSource(DSEmployeeRewardExperience.getInstance());
		employeeRewardExperiencesGrid.addDoubleClickHandler(new DoubleClickHandler(){
			@Override
			public void onDoubleClick(DoubleClickEvent event) {
				GenericViewWindow detail = new GenericViewWindow();
				detail.setTitle("职员奖惩经历表");
				detail.setWidth(700); detail.setHeight(500); 
				EmployeeRewardExperienceViewer viewer = new EmployeeRewardExperienceViewer();
				viewer.setLayoutMode(AbstractDetailViewer.COMPONENT_LAYOUT_NORMAL);
				viewer.initComponents();
				viewer.viewSelectedData((ListGrid)event.getSource());
				detail.setContent(viewer);
				detail.centerInPage();detail.show();
			}
		});
		employeeWorkExperiencesGrid.setDataSource(DSEmployeeWorkExperience.getInstance());
		employeeWorkExperiencesGrid.addDoubleClickHandler(new DoubleClickHandler(){
			@Override
			public void onDoubleClick(DoubleClickEvent event) {
				GenericViewWindow detail = new GenericViewWindow();
				detail.setTitle("职员工作经历表");
				detail.setWidth(700); detail.setHeight(500); 
				EmployeeWorkExperienceViewer viewer = new EmployeeWorkExperienceViewer();
				viewer.setLayoutMode(AbstractDetailViewer.COMPONENT_LAYOUT_NORMAL);
				viewer.initComponents();
				viewer.viewSelectedData((ListGrid)event.getSource());
				detail.setContent(viewer);
				detail.centerInPage();detail.show();
			}
		});
		employeeTechnicalTitlesGrid.setDataSource(DSEmployeeTechnicalTitle.getInstance());
		employeeTechnicalTitlesGrid.addDoubleClickHandler(new DoubleClickHandler(){
			@Override
			public void onDoubleClick(DoubleClickEvent event) {
				GenericViewWindow detail = new GenericViewWindow();
				detail.setTitle("职员技术职称表");
				detail.setWidth(700); detail.setHeight(500); 
				EmployeeTechnicalTitleViewer viewer = new EmployeeTechnicalTitleViewer();
				viewer.setLayoutMode(AbstractDetailViewer.COMPONENT_LAYOUT_NORMAL);
				viewer.initComponents();
				viewer.viewSelectedData((ListGrid)event.getSource());
				detail.setContent(viewer);
				detail.centerInPage();detail.show();
			}
		});
		departmentAttachmentsEmployeesGrid.setDataSource(DSMemployeeAttachment.getInstance());
		departmentAttachmentsEmployeesGrid.addDoubleClickHandler(new DoubleClickHandler(){
			@Override
			public void onDoubleClick(DoubleClickEvent event) {
				GenericViewWindow detail = new GenericViewWindow();
				detail.setTitle("附件");
				detail.setWidth(700); detail.setHeight(500);
				EmployeeAttachmentViewer viewer = new EmployeeAttachmentViewer();
				viewer.setLayoutMode(AbstractDetailViewer.COMPONENT_LAYOUT_NORMAL);
				viewer.initComponents();
				viewer.viewSelectedData((ListGrid)event.getSource());
				detail.setContent(viewer);
				detail.centerInPage();detail.show();
			}
		});
		
		
		departmentFunctionEmployeesGrid.setDataSource(DSOnLoadDepartmentFunction.getInstance());
		departmentFunctionEmployeesGrid.setShowRowNumbers(true);
		departmentFunctionEmployeesGrid.setAutoFitFieldWidths(false);
    }

    @Override
    public int getHorizontalPercent() {
        return 0;
    }

    @Override
    public String getName() {
        return "职员信息";
    }

    @Override
    public int getGroupCount() {
		return 3;
    }

    @Override
    public List<GroupColumn> getGroupColumnNames() {
        List<GroupColumn> gcs = new ArrayList<>();
        GroupColumn gc;
        gc = new GroupColumn();
        gc.setGroupName("baseInfo");
        gc.setGroupCaption("基础信息");
        gc.getColumnNames().add("employeeNo");
		gc.getColumnNames().add("employeeName");
		gc.getColumnNames().add("employeeNameEn");
		gc.getColumnNames().add("card");
		gc.getColumnNames().add("birth");
		gc.getColumnNames().add("age");
		gc.getColumnNames().add("gender");
		gc.getColumnNames().add("country");
		gc.getColumnNames().add("education");
		gc.getColumnNames().add("degree");
		gc.getColumnNames().add("startWorkDate");
		gc.getColumnNames().add("languages");
//		gc.getColumnNames().add("professionalSequence");
		if(ClientUtil.checkIsHavePermission(BasicPermissionStatic.EMPLOYEE_CHECK_ALL_DETAIL_DATA)){
			gc.getColumnNames().add("fileNumber");
			gc.getColumnNames().add("birthplace");
			gc.getColumnNames().add("accountLocation");
			gc.getColumnNames().add("householdRegistration");
			gc.getColumnNames().add("nationality");
			gc.getColumnNames().add("marriedStatus");
			gc.getColumnNames().add("health");
			gc.getColumnNames().add("politicalFace");
			gc.getColumnNames().add("isCheck");
			gc.getColumnNames().add("socialComputerNumber");
			gc.getColumnNames().add("fundAccount");
			gc.getColumnNames().add("bankCardNum");
			gc.getColumnNames().add("employeeShift");
		}
        gcs.add(gc);
        gc = new GroupColumn();
        gc.setGroupName("personelInfo");
        gc.setGroupCaption("人事信息");
        gc.getColumnNames().add("onboardDate");
        gc.getColumnNames().add("resignationDate");
        gc.getColumnNames().add("foreman");
        gc.getColumnNames().add("workAddress");
        gc.getColumnNames().add("status");
        gc.getColumnNames().add("ownedCompany");
        gc.getColumnNames().add("departmentId");
        gc.getColumnNames().add("dutyId");
		gc.getColumnNames().add("cityLevel");
        if(ClientUtil.checkIsHavePermission(BasicPermissionStatic.EMPLOYEE_CHECK_ALL_DETAIL_DATA)){
	        gc.getColumnNames().add("positiveDate");
	        gc.getColumnNames().add("tryTime");
	        gc.getColumnNames().add("contractStartDate");
	        gc.getColumnNames().add("contractEndDate");
	        gc.getColumnNames().add("plateId");
	        gc.getColumnNames().add("gradeId");
	        gc.getColumnNames().add("jobs");
	        gc.getColumnNames().add("selfIntroduction");
	        gc.getColumnNames().add("personalBusinessRemark");
        }
        gcs.add(gc);
        gc = new GroupColumn();
        gc.setGroupName("contactInfo");
        gc.setGroupCaption("联系信息");
        gc.getColumnNames().add("phone");
		gc.getColumnNames().add("mobile");
		gc.getColumnNames().add("companyEmail");
		if(ClientUtil.checkIsHavePermission(BasicPermissionStatic.EMPLOYEE_CHECK_ALL_DETAIL_DATA)){
			gc.getColumnNames().add("homePhone");
	        gc.getColumnNames().add("emergencyContactPerson");
			gc.getColumnNames().add("emergencyContactPhone");
			gc.getColumnNames().add("cardAddress");
			gc.getColumnNames().add("nowAddress");
		}
		gcs.add(gc);
//		gc = new GroupColumn();
//		gc.setGroupName("payInfo");
//		gc.setGroupCaption("薪酬信息");
//		gc.getColumnNames().add("tryTimePay");
//		gc.getColumnNames().add("positivePay");
//		gc.getColumnNames().add("annualPerformance");
//		gc.getColumnNames().add("annualBonus");
//		gcs.add(gc);
        return gcs;
    }

    @Override
    public void viewSelectedData(ListGrid grid) {
//        super.viewSelectedData(grid);
        Record selected = grid.getSelectedRecord();
        Object val;
        java.util.Map condition = null;
        condition = new java.util.HashMap();
        condition.put("employeeId", selected.getAttributeAsString("employeeId"));
        DBDataSource.callOperation("ST_Employee", "find", condition, new DSCallback() {
            @Override
            public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
                if (dsResponse.getStatus() >= 0) {
                    viewRecord(dsResponse.getData()[0]);
                }
            }
        });
        java.util.Map parentSearch = __parentSearchForm == null ? new java.util.HashMap() : __parentSearchForm.getValues();
        
        condition = new java.util.HashMap();
		condition.put("employeeId", selected.getAttributeAsString("employeeId"));
		DBDataSource.callOperation("NQ_CopyEmployeeAllFunctionToOther", "find", condition, new DSCallback() {
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
					employeeFunctionsGrid.setData(tree);
				}
			}
		});
        
        condition = new java.util.HashMap();
        condition.put("employeeId", selected.getAttributeAsString("employeeId"));
        DBDataSource.callOperation("ST_EmployeeRole", "find", condition, new DSCallback() {
            @Override
            public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
                if (dsResponse.getStatus() >= 0) {
                    employeeRolesGrid.setData(dsResponse.getData());
                }
            }
        });
        condition = new java.util.HashMap();
		condition.put("employeeId", selected.getAttributeAsString("employeeId"));
		DBDataSource.callOperation("ST_EmployeeEducationInformation", "find", condition, new DSCallback(){
			@Override
			public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
				if(dsResponse.getStatus() >= 0){
					employeeEducationInformationsGrid.setData(dsResponse.getData());
				}
			}
		});
		condition = new java.util.HashMap();
		condition.put("employeeId", selected.getAttributeAsString("employeeId"));
		DBDataSource.callOperation("ST_EmployeeFamilyInformation", "find", condition, new DSCallback(){
			@Override
			public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
				if(dsResponse.getStatus() >= 0){
					employeeFamilyInformationsGrid.setData(dsResponse.getData());
				}
			}
		});
		condition = new java.util.HashMap();
		condition.put("employeeId", selected.getAttributeAsString("employeeId"));
		DBDataSource.callOperation("ST_EmployeeRewardExperience", "find", condition, new DSCallback(){
			@Override
			public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
				if(dsResponse.getStatus() >= 0){
					employeeRewardExperiencesGrid.setData(dsResponse.getData());
				}
			}
		});
		condition = new java.util.HashMap();
		condition.put("employeeId", selected.getAttributeAsString("employeeId"));
		DBDataSource.callOperation("ST_EmployeeWorkExperience", "find", condition, new DSCallback(){
			@Override
			public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
				if(dsResponse.getStatus() >= 0){
					employeeWorkExperiencesGrid.setData(dsResponse.getData());
				}
			}
		});
		condition = new java.util.HashMap();
		condition.put("employeeId", selected.getAttributeAsString("employeeId"));
		DBDataSource.callOperation("ST_EmployeeTechnicalTitle", "find", condition, new DSCallback(){
			@Override
			public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
				if(dsResponse.getStatus() >= 0){
					employeeTechnicalTitlesGrid.setData(dsResponse.getData());
				}
			}
		});

		condition = new java.util.HashMap();
		condition.put("employeeId", selected.getAttributeAsString("employeeId"));
		DBDataSource.callOperation("ST_Employee", "find", condition, new DSCallback(){
			@Override
			public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
				if(dsResponse.getStatus() >= 0){
					departmentAttachmentsEmployeesGrid.setData(dsResponse.getData());
				}
			}
		});
		
		Map<String,Object> params = new HashMap<>();
		params.put("employeeId",selected.getAttributeAsString("employeeId"));
		params.put("type",2);
		//加载职员信息
		DBDataSource.callOperation("NQ_OnLoadDepartmentFunction","find",params, new DSCallback() {
			@Override
			public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
				if (dsResponse.getStatus() >= 0) {
					departmentFunctionEmployeesGrid.setData(dsResponse.getData());
				} 
			}
		});
    }

    @Override
    public DataSource getMainDataSource() {
        return DSEmployeeOnboardInformation.getInstance();
    }

    @Override
    public int getDetailCount() {
    	if(ClientUtil.checkIsHavePermission(BasicPermissionStatic.EMPLOYEE_CHECK_ALL_DETAIL_DATA)){
    		return 8;
    	}else if(ClientUtil.checkIsHavePermission(BasicPermissionStatic.EMPLOYEE_CHECK_ROLE_DATA)){
    		return 3;
    	}else{
    		return 2;
    	}
    }

    @Override
    public List<ListGrid> getDetailListGrids() {
        List<ListGrid> res = new ArrayList<ListGrid>();
        res.add(employeeEducationInformationsGrid);//学历信息 默认就有
        //如果有职员管理查看职员全部详细资料权限或者职员管理查看职员角色权限
        if(ClientUtil.checkIsHavePermission(BasicPermissionStatic.EMPLOYEE_CHECK_ALL_DETAIL_DATA) || ClientUtil.checkIsHavePermission(BasicPermissionStatic.EMPLOYEE_CHECK_ROLE_DATA)){
        	res.add(employeeRolesGrid);//员工角色
        	 if(ClientUtil.checkIsHavePermission(BasicPermissionStatic.EMPLOYEE_CHECK_ALL_DETAIL_DATA)){
         		res.add(employeeFamilyInformationsGrid);//家庭信息
         		res.add(employeeRewardExperiencesGrid);//奖惩经理
         		res.add(employeeWorkExperiencesGrid);//工作经历
         		res.add(employeeTechnicalTitlesGrid);//技术职称
         	}
        }
        res.add(departmentFunctionEmployeesGrid);//部门权限信息 默认就有
        res.add(departmentAttachmentsEmployeesGrid);//新增的附件目前放在哪儿不明确
        return res;
    }
    
    @Override
    public List<Canvas> getCanvasPanels() {
        Record selected = grid.getSelectedRecord();
    	List<Canvas> res = new ArrayList<Canvas>();
    	  if(ClientUtil.checkIsHavePermission(BasicPermissionStatic.EMPLOYEE_CHECK_ALL_DETAIL_DATA) || 
          		ClientUtil.checkIsHavePermission(BasicPermissionStatic.EMPLOYEE_CHECK_FUNCTION_DATA)){
    			copyOriginalPermissionsFunctionPanel = new CopyOriginalPermissionsFunctionPanel();
    			copyOriginalPermissionsFunctionPanel.setWidth100();
    			copyOriginalPermissionsFunctionPanel.setHeight100();
    	    	copyOriginalPermissionsFunctionPanel.setEmployeeId(BaseHelpUtils.getIntValue(selected.getAttributeAsString("employeeId")));
    	    	copyOriginalPermissionsFunctionPanel.startEdit();
    	    	res.add(copyOriginalPermissionsFunctionPanel);
          }
    
    	return res;
    }
    
    @Override
    public List<String> getCanvasPanelNames() {
    	List<String> res = new ArrayList<String>();
    	res.add("功能权限");
    	return res;
    }

    @Override
    public List<String> getDetailNames() {
        List<String> res = new ArrayList<String>();
        res.add("学历信息");
        res.add("员工角色");
		res.add("家庭成员");
		res.add("奖惩经历");
		res.add("工作经历");
		res.add("技术职称");
		res.add("部门权限信息");
		res.add("附件");
        return res;
    }

    public SearchForm getParentSearchForm() {
        return this.__parentSearchForm;
    }

    public void setParentSearchForm(SearchForm value) {
        this.__parentSearchForm = value;
    }

	public ListGrid getGrid() {
		return grid;
	}

	public void setGrid(ListGrid grid) {
		this.grid = grid;
	}

}
