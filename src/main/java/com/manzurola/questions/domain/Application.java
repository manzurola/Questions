package com.manzurola.questions.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;

/**
 * Created by guym on 17/05/2017.
 */
@Configuration
public class Application {

    @Bean
    public QuestionService questionService() throws Exception {
        return new QuestionServiceImpl(elasticsearchService());
    }

    @Bean
    public ElasticsearchService elasticsearchService() throws Exception {
        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch_guym").build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));

        return new ElasticsearchServiceImpl(client, objectMapper());
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
