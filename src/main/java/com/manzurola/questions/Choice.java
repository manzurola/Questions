package com.manzurola.questions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Choice choice = (Choice) o;
        return index == choice.index &&
                correct == choice.correct &&
                Objects.equals(text, choice.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, index, correct);
    }

    @Override
    public String toString() {
        return "Choice{" +
                "text='" + text + '\'' +
                ", index=" + index +
                ", correct=" + correct +
                '}';
    }
}
