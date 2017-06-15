package com.manzurola.questions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guym on 07/06/2017.
 */
public class MultiChoiceRewriteTarget {
    private final List<WordChoiceGroup> choices;

    public MultiChoiceRewriteTarget(List<WordChoiceGroup> choices) {
        this.choices = new ArrayList<>(choices);
    }


}
