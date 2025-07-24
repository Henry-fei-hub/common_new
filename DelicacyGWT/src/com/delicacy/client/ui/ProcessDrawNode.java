package com.delicacy.client.ui;

import com.delicacy.client.BaseHelpUtils;
import com.delicacy.client.app.form.NewProcessActivityEdit;
import com.delicacy.client.data.DataEditEvent;
import com.delicacy.client.data.DataEditedHandler;
import com.delicacy.client.process.SystemProcessConstants;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.drawing.DrawGroup;
import com.smartgwt.client.widgets.drawing.DrawImage;
import com.smartgwt.client.widgets.drawing.DrawItem;
import com.smartgwt.client.widgets.drawing.DrawLabel;
import com.smartgwt.client.widgets.drawing.DrawPane;
import com.smartgwt.client.widgets.drawing.Point;
import com.smartgwt.client.widgets.drawing.events.DragMove;
import com.smartgwt.client.widgets.drawing.events.DragStart;
import com.smartgwt.client.widgets.drawing.events.DragStop;
import com.smartgwt.client.widgets.drawing.events.DragStopHandler;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.MenuItemIfFunction;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 *
 * @author guangxun
 */
public class ProcessDrawNode extends DrawGroup {

	public final static String ACTIVITY_SERIAL_NO = "activitySerialNo";
	public final static String ACTIVITY_TYPE = "activityType";
	public final static String ACTIVITY_START = "start";
	public final static String ACTIVITY_END = "end";
	public final static String ACTIVITY_APPROVAL = "approval";
	public final static String ACTIVITY_PROCESSOR = "processor";
	public final static String ACTIVITY_ATTENTION = "attention";
	public final static String DETAIL_ACTIVITY = "detailSystemProcessActivity";
	public final static String DETAIL_LINK = "detailSystemProcessLink";

	private static final Logger __logger = Logger.getLogger("");

	private DrawImage drawImage;
	private DrawLabel label;
	private DrawLabel description;
	private Record __data = new Record();
	private Set<ProcessNodeConnector> previousConnectors = new HashSet<>();
	private Set<ProcessNodeConnector> nextConnectors = new HashSet<>();
	private String nodeType = null;
	private int activitySerialNo = 0;
	public final static int ICON_SIZE = 64;
	private int imageSize = ICON_SIZE;
	private boolean __displayOnly = false;

	public ProcessDrawNode(int x, int y) {
		this(x, y, ICON_SIZE, false);
	}

	public ProcessDrawNode(int x, int y, int __imageSize, boolean displayOnly) {
		super();
		this.addDragStopHandler(new DrawGroupDragStopHandler());
		this.addDragMoveHandler(new DrawGroupDragMoveHandler());
		imageSize = __imageSize;
		__displayOnly = displayOnly;
		drawImage = new DrawImage();
		drawImage.setTop(y);
		drawImage.setLeft(x);
		drawImage.setWidth(imageSize);
		drawImage.setHeight(imageSize);
		drawImage.setSrc("auditor_normal.png");
		label = new DrawLabel();
		label.setAttribute("alignment", "center", true);
		label.setTop(y + imageSize);
		label.setLeft(x + 12);
		label.setFontSize(14);
		label.setContents("审批人");
		description = new DrawLabel();
		description.setAlignment("center");
		description.setTop(y + imageSize + 14);
		description.setLeft(x + 12);
		description.setFontSize(14);
		description.setContents("");

		setDrawItems(drawImage, label, description);
		// setKeepInParentRect(true);
		if (!__displayOnly) {
			setContextMenu(createContextMenu());
			addDragStartHandler(new com.smartgwt.client.widgets.drawing.events.DragStartHandler() {
				@Override
				public void onDragStart(DragStart event) {
					repaintOtherItems();
				}

			});
			addDragMoveHandler(new com.smartgwt.client.widgets.drawing.events.DragMoveHandler() {
				@Override
				public void onDragMove(DragMove event) {
					dragMoveProcess();
				}

			});
			addDragStopHandler(new com.smartgwt.client.widgets.drawing.events.DragStopHandler() {
				@Override
				public void onDragStop(DragStop event) {
					dragMoveProcess(true);
				}

			});

			addClickHandler(new com.smartgwt.client.widgets.drawing.events.ClickHandler() {
				@Override
				public void onClick(com.smartgwt.client.widgets.drawing.events.ClickEvent event) {
					repaintOtherItems();
				}

			});
		}

	}
	
