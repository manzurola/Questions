package com.manzurola.questions.filltheblanks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.InputStream;
import java.util.List;

/**
 * Created by guym on 21/05/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class DataLoadTest {

    @Autowired
    public Repository repository;

    @Test
    public void loadData() throws Exception {
        InputStream input = this.getClass().getClassLoader().getResourceAsStream("questions-fill-in-the-blanks.csv");
        CSVQuestionReader reader = new CSVQuestionReader(input);
        List<Question> questions = reader.read();
        repository.addQuestions(questions);
    }
}
