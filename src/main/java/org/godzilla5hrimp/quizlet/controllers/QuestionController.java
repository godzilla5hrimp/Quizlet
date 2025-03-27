package org.godzilla5hrimp.quizlet.controllers;

import org.godzilla5hrimp.quizlet.db.QuestionDao;
import org.godzilla5hrimp.quizlet.service.question.Question;
import org.godzilla5hrimp.quizlet.service.quiz.Quiz;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import io.javalin.Javalin;
import io.javalin.http.Context;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QuestionController {
    
    private QuestionDao questionDao;

    public QuestionController(Javalin app) {
        this.questionDao = new QuestionDao();
        app.post("/question", this::saveQuestion);
        app.get("/question/{questionId}", this::getQuesiton);
    }

    public void saveQuestion(final Context ctx) {
        try {
            Question questionToUpdate = new Gson().fromJson(ctx.body(), Question.class);
            if (!questionToUpdate.equals(null)) {
                questionDao.saveQuestion(questionToUpdate);
            } else {
                JsonObject errObject = new JsonObject();
                errObject.add("error", new Gson().toJsonTree("error fetching question"));
                ctx.json(errObject);
            }
        } catch(Exception e) {
            log.error("error fetching question with exception {}", e.getStackTrace().toString());
        }
    }

    public void getQuesiton(final Context ctx) {
        try {
            Question result = questionDao.getQuestion(Long.valueOf(ctx.pathParam("questionId")));
            if (!result.equals(null)) {
                ctx.json(result);questionId
            } else {
                JsonObject errObject = new JsonObject();
                errObject.add("error", new Gson().toJsonTree("error fetching question"));
                ctx.json(errObject);
            }
        } catch(Exception e) {
            log.error("error fetching question with exception {}", e.getStackTrace().toString());
        }
    }
}
