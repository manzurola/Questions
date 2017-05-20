package com.manzurola.questions.filltheblanks.search;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manzurola.questions.filltheblanks.Question;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.SearchHit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guym on 17/05/2017.
 */
public class SearchClientImpl implements SearchClient {

    private final TransportClient client;
    private final ObjectMapper mapper;
    private final String index;
    private final String type;

    public SearchClientImpl(TransportClient client, ObjectMapper mapper, String index, String type) {
        this.client = client;
        this.mapper = mapper;
        this.index = index;
        this.type = type;
    }

    public IndexResponse index(Question document) throws Exception {
        IndexRequestBuilder requestBuilder = client.prepareIndex(index, type, document.getId());
        return requestBuilder.setSource(mapper.writeValueAsBytes(document), XContentType.JSON).get();
    }


    public List<Question> search(SearchQuery query) throws Exception {
        SearchResponse response = client.prepareSearch(index).setTypes(type)
                .setQuery(query.getQuery())                 // SearchQuery
//                .setFrom(query.getFrom()).setSize(query.getSize()).setExplain(true)
//                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .get();

        List<Question> results = new ArrayList<Question>();
        for (SearchHit searchHit : response.getHits()) {
            results.add(mapper.readValue(searchHit.getSourceAsString(), Question.class));
        }

        return results;
    }

}
