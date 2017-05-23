package com.manzurola.questions.filltheblanks;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by guym on 21/05/2017.
 */
public interface QuestionReader {

    List<Question> read() throws IOException;

    String getVersion();
}
