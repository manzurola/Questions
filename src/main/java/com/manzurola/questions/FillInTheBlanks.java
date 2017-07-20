package com.manzurola.questions;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by guym on 15/06/2017.
 */
public class FillInTheBlanks extends Question {

    private final String blankToken;

    public FillInTheBlanks(String body,
                           List<String> answerKey,
                           String subject,
                           String instructions,
                           String source,
                           int difficultyLevel,
                           List<Choice> choices,
                           String blankToken) {
        super(body, answerKey, subject, instructions, source, difficultyLevel, choices);
        this.blankToken = blankToken;
    }

    public FillInTheBlanks(@JsonProperty("id") String id,
                           @JsonProperty("body") String body,
                           @JsonProperty("answerKey") List<String> answerKey,
                           @JsonProperty("subject") String subject,
                           @JsonProperty("instructions") String instructions,
                           @JsonProperty("source") String source,
                           @JsonProperty("difficultyLevel") int difficultyLevel,
                           @JsonProperty("choices") List<Choice> choices,
                           @JsonProperty("blankToken") String blankToken) {
        super(id, body, answerKey, subject, instructions, source, difficultyLevel, choices);
        this.blankToken = blankToken;
    }

    public FillInTheBlanks(FillInTheBlanks copy) {
        super(copy);
        this.blankToken = copy.blankToken;
    }

    public String getBlankToken() {
        return blankToken;
    }

    public int numOfBlanks() {
        Set<Integer> uniqueChoiceIndices = new HashSet<>();
        for (Choice choice : getChoices()) {
            uniqueChoiceIndices.add(choice.getIndex());
        }
        return uniqueChoiceIndices.size();
    }

    public Choice getCorrectChoiceForBlank(int index) {
        List<Choice> choices = getChoicesAt(index);
        for (Choice choice : choices) {
            if (choice.isCorrect()) return choice;
        }
        return null;
    }

    public Choice getCorrectChoiceForBlankAtSentencePart(int sentencePartIndex) {
        return getChoicesForBlankAtSentencePart(
                sentencePartIndex
        ).stream().filter(Choice::isCorrect).findFirst().get();
    }

    public List<Choice> getChoicesForBlankAtSentencePart(int sentencePartIndex) {
        List<String> parts = splitSentence();
        for (int i = 0, blankCount = 0; i < parts.size(); i++) {
            // if index refers to an actual blank
            if (getBlankToken().equals(parts.get(i)) && i == sentencePartIndex) {
                // get the choices of the blank at blankCount
                return getChoicesAt(blankCount);
            } else if (getBlankToken().equals(parts.get(i))) {
                blankCount++;
            }
        }
        return new ArrayList<>();
    }

    public List<String> splitSentence() {
        String quote = Pattern.quote(getBlankToken());
        return Arrays.asList(getBody().split(String.format("(?=%s)|(?<=%s)", quote, quote)));
    }

    public boolean isBlank(String value) {
        return getBlankToken().equals(value);
    }

    public List<FillInTheBlanks> asSingleChoice() {
        List<FillInTheBlanks> questions = new ArrayList<>();
        for (int i = 0; i < numOfBlanks(); i++) {
            questions.add(new SingleChoiceFillInTheBlanks(this, i));
        }
        return questions;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FillInTheBlanks that = (FillInTheBlanks) o;
        return Objects.equals(blankToken, that.blankToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), blankToken);
    }

    @Override
    public String toString() {
        return "FillInTheBlanks{" +
                "blankToken='" + blankToken + '\'' +
                ", numOfBlanks=" + numOfBlanks() +
                "} " + super.toString();
    }

    //    private String createSolution(String sentence) {
//        Matcher matcher = CONTENT_PATTERN.matcher(sentence);
//        StringBuffer buf = new StringBuffer();
//        while (matcher.find()) {
//            String answer = matcher.group(2);
//            matcher.appendReplacement(buf, matcher.group(0).replaceFirst(Pattern.quote(matcher.group(1)), answer));
//        }
//        matcher.appendTail(buf);
//        String solution = buf.toString();
//        solution = solution.replaceAll("( ){2,}", " ").replaceAll("^( )", "").replaceAll("( )$", "");
//        return solution;
//    }
}
