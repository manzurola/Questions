package com.manzurola.questions.filltheblanks;

import com.opencsv.CSVReader;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by guym on 21/05/2017.
 */
public class CSVQuestionReader implements QuestionReader {
    private final String BLANK_BODY_PLACEHOLDER = "(?)";
    private final Pattern BODY_PATTERN = Pattern.compile("(\\$\\((.*?)\\))");

    public List<Question> read(InputStream input) throws IOException {
        CSVReader reader = new CSVReader(new BufferedReader(new InputStreamReader(input)));
        List<String[]> strings = reader.readAll();
        List<Question> questions = new ArrayList<>();
        for (String[] string : strings) {
            Question question = parse(string);
            questions.add(question);
        }
        return questions;
    }

    private Question parse(String[] values) throws InvalidObjectException {
        final int expectedLength = 3;
        if (values.length != expectedLength)
            throw new InvalidObjectException(String.format("expecting [%d] values, found only [%d] in entry %s", expectedLength, values.length, Arrays.toString(values)));
        String body = values[0];
        String subject = values[1];
        String notes = values[2];

        StringBuffer actualBody = new StringBuffer();
        List<String> actualAnswer = new ArrayList<>();

        Matcher matcher = BODY_PATTERN.matcher(body);
        while (matcher.find()) {
            matcher.appendReplacement(actualBody, matcher.group(0).replaceFirst(Pattern.quote(matcher.group(1)), Matcher.quoteReplacement(BLANK_BODY_PLACEHOLDER)));
            actualAnswer.add(matcher.group(2));
        }
        matcher.appendTail(actualBody);

        return new Question(subject, actualBody.toString(), actualAnswer, notes);
    }

}
