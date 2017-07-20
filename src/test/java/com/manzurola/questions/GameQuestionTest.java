package com.manzurola.questions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.manzurola.questions.game.GameQuestion;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by guym on 19/07/2017.
 */
public class GameQuestionTest {

    @Test
    public void printQuestion() throws Exception {
        InputStream input = this.getClass().getClassLoader().getResourceAsStream("questions-en-fill-in-the-blanks.csv");
        FillInTheBlanksReader reader = new FillInTheBlanksReader(new InputStreamReader(input), true);
        List<FillInTheBlanks> questions = reader.readAll();


        FillInTheBlanks question = questions.get(0);
        List<FillInTheBlanks> fillInTheBlankss = question.asSingleChoice();
        System.out.println(fillInTheBlankss);

        GameQuestion gameQuestion = new GameQuestion(question);
        System.out.println(new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT).writeValueAsString(gameQuestion));

    }
}
