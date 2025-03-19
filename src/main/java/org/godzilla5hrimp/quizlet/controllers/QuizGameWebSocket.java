package org.godzilla5hrimp.quizlet.controllers;

import java.util.function.Consumer;

import org.eclipse.jetty.server.handler.ContextHandler.Context;
import org.godzilla5hrimp.quizlet.db.QuizSessionDao;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.javalin.Javalin;
import io.javalin.websocket.WsConfig;
import io.javalin.websocket.WsConnectContext;

public class QuizGameWebSocket {
    
    QuizSessionDao quizSessionDao;

    public QuizGameWebSocket(final Javalin app) {
        app.ws("quiz/{sessionId}", prepareConfig());
    }

    public Consumer<WsConfig> prepareConfig() {
        Consumer result = new Consumer<WsConfig>() {
        };
        WsConfig result = new WsConfig();
        result.onConnect(this::connectToSession);

        return result;
    }

    public void connectToSession(final WsConnectContext ctx) {

    }

    public void getQuestion(final Long questionId) {
        JsonObject result = JsonParser.parseString(new Gson().toJson(questionDao.getQuestion(questionId))).getAsJsonObject();
    }
}
