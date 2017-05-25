package com.manzurola.questions;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by guym on 21/05/2017.
 */
public class ParseIQuestionTest {

    @Test
    public void parseVer1() throws Exception {
        // extract answers from blanks and rebuild the sentence with empty placeholder instead of the blanks
        String value = ":?a?: cigarette is made :?of?: tobacco and :??: paper";
        String expectedBody = ":??: cigarette is made :??: tobacco and :??: paper";
        List<String> expectedAnswer = Arrays.asList("a", "of", "");
        String blankPlaceholder = ":??:";

//        Pattern pattern = Pattern.compile(".*?(\\$\\{(.*?)}).*?");
        Pattern pattern = Pattern.compile("(:\\?(.*?)\\?:)");
        StringBuffer actualBody = new StringBuffer();
        List<String> actualAnswer = new ArrayList<>();

        Matcher matcher = pattern.matcher(value);
        while (matcher.find()) {
            matcher.appendReplacement(actualBody, matcher.group(0).replaceFirst(Pattern.quote(matcher.group(1)), blankPlaceholder));
            actualAnswer.add(matcher.group(2));
        }
        matcher.appendTail(actualBody);

        Assert.assertEquals(expectedBody,actualBody.toString());
        Assert.assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void parseVer2() throws Exception {
        // extract answers from blanks and rebuild the sentence with empty placeholder instead of the blanks
        String value = "$(a) cigarette is made $(of) tobacco and $() paper";
        String blankPlaceholder = "(?)";
        String expectedBody = String.format("%s cigarette is made %s tobacco and %s paper", blankPlaceholder, blankPlaceholder, blankPlaceholder);
        List<String> expectedAnswer = Arrays.asList("a", "of", "");

        Pattern pattern = Pattern.compile("(\\$\\((.*?)\\))");
        StringBuffer actualBody = new StringBuffer();
        List<String> actualAnswer = new ArrayList<>();

        Matcher matcher = pattern.matcher(value);
        while (matcher.find()) {
            matcher.appendReplacement(actualBody, matcher.group(0).replaceFirst(Pattern.quote(matcher.group(1)), Matcher.quoteReplacement(blankPlaceholder)));
            actualAnswer.add(matcher.group(2));
        }
        matcher.appendTail(actualBody);

        Assert.assertEquals(expectedBody,actualBody.toString());
        Assert.assertEquals(expectedAnswer, actualAnswer);
    }
}
