package org.godzilla5hrimp.quizlet.service.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

//TODO: check stack-auth for potential user login implementation
// with OAuth
@Entity
@Getter
@Setter
@Table(name = "quiz_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "user_email")
    private String userEmail;
}
