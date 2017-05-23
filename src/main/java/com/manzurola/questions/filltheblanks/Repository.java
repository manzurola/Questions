package com.manzurola.questions.filltheblanks;

import java.util.List;

/**
 * Created by guym on 16/05/2017.
 */
public interface Repository {

    void addQuestion(Question question) throws Exception;

    void addQuestions(List<Question> questions) throws Exception;

    void deleteQuestion(String id) throws Exception;

    void deleteQuestions(List<String> ids) throws Exception;

    List<Question> searchQuestions(SearchQuery query) throws Exception;

}
