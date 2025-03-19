package org.godzilla5hrimp.quizlet.controllers;

import org.godzilla5hrimp.quizlet.db.QuestionDao;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.javalin.Javalin;

public class QuestionWebSocket {
    
    private QuestionDao questionDao;

    public QuestionWebSocket(final Javalin app) {
    }

    public void getQuestion(final Long questionId) {
        JsonObject result = JsonParser.parseString(new Gson().toJson(questionDao.getQuestion(questionId))).getAsJsonObject();
    }
}
