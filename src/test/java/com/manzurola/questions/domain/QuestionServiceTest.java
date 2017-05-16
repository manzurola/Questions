package com.manzurola.questions.domain;

import org.junit.Test;

/**
 * Created by guym on 16/05/2017.
 */
public class QuestionServiceTest {

    @Test
    public void addQuestion() throws Exception {

        QuestionServiceImpl service = new QuestionServiceImpl();

        Question question = new Question(null, "blanks", "past simple", "Last night I %?% to the store", "went");

        service.addQuestion(question);

    }
}
