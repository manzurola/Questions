package com.manzurola.questions.game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.manzurola.questions.FillInTheBlanks;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by guym on 19/07/2017.
 */
public class GameQuestion {

    private final String id = UUID.randomUUID().toString();
    private final FillInTheBlanks fillInTheBlanks;
    private final SentenceGenerator sentenceGenerator = new SingleChoiceSentenceGenerator();
    private final List<Sentence> sentences;
    private double difficulty;

    public GameQuestion(FillInTheBlanks fillInTheBlanks) {
        this.fillInTheBlanks = fillInTheBlanks;
        this.sentences = sentenceGenerator.generate(fillInTheBlanks);
    }

    public String getId() {
        return id;
    }

    @JsonIgnore
    public List<Sentence> getSentences() {
        return sentences;
    }

    @JsonProperty("numOfSentences")
    public int numOfSentences() {
        return sentences.size();
    }

    @JsonProperty("sentences")
    public List<List<String>> sentences() {
        List<List<String>> result = new ArrayList<>();
        for (Sentence sentence : this.sentences) {
            result.add(sentence.getParts());
        }
        return result;
    }

    @JsonIgnore
    public FillInTheBlanks getFillInTheBlanks() {
        return fillInTheBlanks;
    }

    @JsonProperty("correct")
    public int correctSentence() {
        for (int i = 0; i < sentences.size(); i++) {
            if (sentences.get(i).isCorrect()) return i;
        }
        return -1;
    }

    @JsonProperty("choice")
    public int indexOfChoice() {
        return sentences.get(0).getChoiceIndex();
    }

    @JsonProperty("difficulty")
    public double difficulty() {
        return difficulty;
    }

    public void setDifficulty(double difficulty) {
        this.difficulty = difficulty;
    }
}
