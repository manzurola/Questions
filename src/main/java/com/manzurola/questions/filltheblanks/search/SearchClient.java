package com.manzurola.questions.filltheblanks.search;

import com.manzurola.questions.filltheblanks.Question;
import org.elasticsearch.action.index.IndexResponse;

import java.util.List;

/**
 * Created by guym on 17/05/2017.
 */
public interface SearchClient {

    IndexResponse index(Question document) throws Exception;

    List<Question> search(SearchQuery searchQuery) throws Exception;
}
