package com.manzurola.questions.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by guym on 16/05/2017.
 */
public class Question {

    private final String id;
    private final String type;
    private final String subject;
    private final String body;
    private final String answer;

    @JsonCreator
    public Question(@JsonProperty("id") String id,
                    @JsonProperty("type") String type,
                    @JsonProperty("subject") String subject,
                    @JsonProperty("body") String body,
                    @JsonProperty("answer") String answer) {
        this.id = id;
        this.type = type;
        this.subject = subject;
        this.body = body;
        this.answer = answer;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("subject")
    public String getSubject() {
        return subject;
    }

    @JsonProperty("body")
    public String getBody() {
        return body;
    }

    @JsonProperty("answer")
    public String getAnswer() {
        return answer;
    }
}
