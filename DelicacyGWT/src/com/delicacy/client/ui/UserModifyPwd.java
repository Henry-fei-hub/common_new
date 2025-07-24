package com.delicacy.client.ui;

import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.data.DBDataSource;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.Cookies;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import java.util.LinkedHashMap;
import java.util.logging.Logger;

/**
 *
 * @author Guangxun
 */
public class UserModifyPwd extends HLayout implements HasHandlers {

    private final HandlerManager handlerManager;
    private Window parentWindow;
    private static Logger __logger = Logger.getLogger("");

    public UserModifyPwd() {
        this.handlerManager = new HandlerManager(this);
    }

    public void initComponents() {
        setWidth100();
        setHeight100();
        setPadding(10);
        setMembersMargin(5);

        VLayout formLayout = new VLayout();
        formLayout.setHeight100();
        formLayout.setMembersMargin(5);
        formLayout.setWidth("50%");
        formLayout.setAlign(Alignment.CENTER);
        addMember(formLayout);

        HLayout messageLayout = new HLayout();
        messageLayout.setWidth100();
        messageLayout.setHeight100();
        messageLayout.setAlign(Alignment.CENTER);

        final DynamicForm form = new DynamicForm();
        form.setNumCols(2);
        form.setAlign(Alignment.CENTER);
        form.setWidth100();
        form.setHeight100();
        final PasswordItem oldPwd = new PasswordItem("oldPwd", "Original password");
        oldPwd.setWidth("*");
        oldPwd.setRequired(true);

        final PasswordItem newPwd = new PasswordItem("pwd", "New password");
        newPwd.setWidth("*");
        newPwd.setRequired(true);

        final PasswordItem ConfirmPwd = new PasswordItem("ConfirmPwd", "New password");
        ConfirmPwd.setWidth("*");
        ConfirmPwd.setRequired(true);

        form.setItems(oldPwd, newPwd, ConfirmPwd);
        messageLayout.addMember(form);
        formLayout.addMember(messageLayout);
        ClientUtil.DynamicFormProcessAccordingToDevice(form);
        HLayout btnLayout = new HLayout();
        btnLayout.setWidth100();
        btnLayout.setHeight(70);
        btnLayout.setMembersMargin(10);
        btnLayout.setAlign(Alignment.CENTER);

        IButton okBtn = new IButton("Change");
        okBtn.setIcon("[SKIN]/actions/save.png");
        okBtn.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                if (!form.validate()) {
                    return;
                }
                String newpwd = newPwd.getValue().toString();
                String confirmpwd = ConfirmPwd.getValue().toString();
                if (!newpwd.endsWith(confirmpwd)) {
                    SC.say("The old and new password is not same,please re-enter.");
                    return;
                }
//                __logger.info(oldPwd.getValue().toString());
                LinkedHashMap map = new LinkedHashMap();
                map.put("email", oldPwd.getValue());
                map.put("employeePassword", newpwd);
                String employeeNo = Cookies.getCookie(ClientUtil.OPRERATORNO_COOKIE);
                map.put("employeeNo", employeeNo);
                DBDataSource.callOperation("EP_ModifyUserPwdBiz", map, new DSCallback() {
                    @Override
                    public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
                        if (dsResponse.getStatus() >= 0) {
                            SC.say("Success");
                            if (getParentWindow() == null) {
                                return;
                            }
                            getParentWindow().destroy();
                        } else {
                            SC.say("Failure." + dsResponse.getErrors().get("errorMsg"));
                        }
                    }
                });
            }
        });
        IButton cleanBtn = new IButton("Close");
        cleanBtn.setIcon("[SKIN]/actions/close.png");
        cleanBtn.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                if (getParentWindow() == null) {
                    return;
                }
                getParentWindow().destroy();
            }
        });
        btnLayout.addMember(okBtn);
        btnLayout.addMember(cleanBtn);
        formLayout.addMember(btnLayout);

    }

    public Window getParentWindow() {
        return parentWindow;
    }

    public void setParentWindow(Window parentWindow) {
        this.parentWindow = parentWindow;
    }

}
