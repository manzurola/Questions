package com.manzurola.questions.game;

import com.manzurola.questions.Question;

/**
 * Created by guym on 17/07/2017.
 */
public interface DifficultyClassifier {

    double classify(GameQuestion question) throws Exception;
}
