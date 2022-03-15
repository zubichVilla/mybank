package com.zube;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import com.zube.web.BankServlet;

public class ApplicationLauncher {

    public static void main (String[] args) throws LifecycleException {

        String vmServerArgument = System.getProperty("server.port");
        Integer port;

        if(vmServerArgument == null){
            port = 8080;
        }else {
            port = Integer.parseInt(vmServerArgument);
        }

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);
        tomcat.getConnector();

        Context ctx = tomcat.addContext("", null);
        Wrapper servlet = Tomcat.addServlet(ctx, "myFirstServlet", new BankServlet());
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/*");//bilo koja putanja

        tomcat.start();
    }
}
