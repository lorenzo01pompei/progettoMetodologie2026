package it.unicam.cs.mpgc.rpg125936.service.game;

import it.unicam.cs.mpgc.rpg125936.domain.location.Mondo;
import it.unicam.cs.mpgc.rpg125936.domain.user.Enemy;
import it.unicam.cs.mpgc.rpg125936.repository.EnemyRepository;
import it.unicam.cs.mpgc.rpg125936.repository.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/// Classe che si occupa di initializzare i mondi ognuno con i suoi contenuti
public class WorldInitializer {

    private final EnemyRepository enemyRepository = new EnemyRepository();

    /**
     * Inizializza il mondo con i suoi nemici.
     * Se esistono nemici persistiti per questo mondo li carica dal DB;
     * altrimenti crea i nemici di default, li equipaggia e li salva.
     *
     * @param mondo il mondo da inizializzare
     */
    public void init(Mondo mondo) {
        List<Enemy> existsEnemies = enemyRepository.findByWorld(mondo.getName());
        if (!existsEnemies.isEmpty()) {
            mondo.setEnemies(existsEnemies);
        } else {
            List<Enemy> defaultEnemies = mondo.createDefaultEnemies();
            persistEnemies(defaultEnemies, mondo.getName());
            mondo.setEnemies(defaultEnemies);
        }
    }

    /**
     * Salva la lista di nemici su DB all'interno di una transazione.
     * Prima del salvataggio ogni nemico viene equipaggiato tramite equipDefault()
     *
     * @param enemies   nemici da salvare
     * @param worldName nome del mondo a cui appartengono
     */
    private void persistEnemies(List<Enemy> enemies, String worldName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();

            for (Enemy e : enemies) {
                e.equipDefault();
                e.setWorldName(worldName);
                session.persist(e);
            }
            t.commit();

        }
    }
}
