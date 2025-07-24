package com.delicacy.client.app.form;

import java.util.*;
import com.delicacy.client.MapUtils;
import com.delicacy.client.app.datasource.DSEmployeeRewardExperience;
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

public class EmployeeDetailEmployeeRewardExperience extends AbstractWizadPage
{

	private static final Logger __logger = Logger.getLogger("");
	private DSCallback __startLoadedDetail;
	private DSCallback __endLoadedDetail;
	private DelicacyListGrid grid = new DelicacyListGrid(true);
	private int employeeId;

	public EmployeeDetailEmployeeRewardExperience(){


		HLayout employeeRewardExperiences = new HLayout();
		employeeRewardExperiences.setWidth100();
		employeeRewardExperiences.setHeight100();
		grid.setHeaderHeight(60); 
		grid.setHeaderSpans(new HeaderSpan[] {
		   new HeaderSpan("奖惩贡献", new String[] {"employeeRewardExperienceId","employeeId","rewardName","rewardDescription","rewardDate","rewardUnit"})
	    });
		grid.setDataSource(DSEmployeeRewardExperience.getInstance());
		grid.setAutoFitFieldWidths(false);
		employeeRewardExperiences.addMember(grid);
		VLayout employeeRewardExperiencesControls = new VLayout();
		employeeRewardExperiencesControls.setHeight100();
		employeeRewardExperiencesControls.setWidth(60);
		employeeRewardExperiencesControls.setLayoutTopMargin(30);
		employeeRewardExperiencesControls.setLayoutLeftMargin(5);
		employeeRewardExperiencesControls.setLayoutRightMargin(5);
		employeeRewardExperiencesControls.setMembersMargin(10);
		employeeRewardExperiences.addMember(employeeRewardExperiencesControls);
		addMember(employeeRewardExperiences);
		IButton employeeRewardExperiencesNewButton = new IButton("新增");
		employeeRewardExperiencesNewButton.setIcon("[SKIN]/actions/add.png");
		employeeRewardExperiencesNewButton.addClickHandler( new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				grid.startEditingNew();
			}
		});
		IButton employeeRewardExperiencesRemoveButton = new IButton("删除所有");
		employeeRewardExperiencesRemoveButton.setIcon("[SKIN]/actions/remove.png");
		employeeRewardExperiencesRemoveButton.addClickHandler( new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				grid.setData(new RecordList());
			}
		});
		employeeRewardExperiencesControls.addMember(employeeRewardExperiencesNewButton);
		employeeRewardExperiencesControls.addMember(employeeRewardExperiencesRemoveButton);
	}

	@Override
	public void startEdit() {
		if(getPageMode() != PAGE_MODE_UPDATE) return;
		reloadDetailTableData();
	}

	public void reloadDetailTableData(){
		Map condition = new HashMap();
		condition.put("employeeId", employeeId);
		DBDataSource.callOperation("ST_EmployeeRewardExperience", "find", condition, new DSCallback(){
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
		res.add("detailEmployeeRewardExperience");
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
		MapUtils.convertRecordToMap(grid.getDataSource(), rows, param, "detailEmployeeRewardExperience");
		return param;
	}

	@Override
	public String getName() {
		return "职员奖惩经历表";
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

