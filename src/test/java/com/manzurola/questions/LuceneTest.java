package com.manzurola.questions;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.LowerCaseTokenizer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.junit.Test;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guym on 22/07/2017.
 */
public class LuceneTest {

    @Test
    public void tokenize() throws Exception {
        Analyzer analyzer = new Analyzer() {
            @Override
            protected TokenStreamComponents createComponents(String s) {
                Tokenizer source = new LowerCaseTokenizer();
                return new TokenStreamComponents(source);
            }
        };

        TokenStream stream = analyzer.tokenStream("", new StringReader("Hello! my name is Guy."));
        stream.reset();

        List<String> result = new ArrayList<>();

        while (stream.incrementToken()) {
            result.add(stream.getAttribute(CharTermAttribute.class).toString());
        }

        System.out.println(result);
    }
}
