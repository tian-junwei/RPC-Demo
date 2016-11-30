package com.tianjunwei.test.app;

import com.tianjunwei.test.client.HelloService;
import com.tianjunwei.zknettyRpc.client.RpcClient;
import com.tianjunwei.zknettyRpc.registry.ServiceDiscovery;

/**
 * Created by luxiaoxun on 2016-03-11.
 */
public class Benchmark {

    public static void main(String[] args) throws InterruptedException {

        ServiceDiscovery serviceDiscovery = new ServiceDiscovery("127.0.0.1:2181");
        final RpcClient rpcClient = new RpcClient(serviceDiscovery);

        int threadNum = 10;
        final int requestNum = 100;
        Thread[] threads = new Thread[threadNum];

        long startTime = System.currentTimeMillis();
        //benchmark for sync call
        for(int i = 0; i < threadNum; ++i){
            threads[i] = new Thread(new Runnable(){
                @Override
                public void run() {
                    for (int i = 0; i < requestNum; i++) {
                        final HelloService syncClient = rpcClient.create(HelloService.class);
                        String result = syncClient.hello(Integer.toString(i));
                        if (!result.equals("Hello! " + i))
                            System.out.print("error = " + result);
                    }
                }
            });
            threads[i].start();
        }
        for(int i=0; i<threads.length;i++){
            threads[i].join();
        }
        long timeCost = (System.currentTimeMillis() - startTime);
        String msg = String.format("Sync call total-time-cost:%sms, req/s=%s",timeCost,((double)(requestNum * threadNum)) / timeCost * 1000);
        System.out.println(msg);

        rpcClient.stop();
    }
}
