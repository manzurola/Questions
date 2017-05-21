package com.manzurola.questions.filltheblanks.search;

import com.manzurola.questions.filltheblanks.Application;
import com.manzurola.questions.filltheblanks.Question;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by guym on 19/05/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class SearchClientTest {


    @Autowired
    private SearchClient searchClient;

    @Test
        public void indexAndSearch() throws Exception {
//            Question question1 = new Question("past simple", "I %?% a burger last year guy", "ate");
//            Question question2 = new Question("past simple", "I %?% a burger last year guy", "ate hi");
//
//            searchClient.index(question1);
//            searchClient.index(question2);

            List<Question> questions = searchClient.search(new SearchQuery("guy", "hi", "simple"));

            System.out.println(questions.size());
            System.out.println(questions);
    }
}
