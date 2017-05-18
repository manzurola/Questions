package com.manzurola.questions.domain;

/**
 * Created by guym on 16/05/2017.
 */
public class QuestionServiceImpl implements QuestionService {

    private final ElasticsearchService elasticsearch;

    public QuestionServiceImpl(ElasticsearchService elasticsearch) {
        this.elasticsearch = elasticsearch;
    }

    public void addQuestion(Question question) throws Exception{
        elasticsearch.index(new QuestionDocument(question));
    }

    private static class QuestionDocument implements ElasticsearchDocument<Question> {

        private final Question question;

        public QuestionDocument(Question question) {
            this.question = question;
        }

        public String getId() {
            return question.getId();
        }

        public String getIndex() {
            return "questions_en";
        }

        public String getType() {
            return null;
//            return question.getType();
        }

        public Question getObject() {
            return question;
        }
    }

}
