package com.manzurola.questions.game;

import com.manzurola.questions.Choice;
import com.manzurola.questions.FillInTheBlanks;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guym on 19/07/2017.
 */
public class SingleChoiceSentenceGenerator implements SentenceGenerator {

    @Override
    public List<Sentence> generate(FillInTheBlanks question) {
        // precondition - each blank should have the same number of choices

        List<Sentence> sentences = new ArrayList<>();
        // create sentences as the number of blanks * number of choices
        int numOfBlanks = question.numOfBlanks();
        for (int blankIndex = 0; blankIndex < numOfBlanks; blankIndex++) {

            for (int choiceIndex = 0; choiceIndex < question.countChoicesAt(blankIndex); choiceIndex++) {
                sentences.add(generateSingleChoiceSentence(blankIndex, choiceIndex, question));
            }
        }

        return sentences;
    }

    private Sentence generateSingleChoiceSentence(int blankIndex, int choiceIndex, FillInTheBlanks question) {
        List<String> sentenceParts = new ArrayList<>();
        Choice selectedChoice = question.getChoiceAt(blankIndex, choiceIndex);
        boolean isCorrect = selectedChoice.isCorrect();
        int choiceAtPart = 0;

        List<String> parts = question.splitSentence();

        for (int i = 0; i < parts.size(); i++) {

            String part = parts.get(i);

            if (question.getBlankToken().equals(part)) {

                Choice choice = question.getChoicesForBlankAtSentencePart(i).get(choiceIndex);

                // this is a blank, if it matches the supplied blank index, replace it with the choice at choiceIndex

                if (choice.getIndex() == blankIndex) {

                    // this is our target blank, replace it with the choice. If it is correct, our sentence is correct

                    sentenceParts.add(choice.getText());
                    choiceAtPart = i;

                } else {

                    // this is not our target blank so we always choose the correct choice as the sentence part

                    sentenceParts.add(question.getCorrectChoiceForBlankAtSentencePart(i).getText());
                }
            } else {

                // fill up the rest of the parts

                sentenceParts.add(part);
            }
        }

        return new Sentence(sentenceParts, choiceAtPart, isCorrect);
    }
}
