package com.delicacy.client;

import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.layout.VLayout;

public abstract class DelicacyPanel extends VLayout {

    public DelicacyPanel() {
        setWidth100();
        setHeight100();
        Canvas viewPanel = getViewPanel();
        addMember(viewPanel);
    }

    protected boolean isTopIntro() {
        return false;
    }

    public String getHtmlUrl() {
        return null;
    }

    public String getXmlDataUrl() {
        return null;
    }

    public String getJsonDataUrl() {
        return null;
    }

    public String getCssUrl() {
        return null;
    }

    public String getIntro() {
        return null;
    }

    public abstract Canvas getViewPanel();

}
