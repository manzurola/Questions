package com.manzurola.questions.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by guym on 19/07/2017.
 */
public class Sentence implements Iterable {

    private List<SentenceSlice> slices;
    private List<Integer> blanks = new ArrayList<>();

    public Sentence(List<SentenceSlice> slices) {
        this.slices = slices;
        for (int i = 0; i < slices.size(); i++) {
            if (slices.get(i).isBlank()) blanks.add(i);
        }
    }

    @Override
    public Iterator iterator() {
        return slices.iterator();
    }

    public List<SentenceSlice> getBlanks() {
        return slices.stream().filter(SentenceSlice::isBlank).collect(Collectors.toList());
    }

    public int getIndexOfSlice(SentenceSlice slice) {
        return slices.indexOf(slice);
    }

    public Sentence replaceSlice(SentenceSlice source, SentenceSlice target) {
        int i = getIndexOfSlice(source);
        List<SentenceSlice> newSlices = new ArrayList<>();
        Collections.copy(newSlices, slices);
        newSlices.set(i, target);
        return new Sentence(newSlices);
    }

    public List<String> asTokenList() {
        return slices.stream().map(SentenceSlice::getText).collect(Collectors.toList());
    }
}
