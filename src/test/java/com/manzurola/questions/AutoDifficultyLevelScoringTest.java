package com.manzurola.questions;

import org.junit.Test;

/**
 * Created by guym on 17/07/2017.
 */
public class AutoDifficultyLevelScoringTest {

    @Test
    public void score() throws Exception {
        // load frequency list english words
        // for each question, calculate score as follows
        //      for each word, word_frequency =
        //          if in blank -> blank_word_weight * 1/frequency(blank_word)
        //          else read_word_weight * 1/frequency(read_word)
        //      average of all frequencies
        //      score = (average(word_frequency) * word_frequency_weight + (assigned_difficulty_level / total_difficulty_levels) * assigned_difficulty_weight) / 2
    }
}
