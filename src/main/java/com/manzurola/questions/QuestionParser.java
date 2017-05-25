package com.manzurola.questions;

/**
 * Created by guym on 24/05/2017.
 */
public interface QuestionParser {

    Question parseQuestion(String[] values);

    String getVersion();

    String getQuestionType();
}
