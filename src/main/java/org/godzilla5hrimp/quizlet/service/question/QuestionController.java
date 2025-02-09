package org.godzilla5hrimp.quizlet.service.question;

import org.godzilla5hrimp.quizlet.service.quiz.Quiz;

import io.javalin.Javalin;

public class QuestionController {
    private Javalin workingInstance;
    private Quiz quiz;

    void QuestionController(final Javalin instance, final Quiz quiz) {
        this.workingInstance = instance;
        this.quiz = quiz;
    }
}