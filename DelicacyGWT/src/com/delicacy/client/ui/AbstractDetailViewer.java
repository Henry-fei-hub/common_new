package com.delicacy.client.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.delicacy.client.BaseHelpUtils;
import com.delicacy.client.MapUtils;
import com.delicacy.client.data.DBDataSource;
import com.delicacy.client.data.GroupColumn;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.SplitPane;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.viewer.DetailViewer;
import com.smartgwt.client.widgets.viewer.DetailViewerField;

/**
 *
 * @author Guangxun Cao 2015
 */
public abstract class AbstractDetailViewer extends HLayout {

	public final static int COMPONENT_LAYOUT_NORMAL = 0;
	public final static int COMPONENT_LAYOUT_TAB = 1;
	public final static int COMPONENT_LAYOUT_TOPBOTTOM = 2;
	protected boolean needApprovalComment = false;
	public static SectionStack detailStack = null;

	public Record __instanceData;
	
	public boolean isNeedApprovalComment() {
		return needApprovalComment;
	}

	public void setNeedApprovalComment(boolean needApprovalComment) {
		this.needApprovalComment = needApprovalComment;
	}

	public AbstractDetailViewer() {
	}

	public void initComponents() {
		setWidth100();
		setHeight100();
		setLayoutMargin(5);
		setMembersMargin(5);
		setBackgroundColor("azure");
		setBorder("1px solid gray");
		setCanSelectText(true);
		int horizontalPercent = getHorizontalPercent();
		// 如果没有定义字段分组，或者就一个组
		List<ListGrid> grids = getDetailListGrids();
		List<String> names = getDetailNames();
		List<GroupColumn> gcs = getGroupColumnNames();
		if (getGroupCount() <= 1) {
			// 那么就是用一个DetailViewer就够了
			DetailViewer mainView = new DetailViewer();
			mainView.setCanSelectText(true);
			mainView.setHeight100();
			mainView.setWidth100();
			mainView.setWrapValues(true);

			mainView.setKeepInParentRect(true);
			// mainView.setWrapLabel(true);
			// 如果有一个分组，就需要给这个DetailViewer增加显示的字段
			if (getGroupCount() == 1) {
				GroupColumn gc = gcs.get(0);
				DetailViewerField[] dvfs = new DetailViewerField[gc.getColumnNames().size()];
				for (int i = 0; i < gc.getColumnNames().size(); i++) {
					dvfs[i] = new DetailViewerField(gc.getColumnNames().get(i));

				}
				mainView.setFields(dvfs);
			}
			mainView.setDataSource(getMainDataSource());
			detailViewers.add(mainView);
		} else {
			for (int i = 0; i < getGroupCount(); i++) {
				DetailViewer viewers = new DetailViewer();
				viewers.setCanSelectText(true);
				// viewers.setWrapLabel(true);
				viewers.setWrapValues(true);
				GroupColumn gc = gcs.get(i);
				DetailViewerField[] dvfs = new DetailViewerField[gc.getColumnNames().size()];
				for (int k = 0; k < gc.getColumnNames().size(); k++) {
					dvfs[k] = new DetailViewerField(gc.getColumnNames().get(k));
				}
				viewers.setDataSource(getMainDataSource());
				viewers.setFields(dvfs);
				detailViewers.add(viewers);
			}
		}
		switch (getLayoutMode()) {
		case COMPONENT_LAYOUT_NORMAL:
			SplitPane mainPane = new SplitPane();
			mainPane.setCanSelectText(true);
			mainPane.setShowListToolStrip(false);
			VLayout left = new VLayout();
			left.setCanSelectText(true);
			left.setHeight100();
			left.setPadding(5);
			left.setMembersMargin(10);
			int ii = 0;
			for (DetailViewer detailViewer : detailViewers) {
				if (getGroupCount() > 0 && getIsGroup()) {
					detailViewer.setIsGroup(true);
					detailViewer.setCanSelectText(true);
					detailViewer.setGroupTitle(gcs.get(ii++).getGroupCaption());
				}
				left.addMember(detailViewer);
			}
			if (getDetailCount() > 0) {
				// left.setShowResizeBar(true);
				if (horizontalPercent == 0) {
					left.setWidth("50%");
				} else {
					left.setWidth(String.valueOf((horizontalPercent / 10) * 10) + "%");
				}
			} else {
				// left.setShowResizeBar(false);
				left.setWidth100();
			}
			left.setOverflow(Overflow.AUTO);
			mainPane.setNavigationPane(left);

			if (getDetailCount() > 0) {
				if (getDetailCount() == 1) {
					ListGrid grid = grids.get(0);
					grid.setCanDragSelectText(true);
					grid.setHeight100();
					if (horizontalPercent == 0) {
						grid.setWidth("50%");
					} else {
						grid.setWidth(String.valueOf(((100 - horizontalPercent) / 10) * 10) + "%");
					}
					mainPane.setListPane(grid);
				} else {
					detailStack = new SectionStack();
					detailStack.setCanSelectText(true);
					detailStack.setHeight100();
					if (horizontalPercent == 0) {
						detailStack.setWidth("50%");
					} else {
						detailStack.setWidth(String.valueOf(((100 - horizontalPercent) / 10) * 10) + "%");
					}
					detailStack.setVisibilityMode(VisibilityMode.MULTIPLE);
					for (int i = 0; i < getDetailCount(); i++) {

						SectionStackSection detailSection = new SectionStackSection(names.get(i));
						detailSection.addItem(grids.get(i));
						detailSection.setExpanded(true);
						detailStack.addSection(detailSection);
					}
					mainPane.setListPane(detailStack);
				}
			}
			if (getDetailCount() > 0)
				addMember(mainPane);
			else
				addMember(left);
			break;
		case COMPONENT_LAYOUT_TOPBOTTOM:
			VLayout global = new VLayout();
			global.setCanSelectText(true);
			VLayout left1 = new VLayout();
			left1.setCanSelectText(true);
			left1.setHeight100();
			left1.setPadding(5);
			left1.setMembersMargin(10);
			int ii1 = 0;
			for (DetailViewer detailViewer : detailViewers) {
				if (getGroupCount() > 0 && getIsGroup()) {
					detailViewer.setCanSelectText(true);
					detailViewer.setIsGroup(true);
					detailViewer.setGroupTitle(gcs.get(ii1++).getGroupCaption());
				}
				left1.addMember(detailViewer);
			}
			if (getDetailCount() > 0) {
				left1.setShowResizeBar(true);
				if (horizontalPercent == 0) {
					left1.setWidth("50%");
				} else {
					left1.setWidth(String.valueOf((horizontalPercent / 10) * 10) + "%");
				}
			} else {
				left1.setShowResizeBar(false);
				left1.setWidth100();
			}
			global.addMember(left1);
			if (getDetailCount() > 0) {
				if (getDetailCount() == 1) {
					ListGrid grid = grids.get(0);
					grid.setHeight100();
					grid.setCanSelectText(true);
					if (horizontalPercent == 0) {
						grid.setWidth("50%");
					} else {
						grid.setWidth(String.valueOf(((100 - horizontalPercent) / 10) * 10) + "%");
					}
					global.addMember(grid);
				} else {
					SectionStack detailStack = new SectionStack();
					detailStack.setCanSelectText(true);
					detailStack.setHeight100();
					if (horizontalPercent == 0) {
						detailStack.setWidth("50%");
					} else {
						detailStack.setWidth(String.valueOf(((100 - horizontalPercent) / 10) * 10) + "%");
					}
					detailStack.setVisibilityMode(VisibilityMode.MULTIPLE);
					for (int i = 0; i < getDetailCount(); i++) {

						SectionStackSection detailSection = new SectionStackSection(names.get(i));
						detailStack.addSection(detailSection);
						detailSection.addItem(grids.get(i));
						detailSection.setExpanded(true);
					}
					global.addMember(detailStack);
				}
			}
			addMember(global);
			break;
		case COMPONENT_LAYOUT_TAB:
			TabSet mainTab = new TabSet();
			Tab tab;
			if (getGroupCount() == 0) {
				tab = new Tab(getName());
				tab.setPane(detailViewers.get(0));
				mainTab.addTab(tab);
			} else {
				for (int i = 0; i < getGroupCount(); i++) {
					tab = new Tab(gcs.get(i).getGroupCaption());
					tab.setPane(detailViewers.get(i));
					mainTab.addTab(tab);
				}
			}
			for (int i = 0; i < getDetailCount(); i++) {
				tab = new Tab(names.get(i));
				tab.setPane(grids.get(i));
				mainTab.addTab(tab);
			}
			List<Canvas> canvasPanels = getCanvasPanels();
			
			if(!BaseHelpUtils.isNullOrEmpty(canvasPanels) && canvasPanels.size() > 0){
				List<String> canvasPanelNames = getCanvasPanelNames();
				int canvasPanelsCount = canvasPanelNames.size();
				for(int i = 0;i<canvasPanels.size();i++){
					tab = new Tab(canvasPanelsCount<=i?"":canvasPanelNames.get(i));
					tab.setPane(canvasPanels.get(i));
					mainTab.addTab(tab);
				}
			}
			addMember(mainTab);
			break;
		}

	}

