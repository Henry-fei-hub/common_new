package com.delicacy.client.ui;

import static com.delicacy.client.ui.AbstractProcessPanel.LAYOUT_TOPBOTTOM;
import static com.delicacy.client.ui.AbstractProcessPanel.PAGE_DIAPLAY_ONE;
import static com.delicacy.client.ui.GenericWizadWindow.BORDER_STYLE;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.delicacy.client.BaseHelpUtils;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.data.DBDataSource;
import com.delicacy.client.data.DataEditEvent;
import com.delicacy.client.data.DataEditedHandler;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.management.datasource.DSSelectGridEmployeeValue;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.LayoutPolicy;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.TextMatchStyle;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.drawing.DrawPane;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.IMenuButton;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 *
 * @author guangxun
 */
public class PooledTaskWindow extends Window implements HasHandlers {

    private ToolStripButton previousButton;
    private AbstractDetailViewer viewPanel;
    private AbstractProcessPanel businessPanel;
    private ToolStripButton nextButton;
    private ToolStripButton saveButton;
    private int __processId = 0;
    private Record __instanceData;
    private String __executeName;
    private int activityType = 2;
    private static final Logger __LOGGER = Logger.getLogger("");
    private ComboBoxItem selectApprovalment;
    private ToolStripButton reassignButton;
    private ToolStripButton rejectButton;
    private IMenuButton backMenuButton;

    public PooledTaskWindow() {

    }

    protected void buttonProcess() {
    	if (activityType == 3) {
	    	if (businessPanel.getPageNum() == 1) {
	            previousButton.setVisible(false);
	            nextButton.setVisible(false);
	        }
	        if (businessPanel.getCurrentPage() == 0) {
	            previousButton.setDisabled(true);
	        } else {
	            previousButton.setDisabled(false);
	        }
	        if (businessPanel.getCurrentPage() == businessPanel.getPageNum() - 1) {
	            nextButton.setDisabled(true);
	        } else {
	            nextButton.setDisabled(false);
	        }
	        if (businessPanel.getCurrentPage() == businessPanel.getPageNum() - 1) {
	            saveButton.setDisabled(false);
	        } else {
	            saveButton.setDisabled(false);
	        }
    	}else {
    		previousButton.setVisible(false);
            nextButton.setVisible(false);
            saveButton.setDisabled(false);
    	}
    }

    public void initComponents() {
        //设置页面属性
        setWidth("100%");
        setHeight("100%");
        //setShowEdges(true);

        setShowMinimizeButton(true);
        setShowCloseButton(true);
        setCanDragReposition(true);
        setCanDragResize(true);
        setShowShadow(false);
        setIsModal(true);
        setShowModalMask(true);
        setPadding(20);
        setMembersMargin(10);
        setOverflow(Overflow.AUTO);
        centerInPage();

        addCloseClickHandler(new CloseClickHandler() {
            @Override
            public void onCloseClick(CloseClickEvent event) {
                destroy();
            }
        });

        if (getInstanceData() == null) {
            SC.say("没有设置流程实例数据");
            destroy();
            return;
        }
        String sprocessId = __instanceData.getAttribute("processId");
        String sprocessType = __instanceData.getAttribute("processType");
        String sprocessInstanceId = __instanceData.getAttribute("processInstanceId");
        String backViewName = __instanceData.getAttribute("backViewName");
        activityType = __instanceData.getAttribute("activityType") == null ? 2 : __instanceData.getAttributeAsInt("activityType");
        __executeName = KeyValueManager.getValue("processExecutors", sprocessId);
        if (__executeName == null) {
			__executeName = KeyValueManager.getValue("process_type_executor", sprocessType);
			if (__executeName == null) {
				SC.say("没有找到相应的流程处理程序");
				destroy();
				return;
			}
		}
        WorkflowProcessor workflowProcessor = Workflows.getProcessor(__executeName);
        if (workflowProcessor == null) {
            SC.say("没有找到注册的流程处理程序");
            destroy();
            return;
        }
        if (activityType == 2) {
            viewPanel = workflowProcessor.getViewPanel(backViewName == null ? "" : backViewName, ClientUtil.checkAndGetIntValue(sprocessType));
            if (viewPanel == null) {
                SC.say("浏览数据面板为空");
                destroy();
                return;
            }
            viewPanel.setLayoutMode(__layoutMode);
            viewPanel.set__instanceData(__instanceData);
            viewPanel.initComponents();
        } else if (activityType == 3) {
        	businessPanel = workflowProcessor.getProcessPanel(backViewName == null ? "" : backViewName);
            if (businessPanel == null) {
                SC.say("浏览数据面板为空");
                destroy();
                return;
            }
            businessPanel.setLayoutMode(__layoutMode);
            businessPanel.setProcessId(__processId);
            businessPanel.setProcessTypeId(BaseHelpUtils.getIntValue(sprocessType));
            businessPanel.set__instanceData(__instanceData);
            businessPanel.initComponents();
        }

        Map params = new HashMap();
        params.put("processInstanceId", sprocessInstanceId);
        DBDataSource.callOperation("EP_GetProcessInstance", params, new DSCallback() {
            @Override
            public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
                if (dsResponse.getStatus() >= 0) {
                    Record[] records = dsResponse.getData();
                    if (records.length == 0) {
                        return;
                    }
                    Record record = records[0];
                    setData(record);
                    Record businessData = record.getAttributeAsRecord("userData");
                    if (activityType == 3) {
                        businessPanel.setData(businessData);
                        businessPanel.refreshPageData();
//                        businessPanel.startEdit();
                    } else {
                        viewPanel.setRecord(businessData);
                        viewPanel.viewRecord(businessData);
                    }
                    __LOGGER.info("具体数据：" + record.toMap().toString());
                    Record processInstance = record.getAttributeAsRecord("processInstance");
                    __LOGGER.info("流程数据：" + processInstance.toMap().toString());
                    setProcessInstance(processInstance);
//                    DrawPane drawPane = ShowPanelUtils.generateProcessPane(processInstance);
//                    drawPane.setWidth100();
//                    drawPane.setHeight100();
//                    workflowPanel.addMember(drawPane);
                } else {
					ClientUtil.displayErrorMessage(dsResponse);
				}
            }
        });

