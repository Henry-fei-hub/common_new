package com.delicacy.client.management.panel;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.delicacy.client.PanelFactory;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.data.DBDataSource;
import com.delicacy.client.data.DataEditEvent;
import com.delicacy.client.data.DataEditedHandler;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.management.datasource.DSSsystemprocessinstanceor;
import com.delicacy.client.management.form.SsystemprocessinstanceorSearchForm;
import com.delicacy.client.management.form.SsystemprocessinstanceorViewer;
import com.delicacy.client.ui.AbstractDetailViewer;
import com.delicacy.client.ui.AbstractSearchPanel;
import com.delicacy.client.ui.EditProcessWindow;
import com.delicacy.client.ui.GenericViewWindow;
import com.delicacy.client.ui.LayoutConstant;
import com.delicacy.client.ui.NewProcessWindow;
import com.delicacy.client.ui.ViewProcessWindow;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.FormLayoutType;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.DoubleClickEvent;
import com.smartgwt.client.widgets.events.DoubleClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.SearchForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;

public class SsystemprocessinstanceorPanel extends AbstractSearchPanel {

	private static final Logger __LOGGER = Logger.getLogger("");
	private SelectItem si;

	public static class Factory implements PanelFactory {

		private String id;

		@Override
		public Canvas create() {
			SsystemprocessinstanceorPanel cm = new SsystemprocessinstanceorPanel();
			id = cm.getID();
			return cm;
		}

		@Override
		public String getID() {
			return id;
		}

		@Override
		public String getDescription() {
			return "Ssystemprocessinstanceor";
		}

	}

	@Override
	public void init() {
		super.init();
		__controlPosition = LayoutConstant.RIGHT;
	}

	@Override
	public void initComponents() {
		resultGrid.setAutoFitFieldWidths(false);
		IButton refreshButton = new IButton("刷新");
		controlLayout.addMember(refreshButton);
		refreshButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				commonQuery();
			}
		});

		resultGrid.addDoubleClickHandler(new DoubleClickHandler() {
			@Override
			public void onDoubleClick(DoubleClickEvent event) {
				ListGridRecord selected = resultGrid.getSelectedRecord();
				ViewProcessWindow Reimbursement = new ViewProcessWindow();
				Reimbursement.setLayoutMode(0);
				Reimbursement.setInstanceData(selected);
				Reimbursement.initComponents();
				Reimbursement.setTitle(ClientUtil.getProcessTypeName(selected));
				Reimbursement.show();
			}
		});
		IButton viewButton = new IButton("查看详细");
		viewButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (!resultGrid.anySelected()) {
					SC.say("please select a record to edit");
					return;
				}
				ListGridRecord selected = resultGrid.getSelectedRecord();
				ViewProcessWindow Reimbursement = new ViewProcessWindow();
				Reimbursement.setLayoutMode(0);
				Reimbursement.setInstanceData(selected);
				Reimbursement.initComponents();
				Reimbursement.setTitle(ClientUtil.getProcessTypeName(selected));
				Reimbursement.show();
			}
		});
		controlLayout.addMember(viewButton);

		HLayout newButtonLayout = new HLayout();
		newButtonLayout.hide();
		newButtonLayout.setWidth(110);
		newButtonLayout.setHeight(26);
		controlLayout.addMember(newButtonLayout);
		IButton newButton = new IButton("");
		newButtonLayout.addMember(newButton);
		newButton.setWidth(26);
		newButton.setHeight(26);
		newButton.setIcon("[SKIN]/actions/add.png");
		newButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (si.getValue() == null)
					return;
				NewProcessWindow Reimbursement = new NewProcessWindow();
				Reimbursement.addDataEditedHandler(new DataEditedHandler() {
					@Override
					public void onDataEdited(DataEditEvent event) {
						commonQuery();
					}
				});
				Reimbursement.setProcessId(ClientUtil.checkAndGetIntValue(si.getValue()));
				Reimbursement.setLayoutMode(0);
				Reimbursement.initComponents();
				Reimbursement.setTitle(KeyValueManager.getValue("employee_processes", si.getValue().toString()));
				Reimbursement.show();
			}
		});
		DynamicForm df = new DynamicForm();
		df.setItemLayout(FormLayoutType.ABSOLUTE);
		df.setWidth(84);
		df.setHeight(26);
		df.setPadding(0);
		df.setCellPadding(0);
		si = new SelectItem("employeeProcess");
		si.setTop(0);
		si.setLeft(0);
		si.setWidth(84);
		si.setHeight(26);
		si.setShowTitle(false);
		LinkedHashMap vals = KeyValueManager.getValueMap("employee_processes");
		if (!vals.isEmpty()) {
			if (vals.keySet().toArray()[0] != null) {
				String v = vals.keySet().toArray()[0].toString();
				si.setValue(v);
				si.setValueMap(vals);
			}
		}
		
		df.setItems(si);
		df.setNumCols(1);
		newButtonLayout.addMember(df);

		IButton editButton = new IButton("修改流程");
		controlLayout.addMember(editButton);
		editButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (!resultGrid.anySelected()) {
					SC.say("please select a record to edit");
					return;
				}
				ListGridRecord selected = resultGrid.getSelectedRecord();
				int status = selected.getAttributeAsInt("processStatus");
				if (status != 0) {
					SC.say("对不起，该申请不能修改！");
					return;
				}
				EditProcessWindow Reimbursement = new EditProcessWindow();
				Reimbursement.addDataEditedHandler(new DataEditedHandler() {
					@Override
					public void onDataEdited(DataEditEvent event) {
						DBDataSource.copyRecord(event.getData(), resultGrid.getSelectedRecord());
					}
				});
				Reimbursement.setLayoutMode(0);
				Reimbursement.setInstanceData(selected);
				Reimbursement.initComponents();
				Reimbursement.setTitle(ClientUtil.getProcessTypeName(selected));
				Reimbursement.show();
			}
		});

		commonQuery();
	}

	public void showDetail() {
		GWT.runAsync(new RunAsyncCallback() {
			@Override
			public void onFailure(Throwable reason) {
				SC.say("failure to download code");
			}

			@Override
			public void onSuccess() {
				if (!resultGrid.anySelected()) {
					SC.say("请选择一条数据");
					return;
				}
				GenericViewWindow detail = new GenericViewWindow();
				detail.setTitle("Ssystemprocessinstanceor");
				detail.setWidth100();
				detail.setHeight100();
				SsystemprocessinstanceorViewer detailForm = new SsystemprocessinstanceorViewer();
				detailForm.setParentSearchForm(searchForm);
				detailForm.setLayoutMode(AbstractDetailViewer.COMPONENT_LAYOUT_TAB);
				detailForm.initComponents();
				detailForm.viewSelectedData(resultGrid);
				detail.setContent(detailForm);
				detail.centerInPage();
				detail.show();
			}
		});
	}

	@Override
	public boolean checkSearchCriteria(Map criteria) {
		criteria.put("addtionalCondition", "order by process_instance_id");
		criteria.put("employeeId", ClientUtil.getUserId());
		return true;
	}

	@Override
	public int getSearchFormHeight() {
		return 60;
	}

	@Override
	public SearchForm generateSearchForm() {
		return new SsystemprocessinstanceorSearchForm();
	}

	@Override
	public DataSource getDataSource() {
		return DSSsystemprocessinstanceor.getInstance();
	}

}
