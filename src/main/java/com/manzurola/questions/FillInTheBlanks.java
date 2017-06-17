package com.manzurola.questions;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;

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
                           @JsonProperty("difficulyLevel") int difficultyLevel,
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
