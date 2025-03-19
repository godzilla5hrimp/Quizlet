package org.godzilla5hrimp.quizlet.db;

import org.godzilla5hrimp.quizlet.service.question.Question;
import org.hibernate.Session;
import org.hibernate.Transaction;

import jakarta.persistence.RollbackException;
import jakarta.persistence.TransactionRequiredException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QuestionDao {
    
    private Session session;

    public QuestionDao() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }

    public boolean saveQuestion(final Question question) {
        try {
            Transaction transaction = session.beginTransaction();
            session.persist(question);
            transaction.commit();
            session.close();
            return true;
        } catch(IllegalStateException | RollbackException e) {
            log.error("error when saving question [{}] with exception {}", question.getId(), e.getStackTrace());
            return false;
        }
    } 

    public boolean deleteQuestion(final Question question) {
        try {
            Transaction transaction = session.beginTransaction();
            session.remove(question);
            transaction.commit();
            session.close();
            return true;
        } catch(IllegalStateException|IllegalArgumentException|TransactionRequiredException e) {
            log.error("error when deleting question [{}] with exception {}", question.getId(), e.getStackTrace());
            return false;
        }
    }

    public Question getQuestion(final Long questionId) {
        try {
            Transaction transaction = session.beginTransaction();
            Question result = session.find(Question.class, questionId);
            transaction.commit();
            session.close();
            return result;
        } catch(IllegalArgumentException|IllegalStateException e) {
            log.error("error when fetching question [{}] with exception {}", questionId, e.getStackTrace());
            return null;
        }
    }
}