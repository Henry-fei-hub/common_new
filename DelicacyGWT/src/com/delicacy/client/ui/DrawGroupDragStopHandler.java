package com.delicacy.client.ui;

import com.smartgwt.client.widgets.drawing.DrawGroup;
import com.smartgwt.client.widgets.drawing.DrawPane;
import com.smartgwt.client.widgets.drawing.events.DragStop;
import com.smartgwt.client.widgets.drawing.events.DragStopHandler;

public class DrawGroupDragStopHandler implements DragStopHandler {

	@Override
	public void onDragStop(DragStop event) {
		DrawGroup source = (DrawGroup)event.getSource();
		DrawPane drawArea = source.getDrawPane();
		ProcessDrawNode.recalculateArea(drawArea, event.getX(), event.getY());
	}

}
