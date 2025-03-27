package org.godzilla5hrimp.quizlet.controllers;

import org.godzilla5hrimp.quizlet.db.QuizDao;
import org.godzilla5hrimp.quizlet.service.quiz.Quiz;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import io.javalin.Javalin;
import io.javalin.http.Context;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QuizController {

    private QuizDao quizDao;

    public QuizController(final Javalin app) {
        this.quizDao = new QuizDao();
        app.post("/quiz/{quizId}", this::saveQuiz);
        app.post("/quiz", this::saveQuiz);
        app.get("/quiz/{quizId}", this::getQuiz);
    }
    
    public void saveQuiz(final Context ctx) {
        // check for 
        JsonObject quizConfig = new Gson().fromJson(ctx.body(), JsonObject.class);
        ctx.body();
    }

    public void getQuiz(final Context ctx) {
        try {
            Quiz result = quizDao.getQuiz(Long.valueOf(ctx.pathParam("quizId")));
            if (!result.equals(null)) {
                ctx.json(result);
            } else {
                JsonObject errObject = new JsonObject();
                errObject.add("error", new Gson().toJsonTree("error fetching quiz"));
                ctx.json(errObject);
            }
        } catch(Exception e) {
            log.error("error fetching quiz with exception {}", e.getStackTrace().toString());
        }
    }

}
