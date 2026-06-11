package it.unicam.cs.mpgc.rpg125936.repository;

import it.unicam.cs.mpgc.rpg125936.domain.user.Enemy;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Repository per gestire l'accesso ai nemici nel database.
 * - Salva i progressi dei nemici ì
 * - Recupera i nemici di un world specifico per popolare le aree di gioco.
 */
public class EnemyRepository {

    public void save(Enemy enemy) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();
            session.merge(enemy);
            t.commit();
        }
    }

    public List<Enemy> findByWorld(String worldName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Enemy e WHERE e.worldName = :wn", Enemy.class)
                    .setParameter("wn", worldName)
                    .list();
        }
    }
}
