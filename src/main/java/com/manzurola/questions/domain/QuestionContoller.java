package com.manzurola.questions.domain;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by guym on 17/05/2017.
 */
@Path("/questions")
@Produces(MediaType.APPLICATION_JSON)
public class QuestionContoller {

//    @GET
//    @Path("search")
//    public List<Question> searchQuestions(QuestionSearchRequest request) {
//        return null;
//    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hi guy";
    }
}
