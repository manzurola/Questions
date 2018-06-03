package com.manzurola.prodigy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manzurola.prodigy.questions.data.ElasticsearchQuestionRepository;
import com.manzurola.prodigy.questions.data.QuestionRepository;
import com.manzurola.prodigy.questions.rest.api.v1.QuestionController;
import com.manzurola.prodigy.users.data.MysqlUserRepository;
import com.manzurola.prodigy.users.data.UserRepository;
import com.mysql.cj.jdbc.MysqlDataSource;
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
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by guym on 17/05/2017.
 */
@Configuration
@PropertySource({"classpath:application-${prodigyEnvTarget}.properties"})
public class Application {

    @Value("${jetty.port}")
    private Integer jettyPort;

    @Value("${jetty.host}")
    private String jettyHost;

    @Value("${elasticsearch.index}")
    private String elasticsearchIndex;

    @Value("${mysql.database}")
    private String mysqlDatabase;

    @Value("${mysql.user}")
    private String mysqlUser;

    @Value("${mysql.password}")
    private String mysqlPassword;

    @Value("${mysql.host}")
    private String mysqlHost;

    @Value("${mysql.port}")
    private int mysqlPort;


    public static void main(String[] args) throws Exception {
        Application application = new Application();
        application.run();
    }

    @Bean
    public DataSource dataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setDatabaseName(mysqlDatabase);
        dataSource.setUser(mysqlUser);
        dataSource.setPassword(mysqlPassword);
        dataSource.setServerName(mysqlHost);
        dataSource.setPort(mysqlPort);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public QuestionRepository questionRepository() throws Exception {
        return new ElasticsearchQuestionRepository(transportClient(), objectMapper(), elasticsearchIndex);
    }

    @Bean
    public UserRepository userRepository() {
        return new MysqlUserRepository(jdbcTemplate());
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
        return new QuestionController(questionRepository());
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
