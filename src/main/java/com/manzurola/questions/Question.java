package com.manzurola.questions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
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
    private final int difficultyLevel;
    private final List<Choice> choices;

    public Question(String body,
                    List<String> answerKey,
                    String subject,
                    String instructions,
                    String source,
                    int difficultyLevel,
                    List<Choice> choices) {
        this(UUID.randomUUID().toString(), body, answerKey, subject, instructions, source, difficultyLevel, choices);
    }

    @JsonCreator
    public Question(@JsonProperty("id") String id,
                    @JsonProperty("body") String body,
                    @JsonProperty("answerKey") List<String> answerKey,
                    @JsonProperty("subject") String subject,
                    @JsonProperty("instructions") String instructions,
                    @JsonProperty("source") String source,
                    @JsonProperty("difficultyLevel") int difficultyLevel,
                    @JsonProperty("choices") List<Choice> choices) {
        this.id = id;
        this.body = body;
        this.answerKey = answerKey;
        this.subject = subject;
        this.instructions = instructions;
        this.source = source;
        this.difficultyLevel = difficultyLevel;
        this.choices = choices;
    }

    public Question(Question copy) {
        this(copy.id, copy.body, copy.answerKey, copy.subject, copy.instructions, copy.source, copy.difficultyLevel, copy.choices);
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

    @JsonProperty("difficultyLevel")
    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    @JsonProperty("choices")
    public List<Choice> getChoices() {
        return choices;
    }

    public List<Choice> getChoicesAt(int index) {
        List<Choice> choices = new ArrayList<>();
        getChoices().forEach((choice) -> {
            if (choice.getIndex() == index) choices.add(choice);
        });
        return choices;
    }

    public int countChoicesAt(int index) {
        return getChoicesAt(index).size();
    }

    public Choice getChoiceAt(int i, int j) {
        return getChoicesAt(i).get(j);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return difficultyLevel == question.difficultyLevel &&
                Objects.equals(id, question.id) &&
                Objects.equals(body, question.body) &&
                Objects.equals(answerKey, question.answerKey) &&
                Objects.equals(instructions, question.instructions) &&
                Objects.equals(subject, question.subject) &&
                Objects.equals(source, question.source) &&
                Objects.equals(choices, question.choices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, body, answerKey, instructions, subject, source, difficultyLevel, choices);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", body='" + body + '\'' +
                ", answerKey=" + answerKey +
                ", instructions='" + instructions + '\'' +
                ", subject='" + subject + '\'' +
                ", source='" + source + '\'' +
                ", difficultyLevel=" + difficultyLevel +
                ", choices=" + choices +
                '}';
    }
}
