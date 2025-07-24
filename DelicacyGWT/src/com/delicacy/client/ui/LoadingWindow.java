
package com.delicacy.client.ui;

import com.google.gwt.user.client.ui.Image;
import com.smartgwt.client.widgets.Window;

/**
 *
 * @author lxf
 */
public class LoadingWindow extends Window {
	PopupWindow popupWindow;
	@Override
	public void destroy() {
		popupWindow.destroy();
		super.destroy();
	}
	
	public LoadingWindow() {
		popupWindow = new PopupWindow("正在加载...");
		popupWindow.setWidth("8%");
		popupWindow.setHeight("16%");
		popupWindow.addStyleName("loadingStyle");
		popupWindow.setShowCloseButton(false);
		popupWindow.setShowMaximizeButton(false);
		popupWindow.setShowMinimizeButton(false);
		popupWindow.centerInPage();
		Image account = new Image("images/loading38.gif");
		account.setSize("100%", "100%");
		popupWindow.addItem(account);
		popupWindow.centerInPage();
		popupWindow.show();
	}

}
