package com.manzurola.questions.filltheblanks;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manzurola.questions.filltheblanks.search.SearchClient;
import com.manzurola.questions.filltheblanks.rest.api.v1.QuestionController;
import com.manzurola.questions.filltheblanks.search.SearchClientImpl;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.net.InetAddress;

/**
 * Created by guym on 17/05/2017.
 */
@Configuration
@PropertySource("classpath:application.properties")
public class Application {

    @Value("${jetty.port}")
    private Integer jettyPort;

    @Value("${jetty.host}")
    private String jettyHost;

    @Value("${elasticsearch.index}")
    private String elasticsearchIndex;

    @Value("${elasticsearch.type}")
    private String elasticsearchType;

    public static void main(String[] args) throws Exception {
        Application application = new Application();
        application.run();
    }

    @Bean
    public QuestionService questionService() throws Exception {
        return new QuestionServiceImpl(elasticsearchService());
    }

    @Bean
    public SearchClient elasticsearchService() throws Exception {
        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch_guym").build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));

        return new SearchClientImpl(client, objectMapper(), elasticsearchIndex, elasticsearchType);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public QuestionController questionContoller() throws Exception {
        return new QuestionController(questionService());
    }

    @Bean
    public Server server() throws Exception {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        Server server = new Server(jettyPort);
        server.setHandler(context);

        ResourceConfig config = new ResourceConfig();
        config.register(questionContoller());

        ServletHolder jerseyServlet = new ServletHolder(new ServletContainer(config));
        context.addServlet(jerseyServlet, "/*");

        return server;
    }

    public void run() throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        Server server = context.getBean(Server.class);

        try {
            server.start();
            server.join();
        } finally {
            server.destroy();
        }
    }
}
