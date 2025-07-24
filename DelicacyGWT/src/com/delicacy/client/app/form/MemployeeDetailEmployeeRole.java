package com.delicacy.client.app.form;

import com.delicacy.client.MapUtils;
import com.delicacy.client.TransferImgBt;
import com.delicacy.client.app.datasource.DSEmployeeRole;
import com.delicacy.client.app.datasource.DSSemployeeRoleInfo;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.data.DBDataSource;
import com.delicacy.client.ui.AbstractWizadPage;
import com.delicacy.client.ui.DelicacyListGrid;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.TransferImgButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.DoubleClickEvent;
import com.smartgwt.client.widgets.events.DoubleClickHandler;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.EditorExitEvent;
import com.smartgwt.client.widgets.grid.events.EditorExitHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import java.util.*;
import java.util.logging.Logger;

public class MemployeeDetailEmployeeRole extends AbstractWizadPage {

    private static final Logger __logger = Logger.getLogger("");
    private final DelicacyListGrid grid = new DelicacyListGrid(true);
    private final DelicacyListGrid SourceGrid = new DelicacyListGrid();
    private final IButton searchButton = new IButton("查询");
    private final SemployeeRoleInfoSearchForm __searchForm = new SemployeeRoleInfoSearchForm();

    private boolean IS_FLAG = true;

    public RecordList NEW_RECORD_LIST__;

    public boolean isNullOrEmpty(Object val) {
        if (val == null || val == "null") {
            return true;
        }
        if (val instanceof Collection) {
            return ((Collection) val).isEmpty();
        }
        String s = val.toString().trim();
        return s.length() == 0;
    }

    public MemployeeDetailEmployeeRole() {

        VLayout SearchSourceLayout = new VLayout();
        SearchSourceLayout.setWidth("50%");
        ListGridField[] fields = new ListGridField[4];
        int idx = 0;
        fields[idx] = new ListGridField("roleId");
        fields[idx].setCanEdit(false);
        fields[idx].setHidden(true);
        idx++;
        fields[idx] = new ListGridField("roleName");
        idx++;
        fields[idx] = new ListGridField("applicationId");
        idx++;
        fields[idx] = new ListGridField("roleType");
        idx++;
        SourceGrid.setFields(fields);
        SourceGrid.setSelectionAppearance(SelectionAppearance.CHECKBOX);
        SourceGrid.setDataSource(DSSemployeeRoleInfo.getInstance());
        SearchSourceLayout.setHeight100();
        SearchSourceLayout.setLayoutTopMargin(10);
        SearchSourceLayout.setLayoutRightMargin(5);
        SearchSourceLayout.setMembersMargin(10);
        SearchSourceLayout.setShowResizeBar(true);
        HLayout SearchLayout = new HLayout(5);
        SearchLayout.setWidth100();
        SearchLayout.setLayoutRightMargin(5);
        SearchSourceLayout.addMember(SearchLayout);
        SearchLayout.addMember(__searchForm);
        searchButton.setIcon("[SKIN]/actions/search.png");
        searchButton.setWidth(60);
        SearchLayout.addMember(searchButton);
        searchButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                Criteria condition = __searchForm.getValuesAsCriteria();
                reloadSource(SourceGrid, condition);
            }
        });
        HLayout SourceLayout = new HLayout(5);
        SourceLayout.setWidth100();
        SourceLayout.setHeight100();
        SearchSourceLayout.addMember(SourceLayout);
        SourceGrid.setSelectionAppearance(SelectionAppearance.CHECKBOX);
        SourceGrid.setDataSource(DSSemployeeRoleInfo.getInstance());
        SourceLayout.addMember(SourceGrid);
        VLayout SourceControls = new VLayout();
        SourceControls.setHeight100();
        SourceControls.setWidth(60);
        SourceControls.setLayoutTopMargin(30);
        SourceControls.setLayoutLeftMargin(5);
        SourceControls.setLayoutRightMargin(5);
        SourceControls.setMembersMargin(10);
        SourceLayout.addMember(SourceControls);
        TransferImgBt AddToButton = new TransferImgBt(TransferImgBt.RIGHT);
        SourceControls.addMember(AddToButton);
        AddToButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (!SourceGrid.anySelected()) {
                    return;
                }
                addSelectedDataToTarget(SourceGrid, grid);
            }
        });
        SourceGrid.addDoubleClickHandler(new DoubleClickHandler() {
            @Override
            public void onDoubleClick(DoubleClickEvent event) {
                if (!SourceGrid.anySelected()) {
                    return;
                }
                addSelectedDataToTarget(SourceGrid, grid);
            }
        });
        addMember(SearchSourceLayout);
        HLayout employeeRoles = new HLayout();
        employeeRoles.setWidth("50%");
        employeeRoles.setHeight100();
        grid.setDataSource(DSEmployeeRole.getInstance());
        grid.setAutoFitFieldWidths(false);
