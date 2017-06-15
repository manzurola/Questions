package com.manzurola.questions;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by guym on 07/06/2017.
 */
public class MultiChoiceTransformation extends Question {
    private final MultiChoiceRewriteTarget target;


    public MultiChoiceTransformation(String body, List<String> answerKey, String subject, String instructions, String source, String version, MultiChoiceRewriteTarget target) {
        super(body, answerKey, subject, instructions, source, version);
        this.target = target;
    }

    public MultiChoiceTransformation(@JsonProperty("id") String id,
                                     @JsonProperty("body") String body,
                                     @JsonProperty("answerKey") List<String> answerKey,
                                     @JsonProperty("subject") String subject,
                                     @JsonProperty("instructions") String instructions,
                                     @JsonProperty("source") String source,
                                     @JsonProperty("version") String version,
                                     @JsonProperty("target") MultiChoiceRewriteTarget target) {
        super(id, body, answerKey, subject, instructions, source, version);
        this.target = target;
    }

    public MultiChoiceTransformation(MultiChoiceTransformation copy) {
        super(copy);
        this.target = copy.target;
    }

}
