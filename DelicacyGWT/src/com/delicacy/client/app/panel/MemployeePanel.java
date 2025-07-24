package com.delicacy.client.app.panel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.delicacy.client.BaseHelpUtils;
import com.delicacy.client.BasicPermissionStatic;
import com.delicacy.client.MapUtils;
import com.delicacy.client.PanelFactory;
import com.delicacy.client.PermissionControl;
import com.delicacy.client.app.datasource.DSMemployee;
import com.delicacy.client.app.datasource.DSMemployeeNew;
import com.delicacy.client.app.form.MemployeeSearchForm;
import com.delicacy.client.app.form.MemployeeViewer;
import com.delicacy.client.app.form.NewMemployeeForm;
import com.delicacy.client.app.form.UpdateMemployeeForm;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.data.DBDataSource;
import com.delicacy.client.data.DataEditEvent;
import com.delicacy.client.data.DataEditedHandler;
import com.delicacy.client.ui.AbstractDetailViewer;
import com.delicacy.client.ui.AbstractSearchPanel;
import com.delicacy.client.ui.GenericViewWindow;
import com.delicacy.client.ui.PopupWindow;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.DoubleClickEvent;
import com.smartgwt.client.widgets.events.DoubleClickHandler;
import com.smartgwt.client.widgets.form.SearchForm;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class MemployeePanel extends AbstractSearchPanel {

	private static final Logger __logger = Logger.getLogger("");
	
    public static class Factory implements PanelFactory {

        private String id;

        @Override
        public Canvas create() {
            MemployeePanel cm = new MemployeePanel();
            id = cm.getID();
            return cm;
        }

        @Override
        public String getID() {
            return id;
        }

        @Override
        public String getDescription() {
            return "Memployee";
        }

    }

    @Override
    public void initComponents() {
        resultGrid.addDoubleClickHandler(new DoubleClickHandler() {
            @Override
            public void onDoubleClick(DoubleClickEvent event) {
                showDetail();
            }
        });
        IButton empviewButton = new IButton("查看详情");
        controlLayout.addMember(empviewButton);
        empviewButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                showDetail();
            }
        });
        IButton empnewButton = PermissionControl.createPermissionButton("职员新增", BasicPermissionStatic.EMPLOYEE_ADD_BUTTON);
