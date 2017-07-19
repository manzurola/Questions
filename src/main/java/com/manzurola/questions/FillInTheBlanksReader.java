package com.manzurola.questions;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by guym on 16/06/2017.
 */
public class FillInTheBlanksReader implements QuestionReader<FillInTheBlanks> {
    private static final String BLANK_TOKEN = "<?>";
    private static final Pattern CONTENT_PATTERN = Pattern.compile("(\\{(.*?)\\})");
    private static final int VALUE_COUNT = 5;

    private final CSVReader reader;

    public FillInTheBlanksReader(Reader reader, boolean ignoreFirstLine) {
        this.reader = new CSVReader(reader, ',', '\"', 1);
    }

    @Override
    public List<FillInTheBlanks> readAll() throws IOException {
        List<FillInTheBlanks> questions = new ArrayList<>();
        for (String[] values : reader) {
            questions.add(parseValues(values));
        }

        return questions;
    }

    protected FillInTheBlanks parseValues(String[] values) throws IOException {

        if (values.length != VALUE_COUNT) {
            throw new IllegalArgumentException(String.format("expecting [%d] values, found only [%d] in entry [%s]", VALUE_COUNT, values.length, Arrays.toString(values)));
        }

        String content = values[0].trim();
        String subject = values[1].trim();
        String instructions = values[2].trim();
        String source = values[3].trim();
        int difficultyLevel = Integer.valueOf(values[4].trim());

        StringBuffer bodyBuf = new StringBuffer();
        List<String> answerKey = new ArrayList<>();
        List<Choice> choices = new ArrayList<>();
        int blankIndex = 0;

        Matcher matcher = CONTENT_PATTERN.matcher(content);
        while (matcher.find()) {
            matcher.appendReplacement(bodyBuf, matcher.group(0).replaceFirst(Pattern.quote(matcher.group(1)), Matcher.quoteReplacement(BLANK_TOKEN)));
            String answer = matcher.group(2);
            String[] words = answer.split("/");
            for (String word : words) {
                boolean correct = false;
                word = word.trim();
                if (word.startsWith("[") && word.endsWith("]")) {   // dummy
                    word = word.substring(1, word.length() - 1).trim();
                } else {
                    answerKey.add(word);
                    correct = true;
                }
                choices.add(new Choice(word, blankIndex, correct));
            }
            blankIndex++;
        }
        matcher.appendTail(bodyBuf);


        return new FillInTheBlanks(bodyBuf.toString(), answerKey, subject, instructions, source, difficultyLevel, choices, BLANK_TOKEN);
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }

}
