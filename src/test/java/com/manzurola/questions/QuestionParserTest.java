package com.manzurola.questions;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by guym on 24/05/2017.
 */
public class QuestionParserTest {
    private final QuestionParser parser = new FillInTheBlanksParser();

    @Test
    public void parseText() throws Exception {
        String[] values = new String[]{"$(A) cigarette is made $(of) tobacco and $()   paper ", "a subject", "some notes"};
        String expectedBody = "<?> cigarette is made <?> tobacco and <?>   paper ";
        List<String> expectedAnswerKey = Arrays.asList("A", "of", "");

        Question question = parser.parseQuestion(values);

        Assert.assertEquals(expectedBody, question.getBody());
        Assert.assertEquals(expectedAnswerKey, question.getAnswerKey());

    }
}
