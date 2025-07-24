package com.delicacy.client.management.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import com.delicacy.client.BaseHelpUtils;
import com.delicacy.client.MapUtils;
import com.delicacy.client.app.datasource.DSEmployeeRole;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.data.DBDataSource;
import com.delicacy.client.ui.AbstractWizadPage;
import com.delicacy.client.ui.DelicacyListGrid;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.grid.events.RemoveRecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RemoveRecordClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;  


public class MyDepartmentRolePanel extends AbstractWizadPage {

    private static final Logger __logger = Logger.getLogger("");
    private final DelicacyListGrid grid;
    private int myEmployeeId;
    public MyDepartmentRolePanel(final int employeeId) {
    	myEmployeeId = employeeId;
        VLayout SearchSourceLayout = new VLayout();
        SearchSourceLayout.setWidth100();
        ListGridField[] fields = new ListGridField[5];
        int idx = 0;
        fields[idx] = new ListGridField("employeeRoleIdField");
        fields[idx].setHidden(true);
        idx++;
        fields[idx] = new ListGridField("departmentId");
        fields[idx].setHidden(false);
        idx++;
        fields[idx] = new ListGridField("roleId");
        fields[idx].setHidden(false);
        idx++;
        fields[idx] = new ListGridField("isDefault");
        fields[idx].setHidden(false);
        idx++;
        fields[idx] = new ListGridField("operate","操作");
        fields[idx].setAlign(Alignment.CENTER); 
        fields[idx].setWidth(100);
        fields[idx].setType(ListGridFieldType.IMAGE);  
        fields[idx].setImageHeight(30);
        fields[idx].setImageWidth(100);
        fields[idx].setHidden(false);
        idx++;
       
        grid = new DelicacyListGrid(){
        	@Override
        	protected String getCellCSSText(ListGridRecord record, int rowNum, int colNum) {
        		record.setAttribute("operate","setDefaultRole.png");
        		boolean isDefault = BaseHelpUtils.getBoolean(record.getAttribute("isDefault"));
        		if(isDefault){
        			return "color: blue;";
        		}
        		return super.getCellCSSText(record, rowNum, colNum);
        	}
        };
        grid.setFields(fields);
        grid.setDataSource(DSEmployeeRole.getInstance());
        grid.setAutoFitFieldWidths(false);
        grid.setShowSelectedStyle(false);
        grid.setCanRemoveRecords(true);
        grid.setShowRowNumbers(true);
        grid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				int colNum = event.getColNum();
				String fieldName = grid.getFieldName(colNum);
				if(fieldName.equals("operate")){
					Record re = grid.getRecord(event.getRowNum());
					boolean isDefault = BaseHelpUtils.getBoolean(re.getAttribute("isDefault"));
					if(isDefault){
						SC.say("提示","该角色已为默认角色");
					}else{
						int departmentId = BaseHelpUtils.getIntValue(re.getAttribute("departmentId"));
						int roleId = BaseHelpUtils.getIntValue(re.getAttribute("roleId"));
						Map<String,Object> params = new HashMap<>();
						params.put("departmentId",departmentId);
						params.put("roleId",roleId);
						params.put("employeeId",employeeId);
						params.put("optType", "onSetDefaultRole");
						DBDataSource.callOperation("EP_EditorRoleAndDepartmentRoleToEmp", params, new DSCallback() {
				            @Override
				            public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
				                if (dsResponse.getStatus() >= 0) {
				                	startEdit();
				                }
				            }
				        });
					}
				}
			}
		});
        grid.addRemoveRecordClickHandler(new RemoveRecordClickHandler() {
			@Override
			public void onRemoveRecordClick(RemoveRecordClickEvent event) {
				int rowNum = event.getRowNum();
				ListGridRecord record = grid.getRecord(rowNum);
				// 获取到角色id
			    int roleId = ClientUtil.checkAndGetIntValue(record.getAttribute("roleId"));
				int departmentId = ClientUtil.checkAndGetIntValue(record.getAttribute("departmentId"));
				Map condition = new HashMap();
		    	condition.put("optType", "modifyPersonalDepartmentRole");
		    	condition.put("employeeId", myEmployeeId);
		    	condition.put("roleId", roleId);
		    	condition.put("departmentId", departmentId);
		    	condition.put("flag", 2);
		    	condition.put("operateEmployeeId", ClientUtil.getUserId());
				DBDataSource.callOperation("EP_EditorRoleAndDepartmentRoleToEmp", condition, new DSCallback() {
		            @Override
		            public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
		                if (dsResponse.getStatus() >= 0) {
		                	//成功后的操作
		                }
		            }
		        });
			}
		});
        SearchSourceLayout.setHeight100();
        SearchSourceLayout.setLayoutTopMargin(10);
        SearchSourceLayout.setLayoutRightMargin(5);
        SearchSourceLayout.setMembersMargin(10);
        SearchSourceLayout.addMember(grid);
        addMember(SearchSourceLayout);
    }

    @Override
    public void startEdit() {
    	Map condition = new HashMap();
    	condition.put("optType", "findPersonalDepartmentRole");
    	condition.put("employeeId", myEmployeeId);
		DBDataSource.callOperation("EP_EditorRoleAndDepartmentRoleToEmp",condition, new DSCallback() {
            @Override
            public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
                if (dsResponse.getStatus() >= 0) {
                    grid.setData(dsResponse.getData());
                }
            }
        });
    }

    @Override
    public boolean checkData() {
        for (ListGridRecord r : grid.getSelectedRecords()) {
            __logger.info(MapUtils.convertRecordToMap(grid.getDataSource(), r).toString());
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
    public Map getValuesAsMap() {
        Map param = new HashMap();
        ListGridRecord[] selected = grid.getSelectedRecords();
        List resList = new ArrayList();
        for (ListGridRecord r : selected) {
            Map lm = new HashMap();
            lm.put("departmentRoleId", r.getAttribute("departmentRoleId"));
            resList.add(lm);
        }
        param.put("detailDepartmentRole", resList);
        return param;
    }

    @Override
    public String getName() {
        return "";
    }
  
}
