package com.delicacy.client.data;

import java.util.HashSet;
import java.util.Set;

import com.google.gwt.core.client.JavaScriptObject;

public class WebWorker {

	private static native boolean _isWebWorker() /*-{
		return ("Worker" in window);
	}-*/;

	public static boolean isSupported() {
		return _isWebWorker();
	}

	private final Set<WebWorkerListener> listeners = new HashSet<WebWorkerListener>();

	private final String url;

	public WebWorker(String url) {
		this.url = url;
	}

	
	private native JavaScriptObject _create(WebWorker ww, String url) /*-{
		var worker = new Worker(url);
		
		worker.onmessage = function(msg) {
			ww.@com.delicacy.client.data.WebWorker::onMessage(Ljava/lang/String;)(msg.data);
		}
		return worker;
	}-*/;
	
	public native void _postMessage(String msg) /*-{
		this.@com.delicacy.client.data.WebWorker::jsWebworker.postMessage(msg);
	}-*/;
	 
	public void addListener(WebWorkerListener listener) {
		listeners.add(listener);
	}


	protected void onMessage(String msg) {
		for (WebWorkerListener listener : listeners) {
			listener.onMessage(msg);
		}
	}

	
	public void create() {
		jsWebworker = _create(this, url);
	}
	
	
	public void postMessage(String msg){
		_postMessage(msg);
	}

	

	private JavaScriptObject jsWebworker;
}
