package org.godzilla5hrimp.quizlet.db;

import org.hibernate.Session;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QuizSessionDao {

    Session session;


    public QuizSessionDao() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }
}
