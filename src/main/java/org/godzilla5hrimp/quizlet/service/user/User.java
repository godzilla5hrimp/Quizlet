package org.godzilla5hrimp.quizlet.service.user;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

//TODO: check stack-auth for potential user login implementation
// with OAuth
@Getter
@Setter
@Entity
@Table(name = "quiz_user")
public class User {
    @Id
    private UUID id;

    @Column(name = "user_email")
    private String userEmail;

    public User() {
        this.id = UUID.randomUUID();
    }
}
