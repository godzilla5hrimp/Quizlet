package org.godzilla5hrimp.quizlet.service.quiz;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
@Entity
@Table(name = "quiz")
@XmlRootElement
public class Quiz implements Serializable {
    @Id
    private UUID id;
    @Column(name = "quiz_name")
    private String quizName;
    @Column(name = "questions_list")
    private String questionsList;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "updated_date")
    private Date updatedDate;
    @Column(name = "user_created")
    private String userCreated;
    @Column(name = "user_updated")
    private String userUpdated;
    @Column(name = "quiz_version")
    private Long quizVersion;
    @Column(name = "is_private")
    private boolean isPrivate;

    public Quiz() {
        this.id = UUID.randomUUID();
    }

    public Quiz(final String quizName) {
        this.id = UUID.randomUUID();
        this.createdDate = Date.from(Instant.now());
        this.quizName = quizName.isEmpty() || quizName.equals(null) ? "Unnamed Quiz" : quizName;
    }
}
