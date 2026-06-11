package it.unicam.cs.mpgc.rpg125936.repository;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Classe per la connessione al database tramite Hibernate.
 * - Costruisce e mantiene un'unica istanza di SessionFactory (Singleton).
 * - Legge le configurazioni necessarie dal file hibernate.cfg.xml.
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
