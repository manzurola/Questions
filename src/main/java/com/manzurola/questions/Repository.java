package com.manzurola.questions;

import java.util.List;

/**
 * Created by guym on 16/05/2017.
 */
public interface Repository {

    <T extends Question> void addQuestion(T question) throws Exception;

    <T extends Question> void addQuestions(List<T> questions) throws Exception;

    <T extends Question> void deleteQuestion(String id, Class<T> type) throws Exception;

    <T extends Question> void deleteQuestions(List<String> ids, Class<T> type) throws Exception;

    <T extends Question> List<T> searchQuestionsByAnswer(String termsInAnswer, Class<T> type) throws Exception;

//    <T extends Question> List<T> searchQuestionsByAnswerAndSubject(String termsInAnswer, String termsInSubject) throws Exception;
//
//    <T extends Question> List<T> searchQuestionsBySubject(String termsInSubject) throws Exception;
}
