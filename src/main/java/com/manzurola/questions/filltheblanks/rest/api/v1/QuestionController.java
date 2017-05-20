package com.manzurola.questions.filltheblanks.rest.api.v1;

import com.manzurola.questions.filltheblanks.QuestionService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by guym on 17/05/2017.
 */
@Path("/questions")
@Produces(MediaType.APPLICATION_JSON)
public class QuestionController {

    private final QuestionService service;

    public QuestionController(QuestionService service) {
        this.service = service;
    }

    @GET
    @Path("search")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public QuestionSearchResponse searchQuestions(@QueryParam("lang") String language,
                                                  @QueryParam("body") String bodyContains,
                                                  @QueryParam("answer") String answerContains,
                                                  @QueryParam("subject") String subjectContains) {

        // get the result from the manage and convert it to an API response

        return null;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hi guy";
    }

}
