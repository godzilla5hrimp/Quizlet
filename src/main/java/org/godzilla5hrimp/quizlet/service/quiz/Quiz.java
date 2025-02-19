package org.godzilla5hrimp.quizlet.service.quiz;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.google.gson.JsonObject;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
@Entity
@Table(name = "quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(name = "quiz_name")
    private String quizName;
    @Column(name = "questions_list")
    private List<String> questionsList;
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

    public Quiz() {}

    public Quiz(final String quizName) {
        this.createdDate = Date.from(Instant.now());
        this.quizName = quizName.isEmpty() || quizName.equals(null) ? "Unnamed Quiz" : quizName;
    }
}
