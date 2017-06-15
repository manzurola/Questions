package com.manzurola.questions;

import java.util.Objects;

/**
 * Created by guym on 07/06/2017.
 */
public abstract class WordChoice {
    private final String word;
    private final boolean correct;

    public WordChoice(String word, boolean correct) {
        this.word = word;
        this.correct = correct;
    }

    public String getWord() {
        return word;
    }

    public boolean isCorrect() {
        return correct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordChoice that = (WordChoice) o;
        return correct == that.correct &&
                Objects.equals(word, that.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, correct);
    }

    @Override
    public String toString() {
        return "WordChoice{" +
                "word='" + word + '\'' +
                ", correct=" + correct +
                '}';
    }
}
