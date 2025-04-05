package org.godzilla5hrimp.quizlet.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;

import org.godzilla5hrimp.quizlet.service.quizSession.QuizSession;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QuizSessionDao {

    EntityManager em;

    public QuizSessionDao() {
        this.em = JpaUtil.getEntityManagerFactory().createEntityManager();
    }

    public boolean saveSession(final QuizSession quizSession) {
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(quizSession);
            transaction.commit();
            return true;
        } catch(IllegalStateException | RollbackException e) {
            log.error("error when saving quizSession [{}] with exception {}", quizSession.getId(), e.getStackTrace());
            return false;
        }
    }

    public QuizSession getSession(final Long sessionId) {
         try {
            QuizSession result = em.find(QuizSession.class, sessionId);
            return result;
        } catch(IllegalArgumentException|IllegalStateException e) {
            log.error("error when fetching session [{}] with exception {}", sessionId, e.getStackTrace());
            return null;
        }
    }
}
