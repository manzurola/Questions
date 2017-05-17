package com.manzurola.questions.domain;

import org.elasticsearch.action.index.IndexResponse;

/**
 * Created by guym on 17/05/2017.
 */
public interface ElasticsearchService {

    IndexResponse index(ElasticsearchDocument document) throws Exception;
}
