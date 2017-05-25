package com.manzurola.questions;

import com.opencsv.CSVReader;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by guym on 21/05/2017.
 */
public class CSVQuestionReader implements QuestionReader {

    private final CSVReader reader;
    private final QuestionParser parser = new FillInTheBlanksParser();

    public CSVQuestionReader(Reader reader) {
        this.reader = new CSVReader(reader);
    }

    public List<Question> readAll() throws IOException {
        List<Question> questions = new ArrayList<>();
        for (String[] values : reader) {
            Question question = parser.parseQuestion(values);
            questions.add(question);
        }
        return questions;
    }

    @Override
    public Question readNext() throws IOException {
        return parser.parseQuestion(reader.readNext());
    }

    @Override
    public String getParserVersion() {
        return parser.getVersion();
    }


    @Override
    public void close() throws IOException {
        reader.close();
    }

    @Override
    public Iterator<Question> iterator() {
        return new CsvQuestionIterator(reader.iterator(), parser);
    }

}
