package com.manzurola.questions.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;

/**
 * Created by guym on 17/05/2017.
 */
@Configuration
public class Application {

//    @Bean
//    public QuestionService questionService() throws Exception {
//        return new QuestionServiceImpl(elasticsearchService());
//    }

//    @Bean
//    public ElasticsearchService elasticsearchService() throws Exception {
//        Settings settings = Settings.builder()
//                .put("cluster.name", "elasticsearch_guym").build();
//        TransportClient client = new PreBuiltTransportClient(settings)
//                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
//
//        return new ElasticsearchServiceImpl(client, objectMapper());
//    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public QuestionContoller questionContoller() {
        return new QuestionContoller();
    }

    public static void main(String[] args) throws Exception {
        new Application().run();
    }

    public void run() throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        runJetty(Arrays.asList(context.getBean(QuestionContoller.class)));
    }

    private void runJetty(List controllers) throws Exception {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        Server jettyServer = new Server(8888);
        jettyServer.setHandler(context);

        ResourceConfig config = new ResourceConfig();
        for (Object controller : controllers) {
            config.register(controller);
        }

        ServletHolder jerseyServlet = new ServletHolder(new ServletContainer(config));
        context.addServlet(jerseyServlet, "/*");

//        ServletHolder jerseyServlet = context.addServlet(
//                org.glassfish.jersey.servlet.ServletContainer.class, "/*");
//        jerseyServlet.setInitOrder(0);

        try {
            jettyServer.start();
            jettyServer.join();
        } finally {
            jettyServer.destroy();
        }
    }
}
