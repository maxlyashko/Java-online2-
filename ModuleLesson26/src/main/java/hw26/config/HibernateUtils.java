package hw26.config;

import hw26.entity.Client;
import hw26.entity.Goods;
import hw26.entity.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import static jakarta.persistence.Persistence.createEntityManagerFactory;

public class HibernateUtils {
    private static SessionFactory factory;
    private static EntityManager entityManager;

    private static void configureFactory () {
        try {
            factory = new Configuration ( )
                    .addAnnotatedClass ( Client.class )
                    .addAnnotatedClass ( Goods.class )
                    .addAnnotatedClass ( Order.class )
                    .configure ( ).buildSessionFactory ( );
        } catch ( Throwable ex ) {
            System.err.println ( "Failed to create sessionFactory object." + ex );
            throw new ExceptionInInitializerError ( ex );
        }
    }

    private static void configureEntityManager () {
        EntityManagerFactory factory = createEntityManagerFactory ( "jdbc-hiber" );
        entityManager = factory.createEntityManager ( );
    }

    public static org.hibernate.SessionFactory getFactory () {
        if (factory == null) {
            configureFactory ( );
        }
        return factory;
    }

    public static EntityManager getEntityManager () {
        if (entityManager == null) {
            configureEntityManager ( );
        }
        return entityManager;
    }
}
