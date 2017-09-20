package com.manzurola.questions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manzurola.questions.game.BasicDifficultyClassifier;
import com.manzurola.questions.game.DifficultyClassifier;
import com.manzurola.questions.game.GameQuestion;
import com.manzurola.questions.game.WordFrequencyImpl;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by guym on 21/07/2017.
 */
public class LoadScoreAndWriteTest {

    @Test
    public void run() throws Exception {

        // load questions

        List<FillInTheBlanks> questions = loadQuestions();

        // convert to game questions

        List<GameQuestion> gameQuestions = convertToGameQuestions(questions);

        // score questions and sort by difficulty level

        List<GameQuestion> sorted = scoreAndSort(gameQuestions);

        // write to json file

        ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(new File("questions"), sorted);
    }

    private List<FillInTheBlanks> loadQuestions() throws IOException {
        InputStream input = this.getClass().getClassLoader().getResourceAsStream("questions-en-fill-in-the-blanks.csv");
        FillInTheBlanksReader reader = new FillInTheBlanksReader(new InputStreamReader(input), true);
        return reader.readAll();
    }

    private List<GameQuestion> convertToGameQuestions(List<FillInTheBlanks> questions) {
        // for each question
        //      for each blank
        //          create single choice question
        //
        // for each single choice question
        //      create game question
        List<GameQuestion> gameQuestions = new ArrayList<>();

        for (FillInTheBlanks question : questions) {
            for (int i = 0; i < question.numOfBlanks(); i++) {
                SingleChoiceFillInTheBlanks singleChoice = new SingleChoiceFillInTheBlanks(question, i);
                GameQuestion gameQuestion = new GameQuestion(singleChoice);
                gameQuestions.add(gameQuestion);
            }
        }

        return gameQuestions;
    }

    private List<GameQuestion> scoreAndSort(List<GameQuestion> questions) throws Exception {
        String filename = "language-data/english/google-20k-english.txt";
        String path = this.getClass().getClassLoader().getResource(filename).getPath();
        DifficultyClassifier classifier = new BasicDifficultyClassifier(new WordFrequencyImpl(path));
        for (GameQuestion question : questions) {
            double difficulty = classifier.classify(question);
            question.setDifficulty(difficulty);
        }

        questions.sort(Comparator.comparing(q -> (q.difficulty())));

        return questions;
    }
}
