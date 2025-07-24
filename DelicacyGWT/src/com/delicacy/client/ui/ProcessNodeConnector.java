package com.delicacy.client.ui;

import java.util.Objects;
import java.util.logging.Logger;

import com.delicacy.client.BaseHelpUtils;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.ConnectorStyle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.util.ValueCallback;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Dialog;
import com.smartgwt.client.widgets.StatefulCanvas;
import com.smartgwt.client.widgets.drawing.DrawItem;
import com.smartgwt.client.widgets.drawing.DrawLinePath;
import com.smartgwt.client.widgets.drawing.DrawPane;
import com.smartgwt.client.widgets.drawing.Point;
import com.smartgwt.client.widgets.events.ButtonClickEvent;
import com.smartgwt.client.widgets.events.ButtonClickHandler;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.MenuItemIfFunction;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

/**
 *
 * @author guangxun
 */
public class ProcessNodeConnector extends DrawLinePath {

    public final static int MOUSE_DRAG_POSITION_START = 0;
    public final static int MOUSE_DRAG_POSITION_END = 1;
    public final static int MOUSE_DRAG_POSITION_MIDDLE = 2;
    public static String originalCondition = null;
    public static boolean useOriginalCondition = false;

    private static final Logger __logger = Logger.getLogger("");

    public ProcessNodeConnector() {
        this(false);
    }

    public ProcessNodeConnector(boolean displayOnly) {
        super();
        setLineWidth(6);
        setConnectorStyle(ConnectorStyle.DIAGONAL);
        setTailSize(30);
        if (!displayOnly) {
            setContextMenu(createContextMenu());

            addDragStartHandler(new com.smartgwt.client.widgets.drawing.events.DragStartHandler() {
                @Override
                public void onDragStart(com.smartgwt.client.widgets.drawing.events.DragStart event) {

                    int xd = Math.abs(getStartLeft() - getEndLeft()) / 2;
                    int yd = Math.abs(getStartTop() - getEndTop()) / 2;
                    setIsMouseDown(true);
                    if (Math.abs(event.getX() - getStartLeft()) < xd && Math.abs(event.getY() - getStartTop()) < yd) {
                        setDragPosition(MOUSE_DRAG_POSITION_START);
                    } else if (Math.abs(event.getX() - getEndLeft()) < xd
                            && Math.abs(event.getY() - getEndTop()) < yd) {
                        setDragPosition(MOUSE_DRAG_POSITION_END);
                    }
                }

            });
            addDragMoveHandler(new com.smartgwt.client.widgets.drawing.events.DragMoveHandler() {
                @Override
                public void onDragMove(com.smartgwt.client.widgets.drawing.events.DragMove event) {

                    switch (getDragPosition()) {
                        case MOUSE_DRAG_POSITION_START:
                            setStartPoint(new Point(event.getX(), event.getY()));
                            if (getNextNode() != null) {
                                setEndPoint(getNextNode().getPreviousConnectPoint(getPreviousNode()));
                            }
                            break;
                        case MOUSE_DRAG_POSITION_END:
                            setEndPoint(new Point(event.getX(), event.getY()));
                            if (getPreviousNode() != null) {
                                setStartPoint(getPreviousNode().getNextConnectPoint(getNextNode()));
                            }
                            break;
                    }
                }

            });
            addDragStopHandler(new com.smartgwt.client.widgets.drawing.events.DragStopHandler() {
                @Override
                public void onDragStop(com.smartgwt.client.widgets.drawing.events.DragStop event) {
                    setIsMouseDown(false);
                    switch (getDragPosition()) {
                        case MOUSE_DRAG_POSITION_START:
                            ProcessDrawNode pdn = findPositionNode(event.getX(), event.getY());
                            if (pdn == null) {
                                if (getPreviousNode() != null) {
                                    setStartPoint(getPreviousNode().getNextConnectPoint(getNextNode()));
                                }
                            } else {
                                if (getPreviousNode() != null && !getPreviousNode().equals(pdn)) {
                                    getPreviousNode().getNextConnectors().remove(getTheObjectItself());
                                }
                                setStartPoint(pdn.getNextConnectPoint(getNextNode()));
                                setPreviousNode(pdn);
                                pdn.getNextConnectors().add(getTheObjectItself());
                            }
                            setEndPoint(getNextNode().getPreviousConnectPoint(getPreviousNode()));
                            break;
                        case MOUSE_DRAG_POSITION_END:
                            pdn = findPositionNode(event.getX(), event.getY());
                            if (pdn == null) {
                                if (getNextNode() != null) {
                                    setEndPoint(getNextNode().getPreviousConnectPoint(getPreviousNode()));
                                }
                            } else {
                                if (getNextNode() != null && !getNextNode().equals(pdn)) {
                                    getNextNode().getPreviousConnectors().remove(getTheObjectItself());
                                }
                                setEndPoint(pdn.getPreviousConnectPoint(getPreviousNode()));
                                setNextNode(pdn);
                                pdn.getPreviousConnectors().add(getTheObjectItself());
                            }
                            setStartPoint(getPreviousNode().getNextConnectPoint(getNextNode()));
                            break;
                        default:
                            ProcessDrawNode pdn1 = findPositionNode(getStartPoint().getX(), getStartPoint().getY());
                            if (pdn1 != null) {
                                if (getPreviousNode() != null && !getPreviousNode().equals(pdn1)) {
                                    getPreviousNode().getNextConnectors().remove(getTheObjectItself());
                                }
                                setStartPoint(pdn1.getNextConnectPoint(getNextNode()));
                                setPreviousNode(pdn1);
                                pdn1.getNextConnectors().add(getTheObjectItself());
                            } 
                            ProcessDrawNode pdn2 = findPositionNode(getEndPoint().getX(), getEndPoint().getY());
                            if (pdn2 != null) {
                                if (getNextNode() != null && !getNextNode().equals(pdn2)) {
                                    getNextNode().getPreviousConnectors().remove(getTheObjectItself());
                                }
                                setEndPoint(pdn2.getPreviousConnectPoint(getPreviousNode()));
                                setNextNode(pdn2);
                                pdn2.getPreviousConnectors().add(getTheObjectItself());
                            } 
                            break;
                    }
                }

            });
        }
    }

