package com.manzurola.questions.game;

import com.manzurola.questions.Choice;
import com.manzurola.questions.FillInTheBlanks;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.LowerCaseTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guym on 17/07/2017.
 */
public class BasicDifficultyClassifier implements DifficultyClassifier {

    private final double blankWordWeight = 0.7;
    private final double regularWordWeight = 0.1;
    private final double assignedDifficultyWeight = 0.2;

    private WordFrequency wordFrequency;
    private Analyzer analyzer;

    public BasicDifficultyClassifier(WordFrequency wordFrequency) {
        this.wordFrequency = wordFrequency;
        analyzer = new Analyzer() {
            @Override
            protected TokenStreamComponents createComponents(String s) {
                Tokenizer source = new LowerCaseTokenizer();
                return new TokenStreamComponents(source);
            }
        };
    }

    @Override
    public double classify(GameQuestion question) throws Exception {
//
//        FillInTheBlanks fillInTheBlanks = question.getFillInTheBlanks();
//
//        // split body words and lower case all
//        String[] bodyWords = fillInTheBlanks.getBody().split(" ");
//        Arrays.stream(bodyWords).forEach(String::toLowerCase);
//
//        // answer words
//        List<Choice> choices = fillInTheBlanks.getChoices();
//
//
//
//        String[] answerWords = new String[question.getAnswerKey().size()];
//        answerWords = question.getAnswerKey().toArray(answerWords);
//        Arrays.stream(answerWords).forEach(String::toLowerCase);
//
//        //
//        double answerFrequencies = 0;
//        for (String word : answerWords) {
//            double frequency = wordFrequency.get(word);
//            answerFrequencies += frequency;
//        }
//        answerFrequencies /= answerWords.length;
//

        double choiceDifficulty = 0;
        double sentenceDifficulty = 0;
        double assignedDifficulty = question.getFillInTheBlanks().getDifficultyLevel() / 3;

//        try {
        List<String> choiceWords = collectChoiceWords(question.getFillInTheBlanks());
        List<String> sentenceWords = collectSentenceWords(question.getFillInTheBlanks());

        for (String choiceWord : choiceWords) {
            double frequency = 1 - wordFrequency.get(choiceWord);
            choiceDifficulty += frequency;
        }
        choiceDifficulty /= (double) choiceWords.size();


        for (String sentenceWord : sentenceWords) {
            double frequency = 1 - wordFrequency.get(sentenceWord);
            sentenceDifficulty += frequency;
        }

        sentenceDifficulty /= (double) sentenceWords.size();

        double difficulty = choiceDifficulty * blankWordWeight +
                sentenceDifficulty * regularWordWeight +
                assignedDifficulty * assignedDifficultyWeight;
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

        return difficulty;
    }

    private List<String> collectChoiceWords(FillInTheBlanks question) throws IOException {
        List<String> result = new ArrayList<>();
        List<Choice> choices = question.getChoices();
        for (Choice choice : choices) {
            List<String> tokens = tokenize(choice.getText());
            result.addAll(tokens);
        }
        return result;
    }

    private List<String> collectSentenceWords(FillInTheBlanks question) throws IOException {
        String body = question.getBody();
        List<String> tokens = tokenize(body);
        return tokens;
    }

    private List<String> tokenize(String text) throws IOException {

        TokenStream stream = analyzer.tokenStream("", new StringReader(text));
        stream.reset();

        List<String> result = new ArrayList<>();

        while (stream.incrementToken()) {
            String term = stream.getAttribute(CharTermAttribute.class).toString();
            if (term != null) result.add(term);
        }

        stream.close();

        return result;
    }

}
