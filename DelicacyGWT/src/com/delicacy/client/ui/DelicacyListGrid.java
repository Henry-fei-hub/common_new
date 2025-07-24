package com.delicacy.client.ui;

import com.smartgwt.client.types.AutoFitWidthApproach;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.widgets.grid.ListGrid;

/**
 *
 * @author Administrator
 */
public class DelicacyListGrid extends ListGrid {

    public DelicacyListGrid() {
        this(false);
    }

    public DelicacyListGrid(boolean editable) {
        super();
        setWidth100();
        setHeight100();
        setAutoFetchData(false);
        setSaveLocally(true);
        setAutoFitWidthApproach(AutoFitWidthApproach.BOTH);
        setAutoFitFieldWidths(true);
        setShowHeaderContextMenu(false);
        setShowHeaderMenuButton(false);
        setShowEmptyMessage(false);
        this.setEmptyMessage("No data");
        this.setLoadingMessage("Loading ...");

//		setSelectionAppearance(SelectionAppearance.CHECKBOX);
        if (editable) {
            setCanEdit(true);
            setEditByCell(true);
            setEditEvent(ListGridEditEvent.DOUBLECLICK);
            setCanRemoveRecords(true);
        }
    }
}
