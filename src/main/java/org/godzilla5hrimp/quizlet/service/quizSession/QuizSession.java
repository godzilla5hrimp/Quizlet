package org.godzilla5hrimp.quizlet.service.quizSession;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.godzilla5hrimp.quizlet.service.quiz.Quiz;

import com.google.gson.JsonObject;

import io.javalin.websocket.WsContext;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@Entity
@Table(name = "quiz_session")
public class QuizSession {    
    @Id
    private UUID id;
    @Column(name = "quiz_id")
    private UUID quizId;
    @Column(name = "users_connected")
    private String usersConnected;
    @Column(name = "date_started")
    private Date dateStarted;
    @Column(name = "date_ended")
    private Date dateEnded;
     
    private ArrayList<WsContext> usersSessions;
    private QuizSessionState state;
    private Integer quizStep = -1;
    private Integer questionsNumber;

    public QuizSession(final Quiz quizData) {
        this.questionsNumber = 0;
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

    public void sendUpdate(final Quiz quizData) {
        for (WsContext context : usersSessions) {
            context.send("update");
        }
    }

    private JsonObject prepareUpdateObject(final Quiz quizData) {
        JsonObject result = new JsonObject();
        
        return result;
    }
}
