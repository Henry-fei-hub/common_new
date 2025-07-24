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
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class SetDepartmentRolePanel extends AbstractWizadPage implements HasHandlers{
	
	private final RoleListPanel rolePanel;//
	
	private final DepartmentLinkRolePanel departmentLinkRolePanel;//
	
	private static final Logger __LOGGER = Logger.getLogger("");
	

	public SetDepartmentRolePanel(){
		VLayout mainLayout = new VLayout();
		
		HLayout allH =new HLayout();
		allH.setWidth100();
		allH.setHeight100();
		
		VLayout leftLayout = new VLayout();//左边
		leftLayout.setWidth("50%");
		leftLayout.setHeight100();
		
		VLayout rightLayout = new VLayout();//右边
		rightLayout.setWidth("50%");
		rightLayout.setHeight100();
		
		
		rolePanel = new RoleListPanel();
		rolePanel.setWidth100();
		rolePanel.setHeight100();
		rolePanel.addDataEditedHandler(new DataEditedHandler() {
			
			@Override
			public void onDataEdited(DataEditEvent event) {
				DelicacyListGrid fromGrid = rolePanel.getGrid();
				
				if(!fromGrid.anySelected()){
					SC.say("请选择适用于本部门的角色");
					return;
				}
				DelicacyListGrid toGrid = departmentLinkRolePanel.grid;
				Record [] formRecords = fromGrid.getSelectedRecords();
				Record [] toRecords = toGrid.getRecords();
				for (Record record : formRecords) {
					boolean flag = true;
					for (Record record2 : toRecords) {
						String roleId = record.getAttribute("roleId");
						String roleId2 = record2.getAttribute("roleId");
						if(roleId.equals(roleId2)){
							flag = false;
							break;
						}
					}
					if(flag){
						Record data = new Record();
						data.setAttribute("roleId", record.getAttribute("roleId"));
						departmentLinkRolePanel.setAddData(true);
						toGrid.addData(data);
					}
					fromGrid.removeData(record);
				}
			}
		});
		
		departmentLinkRolePanel= new DepartmentLinkRolePanel();
		departmentLinkRolePanel.setLeftPanel(rolePanel);
		departmentLinkRolePanel.setWidth100();
		departmentLinkRolePanel.setHeight100();
		departmentLinkRolePanel.addDataEditedHandler(new DataEditedHandler() {
			
			@Override
			public void onDataEdited(DataEditEvent event) {
				
			}
		});
		
		rolePanel.setLinkPanel(departmentLinkRolePanel);
		leftLayout.addMember(rolePanel);
		rightLayout.addMember(departmentLinkRolePanel);
		
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
		__LOGGER.info("角色数据:"+departmentLinkRolePanel.getValuesAsMap().toString());
		return departmentLinkRolePanel.getValuesAsMap();
	}

	@Override
	public boolean checkData() {
		return true;
	}

	@Override
	public void startEdit() {
		departmentLinkRolePanel.setPageMode(getPageMode());
		departmentLinkRolePanel.setRecord(getRecord());
		departmentLinkRolePanel.startEdit();
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
