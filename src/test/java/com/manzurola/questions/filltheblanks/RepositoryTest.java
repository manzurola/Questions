package com.manzurola.questions.filltheblanks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by guym on 16/05/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class RepositoryTest {

    @Autowired
    private Repository service;

    @Test
    public void addQuestion() throws Exception {

//        Question question = new Question("past simple", "I %?% a burger last year ", "ate");

//        service.addQuestion(question);

    }
}
