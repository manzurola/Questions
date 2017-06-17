package com.manzurola.questions.data;

import com.manzurola.questions.Question;

import java.util.List;

/**
 * Created by guym on 15/06/2017.
 */
public interface Sorter<T extends Question> {

    List<T> sort(List<T> questions);
}
