package org.godzilla5hrimp.quizlet.db;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.persistence.TransactionRequiredException;

import org.godzilla5hrimp.quizlet.service.quiz.Quiz;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QuizDao {

    EntityManager em;

    public QuizDao() {
        this.em = JpaUtil.getEntityManagerFactory().createEntityManager();
    }

    public boolean saveQuiz(Quiz quiz) {
        try {
            // Transaction transaction = session.beginTransaction();
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            quiz.setCreatedDate(new Date().from(Instant.now()));
            quiz.setUpdatedDate(new Date().from(Instant.now()));
            em.persist(quiz);
            transaction.commit();
            log.info("saved quiz entity with id [{}]", quiz.getId());
            return true;
        } catch(IllegalStateException | RollbackException e) {
            log.error("error when saving quiz [{}] with exception: {} ", quiz.getId(), e);
            return false;
        }
    } 

    public boolean deleteQuiz(final Quiz quiz) {
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(quiz);
            transaction.commit();
            return true;
        } catch(IllegalStateException|IllegalArgumentException|TransactionRequiredException e) {
            log.error("error when deleting quiz [{}] with exception {}", quiz.getId(), e.getStackTrace());
            return false;
        }
    }

    public Quiz getQuiz(final String quizId) {
        try {
            Quiz result = em.find(Quiz.class, UUID.fromString(quizId));
            return result;
        } catch(IllegalArgumentException|IllegalStateException e) {
            log.error("error when fetching quiz [{}] with exception {}", UUID.fromString(quizId), e.getStackTrace());
            return null;
        }
    }

    public List<Quiz> getAll() {
        try {
            Query query = em.createQuery("select q from Quiz q");
            List<Quiz> result = query.getResultList();
            return result;
        } catch(IllegalArgumentException|IllegalStateException e) {
            log.error("error when fetching all quizes with exception {}", e.getStackTrace());
            return null;
        }
    }
}
