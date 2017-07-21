package com.manzurola.questions.game;

/**
 * Created by guym on 21/07/2017.
 */
public class Blank extends SentenceSlice {

    private final Choices choices;

    public Blank(String placeholder, Choices choices) {
        super(placeholder);
        this.choices = choices;
    }

    @Override
    public boolean isBlank() {
        return true;
    }

    @Override
    public Choices getChoices() {
        return choices;
    }

    @Override
    public Blank asBlank() {
        return this;
    }
}
