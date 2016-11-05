package com.tianjunwei.hessian.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

public class Main {

	public static void main(String[] args) throws IOException {
		
		String teString = " hello world";
		byte b[] = serialize(teString);
		
		teString = (String) deserialize(b);
		System.out.println(teString);
		
	}
	
	public static byte[] serialize(Object obj) throws IOException{
		if(obj==null) throw new NullPointerException();  
	    ByteArrayOutputStream os = new ByteArrayOutputStream();  
	    HessianOutput ho = new HessianOutput(os);  
	    ho.writeObject(obj);  
	    return os.toByteArray();
	}
	
	public static Object deserialize(byte[] by) throws IOException{  
	    if(by==null) throw new NullPointerException();  
	    ByteArrayInputStream is = new ByteArrayInputStream(by);  
	    HessianInput hi = new HessianInput(is);  
	    return hi.readObject();  
	} 

}
