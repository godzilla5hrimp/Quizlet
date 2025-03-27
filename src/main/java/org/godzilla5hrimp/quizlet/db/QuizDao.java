package org.godzilla5hrimp.quizlet.db;

import org.godzilla5hrimp.quizlet.service.quiz.Quiz;
import org.hibernate.Session;
import org.hibernate.Transaction;

import jakarta.persistence.RollbackException;
import jakarta.persistence.TransactionRequiredException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QuizDao {

    Session session;

    public QuizDao() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }

    public boolean saveQuiz(final Quiz quiz) {
        try {
            Transaction transaction = session.beginTransaction();
            session.persist(quiz);
            transaction.commit();
            session.close();
            return true;
        } catch(IllegalStateException | RollbackException e) {
            log.error("error when saving quiz [{}] with exception {}", quiz.getId(), e.getStackTrace());
            return false;
        }
    } 

    public boolean deleteQuiz(final Quiz quiz) {
        try {
            Transaction transaction = session.beginTransaction();
            session.remove(quiz);
            transaction.commit();
            session.close();
            return true;
        } catch(IllegalStateException|IllegalArgumentException|TransactionRequiredException e) {
            log.error("error when deleting quiz [{}] with exception {}", quiz.getId(), e.getStackTrace());
            return false;
        }
    }

    public Quiz getQuiz(final Long quizId) {
        try {
            Transaction transaction = session.beginTransaction();
            Quiz result = session.find(Quiz.class, quizId);
            transaction.commit();
            session.close();
            return result;
        } catch(IllegalArgumentException|IllegalStateException e) {
            log.error("error when fetching quiz [{}] with exception {}", quizId, e.getStackTrace());
            return null;
        }
    }
}
