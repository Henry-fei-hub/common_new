package com.delicacy.client.management.form;

import java.util.*;
import com.delicacy.client.MapUtils;
import com.delicacy.client.ui.AbstractWizadPage;
import com.delicacy.client.ui.DelicacyListGrid;
import com.smartgwt.client.data.Criteria;
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
import com.smartgwt.client.widgets.events.DoubleClickEvent;
import com.smartgwt.client.widgets.events.DoubleClickHandler;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.widgets.TransferImgButton;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeNode;
import java.util.logging.Logger;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.delicacy.client.management.datasource.DSDepartmentRole;
import com.delicacy.client.management.datasource.DSRole;

public class DepartmentDetailDepartmentRole extends AbstractWizadPage
{

	private static final Logger __logger = Logger.getLogger("");
	private DSCallback __startLoadedDetail;
	private DSCallback __endLoadedDetail;
	private DelicacyListGrid grid = new DelicacyListGrid(true);
	private DelicacyListGrid SourceGrid = new DelicacyListGrid();

	public DepartmentDetailDepartmentRole(){


		VLayout SearchSourceLayout = new VLayout();
		SearchSourceLayout.setWidth100();
		ListGridField[] fields = new ListGridField[1];
		int idx = 0;
		fields[idx] = new ListGridField("roleName");
		idx ++;
		SourceGrid.setFields(fields);
		SourceGrid.setSelectionAppearance(SelectionAppearance.CHECKBOX);
		SourceGrid.setDataSource(DSRole.getInstance());
		SourceGrid.setShowFilterEditor(true);
		SourceGrid.setFilterOnKeypress(true);
		SearchSourceLayout.setHeight100();
		SearchSourceLayout.setLayoutTopMargin(10);
		SearchSourceLayout.setLayoutRightMargin(5);
		SearchSourceLayout.setMembersMargin(10);
		SearchSourceLayout.addMember(SourceGrid);
		addMember(SearchSourceLayout);
	}

	@Override
	public void startEdit() {
		DBDataSource.callOperation("ST_Role", "find", new HashMap(), new DSCallback(){
			@Override
			public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
				if(dsResponse.getStatus() >= 0) {
					SourceGrid.setData(dsResponse.getData());
					if (getRecord() != null) reloadDetailTableData();
				}
			}
		});
	}

	public void reloadDetailTableData(){
		SourceGrid.deselectAllRecords();
		Map condition = new HashMap();
		condition.put("departmentId", getRecord().getAttribute("departmentId"));
		DBDataSource.callOperation("ST_DepartmentRole", "find", condition, new DSCallback(){
			@Override
			public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
				if(dsResponse.getStatus() >= 0) {
					if(__startLoadedDetail != null) __startLoadedDetail.execute(dsResponse, data, dsRequest);
					int len = dsResponse.getData().length;
					if(len == 0) { if(__endLoadedDetail != null) __endLoadedDetail.execute(dsResponse, data, dsRequest);return; }
					ListGridRecord[] originalList = getSourceGrid().getRecords();
					for (int i = 0; i < len; i++) {
						int idx = -1;
						boolean found = false;
						ListGridRecord r = null;
						for (ListGridRecord r1 : originalList) {
							if(r1.getAttributeAsInt("roleId") == dsResponse.getData()[i].getAttributeAsInt("roleId")){
								found = true; idx = i; r = r1; break;
							}
						}
						if(!found) continue;
						DBDataSource.copyRecord(dsResponse.getData()[i], r);
						SourceGrid.selectRecord(r);
					}
					if(__endLoadedDetail != null) __endLoadedDetail.execute(dsResponse, data, dsRequest);
				}
			}
		});
	}

	public void deselectAllSource(){
		SourceGrid.deselectAllRecords();
	}

	@Override
	public boolean checkData() {
		for(ListGridRecord r : SourceGrid.getSelectedRecords()) {
			__logger.info(MapUtils.convertRecordToMap(SourceGrid.getDataSource(), r).toString());
		}
		return true;
	}

	@Override
	public Set<String> getItemNames() {
		Set<String> res = new HashSet<>();
		res.add("detailDepartmentRole");
		return res;
	}

	@Override
	public boolean isTheValuesEmpty() {
		ListGridRecord[] selected = SourceGrid.getSelectedRecords();
		return selected == null || selected.length == 0;
	}

	@Override
	public Map getValuesAsMap() {
		Map param = new HashMap();
		ListGridRecord[] selected = SourceGrid.getSelectedRecords();
		List resList = new ArrayList();
		for(ListGridRecord r : selected){
			Map lm = new HashMap();
			lm.put("roleId", r.getAttribute("roleId"));
			resList.add(lm);
		}
		param.put("detailDepartmentRole", resList);
		return param;
	}

	@Override
	public String getName() {
		return "部门角色";
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

	public DelicacyListGrid getSourceGrid() {
		return this.SourceGrid;
	}

	public void setSourceGrid( DelicacyListGrid value ) {
		this.SourceGrid = value;
	}


}

