package com.manzurola.questions.filltheblanks;

import com.opencsv.CSVReader;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by guym on 21/05/2017.
 */
public class CSVQuestionReader implements QuestionReader {
    private static final String BLANK_BODY_PLACEHOLDER = "<?>";
    private static final Pattern BODY_PATTERN = Pattern.compile("(\\$\\((.*?)\\))");
    private static final int EXPECTED_NUM_OF_VALUES_IN_ENTRY = 3;
    private static final String version = "v1";

    private final CSVReader reader;

    public CSVQuestionReader(InputStream input) {
        this.reader = new CSVReader(new InputStreamReader(input));
    }

    public CSVQuestionReader(Reader reader) {
        this.reader = new CSVReader(reader);
    }

    public CSVQuestionReader(String value) {
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
        for (String[] string : strings) {
            Question question = parse(string);
            questions.add(question);
        }
        return questions;
    }

    @Override
    public String getVersion() {
        return version;
    }

    private Question parse(String[] values) throws InvalidObjectException {
        if (values.length != EXPECTED_NUM_OF_VALUES_IN_ENTRY)
            throw new InvalidObjectException(String.format("expecting [%d] values, found only [%d] in entry %s", EXPECTED_NUM_OF_VALUES_IN_ENTRY, values.length, Arrays.toString(values)));
        String body = values[0].trim();
        String subject = values[1].trim();
        String notes = values[2].trim();

        StringBuffer bodyBuffer = new StringBuffer();
        List<String> actualAnswer = new ArrayList<>();

        Matcher matcher = BODY_PATTERN.matcher(body);
        while (matcher.find()) {
            matcher.appendReplacement(bodyBuffer, matcher.group(0).replaceFirst(Pattern.quote(matcher.group(1)), Matcher.quoteReplacement(BLANK_BODY_PLACEHOLDER)));
            String answer = matcher.group(2);
            actualAnswer.add(answer);
        }
        matcher.appendTail(bodyBuffer);

        String actualBody = bodyBuffer.toString();
        return new Question(actualBody, actualAnswer, BLANK_BODY_PLACEHOLDER, subject, notes, createSolution(actualBody, actualAnswer), body, version);
    }

    private String createSolution(String body, List<String> answerKey) {
        Map<String, String> patternReplacements = new HashMap<>();
        patternReplacements.put(BLANK_BODY_PLACEHOLDER, "");
        patternReplacements.put(" " + BLANK_BODY_PLACEHOLDER + " ", " ");
        final Pattern pattern = Pattern.compile(" " + Pattern.quote(BLANK_BODY_PLACEHOLDER) + " |" + Pattern.quote(BLANK_BODY_PLACEHOLDER));
        String solution = body;
        for (String answer : answerKey) {
            Matcher matcher = pattern.matcher(solution);
            if (matcher.find()) {
                String replacement = answer.isEmpty() ? patternReplacements.get(matcher.group(0)) : answer;
                solution = matcher.replaceFirst(replacement);
            }
        }
        return solution;
    }


}