	protected int layoutMode = COMPONENT_LAYOUT_NORMAL;
	protected List<DetailViewer> detailViewers = new ArrayList<>();
	protected boolean isGroup = false;
	protected String __businessId;
	
	public void load() {

	}
	
	public List<Canvas> getCanvasPanels(){
		return null;
	}
	
	public List<String> getCanvasPanelNames(){
		return null;
	}

	public abstract int getHorizontalPercent();

	public abstract DataSource getMainDataSource();

	public abstract int getDetailCount();

	public abstract int getGroupCount();

	public abstract String getName();

	public abstract List<GroupColumn> getGroupColumnNames();

	public abstract List<ListGrid> getDetailListGrids();

	public abstract List<String> getDetailNames();

	private Record __record;

	public List<String> getDetailPackageNames() {
		return new ArrayList<>();
	}

	public void viewRecord(Record r, boolean includeDetail) {
		setRecord(r);
		Record[] rs = new Record[1];
		rs[0] = r;
		for (DetailViewer view : detailViewers) {
			view.setCanSelectText(true);
			view.setData(rs);
			view.selectRecord(0);
			view.redraw();
		}
		if (includeDetail) {
			viewDetailData();
		}
	}

	public void viewRecord(Record r) {
		viewRecord(r, true);
	}

