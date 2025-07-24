package com.delicacy.client.ui;

import com.delicacy.client.DelicacyPanel;
import com.delicacy.client.PaginationPanel;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.data.DBDataSource;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.AutoFitWidthApproach;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.util.Browser;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.KeyPressEvent;
import com.smartgwt.client.widgets.events.KeyPressHandler;
import com.smartgwt.client.widgets.form.SearchForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.SplitPane;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.tree.TreeGrid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public abstract class AbstractSearchTree extends DelicacyPanel {

    private static final Logger __logger = Logger.getLogger("");
    protected PaginationPanel pager;
    protected SearchForm searchForm;
    protected TreeGrid resultGrid;
    protected VLayout controlLayout;
    protected int __currentPage = 0;
    protected IButton searchButton;
    protected ToolStrip toolBar;
	protected SplitPane splitPane;

    public AbstractSearchTree() {
        super();
        __layoutMode = LayoutConstant.LEFTRIGHT;
        __detailCanvas = new ArrayList<>();
        __controlPosition = LayoutConstant.RIGHT;
        __needPagenation = true;
        __needControl = true;
    }

    @Override
    public Canvas getViewPanel() {
        init();
        searchForm = generateSearchForm();
        VLayout layout = new VLayout();
        layout.setWidth100();
        layout.setHeight100();
        layout.setBackgroundColor("#e2e2e2");

        if (searchForm != null) {
            ClientUtil.searchFormProcessAccordingToDevice(searchForm);
            if (getSearchFormHeight() > 0) {
                searchForm.setHeight(getSearchFormHeight());
            }
            HLayout searchLayout = new HLayout(5);
            searchLayout.setMargin(10);
            layout.addMember(searchLayout);

            searchForm.setWidth100();
            searchLayout.addMember(searchForm);

            VLayout buttonLayout = new VLayout(5);
            searchButton = new IButton("搜索");
            searchButton.setIcon("[SKIN]/actions/search.png");

            IButton resetButton = new IButton("重置");
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
        if (__needControl) {
            switch (__controlPosition) {
                case LayoutConstant.TOP:
                case LayoutConstant.BOTTOM:
                    toolBar = new ToolStrip();
                    toolBar.setHeight(40);
                    break;
                case LayoutConstant.RIGHT:
                case LayoutConstant.LEFT:
                    controlLayout = new VLayout();
                    controlLayout.setHeight100();
                    controlLayout.setWidth(120);
                    controlLayout.setLayoutTopMargin(30);
                    controlLayout.setLayoutLeftMargin(5);
                    controlLayout.setLayoutRightMargin(5);
                    controlLayout.setMembersMargin(10);
                    break;
            }
        }
        if (__needControl && __controlPosition == LayoutConstant.TOP) {
            resultLayout.addMember(toolBar);
        }
        HLayout main = new HLayout();
        main.setWidth100();
        main.setHeight100();
        resultLayout.addMember(main);
        if (__needControl && __controlPosition == LayoutConstant.LEFT) {
            main.addMember(controlLayout);
        }
        Canvas mainCanvas = null;
        if (__layoutMode == LayoutConstant.LEFTRIGHT) {
            mainCanvas = generateHDataCanvas();
        } else {
            mainCanvas = generateVDataCanvas();
        }
        main.addMember(mainCanvas);
        if (__needControl && __controlPosition == LayoutConstant.RIGHT) {
            main.addMember(controlLayout);
        }
        if (__needControl && __controlPosition == LayoutConstant.BOTTOM) {
            resultLayout.addMember(toolBar);
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

    public void generateTreeGrid() {
        resultGrid = new TreeGrid();
        resultGrid.setDataSource(getDataSource());
        resultGrid.setSaveLocally(true);
        resultGrid.setAutoFetchData(false);
        resultGrid.setAutoFitWidthApproach(AutoFitWidthApproach.BOTH);
        resultGrid.setAutoFitFieldWidths(false);
        resultGrid.setShowHeaderContextMenu(false);
        resultGrid.setShowHeaderMenuButton(false);
        resultGrid.setCanEdit(true);
        resultGrid.setCanReorderRecords(true);
        resultGrid.setCanAcceptDroppedRecords(true);
        resultGrid.setCanReparentNodes(true);
    }

    public Canvas generateHDataCanvas() {
        if (__detailCanvas != null && __detailCanvas.size() > 0) {
            setSplitPane(new SplitPane());
            getSplitPane().setHeight100();
            getSplitPane().setWidth100();
//            getSplitPane().setShowListToolStrip(false);
//            getSplitPane().setShowDetailToolStrip(true);

            generateTreeGrid();
            getSplitPane().setNavigationPane(resultGrid);

            resultGrid.setWidth("50%");
            if (__detailCanvas.size() == 1) {
                Canvas c1 = __detailCanvas.get(0);
                c1.setHeight100();
                c1.setWidth("50%");
                getSplitPane().setListPane(c1);
            } else if (__detailCanvas.size() == 2) {
                Canvas c1 = __detailCanvas.get(0);
                c1.setHeight100();
                c1.setWidth("50%");
                getSplitPane().setListPane(c1);
                Canvas c2 = __detailCanvas.get(1);
                c2.setHeight100();
                c2.setWidth("50%");
                getSplitPane().setDetailPane(c2);
            } else {
                SectionStack sectionStack = new SectionStack();
                sectionStack.setHeight100();
                sectionStack.setWidth("50%");
                sectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);
                sectionStack.setAnimateSections(true);
                List<SectionStackSection> sections = new ArrayList<>();
                for (Canvas c : __detailCanvas) {
                    AbstractWizadPage awp = (AbstractWizadPage) c;
                    SectionStackSection sss = new SectionStackSection();
                    sss.setTitle(awp.getName());
                    sss.setExpanded(true);
                    sss.setItems(c);
                }
                sectionStack.setSections(sections.toArray(new SectionStackSection[sections.size()]));
                getSplitPane().setListPane(sectionStack);
            }
            return getSplitPane();
        } else {
            generateTreeGrid();
            resultGrid.setWidth100();
            resultGrid.setHeight100();
            return resultGrid;
        }
    }

    public Canvas generateVDataCanvas() {
        VLayout dataLayout = new VLayout();
        dataLayout.setHeight100();
        dataLayout.setWidth100();

        generateTreeGrid();
        resultGrid.setWidth100();
        dataLayout.addMember(resultGrid);
        if (__detailCanvas != null && __detailCanvas.size() > 0) {
            resultGrid.setHeight("50%");
            if (__detailCanvas.size() == 1) {
                Canvas c1 = __detailCanvas.get(0);
                c1.setWidth100();
                c1.setHeight("50%");
                dataLayout.addMember(c1);
            } else {
                SectionStack sectionStack = new SectionStack();
                sectionStack.setWidth100();
                sectionStack.setHeight("50%");
                sectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);
                sectionStack.setAnimateSections(true);
                List<SectionStackSection> sections = new ArrayList<>();
                for (Canvas c : __detailCanvas) {
                    AbstractWizadPage awp = (AbstractWizadPage) c;
                    SectionStackSection sss = new SectionStackSection();
                    sss.setTitle(awp.getName());
                    sss.setExpanded(true);
                    sss.setItems(c);
                }
                sectionStack.setSections(sections.toArray(new SectionStackSection[sections.size()]));
            }
        } else {
            resultGrid.setHeight100();
        }
        return dataLayout;
    }

    public void init() {
        __layoutMode = LayoutConstant.LEFTRIGHT;
        __detailCanvas = new ArrayList<>();
        __controlPosition = LayoutConstant.RIGHT;
        __needPagenation = false;
        __needControl = true;
    }

    public void setControlLayoutWidth() {
        setControlLayoutWidth(120);
    }

    public boolean showPagination() {
        return isNeedPagenation();
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
        Criteria c = ClientUtil.mapToCriteria(condition);
        if(!checkSearchCriteria(c)){
        	return null;
        }
        return c.getValues();
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
        final LoadingWindow loading = new LoadingWindow();
        DBDataSource.callOperation(operationName, "find", condition, new DSCallback() {
            @Override
            public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
            	loading.destroy();
                if (searchForm != null) {
                    searchButton.setIcon("[SKIN]/actions/search.png");
                }
                if (dsResponse.getStatus() >= 0) {
                    fetchDataCallback(dsResponse, data);
                } else {
                    SC.say(dsResponse.getErrors().get("errorMsg").toString());
                }
                if (showPagination() && pager != null) {
                    pager.getCurrentPage().setContents(String.valueOf(currentPage));
                }
            }
        });
    }

    public boolean checkSearchCriteria(Map criteria) {
        return true;
    }

    public void afterDataReceived(Record[] data) {
    }

    public String getSourceGenUrl() {
        return null;
    }

    public boolean showControlPanel() {
        return __needControl;
    }

    public int getSearchFormHeight() {
        return 0;
    }

    public abstract void fetchDataCallback(DSResponse response, Object rawData);

    public boolean checkSearchCriteria(Criteria criteria) {
        return true;
    }

    @Override
    public String getIntro() {
        return null;
    }
    protected int totalPages = 0;
    protected int currentPage = 1;
    protected final int pageLines = 20;

    public abstract SearchForm generateSearchForm();

    public abstract DataSource getDataSource();

    public abstract void initComponents();

    protected int __layoutMode = LayoutConstant.LEFTRIGHT;
    protected List<Canvas> __detailCanvas = new ArrayList<>();
    protected int __controlPosition = LayoutConstant.RIGHT;
    protected boolean __needPagenation = true;
    protected boolean __needControl = true;

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

    /**
     * @return the __layoutMode
     */
    public int getLayoutMode() {
        return __layoutMode;
    }

    /**
     * @param __layoutMode the __layoutMode to set
     */
    public void setLayoutMode(int __layoutMode) {
        this.__layoutMode = __layoutMode;
    }

    /**
     * @return the __details
     */
    public List<Canvas> getDetails() {
        return __detailCanvas;
    }

    /**
     * @param __details the __details to set
     */
    public void setDetails(List<Canvas> __details) {
        this.__detailCanvas = __details;
    }

    /**
     * @return the __controlPosition
     */
    public int getControlPosition() {
        return __controlPosition;
    }

    /**
     * @param __controlPosition the __controlPosition to set
     */
    public void setControlPosition(int __controlPosition) {
        this.__controlPosition = __controlPosition;
    }

    /**
     * @return the _needPagenation
     */
    public boolean isNeedPagenation() {
        return __needPagenation;
    }

    /**
     * @param _needPagenation the _needPagenation to set
     */
    public void setNeedPagenation(boolean _needPagenation) {
        this.__needPagenation = _needPagenation;
    }

    /**
     * @return the _needControl
     */
    public boolean isNeedControl() {
        return __needControl;
    }

    /**
     * @param _needControl the _needControl to set
     */
    public void setNeedControl(boolean _needControl) {
        this.__needControl = _needControl;
    }

	/**
	 * @return the splitPane
	 */
	public SplitPane getSplitPane() {
		return splitPane;
	}

	/**
	 * @param splitPane the splitPane to set
	 */
	public void setSplitPane(SplitPane splitPane) {
		this.splitPane = splitPane;
	}
}
