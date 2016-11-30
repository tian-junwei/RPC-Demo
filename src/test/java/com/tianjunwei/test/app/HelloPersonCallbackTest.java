package com.tianjunwei.test.app;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.tianjunwei.test.client.HelloPersonService;
import com.tianjunwei.test.client.Person;
import com.tianjunwei.zknettyRpc.client.AsyncRPCCallback;
import com.tianjunwei.zknettyRpc.client.RPCFuture;
import com.tianjunwei.zknettyRpc.client.RpcClient;
import com.tianjunwei.zknettyRpc.client.proxy.IAsyncObjectProxy;
import com.tianjunwei.zknettyRpc.registry.ServiceDiscovery;

/**
 * Created by luxiaoxun on 2016/3/17.
 */
public class HelloPersonCallbackTest {
    public static void main(String[] args) {
        ServiceDiscovery serviceDiscovery = new ServiceDiscovery("127.0.0.1:2181");
        final RpcClient rpcClient = new RpcClient(serviceDiscovery);
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        try {
            IAsyncObjectProxy client = rpcClient.createAsync(HelloPersonService.class);
            int num = 5;
            RPCFuture helloPersonFuture = client.call("GetTestPerson", "xiaoming", num);
            helloPersonFuture.addCallback(new AsyncRPCCallback() {
                @Override
                public void success(Object result) {
                    List<Person> persons = (List<Person>) result;
                    for (int i = 0; i < persons.size(); ++i) {
                        System.out.println(persons.get(i));
                    }
                    countDownLatch.countDown();
                }

                @Override
                public void fail(Exception e) {
                    System.out.println(e);
                    countDownLatch.countDown();
                }
            });

        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        rpcClient.stop();

        System.out.println("End");
    }
}
