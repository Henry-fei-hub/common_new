package com.delicacy.client.app.panel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.delicacy.client.data.DataEditEvent;
import com.delicacy.client.data.DataEditedHandler;
import com.delicacy.client.ui.AbstractWizadPage;
import com.delicacy.client.ui.DelicacyListGrid;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.LayoutPolicy;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;

public class SetDepartmentProcessPanel extends AbstractWizadPage implements HasHandlers{
	
	private final SystemProcessListPanel processPanel;//
	
	private final DepartmentLinkProcessPanel departmentLinkProcessPanel;//
	
    public final static String BORDER_STYLE = "1px solid gray";
    public final static String BACKGROUND_COLOR = "azure";
    public final static String BUTTON_BACKGROUND_COLOR = "azure";
    public final static String FORM_BORDER_STYLE = "1px solid #808080";
    
    
	private static final Logger __LOGGER = Logger.getLogger("");
	

	public SetDepartmentProcessPanel(){
//		setBorder(BORDER_STYLE);
//		setBackgroundColor(BACKGROUND_COLOR);
		VLayout mainLayout = new VLayout();
		
		HLayout allH =new HLayout();
		allH.setWidth100();
		allH.setHeight100();
		allH.setMargin(5);
		
		VLayout leftLayout = new VLayout();//左边
		leftLayout.setWidth("50%");
		leftLayout.setHeight100();
		
		VLayout rightLayout = new VLayout();//右边
		rightLayout.setWidth("50%");
		rightLayout.setHeight100();
		
		
		processPanel = new SystemProcessListPanel();
		processPanel.setWidth100();
		processPanel.setHeight100();
		processPanel.addDataEditedHandler(new DataEditedHandler() {
			
			@Override
			public void onDataEdited(DataEditEvent event) {
				DelicacyListGrid fromGrid = processPanel.getGrid();
				
				if(!fromGrid.anySelected()){
					SC.say("请选择适用于本部门的流程");
					return;
				}
				DelicacyListGrid toGrid = departmentLinkProcessPanel.grid;
				Record [] formRecords = fromGrid.getSelectedRecords();
				Record [] toRecords = toGrid.getRecords();
				for (Record record : formRecords) {
					boolean flag = true;
					for (Record record2 : toRecords) {
						String processId = record.getAttribute("processId");
						String processId2 = record2.getAttribute("processId");
						if(processId.equals(processId2)){
							flag = false;
							break;
						}
					}
					if(flag){
						Record data = new Record();
						data.setAttribute("processId", record.getAttribute("processId"));
						departmentLinkProcessPanel.setAddData(true);
						toGrid.addData(data);
					}
					fromGrid.removeData(record);
				}
			}
		});
		
		departmentLinkProcessPanel= new DepartmentLinkProcessPanel();
		departmentLinkProcessPanel.setLeftPanel(processPanel);
		departmentLinkProcessPanel.setWidth100();
		departmentLinkProcessPanel.setHeight100();
		departmentLinkProcessPanel.addDataEditedHandler(new DataEditedHandler() {
			
			@Override
			public void onDataEdited(DataEditEvent event) {
				
			}
		});
		
		processPanel.setLinkPanel(departmentLinkProcessPanel);
		leftLayout.addMember(processPanel);
		rightLayout.addMember(departmentLinkProcessPanel);
		
		// 底部工具栏添加
//		ToolStrip btnLayout = new ToolStrip();
//		btnLayout.setVPolicy(LayoutPolicy.FILL);
//		btnLayout.setPadding(5);
//		btnLayout.setHeight(40);
//		btnLayout.setWidth100();
//		btnLayout.addFill();
//		
//		final IButton splitBtn = new IButton("确定");
//		btnLayout.addMember(splitBtn);
//		btnLayout.addSpacer(10);
//		splitBtn.addClickHandler(new ClickHandler() {
//			
//			@Override
//			public void onClick(ClickEvent event) {
//				splitBtn.setDisabled(true);
//				final DelicacyListGrid grid = departmentLinkProcessPanel.grid;
//				
//			}
//		});
//		
//		IButton cancelBtn = new IButton("取消");
//		btnLayout.addMember(cancelBtn);
//		btnLayout.addSpacer(10);
//		cancelBtn.addClickHandler(new ClickHandler() {
//
//			@Override
//			public void onClick(ClickEvent event) {
//				SC.ask("提示", "确认取消?", new BooleanCallback() {
//					@Override
//					public void execute(Boolean value) {
//						if (value) {
//							getFatherWindow().destroy();
//						}
//					}
//				});
//			}
//		});
		
		allH.addMember(leftLayout);
		allH.addMember(rightLayout);
		mainLayout.addMember(allH);
		
		VLayout allLayout = new VLayout();
		allLayout.setHeight100();
		allLayout.setWidth100();
		allLayout.addMember(mainLayout);
//		allLayout.addMember(btnLayout);
		addMember(allLayout);
		
	}

	@Override
	public Map getValuesAsMap() {
		__LOGGER.info("部门数据:"+departmentLinkProcessPanel.getValuesAsMap().toString());
		return departmentLinkProcessPanel.getValuesAsMap();
	}

	@Override
	public boolean checkData() {
		return true;
	}

	@Override
	public void startEdit() {
		departmentLinkProcessPanel.setPageMode(getPageMode());
		departmentLinkProcessPanel.setRecord(getRecord());
//		departmentLinkProcessPanel.setDepartmentId(departmentId);
		departmentLinkProcessPanel.startEdit();
	}
	
	private Window fatherWindow;

	public Window getFatherWindow() {
		return fatherWindow;
	}

	public void setFatherWindow(Window fatherWindow) {
		this.fatherWindow = fatherWindow;
	}
	
	protected final HandlerManager handlerManager = new HandlerManager(this);
    protected final List<DataEditedHandler> __dataEditedHandler = new ArrayList<>();

    @Override
    public void fireEvent(GwtEvent<?> event) {
        handlerManager.fireEvent(event);
    }

    public HandlerRegistration addDataEditedHandler(DataEditedHandler handler) {
        return handlerManager.addHandler(DataEditEvent.TYPE, handler);
    }

}
