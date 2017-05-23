package com.manzurola.questions.filltheblanks;

import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * Created by guym on 21/05/2017.
 */
public class CSVQuestionReaderTest {

    @Test
    public void readFile() throws Exception {
        InputStream input = this.getClass().getClassLoader().getResourceAsStream("questions-fill-in-the-blanks.csv");
        CSVQuestionReader reader = new CSVQuestionReader();
        List<Question> questions = reader.read(input);
        System.out.println(questions);
    }
}
