package com.delicacy.client.app.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import com.delicacy.client.MapUtils;
import com.delicacy.client.data.DBDataSource;
import com.delicacy.client.ui.AbstractWizadPage;
import com.delicacy.client.ui.DragableImage;
import com.delicacy.client.ui.ProcessDrawNode;
import com.delicacy.client.ui.ProcessNodeConnector;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ArrowStyle;
import com.smartgwt.client.types.LinePattern;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.drawing.DrawItem;
import com.smartgwt.client.widgets.drawing.DrawPane;
import com.smartgwt.client.widgets.drawing.Point;
import com.smartgwt.client.widgets.drawing.events.DragStart;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.DoubleClickEvent;
import com.smartgwt.client.widgets.events.DoubleClickHandler;
import com.smartgwt.client.widgets.events.DragStartEvent;
import com.smartgwt.client.widgets.events.DragStartHandler;
import com.smartgwt.client.widgets.events.DropEvent;
import com.smartgwt.client.widgets.events.DropHandler;
import com.smartgwt.client.widgets.events.ResizedEvent;
import com.smartgwt.client.widgets.events.ResizedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.layout.VStack;

public class SsystemprocessporDetailSystemProcessActivity extends AbstractWizadPage {

    private static final Logger __logger = Logger.getLogger("");
    private DrawPane drawArea;
    private String nodeType = "";
    private ProcessDrawNode currentItem = null;
    private ProcessDrawNode startItem = null;
    private ProcessDrawNode endItem = null;
    private ProcessNodeConnector connector = null;
    private double originalWidth = 0;
    private double originalHeight = 0;
    private double zoom = 1.0;

