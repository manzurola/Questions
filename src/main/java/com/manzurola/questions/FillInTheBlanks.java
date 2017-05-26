package com.manzurola.questions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by guym on 26/05/2017.
 */
public class FillInTheBlanks extends Question{
    private final String blankToken;

    public FillInTheBlanks(String body, List<String> answerKey, String subject, String instructions, String source, String version, String blankToken) {
        super(body, answerKey, subject, instructions, source, version);
        this.blankToken = blankToken;
    }

    @JsonCreator
    public FillInTheBlanks(@JsonProperty("id") String id,
                           @JsonProperty("body") String body,
                           @JsonProperty("answerKey") List<String> answerKey,
                           @JsonProperty("subject") String subject,
                           @JsonProperty("instructions") String instructions,
                           @JsonProperty("source") String source,
                           @JsonProperty("version") String version,
                           @JsonProperty("blankToken") String blankToken) {
        super(id, body, answerKey, subject, instructions, source, version);
        this.blankToken = blankToken;
    }

    public FillInTheBlanks(FillInTheBlanks copy) {
        super(copy);
        this.blankToken = copy.blankToken;
    }

    @JsonProperty("blankToken")
    public String getBlankToken() {
        return blankToken;
    }
}
