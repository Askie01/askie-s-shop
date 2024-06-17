package com.askie01.repositories;

import com.askie01.factories.HibernateSessionFactory;
import jakarta.servlet.ServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.askie01.users.User;

import java.time.LocalDate;
import java.util.Optional;

public class UserRepository {
    private final Logger log = LogManager.getLogger(UserRepository.class.getName());

    public void save(User user) {
        final SessionFactory sessionFactory = new HibernateSessionFactory().getSessionFactory();
        final Session session = sessionFactory.openSession();
        final Transaction transaction = session.beginTransaction();
        session.persist(user);
        transaction.commit();
        log.info("Saved: '{}'", user);
    }

    public Optional<User> get(String login, String password) {
        final User user = new HibernateSessionFactory()
                .getSessionFactory()
                .openSession()
                .createQuery("from User where login = :login and password = :password", User.class)
                .setParameter("login", login)
                .setParameter("password", password)
                .uniqueResult();

        if (user == null) {
            log.warn("Login: '{}' doesn't exist.'", login);
        } else {
            log.info("Found: '{}'.", user);
        }

        return Optional.ofNullable(user);
    }

    public User get(ServletRequest request) {
        final String login = request.getParameter("login");
        final String password = request.getParameter("password");
        final String username = request.getParameter("username");
        final String firstName = request.getParameter("firstName");
        final String lastName = request.getParameter("lastName");
        final String email = request.getParameter("email");
        final String phone = request.getParameter("phone");
        final LocalDate birthdate = LocalDate.parse(request.getParameter("birthdate"));

        return new User(login, password, username, firstName, lastName, email, phone, birthdate);
    }
}
