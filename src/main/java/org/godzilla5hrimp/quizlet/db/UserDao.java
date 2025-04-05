package org.godzilla5hrimp.quizlet.db;

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
            em.persist(user);
            transaction.commit();
            return true;
        } catch(IllegalStateException | RollbackException e) {
            log.error("error when saving user [{}] with exception {}", user.getId(), e.getStackTrace());
            return false;
        }
    } 

}
