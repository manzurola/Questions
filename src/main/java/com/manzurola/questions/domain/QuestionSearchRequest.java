package com.manzurola.questions.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by guym on 17/05/2017.
 */
public class QuestionSearchRequest {
    private final List<String> textInBody;
    private final List<String> textInAnswer;

    @JsonCreator
    public QuestionSearchRequest(@JsonProperty("textInBody") List<String> textInBody,
                                 @JsonProperty("textInAnswer") List<String> textInAnswer) {
        this.textInBody = textInBody;
        this.textInAnswer = textInAnswer;
    }

    @JsonProperty("textInBody")
    public List<String> getTextInBody() {
        return textInBody;
    }

    @JsonProperty("textInAnswer")
    public List<String> getTextInAnswer() {
        return textInAnswer;
    }
}
