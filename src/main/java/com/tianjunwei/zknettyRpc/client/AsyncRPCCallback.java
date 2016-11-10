package com.tianjunwei.zknettyRpc.client;

public interface AsyncRPCCallback {

    void success(Object result);

    void fail(Exception e);

}
