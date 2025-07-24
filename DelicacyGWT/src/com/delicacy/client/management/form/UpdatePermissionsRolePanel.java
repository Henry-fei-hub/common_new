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
import com.delicacy.client.app.datasource.DSRoleWithR;
import com.delicacy.client.data.ClientUtil;


public class UpdatePermissionsRolePanel extends AbstractWizadPage {

    private static final Logger __logger = Logger.getLogger("");
    private DelicacyListGrid grid = new DelicacyListGrid();
    private UpdatePermissionsFunctionPanel functionDetailPanel;
    public String roleName;
    public Integer roleType;
    public Integer applicationId;
    
    public UpdatePermissionsRolePanel() {
        VLayout SearchSourceLayout = new VLayout();
        SearchSourceLayout.setWidth100();
        ListGridField[] fields = new ListGridField[4];
        int idx = 0;
        fields[idx] = new ListGridField("roleId");
        fields[idx].setHidden(true);
        idx++;
        fields[idx] = new ListGridField("roleName");
        fields[idx].setHidden(false);
        idx++;
        fields[idx] = new ListGridField("roleType");
        fields[idx].setHidden(false);
        idx++;
        fields[idx] = new ListGridField("applicationId");
        fields[idx].setHidden(false);
        idx++;
       
        grid.setFields(fields);
        grid.setDataSource(DSRoleWithR.getInstance());
        grid.setAutoFitFieldWidths(false);
        grid.addClickHandler(new ClickHandler() {
			
     			@Override
     			public void onClick(ClickEvent event) {
     				functionDetailPanel.setGrid(grid);
     				functionDetailPanel.startEdit();
     				
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
    	condition.put("roleName", roleName);
    	condition.put("roleType", roleType);
    	condition.put("applicationId", applicationId);
    	condition.put("optType", "roleData");
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
        res.add("detailRole");
        return res;
    }

    @Override
    public Map getValuesAsMap() {
        Map param = new HashMap();
        ListGridRecord[] selected = grid.getSelectedRecords();
        List resList = new ArrayList();
        for (ListGridRecord r : selected) {
            Map lm = new HashMap();
            lm.put("roleId", r.getAttribute("roleId"));
            resList.add(lm);
        }
        param.put("detailRole", resList);
        return param;
    }

    @Override
    public String getName() {
        return "";
    }

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getRoleType() {
		return roleType;
	}

	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}

	public Integer getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
	}


	public UpdatePermissionsFunctionPanel getFunctionDetailPanel() {
		return functionDetailPanel;
	}

	public void setFunctionDetailPanel(UpdatePermissionsFunctionPanel functionDetailPanel) {
		this.functionDetailPanel = functionDetailPanel;
	}

	public DelicacyListGrid getGrid() {
		return grid;
	}

	public void setGrid(DelicacyListGrid grid) {
		this.grid = grid;
	}

  
}
