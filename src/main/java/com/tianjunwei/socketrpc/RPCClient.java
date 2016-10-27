package com.tianjunwei.socketrpc;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class RPCClient {
	static Socket socket = null;
	
	static{
		try {
			socket= new Socket("localhost", 8080);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
