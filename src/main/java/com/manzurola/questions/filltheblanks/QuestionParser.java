package com.manzurola.questions.filltheblanks;

/**
 * Created by guym on 24/05/2017.
 */
public interface QuestionParser {

    Question parseSentence(String sentence);

    String getVersion();
}
