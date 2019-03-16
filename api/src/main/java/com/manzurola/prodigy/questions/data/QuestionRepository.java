package com.manzurola.prodigy.questions.data;

import com.manzurola.prodigy.questions.Question;

import java.util.List;

/**
 * Created by guym on 16/05/2017.
 */
public interface QuestionRepository {

    void add(Question question) throws Exception;

    void add(List<Question> questions) throws Exception;

    void delete(String id) throws Exception;

    void delete(List<String> ids) throws Exception;

    List<Question> searchByAnswer(String termsInAnswer) throws Exception;

//    <Question extends Question> List<Question> searchQuestionsByAnswerAndSubject(String termsInAnswer, String termsInSubject) throws Exception;
//
//    <Question extends Question> List<Question> searchQuestionsBySubject(String termsInSubject) throws Exception;
}
