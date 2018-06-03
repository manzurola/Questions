package com.manzurola.prodigy.questions.rest.api.v1;

import com.manzurola.prodigy.questions.Question;
import com.manzurola.prodigy.questions.data.QuestionRepository;

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

    private final QuestionRepository questionRepository;
    private final Map<String, Class<? extends Question>> mappedType;

    public QuestionController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
        mappedType = new HashMap<>();
//        mappedType.put("fill-in-the-blanks", FillInTheBlanks.class);
//        mappedType.put("rewrite-the-sentence", RewriteTheSentence.class);
    }

    @GET
    @Path("/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Question> searchQuestions(@PathParam("type") String type, @QueryParam("answer") String answer) throws Exception {
        List<? extends Question> questions = questionRepository.searchQuestionsByAnswer(answer, mappedType.get(type));
        List<Question> result = new ArrayList<>();
        for (Question question : questions) {
            result.add(question);
        }
        return result;
    }

}
