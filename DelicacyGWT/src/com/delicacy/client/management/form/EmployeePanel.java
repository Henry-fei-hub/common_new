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
import java.util.logging.Logger;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGridField;
import com.smartgwt.client.widgets.tree.TreeNode;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.delicacy.client.app.datasource.DSFunction;
import com.delicacy.client.app.datasource.DSMemployee;
import com.delicacy.client.data.ClientUtil;


public class EmployeePanel extends AbstractWizadPage {

    private static final Logger __logger = Logger.getLogger("");
    private final DelicacyListGrid grid = new DelicacyListGrid();
    public String employeeNo;
    public String employeeName;
    public Integer departmentId;
    
    public EmployeePanel() {
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
        grid.setSelectionAppearance(SelectionAppearance.CHECKBOX);
        grid.setDataSource(DSMemployee.getInstance());
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
//    	condition.put("employeeNo", employeeNo);
//    	condition.put("employeeName", employeeName);
//    	condition.put("departmentId", departmentId);
		DBDataSource.callOperation("EP_RoleDepartmentEmployee", "find", condition, new DSCallback() {
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
    /**
	 * 根据部门id查询出其子部门
	 * 
	 * @return
	 */
	public void findChildIdByDepartmenId() {
		final LoadingWindow loading = new LoadingWindow();
		final List<Integer> childIdList = new ArrayList<>();
		Map condition = new HashMap();
		condition.put("departmentId", departmentId);
		DBDataSource.callOperation("ST_DepartmentId", "find", condition, new DSCallback() {
			@Override
			public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
				if (dsResponse.getStatus() >= 0) {
					int len = dsResponse.getData().length;
					if (len == 0) {
						return;
					}
					for (Record record : dsResponse.getData()) {
						childIdList.add(record.getAttributeAsInt("childId"));
					}
					//根据搜索条件查询和被选数据进行排序
					searchEmployee(childIdList);
					loading.destroy();
				}
			}
		});

	}

	/**
	 * 根据搜索条件查询和被选数据进行排序
	 * 
	 * @return
	 */
	public void searchEmployee(	List<Integer> allChildIdList) {
		List<Record> list = new ArrayList<>();
		RecordList finalRecordList = new RecordList();
		// 获取所有的grid数据
		RecordList recordList = grid.getDataAsRecordList();
		Record record = null;
		for (int i = 0; i < recordList.getLength(); i++) {
			record = recordList.get(i);
			// 判断是否为选中数据
			if (checkIsSelectData(record.getAttributeAsInt("employeeId"))) {
				record.setAttribute("level", 1);
				list.add(record);
				continue;
			}
			// 按条件查询出数据
			if (!BaseHelpUtils.isNullOrEmpty(employeeNo) || !BaseHelpUtils.isNullOrEmpty(employeeName)
					|| !BaseHelpUtils.isNullOrEmpty(departmentId)) {
				if (checkIsFindData(record,allChildIdList)) {
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
	public Boolean checkIsSelectData(int employeeId) {
		// 获取选择的数据
		ListGridRecord[] listGridRecords = grid.getSelectedRecords();
		for (ListGridRecord listGridRecord : listGridRecords) {
			if (employeeId == listGridRecord.getAttributeAsInt("employeeId")) {
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
	public Boolean checkIsFindData(Record record,List<Integer> childIdList) {
		if (!BaseHelpUtils.isNullOrEmpty(employeeNo) && BaseHelpUtils.isNullOrEmpty(employeeName)
				&& BaseHelpUtils.isNullOrEmpty(departmentId)) {
			if (record.getAttributeAsString("employeeNo").contains(employeeNo)) {
				return true;
			}
		}
		if (!BaseHelpUtils.isNullOrEmpty(employeeNo) && !BaseHelpUtils.isNullOrEmpty(employeeName)
				&& BaseHelpUtils.isNullOrEmpty(departmentId)) {
			if (record.getAttributeAsString("employeeNo").contains(employeeNo)
					&& record.getAttributeAsString("employeeName").contains(employeeName)) {
				return true;
			}
		}
		if (!BaseHelpUtils.isNullOrEmpty(employeeNo) && BaseHelpUtils.isNullOrEmpty(employeeName)
				&& !BaseHelpUtils.isNullOrEmpty(departmentId)) {
			if (record.getAttributeAsString("employeeNo").contains(employeeNo) && checkDepartmentData(record,childIdList)) {
				return true;
			}
		}
		if (!BaseHelpUtils.isNullOrEmpty(employeeNo) && !BaseHelpUtils.isNullOrEmpty(employeeName)
				&& !BaseHelpUtils.isNullOrEmpty(departmentId)) {
			if (record.getAttributeAsString("employeeNo").contains(employeeNo)
					&& record.getAttributeAsString("employeeName").contains(employeeName)
					&& checkDepartmentData(record,childIdList)) {
				return true;
			}
		}
		if (BaseHelpUtils.isNullOrEmpty(employeeNo) && !BaseHelpUtils.isNullOrEmpty(employeeName)
				&& BaseHelpUtils.isNullOrEmpty(departmentId)) {
			if (record.getAttributeAsString("employeeName").contains(employeeName)) {
				return true;
			}
		}
		if (BaseHelpUtils.isNullOrEmpty(employeeNo) && BaseHelpUtils.isNullOrEmpty(employeeName)
				&& !BaseHelpUtils.isNullOrEmpty(departmentId)) {
			if (checkDepartmentData(record,childIdList)) {
				return true;
			}
		}
		if (BaseHelpUtils.isNullOrEmpty(employeeNo) && !BaseHelpUtils.isNullOrEmpty(employeeName)
				&& !BaseHelpUtils.isNullOrEmpty(departmentId)) {
			if (record.getAttributeAsString("employeeName").contains(employeeName)&&checkDepartmentData(record,childIdList)) {
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
				int employeeId = ClientUtil.checkAndGetIntValue(finalRecordList.get(i).getAttributeAsInt("employeeId"));
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

	/**
	 * 判断是否为查询的部门
	 * 
	 * @return
	 */
	public Boolean checkDepartmentData(Record record,List<Integer> childIdList) {
		if (BaseHelpUtils.isNullOrEmpty(childIdList)) {
			return false;
		}
		for (Integer integer : childIdList) {
			if (record.getAttributeAsInt("departmentId") == integer) {
				return true;
			}
		}
		return false;
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
  
}
