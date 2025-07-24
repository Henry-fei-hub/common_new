package com.delicacy.client.app.panel;

import java.util.Map;
import java.util.logging.Logger;

import com.delicacy.client.PanelFactory;
import com.delicacy.client.app.datasource.DSProcessTestThreadTask;
import com.delicacy.client.app.datasource.DSSystemProcessorTestResult;
import com.delicacy.client.app.form.ProcessTestThreadTaskSearchForm;
import com.delicacy.client.app.form.ProcessTestThreadTaskViewer;
import com.delicacy.client.app.form.SystemProcessorTestResultViewer;
import com.delicacy.client.ui.AbstractDetailViewer;
import com.delicacy.client.ui.AbstractExpansionRelatedPage;
import com.delicacy.client.ui.GenericViewWindow;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.DoubleClickEvent;
import com.smartgwt.client.widgets.events.DoubleClickHandler;
import com.smartgwt.client.widgets.form.SearchForm;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class ProcessTestThreadTaskPanel extends AbstractExpansionRelatedPage
{


	private static final Logger __LOGGER = Logger.getLogger("");
	public static class Factory implements PanelFactory {
		private String id;
		@Override
		public Canvas create() {
			ProcessTestThreadTaskPanel cm = new ProcessTestThreadTaskPanel();
			id = cm.getID();
			return cm;
		}

		@Override
		public String getID() {
			return id;
		}

		@Override
		public String getDescription() {
			return "ProcessTestThreadTask";
		}

	}

	@Override
	public void initComponents() {
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
				showDetail();
			}
		});
		IButton viewButton = new IButton("查看详细");
		viewButton.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				showDetail();
			}
		});
		controlLayout.addMember(viewButton);
		commonQuery();
	}

	@Override
	public DoubleClickHandler getChildGridDoubleClickHandler(){
		return new DoubleClickHandler() {
			
			@Override
			public void onDoubleClick(DoubleClickEvent event) {
				if (!resultChildGrid.anySelected()) {
					SC.say("请选择一条数据");
					return;
				}
				GenericViewWindow detail = new GenericViewWindow();
				detail.setTitle("流程测试结果"); 
				detail.setWidth100(); 
				detail.setHeight100(); 
				SystemProcessorTestResultViewer detailForm = new SystemProcessorTestResultViewer();
				detailForm.setParentSearchForm(searchForm);
				detailForm.setLayoutMode(AbstractDetailViewer.COMPONENT_LAYOUT_TAB);
				detailForm.initComponents();
				detailForm.viewSelectedData(resultChildGrid);
				detail.setContent(detailForm);
				detail.centerInPage();
				detail.show();
			}
		};
    };
	
    @Override
    public void setChildGridOption(ListGrid resultChildGrid) {
    	resultChildGrid.setShowRowNumbers(true);
    	ListGridField[] fields = new ListGridField[8];
    	int idx = 0;
    	fields[idx++] = new ListGridField("employeeNo");
    	fields[idx++] = new ListGridField("employeeName");
    	fields[idx++] = new ListGridField("departmentId");
    	fields[idx++] = new ListGridField("plateId");
    	fields[idx++] = new ListGridField("companyId");
    	fields[idx++] = new ListGridField("processName");
    	fields[idx++] = new ListGridField("testResult");
    	fields[idx++] = new ListGridField("errorMsg");
    	resultChildGrid.setFields(fields);
    }
	
	public void showDetail() {
		GWT.runAsync(new RunAsyncCallback() {
			@Override
			public void onFailure(Throwable reason) {
				SC.say("failure to download code");
			}
			@Override
			public void onSuccess() {
				if(!resultGrid.anySelected()){ SC.say("请选择一条数据"); return;}
				GenericViewWindow detail = new GenericViewWindow();
				detail.setTitle("流程测试任务"); 
				detail.setWidth100(); 
				detail.setHeight100(); 
				ProcessTestThreadTaskViewer detailForm = new ProcessTestThreadTaskViewer();
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
		criteria.put("taskType", "5");
		criteria.put("addtionalCondition", "order by thread_task_manage_id");
		return true;
	}


	@Override
	public SearchForm generateSearchForm() {
		return new ProcessTestThreadTaskSearchForm();
	}

	@Override
	public DataSource getDataSource() {
		return DSProcessTestThreadTask.getInstance();
	}

	@Override
	public DataSource getChildDataSource() {
		return DSSystemProcessorTestResult.getInstance();
	}
}

