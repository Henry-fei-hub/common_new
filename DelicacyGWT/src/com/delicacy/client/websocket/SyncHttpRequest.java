package com.delicacy.client.websocket;

import com.google.gwt.core.client.JavaScriptObject;

/**
 *
 * @author Administrator
 */
public class SyncHttpRequest {

    public final static String DEFAULTURL = "/DelicacyServlet";
    public final static String DEFAULTMETHOD = "POST";

    private String __requestData;

    public SyncHttpRequest() {

    }

    private native JavaScriptObject __createNewHttpRequest(String method, String url)/*-{
        var request = new XMLHttpRequest();
        request.open(method, url, false);
        return request;
    }-*/;

    public native void _send(String msg) /*-{
	this.@com.delicacy.client.websocket.SyncHttpRequest::xmlHttpRequest.send(msg);
    }-*/;

    public native String _getReponse() /*-{
	return this.@com.delicacy.client.websocket.SyncHttpRequest::xmlHttpRequest.responseText;
    }-*/;

    public native int _getStatus() /*-{
	return this.@com.delicacy.client.websocket.SyncHttpRequest::xmlHttpRequest.status;
    }-*/;

    public void open() {
        xmlHttpRequest = __createNewHttpRequest(DEFAULTMETHOD, DEFAULTURL);
    }

    public void send(String msg) {
        _send(msg);
    }
    
    public String getData(String msg){
        open();
        send(msg);
        if(_getStatus() == 200) return _getReponse();
        else
            return null;
    }

    private JavaScriptObject xmlHttpRequest;

    /**
     * @return the __requestData
     */
    public String getRequestData() {
        return __requestData;
    }

    /**
     * @param __requestData the __requestData to set
     */
    public void setRequestData(String __requestData) {
        this.__requestData = __requestData;
    }
}
