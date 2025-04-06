package org.godzilla5hrimp.quizlet.controllers;

import java.util.List;
import java.util.UUID;

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
        //todo: change to put request
        app.put("/quiz/{quizId}", this::updateQuiz);
        app.post("/quiz", this::saveQuiz);
        app.get("/quiz/all", this::getAll);
        app.get("/quiz/{quizId}", this::getQuiz);
        app.delete("/quiz/{quizId}", this::delete);
    }
    
    public void saveQuiz(final Context ctx) {
        // check for 
        Quiz quiz = new Gson().fromJson(ctx.body(), Quiz.class);
        if (!quiz.equals(null)) {
            quizDao.saveQuiz(quiz);
            ctx.status(200);
            ctx.result("success");
        } else {
            ctx.status(500);
            ctx.result("internal server error");
        }
    }

    public void getQuiz(final Context ctx) {
        try {
            Quiz result = quizDao.getQuiz(ctx.pathParam("quizId"));
            if (!result.equals(null)) {
                ctx.json(result);
            } else {
                JsonObject errObject = new JsonObject();
                errObject.add("error", new Gson().toJsonTree("error fetching quiz"));
                ctx.json(errObject);
            }
        } catch(Exception e) {
            log.error("error fetching quiz with exception {}", e);
        }
    }

    public void updateQuiz(final Context ctx) {
         Quiz quiz = new Gson().fromJson(ctx.body(), Quiz.class);
         if (!quiz.equals(null)) {
            quiz.setId(UUID.fromString(ctx.pathParam("quizId")));
            quizDao.updateQuiz(quiz);
            ctx.status(200);
            ctx.result("success");
         } else {
             ctx.status(500);
             ctx.result("internal server error");
         }
    }

    public void getAll(final Context ctx) {
        try {
            List<Quiz> result = quizDao.getAll();
            if (!result.equals(null)) {
                ctx.json(result);
            } else {
                JsonObject errObject = new JsonObject();
                errObject.add("error", new Gson().toJsonTree("error fetching quiz"));
                ctx.json(errObject);
            }
        } catch(Exception e) {
            log.error("error fetching all quizes {}", e);
        }
    }
    
    public void delete(final Context ctx) {
        try {
            final UUID quizUuid = UUID.fromString(ctx.pathParam("quizId"));
            if (!quizUuid.equals(null)) {
                quizDao.deleteQuiz(quizUuid);
                ctx.status(200);
                ctx.result("success");
            } else {
                ctx.status(500);
                ctx.result("internal server error");
            }
        } catch(Exception e) {
            ctx.status(500);
            ctx.result("internal server error");
            log.error("error fetching all quizes {}", e);
        }
    }

}
