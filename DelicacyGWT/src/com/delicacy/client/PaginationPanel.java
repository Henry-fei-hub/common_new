package com.delicacy.client;

import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.types.BackgroundRepeat;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;
import java.util.HashMap;
import java.util.Map;

public class PaginationPanel extends ToolStrip {

	private ToolStripButton firstButton = null;
	private ToolStripButton previousButton = null;
	private ToolStripButton nextButton = null;
	private ToolStripButton lastButton = null;
	private ToolStripButton gotoButton = null;
	private ToolStripButton refreshButton = null;
	private Label currentPage = null;
	private Label totalPages = null;
	private CheckboxItem isPage = null;
	private TextItem pageLines = null;
	private TextItem gotoPage = null;

	public PaginationPanel() {
		super();
		this.setLayoutAlign(VerticalAlignment.CENTER);
		this.setWidth100();
		this.setHeight(26);
		this.setBackgroundColor("#e2e2e2");
		isPage = new CheckboxItem("paged", "分页");
		isPage.setWidth(50);
		pageLines = new TextItem("pageLines", "每页");
		pageLines.setWidth(50);
		pageLines.setWrapTitle(false);
		pageLines.setDefaultValue(20);
		gotoPage = new TextItem("gotoPage", "第");
		gotoPage.setWidth(50);
		currentPage = new Label();
		currentPage.setWidth(50);
		totalPages = new Label();
		totalPages.setWidth(50);

		HLayout buttonLayout = new HLayout(10);
		buttonLayout.setHeight100();
		buttonLayout.setWidth("260px");
		buttonLayout.setPadding(3);

		isPage.setValue(true);
		this.addFill();
		refreshButton = new ToolStripButton();
		refreshButton.setIcon("[SKIN]/actions/refresh.png");
		this.addButton(refreshButton);
		this.addSpacer(10);
		this.addFormItem(isPage);
		this.addSpacer(10);
		this.addFormItem(pageLines);
		this.addSpacer(10);
		this.addFormItem(gotoPage);
		this.addSpacer(10);
		gotoButton = new ToolStripButton("GO");
		buttonLayout.addMember(gotoButton);
		// this.addSpacer(10);
		firstButton = new ToolStripButton();
		firstButton.setIcon("[SKIN]/actions/first.png");
		buttonLayout.addMember(firstButton);
		// this.addSpacer(10);
		previousButton = new ToolStripButton();
		previousButton.setIcon("[SKIN]/actions/prev.png");
		buttonLayout.addMember(previousButton);
		// this.addSpacer(10);
		buttonLayout.addMember(currentPage);
		// this.addSpacer(10);
		nextButton = new ToolStripButton();
		nextButton.setIcon("[SKIN]/actions/next.png");
		buttonLayout.addMember(nextButton);

		// this.addSpacer(10);
		lastButton = new ToolStripButton();
		lastButton.setIcon("[SKIN]/actions/last.png");
		buttonLayout.addMember(lastButton);
		// this.addSpacer(10);
		buttonLayout.addMember(totalPages);
		this.addMember(buttonLayout);
		this.addSpacer(30);

	}

	public Criteria getCriteria(int currentPage, int pageLine) {
		Criteria result = new Criteria();
		if (isPage.getValueAsBoolean()) {
			String strPageLines = pageLines.getValueAsString();
			try {
				pageLine = Integer.parseInt(strPageLines);
			} catch (NumberFormatException ex) {
				pageLine = 20;
			}
			result.setAttribute("currentPage", currentPage);
			result.setAttribute("pageLines", pageLine);
		}
		return result;
	}

	public Map getPagenationCondition(int currentPage, int pageLine) {
		Map result = new HashMap();
		if (isPage.getValueAsBoolean()) {
			String strPageLines = pageLines.getValueAsString();
			try {
				pageLine = Integer.parseInt(strPageLines);
			} catch (Exception ex) {
			}
			result.put("currentPage", currentPage == 0 ? 1 : currentPage);
			result.put("pageLines", pageLine);
		} else {
			result.put("currentPage", 0);
			result.put("pageLines", 0);
		}
		return result;
	}

	public Label getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Label currentPage) {
		this.currentPage = currentPage;
	}

	public ToolStripButton getFirstButton() {
		return firstButton;
	}

	public void setFirstButton(ToolStripButton firstButton) {
		this.firstButton = firstButton;
	}

	public ToolStripButton getPreviousButton() {
		return previousButton;
	}

	public void setPreviousButton(ToolStripButton previousButton) {
		this.previousButton = previousButton;
	}

	public ToolStripButton getNextButton() {
		return nextButton;
	}

	public void setNextButton(ToolStripButton nextButton) {
		this.nextButton = nextButton;
	}

	public ToolStripButton getLastButton() {
		return lastButton;
	}

	public void setLastButton(ToolStripButton lastButton) {
		this.lastButton = lastButton;
	}

	public ToolStripButton getGotoButton() {
		return gotoButton;
	}

	public void setGotoButton(ToolStripButton gotoButton) {
		this.gotoButton = gotoButton;
	}

	public Label getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Label totalPages) {
		this.totalPages = totalPages;
	}

	public boolean getIsPage() {
		return isPage.getValueAsBoolean();
	}

	public void setIsPage(boolean isPage) {
		this.isPage.setValue(isPage);
	}

	public TextItem getGotoPage() {
		return gotoPage;
	}

	public void setGotoPage(TextItem gotoPage) {
		this.gotoPage = gotoPage;
	}

	// 获取需要直接跳转到得页数
	public int getGotoPages() {
		try {
			return Integer.parseInt(this.gotoPage.getValueAsString());
		} catch (Exception e) {
			SC.say("请输入一个大于0的正整数");
			return 0;
		}
	}

	/**
	 * @return the refreshButton
	 */
	public ToolStripButton getRefreshButton() {
		return refreshButton;
	}

	/**
	 * @param refreshButton
	 *            the refreshButton to set
	 */
	public void setRefreshButton(ToolStripButton refreshButton) {
		this.refreshButton = refreshButton;
	}

}
