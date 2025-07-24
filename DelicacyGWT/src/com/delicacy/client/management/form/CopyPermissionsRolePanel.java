package com.delicacy.client.management.form;

import java.util.*;

import com.delicacy.client.BaseHelpUtils;
import com.delicacy.client.MapUtils;
import com.delicacy.client.ui.AbstractWizadPage;
import com.delicacy.client.ui.DelicacyListGrid;
import com.delicacy.client.ui.LoadingWindow;
import com.delicacy.client.data.DBDataSource;
import com.delicacy.client.management.datasource.DSDepartment;
import com.smartgwt.client.data.AdvancedCriteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;

import java.util.logging.Logger;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGridField;
import com.smartgwt.client.widgets.tree.TreeNode;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.delicacy.client.app.datasource.DSFunction;
import com.delicacy.client.app.datasource.DSMemployee;
import com.delicacy.client.app.datasource.DSRoleWithR;
import com.delicacy.client.data.ClientUtil;


public class CopyPermissionsRolePanel extends AbstractWizadPage {

    private static final Logger __logger = Logger.getLogger("");
    private  final DelicacyListGrid grid = new DelicacyListGrid();
    private Integer employeeId;
    public CopyPermissionsRolePanel() {
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
        grid.setSelectionAppearance(SelectionAppearance.CHECKBOX);
        grid.setDataSource(DSRoleWithR.getInstance());
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
    	condition.put("employeeId", employeeId);
    	condition.put("optType", "addRole");
		DBDataSource.callOperation("EP_CopyEmployeeFunction", condition, new DSCallback() {
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

	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public DelicacyListGrid getGrid() {
		return grid;
	}

  
}