	public static void recalculateArea(DrawPane drawArea, int x, int y) {
		boolean changed = false;
		if(x > drawArea.getWidth()) {
			drawArea.setWidth(x+100);
			drawArea.setDrawingWidth(x+100);
			changed = true;
		}
		if(y > drawArea.getHeight()) {
			drawArea.setHeight(y+100);
			drawArea.setDrawingHeight(y+100);
			changed = true;
		}
		if(changed) drawArea.redraw();
	}

	@Override
	public void setTop(int v) {
		super.setTop(v);
		drawImage.setTop(v);
		label.setTop(v + ICON_SIZE);
	}

	@Override
	public void setLeft(int v) {
		super.setLeft(v);
		drawImage.setLeft(v);
		label.setLeft(v + 12);
	}

	public void setNormalIcon() {
		switch (getNodeType()) {
		case ACTIVITY_START:
			getDrawImage().setSrc("start.png");
			break;
		case ACTIVITY_END:
			getDrawImage().setSrc("stop.png");
			break;
		case ACTIVITY_PROCESSOR:
			getDrawImage().setSrc("operator_normal.png");
			break;
		case ACTIVITY_ATTENTION:
			getDrawImage().setSrc("insider_normal.png");
			break;
		default:
			getDrawImage().setSrc("auditor_normal.png");
			break;
		}
	}

	public void setActiveIcon() {
		switch (getNodeType()) {
		case ACTIVITY_START:
			getDrawImage().setSrc("start.png");
			break;
		case ACTIVITY_END:
			getDrawImage().setSrc("stop.png");
			break;
		case ACTIVITY_PROCESSOR:
			getDrawImage().setSrc("operator_active.png");
			break;
		case ACTIVITY_ATTENTION:
			getDrawImage().setSrc("insider_active.png");
			break;
		default:
			getDrawImage().setSrc("auditor_active.png");
			break;
		}
	}

	// 如果一个节点被选中,其他节点都显示成没被选中的样子
	public void repaintOtherItems() {
		DrawPane dp = getDrawPane();
		if (dp == null) {
			return;
		}
		DrawItem[] items = dp.getDrawItems();
		if (items == null || items.length == 0) {
			return;
		}
		for (DrawItem i : items) {
			if (i instanceof ProcessDrawNode) {
				ProcessDrawNode n = (ProcessDrawNode) i;
				if (n.equals(this)) {
					continue;
				}
				n.setNormalIcon();
			}
		}
		setActiveIcon();

	}

	public void setSrc(String icon) {
		getDrawImage().setSrc(icon);
	}

	@Override
	public int getTop() {
		return getDrawImage().getTop();
	}

	@Override
	public int getLeft() {
		return getDrawImage().getLeft();

	}

	public void setContent(String str) {
		getLabel().setContents(str);
	}

	public void setDescription(String str) {
		description.setContents(str);
	}

