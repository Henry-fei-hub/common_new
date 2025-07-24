package com.delicacy.client.ui;

import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;

public class PopupWindow extends Window {

    public PopupWindow() {
        setWidth("90%");
        setHeight("90%");
        setCommonProperty();
    }

    public PopupWindow(String title) {
        setWidth("90%");
        setHeight("90%");
        setTitle(title);
        setCommonProperty();
    }

    public PopupWindow(int width, int height) {
        setWidth(width);
        setHeight(height);
        setCommonProperty();
    }

    public PopupWindow(String title, int width, int height) {
        setWidth(width);
        setHeight(height);
        setTitle(title);
        setCommonProperty();
    }

    protected void setCommonProperty() {
        setHiliteBodyColor("#C3D9FF");
        setShowMinimizeButton(true);
        setShowMaximizeButton(true);
        setShowCloseButton(true);
        setCanDragReposition(true);
        setCanDragResize(true);
        setShowShadow(true);
        setIsModal(true);
        setShowModalMask(true);
        setPadding(10);
        setMembersMargin(10);
        centerInPage();

        this.addCloseClickHandler(new CloseClickHandler() {
            @Override
            public void onCloseClick(CloseClickEvent event) {
                destroy();
            }
        });
    }
}
