package com.delicacy.client.app.form;

import java.util.*;
import com.delicacy.client.MapUtils;
import com.delicacy.client.app.datasource.DSEmployeeFamilyInformation;
import com.delicacy.client.ui.AbstractWizadPage;
import com.delicacy.client.ui.DelicacyListGrid;
import com.delicacy.client.data.DBDataSource;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.widgets.grid.HeaderSpan;
import java.util.logging.Logger;

public class EmployeeDetailEmployeeFamilyInformation extends AbstractWizadPage
{

	private static final Logger __logger = Logger.getLogger("");
	private DSCallback __startLoadedDetail;
	private DSCallback __endLoadedDetail;
	private DelicacyListGrid grid = new DelicacyListGrid(true);
	private int employeeId;

	public EmployeeDetailEmployeeFamilyInformation(){


		HLayout employeeFamilyInformations = new HLayout();
		employeeFamilyInformations.setWidth100();
		employeeFamilyInformations.setHeight100();
		grid.setHeaderHeight(60); 
		grid.setHeaderSpans(new HeaderSpan[] {
		   new HeaderSpan("家庭成员", new String[] {"employeeFamilyInformationId","employeeId","familyMemberName","relation","familyWorkPlace","telephone"})
	    });
		grid.setDataSource(DSEmployeeFamilyInformation.getInstance());
		grid.setAutoFitFieldWidths(false);
		employeeFamilyInformations.addMember(grid);
		VLayout employeeFamilyInformationsControls = new VLayout();
		employeeFamilyInformationsControls.setHeight100();
		employeeFamilyInformationsControls.setWidth(60);
		employeeFamilyInformationsControls.setLayoutTopMargin(30);
		employeeFamilyInformationsControls.setLayoutLeftMargin(5);
		employeeFamilyInformationsControls.setLayoutRightMargin(5);
		employeeFamilyInformationsControls.setMembersMargin(10);
		employeeFamilyInformations.addMember(employeeFamilyInformationsControls);
		addMember(employeeFamilyInformations);
		IButton employeeFamilyInformationsNewButton = new IButton("新增");
		employeeFamilyInformationsNewButton.setIcon("[SKIN]/actions/add.png");
		employeeFamilyInformationsNewButton.addClickHandler( new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				grid.startEditingNew();
			}
		});
		IButton employeeFamilyInformationsRemoveButton = new IButton("删除所有");
		employeeFamilyInformationsRemoveButton.setIcon("[SKIN]/actions/remove.png");
		employeeFamilyInformationsRemoveButton.addClickHandler( new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				grid.setData(new RecordList());
			}
		});
		employeeFamilyInformationsControls.addMember(employeeFamilyInformationsNewButton);
		employeeFamilyInformationsControls.addMember(employeeFamilyInformationsRemoveButton);
	}

	@Override
	public void startEdit() {
		if(getPageMode() != PAGE_MODE_UPDATE) return;
		reloadDetailTableData();
	}

	public void reloadDetailTableData(){
		Map condition = new HashMap();
		condition.put("employeeId", employeeId);
		DBDataSource.callOperation("ST_EmployeeFamilyInformation", "find", condition, new DSCallback(){
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

	@Override
	public boolean checkData() {
		for(ListGridRecord r : grid.getRecords()) {
			__logger.info(MapUtils.convertRecordToMap(grid.getDataSource(), r).toString());
		}
		return true;
	}

	@Override
	public Set<String> getItemNames() {
		Set<String> res = new HashSet<>();
		res.add("detailEmployeeFamilyInformation");
		return res;
	}

	@Override
	public boolean isTheValuesEmpty() {
		ListGridRecord[] rows = grid.getRecords();
		return rows == null || rows.length == 0;
	}

	@Override
	public Map getValuesAsMap() {
		Map param = new HashMap();
		ListGridRecord[] rows = grid.getRecords();
		MapUtils.convertRecordToMap(grid.getDataSource(), rows, param, "detailEmployeeFamilyInformation");
		return param;
	}

	@Override
	public String getName() {
		return "职员家庭成员表";
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

	public DelicacyListGrid getGrid() {
		return this.grid;
	}

	public void setGrid( DelicacyListGrid value ) {
		this.grid = value;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

}

