package com.delicacy.client.app.form;

import java.util.*;
import com.delicacy.client.MapUtils;
import com.delicacy.client.app.datasource.DSEmployeeWorkExperience;
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

public class EmployeeDetailEmployeeWorkExperience extends AbstractWizadPage
{

	private static final Logger __logger = Logger.getLogger("");
	private DSCallback __startLoadedDetail;
	private DSCallback __endLoadedDetail;
	private DelicacyListGrid grid = new DelicacyListGrid(true);
	private int employeeId;

	public EmployeeDetailEmployeeWorkExperience(){


		HLayout employeeWorkExperiences = new HLayout();
		employeeWorkExperiences.setWidth100();
		employeeWorkExperiences.setHeight100();
		grid.setHeaderHeight(60); 
		grid.setHeaderSpans(new HeaderSpan[] {
		   new HeaderSpan("工作经历", new String[] {"employeeWorkExperienceId","employeeId","workPlace","workexStartDate","workexEndDate","position","reasonOfLeaving","isForeignCompany"})
	    });
		grid.setDataSource(DSEmployeeWorkExperience.getInstance());
		grid.setAutoFitFieldWidths(false);
		employeeWorkExperiences.addMember(grid);
		VLayout employeeWorkExperiencesControls = new VLayout();
		employeeWorkExperiencesControls.setHeight100();
		employeeWorkExperiencesControls.setWidth(60);
		employeeWorkExperiencesControls.setLayoutTopMargin(30);
		employeeWorkExperiencesControls.setLayoutLeftMargin(5);
		employeeWorkExperiencesControls.setLayoutRightMargin(5);
		employeeWorkExperiencesControls.setMembersMargin(10);
		employeeWorkExperiences.addMember(employeeWorkExperiencesControls);
		addMember(employeeWorkExperiences);
		IButton employeeWorkExperiencesNewButton = new IButton("新增");
		employeeWorkExperiencesNewButton.setIcon("[SKIN]/actions/add.png");
		employeeWorkExperiencesNewButton.addClickHandler( new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				grid.startEditingNew();
			}
		});
		IButton employeeWorkExperiencesRemoveButton = new IButton("删除所有");
		employeeWorkExperiencesRemoveButton.setIcon("[SKIN]/actions/remove.png");
		employeeWorkExperiencesRemoveButton.addClickHandler( new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				grid.setData(new RecordList());
			}
		});
		employeeWorkExperiencesControls.addMember(employeeWorkExperiencesNewButton);
		employeeWorkExperiencesControls.addMember(employeeWorkExperiencesRemoveButton);
	}

	@Override
	public void startEdit() {
		if(getPageMode() != PAGE_MODE_UPDATE) return;
		reloadDetailTableData();
	}

	public void reloadDetailTableData(){
		Map condition = new HashMap();
		condition.put("employeeId", employeeId);
		DBDataSource.callOperation("ST_EmployeeWorkExperience", "find", condition, new DSCallback(){
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
		res.add("detailEmployeeWorkExperience");
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
		MapUtils.convertRecordToMap(grid.getDataSource(), rows, param, "detailEmployeeWorkExperience");
		return param;
	}

	@Override
	public String getName() {
		return "职员工作经历表";
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

