package com.delicacy.client.app.form;

import java.util.*;
import com.delicacy.client.MapUtils;
import com.delicacy.client.app.datasource.DSEmployeeTechnicalTitle;
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

public class EmployeeDetailEmployeeTechnicalTitle extends AbstractWizadPage
{

	private static final Logger __logger = Logger.getLogger("");
	private DSCallback __startLoadedDetail;
	private DSCallback __endLoadedDetail;
	private DelicacyListGrid grid = new DelicacyListGrid(true);
	private int employeeId;

	public EmployeeDetailEmployeeTechnicalTitle(){


		HLayout employeeTechnicalTitles = new HLayout();
		employeeTechnicalTitles.setWidth100();
		employeeTechnicalTitles.setHeight100();
		grid.setHeaderHeight(60); 
		grid.setHeaderSpans(new HeaderSpan[] {
		   new HeaderSpan("技术职称", new String[] {"employeeTechnicalTitleId","employeeId","technicalTitles","technicalSpecialty","technicalLevel","assessmentTime"})
	    });
		grid.setDataSource(DSEmployeeTechnicalTitle.getInstance());
		grid.setAutoFitFieldWidths(false);
		employeeTechnicalTitles.addMember(grid);
		VLayout employeeTechnicalTitlesControls = new VLayout();
		employeeTechnicalTitlesControls.setHeight100();
		employeeTechnicalTitlesControls.setWidth(60);
		employeeTechnicalTitlesControls.setLayoutTopMargin(30);
		employeeTechnicalTitlesControls.setLayoutLeftMargin(5);
		employeeTechnicalTitlesControls.setLayoutRightMargin(5);
		employeeTechnicalTitlesControls.setMembersMargin(10);
		employeeTechnicalTitles.addMember(employeeTechnicalTitlesControls);
		addMember(employeeTechnicalTitles);
		IButton employeeTechnicalTitlesNewButton = new IButton("新增");
		employeeTechnicalTitlesNewButton.setIcon("[SKIN]/actions/add.png");
		employeeTechnicalTitlesNewButton.addClickHandler( new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				grid.startEditingNew();
			}
		});
		IButton employeeTechnicalTitlesRemoveButton = new IButton("删除所有");
		employeeTechnicalTitlesRemoveButton.setIcon("[SKIN]/actions/remove.png");
		employeeTechnicalTitlesRemoveButton.addClickHandler( new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				grid.setData(new RecordList());
			}
		});
		employeeTechnicalTitlesControls.addMember(employeeTechnicalTitlesNewButton);
		employeeTechnicalTitlesControls.addMember(employeeTechnicalTitlesRemoveButton);
	}

	@Override
	public void startEdit() {
		if(getPageMode() != PAGE_MODE_UPDATE) return;
		reloadDetailTableData();
	}

	public void reloadDetailTableData(){
		Map condition = new HashMap();
		condition.put("employeeId", employeeId);
		DBDataSource.callOperation("ST_EmployeeTechnicalTitle", "find", condition, new DSCallback(){
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
		res.add("detailEmployeeTechnicalTitle");
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
		MapUtils.convertRecordToMap(grid.getDataSource(), rows, param, "detailEmployeeTechnicalTitle");
		return param;
	}

	@Override
	public String getName() {
		return "职员技术职称表";
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

