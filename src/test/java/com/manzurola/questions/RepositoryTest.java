package com.manzurola.questions;

import com.manzurola.questions.data.Repository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by guym on 16/05/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class RepositoryTest {

    @Autowired
    private Repository repository;

    @Test
    public void addQuestion() throws Exception {


        List<FillInTheBlanks> questions = repository.searchQuestionsByAnswer("a", FillInTheBlanks.class);

        System.out.println(questions);
    }
}
