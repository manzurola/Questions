package com.manzurola.questions.game;

import com.manzurola.questions.fillintheblanks.FillInTheBlanks;

import java.util.List;

/**
 * Created by guym on 19/07/2017.
 */
public interface SentenceGenerator {

    List<Sentence> generate(FillInTheBlanks question);
}
