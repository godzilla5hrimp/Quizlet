package org.godzilla5hrimp.quizlet.db;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY;

    static {
        // Create EntityManagerFactory with properties (no persistence.xml needed)
        ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("quizletPU");
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return ENTITY_MANAGER_FACTORY;
    }

    public static void shutdown() {
        if (ENTITY_MANAGER_FACTORY != null) {
            ENTITY_MANAGER_FACTORY.close();
        }
    }
}
