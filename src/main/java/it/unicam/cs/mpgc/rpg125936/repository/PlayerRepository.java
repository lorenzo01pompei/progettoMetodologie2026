package it.unicam.cs.mpgc.rpg125936.repository;

import it.unicam.cs.mpgc.rpg125936.domain.user.Player;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PlayerRepository {

    public void save(Player player) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();
            session.merge(player);
            t.commit();
        }
    }

    public Player loadPlayer() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Player> players = session.createQuery("FROM Player", Player.class).list();
            if (players.isEmpty()){
                return null;
            }else{
                return players.get(0);
            }

        }
    }

    public boolean hasSavedGame() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Long count = session.createQuery("SELECT COUNT(p) FROM Player p", Long.class).getSingleResult();
            return count > 0;
        }
    }

}
