package com.manzurola.questions;

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
public abstract class Question {

    private final String id;
    private final String body;
    private final List<String> answerKey;
    private final String instructions;
    private final String subject;
    private final String source;
    private final String version; // to reference the parser version

    public Question(String body,
                    List<String> answerKey,
                    String subject,
                    String instructions,
                    String source,
                    String version) {
        this(UUID.randomUUID().toString(), body, answerKey, subject, instructions, source, version);
    }

    @JsonCreator
    public Question(@JsonProperty("id") String id,
                    @JsonProperty("body") String body,
                    @JsonProperty("answerKey") List<String> answerKey,
                    @JsonProperty("subject") String subject,
                    @JsonProperty("instructions") String instructions,
                    @JsonProperty("source") String source,
                    @JsonProperty("version") String version) {
        this.id = id;
        this.body = body;
        this.answerKey = answerKey;
        this.subject = subject;
        this.instructions = instructions;
        this.source = source;
        this.version = version;
    }

    public Question(Question copy) {
        this(copy.id, copy.body, copy.answerKey, copy.subject, copy.instructions, copy.source, copy.version);
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

    @JsonProperty("answerKey")
    public List<String> getAnswerKey() {
        return answerKey;
    }

    @JsonProperty("instructions")
    public String getInstructions() {
        return instructions;
    }

    @JsonProperty("source")
    public String getSource() {
        return source;
    }

    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return "IQuestion{" +
                "id='" + id + '\'' +
                ", body='" + body + '\'' +
                ", answerKey=" + answerKey +
                ", instructions='" + instructions + '\'' +
                ", subject='" + subject + '\'' +
                ", source='" + source + '\'' +
                ", version='" + version + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(id, question.id) &&
                Objects.equals(body, question.body) &&
                Objects.equals(answerKey, question.answerKey) &&
                Objects.equals(instructions, question.instructions) &&
                Objects.equals(subject, question.subject) &&
                Objects.equals(source, question.source) &&
                Objects.equals(version, question.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, body, answerKey, instructions, subject, source, version);
    }
}
