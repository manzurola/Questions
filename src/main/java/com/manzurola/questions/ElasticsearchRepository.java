package com.manzurola.questions;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import java.io.IOException;
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
    public List<Question> searchQuestionsByAnswer(String termsInAnswer) throws Exception {
        BoolQueryBuilder boolQuery = new BoolQueryBuilder();
        boolQuery.must(QueryBuilders.matchQuery("answerKey", termsInAnswer));
        return search(boolQuery);
    }

    @Override
    public List<Question> searchQuestionsByAnswerAndSubject(String termsInAnswer, String termsInSubject) throws Exception {
        BoolQueryBuilder boolQuery = new BoolQueryBuilder();
        boolQuery.must(QueryBuilders.matchQuery("answerKey", termsInAnswer))
                .must(QueryBuilders.matchQuery("subject", termsInSubject));
        return search(boolQuery);
    }

    @Override
    public List<Question> searchQuestionsBySubject(String termsInSubject) throws Exception {
        BoolQueryBuilder boolQuery = new BoolQueryBuilder();
        boolQuery.must(QueryBuilders.matchQuery("subject", termsInSubject));
        return search(boolQuery);
    }

    private List<Question> search(QueryBuilder query) throws IOException {
        SearchResponse response = client.prepareSearch(index).setTypes(type)
                .setQuery(query)
                .get();
        List<Question> results = new ArrayList<>();
        for (SearchHit searchHit : response.getHits()) {
            results.add(mapper.readValue(searchHit.getSourceAsString(), Question.class));
        }

        return results;
    }
}
