package repositories;

import factories.HibernateSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import users.User;

import java.util.Optional;

public class UserRepository {
    private final Logger log = LogManager.getLogger(UserRepository.class.getName());

    public void save(User user) {
        final SessionFactory sessionFactory = new HibernateSessionFactory("hibernate.cfg.xml").getSessionFactory();
        final Session session = sessionFactory.openSession();
        final Transaction transaction = session.beginTransaction();
        session.persist(user);
        transaction.commit();
        log.info("Successfully saved user: '{}'", user);
    }

    public Optional<User> find(String login, String password) {
        final User user = new HibernateSessionFactory("hibernate.cfg.xml")
                .getSessionFactory()
                .openSession()
                .createQuery("from User where login = :login and password = :password", User.class)
                .setParameter("login", login)
                .setParameter("password", password)
                .uniqueResult();

        if (user == null) {
            log.warn("Can't find username: '{}'.'", login);
        } else {
            log.info("Found user: '{}'.", login);
        }

        return Optional.ofNullable(user);
    }
}