//        controlLayout.addMember(empnewButton);
        empnewButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                GWT.runAsync(new RunAsyncCallback() {
                    @Override
                    public void onFailure(Throwable reason) {
                        SC.say("failure to download");
                    }

                    @Override
                    public void onSuccess() {
                        NewMemployeeForm detailForm = new NewMemployeeForm();
                        detailForm.setTitle("职员新增");
                        detailForm.addDataEditedHandler(new DataEditedHandler() {
                            @Override
                            public void onDataEdited(DataEditEvent event) {
                                commonQuery();
//                                RecordList list = resultGrid.getDataAsRecordList();
//                                RecordList newList = new RecordList();
//                                newList.addList(list.getRange(0, list.getLength()));
//                                newList.add(event.getData());
//                                resultGrid.setData(newList);
//                                resultGrid.selectSingleRecord(newList.getLength() - 1);
                            }
                        });
                        detailForm.setSearchForm(searchForm);
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
        IButton empupdateButton = PermissionControl.createPermissionButton("职员修改", BasicPermissionStatic.EMPLOYEE_UPDATE_BUTTON);
        controlLayout.addMember(empupdateButton);
        empupdateButton.addClickHandler(new ClickHandler() {
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
                        final ListGridRecord selected1 = resultGrid.getSelectedRecord();
                        UpdateMemployeeForm detailForm = new UpdateMemployeeForm();
                        detailForm.setTitle("职员修改");
                        detailForm.addDataEditedHandler(new DataEditedHandler() {
                            @Override
                            public void onDataEdited(DataEditEvent event) {
                                commonQuery();
//                                DBDataSource.copyRecord(event.getData(), selected1);
                            }
                        });
                        detailForm.setSearchForm(searchForm);
//                        detailForm.setRecord(selected1);
                        detailForm.reloadData(selected1.getAttributeAsInt("employeeId"));
                        detailForm.setCurrentPage(getCurrentPage());
                        detailForm.initComponents();
                        detailForm.setOperatorId(ClientUtil.getEmployeeId());
                        detailForm.startEdit();
                        detailForm.setWidth100();
                        detailForm.setHeight100();
                        detailForm.centerInPage();
                        detailForm.show();
                        setCurrentPage(detailForm.getCurrentPage());
                    }
                });
            }
        });
        IButton removeButton = PermissionControl.createPermissionButton("职员移除", BasicPermissionStatic.EMPLOYEE_DELETE_BUTTON);
        controlLayout.addMember(removeButton);
        removeButton.addClickHandler(new ClickHandler() {
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
                        SC.confirm("提示", "您确定要移除该职员吗？", new BooleanCallback() {
                            @Override
                            public void execute(Boolean r) {
                                if (r) {
                                    ListGridRecord[] selected = resultGrid.getSelectedRecords();
                                    int employeeId = ClientUtil.checkAndGetIntValue(selected[0].getAttribute("employeeId"));
                                    Map param = new LinkedHashMap();
                                    param.put("employeeId", employeeId);
                                    String message = MapUtils.toJSON(param);
                                    //删除方法
                                    DBDataSource.callOperation("EP_EmployeeDelete", "delete", message, new DSCallback() {
                                        @Override
                                        public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
                                            if (dsResponse.getStatus() >= 0) {
                                                //执行成功后重新执行查询，刷新表格数据
                                                commonQuery();
                                            } else {
                                                Map errors = dsResponse.getErrors();
                                                SC.say("delete failure" + errors);
                                            }
                                        }
                                    });
                                }
                            }
                        });
                    }
                });
            }
        });
        IButton resetButton = PermissionControl.createPermissionButton("密码重置", BasicPermissionStatic.EMPLOYEE_RESET_PWD_BUTTON);
        controlLayout.addMember(resetButton);
        resetButton.hide();
        resetButton.addClickHandler(new ClickHandler() {
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
                        SC.confirm("提示", "您确定要重置该职员密码吗？", new BooleanCallback() {
                            @Override
                            public void execute(Boolean r) {
                                if (r) {
                                    ListGridRecord[] selected = resultGrid.getSelectedRecords();
                                    int employeeId = ClientUtil.checkAndGetIntValue(selected[0].getAttribute("employeeId"));
                                    Map param = new LinkedHashMap();
                                    param.put("employeeId", employeeId);
                                    String message = MapUtils.toJSON(param);
                                    //重置
                                    DBDataSource.callOperation("EP_EmployeePasswordReset", "update", message, new DSCallback() {
                                        @Override
                                        public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
                                            if (dsResponse.getStatus() >= 0) {
                                                //执行成功后提示重置成功
                                                SC.say("提示","密码重置成功");
                                            } else {
                                                Map errors = dsResponse.getErrors();
                                                SC.say("update failure" + errors);
                                            }
                                        }
                                    });
                                }
                            }
                        });
                    }
                });
            }
        });
        IButton setRoleButton = PermissionControl.createPermissionButton("角色设置", BasicPermissionStatic.EMPLOYEE_ROLE_SET_BUTTON);
        controlLayout.addMember(setRoleButton);
        setRoleButton.addClickHandler(new ClickHandler() {
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
                        int employeeId = ClientUtil.checkAndGetIntValue(selected[0].getAttribute("employeeId"));
                        String employeeName = selected[0].getAttribute("employeeName");
                        PopupWindow pw = new PopupWindow();
                    //    EmpDepartmentRolePanel panel = new EmpDepartmentRolePanel(employeeId);
                        EmpRoleAndDepartmentRolePanel panel = new EmpRoleAndDepartmentRolePanel(employeeId,employeeName);
                        pw.addItem(panel);
                        panel.setParentCanvas(pw);
                        pw.setTitle("职员(" + employeeName + ")的角色设置");
                        pw.setWidth100();
                        pw.setHeight100();
                        pw.centerInPage();
                        pw.show();
                    }
                });
            }
        });
        IButton downButton = PermissionControl.createPermissionButton("导出", BasicPermissionStatic.EMPLOYEE_EXPORT_BUTTON);
        controlLayout.addMember(downButton);
        downButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                Criteria record = searchForm.getValuesAsCriteria();
                Map param = record.getValues();
                checkSearchCriteria(param);
                ClientUtil.departmentParameterProcess(param);
                DBDataSource.downloadFile("DW_OnDownloadEmployeeInfo", param);
            }
        });
        commonQuery();
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
                detail.setTitle("职员信息");
                detail.setWidth100();
                detail.setHeight100();
                MemployeeViewer detailForm = new MemployeeViewer(resultGrid);
                detailForm.setParentSearchForm(searchForm);
                detailForm.setLayoutMode(AbstractDetailViewer.COMPONENT_LAYOUT_TAB);
                detailForm.initComponents();
                detailForm.viewSelectedData(resultGrid);
                detailForm.setGrid(resultGrid);
                detail.setContent(detailForm);
                detail.centerInPage();
                detail.show();
            }
        });
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public boolean checkSearchCriteria(Map criteria) {
        ClientUtil.departmentParameterProcess(criteria);
        List<Map> keyvalues = new ArrayList<>();
        if(BaseHelpUtils.isNullOrEmpty(criteria.get("onboardYear"))){
        	Map<String, String> kv = new HashMap<>();
        	kv.put("key", "( EXTRACT ( YEAR FROM onboard_date ))");
        	kv.put("value", "1");
        	keyvalues.add(kv);
        }
        if(BaseHelpUtils.isNullOrEmpty(criteria.get("onboardMonth"))){
        	Map<String, String> kv = new HashMap<>();
        	kv.put("key", "( EXTRACT ( MONTH FROM onboard_date ))");
        	kv.put("value", "1");
        	keyvalues.add(kv);
        }
        if(BaseHelpUtils.isNullOrEmpty(criteria.get("resiYear"))){
        	Map<String, String> kv = new HashMap<>();
        	kv.put("key", "( EXTRACT ( YEAR FROM resignation_date ))");
        	kv.put("value", "1");
        	keyvalues.add(kv);
        }
        if(BaseHelpUtils.isNullOrEmpty(criteria.get("resiMonth"))){
        	Map<String, String> kv = new HashMap<>();
        	kv.put("key", "( EXTRACT ( MONTH FROM resignation_date ))");
        	kv.put("value", "1");
        	keyvalues.add(kv);
        }
        if(BaseHelpUtils.isNullOrEmpty(criteria.get("birthMonth"))){
        	Map<String, String> kv = new HashMap<>();
        	kv.put("key", "( EXTRACT ( MONTH FROM birth ))");
        	kv.put("value", "1");
        	keyvalues.add(kv);
        }
        if(BaseHelpUtils.isNullOrEmpty(criteria.get("roleId"))){
        	Map<String, String> kv = new HashMap<>();
        	kv.put("key", "e.employee_id IN ( SELECT employee_id FROM employee_roles WHERE role_id =? )");
        	kv.put("value", "role_id = ?");
        	keyvalues.add(kv);
        }
        if (BaseHelpUtils.isNullOrEmpty(criteria.get("startWorkYear"))){
            Map<String, String> kv = new HashMap<>();
            kv.put("key", "( EXTRACT ( YEAR FROM start_work_date ))");
            kv.put("value", "1");
            keyvalues.add(kv);
        }
        if (BaseHelpUtils.isNullOrEmpty(criteria.get("startWorkMonth"))){
            Map<String, String> kv = new HashMap<>();
            kv.put("key", "( EXTRACT ( MONTH FROM start_work_date ))");
            kv.put("value", "1");
            keyvalues.add(kv);
        }
        if (BaseHelpUtils.isNullOrEmpty(criteria.get("contractStartYear"))){
            Map<String, String> kv = new HashMap<>();
            kv.put("key", "( EXTRACT ( YEAR FROM contract_start_date ))");
            kv.put("value", "1");
            keyvalues.add(kv);
        }
        if (BaseHelpUtils.isNullOrEmpty(criteria.get("contractStartMonth"))){
            Map<String, String> kv = new HashMap<>();
            kv.put("key", "( EXTRACT ( MONTH FROM contract_start_date ))");
            kv.put("value", "1");
            keyvalues.add(kv);
        }
        if (BaseHelpUtils.isNullOrEmpty(criteria.get("contractEndYear"))){
            Map<String, String> kv = new HashMap<>();
            kv.put("key", "( EXTRACT ( YEAR FROM contract_end_date ))");
            kv.put("value", "1");
            keyvalues.add(kv);
        }
        if (BaseHelpUtils.isNullOrEmpty(criteria.get("contractEndMonth"))){
            Map<String, String> kv = new HashMap<>();
            kv.put("key", "( EXTRACT ( MONTH FROM contract_end_date ))");
            kv.put("value", "1");
            keyvalues.add(kv);
        }

        if(!BaseHelpUtils.isNullOrEmpty(keyvalues) && keyvalues.size() > 0){
        	criteria.put("keyValues", keyvalues);
        }

        int isCheck = BaseHelpUtils.getIntValue(criteria.get("isCheck"));
        if(isCheck == 1){
        	criteria.put("isCheck", Boolean.TRUE);
        }else if(isCheck == 2){
        	criteria.put("isCheck", Boolean.FALSE);
        }
        return true;
    }

    @Override
    public SearchForm generateSearchForm() {
        return new MemployeeSearchForm();
    }

    @Override
    public DataSource getDataSource() {
        return DSMemployee.getInstance();
    }
    
    @Override
    public int getSearchFormHeight() {
        return 179;
    }
    
}
