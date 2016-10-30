package com.tianjunwei.nettyrpc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.tianjunwei.nettyrpc.entity.ClassInfo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;


public class RPCProxy {
		
	@SuppressWarnings("unchecked")
	public static <T> T create(Object target){
			 
		return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(), new InvocationHandler(){

			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
						throws Throwable {
				
				ClassInfo classInfo = new ClassInfo();
				classInfo.setClassName(target.getClass().getName());
				classInfo.setMethodName(method.getName());
				classInfo.setObjects(args);
				classInfo.setTypes(method.getParameterTypes());
				
				ResultHandler resultHandler = new ResultHandler();
		        EventLoopGroup group = new NioEventLoopGroup();  
		        try {  
		            Bootstrap b = new Bootstrap();  
		            b.group(group)  
		             .channel(NioSocketChannel.class)  
		             .option(ChannelOption.TCP_NODELAY, true)  
		             .handler(new ChannelInitializer<SocketChannel>() {  
		                 @Override  
		                 public void initChannel(SocketChannel ch) throws Exception {  
		                	 ChannelPipeline pipeline = ch.pipeline();  
                             pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));  
                             pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));  
                             pipeline.addLast("encoder", new ObjectEncoder());    
                             pipeline.addLast("decoder", new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));  
                             pipeline.addLast("handler",resultHandler);
		                 }  
		             });  
		  
		            ChannelFuture future = b.connect("localhost", 8080).sync();  
		            future.channel().writeAndFlush(classInfo).sync();
		            future.channel().closeFuture().sync();  
		        } finally {  
		            group.shutdownGracefully();  
		        }
		        return resultHandler.getResponse();
			}
		});
	}
}
