package com.delicacy.client.app.panel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.delicacy.client.MapUtils;
import com.delicacy.client.data.DBDataSource;
import com.delicacy.client.data.DataEditEvent;
import com.delicacy.client.data.DataEditedHandler;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.department.datasource.DSDepartmentRole;
import com.delicacy.client.ui.AbstractWizadPage;
import com.delicacy.client.ui.DelicacyListGrid;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.DataChangedEvent;
import com.smartgwt.client.widgets.grid.events.DataChangedHandler;
import com.smartgwt.client.widgets.layout.VLayout;

public class DepartmentLinkRolePanel extends AbstractWizadPage implements HasHandlers {

	private static final Logger __LOGGER = Logger.getLogger("");

	public final static String BORDER_STYLE = "1px solid #ABABAB";

	public DelicacyListGrid grid;
	private RoleListPanel leftPanel;
	private boolean addData = false;
	private DSCallback __startLoadedDetail;
	private DSCallback __endLoadedDetail;
	

	public DepartmentLinkRolePanel() {
		setBorder(BORDER_STYLE);
		grid = new DelicacyListGrid(true);
		int idx = 0;
		ListGridField[] fields = new ListGridField[3];
		fields[idx] = new ListGridField("departmentRoleId");
		fields[idx].setHidden(true);
		idx++;
		fields[idx] = new ListGridField("roleId");
		KeyValueManager.loadValueMap("roles", fields[idx]);
		idx++;
		fields[idx] = new ListGridField("departmentId");
		KeyValueManager.loadValueMap("all_departments", fields[idx]);
		grid.setFields(fields);
		grid.setSelectionAppearance(SelectionAppearance.ROW_STYLE);
		grid.setDataSource(DSDepartmentRole.getInstance());
		grid.setSelectionType(SelectionStyle.SINGLE);
		grid.setCanEdit(false);
		grid.setCanSelectText(true);
		grid.setCanDragSelectText(true);
		grid.setShowRowNumbers(true);
		grid.setAutoFitFieldWidths(false);
		grid.addDataChangedHandler(new DataChangedHandler() {
			
			@Override
			public void onDataChanged(DataChangedEvent event) {
				if(addData) {
					addData = false;
				}else {
					if(null != leftPanel) {
						leftPanel.commonQuery();
					}
				}
			}
		});
		
		VLayout mainLayout = new VLayout();
		mainLayout.setWidth100();
		mainLayout.setHeight100();
        
		mainLayout.addMember(grid);
		addMember(mainLayout);
	}

	
	@Override
	public Map getValuesAsMap() {
		Map param = new HashMap();
		ListGridRecord[] rows = grid.getRecords();
		MapUtils.convertRecordToMap(grid.getDataSource(), rows, param, "detailDepartmentRole");
		return param;
	}

	@Override
	public boolean checkData() {
		boolean flag = true;
		ListGridRecord [] record = grid.getRecords();
		for (ListGridRecord listGridRecord : record) {
			int index =grid.getRowNum(listGridRecord);
			if(!grid.validateRow(index)){
				flag =false;
				break;
			}
		}
		return flag;
	}

	@Override
	public void startEdit() {
		if(getPageMode() != PAGE_MODE_UPDATE) return;
		reloadDetailTableData();
	}
	
	public void reloadDetailTableData(){
		Map<String, String> condition = new HashMap<>();
		condition.put("departmentId", getRecord().getAttribute("departmentId"));
		DBDataSource.callOperation("ST_DepartmentRole", "find", condition, new DSCallback(){
			@Override
			public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
				if(dsResponse.getStatus() >= 0) {
					if(__startLoadedDetail != null) __startLoadedDetail.execute(dsResponse, data, dsRequest);
					grid.setData(dsResponse.getData());
					if(__endLoadedDetail != null) __endLoadedDetail.execute(dsResponse, data, dsRequest);
				}
			}
		});
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

	public RoleListPanel getLeftPanel() {
		return leftPanel;
	}

	public void setLeftPanel(RoleListPanel leftPanel) {
		this.leftPanel = leftPanel;
	}

	public boolean isAddData() {
		return addData;
	}

	public void setAddData(boolean addData) {
		this.addData = addData;
	}
	
	public DSCallback getStartLoadedDetail() {
		return this.__startLoadedDetail;
	}

	public void setStartLoadedDetail( DSCallback value ) {
		this.__startLoadedDetail = value;
	}

	public DSCallback getEndLoadedDetail() {
		return this.__endLoadedDetail;
	}

	public void setEndLoadedDetail( DSCallback value ) {
		this.__endLoadedDetail = value;
	}
}
