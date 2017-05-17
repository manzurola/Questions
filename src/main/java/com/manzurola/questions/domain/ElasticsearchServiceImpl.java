package com.manzurola.questions.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;

/**
 * Created by guym on 17/05/2017.
 */
public class ElasticsearchServiceImpl implements ElasticsearchService {

    private final TransportClient client;
    private final ObjectMapper mapper;

    public ElasticsearchServiceImpl(TransportClient client, ObjectMapper mapper) {
        this.client = client;
        this.mapper = mapper;
    }

    public IndexResponse index(ElasticsearchDocument document) throws Exception {
        return client.prepareIndex(document.getIndex(), document.getType(), document.getId())
                .setSource(mapper.writeValueAsBytes(document.getObject()), XContentType.JSON)
                .get();
    }
}
