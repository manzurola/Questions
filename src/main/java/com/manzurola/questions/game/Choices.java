package com.manzurola.questions.game;

import java.util.*;

/**
 * Created by guym on 21/07/2017.
 */
public class Choices {
    private final List<String> correct;
    private final List<String> dummies;

    public Choices(Set<String> correct, Set<String> incorrect) {
        this.correct = new ArrayList<>(correct);
        this.dummies = new ArrayList<>(incorrect);
    }

    public List<String> getCorrect() {
        return correct;
    }

    public List<String> getIncorrect() {
        return dummies;
    }
}
