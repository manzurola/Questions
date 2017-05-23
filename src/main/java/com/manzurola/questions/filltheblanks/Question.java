package com.manzurola.questions.filltheblanks;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

/**
 * Created by guym on 16/05/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Question {

    private final String id;
    private final String body;
    private final String blankToken; // so client may parse body
    private final List<String> answerKey; // list index matches blank index, each blank may have more than one answer
    private final String notes;
    private final String subject;
    private final String solution;
    private final String originalSentence;
    private final String version;

    public Question(String body,
                    List<String> answerKey,
                    String blankToken,
                    String subject,
                    String notes,
                    String solution,
                    String originalSentence,
                    String version) {
        this(UUID.randomUUID().toString(), body, answerKey, blankToken, subject, notes, solution, originalSentence, version);
    }

    @JsonCreator
    public Question(@JsonProperty("id") String id,
                    @JsonProperty("body") String body,
                    @JsonProperty("answerKey") List<String> answerKey,
                    @JsonProperty("blankToken") String blankToken,
                    @JsonProperty("subject") String subject,
                    @JsonProperty("notes") String notes,
                    @JsonProperty("solution") String solution,
                    @JsonProperty("originalSentence") String originalSentence,
                    @JsonProperty("version") String version) {
        this.id = id;
        this.subject = subject;
        this.body = body;
        this.answerKey = Collections.unmodifiableList(answerKey);
        this.notes = notes;
        this.blankToken = blankToken;
        this.solution = solution;
        this.originalSentence = originalSentence;
        this.version = version;
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
        return new ArrayList<>(answerKey);
    }

    @JsonProperty("notes")
    public String getNotes() {
        return notes;
    }

    @JsonProperty("blankToken")
    public String getBlankToken() {
        return blankToken;
    }

    @JsonProperty("solution")
    public String getSolution() {
        return solution;
    }

    @JsonProperty("originalSentence")
    public String getOriginalSentence() {
        return originalSentence;
    }

    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", body='" + body + '\'' +
                ", blankToken='" + blankToken + '\'' +
                ", answerKey=" + answerKey +
                ", notes='" + notes + '\'' +
                ", subject='" + subject + '\'' +
                ", solution='" + solution + '\'' +
                ", originalSentence='" + originalSentence + '\'' +
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
                Objects.equals(notes, question.notes) &&
                Objects.equals(subject, question.subject) &&
                Objects.equals(solution, question.solution) &&
                Objects.equals(originalSentence, question.originalSentence) &&
                Objects.equals(version, question.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, body, blankToken, answerKey, notes, subject, solution, originalSentence, version);
    }
}
