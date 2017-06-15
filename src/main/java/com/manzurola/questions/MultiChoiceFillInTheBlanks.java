package com.manzurola.questions;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;

/**
 * Created by guym on 15/06/2017.
 */
public class MultiChoiceFillInTheBlanks extends FillInTheBlanks implements MultiChoice {

    private final List<Choice> choices;

    public MultiChoiceFillInTheBlanks(String body, List<String> answerKey,
                                      String subject,
                                      String instructions,
                                      String source,
                                      String version,
                                      String blankToken,
                                      List<Choice> choices) {
        super(body, answerKey, subject, instructions, source, version, blankToken);
        this.choices = Collections.unmodifiableList(choices);
    }

    public MultiChoiceFillInTheBlanks(@JsonProperty("id") String id,
                                      @JsonProperty("body") String body,
                                      @JsonProperty("answerKey") List<String> answerKey,
                                      @JsonProperty("subject") String subject,
                                      @JsonProperty("instructions") String instructions,
                                      @JsonProperty("source") String source,
                                      @JsonProperty("version") String version,
                                      @JsonProperty("blankToken") String blankToken,
                                      @JsonProperty("choices") List<Choice> choices) {
        super(id, body, answerKey, subject, instructions, source, version, blankToken);
        this.choices = Collections.unmodifiableList(choices);
    }

    public MultiChoiceFillInTheBlanks(MultiChoiceFillInTheBlanks copy) {
        super(copy);
        this.choices = Collections.unmodifiableList(copy.choices);
    }

    @Override
    public List<Choice> getChoices() {
        return choices;
    }

}
