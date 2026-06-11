package it.unicam.cs.mpgc.rpg125936.repository;

import it.unicam.cs.mpgc.rpg125936.domain.user.Player;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Repository per gestire il salvataggio e il caricamento del player nel database
 * - Assicura che esista un solo salvataggio attivo nel gioco.
 * - Fornisce metodi per caricare la partita o verificare se esiste un salvataggio.
 */
public class PlayerRepository {

    public Player save(Player player) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();
            if (player.getId() == null) {
                Player existing = session.createQuery("FROM Player", Player.class)
                    .setMaxResults(1)
                    .uniqueResult();
                if (existing != null) {
                    player.setId(existing.getId());
                }
            }
            player = session.merge(player);
            t.commit();
            return player;
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
