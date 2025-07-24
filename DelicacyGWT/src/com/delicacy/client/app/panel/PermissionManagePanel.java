package com.delicacy.client.app.panel;

import java.util.logging.Logger;

import com.delicacy.client.DelicacyPanel;
import com.delicacy.client.PanelFactory;
import com.smartgwt.client.widgets.Canvas;

public class PermissionManagePanel extends DelicacyPanel{
	private static Logger __logger = Logger.getLogger("");
	protected PermissionManageDesignPanel detailForm;
	@Override
	public Canvas getViewPanel() {
		detailForm = new PermissionManageDesignPanel();
		detailForm.setKeepInParentRect(true);
		return detailForm;
	}
	public static class Factory implements PanelFactory{

		private String id;

		@Override
		public Canvas create() {
			PermissionManagePanel cm = new PermissionManagePanel();
			id = cm.getID();
			return cm;
		}

		@Override
		public String getID() {
			return id;
		}

		@Override
		public String getDescription() {
			return "PermissionManagePanel";
		}

	}

}
