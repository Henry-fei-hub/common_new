package com.delicacy.client.management.form;

import java.util.*;
import com.delicacy.client.MapUtils;
import com.delicacy.client.ui.AbstractWizadPage;
import com.delicacy.client.ui.DelicacyListGrid;
import com.delicacy.client.data.DBDataSource;
import com.delicacy.client.management.datasource.DSDepartment;
import com.smartgwt.client.data.AdvancedCriteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;

import java.util.logging.Logger;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGridField;
import com.smartgwt.client.widgets.tree.TreeNode;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.delicacy.client.app.datasource.DSFunction;
import com.delicacy.client.app.datasource.DSMemployee;
import com.delicacy.client.data.ClientUtil;


public class UpdatePermissionsEmployeePanel extends AbstractWizadPage {

    private static final Logger __logger = Logger.getLogger("");
    private DelicacyListGrid grid = new DelicacyListGrid();
    private UpdatePermissionsFunctionPanel functionDetailPanel;
    public String employeeNo;
    public String employeeName;
    public Integer departmentId;
    
    public UpdatePermissionsEmployeePanel() {
        VLayout SearchSourceLayout = new VLayout();
        SearchSourceLayout.setWidth100();
        ListGridField[] fields = new ListGridField[4];
        int idx = 0;
        fields[idx] = new ListGridField("employeeId");
        fields[idx].setHidden(true);
        idx++;
        fields[idx] = new ListGridField("employeeNo");
        fields[idx].setHidden(false);
        idx++;
        fields[idx] = new ListGridField("employeeName");
        fields[idx].setHidden(false);
        idx++;
        fields[idx] = new ListGridField("departmentId");
        fields[idx].setHidden(false);
        idx++;
       
        grid.setFields(fields);
     //   grid.setSelectionAppearance(SelectionAppearance.CHECKBOX);
        grid.setDataSource(DSMemployee.getInstance());
     //   grid.setSelectionType(SelectionStyle.SINGLE);
        grid.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				functionDetailPanel.setGrid(grid);
				functionDetailPanel.startEdit();
				
			}
		});
//        grid.addSelectionChangedHandler(new SelectionChangedHandler() {
//			
//			@Override
//			public void onSelectionChanged(SelectionEvent event) {
//				if(event.getState()){
//				//	SC.say(event.getRecord().toMap().toString());
//					functionDetailPanel.setGrid(grid);
//					functionDetailPanel.startEdit();
//				}
//				
//			}
//		});
        grid.setAutoFitFieldWidths(false);
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
    	condition.put("employeeNo", employeeNo);
    	condition.put("employeeName", employeeName);
    	condition.put("departmentId", departmentId);
    	condition.put("optType", "employeeData");
		DBDataSource.callOperation("EP_SerachRoleDepartmentEmployee", "find", condition, new DSCallback() {
            @Override
            public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
                if (dsResponse.getStatus() >= 0) {
                    grid.setData(dsResponse.getData());
                    if (getRecord() != null) {
                        reloadDetailTableData();
                    }
                }
            }
        });
    }
    public void reloadDetailTableData() {
        Map condition = new HashMap();
        condition.put("roleId",getRecord().getAttribute("roleId"));
        DBDataSource.callOperation("ST_EmployeeRole", "find", condition, new DSCallback() {
            @Override
            public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
                if (dsResponse.getStatus() >= 0) {
                    int len = dsResponse.getData().length;
                    if (len == 0) {
                        return;
                    }
                    for (int i = 0; i < len; i++) {
                        int employeeId = ClientUtil.checkAndGetIntValue(dsResponse.getData()[i].getAttribute("employeeId"));
                        AdvancedCriteria ac = new AdvancedCriteria();
                        ac.addCriteria("employeeId", employeeId);
                        int idx = grid.findIndex(ac);
                        if (idx == -1) {
                            continue;
                        }
                        grid.selectRecord(idx);
                    }
      
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
        res.add("detailRoleEmployee");
        return res;
    }

    @Override
    public Map getValuesAsMap() {
        Map param = new HashMap();
        ListGridRecord[] selected = grid.getSelectedRecords();
        List resList = new ArrayList();
        for (ListGridRecord r : selected) {
            Map lm = new HashMap();
            lm.put("employeeId", r.getAttribute("employeeId"));
            resList.add(lm);
        }
        param.put("detailRoleEmployee", resList);
        return param;
    }

    @Override
    public String getName() {
        return "";
    }

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public DelicacyListGrid getGrid() {
		return grid;
	}

	public UpdatePermissionsFunctionPanel getFunctionDetailPanel() {
		return functionDetailPanel;
	}

	public void setFunctionDetailPanel(UpdatePermissionsFunctionPanel functionDetailPanel) {
		this.functionDetailPanel = functionDetailPanel;
	}
  
}
