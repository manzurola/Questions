package com.manzurola.questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by guym on 24/05/2017.
 */
public class FillInTheBlanksParser implements QuestionParser<FillInTheBlanks> {
    private static final String VERSION = "v1";
    private static final String BLANK_TOKEN = "<?>";
    private static final Pattern CONTENT_PATTERN = Pattern.compile("(\\$\\((.*?)\\))");
    private static final int VALUE_COUNT = 4;

    public FillInTheBlanks parseQuestion(String[] values) {
        if (values.length != VALUE_COUNT) {
            throw new IllegalArgumentException(String.format("expecting [%d] values, found only [%d] in entry %s", VALUE_COUNT, values.length, Arrays.toString(values)));
        }

        String content = values[0].trim();
        String subject = values[1].trim();
        String instructions = values[2].trim();
        String source = values[3].trim();

        StringBuffer bodyBuf = new StringBuffer();
        List<String> answerKey = new ArrayList<>();

        Matcher matcher = CONTENT_PATTERN.matcher(content);
        while (matcher.find()) {
            matcher.appendReplacement(bodyBuf, matcher.group(0).replaceFirst(Pattern.quote(matcher.group(1)), Matcher.quoteReplacement(BLANK_TOKEN)));
            String answer = matcher.group(2);
            answerKey.add(answer);
        }
        matcher.appendTail(bodyBuf);

        return new FillInTheBlanks(bodyBuf.toString(), answerKey, subject, instructions, source, VERSION, BLANK_TOKEN);
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
