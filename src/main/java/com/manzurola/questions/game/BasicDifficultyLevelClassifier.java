package com.manzurola.questions.game;

import com.manzurola.questions.Question;

import java.util.Arrays;

/**
 * Created by guym on 17/07/2017.
 */
public class BasicDifficultyLevelClassifier implements DifficultyLevelClassifier {

    private final double blankWordWeight = 0.8;
    private final double regularWordWeight = 0.2;
    private final double wordFrequencyWeight = 0.6;
    private final double assignedDifficultyWeight = 0.4;

    private WordFrequency wordFrequency;

    @Override
    public double classify(Question question) {

        // split body words and lower case all
        String[] bodyWords = question.getBody().split(" ");
        Arrays.stream(bodyWords).forEach(String::toLowerCase);

        // answer words
        String[] answerWords = new String[question.getAnswerKey().size()];
        answerWords = question.getAnswerKey().toArray(answerWords);
        Arrays.stream(answerWords).forEach(String::toLowerCase);

        //
        double answerFrequencies = 0;
        for (String word : answerWords) {
            double frequency = wordFrequency.get(word);
            answerFrequencies += frequency;
        }
        answerFrequencies /= answerWords.length;

        return 0;
    }

}
