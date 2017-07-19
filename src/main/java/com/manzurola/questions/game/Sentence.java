package com.manzurola.questions.game;

import java.util.List;
import java.util.Objects;

/**
 * Created by guym on 19/07/2017.
 */
public class Sentence {

    private final List<String> parts;
    private final int choiceIndex;
    private final boolean isCorrect;

    public Sentence(List<String> parts, int choiceIndex, boolean isCorrect) {
        this.parts = parts;
        this.choiceIndex = choiceIndex;
        this.isCorrect = isCorrect;
    }

    public List<String> getParts() {
        return parts;
    }

    public int getChoiceIndex() {
        return choiceIndex;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public String getChoice() {
        return parts.get(choiceIndex);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sentence sentence = (Sentence) o;
        return choiceIndex == sentence.choiceIndex &&
                isCorrect == sentence.isCorrect &&
                Objects.equals(parts, sentence.parts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parts, choiceIndex, isCorrect);
    }

    @Override
    public String toString() {
        return "Sentence{" +
                "parts=" + parts +
                ", choiceIndex=" + choiceIndex +
                ", isCorrect=" + isCorrect +
                ", choice='" + getChoice() + '\'' +
                '}';
    }
}
