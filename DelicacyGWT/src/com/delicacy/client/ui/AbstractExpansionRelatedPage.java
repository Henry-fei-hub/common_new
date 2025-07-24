package com.delicacy.client.ui;

import java.util.HashMap;
import java.util.Map;

import com.delicacy.client.DelicacyPanel;
import com.delicacy.client.PaginationPanel;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.data.DBDataSource;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.AutoFitWidthApproach;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.RowEndEditAction;
import com.smartgwt.client.util.Browser;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.DoubleClickHandler;
import com.smartgwt.client.widgets.events.KeyPressEvent;
import com.smartgwt.client.widgets.events.KeyPressHandler;
import com.smartgwt.client.widgets.form.SearchForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 
 * @ClassName: AbstractExpansionRelatedPage 
 * @Description:  扩展数据定义面板父类(单层子父结构)    PS:只进行了二级结构定义 如需多级 请自行扩展
 * @author CL
 * @date 2016年10月25日 
 *
 */
public abstract class AbstractExpansionRelatedPage extends DelicacyPanel {
	
	protected PaginationPanel pager;
	protected SearchForm searchForm;//搜索菜单栏
	protected ListGrid resultGrid;//父结构grid
	protected ListGrid resultChildGrid;//子结构grid
	
	protected VLayout controlLayout;//右边控制面板（控制按钮）
	
    protected int __currentPage = 0;
    protected IButton searchButton;

