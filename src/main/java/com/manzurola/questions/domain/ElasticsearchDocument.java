package com.manzurola.questions.domain;

/**
 * Created by guym on 17/05/2017.
 */
public interface ElasticsearchDocument<T> {

    String getId();

    String getIndex();

    String getType();

    T getObject();
}
