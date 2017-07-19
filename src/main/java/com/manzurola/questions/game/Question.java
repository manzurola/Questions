package com.manzurola.questions.game;

import com.manzurola.questions.FillInTheBlanks;

/**
 * Created by guym on 19/07/2017.
 */
public class Question {

    FillInTheBlanks fillInTheBlanks;

    private int id;
    private int numOfChoices;
    private int selectedChoice;
    private String[] sentence;
    private String[] choices;
    private int choiceAtSentencePart;   // the index matching the part of sentence that is a choice
    private int answerAtChoice;         // the index matching the choice that is the correct answer
    private int difficultyLevel;


}
