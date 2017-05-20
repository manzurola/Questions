package com.manzurola.questions.filltheblanks;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.UUID;

/**
 * Created by guym on 16/05/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Question {

    private final String id;
    private final String subject;
    private final String body;
    private final String answer;

    public Question(String subject, String body, String answer) {
        this(UUID.randomUUID().toString(), subject, body, answer);
    }

    @JsonCreator
    public Question(@JsonProperty("id") String id,
                    @JsonProperty("subject") String subject,
                    @JsonProperty("body") String body,
                    @JsonProperty("answer") String answer) {
        this.id = id;
        this.subject = subject;
        this.body = body;
        this.answer = answer;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
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

    @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(id, question.id) &&
                Objects.equals(subject, question.subject) &&
                Objects.equals(body, question.body) &&
                Objects.equals(answer, question.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subject, body, answer);
    }
}
