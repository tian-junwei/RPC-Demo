package com.tianjunwei.socketrpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class RPCServer {

	public static ConcurrentHashMap<String, Object> classMap = new ConcurrentHashMap<String,Object>();
	
	public static void invoker(int port) throws Exception{
		
		ServerSocket server = new ServerSocket(port);
		for(;;){
				try{
					final Socket socket = server.accept();
					ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());  
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							try{
								try {
									ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
									String className = input.readUTF();
									String methodName = input.readUTF();
									Class<?>[] parameterTypes = (Class<?>[])input.readObject();  
			                        Object[] arguments = (Object[])input.readObject();  
									Object claszz = null;
									if(!classMap.containsKey(className)){
										try {
											claszz = Class.forName(className).newInstance();
											classMap.put(className, claszz);
										} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
											e.printStackTrace();
										}
									}else {
										claszz = classMap.get(className);
									}
									Method method = claszz.getClass().getMethod(methodName, parameterTypes);  
			                        Object result = method.invoke(claszz, arguments);  
			                        output.writeObject(result);  
								} catch (IOException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
									output.writeObject(e);
								}finally {
									output.close();
									socket.close();
								}
							}catch(Exception e){
								e.printStackTrace();
							}
						}
					}).start();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
