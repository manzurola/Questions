package com.manzurola.questions;

import com.manzurola.questions.fillintheblanks.FillInTheBlanks;
import com.manzurola.questions.fillintheblanks.FillInTheBlanksReader;
import com.manzurola.questions.game.Sentence;
import com.manzurola.questions.game.SingleChoiceSentenceGenerator;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by guym on 21/05/2017.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = Application.class)
public class DataLoadTest {

//    @Autowired
//    public Repository repository;

    @Test
    public void loadFillInTheBlanks() throws Exception {
        InputStream input = this.getClass().getClassLoader().getResourceAsStream("questions-en-fill-in-the-blanks.csv");
        FillInTheBlanksReader reader = new FillInTheBlanksReader(new InputStreamReader(input), true);
        List<FillInTheBlanks> questions = reader.readAll();
        System.out.println(questions);
    }

    @Test
    public void generateSentences() throws Exception {
        InputStream input = this.getClass().getClassLoader().getResourceAsStream("questions-en-fill-in-the-blanks.csv");
        FillInTheBlanksReader reader = new FillInTheBlanksReader(new InputStreamReader(input), true);
        List<FillInTheBlanks> questions = reader.readAll();

        for (FillInTheBlanks question : questions) {
//            System.out.println(question);
            System.out.println(String.format("sentences for question with body [%s] and choices [%s]:", question.getBody(), question.getChoices()));
            List<Sentence> sentences = new SingleChoiceSentenceGenerator().generate(question);
            for (Sentence sentence : sentences) {
                System.out.println(sentence);
//                System.out.println(sentence.getParts());
            }
        }


    }

    //    @Test
//    public void loadRwrite() throws Exception {
//        InputStream input = this.getClass().getClassLoader().getResourceAsStream("questions_en_living_english_structure - rewrite.csv");
//        CSVQuestionReader<RewriteTheSentence> reader = new CSVQuestionReader<>(new InputStreamReader(input), new RewriteTheSentenceParser());
//        List<RewriteTheSentence> questions = reader.readAll();
//        repository.addQuestions(questions);
//
//    }
}
