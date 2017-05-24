package com.manzurola.questions.filltheblanks;

/**
 * Created by guym on 24/05/2017.
 */
public interface QuestionParser {

    Question parseText(String sentence);

    String getVersion();
}
