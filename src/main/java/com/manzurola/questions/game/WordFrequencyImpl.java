package com.manzurola.questions.game;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by guym on 21/07/2017.
 */
public class WordFrequencyImpl implements WordFrequency {

    private Map<String, Double> frequencies = new HashMap<>();

    public WordFrequencyImpl(String filepath) throws IOException {
        List<String> words = Files.readAllLines(Paths.get(filepath));
        int total = words.size();
        for (double i = 0; i < total; i++) {
            frequencies.put(words.get((int)i), ((total - i) / total));
        }
    }

    @Override
    public double get(String word) {
        Double frequency = frequencies.get(word);
        return frequency != null ? frequency : 0;
    }

}
