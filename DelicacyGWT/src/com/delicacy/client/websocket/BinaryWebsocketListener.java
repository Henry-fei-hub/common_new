package com.delicacy.client.websocket;

public interface BinaryWebsocketListener extends WebsocketListener {

	void onMessage(byte[] bytes);
}
