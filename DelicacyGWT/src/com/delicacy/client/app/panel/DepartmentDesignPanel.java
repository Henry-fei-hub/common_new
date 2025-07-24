package com.delicacy.client.app.panel;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.delicacy.client.BaseHelpUtils;
import com.delicacy.client.MapUtils;
import com.delicacy.client.app.datasource.DSMemployee;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.data.DBDataSource;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.ui.DelicacyListGrid;
import com.delicacy.client.ui.PopupWindow;
import com.google.gwt.event.shared.HasHandlers;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.IPickTreeItem;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class DepartmentDesignPanel extends HLayout implements HasHandlers {
	private static Logger __logger = Logger.getLogger("");
	// 定义面板样式
	public final static String BORDER_STYLE = "2px solid #f2f2f2;border-radius:8px;";
	// 定义整个面板的layout,整个面板分头部面板和中心面板
	private VLayout mainLayout;
	// 定义头部面板，包括左右两个面板
	private HLayout topVLayout;
	// 定义中心面板，包括左、中、右三个主面板
	private HLayout centerLayout;
	private DelicacyListGrid employeeGrid;

	Map<String,Object> params = new HashMap<>();

	Record[] records;

	public void initComponents() {
		mainLayout = new VLayout();
		mainLayout.setBackgroundColor("#FFF");
		mainLayout.setBorder("2px solid #e2e2e2;border-radius:5px");
		mainLayout.setWidth100();
		mainLayout.setHeight100();

		topVLayout = new HLayout();
		topVLayout.setWidth100();
		topVLayout.setHeight("10%");
		topVLayout.setMargin(10);
		topVLayout.setBackgroundColor("#e2e2e2");

		final IPickTreeItem departmentIdItem = new IPickTreeItem("departmentId", "归属部门");
		departmentIdItem.setCanSelectParentItems(true);
		departmentIdItem.setWidth("*");
		departmentIdItem.setValueField("treeId");
		departmentIdItem.setValueTree(KeyValueManager.getTree("departments"));
		DynamicForm form = new DynamicForm();
		form.setWidth("20%");
		form.setHeight100();
		form.setNumCols(2);
		form.setMargin(10);
		form.setItems(departmentIdItem);
		
		IButton sureButton = new IButton("确认");
		sureButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				//获取部门ID
				int departmentId = BaseHelpUtils.getIntValue(departmentIdItem.getValue());
				if(departmentId==0){//说明未选择部门
					SC.say("提示","请选择要归集的部门");
					return;
				}
				if(departmentId == oldDepartmentId){
					SC.say("提示","选择的归集部门不能与原部门相同");
					return;
				}
				//获取要转移的人员
			 records = employeeGrid.getSelectedRecords();
				int len = records.length;
				if(len == 0){//说明未选择要转移的人员
					SC.say("提示","请选择要转移的人员");
					return;
				}

				params.put("optType", "onEmpOfDetChange");
				params.put("departmentId", departmentId);
				Map<String,Object> param = new HashMap<>();
				param.put("departmentId", departmentId);
				param.put("deleteFlag", 0);//未删除的
				DBDataSource.callOperation("ST_Department", "find", param, new DSCallback() {
					@Override
					public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
						int status = dsResponse.getStatus();
						if (status >= 0) {
							Record record = dsResponse.getData()[0];
							if (null != record) {
								Integer plateId =BaseHelpUtils.getIntValue(record.getAttributeAsInt("plateId")) ;
								params.put("plateId", plateId);
							}
							MapUtils.convertRecordToMap(employeeGrid.getDataSource(), records, params, "employeeList");
							DBDataSource.callOperation("EP_OnHrManageProcess",params, new DSCallback() {
								@Override
								public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
									if (dsResponse.getStatus() >= 0) {
										SC.say("提示","操作成功");
										loadData();
									}
								}
							});
						}

					}
				});

			}
		});
		
		IButton cancelButton = new IButton("取消");
		cancelButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				getParentWindow().destroy();
			}
		});

		HLayout buttonHLayout = new HLayout();
		buttonHLayout.setWidth("20%");
		buttonHLayout.setHeight100();
		buttonHLayout.setMargin(10);
		buttonHLayout.setMembersMargin(20);
		buttonHLayout.addMember(sureButton);
		buttonHLayout.addMember(cancelButton);

		topVLayout.addMember(form);
		topVLayout.addMember(buttonHLayout);
		mainLayout.addMember(topVLayout);

		centerLayout = new HLayout(10);
		centerLayout.setHeight("90%");
		centerLayout.setWidth100();
		centerLayout.setMembersMargin(10);
		centerLayout.setLayoutTopMargin(10);
		mainLayout.addMember(centerLayout);
		
		employeeGrid = new DelicacyListGrid();
		employeeGrid.setShowRowNumbers(true);
		employeeGrid.setSelectionType(SelectionStyle.SIMPLE);
		employeeGrid.setSelectionAppearance(SelectionAppearance.CHECKBOX);
		employeeGrid.setAutoFitFieldWidths(false);
		employeeGrid.setDataSource(DSMemployee.getInstance());
		ListGridField[] fields = new ListGridField[5];
		int idx = 0;
		fields[idx] = new ListGridField("employeeId");
		fields[idx].setHidden(true);
		idx++;
		fields[idx] = new ListGridField("employeeNo");
		idx++;
		fields[idx] = new ListGridField("employeeName");
		idx++;
		fields[idx] = new ListGridField("plateId");
		ComboBoxItem plateIdSelect = new ComboBoxItem();
		plateIdSelect.setValueMap(KeyValueManager.getValueMap("system_dictionary_1"));
		fields[idx].setEditorProperties(plateIdSelect);
		idx++;
		fields[idx] = new ListGridField("departmentId");
		ComboBoxItem departmentIdSelect = new ComboBoxItem();
		departmentIdSelect.setValueMap(KeyValueManager.getValueMap("departments"));
		fields[idx].setEditorProperties(departmentIdSelect);
		idx++;
		employeeGrid.setFields(fields);
		centerLayout.addMember(employeeGrid);
		
		addMember(mainLayout);
		
		loadData();
	}
	
	// 加载数据集
	public void loadData() {
		Map<Object, Object> param = new HashMap<>();
		param.put("departmentId",oldDepartmentId);
		param.put("status",0);//只检索在职的人员
		DBDataSource.callOperation("ST_Employee","find",param, new DSCallback() {
			@Override
			public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
				if (dsResponse.getStatus() >= 0) {
					employeeGrid.setData(dsResponse.getData());
				}
			}
		});
	}
	

	
	private int oldDepartmentId;
	private PopupWindow parentWindow;

	public int getOldDepartmentId() {
		return oldDepartmentId;
	}

	public void setOldDepartmentId(int oldDepartmentId) {
		this.oldDepartmentId = oldDepartmentId;
	}

	public PopupWindow getParentWindow() {
		return parentWindow;
	}

	public void setParentWindow(PopupWindow parentWindow) {
		this.parentWindow = parentWindow;
	}
	
	
}