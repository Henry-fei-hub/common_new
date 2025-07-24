package com.delicacy.client.app.form;

import com.delicacy.client.BasicPermissionStatic;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.data.DBDataSource;
import com.delicacy.client.data.DataEditEvent;
import com.delicacy.client.ui.AbstractWizadPage;
import com.delicacy.client.ui.GenericWizadWindow;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.LayoutPolicy;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LookSsystemprocessporForm  extends GenericWizadWindow {

    private int currentPage = 0;

    @Override
    public String getActionName() {
        // 需要根据实际业务修改
        return "EP_ModifyProcessorDefinition";
    }

    @Override
    public boolean checkData(Map data) {
        return true;
    }

    @Override
    public int getPageCount() {
        return 2;
    }

    @Override
    public List<AbstractWizadPage> getPages() {
        setCallback(new DSCallback() {
            @Override
            public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
                // 请在此编写成功保存后要做的工作
            }
        });
        setSubaction("update");
        List<AbstractWizadPage> res = new ArrayList<AbstractWizadPage>();
        res.add(new SsystemprocessporUpdateForm());
        SsystemprocessporDetailSystemProcessActivity systemprocessactivity = new SsystemprocessporDetailSystemProcessActivity();
        systemprocessactivity.setPageMode(AbstractWizadPage.PAGE_MODE_UPDATE);
        res.add(systemprocessactivity);
        return res;
    }


    @Override
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
//        navLayout.addButton(saveButton);
        navLayout.addSpacer(10);
        saveButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                Map values = getValues();
                if (!checkData(values)) {
                    return;
                }
                DBDataSource.callOperation(getActionName(), getSubaction(), values, new DSCallback() {
                    @Override
                    public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
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
}