//   列的数据被编辑，光标离开触发事件        
//        grid.addEditorExitHandler(new EditorExitHandler() {
//            @Override
//            public void onEditorExit(EditorExitEvent event) {
//                __logger.info("====Join in onEditorExit==============");
//                ListGridRecord[] records = grid.getRecords();
//                if (records == null || records.length == 0) {
//                    return;
//                }
//                ListGridRecord newRecord = event.getRecord();
//                Map searchMap = new HashMap();
//                searchMap.put("roleId", newRecord.getAttribute("roleId"));
//                searchMap.put("employeeRoleId", ClientUtil.checkAndGetIntValue(newRecord.getAttribute("employeeRoleId")));
//                //用户的id
//                for (ListGridRecord record : records) {
//                    searchMap.put("employeeId", record.getAttribute("employeeId"));
//                    if (!isNullOrEmpty(searchMap.get("employeeId"))) {
//                        break;//找到用户id 跳出for
//                    } else {
//                        searchMap.put("employeeId", 0);
//                    }
//                }
//
//                int colNum = event.getColNum();
//                if (colNum == 2) {
//                    Object isDefaultNew = event.getNewValue();
//                    Boolean isDefault = (Boolean) isDefaultNew;
//                    searchMap.put("departmentId", 0);
//                    searchMap.put("isDefault", isDefault);
//
//                } else if (colNum == 0) {
//                    Object obj = event.getNewValue();
//                    Integer departmentId = (Integer) obj;
//                    searchMap.put("departmentId", departmentId);
//                    searchMap.put("isDefault", false);
//                }
//
//                DBDataSource.callOperation("EP_DefaultSetRole", searchMap, new DSCallback() {
//                    @Override
//                    public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
//                        if (dsResponse.getStatus() >= 0) {
//                            reloadDetailTableData();
//
//                        } else {
//
//                        }
//                    }
//                });
//            }
//
//        });

        employeeRoles.addMember(grid);
        VLayout employeeRolesControls = new VLayout();
        employeeRolesControls.setHeight100();
        employeeRolesControls.setWidth(60);
        employeeRolesControls.setLayoutTopMargin(30);
        employeeRolesControls.setLayoutLeftMargin(5);
        employeeRolesControls.setLayoutRightMargin(5);
        employeeRolesControls.setMembersMargin(10);
        employeeRoles.addMember(employeeRolesControls);
        addMember(employeeRoles);

        IButton employeeRolesNewButton = new IButton("新增");
        employeeRolesNewButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                grid.startEditingNew();
            }
        });
        IButton setRoleDefaultButton = new IButton("设置默认角色");
        setRoleDefaultButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                ListGridRecord[] records = grid.getRecords();
                if (records == null || records.length == 0) {
                    return;
                }
                String employeeRoleId, employeeId;
                int temp_roleId;
                for (ListGridRecord record : records) {
                    //默认选中的是true 没有选中的是null
                    //当时true 的则不用修改，为null怎修改
                    employeeRoleId = record.getAttribute("employeeRoleId");
                    employeeId = record.getAttribute("employeeId");
                    temp_roleId = ClientUtil.checkAndGetIntValue(record.getAttribute("roleId"));

                    Map params = new HashMap();
                    params.put("employeeRoleId", employeeRoleId);
                    params.put("employeeId", employeeId);
                    params.put("roleId", temp_roleId);
                    DBDataSource.callOperation("EP_DefaultSetRole", params, new DSCallback() {
                        @Override
                        public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
                            if (dsResponse.getStatus() >= 0) {
//                                SC.say("确认成功");
                            } else {
//                                Map errors = dsResponse.getErrors();
//                                SC.say("确认失败" + errors);
                            }
                        }
                    });
                }
            }
        });

        IButton employeeRolesRemoveButton = new IButton("删除所有");
        employeeRolesRemoveButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                grid.setData(new RecordList());
            }
        });
