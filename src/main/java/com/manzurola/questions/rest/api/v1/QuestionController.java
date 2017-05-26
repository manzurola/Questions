package com.manzurola.questions.rest.api.v1;

import com.manzurola.questions.FillInTheBlanks;
import com.manzurola.questions.Question;
import com.manzurola.questions.Repository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by guym on 17/05/2017.
 */
@Path("/questions")
public class QuestionController {

    private final Repository repository;

    public QuestionController(Repository repository) {
        this.repository = repository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<FillInTheBlanks> searchQuestions(@QueryParam("answer") String answer) throws Exception {
        return repository.searchQuestionsByAnswer(answer, FillInTheBlanks.class);
    }

}
