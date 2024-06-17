package com.askie01.factories;

import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import com.askie01.products.Product;
import com.askie01.users.User;

public class HibernateSessionFactory {
    @Getter
    private final SessionFactory sessionFactory;
    private final String configurationFile;

    public HibernateSessionFactory() {
        this.configurationFile = "hibernate.cfg.xml";
        final Configuration config = createDefaultConfiguration();
        this.sessionFactory = createSessionFactory(config);
    }

    public HibernateSessionFactory(Class... annotatedClasses) {
        this.configurationFile = "hibernate.cfg.xml";
        final Configuration config = createConfiguration(configurationFile, annotatedClasses);
        this.sessionFactory = createSessionFactory(config);
    }

    public HibernateSessionFactory(String configurationFile, Class... annotatedClasses) {
        this.configurationFile = configurationFile;
        final Configuration config = createConfiguration(configurationFile, annotatedClasses);
        this.sessionFactory = createSessionFactory(config);
    }

    private Configuration createDefaultConfiguration() {
        return new Configuration()
                .configure(configurationFile)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Product.class);
    }

    private Configuration createConfiguration(String configurationFile, Class... annotatedClasses) {
        Configuration config = new Configuration()
                .configure(configurationFile);

        for (Class annotatedClass : annotatedClasses) {
            config = config.addAnnotatedClass(annotatedClass);
        }

        return config;
    }

    private SessionFactory createSessionFactory(Configuration config) {
        final ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(config.getProperties())
                .build();

        return config.buildSessionFactory(registry);
    }
}
