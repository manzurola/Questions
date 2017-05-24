package com.manzurola.questions.filltheblanks;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by guym on 24/05/2017.
 */
public class CreateSolutionTest {

    @Test
    public void name() throws Exception {
        final Pattern BODY_PATTERN = Pattern.compile("(\\$\\((.*?)\\))");
        String originalSentence = "$(hello) there stranger ($) :)";

        Map<String, String> patternReplacements = new HashMap<>();
        patternReplacements.put(BODY_PATTERN.toString(), "");
        patternReplacements.put(" " + BODY_PATTERN.toString() + " ", " ");
        final Pattern pattern = Pattern.compile(" " + BODY_PATTERN + " |" + BODY_PATTERN);
        Matcher matcher = pattern.matcher(originalSentence);
        while (matcher.find()) {
            String replacement = patternReplacements.get(matcher.group(0));
            originalSentence = matcher.replaceFirst(replacement);
            matcher = pattern.matcher(originalSentence);
        }

        System.out.println(originalSentence);
    }
}
