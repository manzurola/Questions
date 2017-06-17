package com.manzurola.questions;

import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * Created by guym on 21/05/2017.
 */
public interface QuestionReader<T extends Question> extends Closeable {

    List<T> readAll() throws IOException;
}
