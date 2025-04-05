package org.godzilla5hrimp.quizlet.controllers;

import java.util.List;
import java.util.UUID;

import org.godzilla5hrimp.quizlet.db.QuestionDao;
import org.godzilla5hrimp.quizlet.service.question.Question;

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
        app.put("/question/{questionId}", this::updateQuestion);
        app.get("/question/all", this::getAllQuestions);
        app.get("/question/{questionId}", this::getQuesiton);
    }

    public void saveQuestion(final Context ctx) {
        try {
            Question questionToSave = new Gson().fromJson(ctx.body(), Question.class);
            if (!questionToSave.equals(null)) {
                questionDao.saveQuestion(questionToSave);
            } else {
                ctx.json("error");
            }
        } catch(Exception e) {
            log.error("error saving question with exception {}", e);
        }
    }

    public void getQuesiton(final Context ctx) {
        try {
            Question result = questionDao.getQuestion(Long.valueOf(ctx.pathParam("questionId")));
            if (!result.equals(null)) {
                ctx.json(result);
            } else {
                JsonObject errObject = new JsonObject();
                errObject.add("error", new Gson().toJsonTree("error fetching question"));
                ctx.json(errObject);
            }
        } catch(Exception e) {
            log.error("error fetching question with exception {}", e.getStackTrace().toString());
        }
    }

    public void updateQuestion(Context ctx) {
        try {
            Question questionToUpdate = new Gson().fromJson(ctx.body(), Question.class);
            questionToUpdate.setId(UUID.fromString(ctx.pathParam("questionId")));
            if (!questionToUpdate.equals(null)) {
                questionDao.updateQuestion(questionToUpdate);
            } else {
                ctx.json("error");
            }
        } catch(Exception e) {
            log.error("error saving question with exception {}", e);
        }
    }

    public void getAllQuestions(Context ctx) {
        try {
            List<Question> result = questionDao.getAll();
            if (!result.equals(null)) {
                ctx.json(result);
            } else {
                JsonObject errObject = new JsonObject();
                errObject.add("error", new Gson().toJsonTree("error fetching question"));
                ctx.json(errObject);
            }
        } catch(Exception e) {
            log.error("error fetching question with exception {}", e);
        }
    }
}