    private ProcessNodeConnector getTheObjectItself() {
        return this;
    }

    public ProcessDrawNode findPositionNode(int x, int y) {
        DrawPane pane = getDrawPane();
        if (pane == null) {
            return null;
        }
        for (DrawItem di : pane.getDrawItems()) {
            if (di instanceof ProcessDrawNode) {
                ProcessDrawNode pdn = (ProcessDrawNode) di;
                int px1 = pdn.getLeft() - 5;
                int py1 = pdn.getTop() - 5;
                int px2 = pdn.getWidth() + px1 + 5;
                int py2 = pdn.getHeight() + py1 + 5;
                if (x >= px1 && x <= px2 && y >= py1 && y <= py2) {
                    return pdn;
                }

            }
        }
        return null;
    }

    private ProcessDrawNode previousNode;
    private ProcessDrawNode nextNode;
    private Point[] points = null;
    private boolean isMouseDown = false;
    private int dragPosition = MOUSE_DRAG_POSITION_MIDDLE;
    private Record __data = new Record();

    public void calculatePoints() {
        Point startP, endP;
        if (getControlPoint1() != null) {
            startP = getControlPoint1();
        } else {
            startP = getStartPoint();
        }
        if (getControlPoint2() != null) {
            endP = getControlPoint2();
        } else {
            endP = getEndPoint();
        }
        int xd, yd;
        int x1, y1, x2, y2;
        x1 = startP.getX();
        y1 = startP.getY();
        x2 = endP.getX();
        y2 = endP.getY();
        xd = Math.abs(x2 - x1);
        yd = Math.abs(y2 - y1);
        double rate = yd / (double) xd;
        if (xd >= yd) {
            points = new Point[xd + 1];
            int i = x1;
            while (true) {
                int xx = Math.abs(i - x1);
                int yy = (int) Math.round(xx * rate);
                points[xx] = new Point(x2 > x1 ? x1 + xx : x1 - xx, y2 > y1 ? y1 + yy : y1 - yy);
                if (x2 >= x1) {
                    i++;
                    if (i > x2) {
                        break;
                    }
                } else {
                    i--;
                    if (i < x2) {
                        break;
                    }
                }
            }
        } else {
            rate = xd / (double) yd;
            points = new Point[yd + 1];
            int i = y1;
            while (true) {
                int yy = Math.abs(i - y1);
                int xx = (int) Math.round(yy * rate);
                points[yy] = new Point(x2 > x1 ? x1 + xx : x1 - xx, y2 > y1 ? y1 + yy : y1 - yy);

                if (y2 >= y1) {
                    i++;
                    if (i > y2) {
                        break;
                    }
                } else {
                    i--;
                    if (i < y2) {
                        break;
                    }
                }

            }
        }
    }

