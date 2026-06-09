package it.unicam.cs.mpgc.rpg125936.service.game;

import it.unicam.cs.mpgc.rpg125936.domain.location.Mondo;
import it.unicam.cs.mpgc.rpg125936.domain.user.Boss;
import it.unicam.cs.mpgc.rpg125936.domain.user.Colosso;
import it.unicam.cs.mpgc.rpg125936.domain.user.Enemy;
import it.unicam.cs.mpgc.rpg125936.domain.user.Mago;
import it.unicam.cs.mpgc.rpg125936.repository.EnemyRepository;
import it.unicam.cs.mpgc.rpg125936.repository.HibernateUtil;
import it.unicam.cs.mpgc.rpg125936.repository.ItemRegistry;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class WorldInitializer {

    private final EnemyRepository enemyRepository = new EnemyRepository();

    public void init(Mondo mondo) {
        List<Enemy> existing = enemyRepository.findByWorld(mondo.getName());
        if (!existing.isEmpty()) {
            mondo.setEnemies(existing);
        } else {
            List<Enemy> defaultEnemies = mondo.createDefaultEnemies();
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                Transaction t = session.beginTransaction();
                for (Enemy e : defaultEnemies) {
                    equipEnemy(e);
                    e.setWorldName(mondo.getName());
                    session.persist(e);
                }
                t.commit();
            }
            mondo.setEnemies(defaultEnemies);
        }
    }

    private void equipEnemy(Enemy enemy) {
        if (enemy instanceof Boss) {
            enemy.addItem(ItemRegistry.getRandomGun());
            enemy.addItem(ItemRegistry.getRandomGun());
            enemy.addItem(ItemRegistry.getRandomSpell());
        } else if (enemy instanceof Colosso) {
            enemy.addItem(ItemRegistry.getRandomGun());
        } else if (enemy instanceof Mago) {
            enemy.addItem(ItemRegistry.getRandomSpell());
            enemy.addItem(ItemRegistry.getRandomSpell());
            enemy.addItem(ItemRegistry.getRandomGun());
        }
    }
}
