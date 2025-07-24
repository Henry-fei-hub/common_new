package com.delicacy.client.ui;

import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.data.DBDataSource;
import com.delicacy.client.data.DataEditEvent;
import com.delicacy.client.data.DataEditedHandler;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.data.Print;

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
import com.smartgwt.client.types.LayoutPolicy;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.drawing.DrawPane;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 *
 * @author guangxun
 */
public class AttentionWindow extends Window implements HasHandlers {

	private ToolStripButton previousButton;
	private AbstractDetailViewer viewPanel;
	private AbstractProcessPanel businessPanel;
	private AbstractPrint printHtml;
	private ToolStripButton nextButton;
	public ToolStripButton saveButton;
	private int __processId = 0;
	private Record __instanceData;
	private String __executeName;
	private int activityType = 2;
	private static final Logger __LOGGER = Logger.getLogger("");
	private ComboBoxItem selectApprovalment;
	private ToolStripButton reassignButton;
	private ToolStripButton rejectButton;
	private ToolStripButton backButton;
	private ToolStripButton printButton;

	public AttentionWindow() {

	}

	public void buttonProcess() {
		previousButton.setVisible(false);
		nextButton.setVisible(false);
		saveButton.setDisabled(false);
	}

	public void initComponents() {
		// 设置页面属性
		setWidth("90%");
		setHeight("90%");
		// setShowEdges(true);

		setShowMinimizeButton(true);
		setShowMaximizeButton(true);
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
		__LOGGER.info("=====111111111==="+activityType);
		activityType = __instanceData.getAttribute("activityType") == null ? 2
				: __instanceData.getAttributeAsInt("activityType");
		__LOGGER.info("=====activityType==="+activityType);
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
//			viewPanel.setRecord(getInstanceData());
			viewPanel.set__instanceData(getInstanceData());
			viewPanel.initComponents();
		} else if (activityType == 3) {
			businessPanel = workflowProcessor.getProcessPanel("");
			if (businessPanel == null) {
				SC.say("浏览数据面板为空");
				destroy();
				return;
			}
			businessPanel.setLayoutMode(__layoutMode);
			businessPanel.set__instanceData(getInstanceData());
			businessPanel.initComponents();

		}
		printHtml = workflowProcessor.getPrintProcessor();

		Map params = new HashMap();
		params.put("processInstanceId", sprocessInstanceId);
		DBDataSource.callOperation("EP_GetProcessInstance", params, new DSCallback() {
			@Override
			public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
				if (dsResponse.getStatus() >= 0) {
					// __LOGGER.info("666666666");
					Record[] records = dsResponse.getData();
					if (records.length == 0) {
						return;
					}
					Record record = records[0];
					if(null != printHtml)printHtml.setProcessInformation(record);
					setData(record);
					Record businessData = record.getAttributeAsRecord("userData");
					if (activityType == 3) {
						businessPanel.setData(businessData);
						businessPanel.refreshPageData();
					} else {
						viewPanel.setRecord(businessData);
						viewPanel.viewRecord(businessData);
					}
					
					Record processInstance = record.getAttributeAsRecord("processInstance");
					setProcessInstance(processInstance);
//					DrawPane drawPane = ShowPanelUtils.generateProcessPane(processInstance);
//					drawPane.setWidth100();
//					drawPane.setHeight100();
//					workflowPanel.addMember(drawPane);
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
//		workflowPanel = new HLayout();
//		workflowPanel.setWidth100();
//		workflowPanel.setHeight(100);
//		mainLayout.addMember(workflowPanel);

		ToolStrip navLayout = new ToolStrip();
		navLayout.setVPolicy(LayoutPolicy.FILL);
		navLayout.setPadding(5);
		navLayout.setHeight(40);
		navLayout.setWidth100();
		mainLayout.addMember(navLayout);
		addItem(mainLayout);
		navLayout.addFill();
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
				buttonProcess();
			}
		});

		printButton = createButton("打印");
		printButton.setIcon("/pom/sc/skins/Enterprise/images/actions/print.png");
		if (null != printHtml)
			navLayout.addButton(printButton);
		navLayout.addSpacer(10);
		printButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (activityType == 2) {
					printHtml.setPrintData(viewPanel.getValues());
				} else {
					printHtml.setPrintData(businessPanel.getValues());
				}
				Print.it(printHtml.generatePrintHTML());
			}
		});

		saveButton = createButton("已阅");
		saveButton.setIcon("[SKIN]/actions/save.png");
		navLayout.addButton(saveButton);
		navLayout.addSpacer(10);
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				SC.confirm("你确认已经清楚该申请吗？", new BooleanCallback() {
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
	}

	public ToolStripButton createButton(String name) {
		ToolStripButton b = new ToolStripButton(name);
		b.setWidth(80);
		b.setBorder(BORDER_STYLE);
		return b;
	}

	public void submit(int approvalment) {
		Map params = __instanceData.toMap();
		params.put("approvalment", approvalment);
		DBDataSource.callOperation("EP_AttentionProcessor", params, new DSCallback() {
			@Override
			public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
				if (dsResponse.getStatus() >= 0) {
					final DataEditEvent evt = new DataEditEvent();
					if (dsResponse.getData() != null && dsResponse.getData().length > 0) {
						evt.setData(dsResponse.getData()[0]);
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
//	private HLayout workflowPanel;
	private TextAreaItem approvalComment;
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
	 * @param __processId
	 *            the __processId to set
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
	 * @param __executeName
	 *            the __executeName to set
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
	 * @param __pageMode
	 *            the __pageMode to set
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
	 * @param __layoutMode
	 *            the __layoutMode to set
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
	 * @param __instanceData
	 *            the __instanceData to set
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
	 * @param __data
	 *            the __data to set
	 */
	public void setData(Record __data) {
		this.__data = __data;
	}
}
