package org.godzilla5hrimp.quizlet.service.question;

import java.util.HashMap;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Question {
    private String id;
    private String textQuestion;
    private String mediaURL;
    private Boolean isMultipleAnswerQuestion;

    public Question(final String textQuestion) {
        this.textQuestion = textQuestion;
    }

    public Question(final String textQuestion, final String mediaURL) {
        this.textQuestion = textQuestion;
        this.mediaURL = mediaURL;
    }

    public String getTextQuestion() {
        return textQuestion;
    }

    public void setTextQuestion(String textQuestion) {
        this.textQuestion = textQuestion;
    }

    public String getMediaQuestions() {
        return mediaURL;
    }

    public void setMediaQuestions(final String mediaQuestions) {
        this.mediaURL = mediaURL;
    }
}
