package org.godzilla5hrimp.quizlet.service.quizSession;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuizSession {
    private String id;
    private String quizId;
    private ArrayList<String> usersConnected;

    public QuizSession(final String id) {
        this.id = id;
        this.usersConnected = new ArrayList<>();
    }

    public void setQuizId(final String quizId) {
        this.quizId = quizId;
    }

    public ArrayList<String> getUsersConnected() {
        return this.usersConnected;
    }

    public boolean userConnected(final String userId) {
        this.usersConnected.add(userId);
        return true;
    }
}
