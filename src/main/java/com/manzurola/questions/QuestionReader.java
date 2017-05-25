package com.manzurola.questions;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

/**
 * Created by guym on 21/05/2017.
 */
public interface QuestionReader extends Closeable, Iterable<Question> {

    List<Question> readAll() throws IOException;

    Question readNext() throws IOException;

    String getParserVersion();
}