        VLayout mainLayout = new VLayout();
        mainLayout.setOverflow(Overflow.AUTO);

        HLayout customLayout = new HLayout();
        customLayout.setWidth100();
        customLayout.setHeight100();
        if (activityType == 2) {
            customLayout.addMember(viewPanel);
        } else {
            customLayout.addMember(businessPanel);
        }

        mainLayout.addMember(customLayout);
//        workflowPanel = new HLayout();
//        workflowPanel.setWidth100();
//        workflowPanel.setHeight(100);
//        mainLayout.addMember(workflowPanel);
        DynamicForm approvalForm = new DynamicForm();
		approvalForm.setWidth100();
		approvalForm.setHeight(80);
		approvalForm.setNumCols(8);
		approvaledComment = new TextAreaItem("approvalCommented", "已审核意见");
		approvaledComment.setWidth("*");
		approvaledComment.setColSpan(4);
		approvaledComment.setRowSpan(2);
		approvaledComment.setTitleOrientation(TitleOrientation.TOP);
		approvaledComment.setDisabled(true);
        
        approvalComment = new TextAreaItem("approvalComment", "审核意见");
        approvalComment.setWidth("*");
        approvalComment.setColSpan(4);
        approvalComment.setRowSpan(2);
        approvalComment.setTitleOrientation(TitleOrientation.TOP);
        approvalForm.setItems(approvaledComment, approvalComment);
        mainLayout.addMember(approvalForm);
        ToolStrip navLayout = new ToolStrip();
        navLayout.setVPolicy(LayoutPolicy.FILL);
        navLayout.setPadding(5);
        navLayout.setHeight(40);
        navLayout.setWidth100();
        mainLayout.addMember(navLayout);
        addItem(mainLayout);
        navLayout.addFill();

        ListGridField employeeIdField = new ListGridField("employeeId");
		ListGridField employeeNoField = new ListGridField("employeeNo");
		ListGridField employeeNameField = new ListGridField("employeeName");
		ListGridField departmentIdField = new ListGridField("departmentId");
		KeyValueManager.loadValueMap("all_departments",departmentIdField);

