package com.manzurola.questions.filltheblanks;

import com.manzurola.questions.filltheblanks.search.SearchQuery;

import java.util.List;

/**
 * Created by guym on 16/05/2017.
 */
public interface QuestionService {

    void addQuestion(Question question) throws Exception;

    List<Question> searchQuestions(SearchQuery query) throws Exception;

}
