package com.manzurola.questions.game;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.manzurola.questions.fillintheblanks.FillInTheBlanks;

import java.util.List;

/**
 * Created by guym on 19/07/2017.
 */
public class GameQuestion {

    private final FillInTheBlanks fillInTheBlanks;
    private final SentenceGenerator sentenceGenerator = new SingleChoiceSentenceGenerator();
    private final List<Sentence> sentences;

    private final List<String> sentences;
    private final int correctSentence;
    private final int choiceIndex;
    private final int difficuly;

    public GameQuestion(FillInTheBlanks fillInTheBlanks) {
        this.fillInTheBlanks = fillInTheBlanks;
        this.sentences = sentenceGenerator.generate(fillInTheBlanks);
    }

    public String getId() {
        return fillInTheBlanks.getId();
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    @JsonProperty("numOfSentences")
    public int numOfSentences() {
        return sentences.size();
    }
}
