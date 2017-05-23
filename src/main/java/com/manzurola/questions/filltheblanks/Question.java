package com.manzurola.questions.filltheblanks;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
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
    private final List<String> answer;
    private final String notes;

    public Question(String subject, String body, List<String> answer, String notes) {
        this(UUID.randomUUID().toString(), subject, body, answer, notes);
    }

    @JsonCreator
    public Question(@JsonProperty("id") String id,
                    @JsonProperty("subject") String subject,
                    @JsonProperty("body") String body,
                    @JsonProperty("answer") List<String> answer,
                    @JsonProperty("notes") String notes) {
        this.id = id;
        this.subject = subject;
        this.body = body;
        this.answer = answer;
        this.notes = notes;
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
    public List<String> getAnswer() {
        return answer;
    }

    @JsonProperty("notes")
    public String getNotes() {
        return notes;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", answer=" + answer +
                ", notes='" + notes + '\'' +
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
                Objects.equals(answer, question.answer) &&
                Objects.equals(notes, question.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subject, body, answer, notes);
    }
}
