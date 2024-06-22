package com.askie01.repositories;

import com.askie01.factories.HibernateSessionFactory;
import lombok.Cleanup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.askie01.entities.User;

import java.util.Optional;

public class UserRepository {
    private final Logger log = LogManager.getLogger(UserRepository.class.getName());

    public void save(User user) {
        @Cleanup final Session session = new HibernateSessionFactory()
                .getSessionFactory()
                .openSession();
        final Transaction transaction = session.beginTransaction();
        session.persist(user);
        transaction.commit();
        log.info("Saved: '{}'", user);
    }

    public Optional<User> get(String login, String password) {
        @Cleanup final Session session = new HibernateSessionFactory()
                .getSessionFactory()
                .openSession();
        final User user = session
                .createQuery("from User where login = :login and password = :password", User.class)
                .setParameter("login", login)
                .setParameter("password", password)
                .uniqueResult();

        if (user == null) {
            log.warn("User not found for login: '{}''", login);
        } else {
            log.info("User found: '{}'.", user);
        }

        return Optional.ofNullable(user);
    }

    public void update(User user) {
        @Cleanup final Session session = new HibernateSessionFactory()
                .getSessionFactory()
                .openSession();
        final Transaction transaction = session.beginTransaction();
        session.merge(user);
        transaction.commit();
        log.info("Updated {}", user);
    }

    public void remove(User user) {
        @Cleanup final Session session = new HibernateSessionFactory()
                .getSessionFactory()
                .openSession();
        final Transaction transaction = session.beginTransaction();
        session.remove(user);
        transaction.commit();
        log.info("Removed {}", user);
    }
}
