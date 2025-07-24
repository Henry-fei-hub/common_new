package com.delicacy.client.ui;

import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.data.DBDataSource;
import com.delicacy.client.data.DataEditEvent;
import com.delicacy.client.data.DataEditedHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.LayoutPolicy;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.form.SearchForm;
import com.smartgwt.client.widgets.form.ValuesManager;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 *
 * @author Cao Guangxun
 */
public abstract class GenericWizadWindow extends Window implements HasHandlers {

    public final static String BORDER_STYLE = "1px solid gray";
    public final static String BACKGROUND_COLOR = "#f2f2f2";
    public final static String BUTTON_BACKGROUND_COLOR = "#f2f2f2";

    private static final Logger __logger = Logger.getLogger("");

    public GenericWizadWindow() {
    }

    public void initComponents() {
        //设置页面属性
        setWidth100();
        setHeight100();
        //setShowEdges(true);

        setShowMinimizeButton(true);
        setShowCloseButton(true);
        setCanDragReposition(true);
        setCanDragResize(true);
        setShowShadow(false);
        setIsModal(true);
        setShowModalMask(true);
        setPadding(20);
        setMembersMargin(10);
        setOverflow(Overflow.HIDDEN);
        centerInPage();

        this.addCloseClickHandler(new CloseClickHandler() {
            @Override
            public void onCloseClick(CloseClickEvent event) {
                destroy();
            }
        });

        if (getPageCount() > 1) {
            __originalName = getTitle() + "向导";
            setTitle(__originalName);
        } else {
            __originalName = getTitle();
        }
        __pages = getPages();
        for (AbstractWizadPage __page : __pages) {
            if (__page.getPageMode() != AbstractWizadPage.PAGE_MODE_ADD) {
                setPageMode(AbstractWizadPage.PAGE_MODE_UPDATE);
            }
            __page.setBorder(BORDER_STYLE);
            __page.setPadding(5);
            __page.setBackgroundColor(BACKGROUND_COLOR);
        }
        VLayout mainLayout = new VLayout();
        mainLayout.setOverflow(Overflow.HIDDEN);
        customLayout = new HLayout();
        customLayout.setWidth100();
        customLayout.setHeight100();
        customLayout.setPadding(5);
        customLayout.addMember(__pages.get(getCurrentPage()));
        mainLayout.addMember(customLayout);
        navLayout = new ToolStrip();
        navLayout.setVPolicy(LayoutPolicy.FILL);
        navLayout.setPadding(5);
        navLayout.setHeight(40);
        navLayout.setWidth100();
        mainLayout.addMember(navLayout);
        addItem(mainLayout);
        navLayout.addFill();
        previousButton = createButton("上一步");
        previousButton.setIcon("/pom/sc/skins/Enterprise/images/actions/back.png");
        navLayout.addButton(previousButton);
        navLayout.addSpacer(10);
        previousButton.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                if (getCurrentPage() > 0) {
                    customLayout.removeChild(__pages.get(getCurrentPage()));
                    setCurrentPage(getCurrentPage() - 1);
                    customLayout.addMember(__pages.get(getCurrentPage()));
                    buttonProcess();
                }
            }
        });
        nextButton = createButton("下一步");
        nextButton.setIcon("/pom/sc/skins/Enterprise/images/actions/forward.png");
        navLayout.addButton(nextButton);
        navLayout.addSpacer(10);
        nextButton.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                if (!__pages.get(currentPage).checkData()) {
                    return;
                }
                if (getCurrentPage() < getPageCount() - 1) {
                    customLayout.removeChild(__pages.get(getCurrentPage()));
                    setCurrentPage(getCurrentPage() + 1);
                    customLayout.addMember(__pages.get(getCurrentPage()));
                    buttonProcess();
                }
            }
        });
        saveButton = createButton("保存");
        saveButton.setIcon("[SKIN]/actions/save.png");
        navLayout.addButton(saveButton);
        navLayout.addSpacer(10);

        saveButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
            	saveButton.disable();
                Map values = getValues();
                if (!checkData(values)) {
                    saveButton.enable();
                	return;
                }
                final LoadingWindow lodding = new LoadingWindow();
                DBDataSource.callOperation(getActionName(), getSubaction(), values, new DSCallback() {
                    @Override
                    public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
                        lodding.destroy();
                        if (callback != null) {
                            callback.execute(dsResponse, data, dsRequest);
                        }
                        if (dsResponse.getStatus() >= 0) {
                            final DataEditEvent evt = new DataEditEvent();
                            if (dsResponse.getData() != null && dsResponse.getData().length > 0) {
                                evt.setData(dsResponse.getData()[0]);
                            } else {
                                evt.setData(new Record(valueManager.getValues()));
                            }
                            fireEvent(evt);
                            destroy();
                            if (getPageMode() == AbstractWizadPage.PAGE_MODE_ADD && isMultiCreate()) {
                                StringBuilder sb = new StringBuilder();
                                if (getContinueMessage() == null) {
                                    sb.append(__originalName);
                                    sb.append("成功，是否继续？");
                                } else {
                                    sb.append(getContinueMessage());
                                }
                                SC.ask(sb.toString(), new BooleanCallback() {
                                    @Override
                                    public void execute(Boolean value) {
                                        if (value) {
                                            for (AbstractWizadPage __page : __pages) {
                                                __page.setRecord(evt.getData());
                                                __page.startEdit();
                                            }
                                            if (getPageCount() > 1 && getCurrentPage() != 0) {
                                                customLayout.removeChild(__pages.get(getCurrentPage()));
                                                setCurrentPage(0);
                                                customLayout.addMember(__pages.get(getCurrentPage()));
                                            }
                                            buttonProcess();
                                            for (AbstractWizadPage __page : __pages) {
                                                __page.reloadSourceData();
                                            }
                                        } else {
                                            destroy();
                                        }
                                    }
                                });
                            } else {
//								SC.say(__originalName+"成功");
                                destroy();
                            }
                        } else {
                            SC.say(dsResponse.getErrors().get("errorMsg").toString());
                        }
                        saveButton.enable();
                    }
                });
            }
        });
        cancelButton = createButton("取消");
        cancelButton.setIcon("[SKIN]/actions/close.png");
        navLayout.addButton(cancelButton);
        navLayout.addSpacer(10);
        cancelButton.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
            	commonCancel();
            }

        });
        buttonProcess();
        for (AbstractWizadPage p : __pages) {
            p.setSearchForm(searchForm);
            p.setRecord(record);
            p.setValueManage(valueManager);
            getFormItems().putAll(p.getItems());
            p.setParentWindow(this);
        }
        for (AbstractWizadPage p : __pages) {
            p.initEventHandlers();
        }
        for (AbstractWizadPage p : __pages) {
            p.reloadSourceData();
        }
    }
    
    public void commonCancel(){
    	destroy();
    }

    public Map getValues() {
        Map values = new HashMap();
        Map notused = new HashMap();
        for (AbstractWizadPage p : __pages) {
            notused.putAll(ClientUtil.putMapAll(values, p.getValuesAsMap(), p.getItemNames()));
        }
        if (!notused.isEmpty()) {
            ClientUtil.putMapAll(values, notused, null);
        }
        ClientUtil.putMapAll(values, valueManager.getValues(), null);
        return values;
    }

    public ToolStripButton createButton(String name) {
        ToolStripButton b = new ToolStripButton(name);
        b.setWidth(80);
        b.setBorder(BORDER_STYLE);
        return b;
    }

    protected void buttonProcess() {
        if (getPageCount() == 1) {
            previousButton.setVisible(false);
            nextButton.setVisible(false);
        }
        if (getCurrentPage() == 0) {
            previousButton.setDisabled(true);
        } else {
            previousButton.setDisabled(false);
        }
        if (getCurrentPage() == getPageCount() - 1) {
            nextButton.setDisabled(true);
        } else {
            nextButton.setDisabled(false);
        }
        if (getCurrentPage() == getPageCount() - 1) {
            saveButton.setDisabled(false);
        } else {
            saveButton.setDisabled(false);
        }
        if (getPageCount() > 0) {
            setTitle(__originalName + " - " + __pages.get(getCurrentPage()).getName());
        }
    }

    public void startEdit() {
        for (AbstractWizadPage __page : __pages) {
            __page.startEdit();
        }
    }

    public abstract int getPageCount();

    public abstract String getActionName();

    public abstract List<AbstractWizadPage> getPages();

    public abstract boolean checkData(Map data);

    private String __subaction = "save";
    private int currentPage = 0;
    protected ValuesManager valueManager = new ValuesManager();
    protected List<AbstractWizadPage> __pages;
    protected DSCallback callback;
    protected Map<String, FormItem> formItems = new HashMap<>();
    protected HLayout customLayout = null;
    protected ToolStripButton previousButton;
    protected ToolStripButton nextButton;
    protected ToolStripButton saveButton;
    protected ToolStripButton cancelButton;
    protected Record record = null;
    protected SearchForm searchForm;
    protected String __originalName;
    protected ToolStrip navLayout;
    private int pageMode = AbstractWizadPage.PAGE_MODE_ADD;
    private boolean __multiCreate = false;
    private String __continueMessage;

    protected final HandlerManager handlerManager = new HandlerManager(this);

    @Override
    public void fireEvent(GwtEvent<?> event) {
        handlerManager.fireEvent(event);
    }

    public HandlerRegistration addDataEditedHandler(DataEditedHandler handler) {
        return handlerManager.addHandler(DataEditEvent.TYPE, handler);
    }

    /**
     * @return the callback
     */
    public DSCallback getCallback() {
        return callback;
    }

    /**
     * @param callback the callback to set
     */
    public void setCallback(DSCallback callback) {
        this.callback = callback;
    }

    /**
     * @return the __subaction
     */
    public String getSubaction() {
        return __subaction;
    }

    /**
     * @param __subaction the __subaction to set
     */
    public void setSubaction(String __subaction) {
        this.__subaction = __subaction;
    }

    /**
     * @return the formItems
     */
    public Map<String, FormItem> getFormItems() {
        return formItems;
    }

    /**
     * @param formItems the formItems to set
     */
    public void setFormItems(Map<String, FormItem> formItems) {
        this.formItems = formItems;
    }

    /**
     * @return the record
     */
    public Record getRecord() {
        return record;
    }

    /**
     * @param record the record to set
     */
    public void setRecord(Record record) {
        this.record = record;
    }

    /**
     * @return the valueManager
     */
    public ValuesManager getValueManager() {
        return valueManager;
    }

    /**
     * @param valueManager the valueManager to set
     */
    public void setValueManager(ValuesManager valueManager) {
        this.valueManager = valueManager;
    }

    /**
     * @return the searchForm
     */
    public SearchForm getSearchForm() {
        return searchForm;
    }

    /**
     * @param searchForm the searchForm to set
     */
    public void setSearchForm(SearchForm searchForm) {
        this.searchForm = searchForm;
    }

    /**
     * @return the pageMode
     */
    public int getPageMode() {
        return pageMode;
    }

    /**
     * @param pageMode the pageMode to set
     */
    public void setPageMode(int pageMode) {
        this.pageMode = pageMode;
    }

    /**
     * @return the currentPage
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * @param currentPage the currentPage to set
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * @return the navLayout
     */
    public ToolStrip getNavLayout() {
        return navLayout;
    }

    /**
     * @param navLayout the navLayout to set
     */
    public void setNavLayout(ToolStrip navLayout) {
        this.navLayout = navLayout;
    }

    /**
     * @return the __multiCreate
     */
    public boolean isMultiCreate() {
        return __multiCreate;
    }

    /**
     * @param __multiCreate the __multiCreate to set
     */
    public void setMultiCreate(boolean __multiCreate) {
        this.__multiCreate = __multiCreate;
    }

    /**
     * @return the __continueMessage
     */
    public String getContinueMessage() {
        return __continueMessage;
    }

    /**
     * @param __continueMessage the __continueMessage to set
     */
    public void setContinueMessage(String __continueMessage) {
        this.__continueMessage = __continueMessage;
    }

}
