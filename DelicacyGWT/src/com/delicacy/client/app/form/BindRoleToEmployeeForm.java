package com.delicacy.client.app.form;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.delicacy.client.BaseHelpUtils;
import com.delicacy.client.MapUtils;
import com.delicacy.client.TransferImgBt;
import com.delicacy.client.app.datasource.DSMemployee;
import com.delicacy.client.app.datasource.DSRoleWithR;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.data.DBDataSource;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.ui.AbstractWizadPage;
import com.delicacy.client.ui.DelicacyListGrid;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.DoubleClickEvent;
import com.smartgwt.client.widgets.events.DoubleClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.ValuesManager;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.IPickTreeItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class BindRoleToEmployeeForm extends AbstractWizadPage {
	private final Logger __logger = Logger.getLogger("");
    private DelicacyListGrid recordGrid;
    private DelicacyListGrid resultGrid;
    
	public BindRoleToEmployeeForm() {
    	VLayout main = new VLayout(10);
        main.setHeight100();
        main.setWidth100();
        
        Label bel = new Label();
        bel.setHeight("5%");
        bel.setAlign(Alignment.CENTER);
        bel.setContents("<font style=\"font-weight:bold;font-size:16px;\">赋予该角色給选择人员</font>");
        main.addMember(bel);
        
        HLayout empLayout = new HLayout(10);
        empLayout.setHeight("95%");
        empLayout.setWidth100();
        main.addMember(empLayout);
        
        VLayout reLayout = new VLayout();
        reLayout.setHeight100();
        reLayout.setWidth("47%");
        empLayout.addMember(reLayout);
        
        HLayout searchLayout = new HLayout(10);
        searchLayout.setHeight("15%");
        reLayout.addMember(searchLayout);
        
        DynamicForm searchForm = new DynamicForm();
        searchForm.setWidth("80%");
        
        final TextItem employeeNoItem = new TextItem("employeeNo", "职员编号");
        employeeNoItem.setWidth("*");
        
        final TextItem employeeNameItem = new TextItem("employeeName", "职员名称");
        employeeNameItem.setWidth("*");
        
        final IPickTreeItem departmentIdItem = new IPickTreeItem("departmentId", "归属部门");
        departmentIdItem.setCanSelectParentItems(true);
        departmentIdItem.setWidth("*");
        departmentIdItem.setValueField("treeId");
        departmentIdItem.setValueTree(KeyValueManager.getTree("departments"));
        
        searchForm.setNumCols(6);
        searchForm.setFields(employeeNoItem,employeeNameItem,departmentIdItem);
        ClientUtil.DynamicFormProcessAccordingToDevice(searchForm);
        searchLayout.addMember(searchForm);
        
        VLayout btnLayout = new VLayout(10);
        btnLayout.setWidth("20%");
        searchLayout.addMember(btnLayout);
        
        IButton searchBtn = new IButton("搜索");
        searchBtn.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				//获取职员编号信息
				String employeeNo = BaseHelpUtils.getString(employeeNoItem.getValue());
				String employeeName = BaseHelpUtils.getString(employeeNameItem.getValue());
				int departmentId = BaseHelpUtils.getIntValue(departmentIdItem.getValue());
				loadData(employeeNo, employeeName, departmentId);
			}
		});
        btnLayout.addMember(searchBtn);
        
        IButton resetBtn = new IButton("重置");
        resetBtn.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				employeeNoItem.clearValue();
				employeeNameItem.clearValue();
				departmentIdItem.clearValue();
				loadData(null, null, null);
			}
		});
        btnLayout.addMember(resetBtn);
        
        TransferImgBt addToButton = new TransferImgBt(TransferImgBt.RIGHT);
		addToButton.setWidth("4%");
		addToButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				addSelectedContract();
			}
		});
		empLayout.addMember(addToButton);
        
        
        recordGrid = new DelicacyListGrid();
        recordGrid.setHeight("85%");
        recordGrid.setAutoFitFieldWidths(false);
        recordGrid.setDataSource(DSMemployee.getInstance());
        ListGridField[] fields = new ListGridField[4];
		int idx = 0;
		fields[idx] = new ListGridField("employeeId","职员Id");
		fields[idx].setCanEdit(false);
		fields[idx].setHidden(true);
		idx ++;
		fields[idx] = new ListGridField("employeeNo","职员编号");
		fields[idx].setCanEdit(false);
		idx ++;
		fields[idx] = new ListGridField("employeeName","职员名称");
		fields[idx].setCanEdit(false);
		idx ++;
		fields[idx] = new ListGridField("departmentId","归属部门");
		fields[idx].setCanEdit(false);
		ComboBoxItem departmentIdSelect = new ComboBoxItem();
		departmentIdSelect.setValueMap(KeyValueManager.getValueMap("departments"));
		fields[idx].setEditorProperties(departmentIdSelect);
		idx ++;
		recordGrid.setFields(fields);
		recordGrid.addDoubleClickHandler(new DoubleClickHandler() {
			@Override
			public void onDoubleClick(DoubleClickEvent event) {
				addSelectedContract();
			}
		});
		reLayout.addMember(recordGrid);
        
        resultGrid = new DelicacyListGrid();
        resultGrid.setAutoFitFieldWidths(false);
        resultGrid.setCanRemoveRecords(true);
        resultGrid.setWidth("47%");
        resultGrid.setDataSource(DSMemployee.getInstance());
        resultGrid.setFields(fields);
        empLayout.addMember(resultGrid);
        
        addMember(main);
        setPageMode(PAGE_MODE_UPDATE);
        loadDefaultData();
    }

    @Override
    public boolean checkData() {
        return true;
    }

    @Override
    public void startEdit() {
    }
    
    /**
     * 加载页面时默认封装职员信息
     */
    private void loadDefaultData(){
    	Map<String,Object> params = new HashMap<>();
		params.put("status",0);
		//加载职员信息
		DBDataSource.callOperation("ST_Employee","find",params, new DSCallback() {
			@Override
			public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
				if (dsResponse.getStatus() >= 0) {
					Record[] resultData = dsResponse.getData();
					recordGrid.setData(resultData);
					empData = resultData;
					loadRoleEmpData();
				} else {
					SC.say("Failure" + dsResponse.getErrors().get("errorMsg"));
				}
			}
		});
    }
    
    /**
     * 加载数据集
     */
    private void loadData(String employeeNo,String employeeName,Integer departmentId){
    	Map<String,Object> params = new HashMap<>();
		params.put("status",0);
		if(!BaseHelpUtils.isNullOrEmpty(employeeNo)){
			params.put("employeeNo","%"+employeeNo+"%");
		}
		if(!BaseHelpUtils.isNullOrEmpty(employeeName)){
			params.put("employeeName","%"+employeeName+"%");
		}
		if(!BaseHelpUtils.isNullOrEmpty(departmentId) && departmentId > 0){
			params.put("departmentId",departmentId);
		}
		//加载职员信息
		DBDataSource.callOperation("ST_Employee","find",params, new DSCallback() {
			@Override
			public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
				if (dsResponse.getStatus() >= 0) {
					Record[] resultData = dsResponse.getData();
					recordGrid.setData(resultData);
				} else {
					SC.say("Failure" + dsResponse.getErrors().get("errorMsg"));
				}
			}
		});
    }
    
    /**
     * 加载角色下人员
     */
    private void loadRoleEmpData(){
    	Record record = getRecord();
    	if(!BaseHelpUtils.isNullOrEmpty(record) && !BaseHelpUtils.isNullOrEmpty(empData)){
    		int roleId = BaseHelpUtils.getIntValue(record.getAttribute("roleId"));
    		if(roleId <= 0){
    			return;
    		}
    		Map<String,Object> params = new HashMap<>();
    		params.put("roleId",roleId);
    		//加载职员信息
    		DBDataSource.callOperation("ST_EmployeeRole","find",params, new DSCallback() {
    			@Override
    			public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
    				if (dsResponse.getStatus() >= 0) {
    					RecordList list = new RecordList();
    					Record[] da = dsResponse.getData();
    					if(!BaseHelpUtils.isNullOrEmpty(da)){
    						for(Record e : da){
    							int empId = BaseHelpUtils.getIntValue(e.getAttribute("employeeId"));
    							for(Record d : empData){
    								if(empId == BaseHelpUtils.getIntValue(d.getAttribute("employeeId"))){
    									e.setAttribute("employeeNo",d.getAttribute("employeeNo"));
    									e.setAttribute("employeeName",d.getAttribute("employeeName"));
    									list.add(e);
    									break;
    								}
    							}
    						}
    						resultGrid.setData(list);
    					}
    				} else {
    					SC.say("Failure" + dsResponse.getErrors().get("errorMsg"));
    				}
    			}
    		});
    	}
    }
    
    private void addSelectedContract() {
		ListGridRecord[] selectedRecords = recordGrid.getSelectedRecords();
		RecordList originalList = resultGrid.getDataAsRecordList();
		int originalLength = originalList.getLength();
		RecordList newList = new RecordList();
		if (originalLength > 0) {
			newList.addList(originalList.getRange(0, originalLength));
		}
		recordGrid.deselectAllRecords();
		for (ListGridRecord selectedRecord : selectedRecords) {
			Map<String,Object> searchMap = new HashMap<>();
			searchMap.put("employeeId", selectedRecord.getAttribute("employeeId"));
			if (originalLength > 0) {
				int index = originalList.findIndex(searchMap);
				if (index != -1) {
					continue;
				}
			}
			searchMap.put("employeeNo", selectedRecord.getAttribute("employeeNo"));
			searchMap.put("employeeName", selectedRecord.getAttribute("employeeName"));
			searchMap.put("departmentId", selectedRecord.getAttribute("departmentId"));
			newList.add(new Record(searchMap));
		}
		resultGrid.setData(newList);
	}
    
    @Override
    public String getName() {
        return "赋予该角色給选择人员";
    }

    @Override
    public void setValueManage(ValuesManager manager) {
        manager.setDataSource(DSRoleWithR.getInstance());
        manager.addMember(__form);
    }

    @SuppressWarnings("rawtypes")
	@Override
    public java.util.Map getValuesAsMap() {
	   Map param = new HashMap<>();
	   MapUtils.convertRecordToMap(resultGrid.getDataSource(), resultGrid.getRecords(), param, "detailRoleEmployee");
       return param;
    }
    
    private Record[] empData = null;
    
}
