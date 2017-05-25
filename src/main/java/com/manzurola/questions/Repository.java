package com.manzurola.questions;

import java.util.List;

/**
 * Created by guym on 16/05/2017.
 */
public interface Repository {

    void addQuestion(Question question) throws Exception;

    void addQuestions(List<Question> questions) throws Exception;

    void deleteQuestion(String id) throws Exception;

    void deleteQuestions(List<String> ids) throws Exception;

    List<Question> searchQuestionsByAnswer(String termsInAnswer) throws Exception;

    List<Question> searchQuestionsByAnswerAndSubject(String termsInAnswer, String termsInSubject) throws Exception;

    List<Question> searchQuestionsBySubject(String termsInSubject) throws Exception;
}
