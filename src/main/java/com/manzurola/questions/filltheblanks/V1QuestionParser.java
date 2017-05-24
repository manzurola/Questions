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

    public Question parseText(String text) {
        StringBuffer body = new StringBuffer();
        List<String> answerKey = new ArrayList<>();

        Matcher matcher = BODY_PATTERN.matcher(text);
        while (matcher.find()) {
            matcher.appendReplacement(body, matcher.group(0).replaceFirst(Pattern.quote(matcher.group(1)), Matcher.quoteReplacement(BLANK_BODY_PLACEHOLDER)));
            String answer = matcher.group(2);
            answerKey.add(answer);
        }
        matcher.appendTail(body);

        return new Question(body.toString(), answerKey, BLANK_BODY_PLACEHOLDER, createSolution(text), text, VERSION);
    }

    @Override
    public String getVersion() {
        return VERSION;
    }

    private String createSolution(String sentence) {
        Matcher matcher = BODY_PATTERN.matcher(sentence);
        StringBuffer buf = new StringBuffer();
        while (matcher.find()) {
            String answer = matcher.group(2);
            matcher.appendReplacement(buf, matcher.group(0).replaceFirst(Pattern.quote(matcher.group(1)), answer));
        }
        matcher.appendTail(buf);
        String solution = buf.toString();
        solution = solution.replaceAll("( ){2,}", " ").replaceAll("^( )", "").replaceAll("( )$", "");
        return solution;
    }
}
