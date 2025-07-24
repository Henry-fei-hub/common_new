package com.delicacy.client.app.panel;

import com.delicacy.client.BaseHelpUtils;
import com.delicacy.client.BasicPermissionStatic;
import com.delicacy.client.PanelFactory;
import com.delicacy.client.PermissionControl;
import com.delicacy.client.app.datasource.DSSsystemprocesspor;
import com.delicacy.client.app.form.LookSsystemprocessporForm;
import com.delicacy.client.app.form.NewSsystemprocessporForm;
import com.delicacy.client.app.form.SsystemprocessporSearchForm;
import com.delicacy.client.app.form.UpdateSsystemprocessporForm;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.data.DBDataSource;
import com.delicacy.client.data.DataEditEvent;
import com.delicacy.client.data.DataEditedHandler;
import com.delicacy.client.ui.AbstractSearchPanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.smartgwt.client.data.*;
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

import java.util.HashMap;
import java.util.Map;

public class SsystemprocessporPanel extends AbstractSearchPanel {

    public static class Factory implements PanelFactory {

        private String id;

        @Override
        public Canvas create() {
            SsystemprocessporPanel cm = new SsystemprocessporPanel();
            id = cm.getID();
            return cm;
        }

        @Override
        public String getID() {
            return id;
        }

        @Override
        public String getDescription() {
            return "Ssystemprocesspor";
        }

    }

    @Override
    public void initComponents() {
//        IButton refreshButton = new IButton("刷新");
//        controlLayout.addMember(refreshButton);
//        refreshButton.setIcon("[SKIN]/actions/refresh.png");
//        refreshButton.addClickHandler(new ClickHandler() {
//            @Override
//            public void onClick(ClickEvent event) {
//                commonQuery();
//            }
//        });

        resultGrid.addDoubleClickHandler(new DoubleClickHandler() {
            @Override
            public void onDoubleClick(DoubleClickEvent event) {
                showDetail();
            }
        });

//        IButton operation1Button = new IButton("查看详情");
//        controlLayout.addMember(operation1Button);
//        operation1Button.setIcon("[SKIN]/actions/view.png");
//        operation1Button.addClickHandler(new ClickHandler() {
//            @Override
//            public void onClick(ClickEvent event) {
//                showDetail();
//            }
//        });
        IButton  operation2Button = PermissionControl.createPermissionButton("新建", BasicPermissionStatic.WORKFLOW_DEFINITION_NEW);
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
                        NewSsystemprocessporForm detailForm = new NewSsystemprocessporForm();
                        detailForm.setTitle("新建");
                        detailForm.addDataEditedHandler(new DataEditedHandler() {
                            @Override
                            public void onDataEdited(DataEditEvent event) {
                                RecordList list = resultGrid.getDataAsRecordList();
                                RecordList newList = new RecordList();
                                newList.addList(list.getRange(0, list.getLength()));
                                newList.add(event.getData());
                                resultGrid.setData(newList);
                                resultGrid.selectSingleRecord(newList.getLength() - 1);
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

        IButton  operation3Button = PermissionControl.createPermissionButton("修改", BasicPermissionStatic.WORKFLOW_DEFINITION_CHANGE);
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
                        UpdateSsystemprocessporForm detailForm = new UpdateSsystemprocessporForm();
                        detailForm.setTitle("修改流程");
                        detailForm.addDataEditedHandler(new DataEditedHandler() {
                            @Override
                            public void onDataEdited(DataEditEvent event) {
                                DBDataSource.copyRecord(event.getData(), selected1);
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
            }
        });
        IButton removeButton = new IButton("删除");
        controlLayout.addMember(removeButton);
        removeButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (!resultGrid.anySelected()) {
                    SC.say("Please select a data to remove.");
                }
                SC.ask("提示：确定要删除该流程定义吗?", new  BooleanCallback() {
					
					@Override
					public void execute(Boolean value) {
						if(value) {
							 final ListGridRecord selected = resultGrid.getSelectedRecord();
				                DBDataSource.callOperation("EP_DeleteProcessorDefinition", "delete", selected.toMap(), new DSCallback() {
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
						}
					}
				} );
            }
        });

        IButton  testButton = PermissionControl.createPermissionButton("流程测试", BasicPermissionStatic.WORKFLOW_DEFINITION_PROCESSOR_TEST);
        controlLayout.addMember(testButton);
        testButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				GWT.runAsync(new RunAsyncCallback() {

					@Override
					public void onSuccess() {
						if (!resultGrid.anySelected()) {
							SC.confirm("您确定要测试所有流程吗？", new BooleanCallback() {

								@Override
								public void execute(Boolean value) {
									if(value) {
										Map<String, String> params = new HashMap<>();
										params.put("operator", ClientUtil.getUserId());
										params.put("optType", "testAllProcess");
										DBDataSource.callOperation("EP_TestSystemProcess", "find", params, new DSCallback() {
						                    @Override
						                    public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
						                        if (dsResponse.getStatus() < 0) {
						                            ClientUtil.displayErrorMessage(dsResponse);
						                        } else {
						                            SC.say(BaseHelpUtils.getString(dsResponse.getErrors().get("errorMsg")));
						                        }
						                    }
						                });
									}else {
										return;
									}
								}
							});
		                }else {
		                	Map<String, String> params = new HashMap<>();
							params.put("operator", ClientUtil.getUserId());
		                	ListGridRecord[] records = resultGrid.getSelectedRecords();
		                	StringBuilder sb = new StringBuilder();
		                	for(ListGridRecord r : records) {
		                		if(sb.length() > 0) {
		                			sb.append(",");
		                		}
		                		sb.append(BaseHelpUtils.getIntValue(r.getAttributeAsObject("processId")));
		                	}
		                	params.put("optType", "testByProcessId");
		                	params.put("processId", sb.toString());
		                	DBDataSource.callOperation("EP_TestSystemProcess", "find", params, new DSCallback() {
			                    @Override
			                    public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
			                        if (dsResponse.getStatus() < 0) {
			                            ClientUtil.displayErrorMessage(dsResponse);
			                        } else {
			                            SC.say(BaseHelpUtils.getString(dsResponse.getErrors().get("errorMsg")));
			                        }
			                    }
			                });
		                }
					}

					@Override
					public void onFailure(Throwable reason) {
						SC.say("failure to download code");
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
                final ListGridRecord selected1 = resultGrid.getSelectedRecord();
                LookSsystemprocessporForm detailForm = new LookSsystemprocessporForm();
                detailForm.setTitle("查看流程详情");
                detailForm.addDataEditedHandler(new DataEditedHandler() {
                    @Override
                    public void onDataEdited(DataEditEvent event) {
                        DBDataSource.copyRecord(event.getData(), selected1);
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
    }

    @Override
    public boolean checkSearchCriteria(Map criteria) {
        criteria.put("addtionalCondition", "order by process_id");
        return true;
    }

    @Override
    public int getSearchFormHeight() {
    	return 60;
    }

    @Override
    public SearchForm generateSearchForm() {
        return new SsystemprocessporSearchForm();
    }

    @Override
    public DataSource getDataSource() {
        return DSSsystemprocesspor.getInstance();
    }

}
