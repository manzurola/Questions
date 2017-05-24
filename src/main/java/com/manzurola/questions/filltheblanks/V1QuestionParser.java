package com.manzurola.questions.filltheblanks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by guym on 24/05/2017.
 */
public class V1QuestionParser implements QuestionParser {
    private static final String VERSION = "v1";
    private static final String BLANK_BODY_PLACEHOLDER = "<?>";
    private static final Pattern BODY_PATTERN = Pattern.compile("(\\$\\((.*?)\\))");

    @Override
    public Question parseSentence(String sentence) {
        StringBuffer bodyBuffer = new StringBuffer();
        List<String> actualAnswer = new ArrayList<>();

        Matcher matcher = BODY_PATTERN.matcher(sentence);
        while (matcher.find()) {
            matcher.appendReplacement(bodyBuffer, matcher.group(0).replaceFirst(Pattern.quote(matcher.group(1)), Matcher.quoteReplacement(BLANK_BODY_PLACEHOLDER)));
            String answer = matcher.group(2);
            actualAnswer.add(answer);
        }
        matcher.appendTail(bodyBuffer);

        String actualBody = bodyBuffer.toString();
        return new Question(actualBody, actualAnswer, BLANK_BODY_PLACEHOLDER, createSolution(actualBody, actualAnswer), sentence, VERSION);
    }

    @Override
    public String getVersion() {
        return VERSION;
    }

    private String createSolution(String body, List<String> answerKey) {
        Map<String, String> patternReplacements = new HashMap<>();
        patternReplacements.put(BLANK_BODY_PLACEHOLDER, "");
        patternReplacements.put(" " + BLANK_BODY_PLACEHOLDER + " ", " ");
        final Pattern pattern = Pattern.compile(" " + Pattern.quote(BLANK_BODY_PLACEHOLDER) + " |" + Pattern.quote(BLANK_BODY_PLACEHOLDER));
        String solution = body;
        for (String answer : answerKey) {
            Matcher matcher = pattern.matcher(solution);
            if (matcher.find()) {
                String replacement = answer.isEmpty() ? patternReplacements.get(matcher.group(0)) : answer;
                solution = matcher.replaceFirst(replacement);
            }
        }
        return solution;
    }
}
