package com.delicacy.client.app.panel;

import java.util.HashMap;
import java.util.Map;

import com.delicacy.client.BaseHelpUtils;
import com.delicacy.client.BasicPermissionStatic;
import com.delicacy.client.PanelFactory;
import com.delicacy.client.PermissionControl;
import com.delicacy.client.app.datasource.DSRoleWithR;
import com.delicacy.client.app.form.NewRoleWithRForm;
import com.delicacy.client.app.form.RoleWithRViewer;
import com.delicacy.client.app.form.SemployeeRoleInfoSearchForm;
import com.delicacy.client.app.form.UpdateRoleWithRForm;
import com.delicacy.client.data.DBDataSource;
import com.delicacy.client.data.DataEditEvent;
import com.delicacy.client.data.DataEditedHandler;
import com.delicacy.client.ui.AbstractDetailViewer;
import com.delicacy.client.ui.AbstractSearchPanel;
import com.delicacy.client.ui.GenericViewWindow;
import com.delicacy.client.ui.LayoutConstant;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.SearchForm;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class RoleWithRPanel extends AbstractSearchPanel {

    public static class Factory implements PanelFactory {

        private String id;

        @Override
        public Canvas create() {
            RoleWithRPanel cm = new RoleWithRPanel();
            id = cm.getID();
            return cm;
        }

        @Override
        public String getID() {
            return id;
        }

        @Override
        public String getDescription() {
            return "RoleWithR";
        }

    }
    
    @Override
    public void init(){
        super.init();
        __controlPosition = LayoutConstant.RIGHT;
        __needPagenation = false;
        resultGrid.setAutoFitFieldWidths(false);
    }
    
    @Override
    public void initComponents() {
        IButton refreshButton = new IButton("刷新");
        controlLayout.addMember(refreshButton);
        refreshButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                commonQuery();
            }
        });

        IButton operation1Button = new IButton("查看详情");
        operation1Button.hide();
        controlLayout.addMember(operation1Button);
        operation1Button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                showDetail();
            }
        });
        IButton operation2Button = PermissionControl.createPermissionButton("新建", BasicPermissionStatic.ROLE_ADD_BUTTON);
        controlLayout.addMember(operation2Button);
        operation2Button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                GWT.runAsync(new RunAsyncCallback() {
                    @Override
                    public void onFailure(Throwable reason) {
                        SC.say("failure to download");
                    }

                    @Override
                    public void onSuccess() {
                        NewRoleWithRForm detailForm = new NewRoleWithRForm();
                        detailForm.setTitle("新建");
                        detailForm.addDataEditedHandler(new DataEditedHandler() {
                            @Override
                            public void onDataEdited(DataEditEvent event) {
                                commonQuery();
                            }
                        });
                        detailForm.setSearchForm(searchForm);
                        detailForm.initComponents();
                        detailForm.startEdit();
                        detailForm.setWidth("60%");
                        detailForm.setHeight("80%");
                        detailForm.centerInPage();
                        detailForm.show();
                    }
                });
            }
        });
        IButton operation3Button = PermissionControl.createPermissionButton("修改", BasicPermissionStatic.ROLE_UPDATE_BUTTON);
        controlLayout.addMember(operation3Button);
        operation3Button.addClickHandler(new ClickHandler() {
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
                        UpdateRoleWithRForm detailForm = new UpdateRoleWithRForm();
                        detailForm.setTitle("修改");
                        detailForm.addDataEditedHandler(new DataEditedHandler() {
                            @Override
                            public void onDataEdited(DataEditEvent event) {
                            	commonQuery();
                            }
                        });
                        detailForm.setSearchForm(searchForm);
                        detailForm.setRecord(selected1);
                        detailForm.setCurrentPage(getCurrentPage());
                        detailForm.initComponents();
                        detailForm.startEdit();
                        detailForm.setWidth("60%");
                        detailForm.setHeight("80%");
                        detailForm.centerInPage();
                        detailForm.show();
                        setCurrentPage(detailForm.getCurrentPage());
                    }
                });
            }
        });
        IButton removeButton = PermissionControl.createPermissionButton("删除", BasicPermissionStatic.ROLE_DELETE_BUTTON);
        controlLayout.addMember(removeButton);
        removeButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (!resultGrid.anySelected()) {
                    SC.say("Please select a data to remove.");
                }
                SC.ask("提示","你确认要进行删除吗?",new BooleanCallback() {
					@Override
					public void execute(Boolean value) {
						if(value){
							final ListGridRecord selected = resultGrid.getSelectedRecord();
							//获取角色id,如果是事业部负责人（2），运营管理员（3），数据管理专员(47)，则不允许进行删除
							final int roleId = BaseHelpUtils.getIntValue(selected.getAttribute("roleId"));
							Map<String,Object> map =new HashMap<>();
							map.put("isStandard", true);
							DBDataSource.callOperation("ST_Role","find", map, new DSCallback() {
								@Override
								public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
									if (dsResponse.getStatus() < 0) {
										SC.say(BaseHelpUtils.getString(dsResponse.getErrors().get("errorMsg")));
									} else {
										final Map<Integer,Object> roleMap = new HashMap<>();
										Record[] records = dsResponse.getData();
										if(!BaseHelpUtils.isNullOrEmpty(records)&&records.length>0) {
											for (Record record : records) {
												Integer id = record.getAttributeAsInt("roleId");
												roleMap.put(id,id);
											}
											if(roleMap.containsKey(roleId)){
												SC.say("提示","系统角色不可删除");
												return;
											}else {
												DBDataSource.callOperation("EP_DeleteRoleManageWithDepartmentAndFunAndEmp", selected.toMap(), new DSCallback() {
													@Override
													public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
														if (dsResponse.getStatus() < 0) {
															SC.say(BaseHelpUtils.getString(dsResponse.getErrors().get("errorMsg")));
														} else {
															SC.say("删除成功");
															int indexNum = resultGrid.getRowNum(selected);
															resultGrid.removeData(selected);
															resultGrid.selectSingleRecord(indexNum);
														}
													}
												});
											}
										}else {
											SC.say("提示","系统角色数据获取失败！");
											return;
										}
									}
								}
							});
						}
					}
				});
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
                detail.setTitle("职员角色");
                detail.setWidth100();
                detail.setHeight100();
                RoleWithRViewer detailForm = new RoleWithRViewer();
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
    public int getSearchFormHeight() {
    	return 60;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public boolean checkSearchCriteria(Map criteria) {
        criteria.put("addtionalCondition", "order by role_id");
        String roleName = BaseHelpUtils.getString(criteria.get("roleName"));
        if(!BaseHelpUtils.isNullOrEmpty(roleName)){
        	criteria.put("roleName","%"+roleName+"%");
        }
        return true;
    }

    @Override
    public SearchForm generateSearchForm() {
        return new SemployeeRoleInfoSearchForm();
    }

    @Override
    public DataSource getDataSource() {
        return DSRoleWithR.getInstance();
    }

}

