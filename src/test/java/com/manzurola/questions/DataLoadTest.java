package com.manzurola.questions;

import com.manzurola.questions.data.Repository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.InputStream;
import java.io.InputStreamReader;
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
    public void loadFillInTheBlanks() throws Exception {
        InputStream input = this.getClass().getClassLoader().getResourceAsStream("questions_en_living_english_structure - fill-in-the-blanks.csv");
        CSVQuestionReader<FillInTheBlanks> reader = new CSVQuestionReader<>(new InputStreamReader(input), new FillInTheBlanksParserV1());
        List<FillInTheBlanks> questions = reader.readAll();
        repository.addQuestions(questions);
    }

    @Test
    public void loadRwrite() throws Exception {
        InputStream input = this.getClass().getClassLoader().getResourceAsStream("questions_en_living_english_structure - rewrite.csv");
        CSVQuestionReader<RewriteTheSentence> reader = new CSVQuestionReader<>(new InputStreamReader(input), new RewriteTheSentenceParser());
        List<RewriteTheSentence> questions = reader.readAll();
        repository.addQuestions(questions);

    }
}
