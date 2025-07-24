package com.delicacy.client.websocket;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import java.util.HashSet;
import java.util.Set;

public class Websocket {

	private static native boolean _isWebsocket() /*-{
		return ("WebSocket" in window);
	}-*/;

	public static boolean isSupported() {
		return _isWebsocket();
	}

	private final Set<WebsocketListener> listeners = new HashSet<WebsocketListener>();

	private final String url;

	public Websocket(String url) {
		this.url = url;
	}

	public native void _close() /*-{
		 this.@com.delicacy.client.websocket.Websocket::jsWebsocket.close();
	}-*/;

	private native JavaScriptObject _open(Websocket ws, String url) /*-{
		var socket = new WebSocket(url);
		socket.onopen = function() { ws.@com.delicacy.client.websocket.Websocket::onOpen()(); };
		socket.onclose = function() { ws.@com.delicacy.client.websocket.Websocket::onClose()(); };
		socket.onerror = function() { ws.@com.delicacy.client.websocket.Websocket::onError()(); };
		socket.onmessage = function(msg) {
			ws.@com.delicacy.client.websocket.Websocket::onMessage(Ljava/lang/String;)(msg.data);
		}
		return socket;
	 }-*/;
	
//	private native JavaScriptObject _open(Websocket ws, String url) /*-{
//		var socket = new WebSocket(url);
//		socket.onopen = function() { ws.@com.delicacy.client.websocket.Websocket::onOpen()(); };
//		socket.onclose = function() { ws.@com.delicacy.client.websocket.Websocket::onClose()(); };
//		socket.onerror = function() { ws.@com.delicacy.client.websocket.Websocket::onError()(); };
//		socket.onmessage = function(msg) {
//			if(msg.data instanceof ArrayBuffer){
//				var b = new Int8Array(msg.data);
//				ws.@com.delicacy.client.websocket.Websocket::onBinaryMessage([B)(b); 
//			}else if(msg.data instanceof Blob){
//				var reader = new FileReader();
//				reader.onload = function(evt){
//					var r = evt.target;
//					var ba = new Int8Array(r.result);
//					ws.@com.delicacy.client.websocket.Websocket::onBinaryMessage([B)(ba); 
//				}
//				reader.readAsArrayBuffer(msg.data);
//			}else {
//				ws.@com.delicacy.client.websocket.Websocket::onMessage(Ljava/lang/String;)(msg.data);
//			}
//		}
//		return socket;
//	 }-*/;

	public native void _send(String msg) /*-{
		this.@com.delicacy.client.websocket.Websocket::jsWebsocket.send(msg);
	 }-*/;
	
	public native void _send(Element ele) /*-{
		var file = ele.files[0];
		if(file)
			this.@com.delicacy.client.websocket.Websocket::jsWebsocket.send(file);
	 }-*/;
	
	public native void _send(JavaScriptObject obj) /*-{
		var file = obj.files[0];
		if(file)
			this.@com.delicacy.client.websocket.Websocket::jsWebsocket.send(file);
	 }-*/;
	
	public native void _send(byte[] msg, int length) /*-{
		var ab = new ArrayBuffer(length);
		var b = new Int8Array(ab);
		for(var i = 0; i < length; i ++) b[i] = msg[i];
		this.@com.delicacy.client.websocket.Websocket::jsWebsocket.send(ab);
	 }-*/;

	private native int _state() /*-{
		return this.@com.delicacy.client.websocket.Websocket::jsWebsocket.readyState;
	 }-*/;

	public native int getBufferedAmount()/*-{
		return this.@com.delicacy.client.websocket.Websocket::jsWebsocket.bufferedAmount;
	 }-*/;

	public native String getBinaryType()/*-{
		return this.@com.delicacy.client.websocket.Websocket::jsWebsocket.binaryType;
	 }-*/;

	public void addListener(WebsocketListener listener) {
		listeners.add(listener);
	}

	public void close() {
		_close();
	}

	public int getState() {
		return _state();
	}

	protected void onClose() {
		for (WebsocketListener listener : listeners) {
			listener.onClose();
		}
	}

	protected void onError() {
		for (WebsocketListener listener : listeners) {
			if (listener instanceof WebsocketListenerExt) {
				((WebsocketListenerExt) listener).onError();
			}
		}
	}

	protected void onMessage(String msg) {
		for (WebsocketListener listener : listeners) {
			listener.onMessage(msg);
		}
	}

	protected void onBinaryMessage(byte[] msg) {
		for (WebsocketListener listener : listeners) {
			listener.onBinaryMessage(msg);
		}
	}

	protected void onOpen() {
		for (WebsocketListener listener : listeners) {
			listener.onOpen();
		}
	}

	public void open() {
		jsWebsocket = _open(this, url);
	}

	public void send(String msg) {
		_send(msg);
	}

	public void send(byte[] bytes) {
		_send(bytes, bytes.length);
	}

	private JavaScriptObject jsWebsocket;
}
