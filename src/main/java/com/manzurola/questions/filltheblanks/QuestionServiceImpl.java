package com.manzurola.questions.filltheblanks;

import com.manzurola.questions.filltheblanks.search.SearchClient;
import com.manzurola.questions.filltheblanks.search.SearchQuery;

import java.util.List;

/**
 * Created by guym on 16/05/2017.
 */
public class QuestionServiceImpl implements QuestionService {

    private final SearchClient elasticsearch;

    public QuestionServiceImpl(SearchClient elasticsearch) {
        this.elasticsearch = elasticsearch;
    }

    public void addQuestion(Question question) throws Exception{
        elasticsearch.index(question);
    }

    public List<Question> searchQuestions(SearchQuery query) throws Exception {
        return elasticsearch.search(query);
    }

}
