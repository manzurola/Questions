package com.manzurola.questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by guym on 07/06/2017.
 */
public class WordChoiceGroup {
    private final List<CorrectWordChoice> answers;
    private final List<DummyWordChoice> dummies;

    private WordChoiceGroup(CorrectWordChoice answer, List<DummyWordChoice> dummies) {
        this(Arrays.asList(answer), dummies);
    }

    private WordChoiceGroup(List<CorrectWordChoice> answers, List<DummyWordChoice> dummies) {
        this.answers = new ArrayList<>(answers);
        this.dummies = new ArrayList<>(dummies);
    }

    public List<CorrectWordChoice> getAnswers() {
        return answers;
    }

    public List<DummyWordChoice> getDummies() {
        return dummies;
    }
}
