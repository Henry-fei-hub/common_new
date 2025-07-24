package com.delicacy.client.ui;

import java.util.Map;
import java.util.logging.Logger;

import com.delicacy.client.BaseHelpUtils;
import com.delicacy.client.app.form.NewProcessActivityEdit;
import com.delicacy.client.data.DataEditEvent;
import com.delicacy.client.data.DataEditedHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.i18n.client.LocaleInfo;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.LayoutPolicy;
import com.smartgwt.client.util.Page;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 *
 * @author guangxun
 */
public class StandardEditWindow extends PopupWindow implements HasHandlers {
	private final Logger __logger = Logger.getLogger("");

	public StandardEditWindow() {

		setWidth(Page.getWidth() * 2 / 3);
		setHeight(Page.getHeight() * 2 / 3);
		setCommonProperty();

		VLayout mainPanel = new VLayout(5);
		mainPanel.setWidth100();
		mainPanel.setHeight100();
		addItem(mainPanel);

		customPanel = new VLayout();
		customPanel.setWidth100();
		customPanel.setHeight100();
		customPanel.setPadding(5);

		mainPanel.addMember(customPanel);

		ToolStrip controls = new ToolStrip();
		controls.setVPolicy(LayoutPolicy.FILL);
		controls.setPadding(5);
		controls.setHeight(40);
		controls.setWidth100();
		controls.addFill();

		String localName = LocaleInfo.getCurrentLocale().getLocaleName();

		ToolStripButton okButton = new ToolStripButton(localName.equals("zh_CN") ? "确认" : "OK");
		okButton.setBorder("1px solid gray");
		okButton.setIcon("[SKIN]/actions/approve.png");
		okButton.setWidth(80);
		ToolStripButton cancelButton = new ToolStripButton(localName.equals("zh_CN") ? "取消" : "Cancel");
		cancelButton.setBorder("1px solid gray");
		cancelButton.setIcon("[SKIN]/actions/cancel.png");
		cancelButton.setWidth(80);
		controls.addButton(okButton);
		controls.addSpacer(10);
		controls.addButton(cancelButton);
		controls.addSpacer(10);

		okButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				__OK = true;
				DataEditEvent e = new DataEditEvent();
				Map param = __form.getValues();
				e.setData(new Record(param));
				fireEvent(e);
				destroy();
			}
		});

		cancelButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				__OK = false;
				destroy();
			}
		});

		mainPanel.addMember(controls);

	}

	private final VLayout customPanel;
	private DynamicForm __form;
	private Record __data;
	private boolean __OK = false;

	/**
	 * @return the __form
	 */
	public DynamicForm getForm() {
		return __form;
	}

	/**
	 * @param __form
	 *            the __form to set
	 */
	public void setForm(DynamicForm __form) {
		this.__form = __form;
		customPanel.clear();
		customPanel.addMember(__form);
	}

	/**
	 * @return the __data
	 */
	public Record getData() {
		return __data;
	}

	/**
	 * @param __data
	 *            the __data to set
	 */
	public void setData(Record __data) {
		this.__data = __data;
	}

	public void startEdit() {
		if (__form == null) {
			return;
		}
		if (__data == null) {
			__form.editNewRecord();
		} else {
			__form.editRecord(__data);
		}
	}

	/**
	 * @return the __OK
	 */
	public boolean isOK() {
		return __OK;
	}

	/**
	 * @param __OK
	 *            the __OK to set
	 */
	public void setOK(boolean __OK) {
		this.__OK = __OK;
	}

	protected final HandlerManager handlerManager = new HandlerManager(this);

	@Override
	public void fireEvent(GwtEvent<?> event) {
		handlerManager.fireEvent(event);
	}

	public HandlerRegistration addDataEditedHandler(DataEditedHandler handler) {
		return handlerManager.addHandler(DataEditEvent.TYPE, handler);
	}

}
