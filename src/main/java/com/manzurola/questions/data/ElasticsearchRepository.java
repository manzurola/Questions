package com.manzurola.questions.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manzurola.questions.Question;
import com.manzurola.questions.RewriteTheSentence;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by guym on 16/05/2017.
 */
public class ElasticsearchRepository implements Repository {

    private final TransportClient client;
    private final ObjectMapper mapper;
    private final String index;
    private final Map<Class<?>, String> mappedTypes;

    public ElasticsearchRepository(TransportClient client, ObjectMapper mapper, String index) {
        this.client = client;
        this.mapper = mapper;
        this.index = index;
        this.mappedTypes = new HashMap<>();
        mappedTypes.put(FillInTheBlanks.class, "fill_in_the_blanks");
        mappedTypes.put(RewriteTheSentence.class, "rewrite_the_sentence");
    }

    @Override
    public <T extends Question> void addQuestion(T question) throws Exception {
        IndexRequestBuilder requestBuilder = client.prepareIndex(index, getMappedType(question.getClass()), question.getId());
        requestBuilder.setSource(mapper.writeValueAsBytes(question), XContentType.JSON).get();
    }

    @Override
    public <T extends Question> void addQuestions(List<T> questions) throws Exception {
        for (Question question : questions) {
            addQuestion(question);
        }
    }

    @Override
    public <T extends Question> void deleteQuestion(String id, Class<T> type) throws Exception {
        client.prepareDelete(index, getMappedType(type), id).get();
    }

    @Override
    public <T extends Question> void deleteQuestions(List<String> ids, Class<T> type) throws Exception {
        for (String id : ids) {
            deleteQuestion(id, type);
        }
    }

    @Override
    public <T extends Question> List<T> searchQuestionsByAnswer(String termsInAnswer, Class<T> type) throws Exception {
        BoolQueryBuilder boolQuery = new BoolQueryBuilder();
        boolQuery.must(QueryBuilders.matchQuery("answerKey", termsInAnswer));
        return search(boolQuery, type);
    }

    @Override
    public <T extends Question> List<T> getAllQuestions(Class<T> type, Sorter<T> sorter) throws Exception {
        return null;
    }

//    @Override
//    public List<Question> searchQuestionsByAnswerAndSubject(String termsInAnswer, String termsInSubject) throws Exception {
//        BoolQueryBuilder boolQuery = new BoolQueryBuilder();
//        boolQuery.must(QueryBuilders.matchQuery("answerKey", termsInAnswer))
//                .must(QueryBuilders.matchQuery("subject", termsInSubject));
//        return search(boolQuery);
//    }
//
//    @Override
//    public List<Question> searchQuestionsBySubject(String termsInSubject) throws Exception {
//        BoolQueryBuilder boolQuery = new BoolQueryBuilder();
//        boolQuery.must(QueryBuilders.matchQuery("subject", termsInSubject));
//        return search(boolQuery);
//    }

    private <T extends Question> List<T> search(QueryBuilder query, Class<T> type) throws IOException {
        SearchResponse response = client.prepareSearch(index).setTypes(getMappedType(type))
                .setQuery(query)
                .get();
        List<T> results = new ArrayList<>();
        for (SearchHit searchHit : response.getHits()) {
            results.add(mapper.readValue(searchHit.getSourceAsString(), type));
        }

        return results;
    }

    private String getMappedType(Class<?> type) {
        return mappedTypes.get(type);
    }
}
