package org.godzilla5hrimp.quizlet.db.quiz;

import org.godzilla5hrimp.quizlet.service.quiz.Quiz;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.RollbackException;
import jakarta.persistence.TransactionRequiredException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QuizDao {

    EntityManager em;

    public QuizDao() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
        this.em = emf.createEntityManager();
    }

    public boolean saveQuiz(final Quiz quiz) {
        try {
            em.getTransaction().begin();
            em.persist(quiz);
            em.getTransaction().commit();
            return true;
        } catch(IllegalStateException | RollbackException e) {
            log.error("error when saving quiz [{}] with exception {}", quiz.getId(), e.getStackTrace());
            return false;
        }
    } 

    public boolean deleteQuiz(final Quiz quiz) {
        try {
            em.getTransaction().begin();
            em.remove(quiz);
            em.getTransaction().commit();
            return true;
        } catch(IllegalStateException|IllegalArgumentException|TransactionRequiredException e) {
            log.error("error when deleting quiz [{}] with exception {}", quiz.getId(), e.getStackTrace());
            return false;
        }
    }

    public Quiz getQuiz(final String quizId) {
        try {
            em.getTransaction().begin();
            Quiz result = em.find(Quiz.class, quizId);
            em.getTransaction().commit();
            return result;
        } catch(IllegalArgumentException|IllegalStateException e) {
            log.error("error when fetching quiz [{}] with exception {}", quizId, e.getStackTrace());
            return null;
        }
    }
}
