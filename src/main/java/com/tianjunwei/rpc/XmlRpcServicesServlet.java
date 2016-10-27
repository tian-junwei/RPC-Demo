package com.tianjunwei.rpc;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.XmlRpcServletServer;

public class XmlRpcServicesServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2994076601912002234L;
	
	private XmlRpcServletServer server;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            //创建XmlRpcServletServer对象
            server = new XmlRpcServletServer();
            
            //set up handler mapping of XmlRpcServletServer object
            PropertyHandlerMapping pmp = new PropertyHandlerMapping();
            pmp.addHandler("HelloHandler", HelloHandler.class);
            server.setHandlerMapping(pmp);
            
            //more config of XmlRpcServletServer object
            XmlRpcServerConfigImpl serverConfig = (XmlRpcServerConfigImpl)server.getConfig();
            serverConfig.setEnabledForExtensions(true);
            serverConfig.setContentLengthOptional(false);
        } catch (XmlRpcException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        server.execute(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        server.execute(req, resp);
    }

    
    
}
