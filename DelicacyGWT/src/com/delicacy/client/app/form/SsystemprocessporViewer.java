package com.delicacy.client.app.form;

import java.util.ArrayList;
import java.util.List;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.events.DoubleClickEvent;
import com.smartgwt.client.widgets.events.DoubleClickHandler;
import com.smartgwt.client.data.*;
import com.delicacy.client.data.DBDataSource;
import com.delicacy.client.data.GroupColumn;
import com.delicacy.client.ui.GenericViewWindow;
import com.smartgwt.client.widgets.form.SearchForm;
import com.delicacy.client.ui.AbstractDetailViewer;
import com.delicacy.client.ui.DelicacyListGrid;
import com.delicacy.client.app.datasource.DSSsystemprocesspor;
import com.delicacy.client.app.datasource.DSSystemProcessActivity;

public class SsystemprocessporViewer extends AbstractDetailViewer {

    private final DelicacyListGrid systemProcessActivitiesGrid = new DelicacyListGrid();
    private SearchForm __parentSearchForm;

    public SsystemprocessporViewer() {
    }

    @Override
    public void initComponents() {
        super.initComponents();
        systemProcessActivitiesGrid.setDataSource(DSSystemProcessActivity.getInstance());
        systemProcessActivitiesGrid.addDoubleClickHandler(new DoubleClickHandler() {
            @Override
            public void onDoubleClick(DoubleClickEvent event) {
                GenericViewWindow detail = new GenericViewWindow();
                detail.setTitle("");
                detail.setWidth(700);
                detail.setHeight(500);
                SystemProcessActivityViewer viewer = new SystemProcessActivityViewer();
                viewer.setLayoutMode(AbstractDetailViewer.COMPONENT_LAYOUT_NORMAL);
                viewer.initComponents();
                viewer.viewSelectedData((ListGrid) event.getSource());
                detail.setContent(viewer);
                detail.centerInPage();
                detail.show();
            }
        });
    }

    @Override
    public int getHorizontalPercent() {
        return 0;
    }

    @Override
    public String getName() {
        return "流程管理";
    }

    @Override
    public int getGroupCount() {
        return 0;
    }

    @Override
    public List<GroupColumn> getGroupColumnNames() {
        List<GroupColumn> gcs = new ArrayList<GroupColumn>();
        return gcs;
    }

    @Override
    public void viewSelectedData(ListGrid grid) {
        super.viewSelectedData(grid);
        Record selected = grid.getSelectedRecord();
        Object val;
        java.util.Map condition = null;
        java.util.Map parentSearch = __parentSearchForm == null ? new java.util.HashMap() : __parentSearchForm.getValues();
        condition = new java.util.HashMap();
        condition.put("processId", selected.getAttributeAsString("processId"));
        DBDataSource.callOperation("ST_SystemProcessActivity", "find", condition, new DSCallback() {
            @Override
            public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
                if (dsResponse.getStatus() >= 0) {
                    systemProcessActivitiesGrid.setData(dsResponse.getData());
                }
            }
        });
    }

    @Override
    public DataSource getMainDataSource() {
        return DSSsystemprocesspor.getInstance();
    }

    @Override
    public int getDetailCount() {
        return 1;
    }

    @Override
    public List<ListGrid> getDetailListGrids() {
        List<ListGrid> res = new ArrayList<ListGrid>();
        res.add(systemProcessActivitiesGrid);
        return res;
    }

    @Override
    public List<String> getDetailNames() {
        List<String> res = new ArrayList<String>();
        res.add("");
        return res;
    }

    public SearchForm getParentSearchForm() {
        return this.__parentSearchForm;
    }

    public void setParentSearchForm(SearchForm value) {
        this.__parentSearchForm = value;
    }

}
