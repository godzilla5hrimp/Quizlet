package org.godzilla5hrimp.quizlet.db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class DBEntityManager {

    @PersistenceContext
    EntityManager em;

    public boolean saveEntity(Object entity) {
        try {
            em.persist(entity);
        } catch (Exception e) {
        }
        return false;
    }
}