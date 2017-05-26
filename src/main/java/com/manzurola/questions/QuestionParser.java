package com.manzurola.questions;


import com.manzurola.questions.Question;

/**
 * Created by guym on 24/05/2017.
 */
public interface QuestionParser<T extends Question> {

    T parseQuestion(String[] values);

    String getVersion();
}
