package com.manzurola.questions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manzurola.questions.data.ElasticsearchRepository;
import com.manzurola.questions.data.Repository;
import com.manzurola.questions.rest.api.v1.QuestionController;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.net.InetAddress;
import java.net.UnknownHostException;

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

    public static void main(String[] args) throws Exception {
        Application application = new Application();
        application.run();
    }

    @Bean
    public Repository repository() throws Exception {
        return new ElasticsearchRepository(transportClient(), objectMapper(), elasticsearchIndex);
    }

    @Bean
    public TransportClient transportClient() throws UnknownHostException {
        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch_guym").build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));

        return client;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public QuestionController questionContoller() throws Exception {
        return new QuestionController(repository());
    }

    @Bean
    public Server server() throws Exception {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        Server server = new Server(jettyPort);
        server.setHandler(context);

        ResourceConfig config = new ResourceConfig();
        config.register(JacksonFeature.class);
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
