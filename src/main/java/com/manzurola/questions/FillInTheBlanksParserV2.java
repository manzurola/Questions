package com.manzurola.questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by guym on 15/06/2017.
 */
public class FillInTheBlanksParserV2 implements QuestionParser<MultiChoiceFillInTheBlanks> {
    private static final String VERSION = "v2";
    private static final String BLANK_TOKEN = "<?>";
    private static final Pattern CONTENT_PATTERN = Pattern.compile("(\\{(.*?)\\})");
    private static final int VALUE_COUNT = 4;

    public MultiChoiceFillInTheBlanks parseQuestion(String[] values) {
        if (values.length != VALUE_COUNT) {
            throw new IllegalArgumentException(String.format("expecting [%d] values, found only [%d] in entry %s", VALUE_COUNT, values.length, Arrays.toString(values)));
        }

        String content = values[0].trim();
        String subject = values[1].trim();
        String instructions = values[2].trim();
        String source = values[3].trim();

        StringBuffer bodyBuf = new StringBuffer();
        List<String> answerKey = new ArrayList<>();
        List<Choice> choices = new ArrayList<>();
        int blankIndex = 0;

        Matcher matcher = CONTENT_PATTERN.matcher(content);
        while (matcher.find()) {
            matcher.appendReplacement(bodyBuf, matcher.group(0).replaceFirst(Pattern.quote(matcher.group(1)), Matcher.quoteReplacement(BLANK_TOKEN)));
            String answer = matcher.group(2);
            String[] words = answer.split(",");
            for (String word : words) {
                word = word.trim();
                if (word.startsWith("[") && word.endsWith("]")) {   // dummy
                    word = word.substring(1, word.length() - 1).trim();
                } else {
                    answerKey.add(word);
                }
                choices.add(new Choice(word, blankIndex));
            }
            blankIndex++;
        }
        matcher.appendTail(bodyBuf);

        return new MultiChoiceFillInTheBlanks(bodyBuf.toString(), answerKey, subject, instructions, source, VERSION, BLANK_TOKEN, choices);
    }

    @Override
    public String getVersion() {
        return VERSION;
    }

    private String createSolution(String sentence) {
        Matcher matcher = CONTENT_PATTERN.matcher(sentence);
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
