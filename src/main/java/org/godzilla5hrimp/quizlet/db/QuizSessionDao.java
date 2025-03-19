package org.godzilla5hrimp.quizlet.db;

import org.godzilla5hrimp.quizlet.service.quizSession.QuizSession;
import org.hibernate.Session;
import org.hibernate.Transaction;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QuizSessionDao {

    Session session;

    public QuizSessionDao() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }

    public QuizSession getSession(final Long sessionId) {
         try {
            Transaction transaction = session.beginTransaction();
            QuizSession result = session.find(QuizSession.class, sessionId);
            transaction.commit();
            session.close();
            return result;
        } catch(IllegalArgumentException|IllegalStateException e) {
            log.error("error when fetching session [{}] with exception {}", sessionId, e.getStackTrace());
            return null;
        }
    }
}
