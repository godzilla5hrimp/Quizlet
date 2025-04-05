package org.godzilla5hrimp.quizlet.service.question;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "question")
public class Question {
    @Id
    private UUID id;
    @Column(name = "text_question")
    private String textQuestion;
    //todo: add verification for the media URl
    @Column(name = "media_url")
    private String mediaURL;
    @Column(name = "is_multiple_answer_question")
    private Boolean isMultipleAnswerQuestion;
    @Column(name = "answer_list")
    private String answerList;

    public Question() {
        this.id = UUID.randomUUID();
        this.answerList = "";
    }

    public Question(final String textQuestion) {
        this.id = UUID.randomUUID();
        this.textQuestion = textQuestion;
    }

    public Question(final String textQuestion, final String mediaURL) {
        this.id = UUID.randomUUID();
        this.textQuestion = textQuestion;
        this.mediaURL = mediaURL;
    }
}
