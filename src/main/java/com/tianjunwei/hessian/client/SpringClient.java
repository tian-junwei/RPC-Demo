package com.tianjunwei.hessian.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tianjunwei.hessian.server.HelloService;

public class SpringClient {

	    public static void main(String[] args) {  
	        ApplicationContext contex = new ClassPathXmlApplicationContext(  
	                "applicationContext.xml");  
	        // 获得客户端的Hessian代理工厂bean  
	        HelloService helloService = (HelloService) contex.getBean("clientSpring");  
	        System.out.println(helloService.helloWorld("world"));  
	    }  
}
