package com.manzurola.questions.filltheblanks.rest.api.v1;

import com.manzurola.questions.filltheblanks.Question;
import com.manzurola.questions.filltheblanks.Repository;
import com.manzurola.questions.filltheblanks.SearchQuery;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by guym on 17/05/2017.
 */
@Path("/questions")
public class QuestionController {

    private final Repository service;

    public QuestionController(Repository service) {
        this.service = service;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Question> searchQuestions(@QueryParam("body") String bodyContains,
                                          @QueryParam("answer") String answerContains,
                                          @QueryParam("subject") String subjectContains) throws Exception {

        return service.searchQuestions(new SearchQuery(bodyContains, answerContains, subjectContains));
    }


}
