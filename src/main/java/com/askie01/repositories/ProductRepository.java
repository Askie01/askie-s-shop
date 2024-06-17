package com.askie01.repositories;

import com.askie01.factories.HibernateSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.askie01.products.Product;

import java.util.Optional;

public class ProductRepository {
    private final Logger log = LogManager.getLogger(ProductRepository.class.getName());

    public void save(Product product) {
        final SessionFactory sessionFactory = new HibernateSessionFactory().getSessionFactory();
        final Session session = sessionFactory.openSession();
        final Transaction transaction = session.beginTransaction();
        session.persist(product);
        transaction.commit();
        log.info("Saved product: '{}'", product);
    }

    public Optional<Product> get(int productId) {
        final Product product = new HibernateSessionFactory()
                .getSessionFactory()
                .openSession()
                .get(Product.class, productId);

        if (product == null) {
            log.warn("Product id: '{}' doesn't exist.", productId);
        } else {
            log.info("Found product: '{}'", product);
        }

        return Optional.ofNullable(product);
    }
}
