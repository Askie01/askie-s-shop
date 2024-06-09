package factories;

import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import users.User;

@Getter
public class HibernateSessionFactory {
    private final SessionFactory sessionFactory;
    private final String configurationFile;

    public HibernateSessionFactory(String configurationFile) {
        this.configurationFile = configurationFile;
        this.sessionFactory = createSessionFactory(configurationFile);
    }

    private SessionFactory createSessionFactory(String configurationFile) {
        final Configuration config = new Configuration()
                .configure(configurationFile)
                .addAnnotatedClass(User.class);

        final ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(config.getProperties())
                .build();

        return config.buildSessionFactory(registry);
    }
}

