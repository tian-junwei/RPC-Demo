package com.tianjunwei.hessian.client;

import java.net.MalformedURLException;

import com.caucho.hessian.client.HessianProxyFactory;
import com.tianjunwei.hessian.server.HelloService;

public class HelloServiceMain {

	public static void main(String [] args) throws MalformedURLException{
		String url = "http://localhost:8080/hessian";
	    System.out.println(url);
	    HessianProxyFactory factory = new HessianProxyFactory();
	    HelloService helloService = (HelloService) factory.create(HelloService.class, url);
	    System.out.println(helloService.helloWorld("jimmy"));
	}
	
}
