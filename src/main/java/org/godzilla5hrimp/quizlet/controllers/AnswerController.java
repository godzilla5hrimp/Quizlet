package org.godzilla5hrimp.quizlet.controllers;

import java.util.List;
import java.util.UUID;

import org.godzilla5hrimp.quizlet.db.AnswerDao;
import org.godzilla5hrimp.quizlet.service.answer.Answer;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import io.javalin.Javalin;
import io.javalin.http.Context;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AnswerController {
    
    private AnswerDao answerDao;

     public AnswerController(Javalin app) {
        this.answerDao = new AnswerDao();
        app.post("/answer", this::saveAnswer);
        app.put("/answer/{answerId}", this::updateAnswer);
        app.get("/answer/all", this::getAll);
        app.get("/answer/{answerId}", this::getAnswer);
        app.delete("/answer/{answerId}", this::deleteAnswer);
    }

    public void saveAnswer(final Context ctx) {
        try {
            Answer answerToUpdate = new Gson().fromJson(ctx.body(), Answer.class);
            if (!answerToUpdate.equals(null)) {
                answerDao.saveAnswer(answerToUpdate);
            } else {
                JsonObject errObject = new JsonObject();
                errObject.add("error", new Gson().toJsonTree("error fetching answer"));
                ctx.json(errObject);
            }
        } catch(Exception e) {
            log.error("error saving answer with exception {}", e.getStackTrace().toString());
        }
    }

    public void getAnswer(final Context ctx) {
        try {
            Answer result = answerDao.getAnswer(UUID.fromString(ctx.pathParam("answerId")));
            if (!result.equals(null)) {
                ctx.json(result);
            } else {
                JsonObject errObject = new JsonObject();
                errObject.add("error", new Gson().toJsonTree("error fetching question"));
                ctx.json(errObject);
            }
        } catch(Exception e) {
            log.error("error fetching answer {} with exception {}", ctx.pathParam("answerId"), e);
        }
    }

    public void updateAnswer(final Context ctx) {
        try {
            Answer answerToUpdate = new Gson().fromJson(ctx.body(), Answer.class);
            if (!answerToUpdate.equals(null)) {
                answerToUpdate.setId(UUID.fromString(ctx.pathParam("answerId")));
                answerDao.updateAnswer(answerToUpdate);
            } else {
                JsonObject errObject = new JsonObject();
                errObject.add("error", new Gson().toJsonTree("error fetching answer"));
                ctx.json(errObject);
            }
        } catch(Exception e) {
            log.error("error saving answer with exception {}", e.getStackTrace().toString());
        }
    }

    public void getAll(final Context ctx) {
        try {
            List<Answer> result = answerDao.getAll();
            if (!result.equals(null)) {
                ctx.json(result);
            } else {
                ctx.status(500);
                ctx.result("internal server error");
            }
        } catch(Exception e) {
            log.error("error fetching all question entities with exception {} ", e);
        }
    }

    public void deleteAnswer(Context ctx) {
        try {
            UUID answerUuid = UUID.fromString(ctx.pathParam("answerId"));
            if (!answerUuid.equals(null)) {
                answerDao.deleteAnswer(answerUuid);
                ctx.status(200);
                ctx.result("success");
            } else {
                ctx.status(500);
                ctx.result("internal server error");
            }
        } catch(Exception e) {
            log.error("error fetching answer {} with exception {}", ctx.pathParam("answerId"), e);
        }
    }
}
