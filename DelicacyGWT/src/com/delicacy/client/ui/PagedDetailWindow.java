package com.delicacy.client.ui;

import com.delicacy.client.PaginationPanel;
import com.delicacy.client.data.DBDataSource;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.KeyPressEvent;
import com.smartgwt.client.widgets.events.KeyPressHandler;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.VLayout;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public class PagedDetailWindow extends PopupWindow {

    public PagedDetailWindow() {
        super();
        VLayout global = new VLayout();
        global.setWidth100();
        global.setHeight100();
        this.addItem(global);
        global.addMember(resultGrid);
        global.addMember(pager);
        pager.getGotoButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                TextItem item = pager.getGotoPage();
                String strPageNo = item.getValueAsString();
                if (strPageNo == null || strPageNo.equals("")) {
                    return;
                }
                currentPage = Integer.parseInt(strPageNo);
                commonQuery();
            }
        });
        pager.getFirstButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                currentPage = 1;
                commonQuery();
            }
        });
        pager.getPreviousButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (currentPage > 1) {
                    currentPage--;
                    commonQuery();
                }
            }
        });

        pager.getNextButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (currentPage < totalPages) {
                    currentPage++;
                    commonQuery();
                }
            }
        });
        pager.addKeyPressHandler(new KeyPressHandler() {

            @Override
            public void onKeyPress(KeyPressEvent event) {
                if (event.getKeyName() != null) {
                    switch (event.getKeyName()) {
                        case "Arrow_Right":
                            if (currentPage < totalPages) {
                                currentPage++;
                                commonQuery();
                            }
                            break;
                        case "Arrow_Left":
                            if (currentPage > 1) {
                                currentPage--;
                                commonQuery();
                            }
                            break;
                    }
                }
            }
        });
        pager.getLastButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (totalPages > 0) {
                    currentPage = totalPages;
                    commonQuery();
                }
            }
        });
        pager.getRefreshButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                commonQuery();
            }
        });
    }

    public PagedDetailWindow(String title, String actionName, Map params, DataSource ds) {
        this();
        setTitle(title);
        operationName = actionName;
        if (params != null) {
            parameters = params;
        }
        parameters.put("pageLines", 20);
        __dataSource = ds;
        resultGrid.setDataSource(__dataSource);
    }

    public void commonQuery() {
        parameters.putAll(pager.getPagenationCondition(currentPage, 20));
        DBDataSource.callOperation(getOperationName(), getParameters(), new DSCallback() {
            @Override
            public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
                if (dsResponse.getStatus() >= 0) {
                    resultGrid.setData(dsResponse.getData());
                    if (currentPage == 1) {
                        totalPages = dsResponse.getAttributeAsInt("totalPages");
                        pager.getTotalPages().setContents(String.valueOf(totalPages));
                    }
                } else {
                    SC.say(dsResponse.getErrors().get("errorMsg").toString());
                }
                pager.getCurrentPage().setContents(String.valueOf(currentPage));
            }
        });
    }

    private int currentPage = 1;
    private int totalPages = 0;
    private final PaginationPanel pager = new PaginationPanel();
    private final DelicacyListGrid resultGrid = new DelicacyListGrid();
    private Map parameters = new HashMap();
    private DataSource __dataSource;
    private String operationName;

    /**
     * @return the __dataSource
     */
    public DataSource getDataSource() {
        return __dataSource;
    }

    /**
     * @param __dataSource the __dataSource to set
     */
    public void setDataSource(DataSource __dataSource) {
        this.__dataSource = __dataSource;
    }

    /**
     * @return the operationName
     */
    public String getOperationName() {
        return operationName;
    }

    /**
     * @param operationName the operationName to set
     */
    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    /**
     * @return the parameters
     */
    public Map getParameters() {
        return parameters;
    }

    /**
     * @param parameters the parameters to set
     */
    public void setParameters(Map parameters) {
        this.parameters = parameters;
    }

}
