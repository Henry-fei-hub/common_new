package com.delicacy.client.app.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.delicacy.client.data.DBDataSource;
import com.delicacy.client.data.DataEditEvent;
import com.delicacy.client.ui.AbstractWizadPage;
import com.delicacy.client.ui.GenericWizadWindow;
import com.delicacy.client.ui.LoadingWindow;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class NewFunctionWithDepartmentForm extends GenericWizadWindow {

    @Override
    public String getActionName() {
        // 需要根据实际业务修改
    	return	"EP_OnProjectWithStageCommon";
    }

    @Override
    public boolean checkData(Map data) {
    	boolean status = true;
		for(AbstractWizadPage wp : __pages){
			if(!wp.checkData()){
				status = false;
			}
		}
		return status;
    }

    @Override
    public int getPageCount() {
        return 1;
    }

    @Override
    public List<AbstractWizadPage> getPages() {
        setCallback(new DSCallback() {
            @Override
            public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
                // 请在此编写成功保存后要做的工作
            	SC.say("保存成功");
            }
        });
        List<AbstractWizadPage> res = new ArrayList<>();
        BindDepartmentFunctionToEmployeeForm bindFunctionToEmployeeForm = new BindDepartmentFunctionToEmployeeForm();
	    res.add(bindFunctionToEmployeeForm);
        return res;
    }

    public void ss(){
        ToolStripButton save1Button1 = createButton("保存");
        save1Button1.setIcon("[SKIN]/actions/save.png");
        navLayout.removeChild(saveButton);
        navLayout.removeChild(cancelButton);
        navLayout.addButton(save1Button1);
        navLayout.addSpacer(10);
        navLayout.addButton(cancelButton);
        navLayout.addSpacer(10);
        save1Button1.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                Map values = getValues();
                if (!checkData(values)) {
                    return;
                }
                SC.debugger();
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
//                            destroy();
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
//                                            destroy();
                                        }
                                    }
                                });
                            } else {
//								SC.say(__originalName+"成功");
//                                destroy();
                            }
                        } else {
                            SC.say(dsResponse.getErrors().get("errorMsg").toString());
                        }
                    }
                });
            }
        });

    }

}