//        employeeRolesControls.addMember(employeeRolesNewButton);
//        employeeRolesControls.addMember(setRoleDefaultButton);
        employeeRolesControls.addMember(employeeRolesRemoveButton);
    }

    @Override
    public void startEdit() {
        if (getPageMode() != PAGE_MODE_UPDATE) {
            return;
        }
        reloadDetailTableData();
    }

    public void reloadDetailTableData() {
        Map condition = new HashMap();
        condition.put("employeeId", getRecord().getAttribute("employeeId"));
        DBDataSource.callOperation("ST_EmployeeRole", "find", condition, new DSCallback() {
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
        for (ListGridRecord r : grid.getRecords()) {
            __logger.info(MapUtils.convertRecordToMap(grid.getDataSource(), r).toString());
        }
        return true;
    }

    @Override
    public Set<String> getItemNames() {
        Set<String> res = new HashSet<>();
        res.add("detailEmployeeRole");
        return res;
    }

    @Override
    public Map getValuesAsMap() {
        Map param = new HashMap();
        ListGridRecord[] rows = grid.getRecords();
        MapUtils.convertRecordToMap(grid.getDataSource(), rows, param, "detailEmployeeRole");
        return param;
    }

    @Override
    public void reloadSourceData() {
        Criteria condition = __searchForm.getValuesAsCriteria();
        reloadSource(SourceGrid, condition);
    }

    private void reloadSource(final ListGrid source, Criteria condition) {
        if (condition == null) {
            condition = new Criteria();
        }
        FormItem item;
        Object val = null;
        searchButton.setIcon("loading38.gif");
        DBDataSource.callOperation("NQ_SemployeeRoleInfo", "find", condition.getValues(), new DSCallback() {
            @Override
            public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
                searchButton.setIcon("[SKIN]/actions/search.png");
                if (dsResponse.getStatus() >= 0) {
                    source.setData(dsResponse.getData());
                }
            }
        });
    }

    private void addSelectedDataToTarget(ListGrid source, ListGrid target) {
        ListGridRecord[] selectedRecords = source.getSelectedRecords();
        RecordList originalList = target.getDataAsRecordList();
        FormItem item;
        Map<String, FormItem> items = getParentWindow().getFormItems();
        int originalLength = originalList.getLength();
        RecordList newList = new RecordList();
        if (originalLength > 0) {
            newList.addList(originalList.getRange(0, originalLength));
        }
        for (ListGridRecord selectedRecord : selectedRecords) {
            Map searchMap = new HashMap();
            searchMap.put("roleId", selectedRecord.getAttribute("roleId"));
            if (originalLength > 0) {
                int index = originalList.findIndex(searchMap);
                if (index != -1) {
                    continue;
                }
            }
            newList.add(new Record(searchMap));
        }
        target.setData(newList);
    }

    @Override
    public String getName() {
        return "员工角色";
    }

}
