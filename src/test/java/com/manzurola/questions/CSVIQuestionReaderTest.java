package com.manzurola.questions;

import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by guym on 21/05/2017.
 */
public class CSVIQuestionReaderTest {

    @Test
    public void readFile() throws Exception {
        InputStream input = this.getClass().getClassLoader().getResourceAsStream("questions-fill-in-the-blanks.csv");
        CSVQuestionReader reader = new CSVQuestionReader(new InputStreamReader(input));
        List<Question> questions = reader.readAll();
        System.out.println(questions);
    }
}
