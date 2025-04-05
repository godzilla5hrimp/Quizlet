package org.godzilla5hrimp.quizlet.db;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;

import org.godzilla5hrimp.quizlet.service.user.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserDao {
    
    EntityManager em;

    public UserDao() {
        this.em = JpaUtil.getEntityManagerFactory().createEntityManager();
    }    

    public boolean saveUser(final User user) {
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(user);
            transaction.commit();
            return true;
        } catch(IllegalStateException | RollbackException e) {
            log.error("error when saving user [{}] with exception {}", user.getId().toString(), e.getStackTrace());
            return false;
        }
    } 

    public User getUser(final UUID userId) {
        try {
            return em.find(User.class, userId);
        } catch(IllegalStateException | RollbackException e) {
            log.error("error when fetching user [{}] with exception {}", userId.toString(), e.getStackTrace());
            return null;
        }
    }

    public boolean updateUser(final User user) {
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.merge(user);
            transaction.commit();
            return true;
        } catch(IllegalStateException | RollbackException e) {
            log.error("error when updating user [{}] with exception {}", user.getId().toString(), e.getStackTrace());
            return false;
        }
    }

    public boolean deleteUser(final UUID userId) {
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(userId);
            transaction.commit();
            return true;
        } catch(IllegalStateException | RollbackException e) {
            log.error("error when deleting user [{}] with exception {}", userId.toString(), e.getStackTrace());
            return false;
        }
    }

    public List<User> getAllUsers() {
        try {
            return em.createQuery("select u from User u").getResultList();
        } catch(IllegalStateException | RollbackException e) {
            log.error("error when fetching all users with exception {}", e.getStackTrace());
            return null;
        }
    }
}
