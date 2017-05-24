package com.manzurola.questions.filltheblanks;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by guym on 24/05/2017.
 */
public class QuestionTest {

    @Test
    public void sentenceWithMissingWords() throws Exception {
        String sentence = "$(A) cigarette is made $(of) tobacco and $()   paper ";
        String expectedBody = "<?> cigarette is made <?> tobacco and <?>   paper ";
        String expectedSolution = "A cigarette is made of tobacco and paper";
        List<String> expectedAnswerKey = Arrays.asList("A", "of", "");

        Question question = new Question(sentence);

        Assert.assertEquals(expectedBody, question.getBody());
        Assert.assertEquals(expectedSolution, question.getSolution());
        Assert.assertEquals(expectedAnswerKey, question.getAnswerKey());

    }
}
