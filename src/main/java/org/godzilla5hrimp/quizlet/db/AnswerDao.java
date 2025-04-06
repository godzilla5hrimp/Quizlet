package org.godzilla5hrimp.quizlet.db;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;
import javax.persistence.TransactionRequiredException;

import org.godzilla5hrimp.quizlet.service.answer.Answer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AnswerDao {
        
    private EntityManager em;

    public AnswerDao() {
        this.em = JpaUtil.getEntityManagerFactory().createEntityManager();
    }

    public boolean saveAnswer(final Answer answer) {
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(answer);
            transaction.commit();
            return true;
        } catch(IllegalStateException | RollbackException e) {
            log.error("error when saving answer [{}] with exception {}", answer.getId(), e);
            return false;
        }
    } 

    public boolean deleteAnswer(final UUID answerUuid) {
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            Answer answerToDelete = em.find(Answer.class, answerUuid);
            em.remove(answerToDelete);
            transaction.commit();
            return true;
        } catch(IllegalStateException|IllegalArgumentException|TransactionRequiredException e) {
            log.error("error when deleting answer [{}] with exception {}", answerUuid.toString(), e);
            return false;
        }
    }

    public Answer getAnswer(final UUID answerId) {
        try {
            Answer result = em.find(Answer.class, answerId);
            return result;
        } catch(IllegalArgumentException|IllegalStateException e) {
            log.error("error when fetching answer [{}] with exception {}", answerId, e);
            return null;
        }
    }

    public boolean updateAnswer(final Answer answer) {
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.merge(answer);
            transaction.commit();
            return true;
        } catch(IllegalStateException | RollbackException e) {
            log.error("error when saving answer [{}] with exception {}", answer.getId(), e);
            return false;
        }
    }

    public List<Answer> getAll() {
        try {
            List<Answer> result = em.createQuery("select a from Answer a").getResultList();
            return result;
        } catch(IllegalArgumentException|IllegalStateException e) {
            log.error("error when fetching all answers with exception {}", e);
            return null;
        }
    }
}
