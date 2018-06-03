package com.manzurola.prodigy.statics;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.net.URI;
import java.net.URL;

@Configuration
@PropertySource({"classpath:application-${prodigyEnvTarget}.properties"})
public class Application {

    @Value("${jetty.port}")
    private Integer jettyPort;

    @Value("${jetty.host}")
    private String jettyHost;

    @Bean
    public Server server() throws Exception {
        URL url = Application.class.getClassLoader().getResource("html/");
        URI webRootUri = url.toURI();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        context.setBaseResource(Resource.newResource(webRootUri));
        context.setWelcomeFiles(new String[]{"index.html"});

        ServletHolder jerseyServlet = new ServletHolder("default", DefaultServlet.class);
        jerseyServlet.setInitParameter("dirAllowed", "true");

        context.addServlet(jerseyServlet, "/");

        Server server = new Server(jettyPort);
        server.setHandler(context);

        return server;
    }

    public void run() throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        Server server = context.getBean(Server.class);

        try {
            server.start();
            server.join();
        } finally {
            server.destroy();
        }
    }

    public static void main(String[] args) throws Exception {
        Application application = new Application();
        application.run();
    }

}