        selectApprovalment = new ComboBoxItem("employeeId", "请他处理");
        selectApprovalment.setTitleOrientation(TitleOrientation.TOP);
        selectApprovalment.setOptionDataSource(DSSelectGridEmployeeValue.getInstance());
        selectApprovalment.setChangeOnKeypress(false);
        selectApprovalment.setTextMatchStyle(TextMatchStyle.SUBSTRING);
        selectApprovalment.setValueField(employeeIdField.getName());
        selectApprovalment.setDisplayField(employeeNameField.getName());
        selectApprovalment.setPickListFields(departmentIdField, employeeNoField, employeeNameField);
        selectApprovalment.setPickListWidth(500);
        //只查询出在职的员工
        Criteria employeeCondition = new Criteria();
        employeeCondition.addCriteria("status", 0);
        selectApprovalment.setPickListCriteria(employeeCondition);
        selectApprovalment.addChangedHandler(new ChangedHandler(){
            @Override
            public void onChanged(ChangedEvent event) {
                if(event.getValue() != null){
                    rejectButton.setDisabled(true);
                    backMenuButton.setDisabled(true);
                    saveButton.setDisabled(true);
                }else{
                    rejectButton.setDisabled(false);
                    backMenuButton.setDisabled(false);
                    saveButton.setDisabled(false);
                }
            }
        });
        navLayout.addFormItem(selectApprovalment);
        navLayout.addSpacer(10);
        previousButton = createButton("上一步");
        previousButton.setIcon("/pom/sc/skins/Enterprise/images/actions/back.png");
        navLayout.addButton(previousButton);
        navLayout.addSpacer(10);
        previousButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (businessPanel == null) {
                    return;
                }
                businessPanel.previous();
                buttonProcess();
            }
        });

        nextButton = createButton("下一步");
        nextButton.setIcon("/pom/sc/skins/Enterprise/images/actions/forward.png");
        navLayout.addButton(nextButton);
        navLayout.addSpacer(10);
        nextButton.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                if (businessPanel == null) {
                    return;
                }
                businessPanel.next();
                buttonProcess();
            }
        });

        reassignButton = createButton("转交");
        reassignButton.setIcon("[SKIN]/actions/save.png");
        navLayout.addButton(reassignButton);
        navLayout.addSpacer(10);
        reassignButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
            	if(null == selectApprovalment.getValue() || selectApprovalment.getValue().equals(selectApprovalment.getDisplayValue())){
					SC.say("请选择转交接收人!");
					return;
				}
                SC.confirm("你确认转交审批任务吗？", new BooleanCallback() {
                    @Override
                    public void execute(Boolean value) {
                        if (value) {
                            submit(3);
                        }
                    }
                });
            }
        });

        rejectButton = createButton("驳回");
        rejectButton.setIcon("[SKIN]/actions/save.png");
        navLayout.addButton(rejectButton);
        navLayout.addSpacer(10);
        rejectButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                SC.confirm("你确认驳回该申请吗？", new BooleanCallback() {
                    @Override
                    public void execute(Boolean value) {
                        if (value) {
                            submit(1);
                        }
                    }
                });
            }
        });

        Menu menu = new Menu();
		MenuItem returnLastItem = new MenuItem("上个节点");
		returnLastItem.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {

			@Override
			public void onClick(MenuItemClickEvent event) {
				SC.confirm("你确认回退该申请到上一个节点吗？", new BooleanCallback() {
					@Override
					public void execute(Boolean value) {
						if (value) {
							submit(2);
						}
					}
				});
			}
		});
		MenuItem returnFirstItem = new MenuItem("申请人");
		returnFirstItem.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {

			@Override
			public void onClick(MenuItemClickEvent event) {
				SC.confirm("你确定要回退给申请人吗？", new BooleanCallback() {
					@Override
					public void execute(Boolean value) {
						if (value) {
							submit(4);
						}
					}
				});
			}
		});
		menu.setItems(returnLastItem, returnFirstItem);
		backMenuButton = new IMenuButton("回退", menu);
        backMenuButton.setWidth(62);
        backMenuButton.setHeight(31);
		navLayout.addMember(backMenuButton);
		navLayout.addSpacer(10);

        saveButton = createButton("同意");
        saveButton.setIcon("[SKIN]/actions/save.png");
        navLayout.addButton(saveButton);
        navLayout.addSpacer(10);
        saveButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                SC.confirm("你确认同意该申请吗？", new BooleanCallback() {
                    @Override
                    public void execute(Boolean value) {
                        if (value) {
                            submit(0);
                        }
                    }
                });

            }
        });

        ToolStripButton progressButton = createButton("进度");
        progressButton.setIcon("[SKIN]/actions/help.png");
        navLayout.addButton(progressButton);
        navLayout.addSpacer(10);
        progressButton.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                ClientUtil.showWorkFlow(getProcessInstance());
            }

        });

        ToolStripButton cancelButton = createButton("取消");
        cancelButton.setIcon("[SKIN]/actions/close.png");
        navLayout.addButton(cancelButton);
        navLayout.addSpacer(10);
        cancelButton.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                destroy();
            }

        });
        buttonProcess();
		loadApprovaledComment();
    }

    public ToolStripButton createButton(String name) {
        ToolStripButton b = new ToolStripButton(name);
        b.setWidth(80);
        b.setBorder(BORDER_STYLE);
        return b;
    }
    
	public void loadApprovaledComment(){
		String processInstanceId = __instanceData.getAttribute("processInstanceId");
		Map<String, Object> params = new HashMap<>();
		params.put("processInstanceId", processInstanceId);
		params.put("opt_type", "getApprovaledComment");
		DBDataSource.callOperation("EP_CustomSystemWorkFlowProcess", params, new DSCallback() {
			
			@Override
			public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
				approvaledComment.setValue(data);
				
			}
		});
	}

    public void submit(final int approvalment) {
    	if(businessPanel != null){
			businessPanel.setCustomerOperation(approvalment);
		}
        Map params = __instanceData.toMap();
        if(selectApprovalment.getValue() != null){
            params.put("additionalApprovalment", selectApprovalment.getValue());
            params.put("additionalEmployeeName", selectApprovalment.getDisplayValue());
        }
        params.put("approvalment", approvalment);
        params.put("processComment", approvalComment.getValue());
        if (activityType == 3) {
            Map values = businessPanel.getValues();
            if (!businessPanel.checkData(values)) {
                return;
            }
            params.put("userData", values);
        } else if (activityType == 2){
			Map values = viewPanel.getValues();
			params.put("userData", values);
		}
        DBDataSource.callOperation("EP_WorkFlowPooledTaskProcessor", params, new DSCallback() {
            @Override
            public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
            	if(approvalment == 0 && activityType == 3){
					DSCallback callback = businessPanel.getCallback();
					if (callback != null) {
	                    callback.execute(dsResponse, data, dsRequest);
	                }
				}
                if (dsResponse.getStatus() >= 0) {
                    final DataEditEvent evt = new DataEditEvent();
                    if (dsResponse.getData() != null && dsResponse.getData().length > 0) {
                        evt.setData(dsResponse.getData()[0]);
                        DrawPane drawPane = ShowPanelUtils.generateProcessPane(dsResponse.getData()[0]);
                        StandardShowWindow ssw = new StandardShowWindow();
                        ssw.setPanel(drawPane);
                        ssw.show();
                    }
                    fireEvent(evt);
                    destroy();
                } else {
                    SC.say(dsResponse.getErrors().get("errorMsg").toString());
                }
            }
        });
    }

    private int __pageMode = PAGE_DIAPLAY_ONE;
    private int __layoutMode = LAYOUT_TOPBOTTOM;
    private Record __data;
