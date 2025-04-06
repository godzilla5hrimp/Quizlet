package org.godzilla5hrimp.quizlet.db;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;
import javax.persistence.TransactionRequiredException;

import org.godzilla5hrimp.quizlet.service.question.Question;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QuestionDao {
    
    private EntityManager em;

    public QuestionDao() {
        this.em = JpaUtil.getEntityManagerFactory().createEntityManager();
    }

    public boolean saveQuestion(final Question question) {
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(question);
            transaction.commit();
            return true;
        } catch(IllegalStateException | RollbackException e) {
            log.error("error when saving question [{}] with exception {}", question.getId(), e.getStackTrace());
            return false;
        }
    } 

    public boolean deleteQuestion(final UUID questionId) {
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            Question questionToDelete = em.find(Question.class, questionId);
            em.remove(questionToDelete);
            transaction.commit();
            return true;
        } catch(IllegalStateException|IllegalArgumentException|TransactionRequiredException e) {
            log.error("error when deleting question [{}] with exception {}", questionId.toString(), e.getStackTrace());
            return false;
        }
    }

    public boolean updateQuestion(final Question question) {
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.merge(question);
            transaction.commit();
            return true;
        } catch(IllegalStateException | RollbackException e) {
            log.error("error when saving question [{}] with exception {}", question.getId(), e.getStackTrace());
            return false;
        }
    }

    public Question getQuestion(final UUID questionId) {
        try {
            Question result = em.find(Question.class, questionId);
            return result;
        } catch(IllegalArgumentException|IllegalStateException e) {
            log.error("error when fetching question [{}] with exception {}", questionId, e);
            return null;
        }
    }

    public List<Question> getAll() {
        try {
            List<Question> result = em.createQuery("select q from Question q").getResultList();
            return result;
        } catch(IllegalArgumentException|IllegalStateException e) {
            log.error("error when fetching all questions with exception {}", e);
            return null;
        }
    }
}