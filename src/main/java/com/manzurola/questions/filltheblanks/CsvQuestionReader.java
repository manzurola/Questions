package com.manzurola.questions.filltheblanks;

import com.opencsv.CSVReader;

import java.io.*;
import java.util.*;

/**
 * Created by guym on 21/05/2017.
 */
public class CsvQuestionReader implements QuestionReader {
    private static final int EXPECTED_NUM_OF_VALUES_IN_ENTRY = 3;

    private final CSVReader reader;

    public CsvQuestionReader(InputStream input) {
        this.reader = new CSVReader(new InputStreamReader(input));
    }

    public CsvQuestionReader(Reader reader) {
        this.reader = new CSVReader(reader);
    }

    public CsvQuestionReader(String value) {
        this.reader = new CSVReader(new StringReader(value));
    }

    public List<Question> read() throws IOException {
        List<String[]> strings;
        try {
            strings = reader.readAll();
        } finally {
            reader.close();
        }
        List<Question> questions = new ArrayList<>();
        for (String[] values : strings) {
            if (values.length != EXPECTED_NUM_OF_VALUES_IN_ENTRY)
                throw new InvalidObjectException(String.format("expecting [%d] values, found only [%d] in entry %s", EXPECTED_NUM_OF_VALUES_IN_ENTRY, values.length, Arrays.toString(values)));

            String body = values[0].trim();
            String subject = values[1].trim();
            String notes = values[2].trim();

            Question question = new Question(body, subject, notes);
            questions.add(question);
        }
        return questions;
    }
}
