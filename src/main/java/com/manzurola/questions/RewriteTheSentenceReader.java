package com.manzurola.questions;

import com.opencsv.CSVReader;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrTokenizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by guym on 17/06/2017.
 */
public class RewriteTheSentenceReader implements QuestionReader<RewriteTheSentence> {
    private static final int VALUE_COUNT = 5;

    private final CSVReader reader;

    public RewriteTheSentenceReader(Reader reader) {
        this.reader = new CSVReader(reader);
    }

    @Override
    public List<RewriteTheSentence> readAll() throws IOException {
        List<String[]> lines = reader.readAll();
        List<RewriteTheSentence> questions = new ArrayList<>();
        for (String[] line : lines) {
            RewriteTheSentence question = parseQuestion(line);
            questions.add(question);
        }
        return questions;
    }

    public RewriteTheSentence parseQuestion(String[] values) {
        if (values.length != VALUE_COUNT) {
            throw new IllegalArgumentException(String.format("expecting [%d] values, found only [%d] in entry %s", VALUE_COUNT, values.length, Arrays.toString(values)));
        }

        String sentence = values[0].trim();
        String answerKey = values[1].trim();
        String subject = values[2].trim();
        String instructions = values[3].trim();
        String source = values[4].trim();
        int difficultyLevel = Integer.valueOf(values[5].trim());

        // create choices
        String[] answers = answerKey.split("/");
        List<Choice> choices = new ArrayList<>();
        for (String answer : answers) {

        }

        return new RewriteTheSentence(sentence, Arrays.asList(answerKey), subject, instructions, source, difficultyLevel, choices);
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }

//    private List<String> tokenize(String value) {
//        new StrTokenizer()
//    }

}
