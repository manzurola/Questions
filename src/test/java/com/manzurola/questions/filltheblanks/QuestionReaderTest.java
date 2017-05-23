package com.manzurola.questions.filltheblanks;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by guym on 23/05/2017.
 */
public class QuestionReaderTest {

    @Test
    public void parse() throws IOException {
        String csvEntry = "$(A) cigarette is made of $() tobacco and $() paper.,Countables and Uncountables,   Living English Structure by W. Stannard Allen | Exercise 1.3 | Elementary";

        QuestionReader reader = new CSVQuestionReader(csvEntry);
        List<Question> questions = reader.read();
        Question actual = questions.get(0);

        Question expected = new Question(
                actual.getId(),
                "<?> cigarette is made of <?> tobacco and <?> paper.",
                Arrays.asList("A", "", ""),
                "<?>",
                "Countables and Uncountables",
                "Living English Structure by W. Stannard Allen | Exercise 1.3 | Elementary",
                "A cigarette is made of tobacco and paper.",
                "$(A) cigarette is made of $() tobacco and $() paper.",
                "v1");

        Assert.assertEquals(expected, actual);
    }
}
