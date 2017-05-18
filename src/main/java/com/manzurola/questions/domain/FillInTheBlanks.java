package com.manzurola.questions.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * Created by guym on 17/05/2017.
 */
public class FillInTheBlanks extends Question {

    private final String blankPlaceholderToken;

    public FillInTheBlanks(String subject, String body, String answer, String blankPlaceholderToken) {
        super(subject, body, answer);
        this.blankPlaceholderToken = blankPlaceholderToken;
    }

    @JsonCreator
    public FillInTheBlanks(@JsonProperty("id") String id,
                           @JsonProperty("subject") String subject,
                           @JsonProperty("body") String body,
                           @JsonProperty("answer") String answer,
                           @JsonProperty("blankPlaceholderToken") String blankPlaceholderToken) {
       super(id, subject, body, answer);
       this.blankPlaceholderToken = blankPlaceholderToken;
    }



}
