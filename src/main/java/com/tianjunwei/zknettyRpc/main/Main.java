/**
 *    Copyright  2016  tianjunwei
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.tianjunwei.zknettyRpc.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tianjunwei.zknettyRpc.client.RpcClient;
import com.tianjunwei.zknettyRpc.client.RpcClientHandler;

/**
 * @author tianjunwei
 * @time 2016 上午11:20:36
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("client-spring.xml");
		RpcClient rpcClient = (RpcClient) applicationContext.getBean("rpcClient");
		@SuppressWarnings("static-access")
		HelloService helloService = rpcClient.create(HelloService.class);
	    String result = helloService.hello("World");
	}

}
