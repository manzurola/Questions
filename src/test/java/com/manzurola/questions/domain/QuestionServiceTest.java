package com.manzurola.questions.domain;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by guym on 16/05/2017.
 */

public class QuestionServiceTest {

    private QuestionService service;

    public QuestionServiceTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        service = context.getBean(QuestionService.class);
    }

    @Test
    public void addQuestion() throws Exception {

        Question question = new FillInTheBlanks("past simple", "I %?% a burger last year ", "ate", "%?%");

        service.addQuestion(question);

    }
}