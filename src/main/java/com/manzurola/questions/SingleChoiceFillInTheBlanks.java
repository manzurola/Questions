package com.manzurola.questions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guym on 19/07/2017.
 */
public class SingleChoiceFillInTheBlanks extends FillInTheBlanks {
    public SingleChoiceFillInTheBlanks(FillInTheBlanks target, int indexOfBlank) {
        super(target.getId(),
                newBody(target, indexOfBlank),
                newAnswerKey(target, indexOfBlank),
                target.getSubject(),
                target.getInstructions(),
                target.getSource(),
                target.getDifficultyLevel(),
                newChoices(target, indexOfBlank),
                target.getBlankToken());

    }

    private static String newBody(FillInTheBlanks target, int indexOfBlank) {
        List<String> parts = target.splitSentence();
        int blankCount = 0;
        StringBuffer newBody = new StringBuffer();
        for (int i = 0; i < parts.size(); i++) {
            String part = parts.get(i);
            if (target.isBlank(part)) {
                if (blankCount == indexOfBlank) {
                    newBody.append(part);
                } else {
                    newBody.append(target.getCorrectChoiceForBlankAtSentencePart(i).getText());
                }
                blankCount++;
            } else {
                newBody.append(part);
            }
        }

        return newBody.toString();
    }

    private static List<String> newAnswerKey(FillInTheBlanks target, int indexOfBlank) {
        List<String> newAnswerKey = new ArrayList<>();
        newAnswerKey.add(target.getAnswerKey().get(indexOfBlank));
        return newAnswerKey;
    }

    private static List<Choice> newChoices(FillInTheBlanks target, int indexOfBlank) {
        return target.getChoicesAt(indexOfBlank);
    }


}
