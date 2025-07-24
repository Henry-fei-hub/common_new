package com.delicacy.client.ui;

import com.smartgwt.client.types.Cursor;
import com.smartgwt.client.types.DragAppearance;
import com.smartgwt.client.util.EventHandler;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Img;

/**
 *
 * @author guangxun
 */
public class DragableImage extends Img {

    public DragableImage() {
        setWidth(ProcessDrawNode.ICON_SIZE);
        setHeight(ProcessDrawNode.ICON_SIZE);
        setSrc("auditor_add.png");
        setCursor(Cursor.MOVE);
        setCanDrag(true);
        setCanDrop(true);
        setDragType("approval");
        setDragAppearance(DragAppearance.TRACKER);
    }

    @Override
    protected boolean setDragTracker() {
        EventHandler.setDragTracker(Canvas.imgHTML(getSrc(), ProcessDrawNode.ICON_SIZE, ProcessDrawNode.ICON_SIZE), ProcessDrawNode.ICON_SIZE, ProcessDrawNode.ICON_SIZE, 15, 15);
        return false;
    }

}
