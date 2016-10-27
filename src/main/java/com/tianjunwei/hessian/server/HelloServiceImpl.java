package com.tianjunwei.hessian.server;

public class HelloServiceImpl implements HelloService{

	@Override
	public String helloWorld(String message) {
		return "hello," + message;
	}
}
