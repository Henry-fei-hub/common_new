package com.delicacy.client.data;

import com.google.gwt.event.shared.GwtEvent;
import com.smartgwt.client.data.Record;

public class DataEditEvent extends GwtEvent<DataEditedHandler> {

    public static Type<DataEditedHandler> TYPE = new Type<>();
    public static int DATAEDITTYPE_UPDATE = 0;
    public static int DATAEDITTYPE_ADD = 1;

    private int __editType = 0;
    private Record __data;

    public int getEditType() {
        return this.__editType;
    }

    public void setEditType(int value) {
        this.__editType = value;
    }

    public Record getData() {
        return this.__data;
    }

    public void setData(Record value) {
        this.__data = value;
    }

    @Override
    public Type<DataEditedHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(DataEditedHandler handler) {
        handler.onDataEdited(this);
    }

}