    public SsystemprocessporDetailSystemProcessActivity() {
        HLayout rightPanel = new HLayout();
        rightPanel.setWidth100();
        rightPanel.setHeight100();

        final HLayout drawContainer = new HLayout();
        drawContainer.setWidth100();
        drawContainer.setHeight100();
        drawContainer.setOverflow(Overflow.AUTO);
        rightPanel.addMember(drawContainer);

        drawArea = new DrawPane();
        drawArea.setWidth(2000);
        drawArea.setHeight(1000);
        drawArea.setCanDrop(true);
        drawArea.setCanAcceptDrop(true);
        drawArea.setBackgroundColor("#ffffff");
        drawArea.setDropTypes("approval");
        drawArea.setZoomLevel(1.0);

        drawContainer.addMember(drawArea);

        generateDrawNode(10, 10, "start");
        currentItem.setActiveIcon();
        startItem = currentItem;

        generateDrawNode(600, 100, "end");
        currentItem.setActiveIcon();
        endItem = currentItem;

//        drawArea.addMouseDownHandler(new MouseDownHandler() {
//
//            @Override
//            public void onMouseDown(MouseDownEvent event) {
//                int xx = drawContainer.getScrollLeft() + event.getX() - drawArea.getAbsoluteLeft();
//                int yy = drawContainer.getScrollTop() + event.getY() - drawArea.getAbsoluteTop();
//                ProcessDrawNode pdn = findInternalNode(xx, yy);
//                if (pdn != null) {
//                    connector = null;
//                    return;
//                }
//                if(isOnTheLine(xx,yy)){
//                    connector = null;
//                    return;
//                }
//                connector = new ProcessNodeConnector();
//                connector.setStartPoint(new Point(xx, yy));
//                connector.setEndPoint(new Point(xx, yy));
//                connector.setCanDrag(true);
//                drawArea.addDrawItem(connector, true);
//                pdn = findPositionNode(xx, yy);
//                if (pdn != null) {
//                    connector.setPreviousNode(pdn);
//                    pdn.getNextConnectors().add(connector);
//                }
//                drawArea.redraw();
//                drawArea.refreshNow();
//
//            }
//
//        });
//
//        drawArea.addMouseMoveHandler(new MouseMoveHandler() {
//
//            @Override
//            public void onMouseMove(MouseMoveEvent event) {
//                int xx = drawContainer.getScrollLeft() + event.getX() - drawArea.getAbsoluteLeft();
//                int yy = drawContainer.getScrollTop() + event.getY() - drawArea.getAbsoluteTop();
//                if (connector == null) {
//                    return;
//                }
//                connector.setEndPoint(new Point(xx, yy));
//                drawArea.redraw();
//                drawArea.refreshNow();
//            }
//
//        });
//
//        drawArea.addMouseUpHandler(new MouseUpHandler() {
//
//            @Override
//            public void onMouseUp(MouseUpEvent event) {
//                int xx = drawContainer.getScrollLeft() + event.getX() - drawArea.getAbsoluteLeft();
//                int yy = drawContainer.getScrollTop() + event.getY() - drawArea.getAbsoluteTop();
//                if (connector == null) {
//                    return;
//                }
//                connector.setEndPoint(new Point(xx, yy));
//                if (connector.getStartPoint().getX() == connector.getEndPoint().getX()
//                        && connector.getStartPoint().getY() == connector.getEndPoint().getY()) {
//                    connector.destroy();
//                    connector = null;
//                    return;
//                }
//                ProcessDrawNode pdn = findPositionNode(xx, yy);
//                if (pdn != null) {
//                    connector.setNextNode(pdn);
//                    pdn.getPreviousConnectors().add(connector);
//                }
//                drawArea.redraw();
//                drawArea.refreshNow();
//                if (connector.getPreviousNode() == null && connector.getNextNode() == null) {
//                    connector.destroy();
//                }
//                connector = null;
//            }
//
//        });
        ProcessNodeConnector currentPath = generateDrawPath(startItem, endItem);
        currentPath.setDrawPane(drawArea);
        currentPath.draw();

        VLayout controlLayout = new VLayout(20);
        controlLayout.setWidth(120);
        controlLayout.setHeight100();
        controlLayout.setBackgroundColor("#d3d3d3");
        controlLayout.setAlign(Alignment.CENTER);
        controlLayout.setAlign(VerticalAlignment.CENTER);
        controlLayout.setPadding(10);

        IButton drawLineButton = new IButton("画线");
        drawLineButton.setWidth(100);
        controlLayout.addMember(drawLineButton);

        drawLineButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                int xx1 = 0, yy1 = 0, xx2 = 0, yy2 = 0;
                xx1 = drawContainer.getScrollLeft() + drawContainer.getWidth() / 2 - 200;
                xx2 = drawContainer.getScrollLeft() + drawContainer.getWidth() / 2 + 200;
                yy1 = yy2 = 50;
                connector = new ProcessNodeConnector();
                connector.setStartPoint(new Point(xx1, yy1));
                connector.setEndPoint(new Point(xx2, yy2));
                connector.setCanDrag(true);
                drawArea.addDrawItem(connector, true);
            }
        });

        IButton zoomHomeButton = new IButton("还原");
        zoomHomeButton.setWidth(100);
        controlLayout.addMember(zoomHomeButton);

        zoomHomeButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                zoom = 1.0;
                drawArea.setZoomLevel(zoom);
            }
        });

        IButton zoomPlusButton = new IButton("放大");
        zoomPlusButton.setWidth(100);
        controlLayout.addMember(zoomPlusButton);

        zoomPlusButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                zoom += 0.1;
                drawArea.setZoomLevel(zoom);
            }
        });

        IButton zoomSmallButton = new IButton("缩小");
        zoomSmallButton.setWidth(100);
        controlLayout.addMember(zoomSmallButton);

        zoomSmallButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                zoom -= 0.1;
                drawArea.setZoomLevel(zoom);
            }
        });

        IButton removeButton = new IButton("重置");
        removeButton.setWidth(100);
        controlLayout.addMember(removeButton);

        removeButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                SC.confirm("确认", "重置将清空所有流程节点，你所定义的所有数据将被删除，确认要重置吗？", new BooleanCallback() {

                    @Override
                    public void execute(Boolean value) {
                        if (!value) {
                            return;
                        }
                        DrawItem[] items = drawArea.getDrawItems();
                        if (items != null && items.length > 0) {
                            for (DrawItem drawItem : items) {
                                drawItem.destroy();
                            }
                        }
                        generateDrawNode(10, 10, "start");
                        currentItem.setActiveIcon();
                        startItem = currentItem;

                        generateDrawNode(600, 100, "end");
                        currentItem.setActiveIcon();
                        endItem = currentItem;

                        ProcessNodeConnector currentPath = generateDrawPath(startItem, endItem);
                        currentPath.setDrawPane(drawArea);
                        currentPath.draw();
                        drawArea.redraw();
                        drawArea.refreshNow();
                    }
                });
            }
        });

        rightPanel.addMember(controlLayout);

        VStack pallete = new VStack();
        pallete.setWidth(80);
        pallete.setHeight100();
        pallete.setPadding(10);
        pallete.setMembersMargin(20);
        pallete.setBackgroundColor("#d3d3d3");

        pallete.setDragTarget(drawArea);
        pallete.setCanDrag(Boolean.TRUE);

        DragableImage approval = new DragableImage();
        pallete.addMember(approval);
        approval.setPrompt("拖动该图标增加审核人");

        approval.addDragStartHandler(new DragStartHandler() {
            @Override
            public void onDragStart(DragStartEvent event) {
                setNodeType(ProcessDrawNode.ACTIVITY_APPROVAL);
            }
        });

        DragableImage processor = new DragableImage();
        processor.setSrc("operator_add.png");
        pallete.addMember(processor);
        processor.setPrompt("拖动该图标增加处理人");

        processor.addDragStartHandler(new DragStartHandler() {
            @Override
            public void onDragStart(DragStartEvent event) {
                setNodeType(ProcessDrawNode.ACTIVITY_PROCESSOR);
            }
        });

        DragableImage attention = new DragableImage();
        attention.setSrc("insider_add.png");
        pallete.addMember(attention);
        attention.setPrompt("拖动该图标增加知会人");

        attention.addDragStartHandler(new DragStartHandler() {
            @Override
            public void onDragStart(DragStartEvent event) {
                setNodeType(ProcessDrawNode.ACTIVITY_ATTENTION);
            }
        });

        drawArea.addResizedHandler(new ResizedHandler() {
            @Override
            public void onResized(ResizedEvent event) {
                printPosition("New Draw Area width and height: ", drawArea.getWidth(), drawArea.getHeight());
                drawArea.setCanDragScroll(true);
            }

        });
        drawArea.addDropHandler(new DropHandler() {
            @Override
            public void onDrop(DropEvent event) {
                int x = drawContainer.getScrollLeft() + event.getX() - 86;
                int y = drawContainer.getScrollTop() + event.getY() - 32;

                int x1 = x - ProcessDrawNode.ICON_SIZE / 2;
                int x2 = x + ProcessDrawNode.ICON_SIZE / 2;
                int y1 = y - (ProcessDrawNode.ICON_SIZE + 24) / 2;
                int y2 = y + (ProcessDrawNode.ICON_SIZE + 24) / 2;
                final ProcessDrawNode node = generateDrawNode(x1, y1, getNodeType());
                boolean foundInsertPoint = false;
                ProcessNodeConnector suitLine = null;
                int currentValue = -1;
                for (DrawItem di : drawArea.getDrawItems()) {
                    if (di instanceof ProcessDrawNode) {
                        continue;
                    }
                    if (!(di instanceof ProcessNodeConnector)) {
                        continue;
                    }
                    ProcessNodeConnector pnc = (ProcessNodeConnector) di;
                    int refval = -1;
                    if ((refval = pnc.isOnTheLine(x1, y1, x2, y2)) > 0) {
                        foundInsertPoint = true;
                        if (currentValue == -1) {
                            currentValue = refval;
                            suitLine = pnc;
                        } else {
                            if (refval < currentValue) {
                                currentValue = refval;
                                suitLine = pnc;
                            }
                        }
                    }
                }
                if (foundInsertPoint) {
                    __logger.info("点在线上");
                    ProcessDrawNode preNode = suitLine.getPreviousNode();
                    ProcessDrawNode nextNode = suitLine.getNextNode();
                    nextNode.getPreviousConnectors().remove(suitLine);

                    ProcessNodeConnector newp = generateDrawPath(node, nextNode);
                    newp.setDrawPane(drawArea);
                    newp.draw();

                    suitLine.setNextNode(node);
                    suitLine.setEndPoint(node.getPreviousConnectPoint(suitLine.getPreviousNode()));

                    node.getPreviousConnectors().add(suitLine);
                    node.getNextConnectors().add(newp);

                    suitLine.calculatePoints();
                    newp.calculatePoints();
                } else {
                    __logger.info("点不在线上");
                    int mx = (x1 + x2) / 2;
                    int my = (y1 + y2) / 2;
                    double shortestDistance = Double.MAX_VALUE;
                    ProcessDrawNode aimNode = null;
                    for (DrawItem di : drawArea.getDrawItems()) {
                        if (di instanceof ProcessDrawNode) {
                            ProcessDrawNode pdn = (ProcessDrawNode) di;
                            if (pdn.getNodeType().equals("start") || pdn.getNodeType().equals("end")) {
                                continue;
                            }
                            if (pdn.equals(node)) {
                                continue;
                            }
                            int px1 = pdn.getLeft();
                            int py1 = pdn.getTop();
                            int px2 = pdn.getWidth() + px1;
                            int py2 = pdn.getHeight() + py1;

                            printPositions("New Node ", x1, y1, x2, y2);
                            printPositions(pdn.getNodeType() + " ", px1, py1, px2, py2);

                            if (((x1 >= px1 && x1 <= px2) || (x2 >= px1 && x2 <= px2)) && ((y1 >= py1 && y1 <= py2)
                                    || y1 - py2 <= 10 || (y2 >= py1 && y2 <= py2) || py1 - y2 <= 10)) {
                                int mpx = (px1 + px2) / 2;
                                int mpy = (py1 + py2) / 2;
                                //	计算2个点之间的距离
                                double pointDistance = Math.sqrt(Math.pow((mpx - mx), 2) + Math.pow(mpy - my, 2));
                                if (shortestDistance > pointDistance) {
                                    shortestDistance = pointDistance;
                                    aimNode = pdn;
                                }
                            }
                        }
                    }
                    if (null != aimNode) {
                        if (node.getNodeType().equals(ProcessDrawNode.ACTIVITY_ATTENTION)) {
                            ProcessNodeConnector preConnector = generateDrawPath(aimNode, node);
                            preConnector.setDrawPane(drawArea);
                            preConnector.draw();
                            preConnector.calculatePoints();
                        } else {
                            ProcessDrawNode preNode = null;
                            double distance = Double.MAX_VALUE;
                            //	获取与node距离最短的前置节点
                            for (ProcessNodeConnector nc : aimNode.getPreviousConnectors()) {
                                ProcessDrawNode tempNode = nc.getPreviousNode();
                                double tempDistance = Math.sqrt(Math.pow(tempNode.getLeft() + tempNode.getWidth() / 2 - mx, 2) + Math.pow(tempNode.getTop() + tempNode.getHeight() / 2 - my, 2));
                                if (distance > tempDistance) {
                                    distance = tempDistance;
                                    preNode = tempNode;
                                }
                            }
                            ProcessDrawNode nextNode = null;
                            distance = Double.MAX_VALUE;
                            //	获取与node距离最短的后置节点（知会节点除外）
                            for (ProcessNodeConnector nc : aimNode.getNextConnectors()) {
                                if (nc.getNextNode().getNodeType().equals(ProcessDrawNode.ACTIVITY_ATTENTION)) {
                                    continue;
                                }
                                ProcessDrawNode tempNode = nc.getNextNode();
                                double tempDistance = Math.sqrt(Math.pow(tempNode.getLeft() + tempNode.getWidth() / 2 - mx, 2) + Math.pow(tempNode.getTop() + tempNode.getHeight() / 2 - my, 2));
                                if (distance > tempDistance) {
                                    distance = tempDistance;
                                    nextNode = tempNode;
                                }
                            }
                            if (null != preNode) {
                                ProcessNodeConnector preConnector = generateDrawPath(preNode, node);
                                preConnector.setDrawPane(drawArea);
                                preConnector.draw();
                                preConnector.calculatePoints();
                            }
                            if (null != nextNode) {
                                ProcessNodeConnector nextConnector = generateDrawPath(node, nextNode);
                                nextConnector.setDrawPane(drawArea);
                                nextConnector.draw();
                                nextConnector.calculatePoints();
                            }
                        }
                    }
                }
            }
        });

        approval.addDoubleClickHandler(new DoubleClickHandler() {
            @Override
            public void onDoubleClick(DoubleClickEvent event) {
                DrawItem[] items = drawArea.getDrawItems();
                int X = 10, Y = 10;
                if (items != null && items.length > 0) {
                    X = Y = 0;
                    for (DrawItem di : items) {
                        int[] xys = di.getBoundingBox();
                        if (xys[0] > X) {
                            X = xys[0] + 20;
                        }
                        if (xys[1] > Y) {
                            Y = xys[1] + 20;
                        }
                    }
                }
                generateDrawNode(X, Y, "approval");
            }

        });

        HLayout mainLayout = new HLayout();
        mainLayout.setWidth100();
        mainLayout.setHeight100();
        mainLayout.setPadding(5);

        mainLayout.addMembers(pallete, rightPanel);

        addMember(mainLayout);
    }

    public ProcessDrawNode findPositionNode(int x, int y) {
        DrawPane pane = drawArea;
        if (pane == null) {
            return null;
        }
        for (DrawItem di : pane.getDrawItems()) {
            if (di instanceof ProcessDrawNode) {
                ProcessDrawNode pdn = (ProcessDrawNode) di;
                int px1 = pdn.getLeft() - 10;
                int py1 = pdn.getTop() - 10;
                int px2 = pdn.getWidth() + px1 + 10;
                int py2 = pdn.getHeight() + py1 + 10;
                if (x >= px1 && x <= px2 && y >= py1 && y <= py2) {
                    return pdn;
                }

            }
        }
        return null;
    }

    public boolean isOnTheLine(int x, int y) {
        DrawPane pane = drawArea;
        if (pane == null) {
            return false;
        }
        for (DrawItem di : pane.getDrawItems()) {
            if (di instanceof ProcessNodeConnector) {
                ProcessNodeConnector pdn = (ProcessNodeConnector) di;
                if (pdn.isThePointOnTheLine(x, y)) {
                    return true;
                }
            }
        }
        return false;
    }

    public ProcessDrawNode findInternalNode(int x, int y) {
        DrawPane pane = drawArea;
        if (pane == null) {
            return null;
        }
        for (DrawItem di : pane.getDrawItems()) {
            if (di instanceof ProcessDrawNode) {
                ProcessDrawNode pdn = (ProcessDrawNode) di;
                int px1 = pdn.getLeft();
                int py1 = pdn.getTop();
                int px2 = pdn.getWidth() + px1;
                int py2 = pdn.getHeight() + py1;
                if (x >= px1 && x <= px2 && y >= py1 && y <= py2) {
                    return pdn;
                }

            }
        }
        return null;
    }

    private void printPosition(String name, int x, int y) {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(" ");
        sb.append("x = ");
        sb.append(x);
        sb.append(", y = ");
        sb.append(y);
        __logger.info(sb.toString());
    }

    private void printPositions(String name, int x1, int y1, int x2, int y2) {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append("Positions : ");
        sb.append(x1);
        sb.append(' ');
        sb.append(y1);
        sb.append(' ');
        sb.append(x2);
        sb.append(' ');
        sb.append(y2);
        sb.append(' ');
        __logger.info(sb.toString());
    }

    private void relocateItems(int w, int h) {
        double rateX = w / originalWidth;
        double rateY = h / originalHeight;

        for (DrawItem i : drawArea.getDrawItems()) {
            if (i instanceof ProcessDrawNode) {
                int nx = (int) Math.round(rateX * ((ProcessDrawNode) i).getLeft());
                int ny = (int) Math.round(rateY * ((ProcessDrawNode) i).getTop());
                i.moveTo(nx, ny);
            }
        }
        for (DrawItem i : drawArea.getDrawItems()) {
            if (i instanceof ProcessNodeConnector) {
                ProcessNodeConnector pc = (ProcessNodeConnector) i;
                pc.setStartPoint(pc.getPreviousNode().getNextConnectPoint(pc.getNextNode()));
                pc.setEndPoint(pc.getNextNode().getPreviousConnectPoint(pc.getPreviousNode()));
            }
        }
    }

    private void recalculateDrawArea(int x, int y) {
        ProcessDrawNode.recalculateArea(drawArea, x, y);
    }

    // 构建一个新的流程处理节点
    private ProcessDrawNode generateDrawNode(int X, int Y, String dragType) {
        printPosition("Draw Area width and height: ", drawArea.getWidth(), drawArea.getHeight());
        originalWidth = drawArea.getWidth();
        originalHeight = drawArea.getHeight();
        recalculateDrawArea(X, Y);
        // if (X > drawArea.getWidth()) {
        // X = drawArea.getWidth() - 10;
        // }
        // if (Y > drawArea.getHeight()) {
        // Y = drawArea.getHeight() - 10;
        // }

        final ProcessDrawNode approval1 = new ProcessDrawNode(X, Y);

        approval1.setWidth(ProcessDrawNode.ICON_SIZE);
        approval1.setHeight(ProcessDrawNode.ICON_SIZE + 24);
        approval1.setCanDrag(true);
        approval1.setFillColor("blue");

        approval1.setNodeType(dragType);
        switch (dragType) {
            case ProcessDrawNode.ACTIVITY_APPROVAL:
                approval1.setContent("审核人");
                break;
            case ProcessDrawNode.ACTIVITY_START:
                approval1.setContent("发起人");
                break;
            case ProcessDrawNode.ACTIVITY_END:
                approval1.setContent("结束");
                break;
            case ProcessDrawNode.ACTIVITY_PROCESSOR:
                approval1.setContent("处理人");
                break;
            case ProcessDrawNode.ACTIVITY_ATTENTION:
                approval1.setContent("知会人");
                Record data = new Record();
                data.setAttribute("ifAllowOver", true);
                approval1.setData(data);
                break;
        }
        approval1.setTitleLabelPadding(10);
        approval1.addClickHandler(new com.smartgwt.client.widgets.drawing.events.ClickHandler() {
            @Override
            public void onClick(com.smartgwt.client.widgets.drawing.events.ClickEvent event) {
                currentItem = approval1;
            }

        });
        approval1.addDragStartHandler(new com.smartgwt.client.widgets.drawing.events.DragStartHandler() {
            @Override
            public void onDragStart(DragStart event) {
                currentItem = approval1;
            }

        });
        currentItem = approval1;
        drawArea.addDrawItem(approval1, true);
        currentItem.repaintOtherItems();
        drawArea.refreshNow();
        return approval1;
    }

    public ProcessNodeConnector generateDrawPath(ProcessDrawNode start, ProcessDrawNode end) {
        ProcessNodeConnector path = new ProcessNodeConnector();
        path.setStartPoint(start.getNextConnectPoint(end));
        path.setEndPoint(end.getPreviousConnectPoint(start));
        path.setEndArrow(ArrowStyle.BLOCK);
        path.setPreviousNode(start);
        path.setNextNode(end);
        path.setCanDrag(true);
        if (start.getNodeType().equals(ProcessDrawNode.ACTIVITY_ATTENTION)
                || end.getNodeType().equals(ProcessDrawNode.ACTIVITY_ATTENTION)) {
            path.setLinePattern(LinePattern.DASH);
        }
        start.getNextConnectors().add(path);
        end.getPreviousConnectors().add(path);
        return path;
    }

    @Override
    public void startEdit() {
        if (getPageMode() != PAGE_MODE_UPDATE) {
            return;
        }
        reloadDetailTableData();
    }

    public void reloadDetailTableData() {
        Map condition = new HashMap();
        condition.put("processId", getRecord().getAttribute("processId"));
        DBDataSource.callOperation("ST_SystemProcessWithSs", "find", condition, new DSCallback() {
            @Override
            public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
                if (dsResponse.getStatus() >= 0) {
                    if (dsResponse.getData() == null || dsResponse.getData().length == 0) {
                        return;
                    }
                    Record r = dsResponse.getData()[0];
                    Record[] activities = r.getAttributeAsRecordArray(ProcessDrawNode.DETAIL_ACTIVITY);
                    Record[] links = r.getAttributeAsRecordArray(ProcessDrawNode.DETAIL_LINK);
                    drawArea.destroyItems();
                    for (Record r1 : activities) {
                        int x = r1.getAttributeAsInt("posx");
                        int y = r1.getAttributeAsInt("posy");
                        recalculateDrawArea(x, y);
                        int actionType = r1.getAttributeAsInt(ProcessDrawNode.ACTIVITY_TYPE);
                        int serialNo = r1.getAttributeAsInt(ProcessDrawNode.ACTIVITY_SERIAL_NO);
                        String activityName = r1.getAttribute("activityName");
                        String nodeType = ProcessDrawNode.ACTIVITY_APPROVAL;
                        switch (actionType) {
                            case 0:
                                nodeType = ProcessDrawNode.ACTIVITY_START;
                                break;
                            case 1:
                                nodeType = ProcessDrawNode.ACTIVITY_END;
                                break;
                            case 2:
                                nodeType = ProcessDrawNode.ACTIVITY_APPROVAL;
                                break;
                            case 3:
                                nodeType = ProcessDrawNode.ACTIVITY_PROCESSOR;
                                break;
                            case 4:
                                nodeType = ProcessDrawNode.ACTIVITY_ATTENTION;
                                break;
                        }
                        ProcessDrawNode node = generateDrawNode(x, y, nodeType);
                        node.setActivitySerialNo(serialNo);
                        node.setData(r1);
                        if (activityName != null && !activityName.isEmpty()) {
                            node.setContent(activityName);
                        }
                    }
                    for (Record r2 : links) {
                        int n1 = r2.getAttributeAsInt("processActivityId");
                        int n2 = r2.getAttributeAsInt("toProcessActivityId");
                        String con = r2.getAttribute("condition");
                        ProcessDrawNode node1 = findNodeBySerialNo(n1);
                        ProcessDrawNode node2 = findNodeBySerialNo(n2);
                        ProcessNodeConnector c = generateDrawPath(node1, node2);
                        if (con != null && !con.isEmpty()) {
                            c.setTitle(con);
                        }
                        c.setData(r2);
                        c.setDrawPane(drawArea);
                        c.draw();
                    }
                }
            }
        });
    }

    public void clearDrawPane() {
        for (DrawItem i : drawArea.getDrawItems()) {
            i.erase();
        }
    }

    public ProcessDrawNode findNodeBySerialNo(int no) {
        for (DrawItem i : drawArea.getDrawItems()) {
            if (i instanceof ProcessDrawNode) {
                ProcessDrawNode n = (ProcessDrawNode) i;
                if (no == n.getActivitySerialNo()) {
                    return n;
                }

            }
        }
        return null;
    }

    @Override
    public boolean checkData() {
        return true;
    }

    @Override
    public Set<String> getItemNames() {
        Set<String> res = new HashSet<>();
        res.add("detailSystemProcessActivity");
        return res;
    }

    @Override
    public Map getValuesAsMap() {
        List<ProcessDrawNode> nodes = new ArrayList<>();
        List<ProcessNodeConnector> connects = new ArrayList<>();
        List<Map> activities = new ArrayList<>();
        List<Map> links = new ArrayList<>();
        DrawItem[] items = drawArea.getDrawItems();
        for (DrawItem i : items) {
            if (i instanceof ProcessDrawNode) {
                nodes.add((ProcessDrawNode) i);
            }
            if (i instanceof ProcessNodeConnector) {
                connects.add((ProcessNodeConnector) i);
            }
        }
        int count = 1;
        int serialNo = count;
        for (ProcessDrawNode n : nodes) {
            if (n.getData() == null) {
                continue;
            }
            switch (n.getNodeType()) {
                case ProcessDrawNode.ACTIVITY_START:
                    n.getData().setAttribute(ProcessDrawNode.ACTIVITY_TYPE, 0);
                    n.getData().setAttribute(ProcessDrawNode.ACTIVITY_SERIAL_NO, 0);
                    break;
                case ProcessDrawNode.ACTIVITY_END:
                    n.getData().setAttribute(ProcessDrawNode.ACTIVITY_TYPE, 1);
                    n.getData().setAttribute(ProcessDrawNode.ACTIVITY_SERIAL_NO, 999);
                    break;
                case ProcessDrawNode.ACTIVITY_APPROVAL:
                    n.getData().setAttribute(ProcessDrawNode.ACTIVITY_TYPE, 2);
                    serialNo = MapUtils.getValueAsInt(n.getData(), ProcessDrawNode.ACTIVITY_SERIAL_NO);
                    if (serialNo > count) {
                        count = serialNo;
                    }
                    break;
                case ProcessDrawNode.ACTIVITY_PROCESSOR:
                    n.getData().setAttribute(ProcessDrawNode.ACTIVITY_TYPE, 3);
                    serialNo = MapUtils.getValueAsInt(n.getData(), ProcessDrawNode.ACTIVITY_SERIAL_NO);
                    if (serialNo > count) {
                        count = serialNo;
                    }
                    break;
                case ProcessDrawNode.ACTIVITY_ATTENTION:
                    n.getData().setAttribute(ProcessDrawNode.ACTIVITY_TYPE, 4);
                    serialNo = MapUtils.getValueAsInt(n.getData(), ProcessDrawNode.ACTIVITY_SERIAL_NO);
                    if (serialNo > count) {
                        count = serialNo;
                    }
                    break;
            }
        }
        count++;
        for (ProcessDrawNode n : nodes) {
            if (n.getData() == null) {
                continue;
            }
            switch (n.getNodeType()) {
                case ProcessDrawNode.ACTIVITY_START:
                case ProcessDrawNode.ACTIVITY_END:
                    break;
                default:
                    serialNo = MapUtils.getValueAsInt(n.getData(), ProcessDrawNode.ACTIVITY_SERIAL_NO);
                    if (serialNo == 0) {
                        n.getData().setAttribute(ProcessDrawNode.ACTIVITY_SERIAL_NO, count++);
                    }
                    break;
            }

            n.getData().setAttribute("posx", n.getLeft());
            n.getData().setAttribute("posy", n.getTop());
            activities.add(n.getData().toMap());
        }
        for (ProcessNodeConnector pc : connects) {
            Record r = pc.getData();
            r.setAttribute("processActivityId",
                    pc.getPreviousNode().getData().getAttributeAsInt(ProcessDrawNode.ACTIVITY_SERIAL_NO));
            r.setAttribute("toProcessActivityId",
                    pc.getNextNode().getData().getAttributeAsInt(ProcessDrawNode.ACTIVITY_SERIAL_NO));
            links.add(r.toMap());
        }
        Map param = new HashMap();
        param.put(ProcessDrawNode.DETAIL_ACTIVITY, activities);
        param.put(ProcessDrawNode.DETAIL_LINK, links);
        __logger.info(param.toString());
        return param;
    }

    @Override
    public String getName() {
        return "定义流程图";
    }

    /**
     * @return the drawArea
     */
    public DrawPane getDrawArea() {
        return drawArea;
    }

    /**
     * @param drawArea the drawArea to set
     */
    public void setDrawArea(DrawPane drawArea) {
        this.drawArea = drawArea;
    }

    /**
     * @return the currentItem
     */
    public ProcessDrawNode getCurrentItem() {
        return currentItem;
    }

    /**
     * @param currentItem the currentItem to set
     */
    public void setCurrentItem(ProcessDrawNode currentItem) {
        this.currentItem = currentItem;
    }

    /**
     * @return the startItem
     */
    public ProcessDrawNode getStartItem() {
        return startItem;
    }

    /**
     * @param startItem the startItem to set
     */
    public void setStartItem(ProcessDrawNode startItem) {
        this.startItem = startItem;
    }

    /**
     * @return the endItem
     */
    public ProcessDrawNode getEndItem() {
        return endItem;
    }

    /**
     * @param endItem the endItem to set
     */
    public void setEndItem(ProcessDrawNode endItem) {
        this.endItem = endItem;
    }

    /**
     * @return the nodeType
     */
    public String getNodeType() {
        return nodeType;
    }

    /**
     * @param nodeType the nodeType to set
     */
    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

}
