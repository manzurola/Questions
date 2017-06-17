package com.manzurola.questions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by guym on 15/06/2017.
 */
public class Choice {
    private final String text;
    private final int index;
    private final boolean correct;

    @JsonCreator
    public Choice(@JsonProperty("text") String text,
                  @JsonProperty("index") int index,
                  @JsonProperty("correct") boolean correct) {
        this.text = text;
        this.index = index;
        this.correct = correct;
    }

    public String getText() {
        return text;
    }

    public int getIndex() {
        return index;
    }

    public boolean isCorrect() {
        return correct;
    }
}