	@Override
	public Canvas getViewPanel() {
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

		HLayout dataLayout = new HLayout();
		dataLayout.setHeight100();
		dataLayout.setWidth100();
		resultLayout.addMember(dataLayout);
		
		resultGrid = new ListGrid() {    
            public DataSource getRelatedDataSource(ListGridRecord record) {    
                return getChildDataSource();    
            }    
    
            @Override    
            protected Canvas getExpansionComponent(final ListGridRecord record) {    
                final ListGrid grid = this;    
                VLayout layout = new VLayout(5);    
                layout.setPadding(5);    
                resultChildGrid = createChildGrid(); 
                resultChildGrid.setHeight(224);    
                resultChildGrid.setCellHeight(22);    
                resultChildGrid.setDataSource(getRelatedDataSource(record));   
                if(!doCustomCommonQuery()){
                	resultChildGrid.fetchRelatedData(record, getDataSource());    
                }
    
                resultChildGrid.setCanEdit(getChildGridCanEdit());    
                resultChildGrid.setModalEditing(true);    
                resultChildGrid.setEditEvent(ListGridEditEvent.CLICK);    
                resultChildGrid.setListEndEditAction(RowEndEditAction.NEXT);    
                resultChildGrid.setAutoSaveEdits(false);
                resultChildGrid.setCanRemoveRecords(true);
                resultChildGrid.setCanDragSelectText(true);
                resultChildGrid.setAutoFitWidthApproach(AutoFitWidthApproach.BOTH);
                resultChildGrid.setAutoFitFieldWidths(true);
                if(null != getChildGridDoubleClickHandler()){
                	resultChildGrid.addDoubleClickHandler(getChildGridDoubleClickHandler());
                }
                
                //设置子GRID操作属性
                setChildGridOption(resultChildGrid);
                layout.addMember(resultChildGrid);    
                HLayout littleControlLayout = new HLayout(10);//子菜单栏控制面板（点击打开节点后可操作的按钮面板）
                littleControlLayout.setAlign(Alignment.RIGHT);    
                
                //添加操作面板
                if(null != getChildGridOptionCanvas()){
                	Canvas[] allC = getChildGridOptionCanvas();
                    for (Canvas canvas : allC) {
                    	littleControlLayout.addMember(canvas);
    				}
                }
                
                //默认含有关闭面板按钮   方便操作
                IButton closeButton = new IButton("关闭");
                closeButton.addClickHandler(new ClickHandler() {    
                    public void onClick(ClickEvent event) {    
                        grid.collapseRecord(record);    
                    }    
                });    
                littleControlLayout.addMember(closeButton);    
                                                   
                layout.addMember(littleControlLayout);  
                //当为自定义搜索的时候设置数据
                if(doCustomCommonQuery()){
                	customCommonQuery(record, resultChildGrid);
                }
                return layout;    
            }  
            @Override
            protected String getCellCSSText(ListGridRecord record, int rowNum, int colNum) {
            	return getCustomCellCSSText(record, rowNum, colNum);
            }
        };    
		resultGrid.setHeight100();
		resultGrid.setWidth100();
		resultGrid.setDataSource(getDataSource());
		resultGrid.setSaveLocally(true);
		resultGrid.setAutoFetchData(false);
		resultGrid.setAutoFitWidthApproach(AutoFitWidthApproach.BOTH);
		resultGrid.setAutoFitFieldWidths(true);
		resultGrid.setShowHeaderContextMenu(false);
		resultGrid.setShowHeaderMenuButton(false);
		resultGrid.setCanEdit(false);
//		resultGrid.setCanReorderRecords(true);
		resultGrid.setCanAcceptDroppedRecords(true);
        resultGrid.setDrawAheadRatio(4);  
        resultGrid.setCanExpandRecords(true);
        resultGrid.setCanDragSelectText(true);
        setParentGridOption(resultGrid);
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

    public boolean showPagination() {
        return true;
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
        final LoadingWindow loading = new LoadingWindow();
        DBDataSource ds = (DBDataSource) getDataSource();
        String operationName = ds.getActionMode() + ds.getActionName();
        DBDataSource.callOperation(operationName, "find", condition, new DSCallback() {
            @Override
            public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
            	loading.destroy();
                if (searchForm != null) {
                    searchButton.setIcon("[SKIN]/actions/search.png");
                }
                if (dsResponse.getStatus() >= 0) {
                    resultGrid.setData(dsResponse.getData());
                    afterDataReceived(dsResponse.getData());
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
    
    
    public void commonQuery(final Object __o) {
        Map condition = generateCriteria();
        if (condition == null) {
            return;
        }
        if (searchForm != null) {
            searchButton.setIcon("loading38.gif");
        }
        final LoadingWindow loading = new LoadingWindow();
        DBDataSource ds = (DBDataSource) getDataSource();
        String operationName = ds.getActionMode() + ds.getActionName();
        DBDataSource.callOperation(operationName, "find", condition, new DSCallback() {
            @Override
            public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
            	loading.destroy();
                if (searchForm != null) {
                    searchButton.setIcon("[SKIN]/actions/search.png");
                }
                if (dsResponse.getStatus() >= 0) {
                    resultGrid.setData(dsResponse.getData());
                    //执行数据打开处理
                    if(dsResponse.getData().length > 0){
                    	if(null != __o){
	                    	if (__o instanceof Integer) {
	                    		resultGrid.selectRecord((Integer) __o);
								resultGrid.expandRecord(resultGrid.getRecord((Integer) __o));
							}else if (__o instanceof ListGridRecord) {
								resultGrid.selectRecord((ListGridRecord) __o);
								resultGrid.expandRecord((ListGridRecord) __o);
							}else if(__o instanceof ListGridRecord []){
								resultGrid.selectRecords((ListGridRecord []) __o);
								resultGrid.expandRecords((ListGridRecord []) __o);
							}
                    	}
                    }
                    afterDataReceived(dsResponse.getData());
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
	
    public void afterDataReceived(Record[] data) {
    }
	
	public String getSourceGenUrl() {
		return null;
	}
	
    public boolean showControlPanel() {
        return true;
    }
	
    public int getSearchFormHeight() {
        return 0;
    }
    
	@Override
	public String getIntro() {
		return null;
	}

    protected int totalPages = 0;
    protected int currentPage = 1;
    protected final int pageLines = 20;

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
	
	public abstract DataSource getChildDataSource();

	public abstract void initComponents();
	
	/**
	 * 
	 * @Title: getChildGridOptionCanvas 
	 * @Description: 获取子grid操作
	 * @param @return    
	 * @return Map   
	 * @throws
	 */
	public Canvas[] getChildGridOptionCanvas() {
		return null ;
	}
	
	
    /**
     * 
     * @Title: getDoubleClickHandler 
     * @Description: 添加子grid的双击事件
     * @param @return    
     * @return DoubleClickHandler   
     * @throws
     */
    public DoubleClickHandler getChildGridDoubleClickHandler(){
    	return null;
    };
    
    /**
     * 
     * @Title: getChildGridCanEdit 
     * @Description: 子grid能否编辑
     * @param @return    
     * @return boolean   
     * @throws
     */
    public boolean getChildGridCanEdit(){
    	return false;
    }
    
    public ListGrid createChildGrid() {
    	return new ListGrid();
    }
    
    /**
     * 设置父级Grid的属性
     * @param resultGrid
     */
    public void setParentGridOption(ListGrid resultGrid) {
    	
    }
    /**
     * 
     * @Title: setChildGridOption 
     * @Description: 设置子grid操作属性
     * @param @param resultChildGrid   
     * @return void   
     * @throws
     */
    public void setChildGridOption(ListGrid resultChildGrid){
    	
    }
    
    public void customCommonQuery(ListGridRecord record, ListGrid childGrid){
    	
    }
    
    public boolean doCustomCommonQuery(){
    	return false;
    }
    
    protected String getCustomCellCSSText(ListGridRecord record, int rowNum, int colNum) {
    	return null;
    }
    
}