	public void viewDetailData() {

	}

	public Map getValues() {
		if (getRecord() == null)
			return new HashMap();
		Map res = getRecord().toMap();
		List<ListGrid> grids = getDetailListGrids();
		if (grids != null && grids.size() > 0) {
			for (ListGrid g : grids) {
				g.setCanDragSelectText(true);
				DBDataSource ds = (DBDataSource) g.getDataSource();
				String name = "detail" + ds.getActionName();
				MapUtils.convertRecordToMap(g, g.getRecords(), res, name);
			}
		}
		return res;
	}

	public void viewSelectedData(ListGrid grid) {
		for (DetailViewer view : detailViewers) {
			view.viewSelectedData(grid);
		}
	}

	/**
	 * @return the layoutMode
	 */
	public int getLayoutMode() {
		return layoutMode;
	}

	/**
	 * @param layoutMode
	 *            the layoutMode to set
	 */
	public void setLayoutMode(int layoutMode) {
		this.layoutMode = layoutMode;
	}

	public boolean isIsGroup() {
		return isGroup;
	}

	@Override
	public void setIsGroup(boolean isGroup) {
		this.isGroup = isGroup;
	}

	/**
	 * @return the __businessId
	 */
	public String getBusinessId() {
		return __businessId;
	}

	/**
	 * @param __businessId
	 *            the __businessId to set
	 */
	public void setBusinessId(String __businessId) {
		this.__businessId = __businessId;
	}

	/**
	 * @return the __record
	 */
	public Record getRecord() {
		return __record;
	}

	/**
	 * @param __record
	 *            the __record to set
	 */
	public void setRecord(Record __record) {
		this.__record = __record;
	}

	public Record get__instanceData() {
		return __instanceData;
	}

	public void set__instanceData(Record __instanceData) {
		this.__instanceData = __instanceData;
	}

}
