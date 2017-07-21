package com.manzurola.questions.game;

/**
 * Created by guym on 21/07/2017.
 */
public class SentenceSlice {

    private final String text;

    public SentenceSlice(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public boolean isBlank() {
        return false;
    }

    public Choices getChoices() {
        return null;
    }

    public Blank asBlank() {
        return null;
    }
}
