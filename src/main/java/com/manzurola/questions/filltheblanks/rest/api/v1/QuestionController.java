package com.manzurola.questions.filltheblanks.rest.api.v1;

import com.manzurola.questions.filltheblanks.Question;
import com.manzurola.questions.filltheblanks.QuestionService;
import com.manzurola.questions.filltheblanks.search.SearchQuery;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by guym on 17/05/2017.
 */
@Path("/questions")
public class QuestionController {

    private final QuestionService service;

    public QuestionController(QuestionService service) {
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
