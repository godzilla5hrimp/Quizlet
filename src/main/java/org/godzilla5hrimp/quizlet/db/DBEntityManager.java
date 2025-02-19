package org.godzilla5hrimp.quizlet.db;

import java.util.Arrays;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;

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