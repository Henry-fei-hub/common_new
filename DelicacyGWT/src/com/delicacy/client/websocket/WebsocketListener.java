package com.delicacy.client.websocket;

public interface WebsocketListener {

	void onClose();

	void onMessage(String msg);

	void onOpen();
	
	void onBinaryMessage(byte[] msg);
}
