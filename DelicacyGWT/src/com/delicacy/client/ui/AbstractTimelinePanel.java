package com.delicacy.client.ui;

import com.delicacy.client.DelicacyPanel;
import com.delicacy.client.PaginationPanel;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.data.DBDataSource;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.TimeUnit;
import com.smartgwt.client.util.Browser;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.calendar.HeaderLevel;
import com.smartgwt.client.widgets.calendar.Timeline;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.KeyPressEvent;
import com.smartgwt.client.widgets.events.KeyPressHandler;
import com.smartgwt.client.widgets.form.SearchForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public abstract class AbstractTimelinePanel extends DelicacyPanel {

    private static final Logger __logger = Logger.getLogger("");
    protected PaginationPanel pager;
    protected SearchForm searchForm;
    protected Timeline resultGrid;
    protected VLayout controlLayout;
    private int __currentPage = 0;
    private IButton searchButton;

    public AbstractTimelinePanel() {
        super();
    }

    @Override
    public Canvas getViewPanel() {
        searchForm = generateSearchForm();
        VLayout layout = new VLayout();
        layout.setWidth100();
        layout.setHeight100();
        layout.setBackgroundColor("#e2e2e2");

        if (searchForm != null) {
            ClientUtil.searchFormProcessAccordingToDevice(searchForm);
            HLayout searchLayout = new HLayout(5);
            searchLayout.setMargin(10);
            layout.addMember(searchLayout);

            searchForm.setWidth100();
            searchLayout.addMember(searchForm);

            VLayout buttonLayout = new VLayout(5);
            searchButton = new IButton("Find");
            searchButton.setIcon("[SKIN]/actions/search.png");

            IButton resetButton = new IButton("Reset");
            resetButton.setIcon("[SKIN]/actions/redo.png");

            buttonLayout.addMember(searchButton);
            buttonLayout.addMember(resetButton);

            searchLayout.addMember(buttonLayout);

            searchButton.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    currentPage = 1;
                    commonQuery();
                }
            });

            resetButton.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    searchForm.editNewRecord();
                }
            });
        }
        VLayout resultLayout = new VLayout();
        resultLayout.setWidth100();
        resultLayout.setHeight100();

        HLayout dataLayout = new HLayout();
        dataLayout.setHeight100();
        dataLayout.setWidth100();
//        dataLayout.setBorder("2px solid gray");
        resultLayout.addMember(dataLayout);

        resultGrid = new Timeline();
        resultGrid.setDataSource(getDataSource());
        HeaderLevel[] headerLevels = new HeaderLevel[]{
            new HeaderLevel(TimeUnit.WEEK),
            new HeaderLevel(TimeUnit.DAY)
        };
        resultGrid.setHeaderLevels(headerLevels);
        resultGrid.setCanEditLane(false);
        resultGrid.setCanEditEvents(false);
        DataSourceField dsf = getDataSource().getField(getLaneName());
        if (dsf != null) {
            ListGridField[] laneFields = new ListGridField[]{
                new ListGridField(getLaneName(), dsf.getTitle(), getLaneLength())};
            resultGrid.setLaneFields(laneFields);
        }
        Date startDate = new Date();
        CalendarUtil.setToFirstDayOfMonth(startDate);
        resultGrid.setStartDate(startDate);
        Date endDate = new Date();
        CalendarUtil.addMonthsToDate(endDate, 2);
        CalendarUtil.setToFirstDayOfMonth(endDate);
        resultGrid.setEndDate(endDate);
        dataLayout.addMember(resultGrid);
        if (showControlPanel()) {
            controlLayout = new VLayout();
            controlLayout.setHeight100();
            controlLayout.setLayoutTopMargin(30);
            controlLayout.setLayoutLeftMargin(5);
            controlLayout.setLayoutRightMargin(5);
            controlLayout.setMembersMargin(10);

            dataLayout.addMember(controlLayout);
        }

        layout.addMember(resultLayout);

        if (Browser.getIsDesktop() && showPagination()) {
            pager = new PaginationPanel();
            resultLayout.addMember(pager);

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
            pager.getNextButton().addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    if (currentPage < totalPages) {
                        currentPage++;
                        commonQuery();
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
        initComponents();
        if (showControlPanel()) {
            setControlLayoutWidth();
        }
        return layout;
    }

    public void setControlLayoutWidth() {
        setControlLayoutWidth(120);
    }

    public void setControlLayoutWidth(int w) {
        controlLayout.setWidth(w);
        Canvas[] mems = controlLayout.getMembers();
        if (mems != null && mems.length > 0) {
            for (Canvas c : mems) {
                c.setWidth(w - 10);
            }
        }
    }

    public abstract String getLaneName();

    public abstract int getLaneLength();

    public abstract void generateLaneData(Record[] data);

    public Map generateCriteria() {
        Map condition = new HashMap();
        if (searchForm != null) {
            Map values = searchForm.getValues();
            condition.putAll(values);
        }
        if (pager != null) {
            Map pageCon = pager.getPagenationCondition(currentPage, pageLines);
            condition.putAll(pageCon);
        }
        if (!checkSearchCriteria(condition)) {
            return null;
        }
        return condition;
    }

    public void commonQuery() {
        Map condition = generateCriteria();
        if (condition == null) {
            return;
        }
        if (searchForm != null) {
            searchButton.setIcon("loading38.gif");
        }
        DBDataSource ds = (DBDataSource) getDataSource();
        String operationName = ds.getActionMode() + ds.getActionName();
        DBDataSource.callOperation(operationName, condition, new DSCallback() {
            @Override
            public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
                if (searchForm != null) {
                    searchButton.setIcon("[SKIN]/actions/search.png");
                }
                if (dsResponse.getStatus() >= 0) {
                    generateLaneData(dsResponse.getData());
                    if (showPagination()) {
                        if (currentPage == 1) {
                            totalPages = dsResponse.getAttributeAsInt("totalPages");
                            pager.getTotalPages().setContents(String.valueOf(totalPages));
                        }
                    }
                } else {
                    SC.say(dsResponse.getErrors().get("errorMsg").toString());
                }
                if (showPagination()) {
                    pager.getCurrentPage().setContents(String.valueOf(currentPage));
                }
            }
        });
    }

    public boolean checkSearchCriteria(Map criteria) {
        return true;
    }

    public String getSourceGenUrl() {
        return null;
    }

    public boolean showControlPanel() {
        return true;
    }

    public boolean showPagination() {
        return true;
    }

    @Override
    public String getIntro() {
        return null;
    }
    private int totalPages = 0;
    protected int currentPage = 1;
    private final int pageLines = 20;

    protected void setAppWindowAttributes() {
        popupWindow.setWidth100();
        popupWindow.setHeight100();
        popupWindow.setIsModal(true);
        popupWindow.setShowCloseButton(true);
        popupWindow.setShowMaximizeButton(true);
        popupWindow.setShowMinimizeButton(true);
        popupWindow.addCloseClickHandler(new CloseClickHandler() {
            @Override
            public void onCloseClick(CloseClickEvent event) {
                popupWindow.destroy();
            }
        });
    }

    protected Window popupWindow;

    public abstract SearchForm generateSearchForm();

    public abstract DataSource getDataSource();

    public abstract void initComponents();

    /**
     * @return the __currentPage
     */
    public int getCurrentPage() {
        return __currentPage;
    }

    /**
     * @param __currentPage the __currentPage to set
     */
    public void setCurrentPage(int __currentPage) {
        this.__currentPage = __currentPage;
    }
}
