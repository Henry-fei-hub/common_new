package com.delicacy.client.ui;

import static com.delicacy.client.ui.AbstractProcessPanel.LAYOUT_TOPBOTTOM;
import static com.delicacy.client.ui.AbstractProcessPanel.PAGE_DIAPLAY_ONE;
import static com.delicacy.client.ui.GenericWizadWindow.BORDER_STYLE;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.data.DBDataSource;
import com.delicacy.client.data.DataEditEvent;
import com.delicacy.client.data.DataEditedHandler;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.data.Print;
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
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 *
 * @author guangxun
 */
public class ViewProcessWindow extends Window implements HasHandlers {

	private ToolStripButton previousButton;
	private AbstractDetailViewer businessPanel;
	private ToolStripButton nextButton;
	private ToolStripButton saveButton;
	private AbstractPrint printHtml;
	private int __processId = 0;
	private Record __instanceData;
	private String __executeName;
	private ToolStripButton printButton;
	private ToolStripButton quickLinkButton;
	private ToolStripButton uploadButton;
	private static final Logger __LOGGER = Logger.getLogger("");

	public ViewProcessWindow() {

	}

	protected void buttonProcess() {
		previousButton.setVisible(false);
		nextButton.setVisible(false);
		// saveButton.setDisabled(true);
	}

	public void initComponents() {
		SC.debugger();
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
	    String sprocessInstanceId = __instanceData.getAttribute("processInstanceId");
		String backViewName = __instanceData.getAttribute("backViewName");
		String sprocessType = __instanceData.getAttribute("processType");
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
		businessPanel = workflowProcessor.getViewPanel(backViewName == null ? "" : backViewName, ClientUtil.checkAndGetIntValue(sprocessType));
		if (businessPanel == null) {
			SC.say("新建流程面板为空");
			destroy();
			return;
		}
		printHtml = workflowProcessor.getPrintProcessor();
		businessPanel.setRecord(__instanceData);
		businessPanel.set__instanceData(__instanceData);
		businessPanel.setLayoutMode(__layoutMode);
		businessPanel.initComponents();
		businessPanel.viewRecord(new Record(), false);
		// businessPanel.startEdit();

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
					if (null != printHtml)
						printHtml.setProcessInformation(record);
					setData(record);
					Record businessData = record.getAttributeAsRecord("userData");
					businessPanel.viewRecord(businessData);
					Record processInstance = record.getAttributeAsRecord("processInstance");
					setProcessInstance(processInstance);
					// DrawPane drawPane =
					// ShowPanelUtils.generateProcessPane(processInstance);
					// drawPane.setWidth100();
					// drawPane.setHeight100();
					// workflowPanel.addMember(drawPane);
				} else {
					ClientUtil.displayErrorMessage(dsResponse);
				}
			}
		});


		VLayout mainLayout = new VLayout();
		// mainLayout.setOverflow(Overflow.VISIBLE);

		HLayout customLayout = new HLayout();
		customLayout.setWidth100();
		customLayout.setHeight100();
		customLayout.addMember(businessPanel);

		mainLayout.addMember(customLayout);
		// workflowPanel = new HLayout();
		// workflowPanel.setWidth100();
		// workflowPanel.setHeight(100);
		// mainLayout.addMember(workflowPanel);
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

		quickLinkButton = createButton("企业微信审批链接");
		navLayout.addButton(quickLinkButton);
		navLayout.addSpacer(10);
		quickLinkButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Map<String, Object> params = new HashMap<>();
				params.put("processInstanceId", __instanceData.getAttribute("processInstanceId"));
				params.put("opt_type", "createQuickLinkToProcess");
				DBDataSource.callOperation("EP_CheckUserinfoToLogin", params, new DSCallback() {
					
					@Override
					public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
						SC.say("拷贝下面的链接通过企业微信发给需要审批的人(快速点击3次链接快速全选)", data.toString());
					}
				});
				
			}
		});

		printButton = createButton("打印");
		printButton.setIcon("silk/printer.png");
		if (null != printHtml){
			navLayout.addButton(printButton);
			navLayout.addSpacer(10);
		}
		printButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				printHtml.setPrintData(businessPanel.getValues());
				Print.it(printHtml.generatePrintHTML());
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

		ToolStripButton cancelButton = createButton("关闭");
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
	// private HLayout workflowPanel;
	protected final HandlerManager handlerManager = new HandlerManager(this);
	private Record processInstance;

	public Record getProcessInstance() {
		return processInstance;
	}

	public void setProcessInstance(Record processInstance) {
		this.processInstance = processInstance;
	}

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
