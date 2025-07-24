package com.delicacy.client.ui;

import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 *
 * @author Cao Guangxun
 */
public class GenericViewWindow extends Window {

    public GenericViewWindow() {
        //设置页面属性
        this.setTitle(id);
        setWidth100();
        setHeight100();
        //setShowEdges(true);

        setShowMinimizeButton(true);
        setShowMaximizeButton(true);
        setShowCloseButton(true);
        setCanDragReposition(true);
        setCanDragResize(true);
        setShowShadow(false);
        setIsModal(true);
        setShowModalMask(true);
        setPadding(20);
        setMembersMargin(10);
        centerInPage();

        this.addCloseClickHandler(new CloseClickHandler() {
            @Override
            public void onCloseClick(CloseClickEvent event) {
                destroy();
            }
        });

        VLayout mainLayout = new VLayout();
        customLayout = new HLayout();
        customLayout.setWidth100();
        customLayout.setHeight100();
        mainLayout.addMember(customLayout);
        ToolStrip navLayout = new ToolStrip();
        navLayout.setPadding(1);
        navLayout.setHeight(40);
        navLayout.setWidth100();
        mainLayout.addMember(navLayout);
        addItem(mainLayout);
        navLayout.addFill();

        ToolStripButton cancelButton = new ToolStripButton("关闭");
        cancelButton.setTop(1);
        cancelButton.setHeight(32);
        cancelButton.setIcon("[SKIN]/actions/close.png");
        cancelButton.setBorder(GenericWizadWindow.BORDER_STYLE);
        cancelButton.setWidth100();
        navLayout.addButton(cancelButton);
        cancelButton.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                destroy();
            }

        });
    }

    public void setContent(Canvas p) {
        customLayout.addMember(p);
        customLayout.redraw();
    }

    HLayout customLayout = null;

}
