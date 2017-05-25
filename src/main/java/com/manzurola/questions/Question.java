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
public class Question {

    private final String id;
    private final String body;
    private final String blankToken; // so client may parse body
    private final List<String> answerKey;
    private final String instructions;
    private final String subject;
    private final String source;
    private final String version; // to reference the parser version
    private final String type;

    public Question(String body,
                    List<String> answerKey,
                    String blankToken,
                    String subject,
                    String instructions,
                    String source,
                    String version,
                    String type) {
        this(UUID.randomUUID().toString(), body, answerKey, blankToken, subject, instructions, source, version, type);
    }

    @JsonCreator
    public Question(@JsonProperty("id") String id,
                    @JsonProperty("body") String body,
                    @JsonProperty("answerKey") List<String> answerKey,
                    @JsonProperty("blankToken") String blankToken,
                    @JsonProperty("subject") String subject,
                    @JsonProperty("instructions") String instructions,
                    @JsonProperty("source") String source,
                    @JsonProperty("version") String version,
                    @JsonProperty("type") String type) {
        this.id = id;
        this.body = body;
        this.answerKey = answerKey;
        this.blankToken = blankToken;
        this.subject = subject;
        this.instructions = instructions;
        this.source = source;
        this.version = version;
        this.type = type;
    }

    public Question(Question copy) {
        this(copy.id, copy.body, copy.answerKey, copy.blankToken, copy.subject, copy.instructions, copy.source, copy.version, copy.type);
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

    @JsonProperty("blankToken")
    public String getBlankToken() {
        return blankToken;
    }

    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "IQuestion{" +
                "id='" + id + '\'' +
                ", body='" + body + '\'' +
                ", blankToken='" + blankToken + '\'' +
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
                Objects.equals(blankToken, question.blankToken) &&
                Objects.equals(answerKey, question.answerKey) &&
                Objects.equals(instructions, question.instructions) &&
                Objects.equals(subject, question.subject) &&
                Objects.equals(source, question.source) &&
                Objects.equals(version, question.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, body, blankToken, answerKey, instructions, subject, source, version);
    }
}
