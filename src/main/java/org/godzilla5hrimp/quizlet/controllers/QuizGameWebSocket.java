package org.godzilla5hrimp.quizlet.controllers;

import java.util.function.Consumer;

import org.godzilla5hrimp.quizlet.db.QuizSessionDao;

import io.javalin.Javalin;
import io.javalin.websocket.WsConfig;

public class QuizGameWebSocket {
    
    QuizSessionDao quizSessionDao;

    public QuizGameWebSocket(final Javalin app) {
        app.ws("quiz/{sessionId}", prepareConfig());
    }

    public Consumer<WsConfig> prepareConfig() {
        QuizSessionWsConsumer result = new QuizSessionWsConsumer();
        return result;
    }
}
