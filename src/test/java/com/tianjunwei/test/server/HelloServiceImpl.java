package com.tianjunwei.test.server;

import com.tianjunwei.test.client.HelloService;
import com.tianjunwei.test.client.Person;
import com.tianjunwei.zknettyRpc.server.RpcService;

@RpcService(HelloService.class)
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        return "Hello! " + name;
    }

    @Override
    public String hello(Person person) {
        return "Hello! " + person.getFirstName() + " " + person.getLastName();
    }
}
