package com.manzurola.questions.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.util.UUID;

/**
 * Created by guym on 16/05/2017.
 */
public class QuestionServiceImpl implements QuestionService {
    public Question addQuestion(Question question) throws Exception{
        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch_guym").build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));

        UUID id = UUID.randomUUID();

        ObjectMapper mapper = new ObjectMapper();

        IndexResponse indexResponse = client.prepareIndex("questions_en", "fill_in_the_blanks", id.toString())
                .setSource(mapper.writeValueAsBytes(question), XContentType.JSON)
                .get();

        return null;
    }
}
