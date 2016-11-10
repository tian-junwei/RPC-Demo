package com.tianjunwei.zknettyRpc.client.proxy;

import com.tianjunwei.zknettyRpc.client.RPCFuture;

/**
 * Created by luxiaoxun on 2016/3/16.
 */
public interface IAsyncObjectProxy {
    public RPCFuture call(String funcName, Object... args);
}