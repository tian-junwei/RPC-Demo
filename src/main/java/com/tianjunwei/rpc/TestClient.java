package com.tianjunwei.rpc;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class TestClient {

	 /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            //配置客户端
            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
            //设置服务器端地址
            config.setServerURL(new URL("http://localhost:8080/HelloHandler"));
            //创建XmlRpc客户端
            XmlRpcClient client = new XmlRpcClient();
            //绑定以上设置
            client.setConfig(config);
            //创建参数列表
            Vector<String> params = new Vector<String>();
            params.addElement("flyoung");
            //执行XML-RPC 请求
            String result =(String) client.execute("HelloHandler.execute", params);
            System.out.println("result:"+result);
           } catch (MalformedURLException e) {
            e.printStackTrace();
            } catch (XmlRpcException e) {
            e.printStackTrace();
        }
    }
}
