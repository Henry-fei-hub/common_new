package com.delicacy.client.ui;

import com.smartgwt.client.widgets.drawing.DrawGroup;
import com.smartgwt.client.widgets.drawing.DrawPane;
import com.smartgwt.client.widgets.drawing.events.DragMove;
import com.smartgwt.client.widgets.drawing.events.DragMoveHandler;

public class DrawGroupDragMoveHandler implements DragMoveHandler {

	@Override
	public void onDragMove(DragMove event) {
		DrawGroup source = (DrawGroup)event.getSource();
		DrawPane drawArea = source.getDrawPane();
		ProcessDrawNode.recalculateArea(drawArea, event.getX(), event.getY());
	}

}
