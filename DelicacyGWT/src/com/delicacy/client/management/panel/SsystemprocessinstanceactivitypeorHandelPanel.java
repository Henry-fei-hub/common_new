package com.delicacy.client.management.panel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.delicacy.client.PanelFactory;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.management.datasource.DSSsystemprocessinstanceactivitypeor;
import com.delicacy.client.management.form.SsystemprocessinstanceactivitypeorSearchForm;
import com.delicacy.client.ui.AbstractSearchPanel;
import com.delicacy.client.ui.LayoutConstant;
import com.delicacy.client.ui.ViewProcessWindow;
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
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class SsystemprocessinstanceactivitypeorHandelPanel extends AbstractSearchPanel {

    private static final Logger __LOGGER = Logger.getLogger("");

    public static class Factory implements PanelFactory {

        private String id;

        @Override
        public Canvas create() {
            SsystemprocessinstanceactivitypeorHandelPanel cm = new SsystemprocessinstanceactivitypeorHandelPanel();
            id = cm.getID();
            return cm;
        }

        @Override
        public String getID() {
            return id;
        }

        @Override
        public String getDescription() {
            return "Ssystemprocessinstanceactivitypeor";
        }

    }

    @Override
    public void init() {
        super.init();
        __controlPosition = LayoutConstant.RIGHT;
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

        resultGrid.setShowRowNumbers(true);
        resultGrid.addDoubleClickHandler(new DoubleClickHandler() {
            @Override
            public void onDoubleClick(DoubleClickEvent event) {
                showDetail();
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
                    SC.say("please select a record to edit");
                    return;
                }
                ListGridRecord selected = resultGrid.getSelectedRecord();
                ViewProcessWindow Reimbursement = new ViewProcessWindow();
                Reimbursement.setWidth("80%");
                Reimbursement.setHeight("80%");
                Reimbursement.setLayoutMode(0);
                Reimbursement.setInstanceData(selected);
                Reimbursement.initComponents();
                Reimbursement.setTitle(ClientUtil.getProcessTypeName(selected));
                Reimbursement.show();
            }
        });
    }

    @Override
    public boolean checkSearchCriteria(Map criteria) {
        List<Map> keyvalues = new ArrayList<>();
        Map<String, String> kv = new HashMap<>();
        kv.put("key", "and spia.status = 1");
        kv.put("value", "and spia.status > 1");
        keyvalues.add(kv);
        criteria.put("keyValues", keyvalues);
        criteria.put("addtionalCondition", "order by instance_activity_start_time desc");
        criteria.put("employeeId", ClientUtil.getUserId());
        return true;
    }
    
    @Override
    public int getSearchFormHeight() {
    	return 60;
    }

    @Override
    public SearchForm generateSearchForm() {
        return new SsystemprocessinstanceactivitypeorSearchForm();
    }

    @Override
    public DataSource getDataSource() {
        return DSSsystemprocessinstanceactivitypeor.getInstance();
    }

}
