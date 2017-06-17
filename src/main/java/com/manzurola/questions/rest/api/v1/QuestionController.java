package com.manzurola.questions.rest.api.v1;

import com.manzurola.questions.Question;
import com.manzurola.questions.RewriteTheSentence;
import com.manzurola.questions.data.Repository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by guym on 17/05/2017.
 */
@Path("/questions")
public class QuestionController {

    private final Repository repository;
    private final Map<String, Class<? extends Question>> mappedType;

    public QuestionController(Repository repository) {
        this.repository = repository;
        mappedType = new HashMap<>();
        mappedType.put("fill-in-the-blanks", FillInTheBlanks.class);
        mappedType.put("rewrite-the-sentence", RewriteTheSentence.class);
    }

    @GET
    @Path("/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Question> searchQuestions(@PathParam("type") String type, @QueryParam("answer") String answer) throws Exception {
        List<? extends Question> questions = repository.searchQuestionsByAnswer(answer, mappedType.get(type));
        List<Question> result = new ArrayList<>();
        for (Question question : questions) {
            result.add(question);
        }
        return result;
    }

}
