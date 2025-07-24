package com.delicacy.client.ui;

import com.delicacy.client.BaseHelpUtils;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.data.DBDataSource;
import com.delicacy.client.data.DataEditEvent;
import com.delicacy.client.data.DataEditedHandler;
import com.delicacy.client.data.KeyValueManager;

import static com.delicacy.client.ui.AbstractProcessPanel.LAYOUT_TOPBOTTOM;
import static com.delicacy.client.ui.AbstractProcessPanel.PAGE_DIAPLAY_ONE;
import static com.delicacy.client.ui.GenericWizadWindow.BORDER_STYLE;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.LayoutPolicy;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.drawing.DrawPane;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author guangxun
 */
public class EditProcessWindow extends Window implements HasHandlers {

    private ToolStripButton previousButton;
    private AbstractProcessPanel businessPanel;
    private ToolStripButton nextButton;
    private ToolStripButton saveButton;
    private int __processId = 0;
    private Record __instanceData;
    private String __executeName;
    private static final Logger __LOGGER = Logger.getLogger("");

    public EditProcessWindow() {

    }

    protected void buttonProcess() {

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
    }

    public void initComponents() {
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
        String sprocessInstanceId = __instanceData.getAttribute("processInstanceId");
        __executeName = KeyValueManager.getValue("processExecutors", sprocessId);
        if (__executeName == null) {
            SC.say("没有找到相应的流程处理程序");
            destroy();
            return;
        }
        WorkflowProcessor workflowProcessor = Workflows.getProcessor(__executeName);
        if (workflowProcessor == null) {
            SC.say("没有找到注册的流程处理程序");
            destroy();
            return;
        }
        businessPanel = workflowProcessor.getProcessPanel(null);
        if (businessPanel == null) {
            SC.say("新建流程面板为空");
            destroy();
            return;
        }
        businessPanel.setPageMode(__pageMode);
        businessPanel.setLayoutMode(__layoutMode);
        businessPanel.setProcessId(__processId);
        businessPanel.setProcessTypeId( __instanceData.getAttributeAsInt("processType"));
        businessPanel.set__instanceData(__instanceData);
        businessPanel.initComponents();
//        businessPanel.startEdit();

        setWidth(businessPanel.getCustomWidth());
        setHeight(businessPanel.getCustomHeight());
        centerInPage();
        setAlign(Alignment.CENTER);

        Map params = new HashMap();
        params.put("processInstanceId", sprocessInstanceId);
        DBDataSource.callOperation("EP_GetProcessInstance", params, new DSCallback() {
            @Override
            public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
                if (dsResponse.getStatus() >= 0) {
                    Record[] records = dsResponse.getData();
                    if (records.length == 0) return;
                    Record record = records[0];
                    setData(record);
                    Record businessData = record.getAttributeAsRecord("userData");
                    businessPanel.setData(businessData);
                    businessPanel.refreshPageData();
                    Record processInstance = record.getAttributeAsRecord("processInstance");
                    Record[] instanceActivities = processInstance.getAttributeAsRecordArray("activities");
                    for (Record r : instanceActivities) {
                        int nodeType = ClientUtil.checkAndGetIntValue(r.getAttribute("nodeType"));
                        if (nodeType == 0) continue;
                        selectApprovalment.setValue(r.getAttribute("employeeId"));
                        break;
                    }
                    DrawPane drawPane = ShowPanelUtils.generateProcessPane(processInstance);
                    drawPane.setWidth100();
                    drawPane.setHeight100();
                    workflowPanel.addMember(drawPane);
                }
            }
        });

        VLayout mainLayout = new VLayout();
        mainLayout.setOverflow(Overflow.AUTO);

        HLayout customLayout = new HLayout();
        customLayout.setWidth100();
        customLayout.setHeight100();
        customLayout.addMember(businessPanel);

        mainLayout.addMember(customLayout);
        workflowPanel = new HLayout();
        workflowPanel.setWidth100();
        workflowPanel.setHeight(100);
        mainLayout.addMember(workflowPanel);
        ToolStrip navLayout = new ToolStrip();
        navLayout.setVPolicy(LayoutPolicy.FILL);
        navLayout.setPadding(5);
        navLayout.setHeight(40);
        navLayout.setWidth100();
        mainLayout.addMember(navLayout);

        addItem(mainLayout);
        navLayout.addFill();

        Label label = new Label();
        label.setContents("已内嵌审批流程，如有额外审批人请添加在此处→");
        label.setWidth100();
        label.setAlign(Alignment.RIGHT);
        navLayout.addMember(label);

        selectApprovalment = new ComboBoxItem("employeeId", "增加审批人");
        selectApprovalment.setTitleOrientation(TitleOrientation.TOP);
        selectApprovalment.setValueMap(KeyValueManager.getValueMap("on_job_employees"));
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

        saveButton = createButton("提交");
        saveButton.setIcon("[SKIN]/actions/save.png");
        navLayout.addButton(saveButton);
        navLayout.addSpacer(10);
        saveButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (businessPanel == null) {
                    return;
                }
                Map values = businessPanel.getValues();
                if (!businessPanel.checkData(values)) {
                    return;
                }
                saveButton.setDisabled(true);
                Map params = new HashMap();
                if (getData() != null) {
                    params = getData().toMap();
                }
                if (selectApprovalment.getValue() != null) {
                    params.put("approval", selectApprovalment.getValue());
                }
                params.put("userData", values);
                DBDataSource.callOperation("EP_WorkFlowModification", params, new DSCallback() {
                    @Override
                    public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
                    	saveButton.setDisabled(false);
                        if (businessPanel.getCallback() != null) {
                            businessPanel.getCallback().execute(dsResponse, data, dsRequest);
                        }

                        if (dsResponse.getStatus() >= 0) {
                            final DataEditEvent evt = new DataEditEvent();
                            if (dsResponse.getData() != null && dsResponse.getData().length > 0) {
                                evt.setData(dsResponse.getData()[0]);
                                //ClientUtil.showWorkFlow(dsResponse.getData()[0]);
                            } else {
                                evt.setData(new Record(businessPanel.getValues()));
                            }
                            fireEvent(evt);
                            destroy();
                        } else {
                            SC.say(dsResponse.getErrors().get("errorMsg").toString());
                        }

                    }
                });
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
    }

    public ToolStripButton createButton(String name) {
        ToolStripButton b = new ToolStripButton(name);
        b.setWidth(80);
        b.setBorder(BORDER_STYLE);
        return b;
    }

    private int __pageMode = PAGE_DIAPLAY_ONE;
    private int __layoutMode = LAYOUT_TOPBOTTOM;
    private ComboBoxItem selectApprovalment;
    private Record __data;
    private HLayout workflowPanel;
    protected final HandlerManager handlerManager = new HandlerManager(this);

    @Override
    public void fireEvent(GwtEvent<?> event) {
        handlerManager.fireEvent(event);
    }

    public HandlerRegistration addDataEditedHandler(DataEditedHandler handler) {
        return handlerManager.addHandler(DataEditEvent.TYPE, handler);
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
