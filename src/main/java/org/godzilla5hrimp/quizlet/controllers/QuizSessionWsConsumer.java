package org.godzilla5hrimp.quizlet.controllers;

import java.util.function.Consumer;

import org.godzilla5hrimp.quizlet.db.QuizDao;
import org.godzilla5hrimp.quizlet.db.QuizSessionDao;
import org.godzilla5hrimp.quizlet.service.quiz.Quiz;
import org.godzilla5hrimp.quizlet.service.quizSession.QuizSession;

import io.javalin.websocket.WsConfig;
import io.javalin.websocket.WsConnectContext;
import io.javalin.websocket.WsMessageContext;

public class QuizSessionWsConsumer implements Consumer<WsConfig> {

    QuizSessionDao sessionDao;
    QuizDao quizDao;

    @Override
    public void accept(WsConfig config) {
        config.onConnect(this::connectToSession);
        config.onMessage(this::checkAndHandleMessage);
    }
    
    public void connectToSession(WsConnectContext context) {
        final Long sessionId = Long.valueOf(context.queryParam("sessionId"));
        final Long quizId = Long.valueOf(context.queryParam("quizId"));
        final Long userId = Long.valueOf(context.queryParam("userId")); 
        context.send("connected to " + sessionId);
        QuizSession quizSession = sessionDao.getSession(sessionId);
    }

    public void checkAndHandleMessage(WsMessageContext ctx) {

    }

    public void validateMessage(final String message) {

    }
}
