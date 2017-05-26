package com.manzurola.questions;

import java.util.Arrays;

/**
 * Created by guym on 26/05/2017.
 */
public class RewriteTheSentenceParser implements QuestionParser<RewriteTheSentence> {
    private static final String VERSION = "v1";
    private static final String QUESTION_TYPE = "rewrite-the-sentence";
    private static final int VALUE_COUNT = 5;

    public RewriteTheSentence parseQuestion(String[] values) {
        if (values.length != VALUE_COUNT) {
            throw new IllegalArgumentException(String.format("expecting [%d] values, found only [%d] in entry %s", VALUE_COUNT, values.length, Arrays.toString(values)));
        }

        String sentence = values[0].trim();
        String answerKey = values[1].trim();
        String subject = values[2].trim();
        String instructions = values[3].trim();
        String source = values[4].trim();

        return new RewriteTheSentence(sentence, Arrays.asList(answerKey), subject, instructions, source, VERSION);
    }

    @Override
    public String getVersion() {
        return VERSION;
    }
}
