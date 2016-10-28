package com.tianjunwei.socketrpc;

import com.tianjunwei.socketrpc.hello.HelloRpc;
import com.tianjunwei.socketrpc.hello.HelloRpcImpl;

public class Main {

	public static void main(String [] args){
		HelloRpc helloRpc = new HelloRpcImpl();
		helloRpc = RPCProxy.create(helloRpc);
		System.err.println(helloRpc.hello("rpc"));
	}
}
