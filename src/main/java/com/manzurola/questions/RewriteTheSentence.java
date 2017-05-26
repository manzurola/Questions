package com.manzurola.questions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by guym on 26/05/2017.
 */
public class RewriteTheSentence extends Question {
    public RewriteTheSentence(String body, List<String> answerKey, String subject, String instructions, String source, String version) {
        super(body, answerKey, subject, instructions, source, version);
    }

    @JsonCreator
    public RewriteTheSentence(@JsonProperty("id") String id,
                              @JsonProperty("body") String body,
                              @JsonProperty("answerKey") List<String> answerKey,
                              @JsonProperty("subject") String subject,
                              @JsonProperty("instructions") String instructions,
                              @JsonProperty("source") String source,
                              @JsonProperty("version") String version) {
        super(id, body, answerKey, subject, instructions, source, version);
    }
}
