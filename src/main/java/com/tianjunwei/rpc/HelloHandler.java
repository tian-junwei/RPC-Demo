package com.tianjunwei.rpc;

public class HelloHandler implements ServicesHandler{

	@Override
	public String execute(String str) {
		return "hello "+str+"!";
	}

}
