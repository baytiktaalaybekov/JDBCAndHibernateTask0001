package peaksoft.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import peaksoft.model.User;

import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;

public class DatabaseConf {

        public static EntityManager getEntityManager () {
            Configuration configuration = new Configuration();
            configuration.setProperty(DRIVER, "org.postgresql.Driver");
            configuration.setProperty(URL, "jdbc:postgresql://localhost:5432/postgres");
            configuration.setProperty(USER, "postgres");
            configuration.setProperty(PASS, "12345");
            configuration.setProperty(HBM2DDL_AUTO, "create");
            configuration.setProperty(DIALECT, "org.hibernate.dialect.PostgreSQL9Dialect");
            configuration.setProperty(SHOW_SQL, "true");
            configuration.addAnnotatedClass(User.class);
            configuration.setProperty(FORMAT_SQL,"true");
            return configuration.buildSessionFactory().createEntityManager();
        }

}
