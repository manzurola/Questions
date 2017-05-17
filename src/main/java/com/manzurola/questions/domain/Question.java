package com.manzurola.questions.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.org.apache.xalan.internal.xsltc.DOM;

import java.util.UUID;

/**
 * Created by guym on 16/05/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Question implements DomainObject {

    private final String id;
    private final String type;
    private final String subject;
    private final String body;
    private final String answer;

    public Question(String type, String subject, String body, String answer) {
        this(UUID.randomUUID().toString(), type, subject, body, answer);
    }

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
