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
    private final Logger logger = LogManager.getLogger(UserRepository.class.getName());

    public void save(User user) {
        final SessionFactory sessionFactory = new HibernateSessionFactory("hibernate.cfg.xml").getSessionFactory();
        final Session session = sessionFactory.openSession();
        final Transaction transaction = session.beginTransaction();
        session.persist(user);
        transaction.commit();
        logger.info("Successfully saved user: '{}'", user);
    }

    public Optional<User> find(String username) {
        final User user = new HibernateSessionFactory("hibernate.cfg.xml")
                .getSessionFactory()
                .openSession()
                .createQuery("from User where username = :username", User.class)
                .setParameter("username", username)
                .uniqueResult();

        if (user == null) {
            logger.warn("User '{}' not found", username);
        } else {
            logger.info("Found user: '{}'", user);
        }

        return Optional.ofNullable(user);
    }
}