    public boolean isThePointOnTheLine(int x, int y) {
        if (points == null) {
            calculatePoints();
        }
        for (Point point : points) {
            if (point == null) {
                return false;
            }
            if (point.getX() == x && point.getY() == y) {
                return true;
            }
        }
        return false;
    }

    public int isOnTheLine(int x1, int y1, int x2, int y2) {
        if (points == null) {
            calculatePoints();
        }
        int mx = (x1 + x2) / 2;
        int my = (y1 + y2) / 2;
        boolean found = false;
        double refval = Double.MAX_VALUE;
        for (Point point : points) {
            if (point == null) {
                return -1;
            }
            if (point.getX() >= x1 && point.getX() <= x2 && point.getY() >= y1 && point.getY() <= y2) {
                found = true;
                refval = Math.min(refval, Math.sqrt(Math.pow((point.getX()-mx), 2) + Math.pow((point.getY()-my), 2)));
            }
        }
        if (!found) {
            return -1;
        } else {
            return BaseHelpUtils.getIntValue(refval);
        }
    }

    protected Menu createContextMenu() {
        Menu menu = new Menu();
        menu.setWidth(140);

        MenuItemIfFunction enableCondition = new MenuItemIfFunction() {
            @Override
            public boolean execute(Canvas target, Menu menu, MenuItem item) {

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
                originalCondition = "";
                if (__data != null) {
                    originalCondition = __data.getAttribute("condition");
                }
                useOriginalCondition = false;
                Dialog dialog = new Dialog();
                dialog.addButtonClickHandler(new ButtonClickHandler() {
					
					@Override
					public void onButtonClick(ButtonClickEvent event) {
						StatefulCanvas sc = event.getTargetCanvas();
						if(Objects.equals(sc.getTitle(), "取消")) {
							ProcessNodeConnector.useOriginalCondition = true;
						}
					}
				});
                SC.askforValue("条件", "请输入条件:", originalCondition, new ValueCallback() {
                    @Override
                    public void execute(String value) {
                        if (__data == null) {
                            __data = new Record();
                        }
                        String newCondition = null;
                        if(ProcessNodeConnector.useOriginalCondition) {
                        	newCondition = ProcessNodeConnector.originalCondition;
                        }else {
                        	newCondition = value == null ? " " : value;
                        }
                        __data.setAttribute("condition", newCondition);
                        setTitle(newCondition);
                    }

                }, dialog);
            }
        });

        menu.setItems(closeItem, closeAllButCurrent);
        return menu;
    }

    private void remove() {
        ProcessDrawNode preNode = getPreviousNode();
        ProcessDrawNode nextNode1 = getNextNode();
        if (preNode != null) {
            preNode.getNextConnectors().remove(this);
        }
        if (nextNode1 != null) {
            nextNode1.getPreviousConnectors().remove(this);
        }
        erase();
    }

    /**
     * @return the points
     */
    public Point[] getPoints() {
        return points;
    }

    /**
     * @param points the points to set
     */
    public void setPoints(Point[] points) {
        this.points = points;
    }

    /**
     * @return the previousNode
     */
    public ProcessDrawNode getPreviousNode() {
        return previousNode;
    }

    /**
     * @param previousNode the previousNode to set
     */
    public void setPreviousNode(ProcessDrawNode previousNode) {
        this.previousNode = previousNode;
    }

    /**
     * @return the nextNode
     */
    public ProcessDrawNode getNextNode() {
        return nextNode;
    }

    /**
     * @param nextNode the nextNode to set
     */
    public void setNextNode(ProcessDrawNode nextNode) {
        this.nextNode = nextNode;
    }

    /**
     * @return the isMouseDown
     */
    public boolean isIsMouseDown() {
        return isMouseDown;
    }

    /**
     * @param isMouseDown the isMouseDown to set
     */
    public void setIsMouseDown(boolean isMouseDown) {
        this.isMouseDown = isMouseDown;
    }

    /**
     * @return the dragPosition
     */
    public int getDragPosition() {
        return dragPosition;
    }

    /**
     * @param dragPosition the dragPosition to set
     */
    public void setDragPosition(int dragPosition) {
        this.dragPosition = dragPosition;
    }

    /**
     * @return the __data
     */
    public Record getData() {
        return __data;
    }

    /**
     * @param __data the __data to set
     */
    public void setData(Record __data) {
        this.__data = __data;
    }

}
