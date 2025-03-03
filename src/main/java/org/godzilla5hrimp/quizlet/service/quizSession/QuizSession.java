package org.godzilla5hrimp.quizlet.service.quizSession;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import io.javalin.websocket.WsConnectContext;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "quiz_session")
public class QuizSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "quiz_id")
    private Long quizId;
    @Column(name = "users_connected")
    private ArrayList<Long> usersConnected;
    @Column(name = "time_started")
    private Date timeStarted;
    @Column(name = "time_ended")
    private Date timeEnded;
    private HashMap<String, WsConnectContext> usersSessions;

    public QuizSession(final String id) {
        this.id = id;
        this.usersConnected = new ArrayList<>();
        this.usersSessions = new HashMap<>();
    }

    public void setQuizId(final String quizId) {
        this.quizId = quizId;
    }

    public ArrayList<String> getUsersConnected() {
        return this.usersConnected;
    }

    public boolean userConnected(final String userId, final WsConnectContext userConnection) {
        this.usersConnected.add(userId);
        this.usersSessions.put(userId, userConnection);
        usersSessions.forEach((k, v) -> {
            v.send("{\"usersConnected\":" + usersSessions.size() + "}");
        });
        return true;
    }

    public void broadcastMessage(final String mesagseString) {
        usersSessions.get(usersSessions.keySet().stream().findFirst().get()).send(mesagseString);
    }

    public boolean userDisconected(final String userId) {
        if (!this.usersConnected.contains(userId)){
            return false;
        } else {
            this.usersConnected.remove(userId);
            this.usersSessions.remove(userId);
            System.out.println("Removed " + userId + ", number left" + usersConnected.size());
            usersSessions.forEach((userKey, userSession) -> {
                userSession.send("{\"usersConnected\":" + usersSessions.size() + "}");
            });
            return true;
        }
    }
}
