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
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class BindDepartmentFunctionToEmployeeForm extends AbstractWizadPage {
	private final Logger __logger = Logger.getLogger("");
    private DelicacyListGrid recordGrid;
    private DelicacyListGrid resultGrid;
    
    final IPickTreeItem departmentItem = new IPickTreeItem("departmentId", "归属部门");
    
	public BindDepartmentFunctionToEmployeeForm() {
    	HLayout main = new HLayout(10);
        main.setHeight100();
        main.setWidth100();
        
        VLayout reLayout = new VLayout();
        reLayout.setHeight100();
        reLayout.setWidth("48%");

        
        VLayout empLayout = new VLayout(10);
        empLayout.setHeight100();
        empLayout.setWidth("48%");
        
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
		
		
        HLayout companyLayout = new HLayout(10);
        companyLayout.setHeight("15%");
        empLayout.addMember(companyLayout);
        
        DynamicForm companyForm = new DynamicForm();
        companyForm.setWidth("80%");
        
        departmentItem.setCanSelectParentItems(true);
        departmentItem.setWidth("*");
        departmentItem.setValueField("treeId");
        departmentItem.setValueTree(KeyValueManager.getTree("departments"));
        departmentItem.addChangedHandler(new ChangedHandler() {
			
			@Override
			public void onChanged(ChangedEvent event) {
				// TODO Auto-generated method stub
				
		    	if(!BaseHelpUtils.isNullOrEmpty(empData)){
		    		Map<String,Object> params = new HashMap<>();
		    		params.put("businessId",event.getValue());
		    		params.put("type",2);
		    		//加载职员信息
		    		DBDataSource.callOperation("NQ_OnLoadDepartmentFunction","find",params, new DSCallback() {
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
		});
        
        companyForm.setNumCols(4);
        companyForm.setFields(departmentItem);
        ClientUtil.DynamicFormProcessAccordingToDevice(companyForm);
        companyLayout.addMember(companyForm);
		
        resultGrid = new DelicacyListGrid();
        resultGrid.setAutoFitFieldWidths(false);
        resultGrid.setCanRemoveRecords(true);
        resultGrid.setHeight("85%");
        resultGrid.setDataSource(DSMemployee.getInstance());
        resultGrid.setFields(fields);
        empLayout.addMember(resultGrid);
        
        main.addMember(reLayout);
		main.addMember(addToButton);
        main.addMember(empLayout);
        addMember(main);
        setPageMode(PAGE_MODE_UPDATE);
        loadDefaultData();
    }

    @Override
    public boolean checkData() {
    	if(BaseHelpUtils.isNullOrEmpty(departmentItem.getValue())) {
    		SC.say("请选择归属部门");
    		return false;
    	}
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
     * 加载某公司权限下人员
     */
    private void loadRoleEmpData(){
    	if(!BaseHelpUtils.isNullOrEmpty(empData)&&!BaseHelpUtils.isNullOrEmpty(departmentItem.getValue())){
    		Map<String,Object> params = new HashMap<>();
    		params.put("businessId",departmentItem.getValue());
    		params.put("type",2);
    		//加载职员信息
    		DBDataSource.callOperation("NQ_OnLoadDepartmentFunction","find",params, new DSCallback() {
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
	   param.put("optType", "saveCompanyFunction");
	   param.put("businessId", departmentItem.getValue());
	   param.put("type", 2);
	   MapUtils.convertRecordToMap(resultGrid.getDataSource(), resultGrid.getRecords(), param, "detailEmployee");
       return param;
    }
    
    private Record[] empData = null;
    
}
