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
    private final List<String[]> answerKey; // list index matches blank index, each blank may have more than one answer
    private final String notes;
    private final String subject;
    private final String solution;

//    private Question(Builder builder) {
//        this(builder.id,
//                builder.body,
//                builder.answerKey,
//                builder.blankToken,
//                builder.subject,
//                builder.notes,
//                builder.solution);
//    }

    public Question(String body,
                    List<String[]> answerKey,
                    String blankToken,
                    String subject,
                    String notes,
                    String solution) {
        this(UUID.randomUUID().toString(), body, answerKey, blankToken, subject, notes, solution);
    }

    @JsonCreator
    public Question(@JsonProperty("id") String id,
                    @JsonProperty("body") String body,
                    @JsonProperty("answerKey") List<String[]> answerKey,
                    @JsonProperty("blankToken") String blankToken,
                    @JsonProperty("subject") String subject,
                    @JsonProperty("notes") String notes,
                    @JsonProperty("solution") String solution) {
        this.id = id;
        this.subject = subject;
        this.body = body;
        this.answerKey = Collections.unmodifiableList(answerKey);
        this.notes = notes;
        this.blankToken = blankToken;
        this.solution = solution;
    }

//    public static class Builder {
//        private String id = UUID.randomUUID().toString();
//        private String body;
//        private String blankToken;
//        private List<String[]> answerKey;
//        private String subject = "";
//        private String notes = "";
//        private String solution;
//
//        public Builder(String body, String blankToken, List<String[]> answerKey, String solution) {
//            this.body = body;
//            this.blankToken = blankToken;
//            this.answerKey = answerKey;
//            this.solution = solution;
//        }
//
//        public Question build() {
//            return new Question(this);
//        }
//
//        public Builder setId(String id) {
//            this.id = id;
//            return this;
//        }
//
//        public Builder setSubject(String subject) {
//            this.subject = subject;
//            return this;
//        }
//
//        public Builder setNotes(String notes) {
//            this.notes = notes;
//            return this;
//        }
//    }

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
    public List<String[]> getAnswerKey() {
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

    public String getSolution() {
        return solution;
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
                Objects.equals(solution, question.solution);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, body, blankToken, answerKey, notes, subject, solution);
    }
}
