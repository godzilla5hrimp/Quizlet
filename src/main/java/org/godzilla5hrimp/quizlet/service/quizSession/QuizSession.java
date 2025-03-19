package org.godzilla5hrimp.quizlet.service.quizSession;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.godzilla5hrimp.quizlet.service.quiz.Quiz;

import com.google.gson.JsonObject;

import io.javalin.websocket.WsConnectContext;
import io.javalin.websocket.WsContext;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@Entity
public class QuizSession {    
    private Long id;
    private Long quizId;
    private ArrayList<Long> usersConnected;
    private Date timeStarted;
    private Date timeEnded; 
    private ArrayList<WsContext> usersSessions;
    private QuizSessionState state;
    private Integer quizStep = -1;
    private Integer questionsNumber;

    public QuizSession(final Quiz quizData) {
        this.questionsNumber = quizData.getQuestionsList().size();
    }

    public void connectUser(final WsContext userSessionId) {
        usersSessions.add(userSessionId);
    }

    public void disconnectUser(final WsContext userSessionId) {
        usersSessions.remove(userSessionId);
    }

    public boolean isUserConnected(final WsContext userSessionId) {
        return usersSessions.contains(userSessionId) ? true : false;
    }

    public void sendUpdate(final Quiz quiData) {
        for (WsContext context : usersSessions) {
            context.send("update");
        }
    }

    private JsonObject prepareUpdateObject(final Quiz quizData) {
        JsonObject result = new JsonObject();
        
        return result;
    }
}
