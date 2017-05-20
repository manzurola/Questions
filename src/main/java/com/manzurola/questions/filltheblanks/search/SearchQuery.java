package com.manzurola.questions.filltheblanks.search;

import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

/**
 * Created by guym on 19/05/2017.
 */
public class SearchQuery {

    private final int from;
    private final int size;
    private final String termsInQuestionBody;
    private final String termsInQuestionAnswer;
    private final String termsInSubject;

    public SearchQuery(int from, int size, String termsInQuestionBody, String termsInQuestionAnswer, String termsInSubject) {
        this.from = from;
        this.size = size;
        this.termsInQuestionBody = termsInQuestionBody;
        this.termsInQuestionAnswer = termsInQuestionAnswer;
        this.termsInSubject = termsInSubject;
    }



    public int getFrom() {
        return from;
    }

    public int getSize() {
        return size;
    }

    public String getTermsInQuestionBody() {
        return termsInQuestionBody;
    }

    public String getTermsInQuestionAnswer() {
        return termsInQuestionAnswer;
    }

    public String getTermsInSubject() {
        return termsInSubject;
    }

    public QueryBuilder getQuery() {
        BoolQueryBuilder boolQuery = new BoolQueryBuilder();
        boolQuery.must(QueryBuilders.matchQuery("body", termsInQuestionBody))
                .must(QueryBuilders.matchQuery("answer", termsInQuestionAnswer))
                .must(QueryBuilders.matchQuery("subject", termsInSubject));
        return boolQuery;
    }

}
