package com.delicacy.client.app.panel;

import com.smartgwt.client.widgets.*;
import com.smartgwt.client.widgets.events.*;
import com.smartgwt.client.util.SC;
import com.delicacy.client.data.DataEditEvent;
import com.delicacy.client.data.DataEditedHandler;
import com.delicacy.client.ui.AbstractSearchPanel;
import com.delicacy.client.PanelFactory;
import com.delicacy.client.ui.GenericViewWindow;
import com.delicacy.client.ui.AbstractDetailViewer;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.delicacy.client.app.datasource.DSSapplicationaaor;
import com.delicacy.client.app.form.SapplicationaaorViewer;
import com.delicacy.client.app.form.UpdateSapplicationaaorForm;
import com.delicacy.client.data.DBDataSource;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.smartgwt.client.data.*;
import com.smartgwt.client.widgets.form.SearchForm;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.EditCompleteEvent;
import com.smartgwt.client.widgets.grid.events.EditCompleteHandler;
import java.util.Map;

public class SapplicationaaorPanel extends AbstractSearchPanel {

    public static class Factory implements PanelFactory {

        private String id;

        @Override
        public Canvas create() {
            SapplicationaaorPanel cm = new SapplicationaaorPanel();
            id = cm.getID();
            return cm;
        }

        @Override
        public String getID() {
            return id;
        }

        @Override
        public String getDescription() {
            return "Sapplicationaaor";
        }

    }

    @Override
    public void initComponents() {
//		resultGrid.addDoubleClickHandler(new DoubleClickHandler() {
//			@Override
//			public void onDoubleClick(DoubleClickEvent event) {
//				showDetail();
//			}
//		});
        resultGrid.setCanEdit(true);
        ListGridField idField = new ListGridField("applicationId");
        idField.setCanEdit(false);
        ListGridField nameField = new ListGridField("applicationName");
        resultGrid.setFields(idField, nameField);
        resultGrid.addEditCompleteHandler(new EditCompleteHandler() {
            @Override
            public void onEditComplete(EditCompleteEvent event) {
                Map params = event.getNewValues();

                final int rowNumModified = event.getRowNum();
                DBDataSource.callOperation("ST_Application", "saveOrUpdate", params, new DSCallback() {
                    @Override
                    public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
                        if (dsResponse.getStatus() < 0) {
                            SC.say("failure. " + dsResponse.getErrors().get("errorMsg"));
                        } else {
                            DBDataSource.copyRecord(dsResponse.getData()[0], resultGrid.getRecord(rowNumModified));
                            resultGrid.redraw();
                        }
                    }

                });
            }
        });
        IButton newButton = new IButton("新建");
        controlLayout.addMember(newButton);
        newButton.setIcon("[SKIN]/actions/add.png");
        newButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                resultGrid.startEditingNew();
//				GWT.runAsync(new RunAsyncCallback() {
//					@Override
//					public void onFailure(Throwable reason) {
//						SC.say("failure to download");
//					}
//					@Override
//					public void onSuccess() {
//						NewSapplicationaaorForm detailForm = new NewSapplicationaaorForm();
//						detailForm.setTitle("新建");
//						detailForm.addDataEditedHandler(new DataEditedHandler(){
//							@Override
//							public void onDataEdited(DataEditEvent event) {
//								RecordList list = resultGrid.getDataAsRecordList();
//								RecordList newList = new RecordList();
//								newList.addList(list.getRange(0, list.getLength()));
//								newList.add(event.getData());
//								resultGrid.setData(newList);
//								resultGrid.selectSingleRecord(newList.getLength()-1);
//							}
//						});
//						detailForm.setSearchForm(searchForm);
//						detailForm.initComponents();
//						detailForm.startEdit();
//						detailForm.setWidth100();
//						detailForm.setHeight100();
//						detailForm.centerInPage();
//						detailForm.show();
//					}
//				});
            }
        });
        IButton modifyButton = new IButton("修改");
        controlLayout.addMember(modifyButton);
        modifyButton.setIcon("[SKIN]/actions/edit.png");
        modifyButton.addClickHandler(new ClickHandler() {
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
                        final ListGridRecord selected = resultGrid.getSelectedRecord();
                        UpdateSapplicationaaorForm detailForm = new UpdateSapplicationaaorForm();
                        detailForm.setTitle("修改");
                        detailForm.addDataEditedHandler(new DataEditedHandler() {
                            @Override
                            public void onDataEdited(DataEditEvent event) {
                                DBDataSource.copyRecord(event.getData(), selected);
                                resultGrid.redraw();
//								RecordList list = resultGrid.getDataAsRecordList();
//								Map params = new HashMap();
//								params.put("applicationId", event.getData().getAttribute("applicationId"));
//								int index = list.findIndex(params);
//								if (index != -1) {
//									RecordList newList = new RecordList();
//									if (index > 0) {
//										newList.addList(list.getRange(0, index));
//									}
//									newList.add(event.getData());
//									if (index < list.getLength() - 1) {
//										newList.addList(list.getRange(index + 1, list.getLength()));
//									}
//									resultGrid.setData(newList);
//									resultGrid.selectSingleRecord(index);
//								}
                            }
                        });
                        detailForm.setSearchForm(searchForm);
                        detailForm.setRecord(selected);
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
        removeButton.setIcon("[SKIN]/actions/remove.png");
        removeButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (!resultGrid.anySelected()) {
                    SC.say("请选择一条数据修改");
                    return;
                }
                final ListGridRecord selected = resultGrid.getSelectedRecord();
                DBDataSource.callOperation("ST_Application", "delete", selected.toMap(), new DSCallback() {
                    @Override
                    public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
                        if (dsResponse.getStatus() < 0) {
                            SC.say("failure. " + dsResponse.getErrors().get("errorMsg"));
                        } else {
                            resultGrid.removeData(selected);
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
                detail.setTitle("应用系统");
                detail.setWidth100();
                detail.setHeight100();
                SapplicationaaorViewer detailForm = new SapplicationaaorViewer();
                detailForm.setParentSearchForm(searchForm);
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
    public SearchForm generateSearchForm() {
//		return new SapplicationaaorSearchForm();
        return null;
    }

    @Override
    public DataSource getDataSource() {
        return DSSapplicationaaor.getInstance();
    }

}