//    private HLayout workflowPanel;
    private TextAreaItem approvalComment;
	private TextAreaItem approvaledComment;
    protected final HandlerManager handlerManager = new HandlerManager(this);

    @Override
    public void fireEvent(GwtEvent<?> event) {
        handlerManager.fireEvent(event);
    }

    public HandlerRegistration addDataEditedHandler(DataEditedHandler handler) {
        return handlerManager.addHandler(DataEditEvent.TYPE, handler);
    }

    private Record processInstance;

    public Record getProcessInstance() {
		return processInstance;
	}

	public void setProcessInstance(Record processInstance) {
		this.processInstance = processInstance;
	}

	/**
     * @return the __processId
     */
    public int getProcessId() {
        return __processId;
    }

    /**
     * @param __processId the __processId to set
     */
    public void setProcessId(int __processId) {
        this.__processId = __processId;
    }

    /**
     * @return the __executeName
     */
    public String getExecuteName() {
        return __executeName;
    }

    /**
     * @param __executeName the __executeName to set
     */
    public void setExecuteName(String __executeName) {
        this.__executeName = __executeName;
    }

    /**
     * @return the __pageMode
     */
    public int getPageMode() {
        return __pageMode;
    }

    /**
     * @param __pageMode the __pageMode to set
     */
    public void setPageMode(int __pageMode) {
        this.__pageMode = __pageMode;
    }

    /**
     * @return the __layoutMode
     */
    public int getLayoutMode() {
        return __layoutMode;
    }

    /**
     * @param __layoutMode the __layoutMode to set
     */
    public void setLayoutMode(int __layoutMode) {
        this.__layoutMode = __layoutMode;
    }

    /**
     * @return the __instanceData
     */
    public Record getInstanceData() {
        return __instanceData;
    }

    /**
     * @param __instanceData the __instanceData to set
     */
    public void setInstanceData(Record __instanceData) {
        this.__instanceData = __instanceData;
    }

    /**
     * @return the __data
     */
    public Record getData() {
        return __data;
    }

    /**
     * @param __data the __data to set
     */
    public void setData(Record __data) {
        this.__data = __data;
    }
}
