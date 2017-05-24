package com.manzurola.questions.filltheblanks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.SearchHit;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guym on 16/05/2017.
 */
public class ElasticsearchRepository implements Repository {

    private final TransportClient client;
    private final ObjectMapper mapper;
    private final String index;
    private final String type;

    public ElasticsearchRepository(TransportClient client, ObjectMapper mapper, String index, String type) {
        this.client = client;
        this.mapper = mapper;
        this.index = index;
        this.type = type;
    }

    @Override
    public void addQuestion(Question question) throws Exception {
        IndexRequestBuilder requestBuilder = client.prepareIndex(index, type, question.getId());
        requestBuilder.setSource(mapper.writeValueAsBytes(question), XContentType.JSON).get();
    }

    @Override
    public void addQuestions(List<Question> questions) throws Exception {
        for (Question question : questions) {
            addQuestion(question);
        }
    }

    @Override
    public void deleteQuestion(String id) throws Exception {
        client.prepareDelete(index, type, id).get();
    }

    @Override
    public void deleteQuestions(List<String> ids) throws Exception {
        for (String id : ids) {
            deleteQuestion(id);
        }
    }

    @Override
    public List<Question> searchQuestions(SearchQuery query) throws Exception {
        SearchResponse response = client.prepareSearch(index).setTypes(type)
                .setQuery(query.getQuery())                 // SearchQuery
//                .setFrom(query.getFrom()).setSize(query.getSize()).setExplain(true)
//                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .get();

        List<Question> results = new ArrayList<>();
        for (SearchHit searchHit : response.getHits()) {
            results.add(mapper.readValue(searchHit.getSourceAsString(), Document.class));
        }

        return results;
    }


    private static class Document extends Question{

        protected Document(@JsonProperty("id") String id, @JsonProperty("body") String body, @JsonProperty("answerKey") List<String> answerKey, @JsonProperty("blankToken") String blankToken, @JsonProperty("subject") String subject, @JsonProperty("notes") String notes, @JsonProperty("solution") String solution, @JsonProperty("originalSentence") String originalSentence, @JsonProperty("version") String version) {
            super(id, body, answerKey, blankToken, subject, notes, solution, originalSentence, version);
        }

        Document(Question question) {
            super(question);
        }
    }
}