	protected Menu createContextMenu() {
		Menu menu = new Menu();
		menu.setWidth(140);

		MenuItemIfFunction enableCondition = new MenuItemIfFunction() {
			@Override
			public boolean execute(Canvas target, Menu menu, MenuItem item) {
				if (getNodeType().equals("start") || getNodeType().equals("end")) {
					if (item.getTitle().equals("删除") || item.getTitle().equals("属性")) {
						return false;
					}
				}
				return true;
			}
		};

		MenuItem closeItem = new MenuItem("删除");
		closeItem.setIcon("/images/menuicons/delete.png");
		closeItem.setEnableIfCondition(enableCondition);
		closeItem.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(MenuItemClickEvent event) {
				remove();
			}
		});

		MenuItem closeAllButCurrent = new MenuItem("属性");
		closeAllButCurrent.setIcon("/images/menuicons/properties.png");
		closeAllButCurrent.setEnableIfCondition(enableCondition);
		closeAllButCurrent.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(MenuItemClickEvent event) {
				editProperties();
			}
		});

		menu.setItems(closeItem, closeAllButCurrent);
		return menu;
	}

	public void editProperties() {
		StandardEditWindow sew = new StandardEditWindow();
		sew.setHeaderIcon("/images/menuicons/properties.png");
		sew.setTitle("属性");
		sew.setForm(new NewProcessActivityEdit());
		Record data = getData();
		if(BaseHelpUtils.isNullOrEmpty(data.getAttribute("activityType"))) {
			switch(getNodeType()) {
			case ProcessDrawNode.ACTIVITY_START:
				data.setAttribute("activityType", SystemProcessConstants.ACTIVITY_TYPE_START);
				break;
			case ProcessDrawNode.ACTIVITY_END:
				data.setAttribute("activityType", SystemProcessConstants.ACTIVITY_TYPE_END);
				break;
            case ACTIVITY_APPROVAL:
            	data.setAttribute("activityType", SystemProcessConstants.ACTIVITY_TYPE_APPROVAL);
                break;
            case ProcessDrawNode.ACTIVITY_PROCESSOR:
            	data.setAttribute("activityType", SystemProcessConstants.ACTIVITY_TYPE_PROCESS);
                break;
            case ProcessDrawNode.ACTIVITY_ATTENTION:
            	data.setAttribute("activityType", SystemProcessConstants.ACTIVITY_TYPE_ATTENTION);
                break;
			}
		}
		sew.setData(data);
		sew.startEdit();
		final int activityType = BaseHelpUtils.getIntValue(data.getAttribute("activityType"));
		sew.addDataEditedHandler(new DataEditedHandler() {
			@Override
			public void onDataEdited(DataEditEvent event) {
				setData(event.getData());
				Record nodeData = getData();
				String name = nodeData.getAttribute("activityName");
				if (name != null && !name.isEmpty()) {
					setContent(name);
				}
				int newActivityType = BaseHelpUtils.getIntValue(nodeData.getAttribute("activityType"));
				if(activityType != newActivityType) {
					switch(newActivityType) {
					case SystemProcessConstants.ACTIVITY_TYPE_START:
						setNodeType(ProcessDrawNode.ACTIVITY_START);
						if(BaseHelpUtils.isNullOrEmpty(name)) {
							setContent("发起人");
						}
						break;
					case SystemProcessConstants.ACTIVITY_TYPE_END:
						setNodeType(ProcessDrawNode.ACTIVITY_END);
						if(BaseHelpUtils.isNullOrEmpty(name)) {
							setContent("结束");
						}
						break;
					case SystemProcessConstants.ACTIVITY_TYPE_APPROVAL:
						setNodeType(ProcessDrawNode.ACTIVITY_APPROVAL);
						if(BaseHelpUtils.isNullOrEmpty(name)) {
							setContent("审核人");
						}
						break;
					case SystemProcessConstants.ACTIVITY_TYPE_PROCESS:
						setNodeType(ProcessDrawNode.ACTIVITY_PROCESSOR);
						if(BaseHelpUtils.isNullOrEmpty(name)) {
							setContent("处理人");
						}
						break;
					case SystemProcessConstants.ACTIVITY_TYPE_ATTENTION:
						setNodeType(ProcessDrawNode.ACTIVITY_ATTENTION);
						if(BaseHelpUtils.isNullOrEmpty(name)) {
							setContent("知会人");
						}
						break;
					default:
						break;
					}
					setActiveIcon();
				}
			}

		});
		sew.show();
	}

	public void remove() {
		if (getPreviousConnectors().size() == 1 && getNextConnectors().size() == 1) {
			ProcessNodeConnector preConnector = null;
			for (ProcessNodeConnector pc : getPreviousConnectors()) {
				preConnector = pc;
			}
			ProcessNodeConnector nextConnector = null;
			for (ProcessNodeConnector pc : getNextConnectors()) {
				nextConnector = pc;
			}
			preConnector.setNextNode(nextConnector.getNextNode());
			preConnector
					.setEndPoint(nextConnector.getNextNode().getPreviousConnectPoint(preConnector.getPreviousNode()));
			nextConnector.getNextNode().getPreviousConnectors().add(preConnector);
			nextConnector.getNextNode().getPreviousConnectors().remove(nextConnector);
			nextConnector.erase();
			preConnector.calculatePoints();
		} else if (getPreviousConnectors().size() > 1 && getNextConnectors().size() == 1) {
			ProcessNodeConnector nextConnector = null;
			for (ProcessNodeConnector pc : getNextConnectors()) {
				nextConnector = pc;
			}
			ProcessDrawNode nextNode = nextConnector.getNextNode();
			nextNode.getPreviousConnectors().remove(nextConnector);
			nextConnector.erase();
			for (ProcessNodeConnector pc : getPreviousConnectors()) {
				pc.setNextNode(nextNode);
				pc.setEndPoint(nextNode.getPreviousConnectPoint(pc.getPreviousNode()));
				nextNode.getPreviousConnectors().add(pc);
				pc.calculatePoints();
			}
		} else if (getPreviousConnectors().size() == 1 && getNextConnectors().size() > 1) {

			ProcessNodeConnector preConnector = null;
			for (ProcessNodeConnector pc : getPreviousConnectors()) {
				preConnector = pc;
			}
			ProcessDrawNode preNode = preConnector.getPreviousNode();
			preNode.getNextConnectors().remove(preConnector);
			preConnector.erase();
			for (ProcessNodeConnector pc : getNextConnectors()) {
				preNode.getNextConnectors().add(pc);
				pc.setPreviousNode(preNode);
				pc.setStartPoint(preNode.getNextConnectPoint(pc.getNextNode()));
				pc.calculatePoints();
			}
		}
		if (getNodeType().equals(ACTIVITY_ATTENTION) && getPreviousConnectors().size() == 1
				&& getNextConnectors().isEmpty()) {
			ProcessNodeConnector preConnector = null;
			for (ProcessNodeConnector pc : getPreviousConnectors()) {
				preConnector = pc;
			}
			ProcessDrawNode preNode = preConnector.getPreviousNode();
			preNode.getNextConnectors().remove(preConnector);
			preConnector.erase();
		}
		this.erase();

	}

	public void setHelpMessage(String msg) {
		this.drawImage.setShowHover(true);
		this.drawImage.setPrompt("<p style='width:400px;'>" +msg + "</p>");
	}

	private ProcessDrawNode getThisObject() {
		return this;
	}

	public Point getNextConnectPoint() {
		return new Point(getLeft() + getWidth(), getTop() + ICON_SIZE / 2);
	}

	public Point getNextConnectPoint(ProcessDrawNode N) {
		int px1 = getLeft();
		int py1 = getTop();
		int px2 = getWidth() + px1;
		int py2 = getHeight() + py1;
		int pcx1 = (px1 + px2) / 2;
		int pcy1 = (py1 + py2) / 2;

		if (N == null) {
			return new Point(px2, pcy1);
		}

		int nx1 = N.getLeft();
		int ny1 = N.getTop();
		int nx2 = N.getWidth() + nx1;
		int ny2 = N.getHeight() + ny1;
		int ncx1 = (nx1 + nx2) / 2;
		int ncy1 = (ny1 + ny2) / 2;

		int dx1 = Math.abs(nx1 - pcx1);
		int dy1 = Math.abs(ny1 - pcy1);

		int miny1 = pcy1 - dx1;
		int maxy1 = pcy1 + dx1;
		int minx1 = pcx1 - dy1;
		int maxx1 = pcx1 + dy1;

		if (nx1 > px2 && ny1 >= miny1 && ny1 <= maxy1) {
			return new Point(px2, pcy1);
		} else if (px1 > nx2 && ny1 >= miny1 && ny1 <= maxy1) {
			return new Point(px1, pcy1);
		} else if (ny1 > py2 && nx1 >= minx1 && nx1 <= maxx1) {
			return new Point(pcx1, py2);
		} else {
			return new Point(pcx1, py1);
		}
	}

	public Point getPreviousConnectPoint() {
		return new Point(getLeft(), getTop() + ICON_SIZE / 2);
	}

	public Point getPreviousConnectPoint(ProcessDrawNode N) {
		int px1 = getLeft();
		int py1 = getTop();
		int px2 = getWidth() + px1;
		int py2 = getHeight() + py1;
		int pcx1 = (px1 + px2) / 2;
		int pcy1 = (py1 + py2) / 2;

		if (N == null) {
			return new Point(px1, pcy1);
		}

		int nx1 = N.getLeft();
		int ny1 = N.getTop();
		int nx2 = N.getWidth() + nx1;
		int ny2 = N.getHeight() + ny1;
		int ncx1 = (nx1 + nx2) / 2;
		int ncy1 = (ny1 + ny2) / 2;

		int dx1 = Math.abs(nx1 - pcx1);
		int dy1 = Math.abs(ny1 - pcy1);

		int miny1 = pcy1 - dx1;
		int maxy1 = pcy1 + dx1;
		int minx1 = pcx1 - dy1;
		int maxx1 = pcx1 + dy1;

		if (nx1 > px2 && ny1 >= miny1 && ny1 <= maxy1) {
			return new Point(px2, pcy1);
		} else if (px1 > nx2 && ny1 >= miny1 && ny1 <= maxy1) {
			return new Point(px1, pcy1);
		} else if (ny1 > py2 && nx1 >= minx1 && nx1 <= maxx1) {
			return new Point(pcx1, py2);
		} else {
			return new Point(pcx1, py1);
		}
	}

	public StringBuilder append(StringBuilder sb, Double[] box) {
		if (sb == null) {
			sb = new StringBuilder();
		}
		for (int i = 0; i < 4; i++) {
			sb.append(box[i]);
			sb.append(" ");
		}
		return sb;
	}

	public void dragMoveProcess() {
		dragMoveProcess(false);
	}

	public void dragMoveProcess(boolean recal) {
		for (ProcessNodeConnector n : previousConnectors) {
			n.setEndPoint(getPreviousConnectPoint(n.getPreviousNode()));
			if (recal) {
				n.calculatePoints();
			}
		}
		for (ProcessNodeConnector n : nextConnectors) {
			n.setStartPoint(getNextConnectPoint(n.getNextNode()));
			if (recal) {
				n.calculatePoints();
			}
		}
	}

	/**
	 * @return the nodeType
	 */
	public String getNodeType() {
		return nodeType;
	}

	/**
	 * @param nodeType
	 *            the nodeType to set
	 */
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	/**
	 * @return the drawImage
	 */
	public DrawImage getDrawImage() {
		return drawImage;
	}

	/**
	 * @param drawImage
	 *            the drawImage to set
	 */
	public void setDrawImage(DrawImage drawImage) {
		this.drawImage = drawImage;
	}

	/**
	 * @return the label
	 */
	public DrawLabel getLabel() {
		return label;
	}

	/**
	 * @param label
	 *            the label to set
	 */
	public void setLabel(DrawLabel label) {
		this.label = label;
	}

	/**
	 * @return the previousConnectors
	 */
	public Set<ProcessNodeConnector> getPreviousConnectors() {
		return previousConnectors;
	}

	/**
	 * @param previousConnectors
	 *            the previousConnectors to set
	 */
	public void setPreviousConnectors(Set<ProcessNodeConnector> previousConnectors) {
		this.previousConnectors = previousConnectors;
	}

	/**
	 * @return the nextConnectors
	 */
	public Set<ProcessNodeConnector> getNextConnectors() {
		return nextConnectors;
	}

	/**
	 * @param nextConnectors
	 *            the nextConnectors to set
	 */
	public void setNextConnectors(Set<ProcessNodeConnector> nextConnectors) {
		this.nextConnectors = nextConnectors;
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

	/**
	 * @return the activitySerialNo
	 */
	public int getActivitySerialNo() {
		return activitySerialNo;
	}

	/**
	 * @param activitySerialNo
	 *            the activitySerialNo to set
	 */
	public void setActivitySerialNo(int activitySerialNo) {
		this.activitySerialNo = activitySerialNo;
	}

	/**
	 * @return the imageSize
	 */
	public int getImageSize() {
		return imageSize;
	}

	/**
	 * @param imageSize
	 *            the imageSize to set
	 */
	public void setImageSize(int imageSize) {
		this.imageSize = imageSize;
	}

	/**
	 * @return the __displayOnly
	 */
	public boolean isDisplayOnly() {
		return __displayOnly;
	}

	/**
	 * @param __displayOnly
	 *            the __displayOnly to set
	 */
	public void setDisplayOnly(boolean __displayOnly) {
		this.__displayOnly = __displayOnly;
	}

	/**
	 * @return the description
	 */
	public DrawLabel getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(DrawLabel description) {
		this.description = description;
	}

}
