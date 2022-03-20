package com.zube;

import com.zube.context.MyBankApplicationConfiguration;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;

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

        Context tomcatCtx = tomcat.addContext("", null);
        WebApplicationContext webApplicationContext = createWebApplicationContext(tomcatCtx.getServletContext());

        DispatcherServlet dispatcherServlet = new DispatcherServlet(webApplicationContext);
        Wrapper servlet = Tomcat.addServlet(tomcatCtx, "dispatcherServlet", dispatcherServlet);

        servlet.setLoadOnStartup(1);
        servlet.addMapping("/*");//bilo koja putanja

        tomcat.start();
    }

    public static WebApplicationContext createWebApplicationContext(ServletContext servletContext) {

        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(MyBankApplicationConfiguration.class);
        ctx.setServletContext(servletContext);
        ctx.refresh();
        ctx.registerShutdownHook();

        return ctx;
    }
}
