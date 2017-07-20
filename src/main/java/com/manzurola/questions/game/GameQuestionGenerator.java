package com.manzurola.questions.game;

import com.manzurola.questions.FillInTheBlanks;

import java.util.List;

/**
 * Created by guym on 19/07/2017.
 */
public interface GameQuestionGenerator {

    /**
     * Generates one or more game questions from a single fill-in-the-blanks question
     * @param fillInTheBlanks
     * @return
     */
    List<GameQuestion> generate(FillInTheBlanks fillInTheBlanks);
}
