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


public class PermissionsRolePanel extends AbstractWizadPage {

    private static final Logger __logger = Logger.getLogger("");
    private  final DelicacyListGrid grid = new DelicacyListGrid();
    public String roleName;
    public Integer roleType;
    public Integer applicationId;
    
    public PermissionsRolePanel() {
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
//    	condition.put("roleName", roleName);
//    	condition.put("roleType", roleType);
//    	condition.put("applicationId", applicationId);
    	condition.put("optType", "roleData");
		DBDataSource.callOperation("EP_SerachRoleDepartmentEmployee", "find", condition, new DSCallback() {
            @Override
            public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
                if (dsResponse.getStatus() >= 0) {
                    grid.setData(dsResponse.getData());
                }
            }
        });
    }
    

	/**
	 * 根据搜索条件查询和被选数据进行排序
	 * 
	 * @return
	 */
	public void searchRole() {
		List<Record> list = new ArrayList<>();
		RecordList finalRecordList = new RecordList();
		// 获取所有的grid数据
		RecordList recordList = grid.getDataAsRecordList();
		Record record = null;
		for (int i = 0; i < recordList.getLength(); i++) {
			record = recordList.get(i);
			// 判断是否为选中数据
			if (checkIsSelectData(record.getAttributeAsInt("roleId"))) {
				record.setAttribute("level", 1);
				list.add(record);
				continue;
			}
			// 按条件查询出数据
			if (!BaseHelpUtils.isNullOrEmpty(roleName) || !BaseHelpUtils.isNullOrEmpty(roleType)
					|| !BaseHelpUtils.isNullOrEmpty(applicationId)) {
				if (checkIsFindData(record)) {
					record.setAttribute("level", 2);
					list.add(record);
					continue;
				}
			}
			// 剩下的数据
			record.setAttribute("level", 3);
			list.add(record);

		}

		// 进行排序
		list.sort(new Comparator<Record>() {
			@Override
			public int compare(Record o1, Record o2) {

				if (o1.getAttributeAsInt("level") < o2.getAttributeAsInt("level")) {
					return -1;
				}
				if (o1.getAttributeAsInt("level") > o2.getAttributeAsInt("level")) {
					return 1;
				}
				return 0;
			}
		});

		// 将List转换为RecordList
		for (Record record2 : list) {
			finalRecordList.add(record2);
		}
		if (!BaseHelpUtils.isNullOrEmpty(finalRecordList)) {
			grid.setData(finalRecordList);
			echoData(finalRecordList);
		}

	}

	/**
	 * 判断某条数据是否为选中数据
	 * 
	 * @param employeeId
	 * @return
	 */
	public Boolean checkIsSelectData(int roleId) {
		// 获取选择的数据
		ListGridRecord[] listGridRecords = grid.getSelectedRecords();
		for (ListGridRecord listGridRecord : listGridRecords) {
			if (roleId == listGridRecord.getAttributeAsInt("roleId")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断是否为符合查询条件的数据
	 * 
	 * @return
	 */
	public Boolean checkIsFindData(Record record) {
		if (!BaseHelpUtils.isNullOrEmpty(roleName) && BaseHelpUtils.isNullOrEmpty(roleType)
				&& BaseHelpUtils.isNullOrEmpty(applicationId)) {
			if (record.getAttributeAsString("roleName").contains(roleName)) {
				return true;
			}
		}
		if (!BaseHelpUtils.isNullOrEmpty(roleName) && !BaseHelpUtils.isNullOrEmpty(roleType)
				&& BaseHelpUtils.isNullOrEmpty(applicationId)) {
			if (record.getAttributeAsString("roleName").contains(roleName)
					&& record.getAttributeAsInt("roleType")==(roleType)) {
				return true;
			}
		}
		if (!BaseHelpUtils.isNullOrEmpty(roleName) && BaseHelpUtils.isNullOrEmpty(roleType)
				&& !BaseHelpUtils.isNullOrEmpty(applicationId)) {
			if (record.getAttributeAsString("roleName").contains(roleName) && record.getAttributeAsInt("applicationId")==applicationId) {
				return true;
			}
		}
		if (!BaseHelpUtils.isNullOrEmpty(roleName) && !BaseHelpUtils.isNullOrEmpty(roleType)
				&& !BaseHelpUtils.isNullOrEmpty(applicationId)) {
			if (record.getAttributeAsString("roleName").contains(roleName)
					&& record.getAttributeAsInt("roleType")==(roleType)
					&& record.getAttributeAsInt("applicationId")==applicationId) {
				return true;
			}
		}
		if (BaseHelpUtils.isNullOrEmpty(roleName) && !BaseHelpUtils.isNullOrEmpty(roleType)
				&& BaseHelpUtils.isNullOrEmpty(applicationId)) {
			if (record.getAttributeAsInt("roleType")==(roleType)) {
				return true;
			}
		}
		if (BaseHelpUtils.isNullOrEmpty(roleName) && BaseHelpUtils.isNullOrEmpty(roleType)
				&& !BaseHelpUtils.isNullOrEmpty(applicationId)) {
			if (record.getAttributeAsInt("applicationId")==applicationId) {
				return true;
			}
		}
		if (BaseHelpUtils.isNullOrEmpty(roleName) && !BaseHelpUtils.isNullOrEmpty(roleType)
				&& !BaseHelpUtils.isNullOrEmpty(applicationId)) {
			if (record.getAttributeAsInt("roleType")==(roleType)&&record.getAttributeAsInt("applicationId")==applicationId) {
				return true;
			}
		}

		return false;

	}

	/**
	 * 值的回显
	 * 
	 * @param finalRecordList
	 */
	public void echoData(RecordList finalRecordList) {
		for (int i = 0; i < finalRecordList.getLength(); i++) {
			if (finalRecordList.get(i).getAttributeAsInt("level") == 1) {
				int roleId = ClientUtil.checkAndGetIntValue(finalRecordList.get(i).getAttributeAsInt("roleId"));
				AdvancedCriteria ac = new AdvancedCriteria();
				ac.addCriteria("roleId", roleId);
				int idx = grid.findIndex(ac);
				if (idx == -1) {
					continue;
				}
				grid.selectRecord(idx);
			}

		}
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

	public DelicacyListGrid getGrid() {
		return grid;
	}

  
}
