package com.tianjunwei.nettyrpc;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class RPCProxy {
		
	@SuppressWarnings("unchecked")
	public static <T> T create(Object target){
			 
		return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(), new InvocationHandler(){

			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
						throws Throwable {
				// Configure the client.  
		        EventLoopGroup group = new NioEventLoopGroup();  
		        try {  
		            Bootstrap b = new Bootstrap();  
		            b.group(group)  
		             .channel(NioSocketChannel.class)  
		             .option(ChannelOption.TCP_NODELAY, true)  
		             .handler(new ChannelInitializer<SocketChannel>() {  
		                 @Override  
		                 public void initChannel(SocketChannel ch) throws Exception {  
		                     ChannelPipeline p = ch.pipeline();  
		                     p.addLast("decoder", new StringDecoder());  
		                     p.addLast("encoder", new StringEncoder());  
		                 }  
		             });  
		  
		            ChannelFuture future = b.connect("localhost", 8080).sync();  
		            future.channel().writeAndFlush("Hello Netty Server ,I am a common client");
		            future.channel().closeFuture().sync();  
		        } finally {  
		            group.shutdownGracefully();  
		        }
		        return args;
			}
		});
	}
}
