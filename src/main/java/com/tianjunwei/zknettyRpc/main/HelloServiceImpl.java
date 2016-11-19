package com.tianjunwei.zknettyRpc.main;

import com.tianjunwei.zknettyRpc.server.RpcService;

@RpcService(HelloService.class)
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        return "Hello! " + name;
    }
   
}
