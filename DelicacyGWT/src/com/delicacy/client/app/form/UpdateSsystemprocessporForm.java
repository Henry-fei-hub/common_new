package com.delicacy.client.app.form;

import com.delicacy.client.BasicPermissionStatic;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.data.DBDataSource;
import com.delicacy.client.data.DataEditEvent;
import com.delicacy.client.ui.AbstractWizadPage;
import com.delicacy.client.ui.GenericWizadWindow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

public class UpdateSsystemprocessporForm extends GenericWizadWindow {

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
}

